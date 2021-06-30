/*     */ package com.integral.forgottenrelics.items;
/*     */ 
/*     */ import com.integral.forgottenrelics.Main;
/*     */ import com.integral.forgottenrelics.entities.EntityThunderpealOrb;
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
/*     */ import net.minecraft.entity.player.EntityPlayerMP;
/*     */ import net.minecraft.inventory.Container;
/*     */ import net.minecraft.item.EnumRarity;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.StatCollector;
/*     */ import net.minecraft.world.World;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.api.aspects.AspectList;
/*     */ import thaumcraft.common.items.wands.WandManager;
/*     */ import vazkii.botania.common.core.helper.Vector3;
/*     */ 
/*     */ public class ItemThunderpeal
/*     */   extends Item
/*     */ {
/*  30 */   public static final int AerCost = (int)(135.0F * RelicsConfigHandler.thunderpealVisMult);
/*  31 */   public static final int TerraCost = (int)(0.0F * RelicsConfigHandler.thunderpealVisMult);
/*  32 */   public static final int IgnisCost = (int)(85.0F * RelicsConfigHandler.thunderpealVisMult);
/*  33 */   public static final int AquaCost = (int)(0.0F * RelicsConfigHandler.thunderpealVisMult);
/*  34 */   public static final int OrdoCost = (int)(0.0F * RelicsConfigHandler.thunderpealVisMult);
/*  35 */   public static final int PerditioCost = (int)(0.0F * RelicsConfigHandler.thunderpealVisMult);
/*     */ 
/*     */   
/*     */   public ItemThunderpeal() {
/*  39 */     setMaxStackSize(1);
/*  40 */     setUnlocalizedName("ItemThunderpeal");
/*  41 */     setCreativeTab(Main.tabForgottenRelics);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List<String> par3List, boolean par4) {
/*  49 */     if (GuiScreen.isShiftKeyDown()) {
/*  50 */       par3List.add(StatCollector.translateToLocal("item.ItemThunderpeal1.lore"));
/*  51 */       par3List.add(StatCollector.translateToLocal("item.FREmpty.lore"));
/*  52 */       par3List.add(StatCollector.translateToLocal("item.ItemThunderpeal2.lore"));
/*  53 */       par3List.add(StatCollector.translateToLocal("item.ItemThunderpeal3_1.lore") + " " + '\030' + " " + StatCollector.translateToLocal("item.ItemThunderpeal3_2.lore"));
/*  54 */       par3List.add(StatCollector.translateToLocal("item.ItemThunderpeal4.lore"));
/*  55 */       par3List.add(StatCollector.translateToLocal("item.FREmpty.lore"));
/*  56 */       par3List.add(StatCollector.translateToLocal("item.ItemThunderpeal5_1.lore") + " " + '\020' + " " + StatCollector.translateToLocal("item.ItemThunderpeal5_2.lore"));
/*  57 */       par3List.add(StatCollector.translateToLocal("item.ItemThunderpeal6.lore"));
/*  58 */       par3List.add(StatCollector.translateToLocal("item.ItemThunderpeal7.lore"));
/*  59 */     } else if (GuiScreen.isCtrlKeyDown()) {
/*  60 */       par3List.add(StatCollector.translateToLocal("item.FRVisPerCast.lore"));
/*  61 */       this; par3List.add(" " + StatCollector.translateToLocal("item.FRAerCost.lore") + (AerCost / 100.0D));
/*  62 */       this; par3List.add(" " + StatCollector.translateToLocal("item.FRIgnisCost.lore") + (IgnisCost / 100.0D));
/*     */     } else {
/*     */       
/*  65 */       par3List.add(StatCollector.translateToLocal("item.FRShiftTooltip.lore"));
/*  66 */       par3List.add(StatCollector.translateToLocal("item.FRViscostTooltip.lore"));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EnumRarity getRarity(ItemStack itemStack) {
/*  75 */     return EnumRarity.epic;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void registerIcons(IIconRegister iconRegister) {
/*  81 */     this.itemIcon = iconRegister.registerIcon("forgottenrelics:Thunderpeal");
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean spawnOrb(World world, EntityPlayer player) {
/*  86 */     if (!world.isRemote) {
/*     */       
/*  88 */       Vector3 originalPos = Vector3.fromEntityCenter((Entity)player);
/*  89 */       Vector3 vector = originalPos.add((new Vector3(player.getLookVec())).multiply(1.25D));
/*  90 */       vector.y += 0.5D;
/*     */       
/*  92 */       Vector3 motion = (new Vector3(player.getLookVec())).multiply(1.5D);
/*     */       
/*  94 */       EntityThunderpealOrb orb = new EntityThunderpealOrb(world, (EntityLivingBase)player);
/*  95 */       orb.setPosition(vector.x, vector.y, vector.z);
/*     */       
/*  97 */       orb.area += 2;
/*  98 */       orb.motionX = motion.x;
/*  99 */       orb.motionY = motion.y;
/* 100 */       orb.motionZ = motion.z;
/*     */       
/* 102 */       world.playSoundAtEntity((Entity)orb, "thaumcraft:zap", 1.0F, 1.0F + (world.rand.nextFloat() - world.rand.nextFloat()) * 0.2F);
/* 103 */       world.spawnEntityInWorld((Entity)orb);
/*     */       
/* 105 */       return true;
/*     */     } 
/*     */     
/* 108 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
/* 115 */     this; this; if (((!SuperpositionHandler.isOnCoodown(player) ? 1 : 0) & (!world.isRemote ? 1 : 0)) != 0 && WandManager.consumeVisFromInventory(player, (new AspectList()).add(Aspect.AIR, AerCost).add(Aspect.FIRE, IgnisCost))) {
/*     */       
/* 117 */       Container inventory = player.inventoryContainer;
/* 118 */       ((EntityPlayerMP)player).sendContainerToPlayer(inventory);
/*     */       
/* 120 */       spawnOrb(world, player);
/* 121 */       SuperpositionHandler.setCasted(player, 30, true);
/*     */     } 
/*     */     
/* 124 */     return stack;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isFull3D() {
/* 129 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\antro\Documents\forgottenRelicsDeobfuscated.jar!\com\integral\forgottenrelics\items\ItemThunderpeal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */