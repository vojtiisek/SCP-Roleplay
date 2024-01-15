package cz.vojtiisek.scprpsystem.Constructions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

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

import Levelling.LevellingSys;
import cz.vojtiisek.scprpsystem.API;
import cz.vojtiisek.scprpsystem.Items;
import cz.vojtiisek.scprpsystem.SCPRP;
import cz.vojtiisek.scprpsystem.Tasks.TasksSys;
import cz.vojtiisek.scprpsystem.files.ConstructionsFile;
import cz.vojtiisek.scprpsystem.files.LevelsFile;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.model.user.User;

public class ConstructionsMenu implements Listener {
	private static SCPRP main;
	private static String title = "§6§lConstructions";

	static LuckPerms api = LuckPermsProvider.get();

	public static HashMap<String, List<List<String>>> constructions = new HashMap<String, List<List<String>>>(); // site
																													// director
	// name,
	// [construction
	// name, level,
	// description]
	// public static HashMap<ItemStack, Integer> items = new HashMap<ItemStack,
	// Integer>();

	public static HashMap<String, List<String>> joinedTechnics = new HashMap<String, List<String>>();

	public static boolean bool = false;

	public ConstructionsMenu(SCPRP main) {
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

		if (role.equalsIgnoreCase("site-director")/* || player.hasPermission("scprp.admin") */)
			inv.setItem(40, Items.addConstructionItem());
		addConstructions(inv);

		player.openInventory(inv);
	}

	private static void addConstructions(Inventory inv) { // 20 -> 24, 29 -> 33
		for (Entry<String, List<List<String>>> entry : getConstructions().entrySet()) {
			String creator = entry.getKey();
			for (int i = 0; i < getConstructions().get(creator).size(); i++) {
				String name = getConstructions().get(creator).get(i).get(0);
				String level = getConstructions().get(creator).get(i).get(1);
				String desc = getConstructions().get(creator).get(i).get(2);
				ItemStack iS = new ItemStack(Material.BRICK);
				ItemMeta iM = iS.getItemMeta();
				iM.setDisplayName(API.getRandomColorBoldString() + name);
				List<String> lore = new ArrayList<String>();
				lore.add("§7§oDescription: §e§o" + desc);
				lore.add("§6§oLevel required: §c§l" + level);
				lore.add("§e§oSite Director: §f§l" + creator);
				iM.setLore(lore);
				iS.setItemMeta(iM);
				addItemStack(iS, inv);
			}
		}
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
		case 40:
			if (e.getCurrentItem().getItemMeta().equals(Items.addConstructionItem().getItemMeta())) {
				p.closeInventory();
				createConstruction(e.getInventory(), p);
			} else {
				return;
			}
			break;
		case 20:
			if (e.getCurrentItem().getType().equals(Material.BRICK) && e.getCurrentItem().hasItemMeta()) {
				decideClickAction(p, role, e.getCurrentItem().getItemMeta().getDisplayName());
			} else {
				return;
			}
			break;
		case 21:
			if (e.getCurrentItem().getType().equals(Material.BRICK) && e.getCurrentItem().hasItemMeta()) {
				decideClickAction(p, role, e.getCurrentItem().getItemMeta().getDisplayName());
			} else {
				return;
			}
			break;
		case 22:
			if (e.getCurrentItem().getType().equals(Material.BRICK) && e.getCurrentItem().hasItemMeta()) {
				decideClickAction(p, role, e.getCurrentItem().getItemMeta().getDisplayName());
			} else {
				return;
			}
			break;
		case 23:
			if (e.getCurrentItem().getType().equals(Material.BRICK) && e.getCurrentItem().hasItemMeta()) {
				decideClickAction(p, role, e.getCurrentItem().getItemMeta().getDisplayName());
			} else {
				return;
			}
			break;
		case 24:
			if (e.getCurrentItem().getType().equals(Material.BRICK) && e.getCurrentItem().hasItemMeta()) {
				decideClickAction(p, role, e.getCurrentItem().getItemMeta().getDisplayName());
			} else {
				return;
			}
			break;
		case 29:
			if (e.getCurrentItem().getType().equals(Material.BRICK) && e.getCurrentItem().hasItemMeta()) {
				decideClickAction(p, role, e.getCurrentItem().getItemMeta().getDisplayName());
			} else {
				return;
			}
			break;
		case 30:
			if (e.getCurrentItem().getType().equals(Material.BRICK) && e.getCurrentItem().hasItemMeta()) {
				decideClickAction(p, role, e.getCurrentItem().getItemMeta().getDisplayName());
			} else {
				return;
			}
			break;
		case 31:
			if (e.getCurrentItem().getType().equals(Material.BRICK) && e.getCurrentItem().hasItemMeta()) {
				decideClickAction(p, role, e.getCurrentItem().getItemMeta().getDisplayName());
			} else {
				return;
			}
			break;
		case 32:
			if (e.getCurrentItem().getType().equals(Material.BRICK) && e.getCurrentItem().hasItemMeta()) {
				decideClickAction(p, role, e.getCurrentItem().getItemMeta().getDisplayName());
			} else {
				return;
			}
			break;
		case 33:
			if (e.getCurrentItem().getType().equals(Material.BRICK) && e.getCurrentItem().hasItemMeta()) {
				decideClickAction(p, role, e.getCurrentItem().getItemMeta().getDisplayName());
			} else {
				return;
			}
			break;
		default:
			break;
		}

	}

	public void decideClickAction(Player p, String role, String constName) {
		constName = constName.substring(4);
		if (!API.findConstructionByName(constName).isEmpty()) {
			if (role.equalsIgnoreCase("technic")) {
				int levelReq = Integer.parseInt(API.findConstructionByName(constName).get(1));
				int levelTechnic = LevellingSys.convertXpToLevel(role, LevellingSys.getXP(p.getName()));
				if (levelTechnic >= levelReq) {
					joinConstruction(p, constName);
				} else {
					p.sendMessage(
							"§8[§c§l!§8] §c§oYou can not join this construction, because the level required §8(§e§l"
									+ levelReq + "§8)§c§o is higher than your current level §8(§e§l" + levelTechnic
									+ "§8)§c§o.");
				}
			} else if (role.equalsIgnoreCase("storage-leader")) {
				joinConstruction(p, constName);
			} else if (role.equalsIgnoreCase("site-director")) {
				ConstructionEdit.openGUI(p, constName);
			} else {
				return;
			}
		} else {
			p.sendMessage("§8[§c§l!§8] §e§l" + constName + "§c§o does not exist.");
		}
	}

	public void joinConstruction(Player p, String constName) {
		if (API.findConstructionNameByMember(p.getName()) == "emptyemptyemptyemptyemptyerr") {
			if (getTechnics().get(constName) == null || getTechnics().get(constName).isEmpty()) {
				List<String> list = new ArrayList<String>();
				list.add(p.getName());
				getTechnics().put(constName, list);
				saveMembers();
				TasksSys.setSelectedShift(p.getName(), API.getRandomColorBoldString() + constName);
				API.sendConsoleCommand("discord broadcast #815209230407565332 :information_source:  Technic "
						+ p.getName() + " joined construction: **" + constName + "**.");
				p.sendMessage("§8[§a§l!§8] §a§oYou joined construction §6§l" + constName + "§a§o.");
			} else {
				if (getTechnics().get(constName).contains(p.getName())) {
					p.sendMessage("§8[§c§l!§8] §c§oYou are already a part of this construction.");
				} else {
					List<String> list = new ArrayList<String>();
					list = getTechnics().get(constName);
					list.add(p.getName());
					getTechnics().put(constName, list);
					saveMembers();

					p.sendMessage("§8[§a§l!§8] §a§oYou joined construction §6§l" + constName + "§a§o.");
				}
			}

			p.sendMessage(
					"§8[§a§l!§8] §6§oIf you wish to leave the construction, contact the §e§lSite Director§6§o, mentioned in the construction's info.");
		} else {
			p.sendMessage("§8[§c§l!§8] §c§oYou are already attending an another construction.");
		}
	}

	public static void createConstruction(Inventory inv, Player player) {
		ConversationFactory factory = new ConversationFactory(SCPRP.getInstance());
		Conversation conv = factory.withFirstPrompt(new Stage1(SCPRP.getInstace(), player.getName(), inv))
				.withLocalEcho(true).withEscapeSequence("exit").buildConversation(player);
		conv.begin();
	}

	public static HashMap<String, List<List<String>>> getConstructions() {
		return constructions;
	}

	public static void completeCreation(String creator, String name, Inventory inv) {
		saveConstructions();
		addConstructions(inv);
		API.sendConsoleCommand("discord broadcast #815209230407565332 :star: Site Director " + creator
				+ " added a new construction: **" + name + "**.");
		openGUI(Bukkit.getPlayerExact(creator), "site-director");
	}

	private static void addItemStack(ItemStack iS, Inventory inv) { // 20 -> 24, 29 -> 33
		ItemStack currentItem;

		for (int i = 20; i <= 24; i++) {
			currentItem = inv.getItem(i);
			if (i == 24) {
				if (currentItem != null) {
					if (currentItem.getType().equals(Material.AIR) || !currentItem.hasItemMeta()) {
						inv.setItem(i, iS);
						break;
					} else if (!currentItem.getType().equals(Material.AIR) || currentItem.hasItemMeta())
						for (int l = 29; l <= 33; l++) {
							if (inv.getItem(l) != null) {
								currentItem = inv.getItem(l);
								if (currentItem.getType().equals(Material.AIR) || !currentItem.hasItemMeta()) {
									inv.setItem(l, iS);
									break;
								} else if (!currentItem.getType().equals(Material.AIR) || currentItem.hasItemMeta())
									continue;
							} else {
								inv.setItem(l, iS);
								break;
							}
						}
				} else {
					inv.setItem(i, iS);
					break;
				}
			} else {
				if (inv.getItem(i) != null) {
					currentItem = inv.getItem(i);
					if (currentItem.getType().equals(Material.AIR) || !currentItem.hasItemMeta()) {
						inv.setItem(i, iS);
						break;
					} else if (!currentItem.getType().equals(Material.AIR) || currentItem.hasItemMeta())
						continue;
				} else {
					inv.setItem(i, iS);
					break;
				}
			}
		}
	}

	public static void loadConstructions() {
		ConstructionsFile.getConstructionsFile().save();
		ConstructionsFile.getConstructionsFile().reload();
		if (ConstructionsFile.getConstructionsFile().getConfig().isConfigurationSection("Constructions")) {
			for (String creator : ConstructionsFile.getConstructionsFile().getConfig()
					.getConfigurationSection("Constructions").getKeys(false)) {
				List<List<String>> listOfLists = new ArrayList<List<String>>();
				for (String constName : ConstructionsFile.getConstructionsFile().getConfig()
						.getConfigurationSection("Constructions." + creator).getKeys(false)) {
					if (ConstructionsFile.getConstructionsFile().getConfig()
							.isList("Constructions." + creator + "." + constName)) {

						List<String> configList = ConstructionsFile.getConstructionsFile().getConfig()
								.getStringList("Constructions." + creator + "." + constName);

						List<String> list = new ArrayList<String>();
						list.add(constName);

						for (int i = 0; i < configList.size(); i++) {
							list.add(configList.get(i));
						}
						listOfLists.add(list);
						getConstructions().put(creator, listOfLists);
						String str = "";
						for (int i = 1; i < list.size(); i++) {
							str = str + list.get(i) + ", ";
						}
					} else {
						API.sendConsoleCommand(
								"discord broadcast #839057785362448424 [SCPRP] loadConstructions isnt list");
					}
				}
			}

			System.out.println(" ");
			System.out.println("Constructions loaded successfully.");
		}
	}

	public static void saveConstructions() {
		String entryString = "empty";
		try {
			if (!getConstructions().isEmpty()) {
				ConstructionsFile.getConstructionsFile().getConfig().set("Constructions", "");
				List<List<String>> listOfLists = new ArrayList<List<String>>();
				for (Entry<String, List<List<String>>> entry : getConstructions().entrySet()) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						entryString = entry.getKey();
						listOfLists = entry.getValue();
						String constName = entry.getValue().get(i).get(0);
						List<String> newList = new ArrayList<String>();
						newList.add(entry.getValue().get(i).get(1));
						newList.add(entry.getValue().get(i).get(2));
						ConstructionsFile.getConstructionsFile().getConfig()
								.set("Constructions." + entryString + "." + constName, newList);

					}
				}
			}

			System.out.println("Constructions saved successfully.");
		} catch (Exception e) {
			ConstructionsFile.getConstructionsFile().save();
			ConstructionsFile.getConstructionsFile().reload();

			e.printStackTrace();
			saveConstructions();
		}

		ConstructionsFile.getConstructionsFile().save();
		ConstructionsFile.getConstructionsFile().reload();

		saveMembers();
	}

	public static void saveMembers() {
		try {
			if (!getTechnics().isEmpty()) {
				ConstructionsFile.getConstructionsFile().getConfig().set("Members", "");
				for (Entry<String, List<String>> entry : getTechnics().entrySet()) {
					ConstructionsFile.getConstructionsFile().getConfig().set("Members." + entry.getKey(),
							entry.getValue());
				}
			} else {
				ConstructionsFile.getConstructionsFile().getConfig().set("Members", "");
			}

		} catch (Exception e) {
			ConstructionsFile.getConstructionsFile().save();
			ConstructionsFile.getConstructionsFile().reload();

			e.printStackTrace();
			saveConstructions();
		}

		ConstructionsFile.getConstructionsFile().save();
		ConstructionsFile.getConstructionsFile().reload();
	}

	public static void loadMembers() {
		ConstructionsFile.getConstructionsFile().save();
		ConstructionsFile.getConstructionsFile().reload();

		for (String constName : ConstructionsFile.getConstructionsFile().getConfig().getConfigurationSection("Members")
				.getKeys(false)) {
			if (ConstructionsFile.getConstructionsFile().getConfig().isList("Members." + constName)) {
				getTechnics().put(constName,
						ConstructionsFile.getConstructionsFile().getConfig().getStringList("Members." + constName));
			} else {
				API.sendConsoleCommand("discord broadcast #839057785362448424 [SCPRP] loadMembers isnt list");
			}
		}

		System.out.println(" ");
		System.out.println("Construction members loaded successfully.");
	}

	public static List<String> getListOfTechnics(String constName) {
		List<String> list = new ArrayList<String>();
		if (getTechnics().get(constName) != null) {
			for (String str : getTechnics().get(constName)) {
				list.add("§8 - §6§o" + str);
			}
		}
		if (list.isEmpty())
			list.add("§c§oNo Technics have joined this construction yet.");
		return list;
	}

	public static HashMap<String, List<String>> getTechnics() {
		return joinedTechnics;
	}

	public static void completeConstruction(String constructionName) {
		String creator = API.findConstructionCreatorByList(API.findConstructionByName(constructionName));
		if (getConstructions().get(creator).contains(API.findConstructionByName(constructionName)))
			getConstructions().get(creator).remove(API.findConstructionByName(constructionName));
		getTechnics().remove(constructionName);
		saveConstructions();
	}

	public static void removeConstruction(String constructionName) {
		String creator = API.findConstructionCreatorByList(API.findConstructionByName(constructionName));
		if (getConstructions().get(creator).contains(API.findConstructionByName(constructionName))) {
			getConstructions().get(creator).remove(API.findConstructionByName(constructionName));
		} else {
			API.sendConsoleCommand("discord broadcast #839057785362448424 [SCPRP] removeConst error 0001");
		}

		if (getTechnics().get(constructionName) != null) {
			getTechnics().remove(constructionName);
		} else {
			API.sendConsoleCommand("discord broadcast #839057785362448424 [SCPRP] removeConst error 0002");
		}
		saveConstructions();
	}

	public static void kickTechnic(String str) {
		String constName = API.findConstructionNameByMember(str);
		if (constName == "emptyemptyemptyemptyemptyerr") {
			API.sendConsoleCommand(
					"discord broadcast #839057785362448424 [CONSTRUCTIONS] getTechnics emptyemptyemptyemptyemptyerr");
		} else {

			if (getTechnics().get(constName) != null) {
				if (getTechnics().get(constName).contains(str)) {
					getTechnics().get(constName).remove(str);
					saveMembers();
					Bukkit.getPlayerExact(str).sendMessage(
							"§8[§c§l!§8]§c§o You have been kicked from the §e§l" + constName + " §c§o construction.");
				} else {
					API.sendConsoleCommand("discord broadcast #839057785362448424 [SCPRP] kickTechnic error 0001");
				}
			} else {
				API.sendConsoleCommand(
						"discord broadcast #839057785362448424 [CONSTRUCTIONS] getTechnics null: " + constName);
			}
		}
	}
}