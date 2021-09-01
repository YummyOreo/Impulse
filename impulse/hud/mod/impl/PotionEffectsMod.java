package impulse.hud.mod.impl;

import java.io.IOException;
import java.util.Collection;

import impulse.hud.mod.HudMod;
import impulse.hud.mod.ui.buttons.BackgroundButton;
import impulse.hud.mod.ui.buttons.ColorButton;
import impulse.util.ui.GuiUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

public class PotionEffectsMod extends HudMod {

	private ColorButton colorButton;
	
	public PotionEffectsMod() {
		super("[Potion Effects]", 555, 5, "Shows you all your active potion effects!");
	}
	
	@Override
	public void draw() {
		
		if (this.getRainbow()) {
    		GuiUtils.drawChromaString("Effects", getX(), getY(), true);
    	} else {
    		fr.drawStringWithShadow("Effects", getX(), getY(), this.getColor());
    	}
		
		this.drawEffects();
		
		super.draw();
	}
	
	@Override
	public void renderDummy(int mouseX, int mouseY) {

		fr.drawStringWithShadow("Effects", getX(), getY(), this.getColor());
		
		super.renderDummy(mouseX, mouseY);
	}
	
	@Override
	public int getWidth() {
		return fr.getStringWidth("Effects");
	}
	
	@Override
	public int getHeight() {
		return fr.FONT_HEIGHT;
	}
	
	private void drawEffects() {
		Collection<PotionEffect> effects = mc.thePlayer.getActivePotionEffects();
		
		int count = 0;
		
		for (PotionEffect e : effects) {
			count++;
			
			String name = I18n.format(e.getEffectName(), new Object[0]);
			
			switch (e.getAmplifier()) {
            case 1: {
                name = name + " " + I18n.format("enchantment.level.2", new Object[0]);
                break;
            }
            case 2: {
                name = name + "  " + I18n.format("enchantment.level.3", new Object[0]);
                break;
            }
            case 3: {
                name = name + " " + I18n.format("enchantment.level.4", new Object[0]);
                break;
            }
        }
        final String time = Potion.getDurationString(e);
			
    	if (this.getRainbow()) {
    		GuiUtils.drawChromaString(name + "   " + time, getX(), (int) (getY() + (fr.FONT_HEIGHT * (count * 1.5f)) + 4), true);
    	} else {
    		fr.drawStringWithShadow(name + "   " + time, getX(), getY() + (fr.FONT_HEIGHT * (count * 1.5f)) + 4, this.getColor());
    	}
        
		}
	}
	
	@Override
	public void initGui(GuiScreen gui) {
		this.colorButton = new ColorButton(110, 90, Minecraft.getMinecraft().fontRendererObj.getStringWidth("Change Text Color") + 5, Minecraft.getMinecraft().fontRendererObj.FONT_HEIGHT + 2, this);
	}
	
	@Override
	public void drawScreen(GuiScreen gui, int mouseX, int mouseY, float partialTicks) {
		colorButton.draw();
	}
	
	@Override
	public void mouseClicked(GuiScreen gui, int mouseX, int mouseY, int mouseButton) throws IOException {
		colorButton.onClick(mouseX, mouseY, mouseButton);
	}
}
