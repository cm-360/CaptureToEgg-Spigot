package com.github.cm360.capturetoegg.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Ageable;
import org.bukkit.entity.Egg;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Tameable;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.PluginManager;

import com.github.cm360.capturetoegg.events.EntityEggCaptureAttemptEvent;
import com.github.cm360.capturetoegg.events.EntityEggCaptureFailureEvent;
import com.github.cm360.capturetoegg.events.EntityEggCaptureFailureEvent.Reason;
import com.github.cm360.capturetoegg.events.EntityEggCaptureSuccessEvent;

public class EntityEggCaptureAttemptListener implements Listener {

	public static double catchChance = 0.05;
	
	public static boolean allowCatchingBabies = true;
	public static boolean allowCatchingTamed = false;

	@EventHandler
	public void onEntityEggCaptureAttempt(EntityEggCaptureAttemptEvent event) {
		PluginManager pm = Bukkit.getServer().getPluginManager();
		
		Egg egg = event.getEgg();
		Entity target = event.getEntity();
		
		// Attempt to get spawn egg item for this mob
		ItemStack spawnEgg = generateSpawnEgg(target.getType());
		if (spawnEgg == null) {
			pm.callEvent(new EntityEggCaptureFailureEvent(target, egg, Reason.NO_EGG));
			return;
		}
		
		// TODO check permissions
		
		// Check if baby mobs are allowed
		if (!allowCatchingBabies) {
			if (target instanceof Ageable && !((Ageable) target).isAdult()) {
				pm.callEvent(new EntityEggCaptureFailureEvent(target, egg, Reason.BABY));
				return;
			}
		}
		
		// Check if tamed mobs are allowed
		if (!allowCatchingTamed) {
			if (target instanceof Tameable && ((Tameable) target).isTamed()) {
				pm.callEvent(new EntityEggCaptureFailureEvent(target, egg, Reason.TAMED));
				return;
			}
		}
		
		// Random chance for catching
		if (Math.random() < catchChance) {
			pm.callEvent(new EntityEggCaptureSuccessEvent(target, egg, spawnEgg));
		} else {
			pm.callEvent(new EntityEggCaptureFailureEvent(target, egg, Reason.CHANCE));
		}
	}
	
	public ItemStack generateSpawnEgg(EntityType entityType) {
		String spawnEggId = entityType.toString() + "_SPAWN_EGG";
		Material spawnEggMaterial = Material.getMaterial(spawnEggId);
		
		// TODO custom eggs for entities without a vanilla one
		if (spawnEggMaterial == null)
			return null;
		
		ItemStack spawnEggStack = new ItemStack(spawnEggMaterial);
		
		return spawnEggStack;
	}

}
