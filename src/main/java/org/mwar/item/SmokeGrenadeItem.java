package org.mwar.item;
import com.jme3.math.Vector3f;
import dev.lazurite.rayon.api.event.collision.ElementCollisionEvents;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.mwar.Mwar;
import org.mwar.entity.grenade.GrenadeEntity;
import org.mwar.entity.grenade.SmokeGrenadeEntity;

import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

public class SmokeGrenadeItem extends Item {
    public SmokeGrenadeItem(Item.Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        //pre setup
        final ItemStack itemStack = user.getStackInHand(hand);
        SmokeGrenadeEntity entity = Objects.requireNonNull(Mwar.SMOKE_GRENADE_ENTITY.create(world));
        if (!user.isCreative()) {
            itemStack.decrement(1);
        }
        if (!world.isClient()) {
            //do pre-stuff
            Vec3d playerPos = user.getCameraPosVec(1.0f);
            final float offset = 1.0f;
            //play throw sound
            world.playSound(null, user.getX(), user.getY(), user.getZ(), Mwar.GRENADE_PIN_1, SoundCategory.PLAYERS, 5.0F, 1.0F);
            //spawn the item in front of the player
            final Vec3d spawnPos = playerPos.add(Vec3d.fromPolar(user.getRotationClient().add(1.0f)));
            entity.setPosition(spawnPos);
            entity.setFuse(ThreadLocalRandom.current().nextInt(60,100));
            entity.resetPosition();
            //launch it
            entity.getRigidBody().applyCentralForce(new Vector3f((float) user.getRotationVector().x * 1000, (float) user.getRotationVector().y * 1000, (float) user.getRotationVector().z * 1000));
            world.spawnEntity(entity);
            ElementCollisionEvents.BLOCK_COLLISION.register((element, terrain, impulse) -> {
                if (element == entity && impulse > 0.5) {
                    entity.bounceSound(world);
                }
            });
            return TypedActionResult.success(itemStack);
        }

        return TypedActionResult.pass(itemStack);
    }
}
