package impulse.mod;

import java.util.ArrayList;

import impulse.mod.impl.*;

public class ModManager {
	
	//Adds all the mods to a array for safe keeping
	
	public BetterF5Mod betterF5Mod;
	public ToggleStrint toggleStrint;
	public PerspectiveMod perspectiveMod;
	public FreezeMouseMod freezeMouseMod;
	public ProgectLMod progectLMod;
	
	public ArrayList<Mod> mods;
	
	public ModManager() {
		mods =  new ArrayList<>();
		
		// Misc
		
		mods.add(betterF5Mod = new BetterF5Mod());
		mods.add(toggleStrint = new ToggleStrint());
		mods.add(perspectiveMod = new PerspectiveMod());
		mods.add(freezeMouseMod = new FreezeMouseMod());
		mods.add(progectLMod = new ProgectLMod());
	}
	
}
