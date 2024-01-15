package cz.vojtiisek.scprpsystem.ConsoleCommands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import com.earth2me.essentials.api.Economy;

import cz.vojtiisek.scprpsystem.API;
import cz.vojtiisek.scprpsystem.SCPRP;
import net.luckperms.api.LuckPerms;

public class ConsolePayout implements CommandExecutor{
	private static SCPRP main;
	public static LuckPerms api;

	public ConsolePayout(SCPRP main) {
		this.main = main;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabels, String[] args) {
		ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
		sender = console;

		if (cmd.getName().equals("consolepayout")) {
			if (sender instanceof Player) {
				Player player = (Player) sender;
				player.sendMessage("§8[§4§l!§8] §c§oThis is a console only command.");
			} else {
				if (args.length == 2) {
					if (args[1] == null || args[1].isEmpty()) {
						System.out.println(
								"§8§l[§2§lBANK§8§l] §2§oTo pay a player out, use §a§l/payout (Player) §c§l(Value)");
					} else {
						if (Bukkit.getPlayerExact(args[0]) != null) {
							if (Bukkit.getPlayerExact(args[0]).isOnline()) {
								if (API.isInteger(args[1])) {
									Player target = Bukkit.getPlayerExact(args[0]);
									int value = API.parse(args[1]);
									try {
										Economy.add(args[0], (double) value);
										System.out.println(
												"§8[§2§lBANK§8] §a§oThe transaction was successful. Target:§f§l "
														+ args[0] + "§a§o, value: §2§l" + value + "§a§o.");
										target.sendMessage(
												"§8[§2§lBANK§8] §a§oYou have received your payout. Value: §2§l" + value
														+ "§a§o.");
										API.sendConsoleCommand(
												"discord broadcast #849573930308993024 CONSOLE"
														+ " - payout - target-" + args[0] + " - value: " + value);
									} catch (Exception e) {
										API.sendConsoleCommand(
												"discord broadcast #839057785362448424 [SCPRP] Payout Exception, sent by: CONSOLE to " + args[0] + ", value: "
														+ value);
										e.printStackTrace();
									}
								} else {
									System.out.println("§8§l[§c§lBANK§8§l] §c§oValue must be a number!");
								}
							} else {
								System.out.println("§8§l[§c§lBANK§8§l] §e§l" + args[0] + "§c§o is not online!");
							}
						} else {
							System.out.println("§8§l[§c§lBANK§8§l] §e§l" + args[0] + "§c§o does not exist!");
						}
					}
				}
			}
		}
		return false;
	}
}