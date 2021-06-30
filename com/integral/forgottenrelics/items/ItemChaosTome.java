/*     */ package com.integral.forgottenrelics.items;
/*     */ 
/*     */ import com.integral.forgottenrelics.Main;
/*     */ import com.integral.forgottenrelics.entities.EntityChaoticOrb;
/*     */ import com.integral.forgottenrelics.handlers.RelicsConfigHandler;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.math.BigDecimal;
/*     */ import java.math.RoundingMode;
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
/*     */ import net.minecraft.util.StatCollector;
/*     */ import net.minecraft.world.World;
/*     */ import thaumcraft.api.IWarpingGear;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.api.aspects.AspectList;
/*     */ import thaumcraft.common.items.wands.WandManager;
/*     */ import vazkii.botania.common.core.helper.Vector3;
/*     */ 
/*     */ public class ItemChaosTome
/*     */   extends Item implements IWarpingGear {
/*  30 */   public static final int AerCost = (int)(100.0F * RelicsConfigHandler.chaosTomeVisMult);
/*  31 */   public static final int TerraCost = (int)(100.0F * RelicsConfigHandler.chaosTomeVisMult);
/*  32 */   public static final int IgnisCost = (int)(100.0F * RelicsConfigHandler.chaosTomeVisMult);
/*  33 */   public static final int AquaCost = (int)(100.0F * RelicsConfigHandler.chaosTomeVisMult);
/*  34 */   public static final int OrdoCost = (int)(100.0F * RelicsConfigHandler.chaosTomeVisMult);
/*  35 */   public static final int PerditioCost = (int)(100.0F * RelicsConfigHandler.chaosTomeVisMult);
/*     */ 
/*     */   
/*     */   public ItemChaosTome() {
/*  39 */     this.maxStackSize = 1;
/*  40 */     setUnlocalizedName("ItemChaosTome");
/*  41 */     setCreativeTab(Main.tabForgottenRelics);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void registerIcons(IIconRegister iconRegister) {
/*  49 */     this.itemIcon = iconRegister.registerIcon("forgottenrelics:Chaos_Tome");
/*     */   }
/*     */   
/*     */   public static double round(double value, int places) {
/*  53 */     if (places < 0) throw new IllegalArgumentException();
/*     */     
/*  55 */     BigDecimal bd = new BigDecimal(value);
/*  56 */     bd = bd.setScale(places, RoundingMode.HALF_UP);
/*  57 */     return bd.doubleValue();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List<String> par3List, boolean par4) {
/*  65 */     if (GuiScreen.isShiftKeyDown()) {
/*  66 */       par3List.add(StatCollector.translateToLocal("item.ItemChaosTome1.lore"));
/*  67 */       par3List.add(StatCollector.translateToLocal("item.FREmpty.lore"));
/*  68 */       par3List.add(StatCollector.translateToLocal("item.ItemChaosTome2.lore"));
/*  69 */       par3List.add(StatCollector.translateToLocal("item.ItemChaosTome3.lore"));
/*  70 */       par3List.add(StatCollector.translateToLocal("item.FREmpty.lore"));
/*  71 */       par3List.add(StatCollector.translateToLocal("item.ItemChaosTome4.lore"));
/*  72 */       par3List.add(StatCollector.translateToLocal("item.FREmpty.lore"));
/*  73 */       par3List.add(StatCollector.translateToLocal("item.ItemChaosTome5_1.lore") + " 1-" + (int)RelicsConfigHandler.chaosTomeDamageCap + " " + StatCollector.translateToLocal("item.ItemChaosTome5_2.lore"));
/*  74 */       par3List.add(StatCollector.translateToLocal("item.ItemChaosTome6.lore"));
/*     */     }
/*  76 */     else if (GuiScreen.isCtrlKeyDown()) {
/*  77 */       par3List.add(StatCollector.translateToLocal("item.FRVisPerSecond.lore"));
/*  78 */       this; this; par3List.add(" " + StatCollector.translateToLocal("item.FRAerCost.lore") + round(AerCost * Math.random() / 100.0D * 10.0D, 2));
/*  79 */       this; this; par3List.add(" " + StatCollector.translateToLocal("item.FRTerraCost.lore") + round(TerraCost * Math.random() / 100.0D * 10.0D, 2));
/*  80 */       this; this; par3List.add(" " + StatCollector.translateToLocal("item.FRIgnisCost.lore") + round(IgnisCost * Math.random() / 100.0D * 10.0D, 2));
/*  81 */       this; this; par3List.add(" " + StatCollector.translateToLocal("item.FRAquaCost.lore") + round(AquaCost * Math.random() / 100.0D * 10.0D, 2));
/*  82 */       this; this; par3List.add(" " + StatCollector.translateToLocal("item.FROrdoCost.lore") + round(OrdoCost * Math.random() / 100.0D * 10.0D, 2));
/*  83 */       this; this; par3List.add(" " + StatCollector.translateToLocal("item.FRPerditioCost.lore") + round(PerditioCost * Math.random() / 100.0D * 10.0D, 2));
/*     */     } else {
/*     */       
/*  86 */       par3List.add(StatCollector.translateToLocal("item.FRShiftTooltip.lore"));
/*  87 */       par3List.add(StatCollector.translateToLocal("item.FRViscostTooltip.lore"));
/*     */     } 
/*     */     
/*  90 */     par3List.add(StatCollector.translateToLocal("item.FREmpty.lore"));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean spawnOrb(World world, EntityPlayer player) {
/*  95 */     if (!world.isRemote) {
/*     */       
/*  97 */       Vector3 originalPos = Vector3.fromEntityCenter((Entity)player);
/*  98 */       Vector3 vector = originalPos.add((new Vector3(player.getLookVec())).multiply(1.0D));
/*  99 */       vector.y += 0.5D;
/*     */       
/* 101 */       EntityChaoticOrb orb = null;
/*     */       
/* 103 */       if (Math.random() <= 0.35D) {
/* 104 */         orb = new EntityChaoticOrb(world, (EntityLivingBase)player, true);
/*     */       } else {
/* 106 */         orb = new EntityChaoticOrb(world, (EntityLivingBase)player, false);
/*     */       } 
/* 108 */       orb.setPosition(originalPos.x + (Math.random() - 0.5D) * 3.0D, originalPos.y + (Math.random() - 0.5D) * 1.0D, originalPos.z + (Math.random() - 0.5D) * 3.0D);
/*     */       
/* 110 */       Vector3 motion = (new Vector3(orb.posX, orb.posY, orb.posZ)).sub(originalPos.copy()).multiply(0.2D + Math.random() * 0.2D);
/*     */       
/* 112 */       orb.motionX = motion.x;
/* 113 */       orb.motionY = motion.y;
/* 114 */       orb.motionZ = motion.z;
/*     */       
/* 116 */       world.spawnEntityInWorld((Entity)orb);
/*     */       
/* 118 */       if (!world.isRemote) {
/* 119 */         world.playSoundAtEntity((Entity)orb, "thaumcraft:ice", 0.3F, 0.8F + world.rand.nextFloat() * 0.1F);
/*     */       }
/* 121 */       return true;
/*     */     } 
/*     */     
/* 124 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
/* 130 */     player.setItemInUse(stack, stack.getMaxItemUseDuration());
/*     */     
/* 132 */     return stack;
/*     */   }
/*     */ 
/*     */   
/*     */   public EnumAction getItemUseAction(ItemStack par1ItemStack) {
/* 137 */     return EnumAction.bow;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getMaxItemUseDuration(ItemStack par1ItemStack) {
/* 142 */     return 72000;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void onUsingTick(ItemStack stack, EntityPlayer player, int count) {
/* 148 */     if ((((count != stack.getMaxItemUseDuration()) ? 1 : 0) & ((count % 2 == 0) ? 1 : 0) & (!player.worldObj.isRemote ? 1 : 0)) != 0) {
/*     */       
/* 150 */       this; this; this; this; this; this; if (WandManager.consumeVisFromInventory(player, (new AspectList()).add(Aspect.AIR, (int)(AerCost * Math.random())).add(Aspect.EARTH, (int)(TerraCost * Math.random())).add(Aspect.WATER, (int)(AquaCost * Math.random())).add(Aspect.FIRE, (int)(IgnisCost * Math.random())).add(Aspect.ORDER, (int)(OrdoCost * Math.random())).add(Aspect.ENTROPY, (int)(PerditioCost * Math.random())))) {
/* 151 */         spawnOrb(player.worldObj, player);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EnumRarity getRarity(ItemStack itemStack) {
/* 161 */     return EnumRarity.epic;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getWarp(ItemStack arg0, EntityPlayer arg1) {
/* 166 */     return 4;
/*     */   }
/*     */ }


/* Location:              C:\Users\antro\Documents\forgottenRelicsDeobfuscated.jar!\com\integral\forgottenrelics\items\ItemChaosTome.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */