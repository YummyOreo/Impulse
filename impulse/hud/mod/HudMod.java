package impulse.hud.mod;

import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import impulse.Impulse;
import impulse.hud.DraggableComponent;
import impulse.hud.mod.utils.Loader;
import impulse.hud.settings.Setting;
import impulse.hud.mod.Catagory;
import impulse.util.websockets.Connect;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiScreen;

public class HudMod {

	public Minecraft mc = Minecraft.getMinecraft();
	public FontRenderer fr = mc.fontRendererObj;
	
	public String name, description;
	public boolean enabled;
	public DraggableComponent drag;
	public int color;
	public ArrayList<String> disabled = new ArrayList<>();
	public HashMap<String, Setting> settings = new HashMap<>();
	
	public String tags = "";
	
	public boolean background;
	
	//§
	
	public int x, y;
	private boolean rainbow;
	
	public Catagory catagory;
	
	public boolean noShow = false;
	
	public HudMod(String name, int x, int y, String description, Catagory catagory) {
		
		this.catagory = catagory;
		
		this.name = name;
		this.description = description;
		
		this.x = (int) Loader.loadVar(this.name, "x", x);
		this.y = (int) Loader.loadVar(this.name, "y", y);
		this.enabled = (boolean) Loader.loadVar(this.name, "enabled", false);
		
		this.color = (int) Loader.loadVar(this.name, "color", new Color(255, 255, 255).getRGB());
		this.rainbow = (boolean) Loader.loadVar(this.name, "rainbow", false);
		
		this.background = (boolean) Loader.loadVar(this.name, "background", false);
		
		drag = new DraggableComponent(this.x, this.y, getWidth(), getHeight(), new Color(0, 0, 0, 0).getRed());
		
		try {
			boolean temp = (boolean) Impulse.INSTANCE.config.config.get(name + " enabled");
		} catch (NullPointerException e) {
			e.printStackTrace();
			this.firstLoad();
		}
		
		this.load();
		
	}
	
	public int getCatagoryColor() {
		if (this.catagory == Catagory.COSMETIC) {
			return new Color(198, 252, 3).getRGB();
		} else if (this.catagory == Catagory.CHAT) {
			return new Color(97, 176, 255).getRGB();
		} else if (this.catagory == Catagory.HUD) {
			return new Color(102, 102, 102).getRGB();
		} else if (this.catagory == Catagory.MISC) {
			return new Color(111, 0, 255).getRGB();
		} else if (this.catagory == Catagory.SERVER) {
			return new Color(238, 0, 255).getRGB();
		} else if (this.catagory == Catagory.WORLD) {
			return new Color(179, 0, 255).getRGB();
		}
		return new Color(79, 79, 79).getRGB();
	}
	
	public void addTag(String tag) {
		this.tags += tag + ":";
	}
	
	public boolean getBackground() {
		return this.background;
	}
	
	public void setBackground(boolean background) {
		this.background = background;
	}
	
	public void addDisabled(String serverIp) {
		this.disabled.add(serverIp);
	}
	
	public boolean checkDisabled() {
		if (Minecraft.getMinecraft().getCurrentServerData() != null) {
			for (String ip : this.disabled) {
				if (Minecraft.getMinecraft().getCurrentServerData().serverIP.contains(ip)) return true;
			}
		}
		return false;
	}
	
	public ArrayList<String> getDisabled() {
		return disabled;
	}
	
	public void addSettings(Setting set) {
		this.settings.put(set.name, set);
	}

	public void changeSetting(String name, Object value) {
		Setting old = this.settings.get(name);
		this.settings.remove(name);
		old.value = value;
		this.settings.put(name, old);
	}
	
	public HashMap<String, Object> getSettings() {
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		for (String s : this.settings.keySet()) {
			map.put(this.settings.get(s).name, this.settings.get(s).value);
		}
		
		return map;
	}
	
	public void loadSetting() {
		
	}
	
	public Setting getSetting(String name) {
		return this.settings.get(name);
	}
	
	public int getColor() {
		return color;
	}
	
	public void setColor(int color) {
		this.color = color;
	}
	
	public void setRainbow(boolean rainbow) {
		this.rainbow = rainbow;
	}
	
	public boolean getRainbow() {
		return this.rainbow;
	}
	
	public int getWidth() {
		return 50;

	}
	
	public int getHeight() {
		return 50;

	}
	
	public void draw() {
		
	}
	
	public void renderDummy(int mouseX, int mouseY) {
		drag.draw(mouseX, mouseY);
	}
	
	public int getX() {
		return drag.getxPosition();
	}
	
	public int getY() {
		return drag.getyPosition();
	}
	
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
		
		if (this.enabled) {
			this.onEnable();
		} else {
			this.onDisable();
		}
	}
	
	public void toggle() {
		if (this.checkDisabled()) return;
		
		this.setEnabled(!this.enabled);
		
	}
	
	public boolean isEnabled() {
		return this.enabled;
	}
	
	public void onEnable() {
		
	}
	
	public void onDisable() {
		
	}
	
	public void firstLoad() {
		
	}
	
	public void load() {
		
	}
	
	public void initGui(GuiScreen gui) {
		//gui.initGui();
	}
	
	public void drawScreen(GuiScreen gui, int mouseX, int mouseY, float partialTicks) {
		//gui.drawScreen(mouseX, mouseY, partialTicks);
	}
	
	public void mouseClicked(GuiScreen gui, int mouseX, int mouseY, int mouseButton) throws IOException {
		
		//gui.mouseClicked(mouseX, mouseY, mouseButton);
		
	}
	
}
