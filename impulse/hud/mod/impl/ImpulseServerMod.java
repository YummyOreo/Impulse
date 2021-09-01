package impulse.hud.mod.impl;

import impulse.Impulse;
import impulse.hud.mod.HudMod;
import impulse.util.websockets.Connect;

public class ImpulseServerMod extends HudMod {

	public ImpulseServerMod() {
		super("[Impulse Servers]", 0, 0, "Allows you to turn off and on connection to Impulse servers.");
	}
	
	@Override
	public void draw() {
		
		super.draw();
	}
	
	@Override
	public void renderDummy(int mouseX, int mouseY) {

		super.renderDummy(mouseX, mouseY);
	}
	
	@Override
	public int getWidth() {
		return 0;
	}
	
	@Override
	public int getHeight() {
		return 0;
	}
	
	@Override
	public void firstLoad() {
		super.firstLoad();
		this.enabled = true;
	}
	
	@Override
	public void onEnable() {
		// TODO Auto-generated method stub
		super.onEnable();
		Connect.INSTANCE.connectToServer();
		if (mc.thePlayer != null) {
			if (Connect.INSTANCE.enabled) {
				System.out.println(Impulse.INSTANCE.socketClient.addUser(mc.thePlayer.getName()));
				for (HudMod m : Impulse.INSTANCE.hudManager.hudMods) {
					if (m.name.equals("[Top Hat]")) {
						if (m.enabled && Connect.INSTANCE.enabled) {
							Impulse.INSTANCE.socketClient.addCosmetic(mc.thePlayer.getName(), this.name.replace(" ", "" + "%3A" + this.getColor()));
						}
					}
				}
			}
		}
	}
	
	@Override
	public void onDisable() {
		super.onDisable();
		Connect.INSTANCE.enabled = false;
		if (mc.thePlayer != null) {
			Impulse.INSTANCE.socketClient.removeUser(mc.thePlayer.getName());
		}
	}
}
