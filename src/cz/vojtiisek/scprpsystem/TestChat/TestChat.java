package cz.vojtiisek.scprpsystem.TestChat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import Testing.TestingSys;
import cz.vojtiisek.scprpsystem.SCPRP;

public class TestChat implements Listener {
	public static SCPRP main;
	
	public static Map<String, List<String>> chatRooms = new HashMap<String, List<String>>(); //scp, playernames
	public static List<String> connectedPlayers = new ArrayList<String>(); //name

	private static String receiver;
	
	public TestChat(SCPRP main) {
		this.main = main;
	}

	public static void enableChat(String findTestByPlayer, String name) { //scp, playername
		if(getChatRooms().get(findTestByPlayer) != null) {
			if(!getChatRooms().get(findTestByPlayer).contains(name)) {
				getChatRooms().get(findTestByPlayer).add(name);
				getConnectedPlayers().add(name);
				Bukkit.getPlayerExact(name).sendMessage("§3[§f§lTC§3] §b§oTest Chat enabled. §9§oUse the command again in order to disable it.");
			}
		} else {
			List<String> list = new ArrayList<String>();
			list.add(name);
			getChatRooms().put(findTestByPlayer, list);
			Bukkit.getPlayerExact(name).sendMessage("§3[§f§lTC§3] §b§oTest Chat §a§oenabled§b§o. §9§oUse the command again in order to disable it.");
			System.out.println("[TestChat] Chat Room for the SCP-" + findTestByPlayer + " test created by " + name + ".");
		}
	}
	
	public static void disableChat(String findTestByPlayer, String name) { //scp, playername
		if(getChatRooms().get(findTestByPlayer) != null) {
			if(getChatRooms().get(findTestByPlayer).contains(name)) {
				getChatRooms().get(findTestByPlayer).remove(name);
				getConnectedPlayers().remove(name);
				Bukkit.getPlayerExact(name).sendMessage("§3[§f§lTC§3] §b§oTest Chat §c§odisabled§b§o.");
				if(getChatRooms().get(findTestByPlayer).size() == 0) {
					getChatRooms().remove(findTestByPlayer);
					System.out.println("[TestChat] Chat Room for the SCP-" + findTestByPlayer + " test abandoned. Removing it from the Map.");
				}
			} else {
				getChatRooms().remove(findTestByPlayer);
				System.out.println("[TestChat] Chat Room for the SCP-" + findTestByPlayer + " test abandoned. Removing it from the Map.");
			}
		} else {
			System.out.println("[TestChat] Chat Room for the SCP-" + findTestByPlayer + " is null.");
		}
	}
	
	public static void disableChatSilent(String findTestByPlayer, String name) { //scp, playername
		if(getChatRooms().get(findTestByPlayer) != null) {
			if(getChatRooms().get(findTestByPlayer).contains(name)) {
				getChatRooms().get(findTestByPlayer).remove(name);
				getConnectedPlayers().remove(name);
				if(getChatRooms().get(findTestByPlayer).size() == 0) {
					getChatRooms().remove(findTestByPlayer);
					System.out.println("[TestChat] Chat Room for the SCP-" + findTestByPlayer + " test abandoned. Removing it from the Map.");
				}
			} else {
				getChatRooms().remove(findTestByPlayer);
				System.out.println("[TestChat] Chat Room for the SCP-" + findTestByPlayer + " test abandoned. Removing it from the Map.");
			}
		}
	}
	
	@EventHandler
	public static void onChat(AsyncPlayerChatEvent e) {
		if(getChatRooms().size() > 0) {
			for(Player p : e.getRecipients()) {
				if(getConnectedPlayers().contains(p.getName())) {
					if(getConnectedPlayers().contains(e.getPlayer().getName())) {
						String testSender = TestingSys.findTestByPlayer(e.getPlayer().getName());
						String testReceiver = TestingSys.findTestByPlayer(p.getName());
						if(testSender == testReceiver) {
							p.sendMessage("§3[§9§l" + testSender + "§3]§r " + e.getMessage());
							e.getPlayer().sendMessage("§3[§9§l" + testSender + "§3]§r " + e.getMessage());
						} else {
							e.setCancelled(true);
						}
					} else {
						e.setCancelled(true);
						p.sendMessage("§7" + e.getMessage());
					}
				}
			}
			
			if(getConnectedPlayers().contains(e.getPlayer().getName())) {
				e.setCancelled(true);
				for(String reciever : getChatRooms().get(TestingSys.findTestByPlayer(e.getPlayer().getName()))) {
					if(Bukkit.getPlayerExact(reciever) != null && Bukkit.getPlayerExact(reciever).isOnline()) {
						Bukkit.getPlayerExact(receiver).sendMessage("§3[§9§l" + TestingSys.findTestByPlayer(e.getPlayer().getName()) + "§3]§r " + e.getMessage());
					} else {
						disableChatSilent(TestingSys.findTestByPlayer(e.getPlayer().getName()), reciever);
					}
				}
			}
		}
	}
	
	
	public static Map<String, List<String>> getChatRooms() {
		return chatRooms;
	}
	
	public static List<String> getConnectedPlayers() {
		return connectedPlayers;
	}
}