package cz.vojtiisek.scprpsystem;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.spigotmc.event.entity.EntityDismountEvent;

public class SitPlayerDown implements Listener {
	private static SCPRP main;
	public static HashMap<String, ArmorStand> armorStands = new HashMap<String, ArmorStand>();

	public SitPlayerDown(SCPRP main) {
		this.main = main;
	}

	public static void sitDown(Player player) {
		if (player.isOnGround()) {
			if (!player.getLocation().getBlock().isLiquid()) {
				if(getArmorStands().containsKey(player.getName())) { 
					
					ArmorStand aS = getArmorStands().get(player.getName());
					aS.setInvulnerable(true);
					aS.setVisible(false);
					aS.setSilent(true);
					aS.setGravity(false);
					aS.addPassenger(player); //setpassenger
					
				} else {
					
					Location loc = player.getLocation();
					ArmorStand armorStand = (ArmorStand) loc.getWorld().spawnEntity(loc.add(0,-1.43125,0),
							EntityType.ARMOR_STAND);

					armorStand.setInvulnerable(true);
					armorStand.setVisible(false);
					armorStand.setSilent(true);
					armorStand.setGravity(false);
					
					getArmorStands().put(player.getName(), armorStand);
					armorStand.addPassenger(player);
					
				}
			} else {
				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(SCPRP.getInstace(), () -> sitDown(player),
						20);
			}
		} else {
			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(SCPRP.getInstace(), () -> sitDown(player), 20);
		}
	}

	public static void unSit(Player player) {
		if (getArmorStands().get(player.getName()) != null && getArmorStands().containsKey(player.getName())) {
			ArmorStand armorStand = getArmorStands().get(player.getName());
			getArmorStands().remove(player.getName());
			player.leaveVehicle();
			armorStand.removePassenger(player);
			armorStand.setInvulnerable(false);
			armorStand.setHealth(0);
			armorStand.remove();
			
			if (getArmorStands().get(player.getName()) != null && getArmorStands().containsKey(player.getName())) getArmorStands().remove(player.getName());
			if (DRSys.paralysedPlayers.get(player.getName()) != null && DRSys.paralysedPlayers.containsKey(player.getName())) DRSys.paralysedPlayers.remove(player.getName());
			
			player.sendMessage(
					"§8[§9§l?§8] §e§oPress §c§lSHIFT§e§o to stand up in case you don't stand up automatically.");
		} else {
			API.sendConsoleCommand("discord broadcast #839057785362448424 [DRSYS] unSit getArmorStands() null");
		}
	}

	public static HashMap<String, ArmorStand> getArmorStands() {
		return armorStands;
	}

	@EventHandler
	public void onVehicle(EntityDismountEvent e) {
		if (e.getEntity() instanceof Player) {
			Player player = (Player) e.getEntity();
			if (e.getDismounted() instanceof ArmorStand) {
				if (DRSys.paralysedPlayers.get(player.getName()) != null && DRSys.paralysedPlayers.containsKey(player.getName())) {
					sitDown(player);
				}
			}
		}
	}
}