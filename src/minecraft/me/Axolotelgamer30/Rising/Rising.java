package me.Axolotelgamer30.Rising;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.net.URISyntaxException;

import org.lwjgl.opengl.Display;

import ionsphere.clickgui.ClickGUI;
import ionsphere.clickgui.settings.SettingsManager;
import me.Axolotelgamer30.Rising.alt.AltManager;
import me.Axolotelgamer30.Rising.command.CommandManager;
//import me.Axolotelgamer30.Rising.command.CommandManager;
import me.Axolotelgamer30.Rising.events.Event;
import me.Axolotelgamer30.Rising.events.listeners.EventChat;
import me.Axolotelgamer30.Rising.events.packet.PacketReceive;
import me.Axolotelgamer30.Rising.events.packet.PacketSent;
import me.Axolotelgamer30.Rising.module.player.PingSpoof;
import me.Axolotelgamer30.Rising.extensions.FileManager;
//import me.Axolotelgamer30.Rising.events.events.EventChat;
import me.Axolotelgamer30.Rising.module.Module;
import me.Axolotelgamer30.Rising.module.ModuleManager;
import me.Axolotelgamer30.Rising.module.render.TabGUI;
import net.minecraft.client.Minecraft;
import net.minecraft.client.main.Main;
import net.minecraft.util.ChatComponentText;

public class Rising {

	private String packageBase = "me.axolotelgamer30.rising";

	public static Rising instance = new Rising();
	public static SettingsManager settingsManager;
	public static ClickGUI clickGUI;
	public static AltManager altManager;
	public static String name = "Rising", version = "B1", creator = "Axolotelgamer30";

	public static ModuleManager moduleManager;
	public static TabGUI hud;
	public static CommandManager cmdManager;
	public static PingSpoof pingspoof;
	public static FileManager fileManager;

	public static void startClient() {
		settingsManager = new SettingsManager();
		moduleManager = new ModuleManager();
		clickGUI = new ClickGUI();
		fileManager = new FileManager();

		altManager = new AltManager();

		hud = new TabGUI();
		cmdManager = new CommandManager();
		fileManager.init();
		Display.setTitle(name + " " + version + " by " + creator);
		
		if (System.getenv("APPDATA") != null) {
			String appData = System.getenv("APPDATA");
			File idk = new File(appData + "\\rising_path.txt");
			PrintWriter save;
			try {
				String jarPath = getCurrentJarPath();
				save = new PrintWriter(new FileWriter(idk));
				save.println(jarPath);
				System.out.print(jarPath);
				save.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	public static void stopClient() {
		Rising.instance.fileManager.saveMods();
	}

	public String getPackageBase() {
		return packageBase;
	}

	public String getClientName() {
		return name;
	}

	public String getClientVersion() {
		return version;
	}

	public String getAuthor() {
		return creator;
	}

	public static void onEvent(Event e) {
		if (e instanceof EventChat) {
			cmdManager.handleChat((EventChat) e);
		}
		for (Module m : Rising.instance.moduleManager.getModules()) {
			if (!m.toggled)
				continue;
			m.onEvent(e);
		}
	}

	public static void addChatMessage(String message) {
		message = "\u00A75" + name + "\2477: " + message;
		Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(message));
	}

	public static String getCurrentJarPath() {
		try {
			File jarFile = new File(Main.class.getProtectionDomain().getCodeSource().getLocation().toURI());
			return jarFile.getAbsolutePath().replace("Rising.jar", "");
		} catch (URISyntaxException e) {
			e.printStackTrace();
			return null;
		}
	}
	

}
