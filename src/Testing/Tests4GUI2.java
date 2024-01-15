package Testing;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

import cz.vojtiisek.scprpsystem.Items;
import cz.vojtiisek.scprpsystem.SCPRP;

public class Tests4GUI2 implements Listener {
	private static SCPRP main;
	private static String title = "§9§lTESTS DATABASE §d§l- §c§lL4 §a§oPage 2";

	public Tests4GUI2(SCPRP main) {
		this.main = main;
	}

	public static void openGUI(Player player) {
		Inventory inv = Bukkit.createInventory(null, 54, title);
		inv.setItem(0, Items.sideItem());
		inv.setItem(1, Items.sideItem());
		inv.setItem(2, Items.sideItem());
		inv.setItem(3, Items.sideItem());
		inv.setItem(4, Items.sideItem());
		inv.setItem(5, Items.sideItem());
		inv.setItem(6, Items.sideItem());
		inv.setItem(7, Items.sideItem());
		inv.setItem(8, Items.sideItem());
		inv.setItem(9, Items.sideItem());
		inv.setItem(18, Items.sideItem());
		inv.setItem(27, Items.sideItem());
		inv.setItem(36, Items.sideItem());
		inv.setItem(45, Items.sideItem());
		inv.setItem(17, Items.sideItem());
		inv.setItem(26, Items.sideItem());
		inv.setItem(35, Items.sideItem());
		inv.setItem(44, Items.sideItem());
		inv.setItem(45, Items.sideItem());
		inv.setItem(46, Items.sideItem());
		inv.setItem(47, Items.sideItem());
		inv.setItem(48, Items.prevPageTest4());
		inv.setItem(49, Items.sideItem());
		inv.setItem(50, Items.sideItem());
		inv.setItem(51, Items.sideItem());
		inv.setItem(52, Items.sideItem());
		inv.setItem(53, Items.sideItem());

		inv.setItem(20, Items.oldMan());
		inv.setItem(21, Items.reptile());

		player.openInventory(inv);
	}

	@EventHandler
	public void onInvClick(InventoryClickEvent e) {
		Player p = (Player) e.getWhoClicked();
		if (!(e.getInventory().getName().equals(title)))
			return;
		if (e.getCurrentItem() == null || e.getCurrentItem().getType().equals(Material.AIR)
				|| !e.getCurrentItem().hasItemMeta()) {
			e.setCancelled(true);
			return;
		}

		e.setCancelled(true);

		switch (e.getSlot()) {
		case 20:
			if (e.getCurrentItem().getItemMeta().equals(Items.oldMan().getItemMeta())) {
				p.closeInventory();
				p.sendMessage(
						"§8[§c§lSCP§8] Read the following documents: https://drive.google.com/drive/folders/1HITnVd24C1Qv3hQEnMv5MjYToPT2qZ1h?usp=sharing (only pick 1 testing procedure, if there are more than 1 available.)");
				p.sendMessage(
						"§8[§c§lSCP§8] §8§oUse §c§o/test start 106§8§o once you have opened the testing procedure instructions.");
			} else {
				return;
			}
			break;
		case 21:
			if (e.getCurrentItem().getItemMeta().equals(Items.reptile().getItemMeta())) {
				p.closeInventory();
				p.sendMessage(
						"§8[§c§lSCP§8] Read the following documents: https://drive.google.com/drive/folders/1HxxNszu3caP-9PtOX7Ni-oLMGHy3lz9e?usp=sharing (only pick 1 testing procedure, if there are more than 1 available).");
				p.sendMessage(
						"§8[§c§lSCP§8] §8§oUse §c§o/test start 682§8§o once you have opened the testing procedure instructions.");
			} else {
				return;
			}
			break;
		case 48:
			if (e.getCurrentItem().getItemMeta().equals(Items.prevPageTest4().getItemMeta())) {
				p.closeInventory();
				Tests4GUI.openGUI(p);
				return;
			}
			break;
		default:
			break;
		}
	}
}