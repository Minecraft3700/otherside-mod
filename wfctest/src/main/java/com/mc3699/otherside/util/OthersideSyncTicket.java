package com.mc3699.otherside.util;

import net.minecraft.server.level.TicketType;
import net.minecraft.world.level.ChunkPos;

import java.util.Comparator;

public class OthersideSyncTicket {
    public static final TicketType<ChunkPos> OTHERSIDE_TICKET = TicketType.create(
            "otherside_sync",
            Comparator.comparingLong(chunkPos -> chunkPos.toLong())
    );
}
