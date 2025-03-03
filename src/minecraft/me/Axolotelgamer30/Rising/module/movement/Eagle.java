package me.Axolotelgamer30.Rising.module.movement;

import org.lwjgl.input.Keyboard;

import me.Axolotelgamer30.Rising.Rising;
import me.Axolotelgamer30.Rising.module.Category;
import me.Axolotelgamer30.Rising.module.Module;

public class Eagle extends Module{

	public Eagle() {
		super("Eagle", Keyboard.KEY_NONE, Category.MOVEMENT);
	}
	
	public void onUpdate() {
		if(this.toggled) {
			Rising.instance.addChatMessage("not working rn");
		}

	}

}
