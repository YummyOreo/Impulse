package impulse.cosmetics;

import impulse.Impulse;
import impulse.util.websockets.Connect;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;

public class CapeChecker {
	
	public static boolean ownsCape(AbstractClientPlayer entitylivingbaseIn) {
		if (!Impulse.INSTANCE.hudManager.capeMod.isEnabled()) {
			return false;
		}
		// sends a request if the server is enabled and they have not already sent a request
		if (Impulse.INSTANCE.capeEnabled == true && entitylivingbaseIn.getName() == Minecraft.getMinecraft().thePlayer.getName()) {
			return true;
		}
		return false;
	}

}
