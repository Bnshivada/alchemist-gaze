package com.warden.client.modules;

import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;

public class AutoCritical extends Mod {
    public AutoCritical() {
        super("Criticals");
    }

    @Override
    public void onTick() {
        if (!enabled || mc.player == null || mc.world == null) return;

        // Eğer saldırı yapıyorsak ve yerdeysek (Zıplamadan kritik atmak için)
        if (mc.player.handSwinging && mc.player.isOnGround()) {
            
            // Saldırı bekleme süresi dolmuşsa paketleri gönder (Spam yapmamak için)
            if (mc.player.getAttackCooldownProgress(0.5f) >= 0.9f) {
                double x = mc.player.getX();
                double y = mc.player.getY();
                double z = mc.player.getZ();

                // Karakteri milimlik zıplatıp düşürüyor gibi gösteren paketler
                // 1. Yukarı çık
                mc.getNetworkHandler().sendPacket(new PlayerMoveC2SPacket.PositionAndOnGround(x, y + 0.0625, z, true));
                // 2. Aşağı in (Kritik vuruş bu düşüş anında gerçekleşir)
                mc.getNetworkHandler().sendPacket(new PlayerMoveC2SPacket.PositionAndOnGround(x, y, z, false));
                
                // Not: Strict anticheat sunucularında bu ayar tespit edilebilir.
            }
        }
    }
}
