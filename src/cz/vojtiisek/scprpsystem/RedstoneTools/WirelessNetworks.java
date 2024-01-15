package cz.vojtiisek.scprpsystem.RedstoneTools;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.material.Lever;

import cz.vojtiisek.scprpsystem.Items;
import cz.vojtiisek.scprpsystem.SCPRP;
import cz.vojtiisek.scprpsystem.files.WirelessNetworksFile;

public class WirelessNetworks implements Listener {
	private static SCPRP main;

	public static List<String> playersLever = new ArrayList<String>();
	public static List<String> players = new ArrayList<String>();
	public static List<String> playersExpanding = new ArrayList<String>();
	public static Map<String, ArrayList<Location>> networks = new HashMap<String, ArrayList<Location>>();
	public static Map<String, ArrayList<Location>> networksExpanding = new HashMap<String, ArrayList<Location>>();
	public static Map<Location, String> data = new HashMap<Location, String>();
	public static Map<String, Location> levers = new HashMap<String, Location>();
	public static String networkName;

	public WirelessNetworks(SCPRP main) {
		this.main = main;
	}

	public static Map<String, ArrayList<Location>> getNetworks() {
		return networks;
	}

	public static Map<String, Location> getLevers() {
		return levers;
	}

	public static List<String> getPlayers() {
		return players;
	}

	public static Map<Location, String> getData() {
		return data;
	}

	public static List<String> getPlayersLever() {
		return playersLever;
	}

	public static void startCreation(String pName, String network) {
		networkName = network;
		Player p = Bukkit.getPlayerExact(pName);
		p.getInventory().addItem(Items.networkCompleteTool());
		p.sendMessage("§e§oStep 1§6§l: §b§oRight click the lever");
		p.sendMessage(
				"§a§oWhen the network is all selected, right click a block with the §a§lNetwork Completion tool §e§o(a diamond hoe) §a§oto finish the creation.");
	}

	@EventHandler
	public void onLeverUse(PlayerInteractEvent event) {
		if (event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
			if (event.getHand() == EquipmentSlot.HAND) {
				if (event.getClickedBlock().getType().equals(Material.LEVER)) {
					if (getPlayersLever().indexOf(event.getPlayer().getName()) != -1) {
						Location loc = new Location(event.getClickedBlock().getLocation().getWorld(),
								event.getClickedBlock().getLocation().getX(),
								event.getClickedBlock().getLocation().getY(),
								event.getClickedBlock().getLocation().getZ());

						getLevers().put(networkName, loc);
						getPlayers().add(event.getPlayer().getName());
						getPlayersLever().remove(event.getPlayer().getName());

						event.getPlayer().sendMessage(
								"§e§oStep 2§6§l: §b§oRight click the block, which should be powered by the lever. §c§oNo more levers can be added§b§o.");

						event.setCancelled(true);
					} else if (getPlayers().indexOf(event.getPlayer().getName()) != -1) {

						event.getPlayer().sendMessage(
								"§e§oStep 2§6§l: §b§oRight click the block, which should be powered by the lever. §c§oNo more levers can be added§b§o.");
						event.setCancelled(true);
					} else {
						Location loc3 = new Location(
								Bukkit.getWorld(event.getClickedBlock().getLocation().getWorld().getName()),
								event.getClickedBlock().getLocation().getX(),
								event.getClickedBlock().getLocation().getY(),
								event.getClickedBlock().getLocation().getZ());
						if (isLeverNetworked(loc3)) {
							Lever lever = (Lever) loc3.getBlock().getState().getData();
							if (!lever.isPowered()) {
								if (getNetworks().containsKey(findNetworkNameByLeverLoc(loc3)))
									sendRedstone(getNetworks().get(findNetworkNameByLeverLoc(loc3)), loc3);
								event.getPlayer().sendMessage("§8§l[§a§l!§8§l] §a§oWireless Network §f§l"
										+ findNetworkNameByLeverLoc(loc3) + " §a§otoggled §2§lON§a§o.");
							} else {
								offRedstone(getNetworks().get(findNetworkNameByLeverLoc(loc3)));
								event.getPlayer().sendMessage("§8§l[§a§l!§8§l] §a§oWireless Network §f§l"
										+ findNetworkNameByLeverLoc(loc3) + " §a§otoggled §4§lOFF§a§o.");
							}
						}
					}
				} else {
					if (getPlayersLever().indexOf(event.getPlayer().getName()) != -1)
						event.getPlayer().sendMessage("§e§oStep 1§6§l: §b§oRight click the lever");
					if (getPlayers().indexOf(event.getPlayer().getName()) != -1) {
						if (event.getPlayer().getInventory().getItemInMainHand().getType() == Material.DIAMOND_HOE) {
							if (getNetworks().containsKey(networkName) && getNetworks().get(networkName).size() <= 0) {
								event.getPlayer().sendMessage(
										"§e§lWARNING§4§l: §c§oYou did not select any blocks. The network will not be created.");
								if (getLevers().containsKey(networkName))
									getLevers().remove(networkName);
								if (getNetworks().containsKey(networkName))
									getNetworks().remove(networkName);
								if (getPlayers().indexOf(event.getPlayer().getName()) != -1)
									getPlayers().remove(event.getPlayer().getName());
								if (getPlayersLever().indexOf(event.getPlayer().getName()) != -1)
									getPlayersLever().remove(event.getPlayer().getName());
								if (event.getPlayer().getInventory().contains(Items.networkCompleteTool()))
									event.getPlayer().getInventory().remove(Items.networkCompleteTool());
							} else {
								event.getPlayer().sendMessage(
										"§a§lSuccess§f§l: §a§oNetwork §f§l" + networkName + " §a§ocreated.");
								if (getPlayers().indexOf(event.getPlayer().getName()) != -1)
									getPlayers().remove(event.getPlayer().getName());
								if (getPlayersLever().indexOf(event.getPlayer().getName()) != -1)
									getPlayersLever().remove(event.getPlayer().getName());
								if (event.getPlayer().getInventory().contains(Items.networkCompleteTool()))
									event.getPlayer().getInventory().remove(Items.networkCompleteTool());
							}
						} else {
							Location loc2 = new Location(event.getClickedBlock().getLocation().getWorld(),
									event.getClickedBlock().getLocation().getX(),
									event.getClickedBlock().getLocation().getY(),
									event.getClickedBlock().getLocation().getZ());
							if (getNetworks().containsKey(networkName)) {
								getNetworks().get(networkName).add(loc2);
							} else {
								getNetworks().put(networkName, new ArrayList<Location>());
								getNetworks().get(networkName).add(loc2);
							}
							event.getPlayer()
									.sendMessage("§a§oBlock selected§7§o: §b§lX §f§l"
											+ event.getClickedBlock().getLocation().getX() + " §b§lY §f§l"
											+ event.getClickedBlock().getLocation().getY() + " §b§lZ §f§l"
											+ event.getClickedBlock().getLocation().getZ() + " §b§lWorld: §f§l"
											+ event.getClickedBlock().getLocation().getWorld().getName());
						}
					} else if (getPlayersExpanding().indexOf(event.getPlayer().getName()) != -1) {
						if (event.getPlayer().getInventory().getItemInMainHand().getType() == Material.DIAMOND_HOE) {
							if (getNetworksExpanding().containsKey(networkName)
									&& getNetworksExpanding().get(networkName).size() <= 0) {
								event.getPlayer().sendMessage(
										"§e§lWARNING§4§l: §c§oYou did not select any blocks. The network will not be expanded.");
								if (getNetworksExpanding().containsKey(networkName))
									getNetworksExpanding().remove(networkName);
								if (getPlayersExpanding().indexOf(event.getPlayer().getName()) != -1)
									getPlayersExpanding().remove(event.getPlayer().getName());
								if (event.getPlayer().getInventory().contains(Items.networkCompleteTool()))
									event.getPlayer().getInventory().remove(Items.networkCompleteTool());
							} else {
								if(getNetworksExpanding().containsKey(networkName)) {
									getNetworks().put(networkName, getNetworksExpanding().get(networkName));
									for(Location locFor : getNetworksExpanding().get(networkName)) {
										getNetworks().get(networkName).add(locFor);
									}
								}
								event.getPlayer().sendMessage(
										"§a§lSuccess§f§l: §a§oNetwork §f§l" + networkName + " §a§expanded.");
								if (getPlayersExpanding().indexOf(event.getPlayer().getName()) != -1)
									getPlayersExpanding().remove(event.getPlayer().getName());
								if (event.getPlayer().getInventory().contains(Items.networkCompleteTool()))
									event.getPlayer().getInventory().remove(Items.networkCompleteTool());
							}
						} else {
							Location loc3 = new Location(event.getClickedBlock().getLocation().getWorld(),
									event.getClickedBlock().getLocation().getX(),
									event.getClickedBlock().getLocation().getY(),
									event.getClickedBlock().getLocation().getZ());
							if (getNetworksExpanding().containsKey(networkName)) {
								getNetworksExpanding().get(networkName).add(loc3);
							} else {
								getNetworksExpanding().put(networkName, new ArrayList<Location>());
								getNetworksExpanding().get(networkName).add(loc3);
							}
							event.getPlayer()
									.sendMessage("§a§oBlock selected§7§o: §b§lX §f§l"
											+ event.getClickedBlock().getLocation().getX() + " §b§lY §f§l"
											+ event.getClickedBlock().getLocation().getY() + " §b§lZ §f§l"
											+ event.getClickedBlock().getLocation().getZ() + " §b§lWorld: §f§l"
											+ event.getClickedBlock().getLocation().getWorld().getName());
						}
					}
				}
			}
		}
	}

	public static Map<String, ArrayList<Location>> getNetworksExpanding() {
		return networksExpanding;
	}

	public static boolean isLeverNetworked(Location loc) {
		boolean bool = false;
		if (getLevers().containsValue(loc))
			bool = true;
		return bool;
	}

	public static void sendRedstone(ArrayList<Location> list, Location loc1) {
		for (int i = 0; i < list.size(); i++) {
			Location loc = list.get(i);
			Location locData = new Location(Bukkit.getWorld(loc.getWorld().getName()), loc.getX(), loc.getY(),
					loc.getZ());
			Block block = loc.getBlock();
			getData().put(locData, block.getType().toString());
			block.setType(Material.REDSTONE_BLOCK);
		}

		System.out.println("[SCPRP WN] Set all blocks as powered.");
	}

	public static void offRedstone(ArrayList<Location> list) {
		int failed = 0;
		for (int i = 0; i < list.size(); i++) {
			Location loc = list.get(i);
			if (getData().containsKey(loc)) {
				loc.getBlock().setType(Material.valueOf(getData().get(loc)));
			} else {
				failed = failed++;
			}
		}

		System.out.println("[SCPRP WN] Restored block data, execpt for: " + failed + " blocks.");
	}

	public static String findNetworkNameByLeverLoc(Location location) {
		String network = "emptyerror123456";
		for (Entry<String, Location> entry : getLevers().entrySet()) {
			if (entry.getValue().equals(location))
				network = entry.getKey();
		}
		return network;
	}

	public static void offRedstoneAll() {
		for (Entry<Location, String> entry : getData().entrySet()) {
			Location loc = entry.getKey();
			loc.getBlock().setType(Material.valueOf(entry.getValue()));
		}

		System.out.println("[SCPRP WN] Restored all block data.");
	}

	public static void removeBlockData(String name) {
		ArrayList<Location> locations = getNetworks().get(name);
		for (Location loc : locations) {
			getData().remove(loc);
		}

		System.out.println("[SCP WN] Successfully removed all block data for the " + name + " network.");
	}

	public static void removeNetwork(String name) {
		offRedstone(getNetworks().get(name));
		removeBlockData(name);
		getLevers().remove(name);
		getNetworks().remove(name);
		System.out.println("[SCP WN] Successfully removed network " + name);
	}

	public static void saveNetworks() {
		for (Entry<String, ArrayList<Location>> entry : getNetworks().entrySet()) {
			for (int i = 0; i < entry.getValue().size(); i++) {
				WirelessNetworksFile.getWirelessNetworksFile().getConfig()
						.set("Networks." + entry.getKey() + ".block" + i + ".x", entry.getValue().get(i).getX());
				WirelessNetworksFile.getWirelessNetworksFile().getConfig()
						.set("Networks." + entry.getKey() + ".block" + i + ".y", entry.getValue().get(i).getY());
				WirelessNetworksFile.getWirelessNetworksFile().getConfig()
						.set("Networks." + entry.getKey() + ".block" + i + ".z", entry.getValue().get(i).getZ());
				WirelessNetworksFile.getWirelessNetworksFile().getConfig().set(
						"Networks." + entry.getKey() + ".block" + i + ".World",
						entry.getValue().get(i).getWorld().getName());
				WirelessNetworksFile.getWirelessNetworksFile().getConfig().set(
						"Networks." + entry.getKey() + ".block" + i + ".PreviousBlock",
						getData().get(entry.getValue().get(i)));
			}
		}

		for (Entry<String, Location> entry : getLevers().entrySet()) {
			WirelessNetworksFile.getWirelessNetworksFile().getConfig().set("Levers." + entry.getKey() + ".x",
					entry.getValue().getX());
			WirelessNetworksFile.getWirelessNetworksFile().getConfig().set("Levers." + entry.getKey() + ".y",
					entry.getValue().getY());
			WirelessNetworksFile.getWirelessNetworksFile().getConfig().set("Levers." + entry.getKey() + ".z",
					entry.getValue().getZ());
			WirelessNetworksFile.getWirelessNetworksFile().getConfig().set("Levers." + entry.getKey() + ".World",
					entry.getValue().getWorld().getName());
		}

		WirelessNetworksFile.getWirelessNetworksFile().save();
	}

	public static void loadNetworks() {
		for (String name : WirelessNetworksFile.getWirelessNetworksFile().getConfig()
				.getConfigurationSection("Networks").getKeys(false)) {
			getNetworks().put(name, new ArrayList<Location>());
			for (String bindex : WirelessNetworksFile.getWirelessNetworksFile().getConfig()
					.getConfigurationSection("Networks." + name).getKeys(false)) {
				double x = WirelessNetworksFile.getWirelessNetworksFile().getConfig()
						.getDouble("Networks." + name + "." + bindex + ".x");
				double y = WirelessNetworksFile.getWirelessNetworksFile().getConfig()
						.getDouble("Networks." + name + "." + bindex + ".y");
				double z = WirelessNetworksFile.getWirelessNetworksFile().getConfig()
						.getDouble("Networks." + name + "." + bindex + ".z");
				Location loc = new Location(Bukkit.getWorld("RPWorld"), x, y, z);
				getNetworks().get(name).add(loc);
				getData().put(loc, WirelessNetworksFile.getWirelessNetworksFile().getConfig()
						.getString("Networks." + name + "." + bindex + ".PreviousBlock"));
			}
		}

		for (String leverName : WirelessNetworksFile.getWirelessNetworksFile().getConfig()
				.getConfigurationSection("Levers").getKeys(false)) {
			double x = WirelessNetworksFile.getWirelessNetworksFile().getConfig()
					.getDouble("Levers." + leverName + ".x");
			double y = WirelessNetworksFile.getWirelessNetworksFile().getConfig()
					.getDouble("Levers." + leverName + ".y");
			double z = WirelessNetworksFile.getWirelessNetworksFile().getConfig()
					.getDouble("Levers." + leverName + ".z");
			Location loc = new Location(Bukkit.getWorld("RPWorld"), x, y, z);
			getLevers().put(leverName, loc);
		}
		
		System.out.println(" ");
		System.out.println("Wireless Networks loaded successfully.");
	}

	public static List<String> getPlayersExpanding() {
		return playersExpanding;
	}

	public static void expand(String pName, String network) {
		networkName = network;
		Player p = Bukkit.getPlayerExact(pName);
		p.getInventory().addItem(Items.networkCompleteTool());
		getPlayersExpanding().add(pName);
		p.sendMessage("§e§oSelect the blocks, which will be added into the network.");
		p.sendMessage(
				"§a§oWhen the network expansion is all selected, right click a block with the §a§lNetwork Completion tool §e§o(a diamond hoe) §a§oto finish the creation.");
	}
}
