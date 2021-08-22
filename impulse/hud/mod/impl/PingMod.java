package impulse.hud.mod.impl;

import java.awt.Color;

import impulse.event.EventTarget;
import impulse.event.impl.ClientTick;
import impulse.hud.HUDConfigScreen;
import impulse.hud.mod.HudMod;

public class PingMod extends HudMod {

	public PingMod() {
		super("[Ping Mod]", 120, 5, "Allows you to see your current ping.");
		
	}
	
	@Override
	public void draw() {

		try {
			fr.drawStringWithShadow("[" + String.valueOf(mc.getNetHandler().getPlayerInfo(mc.thePlayer.getUniqueID()).getResponseTime()) +  " ms]", getX(), getY(), this.getColor());
		} catch (Exception e) {
			fr.drawStringWithShadow("[0]", getX(), getY(), this.getColor());
		}
		
		super.draw();
	}
	
	@Override
	public void renderDummy(int mouseX, int mouseY) {
		
		fr.drawStringWithShadow("[100 ms]", getX(), getY(), this.getColor());
		
		super.renderDummy(mouseX, mouseY);
	}
	
	@Override
	public int getWidth() {
		return fr.getStringWidth("[100 ms]");
	}
	
	@Override
	public int getHeight() {
		return fr.FONT_HEIGHT;
	}
}
