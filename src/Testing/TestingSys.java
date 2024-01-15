package Testing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import com.earth2me.essentials.api.Economy;
import com.earth2me.essentials.api.NoLoanPermittedException;
import com.earth2me.essentials.api.UserDoesNotExistException;

import Levelling.LevellingSys;
import cz.vojtiisek.scprpsystem.API;
import cz.vojtiisek.scprpsystem.RPAntiCheat;
import cz.vojtiisek.scprpsystem.SCPRP;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.model.user.User;

public class TestingSys implements Listener {
	private static SCPRP main;

	public static HashMap<String, Boolean> begunTests = new HashMap<String, Boolean>();

	public static HashMap<String, ArrayList<Integer>> scps = new HashMap<String, ArrayList<Integer>>();
	public static HashMap<String, String> scpPerms = new HashMap<String, String>();
	public static List<String> safe = new ArrayList<String>();
	public static List<String> euclid = new ArrayList<String>();
	public static List<String> keter = new ArrayList<String>();
	public static HashMap<String, ArrayList<String>> testMembers = new HashMap<String, ArrayList<String>>(0);
	public static HashMap<String, ArrayList<String>> playersLeft = new HashMap<String, ArrayList<String>>(0);
	public static HashMap<String, ArrayList<String>> kickedPlayers = new HashMap<String, ArrayList<String>>(0);
	public static ArrayList<String> attendingMembers = new ArrayList<String>();

	public static HashMap<String, Date> beginningTimes = new HashMap<String, Date>();
	public static HashMap<String, Date> pauseTimes = new HashMap<String, Date>();
	public static HashMap<String, Date> unpausedTimes = new HashMap<String, Date>();
	public static HashMap<String, Date> timeDifferences = new HashMap<String, Date>();

	public static HashMap<String, Long> testCooldowns = new HashMap<String, Long>();

	public static HashMap<String, Integer> joinedClassDs = new HashMap<String, Integer>();
	public static HashMap<String, Integer> joinedSecOffs = new HashMap<String, Integer>();
	public static HashMap<String, Integer> joinedMTFs = new HashMap<String, Integer>();

	static LuckPerms api = LuckPermsProvider.get();

	/*******************************************************************************/
	// 0 - classd, 1 - security officers, 2 - mtf,
	// LCZ
	static ArrayList<Integer> shark = new ArrayList<Integer>(Arrays.asList(0, 0, 0)); // 1057
	static ArrayList<Integer> swat = new ArrayList<Integer>(Arrays.asList(1, 1, 0)); // 912
	static ArrayList<Integer> crystalline = new ArrayList<Integer>(Arrays.asList(1, 2, 0)); // 553
	static ArrayList<Integer> elsheep = new ArrayList<Integer>(Arrays.asList(1, 1, 0)); // 594
	static ArrayList<Integer> drone = new ArrayList<Integer>(Arrays.asList(1, 1, 0)); // 160
	static ArrayList<Integer> josie = new ArrayList<Integer>(Arrays.asList(0, 0, 0)); // 529
	static ArrayList<Integer> coffeemach = new ArrayList<Integer>(Arrays.asList(1, 1, 0)); // 294
	static ArrayList<Integer> infinitecan = new ArrayList<Integer>(Arrays.asList(1, 1, 0)); // 109
	static ArrayList<Integer> skeletonkey = new ArrayList<Integer>(Arrays.asList(0, 1, 1)); // 005

	// HCZ
	static ArrayList<Integer> oldman = new ArrayList<Integer>(Arrays.asList(1, 4, 1)); // 106
	static ArrayList<Integer> shyguy = new ArrayList<Integer>(Arrays.asList(2, 2, 0)); // 096
	static ArrayList<Integer> blackshuck = new ArrayList<Integer>(Arrays.asList(1, 2, 0)); // 023
	static ArrayList<Integer> builderbears = new ArrayList<Integer>(Arrays.asList(1, 2, 0)); // 1048
	static ArrayList<Integer> reptile = new ArrayList<Integer>(Arrays.asList(1, 2, 2)); // 682
	static ArrayList<Integer> dog = new ArrayList<Integer>(Arrays.asList(1, 2, 0)); // 939
	static ArrayList<Integer> bigfoot = new ArrayList<Integer>(Arrays.asList(1, 1, 0)); // 1000
	static ArrayList<Integer> plaguedoc = new ArrayList<Integer>(Arrays.asList(2, 2, 0)); // 049
	static ArrayList<Integer> peanut = new ArrayList<Integer>(Arrays.asList(3, 2, 0)); // 173

	/*******************************************************************************/

	public TestingSys(SCPRP main) {
		this.main = main;
	}

	public static void addPersonnelReq() {
		// lcz
		scps.put("1057", shark);
		scps.put("912", swat);
		scps.put("553", crystalline);
		scps.put("594", elsheep);
		scps.put("160", drone);
		scps.put("529", josie);
		scps.put("294", coffeemach);
		scps.put("109", infinitecan);
		scps.put("005", skeletonkey);

		// hcz
		scps.put("106", oldman);
		scps.put("096", shyguy);
		scps.put("023", blackshuck);
		scps.put("1048", builderbears);
		scps.put("682", reptile);
		scps.put("939", dog);
		scps.put("1000", bigfoot);
		scps.put("049", plaguedoc);
		scps.put("173", peanut);
		
		System.out.println(" ");
		System.out.println("Testing System - Personnel required loaded successfully.");
	}

	public static void addSCPPerms() {
		// lcz
		scpPerms.put("1057", "scprp.scps1");
		scpPerms.put("912", "scprp.scps1");
		scpPerms.put("553", "scprp.scps2");
		scpPerms.put("594", "scprp.scps2");
		scpPerms.put("160", "scprp.scps2");
		scpPerms.put("529", "scprp.scps1");
		scpPerms.put("294", "scprp.scps2");
		scpPerms.put("109", "scprp.scps1");
		scpPerms.put("005", "scprp.scps4");

		// hcz
		scpPerms.put("106", "scprp.scps4");
		scpPerms.put("096", "scprp.scps3");
		scpPerms.put("023", "scprp.scps3");
		scpPerms.put("1048", "scprp.scps3");
		scpPerms.put("682", "scprp.scps4");
		scpPerms.put("939", "scprp.scps3");
		scpPerms.put("1000", "scprp.scps2");
		scpPerms.put("049", "scprp.scps3");
		scpPerms.put("173", "scprp.scps2");

		System.out.println("Testing System - Per-SCP permissions loaded successfully.");
	}
	
	public static void addClasses() {
		safe.add("1057");
		safe.add("912");
		safe.add("553");
		safe.add("529");
		safe.add("109");
		safe.add("005");

		euclid.add("594");
		euclid.add("160");
		euclid.add("294");
		euclid.add("096");
		euclid.add("023");
		euclid.add("049");
		euclid.add("173");

		keter.add("106");
		keter.add("1048");
		keter.add("682");
		keter.add("939");
		keter.add("1000");
		
		System.out.println("Testing System - SCP Object Classes loaded successfully.");
	}

	public static void addMember(String SCP, String playerName) {
		Player player = Bukkit.getPlayerExact(playerName);
		if (testHasBegun(SCP) && !pauseTimes.containsKey(SCP)) {
			player.sendMessage("§8[§c§l!§8] §c§oThis test has already begun!");
		} else if ((testHasBegun(SCP) && pauseTimes.containsKey(SCP)) || !testHasBegun(SCP)) {
			int classds = getPersonnelReqDb().get(SCP).get(0);
			int secOffs = getPersonnelReqDb().get(SCP).get(1);
			int mtf = getPersonnelReqDb().get(SCP).get(2);

			int jClassds = joinedClassDs.get(SCP);
			int jSecOffs = joinedSecOffs.get(SCP);
			int jMtf = joinedMTFs.get(SCP);
			if (!attendingMembers.contains(playerName)) {
				if (kickedPlayers.containsKey(SCP) && !kickedPlayers.get(SCP).contains(playerName)) {
					if (api.getUserManager().getUser(playerName).getPrimaryGroup().equalsIgnoreCase("class-d")) {
						if (classds - jClassds == 0) {
							player.sendMessage(
									"§8[§c§l!§8] §c§oEnough players with this role have already joined this testing!");
						} else {
							attendingMembers.add(playerName);
							testMembers.get(SCP).add(playerName);
							joinedClassDs.put(SCP, joinedClassDs.get(SCP) + 1);
							API.sendRequirementMessage2(SCP);
							checkPersonnelCount(SCP);
						}
					} else if (api.getUserManager().getUser(playerName).getPrimaryGroup()
							.equalsIgnoreCase("security-officer")
							|| api.getUserManager().getUser(playerName).getPrimaryGroup()
									.equalsIgnoreCase("head-security-officer")) {
						if (secOffs - jSecOffs == 0) {
							player.sendMessage(
									"§8[§c§l!§8] §c§oEnough players with this role have already joined this testing!");
						} else {
							attendingMembers.add(playerName);
							testMembers.get(SCP).add(playerName);
							joinedSecOffs.put(SCP, joinedSecOffs.get(SCP) + 1);
							API.sendRequirementMessage2(SCP);
							checkPersonnelCount(SCP);
						}
					} else if (api.getUserManager().getUser(playerName).getPrimaryGroup().equalsIgnoreCase("mtf-nu-7")
							|| api.getUserManager().getUser(playerName).getPrimaryGroup()
									.equalsIgnoreCase("mtf-nu7-commander")) {
						if (mtf - jMtf == 0) {
							player.sendMessage(
									"§8[§c§l!§8] §c§oEnough players with this role have already joined this testing!");
						} else {
							attendingMembers.add(playerName);
							testMembers.get(SCP).add(playerName);
							joinedMTFs.put(SCP, joinedMTFs.get(SCP) + 1);
							API.sendRequirementMessage2(SCP);
							checkPersonnelCount(SCP);
						}
					} else {
						Bukkit.getServer().getPlayer(playerName)
								.sendMessage("§8[§c§l!§8] §c§oYou can not join a SCP testing!");
					}
				} else if (kickedPlayers.containsKey(SCP) && kickedPlayers.get(SCP).contains(playerName)) {
					Bukkit.getServer().getPlayer(playerName)
							.sendMessage("§8[§c§l!§8] §c§oYou can not join a test you were kicked out of!");
				} else {
					kickedPlayers.put(SCP, new ArrayList<String>());
					addMember(SCP, playerName);
				}
			} else {
				Bukkit.getServer().getPlayer(playerName)
						.sendMessage("§8[§c§l!§8] §c§oYou are already a member of a test!");
			}
		}
	}

	public static void checkPersonnelCount(String SCP) {

		int classds = getPersonnelReqDb().get(SCP).get(0);
		int secOffs = getPersonnelReqDb().get(SCP).get(1);
		int mtf = getPersonnelReqDb().get(SCP).get(2);

		int jClassds = joinedClassDs.get(SCP);
		int jSecOffs = joinedSecOffs.get(SCP);
		int jMtf = joinedMTFs.get(SCP);

		if (classds - jClassds == 0 && secOffs - jSecOffs == 0 && mtf - jMtf == 0) {
			if (pauseTimes.containsKey(SCP)) {
				beginTestAfterPause(SCP);
			} else {
				beginTest(SCP);
			}
		}
	}

	private static boolean testHasBegun(String scpdesignation) {
		if (begunTests.get(scpdesignation).equals(true)) {
			return true;
		} else {
			return false;
		}
	}

	public static void createTest(String scp) {
		joinedClassDs.put(scp, 0);
		joinedSecOffs.put(scp, 0);
		joinedMTFs.put(scp, 0);
	}

	public static void beginTest(String scp) {
		begunTests.put(scp, true);

		for (String str : testMembers.get(scp)) {
			Player p = Bukkit.getPlayerExact(str);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_PLING, 2.0F, 1.0F);
		}

		API.sendMessageToEveryone("§8[§a§l!§8] §e§oTesting of §c§lSCP-" + scp + " §e§ohas begun. Use §c§o/test list "
				+ scp + " §e§oto view the attending personnel.");

		API.sendConsoleCommand("discordsrv broadcast #831086704218996776 **Test begun:**");
		API.sendConsoleCommand("discordsrv broadcast #831086704218996776 SCP: **" + scp + "**");
		API.sendConsoleCommand(
				"discordsrv broadcast #831086704218996776 Researcher: **" + testMembers.get(scp).get(0) + "**");
		API.sendConsoleCommand("discordsrv broadcast #831086704218996776 Class-D(s): **" + getClassDs(scp) + "**");
		API.sendConsoleCommand(
				"discordsrv broadcast #831086704218996776 Security Officer(s): **" + getSecurityOfficers(scp) + "**");
		API.sendConsoleCommand("discordsrv broadcast #831086704218996776 MTF Nu-7 Officers(s): **" + getMTFs(scp) + "**");

		startStopwatch(scp);
		// RPAntiCheat.startAntiTestCheat(scp);
	}

	public static void beginTestAfterPause(String scp) {
		for (String str : testMembers.get(scp)) {
			Player p = Bukkit.getPlayerExact(str);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_PLING, 2.0F, 1.0F);
		}

		API.sendMessageToEveryone("§8[§a§l!§8] §e§oTesting of §c§lSCP-" + scp + " §e§ohas begun. Use §c§o/test list "
				+ scp + " §e§oto view the attending personnel.");

		API.sendConsoleCommand("discordsrv broadcast #831086704218996776 **Test unpaused:**");
		API.sendConsoleCommand("discordsrv broadcast #831086704218996776 SCP: **" + scp + "**");
		API.sendConsoleCommand(
				"discordsrv broadcast #831086704218996776 Researcher: **" + testMembers.get(scp).get(0) + "**");
		API.sendConsoleCommand("discordsrv broadcast #831086704218996776 Class-D(s): **" + getClassDs(scp) + "**");
		API.sendConsoleCommand(
				"discordsrv broadcast #831086704218996776 Security Officer(s): **" + getSecurityOfficers(scp) + "**");
		API.sendConsoleCommand("discordsrv broadcast #831086704218996776 MTF Nu-7 Officers(s): **" + getMTFs(scp) + "**");

		unpauseStopwatch(scp);
	}

	public static void startStopwatch(String scp) {
		Date date = new Date(System.currentTimeMillis());
		beginningTimes.put(scp, date);
	}

	public static String stopStopwatchAndGetDifference(String scp) {

		Date beginningDate = getBeginningTime(scp);
		Date endDate = new Date(System.currentTimeMillis());
		String time = "";
		try {
			if (begunTests == null || begunTests.isEmpty()) {
				begunTests.put(scp, false);
				stopStopwatchAndGetDifference(scp);
			} else if (begunTests.get(scp) == true) {
				if (timeDifferences.containsKey(scp) && !timeDifferences.get(scp).equals(null)) {
					Date differences = timeDifferences.get(scp);
					long endTime = endDate.getTime() - beginningDate.getTime();
					long testTime = endTime - differences.getTime();
					time = API.formatTime(testTime);
				} else {
					long difference = endDate.getTime() - beginningDate.getTime();
					time = API.formatTime(difference);
				}
			}
		} catch (NullPointerException e) {
			e.printStackTrace();
			API.sendConsoleCommand(
					"discordsrv broadcast #839057785362448424 [RPAC] stopStopwatchAndGetDifference(scp) null, returning 00:00");
			time = "00:00";
		}
		return time;
	}

	public static long stopStopwatchAndGetMsDifference(String scp) {
		long time = 0;
		try {
			if (begunTests == null) {
				begunTests.put(scp, false);
				stopStopwatchAndGetMsDifference(scp);
			} else if (begunTests.get(scp)) {
				Date beginningDate = getBeginningTime(scp);
				Date endDate = new Date(System.currentTimeMillis());
				if (timeDifferences.containsKey(scp) && !timeDifferences.get(scp).equals(null)) {
					Date differences = timeDifferences.get(scp);
					long endTime = endDate.getTime() - beginningDate.getTime();
					long testTime = endTime - differences.getTime();
					time = testTime;
				} else {
					long difference = endDate.getTime() - beginningDate.getTime();
					time = difference;
				}
			}
		} catch (NullPointerException e) {
			e.printStackTrace();
			API.sendConsoleCommand(
					"discordsrv broadcast #839057785362448424 [RPAC] stopStopwatchAndGet**Ms**Difference(scp) null, returning 0");
			time = 0;
		}
		return time;
	}

	public void pauseStopwatch(String test) {
		Date date = new Date(System.currentTimeMillis());
		if (pauseTimes.containsKey(test)) {
			pauseTimes.remove(test);
			pauseTimes.put(test, date);
		} else {
			pauseTimes.put(test, date);
		}
	}

	public static void unpauseStopwatch(String test) {
		Date date = new Date(System.currentTimeMillis());
		Date pausedAt = pauseTimes.get(test);
		long difference = date.getTime() - pausedAt.getTime();

		Date dateFormatted = new Date(difference);

		if (timeDifferences.containsKey(test)) {
			Date oldDifference = timeDifferences.get(test);
			long newDifference = oldDifference.getTime() + difference;

			Date dateFormattedNew = new Date(newDifference);
			timeDifferences.put(test, dateFormattedNew);
		} else {
			timeDifferences.put(test, dateFormatted);
		}

		unpausedTimes.put(test, date);
		pauseTimes.remove(test);
	}

	public static Date getBeginningTime(String scp) {
		return beginningTimes.get(scp);
	}

	public static void removeBeginningTime(String scp) {
		beginningTimes.remove(scp);
	}

	public static String getClassDs(String scp) {
		String names = "";
		for (String playerName : testMembers.get(scp)) {
			if (api.getUserManager().getUser(playerName).getPrimaryGroup().equalsIgnoreCase("class-d")) {
				names = names + playerName + ", ";
			} else {
			}
		}
		return names;
	}

	public static String getSecurityOfficers(String scp) {
		String names = "";
		for (String playerName : testMembers.get(scp)) {
			if (api.getUserManager().getUser(playerName).getPrimaryGroup().equalsIgnoreCase("security-officer")) {
				names = names + playerName + ", ";
			} else {
			}
		}
		return names;
	}

	public static String getMTFs(String scp) {
		String names = "";
		for (String playerName : testMembers.get(scp)) {
			if (api.getUserManager().getUser(playerName).getPrimaryGroup().equalsIgnoreCase("mtf-nu-7")) {
				names = names + playerName + ", ";
			} else {
			}
		}
		return names;
	}

	public static void endTest(String scp) {
		if (!pauseTimes.containsKey(scp)) {

			for (String str : testMembers.get(scp)) {
				Player p = Bukkit.getPlayerExact(str);
				p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 2.0F, 1.0F);
			}
			
			LevellingSys.addRolesXP();
			giveTestRewards(scp);
			if(begunTests.containsKey(scp))	{
				API.sendMessageToEveryone("§8[§a§l!§8] §e§oTesting of §c§lSCP-" + scp + "§e§o has ended. Time: " + API.formatTime(stopStopwatchAndGetMsDifference(scp)));
			} else {
				API.sendMessageToEveryone("§8[§a§l!§8] §e§oTesting of §c§lSCP-" + scp + "§e§o has ended.");
			}
					

			API.sendConsoleCommand("discordsrv broadcast #831086704218996776 **Test ended:**");
			API.sendConsoleCommand("discordsrv broadcast #831086704218996776 SCP: **" + scp + "**");
			API.sendConsoleCommand(
					"discordsrv broadcast #831086704218996776 Researcher: **" + testMembers.get(scp).get(0) + "**");
			API.sendConsoleCommand("discordsrv broadcast #831086704218996776 Class-D(s): **" + getClassDs(scp) + "**");
			API.sendConsoleCommand(
					"discordsrv broadcast #831086704218996776 Security Officer(s): **" + getSecurityOfficers(scp) + "**");
			API.sendConsoleCommand(
					"discordsrv broadcast #831086704218996776 MTF Nu-7 Officers(s): **" + getMTFs(scp) + "**");
			API.sendConsoleCommand(
					"discordsrv broadcast #831086704218996776 Players, who left: **" + getLeftPlayers(scp) + "**");
			API.sendConsoleCommand(
					"discordsrv broadcast #831086704218996776 Players, who got kicked: **" + getKickedPlayers(scp) + "**");

		if(begunTests.containsKey(scp))	API.sendConsoleCommand(
					"discordsrv broadcast #831086704218996776 Test time: **" + stopStopwatchAndGetDifference(scp) + "**");

			removeFromAttendingMembers(scp);
			joinedClassDs.remove(scp);
			joinedSecOffs.remove(scp);
			joinedMTFs.remove(scp);
			if (begunTests.containsKey(scp)) {
				begunTests.remove(scp);
				getTestCooldowns().put(testMembers.get(scp).get(0), System.currentTimeMillis() + 15 * 1000 * 60);
			}

			if (SCPRP.ongoingTests.contains(scp)) {
				SCPRP.ongoingTests.remove(scp);
			} else {
				API.sendMessageToEveryone("§8[§4§lERROR§8] §4§lERROR CODE: §e§l0001§4§l - PLEASE, REPORT TO VOJTIISEK");
			}
			testMembers.remove(scp);
			if (kickedPlayers.containsKey(scp))
				kickedPlayers.remove(scp);
			if (playersLeft.containsKey(scp))
				playersLeft.remove(scp);

		} else {
			terminateTestNoPersonnel(scp);
		}

	}

	private static String getKickedPlayers(String scp) {
		String names = "";
		if (kickedPlayers.containsKey(scp)) {
			for (String str : kickedPlayers.get(scp)) {
				names = names + str + ", ";
			}
		}

		return names;
	}

	private static String getLeftPlayers(String scp) {
		String players = "";
		if (playersLeft.get(scp) != null && playersLeft.containsKey(scp)) {
			for (String str : playersLeft.get(scp)) {
				players = str + ", ";
			}
		}
		return players;
	}

	public static void terminateTestBreach(String playerEnded, String scp) {
		if (pauseTimes.containsKey(scp)) {
			terminateTestBreachNoPersonnel(playerEnded, scp);
		} else {
			for (String str : testMembers.get(scp)) {
				Player p = Bukkit.getPlayerExact(str);
				p.playSound(p.getLocation(), Sound.BLOCK_ANVIL_BREAK, 2.0F, 1.0F);
			}

			API.sendMessageToEveryone(
					"§8[§a§l!§8] §e§oTesting of §c§lSCP-" + scp + "§e§o has ended due to the tested SCP breach.");

			API.sendConsoleCommand("discordsrv broadcast #831086704218996776 **Test ended due to a breach:**");
			API.sendConsoleCommand("discordsrv broadcast #831086704218996776 SCP: **" + scp + "**");
			API.sendConsoleCommand("discordsrv broadcast #831086704218996776 Breach reported by: **" + playerEnded + "**");
			API.sendConsoleCommand(
					"discordsrv broadcast #831086704218996776 Researcher: **" + testMembers.get(scp).get(0) + "**");
			API.sendConsoleCommand("discordsrv broadcast #831086704218996776 Class-D(s): **" + getClassDs(scp) + "**");
			API.sendConsoleCommand(
					"discordsrv broadcast #831086704218996776 Security Officer(s): **" + getSecurityOfficers(scp) + "**");
			API.sendConsoleCommand(
					"discordsrv broadcast #831086704218996776 MTF Nu-7 Officers(s): **" + getMTFs(scp) + "**");
			API.sendConsoleCommand(
					"discordsrv broadcast #831086704218996776 Players, who left: **" + getLeftPlayers(scp) + "**");
			API.sendConsoleCommand(
					"discordsrv broadcast #831086704218996776 Players, who got kicked: **" + getKickedPlayers(scp) + "**");

			removeFromAttendingMembers(scp);
			SCPRP.tempList.remove(SCPRP.tempList.indexOf(testMembers.get(scp).get(0)));
			joinedClassDs.remove(scp);
			joinedSecOffs.remove(scp);
			joinedMTFs.remove(scp);
			if (begunTests.containsKey(scp))
				begunTests.remove(scp);
			if (SCPRP.ongoingTests.contains(scp)) {
				SCPRP.ongoingTests.remove(scp);
			} else {
				API.sendMessageToEveryone("§8[§4§lERROR§8] §4§lERROR CODE: §e§l0002§4§l - PLEASE, REPORT TO VOJTIISEK");
			}
			testMembers.remove(scp);
			if (kickedPlayers.containsKey(scp))
				kickedPlayers.remove(scp);
			if (playersLeft.containsKey(scp))
				playersLeft.remove(scp);
		}
	}

	public static void terminateTestNoPersonnel(String scp) {

		for (String str : testMembers.get(scp)) {
			Player p = Bukkit.getPlayerExact(str);
			p.playSound(p.getLocation(), Sound.ENTITY_ENDEREYE_DEATH, 2.0F, 1.0F);
		}

		API.sendMessageToEveryone(
				"§8[§a§l!§8] §e§oTesting of §c§lSCP-" + scp + "§e§o has ended due to not enough personnel.");

		API.sendConsoleCommand("discordsrv broadcast #831086704218996776 **Test ended due to not enough personnel:**");
		API.sendConsoleCommand("discordsrv broadcast #831086704218996776 SCP: **" + scp + "**");
		API.sendConsoleCommand(
				"discordsrv broadcast #831086704218996776 Researcher: **" + testMembers.get(scp).get(0) + "**");
		API.sendConsoleCommand("discordsrv broadcast #831086704218996776 Class-D(s): **" + getClassDs(scp) + "**");
		API.sendConsoleCommand(
				"discordsrv broadcast #831086704218996776 Security Officer(s): **" + getSecurityOfficers(scp) + "**");
		API.sendConsoleCommand("discordsrv broadcast #831086704218996776 MTF Nu-7 Officers(s): **" + getMTFs(scp) + "**");
		API.sendConsoleCommand(
				"discordsrv broadcast #831086704218996776 Players, who left: **" + getLeftPlayers(scp) + "**");
		API.sendConsoleCommand(
				"discordsrv broadcast #831086704218996776 Players, who got kicked: **" + getKickedPlayers(scp) + "**");

		if(pauseTimes.containsKey(scp)) unpauseStopwatch(scp);
		removeFromAttendingMembers(scp);
		if(SCPRP.tempList.indexOf(testMembers.get(scp).get(0)) != -1) SCPRP.tempList.remove(SCPRP.tempList.indexOf(testMembers.get(scp).get(0)));
		joinedClassDs.remove(scp);
		joinedSecOffs.remove(scp);
		joinedMTFs.remove(scp);
		if (begunTests.containsKey(scp))
			begunTests.remove(scp);
		if (SCPRP.ongoingTests.contains(scp)) {
			SCPRP.ongoingTests.remove(scp);
		} else {
			API.sendMessageToEveryone("§8[§4§lERROR§8] §4§lERROR CODE: §e§l0003§4§l - PLEASE, REPORT TO VOJTIISEK");
		}
		testMembers.remove(scp);
		if (kickedPlayers.containsKey(scp))
			kickedPlayers.remove(scp);
		if (playersLeft.containsKey(scp))
			playersLeft.remove(scp);
	}

	public static void terminateTestBreachNoPersonnel(String playerEnded, String scp) {
		if (pauseTimes.containsKey(scp)) {
			terminateTestBreachNoPersonnel(playerEnded, scp);
		} else {
			for (String str : testMembers.get(scp)) {
				Player p = Bukkit.getPlayerExact(str);
				p.playSound(p.getLocation(), Sound.BLOCK_ANVIL_BREAK, 2.0F, 1.0F);
			}

			API.sendMessageToEveryone(
					"§8[§a§l!§8] §e§oTesting of §c§lSCP-" + scp + "§e§o has ended due to the tested SCP breach.");

			API.sendConsoleCommand("discordsrv broadcast #831086704218996776 **Test ended due to a breach when paused:**");
			API.sendConsoleCommand("discordsrv broadcast #831086704218996776 SCP: **" + scp + "**");
			API.sendConsoleCommand("discordsrv broadcast #831086704218996776 Breach reported by: **" + playerEnded + "**");
			API.sendConsoleCommand(
					"discordsrv broadcast #831086704218996776 Researcher: **" + testMembers.get(scp).get(0) + "**");
			API.sendConsoleCommand("discordsrv broadcast #831086704218996776 Class-D(s): **" + getClassDs(scp) + "**");
			API.sendConsoleCommand(
					"discordsrv broadcast #831086704218996776 Security Officer(s): **" + getSecurityOfficers(scp) + "**");
			API.sendConsoleCommand(
					"discordsrv broadcast #831086704218996776 MTF Nu-7 Officers(s): **" + getMTFs(scp) + "**");
			API.sendConsoleCommand(
					"discordsrv broadcast #831086704218996776 Players, who left: **" + getLeftPlayers(scp) + "**");
			API.sendConsoleCommand(
					"discordsrv broadcast #831086704218996776 Players, who got kicked: **" + getKickedPlayers(scp) + "**");

			removeFromAttendingMembers(scp);
			SCPRP.tempList.remove(SCPRP.tempList.indexOf(testMembers.get(scp).get(0)));
			joinedClassDs.remove(scp);
			joinedSecOffs.remove(scp);
			joinedMTFs.remove(scp);
			if (begunTests.containsKey(scp))
				begunTests.remove(scp);
			if (SCPRP.ongoingTests.contains(scp)) {
				SCPRP.ongoingTests.remove(scp);
			} else {
				API.sendMessageToEveryone("§8[§4§lERROR§8] §4§lERROR CODE: §e§l0004§4§l - PLEASE, REPORT TO VOJTIISEK");
			}
			testMembers.remove(scp);
			if (kickedPlayers.containsKey(scp))
				kickedPlayers.remove(scp);
			if (playersLeft.containsKey(scp))
				playersLeft.remove(scp);
		}
	}

	public static void kickFromTest(String sender, String name, String reason) {
		String test = findTestByPlayer(name);
		if (api.getUserManager().getUser(name).getPrimaryGroup().equalsIgnoreCase("researcher")) {
			Bukkit.getPlayerExact(sender).sendMessage("§8[§c§l!§8] §c§oA Researcher can not be kicked out of a test.");
		} else {
			if (testMembers.get(test) != null) {
				if (testMembers.get(test).contains(name)) {
					attendingMembers.remove(name);
					if (kickedPlayers.containsKey(findTestByPlayer(name))) {
						kickedPlayers.get(findTestByPlayer(name)).add(name);
					} else {
						kickedPlayers.put(findTestByPlayer(name), new ArrayList<String>());
						kickedPlayers.get(findTestByPlayer(name)).add(name);
					}
					if (api.getUserManager().getUser(name).getPrimaryGroup().equalsIgnoreCase("security-officer")
							|| api.getUserManager().getUser(name).getPrimaryGroup()
									.equalsIgnoreCase("head-security-officer")) {
						joinedSecOffs.put(test, joinedSecOffs.get(test) - 1);
						testMembers.get(test).remove(name);
					} else if (api.getUserManager().getUser(name).getPrimaryGroup().equalsIgnoreCase("mtf-nu-7") || api
							.getUserManager().getUser(name).getPrimaryGroup().equalsIgnoreCase("mtf-nu7-commander")) {

						joinedMTFs.put(test, joinedMTFs.get(test) - 1);
						testMembers.get(test).remove(name);
					} else if (api.getUserManager().getUser(name).getPrimaryGroup().equalsIgnoreCase("class-d")) {

						joinedClassDs.put(test, joinedClassDs.get(test) - 1);
						testMembers.get(test).remove(name);
					}

					API.sendRequirementMessage2(test);

					API.sendConsoleCommand("discordsrv broadcast #819586816444727316 " + sender
							+ " - test kick - Target: - " + name + " - Reason:" + reason + " - SCP-" + test);
				} else {
					Bukkit.getPlayerExact(sender).sendMessage("§8[§a§l!§8] §a§oPlayer §c§l" + name
							+ " §a§ois not a part of any currently running SCP test.");
				}
			} else {
				Bukkit.getPlayerExact(sender).sendMessage(
						"§8[§a§l!§8] §a§oPlayer §c§l" + name + " §a§ois not a part of any currently running SCP test.");
			}
		}
	}

	@EventHandler
	public void onMemberLeave(PlayerQuitEvent e) {
		Player player = e.getPlayer();
		String name = player.getName();
		String test = findTestByPlayer(name);
		if (testMembers.get(findTestByPlayer(player.getName())) != null) {
			if (testHasBegun(test))
				pauseStopwatch(test);
			if (playersLeft.containsKey(test)) {
				playersLeft.get(test).add(player.getName());
			} else {
				playersLeft.put(test, new ArrayList<String>());
				playersLeft.get(test).add(player.getName());
			}
			attendingMembers.remove(name);
			if (api.getUserManager().getUser(player.getName()).getPrimaryGroup().equalsIgnoreCase("researcher")
					|| api.getUserManager().getUser(name).getPrimaryGroup().equalsIgnoreCase("head-researcher")) {
				terminateTestNoPersonnel(test);
			} else if (api.getUserManager().getUser(player.getName()).getPrimaryGroup()
					.equalsIgnoreCase("security-officer")
					|| api.getUserManager().getUser(name).getPrimaryGroup().equalsIgnoreCase("head-security-officer")) {

				joinedSecOffs.put(test, joinedSecOffs.get(test) - 1);
				testMembers.get(test).remove(name);
				API.sendRequirementMessage2(test);
			} else if (api.getUserManager().getUser(player.getName()).getPrimaryGroup().equalsIgnoreCase("class-d")) {
				joinedClassDs.put(test, joinedClassDs.get(test) - 1);
				testMembers.get(test).remove(name);
				API.sendRequirementMessage2(test);
			} else if (api.getUserManager().getUser(player.getName()).getPrimaryGroup().equalsIgnoreCase("mtf-nu-7")
					|| api.getUserManager().getUser(name).getPrimaryGroup().equalsIgnoreCase("mtf-nu7-commander")) {
				joinedMTFs.put(test, joinedMTFs.get(test) - 1);
				testMembers.get(test).remove(name);
				API.sendRequirementMessage2(test);
			}
		}
	}

	@EventHandler
	public void onMemberLeaveKick(PlayerKickEvent e) {
		Player player = e.getPlayer();
		String name = player.getName();
		String test = findTestByPlayer(name);
		if (testMembers.get(findTestByPlayer(player.getName())) != null) {
			if (testHasBegun(test))
				pauseStopwatch(test);
			if (playersLeft.containsKey(test)) {
				playersLeft.get(test).add(player.getName());
			} else {
				playersLeft.put(test, new ArrayList<String>());
				playersLeft.get(test).add(player.getName());
			}
			attendingMembers.remove(name);
			if (api.getUserManager().getUser(player.getName()).getPrimaryGroup().equalsIgnoreCase("researcher")
					|| api.getUserManager().getUser(name).getPrimaryGroup().equalsIgnoreCase("head-researcher")) {
				terminateTestNoPersonnel(test);
			} else if (api.getUserManager().getUser(player.getName()).getPrimaryGroup()
					.equalsIgnoreCase("security-officer")
					|| api.getUserManager().getUser(name).getPrimaryGroup().equalsIgnoreCase("head-security-officer")) {

				joinedSecOffs.put(test, joinedSecOffs.get(test) - 1);
				testMembers.get(test).remove(name);
				API.sendRequirementMessage2(test);
			} else if (api.getUserManager().getUser(player.getName()).getPrimaryGroup().equalsIgnoreCase("class-d")) {
				joinedClassDs.put(test, joinedClassDs.get(test) - 1);
				testMembers.get(test).remove(name);
				API.sendRequirementMessage2(test);
			} else if (api.getUserManager().getUser(player.getName()).getPrimaryGroup().equalsIgnoreCase("mtf-nu-7")
					|| api.getUserManager().getUser(name).getPrimaryGroup().equalsIgnoreCase("mtf-nu7-commander")) {
				joinedMTFs.put(test, joinedMTFs.get(test) - 1);
				testMembers.get(test).remove(name);
				API.sendRequirementMessage2(test);
			}
		}
	}

	public static void giveTestRewards(String scp) {
		if (testMembers.containsKey(scp)) {
			for (String str : testMembers.get(scp)) {
				LevellingSys.addXP(str, countTestXP(RPAntiCheat.CheckTime(scp)));
				sendPayout(str, scp, RPAntiCheat.CheckTime(scp));
			}
		}
	}

	public static int countTestXP(long checkedTime) {
		int xp = 0;
		Random random = new Random();
		int randomModifier = random.nextInt(30 - 5) + 5;
		xp = (int) checkedTime * randomModifier;
		return xp;
	}

	public static String findTestByPlayer(String name) {
		String scp = "";
		for (Entry<String, ArrayList<String>> entry : testMembers.entrySet()) {
			if (entry.getValue().contains(name)) {
				scp = entry.getKey();
			}
		}
		return scp;
	}

	public static void removeFromAttendingMembers(String scp) {
		for (String name : testMembers.get(scp)) {
			attendingMembers.remove(name);
		}
	}

	public static void removeTest(String SCP) {
		testMembers.remove(SCP);
	}

	public static HashMap<String, ArrayList<Integer>> getPersonnelReqDb() {
		return scps;
	}

	public static HashMap<String, Long> getTestCooldowns() {
		return testCooldowns;
	}

	public Long getPlayerCooldown(String name) {
		return getTestCooldowns().get(name);
	}

	public static void sendPayout(String name, String scp, long checkTime) {
		User user = api.getUserManager().getUser(name);
		double payout = 0.0;
		switch (findSCPClass(scp)) {
		case "safe":
			switch (user.getPrimaryGroup()) {
			case "head-researcher":
				payout = 200.0;
				break;
			case "researcher":
				payout = 100.0;
				break;
			case "security-officer":
				payout = 100.0;
				break;
			case "head-security-officer":
				payout = 200.0;
				break;
			case "mtf-nu-7":
				payout = 150.0;
				break;
			case "mtf-nu-7-commander":
				payout = 200.0;
				break;
			case "default":
				payout = 15.0;
				break;
			case "class-d":
				payout = 15.0;
				break;
			default:
				break;
			}
			break;
		case "euclid":
			switch (user.getPrimaryGroup()) {
			case "head-researcher":
				payout = 350.0;
				break;
			case "researcher":
				payout = 250.0;
				break;
			case "security-officer":
				payout = 250.0;
				break;
			case "head-security-officer":
				payout = 350.0;
				break;
			case "mtf-nu-7":
				payout = 300.0;
				break;
			case "mtf-nu-7-commander":
				payout = 400.0;
				break;
			case "default":
				payout = 30.0;
				break;
			case "class-d":
				payout = 30.0;
				break;
			default:
				break;
			}
			break;
		case "keter":
			switch (user.getPrimaryGroup()) {
			case "head-researcher":
				payout = 600.0;
				break;
			case "researcher":
				payout = 450.0;
				break;
			case "security-officer":
				payout = 450.0;
				break;
			case "head-security-officer":
				payout = 600.0;
				break;
			case "mtf-nu-7":
				payout = 500.0;
				break;
			case "mtf-nu-7-commander":
				payout = 700.0;
				break;
			case "default":
				payout = 50.0;
				break;
			case "class-d":
				payout = 50.0;
				break;
			default:
				break;
			}
			break;
		default:
			break;
		}
		
		try {
			Economy.add(name, payout);
		} catch (NoLoanPermittedException e) {
			Bukkit.getPlayerExact(name).sendMessage("§8[§4§lERROR§8] §4§lERROR CODE: §e§lpayout-NLPE§4§l - PLEASE, REPORT TO VOJTIISEK");
			e.printStackTrace();
		} catch (UserDoesNotExistException e) {
			Bukkit.getPlayerExact(name).sendMessage("§8[§4§lERROR§8] §4§lERROR CODE: §e§lpayout-UDNEE§4§l - PLEASE, REPORT TO VOJTIISEK");
			e.printStackTrace();
		}
		Bukkit.getPlayerExact(name).sendMessage(
				"§8[§2§lBANK§8] §a§oYou have received your payout from the Scientific Department. Value: §2§l" + payout
						+ "§a§o.");
	}

	private static String findSCPClass(String scp) {
		String scpClass = "error";
		if (safe.contains(scp))
			scpClass = "safe";
		if (euclid.contains(scp))
			scpClass = "euclid";
		if (keter.contains(scp))
			scpClass = "keter";
		return scpClass;
	}

	public static HashMap<String, String> getSCPPerms() {
		return scpPerms;
	}
}