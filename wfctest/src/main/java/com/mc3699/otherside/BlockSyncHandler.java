package com.mc3699.otherside;


import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;


@Mod.EventBusSubscriber(modid = Otherside.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class BlockSyncHandler {

    public static ResourceKey<Level> othersideKey = ResourceKey.create(Registries.DIMENSION, new ResourceLocation("otherside","otherside"));


    @SubscribeEvent
    public static void othersideToOverworldPlace(BlockEvent.EntityPlaceEvent event)
    {
        Level level = (Level) event.getLevel();

        if(level.dimension().equals(othersideKey))
        {
            BlockPos placePos = event.getPos();
            BlockState placeState = event.getPlacedBlock();

            if(level.getServer() != null)
            {
                ServerLevel overworld = level.getServer().getLevel(Level.OVERWORLD);
                if(overworld != null)
                {
                    overworld.setBlock(placePos,placeState,3);
                }
            }
        }
    }

    @SubscribeEvent
    public static void othersideToOverworldBreak(BlockEvent.BreakEvent event)
    {
        Level level = (Level) event.getLevel();

        if(level.dimension().equals(othersideKey))
        {
            BlockPos breakPos = event.getPos();

            if(level.getServer() != null)
            {
                ServerLevel overworld = level.getServer().getLevel(Level.OVERWORLD);
                if(overworld != null)
                {
                    overworld.setBlock(breakPos, Blocks.AIR.defaultBlockState(), 3);
                }
            }
        }
    }

    @SubscribeEvent
    public static void overworldToOthersidePlace(BlockEvent.EntityPlaceEvent event)
    {
        Level level = (Level) event.getLevel();

        if(level.dimension().equals(Level.OVERWORLD))
        {
            BlockPos placePos = event.getPos();
            BlockState placeState = event.getPlacedBlock();

            if(level.getServer() != null)
            {
                ServerLevel otherside = level.getServer().getLevel(othersideKey);
                if(otherside != null)
                {
                    otherside.setBlock(placePos,placeState,3);
                }
            }
        }
    }

    @SubscribeEvent
    public static void overworldToOthersideBreak(BlockEvent.BreakEvent event)
    {
        Level level = (Level) event.getLevel();

        if(level.dimension().equals(Level.OVERWORLD))
        {
            BlockPos breakPos = event.getPos();

            if(level.getServer() != null)
            {
                ServerLevel otherside = level.getServer().getLevel(othersideKey);
                if(otherside != null)
                {
                    otherside.setBlock(breakPos, Blocks.AIR.defaultBlockState(), 3);
                }
            }
        }
    }
}
