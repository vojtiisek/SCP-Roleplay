package cz.vojtiisek.scprpsystem.Tasks;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;

import Levelling.LevellingSys;
import cz.vojtiisek.scprpsystem.API;
import cz.vojtiisek.scprpsystem.Items;
import cz.vojtiisek.scprpsystem.SCPRP;
import cz.vojtiisek.scprpsystem.CellSystem.Cell;
import cz.vojtiisek.scprpsystem.CellSystem.CellSystem;
import cz.vojtiisek.scprpsystem.Constructions.ConstructionsMenu;
import net.luckperms.api.LuckPerms;

public class TasksMenu implements Listener {
	private static SCPRP main;
	public static LuckPerms api;
	private static String title = "§b§lSelect your shift";
	static String name;
	static String roleStatic;
	static int levelStatic;
	static boolean selected = false;

	public TasksMenu(SCPRP main) {
		this.main = main;
	}

	public static void openGUI(String user, String role, int level) {
		Player player = Bukkit.getPlayerExact(user);
		Inventory inv = Bukkit.createInventory(null, 54, title);
		name = user;
		roleStatic = role;
		levelStatic = level;
		selected = false;
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
		if (role.equals("security-officer")) {
			switch (level) {
			case 1:
				inv.setItem(20, Items.ReceptionTaskBlock());
				inv.setItem(21, Items.S2TaskBlock());
				inv.setItem(22, Items.MinClassDTaskBlock());
				inv.setItem(23, Items.ScientificTaskBlock());
				break;
			case 2:
				inv.setItem(20, Items.ReceptionTaskBlock());
				inv.setItem(21, Items.S2TaskBlock());
				inv.setItem(22, Items.MinClassDTaskBlock());
				inv.setItem(23, Items.ScientificTaskBlock());
				inv.setItem(24, Items.MedClassDTaskBlock());
				inv.setItem(29, Items.S3TaskBlock());
				inv.setItem(30, Items.ETSTaskBlock());
				break;
			case 3:
				inv.setItem(20, Items.ReceptionTaskBlock());
				inv.setItem(21, Items.S2TaskBlock());
				inv.setItem(22, Items.MinClassDTaskBlock());
				inv.setItem(23, Items.ScientificTaskBlock());
				inv.setItem(24, Items.MedClassDTaskBlock());
				inv.setItem(29, Items.S3TaskBlock());
				inv.setItem(30, Items.ETSTaskBlock());
				inv.setItem(31, Items.MaxClassDTaskBlock());
				inv.setItem(32, Items.HCZTaskBlock());
				break;
			case 4:
				inv.setItem(20, Items.ReceptionTaskBlock());
				inv.setItem(21, Items.S2TaskBlock());
				inv.setItem(22, Items.MinClassDTaskBlock());
				inv.setItem(23, Items.ScientificTaskBlock());
				inv.setItem(24, Items.MedClassDTaskBlock());
				inv.setItem(29, Items.S3TaskBlock());
				inv.setItem(30, Items.ETSTaskBlock());
				inv.setItem(31, Items.MaxClassDTaskBlock());
				inv.setItem(32, Items.HCZTaskBlock());
				inv.setItem(33, Items.S5TaskBlock());
				break;
			default:
				player.sendMessage(
						"§8[§4§l!§8] §c§lThere was an error when generating tasks. Please, contact any Mod, Admin, or any L4 personnel and higher.");
				API.sendConsoleCommand(
						"discordsrv broadcast #839057785362448424 [TasksMenu] openGUI(String user, String role, int level) default");
				break;
			}
			player.openInventory(inv);
		} else if (role.equals("technic")) {
			ConstructionsMenu.openGUI(player, role);
		}

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
			if (e.getCurrentItem().getItemMeta().equals(Items.ReceptionTaskBlock().getItemMeta())) {
				TasksSys.setSelectedShift(p.getName(), "§f§lReception & Medical");
				selected = true;
				p.closeInventory();
			} else {
				return;
			}
			break;
		case 21:
			if (e.getCurrentItem().getItemMeta().equals(Items.S2TaskBlock().getItemMeta())) {
				TasksSys.setSelectedShift(p.getName(), "§a§lSector 2");
				selected = true;
				p.closeInventory();
			} else {
				return;
			}
			break;
		case 22:
			if (e.getCurrentItem().getItemMeta().equals(Items.MinClassDTaskBlock().getItemMeta())) {
				TasksSys.setSelectedShift(p.getName(), "§a§lMinimum Class-D Cells");
				selected = true;
				p.closeInventory();
			} else {
				return;
			}
			break;
		case 23:
			if (e.getCurrentItem().getItemMeta().equals(Items.ScientificTaskBlock().getItemMeta())) {
				TasksSys.setSelectedShift(p.getName(), "§b§lScientific Department");
				selected = true;
				p.closeInventory();
			} else {
				return;
			}
			break;
		case 24:
			if (e.getCurrentItem().getItemMeta().equals(Items.MedClassDTaskBlock().getItemMeta())) {
				TasksSys.setSelectedShift(p.getName(), "§e§lMedium Class-D cells");
				selected = true;
				p.closeInventory();
			} else {
				return;
			}
			break;
		case 29:
			if (e.getCurrentItem().getItemMeta().equals(Items.S3TaskBlock().getItemMeta())) {
				TasksSys.setSelectedShift(p.getName(), "§7§lSector 3 + LCZ");
				selected = true;
				p.closeInventory();
			} else {
				return;
			}
			break;
		case 30:
			if (e.getCurrentItem().getItemMeta().equals(Items.ETSTaskBlock().getItemMeta())) {
				TasksSys.setSelectedShift(p.getName(), "§6§lE&Ts Department");
				selected = true;
				p.closeInventory();
			} else {
				return;
			}
			break;
		case 31:
			if (e.getCurrentItem().getItemMeta().equals(Items.MaxClassDTaskBlock().getItemMeta())) {
				TasksSys.setSelectedShift(p.getName(), "§4§lMaximum Class-D cells");
				selected = true;
				p.closeInventory();
			} else {
				return;
			}
			break;
		case 32:
			if (e.getCurrentItem().getItemMeta().equals(Items.HCZTaskBlock().getItemMeta())) {
				TasksSys.setSelectedShift(p.getName(), "§c§lSector 4 + HCZ");
				selected = true;
				p.closeInventory();
			} else {
				return;
			}
			break;
		case 33:
			if (e.getCurrentItem().getItemMeta().equals(Items.S5TaskBlock().getItemMeta())) {
				TasksSys.setSelectedShift(p.getName(), "§4§lSector 5");
				selected = true;
				p.closeInventory();
			} else {
				return;
			}
			break;
		default:
			break;
		}
	}

	@EventHandler
	public void onClose(InventoryCloseEvent ev) {
		if (ev.getInventory().getName().equals(title) && selected == false) {
			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(SCPRP.getInstace(),
					() -> TasksMenu.openGUI(name, roleStatic, levelStatic), 10);

			ev.getPlayer().getWorld().playSound(ev.getPlayer().getLocation(), Sound.BLOCK_ANVIL_BREAK, 2.0F, 1.0F);
			ev.getPlayer().sendMessage("§8§l[§c§l!§8§l] §c§oYou must select a shift in order to continue.");
		}
	}
}