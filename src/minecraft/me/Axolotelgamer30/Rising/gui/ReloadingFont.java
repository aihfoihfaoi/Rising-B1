package me.Axolotelgamer30.Rising.gui;


import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;


public class ReloadingFont extends GuiScreen {
	public void drawScreen(int mouseX, int mouseY, float partialticks) {
		this.drawBackground(23);
		this.drawString(fontRendererObj, "Reloading Font", 150, 100, 34433434);
		this.drawString(fontRendererObj, "Reloading Font",450, 250, 34433434);
	}
}
