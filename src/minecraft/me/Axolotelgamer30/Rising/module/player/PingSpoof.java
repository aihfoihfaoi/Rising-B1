package me.Axolotelgamer30.Rising.module.player;

import me.Axolotelgamer30.Rising.Rising;
import me.Axolotelgamer30.Rising.events.packet.PacketReceive;
import me.Axolotelgamer30.Rising.events.packet.PacketSent;
import net.minecraft.client.Minecraft;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.client.C01PacketChatMessage;
import net.minecraft.network.play.server.S02PacketChat;
import net.minecraft.network.play.server.S12PacketEntityVelocity;

public class PingSpoof {

	protected Minecraft mc;

	public void onEnable() {

	}

	public final static void onSend(PacketSent event) {
		//System.out.print("sent");
		Rising.onEvent(event);
	}

	public final static void onReceive(PacketReceive event) {
		//System.out.print("recived");
		Rising.onEvent(event);
		
		
	}

}