package com.furreddraco.stacking_potions;

import com.furreddraco.stacking_potions.config.StackingPotionsCommonConfig;
import com.mojang.logging.LogUtils;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.PotionItem;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;
import org.slf4j.Logger;

import java.lang.reflect.Field;

@Mod("stacking_potions")
public class StackingPotions
{
    private static final Logger LOGGER = LogUtils.getLogger();

    public StackingPotions()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        modEventBus.addListener(this::commonSetup);

        MinecraftForge.EVENT_BUS.register(this);

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, StackingPotionsCommonConfig.SPEC, "stacking_potions.toml");
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
        event.enqueueWork(() -> {
            for (Item item : ForgeRegistries.ITEMS) {
                if (item instanceof PotionItem) {
                    try {
                        Field stackSizeField = Item.class.getDeclaredField("f_41370_");
                        stackSizeField.setAccessible(true);
                        stackSizeField.set(item, StackingPotionsCommonConfig.POTION_STACKING_LIMIT.get());
                        LOGGER.info("stacking_potions: stack size for potion " + item.getDescriptionId() + "changed to " + StackingPotionsCommonConfig.POTION_STACKING_LIMIT.get());
                    } catch (Exception ex) {
                        LOGGER.error("stacking_potions: " + ex);
                    }
                }
            }
        });
    }
}

