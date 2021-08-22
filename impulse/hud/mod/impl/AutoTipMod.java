package impulse.hud.mod.impl;

import impulse.hud.mod.HudMod;

public class AutoTipMod extends HudMod {

	public AutoTipMod() {
		super("[Auto Tip]", 0, 0, "This mod runs Tip all command every 10 minuets!");
	}
	
	@Override
	public void draw() {
		
		super.draw();
	}
	
	@Override
	public void renderDummy(int mouseX, int mouseY) {

		super.renderDummy(mouseX, mouseY);
	}
	
	@Override
	public int getWidth() {
		return 0;
	}
	
	@Override
	public int getHeight() {
		return 0;
	}
}
