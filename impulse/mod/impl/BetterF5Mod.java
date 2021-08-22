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
		super.onEnable();
		
		mc.gameSettings.thirdPersonView = this.F5;

	}
	
	@Override
	public void onDisable() {
		super.onDisable();
		mc.gameSettings.thirdPersonView = 0;
	}
	
	public void setF5(int F5) {
		this.F5 = F5;

	}
}
