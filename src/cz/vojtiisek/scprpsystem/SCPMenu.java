package cz.vojtiisek.scprpsystem;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

import cz.vojtiisek.scprpsystem.DailyRewards.DailyRewards;
import cz.vojtiisek.scprpsystem.IdentificationCard.IDGUI;
import cz.vojtiisek.scprpsystem.Kits.KitSystem;
import cz.vojtiisek.scprpsystem.files.PlayerInfoFile;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.model.user.User;

public class SCPMenu implements Listener {
	private static SCPRP main;
	public static LuckPerms api;
	private static String title = "§8§lSecure. Contain. Protect.";

	public SCPMenu(SCPRP main) {
		this.main = main;
	}

	public static void openGUI(Player player, User user) {
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

		inv.setItem(13, Items.playerItemMenu(player.getName()));
		inv.setItem(20, Items.kitItem());
		inv.setItem(21, Items.breachStateItem());
		if (player.hasPermission("scprp.technic") || user.getPrimaryGroup().equals("technic")
				|| user.getPrimaryGroup().equals("storage-leader") || user.getPrimaryGroup().equals("site-director")) {
			inv.setItem(22, Items.constructionsItem());
			inv.setItem(31, Items.storageManagerItem());
		} else if (player.hasPermission("scprp.test") || user.getPrimaryGroup().equals("researcher")
				|| user.getPrimaryGroup().equals("head-researcher")) {
			inv.setItem(22, Items.testsItem());
		} else if (user.getPrimaryGroup().equals("security-officer")
				|| user.getPrimaryGroup().equals("head-security-officer")) {
			inv.setItem(22, Items.secoffGunsItem());
		} else if (user.getPrimaryGroup().equals("mtf-nu-7") || user.getPrimaryGroup().equals("mtf-nu-7-commander")) {
			inv.setItem(22, Items.mtfGunsItem());
		}

		if (player.hasPermission("scprp.admin") || player.hasPermission("scp.admin") || player.hasPermission("scp.mod")
				|| player.hasPermission("scprp.mod") || player.hasPermission("scprp.o5")
				|| user.getPrimaryGroup().equals("site-director")) {
			inv.setItem(24, Items.designationsItem());
			inv.setItem(33, Items.freeCellsItem());
		}
		
		inv.setItem(23, Items.discordItem());
		inv.setItem(32, Items.clearInvItem());
		inv.setItem(29, Items.dailyRewardItem(player));
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
		case 13:
			if (e.getCurrentItem().getItemMeta().equals(Items.playerItemMenu(p.getName()).getItemMeta())) {
				p.closeInventory();
				IDGUI.openGUI(p, p.getName());
			} else {
				return;
			}
			break;	
		case 20:
			if (e.getCurrentItem().getItemMeta().equals(Items.kitItem().getItemMeta())) {
				p.closeInventory();
				KitSystem.giveKit(p.getName());
			} else {
				return;
			}
			break;
		case 22:
			if (e.getCurrentItem().getItemMeta().equals(Items.constructionsItem().getItemMeta())) {
				p.closeInventory();
				p.performCommand("constructions");
			} else if (e.getCurrentItem().getItemMeta().equals(Items.testsItem().getItemMeta())) {
				p.closeInventory();
				p.performCommand("tests");
			} else if (e.getCurrentItem().getItemMeta().equals(Items.secoffGunsItem().getItemMeta())) {
				p.closeInventory();
				p.sendMessage("§8[§9§l?§8] §7§oUse §a§o/guns§7§o to open the Security Guns Shop.");
			} else if (e.getCurrentItem().getItemMeta().equals(Items.mtfGunsItem().getItemMeta())) {
				p.closeInventory();
				p.sendMessage("§8[§9§l?§8] §7§oUse §b§o/mtfguns§7§o to open the MTF Guns Shop.");
			} else {
				return;
			}
			break;
		case 23:
			if (e.getCurrentItem().getItemMeta().equals(Items.discordItem().getItemMeta())) {
				p.closeInventory();
				p.sendMessage("§8[§5§lDiscord§8] §d§oClick this invite link to join our Discord server: §5§lhttp://discord.gg/yMqzmrC");
				p.performCommand("discordsrv link");
			} else {
				return;
			}
			break;
		case 29:
			p.closeInventory();
			long now = System.currentTimeMillis();
			if (PlayerInfoFile.getPlayerInfoFile().getConfig()
					.getString("Players." + p.getName() + ".DailyReward") != null) {
				if (PlayerInfoFile.getPlayerInfoFile().getConfig()
						.getString("Players." + p.getName() + ".DailyReward") == "0") {
					DailyRewards.claimReward(p.getName());
				} else {
					if (now - Long.parseLong(PlayerInfoFile.getPlayerInfoFile().getConfig()
							.getString("Players." + p.getName() + ".DailyReward")) < 86400000) {
						p.sendMessage(
								"§8§l[§c§l!§8§l] §e§oYou can claim your next daily reward in §f§l" + API.formatTimeDHM((Long
										.parseLong(PlayerInfoFile.getPlayerInfoFile().getConfig()
												.getString("Players." + p.getName() + ".DailyReward"))
										+ 86400000) - now) + "§e§o.");
					} else {
						DailyRewards.claimReward(p.getName());
					}
				}
			}
			break;
		case 31:
			if (e.getCurrentItem().getItemMeta().equals(Items.storageManagerItem().getItemMeta())) {
				p.closeInventory();
				p.performCommand("storage");
			} else {
				return;
			}
			break;
		case 32:
			if (e.getCurrentItem().getItemMeta().equals(Items.clearInvItem().getItemMeta())) {
				p.closeInventory();
				p.performCommand("clear");
			} else {
				return;
			}
			break;
		default:
			break;
		}
	}
}
