package me.Axolotelgamer30.Rising.module.player;

import me.Axolotelgamer30.Rising.module.Category;
import me.Axolotelgamer30.Rising.module.Module;



public class FastPlace extends Module{

	public FastPlace() {
	super("FastPlace", 0, Category.PLAYER);
	}
	
	@Override
	public void onUpdate() {
		if(this.isToggled()) {
			mc.rightClickDelayTimer = 0;
		}
	}

}
