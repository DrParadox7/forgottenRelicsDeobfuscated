/*     */ package com.integral.forgottenrelics.items;
/*     */ 
/*     */ import baubles.api.BaubleType;
/*     */ import baubles.api.IBauble;
/*     */ import com.integral.forgottenrelics.Main;
/*     */ import com.integral.forgottenrelics.handlers.RelicsConfigHandler;
/*     */ import com.integral.forgottenrelics.handlers.SuperpositionHandler;
/*     */ import cpw.mods.fml.relauncher.ReflectionHelper;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.List;
/*     */ import net.minecraft.client.gui.GuiScreen;
/*     */ import net.minecraft.client.renderer.texture.IIconRegister;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.EnumRarity;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.potion.Potion;
/*     */ import net.minecraft.potion.PotionEffect;
/*     */ import net.minecraft.util.StatCollector;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.api.aspects.AspectList;
/*     */ import thaumcraft.common.items.wands.WandManager;
/*     */ import vazkii.botania.common.core.helper.ItemNBTHelper;
/*     */ 
/*     */ public class ItemDeificAmulet
/*     */   extends ItemBaubleBase
/*     */   implements IBauble
/*     */ {
/*     */   public void registerRenderers() {}
/*     */   
/*     */   public ItemDeificAmulet() {
/*  35 */     super("ItemDeificAmulet");
/*  36 */     setMaxStackSize(1);
/*  37 */     setUnlocalizedName("ItemDeificAmulet");
/*  38 */     setCreativeTab(Main.tabForgottenRelics);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void registerIcons(IIconRegister iconRegister) {
/*  45 */     this.itemIcon = iconRegister.registerIcon("forgottenrelics:Deific_Amulet");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List<String> par3List, boolean par4) {
/*  52 */     if (GuiScreen.isShiftKeyDown()) {
/*  53 */       if (RelicsConfigHandler.deificAmuletEffectImmunity)
/*  54 */         if (RelicsConfigHandler.deificAmuletOnlyNegatesDebuffs) {
/*  55 */           par3List.add(StatCollector.translateToLocal("item.ItemDeificAmulet1_alt.lore"));
/*     */         } else {
/*  57 */           par3List.add(StatCollector.translateToLocal("item.ItemDeificAmulet1.lore"));
/*     */         }  
/*  59 */       par3List.add(StatCollector.translateToLocal("item.ItemDeificAmulet2.lore"));
/*  60 */       if (RelicsConfigHandler.deificAmuletInvincibility)
/*  61 */         par3List.add(StatCollector.translateToLocal("item.ItemDeificAmulet3.lore")); 
/*  62 */       par3List.add(StatCollector.translateToLocal("item.FREmpty.lore"));
/*  63 */       par3List.add(StatCollector.translateToLocal("item.ItemDeificAmulet4.lore"));
/*  64 */       par3List.add(StatCollector.translateToLocal("item.ItemDeificAmulet5.lore"));
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
/*  89 */     if (((!entity.worldObj.isRemote ? 1 : 0) & entity instanceof EntityPlayer) != 0) {
/*     */       
/*  91 */       if ((((entity.getActivePotionEffects() != null) ? 1 : 0) & RelicsConfigHandler.deificAmuletEffectImmunity) != 0)
/*     */       {
/*  93 */         if (RelicsConfigHandler.deificAmuletOnlyNegatesDebuffs) {
/*  94 */           Collection<PotionEffect> effects = new ArrayList<>(entity.getActivePotionEffects());
/*     */           
/*  96 */           for (PotionEffect effect : effects) {
/*  97 */             int id = effect.getPotionID();
/*  98 */             boolean badEffect = ((Boolean)ReflectionHelper.getPrivateValue(Potion.class, Potion.potionTypes[id], new String[] { "isBadEffect", "isBadEffect" })).booleanValue();
/*     */             
/* 100 */             if (badEffect)
/* 101 */               entity.removePotionEffect(id); 
/*     */           } 
/*     */         } else {
/* 104 */           entity.clearActivePotions();
/*     */         } 
/*     */       }
/*     */       
/* 108 */       if (entity.isBurning()) entity.extinguish();
/*     */       
/* 110 */       if (entity.getAir() == 0)
/*     */       {
/* 112 */         if (WandManager.consumeVisFromInventory((EntityPlayer)entity, (new AspectList()).add(Aspect.AIR, (int)(1000.0F * RelicsConfigHandler.deificAmuletVisMult)).add(Aspect.WATER, (int)(1000.0F * RelicsConfigHandler.deificAmuletVisMult)))) entity.setAir(300);
/*     */       
/*     */       }
/*     */       
/* 116 */       if (RelicsConfigHandler.deificAmuletInvincibility) {
/*     */         
/* 118 */         if ((((ItemNBTHelper.getInt(itemstack, "ICooldown", 0) == 0) ? 1 : 0) & ((entity.hurtResistantTime > 10) ? 1 : 0)) != 0) {
/* 119 */           entity.hurtResistantTime = 40;
/* 120 */           ItemNBTHelper.setInt(itemstack, "ICooldown", 32);
/*     */         } 
/*     */         
/* 123 */         if (ItemNBTHelper.getInt(itemstack, "ICooldown", 0) > 0)
/* 124 */           ItemNBTHelper.setInt(itemstack, "ICooldown", ItemNBTHelper.getInt(itemstack, "ICooldown", 0) - 1); 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\antro\Documents\forgottenRelicsDeobfuscated.jar!\com\integral\forgottenrelics\items\ItemDeificAmulet.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */