/*    */ package com.integral.forgottenrelics.items;
/*    */ 
/*    */ import baubles.api.BaubleType;
/*    */ import baubles.api.IBauble;
/*    */ import com.integral.forgottenrelics.Main;
/*    */ import com.integral.forgottenrelics.handlers.RelicsKeybindHandler;
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
/*    */ import org.lwjgl.input.Keyboard;
/*    */ 
/*    */ 
/*    */ public class ItemDiscordRing
/*    */   extends ItemBaubleBase
/*    */   implements IBauble
/*    */ {
/*    */   public ItemDiscordRing() {
/* 26 */     super("ItemDiscordRing");
/* 27 */     this.maxStackSize = 1;
/* 28 */     setUnlocalizedName("ItemDiscordRing");
/* 29 */     setCreativeTab(Main.tabForgottenRelics);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void registerIcons(IIconRegister iconRegister) {
/* 37 */     this.itemIcon = iconRegister.registerIcon("forgottenrelics:Discord_Ring");
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List<String> par3List, boolean par4) {
/* 45 */     String keyName = "???";
/*    */     
/*    */     try {
/* 48 */       keyName = Keyboard.getKeyName(RelicsKeybindHandler.discordRingKey.getKeyCode());
/* 49 */     } catch (Exception exception) {}
/*    */     
/* 51 */     if (GuiScreen.isShiftKeyDown()) {
/* 52 */       par3List.add(StatCollector.translateToLocal("item.ItemDiscordRing1.lore"));
/* 53 */       par3List.add(StatCollector.translateToLocal("item.ItemDiscordRing2.lore"));
/* 54 */       par3List.add(StatCollector.translateToLocal("item.ItemDiscordRing3.lore"));
/* 55 */       par3List.add(StatCollector.translateToLocal("item.FREmpty.lore"));
/* 56 */       par3List.add(StatCollector.translateToLocal("item.ItemDiscordRing4.lore") + " " + StatCollector.translateToLocal("item.FRCode6.lore") + keyName);
/* 57 */       par3List.add(StatCollector.translateToLocal("item.FREmpty.lore"));
/* 58 */       par3List.add(SuperpositionHandler.getBaubleTooltip(getBaubleType(par1ItemStack)));
/*    */     } else {
/*    */       
/* 61 */       par3List.add(StatCollector.translateToLocal("item.FRShiftTooltip.lore"));
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public EnumRarity getRarity(ItemStack itemStack) {
/* 69 */     return EnumRarity.epic;
/*    */   }
/*    */ 
/*    */   
/*    */   public BaubleType getBaubleType(ItemStack arg0) {
/* 74 */     return BaubleType.RING;
/*    */   }
/*    */ 
/*    */   
/*    */   public void onWornTick(ItemStack itemstack, EntityLivingBase entity) {
/* 79 */     super.onWornTick(itemstack, entity);
/*    */   }
/*    */ }


/* Location:              C:\Users\antro\Documents\forgottenRelicsDeobfuscated.jar!\com\integral\forgottenrelics\items\ItemDiscordRing.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */