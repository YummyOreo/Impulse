package impulse.mod;

import impulse.Impulse;
import net.minecraft.client.Minecraft;

public class Mod {
	
	public Minecraft mc = Minecraft.getMinecraft();
	
	public String name, description;
	public boolean enabled;
	public Catagory catagory;
	
	public Mod(String name, String description, Catagory catagory) {
		this.name = name;
		this.description = description;
		this.catagory = catagory;
		
		if (name == "PerspectiveMod" || name == "Freeze Mod") {
			this.enabled = false;
		} else {
			this.enabled = true;
		}
	}
	
	public void onEnable() {
		Impulse.INSTANCE.eventManager.register(this);
	}
	
	public void onDisable() {
		Impulse.INSTANCE.eventManager.unregister(this);
	}
	
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
		if (enabled) {
			onEnable();
		} else {
			onDisable();
		}
	}
	
	public void toggle() {
		setEnabled(!this.enabled);
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public Catagory getCatagory() {
		return catagory;
	}
	
}
