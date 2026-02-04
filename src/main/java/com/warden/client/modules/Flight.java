package com.warden.client.modules;

import com.warden.client.modules.Mod.Category;
import net.minecraft.util.math.Vec3d;

public class Flight extends Mod {
    public Flight() { super("Flight", Category.MOVEMENT); }

    @Override
    public void onTick() {
        if (nullCheck()) return;

        mc.player.getAbilities().flying = false; // Vanilla uçuşunu kapat (kick yememek için)
        
        float yaw = mc.player.getYaw();
        double rad = Math.toRadians(yaw);
        double speed = 0.5;

        double vx = -Math.sin(rad) * speed;
        double vz = Math.cos(rad) * speed;
        double vy = 0;

        if (mc.options.jumpKey.isPressed()) vy = speed;
        if (mc.options.sneakKey.isPressed()) vy = -speed;

        if (mc.player.input.pressingForward) {
            mc.player.setVelocity(vx, vy, vz);
        } else {
            mc.player.setVelocity(0, vy, 0);
        }
    }
}
