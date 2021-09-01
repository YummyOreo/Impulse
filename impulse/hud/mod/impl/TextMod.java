package impulse.hud.mod.impl;

import impulse.hud.mod.HudMod;

public class TextMod extends HudMod {

	public TextMod() {
		super("[Text Mod]", 555, 5, "Filler");
		this.addTag("Color");
	}
	
	@Override
	public void draw() {
		
		fr.drawStringWithShadow("[Hacks: Enabled]", getX(), getY(), -1);
		
		super.draw();
	}
	
	@Override
	public void renderDummy(int mouseX, int mouseY) {

		fr.drawStringWithShadow("[Hacks: Enabled]", getX(), getY(), -1);
		
		super.renderDummy(mouseX, mouseY);
	}
	
	@Override
	public int getWidth() {
		return fr.getStringWidth("[Hacks: Enabled]");
	}
	
	@Override
	public int getHeight() {
		return fr.FONT_HEIGHT;
	}
}
