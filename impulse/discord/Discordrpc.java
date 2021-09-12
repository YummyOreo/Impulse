package impulse.discord;

import impulse.Impulse;
import net.arikia.dev.drpc.*;
import net.minecraft.client.Minecraft;

public class Discordrpc {
	private static String discordID = "865670638675820564";
	private static DiscordRichPresence discordRichPresence = new DiscordRichPresence();
	private static DiscordEventHandlers discordEventHandlers = new DiscordEventHandlers();
	
	public static void startDiscordRPC() {
		System.out.println("Discord - Starting Discord RPC");
		DiscordRPC.discordInitialize(discordID, discordEventHandlers, true);
		
		new Thread("Discord RPC Callback") {
			
			public void run() {
				DiscordRPC.discordRunCallbacks();
			};
			
		}.start();
		
		DiscordRichPresence.Builder b = new DiscordRichPresence.Builder("A client by " + Impulse.INSTANCE.AURTHOR);
		
		b.setStartTimestamps(System.currentTimeMillis() / 1000L);
		b.setDetails("Playing on 1.8");
		b.setBigImage("logo", "Impulse Client " + Impulse.INSTANCE.VERSION);
		DiscordRPC.discordUpdatePresence(b.build());
		System.out.println("Discord - Discord RPC Started");
	
	}
	
	public static void stopDiscordRPC() {
		System.out.println("Discord - Stoping Discord RPC");
		DiscordRPC.discordShutdown();
		System.out.println("Discord - Discord RPC Stopped");
	}
}
