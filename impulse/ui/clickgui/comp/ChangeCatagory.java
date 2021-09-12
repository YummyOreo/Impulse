package impulse.ui.clickgui.comp;

import java.awt.Color;
import java.util.concurrent.Callable;

import impulse.Impulse;
import impulse.discord.Discordrpc;
import impulse.hud.mod.Catagory;
import impulse.hud.mod.HudMod;
import impulse.util.ui.GuiUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;

public class ChangeCatagory {
	
	public int x, y, w, h;
	public String m;
	private Catagory catagory;
	
	public ChangeCatagory(int x, int y, int w, int h, String m, Catagory catagory) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		this.m = m;
		this.catagory = catagory;
	}
	
	public void draw() {
		GuiUtils.drawRoundedRect(x - 1, y - 1, x + w + 1, y + h + 1, 2, new Color(55, 59, 69).getRGB());
		
		GuiUtils.drawRoundedRect(x, y, x + w, y + h, 1, new Color(47, 50, 56).getRGB());
		Minecraft.getMinecraft().fontRendererObj.drawString(m, x  + 2, y + 2, getColor());

	}
	
	private int getColor() {
		if (Impulse.INSTANCE.catagory == catagory) {
			return new Color(0, 211, 95, 255).getRGB();
			
		} else {
			return new Color(224, 85, 53, 255).getRGB();
		}
	}
	
	public void onClick(int mouseX, int mouseY, int button) {
		if (mouseX >= x && mouseX <= x + w && mouseY >= y && mouseY <= y + h + 11) {
			Impulse.INSTANCE.catagory = catagory;
		}

	}

}
