/*     */ package com.integral.forgottenrelics.items;
/*     */ 
/*     */ import com.integral.forgottenrelics.Main;
/*     */ import com.integral.forgottenrelics.handlers.SuperpositionHandler;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.List;
/*     */ import net.minecraft.client.gui.GuiScreen;
/*     */ import net.minecraft.client.renderer.texture.IIconRegister;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.EnumRarity;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.StatCollector;
/*     */ import net.minecraft.world.World;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.common.items.wands.ItemWandCasting;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ItemOmegaCore
/*     */   extends Item
/*     */ {
/*     */   public void registerRenderers() {}
/*     */   
/*     */   public ItemOmegaCore() {
/*  28 */     this.maxStackSize = 1;
/*  29 */     setUnlocalizedName("ItemOmegaCore");
/*  30 */     setCreativeTab(Main.tabForgottenRelics);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void registerIcons(IIconRegister iconRegister) {
/*  38 */     this.itemIcon = iconRegister.registerIcon("forgottenrelics:Omega_Core");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void onUpdate(ItemStack itemstack, World world, Entity entity, int i, boolean b) {
/*  44 */     if (((!world.isRemote ? 1 : 0) & entity instanceof EntityPlayer) != 0) {
/*  45 */       EntityPlayer player = (EntityPlayer)entity;
/*     */       
/*  47 */       List<ItemStack> wandList = SuperpositionHandler.wandSearch((EntityPlayer)entity);
/*  48 */       List<Aspect> primalAspects = Aspect.getPrimalAspects();
/*     */       
/*  50 */       if (wandList.size() > 0)
/*     */       {
/*  52 */         for (int counter = 0; counter < primalAspects.size(); counter++) {
/*     */           
/*  54 */           ItemStack randomWand = SuperpositionHandler.getRandomValidWand(wandList, primalAspects.get(counter));
/*     */           
/*  56 */           if (randomWand != null) {
/*  57 */             ((ItemWandCasting)randomWand.getItem()).addVis(randomWand, primalAspects.get(counter), 1, true);
/*     */           }
/*     */         } 
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List<String> par3List, boolean par4) {
/*  86 */     if (GuiScreen.isShiftKeyDown()) {
/*  87 */       par3List.add(StatCollector.translateToLocal("item.OmegaCore1.lore"));
/*  88 */       par3List.add(StatCollector.translateToLocal("item.OmegaCore2.lore"));
/*     */     } else {
/*     */       
/*  91 */       par3List.add(StatCollector.translateToLocal("item.FRShiftTooltip.lore"));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EnumRarity getRarity(ItemStack itemStack) {
/* 100 */     return EnumRarity.epic;
/*     */   }
/*     */ }


/* Location:              C:\Users\antro\Documents\forgottenRelicsDeobfuscated.jar!\com\integral\forgottenrelics\items\ItemOmegaCore.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */