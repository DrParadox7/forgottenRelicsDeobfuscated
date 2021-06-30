/*     */ package com.integral.forgottenrelics.entities;
/*     */ 
/*     */ import com.integral.forgottenrelics.handlers.DamageRegistryHandler;
/*     */ import com.integral.forgottenrelics.handlers.RelicsConfigHandler;
/*     */ import java.util.List;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.projectile.EntityThrowable;
/*     */ import net.minecraft.potion.Potion;
/*     */ import net.minecraft.potion.PotionEffect;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.MovingObjectPosition;
/*     */ import net.minecraft.world.World;
/*     */ import thaumcraft.common.Thaumcraft;
/*     */ import thaumcraft.common.config.Config;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class EntityDarkMatterOrb
/*     */   extends EntityThrowable
/*     */ {
/*     */   public EntityDarkMatterOrb(World par1World) {
/*  25 */     super(par1World);
/*     */   }
/*     */   
/*     */   public EntityDarkMatterOrb(World par1World, EntityLivingBase par2EntityLiving) {
/*  29 */     super(par1World, par2EntityLiving);
/*     */   }
/*     */   
/*     */   protected float getGravityVelocity() {
/*  33 */     return 0.0F;
/*     */   }
/*     */   
/*     */   public void onUpdate() {
/*  37 */     super.onUpdate();
/*     */     
/*  39 */     double absMotionX = this.motionX;
/*  40 */     double absMotionY = this.motionY;
/*  41 */     double absMotionZ = this.motionZ;
/*     */     
/*  43 */     Math.abs(absMotionX);
/*  44 */     Math.abs(absMotionY);
/*  45 */     Math.abs(absMotionZ);
/*     */     
/*  47 */     if (this.ticksExisted >= 200) {
/*  48 */       setDead();
/*  49 */     } else if ((((this.ticksExisted >= 100) ? 1 : 0) & ((absMotionX < 0.01D) ? 1 : 0) & ((absMotionY < 0.01D) ? 1 : 0) & ((absMotionZ < 0.01D) ? 1 : 0)) != 0) {
/*  50 */       MovingObjectPosition mop = new MovingObjectPosition((Entity)this);
/*  51 */       onImpact(mop);
/*     */     } 
/*  53 */     if (this.worldObj.isRemote) {
/*  54 */       Thaumcraft.proxy.wispFXEG(this.worldObj, this.posX, (float)(this.posY + 0.22D * this.height), this.posZ, (Entity)this);
/*     */     }
/*     */   }
/*     */   
/*     */   public void handleHealthUpdate(byte b) {
/*  59 */     if (b == 16) {
/*  60 */       if (this.worldObj.isRemote) {
/*  61 */         for (int a = 0; a < 30; a++) {
/*  62 */           float fx = (this.worldObj.rand.nextFloat() - this.worldObj.rand.nextFloat()) * 0.3F;
/*  63 */           float fy = (this.worldObj.rand.nextFloat() - this.worldObj.rand.nextFloat()) * 0.3F;
/*  64 */           float fz = (this.worldObj.rand.nextFloat() - this.worldObj.rand.nextFloat()) * 0.3F;
/*  65 */           Thaumcraft.proxy.wispFX3(this.worldObj, this.posX + fx, this.posY + fy, this.posZ + fz, this.posX + (fx * 8.0F), this.posY + (fy * 8.0F), this.posZ + (fz * 8.0F), 0.3F, 5, true, 0.02F);
/*     */         } 
/*     */       }
/*     */     } else {
/*     */       
/*  70 */       super.handleHealthUpdate(b);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void onImpact(MovingObjectPosition mop) {
/*  76 */     Block block = this.worldObj.getBlock(mop.blockX, mop.blockY, mop.blockZ);
/*     */     
/*  78 */     if (mop.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK) {
/*  79 */       if (block instanceof net.minecraft.block.BlockBush || block instanceof net.minecraft.block.BlockLeaves || block instanceof net.minecraft.block.BlockLiquid) {
/*     */         return;
/*     */       }
/*  82 */       this.worldObj.playSoundAtEntity((Entity)this, "random.fizz", 0.5F, 2.6F + (this.rand.nextFloat() - this.rand.nextFloat()) * 0.8F);
/*  83 */       this.ticksExisted = 200;
/*  84 */       this.worldObj.setEntityState((Entity)this, (byte)16);
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/*  89 */     if (!this.worldObj.isRemote && getThrower() != null) {
/*  90 */       List<Entity> list = this.worldObj.getEntitiesWithinAABBExcludingEntity((Entity)getThrower(), this.boundingBox.expand(1.0D, 1.0D, 1.0D));
/*  91 */       for (int i = 0; i < list.size(); i++) {
/*  92 */         Entity entity1 = list.get(i);
/*  93 */         if (entity1 instanceof EntityLivingBase)
/*     */         {
/*  95 */           if (this.worldObj.provider.dimensionId == Config.dimensionOuterId) {
/*     */             
/*  97 */             ((EntityLivingBase)entity1).attackEntityFrom((DamageSource)new DamageRegistryHandler.DamageSourceDarkMatter((Entity)getThrower()), RelicsConfigHandler.eldritchSpellDamageEx);
/*     */             try {
/*  99 */               ((EntityLivingBase)entity1).addPotionEffect(new PotionEffect(Potion.weakness.id, 320, 2));
/* 100 */               ((EntityLivingBase)entity1).addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 400, 2));
/* 101 */               ((EntityLivingBase)entity1).addPotionEffect(new PotionEffect(Potion.wither.id, 250, 3));
/*     */             }
/* 103 */             catch (Exception exception) {}
/*     */           }
/*     */           else {
/*     */             
/* 107 */             ((EntityLivingBase)entity1).attackEntityFrom((DamageSource)new DamageRegistryHandler.DamageSourceDarkMatter((Entity)getThrower()), RelicsConfigHandler.eldritchSpellDamage);
/*     */             try {
/* 109 */               ((EntityLivingBase)entity1).addPotionEffect(new PotionEffect(Potion.weakness.id, 160, 1));
/* 110 */               ((EntityLivingBase)entity1).addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 100, 1));
/* 111 */               ((EntityLivingBase)entity1).addPotionEffect(new PotionEffect(Potion.wither.id, 200, 0));
/*     */             }
/* 113 */             catch (Exception exception) {}
/*     */           } 
/*     */         }
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 120 */       this.worldObj.playSoundAtEntity((Entity)this, "random.fizz", 0.5F, 2.6F + (this.rand.nextFloat() - this.rand.nextFloat()) * 0.8F);
/* 121 */       this.ticksExisted = 199;
/* 122 */       this.worldObj.setEntityState((Entity)this, (byte)16);
/*     */     } 
/*     */   }
/*     */   
/*     */   public float getShadowSize() {
/* 127 */     return 0.1F;
/*     */   }
/*     */ }


/* Location:              C:\Users\antro\Documents\forgottenRelicsDeobfuscated.jar!\com\integral\forgottenrelics\entities\EntityDarkMatterOrb.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */