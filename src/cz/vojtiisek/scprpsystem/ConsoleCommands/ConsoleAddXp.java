package cz.vojtiisek.scprpsystem.ConsoleCommands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import Levelling.LevellingSys;
import cz.vojtiisek.scprpsystem.API;
import cz.vojtiisek.scprpsystem.SCPRP;
import net.luckperms.api.LuckPerms;

public class ConsoleAddXp implements CommandExecutor {
	private static SCPRP main;
	public static LuckPerms api;

	public ConsoleAddXp(SCPRP main) {
		this.main = main;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabels, String[] args) {
		ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
		sender = console;
		if (cmd.getName().equalsIgnoreCase("consoleaddxp")) {
			if (args.length == 2) {
				if (sender instanceof Player) {
					Player player = (Player) sender;
					player.sendMessage("§8[§4§l!§8] §c§oThis is a console only command.");
				} else {
					if (Bukkit.getPlayerExact(args[0]) != null) {
						if (Bukkit.getPlayerExact(args[0]).isOnline()) {
							if (LevellingSys.roleHasLevel(args[0])) {
								if (args[1] != null) {
									if (API.isInteger(args[1])) {
										LevellingSys.addXP(args[0], API.parse(args[1]));
										API.sendConsoleCommand(
												"discord broadcast #819586816444727316 Console - level consoleaddxp - Target: "
														+ args[0] + " - XP: " + args[1]);
										System.out.println("§8§l[§a§l!§8§l] §a§oGiven§b§l " + args[1]
												+ " §a§oXP to §f§l" + args[0] + "§a§o.");
									} else {
										System.out.println("§8§l[§c§l!§8§l] §c§oXP must be a number!");
									}
								} else {
									System.out.println(
											"§8§l[§c§l!§8§l] §b§oTo give XP to a Player, use §f§l/level addxp (Player) (XP)");
								}
								System.out
										.println("§8§l[§9§l?§8§l] §b§o" + args[0] + "§a§o has: §e§l"
												+ LevellingSys.getXP(args[0]) + " §a§oXP. (Level §3§l"
												+ LevellingSys.convertXpToLevel(api.getUserManager().getUser(args[0])
														.getPrimaryGroup().toString(), LevellingSys.getXP(args[0]))
												+ "§a§o)");
							} else {
								System.out.println(
										"§8§l[§c§l!§8§l] §c§oThis player's role does not level up or get any XP.");
							}
						} else {
							System.out.println("§8§l[§c§l!§8§l] §c§oThe stated player is not online!");
						}
					} else {
						System.out.println("§8§l[§c§l!§8§l] §c§oThe stated player does not exist!");
					}
				}
			}
		}
		return false;
	}
}