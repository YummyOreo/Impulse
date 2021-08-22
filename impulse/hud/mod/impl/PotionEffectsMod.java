package impulse.hud.mod.impl;

import java.util.Collection;

import impulse.hud.mod.HudMod;
import net.minecraft.client.resources.I18n;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

public class PotionEffectsMod extends HudMod {

	public PotionEffectsMod() {
		super("[Potion Effects]", 555, 5, "Shows you all your active potion effects!");
	}
	
	@Override
	public void draw() {
		
		fr.drawStringWithShadow("Effects", getX(), getY(), -1);
		
		this.drawEffects();
		
		super.draw();
	}
	
	@Override
	public void renderDummy(int mouseX, int mouseY) {

		fr.drawStringWithShadow("Effects", getX(), getY(), -1);
		
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
			
			fr.drawStringWithShadow(name, getX(), getY() + (fr.FONT_HEIGHT * (count * 1.5f)) + 4, this.getColor());
			fr.drawStringWithShadow(time, getX() + fr.getStringWidth(name) + 5, getY() + (fr.FONT_HEIGHT * (count * 1.5f)) + 4, this.getColor());
		}
	}
}
