package impulse.hud.mod.tag;

import java.util.HashMap;

import impulse.hud.mod.HudMod;

public class ParseTags {

	public static HashMap<String, String> parse(HudMod m) {
		String tags = m.tags;
		String[] begining = tags.split(":");
		HashMap returnHash = new HashMap<>();
		
		for (String a : begining) {
			returnHash.put(a, "true");
	    }
		
		return returnHash;
	}
	
}
