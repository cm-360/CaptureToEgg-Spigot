package com.github.cm360.capturetoegg.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerEggThrowEvent;

import com.github.cm360.capturetoegg.main.CaptureToEggPlugin;

public class EggThrownListener implements Listener {

	@EventHandler
	public void onPlayerThrowEgg(PlayerEggThrowEvent event) {
		if (event.getEgg().getItem().getItemMeta().getCustomModelData() == CaptureToEggPlugin.customModelData)
			event.setHatching(false);
	}

}
