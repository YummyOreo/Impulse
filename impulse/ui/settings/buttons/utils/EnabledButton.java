package impulse.ui.settings.buttons.utils;

import java.awt.Color;

import impulse.Impulse;
import impulse.discord.Discordrpc;
import impulse.hud.mod.HudMod;
import impulse.mod.impl.ToggleStrint;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;

public class EnabledButton {
	
	public int x;
	public int y, w, h;
	public HudMod m;
	
	public EnabledButton(float x, int y, int w, int h, HudMod m) {
		this.x = (int) x;
		this.y = y;
		this.w = w;
		this.h = h;
		this.m = m;
		
	}

	public void draw() {
		Gui.drawRect(x, y, w, y + h + 2 , getColor());
		Minecraft.getMinecraft().fontRendererObj.drawStringWithShadow(getName(), x  * 3.1f, y + 2, -1);

	}
	
	private String getName() {
		if (m.checkDisabled()) {
			return "Disabed";
		} else {
			return "Toggle";
		}
			
		
	}
	
	private int getColor() {
		
		if (m.checkDisabled()) {
			return new Color(128, 128, 128).getRGB();
		} else if (m.isEnabled()) {
			return new Color(0, 211, 95, 255).getRGB();
			
		} else {
			return new Color(224, 85, 53, 255).getRGB();
		}
	

	}
	
	public void onClick(int mouseX, int mouseY, int button) {
		if (mouseX >= x && mouseX <= x + w && mouseY >= y && mouseY <= y + h) {
			
			if (m.checkDisabled()) return;
			
			m.toggle();

		}

	}

}
