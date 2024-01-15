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

public class Tests1GUI implements Listener {
	private static SCPRP main;
	private static String title = "§9§lTESTS DATABASE §f§l- §f§lL1";

	public Tests1GUI(SCPRP main) {
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
		inv.setItem(48, Items.sideItem());
		inv.setItem(49, Items.sideItem());
		inv.setItem(50, Items.sideItem());
		inv.setItem(51, Items.sideItem());
		inv.setItem(52, Items.sideItem());
		inv.setItem(53, Items.sideItem());

		inv.setItem(20, Items.shark());
		inv.setItem(21, Items.swatArmor());
		inv.setItem(22, Items.halfCat());
		inv.setItem(23, Items.infiniteCanteen());

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
			if (e.getCurrentItem().getItemMeta().equals(Items.shark().getItemMeta())) {
				p.closeInventory();
				/*p.sendMessage(
						"§8[§c§lSCP§8] Read the following documents: https://drive.google.com/drive/folders/1OPNuzil70qCLiEy3iFtP2KQHVXfQmzn0?usp=sharing (only pick 1 testing procedure, if there are more than 1 available).");
				p.sendMessage(
						"§8[§c§lSCP§8] §8§oUse §c§o/test start 1057§8§o once you have opened the testing procedure instructions.");*/
				p.sendMessage("§8[§c§lSCP§8] This object can not be tested yet. If you have any ideas for how to test it, contact your superior.");
			} else {
				return;
			}
			break;
		case 21:
			if (e.getCurrentItem().getItemMeta().equals(Items.swatArmor().getItemMeta())) {
				p.closeInventory();
				p.sendMessage(
						"§8[§c§lSCP§8] Read the following documents: https://drive.google.com/drive/folders/1FH8MC0tLQ63YCEj3hGqOVtxVh-j1PFH4?usp=sharing (only pick 1 testing procedure, if there are more than 1 available).");
				p.sendMessage(
						"§8[§c§lSCP§8] §8§oUse §c§o/test start 912§8§o once you have opened the testing procedure instructions.");
			} else {
				return;
			}
			break;
		case 22:
			if (e.getCurrentItem().getItemMeta().equals(Items.halfCat().getItemMeta())) {
				p.closeInventory();
				p.sendMessage(
						"§8[§c§lSCP§8] Read the following documents: https://drive.google.com/drive/folders/1ihubBu0Jd1ufh5R26o3B3J2MSTpTYJZv?usp=sharing (only pick 1 testing procedure, if there are more than 1 available)");
				p.sendMessage(
						"§8[§c§lSCP§8] §8§oUse §c§o/test start 529§8§o once you have opened the testing procedure instructions.");
			} else {
				return;
			}
			break;
		case 23:
			if (e.getCurrentItem().getItemMeta().equals(Items.infiniteCanteen().getItemMeta())) {
				p.closeInventory();
				p.sendMessage(
						"§8[§c§lSCP§8] Read the following documents: https://drive.google.com/drive/folders/1kduysXLmmbRtCwPMIGLcGqXL4uitvZwC?usp=sharing (only pick 1 testing procedure, if there are more than 1 available)");
				p.sendMessage(
						"§8[§c§lSCP§8] §8§oUse §c§o/test start 109§8§o once you have opened the testing procedure instructions.");
			} else {
				return;
			}
			break;
		default:
			break;
		}
	}
}
