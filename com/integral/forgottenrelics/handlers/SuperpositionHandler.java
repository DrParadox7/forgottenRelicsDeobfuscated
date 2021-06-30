/*     */ package com.integral.forgottenrelics.handlers;
/*     */ 
/*     */ import baubles.api.BaubleType;
/*     */ import baubles.common.container.InventoryBaubles;
/*     */ import baubles.common.lib.PlayerHandler;
/*     */ import com.integral.forgottenrelics.Main;
/*     */ import com.integral.forgottenrelics.packets.ArcLightningMessage;
/*     */ import com.integral.forgottenrelics.packets.BurstMessage;
/*     */ import com.integral.forgottenrelics.packets.ICanSwingMySwordMessage;
/*     */ import com.integral.forgottenrelics.packets.LightningMessage;
/*     */ import com.integral.forgottenrelics.packets.NotificationMessage;
/*     */ import cpw.mods.fml.common.network.NetworkRegistry;
/*     */ import cpw.mods.fml.common.network.simpleimpl.IMessage;
/*     */ import java.lang.reflect.Field;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.LinkedList;
/*     */ import java.util.List;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLiving;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.monster.IMob;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.EntityPlayerMP;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.server.MinecraftServer;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.MovingObjectPosition;
/*     */ import net.minecraft.util.StatCollector;
/*     */ import net.minecraft.util.Vec3;
/*     */ import net.minecraft.world.World;
/*     */ import thaumcraft.api.ThaumcraftApi;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.api.aspects.AspectList;
/*     */ import thaumcraft.api.research.ResearchItem;
/*     */ import thaumcraft.common.items.wands.ItemWandCasting;
/*     */ import thaumcraft.common.items.wands.WandManager;
/*     */ import vazkii.botania.common.block.subtile.functional.SubTileHeiseiDream;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SuperpositionHandler
/*     */ {
/*  50 */   public static JusticeHandler justiceHandler = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void bringTheJustice(EntityPlayer player) {
/*  60 */     if (player.worldObj.isRemote) {
/*     */       return;
/*     */     }
/*     */     
/*     */     try {
/*  65 */       if (!justiceHandler.isAlive()) {
/*  66 */         justiceHandler = new JusticeHandler(player);
/*  67 */         justiceHandler.start();
/*     */       }
/*     */     
/*  70 */     } catch (NullPointerException ex) {
/*  71 */       justiceHandler = new JusticeHandler(player);
/*  72 */       justiceHandler.start();
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
/*     */ 
/*     */ 
/*     */   
/*     */   public static void setupResearchTriggers(String researchKey, ItemStack... stacks) {
/*  92 */     List<ItemStack> stackList = new ArrayList<>(Arrays.asList(stacks));
/*  93 */     Main.forgottenKnowledge.put(researchKey, stackList);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void sendNotification(EntityPlayer player, int type) {
/* 103 */     if (!player.worldObj.isRemote)
/* 104 */       Main.packetInstance.sendTo((IMessage)new NotificationMessage(type), (EntityPlayerMP)player); 
/*     */   }
/*     */   
/*     */   public static void setResearchUnhidden(ResearchItem research) {
/*     */     try {
/* 109 */       Field target = Class.forName("thaumcraft.api.research.ResearchItem").getDeclaredField("isHidden");
/*     */       
/* 111 */       target.setAccessible(true);
/* 112 */       target.setBoolean(research, false);
/*     */     }
/* 114 */     catch (Exception exception) {}
/*     */   }
/*     */   
/*     */   public static void setResearchUnlost(ResearchItem research) {
/*     */     try {
/* 119 */       Field target = Class.forName("thaumcraft.api.research.ResearchItem").getDeclaredField("isLost");
/*     */       
/* 121 */       target.setAccessible(true);
/* 122 */       target.setBoolean(research, false);
/*     */     }
/* 124 */     catch (Exception exception) {}
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
/*     */   public static void setupOverrides() {
/* 136 */     for (String s : RelicsConfigHandler.forgottenKnowledgeOverrides) {
/* 137 */       List<ItemStack> stackList = new ArrayList<>();
/* 138 */       s = s.replaceAll("\\s", "");
/*     */       
/* 140 */       String[] splat = s.split("\\[");
/* 141 */       String researchKey = splat[0];
/* 142 */       String unformattedItemList = splat[1];
/*     */       
/* 144 */       unformattedItemList = unformattedItemList.replaceAll("\\]", "");
/*     */       
/* 146 */       String[] itemList = unformattedItemList.split(",");
/*     */       
/* 148 */       for (String item : itemList) {
/* 149 */         String[] params = item.split(":");
/*     */         
/* 151 */         String modid = params[0];
/* 152 */         String itemname = params[1];
/* 153 */         int meta = Integer.parseInt(params[2]);
/*     */         
/* 155 */         Object uncheckedItem = Item.itemRegistry.getObject(modid + ":" + itemname);
/*     */         
/* 157 */         if (!(uncheckedItem instanceof Item)) {
/* 158 */           NullPointerException ex = new NullPointerException("Setting up the Justice Handler overrides has failed. You may have specified invalid items or messed up with formatting.");
/* 159 */           throw ex;
/* 160 */         }  if (researchKey == "Apotheosis") {
/* 161 */           NullPointerException ex = new NullPointerException("Apotheosis research can't have it's trigger overrided.");
/* 162 */           throw ex;
/*     */         } 
/*     */         
/* 165 */         Item theItem = (Item)uncheckedItem;
/* 166 */         ItemStack theStack = new ItemStack(theItem, 1, meta);
/*     */         
/* 168 */         stackList.add(theStack);
/*     */       } 
/*     */       
/* 171 */       Main.forgottenKnowledge.put(researchKey, stackList);
/* 172 */       Main.log.info("Set trigger overrides for research " + researchKey + ": " + stackList);
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
/*     */   public static void cryHavoc(World world, EntityPlayer player, int RANGE) {
/* 184 */     List<IMob> mobs = world.getEntitiesWithinAABB(IMob.class, AxisAlignedBB.getBoundingBox(player.posX - RANGE, player.posY - RANGE, player.posZ - RANGE, player.posX + RANGE + 1.0D, player.posY + RANGE + 1.0D, player.posZ + RANGE + 1.0D));
/* 185 */     if (mobs.size() > 1) {
/* 186 */       for (IMob mob : mobs) {
/* 187 */         if (mob instanceof EntityLiving) {
/* 188 */           EntityLiving entity1 = (EntityLiving)mob;
/* 189 */           if (SubTileHeiseiDream.brainwashEntity(entity1, mobs)) {
/*     */             break;
/*     */           }
/*     */         } 
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   public static void imposeArcLightning(World world, int dimension, double x, double y, double z, double destX, double destY, double destZ, float r, float g, float b, float h) {
/* 198 */     if (!world.isRemote)
/* 199 */       Main.packetInstance.sendToAllAround((IMessage)new ArcLightningMessage(x, y, z, destX, destY, destZ, r, g, b, h), new NetworkRegistry.TargetPoint(dimension, x, y, z, 128.0D)); 
/*     */   }
/*     */   
/*     */   public static void imposeLightning(World world, int dimension, double x, double y, double z, double destX, double destY, double destZ, int duration, float curve, int speed, int type, float width) {
/* 203 */     if (!world.isRemote)
/* 204 */       Main.packetInstance.sendToAllAround((IMessage)new LightningMessage(x, y, z, destX, destY, destZ, duration, curve, speed, type, width), new NetworkRegistry.TargetPoint(dimension, x, y, z, 128.0D)); 
/*     */   }
/*     */   
/*     */   public static void imposeBurst(World world, int dimension, double x, double y, double z, float size) {
/* 208 */     if (!world.isRemote) {
/* 209 */       Main.packetInstance.sendToAllAround((IMessage)new BurstMessage(x, y, z, size), new NetworkRegistry.TargetPoint(dimension, x, y, z, 128.0D));
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
/*     */   public static boolean validatePosition(World world, int x, int y, int z) {
/* 221 */     if (((!world.isAirBlock(x, y - 1, z) ? 1 : 0) & world.getBlock(x, y - 1, z).isCollidable() & world.isAirBlock(x, y, z) & world.isAirBlock(x, y + 1, z)) != 0) {
/* 222 */       return true;
/*     */     }
/* 224 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean isOnCoodown(EntityPlayer player) {
/*     */     int cooldown;
/* 234 */     if (player.worldObj.isRemote) {
/* 235 */       return false;
/*     */     }
/*     */ 
/*     */     
/*     */     try {
/* 240 */       cooldown = ((Integer)Main.castingCooldowns.get(player)).intValue();
/*     */     }
/* 242 */     catch (NullPointerException ex) {
/* 243 */       Main.castingCooldowns.put(player, Integer.valueOf(0));
/* 244 */       cooldown = 0;
/*     */     } 
/*     */     
/* 247 */     if (cooldown != 0) {
/* 248 */       return true;
/*     */     }
/* 250 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void setCasted(EntityPlayer player, int cooldown, boolean swing) {
/* 259 */     if (!player.worldObj.isRemote) {
/* 260 */       Main.castingCooldowns.put(player, Integer.valueOf(cooldown));
/*     */       
/* 262 */       if (swing) {
/* 263 */         player.swingItem();
/* 264 */         Main.packetInstance.sendTo((IMessage)new ICanSwingMySwordMessage(true), (EntityPlayerMP)player);
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
/*     */   public static void setItemAspectsForMetaRange(ItemStack stack, AspectList list, int metaLimit, int startCount) {
/* 278 */     for (int counter = startCount; counter <= metaLimit; counter++) {
/* 279 */       stack.setItemDamage(counter);
/* 280 */       ThaumcraftApi.registerObjectTag(stack, list);
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
/*     */   public static boolean isDamageTypeAbsolute(DamageSource source) {
/* 296 */     if (source == DamageSource.outOfWorld || source == DamageSource.starve || source instanceof DamageRegistryHandler.DamageSourceFate || source instanceof DamageRegistryHandler.DamageSourceOblivion || source instanceof DamageRegistryHandler.DamageSourceSoulDrain || source instanceof DamageRegistryHandler.DamageSourceTrueDamage || source instanceof DamageRegistryHandler.DamageSourceTrueDamageUndef) {
/* 297 */       return true;
/*     */     }
/* 299 */     return false;
/*     */   }
/*     */   
/*     */   public static boolean isEntityBlacklistedFromTelekinesis(EntityLivingBase entity) {
/* 303 */     if (entity instanceof thaumcraft.common.entities.monster.boss.EntityThaumcraftBoss || entity instanceof vazkii.botania.common.entity.EntityDoppleganger) {
/* 304 */       return true;
/*     */     }
/* 306 */     return false;
/*     */   }
/*     */   
/*     */   public static String getBaubleTooltip(BaubleType type) {
/* 310 */     String str = "";
/*     */     
/* 312 */     switch (type)
/*     */     { case AMULET:
/* 314 */         str = StatCollector.translateToLocal("item.FRAmulet.lore");
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
/* 328 */         return str;case BELT: str = StatCollector.translateToLocal("item.FRBelt.lore"); return str;case RING: str = StatCollector.translateToLocal("item.FRRing.lore"); return str; }  str = ""; return str;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static MovingObjectPosition getPointedBlock(EntityPlayer player, World world, float range) {
/* 338 */     double d0 = player.posX;
/* 339 */     double d1 = player.posY + 1.62D - player.yOffset;
/* 340 */     double d2 = player.posZ;
/*     */     
/* 342 */     Vec3 position = Vec3.createVectorHelper(d0, d1, d2);
/* 343 */     Vec3 look = player.getLook(1.0F);
/* 344 */     Vec3 finalvec = position.addVector(look.xCoord * range, look.yCoord * range, look.zCoord * range);
/*     */     
/* 346 */     MovingObjectPosition mop = world.rayTraceBlocks(position, finalvec);
/*     */     
/* 348 */     return mop;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static List<EntityPlayer> getBaubleOwnersList(World world, Item baubleItem) {
/* 358 */     List<EntityPlayer> returnList = new LinkedList<>();
/*     */     
/* 360 */     if (!world.isRemote) {
/*     */       
/* 362 */       List<EntityPlayer> playersList = new ArrayList<>((MinecraftServer.getServer().getConfigurationManager()).playerEntityList);
/*     */       
/* 364 */       for (int counter = playersList.size() - 1; counter >= 0; counter--) {
/*     */         
/* 366 */         if (hasBauble(playersList.get(counter), baubleItem)) {
/* 367 */           returnList.add(playersList.get(counter));
/*     */         }
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 374 */     return returnList;
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
/*     */   public static EntityPlayer findPlayerWithBauble(World world, int radius, Item baubleItem, EntityLivingBase entity) {
/* 386 */     List<EntityPlayer> returnList = new LinkedList<>();
/*     */     
/* 388 */     if (!world.isRemote) {
/* 389 */       List<EntityPlayer> playerList = world.getEntitiesWithinAABB(EntityPlayer.class, AxisAlignedBB.getBoundingBox(entity.posX - radius, entity.posY - radius, entity.posZ - radius, entity.posX + radius, entity.posY + radius, entity.posZ + radius));
/*     */       
/* 391 */       if (playerList.contains(entity)) {
/* 392 */         playerList.remove(entity);
/*     */       }
/* 394 */       for (int counter = playerList.size() - 1; counter >= 0; counter--) {
/*     */         
/* 396 */         if (hasBauble(playerList.get(counter), baubleItem)) {
/* 397 */           returnList.add(playerList.get(counter));
/*     */         }
/*     */       } 
/*     */ 
/*     */       
/* 402 */       if (returnList.size() > 0) {
/* 403 */         return returnList.get((int)((returnList.size() - 1) * Math.random()));
/*     */       }
/* 405 */       return null;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 411 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean hasBauble(EntityPlayer player, Item theBauble) {
/* 421 */     InventoryBaubles baubles = PlayerHandler.getPlayerBaubles(player);
/* 422 */     List<Item> baubleList = new ArrayList<>();
/* 423 */     if (baubles.getStackInSlot(0) != null)
/* 424 */       baubleList.add(baubles.getStackInSlot(0).getItem()); 
/* 425 */     if (baubles.getStackInSlot(1) != null)
/* 426 */       baubleList.add(baubles.getStackInSlot(1).getItem()); 
/* 427 */     if (baubles.getStackInSlot(2) != null)
/* 428 */       baubleList.add(baubles.getStackInSlot(2).getItem()); 
/* 429 */     if (baubles.getStackInSlot(3) != null) {
/* 430 */       baubleList.add(baubles.getStackInSlot(3).getItem());
/*     */     }
/* 432 */     if (baubleList.contains(theBauble)) {
/* 433 */       return true;
/*     */     }
/*     */     
/* 436 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean validTeleport(Entity entity, double x_init, double y_init, double z_init, World world) {
/* 446 */     int x = (int)x_init;
/* 447 */     int y = (int)y_init;
/* 448 */     int z = (int)z_init;
/*     */     
/* 450 */     Block block = world.getBlock(x, y - 1, z);
/*     */     
/* 452 */     if ((((block != Blocks.air) ? 1 : 0) & block.isCollidable()) != 0) {
/*     */       
/* 454 */       for (int counter = 0; counter <= 32; counter++)
/*     */       {
/* 456 */         if (((!world.isAirBlock(x, y + counter - 1, z) ? 1 : 0) & world.getBlock(x, y + counter - 1, z).isCollidable() & world.isAirBlock(x, y + counter, z) & world.isAirBlock(x, y + counter + 1, z)) != 0)
/*     */         {
/* 458 */           imposeBurst(entity.worldObj, entity.dimension, entity.posX, entity.posY + 1.0D, entity.posZ, 1.25F);
/*     */           
/* 460 */           entity.worldObj.playSoundEffect(entity.posX, entity.posY, entity.posZ, "mob.endermen.portal", 1.0F, 1.0F);
/* 461 */           ((EntityLivingBase)entity).setPositionAndUpdate(x + 0.5D, (y + counter), z + 0.5D);
/* 462 */           entity.worldObj.playSoundEffect(x, (y + counter), z, "mob.endermen.portal", 1.0F, 1.0F);
/*     */           
/* 464 */           return true;
/*     */         }
/*     */       
/*     */       }
/*     */     
/*     */     } else {
/*     */       
/* 471 */       for (int counter = 0; counter <= 32; counter++) {
/*     */         
/* 473 */         if (((!world.isAirBlock(x, y - counter - 1, z) ? 1 : 0) & world.getBlock(x, y - counter - 1, z).isCollidable() & world.isAirBlock(x, y - counter, z) & world.isAirBlock(x, y - counter + 1, z)) != 0) {
/*     */           
/* 475 */           imposeBurst(entity.worldObj, entity.dimension, entity.posX, entity.posY + 1.0D, entity.posZ, 1.25F);
/*     */           
/* 477 */           entity.worldObj.playSoundEffect(entity.posX, entity.posY, entity.posZ, "mob.endermen.portal", 1.0F, 1.0F);
/* 478 */           ((EntityLivingBase)entity).setPositionAndUpdate(x + 0.5D, (y - counter), z + 0.5D);
/* 479 */           entity.worldObj.playSoundEffect(x, (y - counter), z, "mob.endermen.portal", 1.0F, 1.0F);
/*     */           
/* 481 */           return true;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 488 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean validTeleportRandomly(Entity entity, World world, int radius) {
/* 497 */     int d = radius * 2;
/*     */     
/* 499 */     double x = entity.posX + (Math.random() - 0.5D) * d;
/* 500 */     double y = entity.posY + (Math.random() - 0.5D) * d;
/* 501 */     double z = entity.posZ + (Math.random() - 0.5D) * d;
/* 502 */     return validTeleport(entity, x, y, z, world);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static ItemStack getRandomValidWand(List<ItemStack> list, Aspect aspect) {
/* 512 */     List<ItemStack> validWand = new LinkedList<>();
/*     */     
/* 514 */     ItemStack randomWand = null;
/*     */     
/* 516 */     if (list.size() > 0) {
/*     */       
/* 518 */       for (int counter = 0; counter < list.size(); counter++) {
/* 519 */         ItemStack sheduledWand = list.get(counter);
/* 520 */         if (((ItemWandCasting)sheduledWand.getItem()).getVis(sheduledWand, aspect) < ((ItemWandCasting)sheduledWand.getItem()).getMaxVis(sheduledWand)) {
/* 521 */           validWand.add(sheduledWand);
/*     */         }
/*     */       } 
/*     */       
/* 525 */       if (validWand.size() > 0) {
/* 526 */         randomWand = validWand.get((int)(Math.random() * (validWand.size() - 1 + 1)));
/*     */       }
/* 528 */       return randomWand;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 533 */     return randomWand;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static List wandSearch(EntityPlayer player) {
/* 542 */     List<ItemStack> itemStackList = new LinkedList<>();
/*     */     
/* 544 */     for (int slot = 0; slot < player.inventory.mainInventory.length; slot++) {
/* 545 */       if (player.inventory.mainInventory[slot] != null)
/*     */       {
/* 547 */         if (player.inventory.mainInventory[slot].getItem() instanceof ItemWandCasting) {
/* 548 */           itemStackList.add(player.inventory.mainInventory[slot]);
/*     */         }
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 554 */     return itemStackList;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static List itemSearch(EntityPlayer player, Item researchItem) {
/* 563 */     List<ItemStack> itemStackList = new LinkedList<>();
/*     */     
/* 565 */     for (int slot = 0; slot < player.inventory.mainInventory.length; slot++) {
/* 566 */       if (player.inventory.mainInventory[slot] != null)
/*     */       {
/* 568 */         if (player.inventory.mainInventory[slot].getItem() == researchItem)
/*     */         {
/* 570 */           itemStackList.add(player.inventory.mainInventory[slot]);
/*     */         }
/*     */       }
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 577 */     return itemStackList;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean sidedVisConsumption(EntityPlayer player, World world, AspectList list) {
/* 586 */     if (!world.isRemote) {
/* 587 */       if (WandManager.consumeVisFromInventory(player, list)) {
/* 588 */         return true;
/*     */       }
/* 590 */       return false;
/*     */     } 
/* 592 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void insanelyDisastrousConsequences(EntityPlayer player) {
/* 601 */     while (player.inventory.hasItem(Main.itemFateTome)) {
/* 602 */       player.inventory.consumeInventoryItem(Main.itemFateTome);
/*     */     }
/*     */     
/* 605 */     List<EntityLivingBase> entityList = player.worldObj.getEntitiesWithinAABB(EntityLivingBase.class, AxisAlignedBB.getBoundingBox(player.posX - 64.0D, player.posY - 64.0D, player.posZ - 64.0D, player.posX + 64.0D, player.posY + 64.0D, player.posZ + 64.0D));
/*     */     
/* 607 */     if ((((entityList != null) ? 1 : 0) & ((entityList.size() > 0) ? 1 : 0)) != 0)
/*     */     {
/* 609 */       for (int counter = entityList.size(); counter > 0; counter--) {
/*     */         
/* 611 */         ((EntityLivingBase)entityList.get(counter - 1)).attackEntityFrom(new DamageRegistryHandler.DamageSourceFate(), 40000.0F);
/* 612 */         player.worldObj.newExplosion((Entity)player, ((EntityLivingBase)entityList.get(counter - 1)).posX, ((EntityLivingBase)entityList.get(counter - 1)).posY, ((EntityLivingBase)entityList.get(counter - 1)).posZ, 16.0F, true, true);
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/* 617 */     player.worldObj.newExplosion((Entity)player, player.posX, player.posY, player.posZ, 100.0F, true, true);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static ItemStack findFirst(EntityPlayer player, Item searchItem) {
/* 628 */     for (int slot = 0; slot < player.inventory.mainInventory.length; slot++) {
/* 629 */       if (player.inventory.mainInventory[slot] != null)
/*     */       {
/* 631 */         if (player.inventory.mainInventory[slot].getItem() == searchItem) {
/* 632 */           return player.inventory.mainInventory[slot];
/*     */         }
/*     */       }
/*     */     } 
/* 636 */     return null;
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
/*     */   public static void convertStuff(EntityPlayer player, Item consumableItem, Item leftoverItem) {
/* 648 */     for (int slot = 0; slot < player.inventory.mainInventory.length; slot++) {
/* 649 */       if (player.inventory.mainInventory[slot] != null)
/*     */       {
/* 651 */         if (player.inventory.mainInventory[slot].getItem() == consumableItem) {
/* 652 */           player.inventory.mainInventory[slot] = new ItemStack(leftoverItem);
/*     */           return;
/*     */         } 
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int[] addInt(int[] series, int newInt) {
/* 663 */     int[] newSeries = new int[series.length + 1];
/*     */     
/* 665 */     for (int i = 0; i < series.length; i++) {
/* 666 */       newSeries[i] = series[i];
/*     */     }
/*     */     
/* 669 */     newSeries[newSeries.length - 1] = newInt;
/* 670 */     return newSeries;
/*     */   }
/*     */ }


/* Location:              C:\Users\antro\Documents\forgottenRelicsDeobfuscated.jar!\com\integral\forgottenrelics\handlers\SuperpositionHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */