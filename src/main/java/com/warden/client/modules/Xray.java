package com.warden.client.modules;

import net.minecraft.client.MinecraftClient;

public class Xray extends Mod {
    public Xray() {
        super("Xray");
    }

    // Bu değişkeni oyunun blokları şeffaf mı çizmesi gerektiğini anlaması için kullanacağız
    public static boolean isXrayEnabled = false;

    @Override
    public void onEnable() {
        isXrayEnabled = true;
        // 1.21.4'te parlaklığı (gamma) arttırarak karanlık yerleri görmeyi sağlar
        mc.options.getGamma().setValue(16.0); 
        // Dünyayı yeniden yükle ki bloklar yeni ayara göre çizilsin
        if (mc.worldRenderer != null) {
            mc.worldRenderer.reload();
        }
    }

    @Override
    public void onDisable() {
        isXrayEnabled = false;
        mc.options.getGamma().setValue(1.0); // Parlaklığı normale döndür
        if (mc.worldRenderer != null) {
            mc.worldRenderer.reload();
        }
    }
}
