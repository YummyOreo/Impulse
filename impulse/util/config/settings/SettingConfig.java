package impulse.util.config.settings;

import java.io.File;
import java.io.IOException;

import impulse.Impulse;
import impulse.hud.mod.HudMod;
import impulse.settings.SettingsMod;
import impulse.util.config.Configuration;
import impulse.util.config.ConfigurationAPI;

public class SettingConfig {
	
	public File configFolder = new File("Impulse");
	public File modsFolder = new File("Impulse/Settings");
	
	public Configuration config, configToSave = ConfigurationAPI.newConfiguration(new File("Impulse/Settings/SettingsConfiguration.impulse"));
	
	public void saveModConfig() {
		
		if(!configFolder.exists()) {
			configFolder.mkdirs();
		}
		
		if(!modsFolder.exists()) {
			modsFolder.mkdirs();
		}
		
		System.out.println("[Mod Save/Mods] Saving Mod Config");
		
		for (SettingsMod s : Impulse.INSTANCE.settingsManager.settings) {
			configToSave.set(s.name + " settings", s.getSetting());
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
			config = ConfigurationAPI.loadExistingConfiguration(new File("Impulse/Settings/SettingsConfiguration.impulse"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
}
