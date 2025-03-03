package me.Axolotelgamer30.Rising.command.commands;

import me.Axolotelgamer30.Rising.Notifications.Notification;
import me.Axolotelgamer30.Rising.Notifications.NotificationManager;
import me.Axolotelgamer30.Rising.Notifications.NotificationType;
import me.Axolotelgamer30.Rising.command.Command;

public class Notify extends Command{

	public Notify() {
		super("notify", "displays a test notify", "notify <text>", "n");
	}

	@Override
	public void onCommand(String[] args, String command) {
		NotificationManager.show(new Notification(NotificationType.INFO, "test", "a", 20));
		
		
	}

}
