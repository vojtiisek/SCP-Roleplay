package cz.vojtiisek.scprpsystem.Storage.GUIs;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.conversations.Conversation;
import org.bukkit.conversations.ConversationFactory;
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

public class WithdrawSM implements Listener {
	private static SCPRP main;
	private static String title = "§6§lSelect Item to withdraw";

	static LuckPerms api = LuckPermsProvider.get();
	static String role = "roleerror";
	static String dept = "depterror";
	static int page = 0;

	public WithdrawSM(SCPRP main) {
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

		ConversationFactory factory = new ConversationFactory(SCPRP.getInstance());
		Conversation conv;

		e.setCancelled(true);

		switch (e.getSlot()) {
		case 20:
			switch (dept) {
			case "medical":
				p.closeInventory();
				if (StorageDatabase.getCount("Bandage", dept) != 0) {
					conv = factory
							.withFirstPrompt(new WithdrawConv(SCPRP.getInstace(), dept, "Bandage",
									Integer.toString(StorageDatabase.getCount("Bandage", dept)), p.getName()))
							.withLocalEcho(true).withEscapeSequence("cancel").buildConversation(p);
					conv.begin();
				} else {
					p.sendMessage(
							"§8[§4§lE&Ts§8] §c§oThis item is out of stock. §e§oContact the Storage Leader, or any Site Director to order it, if needed.");
				}
				break;
			case "security":
				p.closeInventory();

				break;
			case "scientific":
				p.closeInventory();
				if (page == 0) {
					if (StorageDatabase.getCount("Cheese", dept) != 0) {
						conv = factory
								.withFirstPrompt(new WithdrawConv(SCPRP.getInstace(), dept, "Cheese",
										Integer.toString(StorageDatabase.getCount("Cheese", dept)), p.getName()))
								.withLocalEcho(true).withEscapeSequence("cancel").buildConversation(p);
						conv.begin();
					} else {
						p.sendMessage(
								"§8[§4§lE&Ts§8] §c§oThis item is out of stock. §e§oContact the Storage Leader, or any Site Director to order it, if needed.");
					}
				} else {
					if (StorageDatabase.getCount("Bucket", dept) != 0) {
						conv = factory
								.withFirstPrompt(new WithdrawConv(SCPRP.getInstace(), dept, "Bucket",
										Integer.toString(StorageDatabase.getCount("Bucket", dept)), p.getName()))
								.withLocalEcho(true).withEscapeSequence("cancel").buildConversation(p);
						conv.begin();
					} else {
						p.sendMessage(
								"§8[§4§lE&Ts§8] §c§oThis item is out of stock. §e§oContact the Storage Leader, or any Site Director to order it, if needed.");
					}
				}
				break;
			case "other":
				if (StorageDatabase.getCount("Carrot", dept) != 0) {
					conv = factory
							.withFirstPrompt(new WithdrawConv(SCPRP.getInstace(), dept, "Carrot",
									Integer.toString(StorageDatabase.getCount("Carrot", dept)), p.getName()))
							.withLocalEcho(true).withEscapeSequence("cancel").buildConversation(p);
					conv.begin();
				} else {
					p.sendMessage(
							"§8[§4§lE&Ts§8] §c§oThis item is out of stock. §e§oContact the Storage Leader, or any Site Director to order it, if needed.");
				}
				break;
			default:
				break;
			}
			break;
		case 21:
			switch (dept) {
			case "medical":
				p.closeInventory();
				if (StorageDatabase.getCount("FirstAidKit", dept) != 0) {
					conv = factory
							.withFirstPrompt(new WithdrawConv(SCPRP.getInstace(), dept, "FirstAidKit",
									Integer.toString(StorageDatabase.getCount("FirstAidKit", dept)), p.getName()))
							.withLocalEcho(true).withEscapeSequence("cancel").buildConversation(p);
					conv.begin();
				} else {
					p.sendMessage(
							"§8[§4§lE&Ts§8] §c§oThis item is out of stock. §e§oContact the Storage Leader, or any Site Director to order it, if needed.");
				}
				break;
			case "security":
				p.closeInventory();

				break;
			case "scientific":
				p.closeInventory();
				if (StorageDatabase.getCount("WoodPickaxe", dept) != 0) {
					conv = factory
							.withFirstPrompt(new WithdrawConv(SCPRP.getInstace(), dept, "WoodPickaxe",
									Integer.toString(StorageDatabase.getCount("WoodPickaxe", dept)), p.getName()))
							.withLocalEcho(true).withEscapeSequence("cancel").buildConversation(p);
					conv.begin();
				} else {
					p.sendMessage(
							"§8[§4§lE&Ts§8] §c§oThis item is out of stock. §e§oContact the Storage Leader, or any Site Director to order it, if needed.");
				}
				break;
			case "other":
				if (StorageDatabase.getCount("Apple", dept) != 0) {
					conv = factory
							.withFirstPrompt(new WithdrawConv(SCPRP.getInstace(), dept, "Apple",
									Integer.toString(StorageDatabase.getCount("Apple", dept)), p.getName()))
							.withLocalEcho(true).withEscapeSequence("cancel").buildConversation(p);
					conv.begin();
				} else {
					p.sendMessage(
							"§8[§4§lE&Ts§8] §c§oThis item is out of stock. §e§oContact the Storage Leader, or any Site Director to order it, if needed.");
				}
				break;
			default:
				break;
			}
			break;
		case 22:
			switch (dept) {
			case "medical":
				p.closeInventory();
				if (StorageDatabase.getCount("Medkit", dept) != 0) {
					conv = factory
							.withFirstPrompt(new WithdrawConv(SCPRP.getInstace(), dept, "Medkit",
									Integer.toString(StorageDatabase.getCount("Medkit", dept)), p.getName()))
							.withLocalEcho(true).withEscapeSequence("cancel").buildConversation(p);
					conv.begin();
				} else {
					p.sendMessage(
							"§8[§4§lE&Ts§8] §c§oThis item is out of stock. §e§oContact the Storage Leader, or any Site Director to order it, if needed.");
				}
				break;
			case "security":
				p.closeInventory();

				break;
			case "scientific":
				p.closeInventory();
				if (StorageDatabase.getCount("StonePickaxe", dept) != 0) {
					conv = factory
							.withFirstPrompt(new WithdrawConv(SCPRP.getInstace(), dept, "StonePickaxe",
									Integer.toString(StorageDatabase.getCount("StonePickaxe", dept)), p.getName()))
							.withLocalEcho(true).withEscapeSequence("cancel").buildConversation(p);
					conv.begin();
				} else {
					p.sendMessage(
							"§8[§4§lE&Ts§8] §c§oThis item is out of stock. §e§oContact the Storage Leader, or any Site Director to order it, if needed.");
				}
				break;
			case "other":
				if (StorageDatabase.getCount("Bread", dept) != 0) {
					conv = factory
							.withFirstPrompt(new WithdrawConv(SCPRP.getInstace(), dept, "Bread",
									Integer.toString(StorageDatabase.getCount("Bread", dept)), p.getName()))
							.withLocalEcho(true).withEscapeSequence("cancel").buildConversation(p);
					conv.begin();
				} else {
					p.sendMessage(
							"§8[§4§lE&Ts§8] §c§oThis item is out of stock. §e§oContact the Storage Leader, or any Site Director to order it, if needed.");
				}
				break;
			default:
				break;
			}
			break;
		case 23:
			switch (dept) {
			case "medical":
				p.closeInventory();
				if (StorageDatabase.getCount("Adrenaline", dept) != 0) {
					conv = factory
							.withFirstPrompt(new WithdrawConv(SCPRP.getInstace(), dept, "Adrenaline",
									Integer.toString(StorageDatabase.getCount("Adrenaline", dept)), p.getName()))
							.withLocalEcho(true).withEscapeSequence("cancel").buildConversation(p);
					conv.begin();
				} else {
					p.sendMessage(
							"§8[§4§lE&Ts§8] §c§oThis item is out of stock. §e§oContact any Storage Leader or the Site Director to order it, if needed.");
				}
				break;
			case "security":
				p.closeInventory();

				break;
			case "scientific":
				p.closeInventory();
				if (StorageDatabase.getCount("IronAxe", dept) != 0) {
					conv = factory
							.withFirstPrompt(new WithdrawConv(SCPRP.getInstace(), dept, "IronAxe",
									Integer.toString(StorageDatabase.getCount("IronAxe", dept)), p.getName()))
							.withLocalEcho(true).withEscapeSequence("cancel").buildConversation(p);
					conv.begin();
				} else {
					p.sendMessage(
							"§8[§4§lE&Ts§8] §c§oThis item is out of stock. §e§oContact the Storage Leader, or any Site Director to order it, if needed.");
				}
				break;
			case "other":
				if (StorageDatabase.getCount("BakedPotato", dept) != 0) {
					conv = factory
							.withFirstPrompt(new WithdrawConv(SCPRP.getInstace(), dept, "BakedPotato",
									Integer.toString(StorageDatabase.getCount("BakedPotato", dept)), p.getName()))
							.withLocalEcho(true).withEscapeSequence("cancel").buildConversation(p);
					conv.begin();
				} else {
					p.sendMessage(
							"§8[§4§lE&Ts§8] §c§oThis item is out of stock. §e§oContact the Storage Leader, or any Site Director to order it, if needed.");
				}
				break;
			default:
				break;
			}
			break;
		case 24:
			switch (dept) {
			case "medical":
				p.closeInventory();

				break;
			case "security":
				p.closeInventory();

				break;
			case "scientific":
				p.closeInventory();
				if (StorageDatabase.getCount("IronPickaxe", dept) != 0) {
					conv = factory
							.withFirstPrompt(new WithdrawConv(SCPRP.getInstace(), dept, "IronPickaxe",
									Integer.toString(StorageDatabase.getCount("IronPickaxe", dept)), p.getName()))
							.withLocalEcho(true).withEscapeSequence("cancel").buildConversation(p);
					conv.begin();
				} else {
					p.sendMessage(
							"§8[§4§lE&Ts§8] §c§oThis item is out of stock. §e§oContact the Storage Leader, or any Site Director to order it, if needed.");
				}
				break;
			case "other":
				if (StorageDatabase.getCount("CookedFish", dept) != 0) {
					conv = factory
							.withFirstPrompt(new WithdrawConv(SCPRP.getInstace(), dept, "CookedFish",
									Integer.toString(StorageDatabase.getCount("CookedFish", dept)), p.getName()))
							.withLocalEcho(true).withEscapeSequence("cancel").buildConversation(p);
					conv.begin();
				} else {
					p.sendMessage(
							"§8[§4§lE&Ts§8] §c§oThis item is out of stock. §e§oContact the Storage Leader, or any Site Director to order it, if needed.");
				}
				break;
			default:
				break;
			}
			break;
		case 29:
			switch (dept) {
			case "medical":
				p.closeInventory();

				break;
			case "security":
				p.closeInventory();

				break;
			case "scientific":
				p.closeInventory();
				if (StorageDatabase.getCount("GoldSword", dept) != 0) {
					conv = factory
							.withFirstPrompt(new WithdrawConv(SCPRP.getInstace(), dept, "GoldSword",
									Integer.toString(StorageDatabase.getCount("GoldSword", dept)), p.getName()))
							.withLocalEcho(true).withEscapeSequence("cancel").buildConversation(p);
					conv.begin();
				} else {
					p.sendMessage(
							"§8[§4§lE&Ts§8] §c§oThis item is out of stock. §e§oContact the Storage Leader, or any Site Director to order it, if needed.");
				}
				break;
			case "other":
				if (StorageDatabase.getCount("CookedChicken", dept) != 0) {
					conv = factory
							.withFirstPrompt(new WithdrawConv(SCPRP.getInstace(), dept, "CookedChicken",
									Integer.toString(StorageDatabase.getCount("CookedChicken", dept)), p.getName()))
							.withLocalEcho(true).withEscapeSequence("cancel").buildConversation(p);
					conv.begin();
				} else {
					p.sendMessage(
							"§8[§4§lE&Ts§8] §c§oThis item is out of stock. §e§oContact the Storage Leader, or any Site Director to order it, if needed.");
				}
				break;
			default:
				break;
			}
			break;
		case 30:
			switch (dept) {
			case "medical":
				p.closeInventory();

				break;
			case "security":
				p.closeInventory();

				break;
			case "scientific":
				p.closeInventory();
				if (StorageDatabase.getCount("Crowbar", dept) != 0) {
					conv = factory
							.withFirstPrompt(new WithdrawConv(SCPRP.getInstace(), dept, "Crowbar",
									Integer.toString(StorageDatabase.getCount("Crowbar", dept)), p.getName()))
							.withLocalEcho(true).withEscapeSequence("cancel").buildConversation(p);
					conv.begin();
				} else {
					p.sendMessage(
							"§8[§4§lE&Ts§8] §c§oThis item is out of stock. §e§oContact the Storage Leader, or any Site Director to order it, if needed.");
				}
				break;
			case "other":
				if (StorageDatabase.getCount("PumpkinPie", dept) != 0) {
					conv = factory
							.withFirstPrompt(new WithdrawConv(SCPRP.getInstace(), dept, "PumpkinPie",
									Integer.toString(StorageDatabase.getCount("PumpkinPie", dept)), p.getName()))
							.withLocalEcho(true).withEscapeSequence("cancel").buildConversation(p);
					conv.begin();
				} else {
					p.sendMessage(
							"§8[§4§lE&Ts§8] §c§oThis item is out of stock. §e§oContact the Storage Leader, or any Site Director to order it, if needed.");
				}
				break;
			default:
				break;
			}
			break;
		case 31:
			switch (dept) {
			case "medical":
				p.closeInventory();

				break;
			case "security":
				p.closeInventory();

				break;
			case "scientific":
				p.closeInventory();
				if (StorageDatabase.getCount("DiamondShovel", dept) != 0) {
					conv = factory
							.withFirstPrompt(new WithdrawConv(SCPRP.getInstace(), dept, "DiamondShovel",
									Integer.toString(StorageDatabase.getCount("DiamondShovel", dept)), p.getName()))
							.withLocalEcho(true).withEscapeSequence("cancel").buildConversation(p);
					conv.begin();
				} else {
					p.sendMessage(
							"§8[§4§lE&Ts§8] §c§oThis item is out of stock. §e§oContact the Storage Leader, or any Site Director to order it, if needed.");
				}
				break;
			case "other":
				if (StorageDatabase.getCount("CookPork", dept) != 0) {
					conv = factory
							.withFirstPrompt(new WithdrawConv(SCPRP.getInstace(), dept, "CookPork",
									Integer.toString(StorageDatabase.getCount("CookPork", dept)), p.getName()))
							.withLocalEcho(true).withEscapeSequence("cancel").buildConversation(p);
					conv.begin();
				} else {
					p.sendMessage(
							"§8[§4§lE&Ts§8] §c§oThis item is out of stock. §e§oContact the Storage Leader, or any Site Director to order it, if needed.");
				}
				break;
			default:
				break;
			}
			break;
		case 32:
			switch (dept) {
			case "medical":
				p.closeInventory();
				break;
			case "security":
				p.closeInventory();
				break;
			case "scientific":
				if (StorageDatabase.getCount("AnimalNetBig", dept) != 0) {
					conv = factory
							.withFirstPrompt(new WithdrawConv(SCPRP.getInstace(), dept, "AnimalNetBig",
									Integer.toString(StorageDatabase.getCount("AnimalNetBig", dept)), p.getName()))
							.withLocalEcho(true).withEscapeSequence("cancel").buildConversation(p);
					conv.begin();
				} else {
					p.sendMessage(
							"§8[§4§lE&Ts§8] §c§oThis item is out of stock. §e§oContact the Storage Leader, or any Site Director to order it, if needed.");
				}
				break;
			case "other":
				if (StorageDatabase.getCount("Steak", dept) != 0) {
					conv = factory
							.withFirstPrompt(new WithdrawConv(SCPRP.getInstace(), dept, "Steak",
									Integer.toString(StorageDatabase.getCount("Steak", dept)), p.getName()))
							.withLocalEcho(true).withEscapeSequence("cancel").buildConversation(p);
					conv.begin();
				} else {
					p.sendMessage(
							"§8[§4§lE&Ts§8] §c§oThis item is out of stock. §e§oContact the Storage Leader, or any Site Director to order it, if needed.");
				}
				break;
			default:
				break;
			}
			break;
		case 33:
			switch (dept) {
			case "medical":
				p.closeInventory();
				break;
			case "security":
				p.closeInventory();
				break;
			case "scientific":
				if (StorageDatabase.getCount("GlassBottle", dept) != 0) {
					conv = factory
							.withFirstPrompt(new WithdrawConv(SCPRP.getInstace(), dept, "GlassBottle",
									Integer.toString(StorageDatabase.getCount("GlassBottle", dept)), p.getName()))
							.withLocalEcho(true).withEscapeSequence("cancel").buildConversation(p);
					conv.begin();
				} else {
					p.sendMessage(
							"§8[§4§lE&Ts§8] §c§oThis item is out of stock. §e§oContact the Storage Leader, or any Site Director to order it, if needed.");
				}
				break;
			case "other":
				if (StorageDatabase.getCount("RabbitStew", dept) != 0) {
					conv = factory
							.withFirstPrompt(new WithdrawConv(SCPRP.getInstace(), dept, "RabbitStew",
									Integer.toString(StorageDatabase.getCount("RabbitStew", dept)), p.getName()))
							.withLocalEcho(true).withEscapeSequence("cancel").buildConversation(p);
					conv.begin();
				} else {
					p.sendMessage(
							"§8[§4§lE&Ts§8] §c§oThis item is out of stock. §e§oContact the Storage Leader, or any Site Director to order it, if needed.");
				}
				break;
			default:
				break;
			}
			break;
		case 40:
			switch(dept) {
			case "scientific":
				p.closeInventory();
				if (e.getCurrentItem().getItemMeta().equals(Items.nextPageStorage().getItemMeta())) {
					openGUI(p, LuckPermsProvider.get().getUserManager().getUser(p.getName()).getPrimaryGroup(), dept, page+1);
				} else if (e.getCurrentItem().getItemMeta().equals(Items.previousPageStorage().getItemMeta())) {
					openGUI(p, LuckPermsProvider.get().getUserManager().getUser(p.getName()).getPrimaryGroup(), dept, page-1);
				} else {
					openGUI(p, LuckPermsProvider.get().getUserManager().getUser(p.getName()).getPrimaryGroup(), dept, 0);
					p.sendMessage("§c§o[§4§l!§c§o] §e§oThere has been an error while trying to switch pages. Please, contact §4§lThe Administrator§e§o, and tell him this code: §f§lWithdrawSM-SCI40");
					API.sendConsoleCommand("discordsrv broadcast #839057785362448424 [WithdrawSM] Error while switching - Scientific");
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