package impulse.util.cache.cosmetic;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class CacheTopHat {
	public static final Map<String, String> CONSTANT_MAP = new HashMap<>();
	public static final Map<String, Integer> CONSTANT_MAP_TIME= new HashMap<>();
	
	public static void check() {
		for (Entry<String, String> i : CONSTANT_MAP.entrySet()) {
			if ((System.currentTimeMillis() - CONSTANT_MAP_TIME.get(i)) > 600000) {
				CONSTANT_MAP.remove(i);
				CONSTANT_MAP_TIME.remove(i);
			}
		}
	}

}
