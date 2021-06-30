/*    */ package com.integral.forgottenrelics.items;
/*    */ 
/*    */ import com.integral.forgottenrelics.Main;
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import net.minecraft.client.gui.GuiScreen;
/*    */ import net.minecraft.client.renderer.texture.IIconRegister;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.item.EnumRarity;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.StatCollector;
/*    */ import net.minecraft.world.World;
/*    */ import thaumcraft.api.IWarpingGear;
/*    */ import vazkii.botania.common.core.helper.Vector3;
/*    */ 
/*    */ 
/*    */ public class ItemGhastlySkull
/*    */   extends Item
/*    */   implements IWarpingGear
/*    */ {
/*    */   public ItemGhastlySkull() {
/* 26 */     this.maxStackSize = 1;
/* 27 */     setUnlocalizedName("ItemGhastlySkull");
/* 28 */     setCreativeTab(Main.tabForgottenRelics);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void registerIcons(IIconRegister iconRegister) {
/* 36 */     this.itemIcon = iconRegister.registerIcon("forgottenrelics:Ghastly_Skull");
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List<String> par3List, boolean par4) {
/* 44 */     if (GuiScreen.isShiftKeyDown()) {
/* 45 */       par3List.add(StatCollector.translateToLocal("item.ItemGhastlySkull1.lore"));
/* 46 */       par3List.add(StatCollector.translateToLocal("item.ItemGhastlySkull2.lore"));
/*    */     } else {
/*    */       
/* 49 */       par3List.add(StatCollector.translateToLocal("item.FRShiftTooltip.lore"));
/*    */     } 
/*    */ 
/*    */     
/* 53 */     par3List.add(StatCollector.translateToLocal("item.FREmpty.lore"));
/*    */   }
/*    */   
/*    */   public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
/* 57 */     player.setHealth(1.0F);
/*    */     
/* 59 */     Vector3 vec = Vector3.fromEntityCenter((Entity)player);
/* 60 */     Vector3 look = (new Vector3(player.getLookVec())).multiply(16.0D);
/* 61 */     look.add(vec.copy());
/*    */     
/* 63 */     if (world.isRemote) {
/*    */ 
/*    */       
/* 66 */       List<List> selfContainedArray = new ArrayList<>();
/* 67 */       selfContainedArray.add(Main.darkRingDamageNegations);
/* 68 */       selfContainedArray.add(selfContainedArray);
/*    */       
/* 70 */       Main.log.info("The array: " + selfContainedArray);
/*    */     } 
/*    */ 
/*    */     
/* 74 */     return stack;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public EnumRarity getRarity(ItemStack itemStack) {
/* 93 */     return EnumRarity.epic;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getWarp(ItemStack arg0, EntityPlayer arg1) {
/* 99 */     return 3;
/*    */   }
/*    */ }


/* Location:              C:\Users\antro\Documents\forgottenRelicsDeobfuscated.jar!\com\integral\forgottenrelics\items\ItemGhastlySkull.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */