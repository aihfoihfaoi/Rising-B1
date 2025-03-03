package ionsphere.clickgui.options;

import java.awt.Color;

import ionsphere.clickgui.ClickGUI;
import ionsphere.clickgui.settings.Setting;
import me.Axolotelgamer30.Rising.Font.FontWrapper;
import me.Axolotelgamer30.Rising.module.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;

public class ComboBox extends Component {
    public ComboBox(double x, double y, ClickGUI parent, Module module, Setting setting) {
        this.x = x;
        this.y = y;
        this.parent = parent;
        this.module = module;
        this.setting = setting;
    }
    
    @Override
    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        super.mouseClicked(mouseX, mouseY, mouseButton);
        if (isInside(mouseX, mouseY, parent.posX + x - 70 - 10, parent.posY + y, parent.posX + x - 10, parent.posY + y + 10) && mouseButton == 0) {
            int max = setting.getOptions().size();
            if(parent.modeIndex + 1 >= max) parent.modeIndex = 0;
            else parent.modeIndex++;
            setting.setValString(setting.getOptions().get(parent.modeIndex));
        }
    }
    
    @Override
    public void drawScreen(int mouseX, int mouseY) {
        super.drawScreen(mouseX, mouseY);
        Gui.drawRect(parent.posX + x - 70 - 10, parent.posY + y, parent.posX + x - 10 + 24, parent.posY + y + 10,setting.getValBoolean() ? new Color(230,10,230).getRGB() : new Color(30,30,30).getRGB());
        //Minecraft.getMinecraft().fontRendererObj.drawString(setting.getName() + ": " + setting.getValString(), (int)(parent.posX + x - 69 - 10), (int)(parent.posY + y + 1), new Color(200,200,200).getRGB());
        FontWrapper.drawTextWithFont(setting.getName() + ": " + setting.getValString(), (int)(parent.posX + x - 69 - 10), (int)(parent.posY + y - 2), new Color(200,200,200).getRGB());
    }
}
