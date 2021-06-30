/*     */ package com.integral.forgottenrelics.entities;
/*     */ 
/*     */ import com.integral.forgottenrelics.handlers.DamageRegistryHandler;
/*     */ import com.integral.forgottenrelics.handlers.RelicsConfigHandler;
/*     */ import cpw.mods.fml.relauncher.ReflectionHelper;
/*     */ import java.util.LinkedList;
/*     */ import java.util.List;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.projectile.EntityThrowable;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.MovingObjectPosition;
/*     */ import net.minecraft.world.World;
/*     */ import vazkii.botania.common.Botania;
/*     */ import vazkii.botania.common.core.helper.Vector3;
/*     */ import vazkii.botania.common.lib.LibObfuscation;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class EntityRageousMissile
/*     */   extends EntityThrowable
/*     */ {
/*     */   private static final String TAG_TIME = "time";
/*     */   private static final String TAG_THROWER = "thrower";
/*     */   double lockX;
/*  34 */   double lockY = -1.0D;
/*  35 */   int time = 0; double lockZ;
/*     */   
/*     */   public EntityRageousMissile(World world) {
/*  38 */     super(world);
/*     */   }
/*     */ 
/*     */   
/*     */   public EntityRageousMissile(EntityPlayer thrower, boolean evil) {
/*  43 */     this(thrower.worldObj);
/*  44 */     ReflectionHelper.setPrivateValue(EntityThrowable.class, this, thrower, LibObfuscation.THROWER);
/*  45 */     setEvil(evil);
/*  46 */     setThrower(thrower);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void entityInit() {
/*  51 */     this.dataWatcher.addObject(25, Byte.valueOf((byte)0));
/*  52 */     this.dataWatcher.addObject(26, Integer.valueOf(0));
/*  53 */     this.dataWatcher.addObject(27, "Undefined");
/*     */   }
/*     */   
/*     */   public void setEvil(boolean evil) {
/*  57 */     this.dataWatcher.updateObject(25, Byte.valueOf((byte)(evil ? 1 : 0)));
/*     */   }
/*     */   
/*     */   public void setThrower(EntityPlayer player) {
/*  61 */     this.dataWatcher.updateObject(27, player.getDisplayName());
/*     */   }
/*     */   
/*     */   public boolean isEvil() {
/*  65 */     return (this.dataWatcher.getWatchableObjectByte(25) == 1);
/*     */   }
/*     */   
/*     */   public EntityPlayer getTrueThrower() {
/*  69 */     return this.worldObj.getPlayerEntityByName(this.dataWatcher.getWatchableObjectString(27));
/*     */   }
/*     */   
/*     */   public void setTarget(EntityLivingBase e) {
/*  73 */     this.dataWatcher.updateObject(26, Integer.valueOf((e == null) ? -1 : e.getEntityId()));
/*     */   }
/*     */   
/*     */   public EntityLivingBase getTargetEntity() {
/*  77 */     int id = this.dataWatcher.getWatchableObjectInt(26);
/*  78 */     Entity e = this.worldObj.getEntityByID(id);
/*  79 */     if (e != null && e instanceof EntityLivingBase) {
/*  80 */       return (EntityLivingBase)e;
/*     */     }
/*  82 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public void onUpdate() {
/*  87 */     double lastTickPosX = this.lastTickPosX;
/*  88 */     double lastTickPosY = this.lastTickPosY - this.yOffset + (this.height / 2.0F);
/*  89 */     double lastTickPosZ = this.lastTickPosZ;
/*     */     
/*  91 */     super.onUpdate();
/*     */     
/*  93 */     if (((!this.worldObj.isRemote ? 1 : 0) & (!getTarget() ? 1 : 0) & ((this.time > 160) ? 1 : 0)) != 0) {
/*  94 */       setDead();
/*     */       
/*     */       return;
/*     */     } 
/*  98 */     boolean evil = isEvil();
/*  99 */     Vector3 thisVec = Vector3.fromEntityCenter((Entity)this);
/* 100 */     Vector3 oldPos = new Vector3(lastTickPosX, lastTickPosY, lastTickPosZ);
/* 101 */     Vector3 diff = thisVec.copy().sub(oldPos);
/* 102 */     Vector3 step = diff.copy().normalize().multiply(0.05D);
/* 103 */     int steps = (int)(diff.mag() / step.mag());
/* 104 */     Vector3 particlePos = oldPos.copy();
/*     */     
/* 106 */     float rc = 0.0F;
/*     */ 
/*     */ 
/*     */     
/* 110 */     Botania.proxy.setSparkleFXCorrupt(false);
/* 111 */     for (int i = 0; i < steps; i++) {
/* 112 */       Botania.proxy.sparkleFX(this.worldObj, particlePos.x, particlePos.y, particlePos.z, rc, (float)(0.800000011920929D + Math.random() * 0.20000000298023224D), (float)(0.4000000059604645D + Math.random() * 0.6000000238418579D), 0.8F, 2);
/* 113 */       if (this.worldObj.rand.nextInt(steps) <= 1) {
/* 114 */         Botania.proxy.sparkleFX(this.worldObj, particlePos.x + (Math.random() - 0.5D) * 0.4D, particlePos.y + (Math.random() - 0.5D) * 0.4D, particlePos.z + (Math.random() - 0.5D) * 0.4D, rc, (float)(0.800000011920929D + Math.random() * 0.20000000298023224D), (float)(0.4000000059604645D + Math.random() * 0.6000000238418579D), 0.8F, 2);
/*     */       }
/* 116 */       particlePos.add(step);
/*     */     } 
/*     */     
/* 119 */     Botania.proxy.setSparkleFXCorrupt(false);
/*     */     
/* 121 */     EntityLivingBase target = getTargetEntity();
/* 122 */     if (target != null) {
/* 123 */       if (this.lockY == -1.0D) {
/* 124 */         this.lockX = target.posX;
/* 125 */         this.lockY = target.posY;
/* 126 */         this.lockZ = target.posZ;
/*     */       } 
/*     */       
/* 129 */       Vector3 targetVec = Vector3.fromEntityCenter((Entity)target);
/* 130 */       Vector3 diffVec = targetVec.copy().sub(thisVec);
/* 131 */       Vector3 motionVec = diffVec.copy().normalize().multiply(0.5D);
/* 132 */       this.motionX = motionVec.x;
/* 133 */       this.motionY = motionVec.y;
/* 134 */       if (this.time < 30)
/* 135 */         this.motionY = Math.abs(this.motionY); 
/* 136 */       this.motionZ = motionVec.z;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 152 */       if (this.worldObj.isRemote) {
/* 153 */         List<EntityLivingBase> targetList = this.worldObj.getEntitiesWithinAABB(EntityLivingBase.class, AxisAlignedBB.getBoundingBox(this.posX - 0.5D, this.posY - 0.5D, this.posZ - 0.5D, this.posX + 0.5D, this.posY + 0.5D, this.posZ + 0.5D));
/* 154 */         if (targetList.size() > 0)
/*     */         {
/* 156 */           for (int j = 0; j < 12; j++) {
/* 157 */             float r = 0.0F;
/* 158 */             float g = 0.8F + (float)Math.random() * 0.2F;
/* 159 */             float b = 0.4F + (float)Math.random() * 0.6F;
/* 160 */             float s = 0.2F + (float)Math.random() * 0.1F;
/* 161 */             float m = 0.15F;
/* 162 */             float xm = ((float)Math.random() - 0.5F) * m;
/* 163 */             float ym = ((float)Math.random() - 0.5F) * m;
/* 164 */             float zm = ((float)Math.random() - 0.5F) * m;
/*     */ 
/*     */             
/* 167 */             Botania.proxy.wispFX(this.worldObj, this.posX + (this.width / 2.0F), this.posY + (this.height / 2.0F), this.posZ + (this.width / 2.0F), r, g, b, s, xm, ym, zm);
/*     */           } 
/*     */         }
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 176 */       if (evil && diffVec.mag() < 1.0D) {
/* 177 */         setDead();
/*     */       }
/*     */     } else {
/* 180 */       Vector3 targetVec = new Vector3(this.posX + (Math.random() - 0.5D) * 16.0D, this.posY + (Math.random() - 0.5D) * 16.0D, this.posZ + (Math.random() - 0.5D) * 16.0D);
/* 181 */       Vector3 diffVec = targetVec.copy().sub(thisVec);
/* 182 */       Vector3 motionVec = diffVec.copy().normalize().multiply(0.5D);
/* 183 */       this.motionX = motionVec.x;
/* 184 */       this.motionY = motionVec.y;
/* 185 */       this.motionZ = motionVec.z;
/*     */     } 
/*     */ 
/*     */     
/* 189 */     this.time++;
/*     */   }
/*     */ 
/*     */   
/*     */   public void writeEntityToNBT(NBTTagCompound cmp) {
/* 194 */     super.writeEntityToNBT(cmp);
/* 195 */     cmp.setInteger("time", this.time);
/*     */   }
/*     */ 
/*     */   
/*     */   public void readEntityFromNBT(NBTTagCompound cmp) {
/* 200 */     super.readEntityFromNBT(cmp);
/* 201 */     this.time = cmp.getInteger("time");
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean getTarget() {
/* 206 */     EntityLivingBase target = getTargetEntity();
/* 207 */     if (target != null && target.getHealth() > 0.0F && !target.isDead && this.worldObj.loadedEntityList.contains(target))
/* 208 */       return true; 
/* 209 */     if (target != null) {
/* 210 */       setTarget((EntityLivingBase)null);
/*     */     }
/* 212 */     List<Entity> entities = new LinkedList();
/*     */     
/* 214 */     double range = 32.0D;
/* 215 */     entities = this.worldObj.getEntitiesWithinAABBExcludingEntity((Entity)getTrueThrower(), AxisAlignedBB.getBoundingBox(this.posX - range, this.posY - range, this.posZ - range, this.posX + range, this.posY + range, this.posZ + range));
/*     */     
/* 217 */     while (entities.size() > 0) {
/* 218 */       Entity e = entities.get(this.worldObj.rand.nextInt(entities.size()));
/* 219 */       if (!(e instanceof EntityLivingBase) || e.isDead) {
/* 220 */         entities.remove(e);
/*     */         
/*     */         continue;
/*     */       } 
/* 224 */       target = (EntityLivingBase)e;
/* 225 */       setTarget(target);
/*     */     } 
/*     */ 
/*     */     
/* 229 */     return (target != null);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void onImpact(MovingObjectPosition pos) {
/* 234 */     Block block = this.worldObj.getBlock(pos.blockX, pos.blockY, pos.blockZ);
/*     */     
/* 236 */     if ((((pos.entityHit != null) ? 1 : 0) & ((getTargetEntity() == pos.entityHit) ? 1 : 0)) != 0) {
/*     */       
/* 238 */       EntityPlayer thrower = getTrueThrower();
/* 239 */       if (thrower != null) {
/* 240 */         EntityPlayer player = (thrower instanceof EntityPlayer) ? thrower : null;
/* 241 */         pos.entityHit.attackEntityFrom((DamageSource)new DamageRegistryHandler.DamageSourceMagic((Entity)getThrower()), (float)(RelicsConfigHandler.nuclearFuryDamageMIN + Math.random() * (RelicsConfigHandler.nuclearFuryDamageMAX - RelicsConfigHandler.nuclearFuryDamageMIN)));
/*     */       } 
/* 243 */       this.worldObj.playSoundEffect(this.posX, this.posY, this.posZ, "random.fizz", 2.0F, (float)(0.800000011920929D + Math.random() * 0.20000000298023224D));
/* 244 */       setDead();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\antro\Documents\forgottenRelicsDeobfuscated.jar!\com\integral\forgottenrelics\entities\EntityRageousMissile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */