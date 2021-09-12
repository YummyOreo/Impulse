package impulse.hud;

import java.io.IOException;

import impulse.Impulse;
import impulse.event.EventTarget;
import impulse.event.impl.ClientTick;
import impulse.hud.mod.HudMod;
import impulse.ui.clickgui.ClickGUI;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;

public class HUDConfigScreen extends GuiScreen {
	
	public boolean buttonsEnabeled = true;
	
	@Override
	public void initGui() {
		
		super.initGui();
		// adds the buttons to the toggle mods and cosmetics
		if (buttonsEnabeled) {
			this.buttonList.add(new GuiButton(100 , this.width / 2 - 50, this.height / 2 - 10, 100, 20, "Mod Settings"));
		} else {
			
		}
		
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		this.drawDefaultBackground();
		// Draws each mods
		
		for (HudMod m : Impulse.INSTANCE.hudManager.hudMods) {
			if(m.isEnabled()) {
				m.renderDummy(mouseX, mouseY);
			}
		}
		
		super.drawScreen(mouseX, mouseY, partialTicks);
	}
	
	@Override
	protected void actionPerformed(GuiButton button) throws IOException {
		super.actionPerformed(button);
		
		// Opens the correct screen when hitting a button
		if (button.id == 100) {
			mc.displayGuiScreen(new ClickGUI());
		}
		
	}
	
}
