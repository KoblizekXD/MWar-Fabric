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

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * A stone block entity that uses an internally stored {@link EntityRigidBody} to behave using realistic physics.
 * Default Mass: 20kg
 */
public class GrenadeEntity extends LivingEntity implements EntityPhysicsElement {

    private final EntityRigidBody rigidBody;
    private int result;
    private int fuse = 80;

    public GrenadeEntity(EntityType<? extends LivingEntity> entityType, World level) {
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
        level.playSound(null, getX(), getY(), getZ(), Mwar.CAN_BOUNCE3_2, SoundCategory.BLOCKS, 1.0f, ThreadLocalRandom.current().nextFloat(0.8f,1.0f));
    }

    @Override
    public void tick() {
        super.tick();

        this.fuse--;

        if (this.fuse <= 0) {
            this.world.createExplosion(this, this.getX(), this.getY(), this.getZ(), 4.0F, World.ExplosionSourceType.TNT);
            world.playSound(null, this.getX(), this.getY(), this.getZ(), Mwar.GRENADE_DETONATE, SoundCategory.PLAYERS, 5.0F, 1.0F);
            discard();
        }
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
