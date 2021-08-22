package impulse.hud.mod.impl;

import impulse.hud.mod.HudMod;

public class FPSMod extends HudMod {

	public FPSMod() {
		super("[FPS]", 5, 5, "Allows you to see your FPS.");
	}
	
	@Override
	public void draw() {
		
		fr.drawStringWithShadow("[FPS: " + mc.getDebugFPS() + "]", getX(), getY(), this.getColor());
		
		super.draw();
	}
	
	@Override
	public void renderDummy(int mouseX, int mouseY) {

		fr.drawStringWithShadow("[FPS: " + mc.getDebugFPS() + "]", getX(), getY(), this.getColor());
		
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
}
