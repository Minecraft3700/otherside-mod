package com.mc3699.otherside.item;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.RelativeMovement;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.util.ITeleporter;

public class TeleporterItem extends Item {
    public static ResourceKey<Level> othersideKey = ResourceKey.create(Registries.DIMENSION, new ResourceLocation("otherside","otherside"));

    public TeleporterItem(Properties p_41383_) {
        super(p_41383_);
    }


    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        if(!level.isClientSide())
        {

            ServerLevel serverLevel = (ServerLevel) level;
            ServerLevel othersideLevel = serverLevel.getServer().getLevel(othersideKey);
            ServerLevel overworldLevel = serverLevel.getServer().getLevel(Level.OVERWORLD);

            //player.changeDimension(Objects.requireNonNull(serverLevel.getServer().getLevel(othersideKey)));


            if(othersideLevel != null && overworldLevel != null)
            {
                if(serverLevel.dimension().equals(Level.OVERWORLD))
                {
                    player.teleportTo(othersideLevel, player.getX(), player.getY(), player.getZ(), RelativeMovement.ALL, player.getYHeadRot(), player.getXRot());
                } else {
                    player.teleportTo(overworldLevel, player.getX(), player.getY(), player.getZ(), RelativeMovement.ALL, player.getYHeadRot(), player.getXRot());
                }
            }
            
            return InteractionResultHolder.success(player.getItemInHand(hand));
        }
        return InteractionResultHolder.fail(player.getItemInHand(hand));
    }
}
