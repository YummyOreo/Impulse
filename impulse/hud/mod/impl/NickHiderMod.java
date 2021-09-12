package impulse.hud.mod.impl;

import impulse.hud.mod.Catagory;
import impulse.hud.mod.HudMod;
import net.minecraft.client.Minecraft;
import net.minecraft.util.EnumChatFormatting;

public class NickHiderMod extends HudMod {

	public NickHiderMod() {
		super("[Nick Hider]", 0, 0, "Hids your nick", Catagory.CHAT);
	}
	
	private String replaceString(String name) {
		name = name.replace("§1", "");
		name = name.replace("§2", "");
		name = name.replace("§3", "");
		name = name.replace("§4", "");
		name = name.replace("§5", "");
		name = name.replace("§6", "");
		name = name.replace("§7", "");
		name = name.replace("§8", "");
		name = name.replace("§9", "");
		name = name.replace("§a", "");
		name = name.replace("§b", "");
		name = name.replace("§c", "");
		name = name.replace("§d", "");
		name = name.replace("§e", "");
		name = name.replace("§f", "");
		name = name.replace("§r", "");
		name = name.replace("§k", "");
		name = name.replace("§l", "");
		name = name.replace("§m", "");
		name = name.replace("§n", "");
		name = name.replace("§o", "");
		name = name.replace("§f", "");
		name = name.replace("§l", "");
		name = name.replace("§m", "");
		name = name.replace("§n", "");
		name = name.replace("§o", "");
		name = name.replace("§r", "");
		return name;
	}
	
	public String hideChat(String s) {
		String parsedString = "";
		
		boolean addPlayer = false;
		String savedWord = null;
		
		for (String word : s.split(" ")) {
			for (String word1 : Minecraft.getMinecraft().thePlayer.getDisplayName().getUnformattedText().toLowerCase().split(" ")) {
				if (!word1.contains("[")) {
					String word1For = replaceString(word1);
					String wordFor = replaceString(word);
					savedWord = word1For;
					addPlayer = wordFor.toLowerCase().contains(word1For);
				}
			}
			
			if (addPlayer) {
				parsedString += word.toLowerCase().replace(savedWord, "You" + EnumChatFormatting.RESET);
			} else {
				parsedString += word;
			}

			
			parsedString += " ";
		}
		return parsedString;
	}
	
	public String hideTab(String s) {
		String parsedString = "";
		
		boolean addPlayer = false;
		String savedWord = null;
		
		for (String word : s.split(" ")) {
			for (String word1 : Minecraft.getMinecraft().thePlayer.getDisplayName().getUnformattedText().toLowerCase().split(" ")) {
				if (!word1.contains("[")) {
					String word1For = replaceString(word1);
					String wordFor = replaceString(word);
					savedWord = word1For;
					addPlayer = wordFor.toLowerCase().contains(word1For);
				}
			}
			
			if (addPlayer) {
				parsedString += word.toLowerCase().replace(savedWord, "You" + EnumChatFormatting.RESET);
			} else {
				parsedString += word;
			}

			
			parsedString += " ";
		}
		return parsedString;
	}
}
