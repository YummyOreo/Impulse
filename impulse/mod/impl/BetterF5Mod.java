package impulse.mod.impl;

import impulse.mod.Catagory;
import impulse.mod.Mod;

public class BetterF5Mod extends Mod {
	
	private int F5;

	public BetterF5Mod() {
		super("Better F5", "Better F5", Catagory.MISC);
		this.F5 = 0;
	}
	
	@Override
	public void onEnable() {
		mc.gameSettings.thirdPersonView = this.F5;
		super.onEnable();
	}
	
	@Override
	public void onDisable() {
		mc.gameSettings.thirdPersonView = 0;
		super.onDisable();
	}
	
	public void setF5(int F5) {
		this.F5 = F5;

	}
}
