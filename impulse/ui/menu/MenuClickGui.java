package impulse.ui.menu;

import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;

import impulse.Impulse;
import impulse.ui.menu.comp.BackButton;
import impulse.ui.clickgui.comp.ModButton;
import impulse.ui.clickgui.comp.SettingsButton;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;

public class MenuClickGui extends GuiScreen{

	ArrayList<ModButton> modButtons = new ArrayList<>();
	ArrayList<SettingsButton> settingsButton = new ArrayList<>();
	private BackButton backButton;
	
	@Override
	public void initGui() {
		super.initGui();
		
		int rowOne = 110;
		int rowTwo = 110;
		int rowThree = 110;
		
		int amp = 5;
		
		// Back Button
		this.backButton = new BackButton(110, 55, 20, mc.fontRendererObj.FONT_HEIGHT + 2);
		// server button
		this.modButtons.add(new ModButton(140, 55, mc.fontRendererObj.getStringWidth(Impulse.INSTANCE.hudManager.impulseServerMod.name) + 5, mc.fontRendererObj.FONT_HEIGHT + 2, Impulse.INSTANCE.hudManager.impulseServerMod));
		this.settingsButton.add(new SettingsButton(140 + mc.fontRendererObj.getStringWidth(Impulse.INSTANCE.hudManager.impulseServerMod.name) + 4, 55, 10, mc.fontRendererObj.FONT_HEIGHT + 2, Impulse.INSTANCE.hudManager.impulseServerMod));
		// a
		this.modButtons.add(new ModButton(rowOne, 80, mc.fontRendererObj.getStringWidth(Impulse.INSTANCE.hudManager.armorStatus.name) + 5, mc.fontRendererObj.FONT_HEIGHT + 2, Impulse.INSTANCE.hudManager.armorStatus));
		this.settingsButton.add(new SettingsButton(rowOne + mc.fontRendererObj.getStringWidth(Impulse.INSTANCE.hudManager.armorStatus.name) + 4, 80, 10, mc.fontRendererObj.FONT_HEIGHT + 2, Impulse.INSTANCE.hudManager.armorStatus));
		rowOne += ((mc.fontRendererObj.getStringWidth(Impulse.INSTANCE.hudManager.armorStatus.name) + 5) + 10.5F) + amp;
		
		this.modButtons.add(new ModButton(rowOne, 80, mc.fontRendererObj.getStringWidth(Impulse.INSTANCE.hudManager.autoTipMod.name) + 5, mc.fontRendererObj.FONT_HEIGHT + 2, Impulse.INSTANCE.hudManager.autoTipMod));
		this.settingsButton.add(new SettingsButton(rowOne + mc.fontRendererObj.getStringWidth(Impulse.INSTANCE.hudManager.autoTipMod.name) + 4, 80, 10, mc.fontRendererObj.FONT_HEIGHT + 2, Impulse.INSTANCE.hudManager.autoTipMod));
		rowOne += ((mc.fontRendererObj.getStringWidth(Impulse.INSTANCE.hudManager.autoTipMod.name) + 5) + 10.5F) + amp;
		//b
		//c
		this.modButtons.add(new ModButton(rowOne, 80, mc.fontRendererObj.getStringWidth(Impulse.INSTANCE.hudManager.cps.name) + 5, mc.fontRendererObj.FONT_HEIGHT + 2, Impulse.INSTANCE.hudManager.cps));
		this.settingsButton.add(new SettingsButton(rowOne + mc.fontRendererObj.getStringWidth(Impulse.INSTANCE.hudManager.cps.name) + 4, 80, 10, mc.fontRendererObj.FONT_HEIGHT + 2, Impulse.INSTANCE.hudManager.cps));
		rowOne += ((mc.fontRendererObj.getStringWidth(Impulse.INSTANCE.hudManager.cps.name) + 5) + 10.5F) + amp;
		//d 
		this.modButtons.add(new ModButton(rowOne, 80, mc.fontRendererObj.getStringWidth(Impulse.INSTANCE.hudManager.discordRPCToggle.name) + 5, mc.fontRendererObj.FONT_HEIGHT + 2, Impulse.INSTANCE.hudManager.discordRPCToggle));
		this.settingsButton.add(new SettingsButton(rowOne + mc.fontRendererObj.getStringWidth(Impulse.INSTANCE.hudManager.discordRPCToggle.name) + 4, 80, 10, mc.fontRendererObj.FONT_HEIGHT + 2, Impulse.INSTANCE.hudManager.discordRPCToggle));
		rowOne += ((mc.fontRendererObj.getStringWidth(Impulse.INSTANCE.hudManager.discordRPCToggle.name) + 5) + 10.5F) + amp;
		//e
		//f
		this.modButtons.add(new ModButton(rowOne, 80, mc.fontRendererObj.getStringWidth(Impulse.INSTANCE.hudManager.fpsMod.name) + 5, mc.fontRendererObj.FONT_HEIGHT + 2, Impulse.INSTANCE.hudManager.fpsMod));
		this.settingsButton.add(new SettingsButton(rowOne + mc.fontRendererObj.getStringWidth(Impulse.INSTANCE.hudManager.fpsMod.name) + 4, 80, 10, mc.fontRendererObj.FONT_HEIGHT + 2, Impulse.INSTANCE.hudManager.fpsMod));
		rowOne += ((mc.fontRendererObj.getStringWidth(Impulse.INSTANCE.hudManager.fpsMod.name) + 5) + 10.5F) + amp;
		//g
		//h
		//i
		//j
		//k
		this.modButtons.add(new ModButton(rowOne, 80, mc.fontRendererObj.getStringWidth(Impulse.INSTANCE.hudManager.keystrokes.name) + 5, mc.fontRendererObj.FONT_HEIGHT + 2, Impulse.INSTANCE.hudManager.keystrokes));
		this.settingsButton.add(new SettingsButton(rowOne + mc.fontRendererObj.getStringWidth(Impulse.INSTANCE.hudManager.keystrokes.name) + 4, 80, 10, mc.fontRendererObj.FONT_HEIGHT + 2, Impulse.INSTANCE.hudManager.keystrokes));
		rowOne += ((mc.fontRendererObj.getStringWidth(Impulse.INSTANCE.hudManager.keystrokes.name) + 5) + 10.5F) + amp;
		//l
		//m
		//n
		//o
		//p
		// -----
		this.modButtons.add(new ModButton(rowTwo, 95, mc.fontRendererObj.getStringWidth(Impulse.INSTANCE.hudManager.perspectiveMod.name) + 5, mc.fontRendererObj.FONT_HEIGHT + 2, Impulse.INSTANCE.hudManager.perspectiveMod));
		this.settingsButton.add(new SettingsButton(rowTwo + mc.fontRendererObj.getStringWidth(Impulse.INSTANCE.hudManager.perspectiveMod.name) + 4, 95, 10, mc.fontRendererObj.FONT_HEIGHT + 2, Impulse.INSTANCE.hudManager.perspectiveMod));
		rowTwo += ((mc.fontRendererObj.getStringWidth(Impulse.INSTANCE.hudManager.perspectiveMod.name) + 5) + 10.5F) + amp;
		
		this.modButtons.add(new ModButton(rowTwo, 95, mc.fontRendererObj.getStringWidth(Impulse.INSTANCE.hudManager.positionMod.name) + 5, mc.fontRendererObj.FONT_HEIGHT + 2, Impulse.INSTANCE.hudManager.positionMod));
		this.settingsButton.add(new SettingsButton(rowTwo + mc.fontRendererObj.getStringWidth(Impulse.INSTANCE.hudManager.positionMod.name) + 4, 95, 10, mc.fontRendererObj.FONT_HEIGHT + 2, Impulse.INSTANCE.hudManager.positionMod));
		rowTwo += ((mc.fontRendererObj.getStringWidth(Impulse.INSTANCE.hudManager.positionMod.name) + 5) + 10.5F) + amp;

		this.modButtons.add(new ModButton(rowTwo, 95, mc.fontRendererObj.getStringWidth(Impulse.INSTANCE.hudManager.potionEffect.name) + 5, mc.fontRendererObj.FONT_HEIGHT + 2, Impulse.INSTANCE.hudManager.potionEffect));
		this.settingsButton.add(new SettingsButton(rowTwo + mc.fontRendererObj.getStringWidth(Impulse.INSTANCE.hudManager.potionEffect.name) + 4, 95, 10, mc.fontRendererObj.FONT_HEIGHT + 2, Impulse.INSTANCE.hudManager.potionEffect));
		rowTwo += ((mc.fontRendererObj.getStringWidth(Impulse.INSTANCE.hudManager.potionEffect.name) + 5) + 10.5F) + amp;

		
		this.modButtons.add(new ModButton(rowTwo, 95, mc.fontRendererObj.getStringWidth(Impulse.INSTANCE.hudManager.pingMod.name) + 5, mc.fontRendererObj.FONT_HEIGHT + 2, Impulse.INSTANCE.hudManager.pingMod));
		this.settingsButton.add(new SettingsButton(rowTwo + mc.fontRendererObj.getStringWidth(Impulse.INSTANCE.hudManager.pingMod.name) + 4, 95, 10, mc.fontRendererObj.FONT_HEIGHT + 2, Impulse.INSTANCE.hudManager.pingMod));
		rowTwo += ((mc.fontRendererObj.getStringWidth(Impulse.INSTANCE.hudManager.pingMod.name) + 5) + 10.5F) + amp;
		//q
		//r
		//s
		this.modButtons.add(new ModButton(rowThree, 110, mc.fontRendererObj.getStringWidth(Impulse.INSTANCE.hudManager.serverMod.name) + 5, mc.fontRendererObj.FONT_HEIGHT + 2, Impulse.INSTANCE.hudManager.serverMod));
		this.settingsButton.add(new SettingsButton(rowThree + mc.fontRendererObj.getStringWidth(Impulse.INSTANCE.hudManager.serverMod.name) + 4, 110, 10, mc.fontRendererObj.FONT_HEIGHT + 2, Impulse.INSTANCE.hudManager.serverMod));
		rowThree += ((mc.fontRendererObj.getStringWidth(Impulse.INSTANCE.hudManager.serverMod.name) + 5) + 10.5F) + amp;
		
		this.modButtons.add(new ModButton(rowThree, 110, mc.fontRendererObj.getStringWidth(Impulse.INSTANCE.hudManager.toggleStrintGui.name) + 5, mc.fontRendererObj.FONT_HEIGHT + 2, Impulse.INSTANCE.hudManager.toggleStrintGui));
		this.settingsButton.add(new SettingsButton(rowThree + mc.fontRendererObj.getStringWidth(Impulse.INSTANCE.hudManager.toggleStrintGui.name) + 4, 110, 10, mc.fontRendererObj.FONT_HEIGHT + 2, Impulse.INSTANCE.hudManager.toggleStrintGui));
		rowThree += ((mc.fontRendererObj.getStringWidth(Impulse.INSTANCE.hudManager.toggleStrintGui.name) + 5) + 10.5F) + amp;
		//t
		// -----
		this.modButtons.add(new ModButton(rowThree, 110, mc.fontRendererObj.getStringWidth(Impulse.INSTANCE.hudManager.targetHUD.name) + 5, mc.fontRendererObj.FONT_HEIGHT + 2, Impulse.INSTANCE.hudManager.targetHUD));
		this.settingsButton.add(new SettingsButton(rowThree + mc.fontRendererObj.getStringWidth(Impulse.INSTANCE.hudManager.targetHUD.name) + 4, 110, 10, mc.fontRendererObj.FONT_HEIGHT + 2, Impulse.INSTANCE.hudManager.targetHUD));
		rowThree += ((mc.fontRendererObj.getStringWidth(Impulse.INSTANCE.hudManager.targetHUD.name) + 5) + 10.5F) + amp;
		//u
		//v
		//w
		//x
		//y
		//z
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		this.drawDefaultBackground();
		
		ScaledResolution sr = new ScaledResolution(mc);
		
		super.drawScreen(mouseX, mouseY, partialTicks);
		
		Gui.drawRect(100, 50, sr.getScaledWidth() - 100, sr.getScaledHeight() - 50, new Color(0, 0, 0, 170).getRGB());
		
		Gui.drawRect(100, 75, sr.getScaledWidth() - 100, 70, new Color(114, 223, 228, 255).getRGB());
		GlStateManager.pushMatrix();
		GlStateManager.scale(2, 2, 1);
		mc.fontRendererObj.drawStringWithShadow("Mods", (sr.getScaledWidth() / 4F) - mc.fontRendererObj.getStringWidth("Mods") / 2.2F , 26.5F, -1);
		GlStateManager.popMatrix();
		
		for(ModButton m : modButtons) {
			m.draw();
		}
		
		for(SettingsButton s : settingsButton) {
			s.draw();
		}
		
		backButton.draw();
	}
	
	@Override
	protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
		super.mouseClicked(mouseX, mouseY, mouseButton);
		
		for (ModButton m : modButtons) {
			m.onClick(mouseX, mouseY, mouseButton);
		}
		
		for (SettingsButton s : settingsButton) {
			s.onClick(mouseX, mouseY, mouseButton);
		}
		
		backButton.onClick(mouseX, mouseY, mouseButton);
	}
	
}
