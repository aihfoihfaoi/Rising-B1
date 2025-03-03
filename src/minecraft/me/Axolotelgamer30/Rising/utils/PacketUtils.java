package me.Axolotelgamer30.Rising.utils;

import net.minecraft.client.Minecraft;
import net.minecraft.network.Packet;
import net.minecraft.network.play.INetHandlerPlayServer;

import java.util.ArrayList;

public class PacketUtils {
    private static final Minecraft mc = Minecraft.getMinecraft();
    private static final ArrayList<Packet<INetHandlerPlayServer>> packets = new ArrayList<>();

    // Method to handle sending a packet without triggering events
    public static void sendPacketNoEvent(Packet<INetHandlerPlayServer> packet) {
        packets.add(packet);
        mc.getNetHandler().getNetworkManager().sendPacket(packet);
    }

}
