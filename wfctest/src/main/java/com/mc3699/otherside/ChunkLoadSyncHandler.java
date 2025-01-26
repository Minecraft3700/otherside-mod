package com.mc3699.otherside;


import com.mc3699.otherside.util.OthersideSyncTicket;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.level.ChunkEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Otherside.MODID)
public class ChunkLoadSyncHandler {

    public static ResourceKey<Level> othersideKey = ResourceKey.create(Registries.DIMENSION, new ResourceLocation("otherside","otherside"));

    // OVERWORLD TO OTHERSIDE

    @SubscribeEvent
    public static void onOverworldChunkLoad(ChunkEvent.Load event)
    {
        Level level = (Level) event.getLevel();

        if(level.dimension().equals(Level.OVERWORLD) && level instanceof ServerLevel overworld)
        {
            ServerLevel othersideLevel = overworld.getServer().getLevel(othersideKey);
            ChunkPos chunkPos = event.getChunk().getPos();

            if(othersideLevel != null)
            {
                othersideLevel.getChunkSource().addRegionTicket(
                        OthersideSyncTicket.OTHERSIDE_TICKET,
                        chunkPos,
                        0,
                        chunkPos
                );
            }
        }
    }

    @SubscribeEvent
    public static void onOverworldChunkUnload(ChunkEvent.Unload event)
    {
        Level level = (Level) event.getLevel();

        if(level.dimension().equals(Level.OVERWORLD) && level instanceof ServerLevel overworld)
        {
            ServerLevel othersideLevel = overworld.getServer().getLevel(othersideKey);
            ChunkPos chunkPos = event.getChunk().getPos();

            if(othersideLevel != null)
            {
                othersideLevel.getChunkSource().removeRegionTicket(
                        OthersideSyncTicket.OTHERSIDE_TICKET,
                        chunkPos,
                        0,
                        chunkPos
                );
            }
        }
    }


    // OTHERSIDE TO OVERWORLD


    @SubscribeEvent
    public static void onOthersideChunkLoad(ChunkEvent.Load event)
    {
        Level level = (Level) event.getLevel();

        if(level.dimension().equals(othersideKey) && level instanceof ServerLevel serverLevel)
        {
            ServerLevel overworldLevel = serverLevel.getServer().getLevel(Level.OVERWORLD);
            ChunkPos chunkPos = event.getChunk().getPos();

            if(overworldLevel != null)
            {
                overworldLevel.getChunkSource().addRegionTicket(
                        OthersideSyncTicket.OTHERSIDE_TICKET,
                        chunkPos,
                        0,
                        chunkPos
                );
            }
        }

    }

    @SubscribeEvent
    public static void onOthersideChunkUnload(ChunkEvent.Unload event)
    {
        Level level = (Level) event.getLevel();

        if(level.dimension().equals(othersideKey) && level instanceof ServerLevel serverLevel)
        {
            ServerLevel overworldLevel = serverLevel.getServer().getLevel(Level.OVERWORLD);
            ChunkPos chunkPos = event.getChunk().getPos();

            if(overworldLevel != null)
            {
                overworldLevel.getChunkSource().removeRegionTicket(
                        OthersideSyncTicket.OTHERSIDE_TICKET,
                        chunkPos,
                        0,
                        chunkPos
                );
            }
        }
    }


}
