package impulse.hud.mod.impl;

import impulse.hud.mod.HudMod;

public class PositionMod extends HudMod {

	public PositionMod() {
		super("[Position Mod]", 240, 5, "Allows you to see your current position.");
	}
	
	@Override
	public void draw() {
		
		fr.drawStringWithShadow("[" + getXPos() + " | " + getZPos() + "]", getX(), getY(), this.getColor());
		
		super.draw();
	}
	
	@Override
	public void renderDummy(int mouseX, int mouseY) {

		fr.drawStringWithShadow("[10 | 10]", getX(), getY(), this.getColor());
		
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
}
