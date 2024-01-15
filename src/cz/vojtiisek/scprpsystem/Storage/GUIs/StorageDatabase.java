package cz.vojtiisek.scprpsystem.Storage.GUIs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import cz.vojtiisek.scprpsystem.API;
import cz.vojtiisek.scprpsystem.SCPRP;
import cz.vojtiisek.scprpsystem.files.StorageFile;

public class StorageDatabase {
	private static SCPRP main;

	public static Map<String, String> scientific = new HashMap<String, String>(); // Item:Amount (digital)
	public static Map<String, String> medical = new HashMap<String, String>(); // Item:Amount (digital)
	public static Map<String, String> security = new HashMap<String, String>(); // Item:Amount (digital)
	public static Map<String, String> other = new HashMap<String, String>(); // Item:Amount (digital)

	public static Map<String, String> items = new HashMap<String, String>(); // Item:ID

	public StorageDatabase(SCPRP main) {
		this.main = main;
	}

	public static Map<String, String> getMedical() {
		return medical;
	}

	public static Map<String, String> getScientific() {
		return scientific;
	}

	public static Map<String, String> getSecurity() {
		return security;
	}

	public static Map<String, String> getOther() {
		return other;
	}

	public static Map<String, String> getItems() {
		return items;
	}

	public static void addItems() {
		items.put("Bandage", "MEDIS_BANDAJ");
		items.put("FirstAidKit", "MEDIS_ILK_YARDIM");
		items.put("Medkit", "MEDIS_MEDKIT");
		items.put("Adrenaline", "MEDIS_ADRENALIN");

		items.put("Cheese", "SCP_CHEESE");
		items.put("WoodPickaxe", "WOOD_PICKAXE");
		items.put("StonePickaxe", "STONE_PICKAXE");
		items.put("IronAxe", "IRON_AXE");
		items.put("IronPickaxe", "IRON_PICKAXE");
		items.put("GoldSword", "GOLD_SWORD");
		items.put("Crowbar", "TECHGUNS_CROWBAR");
		items.put("DiamondShovel", "DIAMOND_SPADE");
		items.put("GlassBottle", "GLASS_BOTTLE");
		items.put("Bucket", "BUCKET");

		items.put("Carrot", "CARROT_ITEM");
		items.put("Apple", "APPLE");
		items.put("Bread", "BREAD");
		items.put("BakedPotato", "BAKED_POTATO");
		items.put("CookedChicken", "COOKED_CHICKEN");
		items.put("CookedFish", "COOKED_FISH");
		items.put("PumpkinPie", "PUMPKIN_PIE");
		items.put("CookPork", "GRILLED_PORK");
		items.put("Steak", "COOKED_BEEF");
		items.put("RabbitStew", "RABBIT_STEW");
		items.put("AnimalNetBig", "ANIMALNET_ANIMAL_NET_BIG");

		System.out.println("Storage System - Available Items & IDs loaded successfully.");
	}

	public static void orderItem(String dept, String item, String amount) {
		switch (dept) {
		case "medical":
			if (getMedical().containsKey(item)) {
				int currentAmount = API.parse(getMedical().get(item));
				getMedical().remove(item);
				getMedical().put(item, Integer.toString(currentAmount + API.parse(amount)));
			} else {
				getMedical().put(item, amount);
			}
			break;
		case "security":
			if (getSecurity().containsKey(item)) {
				int currentAmount = API.parse(getSecurity().get(item));
				getSecurity().remove(item);
				getSecurity().put(item, Integer.toString(currentAmount + API.parse(amount)));
			} else {
				getSecurity().put(item, amount);
			}
			break;
		case "scientific":
			if (getScientific().containsKey(item)) {
				int currentAmount = API.parse(getScientific().get(item));
				getScientific().remove(item);
				getScientific().put(item, Integer.toString(currentAmount + API.parse(amount)));
			} else {
				getScientific().put(item, amount);
			}
			break;
		case "other":
			if (getOther().containsKey(item)) {
				int currentAmount = API.parse(getOther().get(item));
				getOther().remove(item);
				getOther().put(item, Integer.toString(currentAmount + API.parse(amount)));
			} else {
				getOther().put(item, amount);
			}
			break;
		default:
			break;
		}

		saveFile();
	}

	public static String getItemID(String item) {
		String id = "error";
		if (getItems().containsKey(item)) {
			id = getItems().get(item);
		}
		return id;
	}

	public static int getCount(String item, String dept) {
		int count = 0;
		switch (dept) {
		case "medical":
			if (getMedical().containsKey(item))
				count = API.parse(getMedical().get(item));
		case "security":
			if (getSecurity().containsKey(item))
				count = API.parse(getSecurity().get(item));
			break;
		case "scientific":
			if (getScientific().containsKey(item))
				count = API.parse(getScientific().get(item));
			break;
		case "other":
			if (getOther().containsKey(item))
				count = API.parse(getOther().get(item));
			break;
		default:
			break;
		}
		return count;
	}

	public static void saveFile() {
		String entryString = "empty";
		try {
			if (!getMedical().isEmpty()) {
				List<String> list = new ArrayList<String>();
				for (Entry<String, String> entry : getMedical().entrySet()) {
					entryString = entry.getKey() + ":" + entry.getValue();
					list.add(entryString);
				}
				StorageFile.getStorageFile().getConfig().set("Medical", list);
			}

			entryString = "empty";
			if (!getSecurity().isEmpty()) {
				List<String> list = new ArrayList<String>();
				for (Entry<String, String> entry : getSecurity().entrySet()) {
					entryString = entry.getKey() + ":" + entry.getValue();
					list.add(entryString);
				}
				StorageFile.getStorageFile().getConfig().set("Security", list);
			}

			entryString = "empty";
			if (!getScientific().isEmpty()) {
				List<String> list = new ArrayList<String>();
				for (Entry<String, String> entry : getScientific().entrySet()) {
					entryString = entry.getKey() + ":" + entry.getValue();
					list.add(entryString);
				}
				StorageFile.getStorageFile().getConfig().set("Scientific", list);
			}

			entryString = "empty";
			if (!getOther().isEmpty()) {
				List<String> list = new ArrayList<String>();
				for (Entry<String, String> entry : getOther().entrySet()) {
					entryString = entry.getKey() + ":" + entry.getValue();
					list.add(entryString);
				}
				StorageFile.getStorageFile().getConfig().set("Other", list);
			}

			System.out.println("Storage saved successfully.");
		} catch (Exception e) {
			StorageFile.getStorageFile().save();
			StorageFile.getStorageFile().reload();

			e.printStackTrace();
			saveFile();
		}

		StorageFile.getStorageFile().save();
		StorageFile.getStorageFile().reload();
	}

	public static void loadFile() {
		if (StorageFile.getStorageFile().getConfig().isList("Medical")) {
			for (String str : StorageFile.getStorageFile().getConfig().getStringList("Medical")) {
				String split[] = str.split(":");
				getMedical().put(split[0], split[1]);
			}
		}

		if (StorageFile.getStorageFile().getConfig().isList("Security")) {
			for (String str : StorageFile.getStorageFile().getConfig().getStringList("Security")) {
				String split[] = str.split(":");
				getSecurity().put(split[0], split[1]);
			}
		}

		if (StorageFile.getStorageFile().getConfig().isList("Scientific")) {
			for (String str : StorageFile.getStorageFile().getConfig().getStringList("Scientific")) {
				String split[] = str.split(":");
				getScientific().put(split[0], split[1]);
			}
		}

		if (StorageFile.getStorageFile().getConfig().isList("Other")) {
			for (String str : StorageFile.getStorageFile().getConfig().getStringList("Other")) {
				String split[] = str.split(":");
				getOther().put(split[0], split[1]);
			}
		}

		System.out.println(" ");
		System.out.println("Storage loaded successfully.");
	}

	public static void withdrawItem(String dept, String item, String str, Player player) {
		if (player.getInventory().firstEmpty() == -1) {
			player.sendMessage("§8§l[§c§l!§8§l] §c§oYour inventory is full!");
		} else {
			String currentAmount = "0";
			Material mat = Material.getMaterial(getItemID(item));
			ItemStack iS = new ItemStack(mat, API.parse(str));
			ItemMeta iM = iS.getItemMeta();
			ArrayList<String> lore = new ArrayList<String>();
			lore.add("§7§lWithdrawn by:§6§l " + player.getName());	
			iM.setLore(lore);
			iS.setItemMeta(iM);
			switch (dept) {
			case "medical":
				currentAmount = getMedical().get(item);
				getMedical().remove(item);
				getMedical().put(item, Integer.toString(API.parse(currentAmount) - API.parse(str)));
				player.getInventory().addItem(iS);
				break;
			case "security":
				currentAmount = getSecurity().get(item);
				getSecurity().remove(item);
				getSecurity().put(item, Integer.toString(API.parse(currentAmount) - API.parse(str)));
				player.getInventory().addItem(iS);
				break;
			case "scientific":
				currentAmount = getScientific().get(item);
				getScientific().remove(item);
				getScientific().put(item, Integer.toString(API.parse(currentAmount) - API.parse(str)));
				player.getInventory().addItem(iS);
				break;
			case "other":
				currentAmount = getOther().get(item);
				getOther().remove(item);
				getOther().put(item, Integer.toString(API.parse(currentAmount) - API.parse(str)));
				player.getInventory().addItem(iS);
				break;
			default:
				break;
			}

			saveFile();
		}
	}
}