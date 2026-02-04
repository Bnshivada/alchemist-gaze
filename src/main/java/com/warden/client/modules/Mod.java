package com.warden.client.modules;

import com.warden.client.settings.NumberSetting;
import net.minecraft.client.MinecraftClient;
import java.util.ArrayList;
import java.util.List;

public class Mod {
    public String name;
    public boolean enabled = false;
    public boolean expanded = false;
    public Category category; // Meteor stili için kategori
    public List<NumberSetting> settings = new ArrayList<>();
    
    // Alt sınıfların erişebileceği güvenli Minecraft örneği
    protected final MinecraftClient mc = MinecraftClient.getInstance();

    // Kategoriler
    public enum Category {
        COMBAT, MOVEMENT, RENDER, PLAYER, WORLD
    }

    public Mod(String name, Category category) {
        this.name = name;
        this.category = category;
    }

    public void addSetting(NumberSetting setting) {
        settings.add(setting);
    }

    public void toggle() {
        this.enabled = !this.enabled;
        if (enabled) onEnable(); else onDisable();
    }

    public void onEnable() {}
    public void onDisable() {}
    public void onTick() {}
}
