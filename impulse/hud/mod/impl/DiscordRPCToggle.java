package impulse.hud.mod.impl;

import impulse.discord.Discordrpc;
import impulse.hud.mod.HudMod;

public class DiscordRPCToggle extends HudMod {

	public DiscordRPCToggle() {
		super("[Discord RPC]", 0, 0, "Allows you to turn off and on discord RPC.");
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
		super.onEnable();
		Discordrpc.startDiscordRPC();
	}
	
	@Override
	public void onDisable() {
		super.onDisable();
		Discordrpc.stopDiscordRPC();
	}
}
