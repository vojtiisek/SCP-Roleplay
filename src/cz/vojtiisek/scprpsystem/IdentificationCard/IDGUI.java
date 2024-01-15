package cz.vojtiisek.scprpsystem.IdentificationCard;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

import cz.vojtiisek.scprpsystem.API;
import cz.vojtiisek.scprpsystem.Items;
import cz.vojtiisek.scprpsystem.SCPRP;
import cz.vojtiisek.scprpsystem.CellSystem.Cell;
import cz.vojtiisek.scprpsystem.CellSystem.CellSystem;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;

public class IDGUI implements Listener {
	private static SCPRP main;
	public static LuckPerms api;
	private static String title = "§b§lID Card: §c§l";
	static String name;

	public IDGUI(SCPRP main) {
		this.main = main;
	}

	public static void openGUI(Player player, String user) {
		Inventory inv = Bukkit.createInventory(null, 54, title + user);
		name = user;
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

		inv.setItem(13, Items.playerItem(user));
		if (api == null) {
			inv.setItem(19, Items.rolesItem(user, LuckPermsProvider.get().getUserManager().getUser(user)));
		} else {
			inv.setItem(19, Items.rolesItem(user, api.getUserManager().getUser(user)));
		}

		inv.setItem(21, Items.timeStatsItem(user));
		inv.setItem(23, Items.currentShift(user));
		inv.setItem(28, Items.naufixRankItem(user));
		inv.setItem(30, Items.desigItem(user));
		inv.setItem(39, Items.cellItem(user));
		player.openInventory(inv);
	}

	@EventHandler
	public void onInvClick(InventoryClickEvent e) {
		Player p = (Player) e.getWhoClicked();
		if (!(e.getInventory().getName().equals(title + name)))
			return;
		if (e.getCurrentItem() == null || e.getCurrentItem().getType().equals(Material.AIR)
				|| !e.getCurrentItem().hasItemMeta()) {
			e.setCancelled(true);
			return;
		}

		e.setCancelled(true);

		switch (e.getSlot()) {
		case 39:
			if (API.findCellIdByPname(name) != "error") {
				Cell cell;
				switch (API.findCellLevelById(API.findCellIdByPname(name))) {
				case "minimum":
					cell = CellSystem.getMinimumCells().get(API.findCellIdByPname(name));
					p.sendMessage("§8[§5§lDb§8] §a§oCell data retrieved successfully:");
					p.sendMessage("§8 - §6§oID: §9§l" + API.findCellIdByPname(name));
					p.sendMessage("§8 - §6§oX, Y, Z: §9§l" + cell.getX() + "§6§o, §9§l" + cell.getY() + "§6§o, §9§l"
							+ cell.getZ());
					p.sendMessage("§8 - §6§oWorld: §9§l" + cell.getWorld());
					p.sendMessage("§8 - §6§oSecurity risk: §f§lMinimum");
					if (cell.getOwner().equalsIgnoreCase("NoOwnerYetNoOwnerYet")) {
						p.sendMessage("§8 - §c§oCell not assigned to any §6§oClass-D§c§o.");
					} else {
						p.sendMessage("§8 - §6§oAssigned Class-D (owner): §9§l" + cell.getOwner());
					}
					break;
				case "medium":
					cell = CellSystem.getMediumCells().get(API.findCellIdByPname(name));
					p.sendMessage("§8[§5§lDb§8] §a§oCell data retrieved successfully:");
					p.sendMessage("§8 - §6§oID: §9§l" + API.findCellIdByPname(name));
					p.sendMessage("§8 - §6§oX, Y, Z: §9§l" + cell.getX() + "§6§o, §9§l" + cell.getY() + "§6§o, §9§l"
							+ cell.getZ());
					p.sendMessage("§8 - §6§oWorld: §9§l" + cell.getWorld());
					p.sendMessage("§8 - §6§oSecurity risk: §6§lMedium");
					if (cell.getOwner().equalsIgnoreCase("NoOwnerYetNoOwnerYet")) {
						p.sendMessage("§8 - §c§oCell not assigned to any §6§oClass-D§c§o.");
					} else {
						p.sendMessage("§8 - §6§oAssigned Class-D (owner): §9§l" + cell.getOwner());
					}
					break;
				case "maximum":
					cell = CellSystem.getMaximumCells().get(API.findCellIdByPname(name));
					p.sendMessage("§8[§5§lDb§8] §a§oCell data retrieved successfully:");
					p.sendMessage("§8 - §6§oID: §9§l" + API.findCellIdByPname(name));
					p.sendMessage("§8 - §6§oX, Y, Z: §9§l" + cell.getX() + "§6§o, §9§l" + cell.getY() + "§6§o, §9§l"
							+ cell.getZ());
					p.sendMessage("§8 - §6§oWorld: §9§l" + cell.getWorld());
					p.sendMessage("§8 - §6§oSecurity risk: §4§lMaximum");
					if (cell.getOwner().equalsIgnoreCase("NoOwnerYetNoOwnerYet")) {
						p.sendMessage("§8 - §c§oCell not assigned to any §6§oClass-D§c§o.");
					} else {
						p.sendMessage("§8 - §6§oAssigned Class-D (owner): §9§l" + cell.getOwner());
					}
					break;
				default:
					break;
				}
				break;
			}
		default:
			break;
		}
	}
}