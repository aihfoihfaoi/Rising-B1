package me.Axolotelgamer30.Rising.module.movement;

import org.lwjgl.input.Keyboard;

import me.Axolotelgamer30.Rising.module.Category;
import me.Axolotelgamer30.Rising.module.Module;
import net.minecraft.network.play.client.C03PacketPlayer;

public class FastFall extends Module{

	public FastFall() {
		super("FastFall", Keyboard.KEY_NONE, Category.MOVEMENT);
	}
	
	public void onUpdate() {
		if(this.isToggled()) {
			mc.thePlayer.onGround = true;
			if(mc.thePlayer.fallDistance > 20f) {
				mc.thePlayer.motionY = -5d;
			}
			super.onUpdate();
		}
	}
	
	public void onDisable() {mc.thePlayer.motionY=0;mc.thePlayer.posY=0;mc.thePlayer.onGround=true;}
}
