package me.Axolotelgamer30.Rising.module.movement;

import java.util.ArrayList;

import org.lwjgl.input.Keyboard;

import ionsphere.clickgui.settings.Setting;
import me.Axolotelgamer30.Rising.Rising;
import me.Axolotelgamer30.Rising.module.Category;
import me.Axolotelgamer30.Rising.module.Module;

public class VClip extends Module{

	public VClip() {
		super("VClip", Keyboard.KEY_NONE, Category.MOVEMENT);
	}
	
	@Override
	public void setup() {
		ArrayList<String> options = new ArrayList<>();
		options.add("TP");
		Rising.instance.settingsManager.rSetting(new Setting("VClip Mode", this, "TP", options));
	}
	
	@Override
	public void onEnable() {
		String mode = Rising.instance.settingsManager.getSettingByName("VClip Mode").getValString();
		if(mode == "TP") {
			double currentY = mc.thePlayer.posY;
			double idk = 3.23456326d;
			double currentX = mc.thePlayer.posX;
			double currentZ = mc.thePlayer.posZ;
			mc.thePlayer.setPosition(currentX, currentY-idk, currentZ);
			this.toggle();
		}
		
		super.onEnable();
		
	}

}
