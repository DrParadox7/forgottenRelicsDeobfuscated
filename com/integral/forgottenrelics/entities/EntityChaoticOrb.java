/*     */ package com.integral.forgottenrelics.entities;
/*     */ 
/*     */ import com.integral.forgottenrelics.handlers.DamageRegistryHandler;
/*     */ import com.integral.forgottenrelics.handlers.RelicsConfigHandler;
/*     */ import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;
/*     */ import io.netty.buffer.ByteBuf;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.projectile.EntityThrowable;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.MovingObjectPosition;
/*     */ import net.minecraft.world.World;
/*     */ import thaumcraft.common.Thaumcraft;
/*     */ import thaumcraft.common.config.ConfigBlocks;
/*     */ import thaumcraft.common.lib.utils.EntityUtils;
/*     */ import thaumcraft.common.lib.utils.Utils;
/*     */ import thaumcraft.common.lib.world.ThaumcraftWorldGenerator;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class EntityChaoticOrb
/*     */   extends EntityThrowable
/*     */   implements IEntityAdditionalSpawnData
/*     */ {
/*     */   int count;
/*     */   boolean seeker;
/*     */   int oi;
/*     */   
/*     */   public EntityChaoticOrb(World par1World) {
/*  37 */     super(par1World);
/*  38 */     this.count = 0;
/*  39 */     this.seeker = false;
/*  40 */     this.oi = 0;
/*     */   }
/*     */   
/*     */   public EntityChaoticOrb(World par1World, EntityLivingBase par2EntityLiving, boolean seeker) {
/*  44 */     super(par1World, par2EntityLiving);
/*  45 */     this.count = 0;
/*  46 */     this.seeker = false;
/*  47 */     this.oi = 0;
/*  48 */     this.seeker = seeker;
/*  49 */     this.oi = par2EntityLiving.getEntityId();
/*     */   }
/*     */   
/*     */   public void writeSpawnData(ByteBuf data) {
/*  53 */     data.writeBoolean(this.seeker);
/*  54 */     data.writeInt(this.oi);
/*     */   }
/*     */   
/*     */   public void readSpawnData(ByteBuf data) {
/*  58 */     this.seeker = data.readBoolean();
/*  59 */     this.oi = data.readInt();
/*     */   }
/*     */   
/*     */   protected float getGravityVelocity() {
/*  63 */     return 0.001F;
/*     */   }
/*     */   
/*     */   protected float func_70182_d() {
/*  67 */     return 0.5F;
/*     */   }
/*     */   
/*     */   public void onUpdate() {
/*  71 */     this.count++;
/*  72 */     if (isInsideOfMaterial(Material.portal)) {
/*  73 */       onImpact(new MovingObjectPosition((Entity)this));
/*     */     }
/*  75 */     if (this.worldObj.isRemote) {
/*  76 */       for (int a = 0; a < 6; a++) {
/*  77 */         Thaumcraft.proxy.wispFX4(this.worldObj, ((this.worldObj.rand.nextFloat() - this.worldObj.rand.nextFloat()) * 0.2F), ((this.worldObj.rand.nextFloat() - this.worldObj.rand.nextFloat()) * 0.2F), ((this.worldObj.rand.nextFloat() - this.worldObj.rand.nextFloat()) * 0.2F), (Entity)this, a, true, 0.0F);
/*     */       }
/*  79 */       Thaumcraft.proxy.wispFX2(this.worldObj, this.posX + ((this.worldObj.rand.nextFloat() - this.worldObj.rand.nextFloat()) * 0.2F), this.posY + ((this.worldObj.rand.nextFloat() - this.worldObj.rand.nextFloat()) * 0.2F), this.posZ + ((this.worldObj.rand.nextFloat() - this.worldObj.rand.nextFloat()) * 0.2F), 0.1F, this.rand.nextInt(6), true, true, 0.0F);
/*     */     } 
/*  81 */     Random rr = new Random((getEntityId() + this.count));
/*  82 */     if (this.ticksExisted > 20) {
/*  83 */       if (!this.seeker) {
/*  84 */         this.motionX += ((rr.nextFloat() - rr.nextFloat()) * 0.01F);
/*  85 */         this.motionY += ((rr.nextFloat() - rr.nextFloat()) * 0.01F);
/*  86 */         this.motionZ += ((rr.nextFloat() - rr.nextFloat()) * 0.01F);
/*     */       } else {
/*     */         
/*  89 */         List<Entity> l = EntityUtils.getEntitiesInRange(this.worldObj, this.posX, this.posY, this.posZ, (Entity)this, EntityLivingBase.class, 16.0D);
/*  90 */         double d = Double.MAX_VALUE;
/*  91 */         Entity t = null;
/*  92 */         for (Entity e : l) {
/*     */           
/*  94 */           if (e.getEntityId() == this.oi && 
/*  95 */             Math.random() < 0.8D) {
/*     */             continue;
/*     */           }
/*  98 */           if (e.isDead) {
/*     */             continue;
/*     */           }
/* 101 */           double dd = getDistanceSqToEntity(e);
/* 102 */           if (dd >= d) {
/*     */             continue;
/*     */           }
/* 105 */           d = dd;
/* 106 */           t = e;
/*     */         } 
/*     */         
/* 109 */         if (t != null) {
/* 110 */           double dx = t.posX - this.posX;
/* 111 */           double dy = t.boundingBox.minY + t.height * 0.9D - this.posY;
/* 112 */           double dz = t.posZ - this.posZ;
/* 113 */           double d2 = 0.2D;
/* 114 */           dx /= d;
/* 115 */           dy /= d;
/* 116 */           dz /= d;
/* 117 */           this.motionX += dx * 0.2D;
/* 118 */           this.motionY += dy * 0.2D;
/* 119 */           this.motionZ += dz * 0.2D;
/* 120 */           this.motionX = MathHelper.clamp_float((float)this.motionX, -0.2F, 0.2F);
/* 121 */           this.motionY = MathHelper.clamp_float((float)this.motionY, -0.2F, 0.2F);
/* 122 */           this.motionZ = MathHelper.clamp_float((float)this.motionZ, -0.2F, 0.2F);
/*     */         } 
/*     */       } 
/*     */     }
/* 126 */     super.onUpdate();
/* 127 */     if (this.ticksExisted > 5000) {
/* 128 */       setDead();
/*     */     }
/*     */   }
/*     */   
/*     */   protected void onImpact(MovingObjectPosition mop) {
/* 133 */     if (this.worldObj.isRemote) {
/* 134 */       for (int a = 0; a < 6; a++) {
/* 135 */         for (int b = 0; b < 6; b++) {
/* 136 */           float fx = (this.worldObj.rand.nextFloat() - this.worldObj.rand.nextFloat()) * 0.5F;
/* 137 */           float fy = (this.worldObj.rand.nextFloat() - this.worldObj.rand.nextFloat()) * 0.5F;
/* 138 */           float fz = (this.worldObj.rand.nextFloat() - this.worldObj.rand.nextFloat()) * 0.5F;
/* 139 */           Thaumcraft.proxy.wispFX3(this.worldObj, this.posX + fx, this.posY + fy, this.posZ + fz, this.posX + (fx * 10.0F), this.posY + (fy * 10.0F), this.posZ + (fz * 10.0F), 0.4F, b, true, 0.05F);
/*     */         } 
/*     */       } 
/*     */     }
/* 143 */     if (!this.worldObj.isRemote) {
/* 144 */       float specialchance = 1.0F;
/* 145 */       float expl = 2.0F;
/* 146 */       if (mop.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK && isInsideOfMaterial(Material.portal)) {
/* 147 */         expl = 4.0F;
/* 148 */         specialchance = 10.0F;
/*     */       } 
/*     */       
/* 151 */       if ((((mop.entityHit != null) ? 1 : 0) & ((getThrower() != null) ? 1 : 0)) != 0) {
/* 152 */         mop.entityHit.attackEntityFrom((DamageSource)new DamageRegistryHandler.DamageSourceMagic((Entity)getThrower()), (float)(1.0D + Math.random() * RelicsConfigHandler.chaosTomeDamageCap));
/*     */       }
/* 154 */       this.worldObj.createExplosion((Entity)null, this.posX, this.posY, this.posZ, (float)(1.0D + Math.random() * 6.0D), true);
/* 155 */       if (!this.seeker && this.rand.nextInt(100) <= specialchance) {
/* 156 */         if (this.rand.nextBoolean()) {
/* 157 */           taintSplosion();
/*     */         } else {
/*     */           
/* 160 */           ThaumcraftWorldGenerator.createRandomNodeAt(this.worldObj, mop.blockX, mop.blockY, mop.blockZ, this.rand, false, false, true);
/*     */         } 
/*     */       }
/* 163 */       setDead();
/*     */     } 
/*     */   }
/*     */   
/*     */   public void taintSplosion() {
/* 168 */     int x = (int)this.posX;
/* 169 */     int y = (int)this.posY;
/* 170 */     int z = (int)this.posZ;
/* 171 */     for (int a = 0; a < 10; a++) {
/* 172 */       int xx = x + (int)(this.rand.nextFloat() - this.rand.nextFloat() * 6.0F);
/* 173 */       int zz = z + (int)(this.rand.nextFloat() - this.rand.nextFloat() * 6.0F);
/* 174 */       if (this.rand.nextBoolean() && this.worldObj.getBiomeGenForCoords(xx, zz) != ThaumcraftWorldGenerator.biomeTaint) {
/* 175 */         Utils.setBiomeAt(this.worldObj, xx, zz, ThaumcraftWorldGenerator.biomeTaint);
/* 176 */         int yy = this.worldObj.getHeightValue(xx, zz);
/* 177 */         if (!this.worldObj.isAirBlock(xx, yy - 1, zz)) {
/* 178 */           this.worldObj.setBlock(xx, yy, zz, ConfigBlocks.blockTaintFibres, 0, 3);
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public float getShadowSize() {
/* 185 */     return 0.1F;
/*     */   }
/*     */ }


/* Location:              C:\Users\antro\Documents\forgottenRelicsDeobfuscated.jar!\com\integral\forgottenrelics\entities\EntityChaoticOrb.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */