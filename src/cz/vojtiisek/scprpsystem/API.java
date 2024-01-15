package cz.vojtiisek.scprpsystem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import Levelling.LevellingSys;
import Testing.TestingSys;
import cz.vojtiisek.scprpsystem.CellSystem.Cell;
import cz.vojtiisek.scprpsystem.CellSystem.CellSystem;
import cz.vojtiisek.scprpsystem.Constructions.ConstructionsMenu;
import cz.vojtiisek.scprpsystem.Spawnpoints.SpawnSys;
import cz.vojtiisek.scprpsystem.files.NaufixTeamFile;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.cacheddata.CachedMetaData;
import net.luckperms.api.model.user.User;
import net.luckperms.api.node.Node;
import net.luckperms.api.node.NodeType;
import net.luckperms.api.node.types.PrefixNode;

public class API implements Listener {
	public SCPRP main;
	public static HashMap<UUID, Integer> arrestedPlayers = new HashMap<UUID, Integer>();

	// public static Map<String, List<String>> playerInformations = new
	// HashMap<String, List<String>>();
	public static Map<String, String> draggingMap = new HashMap<String, String>(); // secoff, dragged arrested target

	public static List<String> moderators = new ArrayList<String>();
	public static List<String> admins = new ArrayList<String>();
	public static List<String> vips = new ArrayList<String>();
	public static ArrayList<String> friskedPlayers;

	private int clickCount;

	static LuckPerms api = LuckPermsProvider.get();

	private boolean arrested;

	public API(SCPRP main) {
		this.main = main;
	}

	public static void arrest(Player player) {
		arrestedPlayers.put(player.getUniqueId(), 400);
	}

	@EventHandler
	public void onLeftClick(PlayerInteractEvent e) {
		Player player = e.getPlayer();
		if (isArrested(player) == true) {
			clickCount = arrestedPlayers.get(player.getUniqueId());
			e.setCancelled(true);
			if (!(e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK))) {
				clickCount = clickCount - 1;
				arrestedPlayers.remove(player.getUniqueId());
				arrestedPlayers.put(player.getUniqueId(), clickCount);
				player.sendTitle("§e§l§n" + clickCount + "§c§l clicks remaining", "§7§oto break free from the cuffs.",
						20, 80, 20);
			}
			if (clickCount == 0) {
				arrestedPlayers.remove(player.getUniqueId());
				player.sendMessage("§8[§a§l!§8] §a§oYou successfully broke free from the cuffs.");
			}
		}
	}

	@EventHandler
	public void onRightClick(PlayerInteractEvent e) {
		if (isArrested(e.getPlayer()) == true) {
			e.setCancelled(true);
		}
	}

	@EventHandler
	public void onInvClick(InventoryClickEvent e) {

		if (isArrested((Player) e.getWhoClicked()) == true) {
			e.setCancelled(true);
		}

		if (e.getAction() == InventoryAction.HOTBAR_MOVE_AND_READD || e.getAction() == InventoryAction.HOTBAR_SWAP) {
			e.setCancelled(true);
		}
	}

	@EventHandler
	public void onItemPickup(PlayerPickupItemEvent e) {
		if (isArrested(e.getPlayer()) == true) {
			e.setCancelled(true);
		}
	}

	@EventHandler
	public void onItemDrop(PlayerDropItemEvent e) {
		if (isArrested(e.getPlayer()) == true) {
			e.setCancelled(true);
		}
	}

	@EventHandler
	public void onPlayerHit(EntityDamageByEntityEvent e) {
		if (e.getDamager() instanceof Player) {
			if (isArrested((Player) e.getDamager()) == true) {
				e.setCancelled(true);
			}
		}
	}

	@EventHandler
	public void onPlayerRightClickPlayer(PlayerInteractEntityEvent e) {
		if (e.getRightClicked() instanceof Player) {
			if (e.getHand().equals(EquipmentSlot.HAND)) {
				Player dragging = e.getPlayer();
				Player target = (Player) e.getRightClicked();
				if (LuckPermsProvider.get().getUserManager().getUser(dragging.getName()).getPrimaryGroup()
						.equalsIgnoreCase("security-officer")
						|| LuckPermsProvider.get().getUserManager().getUser(dragging.getName()).getPrimaryGroup()
								.equalsIgnoreCase("head-security-officer")
						|| LuckPermsProvider.get().getUserManager().getUser(dragging.getName()).getPrimaryGroup()
								.equalsIgnoreCase("mtf-nu-7")
						|| LuckPermsProvider.get().getUserManager().getUser(dragging.getName()).getPrimaryGroup()
								.equalsIgnoreCase("mtf-nu-7-officer")
						|| dragging.hasPermission("scprp.mod") || dragging.hasPermission("scprp.admin")) {
					if (isArrested(target)) {
						if (getDraggingMap().containsValue(target.getName())) {
							if (getDraggingMap().get(dragging.getName()).equals(target.getName())) {
								getDraggingMap().remove(e.getPlayer().getName());
								target.setWalkSpeed(0.2f);
								dragging.sendMessage(
										"§8§l[§a§l!§8§l] §a§oYou have let §c§o" + target.getName() + " §a§ogo.");
								target.sendMessage("§8§l[§a§l!§8§l] §a§oYou have been let go.");
							}
						} else {
							getDraggingMap().put(e.getPlayer().getName(), target.getName());

							target.setWalkSpeed(0.1f);
							sendDraggedTitle(target);
							e.getPlayer()
									.sendMessage("§8§l[§a§l!§8§l] §c§oDragging §e§l" + target.getName() + "§c§o...");
						}
					}
				}
			}
		}
	}

	@EventHandler
	public void onPlayerMoveDragging(PlayerMoveEvent e) {
		for (Entry<String, String> entry : getDraggingMap().entrySet()) {
			if (entry.getKey().equalsIgnoreCase(e.getPlayer().getName())) {
				if (Bukkit.getPlayerExact(entry.getValue()) != null
						&& Bukkit.getPlayerExact(entry.getValue()).isOnline()) {
					Location loc = new Location(Bukkit.getPlayerExact(entry.getKey()).getLocation().getWorld(),
							Bukkit.getPlayerExact(entry.getKey()).getLocation().getX() + 0.75,
							Bukkit.getPlayerExact(entry.getKey()).getLocation().getY(),
							Bukkit.getPlayerExact(entry.getKey()).getLocation().getZ() - 0.75);
					Bukkit.getPlayerExact(entry.getValue()).teleport(loc);
				} else {
					getDraggingMap().remove(entry.getKey());
				}
			}
		}
	}

	@EventHandler
	public void onPlayerLeaveDragged(PlayerQuitEvent e) {
		for (Entry<String, String> entry : getDraggingMap().entrySet()) {
			if (entry.getKey().equalsIgnoreCase(e.getPlayer().getName())) {
				Bukkit.getPlayer(entry.getValue()).setWalkSpeed(0.2f);
				getDraggingMap().remove(entry.getKey());
			}
			if (entry.getValue().equalsIgnoreCase(e.getPlayer().getName())) {
				Bukkit.getPlayer(entry.getValue()).setWalkSpeed(0.2f);
				getDraggingMap().remove(entry.getKey());
			}
		}
	}

	@EventHandler
	public void onPlayerRespawnDragged(PlayerRespawnEvent e) {
		for (Entry<String, String> entry : getDraggingMap().entrySet()) {
			if (entry.getKey().equalsIgnoreCase(e.getPlayer().getName())) {
				Bukkit.getPlayer(entry.getValue()).setWalkSpeed(0.2f);
				getDraggingMap().remove(entry.getKey());
			}
			if (entry.getValue().equalsIgnoreCase(e.getPlayer().getName())) {
				Bukkit.getPlayer(entry.getValue()).setWalkSpeed(0.2f);
				getDraggingMap().remove(entry.getKey());
			}
		}
	}

	@EventHandler
	public void onPlayerDeathDragged(PlayerDeathEvent e) {
		if (e.getEntity() instanceof Player) {
			Player p = (Player) e.getEntity();
			for (Entry<String, String> entry : getDraggingMap().entrySet()) {
				if (entry.getKey().equalsIgnoreCase(p.getName())) {
					Bukkit.getPlayer(entry.getValue()).setWalkSpeed(0.2f);
					getDraggingMap().remove(entry.getKey());
				}
				if (entry.getValue().equalsIgnoreCase(p.getName())) {
					Bukkit.getPlayer(entry.getValue()).setWalkSpeed(0.2f);
					getDraggingMap().remove(entry.getKey());
				}
			}
		}
	}

	public static Map<String, String> getDraggingMap() {
		return draggingMap;
	}

	public void sendDraggedTitle(Player target) {
		for (Entry<String, String> entry : getDraggingMap().entrySet()) {
			if (entry.getValue().equalsIgnoreCase(target.getName())) {
				target.sendTitle("§c§lDRAGGED", "§7§oby §e§o" + entry.getKey(), 10, 20, 10);
				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(main, () -> sendDraggedTitle(target), 50);
			}
		}
	}

	public boolean isArrested(Player p) {
		if (arrestedPlayers.containsKey(p.getUniqueId())) {
			arrested = true;
		} else {
			arrested = false;
		}
		return arrested;
	}

	public static void sendMessageToEveryone(String msg) {
		for (Player p : Bukkit.getOnlinePlayers()) {
			p.sendMessage(msg);
		}
	}

	public static void sendMessageToEveryone(Player player, String msg) {
		for (Player p : Bukkit.getOnlinePlayers()) {
			p.sendMessage(msg);
		}
	}

	public static void sendMessageToEveryone(Player player, Player target, String msg) {
		for (Player p : Bukkit.getOnlinePlayers()) {
			p.sendMessage(msg);
		}
	}

	public static void sendConsoleCommand(String cmd) {
		ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
		Bukkit.dispatchCommand(console, cmd);
	}

	public static void sendRequirementMessage(Player player, String scp) {

		for (Player p : Bukkit.getServer().getOnlinePlayers()) {
			p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 2.0F, 1.0F);
		}

		sendMessageToEveryone("§8[§a§l!§8] §a§oPersonnel required for testing §c§lSCP-" + scp + "§a§o:");
		sendMessageToEveryone("§8§l - §4§l" + TestingSys.getPersonnelReqDb().get(scp).get(0) + " §6§oClass-D");
		sendMessageToEveryone(
				"§8§l - §4§l" + TestingSys.getPersonnelReqDb().get(scp).get(1) + " §7§oSecurity Officers §4§l");
		sendMessageToEveryone(
				"§8§l - §4§l" + TestingSys.getPersonnelReqDb().get(scp).get(2) + " §9§lMTF Nu-7 Officers");
		sendMessageToEveryone("§a§oResearcher: §2§l" + player.getName());
		sendMessageToEveryone("§e§oUse §c§l/test join §n" + scp + "§e§o to join the testing.");
	}

	public static void sendRequirementMessage2(String scpDesignation) {

		for (Player p : Bukkit.getServer().getOnlinePlayers()) {
			p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 2.0F, 1.0F);
		}

		int classds = TestingSys.getPersonnelReqDb().get(scpDesignation).get(0);
		int secOffs = TestingSys.getPersonnelReqDb().get(scpDesignation).get(1);
		int mtf = TestingSys.getPersonnelReqDb().get(scpDesignation).get(2);

		int jClassds = TestingSys.joinedClassDs.get(scpDesignation);
		int jSecOffs = TestingSys.joinedSecOffs.get(scpDesignation);
		int jMtf = TestingSys.joinedMTFs.get(scpDesignation);

		sendMessageToEveryone("§8[§a§l!§8] §a§oPersonnel required for testing §c§lSCP-" + scpDesignation + "§a§o:");
		sendMessageToEveryone("§8§l - §4§l" + Integer.toString(classds - jClassds) + " §6§oClass-D");
		sendMessageToEveryone("§8§l - §4§l" + Integer.toString(secOffs - jSecOffs) + " §7§oSecurity Officers §4§l");
		sendMessageToEveryone("§8§l - §4§l" + Integer.toString(mtf - jMtf) + " §9§lMTF Nu-7 Officers");
		sendMessageToEveryone("§e§oUse §c§l/test join §n" + scpDesignation + "§e§o to join the testing.");
	}

	public static String formatTime(Long long1) {
		String min = "";
		String sec = "";
		int minutes = (int) (long1 / 60000);
		int seconds = (int) (long1 - minutes * 60000) / 1000;

		if (minutes >= 10) {
			min = Integer.toString(minutes);
		} else {
			min = "0" + Integer.toString(minutes);
		}

		if (seconds >= 10) {
			sec = Integer.toString(seconds);
		} else {
			sec = "0" + Integer.toString(seconds);
		}

		String time = min + ":" + sec;
		return time;
	}

	public static void setPrefix(String name, int priority, String string, boolean bool) {
		removeAllPrefixes(name);
		String prefix = string;
		if (bool) {
			loadNaufixTeamFile();
			if (isNaufixMember(name)) {
				prefix = getNaufixPrefix(name) + string;
			}
		}

		PrefixNode node = PrefixNode.builder(prefix, 10).build();
		api.getUserManager().getUser(name).data().add(node);
		api.getUserManager().saveUser(api.getUserManager().getUser(name));
	}

	public static String getNaufixPrefix(String name) {
		String prefix = "";
		if (getAdmins().contains(name))
			prefix = "§8[§c§lA§8]";
		if (getModerators().contains(name))
			prefix = "§8[§b§lM§8]";
		if (getVIPs().contains(name))
			prefix = "§8[§e§lV§6§lI§e§lP§8]";
		if (prefix.isEmpty() || prefix.length() <= 0 || prefix.equals("")) {
			API.sendMessageToEveryone("§8[§4§lERROR§8] §4§lERROR CODE: §e§lAPI-gNP§4§l - PLEASE, REPORT TO VOJTIISEK");
			API.sendConsoleCommand("discordsrv broadcast #839057785362448424 [SCPRP] getNaufixPrefix prefix err");
		}
		return prefix;
	}

	public static boolean isNaufixMember(String name) {
		boolean bool = false;
		if (getAdmins().contains(name))
			bool = true;
		if (getModerators().contains(name))
			bool = true;
		if (getVIPs().contains(name))
			bool = true;
		return bool;
	}

	public void removePrefix(String playerName) {
		PrefixNode node = PrefixNode.builder(toStringDesig(ClassDDesignations.getPlayerDesignation(playerName)), 10)
				.build();
		api.getUserManager().getUser(playerName).data().remove(node);
		api.getUserManager().saveUser(api.getUserManager().getUser(playerName));
	}

	public static void removeAllPrefixes(String playerName) {
		for (Node node : api.getUserManager().getUser(playerName).getNodes()) {
			if (node.getType().equals(NodeType.PREFIX)) {
				api.getUserManager().getUser(playerName).data().remove(node);
			}
		}

		for (Node node : api.getUserManager().getUser(playerName).getNodes()) {
			if (node.getKey().toString().indexOf("prefix.") != -1) {
				api.getUserManager().getUser(playerName).data().remove(node);
			}
		}
		api.getUserManager().saveUser(api.getUserManager().getUser(playerName));
	}

	public static void addPermission(String user, String string) {
		Node node = Node.builder(string).build();
		api.getUserManager().getUser(user).data().add(node);
		api.getUserManager().saveUser(api.getUserManager().getUser(user));
	}

	public static void addPermission(String user, String string, boolean bool) {
		Node node = Node.builder(string).value(bool).build();
		api.getUserManager().getUser(user).data().add(node);
		api.getUserManager().saveUser(api.getUserManager().getUser(user));
	}

	public static boolean isInteger(String string) {
		try {
			Integer.parseInt(string);
		} catch (NumberFormatException e) {
			return false;
		} catch (NullPointerException e) {
			return false;
		}
		return true;
	}

	public static int parse(String string) {
		int parsed = Integer.parseInt(string);
		if (parsed < 0) {
			parsed = parsed * -1;
		}
		return parsed;
	}

	public static String toStringDesig(int number) {
		String str = Integer.toString(number);
		if (number < 10) {
			str = "0" + str;
		}

		if (number < 100) {
			str = "0" + str;
		}
		if (number < 1000) {
			str = "0" + str;
		}
		return str;
	}

	public static void removeDesignation(Player player, String string) {
		removeAllPrefixes(string);
		API.sendConsoleCommand("discordsrv broadcast #819586816444727316 " + player.getName()
				+ " - designation remove - Target: " + string);
		player.sendMessage("§8[§a§l!§8] §a§oSuccessfully removed §6§o" + string + "§a§o's Class-D designation prefix.");
	}

	public static void sendDebugMsg(String string) {
		if (Bukkit.getPlayerExact("Vojtiisek") != null && Bukkit.getPlayerExact("Vojtiisek").isOnline())
			Bukkit.getPlayerExact("Vojtiisek").sendMessage(string);

	}

	public static String getOnlineResearchers() {
		String name = "errorEmptyListNoOnline";
		for (Player player : Bukkit.getOnlinePlayers()) {
			User user = api.getUserManager().getUser(player.getName());
			if (user.getPrimaryGroup().equalsIgnoreCase("researcher")) {
				name = player.getName();
			}
		}
		return name;
	}

	public static String getOnlineSecurityOfficers() {
		String name = "errorEmptyListNoOnline";
		for (Player player : Bukkit.getOnlinePlayers()) {
			User user = api.getUserManager().getUser(player.getName());
			if (user.getPrimaryGroup().equalsIgnoreCase("security-officer")) {
				name = player.getName();
			}
		}
		return name;
	}

	public static String getOnlineMTFNu7s() {
		String name = "errorEmptyListNoOnline";
		for (Player player : Bukkit.getOnlinePlayers()) {
			User user = api.getUserManager().getUser(player.getName());
			if (user.getPrimaryGroup().equalsIgnoreCase("mtf-nu7")) {
				name = player.getName();
			}
		}
		return name;
	}

	public static String getOnlineMedics() {
		String name = "errorEmptyListNoOnline";
		for (Player player : Bukkit.getOnlinePlayers()) {
			User user = api.getUserManager().getUser(player.getName());
			if (user.getPrimaryGroup().equalsIgnoreCase("medic")
					|| user.getPrimaryGroup().equalsIgnoreCase("head-medic")) {
				name = player.getName();
			}
		}
		return name;
	}

	public static void sendTitle(Player playerExact, String title, String subline) {
		playerExact.sendTitle(title, subline, 10, 80, 20);
	}

	public static void sendProgressBar(Player p, int amount, int speed, String remainingColor, String completedColor,
			char c) {
		String uncolored = new String(new char[amount]).replace('\0', c);
		String startBar = remainingColor + uncolored;
		p.sendTitle(startBar, "§d§oReviving...", 20, speed, 0);
		for (int i = 0; i < amount; i++) {
			String doneBar = new String(new char[i + 1]).replace('\0', c);
			String remainingBar = new String(new char[amount - (i + 1)]).replace('\0', c);
			String progressBar = completedColor + doneBar + remainingColor + remainingBar;
			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(SCPRP.getInstace(),
					() -> p.sendTitle(progressBar, "§d§oReviving...", 0, speed, 0), speed);
		}
	}

	public static void teleportToSpawnPoint(Player playerExact) {
		String name = playerExact.getName();
		User user = LuckPermsProvider.get().getUserManager().getUser(name);
		String role = user.getPrimaryGroup();
		if (CellSystem.getJailedPlayers().indexOf(playerExact.getName()) != -1) {
			CellSystem.teleportToCell(name, "api1");
			System.out.println("[SCPRP SpawnSys] Player " + name
					+ " has been teleported to their spawnpoint, which is a jail cell. Their role: " + role + ".");
		} else {
			if (role.equalsIgnoreCase("class-d") || role.equalsIgnoreCase("default")) {
				CellSystem.teleportToCell(name, "api2");
				System.out.println(
						"[SCPRP SpawnSys] Class-D " + name + " has been teleported to their cell. Designation: "
								+ ClassDDesignations.getPlayerDesignation(name) + ", Cell #ID: "
								+ API.findCellIdByPname(name) + ".");
			} else {
				if (SpawnSys.getSpawns().containsKey(role)) {
					playerExact.teleport(SpawnSys.getSpawns().get(role));
					System.out.println("[SCPRP SpawnSys] Player " + name
							+ " has been teleported to their spawnpoint. Their role: " + role + ".");
				} else {
					System.out.println("[SCPRP SpawnSys] Could not find any spawnpoint for player " + name
							+ ". Their role: " + role + ".");
				}
			}
		}
	}

	public static void sendWarning(String title, String subline, String role) {
		for (Player player : Bukkit.getOnlinePlayers()) {
			User user = api.getUserManager().getUser(player.getName());
			if (user.getPrimaryGroup().equalsIgnoreCase(role)) {
				player.sendTitle(title, subline, 10, 80, 20);
				sendWarningSound(player);
			}
		}
	}

	public static void sendWarningSound(Player target) {
		target.playSound(target.getLocation(), Sound.BLOCK_NOTE_BASS, 2.0F, 1.0F);
	}

	public static void removeResearcherPermissions(String string) {
		User user = api.getUserManager().getUser(string);
		Node res1 = Node.builder("scprp.researcher1").build();
		Node res2 = Node.builder("scprp.researcher2").build();
		Node res3 = Node.builder("scprp.researcher3").build();
		Node res4 = Node.builder("scprp.researcher4").build();
		Node res5 = Node.builder("scprp.researcher5").build();
		Node res6 = Node.builder("dr.researcher1").build();
		Node res7 = Node.builder("dr.researcher2").build();
		Node res8 = Node.builder("dr.researcher3").build();
		Node res9 = Node.builder("dr.researcher4").build();
		Node res10 = Node.builder("scprp.scps1").build();
		Node res11 = Node.builder("scprp.scps2").build();
		Node res12 = Node.builder("scprp.scps3").build();
		Node res13 = Node.builder("scprp.scps4").build();
		Node res14 = Node.builder("scprp.scps5").build();
		for (Node node : user.getNodes()) {
			if (node.equals(res1))
				user.data().remove(node);
			if (node.equals(res2))
				user.data().remove(node);
			if (node.equals(res3))
				user.data().remove(node);
			if (node.equals(res4))
				user.data().remove(node);
			if (node.equals(res5))
				user.data().remove(node);
			if (node.equals(res6))
				user.data().remove(node);
			if (node.equals(res7))
				user.data().remove(node);
			if (node.equals(res8))
				user.data().remove(node);
			if (node.equals(res9))
				user.data().remove(node);
			if (node.equals(res10))
				user.data().remove(node);
			if (node.equals(res11))
				user.data().remove(node);
			if (node.equals(res12))
				user.data().remove(node);
			if (node.equals(res13))
				user.data().remove(node);
			if (node.equals(res14))
				user.data().remove(node);
		}
		api.getUserManager().saveUser(api.getUserManager().getUser(string));
	}

	public static void removeSecOffPermissions(String str) {
		User user = api.getUserManager().getUser(str);
		Node res1 = Node.builder("scprp.secoff1").build();
		Node res2 = Node.builder("scprp.secoff2").build();
		Node res3 = Node.builder("scprp.secoff3").build();
		Node res4 = Node.builder("scprp.secoff4").build();
		Node res5 = Node.builder("scprp.secoff5").build();
		Node res6 = Node.builder("dr.secoff1").build();
		Node res7 = Node.builder("dr.secoff2").build();
		Node res8 = Node.builder("dr.secoff3").build();
		Node res9 = Node.builder("dr.secoff4").build();
		for (Node node : user.getNodes()) {
			if (node.equals(res1))
				user.data().remove(node);
			if (node.equals(res2))
				user.data().remove(node);
			if (node.equals(res3))
				user.data().remove(node);
			if (node.equals(res4))
				user.data().remove(node);
			if (node.equals(res5))
				user.data().remove(node);
			if (node.equals(res6))
				user.data().remove(node);
			if (node.equals(res7))
				user.data().remove(node);
			if (node.equals(res8))
				user.data().remove(node);
			if (node.equals(res9))
				user.data().remove(node);
		}
		api.getUserManager().saveUser(api.getUserManager().getUser(str));
	}

	public static void removeTechnicPermissions(String str) {
		User user = api.getUserManager().getUser(str);
		Node res1 = Node.builder("scprp.technic1").build();
		Node res2 = Node.builder("scprp.technic2").build();
		Node res3 = Node.builder("scprp.technic3").build();
		Node res4 = Node.builder("scprp.technic4").build();
		Node res5 = Node.builder("scprp.technic5").build();
		Node res6 = Node.builder("dr.technic1").build();
		Node res7 = Node.builder("dr.technic2").build();
		Node res8 = Node.builder("dr.technic3").build();
		Node res9 = Node.builder("dr.technic4").build();
		for (Node node : user.getNodes()) {
			if (node.equals(res1))
				user.data().remove(node);
			if (node.equals(res2))
				user.data().remove(node);
			if (node.equals(res3))
				user.data().remove(node);
			if (node.equals(res4))
				user.data().remove(node);
			if (node.equals(res5))
				user.data().remove(node);
			if (node.equals(res6))
				user.data().remove(node);
			if (node.equals(res7))
				user.data().remove(node);
			if (node.equals(res8))
				user.data().remove(node);
			if (node.equals(res9))
				user.data().remove(node);
		}
		api.getUserManager().saveUser(api.getUserManager().getUser(str));
	}

	public static void removeMTFPermissions(String str) {
		User user = api.getUserManager().getUser(str);
		Node res1 = Node.builder("scprp.mtf1").build();
		Node res2 = Node.builder("scprp.mtf2").build();
		Node res3 = Node.builder("scprp.mtf3").build();
		Node res4 = Node.builder("scprp.mtf4").build();
		Node res5 = Node.builder("scprp.mtf5").build();
		Node res6 = Node.builder("dr.mtf1").build();
		Node res7 = Node.builder("dr.mtf2").build();
		Node res8 = Node.builder("dr.mtf3").build();
		Node res9 = Node.builder("dr.mtf4").build();
		for (Node node : user.getNodes()) {
			if (node.equals(res1))
				user.data().remove(node);
			if (node.equals(res2))
				user.data().remove(node);
			if (node.equals(res3))
				user.data().remove(node);
			if (node.equals(res4))
				user.data().remove(node);
			if (node.equals(res5))
				user.data().remove(node);
			if (node.equals(res6))
				user.data().remove(node);
			if (node.equals(res7))
				user.data().remove(node);
			if (node.equals(res8))
				user.data().remove(node);
			if (node.equals(res9))
				user.data().remove(node);
		}
		api.getUserManager().saveUser(api.getUserManager().getUser(str));
	}

	public static void removeAllPermissions(String str) {
		User user = api.getUserManager().getUser(str);

		removeMTFPermissions(str);
		removeTechnicPermissions(str);
		removeSecOffPermissions(str);
		removeResearcherPermissions(str);

		Node res1 = Node.builder("scprp.judge").build();
		for (Node node : user.getNodes()) {
			if (node.equals(res1))
				user.data().remove(node);
		}

		api.getUserManager().saveUser(api.getUserManager().getUser(str));
	}

	public static String convertUsernameToName(String username) {
		String name = "emptyemptyemptyemptyemptyemptynullerror";
		for (Player player : Bukkit.getServer().getOnlinePlayers()) {
			if (player.getName().toLowerCase().equalsIgnoreCase(username)) {
				name = player.getName();
			}
		}
		return name;
	}

	public static String getRandomColorBoldString() {
		String[] colors = { "§a§l", "§b§l", "§c§l", "§d§l", "§e§l", "§f§l", "§0§l", "§1§l", "§2§l", "§3§l", "§4§l",
				"§5§l", "§6§l", "§7§l", "§8§l", "§9§l" };
		Random rand = new Random();
		String color = colors[rand.nextInt(15)];
		return color;
	}

	public static List<String> findConstructionByName(String constName) {
		List<String> foundList = new ArrayList<String>();
		for (Entry<String, List<List<String>>> entry : ConstructionsMenu.getConstructions().entrySet()) {
			for (int i = 0; i < entry.getValue().size(); i++) {
				if (entry.getValue().get(i).contains(constName))
					foundList = entry.getValue().get(i);
			}
		}
		if (foundList.isEmpty())
			API.sendConsoleCommand("discordsrv broadcast #839057785362448424 [SCPRP] findConstructionByName empty");
		return foundList;
	}

	public static String findConstructionCreatorByList(List<String> list) {
		String creator = "notFoundErrorErrorErrorError";
		for (Entry<String, List<List<String>>> entry : ConstructionsMenu.getConstructions().entrySet()) {
			if (entry.getValue().contains(list))
				creator = entry.getKey();
		}
		if (creator == "notFoundErrorErrorErrorError")
			API.sendConsoleCommand(
					"discordsrv broadcast #839057785362448424 [SCPRP] findConstructionCreatorByList notFoundError");
		return creator;
	}

	public static String findConstructionNameByMember(String str) {
		String s = "emptyemptyemptyemptyemptyerr";
		for (Entry<String, List<String>> entry : ConstructionsMenu.getTechnics().entrySet()) {
			if (entry.getValue().contains(str)) {
				s = entry.getKey();
			}
		}
		return s;
	}

	public static void removeAllRewardDis(String str) {
		User user = api.getUserManager().getUser(str);
		Node res1 = Node.builder("dr.researcher1").value(false).build();
		Node res2 = Node.builder("dr.secoff1").value(false).build();
		Node res3 = Node.builder("dr.mtf1").value(false).build();
		Node res4 = Node.builder("dr.technic1").value(false).build();
		for (Node node : user.getNodes()) {
			if (node.equals(res1))
				user.data().remove(node);
			if (node.equals(res2))
				user.data().remove(node);
			if (node.equals(res3))
				user.data().remove(node);
			if (node.equals(res4))
				user.data().remove(node);
		}
		api.getUserManager().saveUser(api.getUserManager().getUser(str));
	}

	public static String findCellLevelById(String id) {
		String str = "error";
		if (CellSystem.getMinimumCells().containsKey(id))
			str = "minimum";

		if (CellSystem.getMediumCells().containsKey(id))
			str = "medium";

		if (CellSystem.getMaximumCells().containsKey(id))
			str = "maximum";

		if (CellSystem.getJailCells().containsKey(id))
			str = "jail";

		return str;
	}

	public static String findCellIdByPname(String name) {
		String str = "error";
		for (Entry<String, Cell> entry : CellSystem.getMinimumCells().entrySet()) {
			if (CellSystem.getMinimumCells().get(entry.getKey()).getOwner().equalsIgnoreCase(name)) {
				str = entry.getKey();
			}
		}

		for (Entry<String, Cell> entry : CellSystem.getMediumCells().entrySet()) {
			if (CellSystem.getMediumCells().get(entry.getKey()).getOwner().equalsIgnoreCase(name)) {
				str = entry.getKey();
			}
		}

		for (Entry<String, Cell> entry : CellSystem.getMaximumCells().entrySet()) {
			if (CellSystem.getMaximumCells().get(entry.getKey()).getOwner().equalsIgnoreCase(name)) {
				str = entry.getKey();
			}
		}

		for (Entry<String, Cell> entry : CellSystem.getJailCells().entrySet()) {
			if (CellSystem.getJailCells().get(entry.getKey()).getOwner().equalsIgnoreCase(name)) {
				str = entry.getKey();
			}
		}

		return str;
	}

	public static void setNaufixRole(String target, String naufixrole, Player sender) {
		String prefix = "emptyError";

		loadNaufixTeamFile();
		removeNaufixRoleSystem(target);

		for (PrefixNode pN : api.getUserManager().getUser(target).getNodes(NodeType.PREFIX)) {
			prefix = pN.getKey().toString().substring(10);
		}

		if (prefix.equals("emptyError")) {
			if (Bukkit.getPlayerExact(target) != null && Bukkit.getPlayerExact(target).isOnline()) {
				CachedMetaData metaData = api.getPlayerAdapter(Player.class).getMetaData(Bukkit.getPlayerExact(target));
				prefix = metaData.getPrefix();
			}
		}

		switch (naufixrole) {
		case "mod":
			if (!getModerators().contains(target)) {
				if (getAdmins().contains(target))
					getAdmins().remove(target);
				if (getVIPs().contains(target))
					getVIPs().remove(target);

				addModPermissions(target);
				setPrefix(target, 0, "§8[§b§lM§8]" + prefix, false);

				if (!getModerators().contains(target))
					getModerators().add(target);
				saveNaufixTeamFile();

				sender.sendMessage("§8[§f§lNAUFIX§8] §a§oAdded §e§l" + target + "§a§o as a Moderator.");
				if (Bukkit.getPlayerExact(target) != null && Bukkit.getPlayerExact(target).isOnline())
					Bukkit.getPlayerExact(target).sendMessage(
							"§8[§f§lNAUFIX§8] §b§oYou have been added to the Naufix Team as a Moderator. Welcome.");
			} else {
				sender.sendMessage("§8[§c§lNAUFIX§8] §e§l" + target + "§a§o already is a Moderator.");
			}
			break;
		case "adm":
			if (!getAdmins().contains(target)) {
				if (getModerators().contains(target))
					getModerators().remove(target);
				if (getVIPs().contains(target))
					getVIPs().remove(target);

				addAdminPermissions(target);
				setPrefix(target, 0, "§8[§c§lA§8]" + prefix, false);

				if (!getAdmins().contains(target))
					getAdmins().add(target);
				saveNaufixTeamFile();

				sender.sendMessage("§8[§f§lNAUFIX§8] §a§oAdded §e§l" + target + "§a§o as an Admin.");
				if (Bukkit.getPlayerExact(target) != null && Bukkit.getPlayerExact(target).isOnline())
					Bukkit.getPlayerExact(target).sendMessage(
							"§8[§f§lNAUFIX§8] §b§oYou have been added to the Naufix Team as an Admin. Welcome.");
			} else {
				sender.sendMessage("§8[§c§lNAUFIX§8] §e§l" + target + "§a§o already is an Admin.");
			}
			break;
		case "vip":
			break;
		default:
			sendConsoleCommand("discordsrv broadcast #839057785362448424 [SCPRP] setNaufixRole error");
			break;
		}
	}

	private static void addAdminPermissions(String target) {
		addPermission(target, "scprp.admin");
		addPermission(target, "scp.admin");
		addPermission(target, "essentials.*");
		addPermission(target, "scp.*");
		addPermission(target, "scprp.*");
		addPermission(target, "residence.*");
		Bukkit.getPlayerExact(target).setOp(true);
		addPermission(target, "discordsrv.admin", false);
	}

	public static void removeNaufixRoleSystem(String string) {
		if (getAdmins().contains(string))
			getAdmins().remove(string);
		if (getModerators().contains(string))
			getModerators().remove(string);
		if (getVIPs().contains(string))
			getVIPs().remove(string);

		saveNaufixTeamFile();

		removeAllPrefixes(string);
		removeNaufixPermissions(string);

		User convTarget = api.getUserManager().getUser(string);

		if (LevellingSys.roleHasLevel(string)) {
			String role = convTarget.getPrimaryGroup().toString();

			if (LevellingSys.getXP(string) != 0) {
				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(SCPRP.getInstace(),
						() -> LevellingSys.levelUp(string, role), 20);
			}
		} else {
			if (convTarget.getPrimaryGroup().equals("class-d") || convTarget.getPrimaryGroup().equals("default"))
				ClassDDesignations.rollbackDesignation(string);
		}

		API.sendConsoleCommand("discordsrv broadcast #843774743386521620 [SCPRP LpListener] System removed " + string
				+ " from the Naufix Team.");
	}

	public static List<String> getAdmins() {
		return admins;
	}

	public static List<String> getModerators() {
		return moderators;
	}

	public static List<String> getVIPs() {
		return vips;
	}

	public static void addModPermissions(String target) {
		addPermission(target, "scprp.mod");
		addPermission(target, "scp.mod");
		addPermission(target, "essentials.*");
		addPermission(target, "worldedit.*");
		addPermission(target, "coreprotect.inspect");
		addPermission(target, "essentials.balancetop", false);
		addPermission(target, "essentials.ban.exempt", false);
		addPermission(target, "essentials.beezooka", false);
		addPermission(target, "essentials.bigtree", false);
		addPermission(target, "essentials.break", false);
		addPermission(target, "essentials.fireball", false);
		addPermission(target, "essentials.gamemode.others", false);
		addPermission(target, "essentials.kick.exempt", false);
		addPermission(target, "essentials.kill.exempt", false);
		addPermission(target, "essentials.kittycannon", false);
		addPermission(target, "essentials.nuke", false);
		addPermission(target, "essentials.nick", false);
		addPermission(target, "essentials.nickname", false);
		addPermission(target, "essentials.powertool", false);
		addPermission(target, "essentials.powertooltoggle", false);
		addPermission(target, "techguns.allowunsafemode", false);
	}

	public static void removeNaufixPermissions(String target) {
		User user = api.getUserManager().getUser(target);
		Node scopedNode1 = Node.builder("scprp.mod").build();
		Node scopedNode2 = Node.builder("scprp.admin").build();
		Node scopedNode3 = Node.builder("*").build();
		addPermission(target, "essentials.*");
		addPermission(target, "scp.*");
		addPermission(target, "scprp.*");
		addPermission(target, "residence.*");

		Node scopedNode4 = Node.builder("scp.admin").build();
		Node scopedNode5 = Node.builder("scp.mod").build();
		Node scopedNode6 = Node.builder("essentials.*").build();
		Node scopedNode7 = Node.builder("worldedit.*").build();
		Node scopedNode8 = Node.builder("coreprotect.inspect").build();
		Node scopedNode9 = Node.builder("essentials.balancetop").build();
		Node scopedNode10 = Node.builder("essentials.ban.exempt").build();
		Node scopedNode11 = Node.builder("essentials.beezooka").build();
		Node scopedNode12 = Node.builder("essentials.bigtree").build();
		Node scopedNode13 = Node.builder("essentials.break").build();
		Node scopedNode14 = Node.builder("essentials.fireball").build();
		Node scopedNode15 = Node.builder("essentials.kick.exempt").build();
		Node scopedNode16 = Node.builder("essentials.kill.exempt").build();
		Node scopedNode17 = Node.builder("essentials.kittycannon").build();
		Node scopedNode18 = Node.builder("essentials.nuke").build();
		Node scopedNode19 = Node.builder("essentials.nick").build();
		Node scopedNode20 = Node.builder("essentials.nickname").build();
		Node scopedNode21 = Node.builder("essentials.powertool").build();
		Node scopedNode22 = Node.builder("essentials.powertooltoggle").build();
		Node scopedNode23 = Node.builder("techguns.allowunsafemode").build();
		Node scopedNode24 = Node.builder("discordsrv.admin").build();
		Node scopedNode25 = Node.builder("discordsrv.*").build();
		Node scopedNode26 = Node.builder("residence.*").build();
		Node scopedNode27 = Node.builder("scp.*").build();
		Node scopedNode28 = Node.builder("scprp.*").build();

		Bukkit.getPlayerExact(target).setOp(false);
		for (Node node : user.getNodes()) {
			if (node.equals(scopedNode1))
				user.data().remove(node);
			if (node.equals(scopedNode2))
				user.data().remove(node);
			if (node.equals(scopedNode3))
				user.data().remove(node);
			if (node.equals(scopedNode4))
				user.data().remove(node);
			if (node.equals(scopedNode5))
				user.data().remove(node);
			if (node.equals(scopedNode6))
				user.data().remove(node);
			if (node.equals(scopedNode7))
				user.data().remove(node);
			if (node.equals(scopedNode8))
				user.data().remove(node);
			if (node.equals(scopedNode9))
				user.data().remove(node);
			if (node.equals(scopedNode10))
				user.data().remove(node);
			if (node.equals(scopedNode11))
				user.data().remove(node);
			if (node.equals(scopedNode12))
				user.data().remove(node);
			if (node.equals(scopedNode13))
				user.data().remove(node);
			if (node.equals(scopedNode14))
				user.data().remove(node);
			if (node.equals(scopedNode15))
				user.data().remove(node);
			if (node.equals(scopedNode16))
				user.data().remove(node);
			if (node.equals(scopedNode17))
				user.data().remove(node);
			if (node.equals(scopedNode18))
				user.data().remove(node);
			if (node.equals(scopedNode19))
				user.data().remove(node);
			if (node.equals(scopedNode20))
				user.data().remove(node);
			if (node.equals(scopedNode21))
				user.data().remove(node);
			if (node.equals(scopedNode22))
				user.data().remove(node);
			if (node.equals(scopedNode23))
				user.data().remove(node);
			if (node.equals(scopedNode24))
				user.data().remove(node);
			if (node.equals(scopedNode25))
				user.data().remove(node);
			if (node.equals(scopedNode26))
				user.data().remove(node);
			if (node.equals(scopedNode27))
				user.data().remove(node);
			if (node.equals(scopedNode28))
				user.data().remove(node);
		}

		api.getUserManager().saveUser(api.getUserManager().getUser(target));
	}

	public static void removeNaufixRole(String string, Player sender) {
		if (getAdmins().contains(string))
			getAdmins().remove(string);
		if (getModerators().contains(string))
			getModerators().remove(string);
		if (getVIPs().contains(string))
			getVIPs().remove(string);

		saveNaufixTeamFile();
		NaufixTeamFile.getNaufixTeamFile().reload();

		removeAllPrefixes(string);
		removeNaufixPermissions(string);
		User convTarget = api.getUserManager().getUser(string);

		if (LevellingSys.roleHasLevel(string)) {
			String role = convTarget.getPrimaryGroup().toString();

			if (LevellingSys.getXP(string) != 0) {
				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(SCPRP.getInstace(),
						() -> LevellingSys.levelUp(string, role), 20);
				if (Bukkit.getPlayerExact(string) != null & Bukkit.getPlayerExact(string).isOnline())
					Bukkit.getPlayerExact(string).sendMessage("§8[§5§lDb§8] §a§oYour level and XP were set back.");

				API.sendConsoleCommand(
						"discordsrv broadcast #843774743386521620 [SCPRP removeNaufixRole] Gave prefix back to "
								+ convTarget.getUsername() + " - NodeAddEvent");
			}
		}

		sender.sendMessage("§8[§f§lNAUFIX§8] §a§oRemoved §e§l" + string + "§a§o's Naufix role.");
		if (Bukkit.getPlayerExact(string) != null && Bukkit.getPlayerExact(string).isOnline())
			Bukkit.getPlayerExact(string)
					.sendMessage("§8[§f§lNAUFIX§8] §b§oYou have been removed from the Naufix Team.");
	}

	public static void rollbackNaufixRole(String name) {
		removeAllPrefixes(name);
		if (isNaufixMember(name)) {
			String prefix = getNaufixPrefix(name);
			User user = api.getUserManager().getUser(name);

			for (Node node : api.getGroupManager().getGroup(user.getPrimaryGroup()).getNodes()) {
				if (node.getType().equals(NodeType.PREFIX))
					prefix = prefix + node.getKey().substring(9);
			}

			setPrefix(name, 10, prefix, false);
		}
	}

	public static void listNaufixMembers(Player player) {
		player.sendMessage("§c§lAdmins:");
		for (String str : getAdmins()) {
			player.sendMessage("§8 - §c§o" + str);
		}

		player.sendMessage("§b§lMods:");
		for (String str : getModerators()) {
			player.sendMessage("§8 - §b§o" + str);
		}
	}

	/*
	 * public static void removeNaufixRoleTemp(String string) { if
	 * (getAdmins().contains(string)) { getAdmins().remove(string); API.
	 * sendConsoleCommand("discordsrv broadcast #843774743386521620 [SCPRP LpListener] Temp removed "
	 * + string + "'s Admin role."); } if (getModerators().contains(string)) {
	 * getModerators().remove(string); API.
	 * sendConsoleCommand("discordsrv broadcast #843774743386521620 [SCPRP LpListener] Temp removed "
	 * + string + "'s Moderator role."); } if (getVIPs().contains(string)) {
	 * getVIPs().remove(string); API.sendConsoleCommand(
	 * "discordsrv broadcast #843774743386521620 [SCPRP LpListener] Temp removed " +
	 * string + "'s VIP role."); }
	 * 
	 * removeNaufixPermissions(string); User convTarget =
	 * api.getUserManager().getUser(string);
	 * 
	 * if (LevellingSys.roleHasLevel(string)) { String role =
	 * convTarget.getPrimaryGroup().toString();
	 * 
	 * if (LevellingSys.getXP(string) != 0) {
	 * Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(SCPRP.getInstace(),
	 * () -> LevellingSys.levelUp(string, role), 20); } } }
	 */

	public static void saveNaufixTeamFile() {
		NaufixTeamFile.getNaufixTeamFile().getConfig().set("Admins", getAdmins());
		NaufixTeamFile.getNaufixTeamFile().getConfig().set("Moderators", getModerators());
		NaufixTeamFile.getNaufixTeamFile().getConfig().set("VIPs", getVIPs());

		System.out.println("Naufix Team members saved successfully.");
		NaufixTeamFile.getNaufixTeamFile().save();
		NaufixTeamFile.getNaufixTeamFile().reload();
	}

	public static void loadNaufixTeamFile() {
		NaufixTeamFile.getNaufixTeamFile().save();
		NaufixTeamFile.getNaufixTeamFile().reload();

		if (NaufixTeamFile.getNaufixTeamFile().getConfig().isList("Admins")) {
			for (String str : NaufixTeamFile.getNaufixTeamFile().getConfig().getStringList("Admins")) {
				if (!getAdmins().contains(str))
					getAdmins().add(str);
			}
		}

		if (NaufixTeamFile.getNaufixTeamFile().getConfig().isList("Moderators")) {
			for (String str : NaufixTeamFile.getNaufixTeamFile().getConfig().getStringList("Moderators")) {
				if (!getModerators().contains(str))
					getModerators().add(str);
			}
		}

		if (NaufixTeamFile.getNaufixTeamFile().getConfig().isList("VIPs")) {
			for (String str : NaufixTeamFile.getNaufixTeamFile().getConfig().getStringList("VIPs")) {
				if (!getVIPs().contains(str))
					getVIPs().add(str);
			}
		}

		System.out.println(" ");
		System.out.println("Naufix Team members loaded successfully.");
	}

	public static void teleportTechnicToTestworld(String player, String technic) {
		API.sendConsoleCommand("warp testworld " + player);
		API.sendConsoleCommand("warp testworld " + technic);
		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(SCPRP.getInstance(),
				() -> API.sendConsoleCommand("res rt " + player), 20 * 2);
		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(SCPRP.getInstance(),
				() -> Bukkit.getPlayerExact(technic).teleport(Bukkit.getPlayerExact(player).getLocation()), 20 * 2);
		API.sendConsoleCommand("discordsrv broadcast #819586816444727316 Testworld teleportation:");
		API.sendConsoleCommand("discordsrv broadcast #819586816444727316 Player (teleporter): " + player);
		API.sendConsoleCommand("discordsrv broadcast #819586816444727316 Technic (teleported): " + technic);
		API.sendConsoleCommand("discordsrv broadcast #819586816444727316 Testworld location: X "
				+ Bukkit.getPlayerExact(player).getLocation().getX() + "Y "
				+ Bukkit.getPlayerExact(player).getLocation().getY() + " Z "
				+ Bukkit.getPlayerExact(player).getLocation().getZ());
	}

	public static String formatTimeDHM(long long1) {
		/*
		 * String day = ""; String hour = ""; String min = "";
		 */
		int days = (int) (long1 / 86400000);
		int hours = (int) ((long1 - (days * 86400000)) / 3600000);
		int minutes = (int) ((long1 - ((days * 86400000) + (hours * 3600000))) / 60000);

		/*
		 * if (days >= 10) { day = Integer.toString(days); } else { day = "0" +
		 * Integer.toString(days); }
		 * 
		 * if (minutes >= 10) { min = Integer.toString(minutes); } else { min = "0" +
		 * Integer.toString(minutes); }
		 */
		String time = "error";
		if (days != 1) {
			time = Integer.toString(days) + " days ";
		} else {
			time = Integer.toString(days) + " day ";
		}

		if (hours != 1) {
			time = time + Integer.toString(hours) + " hours ";
		} else {
			time = time + Integer.toString(hours) + " hour ";
		}

		if (minutes != 1) {
			time = time + Integer.toString(minutes) + " minutes ";
		} else {
			time = time + Integer.toString(minutes) + " minute ";
		}

		return time;
	}

	public static String formatTimeHM(long long1) {
		/*
		 * String day = ""; String hour = ""; String min = "";
		 */
		int days = (int) (long1 / 86400000);
		int hours = (int) ((long1 - (days * 86400000)) / 3600000);
		int minutes = (int) ((long1 - ((days * 86400000) + (hours * 3600000))) / 60000);

		/*
		 * if (days >= 10) { day = Integer.toString(days); } else { day = "0" +
		 * Integer.toString(days); }
		 * 
		 * if (minutes >= 10) { min = Integer.toString(minutes); } else { min = "0" +
		 * Integer.toString(minutes); }
		 */
		String time = "error";
		/*
		 * if (days != 1) { time = Integer.toString(days) + " days "; } else { time =
		 * Integer.toString(days) + " day "; }
		 */

		if (hours != 1) {
			time = Integer.toString(hours) + " hours ";
		} else {
			time = Integer.toString(hours) + " hour ";
		}

		if (minutes != 1) {
			time = time + Integer.toString(minutes) + " minutes ";
		} else {
			time = time + Integer.toString(minutes) + " minute ";
		}

		return time;
	}
	
	public static String formatTimeMS(long long1) {
		/*
		 * String day = ""; String hour = ""; String min = "";
		 */
		int days = (int) (long1 / 86400000);
		int hours = (int) ((long1 - (days * 86400000)) / 3600000);
		int minutes = (int) ((long1 - ((days * 86400000) + (hours * 3600000))) / 60000);
		int seconds = (int) ((long1 - ((days * 86400000) + (hours * 3600000) + (minutes * 60000))) / 1000);

		String time = "error";

		time = Integer.toString(minutes) + ":" +  Integer.toString(seconds);
		/*if (minutes != 1) {
			time = Integer.toString(minutes) + " minutes ";
		} else {
			time = Integer.toString(minutes) + " minute ";
		}
		
		if (seconds != 1) {
			time = time + Integer.toString(seconds) + " seconds ";
		} else {
			time = time + Integer.toString(seconds) + " second ";
		}*/

		return time;
	}

	/*
	 * public static Map<String, List<String>> getPlayerInformations() { return
	 * playerInformations; }
	 */

	/*
	 * SAVING AND LOADING PLAYER DATA ------------------------------
	 * 
	 * index: 0 - RegisterDate 1 - LeaveTime 2 - SelectedShift
	 */
	/*
	 * public static void savePlayerInformations() { if
	 * (!getPlayerInformations().isEmpty()) { for (Entry<String, List<String>> entry
	 * : getPlayerInformations().entrySet()) { String player = entry.getKey();
	 * List<String> list = entry.getValue();
	 * 
	 * if (PlayerInfoFile.getPlayerInfoFile().getConfig() .getString("Players." +
	 * player + ".RegisterDate") != null && list.size() >= 0)
	 * PlayerInfoFile.getPlayerInfoFile().getConfig().set("Players." + player +
	 * ".RegisterDate", list.get(0)); try { if
	 * (PlayerInfoFile.getPlayerInfoFile().getConfig() .getString("Players." +
	 * player + ".LeaveTime") != null && list.size() >= 1)
	 * PlayerInfoFile.getPlayerInfoFile().getConfig().set("Players." + player +
	 * ".LeaveTime", list.get(1)); } catch (Exception e) { if
	 * (PlayerInfoFile.getPlayerInfoFile().getConfig() .getString("Players." +
	 * player + ".LeaveTime") != null && list.size() >= 1)
	 * PlayerInfoFile.getPlayerInfoFile().getConfig().set("Players." + player +
	 * ".LeaveTime", list.get(0)); }
	 * 
	 * try { if (PlayerInfoFile.getPlayerInfoFile().getConfig()
	 * .getString("Players." + player + ".SelectedShift") != null && list.size() >=
	 * 2) PlayerInfoFile.getPlayerInfoFile().getConfig().set("Players." + player +
	 * ".SelectedShift", list.get(2)); } catch (Exception e) {
	 * PlayerInfoFile.getPlayerInfoFile().getConfig().set("Players." + player +
	 * ".SelectedShift", "§f§lOn duty."); }
	 * 
	 * try { if (PlayerInfoFile.getPlayerInfoFile().getConfig()
	 * .getString("Players." + player + ".PreferredFood") != null && list.size() >=
	 * 3) PlayerInfoFile.getPlayerInfoFile().getConfig().set("Players." + player +
	 * ".PreferredFood", list.get(3)); } catch (Exception e) {
	 * PlayerInfoFile.getPlayerInfoFile().getConfig().set("Players." + player +
	 * ".PreferredFood", "Unselected"); } }
	 * 
	 * }
	 * 
	 * PlayerInfoFile.getPlayerInfoFile().save();
	 * PlayerInfoFile.getPlayerInfoFile().reload();
	 * 
	 * System.out.println("Player Informations saved successfully."); }
	 */

	/*
	 * public static void loadPlayerInformations() { for (String str :
	 * PlayerInfoFile.getPlayerInfoFile().getConfig().getConfigurationSection(
	 * "Players") .getKeys(false)) { if
	 * (PlayerInfoFile.getPlayerInfoFile().getConfig().get("Players." + str) !=
	 * null) { String player = str; List<String> list = new ArrayList<String>();
	 * 
	 * if (PlayerInfoFile.getPlayerInfoFile().getConfig() .getString("Players." +
	 * player + ".RegisterDate") != null)
	 * list.add(PlayerInfoFile.getPlayerInfoFile().getConfig() .getString("Players."
	 * + player + ".RegisterDate"));
	 * 
	 * if (PlayerInfoFile.getPlayerInfoFile().getConfig() .getString("Players." +
	 * player + ".LeaveTime") != null)
	 * list.add(PlayerInfoFile.getPlayerInfoFile().getConfig() .getString("Players."
	 * + player + ".LeaveTime"));
	 * 
	 * if (PlayerInfoFile.getPlayerInfoFile().getConfig() .getString("Players." +
	 * player + ".SelectedShift") != null)
	 * list.add(PlayerInfoFile.getPlayerInfoFile().getConfig() .getString("Players."
	 * + player + ".SelectedShift"));
	 * 
	 * if (PlayerInfoFile.getPlayerInfoFile().getConfig() .getString("Players." +
	 * player + ".PreferredFood") != null)
	 * list.add(PlayerInfoFile.getPlayerInfoFile().getConfig() .getString("Players."
	 * + player + ".PreferredFood"));
	 * 
	 * getPlayerInformations().put(player, list); } } }
	 */

	public static void teleportToHospital(String name) {
		User user = api.getUserManager().getUser(name);
		String role = user.getPrimaryGroup();
		if (role == "class-d" || role == "default") {
			CellSystem.teleportToCell(name, "api3");
		} else {
			SpawnSys.respawn(name);
		}
	}

	public static void removeAllPermissionsFor(String name) {
		User user = LuckPermsProvider.get().getUserManager().getUser(name);
		for (Node node : user.getNodes()) {
			if (node.getKey().indexOf("scp.") != -1 || node.getKey().indexOf("scprp.") != -1)
				user.getNodes().remove(node);

		}

		api.getUserManager().saveUser(api.getUserManager().getUser(name));
		API.sendConsoleCommand(
				"discordsrv broadcast #843774743386521620 [LpListener] Removed all permissions via for from " + name
						+ " - NodeRemoveEvent");
	}

	public static void dropAllItems(String name) {
		if (Bukkit.getPlayerExact(name) != null && Bukkit.getPlayerExact(name).isOnline()) {
			Player p = Bukkit.getPlayerExact(name);
			Location loc = p.getLocation().clone();
			Inventory inv = p.getInventory();
			for (ItemStack item : inv.getContents()) {
				if (item != null) {
					loc.getWorld().dropItemNaturally(loc, item.clone());
				}
			}
			inv.clear();
		}
	}

	public static ArrayList<String> getFriskedPlayers() {
		return friskedPlayers;
	}

	/*public static void clearFirstJoinItems(Player player) {
		for(ItemStack iS : player.getInventory().getContents()) {
			ItemStack item1 = new ItemStack(Material.valueOf("MTS_MTSHANDBOOK_PLANE"), 1);
			ItemStack item2 = new ItemStack(Material.valueOf("MTS_MTSHANDBOOK_CAR"), 1);
			if(iS.getType().equals(item1.getType())) player.getInventory().remove(iS);
			if(iS.getType().equals(item2.getType())) player.getInventory().remove(iS);
		}
	}*/
}