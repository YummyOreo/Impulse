package impulse.ui.reset.comp;

import java.awt.Color;

import impulse.cosmetics.textureManager;
import impulse.hud.HUDConfigScreen;
import impulse.hud.mod.HudMod;
import impulse.hud.mod.utils.Reset;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;

public class ResetButton {
		
	public int x, y, w, h;
	public HudMod m;
	
	public ResetButton(int x, int y, int w, int h) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		
	}
	
	public void draw() {
		Gui.drawRect(x, y, x + w, y + h, new Color(0, 0, 0, 170).getRGB());
		Minecraft.getMinecraft().fontRendererObj.drawStringWithShadow("Reset", x  + 2, y + 2, -1);

	}
	
	public void onClick(int mouseX, int mouseY, int button) {
		if (mouseX >= x && mouseX <= x + w && mouseY >= y && mouseY <= y + h) {
			Reset.save();
		}

	}

}
