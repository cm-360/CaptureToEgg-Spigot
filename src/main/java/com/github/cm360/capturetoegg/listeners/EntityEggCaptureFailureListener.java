package com.github.cm360.capturetoegg.listeners;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.projectiles.ProjectileSource;

import com.github.cm360.capturetoegg.events.EntityEggCaptureFailureEvent;
import com.github.cm360.capturetoegg.events.EntityEggCaptureFailureEvent.Reason;

public class EntityEggCaptureFailureListener implements Listener {

	@EventHandler
	public void onEntityEggCaptureFailure(EntityEggCaptureFailureEvent event) {
		Entity target = event.getEntity();
		World world = target.getWorld();
		Location location = target.getLocation();

		// Announce to players
		ProjectileSource shooter = event.getEgg().getShooter();
		if (shooter instanceof Player) {
			sendFailureMessage((Player) shooter, target.getType().toString(), event.getReason());
		}
		// Particles and sound
		doFailureEffect(world, location);
	}

	public void sendFailureMessage(Player player, String entityName, Reason reason) {
		// TODO configurable messages
		switch (reason) {
		case NO_EGG:
			player.sendMessage(ChatColor.RED + String.format("You cannot capture a %s!", entityName));
			break;
		case PERMISSION:
			player.sendMessage(ChatColor.RED + String.format("You do not have permission to capture a %s!", entityName));
			break;
		default:
			player.sendMessage(ChatColor.RED + String.format("Your attempt to capture the %s failed!", entityName));
			break;
		}
	}

	public void doFailureEffect(World world, Location location) {
		// TODO configurable effects
		world.spawnParticle(Particle.SMOKE_NORMAL, location, 20, 0.5, 0.5, 0.5, 0);
		world.playSound(location, Sound.ENTITY_LLAMA_EAT, SoundCategory.PLAYERS, 3, 1.5f);
	}

}
