package com.github.cm360.capturetoegg.listeners;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Egg;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.inventory.meta.ItemMeta;

import com.github.cm360.capturetoegg.events.EntityEggCaptureAttemptEvent;

public class EntityEggHitListener implements Listener {

	protected FileConfiguration config;
	
	public EntityEggHitListener(FileConfiguration config) {
		this.config = config;
	}
	
	@EventHandler
	public void onProjectileHit(ProjectileHitEvent event) {
		// Only if projectile hit an entity
		Entity hit = event.getHitEntity();
		if (hit == null)
			return;

		// Eggs only
		Entity projectile = event.getEntity();
		if (!(projectile instanceof Egg))
			return;

		// Custom eggs only
		Egg egg = (Egg) projectile;
		ItemMeta im = egg.getItem().getItemMeta();
		if (!(im.hasCustomModelData() && im.getCustomModelData() == config.getInt("custom_model_data")))
			return;

		Bukkit.getServer().getPluginManager().callEvent(new EntityEggCaptureAttemptEvent(hit, egg));
	}

}
