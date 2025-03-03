package ionsphere.clickgui.options;

import java.awt.Color;

import ionsphere.clickgui.ClickGUI;
import ionsphere.clickgui.settings.Setting;
import me.Axolotelgamer30.Rising.Font.FontWrapper;
import me.Axolotelgamer30.Rising.module.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;

public class CheckBox extends Component {
    public CheckBox(double x, double y, ClickGUI parent, Module module, Setting setting) {
        this.x = x;
        this.y = y;
        this.parent = parent;
        this.module = module;
        this.setting = setting;
    }
    
    @Override
    public void drawScreen(int mouseX, int mouseY) {
        super.drawScreen(mouseX, mouseY);
        Gui.drawRect(parent.posX + x - 70 -10, parent.posY + y, parent.posX + x + 10 - 70 - 10, parent.posY + y + 10,setting.getValBoolean() ? new Color(120,120,120).getRGB() : new Color(30,30,30).getRGB());
        //Minecraft.getMinecraft().fontRendererObj.drawString(setting.getName(), (int)(parent.posX + x - 55 - 10), (int)(parent.posY + y + 1), new Color(200,200,200).getRGB());
        FontWrapper.drawTextWithFont(setting.getName(), (int)(parent.posX + x - 55 - 10), (int)(parent.posY + y + 1), new Color(200,200,200).getRGB());
    }
    
    @Override
    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        super.mouseClicked(mouseX, mouseY, mouseButton);
        if (isInside(mouseX, mouseY, parent.posX + x - 70 - 10, parent.posY + y, parent.posX + x + 10 - 70 - 10, parent.posY + y + 10) && mouseButton == 0)
        	setting.setValBoolean(!setting.getValBoolean());
    }
}
