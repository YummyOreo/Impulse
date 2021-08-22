package impulse.hud.mod.impl;

import java.awt.Color;

import org.lwjgl.opengl.GL11;

import impulse.hud.mod.HudMod;
import impulse.util.ui.Texture;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class ArmorStatusMod extends HudMod {

	public ArmorStatusMod() {
		super("[Armor Status]", 5, 120, "Allows you to see your current arrmor and durability.");
	}

	@Override
	public void draw() {
				
		getArmorValues();
		
		super.draw();
	}
	
	@Override
	public void renderDummy(int mouseX, int mouseY) {
		
		renderItem(0, new ItemStack(Items.diamond_sword));
		renderItem(1, new ItemStack(Items.diamond_helmet));
		renderItem(2, new ItemStack(Items.diamond_chestplate));
		renderItem(3, new ItemStack(Items.diamond_leggings));
		renderItem(4, new ItemStack(Items.diamond_boots));

		super.renderDummy(mouseX, mouseY);
	}

	@Override
	public int getWidth() {
		return fr.getStringWidth(name);
	}
	
	@Override
	public int getHeight() {
		return fr.FONT_HEIGHT * 8 + 4;
	}
	
	private void getArmorValues() {
		int iVal = 0;
		for (int i = 0; i < mc.thePlayer.inventory.armorInventory.length; i++) {
			ItemStack itemStack = mc.thePlayer.inventory.armorInventory[i];
			renderItem(4 - i, itemStack);
			iVal += 1;
		}
		ItemStack itemStack = mc.thePlayer.inventory.getCurrentItem();
		renderItem(4 - iVal, itemStack);
		
	}
	
	private void renderItem(int i, ItemStack is) {
		if(is == null) {
				return;
			}
			
			GL11.glPushMatrix();
			int yVal = getY() + (fr.FONT_HEIGHT * (i * 2));
			int xVal = getX();
			
			if(is.getItem().isDamageable()) {
				double damage = ((is.getMaxDamage() - is.getItemDamage()) / (double) is.getMaxDamage()) * 100;
				fr.drawStringWithShadow(String.format("%.2f%%", damage), xVal + 20, yVal + 4, this.getColor());
			} else {
				String number = String.valueOf(is.stackSize);
				if (number.equals("1")|| number.equals("0")) {
					
				} else {
					fr.drawStringWithShadow(number, xVal + 20, yVal + 4, -1);
				}
			}
			
			RenderHelper.enableGUIStandardItemLighting();
			mc.getRenderItem().renderItemAndEffectIntoGUI(is, xVal, yVal);
			GL11.glPopMatrix();
	}
	
}
