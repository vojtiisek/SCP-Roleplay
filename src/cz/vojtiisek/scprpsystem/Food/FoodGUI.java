package cz.vojtiisek.scprpsystem.Food;

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
import cz.vojtiisek.scprpsystem.files.PlayerInfoFile;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.model.user.User;

public class FoodGUI implements Listener {
	private static SCPRP main;
	public static LuckPerms api;
	private static String title = "§6§lFood Selector";

	public FoodGUI(SCPRP main) {
		this.main = main;
	}

	public static void openGUI(Player player, User user) {
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

		if (user.getPrimaryGroup().equals("class-d") || user.getPrimaryGroup().equals("default")) {
			inv.setItem(20, Items.carrotCount(true));
			inv.setItem(21, Items.appleCount(true));
			inv.setItem(22, Items.breadCount(true));
			inv.setItem(23, Items.bakedPotatoCount(true));
			inv.setItem(24, Items.cookedFishCount(true));
		} else {
			inv.setItem(20, Items.carrotCount(true));
			inv.setItem(21, Items.appleCount(true));
			inv.setItem(22, Items.breadCount(true));
			inv.setItem(23, Items.bakedPotatoCount(true));
			inv.setItem(24, Items.cookedFishCount(true));
			inv.setItem(29, Items.cookedChickenCount(true));
			inv.setItem(30, Items.pPieCount(true));
			inv.setItem(31, Items.cookedPorkchopCount(true));
			inv.setItem(32, Items.steakCount(true));
			inv.setItem(33, Items.rabbitStewCount(true));
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
		case 20:
			if (e.getCurrentItem().getItemMeta().equals(Items.carrotCount(true).getItemMeta())) {
				if (e.getClick().isRightClick()) {
					PlayerInfoFile.getPlayerInfoFile().getConfig().set("Players." + p.getName() + ".PreferredFood",
							"Carrot");
					p.sendMessage(
							"§8§l[§a§l!§8§l] §a§oYour favorite food set. From now on, you will get this food type without the need to select it again. §e§oIf you wish to change it, use the §c§l/food§e§o command.");
					p.closeInventory();
				}

				if (e.getClick().isLeftClick()) {
					if (PlayerInfoFile.getPlayerInfoFile().getConfig()
							.getString("Players." + p.getName() + ".PreferredFood") != null
							&& PlayerInfoFile.getPlayerInfoFile().getConfig()
									.getString("Players." + p.getName() + ".PreferredFood") != "Unselected") {
						PlayerInfoFile.getPlayerInfoFile().getConfig().set("Players." + p.getName() + ".PreferredFood",
								"Unselected");
						PlayerInfoFile.getPlayerInfoFile().save();
						p.sendMessage("§8§l[§a§l!§8§l] §c§oUnselected§a§o your preferred food.");
					}
					FoodAPI.getNextFood().put(p.getName(), "Carrot");
					p.sendMessage("§8§l[§a§l!§8§l] §a§oYour next food set.");
				}
			} else {
				return;
			}
		case 21:
			if (e.getCurrentItem().getItemMeta().equals(Items.appleCount(true).getItemMeta())) {
				if (e.getClick().isRightClick()) {
					PlayerInfoFile.getPlayerInfoFile().getConfig().set("Players." + p.getName() + ".PreferredFood",
							"Apple");
					p.sendMessage(
							"§8§l[§a§l!§8§l] §a§oYour favorite food set. From now on, you will get this food type without the need to select it again. §e§oIf you wish to change it, use the §c§l/food§e§o command.");
					p.closeInventory();
				}

				if (e.getClick().isLeftClick()) {
					if (PlayerInfoFile.getPlayerInfoFile().getConfig()
							.getString("Players." + p.getName() + ".PreferredFood") != null
							&& PlayerInfoFile.getPlayerInfoFile().getConfig()
									.getString("Players." + p.getName() + ".PreferredFood") != "Unselected") {
						PlayerInfoFile.getPlayerInfoFile().getConfig().set("Players." + p.getName() + ".PreferredFood",
								"Unselected");
											PlayerInfoFile.getPlayerInfoFile().save();					PlayerInfoFile.getPlayerInfoFile().save();
						p.sendMessage("§8§l[§a§l!§8§l] §c§oUnselected§a§o your preferred food.");
					}
					FoodAPI.getNextFood().put(p.getName(), "Apple");
					p.sendMessage("§8§l[§a§l!§8§l] §a§oYour next food set.");
				}
			} else {
				return;
			}
		case 22:
			if (e.getCurrentItem().getItemMeta().equals(Items.breadCount(true).getItemMeta())) {
				if (e.getClick().isRightClick()) {
					PlayerInfoFile.getPlayerInfoFile().getConfig().set("Players." + p.getName() + ".PreferredFood",
							"Bread");
					p.sendMessage(
							"§8§l[§a§l!§8§l] §a§oYour favorite food set. From now on, you will get this food type without the need to select it again. §e§oIf you wish to change it, use the §c§l/food§e§o command.");
					p.closeInventory();
				}

				if (e.getClick().isLeftClick()) {
					if (PlayerInfoFile.getPlayerInfoFile().getConfig()
							.getString("Players." + p.getName() + ".PreferredFood") != null
							&& PlayerInfoFile.getPlayerInfoFile().getConfig()
									.getString("Players." + p.getName() + ".PreferredFood") != "Unselected") {
						PlayerInfoFile.getPlayerInfoFile().getConfig().set("Players." + p.getName() + ".PreferredFood",
								"Unselected");
											PlayerInfoFile.getPlayerInfoFile().save();					PlayerInfoFile.getPlayerInfoFile().save();
						p.sendMessage("§8§l[§a§l!§8§l] §c§oUnselected§a§o your preferred food.");
					}
					FoodAPI.getNextFood().put(p.getName(), "Bread");
					p.sendMessage("§8§l[§a§l!§8§l] §a§oYour next food set.");
				}
			} else {
				return;
			}
		case 23:
			if (e.getCurrentItem().getItemMeta().equals(Items.bakedPotatoCount(true).getItemMeta())) {
				if (e.getClick().isRightClick()) {
					PlayerInfoFile.getPlayerInfoFile().getConfig().set("Players." + p.getName() + ".PreferredFood",
							"BakedPotato");
					p.sendMessage(
							"§8§l[§a§l!§8§l] §a§oYour favorite food set. From now on, you will get this food type without the need to select it again. §e§oIf you wish to change it, use the §c§l/food§e§o command.");
					p.closeInventory();
				}

				if (e.getClick().isLeftClick()) {
					if (PlayerInfoFile.getPlayerInfoFile().getConfig()
							.getString("Players." + p.getName() + ".PreferredFood") != null
							&& PlayerInfoFile.getPlayerInfoFile().getConfig()
									.getString("Players." + p.getName() + ".PreferredFood") != "Unselected") {
						PlayerInfoFile.getPlayerInfoFile().getConfig().set("Players." + p.getName() + ".PreferredFood",
								"Unselected");
											PlayerInfoFile.getPlayerInfoFile().save();					PlayerInfoFile.getPlayerInfoFile().save();
						p.sendMessage("§8§l[§a§l!§8§l] §c§oUnselected§a§o your preferred food.");
					}
					FoodAPI.getNextFood().put(p.getName(), "BakedPotato");
					p.sendMessage("§8§l[§a§l!§8§l] §a§oYour next food set.");
				}
			} else {
				return;
			}
		case 24:
			if (e.getCurrentItem().getItemMeta().equals(Items.cookedFishCount(true).getItemMeta())) {
				if (e.getClick().isRightClick()) {
					PlayerInfoFile.getPlayerInfoFile().getConfig().set("Players." + p.getName() + ".PreferredFood",
							"CookedFish");
					p.sendMessage(
							"§8§l[§a§l!§8§l] §a§oYour favorite food set. From now on, you will get this food type without the need to select it again. §e§oIf you wish to change it, use the §c§l/food§e§o command.");
					p.closeInventory();
				}

				if (e.getClick().isLeftClick()) {
					if (PlayerInfoFile.getPlayerInfoFile().getConfig()
							.getString("Players." + p.getName() + ".PreferredFood") != null
							&& PlayerInfoFile.getPlayerInfoFile().getConfig()
									.getString("Players." + p.getName() + ".PreferredFood") != "Unselected") {
						PlayerInfoFile.getPlayerInfoFile().getConfig().set("Players." + p.getName() + ".PreferredFood",
								"Unselected");
											PlayerInfoFile.getPlayerInfoFile().save();					PlayerInfoFile.getPlayerInfoFile().save();
						p.sendMessage("§8§l[§a§l!§8§l] §c§oUnselected§a§o your preferred food.");
					}
					FoodAPI.getNextFood().put(p.getName(), "CookedFish");
					p.sendMessage("§8§l[§a§l!§8§l] §a§oYour next food set.");
				}
			} else {
				return;
			}
		case 29:
			if (e.getCurrentItem().getItemMeta().equals(Items.cookedChickenCount(true).getItemMeta())) {
				if (e.getClick().isRightClick()) {
					PlayerInfoFile.getPlayerInfoFile().getConfig().set("Players." + p.getName() + ".PreferredFood",
							"CookedChicken");
					p.sendMessage(
							"§8§l[§a§l!§8§l] §a§oYour favorite food set. From now on, you will get this food type without the need to select it again. §e§oIf you wish to change it, use the §c§l/food§e§o command.");
					p.closeInventory();
				}

				if (e.getClick().isLeftClick()) {
					if (PlayerInfoFile.getPlayerInfoFile().getConfig()
							.getString("Players." + p.getName() + ".PreferredFood") != null
							&& PlayerInfoFile.getPlayerInfoFile().getConfig()
									.getString("Players." + p.getName() + ".PreferredFood") != "Unselected") {
						PlayerInfoFile.getPlayerInfoFile().getConfig().set("Players." + p.getName() + ".PreferredFood",
								"Unselected");
											PlayerInfoFile.getPlayerInfoFile().save();					PlayerInfoFile.getPlayerInfoFile().save();
						p.sendMessage("§8§l[§a§l!§8§l] §c§oUnselected§a§o your preferred food.");
					}
					FoodAPI.getNextFood().put(p.getName(), "CookedChicken");
					p.sendMessage("§8§l[§a§l!§8§l] §a§oYour next food set.");
				}
			} else {
				return;
			}
			break;
		case 30:
			if (e.getCurrentItem().getItemMeta().equals(Items.pPieCount(true).getItemMeta())) {
				if (e.getClick().isRightClick()) {
					PlayerInfoFile.getPlayerInfoFile().getConfig().set("Players." + p.getName() + ".PreferredFood",
							"PumpkinPie");
					p.sendMessage(
							"§8§l[§a§l!§8§l] §a§oYour favorite food set. From now on, you will get this food type without the need to select it again. §e§oIf you wish to change it, use the §c§l/food§e§o command.");
					p.closeInventory();
				}

				if (e.getClick().isLeftClick()) {
					if (PlayerInfoFile.getPlayerInfoFile().getConfig()
							.getString("Players." + p.getName() + ".PreferredFood") != null
							&& PlayerInfoFile.getPlayerInfoFile().getConfig()
									.getString("Players." + p.getName() + ".PreferredFood") != "Unselected") {
						PlayerInfoFile.getPlayerInfoFile().getConfig().set("Players." + p.getName() + ".PreferredFood",
								"Unselected");
											PlayerInfoFile.getPlayerInfoFile().save();					PlayerInfoFile.getPlayerInfoFile().save();
						p.sendMessage("§8§l[§a§l!§8§l] §c§oUnselected§a§o your preferred food.");
					}
					FoodAPI.getNextFood().put(p.getName(), "PumpkinPie");
					p.sendMessage("§8§l[§a§l!§8§l] §a§oYour next food set.");
				}
			} else {
				return;
			}
			break;
		case 31:
			if (e.getCurrentItem().getItemMeta().equals(Items.cookedPorkchopCount(true).getItemMeta())) {
				if (e.getClick().isRightClick()) {
					PlayerInfoFile.getPlayerInfoFile().getConfig().set("Players." + p.getName() + ".PreferredFood",
							"CookPork");
					p.sendMessage(
							"§8§l[§a§l!§8§l] §a§oYour favorite food set. From now on, you will get this food type without the need to select it again. §e§oIf you wish to change it, use the §c§l/food§e§o command.");
					p.closeInventory();
				}

				if (e.getClick().isLeftClick()) {
					if (PlayerInfoFile.getPlayerInfoFile().getConfig()
							.getString("Players." + p.getName() + ".PreferredFood") != null
							&& PlayerInfoFile.getPlayerInfoFile().getConfig()
									.getString("Players." + p.getName() + ".PreferredFood") != "Unselected") {
						PlayerInfoFile.getPlayerInfoFile().getConfig().set("Players." + p.getName() + ".PreferredFood",
								"Unselected");
											PlayerInfoFile.getPlayerInfoFile().save();					PlayerInfoFile.getPlayerInfoFile().save();
						p.sendMessage("§8§l[§a§l!§8§l] §c§oUnselected§a§o your preferred food.");
					}
					FoodAPI.getNextFood().put(p.getName(), "CookPork");
					p.sendMessage("§8§l[§a§l!§8§l] §a§oYour next food set.");
				}
			} else {
				return;
			}
			break;
		case 32:
			if (e.getCurrentItem().getItemMeta().equals(Items.steakCount(true).getItemMeta())) {
				if (e.getClick().isRightClick()) {
					PlayerInfoFile.getPlayerInfoFile().getConfig().set("Players." + p.getName() + ".PreferredFood",
							"Steak");
					p.sendMessage(
							"§8§l[§a§l!§8§l] §a§oYour favorite food set. From now on, you will get this food type without the need to select it again. §e§oIf you wish to change it, use the §c§l/food§e§o command.");
					p.closeInventory();
				}

				if (e.getClick().isLeftClick()) {
					if (PlayerInfoFile.getPlayerInfoFile().getConfig()
							.getString("Players." + p.getName() + ".PreferredFood") != null
							&& PlayerInfoFile.getPlayerInfoFile().getConfig()
									.getString("Players." + p.getName() + ".PreferredFood") != "Unselected") {
						PlayerInfoFile.getPlayerInfoFile().getConfig().set("Players." + p.getName() + ".PreferredFood",
								"Unselected");
											PlayerInfoFile.getPlayerInfoFile().save();					PlayerInfoFile.getPlayerInfoFile().save();
						p.sendMessage("§8§l[§a§l!§8§l] §c§oUnselected§a§o your preferred food.");
					}
					FoodAPI.getNextFood().put(p.getName(), "Steak");
					p.sendMessage("§8§l[§a§l!§8§l] §a§oYour next food set.");
				}
			} else {
				return;
			}
			break;
		case 33:
			if (e.getCurrentItem().getItemMeta().equals(Items.rabbitStewCount(true).getItemMeta())) {
				if (e.getClick().isRightClick()) {
					PlayerInfoFile.getPlayerInfoFile().getConfig().set("Players." + p.getName() + ".PreferredFood",
							"RabbitStew");
					p.sendMessage(
							"§8§l[§a§l!§8§l] §a§oYour favorite food set. From now on, you will get this food type without the need to select it again. §e§oIf you wish to change it, use the §c§l/food§e§o command.");
					p.closeInventory();
				}

				if (e.getClick().isLeftClick()) {
					if (PlayerInfoFile.getPlayerInfoFile().getConfig()
							.getString("Players." + p.getName() + ".PreferredFood") != null
							&& PlayerInfoFile.getPlayerInfoFile().getConfig()
									.getString("Players." + p.getName() + ".PreferredFood") != "Unselected") {
						PlayerInfoFile.getPlayerInfoFile().getConfig().set("Players." + p.getName() + ".PreferredFood",
								"Unselected");
											PlayerInfoFile.getPlayerInfoFile().save();					PlayerInfoFile.getPlayerInfoFile().save();
						p.sendMessage("§8§l[§a§l!§8§l] §c§oUnselected§a§o your preferred food.");
					}
					FoodAPI.getNextFood().put(p.getName(), "RabbitStew");
					p.sendMessage("§8§l[§a§l!§8§l] §a§oYour next food set.");
				}
			} else {
				return;
			}
			break;
		default:
			break;
		}
	}
}
