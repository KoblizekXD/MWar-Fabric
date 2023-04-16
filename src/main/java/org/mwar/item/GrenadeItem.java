package org.mwar.item;


import com.jme3.math.Vector3f;
import dev.lazurite.rayon.api.event.collision.ElementCollisionEvents;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.mwar.Mwar;
import org.mwar.entity.grenade.GrenadeEntity;


import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

public class GrenadeItem extends Item {

    public GrenadeItem(Item.Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        final ItemStack itemStack = user.getStackInHand(hand);
        if (!world.isClient()) {
            user.setCurrentHand(hand);
            //play throw sound
            world.playSound(null, user.getX(), user.getY(), user.getZ(), Mwar.soundList.get(ThreadLocalRandom.current().nextInt(10, 15)), SoundCategory.PLAYERS, 1.0F, 1.0F);
            return TypedActionResult.consume(itemStack);
        }
        return TypedActionResult.fail(itemStack);
    }

    @Override
    public void usageTick(World world, LivingEntity user, ItemStack stack, int remainingUseTicks) {
        if (!world.isClient()) {
            PlayerEntity player = (PlayerEntity) user;
            if (remainingUseTicks <= 1) {
                world.createExplosion(player, player.getX(), player.getY(), player.getZ(), 4.0F, World.ExplosionSourceType.TNT);
                if (!player.isCreative()) {
                    stack.decrement(1);
                }
            }
        }
        super.usageTick(world, user, stack, remainingUseTicks);
    }

    @Override
    public void onStoppedUsing(ItemStack stack, World world, LivingEntity user, int remainingUseTicks) {
        if (user instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity) user;
            final ItemStack itemStack = player.getStackInHand(player.getActiveHand());
        GrenadeEntity entity = Objects.requireNonNull(Mwar.GRENADE_ENTITY.create(world));
        if (!world.isClient()) {
            //do pre-stuff
            Vec3d playerPos = player.getCameraPosVec(1.0f);
            final float offset = 1.0f;
            //spawn the item in front of the player
            final Vec3d spawnPos = playerPos.add(Vec3d.fromPolar(player.getRotationClient().add(1.0f)));
            entity.setPosition(spawnPos);
            System.out.println(remainingUseTicks);
            entity.setFuse(remainingUseTicks);
            entity.resetPosition();
            //launch it
            entity.getRigidBody().applyCentralForce(new Vector3f((float) player.getRotationVector().x * 1000, (float) player.getRotationVector().y * 1000, (float) player.getRotationVector().z * 1000));
            world.spawnEntity(entity);
            ElementCollisionEvents.BLOCK_COLLISION.register((element, terrain, impulse) -> {
                if (element == entity && impulse > 0.5) {
                    entity.bounceSound(world);
                }
           });
          }
            if (!player.isCreative()) {
                itemStack.decrement(1);
            }
        }
        super.onStoppedUsing(stack, world, user, remainingUseTicks);
    }

    @Override
    public int getMaxUseTime(ItemStack stack) {
        return 80;
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.NONE;
    }
}
