package me.Axolotelgamer30.Rising.command.commands;

import org.lwjgl.input.Keyboard;

import me.Axolotelgamer30.Rising.Rising;
import me.Axolotelgamer30.Rising.command.Command;
import me.Axolotelgamer30.Rising.module.Module;

public class Bind extends Command {

	public Bind() {
		super("Bind", "Binds a module by name to a key", "Bind <name> <key> | clear", "b");
	}

	@Override
	public void onCommand(String[] args, String command) {
		if (args.length == 2) {
			String moduleName = args[0];
			String keyName = args[1];

			boolean foundModule = false;

			for (Module module : Rising.instance.moduleManager.getModules()) {
				if (module.name.equalsIgnoreCase(moduleName)) {
					module.setKey(Keyboard.getKeyIndex(keyName.toUpperCase()));
					
					Rising.instance.moduleManager.addChatMessage(String.format("Bound %s to %s.", module.name, Keyboard.getKeyName(module.getKey())));
					foundModule = true;
					break;
				}
			}
			if(!foundModule) {
				Rising.instance.moduleManager.addChatMessage("Module Not Found.");
			}
		}
		
		if(args.length == 1) {
			if (args[0].equalsIgnoreCase("clear")) {
				for (Module module : Rising.instance.moduleManager.getModules()) {
					if (module.name != "ClickGui")
					module.setKey(Keyboard.KEY_NONE);
				}
				Rising.instance.moduleManager.addChatMessage("All Keybinds Have Been Cleared");
			}
			if (args[0].equalsIgnoreCase("list")) {
				Rising.instance.addChatMessage("                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        ");
				for (Module m : Rising.instance.moduleManager.getModules()){
					if(m.getKey() != 0) {
						Rising.instance.moduleManager.addChatMessage(String.format("%s is bound to \u00A76 %s", m.name, Keyboard.getKeyName(m.getKey())));
					}else if(m.getKey() == 0) {
						Rising.instance.moduleManager.addChatMessage(String.format("%s is bound to \u00A74 %s", m.name, Keyboard.getKeyName(m.getKey())));
					}
				}
			}
			
		}

	}

}
