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

public class ToggleStrintGui extends HudMod {
	
	private BackgroundButton backgroundButton;
	private ColorButton colorButton;
	
	private static String toggeledString = "[Strint Toggle: Disabled]";
	private static boolean toggeled = false;
	
	public ToggleStrintGui() {
		super("[Toggle Sprint]", 5, 320, "Allows you to see if you have toggel sprint on (and turn it on and off).", Catagory.HUD);
	}
	
	
	@Override
	public void draw() {
		
		if (this.getBackground()) {
			Gui.drawRect(this.getX() - 1, this.getY() - 1, this.getX() + fr.getStringWidth(checkSprint()) + 1, this.getY() + fr.FONT_HEIGHT , new Color(0, 0, 0, 150).getRGB());
		}
		
		if (this.getRainbow()) {
			GuiUtils.drawChromaString(checkSprint(), getX(), getY(), true);
		} else {
			fr.drawStringWithShadow(checkSprint(), getX(), getY(), this.getColor());
		}
		
		super.draw();
	}
	
	@Override
	public void renderDummy(int mouseX, int mouseY) {

		if (this.getBackground()) {
			Gui.drawRect(this.getX() - 1, this.getY() - 1, this.getX() + fr.getStringWidth(toggeledString) + 1, this.getY() + fr.FONT_HEIGHT , new Color(0, 0, 0, 150).getRGB());
		}
		
		if (this.getRainbow()) {
			GuiUtils.drawChromaString(toggeledString, getX(), getY(), true);
		} else {
			fr.drawStringWithShadow(toggeledString, getX(), getY(), this.getColor());
		}
		
		super.renderDummy(mouseX, mouseY);
	}
	
	@Override
	public int getWidth() {
		return fr.getStringWidth(checkSprint());
	}
	
	@Override
	public int getHeight() {
		return fr.FONT_HEIGHT;
	}
	
	private String checkSprint() {
		if (toggeled == true) {
			return toggeledString;
		} else {
			try {
				if (mc.thePlayer.isSprinting()) {
					return "[Strint Toggle: Vanilla]";
				} else {
					return toggeledString;
				}
			}
			catch(Exception e) {
				return toggeledString;
			}
			
		}
	}
	
	public static void disableSprintToggleHUD() {
		toggeled = false;
		toggeledString = "[Strint Toggle: Disabled]";
	}
	
	public static void enableSprintToggleHUD() {
		toggeled = true;
		toggeledString = "[Strint Toggle: Enabled]";
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
