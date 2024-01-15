package cz.vojtiisek.scprpsystem;

import java.util.HashMap;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Animals;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.EntityTargetLivingEntityEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import Testing.TestingSys;
import cz.vojtiisek.scprpsystem.Events.SpawnkillProtect;
import cz.vojtiisek.scprpsystem.Spawnpoints.SpawnSys;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.model.user.User;

public class DRSys implements Listener {
	private SCPRP main;
	public static LuckPerms api;

	public static HashMap<String, Long> paralysedPlayers = new HashMap<String, Long>();

	public DRSys(SCPRP main) {
		this.main = main;
	}

	public static boolean isParalysed(String player) {
		if (paralysedPlayers.containsKey(player)) {
			return true;
		} else {
			return false;
		}
	}

	/*
	 * public void paralyse(Player sender, String player) {
	 * if(!paralysedPlayers.contains(player)) { paralysedPlayers.add(player); } else
	 * { sender.sendMessage("§8[§c§l!§8] §c§o" + player +
	 * "§2§o is not knocked down!"); } }
	 */

	public static void revive(Player sender, String name) {
		Random random = new Random();
		int rand = random.nextInt(4);
		if (rand == 4)
			rand = 3;
		switch (rand) {
		case 0:
			paralysedPlayers.remove(name);
			SitPlayerDown.unSit(Bukkit.getPlayerExact(name));
			API.sendTitle(Bukkit.getPlayerExact(name), "§a§lYou have been revived by §d§l" + sender.getName(), "");
			Bukkit.getPlayerExact(name).sendMessage("§8[§a§l!§8] §2§oYou have been revived by §f§l" + sender.getName());
			sender.sendMessage("§8[§a§l!§8] §2§oYou have successfully revived §f§l" + name);
			activateReviveProtec(name);
			break;
		case 1:
			paralysedPlayers.remove(name);
			SitPlayerDown.unSit(Bukkit.getPlayerExact(name));
			API.sendTitle(Bukkit.getPlayerExact(name), "§a§lYou have been revived by §d§l" + sender.getName(), "");
			Bukkit.getPlayerExact(name).sendMessage("§8[§a§l!§8] §2§oYou have been revived by §f§l" + sender.getName());
			sender.sendMessage("§8[§a§l!§8] §2§oYou have successfully revived §f§l" + name);
			activateReviveProtec(name);
			break;
		case 2:
			paralysedPlayers.remove(name);
			SitPlayerDown.unSit(Bukkit.getPlayerExact(name));
			API.sendTitle(Bukkit.getPlayerExact(name), "§a§lYou have been revived by §d§l" + sender.getName(), "");
			Bukkit.getPlayerExact(name).sendMessage("§8[§a§l!§8] §2§oYou have been revived by §f§l" + sender.getName());
			sender.sendMessage("§8[§a§l!§8] §2§oYou have successfully revived §f§l" + name);
			activateReviveProtec(name);
			break;
		case 3:
			paralysedPlayers.remove(name);
			SitPlayerDown.unSit(Bukkit.getPlayerExact(name));
			sender.sendMessage("§8[§c§l!§8] §8§oThe attempt to revive §c§l" + name + "§8§o was unsuccessful.");
			Bukkit.getPlayerExact(name).addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 10, 250));
			SpawnSys.respawn(name);
			break;
		default:
			paralysedPlayers.remove(name);
			SitPlayerDown.unSit(Bukkit.getPlayerExact(name));
			API.sendTitle(Bukkit.getPlayerExact(name), "§a§lYou have been revived by §d§l" + sender.getName(), "");
			Bukkit.getPlayerExact(name).sendMessage("§8[§a§l!§8] §2§oYou have been revived by §f§l" + sender.getName());
			sender.sendMessage("§8[§a§l!§8] §2§oYou have successfully revived §f§l" + name);
			activateReviveProtec(name);
			break;
		}

	}

	private static void activateReviveProtec(String name) {
		Player p = Bukkit.getPlayerExact(name);
		SpawnkillProtect.getProtections().put(name, System.currentTimeMillis() + 10000);
		p.sendMessage("§8§l[§a§l!§8§l] §a§oRevival Protection activated for the next §c§o10§a§o seconds...");
	}

	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent e) {
		if (paralysedPlayers.containsKey(e.getPlayer().getName()))
			e.setCancelled(true);
	}

	@EventHandler
	public void onPlayerMove(PlayerMoveEvent e) {
		if (paralysedPlayers.containsKey(e.getPlayer().getName())) {
			if (e.getPlayer().isOnGround()) {
				if (!e.getPlayer().getLocation().getBlock().isLiquid()) {
					Location loc = e.getPlayer().getLocation();
					e.setTo(loc);
					e.setCancelled(true);
				}
			}
		}
	}

	@EventHandler
	public void onItemPickup(PlayerPickupItemEvent e) {
		if (paralysedPlayers.containsKey(e.getPlayer().getName()))
			e.setCancelled(true);
	}

	@EventHandler
	public void onItemDrop(PlayerDropItemEvent e) {
		if (paralysedPlayers.containsKey(e.getPlayer().getName()))
			e.setCancelled(true);
	}

	@EventHandler
	public void onPlayerGetHit(EntityDamageEvent e) {
		if (e.getEntity() instanceof Player) {
			Player target = (Player) e.getEntity();
			String name = target.getName();
			boolean bool = false;
			if (e.getDamage() >= target.getHealth()) {
				if (TestingSys.testMembers.get("049") == null) {
						if (API.getOnlineMedics() != "errorEmptyListNoOnline") {
							if (!paralysedPlayers.containsKey(name)) {
								e.setCancelled(true);
								target.setHealth(4);
								target.setSaturation(10);
								API.sendWarning("§c§l" + name + "§a§o needs help!",
										"§e§oNavigate to him/her and use §c§l/revive " + name, "medic");
								API.sendWarning("§c§l" + name + "§a§o needs help!",
										"§e§oNavigate to him/her and use §c§l/revive " + name, "head-medic");
								for (Player player : Bukkit.getOnlinePlayers()) {
									User user = LuckPermsProvider.get().getUserManager().getUser(player.getName());
									if (user.getPrimaryGroup().equalsIgnoreCase("medic")
											|| user.getPrimaryGroup().equalsIgnoreCase("head-medic")) {
										player.sendMessage("§f§l[§a§lSOS§f§l] §a§oPlayer: §c§l" + name + " §a§oX:§c§l "
												+ Bukkit.getPlayerExact(name).getLocation().getX() + " §a§oY:§c§l "
												+ Bukkit.getPlayerExact(name).getLocation().getY() + " §a§oZ:§c§l "
												+ Bukkit.getPlayerExact(name).getLocation().getZ());
									}
								}
								if (LuckPermsProvider.get().getUserManager().getUser(name).getPrimaryGroup()
										.equalsIgnoreCase("medic")
										|| LuckPermsProvider.get().getUserManager().getUser(name).getPrimaryGroup()
												.equalsIgnoreCase("head-medic")) {
									paralysedPlayers.put(target.getName(), System.currentTimeMillis() + 30 * 1000);
								} else {
									paralysedPlayers.put(target.getName(), System.currentTimeMillis() + 90 * 1000);
								}
								SitPlayerDown.sitDown(target);
								target.sendTitle("§c§lYou are knocked down!", "§e§oWait for any Medic to revive you.",
										20, 80, 20);

								if (paralysedPlayers.get(name) > System.currentTimeMillis()) {
									sendTitle(target);
								}

							} else {
								e.setDamage(0);
							}
						} else {
							e.setCancelled(true);
							target.setHealth(4);
							target.setSaturation(10);
							SpawnSys.respawn(name);
							System.out.println("[SCPRP DRSYS] " + e.getEntity().getName()
									+ " died, but no Medics are online - respawning immediatelly.");
						}
					} else {
						if (TestingSys.testMembers.get("049").contains(name)) {
							System.out.println("[SCPRP DRSYS] " + e.getEntity().getName()
									+ " died during a SCP-049 test - letting PlayerDeathEvent & PlayerRespawnEvent handle it.");
					} else {
						if (API.getOnlineMedics() != "errorEmptyListNoOnline") {
							if (!paralysedPlayers.containsKey(name)) {
								e.setCancelled(true);
								target.setHealth(4);
								target.setSaturation(10);
								API.sendWarning("§c§l" + name + "§a§o needs help!",
										"§e§oNavigate to him/her and use §c§l/revive " + name, "medic");
								API.sendWarning("§c§l" + name + "§a§o needs help!",
										"§e§oNavigate to him/her and use §c§l/revive " + name, "head-medic");
								for (Player player : Bukkit.getOnlinePlayers()) {
									User user = LuckPermsProvider.get().getUserManager().getUser(player.getName());
									if (user.getPrimaryGroup().equalsIgnoreCase("medic")
											|| user.getPrimaryGroup().equalsIgnoreCase("head-medic")) {
										player.sendMessage("§f§l[§a§lSOS§f§l] §a§oPlayer: §c§l" + name + " §a§oX:§c§l "
												+ Bukkit.getPlayerExact(name).getLocation().getX() + " §a§oY:§c§l "
												+ Bukkit.getPlayerExact(name).getLocation().getY() + " §a§oZ:§c§l "
												+ Bukkit.getPlayerExact(name).getLocation().getZ());
									}
								}
								if (LuckPermsProvider.get().getUserManager().getUser(name).getPrimaryGroup()
										.equalsIgnoreCase("medic")
										|| LuckPermsProvider.get().getUserManager().getUser(name).getPrimaryGroup()
												.equalsIgnoreCase("head-medic")) {
									paralysedPlayers.put(target.getName(), System.currentTimeMillis() + 30 * 1000);
								} else {
									paralysedPlayers.put(target.getName(), System.currentTimeMillis() + 90 * 1000);
								}
								SitPlayerDown.sitDown(target);
								target.sendTitle("§c§lYou are knocked down!", "§e§oWait for any Medic to revive you.",
										20, 80, 20);

								if (paralysedPlayers.get(name) > System.currentTimeMillis()) {
									sendTitle(target);
								}

							} else {
								e.setDamage(0);
							}
						} else {
							e.setCancelled(true);
							target.setHealth(4);
							target.setSaturation(10);
							SpawnSys.respawn(name);
							System.out.println("[SCPRP DRSYS] " + e.getEntity().getName()
									+ " died, but no Medics are online - respawning immediatelly.");
						}
					}
				} 
			}
		}
	}

	@EventHandler
	public void onPlayerHitEntity(EntityDamageByEntityEvent e) {
		if (e.getDamager() instanceof Player) {
			Player target = (Player) e.getDamager();
			String name = target.getName();
			if (paralysedPlayers.containsKey(name)) {
				e.setCancelled(true);
			}
		}
	}

	@EventHandler
	public void onPlayerRespawn(PlayerRespawnEvent e) {
		if (SpawnkillProtect.getCombat().containsKey(e.getPlayer().getName()))
			SpawnSys.respawn(e.getPlayer().getName());
	}

	public void sendTitle(Player target) {
		String name = target.getName();
		if (paralysedPlayers.get(name) == null) {
		} else {
			if (paralysedPlayers.get(name) > System.currentTimeMillis()) {

				target.sendTitle("§c§l" + API.formatTime(paralysedPlayers.get(name) - System.currentTimeMillis()),
						"§e§oUntil you respawn automatically.", 0, 20, 0);
				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(main, () -> sendTitle(target), 20);
			} else {
				if (paralysedPlayers.containsKey(name))
					paralysedPlayers.remove(target.getName());
				SitPlayerDown.unSit(target);
				SpawnSys.respawn(name);
			}
		}
	}

	@EventHandler
	public void onPlayerTargeted(EntityTargetLivingEntityEvent e) {
		if (e.getTarget() instanceof Player) {
			if (e.getEntity() instanceof Animals || e.getEntity() instanceof Monster
					|| e.getEntity() instanceof LivingEntity) {
				if (paralysedPlayers.containsKey(e.getTarget().getName())) {
					e.setCancelled(true);
				}
			}
		}
	}
}
