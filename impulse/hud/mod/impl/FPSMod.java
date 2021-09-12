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

public class FPSMod extends HudMod {

	private BackgroundButton backgroundButton;
	private ColorButton colorButton;
	
	public FPSMod() {
		super("[FPS]", 5, 5, "Allows you to see your FPS.", Catagory.HUD);
	}
	
	@Override
	public void draw() {
		
		if (this.getBackground()) {
			Gui.drawRect(this.getX() - 1, this.getY() - 1, this.getX() + fr.getStringWidth("[FPS: " + mc.getDebugFPS() + "]") + 1, this.getY() + fr.FONT_HEIGHT , new Color(0, 0, 0, 150).getRGB());
		}
		
		if (this.getRainbow()) {
			GuiUtils.drawChromaString("[FPS: " + mc.getDebugFPS() + "]", getX(), getY(), true);
		} else {
			fr.drawStringWithShadow("[FPS: " + mc.getDebugFPS() + "]", getX(), getY(), this.getColor());
		}
		
		
		super.draw();
	}
	
	@Override
	public void renderDummy(int mouseX, int mouseY) {
		
		if (this.getBackground()) {
			Gui.drawRect(this.getX() - 1, this.getY() - 1, this.getX() + fr.getStringWidth("[FPS: " + mc.getDebugFPS() + "]") + 1, this.getY() + fr.FONT_HEIGHT , new Color(0, 0, 0, 150).getRGB());
		}

		if (this.getRainbow()) {
			GuiUtils.drawChromaString("[FPS: " + mc.getDebugFPS() + "]", getX(), getY(), true);
		} else {
			fr.drawStringWithShadow("[FPS: " + mc.getDebugFPS() + "]", getX(), getY(), this.getColor());
		}
		
		super.renderDummy(mouseX, mouseY);
	}
	
	@Override
	public int getWidth() {
		return fr.getStringWidth("[FPS: " + mc.getDebugFPS() + "]");
	}
	
	@Override
	public int getHeight() {
		return fr.FONT_HEIGHT;
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
