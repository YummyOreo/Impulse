package impulse.hud.settings;

public class Setting {

	public String name;
	public boolean focused;
	
	public Object value;
	
	public Setting(String name, Object value) {
		this.name = name;
		this.value = value;
	}
	
}
