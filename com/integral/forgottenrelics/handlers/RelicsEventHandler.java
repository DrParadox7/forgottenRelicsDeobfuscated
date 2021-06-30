/*     */ package com.integral.forgottenrelics.handlers;
/*     */ 
/*     */ import baubles.common.lib.PlayerHandler;
/*     */ import com.integral.forgottenrelics.Main;
/*     */ import com.integral.forgottenrelics.items.ItemFateTome;
/*     */ import com.integral.forgottenrelics.packets.ForgottenResearchMessage;
/*     */ import com.integral.forgottenrelics.packets.GuardianVanishMessage;
/*     */ import com.integral.forgottenrelics.packets.PacketVoidMessage;
/*     */ import cpw.mods.fml.common.FMLCommonHandler;
/*     */ import cpw.mods.fml.common.eventhandler.EventPriority;
/*     */ import cpw.mods.fml.common.eventhandler.SubscribeEvent;
/*     */ import cpw.mods.fml.common.network.NetworkRegistry;
/*     */ import cpw.mods.fml.common.network.simpleimpl.IMessage;
/*     */ import java.util.List;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.EntityPlayerMP;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.potion.PotionEffect;
/*     */ import net.minecraft.stats.Achievement;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.MovingObjectPosition;
/*     */ import net.minecraft.util.Vec3;
/*     */ import net.minecraft.world.Teleporter;
/*     */ import net.minecraftforge.event.entity.living.LivingAttackEvent;
/*     */ import net.minecraftforge.event.entity.living.LivingDeathEvent;
/*     */ import net.minecraftforge.event.entity.living.LivingEvent;
/*     */ import net.minecraftforge.event.entity.living.LivingHurtEvent;
/*     */ import net.minecraftforge.event.entity.player.AchievementEvent;
/*     */ import net.minecraftforge.event.entity.player.PlayerEvent;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.api.aspects.AspectList;
/*     */ import thaumcraft.common.Thaumcraft;
/*     */ import thaumcraft.common.config.Config;
/*     */ import thaumcraft.common.items.wands.WandManager;
/*     */ import thaumcraft.common.lib.research.ResearchManager;
/*     */ import thaumcraft.common.lib.world.dim.TeleporterThaumcraft;
/*     */ import vazkii.botania.common.achievement.ModAchievements;
/*     */ import vazkii.botania.common.core.helper.ItemNBTHelper;
/*     */ import vazkii.botania.common.core.helper.Vector3;
/*     */ import vazkii.botania.common.entity.EntityDoppleganger;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class RelicsEventHandler
/*     */ {
/*     */   @SubscribeEvent
/*     */   public void onAchievement(AchievementEvent event) {
/*  51 */     Achievement theAchievement = event.achievement;
/*  52 */     EntityPlayer player = event.entityPlayer;
/*  53 */     Achievement kingKey = ModAchievements.relicKingKey;
/*     */ 
/*     */     
/*  56 */     if ((theAchievement.equals(kingKey) & (!event.entityPlayer.worldObj.isRemote ? 1 : 0)) != 0 && 
/*  57 */       !((EntityPlayerMP)player).func_147099_x().hasAchievementUnlocked(kingKey)) {
/*  58 */       boolean clueUnlocked = ResearchManager.isResearchComplete(player.getDisplayName(), "@Apotheosis");
/*  59 */       boolean researchCompleted = ResearchManager.isResearchComplete(player.getDisplayName(), "Apotheosis");
/*     */       
/*  61 */       if (((!clueUnlocked ? 1 : 0) & (!researchCompleted ? 1 : 0)) != 0) {
/*  62 */         Main.packetInstance.sendTo((IMessage)new ForgottenResearchMessage("@Apotheosis"), (EntityPlayerMP)player);
/*  63 */         Thaumcraft.proxy.getResearchManager().completeResearch(player, "@Apotheosis");
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @SubscribeEvent
/*     */   public void livingTick(LivingEvent.LivingUpdateEvent event) {
/*  72 */     if ((event.entity instanceof EntityPlayerMP & (!event.entity.worldObj.isRemote ? 1 : 0)) != 0) {
/*  73 */       EntityPlayerMP player = (EntityPlayerMP)event.entity;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  81 */       if (RelicsConfigHandler.outerLandsAntiAbuseEnabled && 
/*  82 */         player.ticksExisted % RelicsConfigHandler.outerLandsCheckrate == 0 && 
/*  83 */         player.dimension == Config.dimensionOuterId) {
/*  84 */         double d0 = player.posX;
/*  85 */         double d1 = player.posY + 1.62D - player.yOffset;
/*  86 */         double d2 = player.posZ;
/*     */         
/*  88 */         Vec3 position1 = Vec3.createVectorHelper(d0, d1, d2);
/*  89 */         Vec3 position2 = Vec3.createVectorHelper(d0, d1, d2);
/*  90 */         Vec3 up = Vec3.createVectorHelper(d0, d1 + 24.0D, d2);
/*  91 */         Vec3 down = Vec3.createVectorHelper(d0, d1 - 24.0D, d2);
/*     */         
/*  93 */         MovingObjectPosition pos1 = player.worldObj.rayTraceBlocks(position1, up);
/*  94 */         MovingObjectPosition pos2 = player.worldObj.rayTraceBlocks(position2, down);
/*     */         
/*  96 */         if (pos1 == null || pos2 == null) {
/*  97 */           player.timeUntilPortal = 100;
/*  98 */           player.mcServer.getConfigurationManager().transferPlayerToDimension(player, 0, (Teleporter)new TeleporterThaumcraft(FMLCommonHandler.instance().getMinecraftServerInstance().worldServerForDimension(0)));
/*     */         } 
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 110 */       if (player.ticksExisted % RelicsConfigHandler.researchInspectionFrequency == 0 && 
/* 111 */         Math.random() <= RelicsConfigHandler.knowledgeChance) {
/* 112 */         SuperpositionHandler.bringTheJustice((EntityPlayer)player);
/*     */       }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 119 */       if (Main.castingCooldowns.containsKey(player)) {
/* 120 */         int cooldown = ((Integer)Main.castingCooldowns.get(player)).intValue();
/* 121 */         if (cooldown > 0) {
/* 122 */           cooldown--;
/* 123 */           Main.castingCooldowns.put(player, Integer.valueOf(cooldown));
/*     */           
/*     */           return;
/*     */         } 
/*     */         
/*     */         return;
/*     */       } 
/* 130 */       Main.castingCooldowns.put(player, Integer.valueOf(0));
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 140 */     if (RelicsConfigHandler.guardianAntiAbuseRadius > 0.0F) {
/* 141 */       if ((event.entity instanceof EntityDoppleganger & (!event.entity.worldObj.isRemote ? 1 : 0) & ((event.entity.ticksExisted > 100) ? 1 : 0)) != 0) {
/* 142 */         EntityDoppleganger theGuardian = (EntityDoppleganger)event.entity;
/* 143 */         Vector3 pos = Vector3.fromEntityCenter((Entity)theGuardian);
/* 144 */         double range = RelicsConfigHandler.guardianAntiAbuseRadius;
/* 145 */         AxisAlignedBB boundingBox = AxisAlignedBB.getBoundingBox(theGuardian.posX - range, theGuardian.posY - range, theGuardian.posZ - range, theGuardian.posX + range, theGuardian.posY + range, theGuardian.posZ + range);
/*     */         
/* 147 */         if (theGuardian.worldObj.isAnyLiquid(boundingBox)) {
/*     */           
/* 149 */           Main.packetInstance.sendToAllAround((IMessage)new PacketVoidMessage(pos.x, pos.y, pos.z, true), new NetworkRegistry.TargetPoint(theGuardian.dimension, theGuardian.posX, theGuardian.posY, theGuardian.posZ, 64.0D));
/* 150 */           if (RelicsConfigHandler.guardianNotificationRadius != 0.0F)
/* 151 */             if (RelicsConfigHandler.guardianNotificationRadius > 0.0F) {
/* 152 */               Main.packetInstance.sendToAllAround((IMessage)new GuardianVanishMessage(), new NetworkRegistry.TargetPoint(theGuardian.dimension, theGuardian.posX, theGuardian.posY, theGuardian.posZ, RelicsConfigHandler.guardianNotificationRadius));
/*     */             } else {
/* 154 */               Main.packetInstance.sendToAll((IMessage)new GuardianVanishMessage());
/*     */             }  
/* 156 */           SuperpositionHandler.imposeBurst(theGuardian.worldObj, theGuardian.dimension, pos.x, pos.y, pos.z, 2.0F);
/* 157 */           theGuardian.playSound("thaumcraft:craftfail", 4.0F, (float)(0.8999999761581421D + Math.random() * 0.10000000149011612D));
/*     */           
/* 159 */           if (RelicsConfigHandler.memesEnabled) {
/* 160 */             theGuardian.playSound("forgottenrelics:sound.meme112", 4.0F, 1.0F);
/*     */           }
/* 162 */           theGuardian.setDead();
/*     */         } 
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @SubscribeEvent(priority = EventPriority.LOWEST)
/*     */   public void miningStuff(PlayerEvent.BreakSpeed event) {
/* 175 */     float miningBoost = 1.0F;
/*     */     
/* 177 */     if (SuperpositionHandler.hasBauble(event.entityPlayer, Main.itemAdvancedMiningCharm)) {
/* 178 */       miningBoost += RelicsConfigHandler.advancedMiningCharmBoost;
/*     */     }
/*     */     
/* 181 */     if (SuperpositionHandler.hasBauble(event.entityPlayer, Main.itemMiningCharm)) {
/* 182 */       miningBoost += RelicsConfigHandler.miningCharmBoost;
/*     */     }
/*     */     
/* 185 */     event.newSpeed *= miningBoost;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @SubscribeEvent(priority = EventPriority.HIGHEST)
/*     */   public void onEntityAttacked(LivingAttackEvent event) {
/* 196 */     if ((event.source.getEntity() instanceof EntityPlayer & (!event.isCanceled() ? 1 : 0)) != 0) {
/* 197 */       EntityPlayer attackerPlayer = (EntityPlayer)event.source.getEntity();
/*     */       
/* 199 */       if ((attackerPlayer.inventory.hasItem(Main.itemChaosCore) & ((Math.random() < 0.45D) ? 1 : 0)) != 0) {
/* 200 */         List<Entity> entityList = event.entity.worldObj.getEntitiesWithinAABBExcludingEntity(event.entity, AxisAlignedBB.getBoundingBox(event.entity.posX - 16.0D, event.entity.posY - 16.0D, event.entity.posZ - 16.0D, event.entity.posX + 16.0D, event.entity.posY + 16.0D, event.entity.posZ + 16.0D));
/*     */         
/* 202 */         if ((((entityList != null) ? 1 : 0) & ((entityList.size() > 0) ? 1 : 0)) != 0) {
/* 203 */           Entity randomEntity = entityList.get((int)(Math.random() * (entityList.size() - 1)));
/*     */           
/* 205 */           float redirectedAmount = event.ammount * (float)(Math.random() * 2.0D);
/*     */           
/* 207 */           if (Math.random() < 0.15D) {
/* 208 */             attackerPlayer.attackEntityFrom(event.source, redirectedAmount);
/*     */           } else {
/* 210 */             randomEntity.attackEntityFrom(event.source, redirectedAmount);
/*     */           } 
/*     */           
/* 213 */           event.setCanceled(true);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 219 */     if (event.entity instanceof EntityPlayer) {
/*     */       
/* 221 */       EntityPlayer player = (EntityPlayer)event.entity;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 227 */       if (((!event.isCanceled() ? 1 : 0) & player.inventory.hasItem(Main.itemChaosCore) & ((Math.random() < 0.42D) ? 1 : 0)) != 0) {
/*     */         
/* 229 */         List<Entity> entityList = event.entity.worldObj.getEntitiesWithinAABBExcludingEntity(event.entity, AxisAlignedBB.getBoundingBox(event.entity.posX - 16.0D, event.entity.posY - 16.0D, event.entity.posZ - 16.0D, event.entity.posX + 16.0D, event.entity.posY + 16.0D, event.entity.posZ + 16.0D));
/*     */         
/* 231 */         if ((((entityList != null) ? 1 : 0) & ((entityList.size() > 0) ? 1 : 0)) != 0) {
/* 232 */           Entity randomEntity = entityList.get((int)(Math.random() * (entityList.size() - 1)));
/*     */ 
/*     */ 
/*     */           
/* 236 */           float redirectedAmount = event.ammount * (float)(Math.random() * 2.0D);
/*     */           
/* 238 */           randomEntity.attackEntityFrom(event.source, redirectedAmount);
/*     */           
/* 240 */           event.setCanceled(true);
/*     */         } 
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 250 */       if (((!event.isCanceled() ? 1 : 0) & SuperpositionHandler.hasBauble(player, Main.itemArcanum) & ((Math.random() < RelicsConfigHandler.nebulousCoreDodgeChance) ? 1 : 0) & (!SuperpositionHandler.isDamageTypeAbsolute(event.source) ? 1 : 0)) != 0)
/*     */       {
/* 252 */         for (int counter = 0; counter <= 32; counter++) {
/* 253 */           if (SuperpositionHandler.validTeleportRandomly(event.entity, event.entity.worldObj, 16)) {
/* 254 */             event.entity.hurtResistantTime = 20;
/* 255 */             event.setCanceled(true);
/*     */ 
/*     */ 
/*     */             
/*     */             break;
/*     */           } 
/*     */         } 
/*     */       }
/*     */ 
/*     */       
/* 265 */       if (((!event.isCanceled() ? 1 : 0) & SuperpositionHandler.hasBauble(player, Main.itemDarkSunRing) & Main.darkRingDamageNegations.contains(event.source.damageType)) != 0) {
/*     */         
/* 267 */         if (RelicsConfigHandler.darkSunRingHealLimit) {
/* 268 */           if (event.entity.hurtResistantTime == 0) {
/* 269 */             player.heal(event.ammount);
/* 270 */             event.entity.hurtResistantTime = 20;
/*     */           } 
/*     */         } else {
/* 273 */           player.heal(event.ammount);
/*     */         } 
/*     */         
/* 276 */         event.setCanceled(true);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       }
/* 284 */       else if (((!event.isCanceled() ? 1 : 0) & SuperpositionHandler.hasBauble(player, Main.itemDarkSunRing) & ((event.source.getEntity() != null) ? 1 : 0) & ((Math.random() <= RelicsConfigHandler.darkSunRingDeflectChance) ? 1 : 0)) != 0) {
/*     */         
/* 286 */         if (player.hurtResistantTime == 0) {
/* 287 */           player.hurtResistantTime = 20;
/* 288 */           event.source.getEntity().attackEntityFrom(event.source, event.ammount);
/* 289 */           event.setCanceled(true);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @SubscribeEvent
/*     */   public void onEntityHurt(LivingHurtEvent event) {
/* 305 */     if ((event.entity instanceof EntityPlayer & (!event.isCanceled() ? 1 : 0)) != 0) {
/* 306 */       EntityPlayer player = (EntityPlayer)event.entity;
/*     */       
/* 308 */       if ((player.inventory.hasItem(Main.itemFalseJustice) & (!SuperpositionHandler.isDamageTypeAbsolute(event.source) ? 1 : 0)) != 0) {
/* 309 */         event.setCanceled(true);
/* 310 */         if (event.source.getEntity() == null) {
/* 311 */           player.attackEntityFrom(new DamageRegistryHandler.DamageSourceTrueDamageUndef(), event.ammount * 2.0F);
/*     */         } else {
/*     */           
/* 314 */           player.attackEntityFrom((DamageSource)new DamageRegistryHandler.DamageSourceTrueDamage(event.source.getEntity()), event.ammount * 2.0F);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 324 */     if ((event.source.getEntity() instanceof EntityPlayer & (!event.isCanceled() ? 1 : 0)) != 0) {
/* 325 */       EntityPlayer attackerPlayer = (EntityPlayer)event.source.getEntity();
/*     */       
/* 327 */       if ((attackerPlayer.inventory.hasItem(Main.itemFalseJustice) & (!SuperpositionHandler.isDamageTypeAbsolute(event.source) ? 1 : 0)) != 0) {
/* 328 */         event.setCanceled(true);
/* 329 */         event.entity.attackEntityFrom((DamageSource)new DamageRegistryHandler.DamageSourceTrueDamage(event.source.getEntity()), event.ammount * 2.0F);
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 335 */     if ((event.entity instanceof EntityPlayer & (!event.isCanceled() ? 1 : 0)) != 0) {
/* 336 */       EntityPlayer player = (EntityPlayer)event.entity;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 343 */       if (((!event.isCanceled() ? 1 : 0) & player.inventory.hasItem(Main.itemChaosCore)) != 0) {
/* 344 */         event.ammount *= (float)(Math.random() * 2.0D);
/*     */       }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 351 */       if (((!event.isCanceled() ? 1 : 0) & ((event.ammount > 100.0F) ? 1 : 0) & (!SuperpositionHandler.isDamageTypeAbsolute(event.source) ? 1 : 0) & SuperpositionHandler.hasBauble(player, Main.itemDarkSunRing)) != 0) {
/* 352 */         SuperpositionHandler.sendNotification(player, 2);
/* 353 */         event.setCanceled(true);
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 360 */       if ((SuperpositionHandler.hasBauble(player, Main.itemDarkSunRing) & (!event.isCanceled() ? 1 : 0) & ((Math.random() <= 0.25D) ? 1 : 0) & (!SuperpositionHandler.isDamageTypeAbsolute(event.source) ? 1 : 0)) != 0) {
/* 361 */         event.ammount += event.ammount * (float)Math.random();
/*     */       }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 369 */       if (((!event.entity.worldObj.isRemote ? 1 : 0) & (!SuperpositionHandler.hasBauble(player, Main.itemAncientAegis) ? 1 : 0) & (!event.isCanceled() ? 1 : 0)) != 0) {
/* 370 */         EntityPlayer aegisOwner = SuperpositionHandler.findPlayerWithBauble(event.entity.worldObj, 32, Main.itemAncientAegis, (EntityLivingBase)player);
/*     */         
/* 372 */         if (aegisOwner != null) {
/* 373 */           aegisOwner.attackEntityFrom(event.source, event.ammount * 0.4F);
/* 374 */           event.ammount *= 0.6F;
/*     */         } 
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 382 */       if ((SuperpositionHandler.hasBauble(player, Main.itemAncientAegis) & (!event.isCanceled() ? 1 : 0) & (!SuperpositionHandler.isDamageTypeAbsolute(event.source) ? 1 : 0)) != 0) {
/* 383 */         event.ammount *= 1.0F - RelicsConfigHandler.ancientAegisDamageReduction;
/*     */       }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 391 */       if (((!(event.source instanceof DamageRegistryHandler.DamageSourceSuperposition) ? 1 : 0) & (!(event.source instanceof DamageRegistryHandler.DamageSourceSuperpositionDefined) ? 1 : 0)) != 0 && (
/* 392 */         SuperpositionHandler.hasBauble(player, Main.itemSuperpositionRing) & (!event.isCanceled() ? 1 : 0)) != 0) {
/*     */         DamageSource altSource;
/*     */ 
/*     */         
/* 396 */         if (event.source.getEntity() != null) {
/* 397 */           DamageRegistryHandler.DamageSourceSuperpositionDefined damageSourceSuperpositionDefined = new DamageRegistryHandler.DamageSourceSuperpositionDefined(event.source.getEntity());
/*     */         } else {
/* 399 */           altSource = new DamageRegistryHandler.DamageSourceSuperposition();
/*     */         } 
/* 401 */         if (event.source.isUnblockable())
/* 402 */           altSource.setDamageBypassesArmor(); 
/* 403 */         if (event.source.isDamageAbsolute()) {
/* 404 */           altSource.setDamageIsAbsolute();
/*     */         }
/* 406 */         altSource.damageType = event.source.getDamageType();
/*     */         
/* 408 */         List<EntityPlayer> superpositioned = SuperpositionHandler.getBaubleOwnersList(player.worldObj, Main.itemSuperpositionRing);
/* 409 */         if (superpositioned.contains(player)) {
/* 410 */           superpositioned.remove(player);
/*     */         }
/* 412 */         if (superpositioned.size() > 0) {
/* 413 */           double percent = 0.12D + Math.random() * 0.62D;
/* 414 */           float splitAmount = (float)(event.ammount * percent);
/*     */           
/* 416 */           for (int counter = superpositioned.size() - 1; counter >= 0; counter--) {
/* 417 */             EntityPlayer cPlayer = superpositioned.get(counter);
/* 418 */             cPlayer.attackEntityFrom(altSource, splitAmount / superpositioned.size());
/*     */           } 
/*     */           
/* 421 */           event.ammount -= splitAmount;
/*     */         } 
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 429 */       if (((!event.isCanceled() ? 1 : 0) & SuperpositionHandler.hasBauble(player, Main.itemOblivionAmulet) & (!SuperpositionHandler.isDamageTypeAbsolute(event.source) ? 1 : 0)) != 0 && 
/* 430 */         WandManager.consumeVisFromInventory(player, (new AspectList()).add(Aspect.FIRE, (int)(event.ammount * 8.0F * RelicsConfigHandler.oblivionAmuletVisMult)).add(Aspect.ENTROPY, (int)(event.ammount * 8.0F * RelicsConfigHandler.oblivionAmuletVisMult)))) {
/*     */         
/* 432 */         ItemStack oblivionAmulet = PlayerHandler.getPlayerBaubles(player).getStackInSlot(0);
/*     */         
/* 434 */         ItemNBTHelper.setFloat(oblivionAmulet, "IDamageStored", ItemNBTHelper.getFloat(oblivionAmulet, "IDamageStored", 0.0F) + event.ammount);
/*     */         
/* 436 */         event.setCanceled(true);
/*     */       } 
/*     */     } 
/*     */   }
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
/*     */   @SubscribeEvent
/*     */   public void onDeath(LivingDeathEvent event) {
/* 455 */     if ((event.entity instanceof EntityPlayer & (!(event.source instanceof DamageRegistryHandler.DamageSourceTrueDamage) ? 1 : 0) & (!(event.source instanceof DamageRegistryHandler.DamageSourceTrueDamageUndef) ? 1 : 0)) != 0) {
/* 456 */       EntityPlayer playerAttacked = (EntityPlayer)event.entity;
/* 457 */       if (playerAttacked.inventory.hasItem(Main.itemFalseJustice)) {
/* 458 */         event.setCanceled(true);
/*     */       }
/*     */     }
/* 461 */     else if ((event.source.getEntity() instanceof EntityPlayer & (!(event.source instanceof DamageRegistryHandler.DamageSourceTrueDamage) ? 1 : 0) & (!(event.source instanceof DamageRegistryHandler.DamageSourceTrueDamageUndef) ? 1 : 0)) != 0) {
/* 462 */       EntityPlayer playerAttacker = (EntityPlayer)event.source.getEntity();
/* 463 */       if (playerAttacker.inventory.hasItem(Main.itemFalseJustice)) {
/* 464 */         event.setCanceled(true);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @SubscribeEvent(priority = EventPriority.HIGHEST)
/*     */   public void onPlayerDeath(LivingDeathEvent event) {
/* 476 */     if (event.entity instanceof EntityPlayer) {
/* 477 */       EntityPlayer player = (EntityPlayer)event.entity;
/*     */       
/* 479 */       if (player.inventory.hasItem(Main.itemOmegaCore)) {
/* 480 */         event.setCanceled(true);
/* 481 */         player.setHealth(1.0F);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       }
/* 488 */       else if (!player.worldObj.isRemote) {
/*     */         
/* 490 */         ItemStack fateTomeStack = SuperpositionHandler.findFirst(player, Main.itemFateTome);
/*     */         
/* 492 */         if (fateTomeStack != null)
/*     */         {
/* 494 */           if (fateTomeStack.hasTagCompound())
/*     */           {
/* 496 */             if (ItemNBTHelper.verifyExistance(fateTomeStack, "IFateCooldown") && 
/* 497 */               ItemNBTHelper.getInt(fateTomeStack, "IFateCooldown", 0) == 0 && 
/* 498 */               WandManager.consumeVisFromInventory(player, (new AspectList()).add(Aspect.AIR, ItemFateTome.AerCost).add(Aspect.EARTH, ItemFateTome.TerraCost).add(Aspect.FIRE, ItemFateTome.IgnisCost).add(Aspect.WATER, ItemFateTome.AquaCost).add(Aspect.ORDER, ItemFateTome.OrdoCost).add(Aspect.ENTROPY, ItemFateTome.PerditioCost))) {
/*     */               
/* 500 */               int minCooldown = RelicsConfigHandler.fateTomeCooldownMIN * 20;
/* 501 */               int bonusCooldown = RelicsConfigHandler.fateTomeCooldownMAX * 20 - minCooldown;
/*     */               
/* 503 */               if (RelicsConfigHandler.fateTomeCooldownMAX != 0) {
/* 504 */                 ItemNBTHelper.setInt(fateTomeStack, "IFateCooldown", (int)(minCooldown + Math.random() * bonusCooldown));
/*     */               }
/* 506 */               event.setCanceled(true);
/*     */               
/* 508 */               player.setHealth(player.getMaxHealth());
/*     */               
/* 510 */               if (Math.random() <= 0.75D) {
/* 511 */                 player.addPotionEffect(new PotionEffect(11, 200, 2, false));
/* 512 */                 player.addPotionEffect(new PotionEffect(10, 500, 1, false));
/* 513 */                 player.addPotionEffect(new PotionEffect(12, 1000, 0, false));
/*     */               } else {
/* 515 */                 player.addPotionEffect(new PotionEffect(18, 600, 2, false));
/* 516 */                 player.addPotionEffect(new PotionEffect(15, 200, 0, false));
/* 517 */                 player.addPotionEffect(new PotionEffect(20, 300, 1, false));
/*     */               } 
/* 519 */               SuperpositionHandler.imposeBurst(player.worldObj, player.dimension, player.posX, player.posY + 1.0D, player.posZ, 1.5F);
/* 520 */               player.worldObj.playSoundEffect(player.posX + 0.5D, player.posY + 1.5D, player.posZ + 0.5D, "thaumcraft:runicShieldCharge", 1.0F, 1.0F);
/*     */             } 
/*     */           }
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\antro\Documents\forgottenRelicsDeobfuscated.jar!\com\integral\forgottenrelics\handlers\RelicsEventHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */