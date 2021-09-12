package impulse.settings.load;

import impulse.Impulse;

public class Loader {
	public static Object loadSetting(String settingName, Object reg) {
		try {
			if (Impulse.INSTANCE.settingsConfig.config.get(settingName + " settings") == null) {
				return reg;
			}
			return Impulse.INSTANCE.settingsConfig.config.get(settingName + " settings");
		}catch (Exception e) {
			e.printStackTrace();
			return reg;
		}
	}
}
