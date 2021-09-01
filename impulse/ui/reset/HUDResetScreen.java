package impulse.ui.reset;

import java.awt.Color;
import java.io.IOException;

import impulse.ui.reset.comp.ResetButton;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;

public class HUDResetScreen extends GuiScreen {

	private ResetButton resetButton;
	
	@Override
	public void initGui() {
		super.initGui();
		this.resetButton = new ResetButton(310, 150, 31, mc.fontRendererObj.FONT_HEIGHT + 2);
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
		mc.fontRendererObj.drawStringWithShadow("Reset Mods, THIS WILL CLOSE MINECRAFT", (sr.getScaledWidth() / 4F) - mc.fontRendererObj.getStringWidth("Reset Mods, THIS WILL CLOSE MINECRAFT") / 2.2F , 26.5F, -1);
		GlStateManager.popMatrix();
		
		resetButton.draw();
	}

	@Override
	public void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
		super.mouseClicked(mouseX, mouseY, mouseButton);
		
		resetButton.onClick(mouseX, mouseY, mouseButton);
	}
		

}
