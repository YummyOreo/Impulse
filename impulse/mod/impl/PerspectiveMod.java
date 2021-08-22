package impulse.mod.impl;

import org.lwjgl.opengl.Display;

import impulse.Impulse;
import impulse.mod.Catagory;
import impulse.mod.Mod;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;

public class PerspectiveMod extends Mod {

	public static PerspectiveMod INSTANCE = new PerspectiveMod();
	
	public PerspectiveMod() {
		super("PerspectiveMod", "PerspectiveMod", Catagory.MISC);
	}
	
	public static boolean enabled = true;
	
	private float cameraYaw = 0F;
	private float cameraPitch = 0F;
	
	@Override
	public void onEnable() {
		super.onEnable();
		
		if (Impulse.INSTANCE.hudManager.perspectiveMod.checkDisabled() || !Impulse.INSTANCE.hudManager.perspectiveMod.isEnabled()) return;

		mc.gameSettings.thirdPersonView = 1;

	}
	
	public float getCameraYaw() {
		return Impulse.INSTANCE.modManager.perspectiveMod.isEnabled() ? cameraYaw : mc.thePlayer.rotationYaw;
	}
	
	public float getCameraPitch() {
		return Impulse.INSTANCE.modManager.perspectiveMod.isEnabled() ? cameraPitch : mc.thePlayer.rotationPitch;
	}
	
	public boolean ovverideMouse() {
		
		if (mc.inGameHasFocus && Display.isActive()) {

			if (!Impulse.INSTANCE.modManager.perspectiveMod.isEnabled() || Impulse.INSTANCE.hudManager.perspectiveMod.checkDisabled()) return true;
			
			mc.mouseHelper.mouseXYChange();
			float f1 = mc.gameSettings.mouseSensitivity * 0.6F + 0.2F;
			float f2 = f1 * f1 * f1 * 8.0F;
			float f3 = (float) mc.mouseHelper.deltaX * f2;
			float f4 = (float) mc.mouseHelper.deltaY * f2;
			
			cameraYaw += f3 * 0.15F;
			cameraPitch += f4 * 0.15F;
			
			if (cameraPitch > 90) cameraPitch = 90;
			if (cameraPitch < -90) cameraPitch = -90;
			
			if (cameraPitch > 90) {
				cameraPitch = 90;
			}
			
		}
		
		return false;
		
	}
	
	@Override
	public void toggle() {
		if (Impulse.INSTANCE.hudManager.perspectiveMod.checkDisabled()) return;
		super.toggle();
		//		

	}
	
	@Override
	public void onDisable() {
		super.onDisable();
		mc.gameSettings.thirdPersonView = 0;
	}
}
