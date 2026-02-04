package com.warden.client.modules;

import com.warden.client.modules.Mod.Category;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;

public class NoFall extends Mod {
    public NoFall() { 
        super("NoFall", Category.PLAYER); 
    }

    @Override
    public void onTick() {
        // nullCheck, Mod.java'da tanımlı olmalı
        if (nullCheck()) return;

        // Düşme mesafesi 2.5 bloğu geçtiğinde
        if (mc.player.fallDistance > 2.5f) {
            // 1.21.4 Paket Yapısı: Sadece yere basıp basmadığını (onGround) gönderir.
            // Bu, sunucuya "şu an güvenli bir şekilde yere bastım" sinyali verir.
            if (mc.getNetworkHandler() != null) {
                mc.getNetworkHandler().sendPacket(new PlayerMoveC2SPacket.OnGroundOnly(true));
            }
        }
    }
}
