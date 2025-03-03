package me.Axolotelgamer30.Rising.module.player;

import org.lwjgl.input.Keyboard;

import ionsphere.clickgui.settings.Setting;
import me.Axolotelgamer30.Rising.Rising;
import me.Axolotelgamer30.Rising.module.Category;
import me.Axolotelgamer30.Rising.module.Module;
import me.Axolotelgamer30.Rising.utils.Timer_stealer;
import net.minecraft.inventory.ContainerChest;
import net.minecraft.inventory.Slot;



public class Stealer extends Module{

	public Stealer() {
		super("Stealer", Keyboard.KEY_NONE, Category.PLAYER);
	}
	int delay;
	
	Timer_stealer timer = new Timer_stealer();

	
	@Override
	public void setup() {
		Rising.instance.settingsManager.rSetting(new Setting("delay", this, 1, 0, 10, true));
	}
	
	
	@Override
	public void onUpdate() {
		if(this.isToggled()) {
			int delay1 = (int) Rising.instance.settingsManager.getSettingByName("delay").getValDouble();
			if (delay1 == 0) {
				delay = 0;
			}
			if(delay1 != 0) {
				delay = delay1*100;
			}
			
			if(mc.thePlayer.openContainer != null){
				if(mc.thePlayer.openContainer instanceof ContainerChest){
					ContainerChest chest = (ContainerChest) mc.thePlayer.openContainer;
					int i;
					for(i = 0; i<chest.numRows*9;i++){
						if(mc.thePlayer.openContainer == null){
							break;
						}
						Slot slot =(Slot)chest.inventorySlots.get(i);
						if(slot.getStack() == null)
							continue;
						if(!timer.check((float)(delay == 0 ? 1 : delay))){
							return;
						}
						if(timer.check((float) delay)) {
							mc.playerController.windowClick(chest.windowId, i, 0, 1, mc.thePlayer);
							timer.reset();
						}
					
					}
					mc.thePlayer.closeScreen();
				}
			}
		}
	}
}