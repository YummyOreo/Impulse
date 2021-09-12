package impulse.settings;

import java.util.ArrayList;

import impulse.hud.mod.HudMod;
import impulse.settings.impl.CompactChatSetting;
import impulse.settings.impl.HighlightChatSetting;
import impulse.settings.impl.HighlightTabSetting;
import impulse.settings.impl.ImpulseServerSetting;

public class SettingsManager {
	
	public ArrayList<SettingsMod> settings = new ArrayList<>();
	
	public CompactChatSetting compactChatSetting;
	
	public HighlightChatSetting highlightChatSetting;
	public HighlightTabSetting highlightTabSetting;
	
	public ImpulseServerSetting impulseServerSetting;
	
	public SettingsManager() {
		this.settings.add(compactChatSetting = new CompactChatSetting());
		this.settings.add(highlightChatSetting = new HighlightChatSetting());
		this.settings.add(highlightTabSetting = new HighlightTabSetting());
		this.settings.add(impulseServerSetting = new ImpulseServerSetting());
	}
	
	
	
}
