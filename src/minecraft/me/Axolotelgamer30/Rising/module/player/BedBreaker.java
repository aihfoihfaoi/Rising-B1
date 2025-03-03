package me.Axolotelgamer30.Rising.module.player;

import java.util.ArrayList;

import org.lwjgl.input.Keyboard;

import ionsphere.clickgui.settings.Setting;
import me.Axolotelgamer30.Rising.Rising;
import me.Axolotelgamer30.Rising.module.Category;
import me.Axolotelgamer30.Rising.module.Module;
import net.minecraft.init.Blocks;
import net.minecraft.network.play.client.C07PacketPlayerDigging;
import net.minecraft.network.play.client.C07PacketPlayerDigging.Action;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;

public class BedBreaker extends Module {
	private BlockPos m = null;

	public BedBreaker() {
		super("BedBreaker", Keyboard.KEY_NONE, Category.PLAYER);

	}

    @Override
    public void setup() {
		Rising.instance.settingsManager.rSetting(new Setting("Bed Range", this, 3.0, 1.0, 6.0, true));
    }


	public void onUpdate() {
		if (this.toggled) {
			double X = mc.thePlayer.posX;
			double Y = mc.thePlayer.posY;
			double Z = mc.thePlayer.posZ;
			// BlockPos p = new BlockPos(X, Y, Z);
			// if (mc.theWorld.getBlockState(p).getBlock() == Blocks.bed) {

			// }
			int ra = (int) Rising.instance.settingsManager.getSettingByName("Bed Range").getValDouble();
			for (int y = ra; y >= -ra; --y) {
				for (int x = -ra; x <= ra; ++x) {
					for (int z = -ra; z <= ra; ++z) {
						BlockPos p = new BlockPos(mc.thePlayer.posX + (double) x, mc.thePlayer.posY + (double) y,
								mc.thePlayer.posZ + (double) z);
						boolean bed = mc.theWorld.getBlockState(p).getBlock() == Blocks.bed;
						if (m == p) {
							if (!bed) {
								m = null;
							}
						} else if (bed) {
							mi(p);
							m = p;
							break;
						}
					}
				}

			}
			//this.setToggled(false);
		}

	}

	private void mi(BlockPos p) {
		mc.thePlayer.swingItem();
		mc.thePlayer.sendQueue
				.addToSendQueue(new C07PacketPlayerDigging(Action.START_DESTROY_BLOCK, p, EnumFacing.NORTH));
		mc.thePlayer.sendQueue
				.addToSendQueue(new C07PacketPlayerDigging(Action.STOP_DESTROY_BLOCK, p, EnumFacing.NORTH));
	}
}
