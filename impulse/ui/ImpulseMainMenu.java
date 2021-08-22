package impulse.ui;

import java.awt.Color;
import java.io.IOException;
import java.util.Random;

import org.lwjgl.opengl.GL11;

import impulse.Impulse;
import impulse.cosmetics.textureManager;
import impulse.ui.clickgui.ClickGUI;
import impulse.ui.menu.MenuClickGui;
import impulse.ui.whitlist.Checks;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiMultiplayer;
import net.minecraft.client.gui.GuiOptions;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiSelectWorld;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;

public class ImpulseMainMenu extends GuiScreen {
	
	public static boolean rendered = false;
	private static int int_random;
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		GL11.glColor4f(1, 1, 1, 1);
		
		if (!rendered) {
			rendered = true;
		
			Random rand = new Random(); //instance of random class
			int upperbound = 24;
	        //generate random values from 0-24
			int_random = rand.nextInt(upperbound); 
			System.out.println("Main Menu - " + int_random) ;
			
			if (int_random > 12) {
				mc.getTextureManager().bindTexture(new ResourceLocation(textureManager.INSTANCE.clientName + "/" + textureManager.INSTANCE.wallpaper1));
			} else {
				mc.getTextureManager().bindTexture(new ResourceLocation(textureManager.INSTANCE.clientName + "/" + textureManager.INSTANCE.wallpaper2));
			}
		} else {
			if (int_random > 12) {
				mc.getTextureManager().bindTexture(new ResourceLocation(textureManager.INSTANCE.clientName + "/" + textureManager.INSTANCE.wallpaper1));
			} else {
				mc.getTextureManager().bindTexture(new ResourceLocation(textureManager.INSTANCE.clientName + "/" + textureManager.INSTANCE.wallpaper2));
			}
		}
		
		
		this.drawModalRectWithCustomSizedTexture(0, 0, 0, 0, this.width, this.height, this.width, this.height);
		
		GlStateManager.pushMatrix();
		GlStateManager.translate(width/2f , height/2f, 0);
		GlStateManager.scale(2.5, 2.5, 1);
		GlStateManager.translate(-(width/2f) , -(height/2f), 0);
		
		this.drawCenteredString(mc.fontRendererObj, Impulse.INSTANCE.NAME, width/2f, height/2.6f, -1);
		GlStateManager.popMatrix();
		if (!Impulse.INSTANCE.newUpdate) {
			this.drawCenteredString(mc.fontRendererObj, "By: " + Impulse.INSTANCE.AURTHOR, width/2f, height/3.5f, -1);
		} else {
			this.drawCenteredString(mc.fontRendererObj, "New update, re-run the installer to install", width/2f, height/3.5f, -1);
		}
		GlStateManager.pushMatrix();
		GlStateManager.translate(width, height, 0);
		GlStateManager.scale(0.5F, 0.5F, 1);
		GlStateManager.color(96, 96, 96);
		GlStateManager.translate(-(width) , -(height), 0);
		this.drawCenteredString(mc.fontRendererObj, "Copyright Mojang Studios", (width) / 1.15F, height/2f * 1.9F, -1);
		this.drawCenteredString(mc.fontRendererObj, "Impulse Client " + Impulse.INSTANCE.VERSION, (width - width * 2) / 1.1F, height/2f * 1.9F, -1);
		GlStateManager.popMatrix();
		
		super.drawScreen(mouseX, mouseY, partialTicks);
		
		resetTextureState();
	}
	
	@Override
	public void initGui() {
		this.buttonList.add(new GuiButton(1, this.width/2 - 97, height / 2 -  60, "Singleplayer"));
		this.buttonList.add(new GuiButton(2, this.width/2 - 97, height / 2 -  35, "Multiplayer"));
		this.buttonList.add(new GuiButton(3, this.width/2 - 97, height / 2 + -10, "Settings"));
		this.buttonList.add(new GuiButton(4, this.width/2 - 97, height / 2 + 15, "Mods"));
		this.buttonList.add(new GuiButton(5, this.width/2 - 97, height / 2 + 40, "Quit"));
		super.initGui();
	}
	
	@Override
	protected void actionPerformed(GuiButton button) throws IOException {
		
		if (button.id == 1) {
			mc.displayGuiScreen(new GuiSelectWorld(this));
		} else if (button.id == 2) {
			mc.displayGuiScreen(new GuiMultiplayer(this));
		} else if (button.id == 3) {
			mc.displayGuiScreen(new GuiOptions(this, mc.gameSettings));
		} else if (button.id == 4) {
			mc.displayGuiScreen(new MenuClickGui());
		} else if (button.id == 5) {
			mc.shutdown();
		} 
		
		super.actionPerformed(button);
	}
	
	private static void resetTextureState() {
		GlStateManager.textureState[GlStateManager.activeTextureUnit].textureName = -1;
	}
	
}
