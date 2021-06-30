/*    */ package com.integral.forgottenrelics.proxy;
/*    */ 
/*    */ import com.integral.forgottenrelics.entities.EntityCrimsonOrb;
/*    */ import java.util.Random;
/*    */ import net.minecraft.client.entity.AbstractClientPlayer;
/*    */ import net.minecraft.client.renderer.Tessellator;
/*    */ import net.minecraft.client.renderer.entity.Render;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.util.MathHelper;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import org.lwjgl.opengl.GL11;
/*    */ import thaumcraft.client.fx.ParticleEngine;
/*    */ import thaumcraft.client.lib.UtilsFX;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RenderCrimsonOrb
/*    */   extends Render
/*    */ {
/* 23 */   private Random random = new Random();
/*    */ 
/*    */ 
/*    */   
/*    */   public void renderEntityAt(Entity entity, double x, double y, double z, float fq, float pticks) {
/* 28 */     Tessellator tessellator = Tessellator.instance;
/* 29 */     GL11.glPushMatrix();
/* 30 */     GL11.glTranslated(x, y, z);
/* 31 */     GL11.glEnable(3042);
/* 32 */     GL11.glBlendFunc(770, 1);
/* 33 */     UtilsFX.bindTexture(ParticleEngine.particleTexture);
/* 34 */     float f2 = (1 + entity.ticksExisted % 6) / 8.0F;
/* 35 */     float f3 = f2 + 0.125F;
/* 36 */     float f4 = 0.875F;
/* 37 */     if (entity instanceof EntityCrimsonOrb && ((EntityCrimsonOrb)entity).red) {
/* 38 */       f4 = 0.75F;
/*    */     }
/* 40 */     float f5 = f4 + 0.125F;
/* 41 */     float f6 = 1.0F;
/* 42 */     float f7 = 0.5F;
/* 43 */     float f8 = 0.5F;
/* 44 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.8F);
/* 45 */     GL11.glRotatef(180.0F - this.renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
/* 46 */     GL11.glRotatef(-this.renderManager.playerViewX, 1.0F, 0.0F, 0.0F);
/* 47 */     float bob = MathHelper.sin(entity.ticksExisted / 5.0F) * 0.2F + 0.2F;
/* 48 */     GL11.glScalef(1.0F + bob, 1.0F + bob, 1.0F + bob);
/* 49 */     tessellator.startDrawingQuads();
/* 50 */     tessellator.setBrightness(220);
/* 51 */     tessellator.setNormal(0.0F, 1.0F, 0.0F);
/* 52 */     tessellator.addVertexWithUV(-0.5D, -0.5D, 0.0D, f2, f5);
/* 53 */     tessellator.addVertexWithUV(0.5D, -0.5D, 0.0D, f3, f5);
/* 54 */     tessellator.addVertexWithUV(0.5D, 0.5D, 0.0D, f3, f4);
/* 55 */     tessellator.addVertexWithUV(-0.5D, 0.5D, 0.0D, f2, f4);
/* 56 */     tessellator.draw();
/* 57 */     GL11.glDisable(3042);
/* 58 */     GL11.glDisable(32826);
/* 59 */     GL11.glPopMatrix();
/*    */   }
/*    */   
/*    */   public void doRender(Entity entity, double d, double d1, double d2, float f, float f1) {
/* 63 */     renderEntityAt(entity, d, d1, d2, f, f1);
/*    */   }
/*    */   
/*    */   protected ResourceLocation getEntityTexture(Entity entity) {
/* 67 */     return AbstractClientPlayer.locationStevePng;
/*    */   }
/*    */ }


/* Location:              C:\Users\antro\Documents\forgottenRelicsDeobfuscated.jar!\com\integral\forgottenrelics\proxy\RenderCrimsonOrb.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */