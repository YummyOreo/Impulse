package impulse.hud.mod;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import impulse.Impulse;
import impulse.hud.DraggableComponent;
import impulse.settings.Setting;
import impulse.util.websockets.Connect;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;

public class HudMod {

	public Minecraft mc = Minecraft.getMinecraft();
	public FontRenderer fr = mc.fontRendererObj;
	
	public ArrayList<Setting> settings;
	
	public String name, description;
	public boolean enabled;
	public DraggableComponent drag;
	public int color;
	public HashMap<String, String> disabled = new HashMap<String, String>();
	
	//§
	
	public int x, y;
	private boolean rainbow;
	
	public HudMod(String name, int x, int y, String description) {
		
		this.name = name;
		this.description = description;
		
		try {
			
			this.x = (int)Impulse.INSTANCE.config.config.get(name + " x");
			this.y = (int)Impulse.INSTANCE.config.config.get(name + " y");
			this.enabled = (boolean)Impulse.INSTANCE.config.config.get(name + " enabled");
			
		} catch (NullPointerException e) {
			e.printStackTrace();
			this.x = x;
			this.y = y;
			if (this.name.equals("[Impulse Servers]") || this.name.equals("[Discord RPC]") || this.name.equals("[Cape]")) {
				this.enabled = true;
			} else {
				this.enabled = false;
			}
		}
		
		try {
			this.color = (int)Impulse.INSTANCE.config.config.get(name + " color");
		} catch (Exception e) {
			this.color = new Color(255, 255, 255).getRGB();
		}
		
		try {
			this.rainbow = (boolean)Impulse.INSTANCE.config.config.get(name + " rainbow");
		} catch (Exception e) {
			this.rainbow = false;
		}
		
		if (this.name.equals("[Top Hat]")) {
			if (this.enabled && Connect.INSTANCE.enabled) {
				Impulse.INSTANCE.socketClient.addCosmetic(mc.thePlayer.getName(), this.name.replace(" ", "" + "%3A" + this.getColor()));
			}
		}
		
		settings = new ArrayList<Setting>();
		drag = new DraggableComponent(this.x, this.y, getWidth(), getHeight(), new Color(0, 0, 0, 0).getRed());
		
	}
	
	public void addDisabled(String serverIp) {
		this.disabled.put(serverIp, serverIp);
	}
	
	public boolean checkDisabled() {
		if (Minecraft.getMinecraft().getCurrentServerData() != null) {
			if (this.disabled.get(Minecraft.getMinecraft().getCurrentServerData().serverIP) != null) return true;
		}
		return false;
	}
	
	public HashMap<String, String> getDisabled() {
		return disabled;
	}
	
	private void addSettings(Setting... sets) {
		this.settings.add((Setting)Arrays.asList(sets));
	}
	
	public int getColor() {
		if (this.rainbow) {
			return Color.HSBtoRGB(Impulse.INSTANCE.hue, 1, 1);
		} else {
			return color;
		}
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
		if (this.name.equals("[Top Hat]")) {
			if (this.enabled && Connect.INSTANCE.enabled) {
				Impulse.INSTANCE.socketClient.addCosmetic(mc.thePlayer.getName(), this.name.replace(" ", "" + "%3A" + this.getColor()));
			} else {
				Impulse.INSTANCE.socketClient.removeCosmetic(mc.thePlayer.getName(), this.name.replace(" ", "" + "%3A" + this.getColor()));
			}
		} else if (this.name.equals("[Impulse Servers]")) {
			if (this.enabled) {
				Connect.INSTANCE.connectToServer();
				if (mc.thePlayer != null) {
					if (Connect.INSTANCE.enabled) {
						System.out.println(Impulse.INSTANCE.socketClient.addUser(mc.thePlayer.getName()));
						for (HudMod m : Impulse.INSTANCE.hudManager.hudMods) {
							if (this.name.equals("[Top Hat]")) {
								if (this.enabled && Connect.INSTANCE.enabled) {
									Impulse.INSTANCE.socketClient.addCosmetic(mc.thePlayer.getName(), this.name.replace(" ", "" + "%3A" + this.getColor()));
								}
							}
						}
					}
				}
			} else {
				Connect.INSTANCE.enabled = false;
				if (mc.thePlayer != null) {
					Impulse.INSTANCE.socketClient.removeUser(mc.thePlayer.getName());
				}
			}
		}
	}
	
	public void toggle() {
		if (Minecraft.getMinecraft().getCurrentServerData() != null) {
			if (this.disabled.get(Minecraft.getMinecraft().getCurrentServerData().serverIP) != null) return;
		}
		
		this.setEnabled(!this.enabled);
		
	}
	
	public boolean isEnabled() {
		return this.enabled;
	}
	
}
