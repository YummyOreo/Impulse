package impulse.mod.impl;

import impulse.mod.Catagory;
import impulse.mod.Mod;

public class TestMod extends Mod {

	public TestMod() {
		super("TestMod", "TestMod", Catagory.MISC);
	}
	
	@Override
	public void onEnable() {
		super.onEnable();
		
		System.out.println("THIS MOD IS ONNNNN");

	}
	
}
