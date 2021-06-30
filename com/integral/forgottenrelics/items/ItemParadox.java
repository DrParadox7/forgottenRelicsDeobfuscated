/*    */ package com.integral.forgottenrelics.items;
/*    */ 
/*    */ import com.integral.forgottenrelics.Main;
/*    */ import com.integral.forgottenrelics.handlers.RelicsConfigHandler;
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import java.util.List;
/*    */ import net.minecraft.client.gui.GuiScreen;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.item.EnumRarity;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.item.ItemSword;
/*    */ import net.minecraft.util.DamageSource;
/*    */ import net.minecraft.util.StatCollector;
/*    */ import net.minecraft.world.World;
/*    */ import thaumcraft.api.IRepairable;
/*    */ import thaumcraft.api.IWarpingGear;
/*    */ 
/*    */ 
/*    */ public class ItemParadox
/*    */   extends ItemSword
/*    */   implements IRepairable, IWarpingGear
/*    */ {
/*    */   public ItemParadox(Item.ToolMaterial m) {
/* 28 */     super(m);
/* 29 */     setCreativeTab(Main.tabForgottenRelics);
/* 30 */     setTextureName("forgottenrelics:Paradox");
/* 31 */     setUnlocalizedName("ItemParadox");
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity) {
/* 37 */     double currentDamage = Math.random() * RelicsConfigHandler.paradoxDamageCap;
/*    */     
/* 39 */     entity.attackEntityFrom(DamageSource.causePlayerDamage(player), (float)currentDamage);
/* 40 */     player.attackEntityFrom(DamageSource.causePlayerDamage(player), (float)(RelicsConfigHandler.paradoxDamageCap - currentDamage));
/*    */     
/* 42 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List<String> par3List, boolean par4) {
/* 49 */     if (GuiScreen.isShiftKeyDown()) {
/* 50 */       par3List.add(StatCollector.translateToLocal("item.ItemParadox1.lore"));
/* 51 */       par3List.add(StatCollector.translateToLocal("item.ItemParadox2.lore"));
/* 52 */       par3List.add(StatCollector.translateToLocal("item.ItemParadox3.lore"));
/* 53 */       par3List.add(StatCollector.translateToLocal("item.ItemParadox4.lore"));
/* 54 */       par3List.add(StatCollector.translateToLocal("item.ItemParadox5.lore"));
/*    */     } else {
/*    */       
/* 57 */       par3List.add(StatCollector.translateToLocal("item.FRShiftTooltip.lore"));
/*    */     } 
/*    */     
/* 60 */     par3List.add(StatCollector.translateToLocal("item.FREmpty.lore"));
/* 61 */     par3List.add(StatCollector.translateToLocal("item.ItemParadoxDamage_1.lore") + (int)RelicsConfigHandler.paradoxDamageCap + StatCollector.translateToLocal("item.ItemParadoxDamage_2.lore"));
/*    */   }
/*    */ 
/*    */   
/*    */   public EnumRarity getRarity(ItemStack stack) {
/* 66 */     return EnumRarity.epic;
/*    */   }
/*    */ 
/*    */   
/*    */   public void onUpdate(ItemStack stack, World world, Entity entity, int i, boolean b) {
/* 71 */     if (!world.isRemote && stack.isItemDamaged() && entity.ticksExisted % 20 == 0)
/*    */     {
/* 73 */       stack.damageItem(-1, (EntityLivingBase)entity);
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getWarp(ItemStack arg0, EntityPlayer arg1) {
/* 80 */     return 8;
/*    */   }
/*    */ }


/* Location:              C:\Users\antro\Documents\forgottenRelicsDeobfuscated.jar!\com\integral\forgottenrelics\items\ItemParadox.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */