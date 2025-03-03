package ionsphere.clickgui.settings;

import java.util.ArrayList;

import me.Axolotelgamer30.Rising.module.Module;

public class SettingsManager {
	private ArrayList<Setting> settings;
	public SettingsManager(){ this.settings = new ArrayList<>(); }
	public void rSetting(Setting in){ this.settings.add(in); }
	public ArrayList<Setting> getSettings(){ return this.settings; }
	public ArrayList<Setting> getSettingsByMod(Module mod){
		ArrayList<Setting> out = new ArrayList<>();
		for(Setting s : getSettings()) if(s.getParentMod().equals(mod)) out.add(s);
		if(out.isEmpty()) return null;
		return out;
	}
	
	public Setting getSettingByName(String name){
		for(Setting set : getSettings()){
			if(set.getName().equalsIgnoreCase(name)) return set;
		}
		System.out.println("Setting '" + name +"'not found!");
		return null;
	}
}