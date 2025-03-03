package me.Axolotelgamer30.Rising.module.render;

import java.util.ArrayList;

import org.lwjgl.input.Keyboard;

import me.Axolotelgamer30.Rising.gui.ReloadingFont;
import ionsphere.clickgui.settings.Setting;
import me.Axolotelgamer30.Rising.Rising;
import me.Axolotelgamer30.Rising.module.Category;
import me.Axolotelgamer30.Rising.module.Module;
import me.Axolotelgamer30.Rising.module.ModuleManager;
import me.Axolotelgamer30.Rising.utils.Timer;

public class FontManagerModule extends Module{
	private String previousFont = "";
	private Timer reloadTimer = new Timer();
	public FontManagerModule() {
		super("FontManager", Keyboard.KEY_NONE, Category.RENDER);
		this.setToggled(true);
	}
	
	
	@Override
	public void setup() {
		ArrayList<String> options = new ArrayList<>();
		options.add("Custom");
		options.add("ProductSans");
		options.add("Miyomura");
		options.add("Vanilla");
		Rising.instance.settingsManager.rSetting(new Setting("Font", this, "ProductSans", options));
	}
	
	public void onUpdate() {
        if(this.isToggled()) {
            String font = Rising.instance.settingsManager.getSettingByName("Font").getValString();
            
            if (!font.equals(previousFont)) { // Check if font has changed
                previousFont = font; // Update previousFont
                this.setName("FontManager");
                this.setToggled(false);
                //mc.refreshResources();
                }
                
        }  
            
        if(!this.toggled) {
        	this.setToggled(true);
        }
        }
	
	public static String getfont() {
		return Rising.instance.settingsManager.getSettingByName("Font").getValString();
	};
	public static String getname() {
	    for (Module m : ModuleManager.getModulesbyCategory(Category.RENDER)) {
	        if (m.getName().startsWith("FontManager")) {
	            return m.getName();
	        }
	    }
	    return null; 
	}


}
