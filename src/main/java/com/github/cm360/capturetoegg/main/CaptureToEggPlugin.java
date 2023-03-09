package com.github.cm360.capturetoegg.main;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.NamespacedKey;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Recipe;
import org.bukkit.plugin.java.JavaPlugin;

import com.github.cm360.capturetoegg.listeners.EggThrownListener;
import com.github.cm360.capturetoegg.listeners.EntityEggCaptureAttemptListener;
import com.github.cm360.capturetoegg.listeners.EntityEggCaptureFailureListener;
import com.github.cm360.capturetoegg.listeners.EntityEggCaptureSuccessListener;
import com.github.cm360.capturetoegg.listeners.EntityEggHitListener;
import com.github.cm360.capturetoegg.recipes.CaptureEggRecipe;

public class CaptureToEggPlugin extends JavaPlugin {

	protected FileConfiguration config;
	
	protected List<Listener> listeners;
	
	protected NamespacedKey recipeKey;
	protected Recipe recipe;
	
	public CaptureToEggPlugin() {
		// Config
		config = getConfig();
		
		// Create listeners
		listeners = new ArrayList<Listener>();
		listeners.add(new EntityEggHitListener(config));
		listeners.add(new EggThrownListener(config));
		listeners.add(new EntityEggCaptureAttemptListener());
		listeners.add(new EntityEggCaptureSuccessListener());
		listeners.add(new EntityEggCaptureFailureListener());
		
		// Recipe key
		recipeKey = NamespacedKey.fromString("capture_egg", this);
	}
	
	@Override
	public void onEnable() {
		// Config
		config.addDefault("custom_model_data", 2100);
        config.options().copyDefaults(true);
        saveConfig();
		
        // Register listeners
		listeners.forEach(listener -> {
			getServer().getPluginManager().registerEvents(listener, this);
		});
		
		// Create recipe
		getServer().addRecipe(CaptureEggRecipe.createCaptureEggRecipe(recipeKey, config));
	}
	
	@Override
	public void onDisable() {
		// Unregister listeners
		HandlerList.unregisterAll(this);
		
		// Remove recipe
		getServer().removeRecipe(recipeKey);
	}

}
