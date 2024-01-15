package cz.vojtiisek.scprpsystem.Events;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.player.PlayerJoinEvent;

import cz.vojtiisek.scprpsystem.SCPRP;
import net.luckperms.api.LuckPerms;

public class SpawnkillProtect implements Listener {
	private static SCPRP main;

	public static LuckPerms api;

	public static Map<String, Long> protections = new HashMap<String, Long>();
	public static Map<String, Long> combat = new HashMap<String, Long>();

	public SpawnkillProtect(SCPRP main) {
		this.main = main;
	}

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		if (!LeaveEvent.getCombatLeaves().contains(p.getName())) {
			getProtections().put(e.getPlayer().getName(), System.currentTimeMillis() + 20000);
			e.getPlayer()
					.sendMessage("§8§l[§a§l!§8§l] §a§oSpawn Protection activated for the next §c§o15§a§o seconds...");
		} else {
			LeaveEvent.getCombatLeaves().remove(p.getName());
			e.getPlayer().sendMessage("§8§l[§c§l!§8§l] §c§oSpawn Protection disabled, because you left the server while in Combat.");
		}
	}

	@EventHandler
	public void onPlayerDamage(EntityDamageEvent e) {
		if (e.getEntity() instanceof Player) {
			Player player = (Player) e.getEntity();
			if (getProtections().containsKey(player.getName())) {
				if (getProtections().get(player.getName()) >= System.currentTimeMillis()) {
					e.setDamage(0);
					e.setCancelled(true);
				}
			}
		}
	}

	@EventHandler
	public void onPlayerAttack(EntityDamageByEntityEvent e) {
		if (e.getEntity() instanceof Player) {
			Player p = (Player) e.getEntity();
			if (!(getCombat().containsKey(p.getName()) && getCombat().get(p.getName()) > System.currentTimeMillis())) {
				getCombat().put(p.getName(), System.currentTimeMillis() + 10000);
				p.sendMessage("§8§l[§c§l!§8§l] §c§oYou are in Combat now for the next 10 seconds.");
			}
		}

		if (e.getDamager() instanceof Player) {
			Player p = (Player) e.getDamager();
			if (!(getCombat().containsKey(p.getName()) && getCombat().get(p.getName()) > System.currentTimeMillis())) {
				getCombat().put(p.getName(), System.currentTimeMillis() + 10000);
				p.sendMessage("§8§l[§c§l!§8§l] §c§oYou are in Combat for the next 10 seconds.");
			}
		}

		if (e.getDamager() instanceof Player) {
			Player player = (Player) e.getDamager();
			if (getProtections().containsKey(player.getName())) {
				if (getProtections().get(player.getName()) >= System.currentTimeMillis()) {
					getProtections().remove(player.getName());
					player.sendMessage("§8§l[§c§l!§8§l] §c§oSpawn Protection cancelled.");
				}
			}
		}
	}

	public static Map<String, Long> getProtections() {
		return protections;
	}

	public static Map<String, Long> getCombat() {
		return combat;
	}
}
