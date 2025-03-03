package me.Axolotelgamer30.Rising.module.movement;

import java.util.ArrayList;

import org.lwjgl.input.Keyboard;

import ionsphere.clickgui.settings.Setting;
import me.Axolotelgamer30.Rising.Rising;
import me.Axolotelgamer30.Rising.module.Category;
import me.Axolotelgamer30.Rising.module.Module;


public class Speed extends Module {
    private boolean bmcMode = false;

    public Speed() {
        super("SPEED", Keyboard.KEY_NONE, Category.MOVEMENT);
    }

    @Override
    public void setup() {
        ArrayList<String> options = new ArrayList<>();
        options.add("BMC");
        options.add("legit hop");
        Rising.instance.settingsManager.rSetting(new Setting("Speed Mode", this, "BMC", options));
    }

    @Override
    public void onUpdate() {
        if (this.isToggled()) {
            if (mc.thePlayer.onGround == true) {
                if (Rising.instance.settingsManager.getSettingByName("Speed Mode").getValString().equalsIgnoreCase("BMC")) {
                    mc.thePlayer.motionX *= 1.1F;
                    mc.thePlayer.jump();
                    mc.thePlayer.motionZ *= 1.1F;
                } else if (Rising.instance.settingsManager.getSettingByName("Speed Mode").getValString().equalsIgnoreCase("legit hop")){
                    mc.thePlayer.jump();
                    mc.thePlayer.setSprinting(true);
                }
            }
            
            
        }
    }
}
