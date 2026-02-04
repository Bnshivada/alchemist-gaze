package com.warden.client.modules;

import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.util.Hand;

public class CombatModules {
    public static void runKillAura(MinecraftClient client) {
        if (client.world != null && client.player != null) {
            for (Entity entity : client.world.getEntities()) {
                // Kendimize vurmayalım ve sadece 3 blok yakındakilere vuralım
                if (entity != client.player && client.player.distanceTo(entity) < 3.5) {
                    client.interactionManager.attackEntity(client.player, entity);
                    client.player.swingHand(Hand.MAIN_HAND);
                }
            }
        }
    }
}
