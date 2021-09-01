package impulse.hud.mod.impl;

import java.awt.Color;
import java.io.IOException;

import impulse.hud.mod.HudMod;
import impulse.hud.mod.ui.buttons.BackgroundButton;
import impulse.hud.mod.ui.buttons.ColorButton;
import impulse.util.ui.GuiUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiScreen;

public class ServerMod extends HudMod {

	private BackgroundButton backgroundButton;
	private ColorButton colorButton;
	
	public ServerMod() {
		super("[Server Mod]", 555, 5, "Allows you to see your server.");
	}
	
	@Override
	public void draw() {
		
		if (mc.getCurrentServerData() != null) {
			
			if (this.getBackground()) {
				Gui.drawRect(this.getX() - 1, this.getY() - 1, this.getX() + fr.getStringWidth("[" + mc.getCurrentServerData().serverIP + "]") + 1, this.getY() + fr.FONT_HEIGHT , new Color(0, 0, 0, 150).getRGB());
			}
			
			if (this.getRainbow()) {
				GuiUtils.drawChromaString("[" + mc.getCurrentServerData().serverIP + "]", getX(), getY(), true);
			} else {
				fr.drawStringWithShadow("[" + mc.getCurrentServerData().serverIP + "]", getX(), getY(), this.getColor());
			}
			
		} else {
			
			if (this.getBackground()) {
				Gui.drawRect(this.getX() - 1, this.getY() - 1, this.getX() + fr.getStringWidth("[No Server]") + 1, this.getY() + fr.FONT_HEIGHT , new Color(0, 0, 0, 150).getRGB());
			}
			
			if (this.getRainbow()) {
				GuiUtils.drawChromaString("[No Server]", getX(), getY(), true);
			} else {
				fr.drawStringWithShadow("[No Server]", getX(), getY(), this.getColor());
			}
			
		}
		
		super.draw();
	}
	
	@Override
	public void renderDummy(int mouseX, int mouseY) {

		if (mc.getCurrentServerData() != null) {
			
			if (this.getBackground()) {
				Gui.drawRect(this.getX() - 1, this.getY() - 1, this.getX() + fr.getStringWidth("[" + mc.getCurrentServerData().serverIP + "]") + 1, this.getY() + fr.FONT_HEIGHT , new Color(0, 0, 0, 150).getRGB());
			}
			
			if (this.getRainbow()) {
				GuiUtils.drawChromaString("[" + mc.getCurrentServerData().serverIP + "]", getX(), getY(), true);
			} else {
				fr.drawStringWithShadow("[" + mc.getCurrentServerData().serverIP + "]", getX(), getY(), this.getColor());
			}
			
		} else {
			
			if (this.getBackground()) {
				Gui.drawRect(this.getX() - 1, this.getY() - 1, this.getX() + fr.getStringWidth("[No Server]") + 1, this.getY() + fr.FONT_HEIGHT , new Color(0, 0, 0, 150).getRGB());
			}
			
			if (this.getRainbow()) {
				GuiUtils.drawChromaString("[No Server]", getX(), getY(), true);
			} else {
				fr.drawStringWithShadow("[No Server]", getX(), getY(), this.getColor());
			}
			
		}
		
		super.renderDummy(mouseX, mouseY);
	}
	
	@Override
	public int getWidth() {
		if (mc.getCurrentServerData() != null) {
			return fr.getStringWidth("[" + mc.getCurrentServerData().serverIP + "]");
		} else {
			return fr.getStringWidth("[No Server]");
		}
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
