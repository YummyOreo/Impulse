package impulse.cosmetics;

import impulse.Impulse;
import impulse.hud.mod.HudManager;
import impulse.util.cache.CacheIsUser;
import impulse.util.cache.cosmetic.CacheTopHat;
import impulse.util.websockets.Connect;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;

public class CosmeticController {

	private static boolean checkIsUser(AbstractClientPlayer player) {
		if (player.getName().equals(Minecraft.getMinecraft().thePlayer.getName())) return true;
		if (CacheIsUser.CONSTANT_MAP_IsUser.get(player.getName()) == null) return false;
		if (CacheIsUser.CONSTANT_MAP_IsUser.get(player.getName()) == false) return false;
		return true;
	}
	
	public static String shouldRenderTopHat(AbstractClientPlayer player) {
		if (Minecraft.getMinecraft().thePlayer.ticksExisted < 20) return "false";
		if (checkIsUser(player) == false) return "false";
		if (CacheTopHat.CONSTANT_MAP.get(player.getName()) == null || player.getName().equals(Minecraft.getMinecraft().thePlayer.getName())) {
			if(player.getName().equals(Minecraft.getMinecraft().thePlayer.getName()) && Impulse.INSTANCE.hudManager.topHatMod.isEnabled() == true) {
				CacheTopHat.CONSTANT_MAP.put(player.getName(), "true");
				CacheTopHat.CONSTANT_MAP_TIME.put(player.getName(), (int) System.currentTimeMillis());
				return "true";
			} else if (player.getName().equals(Minecraft.getMinecraft().thePlayer.getName()) == false && Connect.INSTANCE.enabled) {
				String res = Impulse.INSTANCE.socketClient.checkUserCosmetic(player.getName(), "[TopHat]");
				if (!res.equals("false")) {
					CacheTopHat.CONSTANT_MAP.put(player.getName(), res);
					CacheTopHat.CONSTANT_MAP_TIME.put(player.getName(), (int) System.currentTimeMillis());
					return res;
				}
			}
				
			CacheTopHat.CONSTANT_MAP.put(player.getName(), "false");
			CacheTopHat.CONSTANT_MAP_TIME.put(player.getName(), (int) System.currentTimeMillis());
			return "false";
		} else {
			return CacheTopHat.CONSTANT_MAP.get(player.getName());
		}
		
	}
	
	public static float[] getTopHaColor(AbstractClientPlayer player) {
		return new float[] {1, 0, 0};
	}
	
}
