package com.warden.client.modules;

import com.warden.client.settings.NumberSetting;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.util.math.Vec3d;

public class BoatFly extends Mod {
    public NumberSetting speed = new NumberSetting("Hız", 1.0, 0.1, 30.0, 0.5);

    public BoatFly() {
        super("BoatFly");
        addSetting(speed);
    }

    @Override
    public void onTick() {
        if (mc.player != null && mc.player.getVehicle() instanceof BoatEntity boat) {
            double flySpeed = speed.getValue();
            Vec3d velocity = mc.player.getRotationVector().multiply(flySpeed);
            
            double vertical = 0;
            if (mc.options.jumpKey.isPressed()) vertical = 0.5; // Space ile yukarı
            else if (mc.options.sprintKey.isPressed()) vertical = -0.5; // CTRL ile aşağı (Sprint tuşu genellikle CTRL'dir)

            boat.setVelocity(velocity.x, vertical, velocity.z);
        }
    }
}
