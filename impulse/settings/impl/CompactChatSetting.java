package impulse.settings.impl;

import impulse.Impulse;
import impulse.hud.mod.HudMod;
import impulse.settings.SettingsMod;
import impulse.util.websockets.Connect;
import net.minecraft.client.Minecraft;

public class CompactChatSetting extends SettingsMod {

	private Minecraft mc = Minecraft.getMinecraft();
	
	public CompactChatSetting() {
		super("Compact Chat", true);
	}
}
