package impulse.hud.mod.impl;

import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.lwjgl.input.Mouse;

import impulse.hud.mod.Catagory;
import impulse.hud.mod.HudMod;
import impulse.hud.mod.ui.buttons.BackgroundButton;
import impulse.hud.mod.ui.buttons.ColorButton;
import impulse.util.ui.GuiUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiScreen;

public class CPSMod extends HudMod {
	
	private BackgroundButton backgroundButton;
	private ColorButton colorButton;
	
	private List<Long> clicksRMB = new ArrayList<Long>();
	private List<Long> clicksLMB = new ArrayList<Long>();
	private boolean pressedLMB;
	private boolean pressedRMB;
	private long lastPressedLMB;
	private long lastPressedRMB;
	
	
	public CPSMod() {
		super("[CPS]", 525, 5, "Allows you to see your Click Per Second.", Catagory.HUD);
		//this.addTag("Color");
		//this.addTag("Background");
	}

	
	@Override
	public void draw() {
		
		if (Mouse.isButtonDown(0) != this.pressedLMB) {
			
			this.lastPressedLMB = System.currentTimeMillis();
			this.pressedLMB = Mouse.isButtonDown(0);
			
			if (Mouse.isButtonDown(0)) {
				this.clicksLMB.add(this.lastPressedLMB);
			}
		}
		if (Mouse.isButtonDown(1) != this.pressedRMB) {
			
			this.lastPressedRMB = System.currentTimeMillis();
			this.pressedRMB = Mouse.isButtonDown(1);
			
			if (Mouse.isButtonDown(1)) {
				this.clicksRMB.add(this.lastPressedRMB);
			}
		}
		
		cps();
		
		super.draw();
	}
	
	@Override
	public void renderDummy(int mouseX, int mouseY) {
		
		if (this.getBackground()) {
			Gui.drawRect(this.getX() - 1, this.getY() - 1, this.getX() + fr.getStringWidth("[10 | 10 CPS]") + 1, this.getY() + fr.FONT_HEIGHT , new Color(0, 0, 0, 150).getRGB());
		}
		
		if (this.getRainbow()) {
			GuiUtils.drawChromaString("[10 | ", getX(), getY(), true);
			GuiUtils.drawChromaString("10 CPS]", getX() + fr.getStringWidth("[10 | "), getY(), true);
		} else {
			fr.drawStringWithShadow("[10 | ", getX(), getY(), this.getColor());
			fr.drawStringWithShadow("10 CPS]", getX() + fr.getStringWidth("[10 | "), getY(), this.getColor());
		}
		
		super.renderDummy(mouseX, mouseY);
	}
	
	@Override
	public int getWidth() {
		return fr.getStringWidth("[10 | 10 CPS]");
	}
	
	@Override
	public int getHeight() {
		return fr.FONT_HEIGHT;
	}
	
	private void cps() {
		
		
		
		final long timeLMB = System.currentTimeMillis();
		for (int i = 0, j = 0; i < clicksLMB.size(); i++) {
		    if ((timeLMB - clicksLMB.get(i)) > 1000) {
		    	this.clicksLMB.remove(i);
		    }
		}		
		
		final long timeRMB = System.currentTimeMillis();
		for (int i = 0, j = 0; i < clicksRMB.size(); i++) {
		    if ((timeRMB - clicksRMB.get(i)) > 1000) {
		    	this.clicksRMB.remove(i);
		    }
		}
		
		if (this.getBackground()) {
			Gui.drawRect(this.getX() - 1, this.getY() - 1, this.getX() + fr.getStringWidth(String.valueOf(this.clicksRMB.size()) + " CPS]" + "[" + String.valueOf(this.clicksLMB.size()) + " | ") + 1, this.getY() + fr.FONT_HEIGHT , new Color(0, 0, 0, 150).getRGB());
		}
		
		if (this.getRainbow()) {
			GuiUtils.drawChromaString("[" + String.valueOf(this.clicksLMB.size()) + " | ", getX(), getY(), true);
			GuiUtils.drawChromaString(String.valueOf(this.clicksRMB.size()) + " CPS]", getX()  + fr.getStringWidth("[" + String.valueOf(this.clicksLMB.size()) + " | "), getY(), true);
		} else {
		
			fr.drawStringWithShadow("[" + String.valueOf(this.clicksLMB.size()) + " | ", getX(), getY(), this.getColor());
			
			fr.drawStringWithShadow(String.valueOf(this.clicksRMB.size()) + " CPS]", getX()  + fr.getStringWidth("[" + String.valueOf(this.clicksLMB.size()) + " | "), getY(), this.getColor());
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
