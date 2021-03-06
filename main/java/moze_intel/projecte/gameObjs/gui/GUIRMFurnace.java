package moze_intel.projecte.gameObjs.gui;

import moze_intel.projecte.MozeCore;
import moze_intel.projecte.gameObjs.container.RMFurnaceContainer;
import moze_intel.projecte.gameObjs.tiles.RMFurnaceTile;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

public class GUIRMFurnace extends GuiContainer 
{
	private static final ResourceLocation texture = new ResourceLocation(MozeCore.MODID.toLowerCase(), "textures/gui/rmfurnace.png");
	private RMFurnaceTile tile;
	
	public GUIRMFurnace(InventoryPlayer invPlayer, RMFurnaceTile tile)
	{
		super(new RMFurnaceContainer(invPlayer, tile));
		this.xSize = 209;
		this.ySize = 165;
		this.tile = tile;
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float var1, int var2, int var3) 
	{
		GL11.glColor4f(1F, 1F, 1F, 1F);
		Minecraft.getMinecraft().renderEngine.bindTexture(texture);
		
		int x = (width - xSize) / 2;
		int y = (height - ySize) / 2;
		
		this.drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
		
		
		int progress;
		
		if (tile.isBurning())
		{
			progress = tile.getBurnTimeRemainingScaled(12);
			this.drawTexturedModalRect(x + 57, y + 37 + 10 - progress, 210, 10 - progress, 21, progress + 2);
		}
		
		progress = tile.getCookProgressScaled(24);
		this.drawTexturedModalRect(x + 80, y + 39, 210, 20, progress + 5, 20);
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int var1, int var2) 
	{
		this.fontRendererObj.drawString("RM Furnace", 76, 5, 4210752);
		this.fontRendererObj.drawString("Inventory", 76, ySize - 96 + 2, 4210752);
	}
}
