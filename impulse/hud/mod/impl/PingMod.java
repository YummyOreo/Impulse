package impulse.hud.mod.impl;

import java.awt.Color;
import java.io.IOException;

import impulse.event.EventTarget;
import impulse.event.impl.ClientTick;
import impulse.hud.HUDConfigScreen;
import impulse.hud.mod.HudMod;
import impulse.hud.mod.ui.buttons.BackgroundButton;
import impulse.hud.mod.ui.buttons.ColorButton;
import impulse.util.ui.GuiUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiScreen;

public class PingMod extends HudMod {

	private BackgroundButton backgroundButton;
	private ColorButton colorButton;
	
	public PingMod() {
		super("[Ping Mod]", 120, 5, "Allows you to see your current ping.");		
	}
	
	@Override
	public void draw() {

		try {
			
			if (this.getBackground()) {
				Gui.drawRect(this.getX() - 1, this.getY() - 1, this.getX() + fr.getStringWidth("[" + String.valueOf(mc.getNetHandler().getPlayerInfo(mc.thePlayer.getUniqueID()).getResponseTime()) +  " ms]") + 1, this.getY() + fr.FONT_HEIGHT , new Color(0, 0, 0, 150).getRGB());
			}
			
			if (this.getRainbow()) {
				GuiUtils.drawChromaString("[" + String.valueOf(mc.getNetHandler().getPlayerInfo(mc.thePlayer.getUniqueID()).getResponseTime()) +  " ms]", getX(), getY(), true);
			} else {
				fr.drawStringWithShadow("[" + String.valueOf(mc.getNetHandler().getPlayerInfo(mc.thePlayer.getUniqueID()).getResponseTime()) +  " ms]", getX(), getY(), this.getColor());
			}
			
		} catch (Exception e) {
			
			if (this.getBackground()) {
				Gui.drawRect(this.getX() - 1, this.getY() - 1, this.getX() + fr.getStringWidth("[0 ms]") + 1, this.getY() + fr.FONT_HEIGHT , new Color(0, 0, 0, 150).getRGB());
			}
			
			if (this.getRainbow()) {
				GuiUtils.drawChromaString("[0 ms]", getX(), getY(), true);
			} else {
				fr.drawStringWithShadow("[0 ms]", getX(), getY(), this.getColor());
			}
			
		}
		
		super.draw();
	}
	
	@Override
	public void renderDummy(int mouseX, int mouseY) {
		
		if (this.getBackground()) {
			Gui.drawRect(this.getX() - 1, this.getY() - 1, this.getX() + fr.getStringWidth("[100 ms]") + 1, this.getY() + fr.FONT_HEIGHT , new Color(0, 0, 0, 150).getRGB());
		}
		
		if (this.getRainbow()) {
			GuiUtils.drawChromaString("[100 ms]", getX(), getY(), true);
		} else {
			fr.drawStringWithShadow("[100 ms]", getX(), getY(), this.getColor());

		}
		
		super.renderDummy(mouseX, mouseY);
	}
	
	@Override
	public int getWidth() {
		return fr.getStringWidth("[100 ms]");
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
