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
import org.bukkit.inventory.ItemStack;

import cz.vojtiisek.scprpsystem.API;
import cz.vojtiisek.scprpsystem.Items;
import cz.vojtiisek.scprpsystem.SCPRP;
import cz.vojtiisek.scprpsystem.Constructions.Stage1;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.model.user.User;

public class OrderSM implements Listener {
	private static SCPRP main;
	private static String title = "§6§lSelect item to order";

	static LuckPerms api = LuckPermsProvider.get();
	static String role = "roleerror";
	static String dept = "depterror";
	static int page = 0;

	public OrderSM(SCPRP main) {
		this.main = main;
	}

	public static void openGUI(Player player, String rolestr, String deptstr, int pageint) {
		page = pageint;
		role = rolestr;
		dept = deptstr;
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
			inv.setItem(20, Items.bandageItem());
			inv.setItem(21, Items.firstAidKitItem());
			inv.setItem(22, Items.medkitItem());
			inv.setItem(23, Items.adrenalineItem());
			break;
		case "security":
			break;
		case "scientific":
			if (page == 0) {
				inv.setItem(20, Items.cheeseItem());
				inv.setItem(21, Items.woodenPickaxe());
				inv.setItem(22, Items.stonePickaxe());
				inv.setItem(23, Items.ironAxe());
				inv.setItem(24, Items.ironPickaxe());
				inv.setItem(29, Items.goldenSword());
				inv.setItem(30, Items.crowbar());
				inv.setItem(31, Items.diamondShovel());
				inv.setItem(32, Items.animalNetBig());
				inv.setItem(33, Items.glassBottle());
				inv.setItem(40, Items.nextPageStorage());
			} else {
				inv.setItem(20, Items.emptyBucket());
				inv.setItem(40, Items.previousPageStorage());
			}
			break;
		case "other":
			inv.setItem(20, Items.carrot());
			inv.setItem(21, Items.apple());
			inv.setItem(22, Items.bread());
			inv.setItem(23, Items.bakedPotato());
			inv.setItem(24, Items.cookedFish());
			inv.setItem(29, Items.cookedChicken());
			inv.setItem(30, Items.pPie());
			inv.setItem(31, Items.cookedPorkchop());
			inv.setItem(32, Items.steak());
			inv.setItem(33, Items.rabbitStew());
			break;
		default:
			break;
		}

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

		ConversationFactory factory = new ConversationFactory(SCPRP.getInstance());
		Conversation conv;

		e.setCancelled(true);

		switch (e.getSlot()) {
		case 20:
			switch (dept) {
			case "medical":
				p.closeInventory();
				factory = new ConversationFactory(SCPRP.getInstance());
				conv = factory.withFirstPrompt(new AmountConv(SCPRP.getInstace(), dept, "Bandage", "256", p.getName()))
						.withLocalEcho(true).withEscapeSequence("cancel").buildConversation(p);
				conv.begin();
				break;
			case "security":
				break;
			case "scientific":
				p.closeInventory();
				if (page == 0) {
					factory = new ConversationFactory(SCPRP.getInstance());
					conv = factory
							.withFirstPrompt(new AmountConv(SCPRP.getInstace(), dept, "Cheese", "16", p.getName()))
							.withLocalEcho(true).withEscapeSequence("cancel").buildConversation(p);
					conv.begin();
				} else {
					factory = new ConversationFactory(SCPRP.getInstance());
					conv = factory
							.withFirstPrompt(new AmountConv(SCPRP.getInstace(), dept, "Bucket", "64", p.getName()))
							.withLocalEcho(true).withEscapeSequence("cancel").buildConversation(p);
					conv.begin();
				}
				break;
			case "other":
				p.closeInventory();
				factory = new ConversationFactory(SCPRP.getInstance());
				conv = factory.withFirstPrompt(new AmountConv(SCPRP.getInstace(), dept, "Carrot", "256", p.getName()))
						.withLocalEcho(true).withEscapeSequence("cancel").buildConversation(p);
				conv.begin();
				break;
			default:
				break;
			}
			break;
		case 21:
			switch (dept) {
			case "medical":
				p.closeInventory();
				conv = factory
						.withFirstPrompt(new AmountConv(SCPRP.getInstace(), dept, "FirstAidKit", "128", p.getName()))
						.withLocalEcho(true).withEscapeSequence("cancel").buildConversation(p);
				conv.begin();
				break;
			case "security":
				break;
			case "scientific":
				p.closeInventory();
				conv = factory
						.withFirstPrompt(new AmountConv(SCPRP.getInstace(), dept, "WoodPickaxe", "5", p.getName()))
						.withLocalEcho(true).withEscapeSequence("cancel").buildConversation(p);
				conv.begin();
				break;
			case "other":
				p.closeInventory();
				factory = new ConversationFactory(SCPRP.getInstance());
				conv = factory.withFirstPrompt(new AmountConv(SCPRP.getInstace(), dept, "Apple", "256", p.getName()))
						.withLocalEcho(true).withEscapeSequence("cancel").buildConversation(p);
				conv.begin();
				break;
			default:
				break;
			}
			break;
		case 22:
			switch (dept) {
			case "medical":
				p.closeInventory();
				conv = factory.withFirstPrompt(new AmountConv(SCPRP.getInstace(), dept, "Medkit", "128", p.getName()))
						.withLocalEcho(true).withEscapeSequence("cancel").buildConversation(p);
				conv.begin();
				break;
			case "security":
				break;
			case "scientific":
				p.closeInventory();
				conv = factory
						.withFirstPrompt(new AmountConv(SCPRP.getInstace(), dept, "StonePickaxe", "5", p.getName()))
						.withLocalEcho(true).withEscapeSequence("cancel").buildConversation(p);
				conv.begin();
				break;
			case "other":
				p.closeInventory();
				factory = new ConversationFactory(SCPRP.getInstance());
				conv = factory.withFirstPrompt(new AmountConv(SCPRP.getInstace(), dept, "Bread", "256", p.getName()))
						.withLocalEcho(true).withEscapeSequence("cancel").buildConversation(p);
				conv.begin();
				break;
			default:
				break;
			}
			break;
		case 23:
			switch (dept) {
			case "medical":
				p.closeInventory();
				factory = new ConversationFactory(SCPRP.getInstance());
				conv = factory
						.withFirstPrompt(new AmountConv(SCPRP.getInstace(), dept, "Adrenaline", "64", p.getName()))
						.withLocalEcho(true).withEscapeSequence("cancel").buildConversation(p);
				conv.begin();
				break;
			case "security":
				break;
			case "scientific":
				p.closeInventory();
				conv = factory.withFirstPrompt(new AmountConv(SCPRP.getInstace(), dept, "IronAxe", "5", p.getName()))
						.withLocalEcho(true).withEscapeSequence("cancel").buildConversation(p);
				conv.begin();
				break;
			case "other":
				p.closeInventory();
				factory = new ConversationFactory(SCPRP.getInstance());
				conv = factory
						.withFirstPrompt(new AmountConv(SCPRP.getInstace(), dept, "BakedPotato", "256", p.getName()))
						.withLocalEcho(true).withEscapeSequence("cancel").buildConversation(p);
				conv.begin();
				break;
			default:
				break;
			}
			break;
		case 24:
			switch (dept) {
			case "medical":
				break;
			case "security":
				break;
			case "scientific":
				p.closeInventory();
				conv = factory
						.withFirstPrompt(new AmountConv(SCPRP.getInstace(), dept, "IronPickaxe", "5", p.getName()))
						.withLocalEcho(true).withEscapeSequence("cancel").buildConversation(p);
				conv.begin();
				break;
			case "other":
				p.closeInventory();
				factory = new ConversationFactory(SCPRP.getInstance());
				conv = factory
						.withFirstPrompt(new AmountConv(SCPRP.getInstace(), dept, "CookedFish", "256", p.getName()))
						.withLocalEcho(true).withEscapeSequence("cancel").buildConversation(p);
				conv.begin();
				break;
			default:
				break;
			}
			break;
		case 29:
			switch (dept) {
			case "medical":
				break;
			case "security":
				break;
			case "scientific":
				p.closeInventory();
				conv = factory.withFirstPrompt(new AmountConv(SCPRP.getInstace(), dept, "GoldSword", "5", p.getName()))
						.withLocalEcho(true).withEscapeSequence("cancel").buildConversation(p);
				conv.begin();
				break;
			case "other":
				p.closeInventory();
				factory = new ConversationFactory(SCPRP.getInstance());
				conv = factory
						.withFirstPrompt(new AmountConv(SCPRP.getInstace(), dept, "CookedChicken", "128", p.getName()))
						.withLocalEcho(true).withEscapeSequence("cancel").buildConversation(p);
				conv.begin();
				break;
			default:
				break;
			}
			break;
		case 30:
			switch (dept) {
			case "medical":
				break;
			case "security":
				break;
			case "scientific":
				p.closeInventory();
				conv = factory.withFirstPrompt(new AmountConv(SCPRP.getInstace(), dept, "Crowbar", "5", p.getName()))
						.withLocalEcho(true).withEscapeSequence("cancel").buildConversation(p);
				conv.begin();
				break;
			case "other":
				p.closeInventory();
				factory = new ConversationFactory(SCPRP.getInstance());
				conv = factory
						.withFirstPrompt(new AmountConv(SCPRP.getInstace(), dept, "PumpkinPie", "128", p.getName()))
						.withLocalEcho(true).withEscapeSequence("cancel").buildConversation(p);
				conv.begin();
				break;
			default:
				break;
			}
			break;
		case 31:
			switch (dept) {
			case "medical":
				break;
			case "security":
				break;
			case "scientific":
				p.closeInventory();
				conv = factory
						.withFirstPrompt(new AmountConv(SCPRP.getInstace(), dept, "DiamondShovel", "5", p.getName()))
						.withLocalEcho(true).withEscapeSequence("cancel").buildConversation(p);
				conv.begin();
				break;
			case "other":
				p.closeInventory();
				factory = new ConversationFactory(SCPRP.getInstance());
				conv = factory.withFirstPrompt(new AmountConv(SCPRP.getInstace(), dept, "CookPork", "128", p.getName()))
						.withLocalEcho(true).withEscapeSequence("cancel").buildConversation(p);
				conv.begin();
				break;
			default:
				break;
			}
			break;
		case 32:
			switch (dept) {
			case "medical":
				break;
			case "security":
				break;
			case "scientific":
				p.closeInventory();
				factory = new ConversationFactory(SCPRP.getInstance());
				conv = factory
						.withFirstPrompt(new AmountConv(SCPRP.getInstace(), dept, "AnimalNetBig", "64", p.getName()))
						.withLocalEcho(true).withEscapeSequence("cancel").buildConversation(p);
				conv.begin();
				break;
			case "other":
				p.closeInventory();
				factory = new ConversationFactory(SCPRP.getInstance());
				conv = factory.withFirstPrompt(new AmountConv(SCPRP.getInstace(), dept, "Steak", "128", p.getName()))
						.withLocalEcho(true).withEscapeSequence("cancel").buildConversation(p);
				conv.begin();
				break;
			default:
				break;
			}
			break;
		case 33:
			switch (dept) {
			case "medical":
				break;
			case "security":
				break;
			case "scientific":
				p.closeInventory();
				factory = new ConversationFactory(SCPRP.getInstance());
				conv = factory
						.withFirstPrompt(new AmountConv(SCPRP.getInstace(), dept, "GlassBottle", "128", p.getName()))
						.withLocalEcho(true).withEscapeSequence("cancel").buildConversation(p);
				conv.begin();
				break;
			case "other":
				p.closeInventory();
				factory = new ConversationFactory(SCPRP.getInstance());
				conv = factory
						.withFirstPrompt(new AmountConv(SCPRP.getInstace(), dept, "RabbitStew", "128", p.getName()))
						.withLocalEcho(true).withEscapeSequence("cancel").buildConversation(p);
				conv.begin();
				break;
			default:
				break;
			}
			break;
		case 40:
			switch (dept) {
			case "medical":
				break;
			case "security":
				break;
			case "scientific":
				p.closeInventory();
				if (e.getCurrentItem().getItemMeta().equals(Items.nextPageStorage().getItemMeta())) {
					openGUI(p, LuckPermsProvider.get().getUserManager().getUser(p.getName()).getPrimaryGroup(), dept, page+1);
				} else if (e.getCurrentItem().getItemMeta().equals(Items.previousPageStorage().getItemMeta())) {
					openGUI(p, LuckPermsProvider.get().getUserManager().getUser(p.getName()).getPrimaryGroup(), dept, page-1);
				} else {
					openGUI(p, LuckPermsProvider.get().getUserManager().getUser(p.getName()).getPrimaryGroup(), dept, 0);
					p.sendMessage("§c§o[§4§l!§c§o] §e§oThere has been an error while trying to switch pages. Please, contact §4§lThe Administrator§e§o, and tell him this code: §f§lOrderSM-SCI40");
					API.sendConsoleCommand("discordsrv broadcast #839057785362448424 [OrderSM] Error while switching - Scientific");
					return;
				}
				break;
			case "other":
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