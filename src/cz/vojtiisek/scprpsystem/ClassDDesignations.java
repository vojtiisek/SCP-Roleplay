package cz.vojtiisek.scprpsystem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import cz.vojtiisek.scprpsystem.CellSystem.CellSystem;
import cz.vojtiisek.scprpsystem.Kits.KitSystem;
import cz.vojtiisek.scprpsystem.files.ClassDDesigFile;
import cz.vojtiisek.scprpsystem.files.LevelsFile;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.model.user.User;
import net.luckperms.api.node.Node;
import net.luckperms.api.node.types.InheritanceNode;

public class ClassDDesignations {
	private static SCPRP main;
	static LuckPerms api = LuckPermsProvider.get();

	public static HashMap<String, Integer> designations = new HashMap<String, Integer>();
	public static int count = readCount();

	public ClassDDesignations(SCPRP main) {
		this.main = main;
	}

	public static void onNewbieJoin(Player player) {
		//API.clearFirstJoinItems(player);
		if (!getDesignations().containsKey(player.getName())) {
			User user = LuckPermsProvider.get().getUserManager().getUser(player.getName());
			Node oldrole = Node.builder("group.default").build();
			Node newrole = Node.builder("group.class-d").build();
			user.data().add(newrole);
			user.data().remove(oldrole);
			LuckPermsProvider.get().getUserManager().saveUser(user);

			if (LuckPermsProvider.get().getUserManager().getUser(player.getName()).getPrimaryGroup().equalsIgnoreCase("default")
					|| LuckPermsProvider.get().getUserManager().getUser(player.getName()).getPrimaryGroup().equalsIgnoreCase("class-d")) {
				count = count + 1;
				ClassDDesigFile.getClassDDesigFile().getConfig().set("count", count);
				designations.put(player.getName(), count);
				saveFile();
				CellSystem.assignCell(player.getName());
				System.out.println("[SCPRP] Player registered under Class-D Designation: " + API.toStringDesig(count));
				API.setPrefix(player.getName(), 10, "§8[§6D-" + API.toStringDesig(count) + "§8]§6§o", true);
				KitSystem.giveKit(player.getName());
			}
		} else {
			System.out.println("[SCPRP] This player already has a Class-D Designation. Checking for a cell.");
			if(API.findCellIdByPname(player.getName()) == "error") {
				CellSystem.assignCell(player.getName());
			}
		}
	}

	public static void rollbackDesignation(Player player, String name) {
		if (getDesignations().get(name) != null) {
			API.setPrefix(name, 10, "§8[§6D-" + API.toStringDesig(getDesignations().get(name)) + "§8]§6§o", true);
			API.sendConsoleCommand("discord broadcast #819586816444727316 " + player.getName()
					+ " - designation rollback - Target: " + name);
			player.sendMessage(
					"§8[§a§l!§8] §a§oSuccessfully rolled §6§o" + name + "§a§o's Class-D designation prefix back.");
		} else {
			loadFile();
			player.sendMessage("§8[§4§l!§8] §c§oCould not roll §6§o" + name
					+ "§c§o's Class-D designation prefix back. Contact §4§lThe Administrator§c§o.");
			API.sendConsoleCommand(
					"discord broadcast #839057785362448424 [SCPRP] Designation null when rollbacking, target player: "
							+ name);
		}
	}

	public static void rollbackDesignation(String name) {
		Player player = Bukkit.getPlayerExact(name);
		if (getDesignations().get(name) != null) {
			String str = "§8[§6D-" + API.toStringDesig(getDesignations().get(name)) + "§8]§6§o";
			API.addPermission(name, "prefix.10." + str);
			API.setPrefix(name, 10, str, true);
			API.sendConsoleCommand("discord broadcast #843774743386521620 [SCPRP ClassDDesignation] Rollback - Target: "
					+ name + " - designation - " + getDesignations().get(name));
			player.sendMessage("§8[§a§l!§8] §a§oYour Class-D designation was successfully rolled back.");
		} else {
			loadFile();
			player.sendMessage(
					"§8[§4§l!§8] §c§oCould not roll your Class-D designation prefix back. Contact §4§lThe Administrator§c§o.");
			API.sendConsoleCommand(
					"discord broadcast #839057785362448424 [SCPRP] Designation null when rollbacking, target player: "
							+ name);
		}
	}

	public static void rollbackDesignationSilent(String name) {
		if (getDesignations().get(name) != null) {
			API.setPrefix(name, 10, "§8[§6D-" + API.toStringDesig(getDesignations().get(name)) + "§8]§6§o", true);
		} else {
			loadFile();
			API.sendConsoleCommand(
					"discord broadcast #839057785362448424 [SCPRP] Designation null when rollbacking, target player: "
							+ name);
		}
	}

	public static HashMap<String, Integer> getDesignations() {
		return designations;
	}

	public static int readCount() {
		return ClassDDesigFile.getClassDDesigFile().getConfig().getInt("count");
	}

	public static int getPlayerDesignation(String playerName) {
		return designations.get(playerName);
	}

	public static void saveFile() {
		String entryString = "empty";
		try {
			if (!designations.isEmpty()) {
				List<String> list = new ArrayList<String>();
				for (Entry<String, Integer> entry : designations.entrySet()) {
					entryString = entry.getKey() + ":" + Integer.toString(entry.getValue());
					list.add(entryString);
				}
				ClassDDesigFile.getClassDDesigFile().getConfig().set("Designations", list);

				System.out.println(" ");
				System.out.println("Class-D Designations saved successfully.");
			}
		} catch (Exception e) {
			ClassDDesigFile.getClassDDesigFile().save();
			ClassDDesigFile.getClassDDesigFile().reload();

			e.printStackTrace();
			saveFile();
		}

		ClassDDesigFile.getClassDDesigFile().save();
		ClassDDesigFile.getClassDDesigFile().reload();
	}

	public static void loadFile() {
		ClassDDesigFile.getClassDDesigFile().save();
		ClassDDesigFile.getClassDDesigFile().reload();
		if (ClassDDesigFile.getClassDDesigFile().getConfig().isList("Designations")) {
			for (String str : ClassDDesigFile.getClassDDesigFile().getConfig().getStringList("Designations")) {
				String[] split = str.split(":");
				designations.put(split[0], Integer.parseInt(split[1]));
			}
			System.out.println(" ");
			System.out.println("Class-D Designations loaded successfully.");
		} else {
			API.sendConsoleCommand(
					"discord broadcast #839057785362448424 [SCPRP] Could not load designations from their file");
		}
	}
}