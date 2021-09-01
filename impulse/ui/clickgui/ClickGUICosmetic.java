package impulse.ui.clickgui;

import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;

import impulse.Impulse;
import impulse.ui.clickgui.comp.BackButton;
import impulse.ui.clickgui.comp.ModButton;
import impulse.ui.clickgui.comp.SettingsButton;
import impulse.ui.settings.buttons.utils.EnabledButton;
import impulse.util.ui.GuiUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;

public class ClickGUICosmetic extends GuiScreen {

	ArrayList<ModButton> modButtons = new ArrayList<>();
	ArrayList<SettingsButton> settingsButton = new ArrayList<>();
	
	private BackButton backButton;
	private int longString = Minecraft.getMinecraft().fontRendererObj.getStringWidth("Top hat mod") + 5;
	
	@Override
	public void initGui() {
		int rowOne = 110;
		int amp = 5;
		
		super.initGui();
		
		this.backButton = new BackButton(110, 55, 20, mc.fontRendererObj.FONT_HEIGHT + 2);
		
		this.modButtons.add(new ModButton(110, 80, longString + 5, mc.fontRendererObj.FONT_HEIGHT + 2, Impulse.INSTANCE.hudManager.topHatMod));
		this.settingsButton.add(new SettingsButton(rowOne + longString + 4, 80, 10, mc.fontRendererObj.FONT_HEIGHT + 2, Impulse.INSTANCE.hudManager.topHatMod));
		rowOne += ((longString + 5) + 10.5F) + amp;
				
		this.modButtons.add(new ModButton(rowOne, 80, longString + 5, mc.fontRendererObj.FONT_HEIGHT + 2, Impulse.INSTANCE.hudManager.capeMod));
		this.settingsButton.add(new SettingsButton(rowOne + longString + 4, 80, 10, mc.fontRendererObj.FONT_HEIGHT + 2, Impulse.INSTANCE.hudManager.capeMod));
		rowOne += ((longString + 5) + 10.5F) + amp;
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
	}
	
	
}
