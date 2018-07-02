package com.xray.client.gui;

import com.xray.client.xray.XrayController;
import com.xray.common.XRay;
import com.xray.common.reference.Reference;
import java.awt.Color;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

public class GuiOverlay extends Gui {

    private Minecraft mc = Minecraft.getMinecraft();

    @SideOnly(Side.CLIENT)
    @SubscribeEvent(priority = EventPriority.NORMAL)
    public void RenderGameOverlayEvent(RenderGameOverlayEvent.Post event) {

        // Draw Indicator
        if(!XrayController.drawOres() || !XRay.showOverlay)
            return;

        GlStateManager.enableAlpha();
        GlStateManager.enableBlend();
        GlStateManager.color(0, 255, 0, 30);
        mc.renderEngine.bindTexture(new ResourceLocation(Reference.PREFIX_GUI + "circle.png"));
        drawModalRectWithCustomSizedTexture(5, 5, 0f, 0f, 5, 5, 5, 5);
        GlStateManager.disableAlpha();
        GlStateManager.disableBlend();

        GlStateManager.pushMatrix();
        GlStateManager.enableBlend();
        GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        FontRenderer fr = this.mc.fontRenderer;
        int color = Color.getHSBColor(0f, 0f, 1f).getRGB();
        fr.drawStringWithShadow(I18n.format("xray.overlay"), 15, 4, color + (30 << 24));
        GlStateManager.disableBlend();
        GlStateManager.popMatrix();

    }

}
