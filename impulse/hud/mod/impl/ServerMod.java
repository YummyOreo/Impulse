package impulse.hud.mod.impl;

import impulse.hud.mod.HudMod;

public class ServerMod extends HudMod {

	public ServerMod() {
		super("[Server Mod]", 555, 5, "Allows you to see your server.");
	}
	
	@Override
	public void draw() {
		
		if (mc.getCurrentServerData() != null) {
			fr.drawStringWithShadow("[" + mc.getCurrentServerData().serverIP + "]", getX(), getY(), this.getColor());
		} else {
			fr.drawStringWithShadow("[No Server]", getX(), getY(), this.getColor());
		}
		
		super.draw();
	}
	
	@Override
	public void renderDummy(int mouseX, int mouseY) {

		if (mc.getCurrentServerData() != null) {
			fr.drawStringWithShadow("[" + mc.getCurrentServerData().serverIP + "]", getX(), getY(), this.getColor());
		} else {
			fr.drawStringWithShadow("[No Server]", getX(), getY(), this.getColor());
		}
		
		super.renderDummy(mouseX, mouseY);
	}
	
	@Override
	public int getWidth() {
		if (mc.getCurrentServerData() != null) {
			return fr.getStringWidth("[" + mc.getCurrentServerData().serverIP + "]");
		} else {
			return fr.getStringWidth("[No Server]");
		}
	}
	
	@Override
	public int getHeight() {
		return fr.FONT_HEIGHT;
	}
}
