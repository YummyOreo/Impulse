package impulse.hud.mod.ui.buttons;

import java.awt.Color;
import java.util.ArrayList;

import com.sun.swing.internal.plaf.metal.resources.metal;

import impulse.discord.Discordrpc;
import impulse.hud.mod.HudMod;
import impulse.util.ui.GuiUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;

public class ColorButton {
		
	public int x, y, w, h;
	public HudMod m;
	public float hue = 1;
	
	public ColorButton(int x, int y, int w, int h, HudMod m) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		this.m = m;
		
	}
	
	public void draw() {
		if (this.m.getRainbow()) {
			GuiUtils.drawChromaString("Change Color", x  + 2, y + 2, true);
		} else {
			Minecraft.getMinecraft().fontRendererObj.drawStringWithShadow("Change Color", x  + 2, y + 2, getColor());
		}

	}
	
	private int getColor() {
		return this.m.getColor();
	}
	
	public void onClick(int mouseX, int mouseY, int button) {
		if (mouseX >= x && mouseX <= x + w && mouseY >= y && mouseY <= y + h) {
			
			if(this.m.getColor() == new Color(255, 255, 255, 255).getRGB() && !this.m.getRainbow()) {
				this.m.setColor(new Color(0, 0, 0, 255).getRGB());
			} else if (this.m.getColor() == new Color(0, 0, 0, 255).getRGB()) {
				this.m.setColor(new Color(255, 0,0).getRGB());
			} else if (this.m.getColor() == new Color(255, 0, 0).getRGB()) {
				this.m.setColor(new Color(0, 255,0).getRGB());
			} else if (this.m.getColor() == new Color(0, 255, 0).getRGB()) {
				this.m.setColor(new Color(0, 0, 255).getRGB());
			} else if (this.m.getColor() == new Color(0, 0, 255).getRGB()) {
				this.m.setColor(new Color(255, 255, 255).getRGB());
				this.m.setRainbow(true);
			} else {
				this.m.setRainbow(false);
				this.m.setColor(new Color(255, 255, 255).getRGB());
			}
		}

	}

}
