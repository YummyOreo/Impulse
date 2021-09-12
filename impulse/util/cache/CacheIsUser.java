package impulse.util.cache;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class CacheIsUser {
	public static final Map<String, Boolean> CONSTANT_MAP_IsUser = new HashMap<>();
	public static final Map<String, Integer> CONSTANT_MAP_TIME_IsUser = new HashMap<>();

	public static void check() {
		for (Entry<String, Boolean> i : CONSTANT_MAP_IsUser.entrySet()) {
			if ((System.currentTimeMillis() - CONSTANT_MAP_TIME_IsUser.get(i)) > 600000) {
				CONSTANT_MAP_IsUser.remove(i);
				CONSTANT_MAP_TIME_IsUser.remove(i);
			}
		}
	}
	
}
