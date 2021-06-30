/*     */ package com.integral.forgottenrelics.items;
/*     */ 
/*     */ import com.integral.forgottenrelics.Main;
/*     */ import com.integral.forgottenrelics.handlers.RelicsConfigHandler;
/*     */ import com.integral.forgottenrelics.handlers.SuperpositionHandler;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.List;
/*     */ import net.minecraft.client.gui.GuiScreen;
/*     */ import net.minecraft.client.renderer.texture.IIconRegister;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.EnumAction;
/*     */ import net.minecraft.item.EnumRarity;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.StatCollector;
/*     */ import net.minecraft.world.World;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.api.aspects.AspectList;
/*     */ import thaumcraft.common.items.wands.WandManager;
/*     */ import vazkii.botania.common.Botania;
/*     */ import vazkii.botania.common.core.helper.Vector3;
/*     */ 
/*     */ public class ItemWeatherStone
/*     */   extends Item
/*     */ {
/*  28 */   public int VisCost = (int)(2500.0F * RelicsConfigHandler.weatherStoneVisMult);
/*     */ 
/*     */   
/*     */   public ItemWeatherStone() {
/*  32 */     setMaxStackSize(1);
/*  33 */     setUnlocalizedName("ItemWeatherStone");
/*  34 */     setCreativeTab(Main.tabForgottenRelics);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void registerIcons(IIconRegister iconRegister) {
/*  42 */     this.itemIcon = iconRegister.registerIcon("forgottenrelics:Weather_Stone");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List<String> par3List, boolean par4) {
/*  50 */     if (GuiScreen.isShiftKeyDown()) {
/*  51 */       par3List.add(StatCollector.translateToLocal("item.ItemWeatherStone1.lore"));
/*  52 */       par3List.add(StatCollector.translateToLocal("item.FREmpty.lore"));
/*  53 */       par3List.add(StatCollector.translateToLocal("item.ItemWeatherStone2_1.lore") + " " + (this.VisCost / 100) + " " + StatCollector.translateToLocal("item.ItemWeatherStone2_2.lore"));
/*  54 */       par3List.add(StatCollector.translateToLocal("item.ItemWeatherStone3.lore"));
/*     */     } else {
/*     */       
/*  57 */       par3List.add(StatCollector.translateToLocal("item.FRShiftTooltip.lore"));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
/*  63 */     if ((world.isRaining() & (!SuperpositionHandler.isOnCoodown(player) ? 1 : 0)) != 0) {
/*  64 */       player.setItemInUse(stack, stack.getMaxItemUseDuration());
/*     */     }
/*  66 */     return stack;
/*     */   }
/*     */ 
/*     */   
/*     */   public EnumAction getItemUseAction(ItemStack par1ItemStack) {
/*  71 */     return EnumAction.bow;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getMaxItemUseDuration(ItemStack par1ItemStack) {
/*  76 */     return 60;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void onUsingTick(ItemStack stack, EntityPlayer player, int count) {
/*  82 */     Vector3 vec = Vector3.fromEntityCenter((Entity)player);
/*     */     
/*  84 */     if ((((count == 1) ? 1 : 0) & player.worldObj.getWorldInfo().isRaining()) != 0 && 
/*  85 */       WandManager.consumeVisFromInventory(player, (new AspectList()).add(Aspect.AIR, this.VisCost).add(Aspect.EARTH, this.VisCost).add(Aspect.WATER, this.VisCost))) {
/*     */       
/*  87 */       for (int i = 0; i <= 24; i++) {
/*  88 */         float r = 0.0F;
/*  89 */         float g = 0.3F + (float)Math.random() * 0.5F;
/*  90 */         float b = 0.8F + (float)Math.random() * 0.2F;
/*  91 */         float s = 0.2F + (float)Math.random() * 0.2F;
/*  92 */         float m = 0.15F;
/*  93 */         float xm = ((float)Math.random() - 0.5F) * m;
/*  94 */         float ym = ((float)Math.random() - 0.5F) * m;
/*  95 */         float zm = ((float)Math.random() - 0.5F) * m;
/*     */ 
/*     */         
/*  98 */         Botania.proxy.wispFX(player.worldObj, vec.x, vec.y, vec.z, r, g, b, s, xm, ym, zm);
/*     */       } 
/*     */       
/* 101 */       player.worldObj.playSoundEffect(vec.x, vec.y, vec.z, "botania:altarCraft", 1.0F, (float)(0.800000011920929D + Math.random() * 0.20000000298023224D));
/*     */       
/* 103 */       player.worldObj.getWorldInfo().setRaining(false);
/* 104 */       player.worldObj.getWorldInfo().setRainTime(24000 + (int)(Math.random() * 976000.0D));
/*     */       
/* 106 */       SuperpositionHandler.setCasted(player, 100, false);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EnumRarity getRarity(ItemStack itemStack) {
/* 114 */     return EnumRarity.epic;
/*     */   }
/*     */ }


/* Location:              C:\Users\antro\Documents\forgottenRelicsDeobfuscated.jar!\com\integral\forgottenrelics\items\ItemWeatherStone.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */