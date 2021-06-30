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
/*    */ public class ItemAdvancedMiningCharm
/*    */   extends ItemBaubleBase
/*    */ {
/*    */   public void registerRenderers() {}
/*    */   
/*    */   public ItemAdvancedMiningCharm() {
/* 26 */     super("ItemAdvancedMiningCharm");
/*    */     
/* 28 */     this.maxStackSize = 1;
/* 29 */     setUnlocalizedName("ItemAdvancedMiningCharm");
/* 30 */     setCreativeTab(Main.tabForgottenRelics);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void registerIcons(IIconRegister iconRegister) {
/* 38 */     this.itemIcon = iconRegister.registerIcon("forgottenrelics:Advanced_Mining_Charm");
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List<String> par3List, boolean par4) {
/* 46 */     if (GuiScreen.isShiftKeyDown()) {
/* 47 */       par3List.add(StatCollector.translateToLocal("item.ItemAdvancedMiningCharm1_1.lore") + " " + (int)(RelicsConfigHandler.advancedMiningCharmBoost * 100.0F) + StatCollector.translateToLocal("item.ItemAdvancedMiningCharm1_2.lore"));
/* 48 */       par3List.add(StatCollector.translateToLocal("item.ItemAdvancedMiningCharm2_1.lore") + " " + RelicsConfigHandler.advancedMiningCharmReach + StatCollector.translateToLocal("item.ItemAdvancedMiningCharm2_2.lore"));
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
/* 62 */     return EnumRarity.epic;
/*    */   }
/*    */ 
/*    */   
/*    */   public BaubleType getBaubleType(ItemStack arg0) {
/* 67 */     return BaubleType.RING;
/*    */   }
/*    */ 
/*    */   
/*    */   public void onEquippedOrLoadedIntoWorld(ItemStack stack, EntityLivingBase player) {
/* 72 */     Botania.proxy.setExtraReach(player, RelicsConfigHandler.advancedMiningCharmReach);
/*    */   }
/*    */ 
/*    */   
/*    */   public void onUnequipped(ItemStack stack, EntityLivingBase player) {
/* 77 */     Botania.proxy.setExtraReach(player, -RelicsConfigHandler.advancedMiningCharmReach);
/*    */   }
/*    */ }


/* Location:              C:\Users\antro\Documents\forgottenRelicsDeobfuscated.jar!\com\integral\forgottenrelics\items\ItemAdvancedMiningCharm.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */