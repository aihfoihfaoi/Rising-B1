package me.Axolotelgamer30.Rising.module.combat;

import org.lwjgl.input.Keyboard;

import me.Axolotelgamer30.Rising.events.packet.PacketReceive;
import me.Axolotelgamer30.Rising.module.Category;
import me.Axolotelgamer30.Rising.module.Module;
import net.minecraft.network.play.server.S12PacketEntityVelocity;

public class Velo extends Module{

	public Velo() {
		super("Velocity", Keyboard.KEY_NONE, Category.COMBAT);
	}
	public void onEvent(PacketReceive e) {
		System.out.print(e.getPacket().toString());
		if(e.getPacket() instanceof S12PacketEntityVelocity) {
			if(this.toggled) {
				
				e.setCancelled(true);
			}
		}
		
	}

}
