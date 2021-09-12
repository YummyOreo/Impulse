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
import net.minecraft.util.EnumChatFormatting;

public class HealthMod extends HudMod {

	private BackgroundButton backgroundButton;
	private ColorButton colorButton;
	
	public HealthMod() {
		super("[Health Counter]", 555, 5, "Shows you your health as a number!", Catagory.HUD);
	}
	
	@Override
	public void draw() {
		
		if (this.getBackground()) {
			try {
				Gui.drawRect(this.getX() - 1, this.getY() - 1, this.getX() + fr.getStringWidth((int) Minecraft.getMinecraft().thePlayer.getHealth() + "" + EnumChatFormatting.RED + " \u2764") + 1, this.getY() + fr.FONT_HEIGHT , new Color(0, 0, 0, 150).getRGB());
			} catch (Exception e) {
				Gui.drawRect(this.getX() - 1, this.getY() - 1, this.getX() + fr.getStringWidth("0" + EnumChatFormatting.RED + " \u2764") + 1, this.getY() + fr.FONT_HEIGHT , new Color(0, 0, 0, 150).getRGB());
			}
		}
		
		if (this.getRainbow()) {
			try {
				GuiUtils.drawChromaString((int) Minecraft.getMinecraft().thePlayer.getHealth() + " \u2764", getX(), getY(), true);
			} catch (Exception e) {
				GuiUtils.drawChromaString("0" + " \u2764", getX(), getY(), true);
			}
		} else {
			try {
				fr.drawStringWithShadow((int) Minecraft.getMinecraft().thePlayer.getHealth() + "" + EnumChatFormatting.RED + " \u2764", getX(), getY(), this.getColor());
			} catch (Exception e) {
				fr.drawStringWithShadow("0" + EnumChatFormatting.RED + " \u2764", getX(), getY(), this.getColor());
				e.printStackTrace();
			}
		}
		
		super.draw();
	}
	
	@Override
	public void renderDummy(int mouseX, int mouseY) {

		if (this.getBackground()) {
			try {
				Gui.drawRect(this.getX() - 1, this.getY() - 1, this.getX() + fr.getStringWidth((int) Minecraft.getMinecraft().thePlayer.getHealth() + "" + EnumChatFormatting.RED + " \u2764") + 1, this.getY() + fr.FONT_HEIGHT , new Color(0, 0, 0, 150).getRGB());
			} catch (Exception e) {
				Gui.drawRect(this.getX() - 1, this.getY() - 1, this.getX() + fr.getStringWidth("0" + EnumChatFormatting.RED + " \u2764") + 1, this.getY() + fr.FONT_HEIGHT , new Color(0, 0, 0, 150).getRGB());
			}
		}
		
		if (this.getRainbow()) {
			try {
				GuiUtils.drawChromaString((int) Minecraft.getMinecraft().thePlayer.getHealth() + " \u2764", getX(), getY(), true);
			} catch (Exception e) {
				GuiUtils.drawChromaString("0" + " \u2764", getX(), getY(), true);
			}
		} else {
			try {
				fr.drawStringWithShadow((int) Minecraft.getMinecraft().thePlayer.getHealth() + "" + EnumChatFormatting.RED + " \u2764", getX(), getY(), this.getColor());
			} catch (Exception e) {
				fr.drawStringWithShadow("0" + EnumChatFormatting.RED + " \u2764", getX(), getY(), this.getColor());
			}
		}
		
		super.renderDummy(mouseX, mouseY);
	}
	
	@Override
	public int getWidth() {
			return fr.getStringWidth("20" + EnumChatFormatting.RED + " \u2764");
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
