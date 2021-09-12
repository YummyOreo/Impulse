package impulse.settings.impl;

import impulse.Impulse;
import impulse.hud.mod.HudMod;
import impulse.settings.SettingsMod;
import impulse.util.websockets.Connect;
import net.minecraft.client.Minecraft;

public class ImpulseServerSetting extends SettingsMod {

	private Minecraft mc = Minecraft.getMinecraft();
	
	public ImpulseServerSetting() {
		super("Impulse Servers", true);
	}
	
	@Override
	public void setValue(Object value) {
		super.setValue(value);
		
		if ((boolean) value == true) {
			this.onEnable();
		} else {
			this.onDisable();
		}
		
	}

	private void onDisable() {
		Connect.INSTANCE.enabled = false;
		if (mc.thePlayer != null) {
			Impulse.INSTANCE.socketClient.removeUser(mc.thePlayer.getName());
		}
	}

	private void onEnable() {
		Connect.INSTANCE.connectToServer();
		if (mc.thePlayer != null) {
			if (Connect.INSTANCE.enabled) {
				System.out.println(Impulse.INSTANCE.socketClient.addUser(mc.thePlayer.getName()));
				for (HudMod m : Impulse.INSTANCE.hudManager.hudMods) {
					if (m.name.equals("[Top Hat]")) {
						if (m.enabled && Connect.INSTANCE.enabled) {
							Impulse.INSTANCE.socketClient.addCosmetic(mc.thePlayer.getName(), this.name.replace(" ", "") + "%3A" + m.getColor());
						}
					}
				}
			}
		}
	}

}
