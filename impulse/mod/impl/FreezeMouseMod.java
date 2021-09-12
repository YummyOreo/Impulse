package impulse.mod.impl;

import org.lwjgl.opengl.Display;

import impulse.Impulse;
import impulse.mod.Catagory;
import impulse.mod.Mod;

public class FreezeMouseMod extends Mod {

	public FreezeMouseMod() {
		super("Freeze Mod", "Freeze Mod", Catagory.WORLD);
	}

	public boolean ovverideMouse() {
		if (mc.inGameHasFocus && Display.isActive()) {

			if (!this.isEnabled()) return false;
			
			mc.mouseHelper.mouseXYChange();
			float f1 = mc.gameSettings.mouseSensitivity * 0.6F + 0.2F;
			float f2 = f1 * f1 * f1 * 8.0F;
			float f3 = (float) mc.mouseHelper.deltaX * f2;
			float f4 = (float) mc.mouseHelper.deltaY * f2;
			
		}
		
		return true;
	}
}
