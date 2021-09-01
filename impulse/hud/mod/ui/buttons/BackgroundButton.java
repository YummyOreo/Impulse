package impulse.hud.mod.ui.buttons;

import impulse.hud.mod.HudMod;
import impulse.hud.mod.ui.temp.CheckButton;

public class BackgroundButton extends CheckButton {

	public BackgroundButton(int x, int y, HudMod m) {
		super(x, y, m, "Background");
	}
	
	@Override
	public boolean checkEnabled() {
		return this.m.getBackground();
	}
	
	@Override
	public void handleClick() {
		this.m.setBackground(!this.m.getBackground());
	}

}
