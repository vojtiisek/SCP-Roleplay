package cz.vojtiisek.scprpsystem.Mine;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import cz.vojtiisek.scprpsystem.API;
import cz.vojtiisek.scprpsystem.SCPRP;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;

public class OreFunctioning implements Listener {
	private static SCPRP main;

	static LuckPerms api = LuckPermsProvider.get();

	public OreFunctioning(SCPRP main) {
		this.main = main;
	}

	public static Map<String, Map<Location, Long>> playerCooldowns = new HashMap<String, Map<Location, Long>>();
	public static Map<Location, Long> oreCooldowns = new HashMap<Location, Long>();

	@EventHandler
	public static void onOreMined(BlockBreakEvent event) {
		Block b = event.getBlock();
		Location loc = new Location(b.getLocation().getWorld(), b.getLocation().getX(), b.getLocation().getY(),
				b.getLocation().getZ());
		if (Mine.isOreInMine(loc)) {
			Player p = event.getPlayer();

			event.setCancelled(true);
			p.getWorld().playEffect(loc, Effect.VILLAGER_PLANT_GROW, 10);
			if (api.getUserManager().getUser(p.getName()).getPrimaryGroup().equals("class-d")
					|| api.getUserManager().getUser(p.getName()).getPrimaryGroup().equals("default") || p.hasPermission("scp.admin")) {
				if (!(getPlayerCooldowns().containsKey(p.getName())
						&& getPlayerCooldowns().get(p.getName()).containsKey(loc)
						&& (System.currentTimeMillis() - getPlayerCooldowns().get(p.getName()).get(loc) >= 600000))) {
					Random r = new Random();
					switch (b.getType()) {
					case COAL_ORE:
						int[] chancesCoal = { 1, 1, 2, 2, 2, 1, 1, 3, 3, 2, 1, 4, 1, 3 };
						ItemStack coal = new ItemStack(Material.COAL, chancesCoal[r.nextInt(chancesCoal.length)]);
						p.getInventory().addItem(coal);
						break;
					case IRON_ORE:
						int[] chancesIron = { 1, 1, 2, 2, 1, 1, 3, 2, 1, 4, 1, 3 };
						ItemStack iron = new ItemStack(Material.IRON_INGOT, chancesIron[r.nextInt(chancesIron.length)]);
						p.getInventory().addItem(iron);
						break;
					case DIAMOND_ORE:
						int[] chancesDiamond = { 1, 1, 2, 2, 2, 1, 1, 3, 3, 2, 1, 4, 1 };
						ItemStack diamond = new ItemStack(Material.DIAMOND, chancesDiamond[r.nextInt(chancesDiamond.length)]);
						p.getInventory().addItem(diamond);
						break;
					case EMERALD_ORE:
						int[] chancesEmerald = { 1, 1, 1, 2, 1, 1, 3, 2, 1, 4, 1, 3 };
						ItemStack emerald = new ItemStack(Material.EMERALD, chancesEmerald[r.nextInt(chancesEmerald.length)]);
						p.getInventory().addItem(emerald);
						break;
					default: 
						//API.sendConsoleCommand("discordsrv broadcast #839057785362448424 [SCPRP] onOreMined() default");
						//p.sendMessage(
						//		"§4§lThere has been an error when dropping the mined ore. Please, contact any Head/O5 personnel, or any Moderator/Admin as soon as possible.");
						break;
					}

					b.setType(Material.COBBLESTONE);
					if (getPlayerCooldowns().containsKey(p.getName())) {
						getPlayerCooldowns().get(p.getName()).put(loc, System.currentTimeMillis());
					} else {
						Map<Location, Long> tempOreMap = new HashMap<Location, Long>();
						tempOreMap.put(loc, System.currentTimeMillis());
						getPlayerCooldowns().put(p.getName(), tempOreMap);
					}
					getOreCooldowns().put(loc, System.currentTimeMillis());

					Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(main,
							() -> b.setType(Mine.getRandomOre()), 20 * 30);
				} else {
					p.sendMessage("§8§l[§4§l!§8§l]§c§o You can mine this ore in §e§l"
							+ API.formatTimeMS(
									System.currentTimeMillis() - getPlayerCooldowns().get(p.getName()).get(loc))
							+ "§c§o (minutes:seconds).");
				}
			} else {
				p.sendMessage("§8§l[§4§l!§8§l]§c§o Only §6§lClass-D§c§o personnel can mine ores!");
			}
		}
	}

	void checkOres() {
		new BukkitRunnable() {
			@Override
			public void run() {
				for (Entry<Location, Long> entry : getOreCooldowns().entrySet()) {
					Location loc = entry.getKey();
					if (System.currentTimeMillis() - getOreCooldowns().get(loc) >= 600000) {
						loc.getBlock().setType(Mine.getRandomLowTierOre());
						getOreCooldowns().remove(loc);
					}
				}
				
				System.out.println("[SCPRP] Ores resetted.");
			}
		}.runTaskTimer(main, 0, 600000);
	}

	public static Map<String, Map<Location, Long>> getPlayerCooldowns() {
		return playerCooldowns;
	}

	public static Map<Location, Long> getOreCooldowns() {
		return oreCooldowns;
	}
}
