package cz.vojtiisek.scprpsystem.Spawnpoints;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import cz.vojtiisek.scprpsystem.API;
import cz.vojtiisek.scprpsystem.SCPRP;
import cz.vojtiisek.scprpsystem.CellSystem.CellSystem;
import net.luckperms.api.LuckPermsProvider;

public class SpawnSys implements Listener {
	private static SCPRP main;

	public static Map<String, Location> spawns = new HashMap<String, Location>();
	public static Map<String, Location> respawns = new HashMap<String, Location>();
	public static Map<Player, Boolean> deaths = new HashMap<Player, Boolean>();
	
	public static List<String> respawning = new ArrayList<String>();

	public SpawnSys(SCPRP main) {
		this.main = main;
	}

	public static Map<String, Location> getSpawns() {
		return spawns;
	}

	public static Map<String, Location> getRespawns() {
		return respawns;
	}

	public static void addSpawns() {
		spawns.put("technic", new Location(Bukkit.getWorld("RPWorld"), 224, 206, 35));
		spawns.put("medic", new Location(Bukkit.getWorld("RPWorld"), 32, 215, 25));
		spawns.put("researcher", new Location(Bukkit.getWorld("RPWorld"), 44.437, 193, -27.413));
		spawns.put("security-officer", new Location(Bukkit.getWorld("RPWorld"), 15, 193, 11));
		spawns.put("storage-leader", new Location(Bukkit.getWorld("RPWorld"), -151.557, 204, 75.344));
		spawns.put("mtf-nu-7", new Location(Bukkit.getWorld("RPWorld"), -131.524, 207, 178.589));
		spawns.put("judge", new Location(Bukkit.getWorld("RPWorld"), -42.462, 176, 25.468));
		spawns.put("head-medic", new Location(Bukkit.getWorld("RPWorld"), 40, 215, 33));
		spawns.put("head-researcher", new Location(Bukkit.getWorld("RPWorld"), 62, 193, -31));
		spawns.put("head-security-officer", new Location(Bukkit.getWorld("RPWorld"), 9, 193, 28));
		spawns.put("mtf-nu-7-commander", new Location(Bukkit.getWorld("RPWorld"), -127.450, 207, 215.710));
		spawns.put("site-director", new Location(Bukkit.getWorld("RPWorld"), 73.568, 154, -270.451));
		spawns.put("o5-1", new Location(Bukkit.getWorld("RPWorld"), 80.482, 154, -252.447));
		spawns.put("o5-2", new Location(Bukkit.getWorld("RPWorld"), 73.499, 154, -234.533));
		spawns.put("o5-3", new Location(Bukkit.getWorld("RPWorld"), 74, 154, -252));
		spawns.put("o5-4", new Location(Bukkit.getWorld("RPWorld"), 80.489, 154, -234.452));
		spawns.put("o5-5", new Location(Bukkit.getWorld("RPWorld"), 87.507, 154, -252.554));
		spawns.put("o5-6", new Location(Bukkit.getWorld("RPWorld"), 87.492, 154, -234.539));
		spawns.put("o5-7", new Location(Bukkit.getWorld("RPWorld"), 80.407, 154, -270.559));
		spawns.put("the-administrator", new Location(Bukkit.getWorld("RPWorld"), 103, 154, -250));
	}

	public static void addRespawns() {
		respawns.put("medical1", new Location(Bukkit.getWorld("RPWorld"), 50, 186, 74));
		respawns.put("medical2", new Location(Bukkit.getWorld("RPWorld"), 64, 186, 90));
		respawns.put("medical3", new Location(Bukkit.getWorld("RPWorld"), 98, 186, 74));
		respawns.put("medical4", new Location(Bukkit.getWorld("RPWorld"), -4, 186, 95));
		respawns.put("medical5", new Location(Bukkit.getWorld("RPWorld"), -21, 186, 101));
	}

	public static void respawn(String name) {
		if (Bukkit.getPlayerExact(name) != null && Bukkit.getPlayerExact(name).isOnline()) {
			getRespawningPlayers().add(name);
			API.dropAllItems(name);
			Bukkit.getPlayerExact(name).setHealth(20);
			Bukkit.getPlayerExact(name).setSaturation(10);
			int items = 0;
			for(ItemStack iS : Bukkit.getPlayerExact(name).getInventory().getContents()) {
				if(iS != null) {
					if(!iS.getType().equals(Material.AIR)) items = items + 1;
				}
			}
			
			if (items == 0) {
				Bukkit.getPlayerExact(name).addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 300, 100));
				if (CellSystem.getJailedPlayers().contains(name)) {
					CellSystem.teleportToCell(name, "respawn1");
				} else {
					if (LuckPermsProvider.get().getUserManager().getUser(name).getPrimaryGroup()
							.equalsIgnoreCase("default")
							|| LuckPermsProvider.get().getUserManager().getUser(name).getPrimaryGroup()
									.equalsIgnoreCase("class-d")) {
						CellSystem.teleportToCell(name, "respawn2");
					} else {
						Random rand = new Random();
						int count = rand.nextInt((5 - 1) + 1) + 1;
						if (getRespawns().get("medical" + count) != null) {
							Bukkit.getPlayerExact(name).teleport(getRespawns().get("medical" + count));
						} else {
							Bukkit.getPlayerExact(name).teleport(getRespawns().get("medical1"));
							API.sendConsoleCommand(
									"discordsrv broadcast #839057785362448424 [SpawnSys] respawn(String name) null (count = "
											+ count + "), returning medical1");
						}
					}
				}
				
				if(getRespawningPlayers().indexOf(name) != -1) getRespawningPlayers().remove(name);
			} else {
				API.sendDebugMsg("size: " + items);
				respawn(name);
			}
		}
	}

	@EventHandler
	public void onDeath(PlayerDeathEvent e) {
		if (e.getEntity() instanceof Player) {
			Player p = (Player) e.getEntity();
			API.dropAllItems(p.getName());
			getDeaths().put(p, true);
		}
	}
	
	@EventHandler
	public void onDeath(PlayerPickupItemEvent e) {
		if(getRespawningPlayers().contains(e.getPlayer().getName())) e.setCancelled(true);
	}

	@EventHandler
	public void onRespawn(PlayerRespawnEvent e) {
		if (getDeaths().containsKey(e.getPlayer())) {
			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(SCPRP.getInstace(),
					() -> respawn(e.getPlayer().getName()), 20);
			getDeaths().remove(e.getPlayer());
		}
	}

	private Map<Player, Boolean> getDeaths() {
		return deaths;
	}

	private static List<String> getRespawningPlayers() {
		return respawning;
	}
}