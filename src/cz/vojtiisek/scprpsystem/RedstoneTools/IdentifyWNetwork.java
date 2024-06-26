package cz.vojtiisek.scprpsystem.RedstoneTools;

import java.util.HashMap;
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

public class IdentifyWNetwork implements Listener {
	private static SCPRP main;

	public static Map<String, Boolean> identifyList = new HashMap<String, Boolean>();

	public IdentifyWNetwork(SCPRP main) {
		this.main = main;
	}

	public static Map<String, Boolean> getIdentifyList() {
		return identifyList;
	}

	public static void enableTool(String name) {
		getIdentifyList().put(name, true);
		Bukkit.getPlayerExact(name).sendMessage("�8�l[�a�l!�8�l] �a�oIdentify Wireless Network tool �2�lenabled�a�o.");
		Bukkit.getPlayerExact(name).sendMessage("�b�oNow, click any wireless lever to get the network's name.");
		Bukkit.getPlayerExact(name).sendMessage("�e�oTo quit the tool, use the command again.");
	}

	public static void disableTool(String name) {
		getIdentifyList().put(name, false);
		Bukkit.getPlayerExact(name).sendMessage("�8�l[�a�l!�8�l] �a�oIdentify Wireless Network tool �c�ldisabled�a�o.");
	}

	@EventHandler
	public void onLeverUse(PlayerInteractEvent event) {
		if (event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
			if (event.getClickedBlock().getType().equals(Material.LEVER)) {
				if (getIdentifyList().containsKey(event.getPlayer().getName())
						&& getIdentifyList().get(event.getPlayer().getName()).equals(true)) {
					Location loc = new Location(event.getClickedBlock().getLocation().getWorld(),
							event.getClickedBlock().getLocation().getX(), event.getClickedBlock().getLocation().getY(),
							event.getClickedBlock().getLocation().getZ());
					
					if(WirelessNetworks.getLevers().containsValue(loc)) {
						for(Entry<String, Location> entry : WirelessNetworks.getLevers().entrySet()) {
							if(entry.getValue().equals(loc)) event.getPlayer().sendMessage("�8�l[�9�lIDENTIFY�8�l] �e�oThis lever is part of the �6�l" + entry.getKey() + " �e�onetwork.");
						}
					} else {
						event.getPlayer().sendMessage("�8�l[�9�lIDENTIFY�8�l] �c�oThis lever is not a part of any Wireless Network.");
					}
					
					event.setCancelled(true);
				}
			}
		}
	}
}