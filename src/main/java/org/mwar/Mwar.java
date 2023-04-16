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
    public static SoundEvent GRENADE_BOUNCE = SoundEvent.of(new Identifier(MODID,"grenade_bounce"));
    public static SoundEvent GRENADE_PULL = SoundEvent.of(new Identifier(MODID,"grenade_pull"));
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
