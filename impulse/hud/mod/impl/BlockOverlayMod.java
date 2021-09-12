package impulse.hud.mod.impl;

import java.awt.Color;
import java.io.IOException;

import impulse.hud.mod.Catagory;
import impulse.hud.mod.HudMod;
import impulse.hud.mod.ui.temp.CheckButton;
import impulse.hud.mod.ui.temp.ToggleButton;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;

public class BlockOverlayMod extends HudMod {
	
	ColorButton colorButton;
	
	public BlockOverlayMod() {
		super("[Block Overlay]", 0, 0, "Changes your block overlay", Catagory.WORLD);
	}
	
	private class ColorButton extends ToggleButton {

		
		public ColorButton(int x, int y, HudMod m) {
			super(x, y, m, "Change color");
			// TODO Auto-generated constructor stub
		}
		
		@Override
		public int getColor() {
			return m.getColor();
		}
		
		@Override
		public void handleClick() {
			if (m.getColor() == new Color(0.0F, 0.0F, 0.0F, 0.4F).getRGB() || m.getColor() == new Color(0, 0, 0, 255).getRGB()) {
				this.m.setColor(new Color(255, 0,0).getRGB());
			} else if (this.m.getColor() == new Color(255, 0, 0).getRGB()) {
				this.m.setColor(new Color(0, 255,0).getRGB());
			} else if (this.m.getColor() == new Color(0, 255, 0).getRGB()) {
				this.m.setColor(new Color(0, 0, 255).getRGB());
			} else if (this.m.getColor() == new Color(0, 0, 255).getRGB()) {
				this.m.setColor(new Color(255, 255, 255).getRGB());
			} else if (this.m.getColor() == new Color(255, 255, 255).getRGB()){
				this.m.setColor(new Color(0, 0, 0, 0.4F).getRGB());
			}
		}
	}
	
	@Override
	public void initGui(GuiScreen gui) {
		this.colorButton = new ColorButton(110, 90, this);
		
	}
	
	@Override
	public void drawScreen(GuiScreen gui, int mouseX, int mouseY, float partialTicks) {
		colorButton.draw();
	}
	
	@Override
	public void mouseClicked(GuiScreen gui, int mouseX, int mouseY, int mouseButton) throws IOException {
		colorButton.onClick(mouseX, mouseY, mouseButton);
	}
	
	
	
}
