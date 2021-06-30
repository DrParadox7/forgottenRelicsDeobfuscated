/*    */ package com.integral.forgottenrelics.items;
/*    */ 
/*    */ import com.integral.forgottenrelics.Main;
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import java.util.List;
/*    */ import net.minecraft.client.gui.GuiScreen;
/*    */ import net.minecraft.client.renderer.texture.IIconRegister;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.item.EnumRarity;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.potion.PotionEffect;
/*    */ import net.minecraft.util.StatCollector;
/*    */ import net.minecraft.world.World;
/*    */ import thaumcraft.api.IWarpingGear;
/*    */ 
/*    */ 
/*    */ public class ItemChaosCore
/*    */   extends Item
/*    */   implements IWarpingGear
/*    */ {
/*    */   public void registerRenderers() {}
/*    */   
/*    */   public ItemChaosCore() {
/* 28 */     this.maxStackSize = 1;
/* 29 */     setUnlocalizedName("ItemChaosCore");
/* 30 */     setCreativeTab(Main.tabForgottenRelics);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void registerIcons(IIconRegister iconRegister) {
/* 38 */     this.itemIcon = iconRegister.registerIcon("forgottenrelics:Chaos_Core");
/*    */   }
/*    */ 
/*    */   
/*    */   public void onUpdate(ItemStack itemstack, World world, Entity entity, int i, boolean b) {
/* 43 */     if (((!world.isRemote ? 1 : 0) & ((Math.random() <= 2.08E-4D) ? 1 : 0)) != 0) {
/* 44 */       int randomizedPotionID = 1 + (int)(Math.random() * 21.0D);
/* 45 */       if (randomizedPotionID == 6 || randomizedPotionID == 7) {
/* 46 */         randomizedPotionID = 20;
/*    */       }
/* 48 */       ((EntityLivingBase)entity).addPotionEffect(new PotionEffect(randomizedPotionID, 100 + (int)(Math.random() * 2400.0D), (int)(Math.random() * 3.0D), false));
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List<String> par3List, boolean par4) {
/* 57 */     if (GuiScreen.isShiftKeyDown()) {
/* 58 */       par3List.add(StatCollector.translateToLocal("item.ItemChaosCore1.lore"));
/* 59 */       par3List.add(StatCollector.translateToLocal("item.ItemChaosCore2.lore"));
/* 60 */       par3List.add(StatCollector.translateToLocal("item.ItemChaosCore3.lore"));
/* 61 */       par3List.add(StatCollector.translateToLocal("item.FREmpty.lore"));
/* 62 */       par3List.add(StatCollector.translateToLocal("item.ItemChaosCore4.lore"));
/* 63 */       par3List.add(StatCollector.translateToLocal("item.ItemChaosCore5.lore"));
/* 64 */       par3List.add(StatCollector.translateToLocal("item.ItemChaosCore6.lore"));
/* 65 */       par3List.add(StatCollector.translateToLocal("item.FREmpty.lore"));
/* 66 */       par3List.add(StatCollector.translateToLocal("item.ItemChaosCore7.lore"));
/*    */     } else {
/*    */       
/* 69 */       par3List.add(StatCollector.translateToLocal("item.FRShiftTooltip.lore"));
/*    */     } 
/*    */ 
/*    */     
/* 73 */     par3List.add(StatCollector.translateToLocal("item.FREmpty.lore"));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public EnumRarity getRarity(ItemStack itemStack) {
/* 80 */     return EnumRarity.epic;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getWarp(ItemStack arg0, EntityPlayer arg1) {
/* 85 */     return 2;
/*    */   }
/*    */ }


/* Location:              C:\Users\antro\Documents\forgottenRelicsDeobfuscated.jar!\com\integral\forgottenrelics\items\ItemChaosCore.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */