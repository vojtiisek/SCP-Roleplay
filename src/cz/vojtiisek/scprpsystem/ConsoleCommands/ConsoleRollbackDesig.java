package cz.vojtiisek.scprpsystem.ConsoleCommands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import cz.vojtiisek.scprpsystem.ClassDDesignations;
import cz.vojtiisek.scprpsystem.SCPRP;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.model.user.User;

public class ConsoleRollbackDesig implements CommandExecutor {
	private static SCPRP main;
	public static LuckPerms api;

	public ConsoleRollbackDesig(SCPRP main) {
		this.main = main;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabels, String[] args) {
		ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
		sender = console;
		if (cmd.getName().equalsIgnoreCase("consoledesigrb")) {
			if (args.length == 2) {
				if (Bukkit.getPlayerExact(args[0]) != null) {
					if (Bukkit.getPlayerExact(args[0]).isOnline()) {
						User user = api.getUserManager().getUser(args[0]);
						if (user.getPrimaryGroup().equalsIgnoreCase("default")
								|| user.getPrimaryGroup().equalsIgnoreCase("class-d")) {
							ClassDDesignations.rollbackDesignation(args[1]);
						} else {
							System.out.println("[!] This player is not a Class-D!");
						}
					} else {
						System.out.println("[!] The stated player is not online!");
					}
				} else {
					System.out.println("[!] The stated player does not exist!");
				}
			}
		}
		return false;
	}
}