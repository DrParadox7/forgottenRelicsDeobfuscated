/*     */ package com.integral.forgottenrelics.proxy;
/*     */ 
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.renderer.ActiveRenderInfo;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.Vec3;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.api.aspects.AspectList;
/*     */ import thaumcraft.client.lib.QuadHelper;
/*     */ import thaumcraft.client.lib.UtilsFX;
/*     */ import thaumcraft.common.tiles.TileEldritchObelisk;
/*     */ 
/*     */ @SideOnly(Side.CLIENT)
/*     */ public class VortexRender
/*     */   extends TileEntitySpecialRenderer {
/*  25 */   public static final ResourceLocation nodetex = new ResourceLocation("thaumcraft", "textures/misc/nodes.png");
/*  26 */   public static final ResourceLocation vortextex = new ResourceLocation("thaumcraft", "textures/misc/vortex.png");
/*     */   
/*     */   public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float partialTicks) {
/*  29 */     if (tile instanceof TileEldritchObelisk) {
/*  30 */       float size = 10.0F;
/*  31 */       TileEldritchObelisk node = (TileEldritchObelisk)tile;
/*  32 */       double viewDistance = 64.0D;
/*  33 */       EntityLivingBase viewer = (Minecraft.getMinecraft()).renderViewEntity;
/*  34 */       boolean condition = true;
/*  35 */       boolean bool = false;
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void renderNode(EntityLivingBase viewer, double viewDistance, boolean visible, boolean depthIgnore, float size, int x, int y, int z, float partialTicks, AspectList aspects, int timeOpen, boolean collapsing, int beams, boolean plane, boolean cheat) {
/*  40 */     long nt = System.nanoTime();
/*  41 */     int frames = 32;
/*  42 */     if (aspects.size() > 0 && visible) {
/*  43 */       double distance = viewer.getDistance(x + 0.5D, y + 0.5D, z + 0.5D);
/*  44 */       if (distance > viewDistance) {
/*     */         return;
/*     */       }
/*     */       
/*  48 */       float alpha = (float)((viewDistance - distance) / viewDistance);
/*  49 */       GL11.glPushMatrix();
/*  50 */       GL11.glAlphaFunc(516, 0.003921569F);
/*  51 */       GL11.glDepthMask(false);
/*  52 */       if (depthIgnore) {
/*  53 */         GL11.glDisable(2929);
/*     */       }
/*     */       
/*  56 */       GL11.glDisable(2884);
/*  57 */       long time = nt / 5000000L;
/*  58 */       float bscale = 0.25F;
/*  59 */       GL11.glPushMatrix();
/*  60 */       float rad = 6.283186F;
/*  61 */       GL11.glColor4f(1.0F, 1.0F, 1.0F, alpha);
/*  62 */       int i = (int)((nt / 40000000L + x) % frames);
/*  63 */       int count = 0;
/*  64 */       float scale = 0.0F;
/*  65 */       float angle = 0.0F;
/*  66 */       float average = 0.0F;
/*  67 */       UtilsFX.bindTexture(nodetex);
/*  68 */       Aspect[] var31 = aspects.getAspects();
/*  69 */       int var32 = var31.length;
/*     */       
/*     */       int var33;
/*  72 */       for (var33 = 0; var33 < var32; var33++) {
/*  73 */         Aspect aspect = var31[var33];
/*  74 */         if (aspect == null) {
/*  75 */           aspect = Aspect.WATER;
/*     */         }
/*     */         
/*  78 */         if (aspect.getBlend() == 771) {
/*  79 */           alpha = (float)(alpha * 1.5D);
/*     */         }
/*     */         
/*  82 */         average += aspects.getAmount(aspect);
/*  83 */         GL11.glPushMatrix();
/*  84 */         GL11.glEnable(3042);
/*  85 */         GL11.glBlendFunc(770, aspect.getBlend());
/*  86 */         scale = MathHelper.sin(viewer.ticksExisted / (14.0F - count)) * bscale + bscale * 2.0F;
/*  87 */         scale = 0.4F;
/*  88 */         scale *= size;
/*  89 */         angle = (float)(time % (5000 + 500 * count)) / (5000.0F + (500 * count)) * rad;
/*  90 */         if (beams >= 6 && timeOpen >= 50) {
/*  91 */           UtilsFX.renderFacingStrip(x + 0.5D, y + 0.5D, z + 0.5D, angle, scale / 3.0F, alpha / Math.max(1.0F, aspects.size() / 2.0F), frames, 0, i, partialTicks, aspect.getColor());
/*     */         } else {
/*  93 */           UtilsFX.renderFacingStrip(x + 0.5D, y + 0.5D, z + 0.5D, angle, scale, alpha / Math.max(1.0F, aspects.size() / 2.0F), frames, 0, i, partialTicks, aspect.getColor());
/*     */         } 
/*     */         
/*  96 */         GL11.glDisable(3042);
/*  97 */         GL11.glPopMatrix();
/*  98 */         count++;
/*  99 */         if (aspect.getBlend() == 771) {
/* 100 */           alpha = (float)(alpha / 1.5D);
/*     */         }
/*     */       } 
/*     */       
/* 104 */       average /= aspects.size();
/* 105 */       GL11.glPushMatrix();
/* 106 */       GL11.glEnable(3042);
/* 107 */       GL11.glBlendFunc(770, 771);
/* 108 */       GL11.glColor4f(1.0F, 0.0F, 1.0F, alpha);
/* 109 */       float corescale = 1.0F;
/* 110 */       if (timeOpen < 50 && !collapsing) {
/* 111 */         corescale = timeOpen / 50.0F;
/* 112 */       } else if (collapsing) {
/* 113 */         corescale = 1.0F - timeOpen / 25.0F;
/*     */       } 
/*     */       
/* 116 */       if (cheat || (beams >= 6 && timeOpen >= 50)) {
/* 117 */         UtilsFX.bindTexture(nodetex);
/* 118 */         UtilsFX.renderFacingStrip(x + 0.5D, y + 0.5D, z + 0.5D, angle, scale * 0.75F, alpha, frames, 2, i, partialTicks, 16777215);
/*     */       } else {
/* 120 */         UtilsFX.bindTexture(vortextex);
/* 121 */         renderVortex(x + 0.5D, y + 0.5D, z + 0.5D, angle * 20.0F * corescale / (1 + 2 * beams), scale / 5.0F * corescale, 0.8F, partialTicks, 16777215);
/*     */       } 
/*     */       
/* 124 */       GL11.glDisable(3042);
/* 125 */       GL11.glPopMatrix();
/* 126 */       if (plane) {
/* 127 */         Aspect[] var37 = aspects.getAspects();
/* 128 */         var33 = var37.length;
/*     */         
/* 130 */         for (int var38 = 0; var38 < var33; var38++) {
/* 131 */           Aspect aspect = var37[var38];
/* 132 */           if (aspect == null) {
/* 133 */             aspect = Aspect.WATER;
/*     */           }
/*     */           
/* 136 */           if (aspect.getBlend() == 771) {
/* 137 */             alpha = (float)(alpha * 1.5D);
/*     */           }
/*     */           
/* 140 */           average += aspects.getAmount(aspect);
/* 141 */           GL11.glPushMatrix();
/* 142 */           GL11.glEnable(3042);
/* 143 */           GL11.glBlendFunc(770, 771);
/* 144 */           scale = MathHelper.sin(viewer.ticksExisted / (14.0F - count)) * bscale + bscale * 2.0F;
/* 145 */           scale = 0.4F;
/* 146 */           float var10000 = scale * size;
/* 147 */           angle = (float)(time % (5000 + 500 * count)) / (5000.0F + (500 * count)) * rad;
/* 148 */           UtilsFX.renderFacingStrip(x + 0.5D, y + 0.5D, z + 0.5D, angle, 0.5F, alpha / Math.max(1.0F, aspects.size() / 2.0F), frames, 0, i, partialTicks, aspect.getColor());
/* 149 */           GL11.glDisable(3042);
/* 150 */           GL11.glPopMatrix();
/* 151 */           count++;
/* 152 */           if (aspect.getBlend() == 771) {
/* 153 */             alpha = (float)(alpha / 1.5D);
/*     */           }
/*     */         } 
/*     */       } 
/*     */       
/* 158 */       GL11.glPopMatrix();
/* 159 */       GL11.glEnable(2884);
/* 160 */       if (depthIgnore) {
/* 161 */         GL11.glEnable(2929);
/*     */       }
/*     */       
/* 164 */       GL11.glDepthMask(true);
/* 165 */       GL11.glAlphaFunc(516, 0.1F);
/* 166 */       GL11.glPopMatrix();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   static void renderVortex(double px, double py, double pz, float angle, float scale, float alpha, float partialTicks, int color) {
/* 172 */     Tessellator tessellator = Tessellator.instance;
/* 173 */     float arX = ActiveRenderInfo.rotationX;
/* 174 */     float arZ = ActiveRenderInfo.rotationZ;
/* 175 */     float arYZ = ActiveRenderInfo.rotationYZ;
/* 176 */     float arXY = ActiveRenderInfo.rotationXY;
/* 177 */     float arXZ = ActiveRenderInfo.rotationXZ;
/* 178 */     EntityPlayer player = (EntityPlayer)(Minecraft.getMinecraft()).renderViewEntity;
/* 179 */     double iPX = player.prevPosX + (player.posX - player.prevPosX) * partialTicks;
/* 180 */     double iPY = player.prevPosY + (player.posY - player.prevPosY) * partialTicks;
/* 181 */     double iPZ = player.prevPosZ + (player.posZ - player.prevPosZ) * partialTicks;
/* 182 */     GL11.glTranslated(-iPX, -iPY, -iPZ);
/* 183 */     tessellator.startDrawingQuads();
/* 184 */     tessellator.setBrightness(220);
/* 185 */     tessellator.setColorRGBA_I(color, (int)(alpha * 255.0F));
/* 186 */     Vec3 v1 = Vec3.createVectorHelper((-arX * scale - arYZ * scale), (-arXZ * scale), (-arZ * scale - arXY * scale));
/* 187 */     Vec3 v2 = Vec3.createVectorHelper((-arX * scale + arYZ * scale), (arXZ * scale), (-arZ * scale + arXY * scale));
/* 188 */     Vec3 v3 = Vec3.createVectorHelper((arX * scale + arYZ * scale), (arXZ * scale), (arZ * scale + arXY * scale));
/* 189 */     Vec3 v4 = Vec3.createVectorHelper((arX * scale - arYZ * scale), (-arXZ * scale), (arZ * scale - arXY * scale));
/* 190 */     if (angle != 0.0F) {
/* 191 */       Vec3 pvec = Vec3.createVectorHelper(iPX, iPY, iPZ);
/* 192 */       Vec3 tvec = Vec3.createVectorHelper(px, py, pz);
/* 193 */       Vec3 qvec = pvec.subtract(tvec).normalize();
/* 194 */       QuadHelper.setAxis(qvec, angle).rotate(v1);
/* 195 */       QuadHelper.setAxis(qvec, angle).rotate(v2);
/* 196 */       QuadHelper.setAxis(qvec, angle).rotate(v3);
/* 197 */       QuadHelper.setAxis(qvec, angle).rotate(v4);
/*     */     } 
/*     */     
/* 200 */     float f2 = 0.0F;
/* 201 */     float f3 = 1.0F;
/* 202 */     float f4 = 0.0F;
/* 203 */     float f5 = 1.0F;
/* 204 */     tessellator.setNormal(0.0F, 0.0F, -1.0F);
/* 205 */     tessellator.addVertexWithUV(px + v1.xCoord, py + v1.yCoord, pz + v1.zCoord, f2, f5);
/* 206 */     tessellator.addVertexWithUV(px + v2.xCoord, py + v2.yCoord, pz + v2.zCoord, f3, f5);
/* 207 */     tessellator.addVertexWithUV(px + v3.xCoord, py + v3.yCoord, pz + v3.zCoord, f3, f4);
/* 208 */     tessellator.addVertexWithUV(px + v4.xCoord, py + v4.yCoord, pz + v4.zCoord, f2, f4);
/* 209 */     tessellator.draw();
/*     */   }
/*     */ }


/* Location:              C:\Users\antro\Documents\forgottenRelicsDeobfuscated.jar!\com\integral\forgottenrelics\proxy\VortexRender.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */