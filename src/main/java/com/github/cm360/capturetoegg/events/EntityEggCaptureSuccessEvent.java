package com.github.cm360.capturetoegg.events;

import org.bukkit.entity.Egg;
import org.bukkit.entity.Entity;
import org.bukkit.inventory.ItemStack;

public class EntityEggCaptureSuccessEvent extends EntityEggCaptureEvent {

	protected ItemStack resultStack;
	
	public EntityEggCaptureSuccessEvent(Entity entity, Egg egg, ItemStack resultStack) {
		super(entity, egg);
		this.resultStack = resultStack;
	}
	
	public ItemStack getResultStack() {
		return resultStack;
	}

}
