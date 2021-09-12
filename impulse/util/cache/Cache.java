package impulse.util.cache;

import java.util.Map.Entry;

import impulse.util.cache.cosmetic.CacheTopHat;

public class Cache {
	public static void checkAll() {
		for (Entry<String, String> i : CacheTopHat.CONSTANT_MAP.entrySet()) {
			try {
				if ((System.currentTimeMillis() - CacheTopHat.CONSTANT_MAP_TIME.get(i)) > 300000) {
					CacheTopHat.CONSTANT_MAP.remove(i);
					CacheTopHat.CONSTANT_MAP_TIME.remove(i);
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		for (Entry<String, Boolean> i : CacheIsUser.CONSTANT_MAP_IsUser.entrySet()) {
			try {
				if ((System.currentTimeMillis() - CacheIsUser.CONSTANT_MAP_TIME_IsUser.get(i)) > 300000) {
					CacheIsUser.CONSTANT_MAP_IsUser.remove(i);
					CacheIsUser.CONSTANT_MAP_TIME_IsUser.remove(i);
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}
}
