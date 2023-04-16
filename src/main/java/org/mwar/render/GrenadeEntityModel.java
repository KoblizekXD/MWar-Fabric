package org.mwar.render;

import net.minecraft.client.model.ModelPart;
import net.minecraft.client.model.ModelPartBuilder;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;
import org.mwar.entity.grenade.GrenadeEntity;

import java.util.ArrayList;
import java.util.HashMap;

public class GrenadeEntityModel extends EntityModel<GrenadeEntity> {

    private final ModelPart modelPart;

    public GrenadeEntityModel(int x, int y, int z) {
        var cuboidData = ModelPartBuilder.create().cuboid(0, 0, 0, x, y, z).build();
        var cuboids = new ArrayList<ModelPart.Cuboid>();

        for (var data : cuboidData) {
            cuboids.add(data.createCuboid(32, 32));
        }

        modelPart = new ModelPart(cuboids, new HashMap<>());
    }

    @Override
    public void render(MatrixStack matrixStack, VertexConsumer vertexConsumer, int i, int j, float f, float g, float h, float k) {
        modelPart.render(matrixStack, vertexConsumer, i, j, f, g, h, k);
    }

    @Override
    public void setAngles(GrenadeEntity entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
    }

}
