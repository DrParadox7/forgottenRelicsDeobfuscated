/*     */ package com.integral.forgottenrelics.items;
/*     */ 
/*     */ import com.integral.forgottenrelics.Main;
/*     */ import com.integral.forgottenrelics.entities.EntityDarkMatterOrb;
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
/*     */ import thaumcraft.api.IWarpingGear;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.api.aspects.AspectList;
/*     */ import thaumcraft.common.config.Config;
/*     */ import thaumcraft.common.items.wands.WandManager;
/*     */ import vazkii.botania.common.core.helper.Vector3;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ItemEldritchSpell
/*     */   extends Item
/*     */   implements IWarpingGear
/*     */ {
/*  38 */   public static final int AerCost = (int)(0.0F * RelicsConfigHandler.eldritchSpellVisMult);
/*  39 */   public static final int TerraCost = (int)(0.0F * RelicsConfigHandler.eldritchSpellVisMult);
/*  40 */   public static final int IgnisCost = (int)(0.0F * RelicsConfigHandler.eldritchSpellVisMult);
/*  41 */   public static final int AquaCost = (int)(0.0F * RelicsConfigHandler.eldritchSpellVisMult);
/*  42 */   public static final int OrdoCost = (int)(0.0F * RelicsConfigHandler.eldritchSpellVisMult);
/*  43 */   public static final int PerditioCost = (int)(400.0F * RelicsConfigHandler.eldritchSpellVisMult);
/*     */ 
/*     */   
/*     */   public ItemEldritchSpell() {
/*  47 */     setMaxStackSize(1);
/*  48 */     setUnlocalizedName("ItemEldritchSpell");
/*  49 */     setCreativeTab(Main.tabForgottenRelics);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onUpdate(ItemStack itemstack, World world, Entity entity, int i, boolean b) {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List<String> par3List, boolean par4) {
/*     */     float theDamage;
/*  64 */     if (par2EntityPlayer.dimension == Config.dimensionOuterId) {
/*  65 */       theDamage = RelicsConfigHandler.eldritchSpellDamageEx;
/*     */     } else {
/*  67 */       theDamage = RelicsConfigHandler.eldritchSpellDamage;
/*     */     } 
/*  69 */     if (GuiScreen.isShiftKeyDown()) {
/*  70 */       par3List.add(StatCollector.translateToLocal("item.ItemEldritchSpell1.lore"));
/*  71 */       par3List.add(StatCollector.translateToLocal("item.ItemEldritchSpell2.lore"));
/*  72 */       par3List.add(StatCollector.translateToLocal("item.ItemEldritchSpell3.lore"));
/*  73 */       par3List.add(StatCollector.translateToLocal("item.FREmpty.lore"));
/*  74 */       par3List.add(StatCollector.translateToLocal("item.ItemEldritchSpell4.lore"));
/*  75 */       par3List.add(StatCollector.translateToLocal("item.ItemEldritchSpell5_1.lore") + " " + theDamage + " " + StatCollector.translateToLocal("item.ItemEldritchSpell5_2.lore"));
/*  76 */       par3List.add(StatCollector.translateToLocal("item.ItemEldritchSpell6.lore"));
/*  77 */       par3List.add(StatCollector.translateToLocal("item.FREmpty.lore"));
/*  78 */       par3List.add(StatCollector.translateToLocal("item.ItemEldritchSpell7.lore"));
/*  79 */     } else if (GuiScreen.isCtrlKeyDown()) {
/*  80 */       par3List.add(StatCollector.translateToLocal("item.FRVisPerCast.lore"));
/*  81 */       this; par3List.add(" " + StatCollector.translateToLocal("item.FRPerditioCost.lore") + (PerditioCost / 100.0D));
/*     */     } else {
/*     */       
/*  84 */       par3List.add(StatCollector.translateToLocal("item.FRShiftTooltip.lore"));
/*  85 */       par3List.add(StatCollector.translateToLocal("item.FRViscostTooltip.lore"));
/*     */     } 
/*     */     
/*  88 */     par3List.add(StatCollector.translateToLocal("item.FREmpty.lore"));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EnumRarity getRarity(ItemStack itemStack) {
/*  95 */     return EnumRarity.epic;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void registerIcons(IIconRegister iconRegister) {
/* 101 */     this.itemIcon = iconRegister.registerIcon("forgottenrelics:Eldritch_Spell");
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean spawnOrb(World world, EntityPlayer player) {
/* 106 */     if (!world.isRemote) {
/*     */       
/* 108 */       Vector3 originalPos = Vector3.fromEntityCenter((Entity)player);
/* 109 */       Vector3 vector = originalPos.add((new Vector3(player.getLookVec())).multiply(1.0D));
/* 110 */       vector.y += 0.5D;
/*     */       
/* 112 */       Vector3 motion = (new Vector3(player.getLookVec())).multiply(1.5D);
/*     */       
/* 114 */       EntityDarkMatterOrb orb = new EntityDarkMatterOrb(world, (EntityLivingBase)player);
/* 115 */       orb.setPosition(vector.x, vector.y, vector.z);
/*     */       
/* 117 */       orb.motionX = motion.x;
/* 118 */       orb.motionY = motion.y;
/* 119 */       orb.motionZ = motion.z;
/*     */       
/* 121 */       world.playSoundEffect(player.posX, player.posY, player.posZ, "thaumcraft:egattack", 0.6F, 0.8F + (float)Math.random() * 0.2F);
/* 122 */       world.spawnEntityInWorld((Entity)orb);
/*     */       
/* 124 */       return true;
/*     */     } 
/*     */     
/* 127 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
/* 133 */     if (!SuperpositionHandler.isOnCoodown(player)) {
/*     */       
/* 135 */       this; if (WandManager.consumeVisFromInventory(player, (new AspectList()).add(Aspect.ENTROPY, PerditioCost))) {
/*     */         
/* 137 */         if (!world.isRemote) {
/* 138 */           Container inventory = player.inventoryContainer;
/* 139 */           ((EntityPlayerMP)player).sendContainerToPlayer(inventory);
/*     */         } 
/*     */         
/* 142 */         spawnOrb(world, player);
/* 143 */         SuperpositionHandler.setCasted(player, 20, true);
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 149 */     return stack;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isFull3D() {
/* 154 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getWarp(ItemStack arg0, EntityPlayer arg1) {
/* 159 */     return 4;
/*     */   }
/*     */ }


/* Location:              C:\Users\antro\Documents\forgottenRelicsDeobfuscated.jar!\com\integral\forgottenrelics\items\ItemEldritchSpell.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */