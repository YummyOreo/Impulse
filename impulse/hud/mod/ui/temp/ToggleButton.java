package impulse.hud.mod.ui.temp;

import java.awt.Color;
import java.util.ArrayList;

import com.sun.swing.internal.plaf.metal.resources.metal;

import impulse.discord.Discordrpc;
import impulse.hud.mod.HudMod;
import impulse.util.ui.GuiUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;

public class ToggleButton {
		
	public int x, y, w, h;
	public HudMod m;
	public float hue = 1;
	private String name;
	
	public ToggleButton(int x, int y, HudMod m, String name) {
		this.x = x;
		this.y = y;
		this.m = m;
		this.name = name;
		
	}
	
	public void draw() {
		Minecraft.getMinecraft().fontRendererObj.drawStringWithShadow(this.name, x  + 2, y + 2, getColor());

	}
	
	public int getColor() {
		return this.m.getColor();
	}
	
	public void onClick(int mouseX, int mouseY, int button) {
		if (mouseX >= x && mouseX <= x + Minecraft.getMinecraft().fontRendererObj.getStringWidth(this.name) + 5 && mouseY >= y && mouseY <= y + Minecraft.getMinecraft().fontRendererObj.FONT_HEIGHT + 2) {
			this.handleClick();
		}

	}
	
	public void handleClick() {

	}

}
