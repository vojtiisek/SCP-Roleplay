package cz.vojtiisek.scprpsystem.Mine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;

import cz.vojtiisek.scprpsystem.API;
import cz.vojtiisek.scprpsystem.SCPRP;
import cz.vojtiisek.scprpsystem.files.MineOresFile;

public class Mine implements Listener {
	private static SCPRP main;

	public Mine(SCPRP main) {
		this.main = main;
	}

	static Map<String, List<Location>> mines = new HashMap<String, List<Location>>(); // hashmap<mine name, list<ore>>

	static List<String> creatingPlayers = new ArrayList<String>(); // list<player> - creation
	static List<String> removingPlayers = new ArrayList<String>(); // list<player> - remove
	static List<Location> oresRemoval = new ArrayList<Location>(); // list<player> - remove
	static List<Location> locations = new ArrayList<Location>();
	// list<player> - expand ; hashmap<player, list<selected blocks>> ->

	static boolean raid = false;

	@EventHandler
	public static void onRightClick(PlayerInteractEvent event) {
		if (event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
			if (event.getHand().equals(EquipmentSlot.HAND)) {
				Location loc;
				if (event.getClickedBlock().getType().equals(Material.WOOL)
						&& event.getClickedBlock().getData() == (byte) 14) {
					if (getCreatingPlayers().indexOf(event.getPlayer().getName()) != -1) {
						loc = new Location(event.getClickedBlock().getLocation().getWorld(),
								event.getClickedBlock().getLocation().getX(),
								event.getClickedBlock().getLocation().getY(),
								event.getClickedBlock().getLocation().getZ());
						getSelected().add(loc);
						loc.getBlock().setType(Material.WOOL);
						loc.getBlock().setData((byte) 5);
						event.getPlayer().sendMessage(
								"§8§l[§a§l!§8§l] §a§oBlock selected. To deselect it, right click the (now) lime wool.");
					}

				} else if (event.getClickedBlock().getType().equals(Material.WOOL)
						&& event.getClickedBlock().getData() == (byte) 5) {
					if (getCreatingPlayers().indexOf(event.getPlayer().getName()) != -1) {
						loc = new Location(event.getClickedBlock().getLocation().getWorld(),
								event.getClickedBlock().getLocation().getX(),
								event.getClickedBlock().getLocation().getY(),
								event.getClickedBlock().getLocation().getZ());
						if (getSelected().indexOf(loc) != -1) {
							getSelected().remove(loc);
							loc.getBlock().setType(Material.WOOL);
							loc.getBlock().setData((byte) 14);
							event.getPlayer().sendMessage("§8§l[§a§l!§8§l] §c§oBlock deselected.");
						}
					}

				} else if (event.getClickedBlock().getType().equals(Material.STONE)) {
					if (getCreatingPlayers().indexOf(event.getPlayer().getName()) != -1) {
						if (getSelected().size() > 0) {
							for (int i = 0; i < getSelected().size(); i++) {
							}
							getCreatingPlayers().remove(event.getPlayer().getName());
							int sizeName = getMines().size();
							sizeName = sizeName + 1;
							for (int i = 0; i < getSelected().size(); i++) {
								if (getMines().get("Mine" + sizeName) != null
										&& getMines().containsKey("Mine" + sizeName)) {
									getMines().get("Mine" + sizeName).add(getSelected().get(i));
								} else {
									List<Location> tempList = new ArrayList<Location>();
									tempList.add(getSelected().get(i));
									getMines().put("Mine" + sizeName, tempList);
								}
							}

							generateOres(getMines().get("Mine" + sizeName));
							save();
							event.getPlayer()
									.sendMessage("§8§l[§a§l!§8§l] §a§oMine created!, size: " + getMines().size());
							getSelected().clear();
						} else {
							if (getCreatingPlayers().indexOf(event.getPlayer().getName()) != -1)
								getCreatingPlayers().remove(event.getPlayer().getName());
							event.getPlayer()
									.sendMessage("§8§l[§c§l!§8§l] §c§oNo locations selected. No ores will be added.");
						}
					}

					if (getRemovingPlayers().indexOf(event.getPlayer().getName()) != -1) {
						int removed = 0;
						getRemovingPlayers().remove(event.getPlayer().getName());
						if (getOresToRemove().size() > 0) {
							for (Entry<String, List<Location>> entry : getMines().entrySet()) {
								for (int i = 0; i < entry.getValue().size(); i++) {
									for (Location locRem : getOresToRemove()) {
										if (locRem == entry.getValue().get(i)) {
											locRem.getBlock().setType(Material.COBBLESTONE);
											removed = removed + 1;
											entry.getValue().remove(locRem);
										}
									}
								}
							}
							if (removed == 1) {
								event.getPlayer().sendMessage("§8§l[§a§l!§8§l] §e§l" + removed + "§a§o ore removed!");
							} else if (removed > 1) {
								event.getPlayer().sendMessage("§8§l[§a§l!§8§l] §e§l" + removed + "§a§o ores removed!");
							} else {
								event.getPlayer().sendMessage(
										"§8§l[§c§l!§8§l] §c§oNo locations selected. No ores will be removed.");
							}
						} else {
							event.getPlayer()
									.sendMessage("§8§l[§c§l!§8§l] §c§oNo locations selected. No ores will be removed.");
						}
					}
				} else if (event.getClickedBlock().getType().equals(Material.COAL_ORE)
						|| event.getClickedBlock().getType().equals(Material.IRON_ORE)
						|| event.getClickedBlock().getType().equals(Material.DIAMOND_ORE)
						|| event.getClickedBlock().getType().equals(Material.EMERALD_ORE)) {
					if (getRemovingPlayers().indexOf(event.getPlayer().getName()) != -1) {
						Location clickedLoc = new Location(event.getClickedBlock().getLocation().getWorld(),
								event.getClickedBlock().getLocation().getX(),
								event.getClickedBlock().getLocation().getY(),
								event.getClickedBlock().getLocation().getZ());
						if (getOresToRemove().indexOf(clickedLoc) == -1) {
							clickedLoc.getBlock().setType(Material.WOOL);
							clickedLoc.getBlock().setData((byte) 14);
							getOresToRemove().add(clickedLoc);
							if (getOresToRemove().size() == 1)
								event.getPlayer().sendMessage(
										"§8§l[§a§l!§8§l] §e§l" + getOresToRemove().size() + "§a§o ore selected!");
							if (getOresToRemove().size() > 1)
								event.getPlayer().sendMessage(
										"§8§l[§a§l!§8§l] §e§l" + getOresToRemove().size() + "§a§o ores selected!");
						}
					}
				}
			}
		}
	}

	public static void generateOres(List<Location> locs) {
		for (Location loc : locs) {
			loc.getBlock().setType(getRandomOre());
		}
	}

	public static Material getRandomOre() {
		int[] chances = { 1, 1, 1, 1, 2, 1, 1, 2, 2, 2, 2, 2, 2, 1, 1, 3, 3, 2, 1, 1, 1, 4, 1, 2, 1, 3 };
		Random r = new Random();
		int index = r.nextInt(chances.length);
		Material material = Material.COAL_ORE;
		switch (chances[index]) {
		case 1:
			material = Material.COAL_ORE;
			break;
		case 2:
			material = Material.IRON_ORE;
			break;
		case 3:
			material = Material.DIAMOND_ORE;
			break;
		case 4:
			material = Material.EMERALD_ORE;
			break;
		default:
			API.sendConsoleCommand(
					"discordsrv broadcast #839057785362448424 [SCPRP] getRandomOre() default, default value - 1 (coal), value: " + index);
			break;
		}

		return material;
	}

	public static Material getRandomLowTierOre() {
		int[] chances = { 1, 1, 2 };
		Random r = new Random();
		int index = r.nextInt(chances.length);
		Material material = Material.COAL_ORE;
		switch (chances[index]) {
		case 1:
			break;
		case 2:
			material = Material.IRON_ORE;
			break;
		default:
			API.sendConsoleCommand(
					"discordsrv broadcast #839057785362448424 [SCPRP] getRandomLowTierOre() default, default value - 1 (coal)");
			break;
		}

		return material;
	}

	public static boolean isOreInMine(Location loc) {
		boolean bool = false;
		loop: for (Entry<String, List<Location>> entry : getMines().entrySet()) {
			for (Location forLoc : entry.getValue()) {
				if (forLoc.equals(loc)) {
					bool = true;
					break loop;
				}
			}
		}
		
		return bool;
	}

	public static List<String> getCreatingPlayers() {
		return creatingPlayers;
	}

	public static List<String> getRemovingPlayers() {
		return removingPlayers;
	}

	public static List<Location> getOresToRemove() {
		return oresRemoval;
	}

	public static List<Location> getSelected() {
		return locations;
	}

	public static Map<String, List<Location>> getMines() {
		return mines;
	}

	public static boolean raidStatus() {
		return raid;
	}

	public static void enableRaid() {
		raid = true;
	}

	public static void disableRaid() {
		raid = false;
	}

	public static void removeMine(String string) {
		for (Location loc : getMines().get(string)) {
			loc.getBlock().setType(Material.WOOL);
			loc.getBlock().setData((byte) 14);
		}

		getMines().remove(string);
	}

	public static void resetOres(Player p) {
		for (Entry<String, List<Location>> entry : getMines().entrySet()) {
			for (Location loc : entry.getValue()) {
				loc.getBlock().setType(getRandomLowTierOre());
			}
		}
		p.sendMessage("§8§l[§a§l!§8§l] §a§oOrese have been reset §2§lsuccessfully§a§o.");
	}

	public static void save() {
		MineOresFile.getMineOresFile().reload();
		MineOresFile.getMineOresFile().reloadConfig();

		for (Entry<String, List<Location>> entry : getMines().entrySet()) {
			for (int i = 0; i < entry.getValue().size(); i++) {
				MineOresFile.getMineOresFile().getConfig().set("Mines." + entry.getKey() + ".ore" + i + ".x",
						entry.getValue().get(i).getX());
				MineOresFile.getMineOresFile().getConfig().set("Mines." + entry.getKey() + ".ore" + i + ".y",
						entry.getValue().get(i).getY());
				MineOresFile.getMineOresFile().getConfig().set("Mines." + entry.getKey() + ".ore" + i + ".z",
						entry.getValue().get(i).getZ());
				MineOresFile.getMineOresFile().getConfig().set("Mines." + entry.getKey() + ".ore" + i + ".World",
						entry.getValue().get(i).getWorld().getName());
			}
		}

		MineOresFile.getMineOresFile().save();
		System.out.println("Mines saved succesfully.");
	}

	public static void load() {
		for (String name : MineOresFile.getMineOresFile().getConfig().getConfigurationSection("Mines").getKeys(false)) {
			System.out.println("Loading 1");
			getMines().put(name, new ArrayList<Location>());
			System.out.println("Loading 2, name: " + name);
			for (String str : MineOresFile.getMineOresFile().getConfig().getConfigurationSection("Mines." + name)
					.getKeys(false)) {
				System.out.println("Loading 3 - ore: " + str);
				System.out.println("Loading 4: " + "Mines." + name + " " + str);
				double x = MineOresFile.getMineOresFile().getConfig().getDouble("Mines." + name + "." + str + ".x");
				System.out.println("Loading 5: " + x);
				double y = MineOresFile.getMineOresFile().getConfig().getDouble("Mines." + name + "." + str + ".y");
				System.out.println("Loading 6: " + y);
				double z = MineOresFile.getMineOresFile().getConfig().getDouble("Mines." + name + "." + str + ".z");
				System.out.println("Loading 7: " + z + ", file: "
						+ MineOresFile.getMineOresFile().getConfig().getString("Mines." + name + "." + str + ".z"));
				// String worldName = "RPWorld";
				// MineOresFile.getMineOresFile().getConfig().getString("Mines." + name + "." +
				// str + ".World");
				// System.out.println("Loading 8: " + worldName);

				Location loc = new Location(Bukkit.getWorld("RPWorld"), x, y, z);
				System.out.println("Loading 9");
				getMines().get(name).add(loc);
				System.out.println("Loading 10");
			}
		}

		System.out.println(getMines());
		System.out.println("Mines loaded succesfully.");
	}
}