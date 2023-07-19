package com.furreddraco.stacking_potions.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class StackingPotionsCommonConfig {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;


    public static final ForgeConfigSpec.ConfigValue<Integer> POTION_STACKING_LIMIT;


    static {
        BUILDER.push("Config for Stacking Potions");

        POTION_STACKING_LIMIT = BUILDER.comment("The new potion stacking limit. Default is 64.")
                .defineInRange("Potion Stacking Limit", 64, 1, 64);

        BUILDER.pop();
        SPEC = BUILDER.build();
    }
}
