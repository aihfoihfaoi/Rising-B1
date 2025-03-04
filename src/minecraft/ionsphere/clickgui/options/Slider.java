package ionsphere.clickgui.options;

import java.awt.Color;
import java.math.BigDecimal;
import java.math.RoundingMode;

import ionsphere.clickgui.ClickGUI;
import ionsphere.clickgui.settings.Setting;
import me.Axolotelgamer30.Rising.Font.FontWrapper;
import me.Axolotelgamer30.Rising.module.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;

public class Slider extends Component {
    private boolean dragging = false;
    private double renderWidth, renderWidth2;
    
    public Slider(double x, double y, ClickGUI parent, Module module, Setting setting) {
        this.x = x;
        this.y = y;
        this.parent = parent;
        this.module = module;
        this.setting = setting;
    }
    
    @Override
    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        super.mouseClicked(mouseX, mouseY, mouseButton);
        if (isInside(mouseX, mouseY, parent.posX + x - 70, parent.posY + y + 10,parent.posX + x - 70 + renderWidth2, parent.posY + y + 20) && mouseButton == 0) {
            dragging = true;
        }
    }
    
    @Override
    public void mouseReleased(int mouseX, int mouseY, int state) {
        super.mouseReleased(mouseX, mouseY, state);
        dragging = false;
    }
    
    @Override
    public void drawScreen(int mouseX, int mouseY) {
        super.drawScreen(mouseX, mouseY);
        
        double min = setting.getMin();
        double max = setting.getMax();
        double l = 90;
        
        renderWidth = (l) * (setting.getValDouble() - min) / (max - min);
        renderWidth2 = (l) * (setting.getMax() - min) / (max - min);

        double diff = Math.min(l, Math.max(0, mouseX - (parent.posX + x - 70)));
        if (dragging) {
            if(diff == 0)
                setting.setValDouble(setting.getMin());
            else{
                double newValue = roundToPlace(((diff / l) * (max - min) + min), 1);
                setting.setValDouble(newValue);
            }
        }
        Gui.drawRect(parent.posX + x - 70 - 10, parent.posY + y + 10,parent.posX + x - 70 + renderWidth2 - 10, parent.posY + y + 20, Color.WHITE.darker().getRGB());
        Gui.drawRect(parent.posX + x - 70 - 10, parent.posY + y + 10, parent.posX + x - 70 + renderWidth - 10, parent.posY + y + 20, Color.WHITE.getRGB());
        //Minecraft.getMinecraft().fontRendererObj.drawString(setting.getName() + ": " + setting.getValDouble(),(int)(parent.posX + x - 70 - 10),(int)(parent.posY + y), -1);
        FontWrapper.drawTextWithFont(setting.getName() + ": " + setting.getValDouble(),(int)(parent.posX + x - 70 - 10),(int)(parent.posY + y - 4), -1);
    }
    
    private double roundToPlace(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();
        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
