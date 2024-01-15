package cz.vojtiisek.scprpsystem.Events;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import cz.vojtiisek.scprpsystem.API;
import cz.vojtiisek.scprpsystem.SCPRP;
import cz.vojtiisek.scprpsystem.files.PlayerInfoFile;
import net.luckperms.api.LuckPerms;

public class LeaveEvent implements Listener {
	private static SCPRP main;

	public static LuckPerms api;

	public static List<String> combatLeaves = new ArrayList<String>();
	
	public LeaveEvent(SCPRP main) {
		this.main = main;
	}

	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent e) {
		long now = System.currentTimeMillis();	
		if(PlayerInfoFile.getPlayerInfoFile().getConfig().get("Players." + e.getPlayer().getName()) != null) {
			PlayerInfoFile.getPlayerInfoFile().getConfig().set("Players." + e.getPlayer().getName() + ".LeaveTime", Long.toString(now)); 
			PlayerInfoFile.getPlayerInfoFile().save();
		}

		if (SpawnkillProtect.getCombat().containsKey(e.getPlayer().getName())
				&& SpawnkillProtect.getCombat().get(e.getPlayer().getName()) > System.currentTimeMillis()) {
			getCombatLeaves().add(e.getPlayer().getName());
			System.out.println("[SCPRP Combat] Player " + e.getPlayer().getName() + " has left the server while in Combat. Spawn Protection disabled for the next join.");
		}
	}
	
	public static List<String> getCombatLeaves() {
		return combatLeaves;
	}
}