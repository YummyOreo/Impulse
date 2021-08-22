package impulse.ui.clickgui;

import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;

import impulse.Impulse;
import impulse.ui.clickgui.comp.BackButton;
import impulse.ui.clickgui.comp.ModButton;
import impulse.ui.clickgui.comp.SettingsButton;
import impulse.ui.settings.buttons.utils.EnabledButton;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;

public class ClickGUICosmetic extends GuiScreen {

	ArrayList<ModButton> modButtons = new ArrayList<>();
	ArrayList<SettingsButton> settingsButton = new ArrayList<>();
	
	private BackButton backButton;
	
	@Override
	public void initGui() {
		int rowOne = 110;
		int amp = 5;
		
		super.initGui();
		
		this.backButton = new BackButton(110, 55, 20, mc.fontRendererObj.FONT_HEIGHT + 2);
		
		this.modButtons.add(new ModButton(110, 80, mc.fontRendererObj.getStringWidth(Impulse.INSTANCE.hudManager.topHatMod.name) + 5, mc.fontRendererObj.FONT_HEIGHT + 2, Impulse.INSTANCE.hudManager.topHatMod));
		this.settingsButton.add(new SettingsButton(rowOne + mc.fontRendererObj.getStringWidth(Impulse.INSTANCE.hudManager.topHatMod.name) + 4, 80, 10, mc.fontRendererObj.FONT_HEIGHT + 2, Impulse.INSTANCE.hudManager.topHatMod));
		rowOne += ((mc.fontRendererObj.getStringWidth(Impulse.INSTANCE.hudManager.topHatMod.name) + 5) + 10.5F) + amp;
				
		this.modButtons.add(new ModButton(rowOne, 80, mc.fontRendererObj.getStringWidth(Impulse.INSTANCE.hudManager.capeMod.name) + 5, mc.fontRendererObj.FONT_HEIGHT + 2, Impulse.INSTANCE.hudManager.capeMod));
		this.settingsButton.add(new SettingsButton(rowOne + mc.fontRendererObj.getStringWidth(Impulse.INSTANCE.hudManager.capeMod.name) + 4, 80, 10, mc.fontRendererObj.FONT_HEIGHT + 2, Impulse.INSTANCE.hudManager.capeMod));
		rowOne += ((mc.fontRendererObj.getStringWidth(Impulse.INSTANCE.hudManager.capeMod.name) + 5) + 10.5F) + amp;
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
		mc.fontRendererObj.drawStringWithShadow("Cosmetics", (sr.getScaledWidth() / 4F) - mc.fontRendererObj.getStringWidth("Cosmetics") / 2.2F , 26.5F, -1);
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
