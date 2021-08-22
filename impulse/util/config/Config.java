package impulse.util.config;

import java.io.File;
import java.io.IOException;

import impulse.Impulse;
import impulse.hud.mod.HudMod;

public class Config {
	
	public File configFolder = new File("Impulse");
	public File modsFolder = new File("Impulse/Mods");
	
	public Configuration config, configToSave = ConfigurationAPI.newConfiguration(new File("Impulse/Mods/ModConfiguration.impulse"));
	
	public void saveModConfig() {
		
		if(!configFolder.exists()) {
			configFolder.mkdirs();
		}
		
		if(!modsFolder.exists()) {
			modsFolder.mkdirs();
		}
		
		System.out.println("[Mod Save/Mods] Saving Mod Config");
		
		for (HudMod m : Impulse.INSTANCE.hudManager.hudMods) {
			configToSave.set(m.name + " x", m.getX());
			configToSave.set(m.name + " y", m.getY());
			configToSave.set(m.name + " enabled", m.isEnabled());
			configToSave.set(m.name + " color", m.getColor());
			configToSave.set(m.name + " rainbow", m.getRainbow());
		}
		
		try {
			configToSave.save();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void loadModConfig() {

		try {
			System.out.println("Loaded");
			config = ConfigurationAPI.loadExistingConfiguration(new File("Impulse/Mods/ModConfiguration.impulse"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
}
