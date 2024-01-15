package Levelling;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import cz.vojtiisek.scprpsystem.API;
import cz.vojtiisek.scprpsystem.SCPRP;
import cz.vojtiisek.scprpsystem.files.LevelsFile;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.model.user.User;

public class LevellingSys {
	private static SCPRP main;
	static LuckPerms api = LuckPermsProvider.get();

	public static boolean setLevelBool;

	public static HashMap<String, Integer> researchers = new HashMap<String, Integer>();
	public static HashMap<String, Integer> secOffs = new HashMap<String, Integer>();
	public static HashMap<String, Integer> mtfnu7 = new HashMap<String, Integer>();
	public static HashMap<String, Integer> technics = new HashMap<String, Integer>();

	public static ArrayList<Integer> resXP = new ArrayList<Integer>(Arrays.asList(1000, 4000, 15000));
	public static ArrayList<Integer> secoffXP = new ArrayList<Integer>(Arrays.asList(2000, 7500, 15000));
	public static ArrayList<Integer> mtfXP = new ArrayList<Integer>(Arrays.asList(1500, 7500, 17500));
	public static ArrayList<Integer> technicXP = new ArrayList<Integer>(Arrays.asList(2000, 7000, 20000));

	public static int currentLevel;
	public static int newLevelSet;

	public static HashMap<String, ArrayList<Integer>> rolesXP = new HashMap<String, ArrayList<Integer>>();

	public LevellingSys(SCPRP main) {
		this.main = main;
	}

	public static void addRolesXP() {
		getRolesXP().put("researcher", resXP);
		getRolesXP().put("security-officer", secoffXP);
		getRolesXP().put("mtf-nu-7", mtfXP);
		getRolesXP().put("technic", technicXP);
		
		System.out.println("Levelling System - XP Requirements loaded successfully.");
	}

	public static void addXP(String name, int xp) {
		String role = api.getUserManager().getUser(name).getPrimaryGroup().toString();
		if (roleHasLevel(name)) {
			int currentLevel = convertXpToLevel(role, getXP(name));
			Player target = Bukkit.getPlayerExact(name);
			switch (role) {
			case "researcher":
				if (getResearchers().containsKey(name)) {
					int has = getResearchers().get(name);
					getResearchers().put(name, (has + xp));
				} else {
					getResearchers().put(name, xp);
				}
				break;
			case "security-officer":
				if (getSecurityOfficers().containsKey(name)) {
					int has = getSecurityOfficers().get(name);
					getSecurityOfficers().put(name, (has + xp));
				} else {
					getSecurityOfficers().put(name, xp);
				}
				break;
			case "technic":
				if (getTechnics().containsKey(name)) {
					int has = getTechnics().get(name);
					getTechnics().put(name, (has + xp));
				} else {
					getTechnics().put(name, xp);
				}
				break;
			case "mtf-nu-7":
				if (getMTFs().containsKey(name)) {
					int has = getMTFs().get(name);
					getMTFs().put(name, (has + xp));
				} else {
					getMTFs().put(name, xp);
				}
				break;
			default:
				API.sendMessageToEveryone(
						"§8[§4§lERROR§8] §4§lERROR CODE: §e§lLEV0001§4§l - PLEASE, REPORT TO VOJTIISEK");
				break;
			}
			saveFile();
			checkLevelUp(name, currentLevel);
			target.sendMessage("§8[§a§l!§8] §a§oYou have recieved §f§l" + Integer.toString(xp)
					+ " §a§oXP for completing your task.");
		} else {
			System.out.println(
					"[SCPRP LevellingSys] Player " + name + "'s role does not level up, their role: " + role + ".");
			API.sendConsoleCommand("discord broadcast #843774743386521620 [SCPRP LevellingSys] Player " + name
					+ "'s role does not level up, their role: " + role + ".");
		}

	}

	public static void checkLevelUp(String name, int currentLevel) {
		String role = api.getUserManager().getUser(name).getPrimaryGroup().toString();
		loadFile();

		int xp = getXP(name);
		int level = convertXpToLevel(role, xp);
		int newLevel = 0;
		boolean levelUpBool = false;
		Player target = Bukkit.getPlayerExact(name);

		switch (convertXpToLevel(role, xp)) {
		case 0:
			if (currentLevel + 1 == 0) {
				levelUpBool = true;
			}
			break;
		case 1:
			if (currentLevel + 1 == 1) {
				newLevel = 1;
				levelUpBool = true;
			}
			break;
		case 2:
			if (currentLevel + 1 == 2) {
				newLevel = 2;
				levelUpBool = true;
			}
			break;
		case 3:
			if (currentLevel + 1 == 3) {
				newLevel = 3;
				levelUpBool = true;
			}
			break;
		case 4:
			if (currentLevel + 1 == 4) {
				newLevel = 4;
				levelUpBool = true;
			}
			break;
		default:
			levelUpBool = false;
			API.sendMessageToEveryone(
					"§8[§4§lERROR§8] §4§lERROR CODE: §e§lLEV0002-2§4§l - PLEASE, REPORT TO VOJTIISEK");
			break;
		}

		if (levelUpBool == true) {
			levelUp(name, role);
			target.sendMessage("§8[§a§l!§8] §a§oYou have reached §b§lLevel " + Integer.toString(level)
					+ "§a§o. §e§lCongratulations!");
		}
	}

	public static void levelUp(String user, String role) {
		API.removeAllPrefixes(user);
		if (getSetLevelBool()) {
			currentLevel = getNewLevel() - 1;
			setSetLevelBool(false);
		} else {
			currentLevel = convertXpToLevel(role, getXP(user)) - 1;
		}
		switch (role) {
		case "researcher":
			API.removeResearcherPermissions(user);

			switch (currentLevel) {
			case -1:
				API.setPrefix(user, 10, "§8[§f§lL1§8][§a§lResearcher§8]", true);
				API.addPermission(user, "scprp.researcher1");
				API.removeAllRewardDis(user);
				API.addPermission(user, "dr.researcher1");
				API.addPermission(user, "scprp.scps1");
				break;
			case 0:
				API.setPrefix(user, 10, "§8[§f§lL1§8][§a§lResearcher§8]", true);
				API.addPermission(user, "scprp.researcher1");
				API.addPermission(user, "dr.researcher1");
				API.addPermission(user, "scprp.scps1");
				break;
			case 1:
				API.setPrefix(user, 10, "§8[§b§lL2§8][§a§lResearcher§8]", true);
				API.addPermission(user, "scprp.researcher2");
				API.addPermission(user, "dr.researcher2");
				API.addPermission(user, "dr.researcher1", false);
				API.addPermission(user, "scprp.scps1");
				API.addPermission(user, "scprp.scps2");
				break;
			case 2:
				API.setPrefix(user, 10, "§8[§e§lL3§8][§a§lResearcher§8]", true);
				API.addPermission(user, "scprp.researcher3");
				API.addPermission(user, "dr.researcher3");
				API.addPermission(user, "dr.researcher1", false);
				API.addPermission(user, "scprp.scps1");
				API.addPermission(user, "scprp.scps2");
				API.addPermission(user, "scprp.scps3");
				break;
			case 3:
				API.setPrefix(user, 10, "§8[§d§lL4§8][§a§lResearcher§8]", true);
				API.addPermission(user, "scprp.researcher4");
				API.addPermission(user, "dr.researcher4");
				API.addPermission(user, "dr.researcher1", false);
				API.addPermission(user, "scprp.scps1");
				API.addPermission(user, "scprp.scps2");
				API.addPermission(user, "scprp.scps3");
				API.addPermission(user, "scprp.scps4");
				break;
			case 4:
				API.setPrefix(user, 10, "§8[§d§lL4§8][§a§lResearcher§8]", true);
				API.addPermission(user, "scprp.researcher4");
				API.addPermission(user, "dr.researcher4");
				API.addPermission(user, "dr.researcher1", false);
				API.addPermission(user, "scprp.scps1");
				API.addPermission(user, "scprp.scps2");
				API.addPermission(user, "scprp.scps3");
				API.addPermission(user, "scprp.scps4");
				break;
			default:
				break;
			}
			break;

		case "security-officer":
			API.removeSecOffPermissions(user);
			if (currentLevel <= 0) {
				API.setPrefix(user, 10, "§8[§f§lL1§8][§7§lSecurity§8]§o", true);
				API.addPermission(user, "scprp.secoff1");
				API.removeAllRewardDis(user);
				API.addPermission(user, "dr.secoff1");
			} else if (currentLevel == 1) {
				API.setPrefix(user, 10, "§8[§b§lL2§8][§7§lSecurity§8]§o", true);
				API.addPermission(user, "scprp.secoff2");
				API.addPermission(user, "dr.secoff2");
				API.addPermission(user, "dr.secoff1", false);
			} else if (currentLevel == 2) {
				API.setPrefix(user, 10, "§8[§e§lL3§8][§7§lSecurity§8]§o", true);
				API.addPermission(user, "scprp.secoff3");
				API.addPermission(user, "dr.secoff3");
				API.addPermission(user, "dr.secoff1", false);
			} else if (currentLevel == 3) {
				API.setPrefix(user, 10, "§8[§d§lL4§8][§7§lSecurity§8]§o", true);
				API.addPermission(user, "scprp.secoff4");
				API.addPermission(user, "dr.secoff4");
				API.addPermission(user, "dr.secoff1", false);
			}
			break;
		case "technic":
			API.removeTechnicPermissions(user);
			if (currentLevel <= 0) {
				API.setPrefix(user, 10, "§8[§f§lL1§8][§6Technic§8]§e§o", true);
				API.addPermission(user, "scprp.technic1");
				API.removeAllRewardDis(user);
				API.addPermission(user, "dr.technic1");
			} else if (currentLevel == 1) {
				API.setPrefix(user, 10, "§8[§b§lL2§8][§6Technic§8]§e§o", true);
				API.addPermission(user, "scprp.technic2");
				API.addPermission(user, "dr.technic2");
				API.addPermission(user, "dr.technic1", false);
			} else if (currentLevel == 2) {
				API.setPrefix(user, 10, "§8[§e§lL3§8][§6Technic§8]§e§o", true);
				API.addPermission(user, "scprp.technic3");
				API.addPermission(user, "dr.technic3");
				API.addPermission(user, "dr.technic1", false);
			} else if (currentLevel == 3) {
				API.setPrefix(user, 10, "§8[§d§lL4§8][§6Technic§8]§e§o", true);
				API.addPermission(user, "scprp.technic4");
				API.addPermission(user, "dr.technic4");
				API.addPermission(user, "dr.technic1", false);
			}
			break;
		case "mtf-nu-7":
			API.removeMTFPermissions(user);
			if (currentLevel <= 0) {
				API.setPrefix(user, 10, "§8[§f§lL1§8][§9§lMTF §3§oNu-7§8]§b§o", true);
				API.addPermission(user, "scprp.mtf1");
				API.removeAllRewardDis(user);
				API.addPermission(user, "dr.mtf1");
			} else if (currentLevel == 1) {
				API.setPrefix(user, 10, "§8[§b§lL2§8][§9§lMTF §3§oNu-7§8]§b§o", true);
				API.addPermission(user, "scprp.mtf2");
				API.addPermission(user, "dr.mtf2");
				API.addPermission(user, "dr.mtf1", false);
			} else if (currentLevel == 2) {
				API.setPrefix(user, 10, "§8[§e§lL3§8][§9§lMTF §3§oNu-7§8]§b§o", true);
				API.addPermission(user, "scprp.mtf3");
				API.addPermission(user, "dr.mtf3");
				API.addPermission(user, "dr.mtf1", false);
			} else if (currentLevel == 3) {
				API.setPrefix(user, 10, "§8[§d§lL4§8][§9§lMTF §3§oNu-7§8]§b§o", true);
				API.addPermission(user, "scprp.mtf4");
				API.addPermission(user, "dr.mtf4");
				API.addPermission(user, "dr.mtf1", false);
			}
			break;
		default:
			API.sendMessageToEveryone("§8[§4§lERROR§8] §4§lERROR CODE: §e§lLEV0003§4§l - PLEASE, REPORT TO VOJTIISEK");
			break;
		}
	}

	public static int getXP(String name) {
		LevelsFile.getLevelsFile().save();
		loadFile();

		String role = api.getUserManager().getUser(name).getPrimaryGroup().toString();
		int xp = 0;
		if (roleHasLevel(name)) {
			switch (role) {
			case "researcher":
				if (getResearchers().get(name) != null && getResearchers().containsKey(name))
					xp = getResearchers().get(name);
				break;
			case "security-officer":
				if (getSecurityOfficers().get(name) != null && getSecurityOfficers().containsKey(name))
					xp = getSecurityOfficers().get(name);
				break;
			case "technic":
				if (getTechnics().get(name) != null && getTechnics().containsKey(name))
					xp = getTechnics().get(name);
				break;
			case "mtf-nu-7":
				if (getMTFs().get(name) != null && getMTFs().containsKey(name))
					xp = getMTFs().get(name);
				break;
			default:
				API.sendMessageToEveryone(
						"§8[§4§lERROR§8] §4§lERROR CODE: §e§lLEV0001§4§l - PLEASE, REPORT TO VOJTIISEK");
				break;
			}
		}
		return xp;
	}

	public static int convertXpToLevel(String role, int xp) {
		int level = 0;
		addRolesXP();
		if (xp < getRolesXP().get(role).get(0)) {
			level = 1;
		} else if ((xp >= getRolesXP().get(role).get(0)) && (xp < getRolesXP().get(role).get(1))) { // l2
			level = 2;
		} else if ((xp >= getRolesXP().get(role).get(1)) && (xp < getRolesXP().get(role).get(2))) { // l3
			level = 3;
		} else if (xp >= getRolesXP().get(role).get(2)) { // l4
			level = 4;
		} else {
			API.sendMessageToEveryone("§8[§4§lERROR§8] §4§lERROR CODE: §e§lLEVCONV§4§l - PLEASE, REPORT TO VOJTIISEK");
			level = 0;
		}
		return level;
	}

	public static boolean roleHasLevel(String playerName) {
		boolean haslevel = false;
		User user = api.getUserManager().getUser(playerName);
		String role = "none";
		if(user != null && user.getPrimaryGroup() != null) {
			role = user.getPrimaryGroup();
		} else {
			role = "none";
		}
		try {
			switch(role) {
			case "researcher":
				haslevel = true;
				break;
			case "security-officer":
				haslevel = true;
				break;
			case "technic":
				haslevel = true;
				break;
			case "mtf-nu-7":
				haslevel = true;
				break;
			default:
				haslevel = false;
				break;
			}
		} catch (Exception e) {
			haslevel = false;
			System.out.println("[SCPRP] roleHasLevel Exception: " + role);
			API.sendConsoleCommand("discordsrv broadcast #839057785362448424 roleHasLevel Exception: " + role);
		}
		return haslevel;
	}

	public static boolean roleNeedsHire(User user) {
		boolean needshire = false;
		if (user.getPrimaryGroup().equalsIgnoreCase("technic")) {
			needshire = true;
		} else if (user.getPrimaryGroup().equalsIgnoreCase("mtf-nu-7")) {
			needshire = true;
		} else {
			needshire = false;
		}
		return needshire;
	}

	public static void setLevel(String name, String role, int level) {
		int xp = 0;
		switch (level) {
		case 0:
			xp = 0;
		case 1:
			xp = 0;
			break;
		case 2:
			xp = getRolesXP().get(role).get(0);
			break;
		case 3:
			xp = getRolesXP().get(role).get(1);
			;
			break;
		case 4:
			xp = getRolesXP().get(role).get(2);
			;
			break;
		default:
			API.sendMessageToEveryone("§8[§4§lERROR§8] §4§lERROR CODE: §e§lLEV0004§4§l - PLEASE, REPORT TO VOJTIISEK");
			break;
		}

		switch (role) {
		case "researcher":
			if (getResearchers().get(name) != null && getResearchers().containsKey(name))
				getResearchers().remove(name);
			getResearchers().put(name, xp);
			break;
		case "security-officer":
			if (getSecurityOfficers().get(name) != null && getSecurityOfficers().containsKey(name))
				getSecurityOfficers().remove(name);
			getSecurityOfficers().put(name, xp);
			break;
		case "technic":
			if (getTechnics().get(name) != null && getTechnics().containsKey(name))
				getTechnics().remove(name);
			getTechnics().put(name, xp);
			break;
		case "mtf-nu-7":
			if (getMTFs().get(name) != null && getMTFs().containsKey(name))
				getMTFs().remove(name);
			getMTFs().put(name, xp);
			break;
		default:
			API.sendMessageToEveryone("§8[§4§lERROR§8] §4§lERROR CODE: §e§lLEV0005§4§l - PLEASE, REPORT TO VOJTIISEK");
			break;
		}
		setNewLevel(convertXpToLevel(role, xp));
		setSetLevelBool(true);
		levelUp(name, role);
		saveFile();
	}

	public static void saveFile() {
		String entryString = "empty";
		try {
			if (!getResearchers().isEmpty()) {
				List<String> list = new ArrayList<String>();
				for (Entry<String, Integer> entry : getResearchers().entrySet()) {
					entryString = entry.getKey() + ":" + Integer.toString(entry.getValue());
					list.add(entryString);
				}
				LevelsFile.getLevelsFile().getConfig().set("Researchers", list);
			}

			entryString = "empty";
			if (!getSecurityOfficers().isEmpty()) {
				List<String> list = new ArrayList<String>();
				for (Entry<String, Integer> entry : getSecurityOfficers().entrySet()) {
					entryString = entry.getKey() + ":" + Integer.toString(entry.getValue());
					list.add(entryString);
				}
				LevelsFile.getLevelsFile().getConfig().set("SecurityOfficers", list);
			}

			entryString = "empty";
			if (!getTechnics().isEmpty()) {
				List<String> list = new ArrayList<String>();
				for (Entry<String, Integer> entry : getTechnics().entrySet()) {
					entryString = entry.getKey() + ":" + Integer.toString(entry.getValue());
					list.add(entryString);
				}
				LevelsFile.getLevelsFile().getConfig().set("Technics", list);
			}

			entryString = "empty";
			if (!getMTFs().isEmpty()) {
				List<String> list = new ArrayList<String>();
				for (Entry<String, Integer> entry : getMTFs().entrySet()) {
					entryString = entry.getKey() + ":" + Integer.toString(entry.getValue());
					list.add(entryString);
				}
				LevelsFile.getLevelsFile().getConfig().set("MTFNu7Officers", list);
			}
			
			System.out.println("Levelling System saved successfully.");
		} catch (Exception e) {
			LevelsFile.getLevelsFile().save();
			LevelsFile.getLevelsFile().reload();

			e.printStackTrace();
			saveFile();
		}

		LevelsFile.getLevelsFile().save();
		LevelsFile.getLevelsFile().reload();
	}

	public static void loadFile() {
		LevelsFile.getLevelsFile().save();
		LevelsFile.getLevelsFile().reload();
		System.out.println(" ");
		if (LevelsFile.getLevelsFile().getConfig().isList("Researchers")) {
			for (String str : LevelsFile.getLevelsFile().getConfig().getStringList("Researchers")) {
				String[] split = str.split(":");
				getResearchers().put(split[0], Integer.parseInt(split[1]));
			}
			
			System.out.println("Researchers Levels loaded successfully.");
		}

		if (LevelsFile.getLevelsFile().getConfig().isList("SecurityOfficers")) {
			for (String str : LevelsFile.getLevelsFile().getConfig().getStringList("SecurityOfficers")) {
				String[] split = str.split(":");
				getSecurityOfficers().put(split[0], Integer.parseInt(split[1]));
			}
			
			System.out.println("SecOffs Levels loaded successfully.");
		}

		if (LevelsFile.getLevelsFile().getConfig().isList("Technics")) {
			for (String str : LevelsFile.getLevelsFile().getConfig().getStringList("Technics")) {
				String[] split = str.split(":");
				getTechnics().put(split[0], Integer.parseInt(split[1]));
			}
			
			System.out.println("Technics Levels loaded successfully.");
		}

		if (LevelsFile.getLevelsFile().getConfig().isList("MTFNu7Officers")) {
			for (String str : LevelsFile.getLevelsFile().getConfig().getStringList("MTFNu7Officers")) {
				String[] split = str.split(":");
				getMTFs().put(split[0], Integer.parseInt(split[1]));
			}
			System.out.println("MTF Levels loaded successfully.");
		}
		
		System.out.println(" ");
		System.out.println("Levels loaded successfully.");
	}

	public static HashMap<String, Integer> getResearchers() {
		return researchers;
	}

	public static HashMap<String, Integer> getSecurityOfficers() {
		return secOffs;
	}

	public static HashMap<String, Integer> getMTFs() {
		return mtfnu7;
	}

	public static HashMap<String, Integer> getTechnics() {
		return technics;
	}

	public static HashMap<String, ArrayList<Integer>> getRolesXP() {
		return rolesXP;
	}

	public static boolean getSetLevelBool() {
		return setLevelBool;
	}

	public static void setSetLevelBool(boolean b) {
		setLevelBool = b;
	}

	public static int getNewLevel() {
		return newLevelSet;
	}

	public static void setNewLevel(int i) {
		newLevelSet = i;
	}
}