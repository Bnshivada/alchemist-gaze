package com.warden.client.gui;

import com.warden.client.WardenClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

public class WardenMenuScreen extends Screen {
    public WardenMenuScreen() {
        super(Text.literal("Warden Menu"));
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        context.fill(0, 0, this.width, this.height, 0x90000000); // Karartma
        
        context.drawCenteredTextWithShadow(this.textRenderer, "--- WARDEN CLIENT v1.0 ---", this.width / 2, 20, 0x00FFFF);
        
        // BoatFly Durumu
        String status = WardenClient.boatFlyEnabled ? "§a[ON]" : "§c[OFF]";
        context.drawTextWithShadow(this.textRenderer, "BoatFly: " + status, 20, 50, 0xFFFFFF);
        context.drawTextWithShadow(this.textRenderer, "§7(Tıkla: B tuşu ile veya koddan değiştirilebilir)", 20, 62, 0xAAAAAA);
        
        super.render(context, mouseX, mouseY, delta);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        // Basit bir tıklama alanı belirleyelim (BoatFly yazısının üstü)
        if (mouseX >= 20 && mouseX <= 100 && mouseY >= 50 && mouseY <= 60) {
            WardenClient.boatFlyEnabled = !WardenClient.boatFlyEnabled;
            return true;
        }
        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public boolean shouldPause() { return false; }
}
