package impulse.cosmetics.toggle;

import impulse.hud.mod.HudMod;

public class CapeMod extends HudMod {

	public CapeMod() {
		super("[Cape]", 0, 0, "CosNoSettings:Enable and disable your cape!");
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
	
	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return super.isEnabled();
	}
}
