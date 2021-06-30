/*    */ package com.integral.forgottenrelics.items;
/*    */ 
/*    */ import baubles.api.BaubleType;
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
/*    */ import vazkii.botania.common.Botania;
/*    */ 
/*    */ 
/*    */ public class ItemMiningCharm
/*    */   extends ItemBaubleBase
/*    */ {
/*    */   public void registerRenderers() {}
/*    */   
/*    */   public ItemMiningCharm() {
/* 26 */     super("ItemMiningCharm");
/*    */     
/* 28 */     this.maxStackSize = 1;
/* 29 */     setUnlocalizedName("ItemMiningCharm");
/* 30 */     setCreativeTab(Main.tabForgottenRelics);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void registerIcons(IIconRegister iconRegister) {
/* 38 */     this.itemIcon = iconRegister.registerIcon("forgottenrelics:Mining_Charm");
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List<String> par3List, boolean par4) {
/* 46 */     if (GuiScreen.isShiftKeyDown()) {
/* 47 */       par3List.add(StatCollector.translateToLocal("item.ItemMiningCharm1_1.lore") + " " + (int)(RelicsConfigHandler.miningCharmBoost * 100.0F) + StatCollector.translateToLocal("item.ItemMiningCharm1_2.lore"));
/* 48 */       par3List.add(StatCollector.translateToLocal("item.ItemMiningCharm2_1.lore") + " " + RelicsConfigHandler.miningCharmReach + StatCollector.translateToLocal("item.ItemMiningCharm2_2.lore"));
/* 49 */       par3List.add(StatCollector.translateToLocal("item.FREmpty.lore"));
/* 50 */       par3List.add(SuperpositionHandler.getBaubleTooltip(getBaubleType(par1ItemStack)));
/*    */     } else {
/*    */       
/* 53 */       par3List.add(StatCollector.translateToLocal("item.FRShiftTooltip.lore"));
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public EnumRarity getRarity(ItemStack itemStack) {
/* 62 */     return EnumRarity.uncommon;
/*    */   }
/*    */ 
/*    */   
/*    */   public BaubleType getBaubleType(ItemStack arg0) {
/* 67 */     return BaubleType.RING;
/*    */   }
/*    */ 
/*    */   
/*    */   public void onEquippedOrLoadedIntoWorld(ItemStack stack, EntityLivingBase player) {
/* 72 */     Botania.proxy.setExtraReach(player, RelicsConfigHandler.miningCharmReach);
/*    */   }
/*    */ 
/*    */   
/*    */   public void onUnequipped(ItemStack stack, EntityLivingBase player) {
/* 77 */     Botania.proxy.setExtraReach(player, -RelicsConfigHandler.miningCharmReach);
/*    */   }
/*    */ }


/* Location:              C:\Users\antro\Documents\forgottenRelicsDeobfuscated.jar!\com\integral\forgottenrelics\items\ItemMiningCharm.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */