/*     */ package com.integral.forgottenrelics.items;
/*     */ 
/*     */ import com.integral.forgottenrelics.Main;
/*     */ import com.integral.forgottenrelics.entities.EntityBabylonWeaponSS;
/*     */ import com.integral.forgottenrelics.handlers.RelicsConfigHandler;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.List;
/*     */ import net.minecraft.client.gui.GuiScreen;
/*     */ import net.minecraft.client.renderer.texture.IIconRegister;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.EnumAction;
/*     */ import net.minecraft.item.EnumRarity;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.StatCollector;
/*     */ import net.minecraft.world.World;
/*     */ import thaumcraft.api.IWarpingGear;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.api.aspects.AspectList;
/*     */ import thaumcraft.common.items.wands.WandManager;
/*     */ import vazkii.botania.common.core.helper.Vector3;
/*     */ 
/*     */ public class ItemApotheosis
/*     */   extends Item implements IWarpingGear {
/*  30 */   public static final int AerCost = (int)(0.0F * RelicsConfigHandler.apotheosisVisMult);
/*  31 */   public static final int TerraCost = (int)(30.0F * RelicsConfigHandler.apotheosisVisMult);
/*  32 */   public static final int IgnisCost = (int)(60.0F * RelicsConfigHandler.apotheosisVisMult);
/*  33 */   public static final int AquaCost = (int)(0.0F * RelicsConfigHandler.apotheosisVisMult);
/*  34 */   public static final int OrdoCost = (int)(50.0F * RelicsConfigHandler.apotheosisVisMult);
/*  35 */   public static final int PerditioCost = (int)(75.0F * RelicsConfigHandler.apotheosisVisMult);
/*     */ 
/*     */   
/*     */   public ItemApotheosis() {
/*  39 */     this.maxStackSize = 1;
/*  40 */     setUnlocalizedName("ItemApotheosis");
/*  41 */     setCreativeTab(Main.tabForgottenRelics);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void registerIcons(IIconRegister iconRegister) {
/*  49 */     this.itemIcon = iconRegister.registerIcon("forgottenrelics:Apotheosis");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List<String> par3List, boolean par4) {
/*  57 */     if (GuiScreen.isShiftKeyDown()) {
/*  58 */       par3List.add(StatCollector.translateToLocal("item.ItemApotheosis1.lore"));
/*  59 */       par3List.add(StatCollector.translateToLocal("item.FREmpty.lore"));
/*  60 */       par3List.add(StatCollector.translateToLocal("item.ItemApotheosis2.lore"));
/*  61 */       par3List.add(StatCollector.translateToLocal("item.ItemApotheosis3.lore"));
/*  62 */       par3List.add(StatCollector.translateToLocal("item.FREmpty.lore"));
/*  63 */       par3List.add(StatCollector.translateToLocal("item.ItemApotheosis4_1.lore") + " " + (int)RelicsConfigHandler.damageApotheosisDirect + " " + StatCollector.translateToLocal("item.ItemApotheosis4_2.lore"));
/*  64 */       par3List.add(StatCollector.translateToLocal("item.ItemApotheosis5_1.lore") + " " + (int)RelicsConfigHandler.damageApotheosisImpact + " " + StatCollector.translateToLocal("item.ItemApotheosis5_2.lore"));
/*  65 */       par3List.add(StatCollector.translateToLocal("item.ItemApotheosis6.lore"));
/*  66 */     } else if (GuiScreen.isCtrlKeyDown()) {
/*  67 */       par3List.add(StatCollector.translateToLocal("item.FRVisPerSecond.lore"));
/*  68 */       this; par3List.add(" " + StatCollector.translateToLocal("item.FRTerraCost.lore") + (TerraCost / 100.0D * 10.0D));
/*  69 */       this; par3List.add(" " + StatCollector.translateToLocal("item.FRIgnisCost.lore") + (IgnisCost / 100.0D * 10.0D));
/*  70 */       this; par3List.add(" " + StatCollector.translateToLocal("item.FROrdoCost.lore") + (OrdoCost / 100.0D * 10.0D));
/*  71 */       this; par3List.add(" " + StatCollector.translateToLocal("item.FRPerditioCost.lore") + (PerditioCost / 100.0D * 10.0D));
/*     */     } else {
/*     */       
/*  74 */       par3List.add(StatCollector.translateToLocal("item.FRShiftTooltip.lore"));
/*  75 */       par3List.add(StatCollector.translateToLocal("item.FRViscostTooltip.lore"));
/*     */     } 
/*     */     
/*  78 */     par3List.add(StatCollector.translateToLocal("item.FREmpty.lore"));
/*     */   }
/*     */   
/*     */   public void spawnBabylonWeapon(EntityPlayer player) {
/*  82 */     if (!player.worldObj.isRemote) {
/*  83 */       double yaw; Vector3 originalPosX = Vector3.fromEntityCenter((Entity)player);
/*  84 */       EntityBabylonWeaponSS weapon = new EntityBabylonWeaponSS(player.worldObj, (EntityLivingBase)player);
/*     */       
/*  86 */       double rawYaw = player.getRotationYawHead();
/*     */ 
/*     */       
/*  89 */       if (rawYaw < 0.0D) {
/*  90 */         yaw = Math.abs(rawYaw);
/*     */       } else {
/*  92 */         yaw = 360.0D - rawYaw;
/*     */       } 
/*  94 */       double x = 0.0D;
/*  95 */       double z = 0.0D;
/*     */       
/*  97 */       if ((((yaw >= 0.0D) ? 1 : 0) & ((yaw <= 90.0D) ? 1 : 0)) != 0) {
/*     */         
/*  99 */         double m = (yaw - 0.0D) / 90.0D;
/*     */         
/* 101 */         z = 1.0D - m;
/* 102 */         x = m;
/*     */       }
/* 104 */       else if ((((yaw >= 90.0D) ? 1 : 0) & ((yaw <= 180.0D) ? 1 : 0)) != 0) {
/*     */         
/* 106 */         double m = (yaw - 90.0D) / 90.0D;
/*     */         
/* 108 */         x = 1.0D - m;
/* 109 */         z = -m;
/*     */       }
/* 111 */       else if ((((yaw >= 180.0D) ? 1 : 0) & ((yaw <= 270.0D) ? 1 : 0)) != 0) {
/*     */         
/* 113 */         double m = (yaw - 180.0D) / 90.0D;
/*     */         
/* 115 */         z = -(1.0D - m);
/* 116 */         x = -m;
/*     */       }
/* 118 */       else if ((((yaw >= 270.0D) ? 1 : 0) & ((yaw <= 360.0D) ? 1 : 0)) != 0) {
/*     */         
/* 120 */         double m = (yaw - 270.0D) / 90.0D;
/*     */         
/* 122 */         x = -(1.0D - m);
/* 123 */         z = m;
/*     */       } 
/*     */ 
/*     */       
/* 127 */       double multV2 = yaw % 90.0D;
/*     */       
/* 129 */       if (multV2 >= 45.0D)
/*     */       {
/* 131 */         if (multV2 > 45.0D)
/* 132 */           multV2 = 45.0D - multV2 - 45.0D; 
/*     */       }
/* 134 */       double multV3 = 1.0D + multV2 / 90.0D;
/*     */       
/* 136 */       Vector3 lookV = new Vector3(x * multV3, 0.0D, z * multV3);
/* 137 */       Vector3 additive = lookV.copy();
/*     */       
/* 139 */       for (int supercounter = 0; supercounter <= 100; ) {
/*     */         double negative;
/* 141 */         Vector3 finalVec = lookV.copy();
/*     */ 
/*     */ 
/*     */         
/* 145 */         if (Math.random() >= 0.5D) {
/* 146 */           negative = -1.0D;
/*     */         } else {
/* 148 */           negative = 1.0D;
/*     */         } 
/* 150 */         finalVec.rotate(80.0D * negative, new Vector3(0.0D, 1.0D, 0.0D));
/*     */         
/* 152 */         finalVec.multiply(2.0D + Math.random() * 10.0D);
/* 153 */         finalVec.add(additive.copy().multiply(Math.random() * 1.0D));
/*     */         
/* 155 */         finalVec.y += -0.5D + Math.random() * 8.0D;
/*     */         
/* 157 */         finalVec.add(originalPosX.copy());
/*     */         
/* 159 */         double range = 2.0D;
/* 160 */         List<EntityBabylonWeaponSS> weapons = player.worldObj.getEntitiesWithinAABB(EntityBabylonWeaponSS.class, AxisAlignedBB.getBoundingBox(finalVec.x - range, finalVec.y - range, finalVec.z - range, finalVec.x + range, finalVec.y + range, finalVec.z + range));
/*     */         
/* 162 */         if (weapons.size() > 0) {
/*     */           supercounter++; continue;
/*     */         } 
/* 165 */         lookV = finalVec;
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 170 */       weapon.posX = lookV.x;
/* 171 */       weapon.posY = lookV.y;
/* 172 */       weapon.posZ = lookV.z;
/* 173 */       weapon.rotationYaw = player.rotationYawHead;
/* 174 */       weapon.setVariety(itemRand.nextInt(12));
/* 175 */       weapon.setDelay(0);
/* 176 */       weapon.setRotation(MathHelper.wrapAngleTo180_float(-player.rotationYawHead + 180.0F));
/*     */       
/* 178 */       player.worldObj.spawnEntityInWorld((Entity)weapon);
/* 179 */       player.worldObj.playSoundAtEntity((Entity)weapon, "botania:babylonSpawn", 1.0F, 1.0F + player.worldObj.rand.nextFloat() * 3.0F);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
/* 185 */     player.setItemInUse(stack, stack.getMaxItemUseDuration());
/*     */     
/* 187 */     return stack;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isFull3D() {
/* 192 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public EnumAction getItemUseAction(ItemStack par1ItemStack) {
/* 197 */     return EnumAction.bow;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getMaxItemUseDuration(ItemStack par1ItemStack) {
/* 202 */     return 72000;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void onUsingTick(ItemStack stack, EntityPlayer player, int count) {
/* 208 */     if ((((count != stack.getMaxItemUseDuration()) ? 1 : 0) & ((count % 2 == 0) ? 1 : 0) & (!player.worldObj.isRemote ? 1 : 0)) != 0) {
/*     */       
/* 210 */       this; this; this; this; if (WandManager.consumeVisFromInventory(player, (new AspectList()).add(Aspect.EARTH, TerraCost).add(Aspect.FIRE, IgnisCost).add(Aspect.ORDER, OrdoCost).add(Aspect.ENTROPY, PerditioCost))) {
/* 211 */         spawnBabylonWeapon(player);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EnumRarity getRarity(ItemStack itemStack) {
/* 222 */     return EnumRarity.epic;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getWarp(ItemStack arg0, EntityPlayer arg1) {
/* 227 */     return 5;
/*     */   }
/*     */ }


/* Location:              C:\Users\antro\Documents\forgottenRelicsDeobfuscated.jar!\com\integral\forgottenrelics\items\ItemApotheosis.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */