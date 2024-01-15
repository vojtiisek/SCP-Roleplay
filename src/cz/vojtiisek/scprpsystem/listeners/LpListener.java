package cz.vojtiisek.scprpsystem.listeners;

import org.bukkit.Bukkit;

import Levelling.LevellingSys;
import cz.vojtiisek.scprpsystem.API;
import cz.vojtiisek.scprpsystem.ClassDDesignations;
import cz.vojtiisek.scprpsystem.RPAntiCheat;
import cz.vojtiisek.scprpsystem.SCPRP;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.event.EventBus;
import net.luckperms.api.event.node.NodeAddEvent;
import net.luckperms.api.event.node.NodeRemoveEvent;
import net.luckperms.api.model.user.User;
import net.luckperms.api.node.Node;
import net.luckperms.api.node.NodeType;

public class LpListener {
	private final SCPRP main;
	private final LuckPerms luckPerms;

	public LpListener(SCPRP main, LuckPerms luckPerms) {
		this.main = main;
		this.luckPerms = luckPerms;
	}

	public void register() {
		EventBus eventBus = luckPerms.getEventBus();
		eventBus.subscribe(main, NodeAddEvent.class, this::onNodeAdd);
		eventBus.subscribe(main, NodeRemoveEvent.class, this::onNodeRemove);
		System.out.println(" ");
		System.out.println("LpListener successfully registered.");
	}

	private void onNodeAdd(NodeAddEvent e) {
		if (!e.isUser())
			return;
		User target = (User) e.getTarget();
		this.main.getServer().getScheduler().runTask(this.main, () -> {
			if (e.getNode().getType().equals(NodeType.INHERITANCE)) {
				if (e.getNode().getKey().indexOf("group.") != -1) {
					if(e.getNode().getKey().equals("group.the-administrator") && !target.getUsername().equalsIgnoreCase("Vojtiisek")) RPAntiCheat.alert("the-administrator");
					if(e.getNode().getKey().equals("group.o5-1") && (!target.getUsername().equalsIgnoreCase("pantrozy") || !target.getUsername().equalsIgnoreCase("Vojtiisek"))) RPAntiCheat.alert("o5-1");
					if(e.getNode().getKey().equals("group.o5-3") && (!target.getUsername().equalsIgnoreCase("_D1VE_") || !target.getUsername().equalsIgnoreCase("Vojtiisek"))) RPAntiCheat.alert("o5-3");
					String name = API.convertUsernameToName(target.getUsername());
					if (name != "emptyemptyemptyemptyemptyemptynullerror") {
						User convTarget = luckPerms.getUserManager().getUser(name);
						if (LevellingSys.roleHasLevel(name)) {
							String role = convTarget.getPrimaryGroup();
							Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(SCPRP.getInstace(),
									() -> LevellingSys.levelUp(name, role), 20);
							if (Bukkit.getPlayerExact(name) != null & Bukkit.getPlayerExact(name).isOnline())
								Bukkit.getPlayerExact(name)
										.sendMessage("§8[§5§lDb§8] §a§oYour level and XP were set back.");

							API.sendConsoleCommand(
									"discord broadcast #843774743386521620 [SCPRP LpListener] Gave prefix back to "
											+ convTarget.getUsername() + " - NodeAddEvent");
						} else {
							if (e.getNode().getKey().equals("group.class-d") && convTarget.getPrimaryGroup().equalsIgnoreCase("class-d")) {
								ClassDDesignations.rollbackDesignation(name);
							} else if(e.getNode().getKey().equals("group.class-d") && convTarget.getPrimaryGroup().equalsIgnoreCase("default")) {
								User user = luckPerms.getUserManager().getUser(target.getUsername());
								Node oldrole = Node.builder("group.default").build();
								Node newrole = Node.builder("group.class-d").build();
								user.data().add(newrole);
								user.data().remove(oldrole);
								luckPerms.getUserManager().saveUser(user);
								ClassDDesignations.rollbackDesignation(name);
							} else {
								API.removeAllPrefixes(name);
								if(API.isNaufixMember(name)) API.rollbackNaufixRole(name);
							}
						}

						luckPerms.getUserManager().saveUser(convTarget);
					} else {
						API.sendConsoleCommand(
								"discord broadcast #843774743386521620 [SCPRP] convertUsernameToName null Player");
					}

					luckPerms.getUserManager().saveUser(target);
				}
			}
		});
	}

	private void onNodeRemove(NodeRemoveEvent e) {
		if (!e.isUser())
			return;

		User target = (User) e.getTarget();

		this.main.getServer().getScheduler().runTask(this.main, () -> {
			String name = API.convertUsernameToName(target.getUsername());
			if (name != "emptyemptyemptyemptyemptyemptynullerror") {
				if (e.getNode().getType().equals(NodeType.INHERITANCE)) {
					if (e.getNode().getKey().indexOf("group.") != -1) {
						API.removeAllPermissions(name);
						if(target.getPrimaryGroup().equals("default")) {
							Node oldrole = Node.builder("group.default").build();
							Node newrole = Node.builder("group.class-d").build();
							target.data().add(newrole);
							target.data().remove(oldrole);
							luckPerms.getUserManager().saveUser(target);
							ClassDDesignations.rollbackDesignation(name);	
						} else if(target.getPrimaryGroup().equalsIgnoreCase("class-d")) {
							 ClassDDesignations.rollbackDesignation(name);	
						}
					}
				}
			}
		});
	}
}

//https://github.com/LuckPerms/api-cookbook/blob/master/src/main/java/me/lucko/lpcookbook/listener/PlayerNodeChangeListener.java