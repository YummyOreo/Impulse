package impulse.mod.impl;

import impulse.event.EventTarget;
import impulse.event.impl.MouseEvent;
import impulse.mod.Catagory;
import impulse.mod.Mod;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;

public class ProgectLMod extends Mod {

	public ProgectLMod() {
		super("Progect L", "Fixes lots of bugs", Catagory.FIXES);
		this.setEnabled(true);
	}
	
	@EventTarget
	public void onMouse(MouseEvent event) {
		if (this.isEnabled() && (event.dx != 0 || event.dy != 0)) {
            EntityPlayerSP entity = Minecraft.getMinecraft().thePlayer;

            if (entity != null) {
                entity.prevRenderYawOffset = entity.renderYawOffset;
                entity.prevRotationYawHead = entity.rotationYawHead;
                entity.prevRotationYaw = entity.rotationYaw;
                entity.prevRotationPitch = entity.rotationPitch;
            }
        }
	}
	
}
