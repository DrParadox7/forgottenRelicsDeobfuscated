/*     */ package com.integral.forgottenrelics.items;
/*     */ 
/*     */ import com.integral.forgottenrelics.Main;
/*     */ import com.integral.forgottenrelics.entities.EntitySoulEnergy;
/*     */ import com.integral.forgottenrelics.handlers.DamageRegistryHandler;
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
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.StatCollector;
/*     */ import net.minecraft.world.World;
/*     */ import thaumcraft.api.IRepairable;
/*     */ import thaumcraft.api.IWarpingGear;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.api.aspects.AspectList;
/*     */ import thaumcraft.common.items.wands.WandManager;
/*     */ import vazkii.botania.common.core.helper.Vector3;
/*     */ 
/*     */ public class ItemSoulTome
/*     */   extends Item implements IWarpingGear, IRepairable {
/*  32 */   public static final int AerCost = (int)(20.0F * RelicsConfigHandler.soulTomeVisMult);
/*  33 */   public static final int TerraCost = (int)(25.0F * RelicsConfigHandler.soulTomeVisMult);
/*  34 */   public static final int IgnisCost = (int)(35.0F * RelicsConfigHandler.soulTomeVisMult);
/*  35 */   public static final int AquaCost = (int)(0.0F * RelicsConfigHandler.soulTomeVisMult);
/*  36 */   public static final int OrdoCost = (int)(0.0F * RelicsConfigHandler.soulTomeVisMult);
/*  37 */   public static final int PerditioCost = (int)(50.0F * RelicsConfigHandler.soulTomeVisMult);
/*     */ 
/*     */   
/*     */   public ItemSoulTome() {
/*  41 */     this.maxStackSize = 1;
/*  42 */     setUnlocalizedName("ItemSoulTome");
/*  43 */     setCreativeTab(Main.tabForgottenRelics);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void registerIcons(IIconRegister iconRegister) {
/*  51 */     this.itemIcon = iconRegister.registerIcon("forgottenrelics:Soul_Tome");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List<String> par3List, boolean par4) {
/*  59 */     if (GuiScreen.isShiftKeyDown()) {
/*  60 */       par3List.add(StatCollector.translateToLocal("item.ItemSoulTome1.lore"));
/*  61 */       par3List.add(StatCollector.translateToLocal("item.ItemSoulTome2.lore"));
/*  62 */       par3List.add(StatCollector.translateToLocal("item.ItemSoulTome3.lore"));
/*  63 */       par3List.add(StatCollector.translateToLocal("item.FREmpty.lore"));
/*  64 */       par3List.add(StatCollector.translateToLocal("item.ItemSoulTome4.lore"));
/*  65 */       par3List.add(StatCollector.translateToLocal("item.ItemSoulTome5.lore"));
/*  66 */       par3List.add(StatCollector.translateToLocal("item.FREmpty.lore"));
/*  67 */       par3List.add(StatCollector.translateToLocal("item.ItemSoulTome6.lore"));
/*  68 */       par3List.add(StatCollector.translateToLocal("item.ItemSoulTome7.lore"));
/*  69 */       par3List.add(StatCollector.translateToLocal("item.ItemSoulTome8.lore"));
/*  70 */       par3List.add(StatCollector.translateToLocal("item.FREmpty.lore"));
/*  71 */       par3List.add(StatCollector.translateToLocal("item.ItemSoulTome9.lore"));
/*  72 */     } else if (GuiScreen.isCtrlKeyDown()) {
/*  73 */       par3List.add(StatCollector.translateToLocal("item.FRVisPerSecond.lore"));
/*  74 */       this; par3List.add(" " + StatCollector.translateToLocal("item.FRAerCost.lore") + (AerCost / 100.0D * 10.0D));
/*  75 */       this; par3List.add(" " + StatCollector.translateToLocal("item.FRTerraCost.lore") + (TerraCost / 100.0D * 10.0D));
/*  76 */       this; par3List.add(" " + StatCollector.translateToLocal("item.FRIgnisCost.lore") + (IgnisCost / 100.0D * 10.0D));
/*  77 */       this; par3List.add(" " + StatCollector.translateToLocal("item.FRPerditioCost.lore") + (PerditioCost / 100.0D * 10.0D));
/*     */     } else {
/*     */       
/*  80 */       par3List.add(StatCollector.translateToLocal("item.FRShiftTooltip.lore"));
/*  81 */       par3List.add(StatCollector.translateToLocal("item.FRViscostTooltip.lore"));
/*     */     } 
/*     */     
/*  84 */     par3List.add(StatCollector.translateToLocal("item.FREmpty.lore"));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean spawnSoul(World world, EntityLivingBase player, EntityLivingBase target) {
/*  89 */     if (!world.isRemote) {
/*     */       
/*  91 */       Vector3 originalPos = Vector3.fromEntityCenter((Entity)player);
/*  92 */       Vector3 vector = originalPos.add((new Vector3(player.getLookVec())).multiply(1.0D));
/*  93 */       vector.y += 0.5D;
/*     */       
/*  95 */       Vector3 motion = (new Vector3(player.getLookVec())).multiply(1.25D);
/*     */       
/*  97 */       EntitySoulEnergy orb = new EntitySoulEnergy(world, player, target, false);
/*  98 */       orb.setPosition(vector.x, vector.y, vector.z);
/*  99 */       orb.motionX = motion.x;
/* 100 */       orb.motionY = motion.y;
/* 101 */       orb.motionZ = motion.z;
/*     */       
/* 103 */       world.playSoundEffect(player.posX, player.posY, player.posZ, "botania:missile", 2.0F, 0.8F + (float)Math.random() * 0.2F);
/* 104 */       world.spawnEntityInWorld((Entity)orb);
/*     */       
/* 106 */       return true;
/*     */     } 
/*     */     
/* 109 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public EnumAction getItemUseAction(ItemStack par1ItemStack) {
/* 114 */     return EnumAction.bow;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getMaxItemUseDuration(ItemStack par1ItemStack) {
/* 119 */     return 72000;
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
/* 124 */     player.setItemInUse(stack, getMaxItemUseDuration(stack));
/*     */     
/* 126 */     return stack;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void onUsingTick(ItemStack stack, EntityPlayer player, int count) {
/* 132 */     player.motionX = 0.0D;
/* 133 */     player.motionZ = 0.0D;
/*     */     
/* 135 */     int searchRange = 20;
/* 136 */     List<EntityLivingBase> entities = player.worldObj.getEntitiesWithinAABB(EntityLivingBase.class, AxisAlignedBB.getBoundingBox(player.posX - searchRange, player.posY - searchRange, player.posZ - searchRange, player.posX + searchRange, player.posY + searchRange, player.posZ + searchRange));
/*     */     
/* 138 */     if (entities.contains(player)) {
/* 139 */       entities.remove(player);
/*     */     }
/* 141 */     if (count < getMaxItemUseDuration(stack) - 20) {
/* 142 */       for (int counter = entities.size() - 1; counter >= 0; counter--) {
/* 143 */         if (player.getDistanceToEntity((Entity)entities.get(counter)) <= 3.0F && 
/* 144 */           WandManager.consumeVisFromInventory(player, (new AspectList()).add(Aspect.FIRE, (int)(150.0F * RelicsConfigHandler.soulTomeVisMult)).add(Aspect.ENTROPY, (int)(120.0F * RelicsConfigHandler.soulTomeVisMult)))) {
/*     */           
/* 146 */           EntityLivingBase thrownEntity = entities.get(counter);
/*     */           
/* 148 */           Vector3 entityVec = Vector3.fromEntityCenter((Entity)thrownEntity);
/* 149 */           Vector3 playerVec = Vector3.fromEntityCenter((Entity)player);
/*     */           
/* 151 */           Vector3 diff = entityVec.copy().sub(playerVec).multiply((1.0F / player.getDistanceToEntity((Entity)thrownEntity) * 3.0F));
/*     */           
/* 153 */           float curve = (float)(1.0D / player.getDistance(entityVec.x, entityVec.y, entityVec.z) * 8.0D);
/*     */           
/* 155 */           if (!player.worldObj.isRemote)
/* 156 */             for (int counterZ = 0; counterZ <= 3; counterZ++) {
/* 157 */               Main.proxy.lightning(player.worldObj, player.posX, player.posY + 1.0D, player.posZ, entityVec.x, entityVec.y, entityVec.z, 40, curve, (int)(player.getDistance(entityVec.x, entityVec.y, entityVec.z) * 6.0D), 0, 0.075F);
/*     */             } 
/* 159 */           player.worldObj.playSoundAtEntity((Entity)player, "thaumcraft:zap", 1.0F, 0.8F);
/*     */           
/* 161 */           thrownEntity.attackEntityFrom((DamageSource)new DamageRegistryHandler.DamageSourceTLightning((Entity)player), (float)(20.0D + 80.0D * Math.random()));
/*     */           
/* 163 */           thrownEntity.motionX = diff.x;
/* 164 */           thrownEntity.motionY = diff.y + 1.0D;
/* 165 */           thrownEntity.motionZ = diff.z;
/*     */         } 
/*     */       } 
/*     */     }
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
/* 195 */     this; this; this; this; if ((((count < getMaxItemUseDuration(stack) - 20) ? 1 : 0) & ((count % 4 == 0) ? 1 : 0)) != 0 && WandManager.consumeVisFromInventory(player, (new AspectList()).add(Aspect.EARTH, TerraCost).add(Aspect.AIR, AerCost).add(Aspect.FIRE, IgnisCost).add(Aspect.ENTROPY, PerditioCost))) {
/*     */       
/* 197 */       EntityLivingBase randomEntity = null;
/*     */       
/* 199 */       if (entities.size() > 0) {
/* 200 */         randomEntity = entities.get((int)(entities.size() * Math.random()));
/*     */       }
/* 202 */       if ((((randomEntity != null) ? 1 : 0) & (!player.worldObj.isRemote ? 1 : 0)) != 0) {
/*     */         
/* 204 */         float soulDamage = randomEntity.getMaxHealth() / RelicsConfigHandler.soulTomeDivisor;
/*     */         
/* 206 */         if (soulDamage > 20.0F) {
/* 207 */           soulDamage = 20.0F;
/* 208 */         } else if (soulDamage < 1.0F) {
/* 209 */           soulDamage = 1.0F;
/*     */         } 
/* 211 */         randomEntity.attackEntityFrom((DamageSource)new DamageRegistryHandler.DamageSourceSoulDrain((Entity)player), soulDamage);
/* 212 */         spawnSoul(player.worldObj, randomEntity, (EntityLivingBase)player);
/*     */       } 
/*     */     } 
/*     */   }
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
/* 227 */     return 3;
/*     */   }
/*     */ }


/* Location:              C:\Users\antro\Documents\forgottenRelicsDeobfuscated.jar!\com\integral\forgottenrelics\items\ItemSoulTome.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */