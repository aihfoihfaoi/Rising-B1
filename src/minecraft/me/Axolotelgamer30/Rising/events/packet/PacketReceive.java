package me.Axolotelgamer30.Rising.events.packet;

import me.Axolotelgamer30.Rising.events.Event;
import net.minecraft.network.Packet;

public class PacketReceive extends Event {
    private Packet packet;

    public PacketReceive(Packet packet) {
        this.packet = packet;
    }

    public Packet getPacket() {
        return packet;
    }
}
