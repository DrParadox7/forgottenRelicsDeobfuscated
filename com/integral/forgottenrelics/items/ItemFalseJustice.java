/*    */ package com.integral.forgottenrelics.items;
/*    */ 
/*    */ import com.integral.forgottenrelics.Main;
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import java.util.List;
/*    */ import net.minecraft.client.gui.GuiScreen;
/*    */ import net.minecraft.client.renderer.texture.IIconRegister;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.item.EnumRarity;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.StatCollector;
/*    */ import thaumcraft.api.IRepairable;
/*    */ import thaumcraft.api.IWarpingGear;
/*    */ 
/*    */ 
/*    */ public class ItemFalseJustice
/*    */   extends Item
/*    */   implements IWarpingGear, IRepairable
/*    */ {
/*    */   public void registerRenderers() {}
/*    */   
/*    */   public ItemFalseJustice() {
/* 25 */     this.maxStackSize = 1;
/* 26 */     setUnlocalizedName("ItemFalseJustice");
/* 27 */     setCreativeTab(Main.tabForgottenRelics);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void registerIcons(IIconRegister iconRegister) {
/* 35 */     this.itemIcon = iconRegister.registerIcon("forgottenrelics:False_Justice");
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List<String> par3List, boolean par4) {
/* 43 */     if (GuiScreen.isShiftKeyDown()) {
/* 44 */       par3List.add(StatCollector.translateToLocal("item.ItemFalseJustice1.lore"));
/* 45 */       par3List.add(StatCollector.translateToLocal("item.ItemFalseJustice2.lore"));
/* 46 */       par3List.add(StatCollector.translateToLocal("item.ItemFalseJustice3.lore"));
/*    */     } else {
/*    */       
/* 49 */       par3List.add(StatCollector.translateToLocal("item.FRShiftTooltip.lore"));
/*    */     } 
/*    */ 
/*    */     
/* 53 */     par3List.add(StatCollector.translateToLocal("item.FREmpty.lore"));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public EnumRarity getRarity(ItemStack itemStack) {
/* 60 */     return EnumRarity.epic;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getWarp(ItemStack arg0, EntityPlayer arg1) {
/* 65 */     return 4;
/*    */   }
/*    */ }


/* Location:              C:\Users\antro\Documents\forgottenRelicsDeobfuscated.jar!\com\integral\forgottenrelics\items\ItemFalseJustice.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */