/*     */ package com.integral.forgottenrelics.items;
/*     */ 
/*     */ import com.integral.forgottenrelics.Main;
/*     */ import com.integral.forgottenrelics.entities.EntityLunarFlare;
/*     */ import com.integral.forgottenrelics.handlers.RelicsConfigHandler;
/*     */ import com.integral.forgottenrelics.handlers.SuperpositionHandler;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.List;
/*     */ import net.minecraft.client.gui.GuiScreen;
/*     */ import net.minecraft.client.renderer.texture.IIconRegister;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.EnumAction;
/*     */ import net.minecraft.item.EnumRarity;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.MovingObjectPosition;
/*     */ import net.minecraft.util.StatCollector;
/*     */ import net.minecraft.world.World;
/*     */ import thaumcraft.api.IWarpingGear;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.api.aspects.AspectList;
/*     */ import thaumcraft.common.items.wands.WandManager;
/*     */ import vazkii.botania.common.core.helper.Vector3;
/*     */ 
/*     */ public class ItemLunarFlares
/*     */   extends Item implements IWarpingGear {
/*  30 */   public static final int AerCost = (int)(35.0F * RelicsConfigHandler.lunarFlaresVisMult);
/*  31 */   public static final int TerraCost = (int)(0.0F * RelicsConfigHandler.lunarFlaresVisMult);
/*  32 */   public static final int IgnisCost = (int)(50.0F * RelicsConfigHandler.lunarFlaresVisMult);
/*  33 */   public static final int AquaCost = (int)(0.0F * RelicsConfigHandler.lunarFlaresVisMult);
/*  34 */   public static final int OrdoCost = (int)(65.0F * RelicsConfigHandler.lunarFlaresVisMult);
/*  35 */   public static final int PerditioCost = (int)(0.0F * RelicsConfigHandler.lunarFlaresVisMult);
/*     */ 
/*     */   
/*     */   public ItemLunarFlares() {
/*  39 */     this.maxStackSize = 1;
/*  40 */     setUnlocalizedName("ItemLunarFlares");
/*  41 */     setCreativeTab(Main.tabForgottenRelics);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void registerIcons(IIconRegister iconRegister) {
/*  49 */     this.itemIcon = iconRegister.registerIcon("forgottenrelics:Lunar_Flares");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List<String> par3List, boolean par4) {
/*  57 */     if (GuiScreen.isShiftKeyDown()) {
/*  58 */       par3List.add(StatCollector.translateToLocal("item.ItemLunarFlares1.lore"));
/*  59 */       par3List.add(StatCollector.translateToLocal("item.FREmpty.lore"));
/*  60 */       par3List.add(StatCollector.translateToLocal("item.ItemLunarFlares2.lore"));
/*  61 */       par3List.add(StatCollector.translateToLocal("item.ItemLunarFlares3_1.lore") + " " + (int)RelicsConfigHandler.damageLunarFlareImpact + " " + StatCollector.translateToLocal("item.ItemLunarFlares3_2.lore"));
/*  62 */       par3List.add(StatCollector.translateToLocal("item.ItemLunarFlares35.lore"));
/*  63 */       par3List.add(StatCollector.translateToLocal("item.FREmpty.lore"));
/*  64 */       par3List.add(StatCollector.translateToLocal("item.ItemLunarFlares4_1.lore") + " " + (int)RelicsConfigHandler.damageLunarFlareDirect + " " + StatCollector.translateToLocal("item.ItemLunarFlares4_2.lore"));
/*     */     }
/*  66 */     else if (GuiScreen.isCtrlKeyDown()) {
/*  67 */       par3List.add(StatCollector.translateToLocal("item.FRVisPerSecond.lore"));
/*  68 */       this; par3List.add(" " + StatCollector.translateToLocal("item.FRAerCost.lore") + (AerCost / 100.0D * 10.0D));
/*  69 */       this; par3List.add(" " + StatCollector.translateToLocal("item.FRIgnisCost.lore") + (IgnisCost / 100.0D * 10.0D));
/*  70 */       this; par3List.add(" " + StatCollector.translateToLocal("item.FROrdoCost.lore") + (OrdoCost / 100.0D * 10.0D));
/*     */     } else {
/*     */       
/*  73 */       par3List.add(StatCollector.translateToLocal("item.FRShiftTooltip.lore"));
/*  74 */       par3List.add(StatCollector.translateToLocal("item.FRViscostTooltip.lore"));
/*     */     } 
/*     */     
/*  77 */     par3List.add(StatCollector.translateToLocal("item.FREmpty.lore"));
/*     */   }
/*     */ 
/*     */   
/*     */   public void spawnLunarFlare(World world, EntityPlayer player, MovingObjectPosition mop) {
/*  82 */     if ((((mop != null) ? 1 : 0) & (!world.isRemote ? 1 : 0)) != 0) {
/*  83 */       EntityLunarFlare flare = new EntityLunarFlare(world, (EntityLivingBase)player, mop.blockX, mop.blockY, mop.blockZ);
/*  84 */       flare.setPosition(mop.blockX + (Math.random() - 0.5D) * 12.0D, (mop.blockY + 24) + (Math.random() - 0.5D) * 12.0D, mop.blockZ + 0.5D + (Math.random() - 0.5D) * 12.0D);
/*     */       
/*  86 */       Vector3 posVec = new Vector3(mop.blockX + 0.5D, mop.blockY, mop.blockZ + 0.5D);
/*  87 */       Vector3 motVec = new Vector3((Math.random() - 0.5D) * 18.0D, (24.0D + (Math.random() - 0.5D) * 18.0D) * 2.0D, (Math.random() - 0.5D) * 18.0D);
/*  88 */       posVec.add(motVec);
/*  89 */       motVec.normalize().negate().multiply(4.0D);
/*     */       
/*  91 */       flare.setPosition(posVec.x, posVec.y, posVec.z);
/*  92 */       flare.motionX = motVec.x;
/*  93 */       flare.motionY = motVec.y;
/*  94 */       flare.motionZ = motVec.z;
/*     */       
/*  96 */       world.spawnEntityInWorld((Entity)flare);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public EnumAction getItemUseAction(ItemStack par1ItemStack) {
/* 103 */     return EnumAction.bow;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getMaxItemUseDuration(ItemStack par1ItemStack) {
/* 108 */     return 72000;
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
/* 113 */     player.setItemInUse(stack, stack.getMaxItemUseDuration());
/*     */     
/* 115 */     return stack;
/*     */   }
/*     */ 
/*     */   
/*     */   public void onUsingTick(ItemStack stack, EntityPlayer player, int count) {
/* 120 */     if (count != getMaxItemUseDuration(stack) && count % 2 == 0 && !player.worldObj.isRemote) {
/* 121 */       MovingObjectPosition mop = SuperpositionHandler.getPointedBlock(player, player.worldObj, 128.0F);
/*     */ 
/*     */       
/* 124 */       this; this; this; if (mop != null && WandManager.consumeVisFromInventory(player, (new AspectList()).add(Aspect.AIR, AerCost).add(Aspect.FIRE, IgnisCost).add(Aspect.ORDER, OrdoCost))) {
/* 125 */         spawnLunarFlare(player.worldObj, player, mop);
/* 126 */         if (count != getMaxItemUseDuration(stack) && count % 4 == 0 && !player.worldObj.isRemote) {
/* 127 */           player.worldObj.playSoundEffect(player.posX, player.posY, player.posZ, "ForgottenRelics:sound.starfall", 2.0F, (float)(1.0D + Math.random() * 0.5D));
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
/*     */   public EnumRarity getRarity(ItemStack itemStack) {
/* 140 */     return EnumRarity.epic;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getWarp(ItemStack arg0, EntityPlayer arg1) {
/* 145 */     return 3;
/*     */   }
/*     */ }


/* Location:              C:\Users\antro\Documents\forgottenRelicsDeobfuscated.jar!\com\integral\forgottenrelics\items\ItemLunarFlares.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */