package cz.vojtiisek.scprpsystem.Food;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import cz.vojtiisek.scprpsystem.API;
import cz.vojtiisek.scprpsystem.SCPRP;
import cz.vojtiisek.scprpsystem.Storage.GUIs.StorageDatabase;
import cz.vojtiisek.scprpsystem.files.FoodFile;
import cz.vojtiisek.scprpsystem.files.PlayerInfoFile;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;

public class FoodAPI implements Listener {
	public static SCPRP main;

	public static LuckPerms api;

	public static Map<String, String> nextFood = new HashMap<String, String>();
	public static Map<String, Integer> foodCount = new HashMap<String, Integer>();
	public static Map<String, Long> lastTime = new HashMap<String, Long>();

	public static List<Location> buttons = new ArrayList<Location>();
	public static List<String> creator = new ArrayList<String>();
	public static List<String> remover = new ArrayList<String>();

	public FoodAPI(SCPRP main) {
		this.main = main;
	}

	public static Map<String, String> getNextFood() {
		return nextFood;
	}

	public static Map<String, Integer> getFoodCount() {
		return foodCount;
	}

	public static Map<String, Long> getLastTime() {
		return lastTime;
	}

	@EventHandler
	public static void onButtonClick(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		String name = e.getPlayer().getName();
		if (e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
			if (e.getClickedBlock().getType().equals(Material.STONE_BUTTON)
					|| e.getClickedBlock().getType().equals(Material.WOOD_BUTTON)) {
				if (getButtons().size() == 0)
					loadButtons();
				if (isInCreatorMode(name)) {
					if (isFoodMachine(new Location(e.getClickedBlock().getWorld(), e.getClickedBlock().getX(),
							e.getClickedBlock().getY(), e.getClickedBlock().getZ()))) {
						p.sendMessage("§8§l[§c§l!§8§l] §c§oThis button is already part of a Food Machine!");
					} else {
						Location loc = new Location(e.getClickedBlock().getWorld(), e.getClickedBlock().getX(),
								e.getClickedBlock().getY(), e.getClickedBlock().getZ());
						getButtons().add(loc);
						getCreatorPlayers().remove(name);
						saveButtons();
						p.sendMessage("§8§l[§a§l!§8§l] §a§oSuccessfully created a §6§lFood Machine§a§o.");
					}
				} else if (isInRemoverMode(name)) {
					if (isFoodMachine(new Location(e.getClickedBlock().getWorld(), e.getClickedBlock().getX(),
							e.getClickedBlock().getY(), e.getClickedBlock().getZ()))) {
						getButtons().remove(new Location(e.getClickedBlock().getWorld(), e.getClickedBlock().getX(),
								e.getClickedBlock().getY(), e.getClickedBlock().getZ()));
						getRemoverPlayers().remove(name);
						p.sendMessage("§8§l[§a§l!§8§l] §a§oSuccessfully removed this §6§lFood Machine§a§o.");
					} else {
						p.sendMessage("§8§l[§c§l!§8§l] §c§oThis button is not a part of any Food Machine!");
					}

				} else {
					if (isFoodMachine(new Location(e.getClickedBlock().getWorld(), e.getClickedBlock().getX(),
							e.getClickedBlock().getY(), e.getClickedBlock().getZ()))) {
						if (getFoodCount().get(name) != null && getFoodCount().containsKey(name)
								&& getFoodCount().get(name) == 15) {
							long now = System.currentTimeMillis();
							long lastTime = getLastTime().get(name);
							if (now - lastTime >= 1800000) {
								getFoodCount().remove(name);
								getLastTime().remove(name);
							} else {
								p.sendMessage(
										"§8§l[§c§l!§8§l]§c§o You can not withdraw any more food from the Food Machine yet.");
								p.sendMessage("§8§l[§c§l!§8§l]§c§o Cooldown ends in: §e§l "
										+ API.formatTimeMS((lastTime + 1800000) - now));
							}
						} else if (getFoodCount().get(name) == null
								|| getFoodCount().containsKey(name) && getFoodCount().get(name) != 15) {
							if (PlayerInfoFile.getPlayerInfoFile().getConfig()
									.getString("Players." + p.getName() + ".PreferredFood") != null) {
								if (PlayerInfoFile.getPlayerInfoFile().getConfig()
										.getString("Players." + p.getName() + ".PreferredFood").equals("Unselected")) {
									if (getNextFood().containsKey(name)) {
										if (p.getInventory().firstEmpty() == -1) {
											p.sendMessage("§8§l[§c§l!§8§l] §c§oYour inventory is full!");
										} else {
											if (Integer.parseInt(
													StorageDatabase.getOther().get(getNextFood().get(name))) >= 1) {
												int currentAmount = API
														.parse(StorageDatabase.getOther().get(getNextFood().get(name)));
												StorageDatabase.getOther().remove(getNextFood().get(name));
												StorageDatabase.getOther().put(getNextFood().get(name),
														Integer.toString(currentAmount - 1));
												p.getInventory().addItem(new ItemStack(
														Material.valueOf(
																StorageDatabase.getItemID(getNextFood().get(name))),
														1));
												if (getLastTime().containsKey(name))
													if (System.currentTimeMillis() - getLastTime().get(name) >= 1800000)
														if (getFoodCount().containsKey(name))
															getFoodCount().remove(name);
												
												if (getFoodCount().containsKey(name)) {
													int currentCount = getFoodCount().get(name);
													currentCount = currentCount + 1;
													getFoodCount().put(name, currentCount);
												} else {
													getFoodCount().put(name, 1);
												}

												getLastTime().put(name, System.currentTimeMillis());

												getNextFood().remove(name);
												p.sendMessage("§8§l[§a§l!§8§l] §d§oEnjoy your meal!");
											} else {
												p.sendMessage(
														"§8§l[§c§l!§8§l] §c§oThis food type is out of stock. Contact any §6§lStorage Leader§c§o, or the §e§lSite Director§c§o. Selected food type: §f§l"
																+ getNextFood().get(name));
											}
										}
									} else {
										p.sendMessage(
												"§8§l[§c§l!§8§l] §c§oFirst, you need to select the food you want.");
										FoodGUI.openGUI(p, LuckPermsProvider.get().getUserManager().getUser(name));
									}
								} else {
									if (p.getInventory().firstEmpty() == -1) {
										p.sendMessage("§8§l[§c§l!§8§l] §c§oYour inventory is full!");
									} else {
										if (Integer.parseInt(StorageDatabase.getOther()
												.get(PlayerInfoFile.getPlayerInfoFile().getConfig().getString(
														"Players." + p.getName() + ".PreferredFood"))) >= 1) {
											int currentAmount = API.parse(StorageDatabase.getOther()
													.get(PlayerInfoFile.getPlayerInfoFile().getConfig()
															.getString("Players." + p.getName() + ".PreferredFood")));
											StorageDatabase.getOther()
													.remove(PlayerInfoFile.getPlayerInfoFile().getConfig()
															.getString("Players." + p.getName() + ".PreferredFood"));
											StorageDatabase.getOther()
													.put(PlayerInfoFile.getPlayerInfoFile().getConfig()
															.getString("Players." + p.getName() + ".PreferredFood"),
															Integer.toString(currentAmount - 1));
											p.getInventory()
													.addItem(new ItemStack(
															Material.valueOf(StorageDatabase
																	.getItemID(PlayerInfoFile.getPlayerInfoFile()
																			.getConfig().getString("Players."
																					+ p.getName() + ".PreferredFood"))),
															1));
											
											if (getLastTime().containsKey(name))
												if (System.currentTimeMillis() - getLastTime().get(name) >= 1800000)
													if (getFoodCount().containsKey(name))
														getFoodCount().remove(name);
											
											if (getFoodCount().containsKey(name)) {
												int currentCount = getFoodCount().get(name);
												currentCount = currentCount + 1;
												getFoodCount().put(name, currentCount);
											} else {
												getFoodCount().put(name, 1);
											}

											getLastTime().put(name, System.currentTimeMillis());
											
											p.sendMessage("§8§l[§a§l!§8§l] §d§oEnjoy your meal!");
										} else {
											p.sendMessage(
													"§8§l[§c§l!§8§l] §c§oThis food type is out of stock. Contact any §6§lStorage Leader§c§o, or the §e§lSite Director§c§o. Selected food type: §f§l"
															+ PlayerInfoFile.getPlayerInfoFile().getConfig().getString(
																	"Players." + p.getName() + ".PreferredFood"));
										}
									}
								}
							}

						}
					}
				}
			}
		}
	}

	public static boolean isInRemoverMode(String name) {
		boolean bool = false;
		if (getRemoverPlayers().contains(name))
			bool = true;
		return bool;
	}

	public static List<String> getRemoverPlayers() {
		return remover;
	}

	public static void saveButtons() {
		for (int i = 0; i < getButtons().size(); i++) {
			Location loc = getButtons().get(i);
			double x = loc.getX();
			double y = loc.getY();
			double z = loc.getZ();
			FoodFile.getFoodFile().getConfig().set("Machines.Machine" + i + ".x", x);
			FoodFile.getFoodFile().getConfig().set("Machines.Machine" + i + ".y", y);
			FoodFile.getFoodFile().getConfig().set("Machines.Machine" + i + ".z", z);
			FoodFile.getFoodFile().getConfig().set("Machines.Machine" + i + ".World", "RPWorld");
		}

		FoodFile.getFoodFile().save();
		System.out.println(" ");
		System.out.println("Successfully saved all Food Machines' button locations.");
	}

	public static void loadButtons() {
		for (String machine : FoodFile.getFoodFile().getConfig().getConfigurationSection("Machines").getKeys(false)) {
			double x = FoodFile.getFoodFile().getConfig().getDouble("Machines." + machine + ".x");
			double y = FoodFile.getFoodFile().getConfig().getDouble("Machines." + machine + ".y");
			double z = FoodFile.getFoodFile().getConfig().getDouble("Machines." + machine + ".z");
			String w = FoodFile.getFoodFile().getConfig().getString("Machines." + machine + ".World");
			Location loc = new Location(Bukkit.getWorld(w), x, y, z);
			getButtons().add(loc);
		}
		
		System.out.println(" ");
		System.out.println("Successfully loaded all Food Machines' button locations.");
	}

	public static boolean isInCreatorMode(String name) {
		boolean bool = false;
		if (getCreatorPlayers().contains(name))
			bool = true;
		return bool;
	}

	public static List<String> getCreatorPlayers() {
		return creator;
	}

	public static boolean isFoodMachine(Location location) {
		boolean bool = false;
		if (getButtons().contains(location))
			bool = true;
		return bool;
	}

	public static List<Location> getButtons() {
		return buttons;
	}

	public static void addRemoverMode(String name) {
		getRemoverPlayers().add(name);
	}

	public static void addCreatorMode(String name) {
		getCreatorPlayers().add(name);
	}

	public static void disableCreator(String name) {
		getCreatorPlayers().remove(name);
	}

	public static void disableRemover(String name) {
		getRemoverPlayers().remove(name);
	}
}
