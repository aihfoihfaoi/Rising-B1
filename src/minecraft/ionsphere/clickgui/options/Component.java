package ionsphere.clickgui.options;

import ionsphere.clickgui.ClickGUI;
import ionsphere.clickgui.settings.Setting;
import me.Axolotelgamer30.Rising.module.Module;

public class Component {
    public double x, y, x2, y2;
    public ClickGUI parent;
    public Module module;
    public Setting setting;
    
    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {}
    public void mouseReleased(int mouseX, int mouseY, int state) {}
    public void keyTyped(char typedChar, int keyCode) {}
    public void drawScreen(int mouseX, int mouseY) {}
    
    public boolean isInside(int mouseX, int mouseY, double x, double y, double x2, double y2) {
        return (mouseX > x && mouseX < x2) && (mouseY > y && mouseY < y2);
    }
}
