package org.mwar;

import net.fabricmc.api.ModInitializer;
import dev.lazurite.rayon.api.event.collision.ElementCollisionEvents;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mwar.entity.GrenadeEntity;
import org.mwar.item.GrenadeItem;

public class Mwar implements ModInitializer {
    public static final String MODID = "mwar";
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
    public static GrenadeItem GRENADE_ITEM = Registry.register(Registries.ITEM, new Identifier(MODID, "grenade_item"), new GrenadeItem(new Item.Settings().maxCount(1)));
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
        // Item group registration
        ItemGroupEvents.modifyEntriesEvent(ITEM_GROUP).register(content -> content.add(GRENADE_ITEM));
    }
}
