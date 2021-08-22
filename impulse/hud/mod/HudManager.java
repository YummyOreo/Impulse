package impulse.hud.mod;

import java.util.ArrayList;

import impulse.cosmetics.toggle.CapeMod;
import impulse.cosmetics.toggle.TopHatMod;
import impulse.hud.mod.impl.ArmorStatusMod;
import impulse.hud.mod.impl.AutoTipMod;
import impulse.hud.mod.impl.CPSMod;
import impulse.hud.mod.impl.DiscordRPCToggle;
import impulse.hud.mod.impl.FPSMod;
import impulse.hud.mod.impl.ImpulseServerMod;
import impulse.hud.mod.impl.KeystrokesMod;
import impulse.hud.mod.impl.PerspectiveMod;
import impulse.hud.mod.impl.PingMod;
import impulse.hud.mod.impl.PositionMod;
import impulse.hud.mod.impl.PotionEffectsMod;
import impulse.hud.mod.impl.ServerMod;
import impulse.hud.mod.impl.TargetHUD;
import impulse.hud.mod.impl.ToggleStrintGui;
import net.minecraft.potion.PotionEffect;

public class HudManager {

	public ArrayList<HudMod> hudMods = new ArrayList<>();
	
	// Adds all the mods to a array for safe keeping
	
	//a
	public ArmorStatusMod armorStatus;
	public AutoTipMod autoTipMod;
	//b
	//c
	public CapeMod capeMod;
	public CPSMod cps;
	//d
	public DiscordRPCToggle discordRPCToggle;
	//e
	//f
	public FPSMod fpsMod;
	//g
	//h
	//i
	public ImpulseServerMod impulseServerMod;
	//j
	//k
	public KeystrokesMod keystrokes;
	//l
	//m
	//n
	//o
	//p
	public PerspectiveMod perspectiveMod;
	public PositionMod positionMod;
	public PotionEffectsMod potionEffect;
	public PingMod pingMod;
	//q
	//r
	//s
	public ServerMod serverMod;
	//t
	public TopHatMod topHatMod;
	public ToggleStrintGui toggleStrintGui;
	public TargetHUD targetHUD;
	//u
	//v
	//w
	//x
	//y
	//z
	
	public HudManager() {
		// a
		hudMods.add(armorStatus = new ArmorStatusMod());
		hudMods.add(autoTipMod = new AutoTipMod());
		//b
		//c
		hudMods.add(capeMod = new CapeMod());
		hudMods.add(cps = new CPSMod());
		//d
		hudMods.add(discordRPCToggle = new DiscordRPCToggle());
		//e
		//f
		hudMods.add(fpsMod = new FPSMod());
		//g
		//h
		//i
		hudMods.add(impulseServerMod = new ImpulseServerMod());
		//j
		//k
		hudMods.add(keystrokes = new KeystrokesMod());
		//l
		//m
		//n
		//o
		//p
		hudMods.add(perspectiveMod = new PerspectiveMod());
		hudMods.add(positionMod = new PositionMod());
		hudMods.add(potionEffect = new PotionEffectsMod());
		hudMods.add(pingMod = new PingMod());
		//q
		//r
		//s
		hudMods.add(serverMod = new ServerMod());
		//t
		hudMods.add(targetHUD = new TargetHUD());
		hudMods.add(toggleStrintGui = new ToggleStrintGui());
		hudMods.add(topHatMod = new TopHatMod());
		//u
		//v
		//w
		//x
		//y
		//z		
	}
	
	public void renderMods() {
		for (HudMod m : hudMods) {
			if(m.isEnabled()) {
				m.draw();
			}
		}
	}
	
}
