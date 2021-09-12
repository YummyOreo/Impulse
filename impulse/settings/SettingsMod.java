package impulse.settings;

import java.util.HashMap;

import impulse.settings.load.Loader;

public class SettingsMod {
	
	private Object value;
	public String name;
	
	public SettingsMod(String name, Object value) {
		this.name = name;
		
		this.value = Loader.loadSetting(name, value);
	}
	
	public void setValue(Object value) {
		this.value = value;
	}
	
	public Object getValue() {
		return value;
	}
	
	public Object getSetting() {
		return this.value;
		
	}
}
