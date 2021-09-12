package impulse.ui.clickgui.comp;

import java.awt.Color;

import impulse.discord.Discordrpc;
import impulse.hud.mod.HudMod;
import impulse.settings.SettingsMod;
import impulse.util.ui.GuiUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;

public class SettingButton {
	
	public int x, y, w, h;
	public SettingsMod s;
	
	public SettingButton(int x, int y, int w, int h, SettingsMod s) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		this.s = s;
	}
	
	public void draw() {
		GuiUtils.drawRoundedRect(x - 1, y - 1, x + w + 1, y + h + 1, 2, new Color(55, 59, 69).getRGB());
		//Gui.drawRect(x - 1, y - 1, x + w + 1, y + h + 1, new Color(55, 59, 69).getRGB());
		
		GuiUtils.drawRoundedRect(x, y, x + w, y + h, 1, new Color(47, 50, 56).getRGB());
		//Gui.drawRect(x, y, x + w, y + h, new Color(47, 50, 56).getRGB());
		Minecraft.getMinecraft().fontRendererObj.drawString(s.name, x  + 2, y + 2, getColor());

	}
	
	private int getColor() {
		
		if ((boolean) s.getValue()) {
			return new Color(0, 211, 95, 255).getRGB();
			
		} else {
			return new Color(224, 85, 53, 255).getRGB();
		}

	}
	
	public void onClick(int mouseX, int mouseY, int button) {
		if (mouseX >= x && mouseX <= x + w && mouseY >= y && mouseY <= y + h + 11) {
			
			this.s.setValue(!(boolean) s.getValue());
			
		}

	}

}
