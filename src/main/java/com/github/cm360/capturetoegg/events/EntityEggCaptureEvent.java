package com.github.cm360.capturetoegg.events;

import org.bukkit.entity.Egg;
import org.bukkit.entity.Entity;
import org.bukkit.event.HandlerList;
import org.bukkit.event.entity.EntityEvent;

public abstract class EntityEggCaptureEvent extends EntityEvent {

	private static final HandlerList HANDLERS = new HandlerList();
	
	protected Egg egg;
	
	public EntityEggCaptureEvent(Entity entity, Egg egg) {
		super(entity);
		this.egg = egg;
	}
	
	public Egg getEgg() {
		return egg;
	}
	
    public static HandlerList getHandlerList() {
        return HANDLERS;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }

}
