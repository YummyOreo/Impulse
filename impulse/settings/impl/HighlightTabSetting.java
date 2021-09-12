package impulse.settings.impl;

import impulse.Impulse;
import impulse.hud.mod.HudMod;
import impulse.settings.SettingsMod;
import impulse.util.websockets.Connect;
import net.minecraft.client.Minecraft;

public class HighlightTabSetting extends SettingsMod {

	private Minecraft mc = Minecraft.getMinecraft();
	
	public HighlightTabSetting() {
		super("Highlight Name In Tab", true);
	}
	
	@Override
	public void setValue(Object value) {
		super.setValue(value);
	}


}
