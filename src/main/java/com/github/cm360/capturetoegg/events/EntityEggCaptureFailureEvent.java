package com.github.cm360.capturetoegg.events;

import org.bukkit.entity.Egg;
import org.bukkit.entity.Entity;

public class EntityEggCaptureFailureEvent extends EntityEggCaptureEvent {

	public enum Reason { CHANCE, NO_EGG, PERMISSION };
	
	protected Reason reason;
	
	public EntityEggCaptureFailureEvent(Entity entity, Egg egg, Reason reason) {
		super(entity, egg);
		this.reason = reason;
	}
	
	public Reason getReason() {
		return reason;
	}

}
