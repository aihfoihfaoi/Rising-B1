package ionsphere.clickgui.settings;

import java.util.ArrayList;

import me.Axolotelgamer30.Rising.module.Module;



public class Setting {
	private String name, mode, sval;
	private Module parent;
	private ArrayList<String> options;
	private boolean bval, onlyint = false;
	private double dval, min, max;
	
	public Setting(String name, Module parent, String sval, ArrayList<String> options){
		this.name = name;
		this.parent = parent;
		this.sval = sval;
		this.options = options;
		this.mode = "Combo";
	}
	
	public Setting(String name, Module parent, boolean bval){
		this.name = name;
		this.parent = parent;
		this.bval = bval;
		this.mode = "Check";
	}
	
	public Setting(String name, Module parent, double dval, double min, double max, boolean onlyint){
		this.name = name;
		this.parent = parent;
		this.dval = dval;
		this.min = min;
		this.max = max;
		this.onlyint = onlyint;
		this.mode = "Slider";
	}
	
	public String getName(){ return name; }
	public Module getParentMod(){ return parent; }
	public String getValString(){ return this.sval; }
	public void setValString(String in){ this.sval = in; }
	public ArrayList<String> getOptions(){ return this.options; }
	public boolean getValBoolean(){ return this.bval; }
	public void setValBoolean(boolean in){ this.bval = in; }
	public double getValDouble(){
		if(this.onlyint)
			this.dval = (int)dval;
		return this.dval;
	}

	public void setValDouble(double in){ this.dval = in; }
	public double getMin(){ return this.min; }
	public double getMax(){ return this.max; }
	public boolean isCombo(){ return this.mode.equalsIgnoreCase("Combo") ? true : false; }
	public boolean isCheck(){ return this.mode.equalsIgnoreCase("Check") ? true : false; }
	public boolean isSlider(){ return this.mode.equalsIgnoreCase("Slider") ? true : false; }
	public boolean onlyInt(){ return this.onlyint; }
}
