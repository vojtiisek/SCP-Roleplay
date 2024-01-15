package cz.vojtiisek.scprpsystem;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import com.earth2me.essentials.Essentials;

import Testing.TestingSys;
import net.ess3.api.events.AfkStatusChangeEvent;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.model.user.User;

public class RPAntiCheat implements Listener {
	private static SCPRP main;
	public static Essentials ess = (Essentials) Bukkit.getServer().getPluginManager().getPlugin("Essentials");
	public static HashMap<String, Integer> scpMaxTimes = new HashMap<String, Integer>();

	public RPAntiCheat(SCPRP main) {
		this.main = main;
	}

	public static long CheckTime(String scp) {
		long checkedTime = 0;
		try {
			long time = TestingSys.stopStopwatchAndGetMsDifference(scp);
			if (time - 2 * 1000 * 60 > 0) {
				checkedTime = ((time / 1000) / 60) - 2;
				if (checkedTime > scpMaxTimes.get(scp))
					checkedTime = scpMaxTimes.get(scp);
			} else {
				checkedTime = 0;
			}

		} catch (NullPointerException e) {
			e.printStackTrace();
			API.sendConsoleCommand("discord broadcast #839057785362448424 [RPAC] checktime null");
			checkedTime = 0;
		}

		return checkedTime;
	}

	@EventHandler
	public void onTestMemberAFK(AfkStatusChangeEvent e) {
		Player player = Bukkit.getPlayerExact(e.getAffected().getName());
		if (TestingSys.attendingMembers.contains(player.getName())) {
			if (e.getValue() == true) {
				player.kickPlayer("[RP AntiCheat] You can not be AFK during a test.");
				API.sendConsoleCommand(
						"discord broadcast #832325137721458768 " + player.getName() + " was AFK while testing a SCP.");
			}
		}
	}

	public static void addScpsMaxTimes() {
		// lcz
		scpMaxTimes.put("1057", 20);
		scpMaxTimes.put("912", 20);
		scpMaxTimes.put("553", 30);
		scpMaxTimes.put("594", 20);
		scpMaxTimes.put("160", 25);
		scpMaxTimes.put("529", 15);
		scpMaxTimes.put("294", 20);
		scpMaxTimes.put("109", 15);
		scpMaxTimes.put("005", 15);

		// hcz
		scpMaxTimes.put("106", 40);
		scpMaxTimes.put("096", 40);
		scpMaxTimes.put("023", 30);
		scpMaxTimes.put("1048", 30);
		scpMaxTimes.put("682", 60);
		scpMaxTimes.put("939", 30);
		scpMaxTimes.put("1000", 30);
		scpMaxTimes.put("049", 30);
		scpMaxTimes.put("173", 40);

		System.out.println("RP AntiCheat - Per-SCP Maximum test times loaded successfully.");
	}

	public static void alert(String str) {
		switch (str.toLowerCase()) {
		case "the-administrator":
			String name = "error";
			for (User user : LuckPermsProvider.get().getUserManager().getLoadedUsers()) {
				if (user.getPrimaryGroup().equalsIgnoreCase("the-administrator")) {
					if (!user.getUsername().equalsIgnoreCase("Vojtiisek"))
						name = user.getUsername();
				}
			}
			
			if (name == "error") {
				API.sendConsoleCommand("discord broadcast #839057785362448424 [RPAC] Alert " + str + ": Player name error.");
				System.out.println("[RPAC] Alert " + str + ": Player name error.");
			} else {
				API.sendMessageToEveryone("§c[§4§lRPAC§c] §c§lPlayer §f§l" + name + " §c§lgot assigned the §4§lThe Administrator §c§lrole, but is not the true The Administrator. The only true one is §e§lVojtiisek§c§l. Contact him IMMEDIATELLY.");
				API.sendConsoleCommand(
						"discord broadcast #832325137721458768 ALERT! " + name + " HAS ASSIGNED THE THE ADMINISTRATOR ROLE. <@310332134000820224> <@388380225148420108>");
				for(Player p : Bukkit.getOnlinePlayers()) p.playSound(p.getLocation(), Sound.ENTITY_WITHER_HURT, 2.0F, 1.0F);
				
			}
			break;
		case "o5-1":
			name = "error";
			for (User user : LuckPermsProvider.get().getUserManager().getLoadedUsers()) {
				if (user.getPrimaryGroup().equalsIgnoreCase("o5-1")) {
					if (!user.getUsername().equalsIgnoreCase("pantrozy") && !user.getUsername().equalsIgnoreCase("Vojtiisek"))
						name = user.getUsername();
				}
			}
			
			if (name == "error") {
				API.sendConsoleCommand("discord broadcast #839057785362448424 [RPAC] Alert " + str + ": Player name error.");
				System.out.println("[RPAC] Alert " + str + ": Player name error.");
			} else {
				API.sendMessageToEveryone("§c[§4§lRPAC§c] §c§lPlayer §f§l" + name + " §c§lgot assigned the §4§lO5-1 §c§lrole, but is not the true The Administrator. The only true one is §e§lpantrozy§c§l. Contact him IMMEDIATELLY.");
				API.sendConsoleCommand(
						"discord broadcast #832325137721458768 ALERT! " + name + " HAS ASSIGNED THE O5-1 ROLE. <@310332134000820224> <@388380225148420108>");
				for(Player p : Bukkit.getOnlinePlayers()) p.playSound(p.getLocation(), Sound.ENTITY_WITHER_HURT, 2.0F, 1.0F);
				
			}
			break;
		case "o5-3":
			name = "error";
			for (User user : LuckPermsProvider.get().getUserManager().getLoadedUsers()) {
				if (user.getPrimaryGroup().equalsIgnoreCase("the-administrator")) {
					if (!user.getUsername().equalsIgnoreCase("_D1VE_")  && !user.getUsername().equalsIgnoreCase("Vojtiisek"))
						name = user.getUsername();
				}
			}
			
			if (name == "error") {
				API.sendConsoleCommand("discord broadcast #839057785362448424 [RPAC] Alert " + str + ": Player name error.");
				System.out.println("[RPAC] Alert " + str + ": Player name error.");
			} else {
				API.sendMessageToEveryone("§c[§4§lRPAC§c] §c§lPlayer §f§l" + name + " §c§lgot assigned the §4§lO5-3 §c§lrole, but is not the true The Administrator. The only true one is §e§l_D1VE_§c§l. Contact him IMMEDIATELLY.");
				API.sendConsoleCommand(
						"discord broadcast #832325137721458768 ALERT! " + name + " HAS ASSIGNED THE O5-3 ROLE. <@310332134000820224> <@388380225148420108>");
				for(Player p : Bukkit.getOnlinePlayers()) p.playSound(p.getLocation(), Sound.ENTITY_WITHER_HURT, 2.0F, 1.0F);
				
			}
			break;
		default:
			API.sendConsoleCommand("discord broadcast #839057785362448424 [RPAC] Alert " + str + " does not exist.");
			System.out.println("[RPAC] Alert " + str + " does not exist.");
			break;
		}
	}
}