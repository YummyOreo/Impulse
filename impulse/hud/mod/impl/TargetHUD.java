package impulse.hud.mod.impl;

import java.awt.Color;
import java.io.IOException;

import org.lwjgl.opengl.GL11;

import impulse.hud.mod.Catagory;
import impulse.hud.mod.HudMod;
import impulse.hud.mod.ui.buttons.BackgroundButton;
import impulse.hud.mod.ui.buttons.ColorButton;
import impulse.util.ui.GuiUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.network.NetworkPlayerInfo;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;

public class TargetHUD extends HudMod {

	EntityLivingBase target;
	
	private ColorButton colorButton;
	
	public TargetHUD() {
		super("[TargetHUD]", 120, 140, "Shows info about who your looking at!", Catagory.HUD);
	}
	
	@Override
	public void draw() {
		
		renderTargetHud();
		
		super.draw();
	}
	
	@Override
	public void renderDummy(int mouseX, int mouseY) {
		
		target = mc.thePlayer;
		
		if (target != null && target instanceof EntityPlayer) {
			if (target.isInvisible()) return;
		
			Gui.drawRect(getX(), getY() - 3, getX() + 90, getY() + fr.FONT_HEIGHT * 2 + 6, new Color(0, 0, 0, 120).getRGB());
		
			Gui.drawRect(getX(), getY() + fr.FONT_HEIGHT * 2 + 8, getX() + 90, getY() + fr.FONT_HEIGHT * 2 + 6, getWinColor((EntityPlayer) target));
		
			GlStateManager.pushMatrix();
			
			GlStateManager.scale(0.5f, 0.5f, 1);
			
			GlStateManager.translate(0.5F, 0.5F, 1);
			
			NetworkPlayerInfo playerInfo = mc.getNetHandler().getPlayerInfo(target.getUniqueID());
			
			fr.drawStringWithShadow(target.getName(), getX() * 2 + 40, getY() * 2 + 2, this.getColor());
			fr.drawStringWithShadow(EnumChatFormatting.BOLD + ""+ (int) target.getHealth() + EnumChatFormatting.RED + " \u2764", getX() * 2 + 40, getY() * 2 + 4 + fr.FONT_HEIGHT, this.getColor());
			if (playerInfo != null) {
				fr.drawStringWithShadow(EnumChatFormatting.BOLD + "" + (int) playerInfo.getResponseTime() + " ms", getX() * 2 + 40 + fr.getStringWidth(EnumChatFormatting.BOLD + ""+ (int) target.getHealth() + EnumChatFormatting.RED + " \u2764") + 2, getY() * 2 + 4 + fr.FONT_HEIGHT, this.getColor());
			}
			
			if (playerInfo != null) {
				fr.drawStringWithShadow(getWin((EntityPlayer) target), getX() * 2 + 40 + fr.getStringWidth((int) target.getHealth() + " \u2764" + "" + (int) playerInfo.getResponseTime() + " ms") + 10, getY() * 2 + 4 + fr.FONT_HEIGHT, getWinColor((EntityPlayer) target));
			}
			
			
			GlStateManager.popMatrix();
			
	        if (playerInfo != null) {
	        	GL11.glPushMatrix();
	            mc.getTextureManager().bindTexture(playerInfo.getLocationSkin());
	            GL11.glColor4f(1F, 1F, 1F, 1F);
	            
	            Gui.drawScaledCustomSizeModalRect((int) getX() + 2, (int) getY(), 8F, 8F, 8, 8, 15, 15, 64F, 64F);
	            GL11.glPopMatrix();
	        }
		
	        renderArmor(target);
		}
		
		super.renderDummy(mouseX, mouseY);
	}
	
	@Override
	public int getWidth() {
		return 90;
	}
	
	@Override
	public int getHeight() {
		return fr.FONT_HEIGHT * 2 + 4;
	}

	private void renderTargetHud() {
		try {
			target = (EntityLivingBase) mc.pointedEntity;
		} catch(Exception e) {
		
		}
		
		if (target != null && target instanceof EntityPlayer) {
			if (target.isInvisible()) return;
		
			Gui.drawRect(getX(), getY() - 3, getX() + 90, getY() + fr.FONT_HEIGHT * 2 + 6, new Color(0, 0, 0, 120).getRGB());
		
			Gui.drawRect(getX(), getY() + fr.FONT_HEIGHT * 2 + 8, getX() + 90, getY() + fr.FONT_HEIGHT * 2 + 6, getWinColor((EntityPlayer) target));
		
			GlStateManager.pushMatrix();
			
			GlStateManager.scale(0.5f, 0.5f, 1);
			
			GlStateManager.translate(0.5F, 0.5F, 1);
			
			NetworkPlayerInfo playerInfo = mc.getNetHandler().getPlayerInfo(target.getUniqueID());
			
			fr.drawStringWithShadow(target.getName(), getX() * 2 + 40, getY() * 2 + 2, this.getColor());
			fr.drawStringWithShadow(EnumChatFormatting.BOLD + ""+ (int) target.getHealth() + EnumChatFormatting.RED + " \u2764", getX() * 2 + 40, getY() * 2 + 4 + fr.FONT_HEIGHT, this.getColor());
			if (playerInfo != null) {
				fr.drawStringWithShadow(EnumChatFormatting.BOLD + "" + (int) playerInfo.getResponseTime() + " ms", getX() * 2 + 40 + fr.getStringWidth(EnumChatFormatting.BOLD + ""+ (int) target.getHealth() + EnumChatFormatting.RED + " \u2764") + 2, getY() * 2 + 4 + fr.FONT_HEIGHT, this.getColor());
			}
			
			if (playerInfo != null) {
				fr.drawStringWithShadow(getWin((EntityPlayer) target), getX() * 2 + 40 + fr.getStringWidth((int) target.getHealth() + " \u2764" + "" + (int) playerInfo.getResponseTime() + " ms") + 10, getY() * 2 + 4 + fr.FONT_HEIGHT, getWinColor((EntityPlayer) target));
			}
			
			
			GlStateManager.popMatrix();
			
	        if (playerInfo != null) {
	        	GL11.glPushMatrix();
	            mc.getTextureManager().bindTexture(playerInfo.getLocationSkin());
	            GL11.glColor4f(1F, 1F, 1F, 1F);
	            
	            Gui.drawScaledCustomSizeModalRect((int) getX() + 2, (int) getY(), 8F, 8F, 8, 8, 15, 15, 64F, 64F);
	            GL11.glPopMatrix();
	        }
		
	        renderArmor(target);
		}

	}
	
	private void renderArmor(EntityLivingBase target) {
		int iVal = 0;
		for (int i = 0; i < 4; i++) {
			ItemStack itemStack = target.getCurrentArmor(i);
			renderItem(4 - i, itemStack, target);
			iVal += 1;
		}
		ItemStack itemStack = target.getHeldItem();
		renderItem(4 - iVal, itemStack, target);
		
	}
	
	private void renderItem(int i, ItemStack is, EntityLivingBase target) {
		
			if(is == null) {
				return;
			}
			
			i = i + 1;
			
			int yVal = getY() + fr.FONT_HEIGHT + 4;
			int xVal = 0;
			xVal = getX() + fr.getStringWidth((int) target.getHealth() + " \u2764") + (i * 8) - (fr.getStringWidth((int) target.getHealth() + " \u2764") / 2) + 6;

			RenderHelper.enableGUIStandardItemLighting();
			GlStateManager.pushMatrix();
			GlStateManager.scale(0.5, 0.5, .5);
			GlStateManager.translate(xVal, yVal, xVal);
			mc.getRenderItem().renderItemAndEffectIntoGUI(is, xVal, yVal);
			GlStateManager.popMatrix();

	}
	
	 private String getWin(EntityPlayer currentTarget) {
	        if (currentTarget.getHealth() > mc.thePlayer.getHealth()) {
	            return "You are Losing";
	        } else if (currentTarget.getHealth() == mc.thePlayer.getHealth() && currentTarget.getHealth() != 20 && mc.thePlayer.getHealth() != 20) {
	            return "You may win";
	        } else if (currentTarget.getHealth() < mc.thePlayer.getHealth()) {
	            return "You are Winning";
	        } else if (currentTarget.getHealth() == 0) {
	            return "You won!";
	        } else if (mc.thePlayer.getHealth() == 0) {
	            return "You lost!";
	        } else if (currentTarget.getHealth() == 20 && mc.thePlayer.getHealth() == 20) {
	            return "Not fighting";
	        } else {
	            return "You May Win";
	        }
	    }
	    
    private int getWinColor(EntityPlayer currentTarget) {
        if (currentTarget.getHealth() > mc.thePlayer.getHealth()) {
            return new Color(255, 0, 0, 255).getRGB();
        } else if (currentTarget.getHealth() == mc.thePlayer.getHealth() && currentTarget.getHealth() != 20 && mc.thePlayer.getHealth() != 20) {
            return new Color(255, 255, 0, 255).getRGB();
        } else if (currentTarget.getHealth() < mc.thePlayer.getHealth()) {
            return new Color(0, 255, 0, 255).getRGB();
        } else if (currentTarget.getHealth() == 0) {
            return new Color(0, 255, 0, 255).getRGB();
        }else if (mc.thePlayer.getHealth() == 0) {
            return new Color(255, 0, 0, 255).getRGB();
        } else if (currentTarget.getHealth() == 20 && mc.thePlayer.getHealth() == 20) {
            return new Color(0, 188, 255, 255).getRGB();
        } 
        else {
            return new Color(0, 0, 255, 255).getRGB();
        }
    }
    
    @Override
	public void initGui(GuiScreen gui) {
		this.colorButton = new ColorButton(110, 90, Minecraft.getMinecraft().fontRendererObj.getStringWidth("Change Text Color") + 5, Minecraft.getMinecraft().fontRendererObj.FONT_HEIGHT + 2, this);
	}
	
	@Override
	public void drawScreen(GuiScreen gui, int mouseX, int mouseY, float partialTicks) {
		colorButton.draw();
	}
	
	@Override
	public void mouseClicked(GuiScreen gui, int mouseX, int mouseY, int mouseButton) throws IOException {
		colorButton.onClick(mouseX, mouseY, mouseButton);
	}
	
}
