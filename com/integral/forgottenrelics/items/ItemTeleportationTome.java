/*     */ package com.integral.forgottenrelics.items;
/*     */ 
/*     */ import com.integral.forgottenrelics.Main;
/*     */ import com.integral.forgottenrelics.handlers.RelicsConfigHandler;
/*     */ import com.integral.forgottenrelics.handlers.SuperpositionHandler;
/*     */ import com.integral.forgottenrelics.packets.PortalTraceMessage;
/*     */ import cpw.mods.fml.common.network.NetworkRegistry;
/*     */ import cpw.mods.fml.common.network.simpleimpl.IMessage;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.List;
/*     */ import net.minecraft.client.gui.GuiScreen;
/*     */ import net.minecraft.client.renderer.texture.IIconRegister;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.EntityPlayerMP;
/*     */ import net.minecraft.inventory.Container;
/*     */ import net.minecraft.item.EnumRarity;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.MovingObjectPosition;
/*     */ import net.minecraft.util.StatCollector;
/*     */ import net.minecraft.world.World;
/*     */ import thaumcraft.api.IWarpingGear;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.api.aspects.AspectList;
/*     */ import thaumcraft.common.config.Config;
/*     */ import thaumcraft.common.items.wands.WandManager;
/*     */ import thaumcraft.common.lib.utils.EntityUtils;
/*     */ import vazkii.botania.common.core.helper.Vector3;
/*     */ 
/*     */ public class ItemTeleportationTome
/*     */   extends Item
/*     */   implements IWarpingGear {
/*  36 */   public static final int AerCost = (int)(160.0F * RelicsConfigHandler.discordTomeVisMult);
/*  37 */   public static final int TerraCost = (int)(0.0F * RelicsConfigHandler.discordTomeVisMult);
/*  38 */   public static final int IgnisCost = (int)(0.0F * RelicsConfigHandler.discordTomeVisMult);
/*  39 */   public static final int AquaCost = (int)(0.0F * RelicsConfigHandler.discordTomeVisMult);
/*  40 */   public static final int OrdoCost = (int)(240.0F * RelicsConfigHandler.discordTomeVisMult);
/*  41 */   public static final int PerditioCost = (int)(240.0F * RelicsConfigHandler.discordTomeVisMult);
/*     */ 
/*     */   
/*     */   public ItemTeleportationTome() {
/*  45 */     this.maxStackSize = 1;
/*  46 */     setUnlocalizedName("ItemTeleportationTome");
/*  47 */     setCreativeTab(Main.tabForgottenRelics);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void registerIcons(IIconRegister iconRegister) {
/*  55 */     this.itemIcon = iconRegister.registerIcon("forgottenrelics:Teleportation_Tome");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onUpdate(ItemStack stack, World world, Entity par3Entity, int p_77663_4_, boolean p_77663_5_) {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List<String> par3List, boolean par4) {
/*  68 */     if (GuiScreen.isShiftKeyDown()) {
/*  69 */       par3List.add(StatCollector.translateToLocal("item.ItemTeleportationTome1.lore"));
/*  70 */       par3List.add(StatCollector.translateToLocal("item.ItemTeleportationTome2.lore"));
/*  71 */       par3List.add(StatCollector.translateToLocal("item.ItemTeleportationTome3.lore"));
/*  72 */       par3List.add(StatCollector.translateToLocal("item.FREmpty.lore"));
/*  73 */       par3List.add(StatCollector.translateToLocal("item.ItemTeleportationTome4.lore"));
/*  74 */       par3List.add(StatCollector.translateToLocal("item.ItemTeleportationTome5.lore"));
/*  75 */       par3List.add(StatCollector.translateToLocal("item.ItemTeleportationTome6.lore"));
/*  76 */     } else if (GuiScreen.isCtrlKeyDown()) {
/*  77 */       par3List.add(StatCollector.translateToLocal("item.FRVisPerCast.lore"));
/*  78 */       this; par3List.add(" " + StatCollector.translateToLocal("item.FRAerCost.lore") + (AerCost / 100.0D));
/*  79 */       this; par3List.add(" " + StatCollector.translateToLocal("item.FROrdoCost.lore") + (OrdoCost / 100.0D));
/*  80 */       this; par3List.add(" " + StatCollector.translateToLocal("item.FRPerditioCost.lore") + (PerditioCost / 100.0D));
/*     */     } else {
/*     */       
/*  83 */       par3List.add(StatCollector.translateToLocal("item.FRShiftTooltip.lore"));
/*  84 */       par3List.add(StatCollector.translateToLocal("item.FRViscostTooltip.lore"));
/*     */     } 
/*     */     
/*  87 */     par3List.add(StatCollector.translateToLocal("item.FREmpty.lore"));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
/*  93 */     if (player.dimension == Config.dimensionOuterId) {
/*  94 */       return stack;
/*     */     }
/*  96 */     if (((!SuperpositionHandler.isOnCoodown(player) ? 1 : 0) & (!world.isRemote ? 1 : 0)) != 0) {
/*     */       
/*  98 */       Entity pointedEntity = EntityUtils.getPointedEntity(world, (Entity)player, 0.0D, 128.0D, 4.0F);
/*     */       
/* 100 */       this; this; this; if ((player.isSneaking() & WandManager.consumeVisFromInventory(player, (new AspectList()).add(Aspect.AIR, AerCost).add(Aspect.ORDER, OrdoCost).add(Aspect.ENTROPY, PerditioCost))) != 0) {
/*     */         
/* 102 */         if (!world.isRemote) {
/* 103 */           Container inventory = player.inventoryContainer;
/* 104 */           ((EntityPlayerMP)player).sendContainerToPlayer(inventory);
/*     */         } 
/*     */         
/* 107 */         SuperpositionHandler.imposeBurst(world, player.dimension, player.posX, player.posY + 1.0D, player.posZ, 1.25F);
/*     */         
/* 109 */         Vector3 primalVec = Vector3.fromEntityCenter((Entity)player);
/*     */         
/* 111 */         primalVec.y -= 0.5D;
/*     */         
/* 113 */         Vector3 lookVec = primalVec.copy().add((new Vector3(player.getLookVec())).multiply(16.0D));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 119 */         world.playSoundEffect(player.posX, player.posY, player.posZ, "mob.endermen.portal", 1.0F, 1.0F);
/* 120 */         if (!world.isRemote)
/* 121 */           player.setPositionAndUpdate(lookVec.x, lookVec.y, lookVec.z); 
/* 122 */         world.playSoundEffect(lookVec.x, lookVec.y, lookVec.z, "mob.endermen.portal", 1.0F, 1.0F);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 128 */         Vector3 finalVec = Vector3.fromEntityCenter((Entity)player);
/* 129 */         finalVec.y -= 0.5D;
/*     */         
/* 131 */         if (!world.isRemote) {
/* 132 */           Main.packetInstance.sendToAllAround((IMessage)new PortalTraceMessage(primalVec.x, primalVec.y, primalVec.z, finalVec.x, finalVec.y, finalVec.z, player.getDistance(primalVec.x, primalVec.y, primalVec.z)), new NetworkRegistry.TargetPoint(player.dimension, player.posX, player.posY, player.posZ, 128.0D));
/*     */         }
/* 134 */         SuperpositionHandler.setCasted(player, 20, false);
/*     */         
/* 136 */         return stack;
/*     */       } 
/* 138 */       this; this; this; if ((((pointedEntity != null) ? 1 : 0) & pointedEntity instanceof EntityLivingBase & WandManager.consumeVisFromInventory(player, (new AspectList()).add(Aspect.AIR, AerCost).add(Aspect.ORDER, OrdoCost).add(Aspect.ENTROPY, PerditioCost))) != 0) {
/*     */         
/* 140 */         if (!world.isRemote) {
/* 141 */           Container inventory = player.inventoryContainer;
/* 142 */           ((EntityPlayerMP)player).sendContainerToPlayer(inventory);
/*     */         } 
/*     */         
/* 145 */         if (!world.isRemote) {
/* 146 */           SuperpositionHandler.imposeBurst(world, player.dimension, player.posX, player.posY + 1.0D, player.posZ, 1.25F);
/*     */         }
/* 148 */         Vector3 primalVec = Vector3.fromEntityCenter((Entity)player);
/* 149 */         Vector3 finalVec = Vector3.fromEntityCenter(pointedEntity);
/*     */         
/* 151 */         if (!world.isRemote) {
/* 152 */           player.setPositionAndUpdate(finalVec.x, finalVec.y, finalVec.z);
/*     */         }
/* 154 */         ((EntityLivingBase)pointedEntity).setPositionAndUpdate(primalVec.x, primalVec.y, primalVec.z);
/*     */         
/* 156 */         world.playSoundEffect(player.posX, player.posY, player.posZ, "mob.endermen.portal", 1.0F, 1.0F);
/* 157 */         world.playSoundEffect(pointedEntity.posX, pointedEntity.posY, pointedEntity.posZ, "mob.endermen.portal", 1.0F, 1.0F);
/*     */         
/* 159 */         if (!world.isRemote) {
/* 160 */           Main.packetInstance.sendToAllAround((IMessage)new PortalTraceMessage(primalVec.x, primalVec.y, primalVec.z, finalVec.x, finalVec.y, finalVec.z, player.getDistance(primalVec.x, primalVec.y, primalVec.z)), new NetworkRegistry.TargetPoint(player.dimension, player.posX, player.posY, player.posZ, 128.0D));
/*     */         }
/* 162 */         SuperpositionHandler.setCasted(player, 20, false);
/*     */         
/* 164 */         return stack;
/*     */       } 
/*     */ 
/*     */       
/* 168 */       MovingObjectPosition pointed = SuperpositionHandler.getPointedBlock(player, world, 128.0F);
/*     */       
/* 170 */       this; this; this; if ((((pointed != null) ? 1 : 0) & WandManager.consumeVisFromInventory(player, (new AspectList()).add(Aspect.AIR, AerCost).add(Aspect.ORDER, OrdoCost).add(Aspect.ENTROPY, PerditioCost))) != 0) {
/*     */         
/* 172 */         if (!world.isRemote) {
/* 173 */           Container inventory = player.inventoryContainer;
/* 174 */           ((EntityPlayerMP)player).sendContainerToPlayer(inventory);
/*     */         } 
/*     */         
/* 177 */         int x = pointed.blockX;
/* 178 */         int y = pointed.blockY;
/* 179 */         int z = pointed.blockZ;
/*     */         
/* 181 */         for (int counter = 0; counter <= 32; counter++) {
/*     */           
/* 183 */           if (((!world.isAirBlock(x, y + counter - 1, z) ? 1 : 0) & world.getBlock(x, y + counter - 1, z).isCollidable() & world.isAirBlock(x, y + counter, z) & world.isAirBlock(x, y + counter + 1, z)) != 0) {
/*     */             
/* 185 */             if (!world.isRemote) {
/* 186 */               SuperpositionHandler.imposeBurst(world, player.dimension, player.posX, player.posY + 1.0D, player.posZ, 1.25F);
/*     */             }
/* 188 */             Vector3 primalVec = Vector3.fromEntityCenter((Entity)player);
/*     */             
/* 190 */             world.playSoundEffect(player.posX, player.posY, player.posZ, "mob.endermen.portal", 1.0F, 1.0F);
/*     */             
/* 192 */             if (!world.isRemote) {
/* 193 */               player.setPositionAndUpdate(x + 0.5D, (y + counter), z + 0.5D);
/*     */             }
/* 195 */             world.playSoundEffect(x, (y + counter), z, "mob.endermen.portal", 1.0F, 1.0F);
/*     */ 
/*     */             
/* 198 */             Vector3 finalVec = Vector3.fromEntityCenter((Entity)player);
/*     */             
/* 200 */             if (!world.isRemote) {
/* 201 */               Main.packetInstance.sendToAllAround((IMessage)new PortalTraceMessage(primalVec.x, primalVec.y, primalVec.z, finalVec.x, finalVec.y, finalVec.z, player.getDistance(primalVec.x, primalVec.y, primalVec.z)), new NetworkRegistry.TargetPoint(player.dimension, player.posX, player.posY, player.posZ, 128.0D));
/*     */             }
/* 203 */             SuperpositionHandler.setCasted(player, 20, false);
/*     */             
/* 205 */             return stack;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 216 */     return stack;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EnumRarity getRarity(ItemStack itemStack) {
/* 223 */     return EnumRarity.epic;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getWarp(ItemStack arg0, EntityPlayer arg1) {
/* 228 */     return 2;
/*     */   }
/*     */ }


/* Location:              C:\Users\antro\Documents\forgottenRelicsDeobfuscated.jar!\com\integral\forgottenrelics\items\ItemTeleportationTome.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */