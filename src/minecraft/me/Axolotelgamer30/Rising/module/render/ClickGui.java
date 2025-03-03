package me.Axolotelgamer30.Rising.module.render;

import java.util.ArrayList;

import org.lwjgl.input.Keyboard;

import ionsphere.clickgui.settings.Setting;
import me.Axolotelgamer30.Rising.Rising;
import me.Axolotelgamer30.Rising.module.Category;
import me.Axolotelgamer30.Rising.module.Module;



public class ClickGui extends Module{

	public ClickGui() {
        super("ClickGui", Keyboard.KEY_RSHIFT, Category.RENDER);
    }


    @Override
    public void onEnable() {
        super.onEnable();
        System.out.print("Clickgui toggled");
        for(Module m : Rising.instance.moduleManager.getModulesbyCategory(Category.PLAYER)) {
			if(m.getName() == "CapeModule") {
				m.toggle();
			}
		}

        mc.gameSettings.pauseOnLostFocus = false;
		mc.displayGuiScreen(Rising.instance.clickGUI);
        toggle();
        
    }
}