/*     */ package com.integral.forgottenrelics.entities;
/*     */ 
/*     */ import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;
/*     */ import io.netty.buffer.ByteBuf;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.projectile.EntityThrowable;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.MovingObjectPosition;
/*     */ import net.minecraft.world.World;
/*     */ import vazkii.botania.common.Botania;
/*     */ import vazkii.botania.common.core.helper.Vector3;
/*     */ 
/*     */ public class EntityAIProjectileBase
/*     */   extends EntityThrowable implements IEntityAdditionalSpawnData {
/*     */   int targetID;
/*     */   EntityLivingBase target;
/*     */   
/*     */   public EntityAIProjectileBase(World par1World) {
/*  20 */     super(par1World);
/*  21 */     this.targetID = 0;
/*  22 */     setSize(0.0F, 0.0F);
/*     */   }
/*     */   
/*     */   public EntityAIProjectileBase(World par1World, EntityLivingBase par2EntityLiving, EntityLivingBase t, boolean r) {
/*  26 */     super(par1World, par2EntityLiving);
/*  27 */     this.targetID = 0;
/*  28 */     this.target = t;
/*  29 */     setSize(0.0F, 0.0F);
/*     */   }
/*     */   
/*     */   protected float getGravityVelocity() {
/*  33 */     return 0.0F;
/*     */   }
/*     */   
/*     */   public void writeSpawnData(ByteBuf data) {
/*  37 */     int id = -1;
/*  38 */     if (this.target != null) {
/*  39 */       id = this.target.getEntityId();
/*     */     }
/*  41 */     data.writeInt(id);
/*     */   }
/*     */   
/*     */   public void readSpawnData(ByteBuf data) {
/*  45 */     int id = data.readInt();
/*     */     try {
/*  47 */       if (id >= 0) {
/*  48 */         this.target = (EntityLivingBase)this.worldObj.getEntityByID(id);
/*     */       }
/*     */     }
/*  51 */     catch (Exception exception) {}
/*     */   }
/*     */ 
/*     */   
/*     */   protected void onImpact(MovingObjectPosition mop) {
/*  56 */     setDead();
/*     */   }
/*     */   
/*     */   public float getShadowSize() {
/*  60 */     return 0.1F;
/*     */   }
/*     */   
/*     */   public void onUpdate() {
/*  64 */     super.onUpdate();
/*  65 */     if (this.ticksExisted > 1000) {
/*  66 */       setDead();
/*     */     }
/*     */     
/*  69 */     double lastTickPosX = this.lastTickPosX;
/*  70 */     double lastTickPosY = this.lastTickPosY - this.yOffset + (this.height / 2.0F);
/*  71 */     double lastTickPosZ = this.lastTickPosZ;
/*     */     
/*  73 */     Vector3 thisVec = Vector3.fromEntityCenter((Entity)this);
/*  74 */     Vector3 oldPos = new Vector3(lastTickPosX, lastTickPosY, lastTickPosZ);
/*  75 */     Vector3 diff = thisVec.copy().sub(oldPos);
/*  76 */     Vector3 step = diff.copy().normalize().multiply(0.05D);
/*  77 */     int steps = (int)(diff.mag() / step.mag());
/*  78 */     Vector3 particlePos = oldPos.copy();
/*     */     
/*  80 */     float rc = 0.0F;
/*     */ 
/*     */ 
/*     */     
/*  84 */     for (int i = 0; i < steps; i++) {
/*  85 */       Botania.proxy.sparkleFX(this.worldObj, particlePos.x, particlePos.y, particlePos.z, rc, (float)(0.800000011920929D + Math.random() * 0.20000000298023224D), (float)(0.4000000059604645D + Math.random() * 0.6000000238418579D), 0.8F, 2);
/*  86 */       if (this.worldObj.rand.nextInt(steps) <= 1) {
/*  87 */         Botania.proxy.sparkleFX(this.worldObj, particlePos.x + (Math.random() - 0.5D) * 0.4D, particlePos.y + (Math.random() - 0.5D) * 0.4D, particlePos.z + (Math.random() - 0.5D) * 0.4D, rc, (float)(0.800000011920929D + Math.random() * 0.20000000298023224D), (float)(0.4000000059604645D + Math.random() * 0.6000000238418579D), 0.8F, 2);
/*     */       }
/*  89 */       particlePos.add(step);
/*     */     } 
/*     */     
/*  92 */     if (this.target != null) {
/*     */       
/*  94 */       double d = getDistanceSqToEntity((Entity)this.target);
/*  95 */       double dx = this.target.posX - this.posX;
/*  96 */       double dy = this.target.boundingBox.minY + this.target.height * 0.6D - this.posY;
/*  97 */       double dz = this.target.posZ - this.posZ;
/*  98 */       double d2 = 1.5D;
/*  99 */       dx /= d;
/* 100 */       dy /= d;
/* 101 */       dz /= d;
/* 102 */       this.motionX += dx * 1.5D;
/* 103 */       this.motionY += dy * 1.5D;
/* 104 */       this.motionZ += dz * 1.5D;
/* 105 */       this.motionX = MathHelper.clamp_float((float)this.motionX, -0.25F, 0.25F);
/* 106 */       this.motionY = MathHelper.clamp_float((float)this.motionY, -0.25F, 0.25F);
/* 107 */       this.motionZ = MathHelper.clamp_float((float)this.motionZ, -0.25F, 0.25F);
/*     */     } else {
/*     */       
/* 110 */       setDead();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\antro\Documents\forgottenRelicsDeobfuscated.jar!\com\integral\forgottenrelics\entities\EntityAIProjectileBase.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */