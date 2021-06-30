/*     */ package com.integral.forgottenrelics.items;
/*     */ 
/*     */ import com.integral.forgottenrelics.Main;
/*     */ import com.integral.forgottenrelics.entities.EntityRageousMissile;
/*     */ import com.integral.forgottenrelics.handlers.RelicsConfigHandler;
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
/*     */ import thaumcraft.api.IWarpingGear;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.api.aspects.AspectList;
/*     */ import thaumcraft.common.Thaumcraft;
/*     */ import thaumcraft.common.items.wands.WandManager;
/*     */ import vazkii.botania.common.Botania;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ItemMissileTome
/*     */   extends Item
/*     */   implements IWarpingGear
/*     */ {
/*  38 */   public static final int AerCost = (int)(0.0F * RelicsConfigHandler.nuclearFuryVisMult);
/*  39 */   public static final int TerraCost = (int)(0.0F * RelicsConfigHandler.nuclearFuryVisMult);
/*  40 */   public static final int IgnisCost = (int)(20.0F * RelicsConfigHandler.nuclearFuryVisMult);
/*  41 */   public static final int AquaCost = (int)(0.0F * RelicsConfigHandler.nuclearFuryVisMult);
/*  42 */   public static final int OrdoCost = (int)(10.0F * RelicsConfigHandler.nuclearFuryVisMult);
/*  43 */   public static final int PerditioCost = (int)(15.0F * RelicsConfigHandler.nuclearFuryVisMult);
/*     */ 
/*     */   
/*     */   public ItemMissileTome() {
/*  47 */     setMaxStackSize(1);
/*  48 */     setUnlocalizedName("ItemMissileTome");
/*  49 */     setCreativeTab(Main.tabForgottenRelics);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List<String> par3List, boolean par4) {
/*  56 */     if (GuiScreen.isShiftKeyDown()) {
/*  57 */       par3List.add(StatCollector.translateToLocal("item.ItemMissileTome1.lore"));
/*  58 */       par3List.add(StatCollector.translateToLocal("item.ItemMissileTome2.lore"));
/*  59 */       par3List.add(StatCollector.translateToLocal("item.ItemMissileTome3.lore"));
/*  60 */       par3List.add(StatCollector.translateToLocal("item.FREmpty.lore"));
/*  61 */       par3List.add(StatCollector.translateToLocal("item.ItemMissileTome4.lore"));
/*  62 */       par3List.add(StatCollector.translateToLocal("item.ItemMissileTome5.lore"));
/*  63 */       par3List.add(StatCollector.translateToLocal("item.FREmpty.lore"));
/*  64 */       par3List.add(StatCollector.translateToLocal("item.ItemMissileTome6.lore"));
/*  65 */       par3List.add(StatCollector.translateToLocal("item.FREmpty.lore"));
/*  66 */       par3List.add(StatCollector.translateToLocal("item.ItemMissileTome7_1.lore") + " " + (int)RelicsConfigHandler.nuclearFuryDamageMIN + "-" + (int)RelicsConfigHandler.nuclearFuryDamageMAX + " " + StatCollector.translateToLocal("item.ItemMissileTome7_2.lore"));
/*  67 */     } else if (GuiScreen.isCtrlKeyDown()) {
/*  68 */       par3List.add(StatCollector.translateToLocal("item.FRVisPerSecond.lore"));
/*  69 */       this; par3List.add(" " + StatCollector.translateToLocal("item.FRIgnisCost.lore") + (IgnisCost / 100.0D * 10.0D));
/*  70 */       this; par3List.add(" " + StatCollector.translateToLocal("item.FROrdoCost.lore") + (OrdoCost / 100.0D * 10.0D));
/*  71 */       this; par3List.add(" " + StatCollector.translateToLocal("item.FRPerditioCost.lore") + (PerditioCost / 100.0D * 10.0D));
/*     */     } else {
/*  73 */       par3List.add(StatCollector.translateToLocal("item.FRShiftTooltip.lore"));
/*  74 */       par3List.add(StatCollector.translateToLocal("item.FRViscostTooltip.lore"));
/*     */     } 
/*     */     
/*  77 */     par3List.add(StatCollector.translateToLocal("item.FREmpty.lore"));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EnumRarity getRarity(ItemStack itemStack) {
/*  84 */     return EnumRarity.epic;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void registerIcons(IIconRegister iconRegister) {
/*  90 */     this.itemIcon = iconRegister.registerIcon("forgottenrelics:Missile_Tome");
/*     */   }
/*     */ 
/*     */   
/*     */   public EnumAction getItemUseAction(ItemStack par1ItemStack) {
/*  95 */     return EnumAction.bow;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getMaxItemUseDuration(ItemStack par1ItemStack) {
/* 100 */     return 72000;
/*     */   }
/*     */ 
/*     */   
/*     */   public void onUsingTick(ItemStack stack, EntityPlayer player, int count) {
/* 105 */     if (count != getMaxItemUseDuration(stack) && count % 2 == 0 && !player.worldObj.isRemote) {
/*     */       
/* 107 */       this; this; this; if (WandManager.consumeVisFromInventory(player, (new AspectList()).add(Aspect.FIRE, IgnisCost).add(Aspect.ORDER, OrdoCost).add(Aspect.ENTROPY, PerditioCost)))
/* 108 */         spawnMissile(player.worldObj, player, player.posX + (Math.random() - 0.5D) * 3.1D, player.posY + 3.8D + Math.random() - 1.55D, player.posZ + (Math.random() - 0.5D) * 3.1D); 
/* 109 */       Botania.proxy.sparkleFX(player.worldObj, player.posX, player.posY + 2.4D, player.posZ, 1.0F, 0.4F, 1.0F, 6.0F, 6);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean spawnMissile(World world, EntityPlayer thrower, double x, double y, double z) {
/* 115 */     if (thrower != null) {
/* 116 */       EntityRageousMissile missile = new EntityRageousMissile(thrower, false);
/*     */       
/* 118 */       missile.setPosition(x, y, z);
/* 119 */       if (!world.isRemote) {
/* 120 */         world.playSoundEffect(x, y, z, "botania:missile", 0.6F, 0.8F + (float)Math.random() * 0.2F);
/* 121 */         world.spawnEntityInWorld((Entity)missile);
/*     */         
/* 123 */         Thaumcraft.proxy.burst(thrower.worldObj, x, y, z, 0.25F);
/*     */         
/* 125 */         return true;
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 130 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
/* 135 */     par3EntityPlayer.setItemInUse(par1ItemStack, getMaxItemUseDuration(par1ItemStack));
/* 136 */     return par1ItemStack;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isFull3D() {
/* 141 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getWarp(ItemStack arg0, EntityPlayer arg1) {
/* 146 */     return 5;
/*     */   }
/*     */ }


/* Location:              C:\Users\antro\Documents\forgottenRelicsDeobfuscated.jar!\com\integral\forgottenrelics\items\ItemMissileTome.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */