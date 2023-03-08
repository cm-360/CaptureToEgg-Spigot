package com.github.cm360.capturetoegg.listeners;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.World;
import org.bukkit.entity.ChestedHorse;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Steerable;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
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

		// Drop saddle from steerables
		if (target instanceof Steerable) {
			if (((Steerable) target).hasSaddle()) {
				world.dropItem(location, new ItemStack(Material.SADDLE, 1));
			}
		}

		// Drop chest from donkey/mule
		if (target instanceof ChestedHorse) {
			if (((ChestedHorse) target).isCarryingChest()) {
				world.dropItemNaturally(location, new ItemStack(Material.CHEST));
			}
		}

		// Drop inventory items of entity
		if (target instanceof InventoryHolder) {
			ItemStack[] items = ((InventoryHolder) target).getInventory().getContents();
			for (ItemStack itemStack : items) {
				if (itemStack != null) {
					world.dropItemNaturally(location, itemStack);
				}
			}
		}

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
