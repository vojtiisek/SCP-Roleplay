package cz.vojtiisek.scprpsystem.Storage.GUIs;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

import cz.vojtiisek.scprpsystem.API;
import cz.vojtiisek.scprpsystem.Items;
import cz.vojtiisek.scprpsystem.SCPRP;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.model.user.User;

public class MainSM implements Listener {
	private static SCPRP main;
	private static String title = "§6§lSelect operation";
	static LuckPerms api = LuckPermsProvider.get();

	public MainSM(SCPRP main) {
		this.main = main;
	}

	public static void openGUI(Player player, String role) {
		Inventory inv = Bukkit.createInventory(null, 54, title);
		inv.setItem(0, Items.sideItemYellow());
		inv.setItem(1, Items.sideItemYellow());
		inv.setItem(2, Items.sideItemYellow());
		inv.setItem(3, Items.sideItemYellow());
		inv.setItem(4, Items.sideItemYellow());
		inv.setItem(5, Items.sideItemYellow());
		inv.setItem(6, Items.sideItemYellow());
		inv.setItem(7, Items.sideItemYellow());
		inv.setItem(8, Items.sideItemYellow());
		inv.setItem(9, Items.sideItemYellow());
		inv.setItem(18, Items.sideItemYellow());
		inv.setItem(27, Items.sideItemYellow());
		inv.setItem(36, Items.sideItemYellow());
		inv.setItem(45, Items.sideItemYellow());
		inv.setItem(17, Items.sideItemYellow());
		inv.setItem(26, Items.sideItemYellow());
		inv.setItem(35, Items.sideItemYellow());
		inv.setItem(44, Items.sideItemYellow());
		inv.setItem(45, Items.sideItemYellow());
		inv.setItem(46, Items.sideItemYellow());
		inv.setItem(47, Items.sideItemYellow());
		inv.setItem(48, Items.sideItemYellow());
		inv.setItem(49, Items.sideItemYellow());
		inv.setItem(50, Items.sideItemYellow());
		inv.setItem(51, Items.sideItemYellow());
		inv.setItem(52, Items.sideItemYellow());
		inv.setItem(53, Items.sideItemYellow());
//20 - Order, 22 - View items on stock, 24 - Withdraw, (33 - Return items)
		if (role.equalsIgnoreCase("site-director") || player.hasPermission("scprp.storage.order")) {
			inv.setItem(20, Items.addOrderItem());
		} else {
			inv.setItem(20, Items.noPermissionOrderItem());
		}
		inv.setItem(22, Items.viewItemsItem());
		inv.setItem(24, Items.withdrawItem());

		player.openInventory(inv);
	}

	@EventHandler
	public void onInvClick(InventoryClickEvent e) {
		Player p = (Player) e.getWhoClicked();
		User user = api.getUserManager().getUser(p.getName());
		String role = user.getPrimaryGroup().toString();
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
			if (role.equalsIgnoreCase("site-director") || role.equalsIgnoreCase("storage-leader") ||  p.hasPermission("scprp.storage.order")) {
				if (e.getCurrentItem().getItemMeta().equals(Items.addOrderItem().getItemMeta())) {
					p.closeInventory();
					DeptSM.openGUI(p, role, "order");
				} else {
					return;
				}
			}
			break;
		case 22:
			if (e.getCurrentItem().getItemMeta().equals(Items.viewItemsItem().getItemMeta())) {
				p.closeInventory();
				DeptSM.openGUI(p, role, "view");
			} else {
				return;
			}
			break;
		case 24:
			Location withdrawMachLoc = new Location(e.getWhoClicked().getLocation().getWorld(), -94, 204, 69);
			if (e.getCurrentItem().getItemMeta().equals(Items.withdrawItem().getItemMeta())) {
				p.closeInventory();
				double radius = 5D;
				if (p.getLocation()
						.distance(withdrawMachLoc) <= radius) {
					DeptSM.openGUI(p, role, "withdraw");
				} else {
					p.sendMessage("§8[§c§l!§8] §c§oYou need to be closer to the §6§oWithdraw machine§c§o.");
				}

			} else {
				return;
			}
			break;
		case 53:
			p.closeInventory();
			MainSM.openGUI(p, role);
			break;
		default:
			break;
		}

	}
}