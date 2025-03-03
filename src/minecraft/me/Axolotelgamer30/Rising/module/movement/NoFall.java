package me.Axolotelgamer30.Rising.module.movement;

import org.lwjgl.input.Keyboard;

import me.Axolotelgamer30.Rising.module.Category;
import me.Axolotelgamer30.Rising.module.Module;
import net.minecraft.network.play.client.C03PacketPlayer;

public class NoFall extends Module{

	public NoFall() {
		super("Nofall", Keyboard.KEY_NONE, Category.MOVEMENT);
	}
	
	public void onUpdate() {
		if(this.isToggled()) {
			if(mc.thePlayer.fallDistance > 2f) {
				mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer(true));
			}
			super.onUpdate();
		}
	}
}
