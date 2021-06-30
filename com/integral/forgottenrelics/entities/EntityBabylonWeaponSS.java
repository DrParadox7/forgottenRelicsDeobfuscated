/*     */ package com.integral.forgottenrelics.entities;
/*     */ 
/*     */ import com.integral.forgottenrelics.Main;
/*     */ import com.integral.forgottenrelics.handlers.DamageRegistryHandler;
/*     */ import com.integral.forgottenrelics.handlers.RelicsConfigHandler;
/*     */ import com.integral.forgottenrelics.handlers.SuperpositionHandler;
/*     */ import com.integral.forgottenrelics.packets.ApotheosisParticleMessage;
/*     */ import cpw.mods.fml.common.network.NetworkRegistry;
/*     */ import cpw.mods.fml.common.network.simpleimpl.IMessage;
/*     */ import java.util.List;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.MovingObjectPosition;
/*     */ import net.minecraft.world.World;
/*     */ import vazkii.botania.common.Botania;
/*     */ import vazkii.botania.common.core.helper.Vector3;
/*     */ import vazkii.botania.common.entity.EntityThrowableCopy;
/*     */ import vazkii.botania.common.item.equipment.tool.ToolCommons;
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
/*     */ public class EntityBabylonWeaponSS
/*     */   extends EntityThrowableCopy
/*     */ {
/*     */   private static final String TAG_CHARGING = "charging";
/*     */   private static final String TAG_VARIETY = "variety";
/*     */   private static final String TAG_CHARGE_TICKS = "chargeTicks";
/*     */   private static final String TAG_LIVE_TICKS = "liveTicks";
/*     */   private static final String TAG_DELAY = "delay";
/*     */   private static final String TAG_ROTATION = "rotation";
/*     */   
/*     */   public EntityBabylonWeaponSS(World world) {
/*  45 */     super(world);
/*     */   }
/*     */   
/*     */   public EntityBabylonWeaponSS(World world, EntityLivingBase thrower) {
/*  49 */     super(world, thrower);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void entityInit() {
/*  54 */     setSize(0.0F, 0.0F);
/*     */     
/*  56 */     this.dataWatcher.addObject(20, Byte.valueOf((byte)0));
/*  57 */     this.dataWatcher.addObject(21, Integer.valueOf(0));
/*  58 */     this.dataWatcher.addObject(22, Integer.valueOf(0));
/*  59 */     this.dataWatcher.addObject(23, Integer.valueOf(0));
/*  60 */     this.dataWatcher.addObject(24, Integer.valueOf(0));
/*  61 */     this.dataWatcher.addObject(25, Float.valueOf(0.0F));
/*     */     
/*  63 */     this.dataWatcher.setObjectWatched(20);
/*  64 */     this.dataWatcher.setObjectWatched(21);
/*  65 */     this.dataWatcher.setObjectWatched(22);
/*  66 */     this.dataWatcher.setObjectWatched(23);
/*  67 */     this.dataWatcher.setObjectWatched(24);
/*  68 */     this.dataWatcher.setObjectWatched(25);
/*     */   }
/*     */ 
/*     */   
/*     */   public void onUpdate() {
/*  73 */     EntityLivingBase thrower = getThrower();
/*  74 */     if (!this.worldObj.isRemote && (thrower == null || !(thrower instanceof EntityPlayer) || thrower.isDead)) {
/*  75 */       setDead();
/*     */       return;
/*     */     } 
/*  78 */     EntityPlayer player = (EntityPlayer)thrower;
/*     */     
/*  80 */     double x = this.motionX;
/*  81 */     double y = this.motionY;
/*  82 */     double z = this.motionZ;
/*     */     
/*  84 */     int liveTime = getLiveTicks();
/*  85 */     int delay = getDelay();
/*     */     
/*  87 */     if (this.ticksExisted <= 15) {
/*  88 */       this.motionX = 0.0D;
/*  89 */       this.motionY = 0.0D;
/*  90 */       this.motionZ = 0.0D;
/*     */       
/*  92 */       int chargeTime = getChargeTicks();
/*  93 */       setChargeTicks(chargeTime + 1);
/*     */       
/*  95 */       if (this.worldObj.rand.nextInt(20) == 0)
/*  96 */         this.worldObj.playSoundAtEntity((Entity)this, "botania:babylonSpawn", 0.1F, 1.0F + this.worldObj.rand.nextFloat() * 3.0F); 
/*     */     } else {
/*  98 */       if (liveTime < delay) {
/*  99 */         this.motionX = 0.0D;
/* 100 */         this.motionY = 0.0D;
/* 101 */         this.motionZ = 0.0D;
/* 102 */       } else if (liveTime == delay && player != null) {
/* 103 */         Vector3 playerLook = null;
/* 104 */         MovingObjectPosition lookat = ToolCommons.raytraceFromEntity(this.worldObj, (Entity)player, true, 64.0D);
/* 105 */         if (lookat == null)
/* 106 */         { playerLook = (new Vector3(player.getLookVec())).multiply(64.0D).add(Vector3.fromEntity((Entity)player)); }
/* 107 */         else { playerLook = new Vector3(lookat.blockX + 0.5D, lookat.blockY + 0.5D, lookat.blockZ + 0.5D); }
/*     */         
/* 109 */         Vector3 thisVec = Vector3.fromEntityCenter((Entity)this);
/* 110 */         Vector3 motionVec = playerLook.sub(thisVec).normalize().multiply(3.0D);
/*     */         
/* 112 */         x = motionVec.x;
/* 113 */         y = motionVec.y;
/* 114 */         z = motionVec.z;
/* 115 */         this.worldObj.playSoundAtEntity((Entity)this, "botania:babylonAttack", 2.0F, 0.1F + this.worldObj.rand.nextFloat() * 3.0F);
/*     */       } 
/* 117 */       setLiveTicks(liveTime + 1);
/*     */       
/* 119 */       if (!this.worldObj.isRemote) {
/* 120 */         AxisAlignedBB axis = AxisAlignedBB.getBoundingBox(this.posX, this.posY, this.posZ, this.lastTickPosX, this.lastTickPosY, this.lastTickPosZ).expand(2.0D, 2.0D, 2.0D);
/* 121 */         List<EntityLivingBase> entities = this.worldObj.getEntitiesWithinAABB(EntityLivingBase.class, axis);
/* 122 */         for (EntityLivingBase living : entities) {
/* 123 */           if (living == thrower) {
/*     */             continue;
/*     */           }
/* 126 */           if (!living.isDead) {
/* 127 */             if (player != null) {
/* 128 */               living.attackEntityFrom((DamageSource)new DamageRegistryHandler.DamageSourceMagic((Entity)player), RelicsConfigHandler.damageApotheosisDirect);
/*     */             }
/* 130 */             Vector3 targetPos = Vector3.fromEntityCenter((Entity)living);
/* 131 */             Vector3 thisPos = Vector3.fromEntityCenter((Entity)this);
/* 132 */             Vector3 diff = targetPos.copy().sub(thisPos);
/*     */             
/* 134 */             diff.normalize();
/* 135 */             diff.multiply(1.0D / living.getDistanceToEntity((Entity)this));
/*     */             
/* 137 */             if (diff.mag() > 1.0D) {
/* 138 */               diff.normalize();
/*     */             }
/* 140 */             if (living instanceof net.minecraft.entity.boss.IBossDisplayData) {
/* 141 */               diff.multiply(0.5D);
/*     */             }
/* 143 */             living.motionX += diff.x;
/* 144 */             living.motionY += diff.y;
/* 145 */             living.motionZ += diff.z;
/*     */             
/* 147 */             onImpact(new MovingObjectPosition((Entity)living));
/*     */             
/*     */             return;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/* 154 */     super.onUpdate();
/*     */     
/* 156 */     this.motionX = x;
/* 157 */     this.motionY = y;
/* 158 */     this.motionZ = z;
/*     */     
/* 160 */     if (liveTime > delay) {
/* 161 */       Botania.proxy.wispFX(this.worldObj, this.posX, this.posY, this.posZ, 1.0F, 1.0F, 0.0F, 0.3F, 0.0F);
/*     */     }
/* 163 */     if (liveTime > 200 + delay)
/* 164 */       setDead(); 
/*     */   }
/*     */   
/*     */   public void invokeDamageEffects() {
/* 168 */     List<EntityLivingBase> targets = this.worldObj.getEntitiesWithinAABB(EntityLivingBase.class, this.boundingBox.expand(3.0D, 3.0D, 3.0D));
/*     */     
/* 170 */     if (targets.contains(getThrower())) {
/* 171 */       targets.remove(getThrower());
/*     */     }
/* 173 */     for (int counterS = targets.size() - 1; counterS >= 0; counterS--) {
/* 174 */       if (((!((EntityLivingBase)targets.get(counterS)).isDead ? 1 : 0) & (!this.worldObj.isRemote ? 1 : 0)) != 0) {
/* 175 */         ((EntityLivingBase)targets.get(counterS)).attackEntityFrom((DamageSource)new DamageRegistryHandler.DamageSourceMagic((Entity)getThrower()), RelicsConfigHandler.damageApotheosisImpact);
/* 176 */         Vector3 targetPos = Vector3.fromEntityCenter((Entity)targets.get(counterS));
/* 177 */         Vector3 thisPos = Vector3.fromEntityCenter((Entity)this);
/* 178 */         Vector3 diff = targetPos.copy().sub(thisPos);
/*     */         
/* 180 */         diff.normalize();
/* 181 */         diff.multiply(1.0D / ((EntityLivingBase)targets.get(counterS)).getDistanceToEntity((Entity)this));
/*     */         
/* 183 */         if (diff.mag() > 1.0D) {
/* 184 */           diff.normalize();
/*     */         }
/* 186 */         if (targets.get(counterS) instanceof net.minecraft.entity.boss.IBossDisplayData) {
/* 187 */           diff.multiply(0.5D);
/*     */         }
/* 189 */         ((EntityLivingBase)targets.get(counterS)).motionX += diff.x;
/* 190 */         ((EntityLivingBase)targets.get(counterS)).motionY += diff.y;
/* 191 */         ((EntityLivingBase)targets.get(counterS)).motionZ += diff.z;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void onImpact(MovingObjectPosition pos) {
/* 199 */     EntityLivingBase thrower = getThrower();
/*     */     
/* 201 */     Block block = this.worldObj.getBlock(pos.blockX, pos.blockY, pos.blockZ);
/* 202 */     if (pos.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK && (
/* 203 */       block instanceof net.minecraft.block.BlockBush || block instanceof net.minecraft.block.BlockLeaves || block instanceof net.minecraft.block.BlockLiquid)) {
/*     */       return;
/*     */     }
/*     */     
/* 207 */     if (pos.entityHit == null || pos.entityHit != thrower) {
/*     */       
/* 209 */       if (((!this.worldObj.isRemote ? 1 : 0) & ((thrower != null) ? 1 : 0)) != 0) {
/*     */         
/* 211 */         SuperpositionHandler.imposeBurst(this.worldObj, this.dimension, this.posX, this.posY, this.posZ, 1.5F);
/*     */         
/* 213 */         invokeDamageEffects();
/* 214 */         this.worldObj.playSoundEffect(this.posX, this.posY, this.posZ, "random.explode", 8.0F, (float)(0.800000011920929D + Math.random() * 0.2D));
/*     */       } 
/*     */       
/* 217 */       if (!this.worldObj.isRemote) {
/* 218 */         Main.packetInstance.sendToAllAround((IMessage)new ApotheosisParticleMessage(this.posX, this.posY, this.posZ, 40), new NetworkRegistry.TargetPoint(this.dimension, this.posX, this.posY, this.posZ, 128.0D));
/*     */       }
/* 220 */       setDead();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void writeEntityToNBT(NBTTagCompound cmp) {
/* 226 */     super.writeEntityToNBT(cmp);
/* 227 */     cmp.setBoolean("charging", isCharging());
/* 228 */     cmp.setInteger("variety", getVariety());
/* 229 */     cmp.setInteger("chargeTicks", getChargeTicks());
/* 230 */     cmp.setInteger("liveTicks", getLiveTicks());
/* 231 */     cmp.setInteger("delay", getDelay());
/* 232 */     cmp.setFloat("rotation", getRotation());
/*     */   }
/*     */ 
/*     */   
/*     */   public void readEntityFromNBT(NBTTagCompound cmp) {
/* 237 */     super.readEntityFromNBT(cmp);
/* 238 */     setCharging(cmp.getBoolean("charging"));
/* 239 */     setVariety(cmp.getInteger("variety"));
/* 240 */     setChargeTicks(cmp.getInteger("chargeTicks"));
/* 241 */     setLiveTicks(cmp.getInteger("liveTicks"));
/* 242 */     setDelay(cmp.getInteger("delay"));
/* 243 */     setRotation(cmp.getFloat("rotation"));
/*     */   }
/*     */   
/*     */   public boolean isCharging() {
/* 247 */     return (this.dataWatcher.getWatchableObjectByte(20) == 1);
/*     */   }
/*     */   
/*     */   public void setCharging(boolean charging) {
/* 251 */     this.dataWatcher.updateObject(20, Byte.valueOf((byte)(charging ? 1 : 0)));
/*     */   }
/*     */   
/*     */   public int getVariety() {
/* 255 */     return this.dataWatcher.getWatchableObjectInt(21);
/*     */   }
/*     */   
/*     */   public void setVariety(int var) {
/* 259 */     this.dataWatcher.updateObject(21, Integer.valueOf(var));
/*     */   }
/*     */   
/*     */   public int getChargeTicks() {
/* 263 */     return this.dataWatcher.getWatchableObjectInt(22);
/*     */   }
/*     */   
/*     */   public void setChargeTicks(int ticks) {
/* 267 */     this.dataWatcher.updateObject(22, Integer.valueOf(ticks));
/*     */   }
/*     */   
/*     */   public int getLiveTicks() {
/* 271 */     return this.dataWatcher.getWatchableObjectInt(23);
/*     */   }
/*     */   
/*     */   public void setLiveTicks(int ticks) {
/* 275 */     this.dataWatcher.updateObject(23, Integer.valueOf(ticks));
/*     */   }
/*     */   
/*     */   public int getDelay() {
/* 279 */     return this.dataWatcher.getWatchableObjectInt(24);
/*     */   }
/*     */   
/*     */   public void setDelay(int delay) {
/* 283 */     this.dataWatcher.updateObject(24, Integer.valueOf(delay));
/*     */   }
/*     */   
/*     */   public float getRotation() {
/* 287 */     return this.dataWatcher.getWatchableObjectFloat(25);
/*     */   }
/*     */   
/*     */   public void setRotation(float rot) {
/* 291 */     this.dataWatcher.updateObject(25, Float.valueOf(rot));
/*     */   }
/*     */ }


/* Location:              C:\Users\antro\Documents\forgottenRelicsDeobfuscated.jar!\com\integral\forgottenrelics\entities\EntityBabylonWeaponSS.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */