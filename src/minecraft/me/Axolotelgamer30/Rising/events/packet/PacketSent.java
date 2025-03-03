package me.Axolotelgamer30.Rising.events.packet;

import me.Axolotelgamer30.Rising.events.Event;
import net.minecraft.network.Packet;

public class PacketSent extends Event<PacketSent> {
    public Packet p;
    public PacketSent (Packet p) {
        this.p = p;
    }
    
    public Packet getPacket() {
        return this.p;
     }
}