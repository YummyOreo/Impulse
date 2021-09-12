package impulse.hud.mod.impl;

import java.util.HashMap;
import java.util.Iterator;

import org.apache.commons.lang3.StringUtils;
import org.lwjgl.opengl.GL11;

import impulse.hud.mod.Catagory;
import impulse.hud.mod.HudMod;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;

public class ItemTracker extends HudMod {

	private HashMap<String, Integer> items, oldItems = new HashMap<String, Integer>();
	
	private HashMap<String, Long> buffer = new HashMap<String, Long>();
	private HashMap<String, String> bufferNameAdd = new HashMap<String, String>();
	private HashMap<String, String> bufferNameSub = new HashMap<String, String>();
	
	public ItemTracker() {
		super("[Item Tracker]", 555, 5, "Trackes itemes", Catagory.HUD);
	}
	
	@Override
	public void draw() {
		
		if (!mc.thePlayer.isDead) {
			oldItems = items;
			
			items = new HashMap<String, Integer>();
			
			HashMap<String, Integer> add = new HashMap<String, Integer>();
			HashMap<String, Integer> subtract = new HashMap<String, Integer>();
			
			int amp = 1;
			
			
			
			for (int i = 0; i < mc.thePlayer.inventory.mainInventory.length; i++) {
				ItemStack item = mc.thePlayer.inventory.mainInventory[i];
				
				if (item != null) {
					
					
					if (!item.hasDisplayName()) {
						if (items.containsKey(item.getDisplayName())) {
							int temp = items.get(item.getDisplayName());
							items.remove(item.getDisplayName());
							items.put(item.getDisplayName(), item.stackSize + temp);
						} else {
							items.put(item.getDisplayName(), item.stackSize);
						}
					}
				}
					
				
			}
				
			
			try {
				for (String name : oldItems.keySet()) {
					if (!items.containsKey(name)) {
						subtract.put(name, oldItems.get(name));
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			try {
				for (String name : items.keySet()) {
					if (oldItems.containsKey(name)) {
						if (oldItems.get(name) > items.get(name)) {
							if (subtract.containsKey(name)) {
								int temp = subtract.get(name);
								subtract.remove(name);
								subtract.put(name, oldItems.get(name) - (items.get(name) + temp));
							} else {
								subtract.put(name, oldItems.get(name) - items.get(name));
							}
						} else if (oldItems.get(name) < items.get(name)) {
							add.put(name, items.get(name) - oldItems.get(name));
						}
					} else {
						add.put(name, items.get(name));
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			for (String name : add.keySet()) {
				if (bufferNameAdd.containsKey(name)) {
					int num1 = Integer.parseInt(((bufferNameAdd.get(name).split("" + EnumChatFormatting.RESET))[1]).split(" ")[0]);
					int num = num1 + add.get(name);
					
					buffer.remove(bufferNameAdd.get(name));
					bufferNameAdd.remove(name);
					
					buffer.put(EnumChatFormatting.DARK_GREEN + "+ " + EnumChatFormatting.RESET + (num) + " " + name, System.currentTimeMillis());
					bufferNameAdd.put(name, EnumChatFormatting.DARK_GREEN + "+ " + EnumChatFormatting.RESET + num + " " + name);
				} else {
					buffer.put(EnumChatFormatting.DARK_GREEN + "+ " + EnumChatFormatting.RESET + add.get(name) + " " + name, System.currentTimeMillis());
					bufferNameAdd.put(name, EnumChatFormatting.DARK_GREEN + "+ " + EnumChatFormatting.RESET + (add.get(name)) + " " + name);
				}
			}
			
			for (String name : subtract.keySet()) {
				
				if (bufferNameSub.containsKey(name)) {
					int num1 = Integer.parseInt(((bufferNameSub.get(name).split("" + EnumChatFormatting.RESET))[1]).split(" ")[0]);
					int num = num1 + subtract.get(name);
					
					buffer.remove(bufferNameSub.get(name));
					bufferNameSub.remove(name);
					
					buffer.put(EnumChatFormatting.DARK_RED + "- " + EnumChatFormatting.RESET + (num) + " " + name, System.currentTimeMillis());
					bufferNameSub.put(name, EnumChatFormatting.DARK_RED + "- " + EnumChatFormatting.RESET + num + " " + name);
				} else {
					buffer.put(EnumChatFormatting.DARK_RED + "- " + EnumChatFormatting.RESET + subtract.get(name) + " " + name, System.currentTimeMillis());
					bufferNameSub.put(name, EnumChatFormatting.DARK_RED + "- " + EnumChatFormatting.RESET + (subtract.get(name)) + " " + name);
				}
			}
			
			Iterator<String> iterator = buffer.keySet().iterator();
		    
		    while(iterator.hasNext()){
				String buffer2 = iterator.next();
				if ((System.currentTimeMillis() - buffer.get(buffer2)) > 2000) {
					if (buffer2.contains("-")) {
						String name = buffer2.substring(StringUtils.ordinalIndexOf(buffer2, " ", 2)).trim();
						bufferNameSub.remove(name);
					} else {
						String name = buffer2.substring(StringUtils.ordinalIndexOf(buffer2, " ", 2)).trim();
						bufferNameAdd.remove(name);
					}
					
					iterator.remove();
					
				} else {
					GL11.glPushMatrix();
					GL11.glScaled(0.5, 0.5, 1);
					fr.drawStringWithShadow(buffer2, getX(), getY() * 2 + amp, -1);
					amp += fr.FONT_HEIGHT + 2;
					GL11.glPopMatrix();
					
					
				}
		    }
		}
		
		super.draw();
	}
	
	@Override
	public void renderDummy(int mouseX, int mouseY) {
		
		GL11.glPushMatrix();
		GL11.glScaled(0.5, 0.5, 1);
		fr.drawStringWithShadow("[Item Tracker]", getX() * 2, getY() * 2, -1);
		GL11.glPopMatrix();
		
		super.renderDummy(mouseX, mouseY);
	}
	
	@Override
	public int getWidth() {
		return  fr.getStringWidth("Item");
	}
	
	@Override
	public int getHeight() {
		return fr.FONT_HEIGHT;
	}
}
