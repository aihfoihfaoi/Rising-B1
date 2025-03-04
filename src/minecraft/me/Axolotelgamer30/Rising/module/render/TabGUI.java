package me.Axolotelgamer30.Rising.module.render;

import java.util.List;

import org.lwjgl.input.Keyboard;

import me.Axolotelgamer30.Rising.Rising;
import me.Axolotelgamer30.Rising.module.Category;
import me.Axolotelgamer30.Rising.module.Module;
import me.Axolotelgamer30.Rising.utils.ColorUtils;
import me.Axolotelgamer30.Rising.utils.Wrapper;
import net.minecraft.client.gui.Gui;

public class TabGUI extends Module {

	public int currentTab;
	public boolean expanded;

	public TabGUI() {
		super("TabGui", 0, Category.RENDER);
		this.setToggled(true);
	}
	public void onDisable() {
		this.setToggled(true);
	}

	public void draw() {
		if (this.isToggled()) {
			Gui.drawRect(5, 20, 60, 90, 0x90000000);
			Gui.drawRect(8, 23 + currentTab * 13, 7 + 50, 34 + currentTab * 13,
					ColorUtils.rainbowEffect(123 + 1 * 200000000L, 1.0f).getRGB()); // 0xff470275

			int count = 0;

			for (Category c : Category.values()) {
				Wrapper.fr.drawString(c.name(), 10, 25 + count * 13, -1);

				count++;
			}

			if (expanded) {
				Category category = Category.values()[currentTab];
				List<Module> modules = Rising.instance.moduleManager.getModulesbyCategory(category);

				if (modules.size() == 0) {
					return;
				}
				Gui.drawRect(60, 20, 60 + 60, Rising.instance.moduleManager.getModulesbyCategory(category).size()*16, 0x90000000);
				Gui.drawRect(60, 23 + category.moduleIndex * 13, 7 + 50 + 60, 34 + category.moduleIndex*13,ColorUtils.rainbowEffect(123 + 1 * 200000000L, 1.0f).getRGB()); // 0xff470275

				count = 0;
				for (Module m : modules) {
					Wrapper.fr.drawStringWithShadow(m.name, 63, 25 + count * 13, -1);

					count++;
				}
			}

		}

	}

	public void keyPressed(int k) {
		Category category = Category.values()[currentTab];
		List<Module> modules = Rising.instance.moduleManager.getModulesbyCategory(category);
		switch (k) {

		case Keyboard.KEY_UP:
			if (expanded) {
				if (category.moduleIndex <= 0) {
					category.moduleIndex = modules.size() - 1;
				} else
					category.moduleIndex--;
			} else {
				if (currentTab <= 0) {
					currentTab = Category.values().length - 1;
				} else
					currentTab--;
			}

			break;

		case Keyboard.KEY_DOWN:
			if (expanded) {
				if (category.moduleIndex >= modules.size() - 1) {
					category.moduleIndex = 0;
				} else
					category.moduleIndex++;
			} else {
				if (currentTab >= Category.values().length - 1) {
					currentTab = 0;
				} else
					currentTab++;
			}

			break;

		case Keyboard.KEY_RIGHT:
			if (expanded && modules.size() != 0) {
				Module module = modules.get(category.moduleIndex);

				if (!module.name.equals("tabGui")) {
					module.toggle();
				}
			} else {
				if (modules.size() != 0) {
					expanded = true;
					category.moduleIndex = 0;
				}
			}

			break;

		case Keyboard.KEY_LEFT:
			expanded = false;
		}
	}

}
