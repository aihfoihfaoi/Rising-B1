package me.Axolotelgamer30.Rising.module.player;

import org.lwjgl.input.Keyboard;

import me.Axolotelgamer30.Rising.Rising;
import me.Axolotelgamer30.Rising.events.packet.PacketReceive;
import me.Axolotelgamer30.Rising.module.Category;
import me.Axolotelgamer30.Rising.module.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.network.play.server.S02PacketChat;

public class AutoLogin extends Module {

	public AutoLogin() {
		super("Auto Auth", Keyboard.KEY_NONE, Category.PLAYER);
	}
	public void onEvent(PacketReceive event) {
		System.out.print(event.getPacket());
		
		if(Rising.instance.moduleManager.getModuleByName("AutoLogin").toggled) {
			if (event.getPacket() instanceof S02PacketChat) {
				Rising.instance.addChatMessage("chat paket");
				String message = ((S02PacketChat) event.getPacket()).getChatComponent().getUnformattedText();
				if (message.contains("/register ")) {
					Minecraft.getMinecraft().thePlayer.sendChatMessage("/register LeftBoob LeftBoob");
				} else if (message.contains("/login ")) {
					Minecraft.getMinecraft().thePlayer.sendChatMessage("/login LeftBoob LeftBoob");
				}
			}
		}
	}
	

}
