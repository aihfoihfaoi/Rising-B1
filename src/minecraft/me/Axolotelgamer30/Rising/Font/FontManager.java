package me.Axolotelgamer30.Rising.Font;


import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;

public enum FontManager {
    ProductSans20("Rising/fonts/productsans.ttf", 20),
	Miyomura20("Rising/fonts/Miyomura.ttf", 20),
	Custom20("Rising/fonts/custom.ttf", 20);

    private GlyphPageFontRenderer font;
    FontManager(String path, int size, boolean bold, boolean italic, boolean boldItalic) {
        try {
            this.font = GlyphPageFontRenderer.create(Minecraft.getMinecraft().getResourceManager().getResource(new ResourceLocation(path)).getInputStream(), size, bold, italic, boldItalic);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    FontManager(String path, int size) {
        try {
            this.font = GlyphPageFontRenderer.create(Minecraft.getMinecraft().getResourceManager().getResource(new ResourceLocation(path)).getInputStream(), size, false, false, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public GlyphPageFontRenderer getFont() {
        return font;
    }
}