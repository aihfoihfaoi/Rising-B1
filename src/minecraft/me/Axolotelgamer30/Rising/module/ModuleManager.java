package me.Axolotelgamer30.Rising.module;

import java.util.ArrayList;
import java.util.List;

import me.Axolotelgamer30.Rising.Rising;
import me.Axolotelgamer30.Rising.events.Event;
import me.Axolotelgamer30.Rising.events.listeners.EventChat;
import me.Axolotelgamer30.Rising.module.combat.*;
import me.Axolotelgamer30.Rising.module.movement.*;
import me.Axolotelgamer30.Rising.module.player.*;
import me.Axolotelgamer30.Rising.module.render.*;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;

public class ModuleManager {
	
	private static ArrayList<Module> mods;
	
	public ModuleManager() {
		mods = new ArrayList<Module>();
		newMod(new TabGUI());
		//COMBAT
		newMod(new Aura());
		newMod(new Velo());
		//MOVEMENT
		newMod(new Fly());
		newMod(new Scaffold());
		newMod(new Vulcan_Glide());
		newMod(new Sprint());
		newMod(new Speed());
		newMod(new NoFall());
		newMod(new VClip());
		newMod(new FastFall());
		//newMod(new Eagle());
		//PLAYER
		newMod(new BedBreaker());
		newMod(new AntiCobweb());
		newMod(new AutoRespawn());
		newMod(new FastPlace());
		newMod(new Stealer());
		newMod(new AutoLogin());
		newMod(new Test());
//		newMod(new CapeModule__Rising());
		//RENDER
		newMod(new ClickGui());
		newMod(new FontManagerModule());
		newMod(new FPSBooster());
		//MISC
		
	}
	
	public static List<Module> getModulesbyCategory(Category c){
		List<Module> modules = new ArrayList<Module>();
		
		for(Module m : Rising.instance.moduleManager.getModules()) {
			if(m.category == c) {
				modules.add(m);
			}
		}
		return modules;
	}
	public static void newMod(Module m) {
		mods.add(m);
	}
	
	public static ArrayList<Module> getModules(){
		return mods;
	}
	
	
	
	public static void onUpdate() {
		for(Module m : mods) {
			m.onUpdate();
		}
	}
	public static void onRender() {
		for(Module m : mods) {
			m.onRender();
			}
		}
	
	public static void onKey(int k) {
		for(Module m : mods) {
			if(m.getKey() == k) {
				m.toggle();
			}
		}
	}
	public void addChatMessage(String message) {
		message = "\u00A75" + Rising.name + "\u00A7f" + ": " + "\u00A73" + message;
		Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(message));
	}
	
	public static Module getModuleByName(String moduleName) {
		for(Module m : Rising.instance.moduleManager.getModules()) {
			if(!m.getName().trim().equalsIgnoreCase(moduleName) && !m.toString().equalsIgnoreCase(moduleName.trim())) continue;
			return m;
		}
		return null;
		
	}
}
