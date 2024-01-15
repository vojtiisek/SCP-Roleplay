package cz.vojtiisek.scprpsystem.Storage.GUIs;

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
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;

public class ViewSM implements Listener {
	private static SCPRP main;
	private static String title = "§6§lItems Database";

	static LuckPerms api = LuckPermsProvider.get();
	static String role = "roleerror";
	static String dept = "depterror";
	static int page = 0;

	public ViewSM(SCPRP main) {
		this.main = main;
	}

	public static void openGUI(Player player, String rolestr, String deptstr, int pageint) {
		page = pageint;
		role = rolestr;
		dept = deptstr;
		StorageDatabase.loadFile();
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

		switch (dept) {
		case "medical":
			inv.setItem(20, Items.bandageCountItem());
			inv.setItem(21, Items.firstAidKitCountItem());
			inv.setItem(22, Items.medkitCountItem());
			inv.setItem(23, Items.adrenalineCountItem());
			break;
		case "security":
			break;
		case "scientific":
			if (page == 0) {
				inv.setItem(20, Items.cheeseItemCount());
				inv.setItem(21, Items.woodenPickaxeCount());
				inv.setItem(22, Items.stonePickaxeCount());
				inv.setItem(23, Items.ironAxeCount());
				inv.setItem(24, Items.ironPickaxeCount());
				inv.setItem(29, Items.goldenSwordCount());
				inv.setItem(30, Items.crowbarCount());
				inv.setItem(31, Items.diamondShovelCount());
				inv.setItem(32, Items.animalNetBigCount());
				inv.setItem(33, Items.glassBottleCount());
				inv.setItem(40, Items.nextPageStorage());
			} else {
				inv.setItem(20, Items.emptyBucketCount());
				inv.setItem(40, Items.previousPageStorage());
			}
			break;
		case "other":
			inv.setItem(20, Items.carrotCount(false));
			inv.setItem(21, Items.appleCount(false));
			inv.setItem(22, Items.breadCount(false));
			inv.setItem(23, Items.bakedPotatoCount(false));
			inv.setItem(24, Items.cookedFishCount(false));
			inv.setItem(29, Items.cookedChickenCount(false));
			inv.setItem(30, Items.pPieCount(false));
			inv.setItem(31, Items.cookedPorkchopCount(false));
			inv.setItem(32, Items.steakCount(false));
			inv.setItem(33, Items.rabbitStewCount(false));
			break;
		default:
			break;
		}

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
		case 40:
			switch (dept) {
			case "medical":
				break;
			case "security":
				break;
			case "scientific":
				p.closeInventory();
				if (e.getCurrentItem().getItemMeta().equals(Items.nextPageStorage().getItemMeta())) {
					ViewSM.openGUI(p, LuckPermsProvider.get().getUserManager().getUser(p.getName()).getPrimaryGroup(),
							dept, page + 1);
				} else if (e.getCurrentItem().getItemMeta().equals(Items.previousPageStorage().getItemMeta())) {
					openGUI(p, LuckPermsProvider.get().getUserManager().getUser(p.getName()).getPrimaryGroup(), dept,
							page - 1);
				} else {
					openGUI(p, LuckPermsProvider.get().getUserManager().getUser(p.getName()).getPrimaryGroup(), dept,
							0);
					p.sendMessage(
							"§c§o[§4§l!§c§o] §e§oThere has been an error while trying to switch pages. Please, contact §4§lThe Administrator§e§o, and tell him this code: §f§lViewSM-SCI40");
					API.sendConsoleCommand(
							"discordsrv broadcast #839057785362448424 [ViewSM] Error while switching - Scientific");
					return;
				}
				break;
			default:
				break;
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