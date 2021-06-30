/*     */ package com.integral.forgottenrelics.items;
/*     */ 
/*     */ import baubles.api.BaubleType;
/*     */ import baubles.api.IBauble;
/*     */ import baubles.common.container.InventoryBaubles;
/*     */ import baubles.common.lib.PlayerHandler;
/*     */ import com.integral.forgottenrelics.Main;
/*     */ import com.integral.forgottenrelics.handlers.RelicsConfigHandler;
/*     */ import com.integral.forgottenrelics.handlers.SuperpositionHandler;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.List;
/*     */ import net.minecraft.client.gui.GuiScreen;
/*     */ import net.minecraft.client.renderer.texture.IIconRegister;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.EnumRarity;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.StatCollector;
/*     */ import thaumcraft.api.IVisDiscountGear;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.common.items.wands.ItemWandCasting;
/*     */ import vazkii.botania.common.core.helper.ItemNBTHelper;
/*     */ 
/*     */ public class ItemArcanum
/*     */   extends ItemBaubleBase
/*     */   implements IBauble, IVisDiscountGear {
/*     */   public void registerRenderers() {}
/*     */   
/*     */   public ItemArcanum() {
/*  32 */     super("ItemArcanum");
/*  33 */     this.maxStackSize = 1;
/*  34 */     setUnlocalizedName("ItemArcanum");
/*  35 */     setCreativeTab(Main.tabForgottenRelics);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void registerIcons(IIconRegister iconRegister) {
/*  43 */     this.itemIcon = iconRegister.registerIcon("forgottenrelics:Nebulous_Core");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List<String> par3List, boolean par4) {
/*  51 */     par3List.add(StatCollector.translateToLocal("item.ItemArcanum1.lore") + " " + getVisDiscount(par1ItemStack, par2EntityPlayer, Aspect.ENTROPY) + "%");
/*  52 */     par3List.add(StatCollector.translateToLocal("item.FREmpty.lore"));
/*     */     
/*  54 */     if (GuiScreen.isShiftKeyDown()) {
/*     */       
/*  56 */       par3List.add(StatCollector.translateToLocal("item.ItemArcanum2.lore"));
/*  57 */       par3List.add(StatCollector.translateToLocal("item.ItemArcanum3.lore"));
/*  58 */       par3List.add(StatCollector.translateToLocal("item.ItemArcanum4.lore"));
/*  59 */       par3List.add(StatCollector.translateToLocal("item.ItemArcanum5.lore"));
/*  60 */       par3List.add(StatCollector.translateToLocal("item.ItemArcanum6.lore"));
/*  61 */       par3List.add(StatCollector.translateToLocal("item.FREmpty.lore"));
/*  62 */       par3List.add(StatCollector.translateToLocal("item.ItemArcanum7.lore"));
/*  63 */       par3List.add(StatCollector.translateToLocal("item.ItemArcanum8.lore"));
/*  64 */       par3List.add(StatCollector.translateToLocal("item.ItemArcanum9.lore"));
/*  65 */       par3List.add(StatCollector.translateToLocal("item.FREmpty.lore"));
/*  66 */       par3List.add(SuperpositionHandler.getBaubleTooltip(getBaubleType(par1ItemStack)));
/*     */     } else {
/*     */       
/*  69 */       par3List.add(StatCollector.translateToLocal("item.FRShiftTooltip.lore"));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EnumRarity getRarity(ItemStack itemStack) {
/*  77 */     return EnumRarity.epic;
/*     */   }
/*     */ 
/*     */   
/*     */   public BaubleType getBaubleType(ItemStack arg0) {
/*  82 */     return BaubleType.AMULET;
/*     */   }
/*     */ 
/*     */   
/*     */   public void onWornTick(ItemStack itemstack, EntityLivingBase entity) {
/*  87 */     super.onWornTick(itemstack, entity);
/*     */     
/*  89 */     if ((entity instanceof EntityPlayer & (!entity.worldObj.isRemote ? 1 : 0) & ((Math.random() <= 0.025D * RelicsConfigHandler.arcanumGenRate) ? 1 : 0)) != 0) {
/*     */       
/*  91 */       List<ItemStack> wandList = SuperpositionHandler.wandSearch((EntityPlayer)entity);
/*  92 */       List<Aspect> primalAspects = Aspect.getPrimalAspects();
/*     */       
/*  94 */       if (wandList.size() > 0)
/*     */       {
/*  96 */         Aspect randomAspect = primalAspects.get((int)(Math.random() * 6.0D));
/*     */         
/*  98 */         ItemStack randomWand = SuperpositionHandler.getRandomValidWand(wandList, randomAspect);
/*     */         
/* 100 */         if (randomWand != null) {
/* 101 */           ((ItemWandCasting)randomWand.getItem()).addVis(randomWand, randomAspect, 1, true);
/*     */         
/*     */         }
/*     */       }
/*     */     
/*     */     }
/* 107 */     else if ((((Math.random() <= 2.08E-4D) ? 1 : 0) & (!entity.worldObj.isRemote ? 1 : 0)) != 0) {
/*     */       
/* 109 */       for (int counter = 0; counter <= 32 && 
/* 110 */         !SuperpositionHandler.validTeleportRandomly((Entity)entity, entity.worldObj, 32); counter++);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     }
/* 117 */     else if ((((Math.random() <= 2.7E-5D) ? 1 : 0) & (!entity.worldObj.isRemote ? 1 : 0) & entity instanceof EntityPlayer) != 0) {
/*     */       
/* 119 */       ItemStack replacedStack = new ItemStack(Main.itemDormantArcanum);
/* 120 */       ItemNBTHelper.setInt(replacedStack, "ILifetime", (int)((1200.0D + Math.random() * 6000.0D) * RelicsConfigHandler.dormantArcanumVisMult));
/*     */       
/* 122 */       InventoryBaubles baubles = PlayerHandler.getPlayerBaubles((EntityPlayer)entity);
/* 123 */       baubles.setInventorySlotContents(0, replacedStack);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getVisDiscount(ItemStack arg0, EntityPlayer arg1, Aspect arg2) {
/* 131 */     return 35;
/*     */   }
/*     */ }


/* Location:              C:\Users\antro\Documents\forgottenRelicsDeobfuscated.jar!\com\integral\forgottenrelics\items\ItemArcanum.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
