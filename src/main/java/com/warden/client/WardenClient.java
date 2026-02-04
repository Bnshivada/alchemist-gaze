package com.warden.client;

import com.warden.client.gui.WardenMenuScreen;
import com.warden.client.modules.*;
import com.warden.client.ui.HudRender;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;
import java.util.ArrayList;
import java.util.List;

public class WardenClient implements ClientModInitializer {
    public static KeyBinding menuKey;
    public static List<Mod> modules = new ArrayList<>();

    // Modüllerin Tanımlanması
    public static KillAura killAura = new KillAura();
    public static Reach reach = new Reach();
    public static ESP esp = new ESP();
    public static Xray xray = new Xray();
    public static BoatFly boatFly = new BoatFly();
    public static AutoClicker autoClicker = new AutoClicker();
    public static AutoCritical autoCritical = new AutoCritical();

    @Override
    public void onInitializeClient() {
        // Listeye Ekleme (Menüde Görünmesi İçin)
        modules.add(killAura);
        modules.add(reach);
        modules.add(autoClicker);
        modules.add(autoCritical);
        modules.add(esp);
        modules.add(xray);
        modules.add(boatFly);

        menuKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.warden.menu", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_RIGHT_SHIFT, "Warden Client"
        ));

        HudRenderCallback.EVENT.register(HudRender::render);

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (client.player == null) return;
            while (menuKey.wasPressed()) client.setScreen(new WardenMenuScreen());
            for (Mod m : modules) if (m.enabled) m.onTick();
        });
    }
}
