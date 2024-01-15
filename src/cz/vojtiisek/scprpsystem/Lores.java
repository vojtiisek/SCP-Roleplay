package cz.vojtiisek.scprpsystem;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Statistic;
import org.bukkit.entity.Player;

import Levelling.LevellingSys;
import cz.vojtiisek.scprpsystem.files.PlayerInfoFile;
import github.scarsz.discordsrv.DiscordSRV;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.model.user.User;

public class Lores {
	private SCPRP main;

	public Lores(SCPRP main) {
		this.main = main;
	}

	public static LuckPerms api;

	// 1 - §f§l
	// 2 - §a§l
	// 3 - §b§l
	// 4 - §c§l
	// 5 - §4§l

	public static ArrayList<String> infiniteCanteenLore() { // 109
		ArrayList<String> lore = new ArrayList<String>();
		lore.add("§9§o----------------------------------------------------------");
		lore.add("§8§lName:");
		lore.add("§6§lInfinite Canteen");
		lore.add("§8§lClass:");
		lore.add("§e§lEuclid");
		lore.add("§8§lLevel required:");
		lore.add("§f§l1");
		lore.add("§8§lPersonnel required:");
		lore.add("§6§l1§8§l/§7§l1§8§l/§b§l0 §8§l(§6§lClass-D§8§l/§7§lSecurity§8§l/§b§lMTF§8§l)");	
		lore.add("§8§lLocation:");
		lore.add("§7§lLCZ");
		lore.add("§9§o----------------------------------------------------------");
		lore.add("§c§lClick to get the instructions for testing this SCP object.");
		return lore;
	}

	public static List<String> sharkLore() { // 1057
		ArrayList<String> lore = new ArrayList<String>();
		lore.add("§9§o----------------------------------------------------------");
		lore.add("§8§lName:");
		lore.add("§3§lAbsence of Shark");
		lore.add("§8§lClass:");
		lore.add("§a§lSafe");
		lore.add("§8§lLevel required:");
		lore.add("§f§l1");
		lore.add("§8§lPersonnel required:");
		lore.add("§6§lX§8§l/§7§lX§8§l/§b§lX §8§l(§6§lClass-D§8§l/§7§lSecurity§8§l/§b§lMTF§8§l)");	
		lore.add("§8§lLocation:");
		lore.add("§7§lLCZ");
		lore.add("§9§o----------------------------------------------------------");
		lore.add("§c§lClick to get the instructions for testing this SCP object.");
		return lore;
	}

	public static List<String> swatArmorLore() { // 912
		ArrayList<String> lore = new ArrayList<String>();
		lore.add("§9§o----------------------------------------------------------");
		lore.add("§8§lName:");
		lore.add("§8§lAutonomous SWAT Armor");
		lore.add("§8§lClass:");
		lore.add("§a§lSafe");
		lore.add("§8§lLevel required:");
		lore.add("§f§l1");
		lore.add("§8§lPersonnel required:");
		lore.add("§6§l1§8§l/§7§l1§8§l/§b§l0 §8§l(§6§lClass-D§8§l/§7§lSecurity§8§l/§b§lMTF§8§l)");	
		lore.add("§8§lLocation:");
		lore.add("§7§lLCZ");
		lore.add("§9§o----------------------------------------------------------");
		lore.add("§c§lClick to get the instructions for testing this SCP object.");
		return lore;
	}

	public static List<String> halfCatLore() { // 529
		ArrayList<String> lore = new ArrayList<String>();
		lore.add("§9§o----------------------------------------------------------");
		lore.add("§8§lName:");
		lore.add("§7§lJosie");
		lore.add("§8§lClass:");
		lore.add("§a§lSafe");
		lore.add("§8§lLevel required:");
		lore.add("§f§l1");
		lore.add("§8§lPersonnel required:");
		lore.add("§a§lOnly you.");	
		lore.add("§8§lLocation:");
		lore.add("§7§lLCZ");
		lore.add("§9§o----------------------------------------------------------");
		lore.add("§c§lClick to get the instructions for testing this SCP object.");
		return lore;
	}

	public static List<String> crystallineButterfliesLore() { // 553
		ArrayList<String> lore = new ArrayList<String>();
		lore.add("§9§o----------------------------------------------------------");
		lore.add("§8§lName:");
		lore.add("§f§lCrystalline Butterflies");
		lore.add("§8§lClass:");
		lore.add("§a§lSafe");
		lore.add("§8§lLevel required:");
		lore.add("§a§l2");
		lore.add("§8§lPersonnel required:");
		lore.add("§6§l1§8§l/§7§l2§8§l/§b§l0 §8§l(§6§lClass-D§8§l/§7§lSecurity§8§l/§b§lMTF§8§l)");	
		lore.add("§8§lLocation:");
		lore.add("§7§lLCZ");
		lore.add("§9§o----------------------------------------------------------");
		lore.add("§c§lClick to get the instructions for testing this SCP object.");
		return lore;
	}

	public static List<String> electricSheepLore() {
		ArrayList<String> lore = new ArrayList<String>();
		lore.add("§9§o----------------------------------------------------------");
		lore.add("§8§lName:");
		lore.add("§b§lElectric Sheep");
		lore.add("§8§lClass:");
		lore.add("§e§lEuclid");
		lore.add("§8§lLevel required:");
		lore.add("§a§l2");
		lore.add("§8§lPersonnel required:");
		lore.add("§6§l1§8§l/§7§l1§8§l/§b§l0 §8§l(§6§lClass-D§8§l/§7§lSecurity§8§l/§b§lMTF§8§l)");	
		lore.add("§8§lLocation:");
		lore.add("§7§lLCZ");
		lore.add("§9§o----------------------------------------------------------");
		lore.add("§c§lClick to get the instructions for testing this SCP object.");
		return lore;
	}

	public static List<String> predatorDroneLore() {
		ArrayList<String> lore = new ArrayList<String>();
		lore.add("§9§o----------------------------------------------------------");
		lore.add("§8§lName:");
		lore.add("§5§lPredator Drone");
		lore.add("§8§lClass:");
		lore.add("§e§lEuclid");
		lore.add("§8§lLevel required:");
		lore.add("§a§l2");
		lore.add("§8§lPersonnel required:");
		lore.add("§6§lX§8§l/§7§lX§8§l/§b§lX §8§l(§6§lClass-D§8§l/§7§lSecurity§8§l/§b§lMTF§8§l)");	
		lore.add("§8§lLocation:");
		lore.add("§7§lLCZ");
		lore.add("§9§o----------------------------------------------------------");
		lore.add("§c§lClick to get the instructions for testing this SCP object.");
		return lore;
	}

	public static List<String> kitLore() {
		ArrayList<String> lore = new ArrayList<String>();
		lore.add("§7§oIf your role has a kit, clicking this will give you the items.");
		lore.add("§4§lIt is forbidden to drop your items to anyone else§c§o, even if the other player");
		lore.add("§c§ohas the same role and level as you.");
		return lore;
	}

	public static List<String> constructionItemLore() {
		ArrayList<String> lore = new ArrayList<String>();
		lore.add("§7§oBegins the §e§oConstruction creation Wizard§7§o.");
		lore.add("§c§oType §4§lexit§c§o to cancel the creation.");
		return lore;
	}

	public static List<String> removeConst() {
		ArrayList<String> lore = new ArrayList<String>();
		lore.add("§e§oRemove this construction.");
		lore.add("§c§oPlease, do not use this option for completing the construction.");
		lore.add("§8§o(when it is done)");
		return lore;
	}

	public static List<String> completeConst() {
		ArrayList<String> lore = new ArrayList<String>();
		lore.add("§a§oMark this construction as Completed");
		lore.add("§e§oPlease, do not use this option before it is built and 100% done.");
		return lore;
	}

	public static List<String> playerLore(String user) {
		ArrayList<String> lore = new ArrayList<String>();
		lore.add("§8§o------------------");
		lore.add("§7§oNickname:");
		lore.add("§c§l" + user);
		return lore;
	}

	public static List<String> timeLore(String user) {
		Player player = Bukkit.getPlayerExact(user);
		ArrayList<String> lore = new ArrayList<String>();
		lore.add("§8§o---------");
		lore.add("§7§oRegistered on:");
		if (PlayerInfoFile.getPlayerInfoFile().getConfig().get("Players." + user + ".RegisterDate") != null) {
			lore.add("§a§l" + (PlayerInfoFile.getPlayerInfoFile().getConfig().get("Players." + user + ".RegisterDate") + " §7§o(DD/MM/YYYY)"));
		} else {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			LocalDateTime now = LocalDateTime.now();
			String str = "error";
			str = dtf.format(now);
			lore.add("§a§l" + PlayerInfoFile.getPlayerInfoFile().getConfig().get("Players." + user + ".RegisterDate") + " §7§o(DD/MM/YYYY)");
		}
		lore.add(" ");
		lore.add("§7§oTime played:");
		lore.add("§a§l" + API.formatTimeDHM((long) ((player.getStatistic(Statistic.PLAY_ONE_TICK) / 20) * 1000)));
		return lore;
	}

	public static List<String> roleLevelLore(String name, User user) {
		ArrayList<String> lore = new ArrayList<String>();
		lore.add("§8§o------------");
		lore.add("§f§oRole:");
		switch (user.getPrimaryGroup()) {
		case "default":
			lore.add("§6§lClass-D");
			break;
		case "class-d":
			lore.add("§6§lClass-D");
			break;
		case "medic":
			lore.add("§d§lMedic");
			break;
		case "technic":
			lore.add("§6§lTechnic");
			break;
		case "researcher":
			lore.add("§2§lResearcher");
			break;
		case "security-officer":
			lore.add("§7§lSecurity Officer");
			break;
		case "mtf-nu-7":
			lore.add("§9§lMTF Nu-7 Officer");
			break;
		case "mtf-nu-7-commander":
			lore.add("§9§lMTF Nu-7 §4§lCommander");
			break;
		case "head-medic":
			lore.add("§d§lHead Medic");
			break;
		case "head-researcher":
			lore.add("§a§lHead Researcher");
			break;
		case "head-security-officer":
			lore.add("§7§lHead Security Officer");
			break;
		case "site-director":
			lore.add("§e§lSite Director");
			break;
		case "o5-1":
			lore.add("§4§lO5-1");
			break;
		case "o5-2":
			lore.add("§4§lO5-2");
			break;
		case "o5-3":
			lore.add("§4§lO5-3");
			break;
		case "o5-4":
			lore.add("§4§lO5-4");
			break;
		case "o5-5":
			lore.add("§4§lO5-5");
			break;
		case "o5-6":
			lore.add("§4§lO5-6");
			break;
		case "o5-7":
			lore.add("§4§lO5-7");
			break;
		case "the-administrator":
			lore.add("§c§lThe Administrator");
			break;
		case "judge":
			lore.add("§5§lJudge");
			break;
		case "storage-leader":
			lore.add("§e§lStorage Leader");
			break;
		default:
			lore.add("§4§lERR§C§LOR §c§ocontact The Ad§4§oministrator");
			break;
		}
		lore.add("§f§oLevel:");
		switch (user.getPrimaryGroup()) {
		case "technic":
			lore.add("§b§l" + LevellingSys.getXP(name) + " XP");
			lore.add(
					"§9§oLevel §b§l" + LevellingSys.convertXpToLevel(user.getPrimaryGroup(), LevellingSys.getXP(name)));
			break;
		case "researcher":
			lore.add("§b§l" + LevellingSys.getXP(name) + " XP");
			lore.add(
					"§9§oLevel §b§l" + LevellingSys.convertXpToLevel(user.getPrimaryGroup(), LevellingSys.getXP(name)));
			break;
		case "security-officer":
			lore.add("§b§l" + LevellingSys.getXP(name) + " XP");
			lore.add(
					"§9§oLevel §b§l" + LevellingSys.convertXpToLevel(user.getPrimaryGroup(), LevellingSys.getXP(name)));
			break;
		case "mtf-nu-7":
			lore.add("§b§l" + LevellingSys.getXP(name) + " XP");
			lore.add(
					"§9§oLevel §b§l" + LevellingSys.convertXpToLevel(user.getPrimaryGroup(), LevellingSys.getXP(name)));
			break;
		default:
			lore.add("§c§oThis role does not level up.");
			break;
		}
		return lore;
	}

	public static List<String> NaufixRoleLore(String user) {
		String prefix = "§4§lERR§c§lOR";
		ArrayList<String> lore = new ArrayList<String>();
		lore.add("§8§o-----------");
		lore.add("§c§oIs Naufix member?");
		if (API.isNaufixMember(user)) {
			if (API.getAdmins().contains(user))
				prefix = "§c§lAdmin";
			if (API.getModerators().contains(user))
				prefix = "§b§lMod";
			if (API.getVIPs().contains(user))
				prefix = "§e§lVIP";
			lore.add("§a§lTRUE §7(" + prefix + "§7)");
		} else {
			lore.add("§c§lFALSE");
		}
		return lore;
	}

	public static List<String> desigLore(String user) {
		ArrayList<String> lore = new ArrayList<String>();
		lore.add("§8§o--------------------");
		lore.add("§6§l" + API.toStringDesig(ClassDDesignations.getPlayerDesignation(user)));
		return lore;
	}

	public static List<String> cellIDLore(String user) {
		ArrayList<String> lore = new ArrayList<String>();
		lore.add("§8§o---------------");
		lore.add("§6§l" + API.findCellIdByPname(user));
		lore.add("§7§oClick this item for more information about §6§o" + user + "§7§o's cell.");
		return lore;
	}

	public static List<String> currentShiftLore(String user) {
		ArrayList<String> lore = new ArrayList<String>();
		try {
			if (PlayerInfoFile.getPlayerInfoFile().getConfig().get("Players." + user + ".SelectedShift") != null && !PlayerInfoFile.getPlayerInfoFile().getConfig().getString("Players." + user + ".SelectedShift").isEmpty()) {
				lore.add(PlayerInfoFile.getPlayerInfoFile().getConfig().getString("Players." + user + ".SelectedShift"));
			} else {
				lore.add("§f§lOn duty.");
				API.sendConsoleCommand("discord broadcast #839057785362448424 [SCPRP Lores] API.getPlayerInformations() null/empty - currentShiftLore(user)");
			}
		} catch(IndexOutOfBoundsException e) {
			lore.add("§f§lOn duty.");
			e.printStackTrace();
			API.sendConsoleCommand("discord broadcast #839057785362448424 [SCPRP Lores] API.getPlayerInformations() IndexOutOfBoundsException - currentShiftLore(user)");
		}
		return lore;
	}

	public static List<String> S2TaskLore() {
		ArrayList<String> lore = new ArrayList<String>();
		lore.add("§7§o(except Class-D Cells & Scientific Department)");
		return lore;
	}

	public static List<String> peanutLore() {
		ArrayList<String> lore = new ArrayList<String>();
		lore.add("§9§o----------------------------------------------------------");
		lore.add("§8§lName:");
		lore.add("§5§lThe Sculpture");
		lore.add("§8§lClass:");
		lore.add("§e§lEuclid");
		lore.add("§8§lLevel required:");
		lore.add("§a§l2");
		lore.add("§8§lPersonnel required:");
		lore.add("§6§l3§8§l/§7§l2§8§l/§b§l0 §8§l(§6§lClass-D§8§l/§7§lSecurity§8§l/§b§lMTF§8§l)");	
		lore.add("§8§lLocation:");
		lore.add("§7§lHCZ");
		lore.add("§9§o----------------------------------------------------------");
		lore.add("§c§lClick to get the instructions for testing this SCP object.");
		return lore;
	}

	public static List<String> networkCompleteToolLore() {
		ArrayList<String> lore = new ArrayList<String>();
		lore.add("§8§o---------------");
		lore.add("§6§lRight click with this tool to finish creating a network.");
		return lore;
	}

	public static List<String> coffeeMachLore() {
		ArrayList<String> lore = new ArrayList<String>();
		lore.add("§9§o----------------------------------------------------------");
		lore.add("§8§lName:");
		lore.add("§6§lThe Coffee Machine");
		lore.add("§8§lClass:");
		lore.add("§a§lSafe");
		lore.add("§8§lLevel required:");
		lore.add("§f§l2");
		lore.add("§8§lPersonnel required:");
		lore.add("§6§l1§8§l/§7§l1§8§l/§b§l0 §8§l(§6§lClass-D§8§l/§7§lSecurity§8§l/§b§lMTF§8§l)");	
		lore.add("§8§lLocation:");
		lore.add("§7§lLCZ");
		lore.add("§9§o----------------------------------------------------------");
		lore.add("§c§lClick to get the instructions for testing this SCP object.");
		return lore;
	}

	public static List<String> blackShuckLore() {
		ArrayList<String> lore = new ArrayList<String>();
		lore.add("§9§o----------------------------------------------------------");
		lore.add("§8§lName:");
		lore.add("§8§lBlack Shuck");
		lore.add("§8§lClass:");
		lore.add("§e§lEuclid");
		lore.add("§8§lLevel required:");
		lore.add("§f§l3");
		lore.add("§8§lPersonnel required:");
		lore.add("§6§l1§8§l/§7§l2§8§l/§b§l0 §8§l(§6§lClass-D§8§l/§7§lSecurity§8§l/§b§lMTF§8§l)");	
		lore.add("§8§lLocation:");
		lore.add("§7§lHCZ");
		lore.add("§9§o----------------------------------------------------------");
		lore.add("§c§lClick to get the instructions for testing this SCP object.");
		return lore;
	}

	public static List<String> plagueDocLore() {
		ArrayList<String> lore = new ArrayList<String>();
		lore.add("§9§o----------------------------------------------------------");
		lore.add("§8§lName:");
		lore.add("§3§lPlague Doctor");
		lore.add("§8§lClass:");
		lore.add("§e§lEuclid");
		lore.add("§8§lLevel required:");
		lore.add("§f§l3");
		lore.add("§8§lPersonnel required:");
		lore.add("§6§l2§8§l/§7§l2§8§l/§b§l0 §8§l(§6§lClass-D§8§l/§7§lSecurity§8§l/§b§lMTF§8§l)");	
		lore.add("§8§lLocation:");
		lore.add("§7§lHCZ");
		lore.add("§9§o----------------------------------------------------------");
		lore.add("§c§lClick to get the instructions for testing this SCP object.");
		return lore;
	}

	public static List<String> shyGuyLore() {
		ArrayList<String> lore = new ArrayList<String>();
		lore.add("§9§o----------------------------------------------------------");
		lore.add("§8§lName:");
		lore.add("§f§lThe Shy Guy");
		lore.add("§8§lClass:");
		lore.add("§e§lEuclid");
		lore.add("§8§lLevel required:");
		lore.add("§f§l3");
		lore.add("§8§lPersonnel required:");
		lore.add("§6§l2§8§l/§7§l2§8§l/§b§l0 §8§l(§6§lClass-D§8§l/§7§lSecurity§8§l/§b§lMTF§8§l)");	
		lore.add("§8§lLocation:");
		lore.add("§7§lHCZ");
		lore.add("§9§o----------------------------------------------------------");
		lore.add("§c§lClick to get the instructions for testing this SCP object.");
		return lore;
	}

	public static List<String> bearsLore() {
		ArrayList<String> lore = new ArrayList<String>();
		lore.add("§9§o----------------------------------------------------------");
		lore.add("§8§lName:");
		lore.add("§6§lBuilder Bear");
		lore.add("§8§lClass:");
		lore.add("§c§lKeter");
		lore.add("§8§lLevel required:");
		lore.add("§f§l3");
		lore.add("§8§lPersonnel required:");
		lore.add("§6§l1§8§l/§7§l2§8§l/§b§l0 §8§l(§6§lClass-D§8§l/§7§lSecurity§8§l/§b§lMTF§8§l)");	
		lore.add("§8§lLocation:");
		lore.add("§7§lHCZ");
		lore.add("§9§o----------------------------------------------------------");
		lore.add("§c§lClick to get the instructions for testing this SCP object.");
		return lore;
	}

	public static List<String> reptileLore() {
		ArrayList<String> lore = new ArrayList<String>();
		lore.add("§9§o----------------------------------------------------------");
		lore.add("§8§lName:");
		lore.add("§2§lHard-to-Destroy Reptile");
		lore.add("§8§lClass:");
		lore.add("§c§lKeter");
		lore.add("§8§lLevel required:");
		lore.add("§f§l4");
		lore.add("§8§lPersonnel required:");
		lore.add("§6§l1§8§l/§7§l2§8§l/§b§l2 §8§l(§6§lClass-D§8§l/§7§lSecurity§8§l/§b§lMTF§8§l)");	
		lore.add("§8§lLocation:");
		lore.add("§7§lHCZ");
		lore.add("§9§o----------------------------------------------------------");
		lore.add("§c§lClick to get the instructions for testing this SCP object.");
		return lore;
	}

	public static List<String> skeletonKeyLore() {
		ArrayList<String> lore = new ArrayList<String>();
		lore.add("§9§o----------------------------------------------------------");
		lore.add("§8§lName:");
		lore.add("§7§lSkeleton Key");
		lore.add("§8§lClass:");
		lore.add("§a§lSafe");
		lore.add("§8§lLevel required:");
		lore.add("§f§l4 §4§l+ Level 5 approval");
		lore.add("§8§lPersonnel required:");
		lore.add("§6§l0§8§l/§7§l1§8§l/§b§l1 §8§l(§6§lClass-D§8§l/§7§lSecurity§8§l/§b§lMTF§8§l)");	
		lore.add("§8§lLocation:");
		lore.add("§7§lHCZ");
		lore.add("§9§o----------------------------------------------------------");
		lore.add("§c§lClick to get the instructions for testing this SCP object.");
		return lore;
	}

	public static List<String> oldManLore() {
		ArrayList<String> lore = new ArrayList<String>();
		lore.add("§9§o----------------------------------------------------------");
		lore.add("§8§lName:");
		lore.add("§c§lThe Old Man");
		lore.add("§8§lClass:");
		lore.add("§a§lSafe");
		lore.add("§8§lLevel required:");
		lore.add("§f§l4 §4§l+ Level 5 approval");
		lore.add("§8§lPersonnel required:");
		lore.add("§6§l1§8§l/§7§l4§8§l/§b§l1 §8§l(§6§lClass-D§8§l/§7§lSecurity§8§l/§b§lMTF§8§l)");	
		lore.add("§8§lLocation:");
		lore.add("§7§lHCZ");
		lore.add("§9§o----------------------------------------------------------");
		lore.add("§c§lClick to get the instructions for testing this SCP object.");
		return lore;
	}

	public static List<String> manyVoicesLore() {
		ArrayList<String> lore = new ArrayList<String>();
		lore.add("§9§o----------------------------------------------------------");
		lore.add("§8§lName:");
		lore.add("§e§lWith Many Voices");
		lore.add("§8§lClass:");
		lore.add("§c§lKeter");
		lore.add("§8§lLevel required:");
		lore.add("§f§l3");
		lore.add("§8§lPersonnel required:");
		lore.add("§6§lX§8§l/§7§lX§8§l/§b§lX §8§l(§6§lClass-D§8§l/§7§lSecurity§8§l/§b§lMTF§8§l)");	
		lore.add("§8§lLocation:");
		lore.add("§7§lHCZ");
		lore.add("§9§o----------------------------------------------------------");
		lore.add("§c§lClick to get the instructions for testing this SCP object.");
		return lore;
	}
	
	public static List<String> sellMinedOreLore() {
		ArrayList<String> lore = new ArrayList<String>();
		lore.add("§cLEFT CLICK §e§oto sell everything");
		lore.add("§b§oRIGHT CLICK §e§oto sell just one piece");
		return lore;
	}
}