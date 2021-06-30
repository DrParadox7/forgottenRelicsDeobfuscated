/*     */ package com.integral.forgottenrelics.entities;
/*     */ 
/*     */ import com.integral.forgottenrelics.Main;
/*     */ import com.integral.forgottenrelics.handlers.DamageRegistryHandler;
/*     */ import com.integral.forgottenrelics.packets.LunarBurstMessage;
/*     */ import com.integral.forgottenrelics.packets.LunarFlaresParticleMessage;
/*     */ import cpw.mods.fml.common.network.NetworkRegistry;
/*     */ import cpw.mods.fml.common.network.simpleimpl.IMessage;
/*     */ import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;
/*     */ import io.netty.buffer.ByteBuf;
/*     */ import java.util.List;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.projectile.EntityThrowable;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.MovingObjectPosition;
/*     */ import net.minecraft.world.World;
/*     */ import vazkii.botania.common.Botania;
/*     */ import vazkii.botania.common.core.helper.Vector3;
/*     */ 
/*     */ public class EntityLunarFlare
/*     */   extends EntityThrowable implements IEntityAdditionalSpawnData {
/*     */   int lockX;
/*     */   int lockY;
/*     */   int lockZ;
/*     */   EntityLivingBase thrower;
/*     */   
/*     */   public EntityLunarFlare(World par1World) {
/*  30 */     super(par1World);
/*  31 */     setSize(0.0F, 0.0F);
/*     */   }
/*     */   
/*     */   public EntityLunarFlare(World par1World, EntityLivingBase par2EntityLiving, int x, int y, int z) {
/*  35 */     super(par1World, par2EntityLiving);
/*  36 */     this.thrower = par2EntityLiving;
/*  37 */     this.lockX = x;
/*  38 */     this.lockY = y;
/*  39 */     this.lockZ = z;
/*  40 */     setSize(0.0F, 0.0F);
/*     */   }
/*     */   
/*     */   protected float getGravityVelocity() {
/*  44 */     return 0.0F;
/*     */   }
/*     */   
/*     */   public void writeSpawnData(ByteBuf data) {
/*  48 */     data.writeInt(this.lockX);
/*  49 */     data.writeInt(this.lockY);
/*  50 */     data.writeInt(this.lockZ);
/*     */   }
/*     */   
/*     */   public void readSpawnData(ByteBuf data) {
/*  54 */     this.lockX = data.readInt();
/*  55 */     this.lockY = data.readInt();
/*  56 */     this.lockZ = data.readInt();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void onImpact(MovingObjectPosition mop) {
/*  62 */     if ((((getThrower() == null) ? 1 : 0) & (!this.worldObj.isRemote ? 1 : 0)) != 0) {
/*  63 */       setDead();
/*     */       
/*     */       return;
/*     */     } 
/*  67 */     if (mop.typeOfHit == MovingObjectPosition.MovingObjectType.ENTITY)
/*     */     {
/*  69 */       if (mop.entityHit != getThrower()) {
/*  70 */         mop.entityHit.attackEntityFrom((DamageSource)new DamageRegistryHandler.DamageSourceMagic((Entity)getThrower()), 100.0F);
/*     */       }
/*     */     }
/*  73 */     if (mop.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK) {
/*  74 */       if ((((mop.blockX == this.lockX) ? 1 : 0) & ((mop.blockY == this.lockY) ? 1 : 0) & ((mop.blockZ == this.lockZ) ? 1 : 0)) != 0) {
/*  75 */         List<EntityLivingBase> affectedEntities = this.worldObj.getEntitiesWithinAABB(EntityLivingBase.class, this.boundingBox.expand(2.5D, 2.5D, 2.5D));
/*  76 */         if (affectedEntities.contains(getThrower())) {
/*  77 */           affectedEntities.remove(getThrower());
/*     */         }
/*  79 */         if (affectedEntities.contains(mop.entityHit)) {
/*  80 */           affectedEntities.remove(mop.entityHit);
/*     */         }
/*  82 */         for (int counter = affectedEntities.size() - 1; counter >= 0; counter--) {
/*  83 */           if (((!((EntityLivingBase)affectedEntities.get(counter)).isDead ? 1 : 0) & (!this.worldObj.isRemote ? 1 : 0)) != 0) {
/*  84 */             ((EntityLivingBase)affectedEntities.get(counter)).attackEntityFrom((DamageSource)new DamageRegistryHandler.DamageSourceMagic((Entity)getThrower()), 75.0F);
/*     */             
/*  86 */             Vector3 targetPos = Vector3.fromEntityCenter((Entity)affectedEntities.get(counter));
/*  87 */             Vector3 thisPos = Vector3.fromEntityCenter((Entity)this);
/*  88 */             Vector3 diff = targetPos.copy().sub(thisPos);
/*     */             
/*  90 */             diff.normalize();
/*  91 */             diff.multiply(1.0D / ((EntityLivingBase)affectedEntities.get(counter)).getDistanceToEntity((Entity)this));
/*     */             
/*  93 */             if (diff.mag() > 1.0D) {
/*  94 */               diff.normalize();
/*     */             }
/*  96 */             if (affectedEntities.get(counter) instanceof net.minecraft.entity.boss.IBossDisplayData) {
/*  97 */               diff.multiply(0.5D);
/*     */             }
/*  99 */             ((EntityLivingBase)affectedEntities.get(counter)).motionX += diff.x;
/* 100 */             ((EntityLivingBase)affectedEntities.get(counter)).motionY += diff.y;
/* 101 */             ((EntityLivingBase)affectedEntities.get(counter)).motionZ += diff.z;
/*     */           } 
/*     */         } 
/*     */         
/* 105 */         if (!this.worldObj.isRemote) {
/* 106 */           Main.packetInstance.sendToAllAround((IMessage)new LunarFlaresParticleMessage(this.lockX + 0.5D, this.lockY + 1.25D, this.lockZ + 0.5D, 48), new NetworkRegistry.TargetPoint(this.dimension, this.posX, this.posY, this.posZ, 128.0D));
/*     */         }
/* 108 */         this.worldObj.playAuxSFX(2001, this.lockX, this.lockY, this.lockZ, Block.getIdFromBlock(this.worldObj.getBlock(this.lockX, this.lockY, this.lockZ)) + (this.worldObj.getBlockMetadata(this.lockX, this.lockY, this.lockZ) << 12));
/* 109 */         this.worldObj.playSoundEffect(this.lockX, this.lockY, this.lockZ, "ForgottenRelics:sound.lunarFlare", 16.0F, 0.8F + (float)Math.random() * 0.2F);
/*     */         
/* 111 */         if (!this.worldObj.isRemote) {
/* 112 */           Main.packetInstance.sendToAllAround((IMessage)new LunarBurstMessage(this.lockX + 0.5D, this.lockY + 1.5D, this.lockZ + 0.5D, 2.0F), new NetworkRegistry.TargetPoint(this.dimension, this.posX, this.posY, this.posZ, 128.0D));
/*     */         }
/*     */ 
/*     */ 
/*     */         
/* 117 */         setDead();
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public float getShadowSize() {
/* 125 */     return 0.1F;
/*     */   }
/*     */   
/*     */   public void onUpdate() {
/* 129 */     super.onUpdate();
/* 130 */     if (this.ticksExisted > 1000) {
/* 131 */       setDead();
/*     */     }
/*     */     
/* 134 */     double lastTickPosX = this.lastTickPosX;
/* 135 */     double lastTickPosY = this.lastTickPosY - this.yOffset + (this.height / 2.0F);
/* 136 */     double lastTickPosZ = this.lastTickPosZ;
/*     */     
/* 138 */     Vector3 thisVec = Vector3.fromEntityCenter((Entity)this);
/* 139 */     Vector3 oldPos = new Vector3(lastTickPosX, lastTickPosY, lastTickPosZ);
/* 140 */     Vector3 diff = thisVec.copy().sub(oldPos);
/* 141 */     Vector3 step = diff.copy().normalize().multiply(0.05D);
/* 142 */     int steps = (int)(diff.mag() / step.mag());
/* 143 */     Vector3 particlePos = oldPos.copy();
/*     */     
/* 145 */     float rc = 0.0F;
/*     */ 
/*     */ 
/*     */     
/* 149 */     for (int i = 0; i < steps; i++) {
/* 150 */       Botania.proxy.sparkleFX(this.worldObj, particlePos.x + (Math.random() - 0.5D) * 0.2D, particlePos.y + (Math.random() - 0.5D) * 0.2D, particlePos.z + (Math.random() - 0.5D) * 0.2D, rc, (float)(0.800000011920929D + Math.random() * 0.20000000298023224D), (float)(0.4000000059604645D + Math.random() * 0.6000000238418579D), 2.0F, 1);
/* 151 */       if (this.worldObj.rand.nextInt(steps) <= 1) {
/* 152 */         Botania.proxy.sparkleFX(this.worldObj, particlePos.x + (Math.random() - 0.5D) * 1.0D, particlePos.y + (Math.random() - 0.5D) * 1.0D, particlePos.z + (Math.random() - 0.5D) * 1.0D, rc, (float)(0.800000011920929D + Math.random() * 0.20000000298023224D), (float)(0.4000000059604645D + Math.random() * 0.6000000238418579D), 2.4F, 4);
/*     */       }
/* 154 */       particlePos.add(step);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\antro\Documents\forgottenRelicsDeobfuscated.jar!\com\integral\forgottenrelics\entities\EntityLunarFlare.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */