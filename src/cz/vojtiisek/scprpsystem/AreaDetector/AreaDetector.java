package cz.vojtiisek.scprpsystem.AreaDetector;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.EquipmentSlot;

import cz.vojtiisek.scprpsystem.API;
import cz.vojtiisek.scprpsystem.SCPRP;
import cz.vojtiisek.scprpsystem.files.MineOresFile;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;

public class AreaDetector implements Listener {
	private static SCPRP main;

	static LuckPerms api = LuckPermsProvider.get();

	public static List<String> creatingPlayers = new ArrayList<String>();
	public static List<Location> selectedBlocks = new ArrayList<Location>();

	public static Location loc1;
	public static Location loc2;
	public static Location locZero;

	public static Map<String, AreaDetectorTypes> detectorList = new HashMap<String, AreaDetectorTypes>();
	public static Map<String, List<Location>> detectors = new HashMap<String, List<Location>>();
	public static Map<String, Integer> blocksCount = new HashMap<String, Integer>();

	public static Map<String, AreaDetectorTypes> selectedType = new HashMap<String, AreaDetectorTypes>();

	public AreaDetector(SCPRP main) {
		this.main = main;
	}

	@EventHandler
	public static void onRightClick(PlayerInteractEvent e) {
		if (e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
			if (e.getHand().equals(EquipmentSlot.HAND)) {
				API.sendDebugMsg("Clicked: " + e.getClickedBlock().getLocation());
				Location loc;
				if (getCreatingPlayers().indexOf(e.getPlayer().getName()) != -1) {
					if (!e.getPlayer().isSneaking()) {
						loc = new Location(e.getClickedBlock().getLocation().getWorld(),
								e.getClickedBlock().getLocation().getX(), e.getClickedBlock().getLocation().getY(),
								e.getClickedBlock().getLocation().getZ());
						if (getSelected().indexOf(loc) == -1) {
							getSelected().add(loc);
							e.getPlayer().sendMessage(
									"§8§l[§a§l!§8§l]§a§o Block selected. To deselect it, right click it once again.");
							e.getPlayer().sendMessage(
									"§8§l[§9§l?§8§l] §b§oTo finish the Area Detector, use §e§l/areadetector done§b§o.");
						} else {
							getSelected().remove(loc);
							e.getPlayer().sendMessage("§8§l[§a§l!§8§l]§e§o Block deselected.");
						}

					} else {
						if (getCreatingPlayers().contains(e.getPlayer().getName())
								&& getCreatingPlayers().indexOf(e.getPlayer().getName()) != -1)
							getCreatingPlayers().remove(e.getPlayer().getName());
						if (getSelected().size() <= 0 || getSelected().isEmpty()) {
							getSelectedType().remove(e.getPlayer().getName());
							e.getPlayer().sendMessage(
									"§8§l[§b§lAreaDetectors§8§l] §c§oNo blocks added. Cancelling the creation...");
						} else {
							int new_size = getDetectors().size() + 1;
							API.sendDebugMsg("Size: " + getDetectors().size());
							API.sendDebugMsg("New size: " + new_size);
							API.sendDebugMsg("Pre-Name: Detector" + new_size + "   type:"
									+ getSelectedType().get(e.getPlayer().getName()));
							getList().put("Detector" + new_size, getSelectedType().get(e.getPlayer().getName()));
							API.sendDebugMsg("Post-Type: " + getList().get("Detector" + new_size));
							getDetectors().put("Detector" + new_size, getSelected());
							getSelectedType().remove(e.getPlayer().getName());
							e.getPlayer().sendMessage("§8§l[§b§lAreaDetectors§8§l] §a§oAdded a new Area Detector.");
							API.sendDebugMsg("size: " + getDetectors().size());
							API.sendDebugMsg("value: " + getDetectors().get("Detector" + new_size));
							API.sendDebugMsg("size list: " + getList().size());
							API.sendDebugMsg("value list: " + getList().get("Detector" + new_size));
						}
					}
				}
			}
		}
		/*
		 * if (e.getAction().equals(Action.LEFT_CLICK_BLOCK)) { if
		 * (e.getHand().equals(EquipmentSlot.HAND)) { Location loc; if
		 * (getCreatingPlayers().indexOf(e.getPlayer().getName()) != -1) { loc = new
		 * Location(e.getClickedBlock().getLocation().getWorld(),
		 * e.getClickedBlock().getLocation().getX(),
		 * e.getClickedBlock().getLocation().getY(),
		 * e.getClickedBlock().getLocation().getZ()); if
		 * (!getCount().containsKey(e.getPlayer().getName()) ||
		 * (getCount().containsKey(e.getPlayer().getName()) &&
		 * getCount().get(e.getPlayer().getName()) == 0)) { getLoc1().setX(loc.getX());
		 * getLoc1().setY(loc.getY()); getLoc1().setZ(loc.getZ());
		 * getLoc1().setWorld(loc.getWorld()); getCount().put(e.getPlayer().getName(),
		 * 1); e.getPlayer().sendMessage(
		 * "§8§l[§a§l!§8§l]§a§o Block 1 out of 2 selected. To deselect it, start selecting the two blocks once again."
		 * ); } else if (getCount().get(e.getPlayer().getName()) == 1) {
		 * getLoc2().setX(loc.getX()); getLoc2().setY(loc.getY());
		 * getLoc2().setZ(loc.getZ()); getLoc2().setWorld(loc.getWorld());
		 * getCount().put(e.getPlayer().getName(),
		 * getCount().get(e.getPlayer().getName()) + 1); e.getPlayer().sendMessage(
		 * "§8§l[§a§l!§8§l]§a§o Block 2 out of 2 selected. To deselect it, start selecting the two blocks once again."
		 * ); e.getPlayer().sendMessage(
		 * "§8§l[§9§l?§8§l] §b§oTo finish the Area Detector, use §e§l/areadetector done§b§o."
		 * ); } else { getLoc1().zero(); getLoc2().zero();
		 * getCount().remove(e.getPlayer().getName());
		 * e.getPlayer().sendMessage("§8§l[§a§l!§8§l]§a§o Block selection reset.");
		 * e.getPlayer().sendMessage(
		 * "§8§l[§9§l?§8§l] §b§oTo finish the Area Detector, use §e§l/areadetector done§b§o."
		 * ); } } } }
		 */
	}

	@EventHandler
	public static void onMove(PlayerMoveEvent e) {
		Location loc = new Location(e.getTo().getWorld(), e.getTo().getX(), e.getTo().getY(), e.getTo().getZ());
		Location loc2 = new Location(e.getTo().getWorld(), e.getTo().getX(), e.getTo().getY()-1, e.getTo().getZ()); // y - 1
		Location loc3 = new Location(e.getTo().getWorld(), e.getTo().getX(), e.getTo().getY()+1, e.getTo().getZ()); // y + 1
		
		API.sendDebugMsg("onMove event: ");
		API.sendDebugMsg("loc: " + loc);
		API.sendDebugMsg("loc2: " + loc2);
		API.sendDebugMsg("loc3: " + loc3);
		
		if (!blockInDetector(loc).equals("notfound") || !blockInDetector(loc2).equals("notfound") || !blockInDetector(loc3).equals("notfound")) {
			API.sendDebugMsg("found");
			Player p = e.getPlayer();
			String role = api.getUserManager().getUser(p.getName()).getPrimaryGroup();
			String type = blockInDetector(loc);
			
			API.sendDebugMsg("type: " + type);
			API.sendDebugMsg("role: " + role);
			
			switch (type) {
			case "MINE":
				if (MineOresFile.getMineOresFile().getConfig().getBoolean("Raid") == false) {
					if (!(role.equalsIgnoreCase("class-d") || role.equalsIgnoreCase("default")
							|| p.hasPermission("scp.admin") || p.hasPermission("scp.mod"))) {
						p.teleport(e.getFrom());
						p.sendMessage("§8§l[§4§l!§8§l] §c§oYou can not enter the Class-D Mine.");
						p.sendMessage(
								"§8§l[§9§l?§8§l] §e§oIf you have a reason to enter the mine §6§o(for example a wanted Class-D is hiding in there)§e§o, you can ask any §c§oAdministrative§e§o or §4§lL5§e§o personnel to allow a §a§oraid§e§o.");
					} else {
						p.sendMessage("§6§lentering mine");
					}
				} else {
					if (!(role.equalsIgnoreCase("class-d") || role.equalsIgnoreCase("default")
							|| p.hasPermission("scp.admin") || p.hasPermission("scp.mod"))) {
						p.sendMessage("§8§l[§e§l!§8§l] §e§lRaid status: §a§oALLOWED");
					}
				}
				break;
			case "DETECTOR":
				if (findDetectorName(loc).equals("notfounderror")) {
					API.sendConsoleCommand(
							"discordsrv broadcast #839057785362448424 [SCPRP] AreaDetector onMove findDetectorName()  notfounderror, loc: "
									+ loc);
				} else {
					API.sendConsoleCommand("discordsrv broadcast #1021458900177932328 ----------------");
					API.sendConsoleCommand(
							"discordsrv broadcast #1021458900177932328 Detector: " + findDetectorName(loc));
					API.sendConsoleCommand("discordsrv broadcast #1021458900177932328 Player: " + p.getName());
					API.sendConsoleCommand("discordsrv broadcast #1021458900177932328 Role: " + role);
				}
				break;
			default:
				API.sendConsoleCommand(
						"discordsrv broadcast #839057785362448424 [SCPRP] AreaDetector onMove default, loc: " + loc);
				break;
			}
		}
		
		API.sendDebugMsg("end");
	}

	public static String findDetectorName(Location loc) {
		String result = "notfounderror";
		for (Entry<String, List<Location>> entry : getDetectors().entrySet()) {
			for (int i = 0; i < entry.getValue().size(); i++) {
				if (entry.getValue().get(i) == loc) {
					result = entry.getKey();
				}
			}
		}

		return result;
	}

	public static String blockInDetector(Location loc) {
		String result = "notfound";
		for (Entry<String, List<Location>> entry : getDetectors().entrySet()) {
			for (int i = 0; i < entry.getValue().size(); i++) {
				if (loc == entry.getValue().get(i)) {
					result = getList().get(entry.getKey()).toString();
				}
			}
		}
		return result;
	}

	public static void remove(String str) {
		if (getList().containsKey(str))
			getList().remove(str);
		if (getDetectors().containsKey(str))
			getDetectors().remove(str);

	}

	public static void finishDetectors(Player p, AreaDetectorTypes type) {
		if (getCreatingPlayers().indexOf(p.getName()) != -1)
			getCreatingPlayers().remove(p.getName());
		if (getSelected().size() != 0) {
			int sizeName = getDetectors().size() + 1;
			getList().put("Detector" + sizeName, type);
			getDetectors().put("Detector" + sizeName, getSelected());
		} else {
			p.sendMessage("§8§l[§c§l!§8§l] §c§oNo Individual blocks added as Area Detectors.");
		}

		Location loc = getLocZero().zero();
		if (!(getLoc1().equals(loc) && getLoc2().equals(loc))) {
			List<Location> countedBlocks = new ArrayList<Location>();
			int sizeNameRange = getDetectors().size() + 1;
			for (double x = getLoc1().getX(); x <= getLoc2().getX(); x++) {
				for (double y = getLoc1().getY(); y <= getLoc2().getY(); y++) {
					for (double z = getLoc1().getZ(); z <= getLoc2().getZ(); z++) {
						countedBlocks.add(new Location(getLoc1().getWorld(), x, y, z));
					}
				}
			}

			getDetectors().put("Detector" + sizeNameRange, countedBlocks);
			getList().put("Detector" + sizeNameRange, type);
		} else {
			if (getLoc1().equals(loc) && !getLoc2().equals(loc))
				p.sendMessage(
						"§8§l[§c§l!§8§l] §c§oNo Area blocks added as Area Detectors, because Block 1 is not selected.");
			if (!getLoc1().equals(loc) && getLoc2().equals(loc))
				p.sendMessage(
						"§8§l[§c§l!§8§l] §c§oNo Area blocks added as Area Detectors, because Block 2 is not selected.");
			if (getLoc1().equals(loc) && getLoc2().equals(loc))
				p.sendMessage(
						"§8§l[§c§l!§8§l] §c§oNo Area blocks added as Area Detectors, because no blocks were selected.");
		}
	}

	public static void creationManager(Player player, String type) {
		if (getCreatingPlayers().indexOf(player.getName()) != -1) {
			player.sendMessage(
					"§8§l[§c§l!§8§l] §c§oYou are already in the §e§oCreator mode§c§o. To finish creating an Area Detector, §b§oShift + Right Click§c§o any block.");
		} else {
			if (AreaDetectorIdentify.getIdentifyList().containsKey(player.getName()))
				AreaDetectorIdentify.disableTool(player.getName());

			getCreatingPlayers().add(player.getName());
			getSelectedType().put(player.getName(), AreaDetectorTypes.getTypeByName(type));
			API.sendDebugMsg("Selected type: " + AreaDetectorTypes.getTypeByName(type));

			player.sendMessage("§8§l[§a§l!§8§l] §b§oArea Detector creation §a§lenabled§b§o.");
			player.sendMessage(
					"§9§oRight click blocks with an empty hand to add new detectors. To deselect a location, right click the selected blocks once again.");
			player.sendMessage("§e§oWhen done, §b§oShift + Right Click§e§o any block.");
		}
	}

	public static Map<String, AreaDetectorTypes> getList() {
		return detectorList;
	}

	public static Map<String, List<Location>> getDetectors() {
		return detectors;
	}

	public static List<Location> getSelected() {
		return selectedBlocks;
	}

	public static List<String> getCreatingPlayers() {
		return creatingPlayers;
	}

	public static Map<String, Integer> getCount() {
		return blocksCount;
	}

	public static Location getLoc1() {
		return loc1;
	}

	public static Location getLoc2() {
		return loc2;
	}

	public static Location getLocZero() {
		return locZero;
	}

	public static Map<String, AreaDetectorTypes> getSelectedType() {
		return selectedType;
	}
	
	public static void save() {
		
	}
	
	public static void load() {
		
	}
}
