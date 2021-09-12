package impulse.cosmetics.impl;

import java.awt.Color;

import org.lwjgl.opengl.GL11;

import impulse.Impulse;
import impulse.cosmetics.CosmeticBase;
import impulse.cosmetics.CosmeticController;
import impulse.cosmetics.CosmeticModelBase;
import impulse.cosmetics.textureManager;
import impulse.cosmetics.toggle.TopHatMod;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class CosmeticTopHat extends CosmeticBase {
	
	private final ModelTopHat modelTopHat;
	private static final ResourceLocation TEXTURE = new ResourceLocation(textureManager.INSTANCE.clientName + "/" + textureManager.INSTANCE.topHat);
	
	public CosmeticTopHat(RenderPlayer playerRender) {
		super(playerRender);
		modelTopHat = new ModelTopHat(playerRender);
	}

	@Override
	public void render(AbstractClientPlayer player, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netheadYaw, float headPitch, float scale) {
		String colorGet = CosmeticController.shouldRenderTopHat(player);
		if(colorGet.equals("true") || colorGet.contains("TopHat")) {
			
			GlStateManager.pushMatrix();
			playerRenderer.bindTexture(TEXTURE);
			
			if(player.isSneaking()) {
				GL11.glTranslated(0, 0.225F, 0);
			}
			
			int topHatColor;
			
			if (colorGet.equals("true")) {
				topHatColor = Impulse.INSTANCE.hudManager.topHatMod.getColor();
			} else {
				topHatColor = Integer.parseInt(colorGet.split(":")[1]);
			}
			
			float[] color = CosmeticController.getTopHaColor(player);
			if (topHatColor == new Color(255, 0, 0).getRGB()) {
				GL11.glColor3f(1, 0, 0);
			} else if (topHatColor == new Color(255, 255, 255).getRGB()) {
				GL11.glColor3f(255, 255, 255);
			} else if (topHatColor == new Color(0, 0, 0).getRGB()) {
				GL11.glColor3f(0, 0, 0);
			} else if (topHatColor == new Color(255, 255, 255).getRGB()) {
				GL11.glColor3f(255, 255, 255);
			} else if (topHatColor == new Color(0, 255, 0).getRGB()) {
				GL11.glColor3f(0, 255, 0);
			} else if (topHatColor == new Color(0, 0, 255).getRGB()) {
				GL11.glColor3f(0, 0, 255);
			} else {
				Color color1 = new Color(topHatColor);
				GL11.glColor3f(color1.getRed(), color1.getGreen(), color1.getBlue());
			}
			
			modelTopHat.render(player, limbSwing, limbSwingAmount, ageInTicks, netheadYaw, headPitch, scale);
			GL11.glColor3f(1, 1, 1);
			GL11.glPopMatrix();
			
		}
		
	}
	
	private class ModelTopHat extends CosmeticModelBase {
		
		private ModelRenderer rim;
		private ModelRenderer top;

		public ModelTopHat(RenderPlayer player) {
			super(player);
			rim = new ModelRenderer(playerModel, 0, 0);
			rim.addBox(-5.5F, -9.2F, -5.5F, 11, 2, 11);
			top = new ModelRenderer(playerModel, 0, 13);
			top.addBox(-3.5F, -17.2F, -3.5F, 7, 8, 7);
			
		}
		
		@Override
		public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float headYaw, float headPitch, float scale) {
			
			GlStateManager.pushMatrix();
			
			GlStateManager.rotate((int) getHeadYaw((AbstractClientPlayer) entityIn, ageInTicks), 0.0F, 1.0F, 0.0F);
			//GlStateManager.rotate((int) getHeadPitch((AbstractClientPlayer) entityIn, ageInTicks), 1.0F, 0.0F, 0.0F);
			
			rim.rotateAngleX = playerModel.bipedHead.rotateAngleX;
			rim.rotateAngleY = playerModel.bipedHead.rotateAngleY;
			rim.rotationPointX = 0.0F;
			rim.rotateAngleY = 0.0F;
			rim.render(scale);
			
			top.rotateAngleX = playerModel.bipedHead.rotateAngleX;
			top.rotateAngleY = playerModel.bipedHead.rotateAngleY;
			top.rotationPointX = 0.0F;
			top.rotateAngleY = 0.0F;
			top.render(scale);
			
			GlStateManager.popMatrix();

			
		}
		
		private float interpolateRotation(float prevAngle, float nextAngle, float partialTicks) {
			float f = nextAngle - prevAngle;
			while(f < -180) {
				f += 360.0f;
			}
			
			while(f >= 180) {
				f -= 360.0f;
			}
			
			return (nextAngle);
		}
		
		private float getHeadPitch(AbstractClientPlayer player, float partialTicks) {
			return interpolateRotation(player.rotationPitch, player.prevRotationPitch, partialTicks);
		}

		private float getHeadYaw(AbstractClientPlayer player, float partialTicks) {
			return interpolateRotation(player.prevRotationYawHead, player.rotationYawHead, partialTicks) - interpolateRotation(player.prevRenderYawOffset, player.renderYawOffset, partialTicks);
		}
		
	}
	
}
