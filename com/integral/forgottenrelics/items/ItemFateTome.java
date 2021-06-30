/*     */ package com.integral.forgottenrelics.items;
/*     */ 
/*     */ import com.integral.forgottenrelics.Main;
/*     */ import com.integral.forgottenrelics.handlers.RelicsConfigHandler;
/*     */ import com.integral.forgottenrelics.handlers.SuperpositionHandler;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.math.BigDecimal;
/*     */ import java.util.List;
/*     */ import net.minecraft.client.gui.GuiScreen;
/*     */ import net.minecraft.client.renderer.texture.IIconRegister;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.EnumRarity;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.StatCollector;
/*     */ import net.minecraft.world.World;
/*     */ import thaumcraft.api.IWarpingGear;
/*     */ import vazkii.botania.common.core.helper.ItemNBTHelper;
/*     */ 
/*     */ public class ItemFateTome
/*     */   extends Item
/*     */   implements IWarpingGear
/*     */ {
/*  26 */   public static final int AerCost = (int)(10000.0F * RelicsConfigHandler.fateTomeVisMult);
/*  27 */   public static final int TerraCost = (int)(10000.0F * RelicsConfigHandler.fateTomeVisMult);
/*  28 */   public static final int IgnisCost = (int)(10000.0F * RelicsConfigHandler.fateTomeVisMult);
/*  29 */   public static final int AquaCost = (int)(10000.0F * RelicsConfigHandler.fateTomeVisMult);
/*  30 */   public static final int OrdoCost = (int)(10000.0F * RelicsConfigHandler.fateTomeVisMult);
/*  31 */   public static final int PerditioCost = (int)(10000.0F * RelicsConfigHandler.fateTomeVisMult);
/*     */ 
/*     */   
/*     */   public ItemFateTome() {
/*  35 */     this.maxStackSize = 1;
/*  36 */     setUnlocalizedName("ItemFateTome");
/*  37 */     setCreativeTab(Main.tabForgottenRelics);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void registerIcons(IIconRegister iconRegister) {
/*  45 */     this.itemIcon = iconRegister.registerIcon("forgottenrelics:Tome_of_Broken_Fates");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List<String> par3List, boolean par4) {
/*  53 */     if (GuiScreen.isShiftKeyDown()) {
/*  54 */       par3List.add(StatCollector.translateToLocal("item.ItemFateTome1.lore"));
/*  55 */       par3List.add(StatCollector.translateToLocal("item.ItemFateTome2.lore"));
/*  56 */       par3List.add(StatCollector.translateToLocal("item.ItemFateTome3.lore"));
/*  57 */       par3List.add(StatCollector.translateToLocal("item.ItemFateTome4.lore"));
/*  58 */       par3List.add(StatCollector.translateToLocal("item.FREmpty.lore"));
/*     */       
/*  60 */       if (RelicsConfigHandler.fateTomeCooldownMAX != 0) {
/*  61 */         par3List.add(StatCollector.translateToLocal("item.ItemFateTome5_1.lore") + " " + RelicsConfigHandler.fateTomeCooldownMIN + "-" + RelicsConfigHandler.fateTomeCooldownMAX + " " + StatCollector.translateToLocal("item.ItemFateTome5_2.lore"));
/*  62 */         par3List.add(StatCollector.translateToLocal("item.FREmpty.lore"));
/*     */       } 
/*     */       
/*  65 */       par3List.add(StatCollector.translateToLocal("item.ItemFateTome6.lore"));
/*  66 */       par3List.add(StatCollector.translateToLocal("item.ItemFateTome7.lore"));
/*  67 */       par3List.add(StatCollector.translateToLocal("item.FREmpty.lore"));
/*  68 */       par3List.add(StatCollector.translateToLocal("item.ItemFateTome8.lore"));
/*  69 */       par3List.add(StatCollector.translateToLocal("item.ItemFateTome9.lore"));
/*  70 */     } else if (GuiScreen.isCtrlKeyDown()) {
/*  71 */       par3List.add(StatCollector.translateToLocal("item.FRVisPerCast.lore"));
/*  72 */       this; par3List.add(" " + StatCollector.translateToLocal("item.FRAerCost.lore") + (AerCost / 100.0D));
/*  73 */       this; par3List.add(" " + StatCollector.translateToLocal("item.FRTerraCost.lore") + (TerraCost / 100.0D));
/*  74 */       this; par3List.add(" " + StatCollector.translateToLocal("item.FRIgnisCost.lore") + (IgnisCost / 100.0D));
/*  75 */       this; par3List.add(" " + StatCollector.translateToLocal("item.FRAquaCost.lore") + (AquaCost / 100.0D));
/*  76 */       this; par3List.add(" " + StatCollector.translateToLocal("item.FROrdoCost.lore") + (OrdoCost / 100.0D));
/*  77 */       this; par3List.add(" " + StatCollector.translateToLocal("item.FRPerditioCost.lore") + (PerditioCost / 100.0D));
/*     */     } else {
/*     */       
/*  80 */       par3List.add(StatCollector.translateToLocal("item.FRShiftTooltip.lore"));
/*  81 */       par3List.add(StatCollector.translateToLocal("item.FRViscostTooltip.lore"));
/*     */     } 
/*     */     
/*  84 */     if (par1ItemStack.hasTagCompound() && 
/*  85 */       ItemNBTHelper.verifyExistance(par1ItemStack, "IFateCooldown") && 
/*  86 */       ItemNBTHelper.getInt(par1ItemStack, "IFateCooldown", 0) > 0) {
/*  87 */       par3List.add(StatCollector.translateToLocal("item.FREmpty.lore"));
/*  88 */       par3List.add(StatCollector.translateToLocal("item.FRCode" + (int)(Math.random() * 15.0D + 1.0D) + ".lore") + StatCollector.translateToLocal("item.ItemFateTomeCooldown.lore") + " " + (new BigDecimal(ItemNBTHelper.getInt(par1ItemStack, "IFateCooldown", 0) / 20.0D)).setScale(1, 4).doubleValue() + " " + StatCollector.translateToLocal("item.FRSeconds.lore"));
/*     */     } 
/*  90 */     par3List.add(StatCollector.translateToLocal("item.FREmpty.lore"));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onUpdate(ItemStack itemstack, World world, Entity entity, int i, boolean b) {
/*  99 */     if (!(entity instanceof EntityPlayer)) {
/*     */       return;
/*     */     }
/* 102 */     if (((!itemstack.hasTagCompound() ? 1 : 0) & (!world.isRemote ? 1 : 0)) != 0) {
/*     */       
/* 104 */       ItemNBTHelper.setInt(itemstack, "IFateID", (int)(Math.random() * 2.147483647E9D));
/* 105 */       ItemNBTHelper.setInt(itemstack, "IFateCooldown", 0);
/*     */     }
/* 107 */     else if (!world.isRemote) {
/*     */       
/* 109 */       if (ItemNBTHelper.verifyExistance(itemstack, "IFateCooldown") && 
/* 110 */         ItemNBTHelper.getInt(itemstack, "IFateCooldown", 0) > 0) {
/* 111 */         ItemNBTHelper.setInt(itemstack, "IFateCooldown", ItemNBTHelper.getInt(itemstack, "IFateCooldown", 0) - 1);
/*     */         
/* 113 */         if (ItemNBTHelper.getInt(itemstack, "IFateCooldown", 0) == 0) {
/* 114 */           SuperpositionHandler.sendNotification((EntityPlayer)entity, 1);
/*     */         }
/* 116 */         ((EntityPlayer)entity).inventoryContainer.detectAndSendChanges();
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 122 */     if ((((Math.random() <= 1.6E-5D) ? 1 : 0) & (!world.isRemote ? 1 : 0)) != 0 && 
/* 123 */       entity instanceof EntityPlayer) {
/*     */       
/* 125 */       EntityPlayer player = (EntityPlayer)entity;
/*     */       
/* 127 */       if (SuperpositionHandler.itemSearch(player, Main.itemFateTome).size() > 1) {
/* 128 */         SuperpositionHandler.insanelyDisastrousConsequences(player);
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
/* 145 */     return 7;
/*     */   }
/*     */ }


/* Location:              C:\Users\antro\Documents\forgottenRelicsDeobfuscated.jar!\com\integral\forgottenrelics\items\ItemFateTome.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */