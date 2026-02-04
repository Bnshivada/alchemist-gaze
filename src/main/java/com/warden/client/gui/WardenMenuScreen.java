package com.warden.client.gui;

import com.warden.client.WardenClient;
import com.warden.client.modules.Mod;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

import java.util.ArrayList;
import java.util.List;

public class WardenMenuScreen extends Screen {
    public WardenMenuScreen() {
        super(Text.literal("Warden ClickGUI"));
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        // Arka planı hafif karartalım (Sinematik etki)
        context.fill(0, 0, this.width, this.height, 0x70000000);

        int startX = 30; // İlk sütunun X koordinatı
        int width = 100; // Sütun genişliği
        int titleHeight = 18; // Kategori başlığı yüksekliği
        int moduleHeight = 16; // Modül satırı yüksekliği

        // Her bir kategoriyi döngüye alalım
        for (Mod.Category category : Mod.Category.values()) {
            int currentY = 30;

            // 1. KATEGORİ BAŞLIĞI (Koyu Gri/Siyah Panel)
            context.fill(startX, currentY, startX + width, currentY + titleHeight, 0xFF151515);
            context.drawTextWithShadow(textRenderer, category.name(), startX + 5, currentY + 5, 0xFF00AAFF); // Mavi Başlık
            
            currentY += titleHeight;

            // 2. MODÜLLERİ LİSTELE
            for (Mod mod : WardenClient.modules) {
                if (mod.category == category) {
                    // Modül kutusu arka planı (Aktifse daha açık gri, değilse koyu)
                    int bgColor = mod.enabled ? 0xFF282828 : 0xFF101010;
                    context.fill(startX, currentY, startX + width, currentY + moduleHeight, bgColor);
                    
                    // Aktif modülün yanına küçük mavi bir şerit (Meteor imzası)
                    if (mod.enabled) {
                        context.fill(startX, currentY, startX + 2, currentY + moduleHeight, 0xFF00AAFF);
                    }

                    // Modül ismi (Aktifse beyaz, değilse gri)
                    int textColor = mod.enabled ? 0xFFFFFFFF : 0xFFAAAAAA;
                    context.drawTextWithShadow(textRenderer, mod.name, startX + 6, currentY + 4, textColor);

                    currentY += moduleHeight;
                }
            }
            
            // Bir sonraki kategori sütununa geç (Arada 10px boşluk bırak)
            startX += width + 10;
        }

        super.render(context, mouseX, mouseY, delta);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        int startX = 30;
        int width = 100;
        int titleHeight = 18;
        int moduleHeight = 16;

        for (Mod.Category category : Mod.Category.values()) {
            int currentY = 30 + titleHeight; // Başlığı atla, modüllere gel

            for (Mod mod : WardenClient.modules) {
                if (mod.category == category) {
                    // Farenin modülün üstünde olup olmadığını kontrol et
                    if (mouseX >= startX && mouseX <= startX + width && mouseY >= currentY && mouseY <= currentY + moduleHeight) {
                        mod.toggle(); // Modülü aç/kapat
                        return true;
                    }
                    currentY += moduleHeight;
                }
            }
            startX += width + 10;
        }
        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public boolean shouldPause() {
        return false; // Menü açıldığında oyun (singleplayer) durmasın
    }
}
