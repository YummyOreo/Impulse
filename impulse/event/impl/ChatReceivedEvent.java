package impulse.event.impl;

import java.util.List;

import impulse.event.Event;
import net.minecraft.client.gui.ChatLine;
import net.minecraft.util.IChatComponent;

public class ChatReceivedEvent extends Event {
	private IChatComponent component;
    private List<ChatLine> chatLines;

    public ChatReceivedEvent(IChatComponent component, List<ChatLine> chatLines)
    {
        this.component = component;
        this.chatLines = chatLines;
    }

    public IChatComponent getComponent()
    {
        return this.component;
    }

    public List<ChatLine> getChatLines()
    {
        return this.chatLines;
    }

}
