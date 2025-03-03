package me.Axolotelgamer30.Rising.module.movement;

import org.lwjgl.input.Keyboard;

import me.Axolotelgamer30.Rising.module.Category;
import me.Axolotelgamer30.Rising.module.Module;

public class Sprint extends Module{

	public Sprint() {
		super("Sprint", Keyboard.KEY_NONE, Category.MOVEMENT);
		
	}
	
	public void onUpdate() {
		if(this.isToggled() && !mc.thePlayer.isSneaking() && !mc.thePlayer.isCollidedHorizontally && mc.thePlayer.moveForward > 0) {
			mc.thePlayer.setSprinting(true);
		}
	}

}
