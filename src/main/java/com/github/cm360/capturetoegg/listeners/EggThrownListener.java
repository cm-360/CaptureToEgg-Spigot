package com.github.cm360.capturetoegg.listeners;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerEggThrowEvent;

public class EggThrownListener implements Listener {

	protected FileConfiguration config;
	
	public EggThrownListener(FileConfiguration config) {
		this.config = config;
	}
	
	@EventHandler
	public void onPlayerThrowEgg(PlayerEggThrowEvent event) {
		if (event.getEgg().getItem().getItemMeta().getCustomModelData() == config.getInt("custom_model_data"))
			event.setHatching(false);
	}

}
