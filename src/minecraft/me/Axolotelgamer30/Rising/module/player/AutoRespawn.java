package me.Axolotelgamer30.Rising.module.player;

import me.Axolotelgamer30.Rising.module.Category;
import me.Axolotelgamer30.Rising.module.Module;

public class AutoRespawn extends Module{

	public AutoRespawn() {
		super("AutoRespawn", 0, Category.PLAYER);
	}
	
	@Override
	public void onUpdate() {
		if(this.isToggled()) {
			if(mc.thePlayer.isDead) {
				mc.thePlayer.respawnPlayer();
			}
		}
	}

}
