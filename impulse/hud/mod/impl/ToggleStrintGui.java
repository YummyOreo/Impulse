package impulse.hud.mod.impl;

import impulse.hud.mod.HudMod;

public class ToggleStrintGui extends HudMod {
	
	private static String toggeledString = "[Strint Toggle: Disabled]";
	private static boolean toggeled = false;
	
	public ToggleStrintGui() {
		super("[Strint Toggle]", 5, 320, "Allows you to see if you have toggel sprint on (and turn it on and off).");
	}
	
	
	@Override
	public void draw() {
		
		fr.drawStringWithShadow(checkSprint(), getX(), getY(), this.getColor());
		
		super.draw();
	}
	
	@Override
	public void renderDummy(int mouseX, int mouseY) {

		fr.drawStringWithShadow(toggeledString, getX(), getY(), this.getColor());
		
		super.renderDummy(mouseX, mouseY);
	}
	
	@Override
	public int getWidth() {
		return fr.getStringWidth(checkSprint());
	}
	
	@Override
	public int getHeight() {
		return fr.FONT_HEIGHT;
	}
	
	private String checkSprint() {
		if (toggeled == true) {
			return toggeledString;
		} else {
			try {
				if (mc.thePlayer.isSprinting()) {
					return "[Strint Toggle: Vanilla]";
				} else {
					return toggeledString;
				}
			}
			catch(Exception e) {
				return toggeledString;
			}
			
		}
	}
	
	public static void disableSprintToggleHUD() {
		toggeled = false;
		toggeledString = "[Strint Toggle: Disabled]";
	}
	
	public static void enableSprintToggleHUD() {
		toggeled = true;
		toggeledString = "[Strint Toggle: Enabled]";
	}
	
}
