package com.warden.client.modules;

// 1. Kategori hatasını silmek için import
import com.warden.client.modules.Mod.Category;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;

public class AutoCritical extends Mod {
    public AutoCritical() { 
        super("Criticals", Category.COMBAT); 
    }

    @Override
    public void onTick() {
        if (nullCheck()) return;

        // El sallanıyorsa ve oyuncu yerdeyse kritik vuruş paketlerini gönder
        if (mc.player.handSwinging && mc.player.isOnGround()) {
            // Vuruş doluluk oranı %90 üzerindeyse çalıştır
            if (mc.player.getAttackCooldownProgress(0.5f) >= 0.9f && mc.getNetworkHandler() != null) {
                double x = mc.player.getX();
                double y = mc.player.getY();
                double z = mc.player.getZ();

                // 1.21.4 Uyumlu Paket Gönderimi:
                // Havaya zıplamış gibi yap (y + 0.0625)
                mc.getNetworkHandler().sendPacket(new PlayerMoveC2SPacket.Full(x, y + 0.0625, z, mc.player.getYaw(), mc.player.getPitch(), false, false));
                
                // Geri yere düşmüş gibi yap (y)
                mc.getNetworkHandler().sendPacket(new PlayerMoveC2SPacket.Full(x, y, z, mc.player.getYaw(), mc.player.getPitch(), false, false));
            }
        }
    }
}
