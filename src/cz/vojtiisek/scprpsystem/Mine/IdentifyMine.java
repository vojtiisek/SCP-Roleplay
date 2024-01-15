package cz.vojtiisek.scprpsystem.Mine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import cz.vojtiisek.scprpsystem.SCPRP;

public class IdentifyMine implements Listener {
	private static SCPRP main;

	public static Map<String, Boolean> identifyList = new HashMap<String, Boolean>();

	public IdentifyMine(SCPRP main) {
		this.main = main;
	}

	public static Map<String, Boolean> getIdentifyList() {
		return identifyList;
	}

	public static void enableTool(String name) {
		getIdentifyList().put(name, true);
		Bukkit.getPlayerExact(name).sendMessage("§8§l[§a§l!§8§l] §a§oIdentify Mine tool §2§lenabled§a§o.");
		Bukkit.getPlayerExact(name).sendMessage("§b§oNow, click any ore to get the mine's name.");
		Bukkit.getPlayerExact(name).sendMessage("§e§oTo quit the tool, use the command again.");
	}

	public static void disableTool(String name) {
		getIdentifyList().put(name, false);
		Bukkit.getPlayerExact(name).sendMessage("§8§l[§a§l!§8§l] §a§oIdentify Mine tool §c§ldisabled§a§o.");
	}

	@EventHandler
	public void onLeverUse(PlayerInteractEvent event) {
		if (event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
			if (getIdentifyList().containsKey(event.getPlayer().getName())
					&& getIdentifyList().get(event.getPlayer().getName()).equals(true)) {
				if (event.getClickedBlock().getType().equals(Material.COAL_ORE)
						|| event.getClickedBlock().getType().equals(Material.IRON_ORE)
						|| event.getClickedBlock().getType().equals(Material.DIAMOND_ORE)
						|| event.getClickedBlock().getType().equals(Material.EMERALD_ORE)) {

					Location loc = new Location(event.getClickedBlock().getLocation().getWorld(),
							event.getClickedBlock().getLocation().getX(), event.getClickedBlock().getLocation().getY(),
							event.getClickedBlock().getLocation().getZ());
					int count = 0;

					for (Entry<String, List<Location>> entry : Mine.getMines().entrySet()) {
						for (Location idloc : entry.getValue()) {
							if (idloc == loc) {
								count = count + 1;
								event.getPlayer().sendMessage("§8§l[§9§lIDENTIFY§8§l] §e§oThis ore is part of the §9§l"
										+ entry.getKey() + " §e§omine.");
							}
						}
					}

					if (count == 0)
						event.getPlayer().sendMessage("§8§l[§9§lIDENTIFY§8§l] §c§oThis ore is not a part of any Mine.");
				}  else {
					event.getPlayer().sendMessage("§8§l[§9§lIDENTIFY§8§l] §c§oYou must right click an ore!");
				}

				event.setCancelled(true);
			}
		}
	}
}
