package me.Axolotelgamer30.Rising.extensions;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Map.Entry;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import me.Axolotelgamer30.Rising.Rising;
import me.Axolotelgamer30.Rising.module.Module;
import me.Axolotelgamer30.Rising.utils.JsonUtils;

public class FileManager {
	
	public static File ROOT_DIR = new File("RISING");
	
	public static File modules = new File(ROOT_DIR, "config.json");
	
	
	public void init() {
		//makes root dir
		if(!ROOT_DIR.exists()) { ROOT_DIR.mkdirs();}
		
		//handles modules
		if(!modules.exists()) 
			saveMods();
	
		else
			loadMods();
		
	}
	
	public void saveMods() {
		try {
			JsonObject json = new JsonObject();
			for(Module mod : Rising.instance.moduleManager.getModules()) {
				if(mod.name != "TabGUI") {
					JsonObject jsonMod = new JsonObject();
					jsonMod.addProperty("Toggled", mod.isToggled());
					jsonMod.addProperty("KeyBind", mod.getKey());
					json.add(mod.getName(), jsonMod);
				}
				
		}
			PrintWriter save = new PrintWriter(new FileWriter(modules));
			save.println(JsonUtils.prettyGson.toJson(json));
			save.close();
		
	}catch (Exception e) {
		e.printStackTrace();
	}
}
	public void loadMods() {
		try {
			BufferedReader load = new BufferedReader(new FileReader(modules));
			JsonObject json = (JsonObject)JsonUtils.jsonParser.parse(load);
			load.close();
			Iterator<Entry<String, JsonElement>> itr = json.entrySet().iterator();
			while(itr.hasNext()){
				Entry<String, JsonElement> entry = itr.next();
				Module mod = Rising.instance.moduleManager.getModuleByName(entry.getKey());
				if(mod != null) {
				JsonObject jsonModule = (JsonObject)entry.getValue();
				boolean enabled = jsonModule.get("Toggled").getAsBoolean();
				int KeyBind = jsonModule.get("KeyBind").getAsInt();
				if(enabled)
					mod.toggle();
				if(!(KeyBind == 0)) {
					mod.setKey(KeyBind);
				}
				}
				
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
