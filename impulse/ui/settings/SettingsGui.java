package impulse.ui.settings;

import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;

import com.sun.jna.platform.unix.X11.Font;

import impulse.Impulse;
import impulse.hud.mod.HudManager;
import impulse.hud.mod.HudMod;
import impulse.ui.clickgui.comp.ModButton;
import impulse.ui.clickgui.comp.SettingsButton;
import impulse.ui.settings.buttons.BackButton;
import impulse.ui.settings.buttons.utils.ColorButton;
import impulse.ui.settings.buttons.utils.EnabledButton;
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

public class SettingsGui extends GuiScreen{
	
	private ColorButton colorButton;
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
	
	public SettingsGui(HudMod m, boolean menu) {
		this.m = m;
		this.menu = menu;
	}
	
	@Override
	public void initGui() {
		super.initGui();
		
		ScaledResolution sr = new ScaledResolution(mc);
		
		this.backButton = new BackButton(110, 55, 20, fontRendererObj.FONT_HEIGHT + 2, m, menu);
		if ((this.m.getX() != 0 && this.m.getY() != 0) || this.m.description.split(":")[0].equals("Cos")) {
			this.colorButton = new ColorButton(110, 90, fontRendererObj.getStringWidth("Change Text Color") + 5, fontRendererObj.FONT_HEIGHT + 2, this.m);
		} else {
			System.out.println(this.m.name);
		}
		this.enabledButton = new EnabledButton(100 , sr.getScaledHeight() - 70, sr.getScaledWidth() - 100, fontRendererObj.FONT_HEIGHT + 2, this.m);
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		
		ScaledResolution sr = new ScaledResolution(mc);
		
		super.drawScreen(mouseX, mouseY, partialTicks);
		
		if (this.menu) {
			this.drawDefaultBackground();
		}
		Gui.drawRect(100, 50, sr.getScaledWidth() - 100, sr.getScaledHeight() - 50, new Color(0, 0, 0, 170).getRGB());
		
		Gui.drawRect(100, 85, sr.getScaledWidth() - 100, 80, new Color(114, 223, 228, 255).getRGB());
		GlStateManager.pushMatrix();
		GlStateManager.scale(2, 2, 1);
		fr.drawStringWithShadow(this.m.name, (sr.getScaledWidth() / 4F) - fr.getStringWidth(this.m.name) / 2.2F , 26.5F, -1);
		GlStateManager.scale(0.5, 0.5, 1);
		GlStateManager.translate(0.5, 0.5, 1);
		
		if (this.m.description.split(":")[0].equals("Cos") || this.m.description.split(":")[0].equals("CosNoSettings")) {
			this.drawCenteredString(fr, this.m.description.split(":")[1], (sr.getScaledWidth() / 4F) * 2f, 69.5F, -1);
		} else {
			this.drawCenteredString(fr, this.m.description, (sr.getScaledWidth() / 4F) * 2f, 69.5F, -1);
		}
		GlStateManager.popMatrix();
		
		try {
			colorButton.draw();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		backButton.draw();
		
		enabledButton.draw();
	}
	
	@Override
	protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
		super.mouseClicked(mouseX, mouseY, mouseButton);
		
		try {
			colorButton.onClick(mouseX, mouseY, mouseButton);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		backButton.onClick(mouseX, mouseY, mouseButton);
		
		enabledButton.onClick(mouseX, mouseY, mouseButton);
	}
}
