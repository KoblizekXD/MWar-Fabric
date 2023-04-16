package org.mwar;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mwar.entity.grenade.GrenadeEntity;
import org.mwar.entity.grenade.SmokeGrenadeEntity;
import org.mwar.item.GrenadeItem;
import org.mwar.item.SmokeGrenadeItem;
import org.mwar.item.SmokeGrenadeItem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;

public class Mwar implements ModInitializer {
    public static final String MODID = "mwar";
    public static final ArrayList<SoundEvent> soundList = new ArrayList<>();
    public static final Logger LOGGER = LogManager.getLogger("MWar");
    public static EntityType<GrenadeEntity> GRENADE_ENTITY = Registry.register(Registries.ENTITY_TYPE,
            new Identifier(MODID, "grenade_entity"),
            FabricEntityTypeBuilder.createLiving()
                    .entityFactory(GrenadeEntity::new)
                    .spawnGroup(SpawnGroup.MISC)
                    .defaultAttributes(LivingEntity::createLivingAttributes)
                    .dimensions(EntityDimensions.fixed(0.25f, 0.5f))
                    .trackRangeBlocks(80)
                    .build()
    );
    public static EntityType<SmokeGrenadeEntity> SMOKE_GRENADE_ENTITY = Registry.register(Registries.ENTITY_TYPE,
            new Identifier(MODID, "smoke_entity"),
            FabricEntityTypeBuilder.createLiving()
                    .entityFactory(SmokeGrenadeEntity::new)
                    .spawnGroup(SpawnGroup.MISC)
                    .defaultAttributes(LivingEntity::createLivingAttributes)
                    .dimensions(EntityDimensions.fixed(0.25f, 0.5f))
                    .trackRangeBlocks(80)
                    .build()
    );
    public static GrenadeItem GRENADE_ITEM = Registry.register(Registries.ITEM, new Identifier(MODID, "grenade_item"), new GrenadeItem(new Item.Settings().maxCount(1)));
    public static SmokeGrenadeItem SMOKE_GRENADE_AQUA = Registry.register(Registries.ITEM, new Identifier(MODID, "smoke_aqua"), new SmokeGrenadeItem(new Item.Settings().maxCount(1)));
    public static SmokeGrenadeItem SMOKE_GRENADE_DARK = Registry.register(Registries.ITEM, new Identifier(MODID, "smoke_dark"), new SmokeGrenadeItem(new Item.Settings().maxCount(1)));
    public static SmokeGrenadeItem SMOKE_GRENADE_BLUE = Registry.register(Registries.ITEM, new Identifier(MODID, "smoke_blue"), new SmokeGrenadeItem(new Item.Settings().maxCount(1)));
    public static SmokeGrenadeItem SMOKE_GRENADE_DARK_AQUA = Registry.register(Registries.ITEM, new Identifier(MODID, "smoke_dark_aqua"), new SmokeGrenadeItem(new Item.Settings().maxCount(1)));
    public static SmokeGrenadeItem SMOKE_GRENADE_DARK_BLUE = Registry.register(Registries.ITEM, new Identifier(MODID, "smoke_dark_blue"), new SmokeGrenadeItem(new Item.Settings().maxCount(1)));
    public static SmokeGrenadeItem SMOKE_GRENADE_DARK_GRAY = Registry.register(Registries.ITEM, new Identifier(MODID, "smoke_dark_gray"), new SmokeGrenadeItem(new Item.Settings().maxCount(1)));
    public static SmokeGrenadeItem SMOKE_GRENADE_DARK_GREEN = Registry.register(Registries.ITEM, new Identifier(MODID, "smoke_dark_green"), new SmokeGrenadeItem(new Item.Settings().maxCount(1)));
    public static SmokeGrenadeItem SMOKE_GRENADE_DARK_PURPLE = Registry.register(Registries.ITEM, new Identifier(MODID, "smoke_dark_purple"), new SmokeGrenadeItem(new Item.Settings().maxCount(1)));
    public static SmokeGrenadeItem SMOKE_GRENADE_DARK_RED = Registry.register(Registries.ITEM, new Identifier(MODID, "smoke_dark_red"), new SmokeGrenadeItem(new Item.Settings().maxCount(1)));
    public static SmokeGrenadeItem SMOKE_GRENADE_GOLD = Registry.register(Registries.ITEM, new Identifier(MODID, "smoke_gold"), new SmokeGrenadeItem(new Item.Settings().maxCount(1)));
    public static SmokeGrenadeItem SMOKE_GRENADE_GRAY = Registry.register(Registries.ITEM, new Identifier(MODID, "smoke_gray"), new SmokeGrenadeItem(new Item.Settings().maxCount(1)));
    public static SmokeGrenadeItem SMOKE_GRENADE_GREEN = Registry.register(Registries.ITEM, new Identifier(MODID, "smoke_green"), new SmokeGrenadeItem(new Item.Settings().maxCount(1)));
    public static SmokeGrenadeItem SMOKE_GRENADE_LIGHT_PURPLE = Registry.register(Registries.ITEM, new Identifier(MODID, "smoke_light_purple"), new SmokeGrenadeItem(new Item.Settings().maxCount(1)));
    public static SmokeGrenadeItem SMOKE_GRENADE_RED = Registry.register(Registries.ITEM, new Identifier(MODID, "smoke_red"), new SmokeGrenadeItem(new Item.Settings().maxCount(1)));
    public static SmokeGrenadeItem SMOKE_GRENADE_WHITE = Registry.register(Registries.ITEM, new Identifier(MODID, "smoke_white"), new SmokeGrenadeItem(new Item.Settings().maxCount(1)));
    public static SmokeGrenadeItem SMOKE_GRENADE_YELLOW = Registry.register(Registries.ITEM, new Identifier(MODID, "smoke_yellow"), new SmokeGrenadeItem(new Item.Settings().maxCount(1)));
    public static SoundEvent GRENADE_BOUNCE_1 = SoundEvent.of(new Identifier(MODID,"grenade_bounce_1"));
    public static SoundEvent GRENADE_BOUNCE_2 = SoundEvent.of(new Identifier(MODID,"grenade_bounce_2"));
    public static SoundEvent GRENADE_BOUNCE_3 = SoundEvent.of(new Identifier(MODID,"grenade_bounce_3"));
    public static SoundEvent CAN_BOUNCE1_1 = SoundEvent.of(new Identifier(MODID,"can_bounce1_1"));
    public static SoundEvent CAN_BOUNCE1_2 = SoundEvent.of(new Identifier(MODID,"can_bounce1_2"));
    public static SoundEvent CAN_BOUNCE2_1 = SoundEvent.of(new Identifier(MODID,"can_bounce2_1"));
    public static SoundEvent CAN_BOUNCE2_2 = SoundEvent.of(new Identifier(MODID,"can_bounce2_2"));
    public static SoundEvent CAN_BOUNCE3_1 = SoundEvent.of(new Identifier(MODID,"can_bounce3_1"));
    public static SoundEvent CAN_BOUNCE3_2 = SoundEvent.of(new Identifier(MODID,"can_bounce3_2"));
    public static SoundEvent CAN_BOUNCE4_1 = SoundEvent.of(new Identifier(MODID,"can_bounce4_1"));
    public static SoundEvent GRENADE_PIN_1 = SoundEvent.of(new Identifier(MODID,"grenade_pin_1"));
    public static SoundEvent GRENADE_PIN_2 = SoundEvent.of(new Identifier(MODID,"grenade_pin_2"));
    public static SoundEvent GRENADE_PIN_3 = SoundEvent.of(new Identifier(MODID,"grenade_pin_3"));
    public static SoundEvent GRENADE_PIN_4 = SoundEvent.of(new Identifier(MODID,"grenade_pin_4"));
    public static SoundEvent GRENADE_PIN_5 = SoundEvent.of(new Identifier(MODID,"grenade_pin_5"));
    public static SoundEvent GRENADE_PIN_6 = SoundEvent.of(new Identifier(MODID,"grenade_pin_6"));
    public static SoundEvent GRENADE_DETONATE = SoundEvent.of(new Identifier(MODID,"grenade_detonate"));

    public static final ItemGroup ITEM_GROUP = FabricItemGroup.builder(new Identifier(MODID, "group"))
            .icon(() -> new ItemStack(GRENADE_ITEM))
            .build();
    @Override
    public void onInitialize() {
        soundList.addAll(Arrays.asList(GRENADE_BOUNCE_1,GRENADE_BOUNCE_2,GRENADE_BOUNCE_3,CAN_BOUNCE1_1,CAN_BOUNCE1_2,CAN_BOUNCE2_1,CAN_BOUNCE2_2,CAN_BOUNCE3_1,CAN_BOUNCE3_2,CAN_BOUNCE4_1,GRENADE_PIN_1,GRENADE_PIN_2,GRENADE_PIN_3,GRENADE_PIN_4,GRENADE_PIN_5,GRENADE_PIN_6));
        // Item group registration
        ItemGroupEvents.modifyEntriesEvent(ITEM_GROUP).register(content -> {
            content.add(GRENADE_ITEM);
            content.add(SMOKE_GRENADE_AQUA);
            content.add(SMOKE_GRENADE_RED);
            content.add(SMOKE_GRENADE_DARK_BLUE);
            content.add(SMOKE_GRENADE_DARK_GRAY);
            content.add(SMOKE_GRENADE_DARK_GREEN);
            content.add(SMOKE_GRENADE_DARK_BLUE);
            content.add(SMOKE_GRENADE_DARK_RED);
            content.add(SMOKE_GRENADE_DARK_PURPLE);
            content.add(SMOKE_GRENADE_LIGHT_PURPLE);
            content.add(SMOKE_GRENADE_DARK_AQUA);
            content.add(SMOKE_GRENADE_DARK);
            content.add(SMOKE_GRENADE_BLUE);
            content.add(SMOKE_GRENADE_GOLD);
            content.add(SMOKE_GRENADE_GRAY);
            content.add(SMOKE_GRENADE_GREEN);
            content.add(SMOKE_GRENADE_WHITE);
            content.add(SMOKE_GRENADE_YELLOW);

        });
    }
}
