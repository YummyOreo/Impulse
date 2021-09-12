package impulse.mod.impl;

import impulse.Impulse;
import impulse.event.EventTarget;
import impulse.event.impl.EventUpdate;
import impulse.hud.mod.impl.ToggleStrintGui;
import impulse.mod.Catagory;
import impulse.mod.Mod;
import net.minecraft.potion.Potion;

public class ToggleStrint extends Mod {

	public ToggleStrint() {
		super("ToggleStrint", "Toggle Your Strint", Catagory.MISC);
	}
	
	@EventTarget
	public void onUpdate(EventUpdate event) {
		if (this.isEnabled() 
				&& !mc.thePlayer.isBlocking() 
				&& !mc.thePlayer.isSneaking() 
				&& (mc.thePlayer.motionX != 0
				&& mc.thePlayer.motionZ != 0) 
				&& !mc.thePlayer.isCollidedHorizontally
				&& !mc.thePlayer.isPotionActive(Potion.moveSlowdown)
				&& !mc.thePlayer.isPotionActive(Potion.confusion)
				&& !mc.gameSettings.keyBindBack.isKeyDown()
				&& Impulse.INSTANCE.hudManager.toggleStrintGui.isEnabled()) {
			
			ToggleStrintGui.enableSprintToggleHUD();
			
			mc.thePlayer.setSprinting(true);
		} else {
			
			mc.thePlayer.setSprinting(false);
		}
		
		if (this.isEnabled() && Impulse.INSTANCE.hudManager.toggleStrintGui.isEnabled()) {
			ToggleStrintGui.enableSprintToggleHUD();
		}
		
	}
	
	
	@Override
	public void onDisable() {
		super.onDisable();
		
		ToggleStrintGui.disableSprintToggleHUD();
		
		mc.thePlayer.setSprinting(false);
	}
	
}
