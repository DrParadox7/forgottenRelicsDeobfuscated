/*     */ package com.integral.forgottenrelics.items;
/*     */ 
/*     */ import baubles.api.BaubleType;
/*     */ import baubles.api.IBauble;
/*     */ import com.integral.forgottenrelics.Main;
/*     */ import com.integral.forgottenrelics.handlers.DamageRegistryHandler;
/*     */ import com.integral.forgottenrelics.handlers.SuperpositionHandler;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.List;
/*     */ import net.minecraft.client.gui.GuiScreen;
/*     */ import net.minecraft.client.renderer.texture.IIconRegister;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.EnumRarity;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.potion.PotionEffect;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.StatCollector;
/*     */ import thaumcraft.api.IWarpingGear;
/*     */ import vazkii.botania.common.core.helper.ItemNBTHelper;
/*     */ 
/*     */ public class ItemOblivionAmulet
/*     */   extends ItemBaubleBase
/*     */   implements IBauble, IWarpingGear {
/*     */   public void registerRenderers() {}
/*     */   
/*     */   public ItemOblivionAmulet() {
/*  29 */     super("ItemOblivionAmulet");
/*  30 */     this.maxStackSize = 1;
/*  31 */     setUnlocalizedName("ItemOblivionAmulet");
/*  32 */     setCreativeTab(Main.tabForgottenRelics);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void registerIcons(IIconRegister iconRegister) {
/*  40 */     this.itemIcon = iconRegister.registerIcon("forgottenrelics:Oblivion_Amulet");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List<String> par3List, boolean par4) {
/*  48 */     if (GuiScreen.isShiftKeyDown()) {
/*  49 */       par3List.add(StatCollector.translateToLocal("item.ItemOblivionAmulet1.lore"));
/*  50 */       par3List.add(StatCollector.translateToLocal("item.ItemOblivionAmulet2.lore"));
/*  51 */       par3List.add(StatCollector.translateToLocal("item.ItemOblivionAmulet3.lore"));
/*  52 */       par3List.add(StatCollector.translateToLocal("item.FREmpty.lore"));
/*  53 */       par3List.add(StatCollector.translateToLocal("item.ItemOblivionAmulet4.lore"));
/*  54 */       par3List.add(StatCollector.translateToLocal("item.FREmpty.lore"));
/*  55 */       par3List.add(StatCollector.translateToLocal("item.ItemOblivionAmulet5.lore"));
/*  56 */       par3List.add(StatCollector.translateToLocal("item.FREmpty.lore"));
/*  57 */       par3List.add(SuperpositionHandler.getBaubleTooltip(getBaubleType(par1ItemStack)));
/*     */     } else {
/*     */       
/*  60 */       par3List.add(StatCollector.translateToLocal("item.FRShiftTooltip.lore"));
/*     */     } 
/*     */     
/*  63 */     par3List.add(StatCollector.translateToLocal("item.FREmpty.lore"));
/*     */     
/*  65 */     if (par1ItemStack.hasTagCompound()) {
/*     */       
/*  67 */       par3List.add(StatCollector.translateToLocal("item.ItemOblivionAmuletDamage.lore") + " " + (Math.round(par1ItemStack.getTagCompound().getFloat("IDamageStored") * 100.0D) / 100.0D));
/*  68 */       par3List.add(StatCollector.translateToLocal("item.FREmpty.lore"));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EnumRarity getRarity(ItemStack itemStack) {
/*  76 */     return EnumRarity.epic;
/*     */   }
/*     */ 
/*     */   
/*     */   public BaubleType getBaubleType(ItemStack arg0) {
/*  81 */     return BaubleType.AMULET;
/*     */   }
/*     */ 
/*     */   
/*     */   public void onWornTick(ItemStack itemstack, EntityLivingBase entity) {
/*  86 */     super.onWornTick(itemstack, entity);
/*     */     
/*  88 */     if (((!entity.worldObj.isRemote ? 1 : 0) & itemstack.hasTagCompound() & ((Math.random() <= 8.0E-4D) ? 1 : 0)) != 0) {
/*     */       
/*  90 */       if (ItemNBTHelper.getInt(itemstack, "IDamageStored", 0) > 0)
/*     */       {
/*  92 */         float getDamage = (float)(ItemNBTHelper.getFloat(itemstack, "IDamageStored", 0.0F) * Math.random());
/*     */         
/*  94 */         if ((((getDamage > 100.0F) ? 1 : 0) & ((Math.random() <= 0.9D) ? 1 : 0)) != 0) {
/*  95 */           getDamage = (float)(100.0D * Math.random());
/*     */         }
/*     */         
/*  98 */         ItemNBTHelper.setFloat(itemstack, "IDamageStored", ItemNBTHelper.getFloat(itemstack, "IDamageStored", 0.0F) - getDamage);
/*     */         
/* 100 */         entity.attackEntityFrom((DamageSource)new DamageRegistryHandler.DamageSourceOblivion(), getDamage);
/*     */       }
/*     */     
/*     */     }
/* 104 */     else if (((!entity.worldObj.isRemote ? 1 : 0) & ((Math.random() <= 4.0E-4D) ? 1 : 0)) != 0) {
/* 105 */       double omega = Math.random();
/* 106 */       int PotionID = 0;
/*     */       
/* 108 */       if (omega <= 0.25D) {
/* 109 */         PotionID = 4;
/* 110 */       } else if (omega <= 0.5D) {
/* 111 */         PotionID = 15;
/* 112 */       } else if (omega <= 0.75D) {
/* 113 */         PotionID = 18;
/*     */       } else {
/* 115 */         PotionID = 20;
/*     */       } 
/* 117 */       entity.addPotionEffect(new PotionEffect(PotionID, (int)(100.0D + Math.random() * 2000.0D), (int)(Math.random() * 3.0D), true));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getWarp(ItemStack arg0, EntityPlayer arg1) {
/* 125 */     return 4;
/*     */   }
/*     */ }


/* Location:              C:\Users\antro\Documents\forgottenRelicsDeobfuscated.jar!\com\integral\forgottenrelics\items\ItemOblivionAmulet.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */