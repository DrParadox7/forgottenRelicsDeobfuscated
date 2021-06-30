/*     */ package com.integral.forgottenrelics.entities;
/*     */ 
/*     */ import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;
/*     */ import io.netty.buffer.ByteBuf;
/*     */ import java.util.List;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.projectile.EntityThrowable;
/*     */ import net.minecraft.util.MovingObjectPosition;
/*     */ import net.minecraft.world.World;
/*     */ import vazkii.botania.common.Botania;
/*     */ import vazkii.botania.common.core.helper.Vector3;
/*     */ 
/*     */ public class EntityShinyEnergy
/*     */   extends EntityThrowable
/*     */   implements IEntityAdditionalSpawnData {
/*     */   double lockX;
/*     */   double lockY;
/*     */   double lockZ;
/*     */   int targetID;
/*     */   EntityLivingBase thrower;
/*     */   EntityLivingBase target;
/*     */   
/*     */   public EntityShinyEnergy(World par1World) {
/*  25 */     super(par1World);
/*  26 */     this.targetID = 0;
/*  27 */     setSize(0.0F, 0.0F);
/*     */   }
/*     */   
/*     */   public EntityShinyEnergy(World par1World, EntityLivingBase par2EntityLiving, EntityLivingBase t, double x, double y, double z) {
/*  31 */     super(par1World, par2EntityLiving);
/*  32 */     this.thrower = par2EntityLiving;
/*  33 */     this.targetID = 0;
/*  34 */     this.target = t;
/*  35 */     this.lockX = x;
/*  36 */     this.lockY = y;
/*  37 */     this.lockZ = z;
/*  38 */     setSize(0.0F, 0.0F);
/*     */   }
/*     */   
/*     */   protected float getGravityVelocity() {
/*  42 */     return 0.0F;
/*     */   }
/*     */ 
/*     */   
/*     */   public void writeSpawnData(ByteBuf data) {
/*  47 */     int id = -1;
/*  48 */     if (this.target != null) {
/*  49 */       id = this.target.getEntityId();
/*     */     }
/*  51 */     data.writeInt(id);
/*     */     
/*  53 */     data.writeDouble(this.lockX);
/*  54 */     data.writeDouble(this.lockY);
/*  55 */     data.writeDouble(this.lockZ);
/*     */   }
/*     */ 
/*     */   
/*     */   public void readSpawnData(ByteBuf data) {
/*  60 */     int id = data.readInt();
/*     */     try {
/*  62 */       if (id >= 0) {
/*  63 */         this.target = (EntityLivingBase)this.worldObj.getEntityByID(id);
/*     */       }
/*     */     }
/*  66 */     catch (Exception exception) {}
/*     */     
/*  68 */     this.lockX = data.readDouble();
/*  69 */     this.lockY = data.readDouble();
/*  70 */     this.lockZ = data.readDouble();
/*     */   }
/*     */   
/*     */   public void particleExplosion() {
/*  74 */     for (int i = 0; i < 24; i++) {
/*  75 */       float r = 0.0F;
/*  76 */       float g = 0.8F + (float)Math.random() * 0.2F;
/*  77 */       float b = 0.4F + (float)Math.random() * 0.6F;
/*  78 */       float s = 0.3F + (float)Math.random() * 0.3F;
/*  79 */       float m = 0.4F;
/*  80 */       float xm = ((float)Math.random() - 0.5F) * m;
/*  81 */       float ym = ((float)Math.random() - 0.5F) * m;
/*  82 */       float zm = ((float)Math.random() - 0.5F) * m;
/*     */       
/*  84 */       Botania.proxy.setWispFXDistanceLimit(false);
/*  85 */       Botania.proxy.wispFX(this.worldObj, this.lockX + 0.5D, this.lockY + 1.25D, this.lockZ + 0.5D, r, g, b, s, xm, ym, zm, 1.0F);
/*  86 */       Botania.proxy.setWispFXDistanceLimit(true);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void onImpact(MovingObjectPosition mop) {}
/*     */ 
/*     */   
/*     */   public float getShadowSize() {
/*  95 */     return 0.1F;
/*     */   }
/*     */   
/*     */   public void onUpdate() {
/*  99 */     super.onUpdate();
/* 100 */     if (this.ticksExisted > 30) {
/* 101 */       setDead();
/*     */     }
/*     */     
/* 104 */     if (this.target == null) {
/* 105 */       setDead();
/*     */       
/*     */       return;
/*     */     } 
/* 109 */     float size = 1.0F / getDistanceToEntity((Entity)this.target) * 1.0F;
/*     */     
/* 111 */     if (size > 1.5F) {
/* 112 */       size = 1.5F;
/*     */     }
/* 114 */     for (int i = 0; i < 8; i++) {
/* 115 */       Botania.proxy.sparkleFX(this.worldObj, this.posX + (Math.random() - 0.5D) * 0.1D, this.posY + (Math.random() - 0.5D) * 0.1D, this.posZ + (Math.random() - 0.5D) * 0.1D, (float)(0.8999999761581421D + Math.random() * 0.10000000149011612D), (float)(0.20000000298023224D + Math.random() * 0.20000000298023224D), 0.0F, size, 2);
/*     */     }
/*     */     
/* 118 */     Vector3 thisVec = Vector3.fromEntityCenter((Entity)this);
/* 119 */     Vector3 targetVec = Vector3.fromEntityCenter((Entity)this.target);
/* 120 */     Vector3 diffVec = targetVec.copy().sub(thisVec);
/* 121 */     Vector3 motionVec = diffVec.copy().normalize().multiply(0.15D);
/* 122 */     this.motionX = motionVec.x;
/* 123 */     this.motionY = motionVec.y;
/* 124 */     this.motionZ = motionVec.z;
/*     */     
/* 126 */     if (!this.worldObj.isRemote) {
/*     */       
/* 128 */       if (this.target == null) {
/* 129 */         setDead();
/*     */         
/*     */         return;
/*     */       } 
/* 133 */       List<EntityLivingBase> targetList = this.worldObj.getEntitiesWithinAABB(EntityLivingBase.class, this.boundingBox.expand(0.1D, 0.1D, 0.1D));
/* 134 */       if (targetList.contains(this.target))
/* 135 */         setDead(); 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\antro\Documents\forgottenRelicsDeobfuscated.jar!\com\integral\forgottenrelics\entities\EntityShinyEnergy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */