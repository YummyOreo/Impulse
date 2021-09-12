package impulse.hud.mod.utils;

import impulse.Impulse;
import net.minecraft.client.Minecraft;

public class Reset {
	
	public static void save() {
		Impulse.INSTANCE.hasReset = true;
		Impulse.INSTANCE.config.saveResetConfig();
		Minecraft.getMinecraft().shutdown();
	}
	
}
