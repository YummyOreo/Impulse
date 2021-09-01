package impulse.hud.mod.impl;

import java.awt.Color;
import java.awt.List;
import java.io.IOException;

import impulse.hud.mod.HudMod;
import impulse.hud.mod.ui.buttons.BackgroundButton;
import impulse.hud.mod.ui.buttons.ColorButton;
import impulse.util.ui.GuiUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.IResourcePack;
import net.minecraft.client.resources.ResourcePackRepository.Entry;

public class ResourcePackMod extends HudMod {
	
    private Minecraft mc = Minecraft.getMinecraft();
    private IResourcePack pack = getCurrentPack();
    
    private BackgroundButton backgroundButton;
	private ColorButton colorButton;

	public ResourcePackMod() {
		super("[Pack Display]", 555, 5, "Allows you to see your current pack");
	}
	
	@Override
	public void draw() {
		
		if (pack == null) {
			
			if (this.getBackground()) {
				Gui.drawRect(this.getX() - 1, this.getY() - 1, this.getX() + fr.getStringWidth("[No Pack]") + 1, this.getY() + fr.FONT_HEIGHT , new Color(0, 0, 0, 150).getRGB());
			}
			
			if (this.getRainbow()) {
				GuiUtils.drawChromaString("[No Pack]", getX(), getY(), true);
			} else {
				fr.drawStringWithShadow("[No Pack]", getX(), getY(), getColor());
			}
			
		} else {
			
			if (this.getBackground()) {
				Gui.drawRect(this.getX() - 1, this.getY() - 1, this.getX() + fr.getStringWidth("[" + pack.getPackName() + "]") + 1, this.getY() + fr.FONT_HEIGHT , new Color(0, 0, 0, 150).getRGB());
			}
			
			if (this.getRainbow()) {
				String name = pack.getPackName().replace("§0", "");
				name = name.replace("§1", "");
				name = name.replace("§2", "");
				name = name.replace("§3", "");
				name = name.replace("§4", "");
				name = name.replace("§5", "");
				name = name.replace("§6", "");
				name = name.replace("§7", "");
				name = name.replace("§8", "");
				name = name.replace("§9", "");
				name = name.replace("§a", "");
				name = name.replace("§b", "");
				name = name.replace("§c", "");
				name = name.replace("§d", "");
				name = name.replace("§e", "");
				name = name.replace("§f", "");
				name = name.replace("§r", "");
				name = name.replace("§k", "");
				name = name.replace("§l", "");
				name = name.replace("§m", "");
				name = name.replace("§n", "");
				name = name.replace("§o", "");
				GuiUtils.drawChromaString("[" + name + "]", getX(), getY(), true);
			} else {
				fr.drawStringWithShadow("[" + pack.getPackName() + "§r]", getX(), getY(), getColor());
			}
		}
		
		super.draw();
	}
	
	@Override
	public void renderDummy(int mouseX, int mouseY) {

		if (pack == null) {
			
			if (this.getBackground()) {
				Gui.drawRect(this.getX() - 1, this.getY() - 1, this.getX() + fr.getStringWidth("[No Pack]") + 1, this.getY() + fr.FONT_HEIGHT , new Color(0, 0, 0, 150).getRGB());
			}
			
			if (this.getRainbow()) {
				String name = pack.getPackName().replace("§0", "");
				name = name.replace("§1", "");
				name = name.replace("§2", "");
				name = name.replace("§3", "");
				name = name.replace("§4", "");
				name = name.replace("§5", "");
				name = name.replace("§6", "");
				name = name.replace("§7", "");
				name = name.replace("§8", "");
				name = name.replace("§9", "");
				name = name.replace("§a", "");
				name = name.replace("§b", "");
				name = name.replace("§c", "");
				name = name.replace("§d", "");
				name = name.replace("§e", "");
				name = name.replace("§f", "");
				name = name.replace("§r", "");
				name = name.replace("§k", "");
				name = name.replace("§l", "");
				name = name.replace("§m", "");
				name = name.replace("§n", "");
				name = name.replace("§o", "");
				GuiUtils.drawChromaString("[" + name + "]", getX(), getY(), true);
			} else {
				fr.drawStringWithShadow("[No Pack]", getX(), getY(), getColor());
			}
			
		} else {
			
			if (this.getBackground()) {
				Gui.drawRect(this.getX() - 1, this.getY() - 1, this.getX() + fr.getStringWidth("[" + pack.getPackName() + "]") + 1, this.getY() + fr.FONT_HEIGHT , new Color(0, 0, 0, 150).getRGB());
			}
			
			if (this.getRainbow()) {
				GuiUtils.drawChromaString("[" + pack.getPackName() + "]", getX(), getY(), true);
			} else {
				fr.drawStringWithShadow("[" + pack.getPackName() + "§r]", getX(), getY(), getColor());
			}
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
	
	private IResourcePack getCurrentPack() {
        java.util.List<Entry> list = this.mc.getResourcePackRepository().getRepositoryEntries();
        Object pack = null;
        
        return (IResourcePack) (list.size() > 0 ? (((java.util.List<Entry>) list).get(0)).getResourcePack() : this.mc.mcDefaultResourcePack);
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
