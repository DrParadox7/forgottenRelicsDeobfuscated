/*     */ package com.integral.forgottenrelics.entities;
/*     */ 
/*     */ import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;
/*     */ import io.netty.buffer.ByteBuf;
/*     */ import java.util.List;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.projectile.EntityThrowable;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.MovingObjectPosition;
/*     */ import net.minecraft.world.World;
/*     */ import vazkii.botania.common.Botania;
/*     */ import vazkii.botania.common.core.helper.Vector3;
/*     */ 
/*     */ public class EntitySoulEnergy
/*     */   extends EntityThrowable
/*     */   implements IEntityAdditionalSpawnData {
/*     */   int targetID;
/*     */   EntityLivingBase target;
/*     */   
/*     */   public EntitySoulEnergy(World par1World) {
/*  24 */     super(par1World);
/*  25 */     this.targetID = 0;
/*  26 */     setSize(0.0F, 0.0F);
/*     */   }
/*     */   
/*     */   public EntitySoulEnergy(World par1World, EntityLivingBase par2EntityLiving, EntityLivingBase t, boolean r) {
/*  30 */     super(par1World, par2EntityLiving);
/*  31 */     this.targetID = 0;
/*  32 */     this.target = t;
/*  33 */     setSize(0.0F, 0.0F);
/*     */   }
/*     */   
/*     */   protected float getGravityVelocity() {
/*  37 */     return 0.0F;
/*     */   }
/*     */   
/*     */   public void writeSpawnData(ByteBuf data) {
/*  41 */     int id = -1;
/*  42 */     if (this.target != null) {
/*  43 */       id = this.target.getEntityId();
/*     */     }
/*  45 */     data.writeInt(id);
/*     */   }
/*     */   
/*     */   public void readSpawnData(ByteBuf data) {
/*  49 */     int id = data.readInt();
/*     */     try {
/*  51 */       if (id >= 0) {
/*  52 */         this.target = (EntityLivingBase)this.worldObj.getEntityByID(id);
/*     */       }
/*     */     }
/*  55 */     catch (Exception exception) {}
/*     */   }
/*     */ 
/*     */   
/*     */   protected void onImpact(MovingObjectPosition mop) {
/*  60 */     if (mop.typeOfHit != MovingObjectPosition.MovingObjectType.ENTITY || 
/*  61 */       mop.entityHit == this.target);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getShadowSize() {
/*  68 */     return 0.1F;
/*     */   }
/*     */   
/*     */   public void onUpdate() {
/*  72 */     super.onUpdate();
/*  73 */     if (this.ticksExisted > 1000) {
/*  74 */       setDead();
/*     */     }
/*     */     
/*  77 */     double lastTickPosX = this.lastTickPosX;
/*  78 */     double lastTickPosY = this.lastTickPosY - this.yOffset + (this.height / 2.0F);
/*  79 */     double lastTickPosZ = this.lastTickPosZ;
/*     */     
/*  81 */     Vector3 thisVec = Vector3.fromEntityCenter((Entity)this);
/*  82 */     Vector3 oldPos = new Vector3(lastTickPosX, lastTickPosY, lastTickPosZ);
/*  83 */     Vector3 diff = thisVec.copy().sub(oldPos);
/*  84 */     Vector3 step = diff.copy().normalize().multiply(0.05D);
/*  85 */     int steps = (int)(diff.mag() / step.mag());
/*  86 */     Vector3 particlePos = oldPos.copy();
/*     */     
/*  88 */     float rc = 1.0F;
/*  89 */     float gc = 1.0F;
/*  90 */     float bc = 1.0F;
/*     */     
/*  92 */     for (int i = 0; i < steps; i++) {
/*  93 */       Botania.proxy.sparkleFX(this.worldObj, particlePos.x, particlePos.y, particlePos.z, rc, gc, bc, 0.8F, 2);
/*  94 */       if (this.worldObj.rand.nextInt(steps) <= 1) {
/*  95 */         Botania.proxy.sparkleFX(this.worldObj, particlePos.x + (Math.random() - 0.5D) * 0.4D, particlePos.y + (Math.random() - 0.5D) * 0.4D, particlePos.z + (Math.random() - 0.5D) * 0.4D, rc, gc, bc, 0.8F, 2);
/*     */       }
/*  97 */       particlePos.add(step);
/*     */     } 
/*     */ 
/*     */     
/* 101 */     List<EntityLivingBase> targetList = this.worldObj.getEntitiesWithinAABB(EntityLivingBase.class, AxisAlignedBB.getBoundingBox(this.posX - 0.5D, this.posY - 0.5D, this.posZ - 0.5D, this.posX + 0.5D, this.posY + 0.5D, this.posZ + 0.5D));
/* 102 */     if (targetList.contains(this.target)) {
/*     */       
/* 104 */       for (int j = 0; j <= 6; j++) {
/* 105 */         float r = 1.0F;
/* 106 */         float g = 1.0F;
/* 107 */         float b = 1.0F;
/* 108 */         float s = 0.1F + (float)Math.random() * 0.1F;
/* 109 */         float m = 0.15F;
/* 110 */         float xm = ((float)Math.random() - 0.5F) * m;
/* 111 */         float ym = ((float)Math.random() - 0.5F) * m;
/* 112 */         float zm = ((float)Math.random() - 0.5F) * m;
/*     */         
/* 114 */         Botania.proxy.wispFX(this.worldObj, this.posX + (this.width / 2.0F), this.posY + (this.height / 2.0F), this.posZ + (this.width / 2.0F), r, g, b, s, xm, ym, zm);
/*     */       } 
/*     */       
/* 117 */       this.worldObj.playSoundEffect(this.target.posX, this.target.posY, this.target.posZ, "random.fizz", 0.6F, 0.8F + (float)Math.random() * 0.2F);
/*     */       
/* 119 */       this.target.heal(1.0F);
/* 120 */       ((EntityPlayer)this.target).getFoodStats().addStats(1, 1.0F);
/*     */       
/* 122 */       setDead();
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 128 */     if (this.target != null) {
/*     */       
/* 130 */       double d = getDistanceSqToEntity((Entity)this.target);
/* 131 */       double dx = this.target.posX - this.posX;
/* 132 */       double dy = this.target.boundingBox.minY + this.target.height * 0.6D - this.posY;
/* 133 */       double dz = this.target.posZ - this.posZ;
/* 134 */       double d2 = 0.3D;
/* 135 */       dx /= d;
/* 136 */       dy /= d;
/* 137 */       dz /= d;
/*     */       
/* 139 */       this.motionX += dx * 0.3D;
/* 140 */       this.motionY += dy * 0.3D;
/* 141 */       this.motionZ += dz * 0.3D;
/*     */       
/* 143 */       this.motionX = MathHelper.clamp_float((float)this.motionX, -0.35F, 0.35F);
/* 144 */       this.motionY = MathHelper.clamp_float((float)this.motionY, -0.35F, 0.35F);
/* 145 */       this.motionZ = MathHelper.clamp_float((float)this.motionZ, -0.35F, 0.35F);
/*     */     } else {
/*     */       
/* 148 */       setDead();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\antro\Documents\forgottenRelicsDeobfuscated.jar!\com\integral\forgottenrelics\entities\EntitySoulEnergy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */