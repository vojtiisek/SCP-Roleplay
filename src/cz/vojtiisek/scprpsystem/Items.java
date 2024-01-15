package cz.vojtiisek.scprpsystem;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import cz.vojtiisek.scprpsystem.CellSystem.CellSystem;
import cz.vojtiisek.scprpsystem.Storage.GUIs.StorageDatabase;
import cz.vojtiisek.scprpsystem.files.PlayerInfoFile;
import net.luckperms.api.model.user.User;

public class Items {
	private SCPRP main;

	public Items(SCPRP main) {
		this.main = main;
	}

	public static ItemStack sideItem() {
		ItemStack sideItem = new ItemStack(Material.STAINED_GLASS_PANE, 1, DyeColor.MAGENTA.getDyeData());
		ItemMeta sideItemsideItem = sideItem.getItemMeta();
		sideItemsideItem.setDisplayName("§f§l");
		sideItem.setItemMeta(sideItemsideItem);
		return sideItem;
	}

	public static ItemStack sideItemBlack() {
		ItemStack sideItem = new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 15);
		ItemMeta sideItemsideItem = sideItem.getItemMeta();
		sideItemsideItem.setDisplayName("§f§l");
		sideItem.setItemMeta(sideItemsideItem);
		return sideItem;
	}

	public static ItemStack sideItemBlue() {
		ItemStack sideItem = new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 11);
		ItemMeta sideItemsideItem = sideItem.getItemMeta();
		sideItemsideItem.setDisplayName("§f§l");
		sideItem.setItemMeta(sideItemsideItem);
		return sideItem;
	}

	public static ItemStack sideItemYellow() {
		ItemStack sideItem = new ItemStack(Material.STAINED_GLASS_PANE, 1, DyeColor.BLUE.getDyeData());
		ItemMeta sideItemsideItem = sideItem.getItemMeta();
		sideItemsideItem.setDisplayName("§f§l");
		sideItem.setItemMeta(sideItemsideItem);
		return sideItem;
	}

	public static ItemStack infiniteCanteen() {
		ItemStack iS = new ItemStack(Material.valueOf("SCP_WATER_CANTEEN"), 1);
		ItemMeta iM = iS.getItemMeta();
		iM.setDisplayName("§f§lSCP-109");
		iM.setLore(Lores.infiniteCanteenLore());
		iS.setItemMeta(iM);
		return iS;
	}

	public static ItemStack shark() {
		ItemStack iS = new ItemStack(Material.valueOf("SCP_TOKEN_ABSENCE_OF_SHARK"), 1);
		ItemMeta iM = iS.getItemMeta();
		iM.setDisplayName("§f§lSCP-1057");
		iM.setLore(Lores.sharkLore());
		iS.setItemMeta(iM);
		return iS;
	}

	public static ItemStack swatArmor() {
		ItemStack iS = new ItemStack(Material.valueOf("SCP_TOKEN912"), 1);
		ItemMeta iM = iS.getItemMeta();
		iM.setDisplayName("§f§lSCP-912");
		iM.setLore(Lores.swatArmorLore());
		iS.setItemMeta(iM);
		return iS;
	}

	public static ItemStack halfCat() {
		ItemStack iS = new ItemStack(Material.valueOf("SCP_TOKEN529"), 1);
		ItemMeta iM = iS.getItemMeta();
		iM.setDisplayName("§f§lSCP-529");
		iM.setLore(Lores.halfCatLore());
		iS.setItemMeta(iM);
		return iS;
	}

	public static ItemStack crystallineButterflies() {
		ItemStack iS = new ItemStack(Material.valueOf("SCP_TOKEN_CRYSTALLINE_BUTTERFLIES"), 1);
		ItemMeta iM = iS.getItemMeta();
		iM.setDisplayName("§f§lSCP-553");
		iM.setLore(Lores.crystallineButterfliesLore());
		iS.setItemMeta(iM);
		return iS;
	}

	public static ItemStack electricSheep() {
		ItemStack iS = new ItemStack(Material.valueOf("SCP_TOKEN594"), 1);
		ItemMeta iM = iS.getItemMeta();
		iM.setDisplayName("§f§lSCP-594");
		iM.setLore(Lores.electricSheepLore());
		iS.setItemMeta(iM);
		return iS;
	}

	public static ItemStack predatorDrone() {
		ItemStack iS = new ItemStack(Material.valueOf("SCP_TOKEN160"), 1);
		ItemMeta iM = iS.getItemMeta();
		iM.setDisplayName("§f§lSCP-160");
		iM.setLore(Lores.predatorDroneLore());
		iS.setItemMeta(iM);
		return iS;
	}

	public static ItemStack kitItem() {
		ItemStack iS = new ItemStack(Material.CHEST, 1);
		ItemMeta iM = iS.getItemMeta();
		iM.setDisplayName("§e§lRetrieve kit");
		iM.setLore(Lores.kitLore());
		iS.setItemMeta(iM);
		return iS;
	}

	public static ItemStack addConstructionItem() {
		ItemStack iS = new ItemStack(Material.valueOf("VEHICLE_WRENCH"), 1);
		ItemMeta iM = iS.getItemMeta();
		iM.setDisplayName("§e§lAdd a new construction");
		iM.setLore(Lores.constructionItemLore());
		iS.setItemMeta(iM);
		return iS;
	}

	public static ItemStack medicalDeptItem() {
		// TECHGUNS_MILITARY_CRATE 3
		ItemStack iS = new ItemStack(Material.valueOf("MEDIS_CANTOPU"), 1);
		ItemMeta iM = iS.getItemMeta();
		iM.setDisplayName("§d§lMedical Department");
		iM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		iM.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
		iM.addItemFlags(ItemFlag.HIDE_DESTROYS);
		iM.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		iM.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		iM.addItemFlags(ItemFlag.HIDE_PLACED_ON);
		iS.setItemMeta(iM);
		return iS;
	}

	public static ItemStack securityDeptItem() {
		ItemStack iS = new ItemStack(Material.valueOf("TECHGUNS_T2_COMMANDO_HELMET"), 1);
		ItemMeta iM = iS.getItemMeta();
		iM.setDisplayName("§7§lSecurity Department");
		iM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		iM.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
		iM.addItemFlags(ItemFlag.HIDE_DESTROYS);
		iM.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		iM.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		iM.addItemFlags(ItemFlag.HIDE_PLACED_ON);
		iS.setItemMeta(iM);
		return iS;
	}

	public static ItemStack scientificDeptItem() {
		ItemStack iS = new ItemStack(Material.GLASS_BOTTLE);
		ItemMeta iM = iS.getItemMeta();
		iM.setDisplayName("§2§lScientific Department");
		iM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		iM.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
		iM.addItemFlags(ItemFlag.HIDE_DESTROYS);
		iM.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		iM.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		iM.addItemFlags(ItemFlag.HIDE_PLACED_ON);
		iS.setItemMeta(iM);
		return iS;
	}

	public static ItemStack otherDeptsItem() {
		ItemStack iS = new ItemStack(Material.valueOf("SCP_LABEL"));
		ItemMeta iM = iS.getItemMeta();
		iM.setDisplayName("§6§lOther Departments");
		iM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		iM.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
		iM.addItemFlags(ItemFlag.HIDE_DESTROYS);
		iM.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		iM.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		iM.addItemFlags(ItemFlag.HIDE_PLACED_ON);
		iS.setItemMeta(iM);
		return iS;
	}

	public static ItemStack addOrderItem() {
		// ItemStack iS = new ItemStack(Material.valueOf("VEHICLE_VEHICLE_CRATE"), 1);
		ItemStack iS = new ItemStack(Material.valueOf("SCP_BLANK_DOCUMENT"), 1);
		ItemMeta iM = iS.getItemMeta();
		iM.setDisplayName("§6§lOrder items");
		iM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		iM.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
		iM.addItemFlags(ItemFlag.HIDE_DESTROYS);
		iM.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		iM.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		iM.addItemFlags(ItemFlag.HIDE_PLACED_ON);
		iS.setItemMeta(iM);
		return iS;
	}

	public static ItemStack viewItemsItem() {
		// ItemStack iS = new ItemStack(Material.valueOf("SCP_INFO_TABLET"), 1);
		ItemStack iS = new ItemStack(Material.valueOf("SECURITYCRAFT_STORAGE_MODULE"), 1);
		ItemMeta iM = iS.getItemMeta();
		iM.setDisplayName("§f§lView items on stock");
		iM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		iM.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
		iM.addItemFlags(ItemFlag.HIDE_DESTROYS);
		iM.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		iM.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		iM.addItemFlags(ItemFlag.HIDE_PLACED_ON);
		iS.setItemMeta(iM);
		return iS;
	}

	public static ItemStack withdrawItem() {
		// ItemStack iS = new ItemStack(Material.valueOf("VEHICLE_BOOST_PAD"), 1);
		ItemStack iS = new ItemStack(Material.valueOf("SECURITYCRAFT_CODEBREAKER"), 1);
		ItemMeta iM = iS.getItemMeta();
		iM.setDisplayName("§c§lWithdraw items");
		iM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		iM.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
		iM.addItemFlags(ItemFlag.HIDE_DESTROYS);
		iM.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		iM.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		iM.addItemFlags(ItemFlag.HIDE_PLACED_ON);
		iS.setItemMeta(iM);
		return iS;
	}

	public static ItemStack bandageItem() {
		ItemStack iS = new ItemStack(Material.valueOf("MEDIS_BANDAJ"), 1);
		ItemMeta iM = iS.getItemMeta();
		iM.setDisplayName("§d§lBandage");
		List<String> lore = new ArrayList<String>();
		lore.add("§7§oCost per item: §e§l$10");
		lore.add("§7§oMaximum amount per order: §e§l256 pcs");
		lore.add("§f§lRegenerates §4§l2 §f§lHP.");
		iM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		iM.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
		iM.addItemFlags(ItemFlag.HIDE_DESTROYS);
		iM.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		iM.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		iM.addItemFlags(ItemFlag.HIDE_PLACED_ON);
		iM.setLore(lore);
		iS.setItemMeta(iM);
		return iS;
	}

	public static ItemStack firstAidKitItem() {
		ItemStack iS = new ItemStack(Material.valueOf("MEDIS_ILK_YARDIM"), 1);
		ItemMeta iM = iS.getItemMeta();
		iM.setDisplayName("§d§lFirst Aid Kit");
		List<String> lore = new ArrayList<String>();
		lore.add("§7§oCost per item: §e§l$25");
		lore.add("§7§oMaximum amount per order: §e§l128 pcs");
		lore.add("§f§lRegenerates §4§l6 §f§lHP.");
		iM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		iM.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
		iM.addItemFlags(ItemFlag.HIDE_DESTROYS);
		iM.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		iM.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		iM.addItemFlags(ItemFlag.HIDE_PLACED_ON);
		iM.setLore(lore);
		iS.setItemMeta(iM);
		return iS;
	}

	public static ItemStack medkitItem() {
		ItemStack iS = new ItemStack(Material.valueOf("MEDIS_MEDKIT"), 1);
		ItemMeta iM = iS.getItemMeta();
		iM.setDisplayName("§d§lMedkit");
		List<String> lore = new ArrayList<String>();
		lore.add("§7§oCost per item: §e§l$50");
		lore.add("§7§oMaximum amount per order: §e§l128 pcs");
		lore.add("§f§lRegenerates §4§l20 §f§lHP.");
		iM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		iM.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
		iM.addItemFlags(ItemFlag.HIDE_DESTROYS);
		iM.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		iM.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		iM.addItemFlags(ItemFlag.HIDE_PLACED_ON);
		iM.setLore(lore);
		iS.setItemMeta(iM);
		return iS;
	}

	public static ItemStack adrenalineItem() {
		ItemStack iS = new ItemStack(Material.valueOf("MEDIS_ADRENALIN"), 1);
		ItemMeta iM = iS.getItemMeta();
		iM.setDisplayName("§d§lAdrenaline");
		List<String> lore = new ArrayList<String>();
		lore.add("§7§oCost per item: §e§l$30");
		lore.add("§7§oMaximum amount per order: §e§l64 pcs");
		iM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		iM.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
		iM.addItemFlags(ItemFlag.HIDE_DESTROYS);
		iM.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		iM.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		iM.addItemFlags(ItemFlag.HIDE_PLACED_ON);
		iM.setLore(lore);
		iS.setItemMeta(iM);
		return iS;
	}

	public static ItemStack bandageCountItem() {
		ItemStack iS = new ItemStack(Material.valueOf("MEDIS_BANDAJ"), 1);
		ItemMeta iM = iS.getItemMeta();
		iM.setDisplayName("§d§lBandage");
		List<String> lore = new ArrayList<String>();
		if (StorageDatabase.getMedical().get("Bandage") == null)
			StorageDatabase.getMedical().put("Bandage", "0");
		lore.add("§6§oAmount on stock: §e§l" + StorageDatabase.getMedical().get("Bandage"));
		iM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		iM.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
		iM.addItemFlags(ItemFlag.HIDE_DESTROYS);
		iM.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		iM.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		iM.addItemFlags(ItemFlag.HIDE_PLACED_ON);
		iM.setLore(lore);
		iS.setItemMeta(iM);
		return iS;
	}

	public static ItemStack firstAidKitCountItem() {
		ItemStack iS = new ItemStack(Material.valueOf("MEDIS_ILK_YARDIM"), 1);
		ItemMeta iM = iS.getItemMeta();
		iM.setDisplayName("§d§lFirst Aid Kit");
		List<String> lore = new ArrayList<String>();
		if (StorageDatabase.getMedical().get("FirstAidKit") == null)
			StorageDatabase.getMedical().put("FirstAidKit", "0");
		lore.add("§6§oAmount on stock: §e§l" + StorageDatabase.getMedical().get("FirstAidKit"));
		iM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		iM.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
		iM.addItemFlags(ItemFlag.HIDE_DESTROYS);
		iM.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		iM.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		iM.addItemFlags(ItemFlag.HIDE_PLACED_ON);
		iM.setLore(lore);
		iS.setItemMeta(iM);
		return iS;
	}

	public static ItemStack medkitCountItem() {
		ItemStack iS = new ItemStack(Material.valueOf("MEDIS_MEDKIT"), 1);
		ItemMeta iM = iS.getItemMeta();
		iM.setDisplayName("§d§lMedkit");
		List<String> lore = new ArrayList<String>();
		if (StorageDatabase.getMedical().get("Medkit") == null)
			StorageDatabase.getMedical().put("Medkit", "0");
		lore.add("§6§oAmount on stock: §e§l" + StorageDatabase.getMedical().get("Medkit"));
		iM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		iM.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
		iM.addItemFlags(ItemFlag.HIDE_DESTROYS);
		iM.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		iM.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		iM.addItemFlags(ItemFlag.HIDE_PLACED_ON);
		iM.setLore(lore);
		iS.setItemMeta(iM);
		return iS;
	}

	public static ItemStack adrenalineCountItem() {
		ItemStack iS = new ItemStack(Material.valueOf("MEDIS_ADRENALIN"), 1);
		ItemMeta iM = iS.getItemMeta();
		iM.setDisplayName("§d§lAdrenaline");
		List<String> lore = new ArrayList<String>();
		if (StorageDatabase.getMedical().get("Adrenaline") == null)
			StorageDatabase.getMedical().put("Adrenaline", "0");
		lore.add("§6§oAmount on stock: §e§l" + StorageDatabase.getMedical().get("Adrenaline"));
		iM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		iM.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
		iM.addItemFlags(ItemFlag.HIDE_DESTROYS);
		iM.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		iM.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		iM.addItemFlags(ItemFlag.HIDE_PLACED_ON);
		iM.setLore(lore);
		iS.setItemMeta(iM);
		return iS;
	}

	public static ItemStack cheeseItem() {
		ItemStack iS = new ItemStack(Material.valueOf("SCP_CHEESE"), 1);
		ItemMeta iM = iS.getItemMeta();
		iM.setDisplayName("§e§lCheese");
		List<String> lore = new ArrayList<String>();
		lore.add("§7§oCost per item: §e§l$5");
		lore.add("§7§oMaximum amount per order: §e§l16 pcs");
		iM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		iM.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
		iM.addItemFlags(ItemFlag.HIDE_DESTROYS);
		iM.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		iM.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		iM.addItemFlags(ItemFlag.HIDE_PLACED_ON);
		iM.setLore(lore);
		iS.setItemMeta(iM);
		return iS;
	}

	public static ItemStack noPermissionOrderItem() {
		ItemStack iS = new ItemStack(Material.BARRIER, 1);
		ItemMeta iM = iS.getItemMeta();
		iM.setDisplayName("§6§lOrder items");
		List<String> lore = new ArrayList<String>();
		lore.add("§c§oYou are not authorised to order items.");
		lore.add(
				"§7§oContact any §6§lStorage Leader§7§o or the §e§lSite Director§7§o, if any items need to be ordered.");
		iM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		iM.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
		iM.addItemFlags(ItemFlag.HIDE_DESTROYS);
		iM.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		iM.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		iM.addItemFlags(ItemFlag.HIDE_PLACED_ON);
		iM.setLore(lore);
		iS.setItemMeta(iM);
		return iS;
	}

	public static ItemStack mainMenuItem() {
		ItemStack iS = new ItemStack(Material.STRUCTURE_VOID, 1);
		ItemMeta iM = iS.getItemMeta();
		iM.setDisplayName("§6§lGo back to the Main Storage Menu.");
		iM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		iM.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
		iM.addItemFlags(ItemFlag.HIDE_DESTROYS);
		iM.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		iM.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		iM.addItemFlags(ItemFlag.HIDE_PLACED_ON);
		iS.setItemMeta(iM);
		return iS;
	}

	public static ItemStack breachStateItem() {
		ItemStack iS = new ItemStack(Material.valueOf("MALISISDOORS_VERTICALHATCH"), 1);
		ItemMeta iM = iS.getItemMeta();
		iM.setDisplayName("§4§lSCP Breach state:");
		List<String> lore = new ArrayList<String>();
		if (SCPRP.getInstance().breachstate == false) {
			lore.add("§c§oFALSE §7(No ongoing breach)");
		} else {
			lore.add("§a§oTRUE §7(ongoing breach)");
		}
		iM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		iM.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
		iM.addItemFlags(ItemFlag.HIDE_DESTROYS);
		iM.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		iM.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		iM.addItemFlags(ItemFlag.HIDE_PLACED_ON);
		iM.setLore(lore);
		iS.setItemMeta(iM);
		return iS;
	}

	public static ItemStack mtfGunsItem() {
		ItemStack iS = new ItemStack(Material.valueOf("TECHGUNS_SONICSHOTGUN"), 1);
		ItemMeta iM = iS.getItemMeta();
		iM.setDisplayName("§9§lMTF Nu-7 Gun Shop");
		iM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		iM.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
		iM.addItemFlags(ItemFlag.HIDE_DESTROYS);
		iM.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		iM.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		iM.addItemFlags(ItemFlag.HIDE_PLACED_ON);
		iS.setItemMeta(iM);
		return iS;
	}

	public static ItemStack secoffGunsItem() { //
		ItemStack iS = new ItemStack(Material.valueOf("TECHGUNS_COMBATSHOTGUN"), 1);
		ItemMeta iM = iS.getItemMeta();
		iM.setDisplayName("§7§lSecurity Gun Shop");
		iM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		iM.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
		iM.addItemFlags(ItemFlag.HIDE_DESTROYS);
		iM.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		iM.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		iM.addItemFlags(ItemFlag.HIDE_PLACED_ON);
		iS.setItemMeta(iM);
		return iS;
	}

	public static ItemStack testsItem() {
		ItemStack iS = new ItemStack(Material.valueOf("SCP_DOCUMENT_THAUMIEL"), 1);
		ItemMeta iM = iS.getItemMeta();
		iM.setDisplayName("§9§lTests Database");
		iM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		iM.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
		iM.addItemFlags(ItemFlag.HIDE_DESTROYS);
		iM.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		iM.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		iM.addItemFlags(ItemFlag.HIDE_PLACED_ON);
		iS.setItemMeta(iM);
		return iS;
	}

	public static ItemStack constructionsItem() {
		ItemStack iS = new ItemStack(Material.BRICK, 1);
		ItemMeta iM = iS.getItemMeta();
		iM.setDisplayName("§6§lConstructions");
		iM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		iM.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
		iM.addItemFlags(ItemFlag.HIDE_DESTROYS);
		iM.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		iM.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		iM.addItemFlags(ItemFlag.HIDE_PLACED_ON);
		iS.setItemMeta(iM);
		return iS;
	}

	public static ItemStack designationsItem() {
		ItemStack iS = new ItemStack(Material.BOOK, 1);
		ItemMeta iM = iS.getItemMeta();
		iM.setDisplayName("§6§lClass-D Designations count:");
		List<String> lore = new ArrayList<String>();
		lore.add("§c§o" + ClassDDesignations.readCount());
		iM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		iM.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
		iM.addItemFlags(ItemFlag.HIDE_DESTROYS);
		iM.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		iM.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		iM.addItemFlags(ItemFlag.HIDE_PLACED_ON);
		iM.setLore(lore);
		iS.setItemMeta(iM);
		return iS;
	}

	public static ItemStack freeCellsItem() {
		ItemStack iS = new ItemStack(Material.IRON_FENCE, 1);
		ItemMeta iM = iS.getItemMeta();
		iM.setDisplayName("§6§lFree Class-D cells:");
		List<String> lore = new ArrayList<String>();
		lore.add("§f§oMinimum: §f§l" + CellSystem.countMinFreeCells());
		lore.add("§6§oMedium: §e§l" + CellSystem.countMedFreeCells());
		lore.add("§4§oMaximum: §c§l" + CellSystem.countMaxFreeCells());
		lore.add("§7§oJail: §c§l" + CellSystem.countJailFreeCells());
		lore.add("§8§o------------");
		lore.add("§a§lTotal: §c§l" + CellSystem.countFreeCells());
		iM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		iM.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
		iM.addItemFlags(ItemFlag.HIDE_DESTROYS);
		iM.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		iM.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		iM.addItemFlags(ItemFlag.HIDE_PLACED_ON);
		iM.setLore(lore);
		iS.setItemMeta(iM);
		return iS;
	}

	public static ItemStack discordItem() {
		ItemStack iS = new ItemStack(Material.SHULKER_SHELL, 1);
		ItemMeta iM = iS.getItemMeta();
		iM.setDisplayName("§5§lDiscord & Linking Accounts");
		List<String> lore = new ArrayList<String>();
		lore.add("§f§lClick this item to join our Discord server and link your accounts!");
		lore.add("§e§oYou will recieve a code. Send it to the Bot in the #link-accounts channel.");
		iM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		iM.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
		iM.addItemFlags(ItemFlag.HIDE_DESTROYS);
		iM.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		iM.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		iM.addItemFlags(ItemFlag.HIDE_PLACED_ON);
		iM.setLore(lore);
		iS.setItemMeta(iM);
		return iS;
	}

	public static ItemStack storageManagerItem() {
		ItemStack iS = new ItemStack(Material.CHEST, 1);
		ItemMeta iM = iS.getItemMeta();
		iM.setDisplayName("§e§lStorage Manager");
		iM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		iM.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
		iM.addItemFlags(ItemFlag.HIDE_DESTROYS);
		iM.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		iM.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		iM.addItemFlags(ItemFlag.HIDE_PLACED_ON);
		iS.setItemMeta(iM);
		return iS;
	}

	public static ItemStack playerItem(String user) {
		ItemStack iS = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
		SkullMeta playerheadmeta = (SkullMeta) iS.getItemMeta();
		playerheadmeta.setOwner(user);
		playerheadmeta.setDisplayName(user);
		iS.setItemMeta(playerheadmeta);
		ItemMeta iM = iS.getItemMeta();
		iM.setDisplayName("§f§lPlayer information");
		iM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		iM.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
		iM.addItemFlags(ItemFlag.HIDE_DESTROYS);
		iM.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		iM.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		iM.addItemFlags(ItemFlag.HIDE_PLACED_ON);
		iM.setLore(Lores.playerLore(user));
		iS.setItemMeta(iM);
		return iS;
	}

	public static ItemStack timeStatsItem(String user) {
		ItemStack iS = new ItemStack(Material.WATCH, 1);
		ItemMeta iM = iS.getItemMeta();
		iM.setDisplayName("§a§lTime data");
		iM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		iM.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
		iM.addItemFlags(ItemFlag.HIDE_DESTROYS);
		iM.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		iM.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		iM.addItemFlags(ItemFlag.HIDE_PLACED_ON);
		iM.setLore(Lores.timeLore(user));
		iS.setItemMeta(iM);
		return iS;
	}

	public static ItemStack rolesItem(String user, User target) {
		ItemStack iS = new ItemStack(Material.NAME_TAG, 1);
		ItemMeta iM = iS.getItemMeta();
		iM.setDisplayName("§c§lArea-22 Role");
		iM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		iM.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
		iM.addItemFlags(ItemFlag.HIDE_DESTROYS);
		iM.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		iM.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		iM.addItemFlags(ItemFlag.HIDE_PLACED_ON);
		iM.setLore(Lores.roleLevelLore(user, target));
		iS.setItemMeta(iM);
		return iS;
	}

	public static ItemStack naufixRankItem(String user) {
		ItemStack iS = new ItemStack(Material.NETHER_STAR, 1);
		ItemMeta iM = iS.getItemMeta();
		iM.setDisplayName("§4§lNaufix Role");
		iM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		iM.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
		iM.addItemFlags(ItemFlag.HIDE_DESTROYS);
		iM.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		iM.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		iM.addItemFlags(ItemFlag.HIDE_PLACED_ON);
		iM.setLore(Lores.NaufixRoleLore(user));
		iS.setItemMeta(iM);
		return iS;
	}

	public static ItemStack desigItem(String user) {
		ItemStack iS = new ItemStack(Material.valueOf("SCPSARMOR20_CLASSDBODY"), 1);
		ItemMeta iM = iS.getItemMeta();
		iM.setDisplayName("§6§lClass-D Designation:");
		iM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		iM.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
		iM.addItemFlags(ItemFlag.HIDE_DESTROYS);
		iM.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		iM.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		iM.addItemFlags(ItemFlag.HIDE_PLACED_ON);
		iM.setLore(Lores.desigLore(user));
		iS.setItemMeta(iM);
		return iS;
	}

	public static ItemStack cellItem(String user) {
		ItemStack iS = new ItemStack(Material.IRON_FENCE, 1);
		ItemMeta iM = iS.getItemMeta();
		iM.setDisplayName("§6§lClass-D Cell #ID:");
		iM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		iM.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
		iM.addItemFlags(ItemFlag.HIDE_DESTROYS);
		iM.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		iM.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		iM.addItemFlags(ItemFlag.HIDE_PLACED_ON);
		iM.setLore(Lores.cellIDLore(user));
		iS.setItemMeta(iM);
		return iS;
	}

	public static ItemStack currentShift(String user) {
		ItemStack iS = new ItemStack(Material.IRON_PICKAXE, 1);
		ItemMeta iM = iS.getItemMeta();
		iM.setDisplayName("§3§lCurrent Shift/Task");
		iM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		iM.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
		iM.addItemFlags(ItemFlag.HIDE_DESTROYS);
		iM.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		iM.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		iM.addItemFlags(ItemFlag.HIDE_PLACED_ON);
		iM.setLore(Lores.currentShiftLore(user));
		iS.setItemMeta(iM);
		return iS;
	}

	public static ItemStack ReceptionTaskBlock() {
		ItemStack iS = new ItemStack(Material.WOOD_DOOR, 1);
		ItemMeta iM = iS.getItemMeta();
		iM.setDisplayName("§f§lReception & Medical");
		iM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		iM.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
		iM.addItemFlags(ItemFlag.HIDE_DESTROYS);
		iM.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		iM.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		iM.addItemFlags(ItemFlag.HIDE_PLACED_ON);
		iS.setItemMeta(iM);
		return iS;
	}

	public static ItemStack S2TaskBlock() {
		ItemStack iS = new ItemStack(Material.BOOKSHELF, 1);
		ItemMeta iM = iS.getItemMeta();
		iM.setDisplayName("§a§lSector 2");
		iM.setLore(Lores.S2TaskLore());
		iM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		iM.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
		iM.addItemFlags(ItemFlag.HIDE_DESTROYS);
		iM.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		iM.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		iM.addItemFlags(ItemFlag.HIDE_PLACED_ON);
		iS.setItemMeta(iM);
		return iS;
	}

	public static ItemStack MinClassDTaskBlock() {
		ItemStack iS = new ItemStack(Material.IRON_FENCE, 1);
		ItemMeta iM = iS.getItemMeta();
		iM.setDisplayName("§a§lMinimum Class-D Cells");
		iM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		iM.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
		iM.addItemFlags(ItemFlag.HIDE_DESTROYS);
		iM.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		iM.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		iM.addItemFlags(ItemFlag.HIDE_PLACED_ON);
		iS.setItemMeta(iM);
		return iS;
	}

	public static ItemStack ScientificTaskBlock() {
		ItemStack iS = new ItemStack(Material.GLASS_BOTTLE, 1);
		ItemMeta iM = iS.getItemMeta();
		iM.setDisplayName("§b§lScientific Department");
		iM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		iM.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
		iM.addItemFlags(ItemFlag.HIDE_DESTROYS);
		iM.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		iM.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		iM.addItemFlags(ItemFlag.HIDE_PLACED_ON);
		iS.setItemMeta(iM);
		return iS;
	}

	public static ItemStack MedClassDTaskBlock() {
		ItemStack iS = new ItemStack(Material.valueOf("MALISISDOORS_JAIL_DOOR"), 1);
		ItemMeta iM = iS.getItemMeta();
		iM.setDisplayName("§e§lMedium Class-D cells");
		iM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		iM.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
		iM.addItemFlags(ItemFlag.HIDE_DESTROYS);
		iM.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		iM.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		iM.addItemFlags(ItemFlag.HIDE_PLACED_ON);
		iS.setItemMeta(iM);
		return iS;
	}

	public static ItemStack S3TaskBlock() {
		ItemStack iS = new ItemStack(Material.valueOf("SCP_TOKEN594"), 1);
		ItemMeta iM = iS.getItemMeta();
		iM.setDisplayName("§7§lSector 3 + LCZ");
		iM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		iM.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
		iM.addItemFlags(ItemFlag.HIDE_DESTROYS);
		iM.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		iM.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		iM.addItemFlags(ItemFlag.HIDE_PLACED_ON);
		iS.setItemMeta(iM);
		return iS;
	}

	public static ItemStack ETSTaskBlock() {
		ItemStack iS = new ItemStack(Material.valueOf("VEHICLE_WRENCH"), 1);
		ItemMeta iM = iS.getItemMeta();
		iM.setDisplayName("§6§lE&Ts Department");
		iM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		iM.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
		iM.addItemFlags(ItemFlag.HIDE_DESTROYS);
		iM.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		iM.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		iM.addItemFlags(ItemFlag.HIDE_PLACED_ON);
		iS.setItemMeta(iM);
		return iS;
	}

	public static ItemStack MaxClassDTaskBlock() {
		ItemStack iS = new ItemStack(Material.valueOf("MALISISDOORS_VERTICALHATCH"), 1);
		ItemMeta iM = iS.getItemMeta();
		iM.setDisplayName("§4§lMaximum Class-D cells");
		iM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		iM.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
		iM.addItemFlags(ItemFlag.HIDE_DESTROYS);
		iM.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		iM.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		iM.addItemFlags(ItemFlag.HIDE_PLACED_ON);
		iS.setItemMeta(iM);
		return iS;
	}

	public static ItemStack HCZTaskBlock() {
		ItemStack iS = new ItemStack(Material.valueOf("SCP_TOKEN_SHY_GUY"), 1);
		ItemMeta iM = iS.getItemMeta();
		iM.setDisplayName("§c§lSector 4 + HCZ");
		iM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		iM.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
		iM.addItemFlags(ItemFlag.HIDE_DESTROYS);
		iM.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		iM.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		iM.addItemFlags(ItemFlag.HIDE_PLACED_ON);
		iS.setItemMeta(iM);
		return iS;
	}

	public static ItemStack S5TaskBlock() {
		ItemStack iS = new ItemStack(Material.valueOf("SCP_LEVEL5CARD"), 1);
		ItemMeta iM = iS.getItemMeta();
		iM.setDisplayName("§4§lSector 5");
		iM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		iM.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
		iM.addItemFlags(ItemFlag.HIDE_DESTROYS);
		iM.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		iM.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		iM.addItemFlags(ItemFlag.HIDE_PLACED_ON);
		iS.setItemMeta(iM);
		return iS;
	}

	public static ItemStack peanut() {
		ItemStack iS = new ItemStack(Material.valueOf("SCP_TOKEN_SCULPTURE"), 1);
		ItemMeta iM = iS.getItemMeta();
		iM.setDisplayName("§f§lSCP-173");
		iM.setLore(Lores.peanutLore());
		iS.setItemMeta(iM);
		return iS;
	}

	public static ItemStack PeanutToolsItem() {
		ItemStack iS = new ItemStack(Material.valueOf("SCP_CHEESE"), 1);
		ItemMeta iM = iS.getItemMeta();
		iM.setDisplayName("§7§lSCP-173 Toolkit");
		List<String> lore = new ArrayList<String>();
		lore.add("§7§oCost per item: §e§l$50");
		lore.add("§7§oMaximum amount per order: §e§l2 pcs");
		iM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		iM.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
		iM.addItemFlags(ItemFlag.HIDE_DESTROYS);
		iM.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		iM.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		iM.addItemFlags(ItemFlag.HIDE_PLACED_ON);
		iM.setLore(lore);
		iS.setItemMeta(iM);
		return iS;
	}

	public static ItemStack woodenPickaxe() {
		ItemStack iS = new ItemStack(Material.WOOD_PICKAXE, 1);
		ItemMeta iM = iS.getItemMeta();
		iM.setDisplayName("§6§lWooden Pickaxe");
		List<String> lore = new ArrayList<String>();
		lore.add("§c§oNeeded for SCP-173 Test #1.");
		lore.add("§7§oCost per item: §e§l$5");
		lore.add("§7§oMaximum amount per order: §e§l5 pcs");
		iM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		iM.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
		iM.addItemFlags(ItemFlag.HIDE_DESTROYS);
		iM.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		iM.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		iM.addItemFlags(ItemFlag.HIDE_PLACED_ON);
		iM.setLore(lore);
		iS.setItemMeta(iM);
		return iS;
	}

	public static ItemStack stonePickaxe() {
		ItemStack iS = new ItemStack(Material.STONE_PICKAXE, 1);
		ItemMeta iM = iS.getItemMeta();
		iM.setDisplayName("§7§lStone Pickaxe");
		List<String> lore = new ArrayList<String>();
		lore.add("§c§oNeeded for SCP-173 Test #1.");
		lore.add("§7§oCost per item: §e§l$6");
		lore.add("§7§oMaximum amount per order: §e§l5 pcs");
		iM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		iM.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
		iM.addItemFlags(ItemFlag.HIDE_DESTROYS);
		iM.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		iM.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		iM.addItemFlags(ItemFlag.HIDE_PLACED_ON);
		iM.setLore(lore);
		iS.setItemMeta(iM);
		return iS;
	}

	public static ItemStack ironAxe() {
		ItemStack iS = new ItemStack(Material.IRON_AXE, 1);
		ItemMeta iM = iS.getItemMeta();
		iM.setDisplayName("§f§lIron Axe");
		List<String> lore = new ArrayList<String>();
		lore.add("§c§oNeeded for SCP-173 Test #1.");
		lore.add("§7§oCost per item: §e§l$8");
		lore.add("§7§oMaximum amount per order: §e§l5 pcs");
		iM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		iM.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
		iM.addItemFlags(ItemFlag.HIDE_DESTROYS);
		iM.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		iM.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		iM.addItemFlags(ItemFlag.HIDE_PLACED_ON);
		iM.setLore(lore);
		iS.setItemMeta(iM);
		return iS;
	}

	public static ItemStack ironPickaxe() {
		ItemStack iS = new ItemStack(Material.IRON_PICKAXE, 1);
		ItemMeta iM = iS.getItemMeta();
		iM.setDisplayName("§f§lIron Pickaxe");
		List<String> lore = new ArrayList<String>();
		lore.add("§c§oNeeded for SCP-173 Test #1.");
		lore.add("§7§oCost per item: §e§l$8");
		lore.add("§7§oMaximum amount per order: §e§l5 pcs");
		iM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		iM.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
		iM.addItemFlags(ItemFlag.HIDE_DESTROYS);
		iM.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		iM.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		iM.addItemFlags(ItemFlag.HIDE_PLACED_ON);
		iM.setLore(lore);
		iS.setItemMeta(iM);
		return iS;
	}

	public static ItemStack goldenSword() {
		ItemStack iS = new ItemStack(Material.GOLD_SWORD, 1);
		ItemMeta iM = iS.getItemMeta();
		iM.setDisplayName("§e§lGolden Sword");
		List<String> lore = new ArrayList<String>();
		lore.add("§c§oNeeded for SCP-173 Test #1.");
		lore.add("§7§oCost per item: §e§l$12");
		lore.add("§7§oMaximum amount per order: §e§l5 pcs");
		iM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		iM.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
		iM.addItemFlags(ItemFlag.HIDE_DESTROYS);
		iM.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		iM.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		iM.addItemFlags(ItemFlag.HIDE_PLACED_ON);
		iM.setLore(lore);
		iS.setItemMeta(iM);
		return iS;
	}

	public static ItemStack crowbar() {
		ItemStack iS = new ItemStack(Material.valueOf("TECHGUNS_CROWBAR"), 1);
		ItemMeta iM = iS.getItemMeta();
		iM.setDisplayName("§4§lCrowbar");
		List<String> lore = new ArrayList<String>();
		lore.add("§c§oNeeded for SCP-173 Test #1.");
		lore.add("§7§oCost per item: §e§l$10");
		lore.add("§7§oMaximum amount per order: §e§l5 pcs");
		iM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		iM.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
		iM.addItemFlags(ItemFlag.HIDE_DESTROYS);
		iM.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		iM.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		iM.addItemFlags(ItemFlag.HIDE_PLACED_ON);
		iM.setLore(lore);
		iS.setItemMeta(iM);
		return iS;
	}

	public static ItemStack diamondShovel() {
		ItemStack iS = new ItemStack(Material.DIAMOND_SPADE, 1);
		ItemMeta iM = iS.getItemMeta();
		iM.setDisplayName("§b§lDiamond Shovel");
		List<String> lore = new ArrayList<String>();
		lore.add("§c§oNeeded for SCP-173 Test #1.");
		lore.add("§7§oCost per item: §e§l$20");
		lore.add("§7§oMaximum amount per order: §e§l5 pcs");
		iM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		iM.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
		iM.addItemFlags(ItemFlag.HIDE_DESTROYS);
		iM.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		iM.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		iM.addItemFlags(ItemFlag.HIDE_PLACED_ON);
		iM.setLore(lore);
		iS.setItemMeta(iM);
		return iS;
	}

	public static ItemStack cheeseItemCount() {
		ItemStack iS = new ItemStack(Material.valueOf("SCP_CHEESE"), 1);
		ItemMeta iM = iS.getItemMeta();
		iM.setDisplayName("§e§lCheese");
		List<String> lore = new ArrayList<String>();
		if (StorageDatabase.getScientific().get("Cheese") == null)
			StorageDatabase.getScientific().put("Cheese", "0");
		lore.add("§6§oAmount on stock: §e§l" + StorageDatabase.getScientific().get("Cheese"));
		iM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		iM.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
		iM.addItemFlags(ItemFlag.HIDE_DESTROYS);
		iM.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		iM.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		iM.addItemFlags(ItemFlag.HIDE_PLACED_ON);
		iM.setLore(lore);
		iS.setItemMeta(iM);
		return iS;
	}

	public static ItemStack woodenPickaxeCount() {
		ItemStack iS = new ItemStack(Material.WOOD_PICKAXE, 1);
		ItemMeta iM = iS.getItemMeta();
		iM.setDisplayName("§6§lWooden Pickaxe");
		List<String> lore = new ArrayList<String>();
		if (StorageDatabase.getScientific().get("WoodPickaxe") == null)
			StorageDatabase.getScientific().put("WoodPickaxe", "0");
		lore.add("§6§oAmount on stock: §e§l" + StorageDatabase.getScientific().get("WoodPickaxe"));
		iM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		iM.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
		iM.addItemFlags(ItemFlag.HIDE_DESTROYS);
		iM.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		iM.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		iM.addItemFlags(ItemFlag.HIDE_PLACED_ON);
		iM.setLore(lore);
		iS.setItemMeta(iM);
		return iS;
	}

	public static ItemStack stonePickaxeCount() {
		ItemStack iS = new ItemStack(Material.STONE_PICKAXE, 1);
		ItemMeta iM = iS.getItemMeta();
		iM.setDisplayName("§7§lStone Pickaxe");
		List<String> lore = new ArrayList<String>();
		if (StorageDatabase.getScientific().get("StonePickaxe") == null)
			StorageDatabase.getScientific().put("StonePickaxe", "0");
		lore.add("§6§oAmount on stock: §e§l" + StorageDatabase.getScientific().get("StonePickaxe"));
		iM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		iM.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
		iM.addItemFlags(ItemFlag.HIDE_DESTROYS);
		iM.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		iM.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		iM.addItemFlags(ItemFlag.HIDE_PLACED_ON);
		iM.setLore(lore);
		iS.setItemMeta(iM);
		return iS;
	}

	public static ItemStack ironAxeCount() {
		ItemStack iS = new ItemStack(Material.IRON_AXE, 1);
		ItemMeta iM = iS.getItemMeta();
		iM.setDisplayName("§f§lIron Axe");
		List<String> lore = new ArrayList<String>();
		if (StorageDatabase.getScientific().get("IronAxe") == null)
			StorageDatabase.getScientific().put("IronAxe", "0");
		lore.add("§6§oAmount on stock: §e§l" + StorageDatabase.getScientific().get("IronAxe"));
		iM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		iM.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
		iM.addItemFlags(ItemFlag.HIDE_DESTROYS);
		iM.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		iM.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		iM.addItemFlags(ItemFlag.HIDE_PLACED_ON);
		iM.setLore(lore);
		iS.setItemMeta(iM);
		return iS;
	}

	public static ItemStack ironPickaxeCount() {
		ItemStack iS = new ItemStack(Material.IRON_PICKAXE, 1);
		ItemMeta iM = iS.getItemMeta();
		iM.setDisplayName("§f§lIron Pickaxe");
		List<String> lore = new ArrayList<String>();
		if (StorageDatabase.getScientific().get("IronPickaxe") == null)
			StorageDatabase.getScientific().put("IronPickaxe", "0");
		lore.add("§6§oAmount on stock: §e§l" + StorageDatabase.getScientific().get("IronPickaxe"));
		iM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		iM.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
		iM.addItemFlags(ItemFlag.HIDE_DESTROYS);
		iM.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		iM.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		iM.addItemFlags(ItemFlag.HIDE_PLACED_ON);
		iM.setLore(lore);
		iS.setItemMeta(iM);
		return iS;
	}

	public static ItemStack goldenSwordCount() {
		ItemStack iS = new ItemStack(Material.GOLD_SWORD, 1);
		ItemMeta iM = iS.getItemMeta();
		iM.setDisplayName("§e§lGolden Sword");
		List<String> lore = new ArrayList<String>();
		if (StorageDatabase.getScientific().get("GoldSword") == null)
			StorageDatabase.getScientific().put("GoldSword", "0");
		lore.add("§6§oAmount on stock: §e§l" + StorageDatabase.getScientific().get("GoldSword"));
		iM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		iM.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
		iM.addItemFlags(ItemFlag.HIDE_DESTROYS);
		iM.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		iM.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		iM.addItemFlags(ItemFlag.HIDE_PLACED_ON);
		iM.setLore(lore);
		iS.setItemMeta(iM);
		return iS;
	}

	public static ItemStack crowbarCount() {
		ItemStack iS = new ItemStack(Material.valueOf("TECHGUNS_CROWBAR"), 1);
		ItemMeta iM = iS.getItemMeta();
		iM.setDisplayName("§4§lCrowbar");
		List<String> lore = new ArrayList<String>();
		if (StorageDatabase.getScientific().get("Crowbar") == null)
			StorageDatabase.getScientific().put("Crowbar", "0");
		lore.add("§6§oAmount on stock: §e§l" + StorageDatabase.getScientific().get("Crowbar"));
		iM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		iM.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
		iM.addItemFlags(ItemFlag.HIDE_DESTROYS);
		iM.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		iM.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		iM.addItemFlags(ItemFlag.HIDE_PLACED_ON);
		iM.setLore(lore);
		iS.setItemMeta(iM);
		return iS;
	}

	public static ItemStack diamondShovelCount() {
		ItemStack iS = new ItemStack(Material.DIAMOND_SPADE, 1);
		ItemMeta iM = iS.getItemMeta();
		iM.setDisplayName("§b§lDiamond Shovel");
		List<String> lore = new ArrayList<String>();
		if (StorageDatabase.getScientific().get("DiamondShovel") == null)
			StorageDatabase.getScientific().put("DiamondShovel", "0");
		lore.add("§6§oAmount on stock: §e§l" + StorageDatabase.getScientific().get("DiamondShovel"));
		iM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		iM.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
		iM.addItemFlags(ItemFlag.HIDE_DESTROYS);
		iM.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		iM.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		iM.addItemFlags(ItemFlag.HIDE_PLACED_ON);
		iM.setLore(lore);
		iS.setItemMeta(iM);
		return iS;
	}

	public static ItemStack networkCompleteTool() {
		ItemStack iS = new ItemStack(Material.DIAMOND_HOE, 1);
		ItemMeta iM = iS.getItemMeta();
		iM.setDisplayName("§a§lNetwork Completion tool");
		iM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		iM.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
		iM.addItemFlags(ItemFlag.HIDE_DESTROYS);
		iM.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		iM.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		iM.addItemFlags(ItemFlag.HIDE_PLACED_ON);
		iM.setLore(Lores.networkCompleteToolLore());
		iS.setItemMeta(iM);
		return iS;
	}

	public static ItemStack carrot() {
		ItemStack iS = new ItemStack(Material.CARROT_ITEM, 1);
		ItemMeta iM = iS.getItemMeta();
		iM.setDisplayName("§6§lCarrot");
		List<String> lore = new ArrayList<String>();
		lore.add("§7§oCost per item: §e§l$0.5");
		lore.add("§7§oMaximum amount per order: §e§l256 pcs");
		iM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		iM.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
		iM.addItemFlags(ItemFlag.HIDE_DESTROYS);
		iM.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		iM.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		iM.addItemFlags(ItemFlag.HIDE_PLACED_ON);
		iM.setLore(lore);
		iS.setItemMeta(iM);
		return iS;
	}

	public static ItemStack carrotCount(boolean b) {
		ItemStack iS = new ItemStack(Material.CARROT_ITEM, 1);
		ItemMeta iM = iS.getItemMeta();
		iM.setDisplayName("§6§lCarrot");
		List<String> lore = new ArrayList<String>();
		if (StorageDatabase.getOther().get("Carrot") == null)
			StorageDatabase.getOther().put("Carrot", "0");
		lore.add("§6§oAmount on stock: §e§l" + StorageDatabase.getOther().get("Carrot"));
		if (b) {
			lore.add("§6§lLeft click§e§o to select as the next food. §f§oThis resets your preferred food.");
			lore.add("§c§lRight Click§e§o to select as the preferred food. §f§oUse §e§l/food§f§o to change it.");
		}
		iM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		iM.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
		iM.addItemFlags(ItemFlag.HIDE_DESTROYS);
		iM.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		iM.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		iM.addItemFlags(ItemFlag.HIDE_PLACED_ON);
		iM.setLore(lore);
		iS.setItemMeta(iM);
		return iS;
	}

	public static ItemStack apple() {
		ItemStack iS = new ItemStack(Material.APPLE, 1);
		ItemMeta iM = iS.getItemMeta();
		iM.setDisplayName("§4Apple");
		List<String> lore = new ArrayList<String>();
		lore.add("§7§oCost per item: §e§l$0.6");
		lore.add("§7§oMaximum amount per order: §e§l256 pcs");
		iM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		iM.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
		iM.addItemFlags(ItemFlag.HIDE_DESTROYS);
		iM.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		iM.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		iM.addItemFlags(ItemFlag.HIDE_PLACED_ON);
		iM.setLore(lore);
		iS.setItemMeta(iM);
		return iS;
	}

	public static ItemStack appleCount(boolean b) {
		ItemStack iS = new ItemStack(Material.APPLE, 1);
		ItemMeta iM = iS.getItemMeta();
		iM.setDisplayName("§4Apple");
		List<String> lore = new ArrayList<String>();
		if (StorageDatabase.getOther().get("Apple") == null)
			StorageDatabase.getOther().put("Apple", "0");
		lore.add("§6§oAmount on stock: §e§l" + StorageDatabase.getOther().get("Apple"));
		if (b) {
			lore.add("§6§lLeft click§e§o to select as the next food. §f§oThis resets your preferred food.");
			lore.add("§c§lRight Click§e§o to select as the preferred food. §f§oUse §e§l/food§f§o to change it.");
		}
		iM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		iM.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
		iM.addItemFlags(ItemFlag.HIDE_DESTROYS);
		iM.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		iM.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		iM.addItemFlags(ItemFlag.HIDE_PLACED_ON);
		iM.setLore(lore);
		iS.setItemMeta(iM);
		return iS;
	}

	public static ItemStack bread() {
		ItemStack iS = new ItemStack(Material.BREAD, 1);
		ItemMeta iM = iS.getItemMeta();
		iM.setDisplayName("§2Bread");
		List<String> lore = new ArrayList<String>();
		lore.add("§7§oCost per item: §e§l$1");
		lore.add("§7§oMaximum amount per order: §e§l256 pcs");
		iM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		iM.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
		iM.addItemFlags(ItemFlag.HIDE_DESTROYS);
		iM.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		iM.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		iM.addItemFlags(ItemFlag.HIDE_PLACED_ON);
		iM.setLore(lore);
		iS.setItemMeta(iM);
		return iS;
	}

	public static ItemStack breadCount(boolean b) {
		ItemStack iS = new ItemStack(Material.BREAD, 1);
		ItemMeta iM = iS.getItemMeta();
		iM.setDisplayName("§2Bread");
		List<String> lore = new ArrayList<String>();
		if (StorageDatabase.getOther().get("Bread") == null)
			StorageDatabase.getOther().put("Bread", "0");
		lore.add("§6§oAmount on stock: §e§l" + StorageDatabase.getOther().get("Bread"));
		if (b) {
			lore.add("§6§lLeft click§e§o to select as the next food. §f§oThis resets your preferred food.");
			lore.add("§c§lRight Click§e§o to select as the preferred food. §f§oUse §e§l/food§f§o to change it.");
		}
		iM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		iM.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
		iM.addItemFlags(ItemFlag.HIDE_DESTROYS);
		iM.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		iM.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		iM.addItemFlags(ItemFlag.HIDE_PLACED_ON);
		iM.setLore(lore);
		iS.setItemMeta(iM);
		return iS;
	}

	public static ItemStack bakedPotato() {
		ItemStack iS = new ItemStack(Material.BAKED_POTATO, 1);
		ItemMeta iM = iS.getItemMeta();
		iM.setDisplayName("§e§lBaked Potato");
		List<String> lore = new ArrayList<String>();
		lore.add("§7§oCost per item: §e§l$1");
		lore.add("§7§oMaximum amount per order: §e§l256 pcs");
		iM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		iM.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
		iM.addItemFlags(ItemFlag.HIDE_DESTROYS);
		iM.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		iM.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		iM.addItemFlags(ItemFlag.HIDE_PLACED_ON);
		iM.setLore(lore);
		iS.setItemMeta(iM);
		return iS;
	}

	public static ItemStack bakedPotatoCount(boolean b) {
		ItemStack iS = new ItemStack(Material.BAKED_POTATO, 1);
		ItemMeta iM = iS.getItemMeta();
		iM.setDisplayName("§e§lBaked Potato");
		List<String> lore = new ArrayList<String>();
		if (StorageDatabase.getOther().get("BakedPotato") == null)
			StorageDatabase.getOther().put("BakedPotato", "0");
		lore.add("§6§oAmount on stock: §e§l" + StorageDatabase.getOther().get("BakedPotato"));
		if (b) {
			lore.add("§6§lLeft click§e§o to select as the next food. §f§oThis resets your preferred food.");
			lore.add("§c§lRight Click§e§o to select as the preferred food. §f§oUse §e§l/food§f§o to change it.");
		}
		iM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		iM.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
		iM.addItemFlags(ItemFlag.HIDE_DESTROYS);
		iM.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		iM.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		iM.addItemFlags(ItemFlag.HIDE_PLACED_ON);
		iM.setLore(lore);
		iS.setItemMeta(iM);
		return iS;
	}

	public static ItemStack cookedChicken() {
		ItemStack iS = new ItemStack(Material.COOKED_CHICKEN, 1);
		ItemMeta iM = iS.getItemMeta();
		iM.setDisplayName("§cCooked Chicken");
		List<String> lore = new ArrayList<String>();
		lore.add("§7§oCost per item: §e§l$1");
		lore.add("§7§oMaximum amount per order: §e§l256 pcs");
		iM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		iM.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
		iM.addItemFlags(ItemFlag.HIDE_DESTROYS);
		iM.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		iM.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		iM.addItemFlags(ItemFlag.HIDE_PLACED_ON);
		iM.setLore(lore);
		iS.setItemMeta(iM);
		return iS;
	}

	public static ItemStack cookedChickenCount(boolean b) {
		ItemStack iS = new ItemStack(Material.COOKED_CHICKEN, 1);
		ItemMeta iM = iS.getItemMeta();
		iM.setDisplayName("§cCooked Chicken");
		List<String> lore = new ArrayList<String>();
		if (StorageDatabase.getOther().get("CookedChicken") == null)
			StorageDatabase.getOther().put("CookedChicken", "0");
		lore.add("§6§oAmount on stock: §e§2" + StorageDatabase.getOther().get("CookedChicken"));
		if (b) {
			lore.add("§6§lLeft click§e§o to select as the next food. §f§oThis resets your preferred food.");
			lore.add("§c§lRight Click§e§o to select as the preferred food. §f§oUse §e§l/food§f§o to change it.");
		}
		iM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		iM.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
		iM.addItemFlags(ItemFlag.HIDE_DESTROYS);
		iM.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		iM.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		iM.addItemFlags(ItemFlag.HIDE_PLACED_ON);
		iM.setLore(lore);
		iS.setItemMeta(iM);
		return iS;
	}

	public static ItemStack cookedFish() {
		ItemStack iS = new ItemStack(Material.COOKED_FISH, 1);
		ItemMeta iM = iS.getItemMeta();
		iM.setDisplayName("§3Cooked Fish");
		List<String> lore = new ArrayList<String>();
		lore.add("§7§oCost per item: §e§l$2");
		lore.add("§7§oMaximum amount per order: §e§l256 pcs");
		iM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		iM.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
		iM.addItemFlags(ItemFlag.HIDE_DESTROYS);
		iM.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		iM.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		iM.addItemFlags(ItemFlag.HIDE_PLACED_ON);
		iM.setLore(lore);
		iS.setItemMeta(iM);
		return iS;
	}

	public static ItemStack cookedFishCount(boolean b) {
		ItemStack iS = new ItemStack(Material.COOKED_FISH, 1);
		ItemMeta iM = iS.getItemMeta();
		iM.setDisplayName("§3Cooked Fish");
		List<String> lore = new ArrayList<String>();
		if (StorageDatabase.getOther().get("CookedFish") == null)
			StorageDatabase.getOther().put("CookedFish", "0");
		lore.add("§6§oAmount on stock: §e§l" + StorageDatabase.getOther().get("CookedFish"));
		if (b) {
			lore.add("§6§lLeft click§e§o to select as the next food. §f§oThis resets your preferred food.");
			lore.add("§c§lRight Click§e§o to select as the preferred food. §f§oUse §e§l/food§f§o to change it.");
		}
		iM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		iM.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
		iM.addItemFlags(ItemFlag.HIDE_DESTROYS);
		iM.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		iM.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		iM.addItemFlags(ItemFlag.HIDE_PLACED_ON);
		iM.setLore(lore);
		iS.setItemMeta(iM);
		return iS;
	}

	public static ItemStack pPie() {
		ItemStack iS = new ItemStack(Material.PUMPKIN_PIE, 1);
		ItemMeta iM = iS.getItemMeta();
		iM.setDisplayName("§6§lPumpkin Pie");
		List<String> lore = new ArrayList<String>();
		lore.add("§7§oCost per item: §e§l$2.5");
		lore.add("§7§oMaximum amount per order: §e§l128 pcs");
		iM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		iM.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
		iM.addItemFlags(ItemFlag.HIDE_DESTROYS);
		iM.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		iM.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		iM.addItemFlags(ItemFlag.HIDE_PLACED_ON);
		iM.setLore(lore);
		iS.setItemMeta(iM);
		return iS;
	}

	public static ItemStack pPieCount(boolean b) {
		ItemStack iS = new ItemStack(Material.PUMPKIN_PIE, 1);
		ItemMeta iM = iS.getItemMeta();
		iM.setDisplayName("§6§lPumpkin Pie");
		List<String> lore = new ArrayList<String>();
		if (StorageDatabase.getOther().get("PumpkinPie") == null)
			StorageDatabase.getOther().put("PumpkinPie", "0");
		lore.add("§6§oAmount on stock: §e§l" + StorageDatabase.getOther().get("PumpkinPie"));
		if (b) {
			lore.add("§6§lLeft click§e§o to select as the next food. §f§oThis resets your preferred food.");
			lore.add("§c§lRight Click§e§o to select as the preferred food. §f§oUse §e§l/food§f§o to change it.");
		}
		iM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		iM.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
		iM.addItemFlags(ItemFlag.HIDE_DESTROYS);
		iM.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		iM.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		iM.addItemFlags(ItemFlag.HIDE_PLACED_ON);
		iM.setLore(lore);
		iS.setItemMeta(iM);
		return iS;
	}

	public static ItemStack cookedPorkchop() {
		ItemStack iS = new ItemStack(Material.GRILLED_PORK, 1);
		ItemMeta iM = iS.getItemMeta();
		iM.setDisplayName("§7§lCooked Porkchop");
		List<String> lore = new ArrayList<String>();
		lore.add("§7§oCost per item: §e§l$3");
		lore.add("§7§oMaximum amount per order: §e§l128 pcs");
		iM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		iM.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
		iM.addItemFlags(ItemFlag.HIDE_DESTROYS);
		iM.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		iM.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		iM.addItemFlags(ItemFlag.HIDE_PLACED_ON);
		iM.setLore(lore);
		iS.setItemMeta(iM);
		return iS;
	}

	public static ItemStack cookedPorkchopCount(boolean b) {
		ItemStack iS = new ItemStack(Material.GRILLED_PORK, 1);
		ItemMeta iM = iS.getItemMeta();
		iM.setDisplayName("§7§lCooked Porkchop");
		List<String> lore = new ArrayList<String>();
		if (StorageDatabase.getOther().get("CookPork") == null)
			StorageDatabase.getOther().put("CookPork", "0");
		lore.add("§6§oAmount on stock: §e§l" + StorageDatabase.getOther().get("CookPork"));
		if (b) {
			lore.add("§6§lLeft click§e§o to select as the next food. §f§oThis resets your preferred food.");
			lore.add("§c§lRight Click§e§o to select as the preferred food. §f§oUse §e§l/food§f§o to change it.");
		}
		iM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		iM.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
		iM.addItemFlags(ItemFlag.HIDE_DESTROYS);
		iM.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		iM.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		iM.addItemFlags(ItemFlag.HIDE_PLACED_ON);
		iM.setLore(lore);
		iS.setItemMeta(iM);
		return iS;
	}

	public static ItemStack steak() {
		ItemStack iS = new ItemStack(Material.COOKED_BEEF, 1);
		ItemMeta iM = iS.getItemMeta();
		iM.setDisplayName("§8Steak");
		List<String> lore = new ArrayList<String>();
		lore.add("§7§oCost per item: §e§l$3");
		lore.add("§7§oMaximum amount per order: §e§l128 pcs");
		iM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		iM.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
		iM.addItemFlags(ItemFlag.HIDE_DESTROYS);
		iM.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		iM.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		iM.addItemFlags(ItemFlag.HIDE_PLACED_ON);
		iM.setLore(lore);
		iS.setItemMeta(iM);
		return iS;
	}

	public static ItemStack steakCount(boolean b) {
		ItemStack iS = new ItemStack(Material.COOKED_BEEF, 1);
		ItemMeta iM = iS.getItemMeta();
		iM.setDisplayName("§8Steak");
		List<String> lore = new ArrayList<String>();
		if (StorageDatabase.getOther().get("Steak") == null)
			StorageDatabase.getOther().put("Steak", "0");
		lore.add("§6§oAmount on stock: §e§l" + StorageDatabase.getOther().get("Steak"));
		if (b) {
			lore.add("§6§lLeft click§e§o to select as the next food. §f§oThis resets your preferred food.");
			lore.add("§c§lRight Click§e§o to select as the preferred food. §f§oUse §e§l/food§f§o to change it.");
		}
		iM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		iM.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
		iM.addItemFlags(ItemFlag.HIDE_DESTROYS);
		iM.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		iM.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		iM.addItemFlags(ItemFlag.HIDE_PLACED_ON);
		iM.setLore(lore);
		iS.setItemMeta(iM);
		return iS;
	}

	public static ItemStack rabbitStew() {
		ItemStack iS = new ItemStack(Material.RABBIT_STEW, 1);
		ItemMeta iM = iS.getItemMeta();
		iM.setDisplayName("§a§lRabbit Stew");
		List<String> lore = new ArrayList<String>();
		lore.add("§7§oCost per item: §e§l$5");
		lore.add("§7§oMaximum amount per order: §e§l128 pcs");
		iM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		iM.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
		iM.addItemFlags(ItemFlag.HIDE_DESTROYS);
		iM.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		iM.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		iM.addItemFlags(ItemFlag.HIDE_PLACED_ON);
		iM.setLore(lore);
		iS.setItemMeta(iM);
		return iS;
	}

	public static ItemStack rabbitStewCount(boolean b) {
		ItemStack iS = new ItemStack(Material.RABBIT_STEW, 1);
		ItemMeta iM = iS.getItemMeta();
		iM.setDisplayName("§a§lRabbit Stew");
		List<String> lore = new ArrayList<String>();
		if (StorageDatabase.getOther().get("RabbitStew") == null)
			StorageDatabase.getOther().put("RabbitStew", "0");
		lore.add("§6§oAmount on stock: §e§l" + StorageDatabase.getOther().get("RabbitStew"));
		if (b) {
			lore.add("§6§lLeft click§e§o to select as the next food. §f§oThis resets your preferred food.");
			lore.add("§c§lRight Click§e§o to select as the preferred food. §f§oUse §e§l/food§f§o to change it.");
		}
		iM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		iM.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
		iM.addItemFlags(ItemFlag.HIDE_DESTROYS);
		iM.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		iM.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		iM.addItemFlags(ItemFlag.HIDE_PLACED_ON);
		iM.setLore(lore);
		iS.setItemMeta(iM);
		return iS;
	}

	public static ItemStack animalNetBig() {
		ItemStack iS = new ItemStack(Material.valueOf("ANIMALNET_ANIMAL_NET_BIG"), 1);
		ItemMeta iM = iS.getItemMeta();
		iM.setDisplayName("§7§lBig Animal Net");
		List<String> lore = new ArrayList<String>();
		lore.add("§7§oCost per item: §e§l$40");
		lore.add("§7§oMaximum amount per order: §e§l64 pcs");
		iM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		iM.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
		iM.addItemFlags(ItemFlag.HIDE_DESTROYS);
		iM.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		iM.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		iM.addItemFlags(ItemFlag.HIDE_PLACED_ON);
		iM.setLore(lore);
		iS.setItemMeta(iM);
		return iS;
	}

	public static ItemStack animalNetBigCount() {
		ItemStack iS = new ItemStack(Material.valueOf("ANIMALNET_ANIMAL_NET_BIG"), 1);
		ItemMeta iM = iS.getItemMeta();
		iM.setDisplayName("§7§lBig Animal Net");
		List<String> lore = new ArrayList<String>();
		if (StorageDatabase.getScientific().get("AnimalNetBig") == null)
			StorageDatabase.getScientific().put("AnimalNetBig", "0");
		lore.add("§6§oAmount on stock: §e§l" + StorageDatabase.getScientific().get("AnimalNetBig"));
		iM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		iM.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
		iM.addItemFlags(ItemFlag.HIDE_DESTROYS);
		iM.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		iM.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		iM.addItemFlags(ItemFlag.HIDE_PLACED_ON);
		iM.setLore(lore);
		iS.setItemMeta(iM);
		return iS;
	}

	public static ItemStack dailyRewardItem(Player player) {
		ItemStack iS = new ItemStack(Material.EMERALD, 1);
		ItemMeta iM = iS.getItemMeta();
		iM.setDisplayName("§a§lDaily Reward");
		List<String> lore = new ArrayList<String>();
		long nowRew = System.currentTimeMillis();
		if (PlayerInfoFile.getPlayerInfoFile().getConfig()
				.getString("Players." + player.getName() + ".DailyReward") != null) {
			if (PlayerInfoFile.getPlayerInfoFile().getConfig()
					.getString("Players." + player.getName() + ".DailyReward") == "0") {
				lore.add("§a§oClick to claim your §e§lDaily Reward§a§o!");
			} else {
				if (!(nowRew - Long.parseLong(PlayerInfoFile.getPlayerInfoFile().getConfig()
						.getString("Players." + player.getName() + ".DailyReward")) < 86400000)) {
					lore.add("§a§oClick to claim your §e§lDaily Reward§a§o!");
				} else {
					lore.add("§e§oYou can claim your next daily reward in §f§l"
							+ API.formatTimeHM((Long.parseLong(PlayerInfoFile.getPlayerInfoFile().getConfig()
									.getString("Players." + player.getName() + ".DailyReward")) + 86400000) - nowRew)
							+ "§e§o.");
				}
			}
		} else {
			lore.add("§a§oClick to claim your §e§lDaily Reward§a§o!");
		}
		iM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		iM.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
		iM.addItemFlags(ItemFlag.HIDE_DESTROYS);
		iM.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		iM.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		iM.addItemFlags(ItemFlag.HIDE_PLACED_ON);
		iM.setLore(lore);
		iS.setItemMeta(iM);
		return iS;
	}

	public static ItemStack glassBottle() {
		ItemStack iS = new ItemStack(Material.GLASS_BOTTLE, 1);
		ItemMeta iM = iS.getItemMeta();
		iM.setDisplayName("§b§lGlass Bottle §b§o(Empty)");
		List<String> lore = new ArrayList<String>();
		lore.add("§7§oCost per item: §e§l$3");
		lore.add("§7§oMaximum amount per order: §e§l128 pcs");
		iM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		iM.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
		iM.addItemFlags(ItemFlag.HIDE_DESTROYS);
		iM.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		iM.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		iM.addItemFlags(ItemFlag.HIDE_PLACED_ON);
		iM.setLore(lore);
		iS.setItemMeta(iM);
		return iS;
	}

	public static ItemStack glassBottleCount() {
		ItemStack iS = new ItemStack(Material.GLASS_BOTTLE, 1);
		ItemMeta iM = iS.getItemMeta();
		iM.setDisplayName("§b§lGlass Bottle §b§o(Empty)");
		List<String> lore = new ArrayList<String>();
		if (StorageDatabase.getScientific().get("GlassBottle") == null)
			StorageDatabase.getScientific().put("GlassBottle", "0");
		lore.add("§6§oAmount on stock: §e§l" + StorageDatabase.getScientific().get("GlassBottle"));
		iM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		iM.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
		iM.addItemFlags(ItemFlag.HIDE_DESTROYS);
		iM.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		iM.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		iM.addItemFlags(ItemFlag.HIDE_PLACED_ON);
		iM.setLore(lore);
		iS.setItemMeta(iM);
		return iS;
	}

	public static ItemStack emptyBucket() {
		ItemStack iS = new ItemStack(Material.BUCKET, 1);
		ItemMeta iM = iS.getItemMeta();
		iM.setDisplayName("§7§lBucket §f§o(Empty)");
		List<String> lore = new ArrayList<String>();
		lore.add("§7§oCost per item: §e§l$5");
		lore.add("§7§oMaximum amount per order: §e§l64 pcs");
		iM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		iM.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
		iM.addItemFlags(ItemFlag.HIDE_DESTROYS);
		iM.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		iM.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		iM.addItemFlags(ItemFlag.HIDE_PLACED_ON);
		iM.setLore(lore);
		iS.setItemMeta(iM);
		return iS;
	}

	public static ItemStack emptyBucketCount() {
		ItemStack iS = new ItemStack(Material.BUCKET, 1);
		ItemMeta iM = iS.getItemMeta();
		iM.setDisplayName("§7§lBucket §f§o(Empty)");
		List<String> lore = new ArrayList<String>();
		if (StorageDatabase.getScientific().get("Bucket") == null)
			StorageDatabase.getScientific().put("Bucket", "0");
		lore.add("§6§oAmount on stock: §e§l" + StorageDatabase.getScientific().get("Bucket"));
		iM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		iM.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
		iM.addItemFlags(ItemFlag.HIDE_DESTROYS);
		iM.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		iM.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		iM.addItemFlags(ItemFlag.HIDE_PLACED_ON);
		iM.setLore(lore);
		iS.setItemMeta(iM);
		return iS;
	}

	public static ItemStack nextPageStorage() {
		ItemStack iS = new ItemStack(Material.ARROW, 1);
		ItemMeta iM = iS.getItemMeta();
		iM.setDisplayName("§a§oNext Page");
		iM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		iM.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
		iM.addItemFlags(ItemFlag.HIDE_DESTROYS);
		iM.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		iM.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		iM.addItemFlags(ItemFlag.HIDE_PLACED_ON);
		iS.setItemMeta(iM);
		return iS;
	}

	public static ItemStack previousPageStorage() {
		ItemStack iS = new ItemStack(Material.ARROW, 1);
		ItemMeta iM = iS.getItemMeta();
		iM.setDisplayName("§c§oPrevious Page");
		iM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		iM.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
		iM.addItemFlags(ItemFlag.HIDE_DESTROYS);
		iM.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		iM.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		iM.addItemFlags(ItemFlag.HIDE_PLACED_ON);
		iS.setItemMeta(iM);
		return iS;
	}

	public static ItemStack clearInvItem() {
		ItemStack iS = new ItemStack(Material.HOPPER, 1);
		ItemMeta iM = iS.getItemMeta();
		iM.setDisplayName("§c§lClear your inventory");
		List<String> lore = new ArrayList<String>();
		lore.add("§7§oDo you have your inventory full of items you dont need?");
		lore.add("§e§oClick this icon to select items you want to throw out!");
		iM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		iM.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
		iM.addItemFlags(ItemFlag.HIDE_DESTROYS);
		iM.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		iM.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		iM.addItemFlags(ItemFlag.HIDE_PLACED_ON);
		iM.setLore(lore);
		iS.setItemMeta(iM);
		return iS;
	}

	public static ItemStack playerItemMenu(String user) {
		ItemStack iS = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
		SkullMeta playerheadmeta = (SkullMeta) iS.getItemMeta();
		playerheadmeta.setOwner(user);
		playerheadmeta.setDisplayName(user);
		iS.setItemMeta(playerheadmeta);
		ItemMeta iM = iS.getItemMeta();
		iM.setDisplayName("§f§lYour Identification Card");
		iM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		iM.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
		iM.addItemFlags(ItemFlag.HIDE_DESTROYS);
		iM.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		iM.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		iM.addItemFlags(ItemFlag.HIDE_PLACED_ON);
		ArrayList<String> lore = new ArrayList<String>();
		lore.add("§b§oClick to view your ID Card.");
		iM.setLore(lore);
		iS.setItemMeta(iM);
		return iS;
	}

	public static ItemStack coffeeMach() {
		ItemStack iS = new ItemStack(Material.valueOf("SCP_TOKEN_COFFEE_MACHINE"), 1);
		ItemMeta iM = iS.getItemMeta();
		iM.setDisplayName("§f§lSCP-294");
		iM.setLore(Lores.coffeeMachLore());
		iS.setItemMeta(iM);
		return iS;
	}

	public static ItemStack blackShuck() {
		ItemStack iS = new ItemStack(Material.valueOf("SCP_TOKEN_BLACK_SHUCK"), 1);
		ItemMeta iM = iS.getItemMeta();
		iM.setDisplayName("§f§lSCP-023");
		iM.setLore(Lores.blackShuckLore());
		iS.setItemMeta(iM);
		return iS;
	}

	public static ItemStack plagueDoc() {
		ItemStack iS = new ItemStack(Material.valueOf("SCP_TOKEN_PLAGUE_DOCTOR"), 1);
		ItemMeta iM = iS.getItemMeta();
		iM.setDisplayName("§f§lSCP-049");
		iM.setLore(Lores.plagueDocLore());
		iS.setItemMeta(iM);
		return iS;
	}

	public static ItemStack shyGuy() {
		ItemStack iS = new ItemStack(Material.valueOf("SCP_TOKEN_SHY_GUY"), 1);
		ItemMeta iM = iS.getItemMeta();
		iM.setDisplayName("§f§lSCP-096");
		iM.setLore(Lores.shyGuyLore());
		iS.setItemMeta(iM);
		return iS;
	}

	public static ItemStack bears() {
		ItemStack iS = new ItemStack(Material.valueOf("SCP_TOKEN_BUILDER_BEAR"), 1);
		ItemMeta iM = iS.getItemMeta();
		iM.setDisplayName("§f§lSCP-1048 (All 3)");
		iM.setLore(Lores.bearsLore());
		iS.setItemMeta(iM);
		return iS;
	}

	public static ItemStack nextPageTest4() {
		ItemStack iS = new ItemStack(Material.ARROW, 1);
		ItemMeta iM = iS.getItemMeta();
		iM.setDisplayName("§a§oNext Page");
		iM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		iM.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
		iM.addItemFlags(ItemFlag.HIDE_DESTROYS);
		iM.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		iM.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		iM.addItemFlags(ItemFlag.HIDE_PLACED_ON);
		iS.setItemMeta(iM);
		return iS;
	}

	public static ItemStack prevPageTest4() {
		ItemStack iS = new ItemStack(Material.ARROW, 1);
		ItemMeta iM = iS.getItemMeta();
		iM.setDisplayName("§c§oPrevious Page");
		iM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		iM.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
		iM.addItemFlags(ItemFlag.HIDE_DESTROYS);
		iM.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		iM.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		iM.addItemFlags(ItemFlag.HIDE_PLACED_ON);
		iS.setItemMeta(iM);
		return iS;
	}

	public static ItemStack reptile() {
		ItemStack iS = new ItemStack(Material.valueOf("SCP_TOKEN682"), 1);
		ItemMeta iM = iS.getItemMeta();
		iM.setDisplayName("§f§lSCP-682");
		iM.setLore(Lores.reptileLore());
		iS.setItemMeta(iM);
		return iS;
	}

	public static ItemStack skeletonKey() {
		ItemStack iS = new ItemStack(Material.valueOf("SCP_SKELETON_KEY"), 1);
		ItemMeta iM = iS.getItemMeta();
		iM.setDisplayName("§f§lSCP-005");
		iM.setLore(Lores.skeletonKeyLore());
		iS.setItemMeta(iM);
		return iS;
	}

	public static ItemStack oldMan() {
		ItemStack iS = new ItemStack(Material.valueOf("SCP_TOKEN_OLD_MAN"), 1);
		ItemMeta iM = iS.getItemMeta();
		iM.setDisplayName("§f§lSCP-106");
		iM.setLore(Lores.oldManLore());
		iS.setItemMeta(iM);
		return iS;
	}

	public static ItemStack manyVoices() {
		ItemStack iS = new ItemStack(Material.valueOf("SCP_TOKEN_WITH_MANY_VOICES"), 1);
		ItemMeta iM = iS.getItemMeta();
		iM.setDisplayName("§f§lSCP-939");
		iM.setLore(Lores.manyVoicesLore());
		iS.setItemMeta(iM);
		return iS;
	}

	public static ItemStack coalMineItem() {
		ItemStack iS = new ItemStack(Material.COAL, 1);
		ItemMeta iM = iS.getItemMeta();
		iM.setDisplayName("§a§l2$/piece");
		iM.setLore(Lores.sellMinedOreLore());
		iS.setItemMeta(iM);
		return iS;
	}

	public static ItemStack ironMineItem() {
		ItemStack iS = new ItemStack(Material.IRON_INGOT, 1);
		ItemMeta iM = iS.getItemMeta();
		iM.setDisplayName("§a§l5$/piece");
		iM.setLore(Lores.sellMinedOreLore());
		iS.setItemMeta(iM);
		return iS;
	}

	public static ItemStack diamondMineItem() {
		ItemStack iS = new ItemStack(Material.DIAMOND, 1);
		ItemMeta iM = iS.getItemMeta();
		iM.setDisplayName("§a§l20$/piece");
		iM.setLore(Lores.sellMinedOreLore());
		iS.setItemMeta(iM);
		return iS;
	}

	public static ItemStack emeraldMineItem() {
		ItemStack iS = new ItemStack(Material.COAL, 1);
		ItemMeta iM = iS.getItemMeta();
		iM.setDisplayName("§a§l50$/piece");
		iM.setLore(Lores.sellMinedOreLore());
		iS.setItemMeta(iM);
		return iS;
	}

	public static ItemStack buyMineMenuItem() {
		ItemStack iS = new ItemStack(Material.COAL, 1);
		ItemMeta iM = iS.getItemMeta();
		iM.setDisplayName("§e§lMine Upgrades Shop");
		iS.setItemMeta(iM);
		return iS;
	}
}