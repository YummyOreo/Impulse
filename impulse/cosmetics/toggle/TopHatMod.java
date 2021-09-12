package impulse.cosmetics.toggle;

import java.io.IOException;

import impulse.Impulse;
import impulse.hud.mod.Catagory;
import impulse.hud.mod.HudMod;
import impulse.hud.mod.ui.buttons.ColorButton;
import impulse.util.websockets.Connect;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;

public class TopHatMod extends HudMod {

	private ColorButton colorButton;
	
	public TopHatMod() {
		super("[Top Hat]", 0, 0, "A top hat!", Catagory.COSMETIC);
		this.addTag("Cos");
	}
	
	@Override
	public void draw() {
		
		super.draw();
	}
	
	@Override
	public void renderDummy(int mouseX, int mouseY) {

		super.renderDummy(mouseX, mouseY);
	}
	
	@Override
	public int getWidth() {
		return 0;
	}
	
	@Override
	public int getHeight() {
		return 0;
	}
	
	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return super.isEnabled();
	}
	
	@Override
	public void load() {
		super.load();
		if (this.enabled && Connect.INSTANCE.enabled) {
			Impulse.INSTANCE.socketClient.addCosmetic(mc.thePlayer.getName(), this.name.replace(" ", "") + "%3A" + this.getColor());
		}
	}
	
	@Override
	public void onEnable() {
		super.onEnable();
		if (Connect.INSTANCE.enabled) {
			Impulse.INSTANCE.socketClient.addCosmetic(mc.thePlayer.getName(), this.name.replace(" ", "") + "%3A" + this.getColor());
		}
	}
	
	@Override
	public void onDisable() {
		super.onDisable();
		if (Connect.INSTANCE.enabled) {
			Impulse.INSTANCE.socketClient.removeCosmetic(mc.thePlayer.getName(), this.name.replace(" ", "") + "%3A" + this.getColor());

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
