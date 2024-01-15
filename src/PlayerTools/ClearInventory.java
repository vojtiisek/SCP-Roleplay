package PlayerTools;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;

import cz.vojtiisek.scprpsystem.SCPRP;

public class ClearInventory implements Listener {
	private static SCPRP main;
	private static String title = "§c§oSelect items to clear";

	public ClearInventory(SCPRP main) {
		this.main = main;
	}

	public static void openGUI(Player player) {
		Inventory inv = Bukkit.createInventory(null, 36, title);
		player.openInventory(inv);
		player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 2.0F, 1.0F);
	}
	
	@EventHandler
	public void onInvClose(InventoryCloseEvent e) {
		if (!(e.getInventory().getName().equals(title)))
			return;
		
		e.getInventory().clear();
	}

}
