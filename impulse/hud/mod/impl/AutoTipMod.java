package impulse.hud.mod.impl;

import java.util.HashMap;

import impulse.Impulse;
import impulse.event.Event;
import impulse.event.impl.ChatReceivedEvent;
import impulse.hud.mod.Catagory;
import impulse.hud.mod.HudMod;
import impulse.hud.mod.utils.Loader;

public class AutoTipMod extends HudMod {
	
	private long lastTip = 0;
	
	public AutoTipMod() {
		
		super("[Auto Tip]", 0, 0, "This mod runs Tip all command every 10 minuets!", Catagory.CHAT);
		
	}
	
	public void eventPog(String event, Event e) {
		if(event.equals("chat")) {
			if ((System.currentTimeMillis() - lastTip) > 600000 && mc.getCurrentServerData() != null && this.isEnabled()) {
				if (mc.getCurrentServerData().serverIP.contains("hypixel.net")) {
					System.out.println("Tiped");
					mc.thePlayer.sendChatMessage("/tipall");
					lastTip = System.currentTimeMillis();
				}

			}
		}
	}
	
}
