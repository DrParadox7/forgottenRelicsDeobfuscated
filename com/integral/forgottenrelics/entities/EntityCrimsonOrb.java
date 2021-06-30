/*     */ package com.integral.forgottenrelics.entities;
/*     */ 
/*     */ import com.integral.forgottenrelics.handlers.DamageRegistryHandler;
/*     */ import com.integral.forgottenrelics.handlers.RelicsConfigHandler;
/*     */ import com.integral.forgottenrelics.handlers.SuperpositionHandler;
/*     */ import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;
/*     */ import io.netty.buffer.ByteBuf;
/*     */ import java.util.List;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.projectile.EntityThrowable;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.MovingObjectPosition;
/*     */ import net.minecraft.util.Vec3;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class EntityCrimsonOrb
/*     */   extends EntityThrowable
/*     */   implements IEntityAdditionalSpawnData
/*     */ {
/*     */   int targetID;
/*     */   EntityLivingBase target;
/*     */   public boolean red;
/*     */   EntityLivingBase caster;
/*     */   int casterID;
/*     */   
/*     */   public EntityCrimsonOrb(World par1World) {
/*  35 */     super(par1World);
/*  36 */     this.targetID = 0;
/*  37 */     this.red = false;
/*  38 */     this.casterID = 0;
/*     */   }
/*     */   
/*     */   public EntityCrimsonOrb(World par1World, EntityLivingBase c, EntityLivingBase t, boolean r) {
/*  42 */     super(par1World, c);
/*  43 */     this.targetID = 0;
/*  44 */     this.casterID = 0;
/*  45 */     this.red = false;
/*  46 */     this.target = t;
/*  47 */     this.caster = c;
/*  48 */     this.red = r;
/*     */   }
/*     */   
/*     */   protected float getGravityVelocity() {
/*  52 */     return 0.0F;
/*     */   }
/*     */   
/*     */   public void writeSpawnData(ByteBuf data) {
/*  56 */     int idTarget = -1;
/*  57 */     int idCaster = -1;
/*  58 */     if (this.target != null) {
/*  59 */       idTarget = this.target.getEntityId();
/*     */     }
/*  61 */     if (this.caster != null) {
/*  62 */       idCaster = this.caster.getEntityId();
/*     */     }
/*     */     
/*  65 */     data.writeInt(idTarget);
/*  66 */     data.writeInt(idCaster);
/*  67 */     data.writeBoolean(this.red);
/*     */   }
/*     */   
/*     */   public void readSpawnData(ByteBuf data) {
/*  71 */     int targetID = data.readInt();
/*  72 */     int casterID = data.readInt();
/*     */     try {
/*  74 */       if (targetID >= 0) {
/*  75 */         this.target = (EntityLivingBase)this.worldObj.getEntityByID(targetID);
/*     */       }
/*     */     }
/*  78 */     catch (Exception exception) {}
/*     */     
/*     */     try {
/*  81 */       if (casterID >= 0) {
/*  82 */         this.caster = (EntityLivingBase)this.worldObj.getEntityByID(casterID);
/*     */       }
/*     */     }
/*  85 */     catch (Exception exception) {}
/*     */     
/*  87 */     this.red = data.readBoolean();
/*     */   }
/*     */   
/*     */   protected void onImpact(MovingObjectPosition mop) {
/*  91 */     if (!this.worldObj.isRemote && getThrower() != null && mop.typeOfHit == MovingObjectPosition.MovingObjectType.ENTITY) {
/*  92 */       mop.entityHit.attackEntityFrom((DamageSource)new DamageRegistryHandler.DamageSourceMagic((Entity)getThrower()), (float)(RelicsConfigHandler.crimsonSpellDamageMIN + Math.random() * (RelicsConfigHandler.crimsonSpellDamageMAX - RelicsConfigHandler.crimsonSpellDamageMIN)));
/*     */       
/*  94 */       this.worldObj.playSoundEffect(this.posX, this.posY, this.posZ, "thaumcraft:shock", 1.0F, 1.0F + (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F);
/*  95 */       SuperpositionHandler.imposeBurst(this.worldObj, this.dimension, this.posX, this.posY, this.posZ, 1.0F);
/*  96 */       setDead();
/*     */     }
/*  98 */     else if (((!this.worldObj.isRemote ? 1 : 0) & ((mop.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK) ? 1 : 0)) != 0) {
/*  99 */       Block block = this.worldObj.getBlock(mop.blockX, mop.blockY, mop.blockZ);
/* 100 */       if (((!(block instanceof net.minecraft.block.BlockBush) ? 1 : 0) & (!(block instanceof net.minecraft.block.BlockLeaves) ? 1 : 0) & (!(block instanceof net.minecraft.block.BlockLiquid) ? 1 : 0)) != 0) {
/* 101 */         this.worldObj.playSoundEffect(this.posX, this.posY, this.posZ, "thaumcraft:shock", 1.0F, 1.0F + (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F);
/* 102 */         SuperpositionHandler.imposeBurst(this.worldObj, this.dimension, this.posX, this.posY, this.posZ, 1.0F);
/* 103 */         setDead();
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void getNewTarget() {
/* 111 */     int searchRange = 32;
/* 112 */     List<EntityLivingBase> entities = this.worldObj.getEntitiesWithinAABB(EntityLivingBase.class, AxisAlignedBB.getBoundingBox(this.posX - searchRange, this.posY - searchRange, this.posZ - searchRange, this.posX + searchRange, this.posY + searchRange, this.posZ + searchRange));
/*     */     
/* 114 */     if (entities.size() > 0)
/*     */     {
/* 116 */       for (int counter = entities.size() - 1; counter >= 0; counter--) {
/* 117 */         if (!((EntityLivingBase)entities.get(counter)).canEntityBeSeen((Entity)this)) {
/* 118 */           entities.remove(counter);
/* 119 */           counter = entities.size();
/*     */         } 
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/* 125 */     if (entities.contains(this)) {
/* 126 */       entities.remove(this);
/*     */     }
/* 128 */     if (entities.contains(this.caster)) {
/* 129 */       entities.remove(this.caster);
/*     */     }
/* 131 */     if (entities.size() > 0)
/* 132 */       this.target = entities.get((int)((entities.size() - 1) * Math.random())); 
/*     */   }
/*     */   
/*     */   public float getShadowSize() {
/* 136 */     return 0.1F;
/*     */   }
/*     */   
/*     */   public void onUpdate() {
/* 140 */     super.onUpdate();
/* 141 */     if (this.ticksExisted > 1000) {
/* 142 */       setDead();
/*     */     }
/*     */     
/* 145 */     if (!this.red) {
/* 146 */       setDead();
/*     */     }
/* 148 */     if (this.target != null) {
/*     */       
/* 150 */       if (this.target.isDead) {
/* 151 */         getNewTarget();
/*     */       }
/*     */       
/* 154 */       double d = getDistanceSqToEntity((Entity)this.target);
/* 155 */       double dx = this.target.posX - this.posX;
/* 156 */       double dy = this.target.boundingBox.minY + this.target.height * 0.6D - this.posY;
/* 157 */       double dz = this.target.posZ - this.posZ;
/* 158 */       double d2 = 0.3D;
/* 159 */       dx /= d;
/* 160 */       dy /= d;
/* 161 */       dz /= d;
/* 162 */       this.motionX += dx * 0.3D;
/* 163 */       this.motionY += dy * 0.3D;
/* 164 */       this.motionZ += dz * 0.3D;
/* 165 */       this.motionX = MathHelper.clamp_float((float)this.motionX, -0.25F, 0.25F);
/* 166 */       this.motionY = MathHelper.clamp_float((float)this.motionY, -0.25F, 0.25F);
/* 167 */       this.motionZ = MathHelper.clamp_float((float)this.motionZ, -0.25F, 0.25F);
/*     */       
/* 169 */       if ((((this.ticksExisted < 5) ? 1 : 0) & ((this.motionY < 0.0D) ? 1 : 0)) != 0)
/* 170 */         this.motionY = Math.abs(this.motionY); 
/*     */     } else {
/* 172 */       getNewTarget();
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean attackEntityFrom(DamageSource p_70097_1_, float p_70097_2_) {
/* 177 */     if (isEntityInvulnerable()) {
/* 178 */       return false;
/*     */     }
/* 180 */     setBeenAttacked();
/* 181 */     if (p_70097_1_.getEntity() != null) {
/* 182 */       Vec3 vec3 = p_70097_1_.getEntity().getLookVec();
/* 183 */       if (vec3 != null) {
/* 184 */         this.motionX = vec3.xCoord;
/* 185 */         this.motionY = vec3.yCoord;
/* 186 */         this.motionZ = vec3.zCoord;
/* 187 */         this.motionX *= 0.9D;
/* 188 */         this.motionY *= 0.9D;
/* 189 */         this.motionZ *= 0.9D;
/* 190 */         this.worldObj.playSoundAtEntity((Entity)this, "thaumcraft:zap", 1.0F, 1.0F + (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F);
/*     */       } 
/* 192 */       return true;
/*     */     } 
/* 194 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\antro\Documents\forgottenRelicsDeobfuscated.jar!\com\integral\forgottenrelics\entities\EntityCrimsonOrb.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */