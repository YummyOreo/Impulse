package impulse.util.ui;

import net.minecraft.client.renderer.GlStateManager;

public class Texture {
	public static void resetTextureState() {
		GlStateManager.textureState[GlStateManager.activeTextureUnit].textureName = -1;
	}
}
