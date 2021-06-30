/*     */ package com.integral.forgottenrelics.entities;
/*     */ 
/*     */ import com.integral.forgottenrelics.handlers.DamageRegistryHandler;
/*     */ import com.integral.forgottenrelics.handlers.RelicsConfigHandler;
/*     */ import com.integral.forgottenrelics.handlers.SuperpositionHandler;
/*     */ import java.util.ArrayList;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.projectile.EntityThrowable;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.MovingObjectPosition;
/*     */ import net.minecraft.util.Vec3;
/*     */ import net.minecraft.world.World;
/*     */ import thaumcraft.common.lib.utils.EntityUtils;
/*     */ import vazkii.botania.common.core.helper.Vector3;
/*     */ 
/*     */ 
/*     */ public class EntityThunderpealOrb
/*     */   extends EntityThrowable
/*     */ {
/*     */   public int area;
/*     */   public float damage;
/*     */   
/*     */   public EntityThunderpealOrb(World par1World) {
/*  25 */     super(par1World);
/*  26 */     this.area = 4;
/*  27 */     this.damage = RelicsConfigHandler.damageThunderpealBolt;
/*     */   }
/*     */   
/*     */   public EntityThunderpealOrb(World par1World, EntityLivingBase par2EntityLiving) {
/*  31 */     super(par1World, par2EntityLiving);
/*  32 */     this.area = 4;
/*  33 */     this.damage = RelicsConfigHandler.damageThunderpealBolt;
/*     */   }
/*     */   public void shootLightning(World world, Entity entity, double xx, double yy, double zz, boolean main) {
/*     */     float width;
/*  37 */     Vector3 initPos = Vector3.fromEntity(entity);
/*  38 */     Vector3 finalPos = new Vector3(xx, yy, zz);
/*     */     
/*  40 */     Vector3 diffVec = finalPos.copy().sub(initPos);
/*  41 */     Vector3 motionVec = initPos.add(diffVec.copy().multiply(1.0D / getDistance(finalPos.x, finalPos.y, finalPos.z) * 0.5D));
/*     */     
/*  43 */     float curve = 0.5F;
/*  44 */     curve = (float)(curve * 1.0D / entity.getDistance(finalPos.x, finalPos.y, finalPos.z) * 24.0D);
/*     */ 
/*     */     
/*  47 */     if (main) {
/*  48 */       width = 0.075F;
/*     */     } else {
/*  50 */       width = 0.04F;
/*     */     } 
/*  52 */     SuperpositionHandler.imposeLightning(entity.worldObj, entity.dimension, motionVec.x, motionVec.y, motionVec.z, finalPos.x, finalPos.y, finalPos.z, 20, curve, (int)(entity.getDistance(finalPos.x, finalPos.y, finalPos.z) * 1.600000023841858D), 0, width);
/*     */   }
/*     */   
/*     */   protected float getGravityVelocity() {
/*  56 */     return 0.05F;
/*     */   }
/*     */   
/*     */   protected void onImpact(MovingObjectPosition mop) {
/*  60 */     Entity hitEntity = null;
/*  61 */     if (mop.entityHit != null) {
/*  62 */       hitEntity = mop.entityHit;
/*  63 */       hitEntity.attackEntityFrom((DamageSource)new DamageRegistryHandler.DamageSourceTLightning((Entity)getThrower()), RelicsConfigHandler.damageThunderpealDirect);
/*  64 */       hitEntity.hurtResistantTime = 0;
/*     */     } 
/*     */     
/*  67 */     if (!this.worldObj.isRemote) {
/*  68 */       ArrayList<Entity> list = EntityUtils.getEntitiesInRange(this.worldObj, this.posX, this.posY, this.posZ, (Entity)this, EntityLivingBase.class, this.area);
/*     */       
/*  70 */       if (list.contains(getThrower())) {
/*  71 */         list.remove(getThrower());
/*     */       }
/*  73 */       if (list.contains(hitEntity)) {
/*  74 */         list.remove(hitEntity);
/*     */       }
/*  76 */       for (Entity e : list) {
/*     */         
/*  78 */         Vector3 targetVec = Vector3.fromEntityCenter(e);
/*  79 */         shootLightning(this.worldObj, (Entity)this, targetVec.x, targetVec.y, targetVec.z, true);
/*  80 */         e.hurtResistantTime = 0;
/*  81 */         e.attackEntityFrom((DamageSource)new DamageRegistryHandler.DamageSourceTLightning((Entity)getThrower()), this.damage);
/*     */         
/*  83 */         ArrayList<Entity> exlist = EntityUtils.getEntitiesInRange(e.worldObj, e.posX, e.posY, e.posZ, e, EntityLivingBase.class, 4.0D);
/*     */         
/*  85 */         if (exlist.contains(getThrower())) {
/*  86 */           exlist.remove(getThrower());
/*     */         }
/*  88 */         if (exlist.contains(e)) {
/*  89 */           exlist.remove(e);
/*     */         }
/*  91 */         while (exlist.size() > 3) {
/*  92 */           exlist.remove((int)(Math.random() * exlist.size()));
/*     */         }
/*  94 */         for (Entity ex : exlist) {
/*  95 */           Vector3 targetVecX = Vector3.fromEntityCenter(ex);
/*  96 */           shootLightning(this.worldObj, e, targetVecX.x, targetVecX.y, targetVecX.z, false);
/*  97 */           ex.hurtResistantTime = 0;
/*  98 */           ex.attackEntityFrom((DamageSource)new DamageRegistryHandler.DamageSourceTLightning((Entity)getThrower()), this.damage / 2.0F);
/*     */         } 
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 104 */       SuperpositionHandler.imposeBurst(this.worldObj, this.dimension, this.posX, this.posY, this.posZ, 2.0F);
/* 105 */       SuperpositionHandler.imposeBurst(this.worldObj, this.dimension, this.posX, this.posY, this.posZ, 2.0F);
/*     */       
/* 107 */       this.worldObj.playSoundEffect(this.posX, this.posY, this.posZ, "thaumcraft:shock", 2.0F, 1.0F + (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F);
/*     */     } 
/*     */     
/* 110 */     setDead();
/*     */   }
/*     */   
/*     */   public void onUpdate() {
/* 114 */     super.onUpdate();
/* 115 */     if (this.ticksExisted > 500) {
/* 116 */       setDead();
/*     */     }
/*     */   }
/*     */   
/*     */   public float getShadowSize() {
/* 121 */     return 0.1F;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean attackEntityFrom(DamageSource p_70097_1_, float p_70097_2_) {
/* 126 */     if (isEntityInvulnerable()) {
/* 127 */       return false;
/*     */     }
/* 129 */     setBeenAttacked();
/* 130 */     if (p_70097_1_.getEntity() != null) {
/* 131 */       Vec3 vec3 = p_70097_1_.getEntity().getLookVec();
/* 132 */       if (vec3 != null) {
/* 133 */         this.motionX = vec3.xCoord;
/* 134 */         this.motionY = vec3.yCoord;
/* 135 */         this.motionZ = vec3.zCoord;
/* 136 */         this.motionX *= 0.9D;
/* 137 */         this.motionY *= 0.9D;
/* 138 */         this.motionZ *= 0.9D;
/* 139 */         this.worldObj.playSoundAtEntity((Entity)this, "thaumcraft:zap", 1.0F, 1.0F + (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F);
/*     */       } 
/* 141 */       return true;
/*     */     } 
/* 143 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\antro\Documents\forgottenRelicsDeobfuscated.jar!\com\integral\forgottenrelics\entities\EntityThunderpealOrb.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */