package me.Axolotelgamer30.Rising.module.player;

import me.Axolotelgamer30.Rising.Rising;
import me.Axolotelgamer30.Rising.events.packet.PacketReceive;
import me.Axolotelgamer30.Rising.events.packet.PacketSent;
import me.Axolotelgamer30.Rising.module.Category;
import me.Axolotelgamer30.Rising.module.Module;

public class Test extends Module{

	public Test() {
		super("test", 0, Category.PLAYER);
		// TODO Auto-generated constructor stub
	}
	
	public void onEvent(PacketSent event) {
		Rising.instance.addChatMessage("" + event.getPacket()); 
	}

}
