package com.warden.client.modules;

import com.warden.client.modules.Mod.Category;

public class Flight extends Mod {
    public Flight() { 
        super("Flight", Category.MOVEMENT); 
    }

    @Override
    public void onTick() {
        if (nullCheck()) return;

        mc.player.getAbilities().flying = false;
        
        float yaw = mc.player.getYaw();
        double rad = Math.toRadians(yaw);
        double speed = 0.5;

        float forward = mc.player.input.movementForward;
        float sideways = mc.player.input.movementSideways;

        double vy = 0;
        if (mc.options.jumpKey.isPressed()) vy = speed;
        else if (mc.options.sneakKey.isPressed()) vy = -speed;

        if (forward == 0 && sideways == 0) {
            mc.player.setVelocity(0, vy, 0);
        } else {
            double sin = Math.sin(rad);
            double cos = Math.cos(rad);
            double vx = (forward * -sin + sideways * -cos) * speed;
            double vz = (forward * cos + sideways * -sin) * speed;
            mc.player.setVelocity(vx, vy, vz);
        }
    }

    @Override
    public void onDisable() {
        if (mc.player != null) mc.player.setVelocity(0, 0, 0);
    }
}
