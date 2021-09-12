package impulse.hud.mod.impl;

import java.awt.Color;
import java.io.IOException;
import java.util.HashMap;

import org.lwjgl.opengl.GL11;

import impulse.hud.mod.Catagory;
import impulse.hud.mod.HudMod;
import impulse.hud.mod.ui.buttons.ColorButton;
import impulse.hud.mod.ui.temp.CheckButton;
import impulse.hud.mod.utils.Loader;
import impulse.hud.settings.Setting;
import impulse.util.ui.GuiUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class ArmorStatusMod extends HudMod {

	private ColorButton colorButton;
	private HeldItemButton heldItemButton;
	
	Setting heldItem;
	
	public ArmorStatusMod() {
		super("[Armor Status]", 5, 120, "Allows you to see your current arrmor and durability.", Catagory.HUD);
		
		heldItem = new Setting("heldItem", true);
		this.loadSetting();
		this.addSettings(heldItem);
	}
	
	@Override
	public void loadSetting() {
		this.heldItem.value = (boolean) Loader.loadSetting(this.name, "heldItem", true);
	}
	
	@Override
	public void draw() {
				
		getArmorValues();
		
		super.draw();
	}
	
	@Override
	public void renderDummy(int mouseX, int mouseY) {
		
		int number = 0;
		
		if ((boolean) this.getSetting("heldItem").value == true) {
			renderItem(number, new ItemStack(Items.diamond_sword));
			number++;

		}
		
		renderItem(number, new ItemStack(Items.diamond_helmet));
		number++;
		renderItem(number, new ItemStack(Items.diamond_chestplate));
		number++;
		renderItem(number, new ItemStack(Items.diamond_leggings));
		number++;
		renderItem(number, new ItemStack(Items.diamond_boots));

		super.renderDummy(mouseX, mouseY);
	}

	@Override
	public int getWidth() {
		return fr.getStringWidth(name);
	}
	
	@Override
	public int getHeight() {
		try {
			return fr.FONT_HEIGHT * 8 + ((boolean) this.getSetting("heldItem").value ? 4 : 3);
		} catch (Exception e) {
			return 0;
		}
	}
	
	private void getArmorValues() {
		int number = (boolean) this.getSetting("heldItem").value ? 4 : 3;
		int iVal = 0;
		for (int i = 0; i < mc.thePlayer.inventory.armorInventory.length; i++) {
			ItemStack itemStack = mc.thePlayer.inventory.armorInventory[i];
			renderItem(number - i, itemStack);
			iVal++;
		}
		
		if ((boolean) this.getSetting("heldItem").value) {
			ItemStack itemStack = mc.thePlayer.inventory.getCurrentItem();
			renderItem(number - iVal, itemStack);
		}
		
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
				if (this.getRainbow()) {
					GuiUtils.drawChromaString(String.format("%.2f%%", damage), xVal + 20, yVal + 4, true);
				} else {
					fr.drawStringWithShadow(String.format("%.2f%%", damage), xVal + 20, yVal + 4, this.getColor());
				}
			} else {
				String number = String.valueOf(is.stackSize);
				if (number.equals("1")|| number.equals("0")) {
					
				} else {
					if (this.getRainbow()) {
						GuiUtils.drawChromaString(number, xVal + 20, yVal + 4, true);
					} else {
						fr.drawStringWithShadow(number, xVal + 20, yVal + 4, this.getColor());
					}
				}
			}
			
			RenderHelper.enableGUIStandardItemLighting();
			mc.getRenderItem().renderItemAndEffectIntoGUI(is, xVal, yVal);
			GL11.glPopMatrix();
	}
	
	private class HeldItemButton extends CheckButton {

		public HeldItemButton(int x, int y, HudMod m) {
			super(x, y, m, "Show held item");
		}
		
		@Override
		public boolean checkEnabled() {
			return (boolean) this.m.getSetting("heldItem").value;
		}
		
		@Override
		public void handleClick() {
			this.m.changeSetting("heldItem", !((boolean) this.m.getSetting("heldItem").value));
		}
	}
	
	@Override
	public void initGui(GuiScreen gui) {
		this.colorButton = new ColorButton(110, 90, Minecraft.getMinecraft().fontRendererObj.getStringWidth("Change Text Color") + 5, Minecraft.getMinecraft().fontRendererObj.FONT_HEIGHT + 2, this);
		
		this.heldItemButton = new HeldItemButton(110, 110, this);
	}
	
	@Override
	public void drawScreen(GuiScreen gui, int mouseX, int mouseY, float partialTicks) {
		colorButton.draw();
		
		heldItemButton.draw();
	}
	
	@Override
	public void mouseClicked(GuiScreen gui, int mouseX, int mouseY, int mouseButton) throws IOException {
		colorButton.onClick(mouseX, mouseY, mouseButton);
		
		heldItemButton.onClick(mouseX, mouseY, mouseButton);
	}
	
}
