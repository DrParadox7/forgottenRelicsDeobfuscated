/*    */ package com.integral.forgottenrelics.entities;
/*    */ 
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.particle.EntityFX;
/*    */ import net.minecraft.client.renderer.Tessellator;
/*    */ import net.minecraft.world.World;
/*    */ import org.lwjgl.opengl.GL11;
/*    */ import thaumcraft.client.lib.UtilsFX;
/*    */ import thaumcraft.client.renderers.tile.TileNodeRenderer;
/*    */ 
/*    */ public class FXBurst
/*    */   extends EntityFX
/*    */ {
/*    */   public FXBurst(World world, double d, double d1, double d2, float f) {
/* 15 */     super(world, d, d1, d2, 0.0D, 0.0D, 0.0D);
/* 16 */     this.particleRed = 0.0F;
/* 17 */     this.particleGreen = 0.8F + (float)Math.random() * 0.2F;
/* 18 */     this.particleBlue = 0.4F + (float)Math.random() * 0.6F;
/* 19 */     this.particleGravity = 0.0F;
/* 20 */     double motionX = 0.0D;
/* 21 */     this.motionZ = 0.0D;
/* 22 */     this.motionY = 0.0D;
/* 23 */     this.motionX = 0.0D;
/* 24 */     this.particleScale *= f;
/* 25 */     this.particleMaxAge = 31;
/* 26 */     this.noClip = false;
/* 27 */     setSize(0.01F, 0.01F);
/*    */   }
/*    */   
/*    */   public void renderParticle(Tessellator tessellator, float f, float f1, float f2, float f3, float f4, float f5) {
/* 31 */     tessellator.draw();
/* 32 */     GL11.glPushMatrix();
/* 33 */     GL11.glDepthMask(false);
/* 34 */     GL11.glEnable(3042);
/* 35 */     GL11.glBlendFunc(770, 1);
/* 36 */     UtilsFX.bindTexture(TileNodeRenderer.nodetex);
/* 37 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.75F);
/* 38 */     float var8 = (this.particleAge % 32) / 32.0F;
/* 39 */     float var9 = var8 + 0.03125F;
/* 40 */     float var10 = 0.96875F;
/* 41 */     float var11 = 1.0F;
/* 42 */     float var12 = this.particleScale;
/* 43 */     float var13 = (float)(this.prevPosX + (this.posX - this.prevPosX) * f - interpPosX);
/* 44 */     float var14 = (float)(this.prevPosY + (this.posY - this.prevPosY) * f - interpPosY);
/* 45 */     float var15 = (float)(this.prevPosZ + (this.posZ - this.prevPosZ) * f - interpPosZ);
/* 46 */     float var16 = 1.0F;
/* 47 */     tessellator.startDrawingQuads();
/* 48 */     tessellator.setBrightness(240);
/* 49 */     tessellator.setColorRGBA_F(this.particleRed * 1.0F, this.particleGreen * 1.0F, this.particleBlue * 1.0F, 1.0F);
/* 50 */     tessellator.addVertexWithUV((var13 - f1 * var12 - f4 * var12), (var14 - f2 * var12), (var15 - f3 * var12 - f5 * var12), var9, 1.0D);
/* 51 */     tessellator.addVertexWithUV((var13 - f1 * var12 + f4 * var12), (var14 + f2 * var12), (var15 - f3 * var12 + f5 * var12), var9, 0.96875D);
/* 52 */     tessellator.addVertexWithUV((var13 + f1 * var12 + f4 * var12), (var14 + f2 * var12), (var15 + f3 * var12 + f5 * var12), var8, 0.96875D);
/* 53 */     tessellator.addVertexWithUV((var13 + f1 * var12 - f4 * var12), (var14 - f2 * var12), (var15 + f3 * var12 - f5 * var12), var8, 1.0D);
/* 54 */     tessellator.draw();
/* 55 */     GL11.glDisable(3042);
/* 56 */     GL11.glDepthMask(true);
/* 57 */     GL11.glPopMatrix();
/* 58 */     (Minecraft.getMinecraft()).renderEngine.bindTexture(UtilsFX.getParticleTexture());
/* 59 */     tessellator.startDrawingQuads();
/*    */   }
/*    */   
/*    */   public void onUpdate() {
/* 63 */     this.prevPosX = this.posX;
/* 64 */     this.prevPosY = this.posY;
/* 65 */     this.prevPosZ = this.posZ;
/* 66 */     if (this.particleAge++ >= this.particleMaxAge) {
/* 67 */       setDead();
/*    */     }
/*    */   }
/*    */   
/*    */   public void setGravity(float value) {
/* 72 */     this.particleGravity = value;
/*    */   }
/*    */ }


/* Location:              C:\Users\antro\Documents\forgottenRelicsDeobfuscated.jar!\com\integral\forgottenrelics\entities\FXBurst.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */