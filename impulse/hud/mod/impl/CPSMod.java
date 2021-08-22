package impulse.hud.mod.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.lwjgl.input.Mouse;

import impulse.hud.mod.HudMod;

public class CPSMod extends HudMod {
	
	
	private List<Long> clicksRMB = new ArrayList<Long>();
	private List<Long> clicksLMB = new ArrayList<Long>();
	private boolean pressedLMB;
	private boolean pressedRMB;
	private long lastPressedLMB;
	private long lastPressedRMB;
	
	
	public CPSMod() {
		super("[CPS]", 525, 5, "Allows you to see your Click Per Second.");
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
		
		fr.drawStringWithShadow("[10 | ", getX(), getY(), this.getColor());
		fr.drawStringWithShadow("10 CPS]", getX() + fr.getStringWidth("[10 | "), getY(), this.getColor());
		
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
		
		fr.drawStringWithShadow("[" + String.valueOf(this.clicksLMB.size()) + " | ", getX(), getY(), this.getColor());
		
		
		final long timeRMB = System.currentTimeMillis();
		for (int i = 0, j = 0; i < clicksRMB.size(); i++) {
		    if ((timeRMB - clicksRMB.get(i)) > 1000) {
		    	this.clicksRMB.remove(i);
		    }
		}
		
		fr.drawStringWithShadow(String.valueOf(this.clicksRMB.size()) + " CPS]", getX()  + fr.getStringWidth("[" + String.valueOf(this.clicksLMB.size()) + " | "), getY(), this.getColor());
		
	}
	
}
