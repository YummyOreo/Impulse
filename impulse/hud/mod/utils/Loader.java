package impulse.hud.mod.utils;

import java.util.HashMap;

import impulse.Impulse;

public class Loader {
	
	public static Object loadVar(String name, String var, Object Default) {
		try {
			Object returnVal = Impulse.INSTANCE.config.config.get(name + " " + var);
			if (returnVal == null) {
				return Default;
			}
			
			return returnVal;
		} catch (Exception e) {
			e.printStackTrace();
			return Default;
		}
	}
	
	public static Object loadSetting(String modName, String name, Object reg) {
		try {
			if (Impulse.INSTANCE.config.config.getSetting(modName + " settings").get(name) == null) {
				return reg;
			}
			return Impulse.INSTANCE.config.config.getSetting(modName + " settings").get(name);
		}catch (Exception e) {
			e.printStackTrace();
			return reg;
		}
	}
	
}
