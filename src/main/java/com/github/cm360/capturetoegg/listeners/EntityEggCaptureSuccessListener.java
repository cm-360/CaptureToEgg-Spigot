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

import com.github.cm360.capturetoegg.events.EntityEggCaptureSuccessEvent;

public class EntityEggCaptureSuccessListener implements Listener {

	@EventHandler
	public void onEntityEggCaptureSuccess(EntityEggCaptureSuccessEvent event) {
		Entity target = event.getEntity();
		World world = target.getWorld();
		Location location = target.getLocation();

		// Remove the hit entity
		target.remove();

		// Announce to players
		ProjectileSource shooter = event.getEgg().getShooter();
		if (shooter instanceof Player) {
			sendSuccessMessage((Player) shooter, target.getType().toString());
		}
		// Particles
		doSuccessEffect(world, location);

		// Drop spawn egg item
		world.dropItemNaturally(location, event.getResultStack());
	}

	public void sendSuccessMessage(Player player, String entityName) {
		// TODO configurable message
		player.sendMessage(ChatColor.GREEN + String.format("You successfully captured a %s!", entityName));
	}

	public void doSuccessEffect(World world, Location location) {
		// TODO configurable effects
		world.spawnParticle(Particle.CRIT, location, 20, 0.5, 0.5, 0.5, 0.1);
		world.playSound(location, Sound.ITEM_BOTTLE_FILL_DRAGONBREATH, SoundCategory.PLAYERS, 3, 2f);
	}

}
