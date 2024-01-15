package cz.vojtiisek.scprpsystem.Tasks;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;

import cz.vojtiisek.scprpsystem.API;
import cz.vojtiisek.scprpsystem.SCPRP;
import cz.vojtiisek.scprpsystem.files.PlayerInfoFile;
import net.luckperms.api.LuckPerms;

public class TasksSys {
	private static SCPRP main;
	public static LuckPerms api;

	public TasksSys(SCPRP main) {
		this.main = main;
	}

	public static void setSelectedShift(String name, String string) {
		if(string != "§f§lOn duty.") PlayerInfoFile.getPlayerInfoFile().getConfig().set("Players." + name + ".ShiftBoolean", true);
		if (PlayerInfoFile.getPlayerInfoFile().getConfig().get("Players." + name) != null) {
			if (PlayerInfoFile.getPlayerInfoFile().getConfig().get("Players." + name + ".SelectedShift") != null && !PlayerInfoFile.getPlayerInfoFile().getConfig().getString("Players." + name + ".SelectedShift").isEmpty()) {
				PlayerInfoFile.getPlayerInfoFile().getConfig().set("Players." + name + ".SelectedShift", string);
			}
		} else {
			PlayerInfoFile.getPlayerInfoFile().getConfig().set("Players." + name + ".SelectedShift", string);
		}
		
		Bukkit.getPlayerExact(name).sendMessage("§8§l[§a§l!§8§l] §a§oYou selected a shift at " + string + "§a§o. Please, get to the area.");
		PlayerInfoFile.getPlayerInfoFile().save();
	}

	public static List<String> getTaskNames() {
		List<String> list = new ArrayList<String>();
		list.add("§f§lReception & Medical");
		list.add("§a§lSector 2");
		list.add("§a§lMinimum Class-D Cells");
		list.add("§b§lScientific Department");
		list.add("§e§lMedium Class-D cells");
		list.add("§7§lSector 3 + LCZ");
		list.add("§6§lE&Ts Department");
		list.add("§4§lMaximum Class-D cells");
		list.add("§c§lSector 4 + HCZ");
		list.add("§4§lSector 5");
		return list;
	}
}