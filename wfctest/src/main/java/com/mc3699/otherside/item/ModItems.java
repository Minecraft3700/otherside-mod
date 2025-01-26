package com.mc3699.otherside.item;


import com.mc3699.otherside.Otherside;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems
{

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Otherside.MODID);

    public static final RegistryObject<Item> TELEPORT_ITEM = ITEMS.register("teleporter", () -> new TeleporterItem(new Item.Properties()));



    public static void register(IEventBus modEventBus)
    {
        ITEMS.register(modEventBus);
    }

}
