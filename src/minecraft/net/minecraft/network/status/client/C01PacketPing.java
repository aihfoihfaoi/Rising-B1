package net.minecraft.network.status.client;

import java.io.IOException;

import me.Axolotelgamer30.Rising.Rising;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.status.INetHandlerStatusServer;

public class C01PacketPing implements Packet<INetHandlerStatusServer>
{
    private long clientTime;

    public C01PacketPing()
    {
    }

    public C01PacketPing(long ping)
    {
        this.clientTime = ping;
    }

    /**
     * Reads the raw packet data from the data stream.
     */
    public void readPacketData(PacketBuffer buf) throws IOException
    {
        this.clientTime = buf.readLong();
    }

    /**
     * Writes the raw packet data to the data stream.
     */
    public void writePacketData(PacketBuffer buf) throws IOException
    {
        buf.writeLong(this.clientTime);
    }

    /**
     * Passes this Packet on to the NetHandler for processing.
     */
    public void processPacket(INetHandlerStatusServer handler)
    {
    	handler.processPing(this);
    }

    public long getClientTime()
    {
        return this.clientTime;
    }
}
