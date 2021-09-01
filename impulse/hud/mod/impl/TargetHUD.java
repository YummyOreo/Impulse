package impulse.hud.mod.impl;

import java.awt.Color;
import java.io.IOException;

import org.lwjgl.opengl.GL11;

import impulse.hud.mod.HudMod;
import impulse.hud.mod.ui.buttons.BackgroundButton;
import impulse.hud.mod.ui.buttons.ColorButton;
import impulse.util.ui.GuiUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;

public class TargetHUD extends HudMod {

	EntityLivingBase target;
	
	private ColorButton colorButton;
	
	public TargetHUD() {
		super("[TargetHUD]", 120, 140, "Shows info about who your looking at!");
	}
	
	@Override
	public void draw() {
		
		renderTargetHud();
		
		super.draw();
	}
	
	@Override
	public void renderDummy(int mouseX, int mouseY) {
		target = mc.thePlayer;
		if(fr.getStringWidth(target.getName()) > 60) {
			
			
			Gui.drawRect(getX(), getY() - 3, getX() + fr.getStringWidth(target.getName()) + 2, getY() + fr.FONT_HEIGHT * 2 + 4, new Color(0, 0, 0, 120).getRGB());
		
			Gui.drawRect(getX(), getY() + fr.FONT_HEIGHT * 2 + 2, getX() + fr.getStringWidth(target.getName()) + 2, getY() + fr.FONT_HEIGHT * 2 + 4, new Color(114, 223, 228, 255).getRGB());
		} else {
			
			Gui.drawRect(getX(), getY() - 3, getX() + 70, getY() + fr.FONT_HEIGHT * 2 + 4, new Color(0, 0, 0, 120).getRGB());
			
			Gui.drawRect(getX(), getY() + fr.FONT_HEIGHT * 2 + 2, getX() + 70, getY() + fr.FONT_HEIGHT * 2 + 4, new Color(114, 223, 228, 255).getRGB());

		}
		
		if (this.getRainbow()) {
			GuiUtils.drawChromaString(target.getName(), getX() + 2, getY() + 2, true);
			GuiUtils.drawChromaString((int) target.getHealth() + " \u2764", getX() + 2, getY() + 2 + fr.FONT_HEIGHT, true);
		} else {
			fr.drawStringWithShadow(target.getName(), getX() + 2, getY() + 2, this.getColor());
			fr.drawStringWithShadow((int) target.getHealth() + " \u2764", getX() + 2, getY() + 2 + fr.FONT_HEIGHT, this.getColor());
		}
		
		super.renderDummy(mouseX, mouseY);
	}
	
	@Override
	public int getWidth() {
		return 100;
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
		
		if (target != null && target instanceof AbstractClientPlayer) {
			if (target.isInvisible()) return;
			
			if(fr.getStringWidth(target.getName()) > 60) {
				
				
				Gui.drawRect(getX(), getY() - 3, getX() + fr.getStringWidth(target.getName()) + 2, getY() + fr.FONT_HEIGHT * 2 + 4, new Color(0, 0, 0, 120).getRGB());
			
				Gui.drawRect(getX(), getY() + fr.FONT_HEIGHT * 2 + 2, getX() + fr.getStringWidth(target.getName()) + 2, getY() + fr.FONT_HEIGHT * 2 + 4, new Color(114, 223, 228, 255).getRGB());
			} else {
				
				Gui.drawRect(getX(), getY() - 3, getX() + 70, getY() + fr.FONT_HEIGHT * 2 + 4, new Color(0, 0, 0, 120).getRGB());
				
				Gui.drawRect(getX(), getY() + fr.FONT_HEIGHT * 2 + 2, getX() + 70, getY() + fr.FONT_HEIGHT * 2 + 4, new Color(114, 223, 228, 255).getRGB());

			}
			
			if (this.getRainbow()) {
				GuiUtils.drawChromaString(target.getName(), getX() + 2, getY() + 2, true);
				GuiUtils.drawChromaString((int) target.getHealth() + " \u2764", getX() + 2, getY() + 2 + fr.FONT_HEIGHT, true);
			} else {
				fr.drawStringWithShadow(target.getName(), getX() + 2, getY() + 2, this.getColor());
				fr.drawStringWithShadow((int) target.getHealth() + " \u2764", getX() + 2, getY() + 2 + fr.FONT_HEIGHT, this.getColor());
			}
			
			getArmorValues(target);
			
			
		}

	}
	
	private void getArmorValues(EntityLivingBase target) {
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
			
			int yVal = getY() + fr.FONT_HEIGHT + 2;
			int xVal = 0;
			xVal = getX() + fr.getStringWidth((int) target.getHealth() + " \u2764") + (i * 8) - (fr.getStringWidth((int) target.getHealth() + " \u2764") / 2) + 6;

			RenderHelper.enableGUIStandardItemLighting();
			GlStateManager.pushMatrix();
			GlStateManager.scale(0.5, 0.5, .5);
			GlStateManager.translate(xVal, yVal, xVal);
			mc.getRenderItem().renderItemAndEffectIntoGUI(is, xVal, yVal);
			GlStateManager.popMatrix();

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
