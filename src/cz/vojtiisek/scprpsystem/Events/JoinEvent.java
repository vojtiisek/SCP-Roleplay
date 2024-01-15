package cz.vojtiisek.scprpsystem.Events;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import Levelling.LevellingSys;
import cz.vojtiisek.scprpsystem.API;
import cz.vojtiisek.scprpsystem.ClassDDesignations;
import cz.vojtiisek.scprpsystem.SCPRP;
import cz.vojtiisek.scprpsystem.CellSystem.CellSystem;
import cz.vojtiisek.scprpsystem.Constructions.ConstructionsMenu;
import cz.vojtiisek.scprpsystem.DailyRewards.DailyRewards;
import cz.vojtiisek.scprpsystem.Tasks.TasksMenu;
import cz.vojtiisek.scprpsystem.Tasks.TasksSys;
import cz.vojtiisek.scprpsystem.files.PlayerInfoFile;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;

public class JoinEvent implements Listener {
	private static SCPRP main;

	public static LuckPerms api;

	public JoinEvent(SCPRP main) {
		this.main = main;
	}

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {
		Player player = e.getPlayer();
		if (PlayerInfoFile.getPlayerInfoFile().getConfig().get("Players." + e.getPlayer().getName()) == null) {
			System.out.println("[SCPRP] Creating a data file for player: " + e.getPlayer().getName());
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			LocalDateTime now = LocalDateTime.now();
			String str = "error";
			str = dtf.format(now);
			ArrayList<String> playerInfo = new ArrayList<String>();
			playerInfo.add(str);
			playerInfo.add("0");
			playerInfo.add("§f§lOn duty.");
			playerInfo.add("Unselected");

			PlayerInfoFile.getPlayerInfoFile().getConfig().set("Players." + e.getPlayer().getName() + ".RegisterDate",
					str);
			PlayerInfoFile.getPlayerInfoFile().getConfig().set("Players." + e.getPlayer().getName() + ".LeaveTime",
					"0");
			PlayerInfoFile.getPlayerInfoFile().getConfig().set("Players." + e.getPlayer().getName() + ".SelectedShift",
					"§f§lOn duty.");
			PlayerInfoFile.getPlayerInfoFile().getConfig().set("Players." + e.getPlayer().getName() + ".ShiftBoolean",
					false);
			PlayerInfoFile.getPlayerInfoFile().getConfig().set("Players." + e.getPlayer().getName() + ".PreferredFood",
					"Unselected");
			PlayerInfoFile.getPlayerInfoFile().getConfig().set("Players." + e.getPlayer().getName() + ".DailyReward",
					"0");

			PlayerInfoFile.getPlayerInfoFile().save();
			System.out
					.println("[SCPRP] Register date for player: " + e.getPlayer().getName() + " created. Date: " + str);
		} else {
			System.out.println("[SCPRP] Player already has a register date logged.");
		}

		e.getPlayer().sendMessage("§8§o----------------------------------------");
		e.getPlayer().sendMessage("§c§oPlease, note that the server is in §e§lBETA§c§o!");
		e.getPlayer().sendMessage("§c§oReport any bugs you find on §5§l/discord");
		e.getPlayer().sendMessage("§9§oServer menu: §f§l/scp");
		e.getPlayer().sendMessage("§b§oSuggestions are welcome!");

		long nowRew = System.currentTimeMillis();
		if (PlayerInfoFile.getPlayerInfoFile().getConfig()
				.getString("Players." + player.getName() + ".DailyReward") != null) {
			if (PlayerInfoFile.getPlayerInfoFile().getConfig()
					.getString("Players." + player.getName() + ".DailyReward") == "0") {
				e.getPlayer().sendMessage(
						"§a§oDo not forget to claim your §e§oDaily Reward§a§o! Use §f§l/daily§a§o to do so.");
			} else {
				if (!(nowRew - Long.parseLong(PlayerInfoFile.getPlayerInfoFile().getConfig()
						.getString("Players." + player.getName() + ".DailyReward")) < 86400000)) {
					e.getPlayer().sendMessage(
							"§a§oDo not forget to claim your §e§oDaily Reward§a§o! Use §f§l/daily§a§o to do so.");
				}
			}
		} else {
			e.getPlayer()
					.sendMessage("§a§oDo not forget to claim your §e§oDaily Reward§a§o! Use §f§l/daily§a§o to do so.");
		}
		e.getPlayer().sendMessage("§8§o----------------------------------------");

		long leaveTime = 0;
		try {
			if (PlayerInfoFile.getPlayerInfoFile().getConfig()
					.get("Players." + e.getPlayer().getName() + ".LeaveTime") != null) {
				leaveTime = Long.parseLong(PlayerInfoFile.getPlayerInfoFile().getConfig()
						.getString("Players." + e.getPlayer().getName() + ".LeaveTime"));
			}
		} catch (Exception x) {
			x.printStackTrace();
		}

		if (PlayerInfoFile.getPlayerInfoFile().getConfig()
				.get("Players." + e.getPlayer().getName() + ".LeaveTime") != null) {
			PlayerInfoFile.getPlayerInfoFile().getConfig().set("Players." + e.getPlayer().getName() + ".LeaveTime",
					"0");
			System.out.println("[SCPRP] Leave time set to 0 (No previous record)");
		} else {
			PlayerInfoFile.getPlayerInfoFile().getConfig().set("Players." + e.getPlayer().getName() + ".LeaveTime",
					"0");
			System.out.println("[SCPRP] Leave time reset to 0 (Existing previous record)");
		}

		PlayerInfoFile.getPlayerInfoFile().save();

		long now = System.currentTimeMillis();
		if (API.findCellIdByPname(e.getPlayer().getName()) != "error") {
			if (now - leaveTime > 300000 || leaveTime == 0) {
				API.teleportToSpawnPoint(e.getPlayer());
				System.out.println("[SCPRP] Player " + e.getPlayer().getName() + " last seen "
						+ API.formatTimeDHM(now - leaveTime) + " (dd:hh:mm) ago, teleporting to their Spawn point.");

			} else {
				e.getPlayer().sendMessage(
						"§8[§a§l!§8] §a§oYou were not teleported to your Spawn point, as you were last online in less than 5 minutes ago.");
				System.out.println("[SCPRP] Player " + e.getPlayer().getName()
						+ " last seen less than 5 minutes ago. The player has not been teleported to their Spawn point.");
			}
		}

		ClassDDesignations.onNewbieJoin(e.getPlayer());

		String name = e.getPlayer().getName();
		String role = LuckPermsProvider.get().getUserManager().getUser(e.getPlayer().getName()).getPrimaryGroup();

		if (role.equalsIgnoreCase("security-officer")) {
			if (now - leaveTime > 7200000 || leaveTime == 0) {
				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(SCPRP.getInstace(), () -> TasksMenu
						.openGUI(name, role, LevellingSys.convertXpToLevel(role, LevellingSys.getXP(name))), 40);
				e.getPlayer().sendMessage("§8[§9§l?§8] §3§oPlease, select a shift.");

			} else if (TasksSys.getTaskNames().contains(
					PlayerInfoFile.getPlayerInfoFile().getConfig().getString("Players." + name + ".SelectedShift"))
					|| PlayerInfoFile.getPlayerInfoFile().getConfig().getString("Players." + name + ".SelectedShift")
							.equalsIgnoreCase("§f§lOn duty.")) {
				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(SCPRP.getInstace(), () -> TasksMenu
						.openGUI(name, role, LevellingSys.convertXpToLevel(role, LevellingSys.getXP(name))), 40);

				e.getPlayer().sendMessage("§8[§9§l?§8] §3§oPlease, select a shift.");
			} else {
				e.getPlayer().sendMessage("§8§l[§c§l!§8§l] §c§oYou can not change your shift right now.");
			}
		} else if (role.equalsIgnoreCase("technic")) {
			PlayerInfoFile.getPlayerInfoFile().getConfig().set("Players." + e.getPlayer().getName() + ".SelectedShift",
					"§f§lOn duty.");
			if (API.findConstructionNameByMember(e.getPlayer().getName()) == "emptyemptyemptyemptyemptyerr") {
				if (ConstructionsMenu.getConstructions().size() >= 1) {
					Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(SCPRP.getInstace(),
							() -> ConstructionsMenu.openGUI(e.getPlayer(), role), 40);
					e.getPlayer().sendMessage("§8[§9§l?§8] §3§oPlease, select a shift (construction).");
				} else {
					e.getPlayer().sendMessage(
							"§8[§9§l?§8] §3§oThere are currently no ongoing constructions you can join. Ask the Site Director, or any Storage Leader for any tasks.");
				}
			} else {
				TasksSys.setSelectedShift(e.getPlayer().getName(),
						API.getRandomColorBoldString() + API.findConstructionNameByMember(e.getPlayer().getName()));
				e.getPlayer().sendMessage("§8§l[§a§l!§8§l] §3§oYou already have joined a construction ("
						+ API.getRandomColorBoldString() + API.findConstructionNameByMember(e.getPlayer().getName())
						+ "§3§o). Use §e§l/constructions §3§ofor further details.");
			}
		} else {
			e.getPlayer().sendMessage(
					"§8§l[§9§l?§8§l] §3§oTo view your tasks, read your role's Tasks and About documents accessible via Discord, or ask any higher up from your department.");
		}

		if (PlayerInfoFile.getPlayerInfoFile().getConfig().get("Players." + e.getPlayer().getName() + ".PreferredFood",
				"Unselected") == null) {
			PlayerInfoFile.getPlayerInfoFile().getConfig().set("Players." + e.getPlayer().getName() + ".PreferredFood",
					"Unselected");
		}

		if (LuckPermsProvider.get().getUserManager().getUser(e.getPlayer().getName()).getPrimaryGroup()
				.equals("class-d")
				|| LuckPermsProvider.get().getUserManager().getUser(e.getPlayer().getName()).getPrimaryGroup()
						.equals("default")) {
			if (now - leaveTime > 300000 || leaveTime == 0) {
				double radius = 3D;
				if (e.getPlayer().getLocation().getWorld().getName().equals("RPWorld")) {
					if (e.getPlayer().getLocation()
							.distance(new Location(Bukkit.getWorld("RPWorld"), -112.53, 189, -7.62)) >= radius) {
						Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(SCPRP.getInstace(),
								() -> CellSystem.teleportToCell(e.getPlayer().getName(), "joinev"), 20);

						System.out.println("[SCPRP] Player " + e.getPlayer().getName()
								+ " has been telepored to their cell from spawn, their security risk: "
								+ API.findCellLevelById(API.findCellIdByPname(e.getPlayer().getName())));
					}
				} else {
					CellSystem.teleportToCell(e.getPlayer().getName(), "joinev");

					System.out.println("[SCPRP] Player " + e.getPlayer().getName()
							+ " has been telepored to their cell from spawn, their security risk: "
							+ API.findCellLevelById(API.findCellIdByPname(e.getPlayer().getName())));
				}
			}

			ClassDDesignations.rollbackDesignationSilent(e.getPlayer().getName());
		}
		PlayerInfoFile.getPlayerInfoFile().save();
	}
}