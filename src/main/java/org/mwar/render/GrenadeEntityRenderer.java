package org.mwar.render;

import com.jme3.math.Quaternion;

import dev.lazurite.rayon.impl.bullet.math.Convert;
import net.minecraft.client.render.Frustum;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import org.mwar.Mwar;
import org.mwar.entity.grenade.GrenadeEntity;

public class GrenadeEntityRenderer extends EntityRenderer<GrenadeEntity> {

    private static final Identifier texture = new Identifier(Mwar.MODID, "textures/entity/stone_block.png");
    private final GrenadeEntityModel model;

    public GrenadeEntityRenderer(EntityRendererFactory.Context ctx, GrenadeEntityModel model) {
        super(ctx);
        this.model = model;
        this.shadowRadius = 0.2F;
    }

    public void render(GrenadeEntity cubeEntity, float yaw, float delta, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
        var rot = Convert.toMinecraft(cubeEntity.getPhysicsRotation(new Quaternion(), delta));
        var box = cubeEntity.getBoundingBox();

        matrixStack.push();
        matrixStack.multiply(rot);
        matrixStack.translate(box.getXLength() * -0.38, box.getYLength() * -0.38, box.getZLength() * -0.38);

        final var vertexConsumer = vertexConsumerProvider.getBuffer(model.getLayer(getTexture(cubeEntity)));
        model.render(matrixStack, vertexConsumer, i, OverlayTexture.DEFAULT_UV, 1.0F, 1.0F, 1.0F, 1.0F);
        matrixStack.pop();

        super.render(cubeEntity, yaw, delta, matrixStack, vertexConsumerProvider, i);
    }

    @Override
    public boolean shouldRender(GrenadeEntity entity, Frustum frustum, double d, double e, double f) {
        return true;
    }

    @Override
    public Identifier getTexture(GrenadeEntity entity) {
        return this.texture;
    }

}
