package impulse.ui.settings;

import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;

import com.sun.jna.platform.unix.X11.Font;

import impulse.Impulse;
import impulse.hud.mod.HudManager;
import impulse.hud.mod.HudMod;
import impulse.hud.mod.tag.ParseTags;
import impulse.ui.clickgui.comp.ModButton;
import impulse.ui.clickgui.comp.SettingsButton;
import impulse.ui.settings.buttons.BackButton;
import impulse.ui.settings.buttons.utils.EnabledButton;
import impulse.util.ui.GuiUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiMultiplayer;
import net.minecraft.client.gui.GuiOptions;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiSelectWorld;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;

public class SettingsGui extends GuiScreen {
	
	private BackButton backButton;
	private EnabledButton enabledButton;
	
	private HudMod m;
	
	private Minecraft mc = Minecraft.getMinecraft();
	
	private FontRenderer fr = mc.fontRendererObj;
	
	private HudManager hm = Impulse.INSTANCE.hudManager;
	
	private boolean menu = false;
	
	public SettingsGui(HudMod m) {
		this.m = m;
	}
	
	@Override
	public void initGui() {
		super.initGui();
		
		ScaledResolution sr = new ScaledResolution(mc);
		
		this.backButton = new BackButton(110, 55, 20, fontRendererObj.FONT_HEIGHT + 2, m);
				
		this.m.initGui(this);
		
		this.enabledButton = new EnabledButton(100 , sr.getScaledHeight() - 70, sr.getScaledWidth() - 100, fontRendererObj.FONT_HEIGHT + 2, this.m);
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		
		ScaledResolution sr = new ScaledResolution(mc);
		
		super.drawScreen(mouseX, mouseY, partialTicks);
		
		if (this.menu) {
			this.drawDefaultBackground();
		}

		
		GuiUtils.drawRoundedRect(100 - 3, 50 - 3, sr.getScaledWidth() - 100 + 3, sr.getScaledHeight() - 50 + 3, 2, new Color(88, 95, 110).getRGB());;
		
		Gui.drawRect(100, 50, sr.getScaledWidth() - 100, sr.getScaledHeight() - 50, new Color(85, 91, 102).getRGB());
		Gui.drawRect(100, 50, sr.getScaledWidth() - 100, 80, new Color(47, 50, 56).getRGB());
		
		
		Gui.drawRect(100, 85, sr.getScaledWidth() - 100, 80, new Color(114, 223, 228, 255).getRGB());
		GlStateManager.pushMatrix();
		GlStateManager.scale(2, 2, 1);
		fr.drawStringWithShadow(this.m.name, (sr.getScaledWidth() / 4F) - fr.getStringWidth(this.m.name) / 2.2F , 26.5F, -1);
		GlStateManager.scale(0.5, 0.5, 1);
		GlStateManager.translate(0.5, 0.5, 1);
		
		this.drawCenteredString(fr, this.m.description, (sr.getScaledWidth() / 4F) * 2f, 69.5F, -1);
		
		GlStateManager.popMatrix();
		
		this.m.drawScreen(this, mouseX, mouseY, partialTicks);
		
		backButton.draw();
		
		enabledButton.draw();
	}
	
	@Override
	public void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
		super.mouseClicked(mouseX, mouseY, mouseButton);
		
		this.m.mouseClicked(this, mouseX, mouseY, mouseButton);
		
		backButton.onClick(mouseX, mouseY, mouseButton);
		
		enabledButton.onClick(mouseX, mouseY, mouseButton);
	}
}
