package impulse.ui.clickgui;

import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;

import impulse.Impulse;
import impulse.hud.mod.HudMod;
import impulse.hud.mod.impl.ImpulseServerMod;
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
	private BackButton backButton;
	private ResetButton resetButton;
	private ImpusleServerButtom impusleServerButtom;
	private int longString = Minecraft.getMinecraft().fontRendererObj.getStringWidth("Perspective Mod") + 5;
	
	@Override
	public void initGui() {
		// ADD 25
		super.initGui();
		
		int row = 110;
		
		int amp = 5;
		
		// Back Button
		this.backButton = new BackButton(110, 55, 20, mc.fontRendererObj.FONT_HEIGHT + 2);
		this.resetButton = new ResetButton(230, 55, 31, mc.fontRendererObj.FONT_HEIGHT + 2);
		// server button
		this.impusleServerButtom = (new ImpusleServerButtom(130, 55, mc.fontRendererObj.getStringWidth(Impulse.INSTANCE.hudManager.impulseServerMod.name) + 5, mc.fontRendererObj.FONT_HEIGHT + 2, Impulse.INSTANCE.hudManager.impulseServerMod));
		// a
		this.modButtons.add(new ModButton(row, 80, longString + 5, mc.fontRendererObj.FONT_HEIGHT + 2, Impulse.INSTANCE.hudManager.armorStatus));
		this.settingsButton.add(new SettingsButton(row + longString + 4, 80, 10, mc.fontRendererObj.FONT_HEIGHT + 2, Impulse.INSTANCE.hudManager.armorStatus));
		row += ((longString + 5) + 10.5F) + amp;
		
		this.modButtons.add(new ModButton(row, 80, longString + 5, mc.fontRendererObj.FONT_HEIGHT + 2, Impulse.INSTANCE.hudManager.autoGG));
		this.settingsButton.add(new SettingsButton(row + longString + 4, 80, 10, mc.fontRendererObj.FONT_HEIGHT + 2, Impulse.INSTANCE.hudManager.autoGG));
		row += ((longString+ 5) + 10.5F) + amp;
		
		this.modButtons.add(new ModButton(row, 80, longString + 5, mc.fontRendererObj.FONT_HEIGHT + 2, Impulse.INSTANCE.hudManager.autoTipMod));
		this.settingsButton.add(new SettingsButton(row + longString + 4, 80, 10, mc.fontRendererObj.FONT_HEIGHT + 2, Impulse.INSTANCE.hudManager.autoTipMod));
		row += ((longString+ 5) + 10.5F) + amp;
		//b
		//c
		this.modButtons.add(new ModButton(row, 80, longString + 5, mc.fontRendererObj.FONT_HEIGHT + 2, Impulse.INSTANCE.hudManager.cps));
		this.settingsButton.add(new SettingsButton(row + longString + 4, 80, 10, mc.fontRendererObj.FONT_HEIGHT + 2, Impulse.INSTANCE.hudManager.cps));
		row += ((longString + 5) + 10.5F) + amp;
		//d 

		// -----
		row = 110;
		this.modButtons.add(new ModButton(row, 105, longString + 5, mc.fontRendererObj.FONT_HEIGHT + 2, Impulse.INSTANCE.hudManager.discordRPCToggle));
		this.settingsButton.add(new SettingsButton(row + longString + 4, 105, 10, mc.fontRendererObj.FONT_HEIGHT + 2, Impulse.INSTANCE.hudManager.discordRPCToggle));
		row += ((longString + 5) + 10.5F) + amp;
		//e
		//f
		this.modButtons.add(new ModButton(row, 105, longString + 5, mc.fontRendererObj.FONT_HEIGHT + 2, Impulse.INSTANCE.hudManager.fpsMod));
		this.settingsButton.add(new SettingsButton(row + longString + 4, 105, 10, mc.fontRendererObj.FONT_HEIGHT + 2, Impulse.INSTANCE.hudManager.fpsMod));
		row += ((longString + 5) + 10.5F) + amp;
		
		this.modButtons.add(new ModButton(row, 105, longString + 5, mc.fontRendererObj.FONT_HEIGHT + 2, Impulse.INSTANCE.hudManager.fullBrightMod));
		this.settingsButton.add(new SettingsButton(row + longString + 4, 105, 10, mc.fontRendererObj.FONT_HEIGHT + 2, Impulse.INSTANCE.hudManager.fullBrightMod));
		row += ((longString + 5) + 10.5F) + amp;
		//g
		//h
		//i
		//j
		//k
		this.modButtons.add(new ModButton(row, 105, longString + 5, mc.fontRendererObj.FONT_HEIGHT + 2, Impulse.INSTANCE.hudManager.keystrokes));
		this.settingsButton.add(new SettingsButton(row + longString + 4, 105, 10, mc.fontRendererObj.FONT_HEIGHT + 2, Impulse.INSTANCE.hudManager.keystrokes));
		row += ((longString + 5) + 10.5F) + amp;
		//l
		//m
		// -----
		row = 110;
		this.modButtons.add(new ModButton(row, 130, longString + 5, mc.fontRendererObj.FONT_HEIGHT + 2, Impulse.INSTANCE.hudManager.memoryMod));
		this.settingsButton.add(new SettingsButton(row + longString + 4, 130, 10, mc.fontRendererObj.FONT_HEIGHT + 2, Impulse.INSTANCE.hudManager.memoryMod));
		row += ((longString + 5) + 10.5F) + amp;
		//n
		//o
		//p
		
		this.modButtons.add(new ModButton(row, 130, longString + 5, mc.fontRendererObj.FONT_HEIGHT + 2, Impulse.INSTANCE.hudManager.perspectiveMod));
		this.settingsButton.add(new SettingsButton(row + longString + 4, 130, 10, mc.fontRendererObj.FONT_HEIGHT + 2, Impulse.INSTANCE.hudManager.perspectiveMod));
		row += ((longString + 5) + 10.5F) + amp;
		
		this.modButtons.add(new ModButton(row, 130, longString + 5, mc.fontRendererObj.FONT_HEIGHT + 2, Impulse.INSTANCE.hudManager.positionMod));
		this.settingsButton.add(new SettingsButton(row + longString + 4, 130, 10, mc.fontRendererObj.FONT_HEIGHT + 2, Impulse.INSTANCE.hudManager.positionMod));
		row += ((longString + 5) + 10.5F) + amp;
		
		this.modButtons.add(new ModButton(row, 130, longString + 5, mc.fontRendererObj.FONT_HEIGHT + 2, Impulse.INSTANCE.hudManager.potionEffect));
		this.settingsButton.add(new SettingsButton(row + longString + 4, 130, 10, mc.fontRendererObj.FONT_HEIGHT + 2, Impulse.INSTANCE.hudManager.potionEffect));
		row += ((longString + 5) + 10.5F) + amp;
		
		// -----
		row = 110;
		this.modButtons.add(new ModButton(row, 155, longString + 5, mc.fontRendererObj.FONT_HEIGHT + 2, Impulse.INSTANCE.hudManager.pingMod));
		this.settingsButton.add(new SettingsButton(row + longString + 4, 155, 10, mc.fontRendererObj.FONT_HEIGHT + 2, Impulse.INSTANCE.hudManager.pingMod));
		row += ((longString + 5) + 10.5F) + amp;
		//q
		//r
		this.modButtons.add(new ModButton(row, 155, longString + 5, mc.fontRendererObj.FONT_HEIGHT + 2, Impulse.INSTANCE.hudManager.resourcePackMod));
		this.settingsButton.add(new SettingsButton(row + longString + 4, 155, 10, mc.fontRendererObj.FONT_HEIGHT + 2, Impulse.INSTANCE.hudManager.resourcePackMod));
		row += ((longString + 5) + 10.5F) + amp;
		//s
		
		this.modButtons.add(new ModButton(row, 155, longString + 5, mc.fontRendererObj.FONT_HEIGHT + 2, Impulse.INSTANCE.hudManager.serverMod));
		this.settingsButton.add(new SettingsButton(row + longString + 4, 155, 10, mc.fontRendererObj.FONT_HEIGHT + 2, Impulse.INSTANCE.hudManager.serverMod));
		row += ((longString + 5) + 10.5F) + amp;
		//t
		this.modButtons.add(new ModButton(row, 155, longString + 5, mc.fontRendererObj.FONT_HEIGHT + 2, Impulse.INSTANCE.hudManager.targetHUD));
		this.settingsButton.add(new SettingsButton(row + longString + 4, 155, 10, mc.fontRendererObj.FONT_HEIGHT + 2, Impulse.INSTANCE.hudManager.targetHUD));
		row += ((longString + 5) + 10.5F) + amp;
		
		// -----
		row = 110;
		this.modButtons.add(new ModButton(row, 180, longString + 5, mc.fontRendererObj.FONT_HEIGHT + 2, Impulse.INSTANCE.hudManager.toggleStrintGui));
		this.settingsButton.add(new SettingsButton(row + longString + 4, 180, 10, mc.fontRendererObj.FONT_HEIGHT + 2, Impulse.INSTANCE.hudManager.toggleStrintGui));
		row += ((longString + 5) + 10.5F) + amp;
		//u
		//v
		//w
		//x
		//y
		//z
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		
		ScaledResolution sr = new ScaledResolution(mc);
		
		super.drawScreen(mouseX, mouseY, partialTicks);
		
		GuiUtils.drawRoundedRect(100 - 3, 50 - 3, sr.getScaledWidth() - 100 + 3, sr.getScaledHeight() - 50 + 3, 2, new Color(88, 95, 110).getRGB());
		
		Gui.drawRect(100, 50, sr.getScaledWidth() - 100, sr.getScaledHeight() - 50, new Color(85, 91, 102).getRGB());
		Gui.drawRect(100, 50, sr.getScaledWidth() - 100, 70, new Color(47, 50, 56).getRGB());
		
		Gui.drawRect(100, 75, sr.getScaledWidth() - 100, 70, new Color(114, 223, 228, 255).getRGB());
		
		for(ModButton m : modButtons) {
			m.draw();
		}
		
		for(SettingsButton s : settingsButton) {
			s.draw();
		}
		
		backButton.draw();
		
		resetButton.draw();
		
		impusleServerButtom.draw();
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
		
		backButton.onClick(mouseX, mouseY, mouseButton);
		
		resetButton.onClick(mouseX, mouseY, mouseButton);
		
		impusleServerButtom.onClick(mouseX, mouseY, mouseButton);
	}
	
	
}
