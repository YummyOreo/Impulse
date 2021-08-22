package impulse.ui.clickgui.comp;

import java.awt.Color;

import impulse.Impulse;
import impulse.cosmetics.textureManager;
import impulse.discord.Discordrpc;
import impulse.hud.HUDConfigScreen;
import impulse.hud.mod.HudMod;
import impulse.mod.impl.ToggleStrint;
import impulse.ui.settings.SettingsGui;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;

public class SettingsButton {
	
	public int x, y, w, h;
	public HudMod m;
	public boolean menu = false;
	
	public SettingsButton(int x, int y, int w, int h, HudMod m) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		this.m = m;
		
	}
	
	public SettingsButton(int x, int y, int w, int h, HudMod m, boolean menu) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		this.m = m;
		this.menu = menu;
		
	}
	
	public void draw() {
		Gui.drawRect(x + 1, y, x + w + 1, y + h, new Color(0, 0, 0, 170).getRGB());
		Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation(textureManager.INSTANCE.clientName + "/" + textureManager.INSTANCE.settings));
		GlStateManager.pushMatrix();
		GlStateManager.color(255, 255, 255);
		Gui.drawModalRectWithCustomSizedTexture(x, y, 0, 0, h, h, 10.5F, h);
		GlStateManager.popMatrix();
	}
	
	private int getColor() {
		if (m.isEnabled()) {
			return new Color(0, 225, 0, 255).getRGB();
		} else {
			return new Color(255, 0, 0, 255).getRGB();
		}
	}
	
	public void onClick(int mouseX, int mouseY, int button) {
		if (mouseX >= x && mouseX <= x + w && mouseY >= y && mouseY <= y + h) {
			
			Minecraft.getMinecraft().displayGuiScreen(new SettingsGui(m, menu)); 
		}

	}

}
