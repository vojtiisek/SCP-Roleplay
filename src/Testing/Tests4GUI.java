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

public class Tests4GUI implements Listener {
	private static SCPRP main;
	private static String title = "§9§lTESTS DATABASE §d§l- §c§lL4 §a§oPage 1";

	public Tests4GUI(SCPRP main) {
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
		inv.setItem(50, Items.nextPageTest4());
		inv.setItem(51, Items.sideItem());
		inv.setItem(52, Items.sideItem());
		inv.setItem(53, Items.sideItem());

		inv.setItem(20, Items.shark());
		inv.setItem(21, Items.swatArmor());
		inv.setItem(22, Items.halfCat());
		inv.setItem(23, Items.infiniteCanteen());
		inv.setItem(24, Items.crystallineButterflies());
		inv.setItem(29, Items.electricSheep());
		inv.setItem(30, Items.predatorDrone());
		inv.setItem(31, Items.peanut());
		inv.setItem(32, Items.coffeeMach());
		inv.setItem(33, Items.blackShuck());
		inv.setItem(38, Items.plagueDoc());
		inv.setItem(39, Items.shyGuy());
		inv.setItem(40, Items.bears());
		inv.setItem(41, Items.manyVoices());
		inv.setItem(42, Items.skeletonKey());

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
		case 24:
			if (e.getCurrentItem().getItemMeta().equals(Items.crystallineButterflies().getItemMeta())) {
				p.closeInventory();
				p.sendMessage(
						"§8[§c§lSCP§8] Read the following documents: https://drive.google.com/drive/folders/1J4PUBYy7hIE5AfT5DuyI6NWOPp1Hg97y?usp=sharing (only pick 1 testing procedure, if there are more than 1 available).");
				p.sendMessage(
						"§8[§c§lSCP§8] §8§oUse §c§o/test start 553§8§o once you have opened the testing procedure instructions.");
			} else {
				return;
			}
			break;
		case 29:
			if (e.getCurrentItem().getItemMeta().equals(Items.electricSheep().getItemMeta())) {
				p.closeInventory();
				p.sendMessage(
						"§8[§c§lSCP§8] Read the following documents: https://drive.google.com/drive/folders/1yxy5CYFQBNcIK2uFqwFuGPqvpPf2uwmk?usp=sharing (only pick 1 testing procedure, if there are more than 1 available).");
				p.sendMessage(
						"§8[§c§lSCP§8] §8§oUse §c§o/test start 594§8§o once you have opened the testing procedure instructions.");
			} else {
				return;
			}
			break;
		case 30:
			if (e.getCurrentItem().getItemMeta().equals(Items.predatorDrone().getItemMeta())) {
				p.closeInventory();
				/*p.sendMessage(
						"§8[§c§lSCP§8] Read the following documents: . Only pick 1 testing procedure, if there are more than 1 available.");
				p.sendMessage(
						"§8[§c§lSCP§8] §8§oUse §c§o/test start 160§8§o once you have opened the testing procedure instructions.");*/
				p.sendMessage("§8[§c§lSCP§8] This object can not be tested yet. If you have any ideas for how to test it, contact your superior.");
			} else {
				return;
			}
			break;
		case 31:
			if (e.getCurrentItem().getItemMeta().equals(Items.peanut().getItemMeta())) {
				p.closeInventory();
				p.sendMessage(
						"§8[§c§lSCP§8] Read the following documents: https://drive.google.com/drive/folders/1mDXtQyCkt4d-WH7zwnz6MhORL1n3wgBy?usp=sharing (only pick 1 testing procedure, if there are more than 1 available.)");
				p.sendMessage(
						"§8[§c§lSCP§8] §8§oUse §c§o/test start 173§8§o once you have opened the testing procedure instructions.");
			} else {
				return;
			}
			break;
		case 32:
			if (e.getCurrentItem().getItemMeta().equals(Items.coffeeMach().getItemMeta())) {
				p.closeInventory();
				p.sendMessage(
						"§8[§c§lSCP§8] Read the following documents: https://drive.google.com/drive/folders/1xZjjS_1KPsCsUm5rZAHnOBDUkBPj3Z5Y?usp=sharing (only pick 1 testing procedure, if there are more than 1 available.)");
				p.sendMessage(
						"§8[§c§lSCP§8] §8§oUse §c§o/test start 294§8§o once you have opened the testing procedure instructions.");
			} else {
				return;
			}
			break;
		case 33:
			if (e.getCurrentItem().getItemMeta().equals(Items.blackShuck().getItemMeta())) {
				p.closeInventory();
				p.sendMessage(
						"§8[§c§lSCP§8] Read the following documents: https://drive.google.com/drive/folders/1qO7Tw5mR_yAK7AbnQLeATyn9er9RBeRh?usp=sharing (only pick 1 testing procedure, if there are more than 1 available.)");
				p.sendMessage(
						"§8[§c§lSCP§8] §8§oUse §c§o/test start 023§8§o once you have opened the testing procedure instructions.");
			} else {
				return;
			}
			break;
		case 38:
			if (e.getCurrentItem().getItemMeta().equals(Items.plagueDoc().getItemMeta())) {
				p.closeInventory();
				p.sendMessage(
						"§8[§c§lSCP§8] Read the following documents: https://drive.google.com/drive/folders/1Z8ILdroCS-ahDg1Ni8F9K4qQO79w5HES?usp=sharing (only pick 1 testing procedure, if there are more than 1 available.)");
				p.sendMessage(
						"§8[§c§lSCP§8] §8§oUse §c§o/test start 049§8§o once you have opened the testing procedure instructions.");
			} else {
				return;
			}
			break;
		case 39:
			if (e.getCurrentItem().getItemMeta().equals(Items.shyGuy().getItemMeta())) {
				p.closeInventory();
				p.sendMessage(
						"§8[§c§lSCP§8] Read the following documents: https://drive.google.com/drive/folders/1yx8XchnxgXdPh0fksgdxBycMXCLyfV5A?usp=sharing (only pick 1 testing procedure, if there are more than 1 available.)");
				p.sendMessage(
						"§8[§c§lSCP§8] §8§oUse §c§o/test start 096§8§o once you have opened the testing procedure instructions.");
			} else {
				return;
			}
			break;
		case 40:
			if (e.getCurrentItem().getItemMeta().equals(Items.bears().getItemMeta())) {
				p.closeInventory();
				p.sendMessage(
						"§8[§c§lSCP§8] Read the following documents: https://drive.google.com/drive/folders/1xnn3q85OYDnu8xtZmcpDBXKEmLU35Tp9?usp=sharing (only pick 1 testing procedure, if there are more than 1 available.)");
				p.sendMessage(
						"§8[§c§lSCP§8] §8§oUse §c§o/test start 1048§8§o once you have opened the testing procedure instructions.");
			} else {
				return;
			}
			break;
		case 41:
			if (e.getCurrentItem().getItemMeta().equals(Items.manyVoices().getItemMeta())) {
				p.closeInventory();
				/*p.sendMessage(
						"§8[§c§lSCP§8] Read the following documents:  (only pick 1 testing procedure, if there are more than 1 available.)");
				p.sendMessage(
						"§8[§c§lSCP§8] §8§oUse §c§o/test start 939§8§o once you have opened the testing procedure instructions.");*/
				p.sendMessage("§8[§c§lSCP§8] This object can not be tested yet. If you have any ideas for how to test it, contact your superior.");
			} else {
				return;
			}
			break;
		case 42:
			if (e.getCurrentItem().getItemMeta().equals(Items.skeletonKey().getItemMeta())) {
				p.closeInventory();
				p.sendMessage(
						"§8[§c§lSCP§8] Read the following documents: https://drive.google.com/drive/folders/1bmZAHpEe-PaGh-qlxW2wO3j5EEXE01F6?usp=sharing (only pick 1 testing procedure, if there are more than 1 available.)");
				p.sendMessage(
						"§8[§c§lSCP§8] §8§oUse §c§o/test start 005§8§o once you have opened the testing procedure instructions.");
			} else {
				return;
			}
			break;
		case 50:
			if (e.getCurrentItem().getItemMeta().equals(Items.nextPageTest4().getItemMeta())) {
				p.closeInventory();
				Tests4GUI2.openGUI(p);
			} else {
				return;
			}
			break;
		default:
			break;
		}
	}
}