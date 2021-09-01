package impulse.hud.mod.impl;

import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.Predicate;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

import impulse.hud.mod.HudMod;
import impulse.hud.mod.ui.buttons.BackgroundButton;
import impulse.hud.mod.ui.buttons.ColorButton;
import impulse.hud.mod.ui.temp.ToggleButton;
import impulse.hud.mod.utils.Loader;
import impulse.settings.ModeSetting;
import impulse.settings.NumberSetting;
import impulse.util.ui.GuiUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.settings.KeyBinding;

public class KeystrokesMod extends HudMod {
	
	private ColorButton colorButton;
	private BgPressedButton bgPressedButton;
	
	NumberSetting bgPressed;
	
	private List<Long> clicksRMB = new ArrayList<Long>();
	private List<Long> clicksLMB = new ArrayList<Long>();
	private boolean pressedLMB;
	private boolean pressedRMB;
	private long lastPressedLMB;
	private long lastPressedRMB;
	
	public KeystrokesMod() {
		super("[Keystrokes]", 120, 250, "Allows you to see your CPS, and what keys your pressing!");
		
		this.bgPressed = new NumberSetting("bgPressed", new Color(255, 255, 255, 102).getRGB(), new Color(255, 255, 255, 102).getRGB(), new Color(0, 0, 0, 102).getRGB(), new Color(255, 255, 255, 102).getRGB());
		this.loadSetting(); 
	}
		
	@Override
	public HashMap<String, Object> getSettings() {
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		map.put(bgPressed.name, bgPressed.getValue());
		
		return map;
		
	}
	
	@Override
	public void loadSetting() {
		this.bgPressed.value = (int) Loader.loadSetting(this.name, "bgPressed", new Color(0, 0, 0, 102).getRGB());
	}
	
	@Override
	public void changeSetting(String name, Object value) {
		if (name == "bgPressed") {
			this.bgPressed.value =  (int) value;
		}
	}
	
	public static enum KeystrokesMode {
		
		WASD(Key.W, Key.A, Key.S, Key.D),
		WASD_MOUSE(Key.W, Key.A, Key.S, Key.D, Key.LMB, Key.RMB),
		WASD_JUMP_MOUSE(Key.W, Key.A, Key.S, Key.D, Key.LMB, Key.RMB, Key.Jump2);
		
		
		private final Key[] keys;
		private int width, height;
		
		private KeystrokesMode(Key... keysIn) {
			this.keys = keysIn;
			
			for(Key key : keys) {
				this.width = Math.max(this.width, key.getX() + key.getWidth());
				this.height = Math.max(this.height, key.getY() + key.getHeight());
			}
		}
		
		public int getHeight() {
			return height;
		}
		
		public int getWidth() {
			return width;
		}
		
		public Key[] getKeys() {
			return keys;
		}
		
	}
	
	public static class Key {
		public static Minecraft mc = Minecraft.getMinecraft();
		
		private static final Key W = new Key("W", mc.gameSettings.keyBindForward, 21, 01, 18, 18);
		private static final Key A = new Key("A", mc.gameSettings.keyBindLeft, 1, 21, 18, 18);
		private static final Key S = new Key("S", mc.gameSettings.keyBindBack, 21, 21, 18, 18);
		private static final Key D = new Key("D", mc.gameSettings.keyBindRight , 41, 21, 18, 18);

		private static final Key LMB = new Key("LMB", mc.gameSettings.keyBindAttack , 1, 41, 28, 17);
		private static final Key RMB = new Key("RMB", mc.gameSettings.keyBindUseItem , 31, 41, 28, 17);
		
		private static final Key Jump2 = new Key("----", mc.gameSettings.keyBindJump , 1, 72, 58, 18);
		

		private final String name;
		private final KeyBinding keyBind;
		private final int x, y, w, h;
		
		public Key(String name, KeyBinding keyBind, int x, int y, int w, int h) {
			this.name = name;
			this.keyBind = keyBind;
			this.x = x;
			this.y = y;
			this.w = w;
			this.h = h;
		}
		
		public boolean isDown() {
			return keyBind.isKeyDown();
		}
		
		public int getHeight() {
			return h;
		}
		
		public int getWidth() {
			return w;
		}
		
		public String getName() {
			return name;
		}
		
		public int getX() {
			return x;
		}
		
		public int getY() {
			return y;
		}
		
	}
	
	private KeystrokesMode mode = KeystrokesMode.WASD_JUMP_MOUSE;
	
	public int getPressedBG() {
		return (int) bgPressed.value;
	}
	
	@Override
	public int getWidth() {
		return 58;
	}
	
	@Override
	public int getHeight() {
		return 82;
	}
	
	@Override
	public void draw() {
		
		if (Mouse.isButtonDown(0) != this.pressedLMB) {
			
			this.lastPressedLMB = System.currentTimeMillis();
			this.pressedLMB = Mouse.isButtonDown(0);
			
			if (Mouse.isButtonDown(0)) {
				this.clicksLMB.add(this.lastPressedLMB);
			}
		}
		if (Mouse.isButtonDown(1) != this.pressedRMB) {
			
			this.lastPressedRMB = System.currentTimeMillis();
			this.pressedRMB = Mouse.isButtonDown(1);
			
			if (Mouse.isButtonDown(1)) {
				this.clicksRMB.add(this.lastPressedRMB);
			}
		}
		
		cps();	
		
		GL11.glPushMatrix();
		
		for(Key key : mode.getKeys()) {
			
			int textWidth = fr.getStringWidth(key.getName());
			
			Gui.drawRect(getX() + key.getX(), getY() + key.getY(), getX() + key.getX() + key.getWidth(), getY() + key.getY() + key.getHeight(), key.isDown() ? getPressedBG() : new Color(0, 0, 0, 120).getRGB());
			
			if (this.getRainbow() && key.isDown()) {
				GuiUtils.drawChromaString(key.getName(), getX() + key.getX() + key.getWidth() / 2 - textWidth / 2, getY() + key.getY() + key.getHeight() / 2 - 4, true);
			} else {
				fr.drawStringWithShadow(key.getName(), getX() + key.getX() + key.getWidth() / 2 - textWidth / 2, getY() + key.getY() + key.getHeight() / 2 - 4, key.isDown() ? this.getColor() : -1);
			}
						
		}
		
		GL11.glPopMatrix();
		super.draw();
	}
	
	@Override
	public void renderDummy(int mouseX, int mouseY) {
		GL11.glPushMatrix();
		
		for(Key key : mode.getKeys()) {
			
			int textWidth = fr.getStringWidth(key.getName());
			
			Gui.drawRect(getX() + key.getX(), getY() + key.getY(), getX() + key.getX() + key.getWidth(), getY() + key.getY() + key.getHeight(), key.isDown() ? new Color(114, 223, 228, 102).getRGB() : new Color(0, 0, 0, 120).getRGB());
			
			fr.drawStringWithShadow(key.getName(), getX() + key.getX() + key.getWidth() / 2 - textWidth / 2, getY() + key.getY() + key.getHeight() / 2 - 4, key.isDown() ? this.getColor() : -1);
			
		}
		
		Gui.drawRect(getX() + 1, getY() + 52 + 6, getX() + 1 + 28, getY() + 52 + 18,  new Color(0, 0, 0, 120).getRGB());
		
		fr.drawStringWithShadow("10", getX() + 1 + 28 / 2 - fr.getStringWidth("10") / 2, getY() + 52 + 20 / 2 - 4, mc.gameSettings.keyBindAttack.isKeyDown() ? this.getColor() : -1);
		
		
		Gui.drawRect(getX() + 31, getY() + 52 + 6, getX() + 31 + 28, getY() + 52 + 18, new Color(0, 0, 0, 120).getRGB());
		
		fr.drawStringWithShadow("10", getX() + 31 + 28 / 2 - fr.getStringWidth("10") / 2, getY() + 52 + 20 / 2 - 4, mc.gameSettings.keyBindUseItem.isKeyDown() ? this.getColor() : -1);
		
		
		GL11.glPopMatrix();
		super.renderDummy(mouseX, mouseY);
	}
	
	private void cps() {
		
		final long timeLMB = System.currentTimeMillis();
		for (int i = 0, j = 0; i < clicksLMB.size(); i++) {
		    if ((timeLMB - clicksLMB.get(i)) > 1000) {
		    	this.clicksLMB.remove(i);
		    }
		}
		
		Gui.drawRect(getX() + 1, getY() + 52 + 6, getX() + 1 + 28, getY() + 52 + 18,  mc.gameSettings.keyBindAttack.isKeyDown() ? getPressedBG() : new Color(0, 0, 0, 120).getRGB());
		
		
		if (this.getRainbow() && mc.gameSettings.keyBindAttack.isKeyDown()) {
			GuiUtils.drawChromaString(String.valueOf(this.clicksLMB.size()), getX() + 1 + 28 / 2 - fr.getStringWidth(String.valueOf(this.clicksLMB.size())) / 2, getY() + 52 + 20 / 2 - 4, true);
		} else {
			fr.drawStringWithShadow(String.valueOf(this.clicksLMB.size()), getX() + 1 + 28 / 2 - fr.getStringWidth(String.valueOf(this.clicksLMB.size())) / 2, getY() + 52 + 20 / 2 - 4, mc.gameSettings.keyBindAttack.isKeyDown() ? this.getColor() : -1);
		}
		
		
		
		
		final long timeRMB = System.currentTimeMillis();
		for (int i = 0, j = 0; i < clicksRMB.size(); i++) {
		    if ((timeRMB - clicksRMB.get(i)) > 1000) {
		    	this.clicksRMB.remove(i);
		    }
		}
		
		Gui.drawRect(getX() + 31, getY() + 52 + 6, getX() + 31 + 28, getY() + 52 + 18,  mc.gameSettings.keyBindUseItem.isKeyDown() ? getPressedBG() : new Color(0, 0, 0, 120).getRGB());
		
		if (this.getRainbow() && mc.gameSettings.keyBindUseItem.isKeyDown()) {
			GuiUtils.drawChromaString(String.valueOf(this.clicksRMB.size()), getX() + 31 + 28 / 2 - fr.getStringWidth(String.valueOf(this.clicksRMB.size())) / 2, getY() + 52 + 20 / 2 - 4, true);
		} else {
			fr.drawStringWithShadow(String.valueOf(this.clicksRMB.size()), getX() + 31 + 28 / 2 - fr.getStringWidth(String.valueOf(this.clicksRMB.size())) / 2, getY() + 52 + 20 / 2 - 4, mc.gameSettings.keyBindUseItem.isKeyDown() ? this.getColor() : -1);
		}
		
	}
	
	private class BgPressedButton extends ToggleButton {

		public BgPressedButton(int x, int y, HudMod m) {
			super(x, y, m, "Backround Pressed");
		}
		
		@Override
		public int getColor() {
			return (int) this.m.getSettings().get("bgPressed");
		}

		@Override
		public void handleClick() {
			if(this.getColor() == new Color(255, 255, 255, 102).getRGB()) {
				this.m.changeSetting("bgPressed", new Color(0, 0, 0, 102).getRGB());
			} else if (this.getColor() == new Color(0, 0, 0, 102).getRGB()) {
				this.m.changeSetting("bgPressed", new Color(255, 0, 0, 102).getRGB());
			} else if (this.getColor() == new Color(255, 0, 0, 102).getRGB()) {
				this.m.changeSetting("bgPressed", new Color(0, 255, 0, 102).getRGB());
			} else if (this.getColor() == new Color(0, 255, 0, 102).getRGB()) {
				this.m.changeSetting("bgPressed", new Color(0, 0, 255, 102).getRGB());
			} else if (this.getColor() == new Color(0, 0, 255, 102).getRGB()) {
				this.m.changeSetting("bgPressed", new Color(255, 255, 255, 102).getRGB());
			} 
		}
		
	}
	
	@Override
	public void initGui(GuiScreen gui) {
		this.colorButton = new ColorButton(110, 90, Minecraft.getMinecraft().fontRendererObj.getStringWidth("Change Text Color") + 5, Minecraft.getMinecraft().fontRendererObj.FONT_HEIGHT + 2, this);
		
		this.bgPressedButton = new BgPressedButton(110, 110, this);
	}
	
	@Override
	public void drawScreen(GuiScreen gui, int mouseX, int mouseY, float partialTicks) {
		colorButton.draw();
		
		bgPressedButton.draw();
	}
	
	@Override
	public void mouseClicked(GuiScreen gui, int mouseX, int mouseY, int mouseButton) throws IOException {
		colorButton.onClick(mouseX, mouseY, mouseButton);
		
		bgPressedButton.onClick(mouseX, mouseY, mouseButton);
	}
	
}
