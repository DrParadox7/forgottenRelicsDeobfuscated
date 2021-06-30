/*     */ package com.integral.forgottenrelics.items;
/*     */ 
/*     */ import com.integral.forgottenrelics.Main;
/*     */ import com.integral.forgottenrelics.handlers.DamageRegistryHandler;
/*     */ import com.integral.forgottenrelics.handlers.RelicsConfigHandler;
/*     */ import com.integral.forgottenrelics.handlers.SuperpositionHandler;
/*     */ import com.integral.forgottenrelics.packets.PlayerMotionUpdateMessage;
/*     */ import com.integral.forgottenrelics.packets.TelekinesisAttackMessage;
/*     */ import com.integral.forgottenrelics.packets.TelekinesisParticleMessage;
/*     */ import com.integral.forgottenrelics.packets.TelekinesisUseMessage;
/*     */ import cpw.mods.fml.common.network.NetworkRegistry;
/*     */ import cpw.mods.fml.common.network.simpleimpl.IMessage;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.gui.GuiScreen;
/*     */ import net.minecraft.client.renderer.texture.IIconRegister;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.EntityPlayerMP;
/*     */ import net.minecraft.item.EnumAction;
/*     */ import net.minecraft.item.EnumRarity;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.potion.Potion;
/*     */ import net.minecraft.potion.PotionEffect;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.StatCollector;
/*     */ import net.minecraft.world.World;
/*     */ import thaumcraft.api.IWarpingGear;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.api.aspects.AspectList;
/*     */ import thaumcraft.common.items.wands.WandManager;
/*     */ import vazkii.botania.common.core.helper.Vector3;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ItemTelekinesisTome
/*     */   extends Item
/*     */   implements IWarpingGear
/*     */ {
/*  52 */   public static final int AerCost = (int)(6.0F * RelicsConfigHandler.telekinesisTomeVisMult);
/*  53 */   public static final int TerraCost = (int)(0.0F * RelicsConfigHandler.telekinesisTomeVisMult);
/*  54 */   public static final int IgnisCost = (int)(0.0F * RelicsConfigHandler.telekinesisTomeVisMult);
/*  55 */   public static final int AquaCost = (int)(0.0F * RelicsConfigHandler.telekinesisTomeVisMult);
/*  56 */   public static final int OrdoCost = (int)(8.0F * RelicsConfigHandler.telekinesisTomeVisMult);
/*  57 */   public static final int PerditioCost = (int)(0.0F * RelicsConfigHandler.telekinesisTomeVisMult);
/*     */   
/*     */   private static final float RANGE = 3.0F;
/*     */   
/*     */   private static final int COST = 2;
/*  62 */   private static HashMap tomeStatsMap = new HashMap<>();
/*  63 */   private static HashMap<EntityPlayer, HashMap> globalTomeMap = new HashMap<>();
/*     */   
/*     */   private static final String TAG_TICKS_TILL_EXPIRE = "ticksTillExpire";
/*     */   
/*     */   private static final String TAG_TICKS_COOLDOWN = "ticksCooldown";
/*     */   
/*     */   private static final String TAG_TARGET = "target";
/*     */   private static final String TAG_DIST = "dist";
/*     */   private static final String TAG_RE_DIST = "reDist";
/*     */   private boolean verificationVariable = false;
/*  73 */   private static boolean altMethods = RelicsConfigHandler.altTelekinesisAlgorithm;
/*     */   
/*     */   public ItemTelekinesisTome() {
/*  76 */     setMaxStackSize(1);
/*  77 */     setUnlocalizedName("ItemTelekinesisTome");
/*  78 */     setCreativeTab(Main.tabForgottenRelics);
/*     */   }
/*     */ 
/*     */   
/*     */   public HashMap getPlayerTomeData(EntityPlayer player) {
/*  83 */     this; if (globalTomeMap.containsKey(player)) {
/*  84 */       this; return globalTomeMap.get(player);
/*     */     } 
/*  86 */     HashMap<Object, Object> stats = new HashMap<>();
/*  87 */     this; stats.put("ticksTillExpire", Integer.valueOf(0));
/*  88 */     this; stats.put("ticksCooldown", Integer.valueOf(0));
/*  89 */     this; stats.put("target", Integer.valueOf(-1));
/*     */     
/*  91 */     this; stats.put("dist", Double.valueOf(-1.0D));
/*  92 */     this; stats.put("reDist", Double.valueOf(-1.0D));
/*     */     
/*  94 */     this; globalTomeMap.put(player, stats);
/*  95 */     return stats;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Object getTomeTag(EntityPlayer player, String tag, Object expectedValue) {
/* 101 */     HashMap playerData = getPlayerTomeData(player);
/*     */     
/* 103 */     this; this; this; if (tag == "ticksTillExpire" || tag == "ticksCooldown" || tag == "target") {
/*     */       int returnedValue;
/*     */       
/* 106 */       if (playerData.containsKey(tag)) {
/* 107 */         returnedValue = ((Integer)playerData.get(tag)).intValue();
/*     */       } else {
/* 109 */         returnedValue = ((Integer)expectedValue).intValue();
/*     */       } 
/* 111 */       return Integer.valueOf(returnedValue);
/* 112 */     }  this; this; if (tag == "dist" || tag == "reDist") {
/*     */       double returnedValue;
/*     */       
/* 115 */       if (playerData.containsKey(tag)) {
/* 116 */         returnedValue = ((Double)playerData.get(tag)).doubleValue();
/*     */       } else {
/* 118 */         returnedValue = ((Double)expectedValue).doubleValue();
/*     */       } 
/* 120 */       return Double.valueOf(returnedValue);
/*     */     } 
/* 122 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setTomeTag(EntityPlayer player, String tag, Object value) {
/* 127 */     HashMap<String, Integer> playerData = getPlayerTomeData(player);
/*     */     
/* 129 */     this; this; this; if (tag == "ticksTillExpire" || tag == "ticksCooldown" || tag == "target")
/* 130 */     { int savedValue = ((Integer)value).intValue();
/* 131 */       playerData.put(tag, Integer.valueOf(savedValue)); }
/* 132 */     else { this; this; if (tag == "dist" || tag == "reDist") {
/* 133 */         double savedValue = ((Double)value).doubleValue();
/* 134 */         playerData.put(tag, Double.valueOf(savedValue));
/*     */       } else {
/*     */         return;
/*     */       }  }
/* 138 */      this; globalTomeMap.put(player, playerData);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public EnumRarity getRarity(ItemStack itemStack) {
/* 144 */     return EnumRarity.epic;
/*     */   }
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public boolean hasPressedAttackKey() {
/* 150 */     if (((Minecraft.getMinecraft()).gameSettings.keyBindAttack.getIsKeyPressed() & (!this.verificationVariable ? 1 : 0)) != 0) {
/* 151 */       this.verificationVariable = true;
/* 152 */       return true;
/* 153 */     }  if (((!(Minecraft.getMinecraft()).gameSettings.keyBindAttack.getIsKeyPressed() ? 1 : 0) & ((this.verificationVariable == true) ? 1 : 0)) != 0) {
/* 154 */       this.verificationVariable = false;
/* 155 */       return false;
/*     */     } 
/* 157 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List<String> par3List, boolean par4) {
/* 163 */     if (GuiScreen.isShiftKeyDown()) {
/* 164 */       par3List.add(StatCollector.translateToLocal("item.ItemTelekinesisTome1.lore"));
/* 165 */       par3List.add(StatCollector.translateToLocal("item.ItemTelekinesisTome2.lore"));
/* 166 */       par3List.add(StatCollector.translateToLocal("item.ItemTelekinesisTome3.lore"));
/* 167 */       par3List.add(StatCollector.translateToLocal("item.FREmpty.lore"));
/* 168 */       par3List.add(StatCollector.translateToLocal("item.ItemTelekinesisTome4.lore"));
/* 169 */       par3List.add(StatCollector.translateToLocal("item.ItemTelekinesisTome5.lore"));
/* 170 */       par3List.add(StatCollector.translateToLocal("item.ItemTelekinesisTome6.lore"));
/* 171 */       par3List.add(StatCollector.translateToLocal("item.FREmpty.lore"));
/* 172 */       par3List.add(StatCollector.translateToLocal("item.ItemTelekinesisTome7.lore"));
/* 173 */       par3List.add(StatCollector.translateToLocal("item.ItemTelekinesisTome8.lore"));
/* 174 */       par3List.add(StatCollector.translateToLocal("item.ItemTelekinesisTome9.lore"));
/* 175 */       par3List.add(StatCollector.translateToLocal("item.FREmpty.lore"));
/* 176 */       par3List.add(StatCollector.translateToLocal("item.ItemTelekinesisTome10.lore"));
/* 177 */       par3List.add(StatCollector.translateToLocal("item.FREmpty.lore"));
/* 178 */       par3List.add(StatCollector.translateToLocal("item.ItemTelekinesisTome11_1.lore") + " " + (int)RelicsConfigHandler.telekinesisTomeDamageMIN + "-" + (int)RelicsConfigHandler.telekinesisTomeDamageMAX + " " + StatCollector.translateToLocal("item.ItemTelekinesisTome11_2.lore"));
/* 179 */     } else if (GuiScreen.isCtrlKeyDown()) {
/* 180 */       par3List.add(StatCollector.translateToLocal("item.FRVisPerTick.lore"));
/* 181 */       this; par3List.add(" " + StatCollector.translateToLocal("item.FRAerCost.lore") + ItemChaosTome.round(AerCost / 100.0D, 2));
/* 182 */       this; par3List.add(" " + StatCollector.translateToLocal("item.FRPerditioCost.lore") + ItemChaosTome.round(OrdoCost / 100.0D, 2));
/* 183 */       par3List.add(StatCollector.translateToLocal("item.FREmpty.lore"));
/* 184 */       par3List.add(StatCollector.translateToLocal("item.FRVisPerCast.lore"));
/* 185 */       par3List.add(" " + StatCollector.translateToLocal("item.FRAerCost.lore") + (0.8D * RelicsConfigHandler.telekinesisTomeVisMult));
/* 186 */       par3List.add(" " + StatCollector.translateToLocal("item.FROrdoCost.lore") + (0.5D * RelicsConfigHandler.telekinesisTomeVisMult));
/* 187 */       par3List.add(" " + StatCollector.translateToLocal("item.FRIgnisCost.lore") + (2.0D * RelicsConfigHandler.telekinesisTomeVisMult));
/*     */     } else {
/*     */       
/* 190 */       par3List.add(StatCollector.translateToLocal("item.FRViscostTooltip.lore"));
/* 191 */       par3List.add(StatCollector.translateToLocal("item.FRShiftTooltip.lore"));
/*     */     } 
/*     */     
/* 194 */     par3List.add(StatCollector.translateToLocal("item.FREmpty.lore"));
/*     */   }
/*     */ 
/*     */   
/*     */   public void registerIcons(IIconRegister iconRegister) {
/* 199 */     this.itemIcon = iconRegister.registerIcon("forgottenrelics:Telekinesis_Tome");
/*     */   }
/*     */ 
/*     */   
/*     */   public void onUpdate(ItemStack stack, World world, Entity par3Entity, int p_77663_4_, boolean p_77663_5_) {
/* 204 */     if (!(par3Entity instanceof EntityPlayer)) {
/*     */       return;
/*     */     }
/* 207 */     EntityPlayer player = (EntityPlayer)par3Entity;
/*     */     
/* 209 */     int ticksTillExpire = ((Integer)getTomeTag(player, "ticksTillExpire", Integer.valueOf(0))).intValue();
/* 210 */     int ticksCooldown = ((Integer)getTomeTag(player, "ticksCooldown", Integer.valueOf(0))).intValue();
/*     */     
/* 212 */     if (ticksTillExpire == 0) {
/* 213 */       setTomeTag(player, "target", Integer.valueOf(-1));
/* 214 */       setTomeTag(player, "dist", Double.valueOf(-1.0D));
/* 215 */       setTomeTag(player, "reDist", Double.valueOf(-1.0D));
/*     */     } 
/*     */     
/* 218 */     ticksTillExpire--;
/*     */     
/* 220 */     if (ticksCooldown > 0) {
/* 221 */       ticksCooldown--;
/*     */     }
/* 223 */     setTomeTag(player, "ticksTillExpire", Integer.valueOf(ticksTillExpire));
/* 224 */     setTomeTag(player, "ticksCooldown", Integer.valueOf(ticksCooldown));
/*     */     
/* 226 */     if (player.worldObj.isRemote) {
/* 227 */       this; if (!altMethods) {
/* 228 */         boolean attack = false;
/*     */         
/* 230 */         if ((Minecraft.getMinecraft()).currentScreen == null && (
/* 231 */           (Minecraft.getMinecraft()).gameSettings.keyBindUseItem.getIsKeyPressed() & ((player.getCurrentEquippedItem() == stack) ? 1 : 0)) != 0) {
/* 232 */           Main.packetInstance.sendToServer((IMessage)new TelekinesisUseMessage());
/* 233 */           onUsingTickAlt(stack, player, 0);
/* 234 */           attack = true;
/*     */         } 
/*     */         
/* 237 */         if ((attack & ((player.getCurrentEquippedItem() == stack) ? 1 : 0) & hasPressedAttackKey()) != 0) {
/* 238 */           Main.packetInstance.sendToServer((IMessage)new TelekinesisAttackMessage(true));
/*     */         }
/*     */       }
/* 241 */       else if ((((player.getItemInUse() == stack) ? 1 : 0) & hasPressedAttackKey()) != 0) {
/* 242 */         Main.packetInstance.sendToServer((IMessage)new TelekinesisAttackMessage(true));
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EnumAction getItemUseAction(ItemStack par1ItemStack) {
/* 251 */     return EnumAction.bow;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getMaxItemUseDuration(ItemStack par1ItemStack) {
/* 256 */     return 72000;
/*     */   }
/*     */ 
/*     */   
/*     */   public void onUsingTick(ItemStack stack, EntityPlayer player, int count) {
/* 261 */     onUsingTickAlt(stack, player, count);
/*     */   }
/*     */ 
/*     */   
/*     */   public void onUsingTickAlt(ItemStack stack, EntityPlayer player, int count) {
/* 266 */     World world = player.worldObj;
/*     */     
/* 268 */     int targetID = ((Integer)getTomeTag(player, "target", Integer.valueOf(-1))).intValue();
/* 269 */     int ticksCooldown = ((Integer)getTomeTag(player, "ticksCooldown", Integer.valueOf(0))).intValue();
/* 270 */     double length = ((Double)getTomeTag(player, "dist", Integer.valueOf(-1))).doubleValue();
/*     */     
/* 272 */     double re_dist = ((Double)getTomeTag(player, "reDist", Integer.valueOf(-1))).doubleValue();
/*     */     
/* 274 */     if (ticksCooldown == 0) {
/*     */       
/* 276 */       EntityLivingBase item = null;
/*     */       
/* 278 */       if (targetID != -1 && player.worldObj.getEntityByID(targetID) != null) {
/* 279 */         item = getExistingTarget(player, world, targetID, 6.0F);
/*     */       }
/*     */       
/* 282 */       if (item == null) {
/* 283 */         item = searchForTarget(player, world, 3.0F);
/*     */       }
/*     */       
/* 286 */       length = 7.5D;
/*     */       
/* 288 */       if ((((item != null) ? 1 : 0) & ((re_dist == -1.0D) ? 1 : 0)) != 0) {
/* 289 */         re_dist = player.getDistanceToEntity((Entity)item);
/*     */       }
/* 291 */       if (item != null) {
/*     */         
/* 293 */         this; this; if (WandManager.consumeVisFromInventory(player, (new AspectList()).add(Aspect.AIR, AerCost).add(Aspect.ORDER, OrdoCost))) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 300 */           EntityLivingBase targetEntity = item;
/* 301 */           targetEntity.fallDistance = 0.0F;
/* 302 */           if (targetEntity.getActivePotionEffect(Potion.moveSlowdown) == null) {
/* 303 */             targetEntity.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 2, 3, true));
/*     */           }
/*     */           
/* 306 */           Vector3 target3 = Vector3.fromEntityCenter((Entity)player);
/*     */           
/* 308 */           if (player.isSneaking()) {
/* 309 */             target3.add((new Vector3(player.getLookVec())).multiply(re_dist));
/*     */           } else {
/* 311 */             target3.add((new Vector3(player.getLookVec())).multiply(length));
/* 312 */             re_dist = player.getDistanceToEntity((Entity)item);
/*     */           } 
/*     */           
/* 315 */           target3.y += 0.5D;
/*     */           
/* 317 */           Vector3 entityCenter = Vector3.fromEntityCenter((Entity)item);
/*     */           
/* 319 */           if (!world.isRemote) {
/* 320 */             Main.packetInstance.sendToAllAround((IMessage)new TelekinesisParticleMessage(entityCenter.x, entityCenter.y, entityCenter.z, 1.0F), new NetworkRegistry.TargetPoint(item.dimension, item.posX, item.posY, item.posZ, 64.0D));
/*     */           }
/*     */           
/* 323 */           double multiplier = item.getDistance(target3.x, target3.y, target3.z);
/* 324 */           float vectorPower = 0.66666F;
/*     */           
/* 326 */           if (multiplier < 1.5D) {
/* 327 */             vectorPower = 0.333333F;
/* 328 */           } else if (multiplier >= 8.0D) {
/* 329 */             vectorPower *= (float)(multiplier / 8.0D);
/*     */           } 
/*     */           
/* 332 */           if (SuperpositionHandler.isEntityBlacklistedFromTelekinesis(item)) {
/*     */             return;
/*     */           }
/* 335 */           setEntityMotionFromVector((Entity)item, target3, vectorPower);
/*     */           
/* 337 */           setTomeTag(player, "target", Integer.valueOf(item.getEntityId()));
/* 338 */           setTomeTag(player, "dist", Double.valueOf(length));
/* 339 */           setTomeTag(player, "reDist", Double.valueOf(re_dist));
/*     */         } 
/*     */       } 
/*     */       
/* 343 */       if (item != null) {
/* 344 */         setTomeTag(player, "ticksTillExpire", Integer.valueOf(5));
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
/* 350 */     this; if (altMethods) {
/* 351 */       player.setItemInUse(stack, 72000);
/*     */     }
/*     */     
/* 354 */     return stack;
/*     */   }
/*     */   
/*     */   public void leftClick(EntityPlayer player) {
/* 358 */     ItemStack stack = player.getHeldItem();
/* 359 */     if (stack != null && stack.getItem() == Main.itemTelekinesisTome) {
/* 360 */       int targetID = ((Integer)getTomeTag(player, "target", Integer.valueOf(-1))).intValue();
/*     */       
/* 362 */       EntityLivingBase item = null;
/*     */       
/* 364 */       if (targetID != -1 && player.worldObj.getEntityByID(targetID) != null) {
/* 365 */         item = getExistingTarget(player, player.worldObj, targetID, 6.0F);
/*     */         
/* 367 */         if (item != null) {
/* 368 */           lightningAttack(player, item, stack, player.worldObj);
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
/*     */   public static void setEntityMotionFromVector(Entity entity, Vector3 originalPosVector, float modifier) {
/* 381 */     Vector3 entityVector = Vector3.fromEntityCenter(entity);
/* 382 */     Vector3 finalVector = originalPosVector.copy().subtract(entityVector);
/*     */     
/* 384 */     if (finalVector.mag() > 1.0D) {
/* 385 */       finalVector.normalize();
/*     */     }
/* 387 */     entity.motionX = finalVector.x * modifier;
/* 388 */     entity.motionY = finalVector.y * modifier;
/* 389 */     entity.motionZ = finalVector.z * modifier;
/*     */ 
/*     */     
/* 392 */     if ((entity instanceof EntityPlayer & (!entity.worldObj.isRemote ? 1 : 0)) != 0) {
/* 393 */       Main.packetInstance.sendTo((IMessage)new PlayerMotionUpdateMessage(finalVector.x * modifier, finalVector.y * modifier, finalVector.z * modifier), (EntityPlayerMP)entity);
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
/*     */   public EntityLivingBase searchForTarget(EntityPlayer player, World world, float range) {
/* 407 */     EntityLivingBase newTarget = null;
/* 408 */     Vector3 target = Vector3.fromEntityCenter((Entity)player);
/* 409 */     List<EntityLivingBase> entities = new ArrayList<>();
/* 410 */     int distance = 1;
/*     */     
/* 412 */     while (entities.size() == 0 && distance < 32) {
/* 413 */       target.add((new Vector3(player.getLookVec())).multiply(distance));
/*     */       
/* 415 */       target.y += 0.5D;
/* 416 */       entities = player.worldObj.getEntitiesWithinAABB(EntityLivingBase.class, AxisAlignedBB.getBoundingBox(target.x - range, target.y - range, target.z - range, target.x + range, target.y + range, target.z + range));
/*     */       
/* 418 */       if (entities.contains(player)) {
/* 419 */         entities.remove(player);
/*     */       }
/* 421 */       distance++;
/*     */     } 
/*     */     
/* 424 */     if (entities.size() > 0) {
/* 425 */       newTarget = entities.get(0);
/*     */     }
/*     */     
/* 428 */     return newTarget;
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
/*     */   public EntityLivingBase getExistingTarget(EntityPlayer player, World world, int targetID, float range) {
/* 444 */     EntityLivingBase taritem = (EntityLivingBase)world.getEntityByID(targetID);
/* 445 */     Vector3 target = Vector3.fromEntityCenter((Entity)player);
/* 446 */     List<EntityLivingBase> entities = new ArrayList<>();
/* 447 */     int distance = 1;
/*     */     
/* 449 */     while (distance < 32) {
/* 450 */       target.add((new Vector3(player.getLookVec())).multiply(distance));
/*     */       
/* 452 */       target.y += 0.5D;
/* 453 */       entities = world.getEntitiesWithinAABB(EntityLivingBase.class, AxisAlignedBB.getBoundingBox(target.x - range, target.y - range, target.z - range, target.x + range, target.y + range, target.z + range));
/* 454 */       distance++;
/*     */       
/* 456 */       if (entities.contains(player)) {
/* 457 */         entities.remove(player);
/*     */       }
/* 459 */       if (entities.contains(taritem)) {
/* 460 */         return taritem;
/*     */       }
/*     */     } 
/* 463 */     return null;
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
/*     */   public void lightningAttack(EntityPlayer player, EntityLivingBase target, ItemStack stack, World world) {
/* 476 */     if (world.isRemote || SuperpositionHandler.isOnCoodown(player)) {
/*     */       return;
/*     */     }
/* 479 */     Vector3 TVec = Vector3.fromEntityCenter((Entity)target);
/* 480 */     Vector3 moveVector = new Vector3(player.getLookVec().normalize());
/*     */     
/* 482 */     if (player.getDistanceToEntity((Entity)target) <= 16.0F && 
/* 483 */       WandManager.consumeVisFromInventory(player, (new AspectList()).add(Aspect.AIR, (int)(80.0F * RelicsConfigHandler.telekinesisTomeVisMult)).add(Aspect.ORDER, (int)(50.0F * RelicsConfigHandler.telekinesisTomeVisMult)).add(Aspect.FIRE, (int)(200.0F * RelicsConfigHandler.telekinesisTomeVisMult)))) {
/*     */       
/* 485 */       for (int counter = 0; counter <= 3; counter++)
/* 486 */         SuperpositionHandler.imposeLightning(player.worldObj, player.dimension, player.posX, player.posY + 1.0D, player.posZ, TVec.x, TVec.y, TVec.z, 20, (float)((1.0F / player.getDistanceToEntity((Entity)target)) * (2.0D + Math.random() * 4.0D)), (int)(player.getDistanceToEntity((Entity)target) * 1.2F), 0, (float)(0.22499999403953552D + player.getDistanceSqToEntity((Entity)target) / 2000.0D)); 
/* 487 */       player.worldObj.playSoundAtEntity((Entity)player, "thaumcraft:zap", 1.0F, 0.8F);
/*     */       
/* 489 */       target.attackEntityFrom((DamageSource)new DamageRegistryHandler.DamageSourceTLightning((Entity)player), (float)(16.0D + 24.0D * Math.random()) * 1.0F);
/*     */     } 
/*     */ 
/*     */     
/* 493 */     if (player.isSneaking() && (
/* 494 */       WandManager.consumeVisFromInventory(player, (new AspectList()).add(Aspect.AIR, (int)(150.0F * RelicsConfigHandler.telekinesisTomeVisMult)).add(Aspect.ORDER, (int)(80.0F * RelicsConfigHandler.telekinesisTomeVisMult))) & (!player.worldObj.isRemote ? 1 : 0)) != 0) {
/* 495 */       target.motionX = moveVector.x * 3.0D;
/* 496 */       target.motionY = moveVector.y * 1.5D;
/* 497 */       target.motionZ = moveVector.z * 3.0D;
/*     */       
/* 499 */       setTomeTag(player, "target", Integer.valueOf(-1));
/* 500 */       setTomeTag(player, "dist", Double.valueOf(-1.0D));
/* 501 */       setTomeTag(player, "reDist", Double.valueOf(-1.0D));
/* 502 */       setTomeTag(player, "ticksTillExpire", Integer.valueOf(0));
/* 503 */       setTomeTag(player, "ticksCooldown", Integer.valueOf(40));
/*     */     } 
/*     */     
/* 506 */     SuperpositionHandler.setCasted(player, 10, true);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getWarp(ItemStack arg0, EntityPlayer arg1) {
/* 512 */     return 4;
/*     */   }
/*     */ }


/* Location:              C:\Users\antro\Documents\forgottenRelicsDeobfuscated.jar!\com\integral\forgottenrelics\items\ItemTelekinesisTome.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */