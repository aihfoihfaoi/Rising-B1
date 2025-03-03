package me.Axolotelgamer30.Rising.gui;

import me.Axolotelgamer30.Rising.module.render.FontManagerModule;
import me.Axolotelgamer30.Rising.module.render.TabGUI;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.input.Keyboard;

import me.Axolotelgamer30.Rising.Rising;
import me.Axolotelgamer30.Rising.Font.FontManager;
import me.Axolotelgamer30.Rising.Font.FontWrapper;
import me.Axolotelgamer30.Rising.Notifications.Notification;
import me.Axolotelgamer30.Rising.Notifications.NotificationManager;
import me.Axolotelgamer30.Rising.Notifications.NotificationType;
import me.Axolotelgamer30.Rising.module.Module;
import me.Axolotelgamer30.Rising.utils.ColorUtils;
import me.Axolotelgamer30.Rising.utils.RoundedUtils;
import me.Axolotelgamer30.Rising.utils.Wrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.ResourceLocation;

public class GuiIngameHook extends GuiIngame {
	protected Minecraft mc = Minecraft.getMinecraft();

	public GuiIngameHook(Minecraft mcIn) {
		super(mcIn);
	}
	
	public void renderGameOverlay(float p_175180_1_) {
		super.renderGameOverlay(p_175180_1_);

		int nam = mc.thePlayer.getName().length();
		RoundedUtils.drawRoundedRect(2, 2, 37, 14, 3.141592653589793238462643383279502884197f, 0x80000000);
		// drawRect(2, 2, 100, 14, 0x80000000);
		// Gui.drawRect(1, 1, 80 + nam*5.1F, 3, ColorUtils.rainbowEffect(14 +
		// 24*200000000L, 1.0f).getRGB()); // 0x80ff6f00
		RoundedUtils.drawRoundedRect(1, 1, 37, 3, 3.141592653589793238462643383279502884197f,
				ColorUtils.rainbowEffect(14 + 24 * 200000000L, 1.0f).getRGB());
		// Wrapper.fr.drawString("RISING", 4, 4, 0xff6f00);

		// Wrapper.fr.drawString("FPS: " + mc.getDebugFPS(), 50, 4, 0x22C392);
		//RoundedUtils.drawRoundedRect(1, 18, 33 + nam * 5.1F, 30, 3.141592653589793238462643383279502884197f, 0x80000000);
		// Wrapper.fr.drawString("IGN: " + mc.thePlayer.getName(), 4, 20, 0x22C392);
		String a = FontManagerModule.getfont();
		FontWrapper.drawTextWithFont("RISING", 3, 3, 0xff6f00);
		//FontWrapper.drawTextWithFont("IGN: " + mc.thePlayer.getName(), 4, 19, 0x22C392);
		renderArrayList();
		renderCustomHotbar();
		render_rat();
		
	}

	private void renderCustomHotbar() {
		ScaledResolution sr = new ScaledResolution(mc);
		Gui.drawRect(0, sr.getScaledHeight() - 24, sr.getScaledWidth(), sr.getScaledHeight(), 0x20000000);
		FontWrapper.drawTextWithFont("FPS : " + mc.getDebugFPS(), 0, sr.getScaledHeight()-20, 0x2feb09);
	}

	private void renderArrayList() {
		int yCount = 0;
		int index = 0;
		long x = 0;
		for (Module m : Rising.instance.moduleManager.getModules()) {
			m.onRender();
			ScaledResolution sr = new ScaledResolution(mc);
			double offset = yCount * (Wrapper.fr.FONT_HEIGHT + 6);

			if (m.isToggled()) {
				String a = FontManagerModule.getfont();
				// Gui.drawRect(sr.getScaledWidth() - Wrapper.fr.getStringWidth(m.getName()) -
				// 15, offset, sr.getScaledWidth(), 6 + Wrapper.fr.FONT_HEIGHT + offset,
				// 0x80000000);

				// RenderUtils.drawRect(5, height * 2 - 5, maxItemWidth + 15 + 5, height *
				// (items.size() + 2) - 5, new Color(0, 0, 0, 130).getRGB());

				if (!(m.getName().contains("CapeManager"))) {
					FontWrapper.drawTextWithFont("" + m.getName(),
							sr.getScaledWidth() - Wrapper.fr.getStringWidth(m.getName()) - 13, (float) (4 + offset),
							ColorUtils.rainbowEffect(index + x * 200000000L, 1.0f).getRGB());
				}

				// Wrapper.fr.drawString("+ " + m.getName(), 5, yCount + 5,
				// ColorUtils.rainbowEffect(index + x*2000000000L, 1.0F).getRGB());
				yCount++;
				index++;
				x++;
			}
		}
	}
	
	public void render_rat() {
		ScaledResolution sr = new ScaledResolution(mc);
		mc.getTextureManager().bindTexture(new ResourceLocation("Rising/RAT.png"));
		drawModalRectWithCustomSizedTexture(0, 150, 10, 10, 130, 90, 150, 100);
	}

}
