package me.Axolotelgamer30.Rising.module.render;

import java.lang.reflect.Field;

import org.lwjgl.input.Keyboard;

import me.Axolotelgamer30.Rising.Rising;
import me.Axolotelgamer30.Rising.Font.FontWrapper;
import me.Axolotelgamer30.Rising.module.Category;
import me.Axolotelgamer30.Rising.module.Module;
import net.minecraft.client.Minecraft;

public class FPSBooster extends Module{

	public FPSBooster() {
		super("Reload", Keyboard.KEY_NONE, Category.RENDER);
	}
	public void onEnable() {
		this.setToggled(false);
		mc.refreshResources();
	}

}
