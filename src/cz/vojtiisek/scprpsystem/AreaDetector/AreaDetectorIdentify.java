package cz.vojtiisek.scprpsystem.AreaDetector;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import cz.vojtiisek.scprpsystem.SCPRP;

public class AreaDetectorIdentify implements Listener {
	private static SCPRP main;

	public static Map<String, Boolean> identifyList = new HashMap<String, Boolean>();

	public AreaDetectorIdentify(SCPRP main) {
		this.main = main;
	}

	public static Map<String, Boolean> getIdentifyList() {
		return identifyList;
	}

	public static void enableTool(String name) {
		getIdentifyList().put(name, true);
		Bukkit.getPlayerExact(name).sendMessage("§8§l[§a§l!§8§l] §a§oIdentify Area Detector tool §2§lenabled§a§o.");
		Bukkit.getPlayerExact(name).sendMessage("§b§oNow, click the Area Detector blocks to get it's name.");
		Bukkit.getPlayerExact(name).sendMessage("§e§oTo quit the tool, use the command again.");
	}

	public static void disableTool(String name) {
		getIdentifyList().put(name, false);
		Bukkit.getPlayerExact(name).sendMessage("§8§l[§a§l!§8§l] §a§oIdentify Area Detector tool §c§ldisabled§a§o.");
	}

	@EventHandler
	public void onLeverUse(PlayerInteractEvent event) {
		if (event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
			if (getIdentifyList().containsKey(event.getPlayer().getName())
					&& getIdentifyList().get(event.getPlayer().getName()).equals(true)) {
					Location loc = new Location(event.getClickedBlock().getLocation().getWorld(),
							event.getClickedBlock().getLocation().getX(), event.getClickedBlock().getLocation().getY(),
							event.getClickedBlock().getLocation().getZ());
					int count = 0;

					for (Entry<String, List<Location>> entry : AreaDetector.getDetectors().entrySet()) {
						for (Location idloc : entry.getValue()) {
							if (idloc == loc) {
								count = count + 1;
								event.getPlayer().sendMessage("§8§l[§9§lIDENTIFY§8§l] §e§oThis block is part of the §9§l"
										+ entry.getKey() + " §e§oArea Detecor. Type: " + AreaDetector.blockInDetector(loc));
							}
						}
					}

					if (count == 0)
						event.getPlayer().sendMessage("§8§l[§9§lIDENTIFY§8§l] §c§oThis block is not a part of any Area Detector.");
				

				event.setCancelled(true);
			}
		}
	}
}