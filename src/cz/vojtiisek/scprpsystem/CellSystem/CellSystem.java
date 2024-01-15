package cz.vojtiisek.scprpsystem.CellSystem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import cz.vojtiisek.scprpsystem.API;
import cz.vojtiisek.scprpsystem.SCPRP;
import cz.vojtiisek.scprpsystem.files.CellsFile;
import cz.vojtiisek.scprpsystem.files.JailedPlayersFile;

public class CellSystem {
	private static SCPRP main;

	// designation, <x,y,z,world,owner>
	public static Map<String, Cell> minimum = new HashMap<String, Cell>();
	public static Map<String, Cell> medium = new HashMap<String, Cell>();
	public static Map<String, Cell> maximum = new HashMap<String, Cell>();
	public static Map<String, Cell> jail = new HashMap<String, Cell>();

	public static List<String> jailedPlayers = new ArrayList<String>();

	public static int minimumInt;
	public static int mediumInt;
	public static int maximumInt;

	public CellSystem(SCPRP main) {
		this.main = main;
	}

	public static void addCell(String level, Location loc, Player sender) {
		String xPos = Integer.toString((int) loc.getX());
		String yPos = Integer.toString((int) loc.getY());
		String zPos = Integer.toString((int) loc.getZ());
		Cell cell;
		String world = loc.getWorld().getName();
		String owner = "NoOwnerYetNoOwnerYet";
		String designation = "error";
		int designationInt = 9999999;
		switch (level) {
		case "minimum":
			designationInt = getCount() + 1;
			designation = API.toStringDesig(designationInt);
			cell = new Cell(SCPRP.getInstance(), xPos, yPos, zPos, world, owner, "minimum", designation);
			getMinimumCells().put(designation, cell);
			setCount(designationInt);
			saveFile();
			sender.sendMessage("§8[§a§l!§8] §a§oCell §9§o#§l" + designation + " §a§oadded successfully.");
			System.out.println("[SCPRP] Successfully added a new Minimum cell: " + xPos + ", " + yPos + ", " + zPos
					+ ", " + world + ", " + owner);
			break;
		case "medium":
			designationInt = getCount() + 1;
			designation = API.toStringDesig(designationInt);
			cell = new Cell(SCPRP.getInstance(), xPos, yPos, zPos, world, owner, "medium", designation);
			getMediumCells().put(designation, cell);
			setCount(designationInt);
			saveFile();
			sender.sendMessage("§8[§a§l!§8] §a§oCell §9§o#§l" + designation + " §a§oadded successfully.");
			System.out.println("[SCPRP] Successfully added a new Medium cell: " + xPos + ", " + yPos + ", " + zPos
					+ ", " + world + ", " + owner);
			break;
		case "maximum":
			designationInt = getCount() + 1;
			designation = API.toStringDesig(designationInt);
			cell = new Cell(SCPRP.getInstance(), xPos, yPos, zPos, world, owner, "maximum", designation);
			getMaximumCells().put(designation, cell);
			setCount(designationInt);
			saveFile();
			sender.sendMessage("§8[§a§l!§8] §a§oCell §9§o#§l" + designation + " §a§oadded successfully.");
			System.out.println("[SCPRP] Successfully added a new Maximum cell: " + xPos + ", " + yPos + ", " + zPos
					+ ", " + world + ", " + owner);
			break;
		case "jail":
			designationInt = getCount() + 1;
			designation = API.toStringDesig(designationInt);
			cell = new Cell(SCPRP.getInstance(), xPos, yPos, zPos, world, owner, "jail", designation);
			getJailCells().put(designation, cell);
			setCount(designationInt);
			saveFile();
			sender.sendMessage("§8[§a§l!§8] §a§oCell §9§o#§l" + designation + " §a§oadded successfully.");
			System.out.println("[SCPRP] Successfully added a new Jail cell: " + xPos + ", " + yPos + ", " + zPos + ", "
					+ world + ", " + owner);
			break;
		default:
			sender.sendMessage(
					"§8[§4§l!§8] §c§oAn error occured when adding a new cell: §4§laddCell level err §c§o(§e§l" + level
							+ "§e§o)");
			API.sendConsoleCommand("discord broadcast #839057785362448424 [CELLS] addCell level err");
			break;
		}

		if (designation == "error" || designationInt == 9999999)
			API.sendConsoleCommand("discord broadcast #839057785362448424 [CELLS] designation err");
	}

	public static void setOwner(String id, String name, Player sender) {
		Cell cell;
		if (API.findCellIdByPname(name) != "error") {
			switch (API.findCellLevelById(id)) {
			case "minimum":
				cell = getMinimumCells().get(API.findCellIdByPname(name));
				if (cell == null) {
					System.out.println("[SCPRP] Cell null");
					API.sendConsoleCommand("discord broadcast #839057785362448424 [CELLS] setOwner Cell null min");
				} else {
					cell.setOwner("NoOwnerYetNoOwnerYet");
				}
				saveFile();
				break;
			case "medium":
				cell = getMediumCells().get(API.findCellIdByPname(name));
				if (cell == null) {
					System.out.println("[SCPRP] Cell null");
					API.sendConsoleCommand("discord broadcast #839057785362448424 [CELLS] setOwner Cell null med");
				} else {
					cell.setOwner("NoOwnerYetNoOwnerYet");
				}
				saveFile();
				sender.sendMessage("§8[§a§l!§8] §8§oRemoved Cell §9§o#§l" + API.findCellIdByPname(name)
						+ "§8§o's owner: §6§l" + name + "§8§o.");
				break;
			case "maximum":
				cell = getMaximumCells().get(API.findCellIdByPname(name));
				if (cell == null) {
					System.out.println("[SCPRP] Cell null");
					API.sendConsoleCommand("discord broadcast #839057785362448424 [CELLS] setOwner Cell null max");
				} else {
					cell.setOwner("NoOwnerYetNoOwnerYet");
				}
				saveFile();
				sender.sendMessage("§8[§a§l!§8] §8§oRemoved Cell §9§o#§l" + API.findCellIdByPname(name)
						+ "§8§o's owner: §6§l" + name + "§8§o.");
				break;
			case "jail":
				cell = getJailCells().get(API.findCellIdByPname(name));
				if (cell == null) {
					System.out.println("[SCPRP] Cell null");
					API.sendConsoleCommand("discord broadcast #839057785362448424 [CELLS] setOwner Cell null max");
				} else {
					cell.setOwner("NoOwnerYetNoOwnerYet");
				}
				saveFile();
				sender.sendMessage("§8[§a§l!§8] §8§oRemoved Cell §9§o#§l" + API.findCellIdByPname(name)
						+ "§8§o's owner: §6§l" + name + "§8§o.");
				break;
			case "error":
				sender.sendMessage("§8[§c§l!§8] §c§oCell §e§o#§l" + id + " §c§odoes not exist.");
			default:
				break;
			}
		}

		switch (API.findCellLevelById(id)) {
		case "minimum":
			cell = getMinimumCells().get(id);
			cell.setOwner(name);
			saveFile();
			sender.sendMessage("§8[§a§l!§8] §a§oCell §9§o#§l" + id + " §a§ohas a new owner now:§6§l " + name + "§a§o.");
			break;
		case "medium":
			cell = getMediumCells().get(id);
			cell.setOwner(name);
			saveFile();
			sender.sendMessage("§8[§a§l!§8] §a§oCell §9§o#§l" + id + " §a§ohas a new owner now:§6§l " + name + "§a§o.");
			break;
		case "maximum":
			cell = getMaximumCells().get(id);
			cell.setOwner(name);
			saveFile();
			sender.sendMessage("§8[§a§l!§8] §a§oCell §9§o#§l" + id + " §a§ohas a new owner now:§6§l " + name + "§a§o.");
			break;
		case "jail":
			cell = getJailCells().get(id);
			cell.setOwner(name);
			saveFile();
			sender.sendMessage("§8[§a§l!§8] §a§oCell §9§o#§l" + id + " §a§ohas a new owner now:§6§l " + name + "§a§o.");
			break;
		case "error":
			sender.sendMessage("§8[§c§l!§8] §c§oCell §e§o#§l" + id + " §c§odoes not exist.");
			break;
		default:
			break;
		}
	}

	public static void removeOwner(String id, Player sender) {
		Cell cell;
		switch (API.findCellLevelById(id)) {
		case "minimum":
			cell = getMinimumCells().get(id);
			cell.setOwner("NoOwnerYetNoOwnerYet");
			saveFile();
			sender.sendMessage("§8[§a§l!§8] §a§oRemoved Cell §9§o#§l" + id + "§a§o's owner.");
			break;
		case "medium":
			cell = getMediumCells().get(id);
			cell.setOwner("NoOwnerYetNoOwnerYet");
			saveFile();
			sender.sendMessage("§8[§a§l!§8] §a§oRemoved Cell §9§o#§l" + id + "§a§o's owner.");
			break;
		case "maximum":
			cell = getMaximumCells().get(id);
			cell.setOwner("NoOwnerYetNoOwnerYet");
			saveFile();
			sender.sendMessage("§8[§a§l!§8] §a§oRemoved Cell §9§o#§l" + id + "§a§o's owner.");
			break;
		case "jail":
			cell = getJailCells().get(id);
			cell.setOwner("NoOwnerYetNoOwnerYet");
			saveFile();
			sender.sendMessage("§8[§a§l!§8] §a§oRemoved Cell §9§o#§l" + id + "§a§o's owner.");
			break;
		case "error":
			sender.sendMessage("§8[§c§l!§8] §c§oCell §e§o#§l" + id + " §c§odoes not exist.");
			break;
		default:
			break;
		}
	}

	public static void removeOwnerSystem(String id) {
		Cell cell;
		switch (API.findCellLevelById(id)) {
		case "minimum":
			cell = getMinimumCells().get(id);
			cell.setOwner("NoOwnerYetNoOwnerYet");
			saveFile();
			break;
		case "medium":
			cell = getMediumCells().get(id);
			cell.setOwner("NoOwnerYetNoOwnerYet");
			saveFile();
			break;
		case "maximum":
			cell = getMaximumCells().get(id);
			cell.setOwner("NoOwnerYetNoOwnerYet");
			saveFile();
			break;
		case "jail":
			cell = getJailCells().get(id);
			cell.setOwner("NoOwnerYetNoOwnerYet");
			saveFile();
			break;
		default:
			break;
		}
	}

	public static Map<String, Cell> getMinimumCells() {
		return minimum;
	}

	public static Map<String, Cell> getMediumCells() {
		return medium;
	}

	public static Map<String, Cell> getMaximumCells() {
		return maximum;
	}

	public static Map<String, Cell> getJailCells() {
		return jail;
	}

	public static int getCount() {
		minimumInt = CellsFile.getCellsFile().getConfig().getInt("Count");
		return minimumInt;
	}

	public static void setCount(int designation) {
		CellsFile.getCellsFile().getConfig().set("Count", designation);
	}

	public static void saveFile() {
		try {
			CellsFile.getCellsFile().getConfig().set("Count", getCount());
			if (!getMinimumCells().isEmpty()) {
				CellsFile.getCellsFile().getConfig().set("Minimum", "");
				for (Entry<String, Cell> entry : getMinimumCells().entrySet()) {
					Cell cell = getMinimumCells().get(entry.getKey());
					List<String> list = Arrays.asList(cell.getX(), cell.getY(), cell.getZ(), cell.getWorld(),
							cell.getOwner());
					CellsFile.getCellsFile().getConfig().set("Minimum." + entry.getKey(), list);
				}
			}
			if (!getMediumCells().isEmpty()) {
				CellsFile.getCellsFile().getConfig().set("Medium", "");
				for (Entry<String, Cell> entry : getMediumCells().entrySet()) {
					Cell cell = getMediumCells().get(entry.getKey());
					List<String> list = Arrays.asList(cell.getX(), cell.getY(), cell.getZ(), cell.getWorld(),
							cell.getOwner());
					CellsFile.getCellsFile().getConfig().set("Medium." + entry.getKey(), list);
				}
			}

			if (!getMaximumCells().isEmpty()) {
				CellsFile.getCellsFile().getConfig().set("Maximum", "");
				for (Entry<String, Cell> entry : getMaximumCells().entrySet()) {
					Cell cell = getMaximumCells().get(entry.getKey());
					List<String> list = Arrays.asList(cell.getX(), cell.getY(), cell.getZ(), cell.getWorld(),
							cell.getOwner());
					CellsFile.getCellsFile().getConfig().set("Maximum." + entry.getKey(), list);
				}
			}

			if (!getJailCells().isEmpty()) {
				CellsFile.getCellsFile().getConfig().set("Jail", "");
				for (Entry<String, Cell> entry : getJailCells().entrySet()) {
					Cell cell = getJailCells().get(entry.getKey());
					List<String> list = Arrays.asList(cell.getX(), cell.getY(), cell.getZ(), cell.getWorld(),
							cell.getOwner());
					CellsFile.getCellsFile().getConfig().set("Jail." + entry.getKey(), list);
				}
			}

			saveJailedPlayersFile();

		} catch (Exception e) {
			CellsFile.getCellsFile().save();
			CellsFile.getCellsFile().reload();

			e.printStackTrace();
			saveFile();
		}

		CellsFile.getCellsFile().save();
		CellsFile.getCellsFile().reload();
	}

	public static void saveJailedPlayersFile() {
		try {
			if (!getJailedPlayers().isEmpty()) {
				JailedPlayersFile.getJailedPlayersFile().getConfig().set("JailedPlayers", getJailedPlayers());
			}
		} catch (Exception e) {
			JailedPlayersFile.getJailedPlayersFile().save();
			JailedPlayersFile.getJailedPlayersFile().reload();

			e.printStackTrace();
			saveJailedPlayersFile();
		}

		JailedPlayersFile.getJailedPlayersFile().save();
		JailedPlayersFile.getJailedPlayersFile().reload();
	}

	public static void loadFile() {
		CellsFile.getCellsFile().save();
		CellsFile.getCellsFile().reload();
		try {
			for (String str : CellsFile.getCellsFile().getConfig().getConfigurationSection("Minimum").getKeys(false)) {
				if (CellsFile.getCellsFile().getConfig().isList("Minimum." + str)) {
					List<String> list = CellsFile.getCellsFile().getConfig().getStringList("Minimum." + str);
					getMinimumCells().put(str, new Cell(SCPRP.getInstance(), list.get(0), list.get(1), list.get(2),
							list.get(3), list.get(4), "minimum", str));
				}
			}

			for (String str : CellsFile.getCellsFile().getConfig().getConfigurationSection("Medium").getKeys(false)) {
				if (CellsFile.getCellsFile().getConfig().isList("Medium." + str)) {
					List<String> list = CellsFile.getCellsFile().getConfig().getStringList("Medium." + str);
					getMediumCells().put(str, new Cell(SCPRP.getInstance(), list.get(0), list.get(1), list.get(2),
							list.get(3), list.get(4), "medium", str));
				}
			}

			for (String str : CellsFile.getCellsFile().getConfig().getConfigurationSection("Maximum").getKeys(false)) {
				if (CellsFile.getCellsFile().getConfig().isList("Maximum." + str)) {
					List<String> list = CellsFile.getCellsFile().getConfig().getStringList("Maximum." + str);
					getMaximumCells().put(str, new Cell(SCPRP.getInstance(), list.get(0), list.get(1), list.get(2),
							list.get(3), list.get(4), "maximum", str));
				}
			}

			for (String str : CellsFile.getCellsFile().getConfig().getConfigurationSection("Jail").getKeys(false)) {
				if (CellsFile.getCellsFile().getConfig().isList("Jail." + str)) {
					List<String> list = CellsFile.getCellsFile().getConfig().getStringList("Jail." + str);
					getJailCells().put(str, new Cell(SCPRP.getInstance(), list.get(0), list.get(1), list.get(2),
							list.get(3), list.get(4), "jail", str));
				}
			}

			loadJailedPlayersFile();

		} catch (NullPointerException e) {
			System.out.println("[SCPRP CellSystem] No cells created - could not save the file.");
		}

		System.out.println(" ");
		System.out.println("Class-D Cells loaded successfully.");
	}

	public static void loadJailedPlayersFile() {
		if (CellsFile.getCellsFile().getConfig().isList("JailedPlayers")) {
			for (String str : CellsFile.getCellsFile().getConfig().getStringList("JailedPlayers")) {
				getJailedPlayers().add(str);
			}
		}
	}

	public static void assignCell(String name) {
		if (CellsFile.getCellsFile().getConfig().getBoolean("Enabled")) {
			Random rand = new Random();
			int divide = rand.nextInt(3);
			System.out.println("CELL SYS RANDOM: " + divide);
			if (divide == 3)
				divide = 1;
			if (divide == 2)
				divide = 1;
			System.out.println("CELL SYS DIVISION: " + divide);
			switch (divide) {
			case 0:
				for (Entry<String, Cell> entry : getMinimumCells().entrySet()) {
					Cell cell = entry.getValue();
					if (cell.getOwner().equalsIgnoreCase("NoOwnerYetNoOwnerYet")) {
						cell.setOwner(name);
						saveFile();
						System.out.println("[SCPRP] Cell #" + entry.getKey() + " assigned to " + name);
						API.sendConsoleCommand("discord broadcast #843774743386521620 [CELLS] Cell #" + entry.getKey()
								+ " assigned to " + name);
						break;
					} else {
						continue;
					}
				}
				break;
			case 1:
				for (Entry<String, Cell> entry : getMediumCells().entrySet()) {
					Cell cell = entry.getValue();
					if (cell.getOwner().equalsIgnoreCase("NoOwnerYetNoOwnerYet")) {
						cell.setOwner(name);
						saveFile();
						System.out.println("[SCPRP] Cell #" + entry.getKey() + " assigned to " + name);
						API.sendConsoleCommand("discord broadcast #843774743386521620 [CELLS] Cell #" + entry.getKey()
								+ " assigned to " + name);
						break;
					} else {
						continue;
					}
				}
				break;
			/*
			 * case 2: for (Entry<String, Cell> entry : getMediumCells().entrySet()) { Cell
			 * cell = entry.getValue(); if
			 * (cell.getOwner().equalsIgnoreCase("NoOwnerYetNoOwnerYet")) {
			 * cell.setOwner(name); saveFile(); System.out.println("[SCPRP] Cell #" +
			 * entry.getKey() + " assigned to " + name);
			 * API.sendConsoleCommand("discord broadcast #843774743386521620 [CELLS] Cell #"
			 * + entry.getKey() + " assigned to " + name); break; } else { continue; } }
			 * break;
			 */
			default:
				break;
			}

			CellSystem.teleportToCell(name, "assign");
		} else {
			System.out.println("[SCPRP] Cell assigning disabled.");
			API.sendConsoleCommand("discord broadcast #843774743386521620 [CELLS] Cell assigning disabled.");
		}
	}

	public static void switchAssigning(Player player) {
		if (CellsFile.getCellsFile().getConfig().getBoolean("Enabled")) {
			CellsFile.getCellsFile().getConfig().set("Enabled", false);
			player.sendMessage("§8[§a§l!§8] §c§oDisabled cell autoassigning.");
			API.sendConsoleCommand("discord broadcast #819586816444727316 [CELLS] Cell assigning disabled by "
					+ player.getName() + ".");
		} else {
			CellsFile.getCellsFile().getConfig().set("Enabled", true);
			player.sendMessage("§8[§a§l!§8] §a§oEnabled cell autoassigning.");
			API.sendConsoleCommand("discord broadcast #819586816444727316 [CELLS] Cell assigning enabled by "
					+ player.getName() + ".");
		}
	}

	public static void countFreeCells(Player player) {
		int freecells = 0;
		for (Entry<String, Cell> entry : getMinimumCells().entrySet()) {
			Cell cell = entry.getValue();
			if (cell.getOwner().equalsIgnoreCase("NoOwnerYetNoOwnerYet")) {
				freecells = freecells + 1;
			}
		}
		for (Entry<String, Cell> entry : getMediumCells().entrySet()) {
			Cell cell = entry.getValue();
			if (cell.getOwner().equalsIgnoreCase("NoOwnerYetNoOwnerYet")) {
				freecells = freecells + 1;
			}
		}

		for (Entry<String, Cell> entry : getMaximumCells().entrySet()) {
			Cell cell = entry.getValue();
			if (cell.getOwner().equalsIgnoreCase("NoOwnerYetNoOwnerYet")) {
				freecells = freecells + 1;
			}
		}

		for (Entry<String, Cell> entry : getJailCells().entrySet()) {
			Cell cell = entry.getValue();
			if (cell.getOwner().equalsIgnoreCase("NoOwnerYetNoOwnerYet")) {
				freecells = freecells + 1;
			}
		}
		if (freecells <= 0) {
			player.sendMessage("§8[§5§lDb§8] §c§lThere are no free cells!");
			System.out.println("[SCPRP] There are no free cells!");
			API.sendConsoleCommand("discord broadcast #819586816444727316 [CELLS] There are no free cells!");
		} else {
			player.sendMessage("§8[§5§lDb§8] §6§oThere are currently §9§l" + freecells + " §6§ofree cells.");
		}
	}

	public static int countFreeCells() {
		int freecells = 0;
		for (Entry<String, Cell> entry : getMinimumCells().entrySet()) {
			Cell cell = entry.getValue();
			if (cell.getOwner().equalsIgnoreCase("NoOwnerYetNoOwnerYet")) {
				freecells = freecells + 1;
			}
		}
		for (Entry<String, Cell> entry : getMediumCells().entrySet()) {
			Cell cell = entry.getValue();
			if (cell.getOwner().equalsIgnoreCase("NoOwnerYetNoOwnerYet")) {
				freecells = freecells + 1;
			}
		}

		for (Entry<String, Cell> entry : getMaximumCells().entrySet()) {
			Cell cell = entry.getValue();
			if (cell.getOwner().equalsIgnoreCase("NoOwnerYetNoOwnerYet")) {
				freecells = freecells + 1;
			}
		}

		for (Entry<String, Cell> entry : getJailCells().entrySet()) {
			Cell cell = entry.getValue();
			if (cell.getOwner().equalsIgnoreCase("NoOwnerYetNoOwnerYet")) {
				freecells = freecells + 1;
			}
		}

		return freecells;
	}

	public static int countJailFreeCells() {
		int freecells = 0;
		for (Entry<String, Cell> entry : getJailCells().entrySet()) {
			Cell cell = entry.getValue();
			if (cell.getOwner().equalsIgnoreCase("NoOwnerYetNoOwnerYet")) {
				freecells = freecells + 1;
			}
		}

		return freecells;
	}

	public static int countMaxFreeCells() {
		int freecells = 0;
		for (Entry<String, Cell> entry : getMaximumCells().entrySet()) {
			Cell cell = entry.getValue();
			if (cell.getOwner().equalsIgnoreCase("NoOwnerYetNoOwnerYet")) {
				freecells = freecells + 1;
			}
		}

		return freecells;
	}

	public static int countMedFreeCells() {
		int freecells = 0;
		for (Entry<String, Cell> entry : getMediumCells().entrySet()) {
			Cell cell = entry.getValue();
			if (cell.getOwner().equalsIgnoreCase("NoOwnerYetNoOwnerYet")) {
				freecells = freecells + 1;
			}
		}

		return freecells;
	}

	public static int countMinFreeCells() {
		int freecells = 0;
		for (Entry<String, Cell> entry : getMinimumCells().entrySet()) {
			Cell cell = entry.getValue();
			if (cell.getOwner().equalsIgnoreCase("NoOwnerYetNoOwnerYet")) {
				freecells = freecells + 1;
			}
		}

		return freecells;
	}

	public static void teleportToCell(String name, String source) {
		System.out.println("CELL SYSTEM TELEPORT NAME: " + name + " SOURCE: " + source);
		String id = API.findCellIdByPname(name);
		if (Bukkit.getPlayerExact(name) != null && Bukkit.getPlayerExact(name).isOnline()) {
			Player player = Bukkit.getPlayerExact(name);
			if (id != "error") {
				String level = API.findCellLevelById(id);
				if (level != "error") {
					Cell cell;
					Location loc = new Location(Bukkit.getWorld("RPWorld"), 3.563, 203, 6.697);
					switch (level) {
					case "minimum":
						cell = getMinimumCells().get(id);
						loc = cell.getLocation();
						player.sendMessage("§6§oYou have been transported into your cell.");
						break;
					case "medium":
						cell = getMediumCells().get(id);
						loc = cell.getLocation();
						player.sendMessage("§6§oYou have been transported into your cell.");
						break;
					case "maximum":
						cell = getMaximumCells().get(id);
						loc = cell.getLocation();
						player.sendMessage("§6§oYou have been transported into your cell.");
						break;
					case "jail":
						cell = getJailCells().get(id);
						loc = cell.getLocation();
						player.sendMessage("§6§oYou have been transported into your cell.");
						break;
					default:
						API.sendConsoleCommand("discord broadcast #839057785362448424 [CELLS] Teleport switch err");
						break;
					}
					player.teleport(loc);
				} else {
					API.sendConsoleCommand("discord broadcast #839057785362448424 [CELLS] Teleport level err");
				}
			} else {
				API.sendConsoleCommand("discord broadcast #843774743386521620 [CELLS] Tried to teleport " + name
						+ " to their Class-D cell, but they don't have any.");
			}
		} else {
			API.sendConsoleCommand("discord broadcast #839057785362448424 [CELLS] Teleport Player null or offline");
		}

		System.out.print(Thread.currentThread().getStackTrace());
	}

	public static List<String> getJailedPlayers() {
		return jailedPlayers;
	}

	public static void jailPlayer(String string, Player sender) {
		if (countJailFreeCells() > 0) {
			if (API.findCellIdByPname(string) != "error") {
				String id = API.findCellIdByPname(string);
				removeOwnerSystem(id);
				assignJailCell(string);
				getJailedPlayers().add(string);
			}
		} else {
			sender.sendMessage(
					"§8§l[§c§l!§8§l] §c§lThere are no free Jail cells left. Contact The Administrator as soon as possible.");
			System.out.println("[SCPRP] There are no free Jail cells!");
			API.sendConsoleCommand("discord broadcast #819586816444727316 [CELLS] There are no free Jail cells!");
		}

	}

	public static void unjailPlayer(String string) {
		if (API.findCellIdByPname(string) != "error") {
			if (API.findCellLevelById(API.findCellIdByPname(string)) == "jail") {
				String id = API.findCellIdByPname(string);
				removeOwnerSystem(id);
				getJailedPlayers().remove(string);
				if (API.findCellIdByPname(string) == "error")
					assignCell(string);
			}
		}
	}

	public static void assignJailCell(String name) {
		for (Entry<String, Cell> entry : getJailCells().entrySet()) {
			Cell cell = entry.getValue();
			if (cell.getOwner().equalsIgnoreCase("NoOwnerYetNoOwnerYet")) {
				cell.setOwner(name);
				saveFile();
				System.out.println("[SCPRP] Jail Cell #" + entry.getKey() + " assigned to " + name);
				API.sendConsoleCommand("discord broadcast #843774743386521620 [CELLS] Jail Cell #" + entry.getKey()
						+ " assigned to " + name);
				break;
			} else {
				continue;
			}
		}
	}

	public static void clearAllNewbieCells() {
		for (Entry<String, Cell> entry : getMinimumCells().entrySet()) {
			Cell cell = entry.getValue();
			cell.setOwner("NoOwnerYetNoOwnerYet");
		}
		System.out.println("[SCPRP] All Minimum cells were resetted.");

		for (Entry<String, Cell> entry : getMediumCells().entrySet()) {
			Cell cell = entry.getValue();
			cell.setOwner("NoOwnerYetNoOwnerYet");
		}
		System.out.println("[SCPRP] All Medium cells were resetted.");
	}
}