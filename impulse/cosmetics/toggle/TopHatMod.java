package impulse.cosmetics.toggle;

import impulse.hud.mod.HudMod;

public class TopHatMod extends HudMod {

	public TopHatMod() {
		super("[Top Hat]", 0, 0, "Cos:A top hat!");
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
