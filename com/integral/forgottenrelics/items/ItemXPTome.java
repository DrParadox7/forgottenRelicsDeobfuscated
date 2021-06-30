/*     */ package com.integral.forgottenrelics.items;
/*     */ 
/*     */ import com.integral.forgottenrelics.Main;
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
/*     */ import vazkii.botania.common.core.helper.ExperienceHelper;
/*     */ import vazkii.botania.common.core.helper.ItemNBTHelper;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ItemXPTome
/*     */   extends Item
/*     */ {
/*     */   public static final String TAG_ABSORPTION = "AbsorptionMode";
/*     */   public static final int xpPortion = 5;
/*     */   
/*     */   public ItemXPTome() {
/*  28 */     this.maxStackSize = 1;
/*  29 */     setUnlocalizedName("ItemXPTome");
/*  30 */     setCreativeTab(Main.tabForgottenRelics);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void registerIcons(IIconRegister iconRegister) {
/*  39 */     this.itemIcon = iconRegister.registerIcon("forgottenrelics:XP_Tome");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List<String> par3List, boolean par4) {
/*     */     String cMode;
/*  48 */     if (!ItemNBTHelper.getBoolean(par1ItemStack, "IsActive", false))
/*  49 */     { cMode = StatCollector.translateToLocal("item.ItemXPTomeDeactivated.lore"); }
/*  50 */     else { this; if (ItemNBTHelper.getBoolean(par1ItemStack, "AbsorptionMode", true)) {
/*  51 */         cMode = StatCollector.translateToLocal("item.ItemXPTomeAbsorption.lore");
/*     */       } else {
/*  53 */         cMode = StatCollector.translateToLocal("item.ItemXPTomeExtraction.lore");
/*     */       }  }
/*  55 */      if (GuiScreen.isShiftKeyDown()) {
/*  56 */       par3List.add(StatCollector.translateToLocal("item.ItemXPTome1.lore"));
/*  57 */       par3List.add(StatCollector.translateToLocal("item.FREmpty.lore"));
/*  58 */       par3List.add(StatCollector.translateToLocal("item.ItemXPTome2.lore"));
/*  59 */       par3List.add(StatCollector.translateToLocal("item.ItemXPTome3.lore"));
/*  60 */       par3List.add(StatCollector.translateToLocal("item.ItemXPTome4.lore"));
/*  61 */       par3List.add(StatCollector.translateToLocal("item.FREmpty.lore"));
/*  62 */       par3List.add(StatCollector.translateToLocal("item.ItemXPTome5.lore"));
/*  63 */       par3List.add(StatCollector.translateToLocal("item.ItemXPTome6.lore"));
/*  64 */       par3List.add(StatCollector.translateToLocal("item.FREmpty.lore"));
/*  65 */       par3List.add(StatCollector.translateToLocal("item.ItemXPTome7.lore"));
/*  66 */       par3List.add(StatCollector.translateToLocal("item.ItemXPTome8.lore"));
/*  67 */       par3List.add(StatCollector.translateToLocal("item.ItemXPTome9.lore"));
/*     */     }
/*     */     else {
/*     */       
/*  71 */       par3List.add(StatCollector.translateToLocal("item.FRShiftTooltip.lore"));
/*     */     } 
/*     */     
/*  74 */     par3List.add(StatCollector.translateToLocal("item.FREmpty.lore"));
/*  75 */     par3List.add(StatCollector.translateToLocal("item.ItemXPTomeMode.lore") + " " + cMode);
/*  76 */     par3List.add(StatCollector.translateToLocal("item.FREmpty.lore"));
/*  77 */     par3List.add(StatCollector.translateToLocal("item.ItemXPTomeExp.lore"));
/*  78 */     par3List.add(StatCollector.translateToLocal("item.FRCode6.lore") + ItemNBTHelper.getInt(par1ItemStack, "XPStored", 0) + " " + StatCollector.translateToLocal("item.ItemXPTomeUnits.lore") + " " + ExperienceHelper.getLevelForExperience(ItemNBTHelper.getInt(par1ItemStack, "XPStored", 0)) + " " + StatCollector.translateToLocal("item.ItemXPTomeLevels.lore"));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onUpdate(ItemStack itemstack, World world, Entity entity, int i, boolean b) {
/*  85 */     if (!(entity instanceof EntityPlayer) || world.isRemote || !ItemNBTHelper.getBoolean(itemstack, "IsActive", false)) {
/*     */       return;
/*     */     }
/*  88 */     boolean action = false;
/*     */     
/*  90 */     EntityPlayer player = (EntityPlayer)entity;
/*     */     
/*  92 */     this; if (ItemNBTHelper.getBoolean(itemstack, "AbsorptionMode", true)) {
/*     */       
/*  94 */       this; if (ExperienceHelper.getPlayerXP(player) >= 5) {
/*  95 */         this; ExperienceHelper.drainPlayerXP(player, 5);
/*  96 */         this; ItemNBTHelper.setInt(itemstack, "XPStored", ItemNBTHelper.getInt(itemstack, "XPStored", 0) + 5);
/*  97 */         action = true;
/*     */       } else {
/*  99 */         this; if ((((ExperienceHelper.getPlayerXP(player) > 0) ? 1 : 0) & ((ExperienceHelper.getPlayerXP(player) < 5) ? 1 : 0)) != 0) {
/* 100 */           int exp = ExperienceHelper.getPlayerXP(player);
/* 101 */           ExperienceHelper.drainPlayerXP(player, exp);
/* 102 */           ItemNBTHelper.setInt(itemstack, "XPStored", ItemNBTHelper.getInt(itemstack, "XPStored", 0) + exp);
/* 103 */           action = true;
/*     */         }
/*     */       
/*     */       } 
/*     */     } else {
/*     */       
/* 109 */       int xp = ItemNBTHelper.getInt(itemstack, "XPStored", 0);
/*     */       
/* 111 */       this; if (xp >= 5)
/* 112 */       { this; ItemNBTHelper.setInt(itemstack, "XPStored", xp - 5);
/* 113 */         this; ExperienceHelper.addPlayerXP(player, 5);
/* 114 */         action = true; }
/* 115 */       else { this; if ((((xp > 0) ? 1 : 0) & ((xp < 5) ? 1 : 0)) != 0) {
/* 116 */           ItemNBTHelper.setInt(itemstack, "XPStored", 0);
/* 117 */           ExperienceHelper.addPlayerXP(player, xp);
/* 118 */           action = true;
/*     */         }  }
/*     */     
/*     */     } 
/*     */     
/* 123 */     if (action) {
/* 124 */       player.inventoryContainer.detectAndSendChanges();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
/* 131 */     if (!player.isSneaking()) {
/*     */       
/* 133 */       this; if (ItemNBTHelper.getBoolean(stack, "AbsorptionMode", true)) {
/* 134 */         this; ItemNBTHelper.setBoolean(stack, "AbsorptionMode", false);
/* 135 */         world.playSoundAtEntity((Entity)player, "random.levelup", 1.0F, (float)(0.4000000059604645D + Math.random() * 0.10000000149011612D));
/*     */       } else {
/*     */         
/* 138 */         this; ItemNBTHelper.setBoolean(stack, "AbsorptionMode", true);
/* 139 */         world.playSoundAtEntity((Entity)player, "random.levelup", 1.0F, (float)(0.4000000059604645D + Math.random() * 0.10000000149011612D));
/*     */       }
/*     */     
/*     */     }
/* 143 */     else if (ItemNBTHelper.getBoolean(stack, "IsActive", false)) {
/* 144 */       ItemNBTHelper.setBoolean(stack, "IsActive", false);
/* 145 */       world.playSoundAtEntity((Entity)player, "thaumcraft:hhoff", 1.0F, (float)(0.800000011920929D + Math.random() * 0.20000000298023224D));
/*     */     } else {
/*     */       
/* 148 */       ItemNBTHelper.setBoolean(stack, "IsActive", true);
/* 149 */       world.playSoundAtEntity((Entity)player, "thaumcraft:hhon", 1.0F, (float)(0.800000011920929D + Math.random() * 0.20000000298023224D));
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 155 */     player.swingItem();
/*     */ 
/*     */ 
/*     */     
/* 159 */     return stack;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isFull3D() {
/* 164 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public boolean hasEffect(ItemStack stack, int pass) {
/* 171 */     boolean effect = ItemNBTHelper.getBoolean(stack, "IsActive", false);
/*     */     
/* 173 */     if (effect) {
/* 174 */       return true;
/*     */     }
/* 176 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EnumRarity getRarity(ItemStack itemStack) {
/* 184 */     return EnumRarity.epic;
/*     */   }
/*     */ }


/* Location:              C:\Users\antro\Documents\forgottenRelicsDeobfuscated.jar!\com\integral\forgottenrelics\items\ItemXPTome.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */