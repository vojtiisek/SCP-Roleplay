package cz.vojtiisek.scprpsystem.Constructions;

import java.util.ArrayList;
import java.util.List;

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
import org.bukkit.inventory.meta.ItemMeta;

import cz.vojtiisek.scprpsystem.API;
import cz.vojtiisek.scprpsystem.Items;
import cz.vojtiisek.scprpsystem.Lores;
import cz.vojtiisek.scprpsystem.SCPRP;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.model.user.User;

public class ConstructionEdit implements Listener {
	private static SCPRP main;
	private static String title;
	
	public static String constructionName;
	
	static LuckPerms api = LuckPermsProvider.get();


	public static boolean bool = false;

	public ConstructionEdit(SCPRP main) {
		this.main = main;
	}

	public static void openGUI(Player player, String constName) {
		title = "§c§lEDIT: §6§l" + constName;
		constructionName = constName;
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

		inv.setItem(20, createTechnicsListItem(constName));
		inv.setItem(22, createCompleteConstructionItem(constName));
		inv.setItem(24, createRemoveConstructionItem(constName));
		
		player.openInventory(inv);
	}

	private static ItemStack createRemoveConstructionItem(String constName) {
		ItemStack iS = new ItemStack(Material.BARRIER, 1);
		ItemMeta iM = iS.getItemMeta();
		iM.setDisplayName("§4§lRemove construction");
		iM.setLore(Lores.removeConst());
		iS.setItemMeta(iM);
		return iS;
	}

	private static ItemStack createCompleteConstructionItem(String constName) {
		ItemStack iS = new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte)5);
		ItemMeta iM = iS.getItemMeta();
		iM.setDisplayName("§a§lComplete construction");
		iM.setLore(Lores.completeConst());
		iS.setItemMeta(iM);
		return iS;
	}

	public static ItemStack createTechnicsListItem(String constName) {
		ItemStack iS = new ItemStack(Material.PAPER, 1);
		ItemMeta iM = iS.getItemMeta();
		iM.setDisplayName("§6§lJoined in Technics:");
		List<String> lore = new ArrayList<String>();
		lore = ConstructionsMenu.getListOfTechnics(constName);
		lore.add("§c§oClick this item to kick a Technic out of this construction.");
		iM.setLore(lore);
		iS.setItemMeta(iM);
		return iS;
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
		case 20: //start a conversation to state the players nick to kick 
			p.closeInventory();
			if(ConstructionsMenu.getTechnics().get(constructionName) == null || ConstructionsMenu.getTechnics().get(constructionName).isEmpty()) {
				p.sendMessage("§c§oNo Technics have joined this construction.");
			} else {
				statePlayerToKick(p, ConstructionsMenu.getTechnics().get(constructionName));
			}
			break;
		case 22:
			p.closeInventory();
			API.sendConsoleCommand("discord broadcast #815209230407565332 :white_check_mark:  Site Director " + p.getName()
			+ " marked construction: **" + constructionName + "** as COMPLETED.");
			p.sendMessage("§8[§a§l!§8] §f§l" + constructionName + "§a§o marked as done. Attending technics:");
			if(ConstructionsMenu.getTechnics().get(constructionName) == null) {
				p.sendMessage("§c§oNo Technics have joined this construction.");
			} else {
				for(int i = 0; i<ConstructionsMenu.getTechnics().get(constructionName).size(); i++) {
					p.sendMessage("§8 - §6§o" + ConstructionsMenu.getTechnics().get(constructionName).get(i));
				} 
			}
			ConstructionsMenu.completeConstruction(constructionName);
			ConstructionsMenu.openGUI(p, role);
			break;
		case 24:
			p.closeInventory();
			API.sendConsoleCommand("discord broadcast #815209230407565332 :x:  Site Director " + p.getName()
			+ " removed construction: **" + constructionName + "**.");
			p.sendMessage("§8[§a§l!§8] §e§oConstruction §e§l" + constructionName + "§c§o removed.");
			ConstructionsMenu.removeConstruction(constructionName);
			ConstructionsMenu.openGUI(p, role);
			break;
		default:
			break;
		}
	}

	public static void statePlayerToKick(Player player, List<String> list) {
		ConversationFactory factory = new ConversationFactory(SCPRP.getInstance());
		Conversation conv = factory.withFirstPrompt(new KickConv1(SCPRP.getInstace(), player.getName(), list))
				.withLocalEcho(true).withEscapeSequence("exit").buildConversation(player);
		conv.begin();
	}
}