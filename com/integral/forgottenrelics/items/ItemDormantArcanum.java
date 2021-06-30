/*    */ package com.integral.forgottenrelics.items;
/*    */ 
/*    */ import baubles.api.BaubleType;
/*    */ import baubles.api.IBauble;
/*    */ import baubles.common.container.InventoryBaubles;
/*    */ import baubles.common.lib.PlayerHandler;
/*    */ import com.integral.forgottenrelics.Main;
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
/*    */ import thaumcraft.api.aspects.Aspect;
/*    */ import thaumcraft.api.aspects.AspectList;
/*    */ import thaumcraft.common.items.wands.WandManager;
/*    */ import vazkii.botania.common.core.helper.ItemNBTHelper;
/*    */ 
/*    */ public class ItemDormantArcanum
/*    */   extends ItemBaubleBase
/*    */   implements IBauble
/*    */ {
/*    */   public void registerRenderers() {}
/*    */   
/*    */   public ItemDormantArcanum() {
/* 30 */     super("ItemDormantArcanum");
/* 31 */     this.maxStackSize = 1;
/* 32 */     setUnlocalizedName("ItemDormantArcanum");
/* 33 */     setCreativeTab(Main.tabForgottenRelics);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void registerIcons(IIconRegister iconRegister) {
/* 41 */     this.itemIcon = iconRegister.registerIcon("forgottenrelics:Nebulous_Core_Dormant");
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List<String> par3List, boolean par4) {
/* 50 */     if (GuiScreen.isShiftKeyDown()) {
/*    */       
/* 52 */       par3List.add(StatCollector.translateToLocal("item.ItemDormantArcanum1.lore"));
/*    */     } else {
/*    */       
/* 55 */       par3List.add(StatCollector.translateToLocal("item.FRShiftTooltip.lore"));
/*    */     } 
/*    */ 
/*    */     
/* 59 */     if (par1ItemStack.hasTagCompound()) {
/* 60 */       par3List.add(StatCollector.translateToLocal("item.FREmpty.lore"));
/* 61 */       par3List.add(StatCollector.translateToLocal("item.FRCode6.lore") + ((ItemNBTHelper.getInt(par1ItemStack, "ILifetime", 0) * 2) / 100.0D) + StatCollector.translateToLocal("item.ItemDormantArcanum2.lore"));
/*    */     } 
/*    */   }
/*    */ 
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
/* 75 */     return BaubleType.AMULET;
/*    */   }
/*    */ 
/*    */   
/*    */   public void onWornTick(ItemStack itemstack, EntityLivingBase entity) {
/* 80 */     super.onWornTick(itemstack, entity);
/*    */     
/* 82 */     if ((itemstack.hasTagCompound() & (!entity.worldObj.isRemote ? 1 : 0) & entity instanceof EntityPlayer) != 0)
/*    */     {
/* 84 */       if (ItemNBTHelper.getInt(itemstack, "ILifetime", 0) > 0) {
/*    */         
/* 86 */         if (WandManager.consumeVisFromInventory((EntityPlayer)entity, (new AspectList()).add(Aspect.AIR, 3).add(Aspect.FIRE, 3).add(Aspect.EARTH, 3).add(Aspect.WATER, 3).add(Aspect.ORDER, 3).add(Aspect.ENTROPY, 3))) {
/* 87 */           ItemNBTHelper.setInt(itemstack, "ILifetime", ItemNBTHelper.getInt(itemstack, "ILifetime", 0) - 1);
/*    */         }
/*    */       } else {
/*    */         
/* 91 */         InventoryBaubles baubles = PlayerHandler.getPlayerBaubles((EntityPlayer)entity);
/* 92 */         baubles.setInventorySlotContents(0, new ItemStack(Main.itemArcanum));
/*    */       } 
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\antro\Documents\forgottenRelicsDeobfuscated.jar!\com\integral\forgottenrelics\items\ItemDormantArcanum.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */