package impulse.hud.mod.impl;

import java.awt.Color;
import java.io.IOException;

import impulse.hud.mod.Catagory;
import impulse.hud.mod.HudMod;
import impulse.hud.mod.ui.buttons.BackgroundButton;
import impulse.hud.mod.ui.buttons.ColorButton;
import impulse.util.ui.GuiUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiScreen;

public class PositionMod extends HudMod {

	private BackgroundButton backgroundButton;
	private ColorButton colorButton;
	
	public PositionMod() {
		super("[Position Mod]", 240, 5, "Allows you to see your current position.", Catagory.HUD);
	}
	
	@Override
	public void draw() {
		
		if (this.getBackground()) {
			Gui.drawRect(this.getX() - 1, this.getY() - 1, this.getX() + fr.getStringWidth("[" + getXPos() + "x | " + getZPos() + "z]") + 1, this.getY() + fr.FONT_HEIGHT , new Color(0, 0, 0, 150).getRGB());
		}
		
		if (this.getRainbow()) {
			GuiUtils.drawChromaString("[" + getXPos() + "x | " + getZPos() + "z]", getX(), getY(), true);
		} else {
			fr.drawStringWithShadow("[" + getXPos() + "x | " + getZPos() + "z]", getX(), getY(), this.getColor());
		}
		
		super.draw();
	}
	
	@Override
	public void renderDummy(int mouseX, int mouseY) {
		
		if (this.getBackground()) {
			Gui.drawRect(this.getX() - 1, this.getY() - 1, this.getX() + fr.getStringWidth("[10x | 10z]") + 1, this.getY() + fr.FONT_HEIGHT , new Color(0, 0, 0, 150).getRGB());
		}

		if (this.getRainbow()) {
			GuiUtils.drawChromaString("[10x | 10z]", getX(), getY(), true);
		} else {
			fr.drawStringWithShadow("[10x | 10z]", getX(), getY(), this.getColor());
		}
		
		super.renderDummy(mouseX, mouseY);
	}
	
	@Override
	public int getWidth() {
		return fr.getStringWidth("[10 | 10]") + 10;
	}
	
	
	@Override
	public int getHeight() {
		return fr.FONT_HEIGHT;
	}
	
	private String getXPos() {
		try {
			return String.valueOf((int) mc.thePlayer.posX);
		} catch (Exception e) {
			return "0";
		}

	}
	
	private String getZPos() {
		try {
			return String.valueOf((int) mc.thePlayer.posZ);
		} catch (Exception e) {
			return "0";
		}
		
	}
	
	@Override
	public void initGui(GuiScreen gui) {
		this.colorButton = new ColorButton(110, 90, Minecraft.getMinecraft().fontRendererObj.getStringWidth("Change Text Color") + 5, Minecraft.getMinecraft().fontRendererObj.FONT_HEIGHT + 2, this);
		
		backgroundButton = new BackgroundButton(110, 110, this);
	}
	
	@Override
	public void drawScreen(GuiScreen gui, int mouseX, int mouseY, float partialTicks) {
		colorButton.draw();
		
		backgroundButton.draw();
	}
	
	@Override
	public void mouseClicked(GuiScreen gui, int mouseX, int mouseY, int mouseButton) throws IOException {
		colorButton.onClick(mouseX, mouseY, mouseButton);
		
		backgroundButton.onClick(mouseX, mouseY, mouseButton);
	}
}
