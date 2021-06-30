/*     */ package com.integral.forgottenrelics.items;
/*     */ 
/*     */ import com.integral.forgottenrelics.Main;
/*     */ import com.integral.forgottenrelics.handlers.RelicsConfigHandler;
/*     */ import com.integral.forgottenrelics.handlers.SuperpositionHandler;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import net.minecraft.client.gui.GuiScreen;
/*     */ import net.minecraft.client.renderer.texture.IIconRegister;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.EnumAction;
/*     */ import net.minecraft.item.EnumRarity;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.StatCollector;
/*     */ import net.minecraft.world.World;
/*     */ import thaumcraft.api.IRepairable;
/*     */ import thaumcraft.api.IWarpingGear;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.common.items.wands.ItemWandCasting;
/*     */ import thaumcraft.common.tiles.TileEldritchObelisk;
/*     */ import vazkii.botania.common.core.helper.ItemNBTHelper;
/*     */ import vazkii.botania.common.core.helper.Vector3;
/*     */ 
/*     */ public class ItemObeliskDrainer
/*     */   extends Item
/*     */   implements IWarpingGear, IRepairable
/*     */ {
/*     */   public void registerRenderers() {}
/*     */   
/*     */   public ItemObeliskDrainer() {
/*  36 */     this.maxStackSize = 1;
/*  37 */     setUnlocalizedName("ItemObeliskDrainer");
/*  38 */     setCreativeTab(Main.tabForgottenRelics);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void registerIcons(IIconRegister iconRegister) {
/*  46 */     this.itemIcon = iconRegister.registerIcon("forgottenrelics:Obelisk_Drainer");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List<String> par3List, boolean par4) {
/*  54 */     if (GuiScreen.isShiftKeyDown()) {
/*  55 */       par3List.add(StatCollector.translateToLocal("item.ItemObeliskDrainer1.lore"));
/*  56 */       par3List.add(StatCollector.translateToLocal("item.ItemObeliskDrainer2.lore"));
/*  57 */       par3List.add(StatCollector.translateToLocal("item.FREmpty.lore"));
/*  58 */       par3List.add(StatCollector.translateToLocal("item.ItemObeliskDrainer3.lore"));
/*  59 */       par3List.add(StatCollector.translateToLocal("item.ItemObeliskDrainer4.lore"));
/*  60 */       par3List.add(StatCollector.translateToLocal("item.ItemObeliskDrainer5.lore"));
/*     */     } else {
/*     */       
/*  63 */       par3List.add(StatCollector.translateToLocal("item.FRShiftTooltip.lore"));
/*     */     } 
/*     */ 
/*     */     
/*  67 */     par3List.add(StatCollector.translateToLocal("item.FREmpty.lore"));
/*     */   }
/*     */ 
/*     */   
/*     */   public EnumAction getItemUseAction(ItemStack par1ItemStack) {
/*  72 */     return EnumAction.bow;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getMaxItemUseDuration(ItemStack par1ItemStack) {
/*  77 */     return 72000;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void onUsingTick(ItemStack stack, EntityPlayer player, int count) {
/*  83 */     double x = ItemNBTHelper.getDouble(stack, "IDetectedX", 0.0D);
/*  84 */     double y = ItemNBTHelper.getDouble(stack, "IDetectedY", 0.0D);
/*  85 */     double z = ItemNBTHelper.getDouble(stack, "IDetectedZ", 0.0D);
/*     */     
/*  87 */     if (!(player.worldObj.getTileEntity((int)x, (int)y, (int)z) instanceof TileEldritchObelisk) || player.getDistance(x, y + 2.5D, z) > 16.0D) {
/*  88 */       player.setItemInUse(stack, 0);
/*     */       
/*     */       return;
/*     */     } 
/*  92 */     if ((((count % 30 == 0) ? 1 : 0) & ((count != getMaxItemUseDuration(stack)) ? 1 : 0) & (!player.worldObj.isRemote ? 1 : 0)) != 0) {
/*     */       
/*  94 */       Vector3 thisVec = Vector3.fromEntityCenter((Entity)player);
/*  95 */       Vector3 targetVec = Vector3.fromTileEntityCenter(player.worldObj.getTileEntity((int)x, (int)y, (int)z));
/*  96 */       Vector3 diffVec = targetVec.copy().sub(thisVec);
/*  97 */       Vector3 motionVec = thisVec.add(diffVec.copy().multiply(1.0D / player.getDistance(x, y + 2.5D, z) * 0.5D));
/*     */       
/*  99 */       float curve = (float)(1.0D / player.getDistance(x, y + 2.5D, z) * (2.0D + Math.random() * 4.0D));
/* 100 */       if (player.getDistance(x, y + 2.5D, z) <= 3.0D) {
/* 101 */         curve = (float)(1.0D / player.getDistance(x, y + 2.5D, z) * 8.0D);
/*     */       }
/* 103 */       for (int counter = 0; counter <= 3; counter++) {
/* 104 */         SuperpositionHandler.imposeLightning(player.worldObj, player.dimension, x + 0.5D, y + 2.75D, z + 0.5D, motionVec.x, motionVec.y, motionVec.z, 20, curve, (int)(player.getDistance(x, y + 2.5D, z) * 1.600000023841858D), 0, 0.075F);
/*     */       }
/* 106 */       player.worldObj.playSoundAtEntity((Entity)player, "thaumcraft:zap", 1.0F, 0.8F);
/*     */       
/* 108 */       player.attackEntityFrom(DamageSource.generic, 0.01F);
/* 109 */       player.heal(4.0F);
/* 110 */       player.getFoodStats().addStats(2, 1.0F);
/* 111 */       List<Aspect> primals = Aspect.getPrimalAspects();
/* 112 */       Aspect randomAspect = primals.get((int)(Math.random() * primals.size()));
/* 113 */       ItemStack randomWand = SuperpositionHandler.getRandomValidWand(SuperpositionHandler.wandSearch(player), randomAspect);
/*     */       
/* 115 */       if (randomWand != null) {
/* 116 */         ((ItemWandCasting)randomWand.getItem()).addVis(randomWand, randomAspect, (int)((5.0D + Math.random() * 15.0D) * 1.5D * RelicsConfigHandler.obeliskDrainerVisMult), true);
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 121 */     if (((!player.worldObj.isRemote ? 1 : 0) & ((Math.random() <= 0.175D) ? 1 : 0)) != 0) {
/* 122 */       float h = 0.0F;
/* 123 */       if (Math.random() > 0.5D) {
/* 124 */         h = 0.4F;
/*     */       } else {
/* 126 */         h = -0.4F;
/*     */       } 
/* 128 */       SuperpositionHandler.imposeArcLightning(player.worldObj, player.dimension, x + 0.5D, y + 2.5D + (Math.random() - 0.5D) * 2.0D, z + 0.5D, x + 0.5D + (Math.random() - 0.5D) * 4.0D, y + 2.5D + (Math.random() - 0.5D) * 4.0D, z + 0.5D + (Math.random() - 0.5D) * 4.0D, 1.0F, 0.6F, 1.0F, h);
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 133 */     if ((((player != null) ? 1 : 0) & player.worldObj.isRemote) != 0) {
/*     */       
/* 135 */       Main.proxy.wispFX4(player.worldObj, x + 0.5D, y + 1.0D + (player.worldObj.rand.nextFloat() * 3.0F), z + 0.5D, (Entity)player, 5, true, 1.0F);
/* 136 */       for (int counterrr = 0; counterrr <= 4; counterrr++) {
/* 137 */         player.worldObj.spawnParticle("portal", x + 0.5D, y + 2.5D + (Math.random() - 0.5D) * 2.0D, z + 0.5D, (Math.random() - 0.5D) * 3.0D, (Math.random() - 0.5D) * 0.3D, (Math.random() - 0.5D) * 3.0D);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
/* 145 */     List<TileEldritchObelisk> loadedTiles = new ArrayList(world.loadedTileEntityList);
/*     */     
/* 147 */     TileEldritchObelisk requiredObelisk = null;
/*     */     int counter;
/* 149 */     for (counter = 0; counter <= loadedTiles.size() - 1; counter++) {
/* 150 */       if (!(loadedTiles.get(counter) instanceof TileEldritchObelisk)) {
/* 151 */         loadedTiles.remove(counter);
/* 152 */         counter = -1;
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 157 */     for (counter = 0; counter <= loadedTiles.size() - 1; counter++) {
/* 158 */       TileEldritchObelisk checkedObelisk = loadedTiles.get(counter);
/*     */       
/* 160 */       if (player.getDistance(checkedObelisk.xCoord, checkedObelisk.yCoord, checkedObelisk.zCoord) > 16.0D || (checkedObelisk.getWorldObj()).provider.dimensionId != player.dimension) {
/* 161 */         loadedTiles.remove(counter);
/* 162 */         counter = -1;
/*     */       } else {
/*     */         
/* 165 */         requiredObelisk = checkedObelisk;
/*     */         
/*     */         break;
/*     */       } 
/*     */     } 
/*     */     
/* 171 */     if (requiredObelisk != null) {
/*     */       
/* 173 */       ItemNBTHelper.setDouble(stack, "IDetectedX", requiredObelisk.xCoord);
/* 174 */       ItemNBTHelper.setDouble(stack, "IDetectedY", requiredObelisk.yCoord);
/* 175 */       ItemNBTHelper.setDouble(stack, "IDetectedZ", requiredObelisk.zCoord);
/*     */       
/* 177 */       player.setItemInUse(stack, getMaxItemUseDuration(stack));
/*     */     } 
/*     */     
/* 180 */     return stack;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EnumRarity getRarity(ItemStack itemStack) {
/* 187 */     return EnumRarity.epic;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getWarp(ItemStack arg0, EntityPlayer arg1) {
/* 193 */     return 4;
/*     */   }
/*     */ }


/* Location:              C:\Users\antro\Documents\forgottenRelicsDeobfuscated.jar!\com\integral\forgottenrelics\items\ItemObeliskDrainer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */