package impulse;

import impulse.discord.Discordrpc;
import impulse.event.EventManager;
import impulse.event.EventTarget;
import impulse.event.impl.ChatReceivedEvent;
import impulse.event.impl.ClientTick;
import impulse.event.impl.EventUpdate;
import impulse.hud.HUDConfigScreen;
import impulse.hud.mod.Catagory;
import impulse.hud.mod.HudManager;
import impulse.hud.mod.HudMod;
import impulse.mod.ModManager;
import impulse.settings.SettingsManager;
import impulse.settings.SettingsMod;
import impulse.ui.SplashProgress;
import impulse.ui.whitlist.Checks;
import impulse.util.SessionChanger;
import impulse.util.cache.Cache;
import impulse.util.config.hud.Config;
import impulse.util.config.settings.SettingConfig;
import impulse.util.websockets.Connect;
import impulse.websockets.SocketClient;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

public class Impulse {

	// defs the info about the client
	public String NAME = "Impulse Client", VERSION = "1.18 beta", AURTHOR = "YummyOreo", NAMEVER = NAME + " " + VERSION;

	public static Impulse INSTANCE = new Impulse();

	public Minecraft mc = Minecraft.getMinecraft();

	// has sent to addUser request
	public boolean hasSentAddUser = false;

	// if they own the cape
	public boolean capeEnabled = false;

	// cache
	public int lastCacheUpdate = 0;

	// Whitelist
	public boolean isWhiteListed = false;

	// hue rainbow
	public float hue = 1;

	public boolean newWorld = false;

	public boolean newUpdate = false;
	
	public boolean hasReset = false;

	public EventManager eventManager;
	public Config config;
	public SettingConfig settingsConfig;
	public ModManager modManager;
	public HudManager hudManager;
	public SettingsManager settingsManager;
	public SocketClient socketClient;
	
	public Catagory catagory = null;
	
	public void startup() {
		// sets up all the modules
		SplashProgress.setProgress(8, "Client - Configuring Mods");
		eventManager = new EventManager();
		config = new Config();
		config.loadModConfig();
		
		settingsConfig = new SettingConfig();
		settingsConfig.loadModConfig();

		modManager = new ModManager();
		hudManager = new HudManager();
		settingsManager = new SettingsManager();
		socketClient = new SocketClient();
		if ((boolean) settingsManager.impulseServerSetting.getValue()) {
			SplashProgress.setProgress(9, "Client - Connecting to impulse servers");
			Connect.INSTANCE.connectToServer();
		} else {
			SplashProgress.setProgress(9, "Client - Failed to connect impulse servers");
		}

		if (Connect.INSTANCE.enabled) {
			newUpdate = socketClient.checkUpdate();
		}

		// Sets up the discord RPC
		try {
			if ((boolean) config.config.get("[Discord RPC] enabled")) {
				SplashProgress.setProgress(10, "Client - Starting Discord RPC");
				System.out.println("Starting Discord RPC");
				Discordrpc.startDiscordRPC();
			} else {
				SplashProgress.setProgress(10, "Client - Failed to start Discord RPC");
			}

		} catch (Exception e) {
			SplashProgress.setProgress(10, "Client - Starting discord RPC");
			Discordrpc.startDiscordRPC();
		}

		//SessionChanger.getInstance().setUserOffline("YummyOreo");

		isWhiteListed = Checks.checkWhitelist();

		System.out.println("Starting " + NAMEVER + " by: " + AURTHOR);

		eventManager.register(this);
		SplashProgress.setProgress(11, "Minecraft - Loading textures");
		
		// bug fix
		modManager.perspectiveMod.setEnabled(false);
		modManager.betterF5Mod.toggle();
	}

	public void shutdown() {
		System.out.println("Shutting down " + NAMEVER);

		// Stops the discord RPC
		Discordrpc.stopDiscordRPC();
		System.out.println("Removing from servers");
		if ((boolean) settingsManager.impulseServerSetting.getValue() && Connect.INSTANCE.enabled == true) {
			if (mc.thePlayer != null) {
				socketClient.removeUser(mc.thePlayer.getName());
			}
		}

		System.out.println("Saving mods");
		
		if (!hasReset) {
			// Saves the mods
			config.saveModConfig();
		}
		
		settingsConfig.saveModConfig();

		eventManager.unregister(this);
	}
	
	@EventTarget
    public void onChatReceivedEvent(ChatReceivedEvent event) {
		if (mc.getCurrentServerData() != null) {
			 if(hudManager.autoGG.isEnabled() && (mc.getCurrentServerData().serverIP == "hypixel" || mc.getCurrentServerData().serverIP == "mc.hypixel.net" || mc.getCurrentServerData().serverIP == "hypixel.net")) {
			    hudManager.autoGG.eventPog("chat", event);
			}
		}
    }

	@EventTarget
	public void onTick(ClientTick event) {

		hudManager.autoTipMod.eventPog("tick", event);
		
		if (mc.gameSettings.CLICK_GUI.isPressed()) {
			mc.displayGuiScreen(new HUDConfigScreen());

		} 
		if (mc.gameSettings.keyBindSprint.isPressed()) {
			modManager.toggleStrint.toggle();

		} 
		if (mc.gameSettings.PERSPECTIVE.isPressed()) {
			if (hudManager.perspectiveMod.isEnabled() && !hudManager.perspectiveMod.checkDisabled()) {
				modManager.perspectiveMod.toggle();
			}
		} 
		if (mc.gameSettings.F5_BWD.isPressed()) {
			modManager.perspectiveMod.setEnabled(false);
			modManager.betterF5Mod.setF5(1);
			modManager.betterF5Mod.toggle();
		}
		if (mc.gameSettings.F5_FWD.isPressed()) {
			modManager.perspectiveMod.setEnabled(false);
			modManager.betterF5Mod.setF5(2);
			modManager.betterF5Mod.toggle();
		}
		if (mc.gameSettings.FREEZE.isPressed()) {
			modManager.freezeMouseMod.toggle();
		}

		if (this.hue > 1) {
			this.hue = 0;
		}

		this.hue += 0.01F;

	}

	@EventTarget
	public void onUpdate(EventUpdate e) {
		// sends the addUser request
		if (mc.thePlayer != null && mc.theWorld != null) {
			if (!hasSentAddUser && Connect.INSTANCE.enabled) {
				socketClient.addUser(mc.thePlayer.getName());
				for (HudMod m : hudManager.hudMods) {
					if (m.name.equals("[Top Hat]")) {
						if (m.enabled) {
							Impulse.INSTANCE.socketClient.addCosmetic(mc.thePlayer.getName(),
									m.name.replace(" ", ""));
									m.name += "%3A" + m.getColor();
						}
					}
				}
				hasSentAddUser = true;
			}
		}

		if ((System.currentTimeMillis() - lastCacheUpdate) > 300000) {
			Cache.checkAll();
			lastCacheUpdate = (int) System.currentTimeMillis();

		}

		if (mc.thePlayer.worldObj != null && newWorld == false) {
			newWorld = true;
			if (newUpdate) {
				Minecraft.getMinecraft().thePlayer.addChatComponentMessage(
						new ChatComponentText("§kImpulse§r New §supdate§r, re-run the §einstaller§r to §einstall§r"));
			}
		}
	}
	
	public String highlightPlayerName(String s, String type) {
		if (type == "message") {
			
			if (!(boolean) settingsManager.highlightChatSetting.getValue()) return s;
			
			String parsedString = "";
			
			for (String word : s.split(" ")) {
				if (word.toLowerCase().contains(Minecraft.getMinecraft().thePlayer.getName().toLowerCase())) {
					parsedString += word.toLowerCase().replace(Minecraft.getMinecraft().thePlayer.getName().toLowerCase(), EnumChatFormatting.GOLD + Minecraft.getMinecraft().thePlayer.getName() + EnumChatFormatting.RESET);
				} else {
					parsedString += word;
				}
				parsedString += " ";
			}
			return parsedString;
		} else if (type == "tab") {
			
			if (!(boolean) settingsManager.highlightTabSetting.getValue()) return s;
			
			String parsedString = "";
			
			for (String word : s.split(" ")) {
				if (word.toLowerCase().contains(Minecraft.getMinecraft().thePlayer.getName().toLowerCase())) {
					parsedString += word.toLowerCase().replace(Minecraft.getMinecraft().thePlayer.getName().toLowerCase(), EnumChatFormatting.GOLD + Minecraft.getMinecraft().thePlayer.getName() + EnumChatFormatting.RESET);
				} else {
					parsedString += word;
				}
				parsedString += " ";
			}
			return parsedString;
			
		}
		return "";
		
	}

}
