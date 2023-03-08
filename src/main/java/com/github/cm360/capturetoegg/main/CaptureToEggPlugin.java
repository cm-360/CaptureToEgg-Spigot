package com.github.cm360.capturetoegg.main;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.NamespacedKey;
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

	public static int customModelData = 2100;
	
	protected List<Listener> listeners;
	
	protected NamespacedKey recipeKey;
	protected Recipe recipe;
	
	public CaptureToEggPlugin() {
		listeners = new ArrayList<Listener>();
		// Create listeners
		listeners.add(new EntityEggHitListener());
		listeners.add(new EggThrownListener());
		listeners.add(new EntityEggCaptureAttemptListener());
		listeners.add(new EntityEggCaptureSuccessListener());
		listeners.add(new EntityEggCaptureFailureListener());
		
		// Register recipe
		recipeKey = NamespacedKey.fromString("capture_egg", this);
		recipe = CaptureEggRecipe.createCaptureEggRecipe(recipeKey);
	}
	
	@Override
	public void onEnable() {
		listeners.forEach(listener -> {
			getServer().getPluginManager().registerEvents(listener, this);
		});
		getServer().addRecipe(recipe);
	}
	
	@Override
	public void onDisable() {
		HandlerList.unregisterAll(this);
		getServer().removeRecipe(recipeKey);
	}

}
