package me.Axolotelgamer30.Rising.module.movement;

import java.util.ArrayList;

import org.lwjgl.input.Keyboard;

import ionsphere.clickgui.settings.Setting;
import me.Axolotelgamer30.Rising.Rising;
import me.Axolotelgamer30.Rising.module.Category;
import me.Axolotelgamer30.Rising.module.Module;

public class Fly extends Module {

	public Fly() {
		super("Fly", Keyboard.KEY_NONE, Category.MOVEMENT);

	}

	@Override
	public void setup() {
		ArrayList<String> options = new ArrayList<>();
		options.add("OLD VERUS");
		Rising.instance.settingsManager.rSetting(new Setting("Fly Mode", this, "OLD VERUS", options));
	}

	public void onDisable() {
		mc.thePlayer.capabilities.isFlying = false;
		}

	@Override
	public void onUpdate() {
		if (this.isToggled()) {
			double startY = mc.thePlayer.posY;

			// Check the selected mode from the checkbox setting
			if (Rising.instance.settingsManager.getSettingByName("Fly Mode").getValString()
					.equalsIgnoreCase("OLD VERUS")) {
				// Old Verus Fly
				mc.thePlayer.capabilities.isFlying = true;
				mc.thePlayer.onGround = true;
				mc.thePlayer.motionY = 0;
				mc.thePlayer.motionX *= 1.5f;
				mc.thePlayer.motionZ *= 1.5f;
				mc.timer.timerSpeed = 1.0f;

			}

		}

	}

}
