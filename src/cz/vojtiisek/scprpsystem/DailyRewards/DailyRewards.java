package cz.vojtiisek.scprpsystem.DailyRewards;

import java.util.Random;

import org.bukkit.Bukkit;

import com.earth2me.essentials.api.Economy;
import com.earth2me.essentials.api.NoLoanPermittedException;
import com.earth2me.essentials.api.UserDoesNotExistException;

import Levelling.LevellingSys;
import cz.vojtiisek.scprpsystem.API;
import cz.vojtiisek.scprpsystem.SCPRP;
import cz.vojtiisek.scprpsystem.files.PlayerInfoFile;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;

public class DailyRewards {
	public SCPRP main;

	static LuckPerms api = LuckPermsProvider.get();

	public DailyRewards(SCPRP main) {
		this.main = main;
	}

	public static void claimReward(String name) {
		long now = System.currentTimeMillis();
		PlayerInfoFile.getPlayerInfoFile().getConfig().set("Players." + name + ".DailyReward", Long.toString(now));
		PlayerInfoFile.getPlayerInfoFile().save();
		Random r = new Random();
		int xp = r.nextInt(300 - 30) + 30;
		int money = r.nextInt(300 - 50) + 50;
		
		if(LevellingSys.roleHasLevel(name)) {
			LevellingSys.addXP(name, xp);
		} else {
			int bonusMoney = r.nextInt(150 - 50) + 50;
			money = money + bonusMoney;
		}
		
		try {
			Economy.add(name, (double) money);
		} catch (NoLoanPermittedException e) {
			API.sendConsoleCommand(
					"discordsrv broadcast #839057785362448424 [DailyRewards] claimReward(String name) NoLoanPermittedException");
			e.printStackTrace();
		} catch (UserDoesNotExistException e) {
			API.sendConsoleCommand(
					"discordsrv broadcast #839057785362448424 [DailyRewards] claimReward(String name) UserDoesNotExistException");
			e.printStackTrace();
		}
		
		if(LevellingSys.roleHasLevel(name)) {
			Bukkit.getPlayer(name).sendMessage("§8§l[§a§l!§8§l] §a§oYou have claimed your reward and got: §e§l" + money + "§2§l$ §a§oand " + xp + " §b§lXP§a§o!");
		} else {
			Bukkit.getPlayer(name).sendMessage("§8§l[§a§l!§8§l] §a§oYou have claimed your reward and got: §e§l" + money + "§2§l$§a§o!");
		}
	}
}