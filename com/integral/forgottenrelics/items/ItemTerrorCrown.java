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
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.item.EnumRarity;
/*     */ import net.minecraft.item.ItemArmor;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.potion.Potion;
/*     */ import net.minecraft.potion.PotionEffect;
/*     */ import net.minecraft.util.StatCollector;
/*     */ import net.minecraft.world.World;
/*     */ import thaumcraft.api.IGoggles;
/*     */ import thaumcraft.api.IRepairable;
/*     */ import thaumcraft.api.IWarpingGear;
/*     */ import thaumcraft.api.nodes.IRevealer;
/*     */ import thaumcraft.common.lib.utils.EntityUtils;
/*     */ import vazkii.botania.api.mana.ManaItemHandler;
/*     */ import vazkii.botania.common.core.helper.ItemNBTHelper;
/*     */ 
/*     */ public class ItemTerrorCrown
/*     */   extends ItemArmor
/*     */   implements IWarpingGear, IRevealer, IGoggles, IRepairable
/*     */ {
/*     */   public ItemTerrorCrown(int type, ItemArmor.ArmorMaterial mat) {
/*  35 */     super(mat, 0, type);
/*  36 */     setCreativeTab(Main.tabForgottenRelics);
/*  37 */     setUnlocalizedName("ItemTerrorCrown");
/*  38 */     setMaxDamage(1000);
/*     */   }
/*     */   
/*     */   public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack) {
/*  42 */     SuperpositionHandler.cryHavoc(world, player, 24);
/*  43 */     onUpdate(itemStack, world, (Entity)player, 0, false);
/*     */     
/*  45 */     if (!world.isRemote) {
/*     */       
/*  47 */       Entity scannedEntity = EntityUtils.getPointedEntity(world, (Entity)player, 0.0D, 32.0D, 3.0F);
/*  48 */       if (scannedEntity instanceof EntityLivingBase) {
/*  49 */         EntityLivingBase targetEntity = (EntityLivingBase)scannedEntity;
/*     */ 
/*     */         
/*     */         try {
/*  53 */           targetEntity.addPotionEffect(new PotionEffect(Potion.blindness.id, 100, 2, true));
/*  54 */           if (!targetEntity.isPotionActive(Potion.wither))
/*  55 */             targetEntity.addPotionEffect(new PotionEffect(Potion.wither.id, 40, 0, false)); 
/*  56 */           targetEntity.addPotionEffect(new PotionEffect(Potion.confusion.id, 100, 1, true));
/*  57 */           targetEntity.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 30, 1, true));
/*  58 */           targetEntity.addPotionEffect(new PotionEffect(Potion.weakness.id, 80, 2, true));
/*  59 */         } catch (Exception exception) {}
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
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public boolean hasEffect(ItemStack par1ItemStack, int pass) {
/* 132 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getItemEnchantability() {
/* 137 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void registerIcons(IIconRegister iconRegister) {
/* 143 */     this.itemIcon = iconRegister.registerIcon("forgottenrelics:Terror_Crown");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void onUpdate(ItemStack itemstack, World world, Entity entity, int i, boolean b) {
/* 149 */     if (itemstack.isItemEnchanted()) {
/*     */       
/* 151 */       NBTTagCompound nbt = ItemNBTHelper.getNBT(itemstack);
/* 152 */       nbt.removeTag("ench");
/* 153 */       itemstack.setTagCompound(nbt);
/*     */     } 
/*     */ 
/*     */     
/* 157 */     if (entity instanceof EntityPlayer) {
/* 158 */       EntityPlayer player = (EntityPlayer)entity;
/*     */       
/* 160 */       if (!world.isRemote && itemstack.getItemDamage() > 0 && ManaItemHandler.requestManaExact(itemstack, player, 200, true)) {
/* 161 */         itemstack.setItemDamage(itemstack.getItemDamage() - 1);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List<String> par3List, boolean par4) {
/* 172 */     if (GuiScreen.isShiftKeyDown()) {
/* 173 */       par3List.add(StatCollector.translateToLocal("item.ItemTerrorCrown1.lore"));
/* 174 */       par3List.add(StatCollector.translateToLocal("item.ItemTerrorCrown2.lore"));
/* 175 */       par3List.add(StatCollector.translateToLocal("item.FREmpty.lore"));
/* 176 */       par3List.add(StatCollector.translateToLocal("item.ItemTerrorCrown3.lore"));
/* 177 */       par3List.add(StatCollector.translateToLocal("item.FREmpty.lore"));
/* 178 */       par3List.add(StatCollector.translateToLocal("item.ItemTerrorCrown4.lore"));
/*     */     } else {
/*     */       
/* 181 */       par3List.add(StatCollector.translateToLocal("item.FRShiftTooltip.lore"));
/*     */     } 
/*     */ 
/*     */     
/* 185 */     par3List.add(StatCollector.translateToLocal("item.FREmpty.lore"));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public EnumRarity getRarity(ItemStack itemStack) {
/* 191 */     return EnumRarity.epic;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getWarp(ItemStack arg0, EntityPlayer arg1) {
/* 196 */     return 3;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getArmorTexture(ItemStack armor, Entity entity, int slot, String type) {
/* 201 */     return "forgottenrelics:textures/armor/crown_prs.png";
/*     */   }
/*     */   
/*     */   public boolean getIsRepairable(ItemStack par1ItemStack, ItemStack par2ItemStack) {
/* 205 */     return (par2ItemStack.isItemEqual(new ItemStack(Items.gold_ingot)) || super.getIsRepairable(par1ItemStack, par2ItemStack));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean showIngamePopups(ItemStack arg0, EntityLivingBase arg1) {
/* 210 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean showNodes(ItemStack arg0, EntityLivingBase arg1) {
/* 215 */     return true;
/*     */   }
/*     */ }


/* Location:              C:\Users\antro\Documents\forgottenRelicsDeobfuscated.jar!\com\integral\forgottenrelics\items\ItemTerrorCrown.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */