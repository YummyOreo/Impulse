package impulse.hud.mod.impl;

import impulse.event.Event;
import impulse.event.impl.ChatReceivedEvent;
import impulse.hud.mod.HudMod;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.EnumChatFormatting;

public class AutoGG extends HudMod {
	
	private double delayS = 0.0D;
    private int delay = 0;
    private transient double currentDelay = -1.0D;
    private String[] triggers = new String[] {"1st Killer - ", "1st Place - ", "Winner: ", " - Damage Dealt - ", "Winning Team -", "1st - ", "Winners: ", "Winner: ", "Winning Team: ", " won the game!", "Top Seeker: ", "1st Place: ", "Last team standing!", "Winner #1 (", "Top Survivors", "Winners - ", "- Shots Taken -"};

	public AutoGG() {
		super("[Auto GG]", 555, 5, "Sends GG After Each Game!");
	}
	
	public void eventPog(String event, Event e) {
		if(event.equals("tick")) {
			if (this.currentDelay != -1.0D)
	        {
	            this.currentDelay += 0.05D;
	
	            if (this.currentDelay >= (double)this.delay)
	            {
	                mc.thePlayer.sendChatMessage("/ac gg");
	                this.currentDelay = -1.0D;
	            }
	        }
		} else if(event.equals("chat")) {
			this.handleChat(((ChatReceivedEvent)e).getComponent());
		}
	}
	
	private void handleChat(IChatComponent chatComponent)
    {
    	if (mc.getCurrentServerData().serverIP.toLowerCase().contains("hypixel.net"))
        {
        	String s = EnumChatFormatting.getTextWithoutFormattingCodes(chatComponent.getUnformattedText());

            if (s != null && s.startsWith(" "))
            {
                for (String s1 : this.triggers)
                {
                    if (s.contains(s1))
                    {
                        this.currentDelay = 0.0D;
                        break;
                    }
                }
            }
        }
        
    }
	
}
