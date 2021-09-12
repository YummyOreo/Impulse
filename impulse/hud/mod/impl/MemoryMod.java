package impulse.hud.mod.impl;

import java.awt.Color;
import java.io.IOException;

import impulse.hud.mod.Catagory;
import impulse.hud.mod.HudMod;
import impulse.hud.mod.ui.buttons.BackgroundButton;
import impulse.hud.mod.ui.buttons.ColorButton;
import impulse.util.ui.GuiUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiScreen;

public class MemoryMod extends HudMod {
	
	private BackgroundButton backgroundButton;
	private ColorButton colorButton;

	public MemoryMod() {
		super("[Memory usage]", 555, 5, "Shows you memory usage!", Catagory.HUD);
	}
	
	private static long bytesToMb(long bytes)
    {
        return bytes / 1024L / 1024L;
    }
	
	@Override
	public void draw() {
		
		long i = Runtime.getRuntime().maxMemory();
        long j = Runtime.getRuntime().totalMemory();
        long k = Runtime.getRuntime().freeMemory();
        long l = j - k;
        String ram = (String.format("Mem: % 2d%% %03d/%03dMB", new Object[]{Long.valueOf(l * 100L / i), Long.valueOf(bytesToMb(l)), Long.valueOf(bytesToMb(i))}));
        
        if (this.getBackground()) {
            Gui.drawRect(this.getX() - 1, this.getY() - 1, this.getX() + fr.getStringWidth(ram) + 1, this.getY() + fr.FONT_HEIGHT , new Color(0, 0, 0, 150).getRGB());
        }
        
        if (this.getRainbow()) {
        	GuiUtils.drawChromaString(ram, getX(), getY(), true);
        } else {
        	fr.drawStringWithShadow(ram, getX(), getY(), this.getColor());
        }
        
		super.draw();
	}
	
	@Override
	public void renderDummy(int mouseX, int mouseY) {
		
		if (this.getBackground()) {
            Gui.drawRect(this.getX() - 1, this.getY() - 1, this.getX() + fr.getStringWidth("Ram : 1000mb") + 1, this.getY() + fr.FONT_HEIGHT , new Color(0, 0, 0, 150).getRGB());
        }
		
		if (this.getRainbow()) {
        	GuiUtils.drawChromaString("Ram : 1000mb", getX(), getY(), true);
        } else {
        	fr.drawStringWithShadow("Ram : 1000mb", getX(), getY(), -1);
        }

		super.renderDummy(mouseX, mouseY);
	}
	
	@Override
	public int getWidth() {
		return fr.getStringWidth("[Hacks: Enabled]");
	}
	
	@Override
	public int getHeight() {
		return fr.FONT_HEIGHT;
	}
	
	@Override
	public void initGui(GuiScreen gui) {
		this.colorButton = new ColorButton(110, 90, Minecraft.getMinecraft().fontRendererObj.getStringWidth("Change Text Color") + 5, Minecraft.getMinecraft().fontRendererObj.FONT_HEIGHT + 2, this);
		
		backgroundButton = new BackgroundButton(110, 110, this);
	}
	
	@Override
	public void drawScreen(GuiScreen gui, int mouseX, int mouseY, float partialTicks) {
		colorButton.draw();
		
		backgroundButton.draw();
	}
	
	@Override
	public void mouseClicked(GuiScreen gui, int mouseX, int mouseY, int mouseButton) throws IOException {
		colorButton.onClick(mouseX, mouseY, mouseButton);
		
		backgroundButton.onClick(mouseX, mouseY, mouseButton);
	}
}
