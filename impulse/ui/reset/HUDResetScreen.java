package impulse.ui.reset;

import java.awt.Color;
import java.io.IOException;

import impulse.ui.reset.comp.ResetButton;
import impulse.util.ui.GuiUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;

public class HUDResetScreen extends GuiScreen {

	private ResetButton resetButton;
	private FontRenderer fr = Minecraft.getMinecraft().fontRendererObj;
	
	@Override
	public void initGui() {
		super.initGui();
		this.resetButton = new ResetButton(110, 90, 31, mc.fontRendererObj.FONT_HEIGHT + 2);
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		ScaledResolution sr = new ScaledResolution(mc);
		
		super.drawScreen(mouseX, mouseY, partialTicks);

		
		GuiUtils.drawRoundedRect(100 - 3, 50 - 3, sr.getScaledWidth() - 100 + 3, sr.getScaledHeight() - 50 + 3, 2, new Color(88, 95, 110).getRGB());;
		
		Gui.drawRect(100, 50, sr.getScaledWidth() - 100, sr.getScaledHeight() - 50, new Color(85, 91, 102).getRGB());
		Gui.drawRect(100, 50, sr.getScaledWidth() - 100, 80, new Color(47, 50, 56).getRGB());
		
		
		Gui.drawRect(100, 85, sr.getScaledWidth() - 100, 80, new Color(114, 223, 228, 255).getRGB());
		GlStateManager.pushMatrix();
		GlStateManager.scale(2, 2, 1);
		fr.drawStringWithShadow("Reset", (sr.getScaledWidth() / 4F) - fr.getStringWidth("Reset") / 2.2F , 26.5F, -1);
		GlStateManager.scale(0.5, 0.5, 1);
		GlStateManager.translate(0.5, 0.5, 1);
		
		this.drawCenteredString(fr, "This resets all mod settings. (This will also close the client, just relunch it!)", (sr.getScaledWidth() / 4F) * 2f, 69.5F, -1);
		
		GlStateManager.popMatrix();
		
		resetButton.draw();
	}

	@Override
	public void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
		super.mouseClicked(mouseX, mouseY, mouseButton);
		
		resetButton.onClick(mouseX, mouseY, mouseButton);
	}
		

}
