/*    */ package com.integral.forgottenrelics.items;
/*    */ 
/*    */ import baubles.api.BaubleType;
/*    */ import com.google.common.collect.Multimap;
/*    */ import com.integral.forgottenrelics.Main;
/*    */ import com.integral.forgottenrelics.handlers.RelicsConfigHandler;
/*    */ import com.integral.forgottenrelics.handlers.SuperpositionHandler;
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import java.util.List;
/*    */ import net.minecraft.client.gui.GuiScreen;
/*    */ import net.minecraft.client.renderer.texture.IIconRegister;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.entity.SharedMonsterAttributes;
/*    */ import net.minecraft.entity.ai.attributes.AttributeModifier;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.item.EnumRarity;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.StatCollector;
/*    */ 
/*    */ 
/*    */ public class ItemAncientAegis
/*    */   extends ItemBaubleBaseModifier
/*    */ {
/*    */   public ItemAncientAegis() {
/* 26 */     super("ItemAncientAegis");
/* 27 */     setCreativeTab(Main.tabForgottenRelics);
/* 28 */     setUnlocalizedName("ItemAncientAegis");
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List<String> par3List, boolean par4) {
/* 35 */     if (GuiScreen.isShiftKeyDown()) {
/* 36 */       par3List.add(StatCollector.translateToLocal("item.ItemAncientAegis1_1.lore") + " " + (int)(RelicsConfigHandler.ancientAegisDamageReduction * 100.0F) + StatCollector.translateToLocal("item.ItemAncientAegis1_2.lore"));
/* 37 */       par3List.add(StatCollector.translateToLocal("item.ItemAncientAegis2.lore"));
/* 38 */       par3List.add(StatCollector.translateToLocal("item.ItemAncientAegis3.lore"));
/* 39 */       par3List.add(StatCollector.translateToLocal("item.FREmpty.lore"));
/* 40 */       par3List.add(StatCollector.translateToLocal("item.ItemAncientAegis4.lore"));
/* 41 */       par3List.add(StatCollector.translateToLocal("item.ItemAncientAegis5.lore"));
/* 42 */       par3List.add(StatCollector.translateToLocal("item.FREmpty.lore"));
/* 43 */       par3List.add(SuperpositionHandler.getBaubleTooltip(getBaubleType(par1ItemStack)));
/*    */     } else {
/*    */       
/* 46 */       par3List.add(StatCollector.translateToLocal("item.FRShiftTooltip.lore"));
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void onWornTick(ItemStack stack, EntityLivingBase player) {
/* 53 */     super.onWornTick(stack, player);
/*    */     
/* 55 */     if (((!player.worldObj.isRemote ? 1 : 0) & ((player.ticksExisted % 20 == 0) ? 1 : 0) & ((player.getMaxHealth() != player.getHealth()) ? 1 : 0)) != 0) {
/* 56 */       player.heal(1.0F);
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public EnumRarity getRarity(ItemStack itemStack) {
/* 62 */     return EnumRarity.epic;
/*    */   }
/*    */ 
/*    */   
/*    */   public void registerIcons(IIconRegister iconRegister) {
/* 67 */     this.itemIcon = iconRegister.registerIcon("forgottenrelics:Ancient_Aegis");
/*    */   }
/*    */ 
/*    */   
/*    */   public BaubleType getBaubleType(ItemStack itemstack) {
/* 72 */     return BaubleType.BELT;
/*    */   }
/*    */ 
/*    */   
/*    */   void fillModifiers(Multimap<String, AttributeModifier> attributes, ItemStack stack) {
/* 77 */     attributes.put(SharedMonsterAttributes.knockbackResistance.getAttributeUnlocalizedName(), new AttributeModifier(getBaubleUUID(stack), "Bauble modifier", 1.0D, 0));
/*    */   }
/*    */ }


/* Location:              C:\Users\antro\Documents\forgottenRelicsDeobfuscated.jar!\com\integral\forgottenrelics\items\ItemAncientAegis.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */