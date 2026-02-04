package com.warden.client.modules;

import com.warden.client.modules.Mod.Category;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;

public class NoFall extends Mod {
    public NoFall() { 
        super("NoFall", Category.PLAYER); 
    }

    @Override
    public void onTick() {
        if (nullCheck()) return;

        if (mc.player.fallDistance > 2.5f) {
            if (mc.getNetworkHandler() != null) {
                // HATA BURADAYDI: İki tane boolean ekliyoruz.
                // Birincisi 'onGround' (true), ikincisi genellikle 'horizontalCollision' veya 'jump' (false)
                mc.getNetworkHandler().sendPacket(new PlayerMoveC2SPacket.OnGroundOnly(true, false));
            }
        }
    }
}
