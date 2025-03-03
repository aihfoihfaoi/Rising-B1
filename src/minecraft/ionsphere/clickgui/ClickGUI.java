package ionsphere.clickgui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

import ionsphere.clickgui.options.CheckBox;
import ionsphere.clickgui.options.ComboBox;
import ionsphere.clickgui.options.Component;
import ionsphere.clickgui.options.Slider;
import ionsphere.clickgui.settings.Setting;
import me.Axolotelgamer30.Rising.Rising;
import me.Axolotelgamer30.Rising.Font.FontWrapper;
import me.Axolotelgamer30.Rising.module.Category;
import me.Axolotelgamer30.Rising.module.Module;
import me.Axolotelgamer30.Rising.module.render.FontManagerModule;
import me.Axolotelgamer30.Rising.utils.ColorUtils;
import me.Axolotelgamer30.Rising.utils.Wrapper;

public class ClickGUI extends GuiScreen {
	
	//Coded by V1per#8881 for TheAppearedOne#6815
	//But you can remove this!
	
    public double posX, posY, width, height, dragX, dragY;
    public Category selectedCategory;
    public Module selectedModule;
    public int modeIndex;
    public ArrayList<Component> comps = new ArrayList<>();
    public boolean dragging;
    public boolean isInside(int mouseX, int mouseY, double x, double y, double x2, double y2) { return (mouseX > x && mouseX < x2) && (mouseY > y && mouseY < y2); }
    public ScaledResolution getScaledRes() { return new ScaledResolution(Minecraft.getMinecraft()); }
    
    public ClickGUI() {
        dragging = false;
        posX = getScaledRes().getScaledWidth() / 2 - 150;
        posY = getScaledRes().getScaledHeight() / 2 - 100;
        width = posX + 150 * 2;
        height = height + 200;
        selectedCategory = Category.COMBAT;
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        super.drawScreen(mouseX, mouseY, partialTicks);
        if (dragging) {
            posX = mouseX - dragX;
            posY = mouseY - dragY;
        }
        width = posX + 150 * 2;
        height = posY + 200;
        
        //Window with Round Corners
        Gui.drawRect(posX + 1, posY - 3, width - 1, height - 70, new Color(45,71,45).getRGB());
        Gui.drawRect(posX + 2, posY - 5, width - 2, height - 70, new Color(45,71,45).getRGB());
        Gui.drawRect(posX + 3, posY - 6, width - 3, height - 70, new Color(45,71,45).getRGB());
        Gui.drawRect(posX + 4, posY - 7, width - 4, height - 70, new Color(45,71,45).getRGB());
        Gui.drawRect(posX + 5, posY - 8, width - 5, height - 70, new Color(45,71,45).getRGB());
        Gui.drawRect(posX + 6, posY - 9, width - 6, height - 70, new Color(45,71,45).getRGB());
        Gui.drawRect(posX + 8, posY - 10, width - 8, height - 70, new Color(45,71,45).getRGB());
        Gui.drawRect(posX + 1, height + 3 - 10, width - 1, height - 70, new Color(45,51,45).getRGB());
        Gui.drawRect(posX + 2, height + 5 - 10, width - 2, height - 70, new Color(45,71,45).getRGB());
        Gui.drawRect(posX + 3, height + 6 - 10, width - 3, height - 70, new Color(45,71,45).getRGB());
        Gui.drawRect(posX + 4, height + 7 - 10, width - 4, height - 70, new Color(45,71,45).getRGB());
        Gui.drawRect(posX + 5, height + 8 - 10, width - 5, height - 70, new Color(45,71,45).getRGB());
        Gui.drawRect(posX + 6, height + 9 - 10, width - 6, height - 70, new Color(45,71,45).getRGB());
        Gui.drawRect(posX + 8, height + 10 - 10, width - 8, height - 70, new Color(45,71,45).getRGB());
        
        //Settings Background
        Gui.drawRect(posX + 189, posY + 21, posX + 290, posY + 184, Color.DARK_GRAY.getRGB());
        
        //Client Text
        //fontRendererObj.drawString("Rising", (int) (posX + width) / 2 - 30, (int) posY + 2, Color.WHITE.getRGB());
        FontWrapper.drawTextWithFont("Rising", (int) (posX + width) / 2 - 30, (int) posY + 2, Color.WHITE.getRGB());
        //Draw Categories
        int offset = 20;
        for (Category category : Category.values()) {
            Gui.drawRect(posX + 10,posY + 1 + offset, posX + 70 + 17,posY + 15 + offset + 17, category.equals(selectedCategory) ? new Color(70,70,70).getRGB() : new Color(28,28,28).getRGB());
            //fontRendererObj.drawString(category.name(),(int)posX + 13, (int)(posY + 13) + offset - 1, Color.WHITE.getRGB());
            FontWrapper.drawTextWithFont(category.name(),(int)posX + 13, (int)(posY + 13) + offset - 1, Color.WHITE.getRGB());
            offset += 33;
        }
        
        offset = 20;
        
        //Draw Modules
        for (Module m : Rising.instance.moduleManager.getModulesbyCategory(selectedCategory)) {
        	if(!(m.getName() == "CapeModule") && !(m.getName() == "TabGUI")) {
        		Gui.drawRect(posX + 75 + 14 + 10,posY + 1 + offset,posX + 135 + 17 + 14 + 10,posY + 15 + offset,m.isToggled() ? new Color(90,90,90).getRGB() : new Color(28,28,28).getRGB());
        		FontWrapper.drawTextWithFont(m.getName(),(int)posX + 78 + 23, (int)(posY + 1) + offset - 1, Color.WHITE.getRGB());
        		//fontRendererObj.drawString(m.getName(),(int)posX + 78 + 23, (int)(posY + 5) + offset - 1, Color.WHITE.getRGB());
                offset += 15;
        	}
            
        }
        
        //Draw Options
        for (Component comp : comps) comp.drawScreen(mouseX, mouseY);
    }

    @Override
    protected void keyTyped(char typedChar, int keyCode) throws IOException {
        super.keyTyped(typedChar, keyCode);
        for (Component comp : comps) comp.keyTyped(typedChar, keyCode);
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        super.mouseClicked(mouseX, mouseY, mouseButton);
        if (isInside(mouseX, mouseY, posX, posY - 10, width, posY) && mouseButton == 0) {
            dragging = true;
            dragX = mouseX - posX;
            dragY = mouseY - posY;
        }
        int offset = 20;
        for (Category category : Category.values()) {
            if (isInside(mouseX, mouseY,posX + 10,posY + 1 + offset,posX + 70 + 17,posY + 15 + offset + 17) && mouseButton == 0) {
                selectedCategory = category;
            }
            offset += 33;
        }
        offset = 20;
        for (Module m : Rising.instance.moduleManager.getModulesbyCategory(selectedCategory)) {
            if (isInside(mouseX, mouseY,posX + 75 + 14 + 10,posY + 1 + offset,posX + 135 + 17 + 14 + 10,posY + 15 + offset)) {
                if (mouseButton == 0) {
                	if(!(m.name == FontManagerModule.getname())) {
                		m.toggle();
                	}
                }
                if (mouseButton == 1) {
                    int sOffset = 23;
                    comps.clear();
                    if (Rising.instance.settingsManager.getSettingsByMod(m) != null)
                    for (Setting setting : Rising.instance.settingsManager.getSettingsByMod(m)) {
                        selectedModule = m;
                        if (setting.isCombo()) {
                            comps.add(new ComboBox(275, sOffset, this, selectedModule, setting));
                            sOffset += 15;
                        }
                        if (setting.isCheck()) {
                            comps.add(new CheckBox(275, sOffset, this, selectedModule, setting));
                            sOffset += 15;
                        }
                        if (setting.isSlider()) {
                            comps.add(new Slider(275, sOffset, this, selectedModule, setting));
                            sOffset += 25;
                        }
                    }
                }
            }
            offset += 15;
        }
        for (Component comp : comps) {
            comp.mouseClicked(mouseX, mouseY, mouseButton);
        }
    }

    @Override
    protected void mouseReleased(int mouseX, int mouseY, int state) {
        super.mouseReleased(mouseX, mouseY, state);
        dragging = false;
        for (Component comp : comps) {
            comp.mouseReleased(mouseX, mouseY, state);
        }
    }

    @Override
    public void initGui() {
        super.initGui();
        dragging = false;
    }
}
