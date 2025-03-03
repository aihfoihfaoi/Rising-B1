package me.Axolotelgamer30.Rising.Font;

import me.Axolotelgamer30.Rising.module.render.FontManagerModule;
import me.Axolotelgamer30.Rising.utils.ColorUtils;
import me.Axolotelgamer30.Rising.utils.Wrapper;

public class FontWrapper {
	public static void drawTextWithFont(String text, float x, float y, int color) {
		String a = FontManagerModule.getfont();
		if(a == "Custom") {
			FontManager.Custom20.getFont().drawStringWithShadow(text, x, y ,color);
		}
		if(a == "ProductSans") {
			FontManager.ProductSans20.getFont().drawStringWithShadow(text, x, y, color);
		}
		if(a == "Miyomura") {
			FontManager.Miyomura20.getFont().drawStringWithShadow(text, x, y, color);
		}
		if(a == "Vanilla") {
			Wrapper.fr.drawString(text, x, y+2, color);
		}
	}
}
