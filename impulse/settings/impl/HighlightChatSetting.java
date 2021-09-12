package impulse.settings.impl;

import impulse.Impulse;
import impulse.hud.mod.HudMod;
import impulse.settings.SettingsMod;
import impulse.util.websockets.Connect;
import net.minecraft.client.Minecraft;

public class HighlightChatSetting extends SettingsMod {

	private Minecraft mc = Minecraft.getMinecraft();
	
	public HighlightChatSetting() {
		super("Highlight Name In Chat", true);
	}
	
	@Override
	public void setValue(Object value) {
		super.setValue(value);
	}


}
