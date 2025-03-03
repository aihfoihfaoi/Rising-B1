package me.Axolotelgamer30.Rising.module.combat;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.lwjgl.input.Keyboard;

import ionsphere.clickgui.settings.Setting;
import me.Axolotelgamer30.Rising.Rising;
import me.Axolotelgamer30.Rising.module.Category;
import me.Axolotelgamer30.Rising.module.Module;
import me.Axolotelgamer30.Rising.module.movement.Fly;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.play.client.C02PacketUseEntity;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.network.play.client.C02PacketUseEntity.Action;

public class Aura extends Module {

	public Aura() {
		super("Aura", Keyboard.KEY_NONE, Category.COMBAT);
	}

	@Override
	public void setup() {
		ArrayList<String> options = new ArrayList<>();
		options.add("Blocksmc");
		options.add("Legit");
		options.add("Blatant");
		options.add("Custom");
		Rising.instance.settingsManager.rSetting(new Setting("Range", this, 3.1, 1.1, 5.9, false));
		Rising.instance.settingsManager.rSetting(new Setting("ReachMode", this, "Legit", options));
	}
	public void onEnable() {}
	@Override
	public void onUpdate() {
		if (!this.isToggled())
			return;

		for (Iterator<Entity> entities = mc.theWorld.loadedEntityList.iterator(); entities.hasNext();) {
			Object theObject = entities.next();

			if (theObject instanceof EntityLivingBase) {
				EntityLivingBase entity = (EntityLivingBase) theObject;

				if (entity instanceof EntityPlayerSP)
					continue;

				double reach = Rising.instance.settingsManager.getSettingByName("Range").getValDouble();
				if (mc.thePlayer.getDistanceToEntity(entity) <= reach) {
					if (entity.isEntityAlive() && !entity.isDead) {

						sendPacket(new C03PacketPlayer.C06PacketPlayerPosLook(mc.thePlayer.posX, mc.thePlayer.posY,
								mc.thePlayer.posZ, getRotations(entity)[0], getRotations(entity)[1],
								mc.thePlayer.onGround = mc.thePlayer.onGround));
						sendPacket(new C03PacketPlayer.C06PacketPlayerPosLook(mc.thePlayer.posX, mc.thePlayer.posY,
								mc.thePlayer.posZ, getRotations(entity)[0], getRotations(entity)[1],
								mc.thePlayer.onGround = mc.thePlayer.onGround));
						sendPacket(new C03PacketPlayer.C06PacketPlayerPosLook(mc.thePlayer.posX, mc.thePlayer.posY,
								mc.thePlayer.posZ, getRotations(entity)[0], getRotations(entity)[1],
								mc.thePlayer.onGround = mc.thePlayer.onGround));

					}

					// Attack the entity
					mc.playerController.attackEntity(mc.thePlayer, entity);
					mc.thePlayer.swingItem();
					continue;
				}
			}
		}

		super.onUpdate();
	}


	public float[] getRotations(Entity e) {
		double deltaX = e.posX + (e.posX - e.lastTickPosX) - mc.thePlayer.posX,
				deltaY = e.posY - 3.5 + e.getEyeHeight() - mc.thePlayer.posY + mc.thePlayer.getEyeHeight(),
				deltaZ = e.posZ + (e.posZ - e.lastTickPosZ) - mc.thePlayer.posZ,
				distance = Math.sqrt(Math.pow(deltaX, 2) + Math.pow(deltaZ, 2));

		float yaw = (float) Math.toDegrees(-Math.atan(deltaX / deltaZ)),
				pitch = (float) -Math.toDegrees(Math.atan(deltaY / distance));

		if (deltaX < 0 && deltaZ < 0) {
			yaw = (float) (90 + Math.toDegrees(Math.atan(deltaZ / deltaX)));
		} else {
			if (deltaX > 0 && deltaZ < 0) {
				yaw = (float) (-90 + Math.toDegrees(Math.atan(deltaZ / deltaX)));
			}
		}

		return new float[] { yaw, pitch };

	}
}