package impulse.hud.mod.impl;

import impulse.hud.mod.HudMod;

public class ImpulseServerMod extends HudMod {

	public ImpulseServerMod() {
		super("[Impulse Servers]", 0, 0, "Allows you to turn off and on connection to Impulse servers.");
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
