/*     */ package com.integral.forgottenrelics.entities;
/*     */ 
/*     */ import cpw.mods.fml.client.FMLClientHandler;
/*     */ import net.minecraft.client.particle.EntityFX;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.world.World;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ public class FXWisp
/*     */   extends EntityFX
/*     */ {
/*     */   Entity target;
/*     */   public boolean shrink;
/*     */   float moteParticleScale;
/*     */   int moteHalfLife;
/*     */   public boolean tinkle;
/*     */   public int blendmode;
/*     */   
/*     */   public FXWisp(World world, double d, double d1, double d2, float f, float f1, float f2) {
/*  23 */     this(world, d, d1, d2, 1.0F, f, f1, f2);
/*     */   }
/*     */   
/*     */   public FXWisp(World world, double d, double d1, double d2, float f, float red, float green, float blue) {
/*  27 */     super(world, d, d1, d2, 0.0D, 0.0D, 0.0D);
/*  28 */     this.target = null;
/*  29 */     this.shrink = false;
/*  30 */     this.tinkle = false;
/*  31 */     this.blendmode = 1;
/*  32 */     if (red == 0.0F) {
/*  33 */       red = 1.0F;
/*     */     }
/*  35 */     this.particleRed = red;
/*  36 */     this.particleGreen = green;
/*  37 */     this.particleBlue = blue;
/*  38 */     this.particleGravity = 0.0F;
/*  39 */     double motionX = 0.0D;
/*  40 */     this.motionZ = 0.0D;
/*  41 */     this.motionY = 0.0D;
/*  42 */     this.motionX = 0.0D;
/*  43 */     this.particleScale *= f;
/*  44 */     this.moteParticleScale = this.particleScale;
/*  45 */     this.particleMaxAge = (int)(36.0D / (Math.random() * 0.3D + 0.7D));
/*  46 */     this.moteHalfLife = this.particleMaxAge / 2;
/*  47 */     this.noClip = false;
/*  48 */     setSize(0.1F, 0.1F);
/*  49 */     EntityLivingBase renderentity = (FMLClientHandler.instance().getClient()).renderViewEntity;
/*  50 */     int visibleDistance = 50;
/*  51 */     if (!(FMLClientHandler.instance().getClient()).gameSettings.fancyGraphics) {
/*  52 */       visibleDistance = 25;
/*     */     }
/*  54 */     if (renderentity.getDistance(this.posX, this.posY, this.posZ) > visibleDistance) {
/*  55 */       this.particleMaxAge = 0;
/*     */     }
/*  57 */     this.prevPosX = this.posX;
/*  58 */     this.prevPosY = this.posY;
/*  59 */     this.prevPosZ = this.posZ;
/*     */   }
/*     */   
/*     */   public FXWisp(World world, double d, double d1, double d2, float f, int type) {
/*  63 */     this(world, d, d1, d2, f, 0.0F, 0.0F, 0.0F);
/*  64 */     switch (type) {
/*     */       case 0:
/*  66 */         this.particleRed = 0.75F + world.rand.nextFloat() * 0.25F;
/*  67 */         this.particleGreen = 0.25F + world.rand.nextFloat() * 0.25F;
/*  68 */         this.particleBlue = 0.75F + world.rand.nextFloat() * 0.25F;
/*     */         break;
/*     */       
/*     */       case 1:
/*  72 */         this.particleRed = 0.5F + world.rand.nextFloat() * 0.3F;
/*  73 */         this.particleGreen = 0.5F + world.rand.nextFloat() * 0.3F;
/*  74 */         this.particleBlue = 0.2F;
/*     */         break;
/*     */       
/*     */       case 2:
/*  78 */         this.particleRed = 0.2F;
/*  79 */         this.particleGreen = 0.2F;
/*  80 */         this.particleBlue = 0.7F + world.rand.nextFloat() * 0.3F;
/*     */         break;
/*     */       
/*     */       case 3:
/*  84 */         this.particleRed = 0.2F;
/*  85 */         this.particleGreen = 0.7F + world.rand.nextFloat() * 0.3F;
/*  86 */         this.particleBlue = 0.2F;
/*     */         break;
/*     */       
/*     */       case 4:
/*  90 */         this.particleRed = 0.7F + world.rand.nextFloat() * 0.3F;
/*  91 */         this.particleGreen = 0.2F;
/*  92 */         this.particleBlue = 0.2F;
/*     */         break;
/*     */       
/*     */       case 5:
/*  96 */         this.blendmode = 771;
/*  97 */         this.particleRed = world.rand.nextFloat() * 0.1F;
/*  98 */         this.particleGreen = world.rand.nextFloat() * 0.1F;
/*  99 */         this.particleBlue = world.rand.nextFloat() * 0.1F;
/*     */         break;
/*     */       
/*     */       case 6:
/* 103 */         this.particleRed = 0.8F + world.rand.nextFloat() * 0.2F;
/* 104 */         this.particleGreen = 0.8F + world.rand.nextFloat() * 0.2F;
/* 105 */         this.particleBlue = 0.8F + world.rand.nextFloat() * 0.2F;
/*     */         break;
/*     */       
/*     */       case 7:
/* 109 */         this.particleRed = 0.7F + world.rand.nextFloat() * 0.3F;
/* 110 */         this.particleGreen = 0.5F + world.rand.nextFloat() * 0.2F;
/* 111 */         this.particleBlue = 0.3F + world.rand.nextFloat() * 0.1F;
/*     */         break;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public FXWisp(World world, double d, double d1, double d2, double x, double y, double z, float f, int type) {
/* 118 */     this(world, d, d1, d2, f, type);
/* 119 */     if (this.particleMaxAge > 0) {
/* 120 */       double dx = x - this.posX;
/* 121 */       double dy = y - this.posY;
/* 122 */       double dz = z - this.posZ;
/* 123 */       this.motionX = dx / this.particleMaxAge;
/* 124 */       this.motionY = dy / this.particleMaxAge;
/* 125 */       this.motionZ = dz / this.particleMaxAge;
/*     */     } 
/*     */   }
/*     */   
/*     */   public FXWisp(World world, double d, double d1, double d2, Entity tar, int type) {
/* 130 */     this(world, d, d1, d2, 0.4F, type);
/* 131 */     this.target = tar;
/*     */   }
/*     */   
/*     */   public FXWisp(World world, double d, double d1, double d2, double x, double y, double z, float f, float red, float green, float blue) {
/* 135 */     this(world, d, d1, d2, f, red, green, blue);
/* 136 */     if (this.particleMaxAge > 0) {
/* 137 */       double dx = x - this.posX;
/* 138 */       double dy = y - this.posY;
/* 139 */       double dz = z - this.posZ;
/* 140 */       this.motionX = dx / this.particleMaxAge;
/* 141 */       this.motionY = dy / this.particleMaxAge;
/* 142 */       this.motionZ = dz / this.particleMaxAge;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void renderParticle(Tessellator tessellator, float f, float f1, float f2, float f3, float f4, float f5) {
/* 147 */     float agescale = 0.0F;
/* 148 */     if (this.shrink) {
/* 149 */       agescale = (this.particleMaxAge - this.particleAge) / this.particleMaxAge;
/*     */     } else {
/*     */       
/* 152 */       agescale = this.particleAge / this.moteHalfLife;
/* 153 */       if (agescale > 1.0F) {
/* 154 */         agescale = 2.0F - agescale;
/*     */       }
/*     */     } 
/* 157 */     this.particleScale = this.moteParticleScale * agescale;
/* 158 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.75F);
/* 159 */     float f6 = 0.5F * this.particleScale;
/* 160 */     float f7 = (float)(this.prevPosX + (this.posX - this.prevPosX) * f - interpPosX);
/* 161 */     float f8 = (float)(this.prevPosY + (this.posY - this.prevPosY) * f - interpPosY);
/* 162 */     float f9 = (float)(this.prevPosZ + (this.posZ - this.prevPosZ) * f - interpPosZ);
/* 163 */     float var8 = 0.0F;
/* 164 */     float var9 = 0.125F;
/* 165 */     float var10 = 0.875F;
/* 166 */     float var11 = 1.0F;
/* 167 */     tessellator.setBrightness(240);
/* 168 */     tessellator.setColorRGBA_F(this.particleRed, this.particleGreen, this.particleBlue, 0.5F);
/* 169 */     tessellator.addVertexWithUV((f7 - f1 * f6 - f4 * f6), (f8 - f2 * f6), (f9 - f3 * f6 - f5 * f6), 0.125D, 1.0D);
/* 170 */     tessellator.addVertexWithUV((f7 - f1 * f6 + f4 * f6), (f8 + f2 * f6), (f9 - f3 * f6 + f5 * f6), 0.125D, 0.875D);
/* 171 */     tessellator.addVertexWithUV((f7 + f1 * f6 + f4 * f6), (f8 + f2 * f6), (f9 + f3 * f6 + f5 * f6), 0.0D, 0.875D);
/* 172 */     tessellator.addVertexWithUV((f7 + f1 * f6 - f4 * f6), (f8 - f2 * f6), (f9 + f3 * f6 - f5 * f6), 0.0D, 1.0D);
/*     */   }
/*     */   
/*     */   public int getFXLayer() {
/* 176 */     return (this.blendmode != 1) ? 1 : 0;
/*     */   }
/*     */   
/*     */   public void onUpdate() {
/* 180 */     this.prevPosX = this.posX;
/* 181 */     this.prevPosY = this.posY;
/* 182 */     this.prevPosZ = this.posZ;
/* 183 */     if (this.particleAge == 0 && this.tinkle && this.worldObj.rand.nextInt(3) == 0) {
/* 184 */       this.worldObj.playSoundAtEntity((Entity)this, "random.orb", 0.02F, 0.5F * ((this.worldObj.rand.nextFloat() - this.worldObj.rand.nextFloat()) * 0.6F + 2.0F));
/*     */     }
/* 186 */     if (this.particleAge++ >= this.particleMaxAge) {
/* 187 */       setDead();
/*     */     }
/* 189 */     this.motionY -= 0.04D * this.particleGravity;
/* 190 */     if (!this.noClip) {
/* 191 */       pushOutOfBlocks(this.posX, this.posY, this.posZ);
/*     */     }
/* 193 */     moveEntity(this.motionX, this.motionY, this.motionZ);
/* 194 */     if (this.target != null) {
/* 195 */       this.motionX *= 0.985D;
/* 196 */       this.motionY *= 0.985D;
/* 197 */       this.motionZ *= 0.985D;
/* 198 */       double dx = this.target.posX - this.posX;
/* 199 */       double dy = this.target.posY - this.posY;
/* 200 */       double dz = this.target.posZ - this.posZ;
/* 201 */       double d13 = 0.2D;
/* 202 */       double d14 = MathHelper.sqrt_double(dx * dx + dy * dy + dz * dz);
/* 203 */       dx /= d14;
/* 204 */       dy /= d14;
/* 205 */       dz /= d14;
/* 206 */       this.motionX += dx * 0.2D;
/* 207 */       this.motionY += dy * 0.2D;
/* 208 */       this.motionZ += dz * 0.2D;
/* 209 */       this.motionX = MathHelper.clamp_float((float)this.motionX, -0.2F, 0.2F);
/* 210 */       this.motionY = MathHelper.clamp_float((float)this.motionY, -0.2F, 0.2F);
/* 211 */       this.motionZ = MathHelper.clamp_float((float)this.motionZ, -0.2F, 0.2F);
/*     */     } else {
/*     */       
/* 214 */       this.motionX *= 0.9800000190734863D;
/* 215 */       this.motionY *= 0.9800000190734863D;
/* 216 */       this.motionZ *= 0.9800000190734863D;
/* 217 */       if (this.onGround) {
/* 218 */         this.motionX *= 0.699999988079071D;
/* 219 */         this.motionZ *= 0.699999988079071D;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   protected boolean pushOutOfBlocks(double par1, double par3, double par5) {
/* 225 */     int var7 = MathHelper.floor_double(par1);
/* 226 */     int var8 = MathHelper.floor_double(par3);
/* 227 */     int var9 = MathHelper.floor_double(par5);
/* 228 */     double var10 = par1 - var7;
/* 229 */     double var11 = par3 - var8;
/* 230 */     double var12 = par5 - var9;
/* 231 */     if (!this.worldObj.isAirBlock(var7, var8, var9) && this.worldObj.isBlockNormalCubeDefault(var7, var8, var9, true) && !this.worldObj.isAnyLiquid(this.boundingBox)) {
/* 232 */       boolean var13 = !this.worldObj.isBlockNormalCubeDefault(var7 - 1, var8, var9, true);
/* 233 */       boolean var14 = !this.worldObj.isBlockNormalCubeDefault(var7 + 1, var8, var9, true);
/* 234 */       boolean var15 = !this.worldObj.isBlockNormalCubeDefault(var7, var8 - 1, var9, true);
/* 235 */       boolean var16 = !this.worldObj.isBlockNormalCubeDefault(var7, var8 + 1, var9, true);
/* 236 */       boolean var17 = !this.worldObj.isBlockNormalCubeDefault(var7, var8, var9 - 1, true);
/* 237 */       boolean var18 = !this.worldObj.isBlockNormalCubeDefault(var7, var8, var9 + 1, true);
/* 238 */       byte var19 = -1;
/* 239 */       double var20 = 9999.0D;
/* 240 */       if (var13 && var10 < var20) {
/* 241 */         var20 = var10;
/* 242 */         var19 = 0;
/*     */       } 
/* 244 */       if (var14 && 1.0D - var10 < var20) {
/* 245 */         var20 = 1.0D - var10;
/* 246 */         var19 = 1;
/*     */       } 
/* 248 */       if (var15 && var11 < var20) {
/* 249 */         var20 = var11;
/* 250 */         var19 = 2;
/*     */       } 
/* 252 */       if (var16 && 1.0D - var11 < var20) {
/* 253 */         var20 = 1.0D - var11;
/* 254 */         var19 = 3;
/*     */       } 
/* 256 */       if (var17 && var12 < var20) {
/* 257 */         var20 = var12;
/* 258 */         var19 = 4;
/*     */       } 
/* 260 */       if (var18 && 1.0D - var12 < var20) {
/* 261 */         var20 = 1.0D - var12;
/* 262 */         var19 = 5;
/*     */       } 
/* 264 */       float var21 = this.rand.nextFloat() * 0.05F + 0.025F;
/* 265 */       float var22 = (this.rand.nextFloat() - this.rand.nextFloat()) * 0.1F;
/* 266 */       if (var19 == 0) {
/* 267 */         this.motionX = -var21;
/* 268 */         double n = var22;
/* 269 */         this.motionZ = n;
/* 270 */         this.motionY = n;
/*     */       } 
/* 272 */       if (var19 == 1) {
/* 273 */         this.motionX = var21;
/* 274 */         double n2 = var22;
/* 275 */         this.motionZ = n2;
/* 276 */         this.motionY = n2;
/*     */       } 
/* 278 */       if (var19 == 2) {
/* 279 */         this.motionY = -var21;
/* 280 */         double n3 = var22;
/* 281 */         this.motionZ = n3;
/* 282 */         this.motionX = n3;
/*     */       } 
/* 284 */       if (var19 == 3) {
/* 285 */         this.motionY = var21;
/* 286 */         double n4 = var22;
/* 287 */         this.motionZ = n4;
/* 288 */         this.motionX = n4;
/*     */       } 
/* 290 */       if (var19 == 4) {
/* 291 */         this.motionZ = -var21;
/* 292 */         double n5 = var22;
/* 293 */         this.motionX = n5;
/* 294 */         this.motionY = n5;
/*     */       } 
/* 296 */       if (var19 == 5) {
/* 297 */         this.motionZ = var21;
/* 298 */         double n6 = var22;
/* 299 */         this.motionX = n6;
/* 300 */         this.motionY = n6;
/*     */       } 
/* 302 */       return true;
/*     */     } 
/* 304 */     return false;
/*     */   }
/*     */   
/*     */   public void setGravity(float value) {
/* 308 */     this.particleGravity = value;
/*     */   }
/*     */ }


/* Location:              C:\Users\antro\Documents\forgottenRelicsDeobfuscated.jar!\com\integral\forgottenrelics\entities\FXWisp.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */