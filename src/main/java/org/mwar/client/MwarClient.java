package org.mwar.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import org.mwar.Mwar;
import org.mwar.render.GrenadeEntityModel;
import org.mwar.render.GrenadeEntityRenderer;

public class MwarClient implements ClientModInitializer {
    /**
     * Runs the mod initializer on the client environment.
     */
    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.register(Mwar.GRENADE_ENTITY, (context) -> new GrenadeEntityRenderer(context, new GrenadeEntityModel(3, 6, 3)));
    }
}
