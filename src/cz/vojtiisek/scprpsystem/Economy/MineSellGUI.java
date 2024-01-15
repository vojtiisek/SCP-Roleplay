package cz.vojtiisek.scprpsystem.Economy;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import cz.vojtiisek.scprpsystem.API;
import cz.vojtiisek.scprpsystem.Items;
import cz.vojtiisek.scprpsystem.SCPRP;
import cz.vojtiisek.scprpsystem.CellSystem.Cell;
import cz.vojtiisek.scprpsystem.CellSystem.CellSystem;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;

public class MineSellGUI implements Listener {
	private static SCPRP main;
	public static LuckPerms api;
	private static String title = "§7§lExchange ores";
	static String name;

	public MineSellGUI(SCPRP main) {
		this.main = main;
	}

	public static void openGUI(Player player) {
		Inventory inv = Bukkit.createInventory(null, 54, title);
		inv.setItem(0, Items.sideItemBlue());
		inv.setItem(1, Items.sideItemBlue());
		inv.setItem(2, Items.sideItemBlue());
		inv.setItem(3, Items.sideItemBlue());
		inv.setItem(4, Items.sideItemBlue());
		inv.setItem(5, Items.sideItemBlue());
		inv.setItem(6, Items.sideItemBlue());
		inv.setItem(7, Items.sideItemBlue());
		inv.setItem(8, Items.sideItemBlue());
		inv.setItem(9, Items.sideItemBlue());
		inv.setItem(18, Items.sideItemBlue());
		inv.setItem(27, Items.sideItemBlue());
		inv.setItem(36, Items.sideItemBlue());
		inv.setItem(45, Items.sideItemBlue());
		inv.setItem(17, Items.sideItemBlue());
		inv.setItem(26, Items.sideItemBlue());
		inv.setItem(35, Items.sideItemBlue());
		inv.setItem(44, Items.sideItemBlue());
		inv.setItem(45, Items.sideItemBlue());
		inv.setItem(46, Items.sideItemBlue());
		inv.setItem(47, Items.sideItemBlue());
		inv.setItem(48, Items.sideItemBlue());
		inv.setItem(49, Items.sideItemBlue());
		inv.setItem(50, Items.sideItemBlue());
		inv.setItem(51, Items.sideItemBlue());
		inv.setItem(52, Items.sideItemBlue());
		inv.setItem(53, Items.sideItemBlue());

		//29 - coal
		//30 - iron ingot
		//31 - diamond
		//32 - emerald
		//33 - open mine buy menu 
		inv.setItem(29, Items.coalMineItem());
		inv.setItem(30, Items.ironMineItem());
		inv.setItem(31, Items.diamondMineItem());
		inv.setItem(32, Items.emeraldMineItem());
		inv.setItem(33, Items.buyMineMenuItem());
		
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
		case 29:
			int coalFound = 0;
			for(int i = 0; i<p.getInventory().getContents().length; i++) {
				if(p.getInventory().getItem(i).getType().equals(e.getCurrentItem().getType())) {
					coalFound = coalFound + 1;
				}
			}
			
			if(e.getClick().equals(ClickType.LEFT)) {
				for(ItemStack iS : p.getInventory().getContents()) {
					if(iS.getType().equals(e.getCurrentItem().getType())) {
						p.getInventory().remove(iS);
					}
				}
			} 
			
			if(e.getClick().equals(ClickType.RIGHT)) {
				for(ItemStack iS : p.getInventory().getContents()) {
					if(iS.getType().equals(e.getCurrentItem().getType())) {
						p.getInventory().remove(iS);
					}
				}
			} 
			
			if(coalFound == 0) {
				p.sendMessage("§8§l[§4§l!§8§l] §c§oYou do not have any coal to sell.");
			}
			break;
		case 30:
			break;
		case 31:
			break;
		case 32:
			break;
		case 33:
			break;
		default:
			break;
		}
	}
}