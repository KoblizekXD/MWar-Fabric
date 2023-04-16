package org.mwar.entity.grenade;

import dev.lazurite.rayon.api.EntityPhysicsElement;
import dev.lazurite.rayon.impl.bullet.collision.body.ElementRigidBody;
import dev.lazurite.rayon.impl.bullet.collision.body.EntityRigidBody;
import dev.lazurite.rayon.impl.bullet.collision.body.shape.MinecraftShape;
import dev.lazurite.rayon.impl.bullet.collision.space.MinecraftSpace;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.Arm;
import net.minecraft.world.World;
import org.mwar.Mwar;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animatable.instance.SingletonAnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class SmokeGrenadeEntity extends LivingEntity implements EntityPhysicsElement {

    private final EntityRigidBody rigidBody;

    private int fuse = 80;

    public SmokeGrenadeEntity(EntityType<? extends LivingEntity> entityType, World level) {
        super(entityType, level);
        this.rigidBody = new EntityRigidBody(this, MinecraftSpace.get(level), MinecraftShape.convex(getBoundingBox()));
        this.rigidBody.setRestitution(0.8f);
        this.rigidBody.setDragCoefficient(0.7f);
        this.rigidBody.setMass(0.7f);
        this.rigidBody.setFriction(0.7f);
        this.rigidBody.setBuoyancyType(ElementRigidBody.BuoyancyType.NONE);
    }

    @Override
    public Iterable<ItemStack> getArmorItems() {
        return List.of();
    }

    public void setFuse(int fuse) {
        this.fuse = fuse;
    }

    @Override
    public ItemStack getEquippedStack(EquipmentSlot slot) {
        return new ItemStack(Items.AIR);
    }

    @Override
    public void equipStack(EquipmentSlot slot, ItemStack stack) {
    }
    @Override
    public Arm getMainArm() {
        return null;
    }
    public void bounceSound(World level) {
        level.playSound(null, getX(), getY(), getZ(), Mwar.soundList.get(ThreadLocalRandom.current().nextInt(3,9)), SoundCategory.BLOCKS, 1.0f, ThreadLocalRandom.current().nextFloat(0.8f,1.0f));

    }
    @Override
    public boolean isSilent() {
        return true;
    }

    @Override
    public EntityRigidBody getRigidBody() {
        return this.rigidBody;
    }

}

