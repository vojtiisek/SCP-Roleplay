package cz.vojtiisek.scprpsystem.Storage.GUIs;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

import cz.vojtiisek.scprpsystem.Items;
import cz.vojtiisek.scprpsystem.SCPRP;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.model.user.User;

public class DeptSM implements Listener{
	private static SCPRP main;
	private static String title = "§6§lSelect department";

	static LuckPerms api = LuckPermsProvider.get();
	static String role = "roleerror";
	static String action = "actionerror";

	public DeptSM(SCPRP main) {
		this.main = main;
	}

	public static void openGUI(Player player, String rolestr, String actionstr) {
		role = rolestr;
		action = actionstr;
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
		inv.setItem(53, Items.mainMenuItem());

		inv.setItem(20, Items.medicalDeptItem());
		inv.setItem(22, Items.securityDeptItem());
		inv.setItem(24, Items.scientificDeptItem());
		inv.setItem(43, Items.otherDeptsItem());
		
		player.openInventory(inv);
	}

	@EventHandler
	public void onInvClick(InventoryClickEvent e) {
		Player p = (Player) e.getWhoClicked();
		User user = api.getUserManager().getUser(p.getName());
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
			if (e.getCurrentItem().getItemMeta().equals(Items.medicalDeptItem().getItemMeta())) {
				p.closeInventory();
				switch(action) {
				case "order":
					OrderSM.openGUI(p, role, "medical", 0);
					break;
				case "view":
					ViewSM.openGUI(p, role, "medical", 0);
					break;
				case "withdraw":
					WithdrawSM.openGUI(p, role, "medical", 0);
					break;
				default:
					break;
				}
			} else {
				return;
			}
			break;
		case 22:
			if (e.getCurrentItem().getItemMeta().equals(Items.securityDeptItem().getItemMeta())) {
				p.closeInventory();
				switch(action) {
				case "order":
					OrderSM.openGUI(p, role, "security", 0);
					break;
				case "view":
					ViewSM.openGUI(p, role, "security", 0);
					break;
				case "withdraw":
					WithdrawSM.openGUI(p, role, "security", 0);
					break;
				default:
					break;
				}
			} else {
				return;
			}
			break;
		case 24:
			if (e.getCurrentItem().getItemMeta().equals(Items.scientificDeptItem().getItemMeta())) {
				p.closeInventory();
				switch(action) {
				case "order":
					OrderSM.openGUI(p, role, "scientific", 0);
					break;
				case "view":
					ViewSM.openGUI(p, role, "scientific", 0);
					break;
				case "withdraw":
					WithdrawSM.openGUI(p, role, "scientific", 0);
					break;
				default:
					break;
				}
			} else {
				return;
			}
			break;
		case 43:
			if (e.getCurrentItem().getItemMeta().equals(Items.otherDeptsItem().getItemMeta())) {
				p.closeInventory();
				switch(action) {
				case "order":
					OrderSM.openGUI(p, role, "other", 0);
					break;
				case "view":
					ViewSM.openGUI(p, role, "other", 0);
					break;
				case "withdraw":
					WithdrawSM.openGUI(p, role, "other", 0);
					break;
				default:
					break;
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