package com.warden.client.modules;

public class Xray extends Mod {
    public Xray() {
        super("Xray");
    }

    @Override
    public void onEnable() {
        // Dünyayı yeniden yükle ki bloklar saydamlaşsın (Mixins ile desteklenmeli)
        if (mc.worldRenderer != null) {
            mc.worldRenderer.reload();
        }
    }

    @Override
    public void onDisable() {
        if (mc.worldRenderer != null) {
            mc.worldRenderer.reload();
        }
    }
}
