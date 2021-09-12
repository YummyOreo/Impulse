package impulse.hud.mod.impl;

import impulse.hud.mod.Catagory;
import impulse.hud.mod.HudMod;

public class FullBrightMod extends HudMod {

	public FullBrightMod() {
		super("[Fullbright]", 0, 0, "Allows you to see in dark spots!", Catagory.WORLD);
	}
	
	@Override
	public void onDisable() {
		// TODO Auto-generated method stub
		super.onDisable();
		mc.gameSettings.gammaSetting = 0f;
	}
	
	@Override
	public void onEnable() {
		// TODO Auto-generated method stub
		super.onEnable();
		mc.gameSettings.gammaSetting = 100f;
	}
	
	@Override
	public void load() {
		// TODO Auto-generated method stub
		super.load();
		if (this.isEnabled()) {
			mc.gameSettings.gammaSetting = 100f;
		} else {
			mc.gameSettings.gammaSetting = 0f;
		}
	}
}
