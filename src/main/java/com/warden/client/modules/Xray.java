package com.warden.client.modules;

// 1. Çarpıyı silen sihirli satır:
import com.warden.client.modules.Mod.Category;

public class Xray extends Mod {
    public Xray() { 
        super("Xray", Category.RENDER); 
    }

    // Bu değişkeni Mixin sınıfımız kontrol edecek
    public static boolean isXrayEnabled = false;

    @Override
    public void onEnable() {
        isXrayEnabled = true;
        // 1.21.4 Gamma ayarı (Karanlık yerleri aydınlatır)
        mc.options.getGamma().setValue(16.0); 
        
        if (mc.worldRenderer != null) {
            mc.worldRenderer.reload(); // Dünyayı tekrar çizdir
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
