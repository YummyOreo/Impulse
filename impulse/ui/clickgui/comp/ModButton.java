package impulse.ui.clickgui.comp;

import java.awt.Color;

import impulse.discord.Discordrpc;
import impulse.hud.mod.HudMod;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;

public class ModButton {
	
	public int x, y, w, h;
	public HudMod m;
	
	public ModButton(int x, int y, int w, int h, HudMod m) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		this.m = m;
	}
	
	public void draw() {
		Gui.drawRect(x, y, x + w, y + h, new Color(0, 0, 0, 170).getRGB());
		Minecraft.getMinecraft().fontRendererObj.drawStringWithShadow(m.name, x  + 2, y + 2, getColor());

	}
	
	private int getColor() {
		
		if (m.checkDisabled()) {
				return new Color(128, 128, 128).getRGB();
		} else if (m.isEnabled()) {
			return new Color(0, 225, 0, 255).getRGB();
			
		} else {
			return new Color(255, 0, 0, 255).getRGB();
		}
		

	}
	
	public void onClick(int mouseX, int mouseY, int button) {
		if (mouseX >= x && mouseX <= x + w && mouseY >= y && mouseY <= y + h) {
			
			if (m.checkDisabled()) return;
			
			if(m.isEnabled()) {
				if (m.name == "[Discord RPC]") {
					Discordrpc.stopDiscordRPC();
				}
				
				m.setEnabled(false);
			} else {
				
				if (m.name == "[Discord RPC]") {
					Discordrpc.startDiscordRPC();
				}
				
				m.setEnabled(true);
			}
		}

	}

}
