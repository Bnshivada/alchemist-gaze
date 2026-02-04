package com.warden.client.modules;

import com.warden.client.settings.NumberSetting;

public class Reach extends Mod {
    public static NumberSetting reachDistance = new NumberSetting("Mesafe", 4.0, 3.0, 6.0, 0.1);

    public Reach() {
        super("Reach");
        addSetting(reachDistance);
    }
    
    // Not: Gerçek Reach için Mixin gerekir, ancak KillAura bu değeri okuyacak.
}
