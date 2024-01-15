package cz.vojtiisek.scprpsystem;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.conversations.Conversable;
import org.bukkit.conversations.Conversation;
import org.bukkit.conversations.ConversationAbandonedEvent;
import org.bukkit.conversations.ConversationFactory;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import com.earth2me.essentials.Essentials;
import com.earth2me.essentials.api.Economy;

import Levelling.LevellingSys;
import PlayerTools.ClearInventory;
import Testing.TestingSys;
import Testing.Tests1GUI;
import Testing.Tests2GUI;
import Testing.Tests3GUI;
import Testing.Tests4GUI;
import Testing.Tests4GUI2;
import cz.vojtiisek.scprpsystem.AreaDetector.AreaDetector;
import cz.vojtiisek.scprpsystem.AreaDetector.AreaDetectorIdentify;
import cz.vojtiisek.scprpsystem.CellSystem.Cell;
import cz.vojtiisek.scprpsystem.CellSystem.CellSystem;
import cz.vojtiisek.scprpsystem.ConsoleCommands.ConsoleAddXp;
import cz.vojtiisek.scprpsystem.ConsoleCommands.ConsolePayout;
import cz.vojtiisek.scprpsystem.ConsoleCommands.ConsoleRollbackDesig;
import cz.vojtiisek.scprpsystem.Constructions.ConstructionEdit;
import cz.vojtiisek.scprpsystem.Constructions.ConstructionsMenu;
import cz.vojtiisek.scprpsystem.DailyRewards.DailyRewards;
import cz.vojtiisek.scprpsystem.Economy.MineSellGUI;
import cz.vojtiisek.scprpsystem.Events.JoinEvent;
import cz.vojtiisek.scprpsystem.Events.LeaveEvent;
import cz.vojtiisek.scprpsystem.Events.SpawnkillProtect;
import cz.vojtiisek.scprpsystem.Food.FoodAPI;
import cz.vojtiisek.scprpsystem.Food.FoodGUI;
import cz.vojtiisek.scprpsystem.IdentificationCard.IDGUI;
import cz.vojtiisek.scprpsystem.Mine.IdentifyMine;
import cz.vojtiisek.scprpsystem.Mine.Mine;
import cz.vojtiisek.scprpsystem.Mine.OreFunctioning;
import cz.vojtiisek.scprpsystem.RedstoneTools.IdentifyWNetwork;
import cz.vojtiisek.scprpsystem.RedstoneTools.WirelessNetworks;
import cz.vojtiisek.scprpsystem.Spawnpoints.SpawnSys;
import cz.vojtiisek.scprpsystem.Storage.GUIs.DeptSM;
import cz.vojtiisek.scprpsystem.Storage.GUIs.MainSM;
import cz.vojtiisek.scprpsystem.Storage.GUIs.OrderSM;
import cz.vojtiisek.scprpsystem.Storage.GUIs.StorageDatabase;
import cz.vojtiisek.scprpsystem.Storage.GUIs.ViewSM;
import cz.vojtiisek.scprpsystem.Storage.GUIs.WithdrawSM;
import cz.vojtiisek.scprpsystem.Tasks.TasksMenu;
import cz.vojtiisek.scprpsystem.Tasks.TasksSys;
import cz.vojtiisek.scprpsystem.TestChat.TestChat;
import cz.vojtiisek.scprpsystem.Testworld.Testworldconv1;
import cz.vojtiisek.scprpsystem.files.BlockDataFile;
import cz.vojtiisek.scprpsystem.files.CellsFile;
import cz.vojtiisek.scprpsystem.files.ClassDDesigFile;
import cz.vojtiisek.scprpsystem.files.ConstructionsFile;
import cz.vojtiisek.scprpsystem.files.FoodFile;
import cz.vojtiisek.scprpsystem.files.JailedPlayersFile;
import cz.vojtiisek.scprpsystem.files.LevelsFile;
import cz.vojtiisek.scprpsystem.files.MineOresFile;
import cz.vojtiisek.scprpsystem.files.NaufixTeamFile;
import cz.vojtiisek.scprpsystem.files.PlayerInfoFile;
import cz.vojtiisek.scprpsystem.files.StorageFile;
import cz.vojtiisek.scprpsystem.files.WirelessNetworksFile;
import cz.vojtiisek.scprpsystem.listeners.LpListener;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.model.user.User;
import net.luckperms.api.node.Node;

public class SCPRP extends JavaPlugin implements Listener, Conversable {
	private static SCPRP main;

	public static ArrayList<String> ongoingTests = new ArrayList<String>();
	public static ArrayList<String> tempList = new ArrayList<String>();

	public static LuckPerms api;
	public static Essentials ess;

	boolean breachstate;

	public void onEnable() {
		main = this;
		System.out.println(" ");
		System.out.println(" ");
		System.out.println(" ");
		System.out.println(" ");
		System.out.println("------------------------------------------");
		System.out.println(" ");
		System.out.println("SCP ROLEPLAY SYSTEM");
		System.out.println("Made by Vojtiisek. Discord: Vojtiisek#5691");
		System.out.println("STATE: Starting");
		System.out.println(" ");
		System.out.println(" ");
		System.out.println("Loading LP API...");
		RegisteredServiceProvider<LuckPerms> provider = Bukkit.getServicesManager().getRegistration(LuckPerms.class);
		if (provider != null) {
			api = provider.getProvider();
		}

		LpListener listener = new LpListener(this, api);
		listener.register();

		System.out.println("Loading Essentials API...");
		ess = (Essentials) getServer().getPluginManager().getPlugin("Essentials");

		System.out.println("Loading internal classes...");
		Bukkit.getServer().getPluginManager().registerEvents(this, this);
		Bukkit.getServer().getPluginManager().registerEvents(new API(this), this);
		Bukkit.getServer().getPluginManager().registerEvents(new DRSys(this), this);
		Bukkit.getServer().getPluginManager().registerEvents(new Tests1GUI(this), this);
		Bukkit.getServer().getPluginManager().registerEvents(new Tests2GUI(this), this);
		Bukkit.getServer().getPluginManager().registerEvents(new Tests3GUI(this), this);
		Bukkit.getServer().getPluginManager().registerEvents(new Tests4GUI(this), this);
		Bukkit.getServer().getPluginManager().registerEvents(new Tests4GUI2(this), this);
		Bukkit.getServer().getPluginManager().registerEvents(new TestingSys(this), this);
		Bukkit.getServer().getPluginManager().registerEvents(new RPAntiCheat(this), this);
		Bukkit.getServer().getPluginManager().registerEvents(new JoinEvent(this), this);
		Bukkit.getServer().getPluginManager().registerEvents(new LeaveEvent(this), this);
		Bukkit.getServer().getPluginManager().registerEvents(new SitPlayerDown(this), this);
		Bukkit.getServer().getPluginManager().registerEvents(new SCPMenu(this), this);
		Bukkit.getServer().getPluginManager().registerEvents(new ConstructionsMenu(this), this);
		Bukkit.getServer().getPluginManager().registerEvents(new ConstructionEdit(this), this);
		Bukkit.getServer().getPluginManager().registerEvents(new MainSM(this), this);
		Bukkit.getServer().getPluginManager().registerEvents(new DeptSM(this), this);
		Bukkit.getServer().getPluginManager().registerEvents(new OrderSM(this), this);
		Bukkit.getServer().getPluginManager().registerEvents(new ViewSM(this), this);
		Bukkit.getServer().getPluginManager().registerEvents(new WithdrawSM(this), this);
		Bukkit.getServer().getPluginManager().registerEvents(new IDGUI(this), this);
		Bukkit.getServer().getPluginManager().registerEvents(new TasksMenu(this), this);
		Bukkit.getServer().getPluginManager().registerEvents(new WirelessNetworks(this), this);
		Bukkit.getServer().getPluginManager().registerEvents(new IdentifyWNetwork(this), this);
		Bukkit.getServer().getPluginManager().registerEvents(new SpawnkillProtect(this), this);
		Bukkit.getServer().getPluginManager().registerEvents(new FoodGUI(this), this);
		Bukkit.getServer().getPluginManager().registerEvents(new FoodAPI(this), this);
		Bukkit.getServer().getPluginManager().registerEvents(new ClearInventory(this), this);
		Bukkit.getServer().getPluginManager().registerEvents(new SpawnSys(this), this);
		Bukkit.getServer().getPluginManager().registerEvents(new TestChat(this), this);
		Bukkit.getServer().getPluginManager().registerEvents(new Mine(this), this);
		Bukkit.getServer().getPluginManager().registerEvents(new IdentifyMine(this), this);
		Bukkit.getServer().getPluginManager().registerEvents(new OreFunctioning(this), this);
		Bukkit.getServer().getPluginManager().registerEvents(new AreaDetector(this), this);
		Bukkit.getServer().getPluginManager().registerEvents(new AreaDetectorIdentify(this), this);
		Bukkit.getServer().getPluginManager().registerEvents(new MineSellGUI(this), this);

		System.out.println("Loading static lists and hashmaps...");
		TestingSys.addPersonnelReq();
		TestingSys.addSCPPerms();
		TestingSys.addClasses();
		RPAntiCheat.addScpsMaxTimes();
		LevellingSys.addRolesXP();
		StorageDatabase.addItems();
		SpawnSys.addRespawns();
		SpawnSys.addSpawns();

		System.out.println("Creating files...");
		ClassDDesigFile.getClassDDesigFile().create();
		LevelsFile.getLevelsFile().create();
		ConstructionsFile.getConstructionsFile().create();
		CellsFile.getCellsFile().create();
		NaufixTeamFile.getNaufixTeamFile().create();
		StorageFile.getStorageFile().create();
		PlayerInfoFile.getPlayerInfoFile().create();
		WirelessNetworksFile.getWirelessNetworksFile().create();
		JailedPlayersFile.getJailedPlayersFile().create();
		FoodFile.getFoodFile().create();
		MineOresFile.getMineOresFile().create();

		System.out.println("Reloading files...");
		JailedPlayersFile.getJailedPlayersFile().reload();
		ClassDDesigFile.getClassDDesigFile().reload();
		LevelsFile.getLevelsFile().reload();
		ConstructionsFile.getConstructionsFile().reload();
		CellsFile.getCellsFile().reload();
		NaufixTeamFile.getNaufixTeamFile().reload();
		StorageFile.getStorageFile().reload();
		PlayerInfoFile.getPlayerInfoFile().reload();
		WirelessNetworksFile.getWirelessNetworksFile().reload();
		FoodFile.getFoodFile().reload();
		MineOresFile.getMineOresFile().reload();

		System.out.println("Loading data from files...");
		ClassDDesignations.loadFile();
		LevellingSys.loadFile();
		ConstructionsMenu.loadConstructions();
		CellSystem.loadFile();
		API.loadNaufixTeamFile();
		StorageDatabase.loadFile();
		// API.loadPlayerInformations();
		Mine.load();
		WirelessNetworks.loadNetworks();
		ConstructionsMenu.loadMembers();
		FoodAPI.loadButtons();

		System.out.println("Registering command executors...");
		this.getCommand("consoleaddxp").setExecutor(new ConsoleAddXp(this));
		this.getCommand("consolepayout").setExecutor(new ConsolePayout(this));
		this.getCommand("consoledesigrb").setExecutor(new ConsoleRollbackDesig(this));

		System.out.println(" ");
		System.out.println(" ");
		System.out.println("Plugin loaded successfully.");
		System.out.println(" ");
		System.out.println("------------------------------------------");
		System.out.println(" ");
		System.out.println(" ");
		System.out.println(" ");
		System.out.println(" ");
	}

	public void onDisable() {
		System.out.println(" ");
		System.out.println(" ");
		System.out.println(" ");
		System.out.println(" ");
		System.out.println("------------------------------------------");
		System.out.println(" ");
		System.out.println("SCP ROLEPLAY SYSTEM");
		System.out.println("STATE: Shutting down");
		System.out.println(" ");
		System.out.println(" ");
		System.out.println("Saving data to files...");

		ClassDDesignations.saveFile();
		LevellingSys.saveFile();
		ConstructionsMenu.saveConstructions();
		API.saveNaufixTeamFile();
		StorageDatabase.saveFile();
		WirelessNetworks.saveNetworks();
		// API.savePlayerInformations();
		FoodAPI.saveButtons();
		Mine.save();

		System.out.println("Saving files...");
		ClassDDesigFile.getClassDDesigFile().save();
		LevelsFile.getLevelsFile().save();
		ConstructionsFile.getConstructionsFile().save();
		NaufixTeamFile.getNaufixTeamFile().save();
		StorageFile.getStorageFile().save();
		PlayerInfoFile.getPlayerInfoFile().save();
		FoodFile.getFoodFile().save();
		MineOresFile.getMineOresFile().save();

		System.out.println(" ");
		System.out.println(" ");
		System.out.println("Plugin unloaded successfully.");
		System.out.println(" ");
		System.out.println("------------------------------------------");
		System.out.println(" ");
		System.out.println(" ");
		System.out.println(" ");
		System.out.println(" ");
	}

	public boolean onCommand(CommandSender sender, Command cmd, String commandLabels, String[] args) {
		Player player = (Player) sender;

		if (cmd.getName().equalsIgnoreCase("linkaccount")) {
			API.sendProgressBar(player, 20, 100, "§e§l", "§a§l", '|');
			player.sendMessage(
					"§8[§5§lDiscord§8] §d§oClick this invite link to join our Discord server: §5§lhttp://discord.gg/yMqzmrC");
			player.performCommand("discordsrv link");
		}

		if (cmd.getName().equalsIgnoreCase("dc")) {
			player.sendMessage(
					"§8[§5§lDiscord§8] §d§oClick this invite link to join our Discord server: §5§lhttp://discord.gg/yMqzmrC");
			player.performCommand("discordsrv link");
		}

		if (cmd.getName().equalsIgnoreCase("arrived")) {
			if (api.getUserManager().getUser(player.getName()).getPrimaryGroup().equals("mtf-nu-7")
					|| api.getUserManager().getUser(player.getName()).getPrimaryGroup().equals("mtf-nu-7-commander")
					|| player.hasPermission("scprp.o5") || player.hasPermission("scp.mod")
					|| player.hasPermission("scp.admin")) {
				API.sendMessageToEveryone(
						"§8§l[§4§l!§8§l] §b§oMobile Task Force unit Nu-7 has entered Area-22. Stay in safe areas and breach shelters, until the unit reaches your destination. Follow their instructions and co-operate.");
			} else {
				player.sendMessage("§8§l[§c§l!§8§l] §c§oYou do not have any permission to use this command.");
			}
		}

		if (cmd.getName().equalsIgnoreCase("dropitems")) {
			double radiusFrisk = 3D;
			if (api.getUserManager().getUser(player.getName()).getPrimaryGroup().equals("mtf-nu-7")
					|| api.getUserManager().getUser(player.getName()).getPrimaryGroup().equals("security-officer")) {
				if (args.length == 0) {
					player.sendMessage(
							"§8[§9§l?§8] §3§oUse this command to force a player to drop all of their items.");
					player.sendMessage(
							"§8[§9§l?§8] §e§oYou can only use this command on players, who you have recently frisked.");
					player.sendMessage("§8[§9§l?§8] §3§oUsage: §9§o/dropitems §e§o(Player)");
				} else if (args.length == 1) {
					if (Bukkit.getPlayerExact(args[0]) != null && Bukkit.getPlayerExact(args[0]).isOnline()) {
						if (player.getLocation()
								.distance(Bukkit.getPlayerExact(args[0]).getLocation()) <= radiusFrisk) {
							if (API.getFriskedPlayers().contains(args[0])) {
								API.dropAllItems(args[0]);
								API.getFriskedPlayers().remove(args[0]);
								player.sendMessage(
										"§e§oPlayer §c§o" + args[0] + " §e§owas forced to drop all their items.");
								API.sendConsoleCommand("discordsrv broadcast #819586816444727316 " + player.getName()
										+ " - dropitems - " + args[0]);
							} else {
								player.sendMessage("§8§l[§c§l!§8§l] §c§oPlayer §e§o" + args[0]
										+ " §c§omust be frisked for any illegal items first.");
							}
						} else {
							player.sendMessage(
									"§8[§c§l!§8] §c§oYou need to be closer than §c§l3§c§o blocks (or less) to §7§l"
											+ args[0] + "§c§o.");
						}
					} else {
						player.sendMessage("§8§l[§c§l!§8§l] §c§oPlayer §e§o" + args[0] + " §c§ois not online!");
					}
				}
			} else if (api.getUserManager().getUser(player.getName()).getPrimaryGroup().equals("head-security-officer")
					|| api.getUserManager().getUser(player.getName()).getPrimaryGroup().equals("mtf-nu-7-commander")
					|| api.getUserManager().getUser(player.getName()).getPrimaryGroup().equals("site-director")
					|| player.hasPermission("scprp.o5") || player.hasPermission("scprp.mod")
					|| player.hasPermission("scprp.admin")) {
				if (args.length == 0) {
					player.sendMessage(
							"§8[§9§l?§8] §3§oUse this command to force a player to drop all of their items.");
					player.sendMessage("§8[§9§l?§8] §3§oUsage: §9§o/dropitems §e§o(Player)");
				} else if (args.length == 1) {
					if (Bukkit.getPlayerExact(args[0]) != null && Bukkit.getPlayerExact(args[0]).isOnline()) {
						if (player.getLocation()
								.distance(Bukkit.getPlayerExact(args[0]).getLocation()) <= radiusFrisk) {
							API.dropAllItems(args[0]);
							if (API.getFriskedPlayers().contains(args[0]))
								API.getFriskedPlayers().remove(args[0]);
							player.sendMessage(
									"§e§oPlayer §c§o" + args[0] + " §e§owas forced to drop all their items.");
							API.sendConsoleCommand("discordsrv broadcast #819586816444727316 " + player.getName()
									+ " - dropitems - " + args[0]);
						} else {
							player.sendMessage(
									"§8[§c§l!§8] §c§oYou need to be closer than §c§l3§c§o blocks (or less) to §7§l"
											+ args[0] + "§c§o.");
						}
					} else {
						player.sendMessage("§8§l[§c§l!§8§l] §c§oPlayer §e§o" + args[0] + " §c§ois not online!");
					}
				}
			}
		}

		if (cmd.getName().equalsIgnoreCase("breach")) {
			if (args.length == 0) {
				if (!player.hasPermission("scprp.secoff")) {
					if (args.length == 0)
						player.sendMessage("§8§l[§c§l!§8§l] §c§oTo report a breach, use §4§l/breach report");
				} else {
					if (args.length == 0)
						player.sendMessage("§8§l[§c§l!§8§l] §c§oTo report a breach, use §4§l/breach report");
					if (args.length == 0)
						player.sendMessage("§8§l[§c§l!§8§l] §c§oTo end a breach, use §4§l/breach end");
					if (args.length == 0)
						player.sendMessage("§8§l[§c§l!§8§l] §c§oTo log a SCP that breached, use §4§l/breach log (SCP)");
				}
			} else if (args.length == 1) {
				if (args[0].equalsIgnoreCase("report")) {
					if (breachstate == false) {
						breachstate = true;
						Node node = Node.builder("essentials.heal").value(true).build();
						api.getGroupManager().getGroup("medic").data().add(node);

						for (Player p : Bukkit.getOnlinePlayers()) {
							User user = api.getUserManager().getUser(player.getName());
							if (user.getPrimaryGroup().equalsIgnoreCase("medic")) {
								p.sendMessage(
										"§8[§e§l!§8] §e§oA breach has been reported! §d§lYou have gained permission to use §f§l/heal§d§l, until the breach ends.");
							}
						}

						player.chat("/dcbreach");
						API.sendConsoleCommand(
								"discordsrv broadcast #819586816444727316 " + player.getName() + " - breach report");
						for (Player p : Bukkit.getOnlinePlayers()) {
							p.playSound(p.getLocation(), Sound.BLOCK_GLASS_BREAK, 2.0F, 1.0F);
							p.sendMessage("§8[§c§l!§8] §4§l" + player.getName()
									+ " §c§oreported a breach. Find the nearest Breach Shelter as soon as possible and await instructions from Security Officers and Mobile Task Force units.");
						}

						if (API.getOnlineMTFNu7s() != "errorEmptyListNoOnline")
							API.sendWarning("§4§lA BREACH HAS BEEN REPORTED.",
									"§7§oAll combative units are to respond immediatelly.", "mtf-nu-7");
						if (API.getOnlineSecurityOfficers() != "errorEmptyListNoOnline")
							API.sendWarning("§4§lA BREACH HAS BEEN REPORTED.",
									"§7§oAll combative units are to respond immediatelly.", "security-officer");
					} else {
						player.sendMessage("§8[§c§l!§8] §c§oThe breach has already been reported.");
					}
				} else if (args[0].equalsIgnoreCase("end")) {
					if (player.hasPermission("scprp.secoff")) {
						if (breachstate == true) {
							Node node = Node.builder("essentials.heal").value(true).build();
							api.getGroupManager().getGroup("medic").data().remove(node);

							for (Player p : Bukkit.getOnlinePlayers()) {
								User user = api.getUserManager().getUser(player.getName());
								if (user.getPrimaryGroup().equalsIgnoreCase("medic")) {
									p.sendMessage("§8[§e§l!§8] §d§oYou are no longer able to use §f§l/heal§d§o.");
								}
							}

							player.chat("/dcendbreach");
							API.sendConsoleCommand(
									"discordsrv broadcast #819586816444727316 " + player.getName() + " - breach end");
							for (Player p : Bukkit.getOnlinePlayers()) {
								p.playSound(p.getLocation(), Sound.BLOCK_NOTE_PLING, 2.0F, 1.0F);
								p.sendMessage(
										"§8[§a§l!§8] §a§oAll breached SCPs have been recontained. The facility is safe now.");
							}
							breachstate = false;
						} else {
							player.sendMessage(
									"§8[§c§l!§8] §c§oThere is no breach at the moment. Use §e§l/breach report§c§o, if you have seen a breached SCP.");
						}
					} else {
						player.sendMessage("§8§l[§c§l!§8§l] §c§oYou do not have any permission to use this command.");
					}
				} else if (args[0].equalsIgnoreCase("log")) {
					player.sendMessage(
							"§8§l[§c§l!§8§l] §c§oYou must state the SCP's designation! Example: §e§l/breach log 173");
				} else {
					player.sendMessage("§8§l[§c§l!§8§l] §c§oWrong usage. See §e§l/breach§c§o for more information.");
				}
			} else if (args.length == 2) {
				if (args[0].equalsIgnoreCase("log") && args[1] != null) {
					if (player.hasPermission("scprp.secoff")) {
						if (breachstate) {
							if (TestingSys.scps.containsKey(args[1])) {

								API.sendMessageToEveryone("§8[§9Intercom§8] §c§lSCP-" + args[1]
										+ " §c§ohas been confirmed as §4§lBREACHED§c§o.");
								API.sendConsoleCommand("discordsrv broadcast #819586816444727316 " + player.getName()
										+ " - breach log - SCP-" + args[1]);
								if (TestingSys.begunTests.containsKey(args[1]))
									TestingSys.terminateTestBreach(player.getName(), args[1]);
							} else {
								player.sendMessage("§8§l[§c§lDb§8§l] §c§oThe stated SCP does not exist.");
							}
						} else {
							player.sendMessage("§8§l[§c§l!§8§l] §c§oThere is no ongoing breach.");
						}
					} else
						player.sendMessage("§8§l[§c§l!§8§l] §c§oYou do not have any permission to use this command.");
				} else {
					player.sendMessage("§8§l[§c§l!§8§l] §c§oWrong usage. See §e§l/breach§c§o for more information.");
				}
			}
		}

		if (cmd.getName().equalsIgnoreCase("tc") || cmd.getName().equalsIgnoreCase("testchat")) {
			if (TestingSys.testMembers.containsKey(TestingSys.findTestByPlayer(player.getName()))) {
				if (!TestChat.getConnectedPlayers().contains(player.getName())) {
					TestChat.enableChat(TestingSys.findTestByPlayer(player.getName()), player.getName());
				} else {
					TestChat.disableChat(TestingSys.findTestByPlayer(player.getName()), player.getName());
				}
			} else {
				player.sendMessage("§8§l[§c§l!§8§l] §c§oYou are not attending any test!");
			}
		}

		if (cmd.getName().equalsIgnoreCase("storage")) {
			if (api.getUserManager().getUser(player.getName()).getPrimaryGroup().equals("site-director")
					|| api.getUserManager().getUser(player.getName()).getPrimaryGroup().equals("technic")
					|| api.getUserManager().getUser(player.getName()).getPrimaryGroup().equals("storage-leader")
					|| player.hasPermission("scprp.admin") || player.hasPermission("scprp.storage")
					|| player.hasPermission("scprp.storage.*")) {
				MainSM.openGUI(player, api.getUserManager().getUser(player.getName()).getPrimaryGroup());
			} else {
				player.sendMessage("§8§l[§c§l!§8§l] §c§oYou do not have any permission to use this command.");
			}
		}

		if (cmd.getName().equalsIgnoreCase("testworld")) {
			if (api.getUserManager().getUser(player.getName()).getPrimaryGroup().equals("site-director")
					|| player.hasPermission("scprp.o5") || player.hasPermission("scprp.admin")) {

				ConversationFactory factory = new ConversationFactory(SCPRP.getInstance());
				Conversation conv = factory.withFirstPrompt(new Testworldconv1(SCPRP.getInstace(), player.getName()))
						.withLocalEcho(true).withEscapeSequence("exit").buildConversation(player);
				conv.begin();

			} else {
				player.sendMessage("§8§l[§c§l!§8§l] §c§oYou do not have any permission to use this command.");
			}
		}

		if (cmd.getName().equalsIgnoreCase("payout")) {
			if (sender instanceof Player) {
				if (player.hasPermission("scprp.admin") || player.hasPermission("scpd.admin")
						|| player.hasPermission("scp.mod") || player.hasPermission("scprp.mtfcmd")
						|| player.hasPermission("scprp.mod") || player.hasPermission("scprp.headres")
						|| player.hasPermission("scprp.headmedic") || player.hasPermission("scprp.headsecoff")
						|| player.hasPermission("scprp.sitedir") || player.hasPermission("scprp.o5")) {

					if (args.length == 0) {
						player.sendMessage(
								"§8§l[§2§lBANK§8§l] §2§oTo pay a player out, use §a§l/payout (Player) (Value)");
					} else if (args.length == 1) {
						player.sendMessage(
								"§8§l[§2§lBANK§8§l] §2§oTo pay a player out, use §a§l/payout §c§l(Player)§a§l (Value)");
					} else if (args.length == 2) {
						if (args[1] == null || args[1].isEmpty()) {
							player.sendMessage(
									"§8§l[§2§lBANK§8§l] §2§oTo pay a player out, use §a§l/payout (Player) §c§l(Value)");
						} else {
							if (Bukkit.getPlayerExact(args[0]) != null) {
								if (Bukkit.getPlayerExact(args[0]).isOnline()) {
									if (API.isInteger(args[1])) {
										Player target = Bukkit.getPlayerExact(args[0]);
										int value = API.parse(args[1]);
										try {
											Economy.add(args[0], (double) value);
											player.sendMessage(
													"§8[§2§lBANK§8] §a§oThe transaction was successful. Target:§f§l "
															+ args[0] + "§a§o, value: §2§l" + value + "§a§o.");
											target.sendMessage(
													"§8[§2§lBANK§8] §a§oYou have received your payout from §f§l"
															+ player.getName() + " §a§o. Value: §2§l" + value
															+ "§a§o.");
											API.sendConsoleCommand(
													"discordsrv broadcast #849573930308993024 " + player.getName()
															+ " - payout - target-" + args[0] + " - value: " + value);
										} catch (Exception e) {
											API.sendConsoleCommand(
													"discordsrv broadcast #839057785362448424 [SCPRP] payout MaxMoneyException, sent by: "
															+ player.getName() + " to " + args[0] + ", value: "
															+ value);
											e.printStackTrace();
										}
									} else {
										player.sendMessage("§8§l[§c§lBANK§8§l] §c§oValue must be a number!");
									}
								} else {
									player.sendMessage("§8§l[§c§lBANK§8§l] §e§l" + args[0] + "§c§o is not online!");
								}
							} else {
								player.sendMessage("§8§l[§c§lBANK§8§l] §e§l" + args[0] + "§c§o does not exist!");
							}
						}
					} else {
						player.sendMessage(
								"§8§l[§c§lBANK§8§l] §c§oWrong usage. See §2§l/payout§c§o for more information.");
					}
				} else {
					player.sendMessage("§8§l[§c§l!§8§l] §c§oYou do not have any permission to use this command.");
				}
			} else if (sender instanceof ConsoleCommandSender) {
				ConsoleCommandSender console = getServer().getConsoleSender();
				sender = console;
				if (args.length == 2) {
					if (args[1] == null || args[1].isEmpty()) {
						System.out.println(
								"§8§l[§2§lBANK§8§l] §2§oTo pay a player out, use §a§l/payout (Player) §c§l(Value)");
					} else {
						if (Bukkit.getPlayerExact(args[0]) != null) {
							if (Bukkit.getPlayerExact(args[0]).isOnline()) {
								if (API.isInteger(args[1])) {
									Player target = Bukkit.getPlayerExact(args[0]);
									int value = API.parse(args[1]);
									try {
										Economy.add(args[0], (double) value);
										System.out.println(
												"§8[§2§lBANK§8] §a§oThe transaction was successful. Target:§f§l "
														+ args[0] + "§a§o, value: §2§l" + value + "§a§o.");
										target.sendMessage(
												"§8[§2§lBANK§8] §a§oYou have received your payout. Value: §2§l" + value
														+ "§a§o.");
										API.sendConsoleCommand("discordsrv broadcast #849573930308993024 CONSOLE"
												+ " - payout - target-" + args[0] + " - value: " + value);
									} catch (Exception e) {
										API.sendConsoleCommand(
												"discordsrv broadcast #839057785362448424 [SCPRP] Payout Exception, sent by: CONSOLE to "
														+ args[0] + ", value: " + value);
										e.printStackTrace();
									}
								} else {
									System.out.println("§8§l[§c§lBANK§8§l] §c§oValue must be a number!");
								}
							} else {
								System.out.println("§8§l[§c§lBANK§8§l] §e§l" + args[0] + "§c§o is not online!");
							}
						} else {
							System.out.println("§8§l[§c§lBANK§8§l] §e§l" + args[0] + "§c§o does not exist!");
						}
					}
				}
			}
		}

		if (cmd.getName().equalsIgnoreCase("payoutxp")) {
			if (sender instanceof Player) {
				if (player.hasPermission("scprp.admin") || player.hasPermission("scpd.admin")
						|| player.hasPermission("scp.mod") || player.hasPermission("scprp.mtfcmd")
						|| player.hasPermission("scprp.mod") || player.hasPermission("scprp.headres")
						|| player.hasPermission("scprp.headmedic") || player.hasPermission("scprp.headsecoff")
						|| player.hasPermission("scprp.sitedir") || player.hasPermission("scprp.o5")) {
					if (args.length == 0) {
						player.sendMessage(
								"§8§l[§9§l?§8§l] §b§oTo pay a player out in §c§nRP Experience§b§o, use §e§l/payoutxp (Player) (Value)");
					} else if (args.length == 1) {
						player.sendMessage(
								"§8§l[§9§l?§8§l] §b§oTo pay a player out in §c§nRP Experience§b§o, use §e§l/payoutxp (Player) (Value)");
					} else if (args.length == 2) {
						if (Bukkit.getPlayerExact(args[0]) != null && Bukkit.getPlayerExact(args[0]).isOnline()) {
							if (LevellingSys.roleHasLevel(args[0])) {
								if (API.isInteger(args[1])) {
									LevellingSys.addXP(args[0], API.parse(args[1]));
									API.sendConsoleCommand(
											"discordsrv broadcast #849573930308993024 " + player.getName()
													+ " - payout**xp** - target-" + args[0] + " - value: " + args[1]);
									player.sendMessage("§8§l[§a§l!§8§l] §a§oSuccessfully sent §b§l" + args[1]
											+ " §a§oXP to §f§l" + args[0] + "§a§o.");
								} else {
									player.sendMessage("§8[§c§l!§8] §c§o" + args[1]
											+ "§8§o is not a number, or can not be used. Contact §4§lThe Administrator§c§o in case of a bug.");
								}
							} else {
								player.sendMessage("§8[§c§l!§8] §c§o" + args[0]
										+ "§8§o's role is not a part of the RP Levelling System. Contact §4§lThe Administrator§c§o in case of this being a bug.");
							}
						} else {
							player.sendMessage("§8[§c§l!§8] §c§o" + args[0] + "§8§o is not online!");
						}
					}
				} else {
					player.sendMessage("§8[§c§l!§8] §c§oYou do not have any permission to use this command.");
				}
			}
		}

		if (cmd.getName().equalsIgnoreCase("revive")) {
			double radius = 3D;
			if (player.hasPermission("scprp.medic")) {
				if (args.length == 0) {
					if (args.length == 0)
						player.sendMessage("§8§l[§a§l!§8§l] §a§oTo revive a player, use §f§l/revive (Player)");
				} else if (args.length == 1) {
					String name = args[0];
					if (Bukkit.getPlayerExact(name) != null) {
						if (args[0].equalsIgnoreCase(player.getName())) {
							player.sendMessage("§8[§c§l!§8] §c§oYou can not revive yourself.");
						} else {

							if (DRSys.isParalysed(name)) {
								if (player.getLocation()
										.distance(Bukkit.getPlayerExact(name).getLocation()) <= radius) {
									DRSys.revive(player, name);
									API.sendConsoleCommand("discordsrv broadcast #819586816444727316 "
											+ player.getName() + " - revive - Target: " + name);
								} else {
									player.sendMessage(
											"§8[§c§l!§8] §c§oYou need to be closer than §c§l3§c§o blocks (or less) to §7§l"
													+ name + "§c§o.");
								}
							} else {
								sender.sendMessage("§8[§c§l!§8] §c§o" + args[0]
										+ "§2§o is not knocked down and does not need to be revived.");
							}
						}
					} else {
						player.sendMessage("§8[§c§l!§8] §c§o" + name + "§8§o is not online!");
					}
				} else {
					player.sendMessage("§8§l[§c§l!§8§l] §c§oWrong usage. See §a§l/revive§c§o for more information.");
				}
			} else {
				player.sendMessage("§8§l[§c§l!§8§l] §c§oYou do not have any permission to use this command.");
			}
		}

		if (cmd.getName().equalsIgnoreCase("arrest")) {
			double radius = 3D;
			if (player.hasPermission("scprp.secoff")) {
				if (args.length == 0)
					player.sendMessage(
							"§8§l[§9§l?§8§l] §c§oTo arrest a player, use §f§l/arrest §e§l(Player) §7§l(Reason)§c§o.");
				if (args.length == 1) {
					String name = args[0];
					Player target = Bukkit.getPlayerExact(name);
					if (Bukkit.getServer().getPlayerExact(name) != null) {
						if (!API.arrestedPlayers.containsKey(target.getUniqueId())) {
							if (player.getLocation().distance(target.getLocation()) <= radius) {
								API.arrest(target);
								player.sendMessage("§8[§a§l!§8] §a§oArrested §e§l" + name + "§a§o.");
								target.sendMessage(
										"§8[§c§l!§8] §7§oYou have been arrested by §c§l" + player.getName() + "§7§o.");
								player.sendMessage(
										"§8[§c§l!§8] §c§oDo not forget to make a report about this arrest. See the Security Officer tasks document for more information.");
								API.sendConsoleCommand("discordsrv broadcast #819586816444727316 " + player.getName()
										+ " - arrest - Target: " + name + " - Reason: ---not specified---");
							} else {
								player.sendMessage(
										"§8[§c§l!§8] §c§oYou need to be closer than §c§l3§c§o blocks (or less) to §7§l"
												+ name + "§c§o.");
							}
						} else {
							player.sendMessage("§8[§c§l!§8] §c§o" + name + "§8§o is already arrested!");
						}
					} else {
						player.sendMessage("§8[§c§l!§8] §c§o" + name + "§8§o is not online!");
					}
				} else if (args.length >= 2) {
					String name = args[0];
					String reason = "";
					Player target = Bukkit.getPlayerExact(args[0]);
					if (Bukkit.getServer().getPlayerExact(name) != null) {
						if (!API.arrestedPlayers.containsKey(target.getUniqueId())) {
							if (player.getLocation().distance(target.getLocation()) <= radius) {
								for (int i = 1; i < args.length; i++) {
									reason = reason + args[i] + " ";
								}

								API.arrest(target);
								player.sendMessage("§8[§a§l!§8] §a§oArrested §e§l" + name + "§a§o.");
								target.sendMessage("§8[§c§l!§8] §7§oYou have been arrested by §c§l" + player.getName()
										+ "§7§o. Reason: §2§l" + reason);
								API.sendConsoleCommand("discordsrv broadcast #819586816444727316 " + player.getName()
										+ " - arrest - Target: " + name + " - Reason: " + reason);
								for (Player p : Bukkit.getOnlinePlayers()) {
									p.sendMessage("§8[§c!§8] §c§o" + name + "§8§o has been arrested by §e§l"
											+ player.getName() + "§8§o. Reason: §2§l" + reason);
								}
							} else {
								player.sendMessage(
										"§8[§c§l!§8] §c§oYou need to be closer than §c§l3§c§o blocks (or less) to §7§l"
												+ name + "§c§o.");
							}
						} else {
							player.sendMessage("§8[§c§l!§8] §c§o" + name + "§8§o is already arrested!");
						}
					} else {
						player.sendMessage("§8[§c§l!§8] §c§o" + name + "§8§o is not online!");
					}
				}
			} else {
				player.sendMessage("§8§l[§c§l!§8§l] §c§oYou do not have any permission to use this command.");
			}
		}

		if (cmd.getName().equalsIgnoreCase("daily")) {
			long now = System.currentTimeMillis();
			if (PlayerInfoFile.getPlayerInfoFile().getConfig()
					.getString("Players." + player.getName() + ".DailyReward") != null) {
				if (PlayerInfoFile.getPlayerInfoFile().getConfig()
						.getString("Players." + player.getName() + ".DailyReward") == "0") {
					DailyRewards.claimReward(player.getName());
				} else {
					if (now - Long.parseLong(PlayerInfoFile.getPlayerInfoFile().getConfig()
							.getString("Players." + player.getName() + ".DailyReward")) < 86400000) {
						player.sendMessage("§8§l[§c§l!§8§l] §e§oYou can claim your next daily reward in §f§l"
								+ API.formatTimeHM((Long.parseLong(PlayerInfoFile.getPlayerInfoFile().getConfig()
										.getString("Players." + player.getName() + ".DailyReward")) + 86400000) - now)
								+ "§e§o.");
					} else {
						DailyRewards.claimReward(player.getName());
					}
				}
			} else {
				PlayerInfoFile.getPlayerInfoFile().getConfig().set("Players." + player.getName() + ".DailyReward", "0");
				PlayerInfoFile.getPlayerInfoFile().save();
				DailyRewards.claimReward(player.getName());
			}
		}

		if (cmd.getName().equalsIgnoreCase("cell")) {
			if (player.hasPermission("scprp.admin") || player.hasPermission("scprp.o5")
					|| player.hasPermission("scprp.sitedir")) {
				if (args.length == 0) {
					if (args.length == 0) {
						player.sendMessage("§8§o----------------------------------------------------");
						player.sendMessage(
								"§6§oTo add a new cell, use §9§l/cell add (§f§ominimum/§6§omedium/§4§omaximum/§7§ljail)");
						player.sendMessage("§6§oTo view informations about a cell, use §9§l/cell info (id)");
						player.sendMessage(
								"§6§oTo find out how many cells are free to assign (unowned), use §9§l/cell freecells");
						player.sendMessage("§6§oTo find out the ID of a Class-D's cell, use §9§l/cell find (name)");
						player.sendMessage("§6§oTo teleport a Class-D into his/her cell, use §9§l/cell tp (name)");
						player.sendMessage("§6§oTo remove a cell, use §9§l/cell remove (id)");
						player.sendMessage(
								"§6§oTo set a Class-D as an owner of a cell, use §9§l/cell setowner (id) (Class-D's nick)");
						player.sendMessage("§6§oTo remove a cell's owner, use §9§l/cell removeowner (id)");
						player.sendMessage("§6§oTo remove owners of all Minimum and Medium cells, use §9§l/cell clearnewbies");
						player.sendMessage("§8§o----------------------------------------------------");
					}

				} else if (args.length == 1) {
					if (args[0].equalsIgnoreCase("switch")) {
						// CellSystem.switchAssigning(player);
					} else if (args[0].equalsIgnoreCase("freecells")) {
						CellSystem.countFreeCells(player);
					} else if (args[0].equalsIgnoreCase("clearnewbies")) {
								CellSystem.clearAllNewbieCells();
								API.sendConsoleCommand("discordsrv broadcast #819586816444727316 " + player.getName()
								+ " - Reset all Minimum and Medium cells");
								player.sendMessage("§8§l[§a§l!§8§l] §a§oAll §f§lMinimum§a§o and §6§lMedium§a§o cells were reset.");
					} else {
						player.sendMessage("§8§o----------------------------------------------------");
						player.sendMessage(
								"§c§oTo add a new cell, use §9§l/cell add (§f§ominimum/§6§omedium/§4§omaximum§7§ljail))");
						player.sendMessage("§c§oTo view informations about a cell, use §9§l/cell info (id)");
						player.sendMessage(
								"§6§oTo find out how many cells are free to assign (unowned), use §9§l/cell freecells");
						player.sendMessage("§c§oTo find out the ID of a Class-D's cell, use §9§l/cell find (name)");
						player.sendMessage("§c§oTo teleport a Class-D into his/her cell, use §9§l/cell tp (name)");
						player.sendMessage("§c§oTo remove a cell, use §9§l/cell remove (id)");
						player.sendMessage(
								"§c§oTo set a Class-D as an owner of a cell, use §9§l/cell setowner (id) (Class-D's nick)");
						player.sendMessage("§c§oTo remove a cell's owner, use §9§l/cell removeowner (id)");
						player.sendMessage("§6§oTo remove owners of all Minimum and Medium cells, use §9§l/cell clearnewbies");
						player.sendMessage("§8§o----------------------------------------------------");
					}
				} else if (args.length == 2) {
					switch (args[0].toLowerCase()) {
					case "add":
						switch (args[1].toLowerCase()) {
						case "minimum":
							CellSystem.addCell("minimum", player.getLocation(), player);
							break;
						case "medium":
							CellSystem.addCell("medium", player.getLocation(), player);
							break;
						case "maximum":
							CellSystem.addCell("maximum", player.getLocation(), player);
							break;
						case "min":
							CellSystem.addCell("minimum", player.getLocation(), player);
							break;
						case "med":
							CellSystem.addCell("medium", player.getLocation(), player);
							break;
						case "max":
							CellSystem.addCell("maximum", player.getLocation(), player);
						case "jail":
							CellSystem.addCell("jail", player.getLocation(), player);
							break;
						default:
							player.sendMessage(
									"§8§l[§c§l!§8§l] §c§oAvailable Class-D Security Risks: §f§lMinimum §8(§f§omin§8)§c§o, §6§lMedium §8(§6§omed)8)§c§o, §4§lMaximum §8(§4§omax§8)§c§o, §7§jJail§c§o.");
							break;
						}
						break;
					case "info":
						if (API.findCellLevelById(args[1]) != "error") {
							Cell cell;
							switch (API.findCellLevelById(args[1])) {
							case "minimum":
								cell = CellSystem.getMinimumCells().get(args[1]);
								player.sendMessage("§8[§5§lDb§8] §a§oCell data retrieved successfully:");
								player.sendMessage("§8 - §6§oID: §9§l" + args[1]);
								player.sendMessage("§8 - §6§oX, Y, Z: §9§l" + cell.getX() + "§6§o, §9§l" + cell.getY()
										+ "§6§o, §9§l" + cell.getZ());
								player.sendMessage("§8 - §6§oWorld: §9§l" + cell.getWorld());
								player.sendMessage("§8 - §6§oSecurity risk: §f§lMinimum");
								if (cell.getOwner().equalsIgnoreCase("NoOwnerYetNoOwnerYet")) {
									player.sendMessage("§8 - §c§oCell not assigned to any §6§oClass-D§c§o.");
								} else {
									player.sendMessage("§8 - §6§oAssigned Class-D (owner): §9§l" + cell.getOwner());
								}
								break;
							case "medium":
								cell = CellSystem.getMediumCells().get(args[1]);
								player.sendMessage("§8[§5§lDb§8] §a§oCell data retrieved successfully:");
								player.sendMessage("§8 - §6§oID: §9§l" + args[1]);
								player.sendMessage("§8 - §6§oX, Y, Z: §9§l" + cell.getX() + "§6§o, §9§l" + cell.getY()
										+ "§6§o, §9§l" + cell.getZ());
								player.sendMessage("§8 - §6§oWorld: §9§l" + cell.getWorld());
								player.sendMessage("§8 - §6§oSecurity risk: §6§lMedium");
								if (cell.getOwner().equalsIgnoreCase("NoOwnerYetNoOwnerYet")) {
									player.sendMessage("§8 - §c§oCell not assigned to any §6§oClass-D§c§o.");
								} else {
									player.sendMessage("§8 - §6§oAssigned Class-D (owner): §9§l" + cell.getOwner());
								}
								break;
							case "maximum":
								cell = CellSystem.getMaximumCells().get(args[1]);
								player.sendMessage("§8[§5§lDb§8] §a§oCell data retrieved successfully:");
								player.sendMessage("§8 - §6§oID: §9§l" + args[1]);
								player.sendMessage("§8 - §6§oX, Y, Z: §9§l" + cell.getX() + "§6§o, §9§l" + cell.getY()
										+ "§6§o, §9§l" + cell.getZ());
								player.sendMessage("§8 - §6§oWorld: §9§l" + cell.getWorld());
								player.sendMessage("§8 - §6§oSecurity risk: §4§lMaximum");
								if (cell.getOwner().equalsIgnoreCase("NoOwnerYetNoOwnerYet")) {
									player.sendMessage("§8 - §c§oCell not assigned to any §6§oClass-D§c§o.");
								} else {
									player.sendMessage("§8 - §6§oAssigned Class-D (owner): §9§l" + cell.getOwner());
								}
								break;
							case "jail":
								cell = CellSystem.getJailCells().get(args[1]);
								player.sendMessage("§8[§5§lDb§8] §a§oCell data retrieved successfully:");
								player.sendMessage("§8 - §6§oID: §9§l" + args[1]);
								player.sendMessage("§8 - §6§oX, Y, Z: §9§l" + cell.getX() + "§6§o, §9§l" + cell.getY()
										+ "§6§o, §9§l" + cell.getZ());
								player.sendMessage("§8 - §6§oWorld: §9§l" + cell.getWorld());
								player.sendMessage("§8 - §6§oSecurity risk: §7§lJail");
								if (cell.getOwner().equalsIgnoreCase("NoOwnerYetNoOwnerYet")) {
									player.sendMessage("§8 - §c§oCell not assigned to any §6§oClass-D§c§o.");
								} else {
									player.sendMessage("§8 - §6§oAssigned Class-D (owner): §9§l" + cell.getOwner());
								}
								break;
							default:
								break;
							}
						} else {
							player.sendMessage("§8[§c§l!§8] §c§oCell §e§o#§l" + args[1] + " §c§odoes not exist.");
						}
						break;
					case "tp":
						if (API.findCellIdByPname(args[1]) != "error") {
							if (Bukkit.getPlayerExact(args[1]) != null) {
								if (Bukkit.getPlayerExact(args[1]).isOnline()) {
									CellSystem.teleportToCell(args[1], "main");
									player.sendMessage("§8[§a§l!§8] §a§oTeleported §6§o" + args[1]
											+ " §a§ointo his/her cell §e§o#§l" + API.findCellIdByPname(args[1])
											+ "§a§o.");
								} else {
									player.sendMessage("§8[§c§l!§8] §c§oPlayer §e§l" + args[1] + " §c§ois not online!");
								}
							} else {
								player.sendMessage("§8[§c§l!§8] §c§oPlayer §e§l" + args[1] + " §c§ois not online!");
							}
						} else {
							player.sendMessage("§8[§c§l!§8] §e§o" + args[1] + " §c§odoes not own any cell.");
						}
						break;
					case "find":
						if (API.findCellIdByPname(args[1]) != "error") {
							player.sendMessage("§8[§5§lDb§8] §a§oCell ID for Class-D §6§l" + args[1]
									+ " §a§ofound: §9§o#§l" + API.findCellIdByPname(args[1]) + " §a§o.");
						} else {
							player.sendMessage("§8[§c§l!§8] §e§o" + args[1] + " §c§odoes not own any cell.");
						}
						break;
					case "remove":
						if (API.findCellLevelById(args[1]) != "error") {
							String id = args[1];
							switch (API.findCellLevelById(id)) {
							case "minimum":
								CellSystem.getMinimumCells().remove(id);
								CellSystem.saveFile();
								player.sendMessage(
										"§8[§a§l!§8] §a§oCell §9§o#§l" + id + " §c§oremoved §a§osuccessfully.");
								break;
							case "medium":
								CellSystem.getMediumCells().remove(id);
								CellSystem.saveFile();
								player.sendMessage(
										"§8[§a§l!§8] §a§oCell §9§o#§l" + id + " §c§oremoved §a§osuccessfully.");
								break;
							case "maximum":
								CellSystem.getMaximumCells().remove(id);
								CellSystem.saveFile();
								player.sendMessage(
										"§8[§a§l!§8] §a§oCell §9§o#§l" + id + " §c§oremoved §a§osuccessfully.");
							case "jail":
								CellSystem.getJailCells().remove(id);
								CellSystem.saveFile();
								player.sendMessage(
										"§8[§a§l!§8] §a§oCell §9§o#§l" + id + " §c§oremoved §a§osuccessfully.");
								break;
							default:
								break;
							}
						} else {
							player.sendMessage("§8[§c§l!§8] §c§oCell §e§o#§l" + args[1] + " §c§odoes not exist.");
						}
						break;
					case "setowner":
						player.sendMessage(
								"§8[§c§l!§8] §c§oTo set a Class-D as an owner of a cell, use §9§l/cell setowner (id) (Class-D's nick)");
						break;
					case "removeowner":
						if (args[1] != null || !args[1].isEmpty()) {
							CellSystem.removeOwner(args[1], player);
						} else {
							player.sendMessage(
									"§8[§c§l!§8] §c§oTo remove a cell's owner, use §9§l/cell removeowner (id)");
						}
						break;
					default:
						player.sendMessage("§8§l[§c§l!§8§l] §c§oWrong usage. See §9§l/cell§c§o for more information.");
						break;
					}
				} else if (args.length == 3) {
					if (args[0].equalsIgnoreCase("setowner")) {
						if (args[1] == null || args[1].isEmpty() || args[2] == null || args[2].isEmpty()) {
							player.sendMessage(
									"§8[§c§l!§8] §c§oTo set a Class-D as an owner of a cell, use §9§l/cell setowner (id) (Class-D's nick)");
						} else {
							CellSystem.setOwner(args[1], args[2], player);
						}
					} else {
						player.sendMessage("§8§l[§c§l!§8§l] §c§oWrong usage. See §9§l/cell§c§o for more information.");
					}
				} else {
					player.sendMessage("§8§l[§c§l!§8§l] §c§oWrong usage. See §9§l/cell§c§o for more information.");
				}
			} else {
				player.sendMessage("§8§l[§c§l!§8§l] §c§oYou do not have any permission to use this command.");
			}
		}

		if (cmd.getName().equalsIgnoreCase("jail")) {
			if (api.getUserManager().getUser(player.getName()).getPrimaryGroup()
					.equalsIgnoreCase("head-security-officer")
					|| api.getUserManager().getUser(player.getName()).getPrimaryGroup()
							.equalsIgnoreCase("mtf-nu-7-commander")
					|| api.getUserManager().getUser(player.getName()).getPrimaryGroup().equalsIgnoreCase("judge")
					|| api.getUserManager().getUser(player.getName()).getPrimaryGroup()
							.equalsIgnoreCase("site-director")
					|| player.hasPermission("scprp.o5") || player.hasPermission("scp.admin")
					|| player.hasPermission("scp.mod")) {
				if (args.length == 0) {
					player.sendMessage("§8§l[§9§l?§8§l] §c§oTo jail a player, use §e§o/jail (Nickname)");
					player.sendMessage("§8§l[§9§l?§8§l] §c§oTo unjail a player, use §a§o/unjail (Nickname)");
				}
				if (args.length == 1) {
					if (Bukkit.getPlayerExact(args[0]) != null && Bukkit.getPlayerExact(args[0]).isOnline()) {
						if (CellSystem.getJailedPlayers().indexOf(args[0]) == -1) {
							CellSystem.jailPlayer(args[0], player);
							player.sendMessage("§8§l[§a§l!§8§l] §c§oPlayer §e§l" + args[0]
									+ " §c§owas put into jail §a§lsuccessfully§c§o.");
						} else {
							player.sendMessage("§8§l[§c§l!§8§l] §c§oPlayer §e§l" + args[0] + " §c§ois already jailed!");
						}
					} else {
						player.sendMessage("§8§l[§c§l!§8§l] §c§oThe stated player is not online!");
					}
				}
			} else {
				player.sendMessage("§8§l[§c§l!§8§l] §c§oYou do not have any permission to use this command.");
			}
		}

		if (cmd.getName().equalsIgnoreCase("unjail")) {
			API.sendDebugMsg("jail size:" + CellSystem.getJailedPlayers().size());
			for (String str : CellSystem.getJailedPlayers()) {
				API.sendDebugMsg(str);
			}
			if (api.getUserManager().getUser(player.getName()).getPrimaryGroup()
					.equalsIgnoreCase("head-security-officer")
					|| api.getUserManager().getUser(player.getName()).getPrimaryGroup()
							.equalsIgnoreCase("site-director")
					|| player.hasPermission("scprp.o5") || player.hasPermission("scp.admin")
					|| player.hasPermission("scp.mod")) {
				if (args.length == 0) {
					player.sendMessage("§8§l[§9§l?§8§l] §c§oTo jail a player, use §e§o/jail (Nickname)");
					player.sendMessage("§8§l[§9§l?§8§l] §c§oTo unjail a player, use §a§o/unjail (Nickname)");
				}
				if (args.length == 1) {
					if (Bukkit.getPlayerExact(args[0]) != null && Bukkit.getPlayerExact(args[0]).isOnline()) {
						if (CellSystem.getJailedPlayers().indexOf(args[0]) != -1) {
							CellSystem.unjailPlayer(args[0]);
							player.sendMessage("§8§l[§a§l!§8§l] §c§oPlayer §e§l" + args[0]
									+ " §c§owas released from jail §a§lsuccessfully§c§o.");
						} else {
							player.sendMessage("§8§l[§c§l!§8§l] §c§oPlayer §e§l" + args[0] + " §c§ois not jailed!");
						}
					} else {
						player.sendMessage("§8§l[§c§l!§8§l] §c§oThe stated player is not online!");
					}
				}
			} else {
				player.sendMessage("§8§l[§c§l!§8§l] §c§oYou do not have any permission to use this command.");
			}
		}

		if (cmd.getName().equalsIgnoreCase("frisk")) {
			double radiuss = 3D;
			if (player.hasPermission("scprp.secoff")) {
				if (args.length == 0)
					player.sendMessage("§8§l[§9§l?§8§l] §b§oTo frisk a player, use /frisk §9§l(Player)");
				if (args.length == 1) {
					String name = args[0];
					if (Bukkit.getServer().getPlayerExact(name) != null) {
						Player target = Bukkit.getPlayerExact(name);
						if (player.getLocation().distance(target.getLocation()) <= radiuss) {
							player.performCommand("invsee " + name);
							API.getFriskedPlayers().add(name);
							target.sendMessage(
									"§8[§c§l!§8] §7§oYou are being frisked by §c§l" + player.getName() + "§7§o.");
							player.sendMessage("§8[§a§l!§8] §a§oFrisking §e§l" + name + "§a§o.");
							player.sendMessage(
									"§8[§c§l!§8] §c§oIf you find any illegal items, follow instructions in Security Officer's tasks document.");
							API.sendConsoleCommand("discordsrv broadcast #819586816444727316 " + player.getName()
									+ " - frisk - Target: " + name);
						} else {
							player.sendMessage(
									"§8[§c§l!§8] §c§oYou need to be closer than §c§l3§c§o blocks (or less) to §7§l"
											+ name + "§c§o.");
						}
					} else {
						player.sendMessage("§8[§c§l!§8] §c§o" + name + "§8§o is not online!");
					}
				}
			} else {
				player.sendMessage("§8§l[§c§l!§8§l] §c§oYou do not have any permission to use this command.");
			}
		}

		if (cmd.getName().equalsIgnoreCase("unarrest")) {
			double radiusss = 3D;
			if (player.hasPermission("scprp.secoff")) {
				if (args.length == 0)
					player.sendMessage("§8§l[§9§l?§8§l] §b§oTo unarrest a player, use /unarrest §9§l(Player)");
				if (args.length == 1) {
					String name = args[0];
					if (Bukkit.getServer().getPlayerExact(name) != null) {
						Player target = Bukkit.getPlayerExact(name);
						if (player.getLocation().distance(target.getLocation()) <= radiusss) {
							API.arrestedPlayers.remove(target.getUniqueId());
							target.sendMessage("§8[§c§l!§8] §c§l" + player.getName() + "§7§o unarrested you.");
							player.sendMessage("§8[§a§l!§8] §a§oUnarrested §e§l" + name + "§a§o.");
							API.sendConsoleCommand("discordsrv broadcast #819586816444727316 " + player.getName()
									+ " - unarrest - Target: " + name);
						} else {
							player.sendMessage(
									"§8[§c§l!§8] §c§oYou need to be closer than §c§l3§c§o blocks (or less) to §7§l"
											+ name + "§c§o.");
						}

					} else {
						player.sendMessage("§8[§c§l!§8] §c§o" + name + "§8§o is not online!");
					}
				}
			} else {
				player.sendMessage("§8§l[§c§l!§8§l] §c§oYou do not have any permission to use this command.");
			}
		}

		if (cmd.getName().equalsIgnoreCase("tests")) {
			if (player.hasPermission("scprp.researcher1") && !player.hasPermission("scprp.researcher2")
					&& !player.hasPermission("scprp.researcher3") && !player.hasPermission("scprp.researcher4")) {
				Tests1GUI.openGUI(player);
			} else if (player.hasPermission("scprp.researcher2") && !player.hasPermission("scprp.researcher3")
					&& !player.hasPermission("scprp.researcher4")) {
				Tests2GUI.openGUI(player);
			} else if (player.hasPermission("scprp.researcher3") && !player.hasPermission("scprp.researcher4")) {
				Tests3GUI.openGUI(player);
			} else if (player.hasPermission("scprp.researcher4")) {
				Tests4GUI.openGUI(player);
			} else {
				player.sendMessage("§8§l[§c§l!§8§l] §c§oYou do not have any permission to use this command.");
			}
		}

		if (cmd.getName().equalsIgnoreCase("test")) {
			if (TestingSys.scps.isEmpty()) {
				System.out.println("[SCPRP DB] scps hashmap empty. Adding scps to the list.");
				API.sendConsoleCommand(
						"discordsrv broadcast #839057785362448424 [SCPRP DB] scps hashmap empty. Adding scps to the list.");
				TestingSys.addPersonnelReq();
			}
			if (args.length == 0) {
				if (player.hasPermission("scprp.test")) {
					player.sendMessage("§8§l[§9§l?§8§l] §b§oTo begin a SCP test, use §f§l/test start (SCP)");
					player.sendMessage(
							"§8§l[§9§l?§8§l] §b§oTo end the test (when it's completed - §a§lsuccessfully§b§o), use §f§l/test end (SCP)");
					player.sendMessage(
							"§8§l[§9§l?§8§l] §b§oTo kick a player out of your test, use §f§l/test kick (Player) (Reason)");
					player.sendMessage(
							"§8§l[§9§l?§8§l] §b§oTo view members of a SCP testing, use §f§l/test list (SCP)");
					player.sendMessage(
							"§8§l[§9§l?§8§l] §b§oTo view all currently running tests, use §f§l/test listall");
					if (player.hasPermission("scp.admin"))
						player.sendMessage(
								"§8§l[§4§lA§8§l] §c§oTo remove player's test cooldown, use §f§l/test removecd (Player)");
					if (player.hasPermission("scp.admin"))
						player.sendMessage("§8§l[§4§lA§8§l] §c§oTo force begin a test, use §f§l/test forcebegin (SCP)");
				} else {
					player.sendMessage("§8§l[§9§l?§8§l] §b§oTo join a SCP test, use §f§l/test join (SCP)");
					player.sendMessage(
							"§8§l[§9§l?§8§l] §b§oTo get a list of attending personnel of an ongoing test, use §f§l/test list (SCP)");
				}
			} else if (args.length == 1) {
				switch (args[0].toLowerCase()) {
				case "start":
					if (player.hasPermission("scprp.test")) {
						player.sendMessage("§8§l[§c§l!§8§l] §b§oTo begin a SCP test, use §f§l/test start (SCP)");
					} else {
						player.sendMessage("§8§l[§c§l!§8§l] §c§oYou do not have any permission to use this command.");
					}
					break;
				case "removecd":
					if (player.hasPermission("scp.admin")) {
						player.sendMessage(
								"§8§l[§4§lA§8§l] §c§oTo remove player's test cooldown, use §f§l/test removecd (Player)");
					} else {
						player.sendMessage("§8§l[§c§l!§8§l] §c§oYou do not have any permission to use this command.");
					}
					break;
				case "end":
					if (player.hasPermission("scprp.test")) {
						if (TestingSys.findTestByPlayer(player.getName()).equalsIgnoreCase("")
								|| TestingSys.findTestByPlayer(player.getName()).isEmpty()
								|| TestingSys.findTestByPlayer(player.getName()).length() <= 0
								|| TestingSys.findTestByPlayer(player.getName()) == null) {
							player.sendMessage("§8§l[§c§l!§8§l] §c§oYou do not lead any currently running test.");
						} else {
							if (ongoingTests.contains(TestingSys.findTestByPlayer(player.getName()))) {
								API.sendMessageToEveryone(player,
										"§8[§a§l!§8] §a§oTesting of §b§lSCP-"
												+ TestingSys.findTestByPlayer(player.getName())
												+ "§a§o has been completed. Researcher: §2§l" + player.getName());
								player.sendMessage(
										"§8[§c§l!§8] §e§oNow, make a report on Discord §f§l(#scp-test-results)§e§o.");
								if (tempList.contains(player.getName()))
									tempList.remove(player.getName());
								TestingSys.endTest(TestingSys.findTestByPlayer(player.getName()));
								API.sendConsoleCommand("discordsrv broadcast #819586816444727316 " + player.getName()
										+ " - test end - SCP-" + TestingSys.findTestByPlayer(player.getName()));
							} else {
								player.sendMessage("§8§l[§c§l!§8§l] §c§oYou do not lead any currently running test.");
							}
						}
					} else {
						player.sendMessage("§8§l[§c§l!§8§l] §c§oYou do not have any permission to use this command.");
					}
					break;
				case "kick":
					if (player.hasPermission("scprp.test")) {
						player.sendMessage(
								"§8§l[§c§l!§8§l] §b§oTo kick a player out of your test, use §f§l/test kick (Player) (Reason)");
					} else {
						player.sendMessage("§8§l[§c§l!§8§l] §c§oYou do not have any permission to use this command.");
					}
					break;
				case "list":
					player.sendMessage(
							"§8§l[§c§l!§8§l] §b§oTo view members of a SCP testing, use §f§l/test list (SCP)");
					break;
				case "join":
					if (player.hasPermission("scprp.test")) {
						player.sendMessage(
								"§8§l[§c§l!§8§l] §c§oResearchers can not join tests. They only can start their own.");
					} else if (CellSystem.getJailedPlayers().contains(player.getName())) {
						player.sendMessage(
								"§8§l[§c§l!§8§l] §c§oJailed players an not join tests. They only can start their own.");
					} else {
						player.sendMessage("§8§l[§c§l!§8§l] §b§oTo join a SCP test, use §f§l/test join (SCP)");
					}
					break;
				case "forcejoin":
					if (player.hasPermission("scprp.test")) {
						player.sendMessage(
								"§8§l[§c§l!§8§l] §b§oTo force a Class-D to join a SCP test, use §f§l/test forcejoin (Name) (SCP)");
					} else {
						player.sendMessage("§8§l[§c§l!§8§l] §c§oYou do not have any permission to use this command.");
					}
					break;
				case "listall":
					if (!ongoingTests.isEmpty()) {
						player.sendMessage("§8[§a§o?§8] §a§oList of all currently running SCP tests:");
						for (int i = 0; i < ongoingTests.size(); i++) {
							player.sendMessage("§8 - §f§l" + ongoingTests.get(i));
						}
					} else {
						player.sendMessage("§8[§a§o?§8] §a§oThere are no running SCP tests right now. ");
					}
					break;
				default:
					player.sendMessage("§8§l[§c§l!§8§l] §c§oWrong usage. See §a§l/test§c§o for more information.");
					break;
				}
			} else if (args.length == 2) {
				switch (args[0].toLowerCase()) {
				case "start":
					if (player.hasPermission("scprp.test")) {
						if (args[1] == null || args[1].isEmpty() || args[1].equalsIgnoreCase(" ")) {
							player.sendMessage("§8§l[§c§l!§8§l] §b§oTo begin a SCP test, use §f§l/test start (SCP)");
							player.sendMessage(
									"§8[§c§l!§8] §e§o/tests§b§o for more information about the SCP's and it's documents.");
						} else {
							if (TestingSys.scps.containsKey(args[1])) {
								if (player.hasPermission(TestingSys.getSCPPerms().get(args[1]))) {
									if (ongoingTests.contains(args[1])) {
										player.sendMessage("§8§l[§c§l!§8§l] §c§oThis SCP is already being tested!");
									} else {
										if (tempList.contains(player.getName())) {
											player.sendMessage(
													"§8[§c§l!§8] §c§oYou can lead only one (1) test at time.");
										} else {
											if (TestingSys.getTestCooldowns().containsKey(player.getName())) {
												if (TestingSys.getTestCooldowns().get(player.getName()) > System
														.currentTimeMillis()) {
													player.sendMessage(
															"§8[§c§l!§8] §8§oYou can start your next SCP test in: §c§l"
																	+ API.formatTime(TestingSys.getTestCooldowns()
																			.get(player.getName())
																			- System.currentTimeMillis()));
												} else {
													TestingSys.getTestCooldowns().remove(player.getName());
													player.sendMessage(
															"§8[§c§l!§8] §8§oTry using the command once more, please.");
												}
											} else {
												switch (args[1].toString()) {
												case "1057":
													tempList.add(player.getName());
													TestingSys.testMembers.put(args[1], new ArrayList<String>());
													TestingSys.testMembers.get(args[1]).add(player.getName());
													TestingSys.begunTests.put(args[1], false);
													API.sendRequirementMessage(player, args[1]);
													break;
												case "912":
													tempList.add(player.getName());
													TestingSys.testMembers.put(args[1], new ArrayList<String>());
													TestingSys.testMembers.get(args[1]).add(player.getName());
													TestingSys.begunTests.put(args[1], false);
													API.sendRequirementMessage(player, args[1]);
													break;
												case "553":
													tempList.add(player.getName());
													TestingSys.testMembers.put(args[1], new ArrayList<String>());
													TestingSys.testMembers.get(args[1]).add(player.getName());
													TestingSys.begunTests.put(args[1], false);
													API.sendRequirementMessage(player, args[1]);
													break;
												case "594":
													tempList.add(player.getName());
													TestingSys.testMembers.put(args[1], new ArrayList<String>());
													TestingSys.testMembers.get(args[1]).add(player.getName());
													TestingSys.begunTests.put(args[1], false);
													API.sendRequirementMessage(player, args[1]);
													break;
												case "160":
													tempList.add(player.getName());
													TestingSys.testMembers.put(args[1], new ArrayList<String>());
													TestingSys.testMembers.get(args[1]).add(player.getName());
													TestingSys.begunTests.put(args[1], false);
													API.sendRequirementMessage(player, args[1]);
													break;
												case "529":
													tempList.add(player.getName());
													TestingSys.testMembers.put(args[1], new ArrayList<String>());
													TestingSys.testMembers.get(args[1]).add(player.getName());
													TestingSys.begunTests.put(args[1], false);
													if (TestingSys.findTestByPlayer(player.getName()) != null
															|| !TestingSys.findTestByPlayer(player.getName()).isEmpty())
														TestingSys.beginTest(args[1]);
													break;
												case "294":
													tempList.add(player.getName());
													TestingSys.testMembers.put(args[1], new ArrayList<String>());
													TestingSys.testMembers.get(args[1]).add(player.getName());
													TestingSys.begunTests.put(args[1], false);
													API.sendRequirementMessage(player, args[1]);
													break;
												case "109":
													tempList.add(player.getName());
													TestingSys.testMembers.put(args[1], new ArrayList<String>());
													TestingSys.testMembers.get(args[1]).add(player.getName());
													TestingSys.begunTests.put(args[1], false);
													API.sendRequirementMessage(player, args[1]);
													break;
												case "005":
													tempList.add(player.getName());
													TestingSys.testMembers.put(args[1], new ArrayList<String>());
													TestingSys.testMembers.get(args[1]).add(player.getName());
													TestingSys.begunTests.put(args[1], false);
													API.sendRequirementMessage(player, args[1]);
													break;
												case "106":
													tempList.add(player.getName());
													TestingSys.testMembers.put(args[1], new ArrayList<String>());
													TestingSys.testMembers.get(args[1]).add(player.getName());
													TestingSys.begunTests.put(args[1], false);
													API.sendRequirementMessage(player, args[1]);
													break;
												case "096":
													tempList.add(player.getName());
													TestingSys.testMembers.put(args[1], new ArrayList<String>());
													TestingSys.testMembers.get(args[1]).add(player.getName());
													TestingSys.begunTests.put(args[1], false);
													API.sendRequirementMessage(player, args[1]);
													break;
												case "023":
													tempList.add(player.getName());
													TestingSys.testMembers.put(args[1], new ArrayList<String>());
													TestingSys.testMembers.get(args[1]).add(player.getName());
													TestingSys.begunTests.put(args[1], false);
													API.sendRequirementMessage(player, args[1]);
													break;
												case "1048":
													tempList.add(player.getName());
													TestingSys.testMembers.put(args[1], new ArrayList<String>());
													TestingSys.testMembers.get(args[1]).add(player.getName());
													TestingSys.begunTests.put(args[1], false);
													API.sendRequirementMessage(player, args[1]);
													break;
												case "682":
													tempList.add(player.getName());
													TestingSys.testMembers.put(args[1], new ArrayList<String>());
													TestingSys.testMembers.get(args[1]).add(player.getName());
													TestingSys.begunTests.put(args[1], false);
													API.sendRequirementMessage(player, args[1]);
													break;
												case "939":
													tempList.add(player.getName());
													TestingSys.testMembers.put(args[1], new ArrayList<String>());
													TestingSys.testMembers.get(args[1]).add(player.getName());
													TestingSys.begunTests.put(args[1], false);
													API.sendRequirementMessage(player, args[1]);
													break;
												case "1000":
													tempList.add(player.getName());
													TestingSys.testMembers.put(args[1], new ArrayList<String>());
													TestingSys.testMembers.get(args[1]).add(player.getName());
													TestingSys.begunTests.put(args[1], false);
													API.sendRequirementMessage(player, args[1]);
													break;
												case "049":
													tempList.add(player.getName());
													TestingSys.testMembers.put(args[1], new ArrayList<String>());
													TestingSys.testMembers.get(args[1]).add(player.getName());
													TestingSys.begunTests.put(args[1], false);
													API.sendRequirementMessage(player, args[1]);
													break;
												case "173":
													tempList.add(player.getName());
													TestingSys.testMembers.put(args[1], new ArrayList<String>());
													TestingSys.testMembers.get(args[1]).add(player.getName());
													TestingSys.begunTests.put(args[1], false);
													API.sendRequirementMessage(player, args[1]);
													break;
												default:
													player.sendMessage(
															"§8§l[§c§lDb§8§l] §c§oThe stated SCP does not exist.");
													break;
												}
												TestingSys.createTest(args[1]);
												ongoingTests.add(args[1]);
												player.sendMessage(
														"§8[§c§l!§8] You §4§lmust§8 follow the instructions stated in the tested SCP's testing procedure document. You can add some steps out of your head, though.");
												player.sendMessage(
														"§8[§c§l!§8] §e§o/tests§8 for more information about the SCP's and it's documents.");

												API.sendConsoleCommand("discordsrv broadcast #819586816444727316 "
														+ player.getName() + " - test start - SCP-" + args[1]);
											}
										}
									}
								} else {
									player.sendMessage(
											"§8§l[§c§l!§8§l] §c§oYour level does not meet the requirement for this SCP Test.");
								}
							} else {
								player.sendMessage("§8§l[§c§lDb§8§l] §c§oThe stated SCP does not exist!");
							}
						}
					} else {
						player.sendMessage("§8§l[§c§l!§8§l] §c§oYou do not have any permission to use this command.");
					}
					break;
				/*
				 * case "end": if (player.hasPermission("scprp.test")) { if (args[1] == null ||
				 * args[1].isEmpty() || args[1].equalsIgnoreCase(" ")) { player.sendMessage(
				 * "§8§l[§c§l!§8§l] §b§oTo end the test (when it's completed - §a§lsuccessfully§b§o), use §f§l/test end (SCP)"
				 * ); } else { if (ongoingTests.contains(args[1])) {
				 * API.sendMessageToEveryone(player, "§8[§a§l!§8] §a§oTesting of §b§lSCP-" +
				 * args[1] + "§a§o has been completed. Researcher: §2§l" + player.getName());
				 * player.sendMessage(
				 * "§8[§c§l!§8] §e§oNow, make a report on Discord §f§l(#scp-test-results)§e§o."
				 * ); tempList.remove(player.getName()); TestingSys.endTest(args[1]);
				 * API.sendConsoleCommand("discordsrv broadcast #819586816444727316 " +
				 * player.getName() + " - test end - SCP-" + args[1]); } else {
				 * player.sendMessage(
				 * "§8§l[§c§lDb§8§l] §c§oThe stated SCP does not exist, or is not being tested!"
				 * ); } } } else { player.
				 * sendMessage("§8§l[§c§l!§8§l] §c§oYou do not have any permission to use this command."
				 * ); } break;
				 */
				case "kick":
					if (!player.hasPermission("scprp.test")) {
						player.sendMessage("§8§l[§c§l!§8§l] §c§oYou do not have any permission to use this command.");
					} else {
						player.sendMessage(
								"§8§l[§c§l!§8§l] §b§oTo kick a player out of your test, use §f§l/test kick (Player) (Reason)");
					}
					break;
				case "list":
					if (args[1] == null || args[1].isEmpty() || args[1].equalsIgnoreCase(" ")) {

						player.sendMessage(
								"§8§l[§c§l!§8§l] §b§oTo view members of a SCP testing, use §f§l/test list (SCP)");
					} else {
						if (args[1] == null || args[1].isEmpty() || args[1].equalsIgnoreCase(" ")) {
							player.sendMessage(
									"§8§l[§c§l!§8§l] §b§oTo view members of a SCP testing, use §f§l/test list (SCP)");
						} else {
							if (TestingSys.begunTests.containsKey(args[1])) {
								player.sendMessage(
										"§8§l[§a§lDb§8§l] §a§oSuccessfully retrieved the list for the testing of SCP-"
												+ args[1] + ":");
								player.sendMessage(
										"§8§l - §a§oResearcher: §2§l " + TestingSys.testMembers.get(args[1]).get(0));
								player.sendMessage("§8§l - §6§oClass-D(s): §7§l " + TestingSys.getClassDs(args[1]));
								player.sendMessage("§8§l - §7§oSecurity Officer(s): §f§l "
										+ TestingSys.getSecurityOfficers(args[1]));
								player.sendMessage("§8§l - §9§oMTF(s): §3§l " + TestingSys.getMTFs(args[1]));
							} else {
								player.sendMessage(
										"§8§l[§c§lDb§8§l] §c§oThe stated SCP does not exist, or it's test has not begun yet!");
							}
						}
					}
					break;
				case "join":
					if (player.hasPermission("scprp.test")) {
						player.sendMessage(
								"§8§l[§c§l!§8§l] §c§oResearchers can not join tests. They only can start their own.");
					} else if (CellSystem.getJailedPlayers().contains(player.getName())) {
						player.sendMessage(
								"§8§l[§c§l!§8§l] §c§oJailed players an not join tests. They only can start their own.");
					} else {
						if (args[1] == null || args[1].isEmpty() || args[1].equalsIgnoreCase(" ")) {
							player.sendMessage("§8§l[§c§l!§8§l] §b§oTo join a SCP test, use §f§l/test join (SCP)");
						} else {
							if (ongoingTests.contains(args[1])) {
								TestingSys.addMember(args[1], player.getName());
							} else {
								player.sendMessage(
										"§8§l[§c§lDb§8§l] §c§oThe stated SCP does not exist, or is not being tested!");
							}
						}
					}
					break;
				case "forcejoin":
					if (player.hasPermission("scprp.test")) {
						player.sendMessage(
								"§8§l[§c§l!§8§l] §b§oTo force a Class-D to join a SCP test, use §f§l/test forcejoin (Name) (SCP)");
					} else {
						player.sendMessage("§8§l[§c§l!§8§l] §c§oYou do not have any permission to use this command.");
					}
					break;
				case "forcebegin":
					if (player.hasPermission("scp.admin")) {
						if (args[1] != null) {
							if (ongoingTests.contains(args[1])) {
								TestingSys.beginTest(args[1]);
								API.sendConsoleCommand("discordsrv broadcast #819586816444727316 Admin: "
										+ player.getName() + " test forcebegin - SCP-" + args[1]);
								API.sendConsoleCommand("discordsrv broadcast #831086704218996776 Testing of SCP-"
										+ args[1] + " was force begun by " + player.getName());
							} else {
								player.sendMessage(
										"§8§l[§c§l!§8§l] §c§oThe test must be started, before you can change its state.");
							}
						} else {
							player.sendMessage("§8§l[§c§l!§8§l] §c§oWrong usage.");
						}
					} else {
						player.sendMessage("§8§l[§c§l!§8§l] §c§oYou do not have any permission to use this command.");
					}
					break;
				case "removecd":
					if (player.hasPermission("scp.admin")) {
						if (args[1] != null) {
							if (TestingSys.getTestCooldowns().containsKey(args[1])) {
								API.sendConsoleCommand("discordsrv broadcast #831086704218996776 Admin: "
										+ player.getName() + " - remove cooldown -" + args[1]);
								TestingSys.getTestCooldowns().remove(args[1]);
								player.sendMessage(
										"§8§l[§a§l!§8§l] §a§oRemoved §b§o" + args[1] + "§a§o's tests cooldown.");
								Bukkit.getPlayerExact(args[1])
										.sendMessage("§8§l[§a§l!§8§l] §a§oAdmin §c§o" + player.getName()
												+ " §a§oremoved your tests cooldown. You can start a test again.");
							} else {
								player.sendMessage(
										"§8§l[§c§l!§8§l] §b§o" + args[1] + " §a§odoes not have a tests cooldown.");
							}
						} else {
							player.sendMessage("§8§l[§c§l!§8§l] §c§oWrong usage.");
						}
					} else {
						player.sendMessage("§8§l[§c§l!§8§l] §c§oYou do not have any permission to use this command.");
					}
					break;
				case "removecooldown":
					if (player.hasPermission("scp.admin")) {
						if (args[1] != null) {
							if (TestingSys.getTestCooldowns().containsKey(args[1])) {
								TestingSys.getTestCooldowns().remove(args[1]);
								API.sendConsoleCommand("discordsrv broadcast #819586816444727316 Admin: "
										+ player.getName() + " test removecooldown -" + args[1]);
							} else {
								player.sendMessage(
										"§8§l[§c§l!§8§l] §c§oThe test must be started, before you can change its state.");
							}
						} else {
							player.sendMessage("§8§l[§c§l!§8§l] §c§oWrong usage.");

						}
					} else {
						player.sendMessage("§8§l[§c§l!§8§l] §c§oYou do not have any permission to use this command.");
					}
					break;
				default:
					player.sendMessage("§8§l[§c§l!§8§l] §c§oWrong usage. See §a§l/test§c§o for more information.");
					break;
				}
			} else if (args.length >= 3) {
				if (args[0].equalsIgnoreCase("kick")) {
					if (args[1] == null || args[1].isEmpty() || args[1].equalsIgnoreCase(" ")) {
						player.sendMessage(
								"§8§l[§c§l!§8§l] §b§oTo kick a player out of your test, use §f§l/test kick (Player) (Reason)");
					} else {
						if (!(args[2] == null || args[2].isEmpty() || args[2].equalsIgnoreCase(" "))) {
							if (Bukkit.getPlayerExact(args[1]).isOnline()) {
								String reason = "";
								Player t = Bukkit.getPlayerExact(args[1]);
								for (int i = 2; i < args.length; i++) {
									reason = reason + args[i] + " ";
								}

								t.sendMessage(
										"§8[§c§l!§8] §c§oYou have been kicked from the testing. Reason: §4§l" + reason);
								player.sendMessage("§8[§a§l!§8] §a§oKicked §c§l" + args[1]
										+ "§a§oout of the test. Reason: §e§o" + reason);

								TestingSys.kickFromTest(player.getName(), args[1], reason);
							} else {
								player.sendMessage("§8§l[§9§l?§8§l] §c§oThis player is not online.");
							}
						} else {
							player.sendMessage(
									"§8§l[§c§l!§8§l] §b§oTo kick a player out of your test, use §f§l/test kick (Player) (Reason)");
						}
					}
				} else if (args[0].equalsIgnoreCase("forcejoin")) {
					if (args[1] == null || args[1].isEmpty() || args[1].equalsIgnoreCase(" ")) {
						player.sendMessage(
								"§8§l[§c§l!§8§l] §b§oTo force a Class-D to join a SCP test, use §f§l/test forcejoin (Name) (SCP)");
					} else {
						if (!(args[2] == null || args[2].isEmpty() || args[2].equalsIgnoreCase(" "))) {
							if (Bukkit.getServer().getPlayerExact(args[1]) != null) {
								if (api.getUserManager().getUser(args[1]).getPrimaryGroup().equals("class-d")) {
									if (ongoingTests.contains(args[2])) {
										TestingSys.addMember(args[1], args[2]);
										player.sendMessage("§8§l[§a§l!§8§l] §a§oForced §c§o" + args[1]
												+ " §a§oto join the testing of SCP-" + args[2]);
									} else {
										player.sendMessage(
												"§8§l[§c§lDb§8§l] §c§oThe stated SCP does not exist, or is not being tested!");
									}
								} else {
									player.sendMessage(
											"§8§l[§c§l!§8§l] §c§oYou can only force Class-D personnel to join!");
								}
							} else {
								player.sendMessage(
										"§8§l[§c§l!§8§l] §c§oThe stated Class-D does not exist, or is not online!");
							}
						} else {
							player.sendMessage(
									"§8§l[§c§l!§8§l] §b§oTo force a Class-D to join a SCP test, use §f§l/test forcejoin (Name) (SCP)");
						}
					}
				}
			}
		}

		if (cmd.getName().equalsIgnoreCase("scp")) {
			if (player != null) {
				SCPMenu.openGUI(player, api.getUserManager().getUser(player.getName()));
			}
		}

		if (cmd.getName().equalsIgnoreCase("findclassd")) {
			String role = LuckPermsProvider.get().getUserManager().getUser(player.getName()).getPrimaryGroup();
			if (role.equalsIgnoreCase("security-officer") || role.equalsIgnoreCase("site-director")
					|| role.equalsIgnoreCase("mtf-nu-7") || role.equalsIgnoreCase("mtf-nu-7-commander")
					|| role.equalsIgnoreCase("head-security-officer") || player.hasPermission("scprp.o5")
					|| player.hasPermission("scp.admin")) {
				if (args.length == 0) {
					player.sendMessage(
							"§8§l[§9§l?§8§l] §6§oUse the §e§l/findclassd (Player/Designation)§6§l command, to find a Class-D's designation, or a Class-D using their designation.");
				} else if (args.length == 1) {
					if(args[0] != null) {
						if(ClassDDesignations.getDesignations().containsKey(args[0]) || ClassDDesignations.getDesignations().containsValue(args[0]) ) {
							player.sendMessage("§8§l[§5§lDb§8§l] §6§oSuccessfully retrieved information:");
							for(Entry<String, Integer> entry : ClassDDesignations.getDesignations().entrySet()) {
								if(entry.getKey().equals(args[0])) player.sendMessage("§8§o - §6§oClass-D Designation for the name §e§l" + args[0] + "§6§o: §f§l" + entry.getValue());
								if(entry.getValue().equals(API.parse(args[0]))) player.sendMessage("§8§o - §6§oClass-D name for the designation §e§l" + args[0] + "§6§o: §f§l" + entry.getKey());
							}
						} else {
							player.sendMessage("§8§l[§5§lDb§8§l] §c§oNo existing entries for: §6§l" + args[0] + "§c§o.");
						}
					} else {
						player.sendMessage(
								"§8§l[§9§l?§8§l] §6§oUse the §e§l/findclassd (Player/Designation)§6§l command, to find a Class-D's designation, or a Class-D using their designation.");
					}
				}
			} else {
				player.sendMessage("§8§l[§c§l!§8§l] §c§oYou do not have any permission to use this command.");
			}
		}

		if (cmd.getName().equalsIgnoreCase("dcbreach")) {
			API.sendConsoleCommand("discordsrv broadcast #819586816444727316 " + player.getName() + " - dcbreach");
		}

		if (cmd.getName().equalsIgnoreCase("constructions")) {
			User user = api.getUserManager().getUser(player.getName());
			String role = user.getPrimaryGroup().toString();
			if (role.equalsIgnoreCase("technic") || role.equalsIgnoreCase("site-director")
					|| role.equalsIgnoreCase("storage-leader") || player.hasPermission("scp.admin")) {
				if (args.length == 0)
					ConstructionsMenu.openGUI(player, role);
				if (args.length == 1) {
					if (args[0].equalsIgnoreCase("write")) {
						String message = "empty";
						for (Entry<String, List<List<String>>> entry : ConstructionsMenu.getConstructions()
								.entrySet()) {
							String creator = entry.getKey();
							message = creator + ", ";
							player.sendMessage(
									Integer.toString(ConstructionsMenu.getConstructions().get(creator).size()));
							for (int i = 0; i < ConstructionsMenu.getConstructions().get(creator).size(); i++) {
								for (String str : ConstructionsMenu.getConstructions().get(creator).get(i)) {
									message = message + str + ", ";
								}
							}
						}
						player.sendMessage(message);
					}
				}
			} else {
				player.sendMessage("§8§l[§c§l!§8§l] §c§oYou do not have any permission to use this command.");
			}
		}

		if (cmd.getName().equalsIgnoreCase("dcendbreach")) {
			if (player.hasPermission("scprp.secoff")) {
				API.sendConsoleCommand(
						"discordsrv broadcast #819586816444727316 " + player.getName() + " - dcendbreach");
			} else {
				player.sendMessage("§8§l[§c§l!§8§l] §c§oYou do not have the permission to end a breach.");
			}
		}

		if (cmd.getName().equalsIgnoreCase("hire")) {
			if (player.hasPermission("scprp.sitedir") || player.hasPermission("scprp.headsecoff")
					|| player.hasPermission("scprp.headres") || player.hasPermission("scprp.headmedic")
					|| player.hasPermission("scprp.mtfcmd")) {
				if (args.length == 0) {
					player.sendMessage(
							"§8§l[§9§l?§8§l] §2§oUse §e§l/hire (Player) §2§oto hire a player when he/she has been tested/trained.");
				} else if (args.length == 1) {
					if (args[0] == null || args[0].isEmpty()) {
						player.sendMessage("§8§l[§c§l!§8§l] §c§oWrong usage.");
					} else {
						if (Bukkit.getPlayerExact(args[0]) == null || !Bukkit.getPlayerExact(args[0]).isOnline()) {
							player.sendMessage("§8§l[§c§l!§8§l] §c§oThe stated player must be online!");
						} else {
							Player target = Bukkit.getPlayerExact(args[0]);
							User user = api.getUserManager().getUser(args[0]);
							if (LevellingSys.roleNeedsHire(user)) {
								if (LevellingSys.convertXpToLevel(user.getPrimaryGroup().toString(),
										LevellingSys.getXP(args[0])) >= 2) {
									player.sendMessage("§8§l[§c§l!§8§l] §c§oThe stated player is already hired!");
								} else {
									LevellingSys.setLevel(args[0],
											api.getUserManager().getUser(args[0]).getPrimaryGroup().toString(), 2);
									player.sendMessage(
											"§8§l[§a§l!§8§l] §a§oSuccessfully hired §f§l" + args[0] + "§a§o.");
									target.sendMessage("§8§l[§a§l!§8§l] §a§oYou have been hired by §f§l"
											+ player.getName() + "§a§o.");
									API.sendConsoleCommand(
											"discordsrv broadcast #819586816444727316 " + player.getName() + " - hire: "
													+ args[0] + "- target role: " + user.getPrimaryGroup().toString());
								}

							} else {
								player.sendMessage(
										"§8§l[§c§l!§8§l] §c§oThe stated player's role does not require hiring!");
							}
						}
					}
				} else {
					player.sendMessage("§8§l[§c§l!§8§l] §c§oWrong usage.");
				}
			} else {
				player.sendMessage("§8§l[§c§l!§8§l] §c§oYou do not have any permission to use this command.");
			}
		}

		if (cmd.getName().equalsIgnoreCase("designation")) {
			if (player.hasPermission("scprp.designation")) {
				if (args.length == 0) {
					player.sendMessage(
							"§8§l[§9§l?§8§l] §6§oTo remove a player's Class-D Designation, use §e§l/designation remove (Name)");
					player.sendMessage(
							"§8§l[§9§l?§8§l] §6§oTo get a player back to his/her designation, use §e§l/designation rollback (Name)");
				} else if (args.length == 1) {
					switch (args[0].toLowerCase()) {
					case "remove":
						player.sendMessage(
								"§8§l[§c§l!§8§l] §6§oTo remove a player's Class-D Designation, use §e§l/designation remove (Name)");
						break;
					case "rollback":
						player.sendMessage(
								"§8§l[§c§l!§8§l] §6§oTo get a player back to his/her designation, use §e§l/designation rollback (Name)");
						break;
					case "writeout":
						ClassDDesignations.loadFile();
						if (!ClassDDesignations.getDesignations().isEmpty()) {
							for (Entry<String, Integer> entry : ClassDDesignations.getDesignations().entrySet()) {
								System.out.println(entry.getKey() + ":" + entry.getValue());
							}
						} else {
							System.out.println("empty:empty");
						}

						break;
					default:
						player.sendMessage("§8§l[§c§l!§8§l] §c§oWrong usage.");
						break;
					}
				} else if (args.length == 2) {
					if (args[1] == null || args[1].isEmpty()) {
						player.sendMessage("§8§l[§c§l!§8§l] §c§oYou must state a player!");
					} else {
						if (Bukkit.getServer().getPlayerExact(args[1]) == null
								|| !Bukkit.getPlayerExact(args[1]).isOnline()) {
							player.sendMessage(
									"§8§l[§c§l!§8§l] §c§oThe stated Player does not exist, or is not online!");
						} else {
							User user = api.getUserManager().getUser(args[1]);
							switch (args[0].toLowerCase()) {
							case "remove":
								if (user.getPrimaryGroup().equalsIgnoreCase("default")
										|| user.getPrimaryGroup().equalsIgnoreCase("class-d")) {
									API.removeDesignation(player, args[1]);
								} else {
									player.sendMessage("§8§l[§c§l!§8§l] §c§oThis player is not a §6§oClass-D§c§o!");
								}
								break;
							case "rollback":
								if (user.getPrimaryGroup().equalsIgnoreCase("default")
										|| user.getPrimaryGroup().equalsIgnoreCase("class-d")) {
									ClassDDesignations.rollbackDesignation(player, args[1]);
								} else {
									player.sendMessage("§8§l[§c§l!§8§l] §c§oThis player is not a §6§oClass-D§c§o!");
								}
								break;
							default:
								player.sendMessage("§8§l[§c§l!§8§l] §c§oWrong usage.");
								break;
							}
						}
					}
				} else {
					player.sendMessage("§8§l[§c§l!§8§l] §c§oWrong usage.");
				}
			} else {
				player.sendMessage("§8§l[§c§l!§8§l] §c§oYou do not have the permission to use this command.");
			}
		}

		if (cmd.getName().equalsIgnoreCase("clear")) {
			ClearInventory.openGUI(player);
		}

		if (cmd.getName().equalsIgnoreCase("naufix")) {
			if (player.hasPermission("scp.admin")) {
				if (args.length == 0) {
					player.sendMessage("§f§l-------------------------------");
					player.sendMessage("§b§oAdd a Player as a §9§oMod§b§o: §e§l/naufix setmod (Player)");
					player.sendMessage("§b§oAdd a Player as an §c§oAdmin§b§o: §e§l/naufix setadmin (Player)");
					player.sendMessage("§b§oRemove a Player from Naufix Team: §e§l/naufix remove (Player)");
				} else if (args.length == 1) {
					switch (args[0].toLowerCase()) {
					case "setmod":
						player.sendMessage("§b§oAdd a Player as a §9§oMod§b§o: §e§l/naufix setmod (Player)");
						break;
					case "setadmin":
						player.sendMessage("§b§oAdd a Player as an §c§oAdmin§b§o: §e§l/naufix setadmin (Player)");
						break;
					case "remove":
						player.sendMessage("§b§oRemove a Player from Naufix Team: §e§l/naufix remove (Player)");
						break;
					case "list":
						API.listNaufixMembers(player);
						break;
					default:
						player.sendMessage("§8[§c§l!§8] §c§oWrong usage.");
						break;
					}
				} else if (args.length == 2) {
					switch (args[0].toLowerCase()) {
					case "setmod":
						if (Bukkit.getPlayerExact(args[1]) != null && Bukkit.getPlayerExact(args[1]).isOnline()) {
							API.setNaufixRole(args[1], "mod", player);
						} else {
							player.sendMessage("§8[§c§l!§8] §c§oPlayer §e§o" + args[1] + "§c§o is not online.");
						}
						break;
					case "setadmin":
						if (Bukkit.getPlayerExact(args[1]) != null && Bukkit.getPlayerExact(args[1]).isOnline()) {
							API.setNaufixRole(args[1], "adm", player);
						} else {
							player.sendMessage("§8[§c§l!§8] §c§oPlayer §e§o" + args[1] + "§c§o is not online.");
						}
						break;
					case "remove":
						if (Bukkit.getPlayerExact(args[1]) != null && Bukkit.getPlayerExact(args[1]).isOnline()) {
							API.removeNaufixRole(args[1], player);
						} else {
							player.sendMessage("§8[§c§l!§8] §c§oPlayer §e§o" + args[1] + "§c§o is not online.");
						}
						break;
					default:
						player.sendMessage("§8[§c§l!§8] §c§oWrong usage.");
						break;
					}
				}
			}
		}

		if (cmd.getName().equalsIgnoreCase("food")) {
			if (!(player.hasPermission("scprp.food") || player.hasPermission("scprp.mod")
					|| player.hasPermission("scprp.admin"))) {
				player.sendMessage("§8§l[§a§l!§8§l] §e§oOpening the §6§lFood Selector §e§omenu.");
				FoodGUI.openGUI(player, LuckPermsProvider.get().getUserManager().getUser(player.getName()));
			} else {
				if (args.length == 0) {
					player.sendMessage("§6§lFood Machine:");
					player.sendMessage("§8§l-------------");
					player.sendMessage("§8§l- §e§oOpen the Food Selector§f§l         - §e§l/food open");
					player.sendMessage("§8§l- §e§oAdd a new Food Machine§f§l         - §e§l/food add");
					player.sendMessage("§8§l- §e§oExit Creator / Removal Mode§f§l    - §e§l/food cancel");
					player.sendMessage("§8§l- §e§oRemove a Food Machine§f§l         - §e§l/food remove");
				}
				if (args.length == 1) {
					if (args[0].equalsIgnoreCase("open")) {
						player.sendMessage("§8§l[§a§l!§8§l] §e§oOpening the §6§lFood Selector §e§omenu.");
						FoodGUI.openGUI(player, LuckPermsProvider.get().getUserManager().getUser(player.getName()));
					}

					if (args[0].equalsIgnoreCase("add")) {
						if (FoodAPI.isInCreatorMode(player.getName())) {
							player.sendMessage("§8§l[§c§l!§8§l] §c§oYou already are in the Creator mode!");
							player.sendMessage(
									"§8§l[§9§l?§8§l] §3§oIf you wish to cancel the process, use §e§l/food cancel§3§o.");
						} else {
							if (FoodAPI.isInRemoverMode(player.getName())) {
								FoodAPI.disableRemover(player.getName());
								player.sendMessage("§8§l[§a§l!§8§l] §c§oDisabling Remover Mode...");
							}
							FoodAPI.addCreatorMode(player.getName());
							player.sendMessage(
									"§8§l[§9§l?§8§l] §7§oTo add a new §6§lFood Machine§7§o, right click it's Wooden or Stone button.");
							player.sendMessage(
									"§8§l[§9§l?§8§l] §3§oIf you wish to cancel the process, use §e§l/food cancel§3§o.");
						}
					}

					if (args[0].equalsIgnoreCase("cancel")) {
						if (FoodAPI.isInRemoverMode(player.getName())) {
							FoodAPI.disableRemover(player.getName());
							player.sendMessage("§8§l[§a§l!§8§l] §a§oRemover Mode disabled.");
						} else if (FoodAPI.isInCreatorMode(player.getName())) {
							FoodAPI.disableCreator(player.getName());
							player.sendMessage("§8§l[§a§l!§8§l] §a§oCreator Mode disabled.");
						} else {
							player.sendMessage("§8§l[§a§l!§8§l] §c§oNothing left to cancel.");
						}
					}

					if (args[0].equalsIgnoreCase("remove")) {
						if (FoodAPI.isInRemoverMode(player.getName())) {
							player.sendMessage("§8§l[§c§l!§8§l] §c§oYou already are in the Removal mode!");
							player.sendMessage(
									"§8§l[§9§l?§8§l] §3§oIf you wish to cancel the process, use §e§l/food cancel§3§o.");
						} else {
							if (FoodAPI.isInCreatorMode(player.getName())) {
								FoodAPI.disableCreator(player.getName());
								player.sendMessage("§8§l[§a§l!§8§l] §c§oDisabling Creator Mode...");
							}
							FoodAPI.addRemoverMode(player.getName());
							player.sendMessage(
									"§8§l[§9§l?§8§l] §7§oTo remove a §6§lFood Machine§7§o, right click it's button.");
							player.sendMessage(
									"§8§l[§9§l?§8§l] §3§oIf you wish to cancel the process, use §e§l/food cancel§3§o.");
						}
					}
				}
			}
		}	

		if (cmd.getName().equalsIgnoreCase("id")) {
			double radius = 3D;
			if (args.length == 0) {
				if (player.hasPermission("scprp.id")) {
					player.sendMessage("§8[§9§l?§8] §7§oTo view somebody's Identification Card, use §b§o/id (Player)");
				} else {
					player.sendMessage("§8[§a§l!§8] Viewing your Identification Card.");
					IDGUI.openGUI(player, player.getName());
				}
			} else if (args.length == 1) {
				if (player.hasPermission("scprp.id")) {
					if (args[0] == null || args[0].isEmpty()) {
						player.sendMessage(
								"§8[§9§l?§8] §7§oTo view somebody's Identification Card, use §b§o/id (Player)");
					} else {
						if (Bukkit.getPlayerExact(args[0]) == null || !Bukkit.getPlayerExact(args[0]).isOnline()) {
							player.sendMessage("§8[§c§l!§8] §c§oPlayer §e§l" + args[0] + " §c§ois not online.");
						} else {
							if (player.getLocation().distance(Bukkit.getPlayerExact(args[0]).getLocation()) <= radius) {
								IDGUI.openGUI(player, args[0]);
								player.sendMessage(
										"§8[§a§l!§8] §a§oViewing §f§l" + args[0] + "§a§o's Identification Card.");
							} else {
								player.sendMessage(
										"§8[§c§l!§8] §c§oYou need to be closer than §c§l3§c§o blocks (or less) to §7§l"
												+ args[0] + "§c§o.");
							}
						}
					}
				} else {
					IDGUI.openGUI(player, player.getName());
					player.sendMessage("§8[§a§l!§8] §a§oViewing your Identification Card.");
				}
			} else {
				if (player.hasPermission("scprp.id")) {
					if (args[0] == null || args[0].isEmpty()) {
						player.sendMessage(
								"§8[§9§l?§8] §7§oTo view somebody's Identification Card, use §b§o/id (Player)");
					} else {
						if (Bukkit.getPlayerExact(args[0]) == null || !Bukkit.getPlayerExact(args[0]).isOnline()) {
							player.sendMessage("§8[§c§l!§8] §c§oPlayer §e§l" + args[0] + " §c§ois not online.");
						} else {
							if (player.getLocation().distance(Bukkit.getPlayerExact(args[0]).getLocation()) <= radius) {
								IDGUI.openGUI(player, args[0]);
								player.sendMessage(
										"§8[§a§l!§8] §a§oViewing §f§l" + args[0] + "§a§o's Identification Card.");
							} else {
								player.sendMessage(
										"§8[§c§l!§8] §c§oYou need to be closer than §c§l3§c§o blocks (or less) to §7§l"
												+ args[0] + "§c§o.");
							}
						}
					}
				} else {
					IDGUI.openGUI(player, player.getName());
					player.sendMessage("§8[§a§l!§8] §a§oViewing your Identification Card.");
				}
			}
		}

		if (cmd.getName().equalsIgnoreCase("level")) {
			if (player.hasPermission("scprp.level")) {
				if (args.length == 0) {
					player.sendMessage("§8§l[§9§l?§8§l] §b§oTo view a player's level, use §f§l/level view (Player)");
					player.sendMessage(
							"§8§l[§9§l?§8§l] §b§oTo give XP to a Player, use §f§l/level addxp (Player) (XP)");
					player.sendMessage(
							"§8§l[§9§l?§8§l] §b§oTo set a player's level, use §f§l/level set (Player) (Level)");
				} else if (args.length == 1) {
					switch (args[0].toLowerCase()) {
					case "view":
						if (LevellingSys.roleHasLevel(player.getName())) {
							player.sendMessage("§8§l[§9§l?§8§l] §a§oYou have: §e§l"
									+ LevellingSys.getXP(player.getName()) + " §a§oXP. (Level §3§l"
									+ LevellingSys.convertXpToLevel(
											api.getUserManager().getUser(player.getName()).getPrimaryGroup().toString(),
											LevellingSys.getXP(player.getName()))
									+ "§a§o)");
							player.sendMessage(
									"§8§l[§9§l?§8§l] §b§oTo view a player's level, use §f§l/level view (Player)");
						} else {
							player.sendMessage("§8§l[§c§l!§8§l] §c§oYour role does not level up or get any XP!");
						}
						break;
					case "addxp":
						player.sendMessage(
								"§8§l[§c§l!§8§l] §b§oTo give XP to a Player, use §f§l/level addxp (Player) (XP)");
						break;
					case "set":
						player.sendMessage(
								"§8§l[§c§l!§8§l] §b§oTo set a player's level, use §f§l/level set (Player) (Level)");
						break;
					default:
						player.sendMessage("§8§l[§c§l!§8§l] §c§oWrong usage.");
						break;
					}
				} else if (args.length == 2) {
					switch (args[0].toLowerCase()) {
					case "view":
						if (Bukkit.getPlayerExact(args[1]) != null) {
							if (Bukkit.getPlayerExact(args[1]).isOnline()) {
								if (LevellingSys.roleHasLevel(args[1])) {
									player.sendMessage("§8§l[§9§l?§8§l] §b§o" + args[1] + "§a§o has: §e§l"
											+ LevellingSys.getXP(args[1]) + " §a§oXP. (Level §3§l"
											+ LevellingSys.convertXpToLevel(
													api.getUserManager().getUser(args[1]).getPrimaryGroup().toString(),
													LevellingSys.getXP(args[1]))
											+ "§a§o)");
								} else {
									player.sendMessage(
											"§8§l[§c§l!§8§l] §c§oThis player's role does not level up or get any XP.");
								}
							} else {
								player.sendMessage("§8§l[§c§l!§8§l] §c§oThe stated player is not online!");
							}
						} else {
							player.sendMessage("§8§l[§c§l!§8§l] §c§oThe stated player does not exist!");
						}
						break;
					case "addxp":
						player.sendMessage(
								"§8§l[§c§l!§8§l] §b§oTo give XP to a Player, use §f§l/level addxp (Player) (XP)");
						break;
					case "set":
						player.sendMessage(
								"§8§l[§c§l!§8§l] §b§oTo set a player's level, use §f§l/level set (Player) (Level)");
						break;
					default:
						player.sendMessage("§8§l[§c§l!§8§l] §c§oWrong usage.");
						break;
					}
				} else if (args.length == 3) {
					switch (args[0].toLowerCase()) {
					case "addxp":
						if (Bukkit.getPlayerExact(args[1]) != null) {
							if (Bukkit.getPlayerExact(args[1]).isOnline()) {
								if (LevellingSys.roleHasLevel(args[1])) {
									if (args[2] != null) {
										if (API.isInteger(args[2])) {
											LevellingSys.addXP(args[1], API.parse(args[2]));
											API.sendConsoleCommand("discordsrv broadcast #819586816444727316 "
													+ player.getName() + " - level addxp - Target: " + args[1]
													+ " - XP: " + args[2]);
											player.sendMessage("§8§l[§a§l!§8§l] §a§oGiven§b§l " + args[2]
													+ " §a§oXP to §f§l" + args[1] + "§a§o.");
										} else {
											player.sendMessage("§8§l[§c§l!§8§l] §c§oXP must be a number!");
										}
									} else {
										player.sendMessage(
												"§8§l[§c§l!§8§l] §b§oTo give XP to a Player, use §f§l/level addxp (Player) (XP)");
									}
									player.sendMessage("§8§l[§9§l?§8§l] §b§o" + args[1] + "§a§o has: §e§l"
											+ LevellingSys.getXP(args[1]) + " §a§oXP. (Level §3§l"
											+ LevellingSys.convertXpToLevel(
													api.getUserManager().getUser(args[1]).getPrimaryGroup().toString(),
													LevellingSys.getXP(args[1]))
											+ "§a§o)");
								} else {
									player.sendMessage(
											"§8§l[§c§l!§8§l] §c§oThis player's role does not level up or get any XP.");
								}
							} else {
								player.sendMessage("§8§l[§c§l!§8§l] §c§oThe stated player is not online!");
							}
						} else {
							player.sendMessage("§8§l[§c§l!§8§l] §c§oThe stated player does not exist!");
						}
						break;
					case "set":
						if (player.hasPermission("scprp.admin")) {
							if (Bukkit.getPlayerExact(args[1]) != null) {
								if (Bukkit.getPlayerExact(args[1]).isOnline()) {
									if (LevellingSys.roleHasLevel(args[1])) {
										if (args[2] != null) {
											if (API.isInteger(args[2])) {
												if (API.parse(args[2]) >= 1 && API.parse(args[2]) <= 4) {
													LevellingSys.setLevel(args[1], api.getUserManager().getUser(args[1])
															.getPrimaryGroup().toString(), API.parse(args[2]));
													API.sendConsoleCommand("discordsrv broadcast #819586816444727316 "
															+ player.getName() + " - level set - Target: " + args[1]
															+ " - Level: " + args[2]);
													player.sendMessage("§8§l[§a§l!§8§l] §a§oSet§b§l " + args[1]
															+ "'s §a§oLevel to §9§l" + args[2] + "§a§o.");
													Bukkit.getPlayerExact(args[1]).sendMessage(
															"§8§l[§a§l!§8§l] §a§oYour level was set to §f§l" + args[2]
																	+ " §a§oby§c§l " + player.getName() + "§a§o.");
												} else {
													player.sendMessage(
															"§8§l[§c§l!§8§l] §c§oLevel must be a number between §4§l1§c§o and §4§l4§c§o!");
												}
											} else {
												player.sendMessage(
														"§8§l[§c§l!§8§l] §c§oLevel must be a number between §4§l1§c§o and §4§l4§c§o!");
											}
										} else {
											player.sendMessage(
													"§8§l[§c§l!§8§l] §b§oTo set a player's level, use §f§l/level set (Player) (Level)");
										}

									} else {
										player.sendMessage(
												"§8§l[§c§l!§8§l] §c§oThis player's role does not level up or get any XP.");
									}
								} else {
									player.sendMessage("§8§l[§c§l!§8§l] §c§oThe stated player is not online!");
								}
							} else {
								player.sendMessage("§8§l[§c§l!§8§l] §c§oThe stated player does not exist!");
							}
						} else {
							player.sendMessage(
									"§8§l[§c§l!§8§l] §c§oYou do not have the permission to use this command.");
						}
						break;
					default:
						player.sendMessage("§8§l[§c§l!§8§l] §c§oWrong usage.");
						break;
					}
				}
			} else {
				player.sendMessage("§8§l[§c§l!§8§l] §c§oYou do not have the permission to use this command.");
			}
		}

		if(cmd.getName().equalsIgnoreCase("mine")) {
			if (player.hasPermission("scprp.admin") || player.hasPermission("scp.admin")
					|| player.hasPermission("scprp.mod") || player.hasPermission("scp.mod")) {
				if (args.length == 0) {
					player.sendMessage(
							"§8§l[§9§l?§8§l] §b§oTo start creating a mine, use §f§l/mine create");
					player.sendMessage(
							"§7§o- then, right click Red Wool blocks, which represent the future ore locations.");
					player.sendMessage(
							"§8§l[§9§l?§8§l] §b§oTo expand a mine (add new ores), use §f§l/mine expand (name)");
					player.sendMessage(
							"§8§l[§9§l?§8§l] §b§oTo remove a mine, use §f§l/mine removemine (name)");
					player.sendMessage(
							"§8§l[§9§l?§8§l] §b§oTo remove an ore from a mine, use §f§l/mine remove (name)");
					player.sendMessage(
							"§7§o- then, right click ores, which you want to remove.");
					player.sendMessage(
							"§8§l[§9§l?§8§l] §b§oTo find the name of a mine, use §f§l/mine identify");
					player.sendMessage("§7§o- then, click the ores. Use the command once again to quit the tool.");
					player.sendMessage(
							"§8§l[§9§l?§8§l] §b§oTo start a raid, use §f§l/mine raid");
					player.sendMessage("§7§o- will allow non-Class-D personnel to enter the mine. Use again to disable.");
					player.sendMessage(
							"§8§l[§9§l?§8§l] §b§oTo reset all ores to Tier 1 or 2, use §f§l/mine reset");
				} else if (args.length == 1) {
					switch (args[0].toLowerCase()) {
					case "load":
						Mine.load();
					case "debuglist":
						API.sendDebugMsg("Size: " + Mine.getMines().size()); 
						for (Entry<String, List<Location>> entry : Mine.getMines().entrySet()) {
							API.sendDebugMsg("Key: " + entry.getKey() + " , list size: " + entry.getKey().length());
							for (int i = 0; i<entry.getValue().size();i++) {
								player.sendMessage("Index: " + i);
								player.sendMessage("key: " + entry.getKey() + " : " + entry.getValue().get(i));
							}
						}
						break;
					case "debuglist2":
						Mine.getSelected().size();
						API.sendDebugMsg("Size: " + Mine.getSelected().size()); 
						for (Location loc : Mine.getSelected()) {
							API.sendDebugMsg("" + loc);
						}
						break;
					case "identify":
						if (IdentifyMine.getIdentifyList().containsKey(player.getName())) {
							if (IdentifyMine.getIdentifyList().get(player.getName()).equals(true)) {
								IdentifyMine.disableTool(player.getName());
							} else {
								IdentifyMine.enableTool(player.getName());
							}
						} else {
							IdentifyMine.enableTool(player.getName());
						}
						break;
					case "create":
						if (IdentifyMine.getIdentifyList().containsKey(player.getName())) {
							if (IdentifyMine.getIdentifyList().get(player.getName()).equals(true)) 
								IdentifyMine.disableTool(player.getName());
						}
						
						if (Mine.getRemovingPlayers().indexOf(player.getName()) != -1) Mine.getRemovingPlayers().remove(player.getName());
						
						Mine.getCreatingPlayers().add(player.getName());
						player.sendMessage("§8§l[§a§l!§8§l] §b§oMine creation §a§lenabled§b§o.");
						player.sendMessage("§9§oRight click §4§lRED WOOL§9§o with an empty hand to add new ores. Selected ore locations will turn into §a§lLIME WOOL§9§o. To deselect an ore location, right click the Lime Wool, it will turn back into Red.");
						player.sendMessage("§e§oWhen done, right click a §7§lSTONE§e§o block, to complete the creation and generate ores.");
						break;
					case "expand":
						player.sendMessage(
								"§8§l[§9§l?§8§l] §b§oTo expand a mine (add new ores), use §f§l/mine expand (name)");
						break;
					case "remove":
						if (IdentifyMine.getIdentifyList().containsKey(player.getName())) {
							if (IdentifyMine.getIdentifyList().get(player.getName()).equals(true)) 
								IdentifyMine.disableTool(player.getName());
						}
						
						if (Mine.getCreatingPlayers().indexOf(player.getName()) != -1) 
								Mine.getCreatingPlayers().remove(player.getName());
				
						Mine.getRemovingPlayers().add(player.getName());
						
						player.sendMessage("§8§l[§a§l!§8§l] §b§oOre removal §a§lenabled§b§o.");
						player.sendMessage("§9§oRight click §e§lOres§9§o with an empty hand to remove. Selected ore locations will turn into §4§lRED WOOL§9§o. To deselect an ore location, right click the Red Wool, it will turn back into an ore.");
						player.sendMessage("§e§oWhen done, right click a §7§lSTONE§e§o block, to complete the ore removal.");
					case "removemine":
						player.sendMessage(
								"§8§l[§c§l!§8§l] §c§oTo remove a mine, use §f§l/network removemine (name)");
						break;
					case "raid":
						
						break;
					case "reset":
						Mine.resetOres(player);
						break;
					default:
						player.sendMessage("§8§l[§c§l!§8§l] §c§oWrong usage.");
						break;
					}
				} else if (args.length == 2) {
					switch (args[0].toLowerCase()) {
					case "removemine":
						if (args[1] != null && !args[1].isEmpty()) {
							if (Mine.getMines().containsKey(args[1])) {
								Mine.removeMine(args[1]);
								player.sendMessage("§8§l[§a§l!§8§l] §a§oMine §c§l" + args[1]
										+ " §a§oremoved §2§lsuccessfully§a§o.");
							} else {
								player.sendMessage("§8§l[§c§l!§8§l] §c§oA mine named §e§l" + args[1]
										+ " §c§odoes not exist! See §e§o/mine§c§o for more information.");
							}
						} else {
							player.sendMessage(
									"§8§l[§c§l!§8§l] §c§oYou need to specify the name of the mine. See §e§o/mine§c§o for more information.");
						}
						break;
					case "expand":
						if (args[1] != null && !args[1].isEmpty()) {
							if (Mine.getMines().containsKey(args[1])) {
								if (IdentifyWNetwork.getIdentifyList().containsKey(player.getName()))
									IdentifyWNetwork.disableTool(player.getName());
									WirelessNetworks.expand(player.getName(), args[1]);
							} else {
								player.sendMessage("§8§l[§c§l!§8§l] §c§oA network named §e§l" + args[1]
										+ " §c§odoes not exist! See §e§o/network§c§o for more information.");
							}
						} else {
							player.sendMessage(
									"§8§l[§c§l!§8§l] §c§oYou need to specify the name of the mine. See §e§o/mine§c§o for more information.");
						}
						break;
					default:
						player.sendMessage("§8§l[§c§l!§8§l] §c§oWrong usage.");
						break;
					}
				}
			} else {
				player.sendMessage("§8§l[§c§l!§8§l] §c§oYou do not have the permission to use this command.");
			}
		}
		
		if(cmd.getName().equalsIgnoreCase("areadetector")) {
			if (player.hasPermission("scprp.admin") || player.hasPermission("scp.admin")
					|| player.hasPermission("scprp.mod") || player.hasPermission("scp.mod")) {
				if (args.length == 0) {
					player.sendMessage(
							"§8§l[§9§l?§8§l] §b§oTo add a new Area Detector, use §f§l/areadetector add (type)");
					player.sendMessage(
							"§8§o- TYPES: §9§oMINE§8§o, §9§lDETECTOR");
					player.sendMessage(
							"§8§l[§9§l?§8§l] §b§oTo remove an Area Detector, use §f§l/areadetector remove (name)");
					player.sendMessage(
							"§8§l[§9§l?§8§l] §b§oTo identify the name of an Area Detector, use §f§l/areadetector identify");
					player.sendMessage("§8§o- then, click the detector block. Use the command once again to quit the tool.");
				} else if (args.length == 1) {
					switch (args[0].toLowerCase()) {
					case "identify":
						if (AreaDetectorIdentify.getIdentifyList().containsKey(player.getName())) {
							if (AreaDetectorIdentify.getIdentifyList().get(player.getName()).equals(true)) {
								AreaDetectorIdentify.disableTool(player.getName());
							} else {
								AreaDetectorIdentify.enableTool(player.getName());
							}
						} else {
							AreaDetectorIdentify.enableTool(player.getName());
						}
						break;
					case "add":
						player.sendMessage(
								"§8§l[§c§l!§8§l] §c§oTo add a new Area Detector, use §f§l/areadetector add (type)");
						player.sendMessage(
								"§8§o- TYPES: §9§oMINE§8§o, §9§lDETECTOR");
						break;
					case "remove":
						player.sendMessage(
								"§8§l[§c§l!§8§l] §c§oTo remove an Area Detector, use §f§l/areadetector remove (name)");
					case "debuglist":
						for(Entry<String, List<Location>> e : AreaDetector.getDetectors().entrySet()) {
							API.sendDebugMsg("Name: " + e.getKey() + "   Locations: " + e.getValue());
							API.sendDebugMsg("Type: " + AreaDetector.getList().get(e.getKey()));
						}
						break;
					default:
						player.sendMessage("§8§l[§c§l!§8§l] §c§oWrong usage.");
						break;
					}
				} else if (args.length == 2) {
					switch (args[0].toLowerCase()) {
					case "add":
						if (args[1] != null && !args[1].isEmpty() && (args[1].equalsIgnoreCase("mine") || args[1].equalsIgnoreCase("detector"))) {
							AreaDetector.creationManager(player,args[1]);
						} else {
							player.sendMessage(
									"§8§l[§c§l!§8§l] §c§oYou need to select the type. See §e§o/areadetector§c§o for more information.");
						}
						break;
					case "remove":
						if (args[1] != null && !args[1].isEmpty()) {
							if (AreaDetector.getDetectors().containsKey(args[1])) {
								if (AreaDetectorIdentify.getIdentifyList().containsKey(player.getName()))
									AreaDetectorIdentify.disableTool(player.getName());
								AreaDetector.remove(args[1]);
								player.sendMessage("§8§l[§a§l!§8§l] §a§oArea Detector §c§l" + args[1]
										+ " §a§oremoved §2§lsuccessfully§a§o.");
							} else {
								player.sendMessage("§8§l[§c§l!§8§l] §c§oArea Detector named §e§l" + args[1]
										+ " §c§odoes not exist! See §e§o/areadetector§c§o for more information.");
							}
						} else {
							player.sendMessage(
									"§8§l[§c§l!§8§l] §c§oYou need to specify the name of the Area Detector. See §e§o/areadetector§c§o for more information.");
						}
						break;
					default:
						player.sendMessage("§8§l[§c§l!§8§l] §c§oWrong usage.");
						break;
					}
				}
			} else {
				player.sendMessage("§8§l[§c§l!§8§l] §c§oYou do not have the permission to use this command.");
			}
		}
		
		if (cmd.getName().equalsIgnoreCase("network")) {
			if (player.hasPermission("scprp.admin") || player.hasPermission("scp.admin")
					|| player.hasPermission("scprp.mod") || player.hasPermission("scp.mod")) {
				if (args.length == 0) {
					player.sendMessage(
							"§8§l[§9§l?§8§l] §b§oTo start creating a wireless network, use §f§l/network create (name)");
					player.sendMessage(
							"§8§l- then, add the Lever and the blocks that will be powered by the lever wirelessly.");
					player.sendMessage(
							"§8§l[§9§l?§8§l] §b§oTo expand a wireless network (add new target blocks), use §f§l/network expand (name)");
					player.sendMessage(
							"§8§l[§9§l?§8§l] §b§oTo remove a wireless network, use §f§l/network remove (name)");
					player.sendMessage(
							"§8§l[§9§l?§8§l] §b§oTo identify the name of a wireless network, use §f§l/network identify");
					player.sendMessage("§8§l- then, click the lever. Use the command once again to quit the tool.");
					player.sendMessage(
							"§8§l[§9§l?§8§l] §b§oTo get the Network Completion Tool (incase you lost it), use §f§l/network tool");
				} else if (args.length == 1) {
					switch (args[0].toLowerCase()) {
					case "identify":
						if (IdentifyWNetwork.getIdentifyList().containsKey(player.getName())) {
							if (IdentifyWNetwork.getIdentifyList().get(player.getName()).equals(true)) {
								IdentifyWNetwork.disableTool(player.getName());
							} else {
								IdentifyWNetwork.enableTool(player.getName());
							}
						} else {
							IdentifyWNetwork.enableTool(player.getName());
						}
						break;
					case "tool":
						if (WirelessNetworks.getPlayers().indexOf(player.getName()) == -1) {
							player.sendMessage(
									"§8§l[§c§l!§8§l] §c§oYou can not get the Network Completion Tool while not in Creator mode.");
						} else {
							player.getInventory().addItem(Items.networkCompleteTool());
							player.sendMessage(
									"§8§l[§a§l!§8§l] §a§oThe Network Completion Tool should be in your inventory.");
						}
						break;
					case "create":
						player.sendMessage(
								"§8§l[§c§l!§8§l] §c§oTo start creating a wireless network, use §f§l/network create (name)");
						break;
					case "expand":
						player.sendMessage(
								"§8§l[§9§l?§8§l] §b§oTo expand a wireless network (add new target blocks), use §f§l/network expand (name)");
						break;
					case "remove":
						player.sendMessage(
								"§8§l[§c§l!§8§l] §c§oTo remove a wireless network, use §f§l/network remove (name)");
						break;
					default:
						player.sendMessage("§8§l[§c§l!§8§l] §c§oWrong usage.");
						break;
					}
				} else if (args.length == 2) {
					switch (args[0].toLowerCase()) {
					case "create":
						if (args[1] != null && !args[1].isEmpty()) {
							if (!WirelessNetworks.getNetworks().containsKey(args[1])) {
								if (WirelessNetworks.getPlayers().indexOf(player.getName()) != -1) {
									player.sendMessage(
											"§8§l[§c§l!§8§l] §c§oYou are already in the §e§oCreator mode§c§o. To finish creating a network, right click a block with the §a§lNetwork Completion tool §e§o(a diamond hoe)");
								} else {
									if (IdentifyWNetwork.getIdentifyList().containsKey(player.getName()))
										IdentifyWNetwork.disableTool(player.getName());
									WirelessNetworks.getPlayersLever().add(player.getName());
									WirelessNetworks.startCreation(player.getName(), args[1]);
								}
							} else {
								player.sendMessage("§8§l[§c§l!§8§l] §c§oA network named §e§l" + args[1]
										+ " §c§oalready exists! Choose a different name.");
							}
						} else {
							player.sendMessage(
									"§8§l[§c§l!§8§l] §c§oYou need to name the network. See §e§o/network§c§o for more information.");
						}
						break;
					case "remove":
						if (args[1] != null && !args[1].isEmpty()) {
							if (WirelessNetworks.getNetworks().containsKey(args[1])) {
								if (IdentifyWNetwork.getIdentifyList().containsKey(player.getName()))
									IdentifyWNetwork.disableTool(player.getName());
								WirelessNetworks.removeNetwork(args[1]);
								player.sendMessage("§8§l[§a§l!§8§l] §a§oNetwork §c§l" + args[1]
										+ " §a§oremoved §2§lsuccessfully§a§o.");
							} else {
								player.sendMessage("§8§l[§c§l!§8§l] §c§oA network named §e§l" + args[1]
										+ " §c§odoes not exist! See §e§o/network§c§o for more information.");
							}
						} else {
							player.sendMessage(
									"§8§l[§c§l!§8§l] §c§oYou need to specify the name of the network. See §e§o/network§c§o for more information.");
						}
						break;
					case "expand":
						if (args[1] != null && !args[1].isEmpty()) {
							if (WirelessNetworks.getNetworks().containsKey(args[1])) {
								if (IdentifyWNetwork.getIdentifyList().containsKey(player.getName()))
									IdentifyWNetwork.disableTool(player.getName());
									WirelessNetworks.expand(player.getName(), args[1]);
							} else {
								player.sendMessage("§8§l[§c§l!§8§l] §c§oA network named §e§l" + args[1]
										+ " §c§odoes not exist! See §e§o/network§c§o for more information.");
							}
						} else {
							player.sendMessage(
									"§8§l[§c§l!§8§l] §c§oYou need to specify the name of the network. See §e§o/network§c§o for more information.");
						}
						break;
					default:
						player.sendMessage("§8§l[§c§l!§8§l] §c§oWrong usage.");
						break;
					}
				}
			} else {
				player.sendMessage("§8§l[§c§l!§8§l] §c§oYou do not have the permission to use this command.");
			}
		}

		return true;
	}

	public static SCPRP getInstance() {
		return main;
	}

	public static SCPRP getInstace() {
		return main;
	}

	@Override
	public void abandonConversation(Conversation arg0) {

	}

	@Override
	public void abandonConversation(Conversation arg0, ConversationAbandonedEvent arg1) {

	}

	@Override
	public void acceptConversationInput(String arg0) {

	}

	@Override
	public boolean beginConversation(Conversation arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isConversing() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void sendRawMessage(String arg0) {

	}
}