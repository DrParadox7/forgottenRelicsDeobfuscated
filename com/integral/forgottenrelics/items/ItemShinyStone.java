/*     */ package com.integral.forgottenrelics.items;
/*     */ 
/*     */ import baubles.api.BaubleType;
/*     */ import baubles.api.IBauble;
/*     */ import com.integral.forgottenrelics.Main;
/*     */ import com.integral.forgottenrelics.entities.EntityShinyEnergy;
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
/*     */ import vazkii.botania.common.core.helper.ItemNBTHelper;
/*     */ import vazkii.botania.common.core.helper.Vector3;
/*     */ 
/*     */ public class ItemShinyStone
/*     */   extends ItemBaubleBase
/*     */   implements IBauble {
/*     */   public ItemShinyStone() {
/*  27 */     super("ItemShinyStone");
/*  28 */     this.maxStackSize = 1;
/*  29 */     setUnlocalizedName("ItemShinyStone");
/*  30 */     setCreativeTab(Main.tabForgottenRelics);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void registerIcons(IIconRegister iconRegister) {
/*  38 */     this.itemIcon = iconRegister.registerIcon("forgottenrelics:Shiny_Stone");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List<String> par3List, boolean par4) {
/*  46 */     if (GuiScreen.isShiftKeyDown()) {
/*  47 */       par3List.add(StatCollector.translateToLocal("item.ItemShinyStone1.lore"));
/*  48 */       par3List.add(StatCollector.translateToLocal("item.ItemShinyStone2.lore"));
/*  49 */       par3List.add(StatCollector.translateToLocal("item.FREmpty.lore"));
/*  50 */       par3List.add(SuperpositionHandler.getBaubleTooltip(getBaubleType(par1ItemStack)));
/*     */     } else {
/*     */       
/*  53 */       par3List.add(StatCollector.translateToLocal("item.FRShiftTooltip.lore"));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void spawnEnergyParticle(EntityLivingBase entity) {
/*  59 */     EntityShinyEnergy energy = new EntityShinyEnergy(entity.worldObj, entity, entity, entity.posX, entity.posY, entity.posZ);
/*     */     
/*  61 */     Vector3 position = Vector3.fromEntityCenter((Entity)entity);
/*     */ 
/*     */     
/*  64 */     Vector3 motVec = new Vector3((Math.random() - 0.5D) * 3.0D, (Math.random() - 0.5D) * 3.0D, (Math.random() - 0.5D) * 3.0D);
/*  65 */     position.add(motVec);
/*  66 */     motVec.normalize().negate().multiply(0.1D);
/*     */     
/*  68 */     energy.setPosition(position.x, position.y, position.z);
/*  69 */     energy.motionX = motVec.x;
/*  70 */     energy.motionY = motVec.y;
/*  71 */     energy.motionZ = motVec.z;
/*     */     
/*  73 */     entity.worldObj.spawnEntityInWorld((Entity)energy);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EnumRarity getRarity(ItemStack itemStack) {
/*  80 */     return EnumRarity.epic;
/*     */   }
/*     */ 
/*     */   
/*     */   public BaubleType getBaubleType(ItemStack arg0) {
/*  85 */     return BaubleType.AMULET;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void onWornTick(ItemStack itemstack, EntityLivingBase entity) {
/*  91 */     super.onWornTick(itemstack, entity);
/*     */     
/*  93 */     if (((!entity.worldObj.isRemote ? 1 : 0) & ((entity.ticksExisted % RelicsConfigHandler.shinyStoneCheckrate == 0) ? 1 : 0)) != 0) {
/*     */       
/*  95 */       double posX = ItemNBTHelper.getDouble(itemstack, "LastX", 0.0D);
/*  96 */       double posY = ItemNBTHelper.getDouble(itemstack, "LastY", 0.0D);
/*  97 */       double posZ = ItemNBTHelper.getDouble(itemstack, "LastZ", 0.0D);
/*     */       
/*  99 */       int Static = ItemNBTHelper.getInt(itemstack, "Static", 0);
/*     */       
/* 101 */       if ((((entity.posX == posX) ? 1 : 0) & ((entity.posY == posY) ? 1 : 0) & ((entity.posZ == posZ) ? 1 : 0)) != 0) {
/*     */         
/* 103 */         int particleNumber = 3;
/*     */         
/* 105 */         ItemNBTHelper.setInt(itemstack, "HealRate", 1);
/*     */         
/* 107 */         if (Static >= 40) {
/* 108 */           ItemNBTHelper.setInt(itemstack, "HealRate", 2);
/* 109 */           particleNumber = 2;
/*     */         } 
/* 111 */         if (Static >= 80) {
/* 112 */           ItemNBTHelper.setInt(itemstack, "HealRate", 3);
/* 113 */           particleNumber = 1;
/*     */         } 
/* 115 */         if (Static >= 200) {
/* 116 */           ItemNBTHelper.setInt(itemstack, "HealRate", 4);
/* 117 */           particleNumber = 0;
/*     */         } 
/*     */         
/* 120 */         for (int counter = particleNumber; counter <= 3; counter++) {
/* 121 */           spawnEnergyParticle(entity);
/*     */         }
/*     */         
/* 124 */         ItemNBTHelper.setInt(itemstack, "Static", Static + 4);
/*     */       } else {
/* 126 */         ItemNBTHelper.setInt(itemstack, "Static", 0);
/* 127 */         ItemNBTHelper.setInt(itemstack, "HealRate", 0);
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 132 */       ItemNBTHelper.setDouble(itemstack, "LastX", entity.posX);
/* 133 */       ItemNBTHelper.setDouble(itemstack, "LastY", entity.posY);
/* 134 */       ItemNBTHelper.setDouble(itemstack, "LastZ", entity.posZ);
/*     */     } 
/*     */ 
/*     */     
/* 138 */     int healRate = ItemNBTHelper.getInt(itemstack, "HealRate", 0);
/* 139 */     int healCheckrate = (int)(RelicsConfigHandler.shinyStoneCheckrate / 4.0D);
/*     */     
/* 141 */     if ((((healRate == 1) ? 1 : 0) & ((entity.ticksExisted % 10 * healCheckrate == 0) ? 1 : 0)) != 0) {
/* 142 */       entity.heal(1.0F);
/* 143 */     } else if ((((healRate == 2) ? 1 : 0) & ((entity.ticksExisted % 5 * healCheckrate == 0) ? 1 : 0)) != 0) {
/* 144 */       entity.heal(1.0F);
/* 145 */     } else if ((((healRate == 3) ? 1 : 0) & ((entity.ticksExisted % 2 * healCheckrate == 0) ? 1 : 0)) != 0) {
/* 146 */       entity.heal(1.0F);
/* 147 */     } else if ((((healRate == 4) ? 1 : 0) & ((entity.ticksExisted % 1 * healCheckrate == 0) ? 1 : 0)) != 0) {
/* 148 */       entity.heal(1.0F);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\antro\Documents\forgottenRelicsDeobfuscated.jar!\com\integral\forgottenrelics\items\ItemShinyStone.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */