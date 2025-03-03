package me.Axolotelgamer30.Rising.module;

import me.Axolotelgamer30.Rising.Rising;
import me.Axolotelgamer30.Rising.Notifications.Notification;
import me.Axolotelgamer30.Rising.Notifications.NotificationManager;
import me.Axolotelgamer30.Rising.Notifications.NotificationType;
import me.Axolotelgamer30.Rising.events.Event;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.multiplayer.PlayerControllerMP;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.network.Packet;

public class Module {
	
	protected Minecraft mc = Minecraft.getMinecraft();

	public String name;
	private int key;
	public boolean toggled;
	Category category;
	
	public Module(String nm, int k, Category c) {
		name = nm;
		key = k;
		category = c;
		toggled = false;
		setup();
		
	}
	
	public void toggle() {
		toggled = !toggled;
		if(toggled) {
			onEnable();
		}else {
			onDisable();
		}
		
	}
	public void onEvent(Event e) {}
	public void onEnable() {
		NotificationManager.show(new Notification(NotificationType.INFO, "Enabled!", getName() + " Enabled!", 1));
	}
	public void onDisable() {
		NotificationManager.show(new Notification(NotificationType.INFO, "Disabled!", getName() + " Disabled!", 1));
	}
	public void onUpdate() {}
	public void onRender() {}
	public void setup() {}
	
	
	public Minecraft getMc() {
		return mc;
	}

	public void setMc(Minecraft mc) {
		this.mc = mc;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getKey() {
		return key;
	}

	public void setKey(int key) {
		this.key = key;
	}

	public boolean isToggled() {
		return toggled;
	}

	public void setToggled(boolean toggled) {
		this.toggled = toggled;
	}
	
	public Category getCategory() {
		return category;
	}
	
	public void setCategory(Category category) {
		this.category = category;
	}
	
	protected EntityPlayerSP player() {
		return mc.thePlayer;
	}
	
	protected PlayerControllerMP playerController() {
		return mc.playerController;
	}
	
	protected WorldClient world() {
		return mc.theWorld;
	}
	
	@SuppressWarnings("rawtypes")
	protected void sendPacket(Packet p) {
		player().sendQueue.addToSendQueue(p);
	}
	
	public void enableOnStartUp() {
		toggled = true;
		try {
			toggle();
			onEnable();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	
}
