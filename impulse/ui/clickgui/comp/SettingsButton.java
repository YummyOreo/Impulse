package impulse.ui.clickgui.comp;

import java.awt.Color;

import impulse.Impulse;
import impulse.cosmetics.textureManager;
import impulse.discord.Discordrpc;
import impulse.hud.HUDConfigScreen;
import impulse.hud.mod.HudMod;
import impulse.mod.impl.ToggleStrint;
import impulse.ui.settings.SettingsGui;
import impulse.util.ui.GuiUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;

public class SettingsButton {
	
	public int x, y, w, h;
	public HudMod m;
	
	public SettingsButton(int x, int y, int w, int h, HudMod m) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		this.m = m;
		
	}
	
	public void draw() {
		GuiUtils.drawRoundedRect(x + 1, y + 11, x + w + 1, y + h  + 11, 1, new Color(105, 105, 105).getRGB());
		//Gui.drawRect(x + 1, y + 11, x + w + 1, y + h  + 11, new Color(105, 105, 105).getRGB());
	}
	
	public void onClick(int mouseX, int mouseY, int button) {
		if (mouseX >= x && mouseX <= x + w && mouseY >= y + 11 && mouseY <= y + h + 11) {
			
			Minecraft.getMinecraft().displayGuiScreen(new SettingsGui(m)); 
		}

	}

}
