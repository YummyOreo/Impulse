package impulse.hud.mod.impl;

import impulse.hud.mod.Catagory;
import impulse.hud.mod.HudMod;

public class PerspectiveMod extends HudMod {

	public PerspectiveMod() {
		super("[Freelook]", 0, 0, "Allows you to look in 360 veiw!", Catagory.MISC);
		this.addDisabled("hypixel.net");
		this.addDisabled("mc.hypixel.net");
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
