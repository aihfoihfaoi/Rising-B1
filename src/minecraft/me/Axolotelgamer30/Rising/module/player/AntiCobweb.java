package me.Axolotelgamer30.Rising.module.player;

import me.Axolotelgamer30.Rising.module.Category;
import me.Axolotelgamer30.Rising.module.Module;

public class AntiCobweb extends Module{

	public AntiCobweb() {
		super("AntiCobweb", 0, Category.PLAYER);
	}
	
	@Override
	public void onUpdate() {
		if(this.isToggled()) {
			mc.thePlayer.isInWeb = false;
		}
	}

}
