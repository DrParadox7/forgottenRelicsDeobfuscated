/*    */ package com.integral.forgottenrelics.items;
/*    */ 
/*    */ import baubles.api.BaubleType;
/*    */ import baubles.api.IBauble;
/*    */ import com.integral.forgottenrelics.Main;
/*    */ import com.integral.forgottenrelics.handlers.RelicsConfigHandler;
/*    */ import com.integral.forgottenrelics.handlers.SuperpositionHandler;
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import java.util.List;
/*    */ import net.minecraft.client.gui.GuiScreen;
/*    */ import net.minecraft.client.renderer.texture.IIconRegister;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.item.EnumRarity;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.StatCollector;
/*    */ 
/*    */ public class ItemDarkSunRing
/*    */   extends ItemBaubleBase
/*    */   implements IBauble
/*    */ {
/*    */   public void registerRenderers() {}
/*    */   
/*    */   public ItemDarkSunRing() {
/* 26 */     super("ItemDarkSunRing");
/* 27 */     this.maxStackSize = 1;
/* 28 */     setUnlocalizedName("ItemDarkSunRing");
/* 29 */     setCreativeTab(Main.tabForgottenRelics);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void registerIcons(IIconRegister iconRegister) {
/* 37 */     this.itemIcon = iconRegister.registerIcon("forgottenrelics:Dark_Sun_Ring");
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List<String> par3List, boolean par4) {
/* 45 */     if (GuiScreen.isShiftKeyDown()) {
/* 46 */       par3List.add(StatCollector.translateToLocal("item.ItemDarkSunRing1.lore"));
/* 47 */       par3List.add(StatCollector.translateToLocal("item.ItemDarkSunRing2_1.lore") + " " + (int)RelicsConfigHandler.darkSunRingDamageCap + StatCollector.translateToLocal("item.ItemDarkSunRing2_2.lore"));
/* 48 */       par3List.add(StatCollector.translateToLocal("item.ItemDarkSunRing3.lore"));
/* 49 */       par3List.add(StatCollector.translateToLocal("item.FREmpty.lore"));
/* 50 */       par3List.add(StatCollector.translateToLocal("item.ItemDarkSunRing4_1.lore") + " " + (int)(RelicsConfigHandler.darkSunRingDeflectChance * 100.0F) + StatCollector.translateToLocal("item.ItemDarkSunRing4_2.lore"));
/* 51 */       par3List.add(StatCollector.translateToLocal("item.ItemDarkSunRing5.lore"));
/* 52 */       par3List.add(StatCollector.translateToLocal("item.FREmpty.lore"));
/* 53 */       par3List.add(StatCollector.translateToLocal("item.ItemDarkSunRing6.lore"));
/* 54 */       par3List.add(StatCollector.translateToLocal("item.ItemDarkSunRing7.lore"));
/* 55 */       par3List.add(StatCollector.translateToLocal("item.FREmpty.lore"));
/* 56 */       par3List.add(StatCollector.translateToLocal("item.ItemDarkSunRing8.lore"));
/* 57 */       par3List.add(StatCollector.translateToLocal("item.ItemDarkSunRing9.lore"));
/* 58 */       par3List.add(StatCollector.translateToLocal("item.FREmpty.lore"));
/* 59 */       par3List.add(SuperpositionHandler.getBaubleTooltip(getBaubleType(par1ItemStack)));
/*    */     } else {
/*    */       
/* 62 */       par3List.add(StatCollector.translateToLocal("item.FRShiftTooltip.lore"));
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public EnumRarity getRarity(ItemStack itemStack) {
/* 70 */     return EnumRarity.epic;
/*    */   }
/*    */ 
/*    */   
/*    */   public BaubleType getBaubleType(ItemStack arg0) {
/* 75 */     return BaubleType.RING;
/*    */   }
/*    */ 
/*    */   
/*    */   public void onWornTick(ItemStack itemstack, EntityLivingBase entity) {
/* 80 */     super.onWornTick(itemstack, entity);
/* 81 */     if (entity.isBurning())
/* 82 */       entity.extinguish(); 
/*    */   }
/*    */ }


/* Location:              C:\Users\antro\Documents\forgottenRelicsDeobfuscated.jar!\com\integral\forgottenrelics\items\ItemDarkSunRing.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */