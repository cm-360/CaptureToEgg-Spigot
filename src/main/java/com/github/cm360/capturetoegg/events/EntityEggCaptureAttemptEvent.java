package com.github.cm360.capturetoegg.events;

import org.bukkit.entity.Egg;
import org.bukkit.entity.Entity;

public class EntityEggCaptureAttemptEvent extends EntityEggCaptureEvent {

	public EntityEggCaptureAttemptEvent(Entity entity, Egg egg) {
		super(entity, egg);
	}

}
