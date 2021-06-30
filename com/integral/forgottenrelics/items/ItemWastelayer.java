/*    */ package com.integral.forgottenrelics.items;
/*    */ 
/*    */ import com.integral.forgottenrelics.Main;
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import java.util.List;
/*    */ import net.minecraft.client.gui.GuiScreen;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.item.EnumRarity;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.item.ItemSword;
/*    */ import net.minecraft.util.StatCollector;
/*    */ import net.minecraft.world.World;
/*    */ import thaumcraft.api.IRepairable;
/*    */ import thaumcraft.api.IWarpingGear;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ItemWastelayer
/*    */   extends ItemSword
/*    */   implements IRepairable, IWarpingGear
/*    */ {
/*    */   public ItemWastelayer(Item.ToolMaterial m) {
/* 28 */     super(m);
/* 29 */     setCreativeTab(Main.tabForgottenRelics);
/* 30 */     setTextureName("forgottenrelics:Wastelayer");
/* 31 */     setUnlocalizedName("ItemWastelayer");
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity) {
/* 38 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List<String> par3List, boolean par4) {
/* 45 */     if (GuiScreen.isShiftKeyDown()) {
/* 46 */       par3List.add(StatCollector.translateToLocal("item.ItemWastelayer1.lore"));
/* 47 */       par3List.add(StatCollector.translateToLocal("item.ItemWastelayer2.lore"));
/* 48 */       par3List.add(StatCollector.translateToLocal("item.ItemWastelayer3.lore"));
/*    */     } else {
/*    */       
/* 51 */       par3List.add(StatCollector.translateToLocal("item.FRShiftTooltip.lore"));
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public EnumRarity getRarity(ItemStack stack) {
/* 59 */     return EnumRarity.epic;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void onUpdate(ItemStack stack, World world, Entity entity, int i, boolean b) {}
/*    */ 
/*    */ 
/*    */   
/*    */   public int getWarp(ItemStack arg0, EntityPlayer arg1) {
/* 70 */     return 10;
/*    */   }
/*    */ }


/* Location:              C:\Users\antro\Documents\forgottenRelicsDeobfuscated.jar!\com\integral\forgottenrelics\items\ItemWastelayer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */