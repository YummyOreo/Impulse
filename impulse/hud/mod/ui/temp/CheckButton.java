package impulse.hud.mod.ui.temp;

import java.awt.Color;

import impulse.hud.mod.HudMod;
import impulse.util.ui.GuiUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;

public class CheckButton {
	public int x, y, w, h;
	public HudMod m;
	public float hue = 1;
	public String name;
	public FontRenderer fr;
	
	public CheckButton(int x, int y, HudMod m, String name) {
		this.x = x;
		this.y = y;
		this.m = m;
		this.name = name;
		this.fr = Minecraft.getMinecraft().fontRendererObj;
		this.w = fr.getStringWidth(name) + 5;
		this.h = fr.FONT_HEIGHT + 2;
	}
	
	public void draw() {
		GuiUtils.drawRoundedRect(x - 1, y - 1, x + w + 11 + 1, y + h + 2, 2, new Color(55, 59, 69).getRGB());;
		GuiUtils.drawRoundedRect(x, y, x + w, y + h + 1, 2, new Color(85, 91, 102).getRGB());;
		GuiUtils.drawRoundedRect(x + w + 1, y, x + w + 11, y + h + 1, 2, getColor());;
		//Gui.drawRect(x + w + 1, y, x + w + 11, y + h + 1, getColor());
		fr.drawStringWithShadow(this.name, x  + 2, y + 2, -1);

	}
	
	public boolean checkEnabled() {
		return this.m.enabled;
	}
	
	private int getColor() {
		if (this.checkEnabled()) {
			return new Color(0, 211, 95, 255).getRGB();
			
		} else {
			return new Color(224, 85, 53, 255).getRGB();
		}
	}
	
	public void onClick(int mouseX, int mouseY, int button) {
		if (mouseX >= x && mouseX <= x + w + 11 && mouseY >= y && mouseY <= y + h) {
			this.handleClick();
		}

	}
	
	public void handleClick() {
		
	}
}
