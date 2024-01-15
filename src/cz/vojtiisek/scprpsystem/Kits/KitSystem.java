package cz.vojtiisek.scprpsystem.Kits;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import Levelling.LevellingSys;
import cz.vojtiisek.scprpsystem.SCPRP;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;

public class KitSystem {
	private static SCPRP main;

	static LuckPerms api = LuckPermsProvider.get();

	public KitSystem(SCPRP main) {
		this.main = main;
	}

	public static void giveKit(String name) {
		String role = api.getUserManager().getUser(name).getPrimaryGroup().toString();
		Player player = Bukkit.getPlayer(name);
		switch (role) {
		case "default":
			giveCustomItemStacks(name, Kits.getRoleKit(Kits.CLASSD));
			break;
		case "class-d":
			giveCustomItemStacks(name, Kits.getRoleKit(Kits.CLASSD));
			break;
		case "medic":
			giveCustomItemStacks(name, Kits.getRoleKit(Kits.MEDIC));
			break;
		case "technic":
			giveCustomItemStacks(name, Kits.getRoleKit(Kits.TECHNIC));
			break;
		case "researcher":
			switch (LevellingSys.convertXpToLevel(role, LevellingSys.getXP(name))) {
			case 0:
				giveCustomItemStacks(name, Kits.getRoleKit(Kits.RESEARCHER1));
				break;
			case 1:
				giveCustomItemStacks(name, Kits.getRoleKit(Kits.RESEARCHER1));
				break;
			case 2:
				giveCustomItemStacks(name, Kits.getRoleKit(Kits.RESEARCHER2));
				break;
			case 3:
				giveCustomItemStacks(name, Kits.getRoleKit(Kits.RESEARCHER3));
				break;
			case 4:
				giveCustomItemStacks(name, Kits.getRoleKit(Kits.RESEARCHER4));
				break;
			default:
				break;
			}
			break;
		case "security-officer":
			switch (LevellingSys.convertXpToLevel(role, LevellingSys.getXP(name))) {
			case 0:
				giveCustomItemStacks(name, Kits.getRoleKit(Kits.SECOFF1));
				break;
			case 1:
				giveCustomItemStacks(name, Kits.getRoleKit(Kits.SECOFF1));
				break;
			case 2:
				giveCustomItemStacks(name, Kits.getRoleKit(Kits.SECOFF2));
				break;
			case 3:
				giveCustomItemStacks(name, Kits.getRoleKit(Kits.SECOFF3));
				break;
			case 4:
				giveCustomItemStacks(name, Kits.getRoleKit(Kits.SECOFF4));
				break;
			default:
				break;
			}
			break;
		case "mtf-nu-7":
			switch (LevellingSys.convertXpToLevel(role, LevellingSys.getXP(name))) {
			case 0:
				giveCustomItemStacks(name, Kits.getRoleKit(Kits.MTFNU71));
				break;
			case 1:
				giveCustomItemStacks(name, Kits.getRoleKit(Kits.MTFNU71));
				break;
			case 2:
				giveCustomItemStacks(name, Kits.getRoleKit(Kits.MTFNU72));
				break;
			case 3:
				giveCustomItemStacks(name, Kits.getRoleKit(Kits.MTFNU73));
				break;
			case 4:
				giveCustomItemStacks(name, Kits.getRoleKit(Kits.MTFNU74));
				break;
			default:
				break;
			}
			break;
		case "mtf-nu-7-commander":
			giveCustomItemStacks(name, Kits.getRoleKit(Kits.MTFNU7CMD));
			break;
		case "head-medic":
			giveCustomItemStacks(name, Kits.getRoleKit(Kits.HEADMEDIC));
			break;
		case "head-researcher":
			giveCustomItemStacks(name, Kits.getRoleKit(Kits.HEADRES));
			break;
		case "head-security-officer":
			giveCustomItemStacks(name, Kits.getRoleKit(Kits.HEADSECOFF));
			break;
		case "site-director":
			giveCustomItemStacks(name, Kits.getRoleKit(Kits.SITEDIR));
			break;
		case "o5-1":
			giveCustomItemStacks(name, Kits.getRoleKit(Kits.O51));
			break;
		case "o5-2":
			giveCustomItemStacks(name, Kits.getRoleKit(Kits.O52));
			break;
		case "o5-3":
			giveCustomItemStacks(name, Kits.getRoleKit(Kits.O53));
			break;
		case "o5-4":
			giveCustomItemStacks(name, Kits.getRoleKit(Kits.O54));
			break;
		case "o5-5":
			giveCustomItemStacks(name, Kits.getRoleKit(Kits.O55));
			break;
		case "o5-6":
			giveCustomItemStacks(name, Kits.getRoleKit(Kits.O56));
			break;
		case "o57":
			giveCustomItemStacks(name, Kits.getRoleKit(Kits.O57));
			break;
		case "the-administrator":
			giveCustomItemStacks(name, Kits.getRoleKit(Kits.THEADMIN));
			break;
		case "judge":
			giveCustomItemStacks(name, Kits.getRoleKit(Kits.JUDGE));
			break;
		case "storage-leader":
			giveCustomItemStacks(name, Kits.getRoleKit(Kits.STORAGELEADER));
			break;
		default:
			player.sendMessage("§8[§a§l!§8] §a§oYour role does not have a kit.");
			break;
		}
		player.sendMessage("§8[§a§l!§8] §a§oKit given successfully.");
	}
	
	public static void giveCustomItemStacks(String name, List<String> list) {
		Player player = Bukkit.getPlayerExact(name);
		String role = api.getUserManager().getUser(name).getPrimaryGroup().toString();
		for (String str : list) {
			if(str == "LEATHER_BOOTS") {
				if(role == "head-researcher" || role == "head-medic") {
					ItemStack iS = new ItemStack(Material.LEATHER_BOOTS, 1);
					ItemMeta iM = iS.getItemMeta();
					LeatherArmorMeta armorMeta = (LeatherArmorMeta) iM;
					armorMeta.setColor(Color.RED);
					iM.setLore(setOwnerLore(name));
					iS.setItemMeta(iM);
					if (player != null && player.isOnline())
						player.getInventory().addItem(iS);
				} else {
					ItemStack iS = new ItemStack(Material.valueOf(str), 1);
					ItemMeta iM = iS.getItemMeta();
					LeatherArmorMeta armorMeta = (LeatherArmorMeta) iM;
					armorMeta.setColor(Color.GRAY);
					iM.setLore(setOwnerLore(name));
					iS.setItemMeta(iM);
					if (player != null && player.isOnline())
						player.getInventory().addItem(iS);
				}
			} else {
				ItemStack iS = new ItemStack(Material.valueOf(str), 1);
				ItemMeta iM = iS.getItemMeta();
				iM.setLore(setOwnerLore(name));
				iS.setItemMeta(iM);
				if (player != null && player.isOnline())
					player.getInventory().addItem(iS);
			}
		}
	}
	
	public static List<String> setOwnerLore(String name) {
		List<String> lore = new ArrayList<String>();
		lore.add("§8Owner: §e§o" + name);
		return lore;
	}
}