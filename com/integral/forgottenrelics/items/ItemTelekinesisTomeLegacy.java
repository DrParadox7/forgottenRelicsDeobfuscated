/*     */ package com.integral.forgottenrelics.items;
/*     */ 
/*     */ import com.integral.forgottenrelics.Main;
/*     */ import com.integral.forgottenrelics.handlers.DamageRegistryHandler;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import net.minecraft.client.gui.GuiScreen;
/*     */ import net.minecraft.client.renderer.texture.IIconRegister;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.item.EntityItem;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.EntityPlayerMP;
/*     */ import net.minecraft.inventory.Container;
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
/*     */ import vazkii.botania.common.Botania;
/*     */ import vazkii.botania.common.core.helper.ItemNBTHelper;
/*     */ import vazkii.botania.common.core.helper.Vector3;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ItemTelekinesisTomeLegacy
/*     */   extends Item
/*     */   implements IWarpingGear
/*     */ {
/*     */   public static final int AerCost = 6;
/*     */   public static final int TerraCost = 0;
/*     */   public static final int IgnisCost = 200;
/*     */   public static final int AquaCost = 0;
/*     */   public static final int OrdoCost = 6;
/*     */   public static final int PerditioCost = 2;
/*     */   private static final float RANGE = 3.0F;
/*     */   private static final int COST = 2;
/*     */   private static final String TAG_TICKS_TILL_EXPIRE = "ticksTillExpire";
/*     */   private static final String TAG_TICKS_COOLDOWN = "ticksCooldown";
/*     */   private static final String TAG_ATTACK_COOLDOWN = "attackCooldown";
/*     */   private static final String TAG_TARGET = "target";
/*     */   private static final String TAG_DIST = "dist";
/*     */   private static final String TAG_RE_DIST = "reDist";
/*     */   
/*     */   public ItemTelekinesisTomeLegacy() {
/*  60 */     this.maxStackSize = 1;
/*  61 */     setUnlocalizedName("ItemTelekinesisTome");
/*  62 */     setCreativeTab(Main.tabForgottenRelics);
/*     */   }
/*     */ 
/*     */   
/*     */   public void registerIcons(IIconRegister iconRegister) {
/*  67 */     this.itemIcon = iconRegister.registerIcon("forgottenrelics:Telekinesis_Tome");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public EnumRarity getRarity(ItemStack itemStack) {
/*  73 */     return EnumRarity.epic;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List<String> par3List, boolean par4) {
/*  80 */     if (GuiScreen.isShiftKeyDown()) {
/*  81 */       par3List.add(StatCollector.translateToLocal("item.ItemTelekinesisTome1.lore"));
/*  82 */       par3List.add(StatCollector.translateToLocal("item.ItemTelekinesisTome2.lore"));
/*  83 */       par3List.add(StatCollector.translateToLocal("item.ItemTelekinesisTome3.lore"));
/*  84 */       par3List.add(StatCollector.translateToLocal("item.FREmpty.lore"));
/*  85 */       par3List.add(StatCollector.translateToLocal("item.ItemTelekinesisTome4.lore"));
/*  86 */       par3List.add(StatCollector.translateToLocal("item.ItemTelekinesisTome5.lore"));
/*  87 */       par3List.add(StatCollector.translateToLocal("item.ItemTelekinesisTome6.lore"));
/*  88 */       par3List.add(StatCollector.translateToLocal("item.FREmpty.lore"));
/*  89 */       par3List.add(StatCollector.translateToLocal("item.ItemTelekinesisTome7.lore"));
/*  90 */       par3List.add(StatCollector.translateToLocal("item.ItemTelekinesisTome8.lore"));
/*  91 */       par3List.add(StatCollector.translateToLocal("item.ItemTelekinesisTome9.lore"));
/*  92 */       par3List.add(StatCollector.translateToLocal("item.FREmpty.lore"));
/*  93 */       par3List.add(StatCollector.translateToLocal("item.ItemTelekinesisTome10.lore"));
/*  94 */       par3List.add(StatCollector.translateToLocal("item.FREmpty.lore"));
/*  95 */       par3List.add(StatCollector.translateToLocal("item.ItemTelekinesisTome11.lore"));
/*  96 */     } else if (GuiScreen.isCtrlKeyDown()) {
/*  97 */       par3List.add(StatCollector.translateToLocal("item.FRVisPerTick.lore"));
/*  98 */       this; par3List.add(" " + StatCollector.translateToLocal("item.FRAerCost.lore") + (6.0D / 100.0D));
/*  99 */       this; par3List.add(" " + StatCollector.translateToLocal("item.FRPerditioCost.lore") + (6.0D / 100.0D));
/* 100 */       par3List.add(StatCollector.translateToLocal("item.FREmpty.lore"));
/* 101 */       par3List.add(StatCollector.translateToLocal("item.FRVisPerCast.lore"));
/* 102 */       par3List.add(" " + StatCollector.translateToLocal("item.FRAerCost.lore") + 0.8D);
/* 103 */       par3List.add(" " + StatCollector.translateToLocal("item.FROrdoCost.lore") + 0.5D);
/* 104 */       par3List.add(" " + StatCollector.translateToLocal("item.FRIgnisCost.lore") + 2.0D);
/*     */     } else {
/*     */       
/* 107 */       par3List.add(StatCollector.translateToLocal("item.FRViscostTooltip.lore"));
/* 108 */       par3List.add(StatCollector.translateToLocal("item.FRShiftTooltip.lore"));
/*     */     } 
/*     */     
/* 111 */     par3List.add(StatCollector.translateToLocal("item.FREmpty.lore"));
/*     */   }
/*     */ 
/*     */   
/*     */   public void onUpdate(ItemStack stack, World world, Entity par3Entity, int p_77663_4_, boolean p_77663_5_) {
/* 116 */     if (!(par3Entity instanceof EntityPlayer)) {
/*     */       return;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 124 */     int ticksTillExpire = ItemNBTHelper.getInt(stack, "ticksTillExpire", 0);
/* 125 */     int ticksCooldown = ItemNBTHelper.getInt(stack, "ticksCooldown", 0);
/* 126 */     int attackCooldown = ItemNBTHelper.getInt(stack, "attackCooldown", 0);
/*     */     
/* 128 */     if (ticksTillExpire == 0) {
/* 129 */       ItemNBTHelper.setInt(stack, "target", -1);
/* 130 */       ItemNBTHelper.setDouble(stack, "dist", -1.0D);
/* 131 */       ItemNBTHelper.setDouble(stack, "reDist", -1.0D);
/*     */     } 
/*     */     
/* 134 */     if (attackCooldown > 0) {
/* 135 */       attackCooldown--;
/*     */     }
/* 137 */     if (ticksCooldown > 0) {
/* 138 */       ticksCooldown--;
/*     */     }
/* 140 */     ticksTillExpire--;
/* 141 */     ItemNBTHelper.setInt(stack, "ticksTillExpire", ticksTillExpire);
/* 142 */     ItemNBTHelper.setInt(stack, "ticksCooldown", ticksCooldown);
/* 143 */     ItemNBTHelper.setInt(stack, "attackCooldown", attackCooldown);
/*     */     
/* 145 */     EntityPlayer player = (EntityPlayer)par3Entity;
/* 146 */     PotionEffect haste = player.getActivePotionEffect(Potion.digSpeed);
/* 147 */     float check = (haste == null) ? 0.16666667F : ((haste.getAmplifier() == 1) ? 0.5F : 0.4F);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean onEntitySwing(EntityLivingBase entityLiving, ItemStack stack) {
/* 158 */     if (entityLiving instanceof EntityPlayer) {
/* 159 */       leftClick((EntityPlayer)entityLiving);
/*     */     }
/*     */     
/* 162 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
/* 169 */     int targetID = ItemNBTHelper.getInt(stack, "target", -1);
/* 170 */     int ticksCooldown = ItemNBTHelper.getInt(stack, "ticksCooldown", 0);
/* 171 */     double length = ItemNBTHelper.getDouble(stack, "dist", -1.0D);
/* 172 */     double re_dist = ItemNBTHelper.getDouble(stack, "reDist", -1.0D);
/*     */     
/* 174 */     if (ticksCooldown == 0) {
/* 175 */       Entity item = null;
/* 176 */       if (targetID != -1 && player.worldObj.getEntityByID(targetID) != null) {
/* 177 */         Entity taritem = player.worldObj.getEntityByID(targetID);
/*     */         
/* 179 */         boolean found = false;
/* 180 */         Vector3 target = Vector3.fromEntityCenter((Entity)player);
/* 181 */         List<Entity> entities = new ArrayList<>();
/* 182 */         int distance = 1;
/* 183 */         while (entities.size() == 0 && distance < 32) {
/* 184 */           target.add((new Vector3(player.getLookVec())).multiply(distance));
/*     */           
/* 186 */           target.y += 0.5D;
/* 187 */           entities = player.worldObj.getEntitiesWithinAABBExcludingEntity((Entity)player, AxisAlignedBB.getBoundingBox(target.x - 3.0D, target.y - 3.0D, target.z - 3.0D, target.x + 3.0D, target.y + 3.0D, target.z + 3.0D));
/* 188 */           distance++;
/* 189 */           if (entities.contains(taritem)) {
/* 190 */             found = true;
/*     */           }
/*     */         } 
/* 193 */         if (found) {
/* 194 */           item = player.worldObj.getEntityByID(targetID);
/*     */         }
/*     */       } 
/* 197 */       if (item == null) {
/* 198 */         Vector3 target = Vector3.fromEntityCenter((Entity)player);
/* 199 */         List<Entity> entities = new ArrayList<>();
/* 200 */         int distance = 1;
/* 201 */         while (entities.size() == 0 && distance < 32) {
/* 202 */           target.add((new Vector3(player.getLookVec())).multiply(distance));
/*     */           
/* 204 */           target.y += 0.5D;
/* 205 */           entities = player.worldObj.getEntitiesWithinAABBExcludingEntity((Entity)player, AxisAlignedBB.getBoundingBox(target.x - 3.0D, target.y - 3.0D, target.z - 3.0D, target.x + 3.0D, target.y + 3.0D, target.z + 3.0D));
/* 206 */           distance++;
/*     */           
/* 208 */           if (entities.size() > 0) {
/* 209 */             for (int counter = 0; counter <= entities.size() - 1; counter++) {
/* 210 */               Entity itemS = entities.get(counter);
/* 211 */               if (itemS instanceof net.minecraft.entity.item.EntityXPOrb || itemS instanceof thaumcraft.common.entities.EntityAspectOrb || itemS instanceof thaumcraft.common.entities.monster.boss.EntityThaumcraftBoss || itemS instanceof vazkii.botania.common.entity.EntityDoppleganger) {
/* 212 */                 entities.remove(itemS);
/* 213 */                 counter = -1;
/*     */               } 
/*     */             } 
/*     */           }
/* 217 */           if (entities.size() > 0) {
/* 218 */             item = entities.get(0);
/*     */           }
/*     */         } 
/*     */         
/* 222 */         if (item != null) {
/* 223 */           re_dist = player.getDistanceToEntity(item);
/*     */         }
/* 225 */         length = 7.5D;
/*     */         
/* 227 */         if (item instanceof EntityItem) {
/* 228 */           length = 2.0D;
/*     */         }
/*     */       } 
/*     */       
/* 232 */       if (item != null)
/*     */       {
/* 234 */         if (WandManager.consumeVisFromInventory(player, (new AspectList()).add(Aspect.AIR, 6).add(Aspect.ORDER, 6))) {
/* 235 */           if (item instanceof EntityItem) {
/* 236 */             ((EntityItem)item).delayBeforeCanPickup = 5;
/*     */           }
/* 238 */           if (!world.isRemote) {
/* 239 */             Container inventory = player.inventoryContainer;
/* 240 */             ((EntityPlayerMP)player).sendContainerToPlayer(inventory);
/*     */           } 
/*     */           
/* 243 */           if (item instanceof EntityLivingBase) {
/* 244 */             EntityLivingBase targetEntity = (EntityLivingBase)item;
/* 245 */             targetEntity.fallDistance = 0.0F;
/* 246 */             if (targetEntity.getActivePotionEffect(Potion.moveSlowdown) == null) {
/* 247 */               targetEntity.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 2, 3, true));
/*     */             }
/*     */           } 
/* 250 */           Vector3 target3 = Vector3.fromEntityCenter((Entity)player);
/* 251 */           if (player.isSneaking()) {
/* 252 */             target3.add((new Vector3(player.getLookVec())).multiply(re_dist));
/*     */           } else {
/* 254 */             target3.add((new Vector3(player.getLookVec())).multiply(length));
/* 255 */             re_dist = player.getDistanceToEntity(item);
/*     */           } 
/*     */           
/* 258 */           target3.y += 0.5D;
/* 259 */           if (item instanceof EntityItem) {
/* 260 */             target3.y += 0.25D;
/*     */           }
/* 262 */           for (int i = 0; i < 4; i++) {
/* 263 */             float r = 0.2F + (float)Math.random() * 0.3F;
/* 264 */             float g = 0.0F;
/* 265 */             float b = 0.5F + (float)Math.random() * 0.2F;
/* 266 */             float s = 0.2F + (float)Math.random() * 0.1F;
/* 267 */             float m = 0.15F;
/* 268 */             float xm = ((float)Math.random() - 0.5F) * m;
/* 269 */             float ym = ((float)Math.random() - 0.5F) * m;
/* 270 */             float zm = ((float)Math.random() - 0.5F) * m;
/*     */ 
/*     */             
/* 273 */             Botania.proxy.wispFX(world, item.posX + (item.width / 2.0F), item.posY + (item.height / 2.0F), item.posZ + (item.width / 2.0F), r, g, b, s, xm, ym, zm);
/*     */           } 
/*     */           
/* 276 */           for (int counter = 0; counter <= 8; counter++) {
/* 277 */             Main.proxy.spawnSuperParticle(world, "portalstuff", item.posX, item.posY, item.posZ, (Math.random() - 0.5D) * 3.0D, (Math.random() - 0.5D) * 3.0D, (Math.random() - 0.5D) * 3.0D, 1.0F, 64.0D);
/*     */           }
/* 279 */           double multiplier = item.getDistance(target3.x, target3.y, target3.z);
/* 280 */           float vectorPower = 0.66666F;
/*     */           
/* 282 */           if (multiplier < 1.0D) {
/* 283 */             vectorPower = 0.333333F;
/* 284 */           } else if (multiplier >= 8.0D) {
/* 285 */             vectorPower *= (float)(multiplier / 8.0D);
/*     */           } 
/*     */           
/* 288 */           setEntityMotionFromVector(item, target3, vectorPower);
/*     */           
/* 290 */           ItemNBTHelper.setInt(stack, "target", item.getEntityId());
/* 291 */           ItemNBTHelper.setDouble(stack, "dist", length);
/* 292 */           ItemNBTHelper.setDouble(stack, "reDist", re_dist);
/*     */         } 
/*     */       }
/*     */       
/* 296 */       if (item != null)
/* 297 */         ItemNBTHelper.setInt(stack, "ticksTillExpire", 5); 
/*     */     } 
/* 299 */     return stack;
/*     */   }
/*     */   
/*     */   public static void setEntityMotionFromVector(Entity entity, Vector3 originalPosVector, float modifier) {
/* 303 */     Vector3 entityVector = Vector3.fromEntityCenter(entity);
/* 304 */     Vector3 finalVector = originalPosVector.copy().subtract(entityVector);
/*     */     
/* 306 */     if (finalVector.mag() > 1.0D) {
/* 307 */       finalVector.normalize();
/*     */     }
/* 309 */     entity.motionX = finalVector.x * modifier;
/* 310 */     entity.motionY = finalVector.y * modifier;
/* 311 */     entity.motionZ = finalVector.z * modifier;
/*     */   }
/*     */   
/*     */   public static void leftClick(EntityPlayer player) {
/* 315 */     ItemStack stack = player.getHeldItem();
/* 316 */     if (stack != null && stack.getItem() == Main.itemTelekinesisTome) {
/* 317 */       int attackCooldown = ItemNBTHelper.getInt(stack, "attackCooldown", 0);
/* 318 */       int targetID = ItemNBTHelper.getInt(stack, "target", -1);
/* 319 */       ItemNBTHelper.getDouble(stack, "dist", -1.0D);
/* 320 */       ItemNBTHelper.getDouble(stack, "reDist", -1.0D);
/* 321 */       Entity item = null;
/*     */       
/* 323 */       if (targetID != -1 && player.worldObj.getEntityByID(targetID) != null) {
/* 324 */         Entity taritem = player.worldObj.getEntityByID(targetID);
/*     */         
/* 326 */         boolean found = false;
/* 327 */         Vector3 target = Vector3.fromEntityCenter((Entity)player);
/* 328 */         List<Entity> entities = new ArrayList<>();
/* 329 */         int distance = 1;
/* 330 */         while (entities.size() == 0 && distance < 32) {
/* 331 */           target.add((new Vector3(player.getLookVec())).multiply(distance));
/*     */           
/* 333 */           target.y += 0.5D;
/* 334 */           entities = player.worldObj.getEntitiesWithinAABBExcludingEntity((Entity)player, AxisAlignedBB.getBoundingBox(target.x - 3.0D, target.y - 3.0D, target.z - 3.0D, target.x + 3.0D, target.y + 3.0D, target.z + 3.0D));
/* 335 */           distance++;
/* 336 */           if (entities.contains(taritem)) {
/* 337 */             found = true;
/*     */           }
/*     */         } 
/* 340 */         if (found) {
/* 341 */           item = taritem;
/* 342 */           Vector3 moveVector = new Vector3(player.getLookVec().normalize());
/* 343 */           if (item instanceof EntityItem) {
/* 344 */             lightningAttack(player, item, stack, player.worldObj);
/* 345 */             item.setDead();
/*     */             return;
/*     */           } 
/* 348 */           if ((((player.getDistanceToEntity(item) <= 16.0F) ? 1 : 0) & ((attackCooldown == 0) ? 1 : 0)) != 0) {
/* 349 */             lightningAttack(player, item, stack, player.worldObj);
/*     */           }
/*     */           return;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static void lightningAttack(EntityPlayer player, Entity target, ItemStack stack, World world) {
/* 359 */     if (world.isRemote) {
/*     */       return;
/*     */     }
/* 362 */     Vector3 TVec = Vector3.fromEntityCenter(target);
/* 363 */     Vector3 moveVector = new Vector3(player.getLookVec().normalize());
/*     */     
/* 365 */     if (WandManager.consumeVisFromInventory(player, (new AspectList()).add(Aspect.AIR, 80).add(Aspect.ORDER, 50).add(Aspect.FIRE, 200))) {
/*     */       
/* 367 */       for (int counter = 0; counter <= 3; counter++)
/* 368 */         Main.proxy.lightning(player.worldObj, player.posX, player.posY + 1.0D, player.posZ, TVec.x, TVec.y, TVec.z, 20, (float)((1.0F / player.getDistanceToEntity(target)) * Math.random() * 4.0D), (int)(player.getDistanceToEntity(target) * 1.2F), 0, 0.175F); 
/* 369 */       player.worldObj.playSoundAtEntity((Entity)player, "thaumcraft:zap", 1.0F, 0.8F);
/*     */       
/* 371 */       target.attackEntityFrom((DamageSource)new DamageRegistryHandler.DamageSourceTLightning((Entity)player), (float)(16.0D + 24.0D * Math.random()));
/*     */     } 
/*     */ 
/*     */     
/* 375 */     if (player.isSneaking() && (
/* 376 */       WandManager.consumeVisFromInventory(player, (new AspectList()).add(Aspect.AIR, 150).add(Aspect.ORDER, 80)) & (!player.worldObj.isRemote ? 1 : 0)) != 0) {
/* 377 */       target.motionX = moveVector.x * 3.0D;
/* 378 */       target.motionY = moveVector.y * 1.5D;
/* 379 */       target.motionZ = moveVector.z * 3.0D;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getWarp(ItemStack arg0, EntityPlayer arg1) {
/* 387 */     return 4;
/*     */   }
/*     */ }


/* Location:              C:\Users\antro\Documents\forgottenRelicsDeobfuscated.jar!\com\integral\forgottenrelics\items\ItemTelekinesisTomeLegacy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */