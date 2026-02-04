package com.warden.client.modules;

import com.warden.client.modules.Mod.Category;
import net.minecraft.util.math.Vec3d;

public class Flight extends Mod {
    public Flight() { 
        super("Flight", Category.MOVEMENT); 
    }

    @Override
    public void onTick() {
        // nullCheck metodu Mod.java'da tanımlı olmalı
        if (nullCheck()) return;

        // Vanilla uçuş yeteneğini kapatıyoruz ki hile korumasına yakalanmayalım
        mc.player.getAbilities().flying = false;
        
        float yaw = mc.player.getYaw();
        double rad = Math.toRadians(yaw);
        double speed = 0.5;

        // İleri ve yan hareket girdilerini alıyoruz
        float forward = mc.player.input.movementForward;
        float sideways = mc.player.input.movementSideways;

        double vy = 0;
        if (mc.options.jumpKey.isPressed()) vy = speed;
        else if (mc.options.sneakKey.isPressed()) vy = -speed;

        if (forward == 0 && sideways == 0) {
            // Hiçbir tuşa basılmıyorsa sadece dikey hızı uygula (havada asılı kalma)
            mc.player.setVelocity(0, vy, 0);
        } else {
            // W-A-S-D yönüne göre hızı hesapla
            double sin = Math.sin(rad);
            double cos = Math.cos(rad);
            
            double vx = (forward * -sin + sideways * -cos) * speed;
            double vz = (forward * cos + sideways * -sin) * speed;

            mc.player.setVelocity(vx, vy, vz);
        }
    }

    @Override
    public void onDisable() {
        // Hileyi kapattığında momentumu sıfırla ki küt diye fırlamasın
        if (mc.player != null) {
            mc.player.setVelocity(0, 0, 0);
        }
    }
}
