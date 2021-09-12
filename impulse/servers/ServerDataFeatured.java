package impulse.servers;

import impulse.cosmetics.textureManager;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.util.ResourceLocation;

public class ServerDataFeatured extends ServerData {
	
	public static final ResourceLocation STAR_ICON = new ResourceLocation(textureManager.INSTANCE.clientName + "/" + textureManager.INSTANCE.fearuredServerStar);

	public ServerDataFeatured(String serverName, String serverIp) {
		super(serverName, serverIp, false);
	}
	
	
	
}
