package impulse.ui.clickgui;

import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;

import impulse.Impulse;
import impulse.hud.mod.Catagory;
import impulse.hud.mod.HudMod;
import impulse.settings.SettingsMod;
import impulse.ui.clickgui.comp.*;
import impulse.util.ui.GuiUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;

public class ClickGUI extends GuiScreen {

	ArrayList<ModButton> modButtons = new ArrayList<>();
	ArrayList<SettingsButton> settingsButton = new ArrayList<>();
	ArrayList<ChangeCatagory> changeCatagory = new ArrayList<>();
	private BackButton backButton;
	private ResetButton resetButton;
	private ArrayList<SettingButton> settingButton = new ArrayList<>();
	private int longString = Minecraft.getMinecraft().fontRendererObj.getStringWidth("Perspective Mod") + 5;
	
	
	@Override
	public void initGui() {
		// ADD 25
		super.initGui();
		
		// Back Button
		this.backButton = new BackButton(110, 55, 20, mc.fontRendererObj.FONT_HEIGHT + 2);
		
		int row = 130;
		
		this.changeCatagory.add(new ChangeCatagory(row, 55, mc.fontRendererObj.getStringWidth("ALL") + 5, mc.fontRendererObj.FONT_HEIGHT + 2, "ALL", null));
		row += mc.fontRendererObj.getStringWidth("ALL") + 10;
		this.changeCatagory.add(new ChangeCatagory(row, 55, mc.fontRendererObj.getStringWidth("HUD") + 5, mc.fontRendererObj.FONT_HEIGHT + 2, "HUD", Catagory.HUD));
		row += mc.fontRendererObj.getStringWidth("HUD") + 10;
		this.changeCatagory.add(new ChangeCatagory(row, 55, mc.fontRendererObj.getStringWidth("MISC") + 5, mc.fontRendererObj.FONT_HEIGHT + 2, "MISC", Catagory.MISC));
		row += mc.fontRendererObj.getStringWidth("MISC") + 10;
		this.changeCatagory.add(new ChangeCatagory(row, 55, mc.fontRendererObj.getStringWidth("WORLD") + 5, mc.fontRendererObj.FONT_HEIGHT + 2, "WORLD", Catagory.WORLD));
		row += mc.fontRendererObj.getStringWidth("WORLD") + 10;
		this.changeCatagory.add(new ChangeCatagory(row, 55, mc.fontRendererObj.getStringWidth("SERVER") + 5, mc.fontRendererObj.FONT_HEIGHT + 2, "SERVER", Catagory.SERVER));
		row += mc.fontRendererObj.getStringWidth("SERVER") + 10;
		this.changeCatagory.add(new ChangeCatagory(row, 55, mc.fontRendererObj.getStringWidth("CHAT") + 5, mc.fontRendererObj.FONT_HEIGHT + 2, "CHAT", Catagory.CHAT));
		row += mc.fontRendererObj.getStringWidth("CHAT") + 10;
		this.changeCatagory.add(new ChangeCatagory(row, 55, mc.fontRendererObj.getStringWidth("COSMETIC") + 5, mc.fontRendererObj.FONT_HEIGHT + 2, "COSMETIC", Catagory.COSMETIC));
		row += mc.fontRendererObj.getStringWidth("COSMETIC") + 10;
		this.changeCatagory.add(new ChangeCatagory(row, 55, mc.fontRendererObj.getStringWidth("SETTINGS") + 5, mc.fontRendererObj.FONT_HEIGHT + 2, "SETTINGS", Catagory.SETTINGS));

	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		
		modButtons = new ArrayList<>();
		settingsButton = new ArrayList<>();
		resetButton = null;
		settingButton = new ArrayList<>();
		
		int row = 110;
		
		int amp = 5;
		
		int count = 0;
		
		int hight = 80;
		
		ScaledResolution sr = new ScaledResolution(mc);
		
		super.drawScreen(mouseX, mouseY, partialTicks);
		
		GuiUtils.drawRoundedRect(100 - 3, 50 - 3, sr.getScaledWidth() - 100 + 3, sr.getScaledHeight() - 50 + 3, 2, new Color(88, 95, 110).getRGB());
		
		Gui.drawRect(100, 50, sr.getScaledWidth() - 100, sr.getScaledHeight() - 50, new Color(85, 91, 102).getRGB());
		Gui.drawRect(100, 50, sr.getScaledWidth() - 100, 70, new Color(47, 50, 56).getRGB());
		
		Gui.drawRect(100, 75, sr.getScaledWidth() - 100, 70, new Color(114, 223, 228, 255).getRGB());
		
		this.resetButton = new ResetButton(450, 55, 31, mc.fontRendererObj.FONT_HEIGHT + 2);
		
		if (Impulse.INSTANCE.catagory == Catagory.SETTINGS) {
			
			for (SettingsMod s : Impulse.INSTANCE.settingsManager.settings) {
				if (row > (((longString + 5) + 10F) + amp) * 4) {
					hight += 15;
					row = 110;
					this.settingButton.add(new SettingButton(row, hight, mc.fontRendererObj.getStringWidth(s.name) + 5, mc.fontRendererObj.FONT_HEIGHT + 2, s));
					row += ((mc.fontRendererObj.getStringWidth(s.name) + 5)) + amp;
				} else {
					this.settingButton.add(new SettingButton(row, hight, mc.fontRendererObj.getStringWidth(s.name) + 5, mc.fontRendererObj.FONT_HEIGHT + 2, s));
					row += ((mc.fontRendererObj.getStringWidth(s.name) + 5)) + amp;
				}
			}
			
		} else {
			for (HudMod m : Impulse.INSTANCE.hudManager.hudMods) {
				
				 if (Impulse.INSTANCE.catagory == null && m.catagory != Catagory.COSMETIC && m.noShow == false) {
					if (count == 4) {
						count = 0;
						hight += 25;
						row = 110;
						this.modButtons.add(new ModButton(row, hight, longString + 5, mc.fontRendererObj.FONT_HEIGHT + 2, m));
						this.settingsButton.add(new SettingsButton(row + longString + 4, hight, 10, mc.fontRendererObj.FONT_HEIGHT + 2, m));
						row += ((longString + 5) + 10.5F) + amp;
					} else {
						this.modButtons.add(new ModButton(row, hight, longString + 5, mc.fontRendererObj.FONT_HEIGHT + 2, m));
						this.settingsButton.add(new SettingsButton(row + longString + 4, hight, 10, mc.fontRendererObj.FONT_HEIGHT + 2, m));
						row += ((longString + 5) + 10.5F) + amp;
					}
					count++;
				} else if (m.catagory == Impulse.INSTANCE.catagory && m.noShow == false) {
					if (count == 4) {
						count = 0;
						hight += 25;
						row = 110;
						this.modButtons.add(new ModButton(row, hight, longString + 5, mc.fontRendererObj.FONT_HEIGHT + 2, m));
						this.settingsButton.add(new SettingsButton(row + longString + 4, hight, 10, mc.fontRendererObj.FONT_HEIGHT + 2, m));
						row += ((longString + 5) + 10.5F) + amp;
					} else {
						this.modButtons.add(new ModButton(row, hight, longString + 5, mc.fontRendererObj.FONT_HEIGHT + 2, m));
						this.settingsButton.add(new SettingsButton(row + longString + 4, hight, 10, mc.fontRendererObj.FONT_HEIGHT + 2, m));
						row += ((longString + 5) + 10.5F) + amp;
					}
					count++;
				}
			}
		}
		
		for(ModButton m : modButtons) {
			m.draw();
		}
		
		for(SettingsButton s : settingsButton) {
			s.draw();
		}
		
		for(ChangeCatagory c : changeCatagory) {
			c.draw();
		}
		
		backButton.draw();
		
		for(SettingButton s : settingButton) {
			s.draw();
		}
		
		try {
			resetButton.draw();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
	@Override
	public void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
		super.mouseClicked(mouseX, mouseY, mouseButton);
		
		for (ModButton m : modButtons) {
			m.onClick(mouseX, mouseY, mouseButton);
		}
		
		for (SettingsButton s : settingsButton) {
			s.onClick(mouseX, mouseY, mouseButton);
		}
		
		for(ChangeCatagory c : changeCatagory) {
			c.onClick(mouseX, mouseY, mouseButton);
		}
		
		backButton.onClick(mouseX, mouseY, mouseButton);
		
		for(SettingButton s : settingButton) {
			s.onClick(mouseX, mouseY, mouseButton);
		}
		
		try {
			resetButton.onClick(mouseX, mouseY, mouseButton);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
	
}
