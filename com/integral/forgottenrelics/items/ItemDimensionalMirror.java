/*     */ package com.integral.forgottenrelics.items;
/*     */ 
/*     */ import com.integral.forgottenrelics.Main;
/*     */ import com.integral.forgottenrelics.handlers.ExtradimensionalTeleporter;
/*     */ import com.integral.forgottenrelics.handlers.RelicsConfigHandler;
/*     */ import com.integral.forgottenrelics.handlers.SuperpositionHandler;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.List;
/*     */ import net.minecraft.client.gui.GuiScreen;
/*     */ import net.minecraft.client.renderer.texture.IIconRegister;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.EntityPlayerMP;
/*     */ import net.minecraft.item.EnumAction;
/*     */ import net.minecraft.item.EnumRarity;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.StatCollector;
/*     */ import net.minecraft.world.Teleporter;
/*     */ import net.minecraft.world.World;
/*     */ import vazkii.botania.common.core.helper.ItemNBTHelper;
/*     */ import vazkii.botania.common.core.helper.Vector3;
/*     */ 
/*     */ 
/*     */ public class ItemDimensionalMirror
/*     */   extends Item
/*     */ {
/*     */   public void registerRenderers() {}
/*     */   
/*     */   public ItemDimensionalMirror() {
/*  32 */     this.maxStackSize = 1;
/*  33 */     setUnlocalizedName("ItemDimensionalMirror");
/*  34 */     setCreativeTab(Main.tabForgottenRelics);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void registerIcons(IIconRegister iconRegister) {
/*  42 */     this.itemIcon = iconRegister.registerIcon("forgottenrelics:Dimensional_Mirror");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List<String> par3List, boolean par4) {
/*  50 */     if (GuiScreen.isShiftKeyDown()) {
/*  51 */       par3List.add(StatCollector.translateToLocal("item.ItemDimensionalMirror1.lore"));
/*  52 */       par3List.add(StatCollector.translateToLocal("item.ItemDimensionalMirror2.lore"));
/*  53 */       par3List.add(StatCollector.translateToLocal("item.ItemDimensionalMirror3.lore"));
/*  54 */       par3List.add(StatCollector.translateToLocal("item.FREmpty.lore"));
/*  55 */       par3List.add(StatCollector.translateToLocal("item.ItemDimensionalMirror4.lore"));
/*     */     } else {
/*     */       
/*  58 */       par3List.add(StatCollector.translateToLocal("item.FRShiftTooltip.lore"));
/*     */     } 
/*     */ 
/*     */     
/*  62 */     if (par1ItemStack.hasTagCompound()) {
/*  63 */       par3List.add(StatCollector.translateToLocal("item.FREmpty.lore"));
/*  64 */       par3List.add(StatCollector.translateToLocal("item.MirrorLoc.lore"));
/*  65 */       par3List.add(StatCollector.translateToLocal("item.FREmpty.lore"));
/*  66 */       par3List.add(StatCollector.translateToLocal("item.MirrorX.lore") + ItemNBTHelper.getInt(par1ItemStack, "IStoredX", 0));
/*  67 */       par3List.add(StatCollector.translateToLocal("item.MirrorY.lore") + ItemNBTHelper.getInt(par1ItemStack, "IStoredY", 0));
/*  68 */       par3List.add(StatCollector.translateToLocal("item.MirrorZ.lore") + ItemNBTHelper.getInt(par1ItemStack, "IStoredZ", 0));
/*  69 */       par3List.add(StatCollector.translateToLocal("item.FREmpty.lore"));
/*  70 */       par3List.add(StatCollector.translateToLocal("item.MirrorDimension.lore") + ItemNBTHelper.getInt(par1ItemStack, "IDimensionID", 0));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public EnumAction getItemUseAction(ItemStack par1ItemStack) {
/*  76 */     return EnumAction.bow;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getMaxItemUseDuration(ItemStack par1ItemStack) {
/*  81 */     return 80;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public boolean hasEffect(ItemStack stack, int pass) {
/*  88 */     boolean effect = stack.hasTagCompound();
/*     */     
/*  90 */     if (effect) {
/*  91 */       return true;
/*     */     }
/*  93 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isFull3D() {
/*  99 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void onUsingTick(ItemStack stack, EntityPlayer player, int count) {
/* 105 */     Vector3 vec = Vector3.fromEntityCenter((Entity)player);
/*     */     
/* 107 */     for (int counter = 0; counter <= 3; counter++) {
/* 108 */       player.worldObj.spawnParticle("portal", vec.x, vec.y, vec.z, (Math.random() - 0.5D) * 3.0D, (Math.random() - 0.5D) * 3.0D, (Math.random() - 0.5D) * 3.0D);
/*     */     }
/* 110 */     if (count == 1) {
/* 111 */       int dimension = ItemNBTHelper.getInt(stack, "IDimensionID", 0);
/* 112 */       int x = ItemNBTHelper.getInt(stack, "IStoredX", 0);
/* 113 */       int y = ItemNBTHelper.getInt(stack, "IStoredY", 0);
/* 114 */       int z = ItemNBTHelper.getInt(stack, "IStoredZ", 0);
/*     */       
/* 116 */       SuperpositionHandler.imposeBurst(player.worldObj, player.dimension, vec.x, vec.y, vec.z, 1.25F);
/*     */       
/* 118 */       if (((!player.worldObj.isRemote ? 1 : 0) & ((player.dimension != dimension) ? 1 : 0)) != 0) {
/* 119 */         ((EntityPlayerMP)player).mcServer.getConfigurationManager().transferPlayerToDimension((EntityPlayerMP)player, dimension, (Teleporter)new ExtradimensionalTeleporter(((EntityPlayerMP)player).mcServer.worldServerForDimension(dimension), x + 0.5D, y + 0.5D, z + 0.5D));
/*     */       } else {
/* 121 */         player.setPositionAndUpdate(x + 0.5D, y + 0.5D, z + 0.5D);
/*     */       } 
/* 123 */       player.worldObj.playSoundEffect(vec.x, vec.y, vec.z, "mob.endermen.portal", 1.0F, (float)(0.800000011920929D + Math.random() * 0.2D));
/* 124 */       player.worldObj.playSoundEffect(player.posX, player.posY, player.posZ, "mob.endermen.portal", 1.0F, (float)(0.800000011920929D + Math.random() * 0.2D));
/*     */       
/* 126 */       for (int i = 0; i <= 128; i++) {
/* 127 */         player.worldObj.spawnParticle("portal", player.posX, player.posY - 1.0D, player.posZ, (Math.random() - 0.5D) * 3.0D, (Math.random() - 0.5D) * 3.0D, (Math.random() - 0.5D) * 3.0D);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
/* 134 */     boolean written = false;
/*     */     
/* 136 */     if (stack.hasTagCompound()) {
/* 137 */       written = true;
/*     */     } else {
/* 139 */       written = false;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 144 */     if ((written & (!player.isSneaking() ? 1 : 0)) != 0) {
/*     */       
/* 146 */       if (((!RelicsConfigHandler.interdimensionalMirror ? 1 : 0) & ((player.dimension != ItemNBTHelper.getInt(stack, "IDimensionID", 0)) ? 1 : 0)) != 0)
/* 147 */         return stack; 
/* 148 */       if ((((player.dimension == 1) ? 1 : 0) & ((ItemNBTHelper.getInt(stack, "IDimensionID", 0) != 1) ? 1 : 0)) != 0) {
/* 149 */         return stack;
/*     */       }
/* 151 */       player.setItemInUse(stack, stack.getMaxItemUseDuration());
/*     */     }
/* 153 */     else if (player.isSneaking()) {
/*     */       
/* 155 */       ItemNBTHelper.setInt(stack, "IStoredX", (int)player.posX);
/* 156 */       ItemNBTHelper.setInt(stack, "IStoredY", (int)player.posY);
/* 157 */       ItemNBTHelper.setInt(stack, "IStoredZ", (int)player.posZ);
/*     */       
/* 159 */       ItemNBTHelper.setInt(stack, "IDimensionID", player.dimension);
/*     */       
/* 161 */       world.playSoundEffect(player.posX, player.posY, player.posZ, "thaumcraft:jar", 1.0F, 2.0F);
/*     */       
/* 163 */       player.swingItem();
/*     */     } 
/*     */ 
/*     */     
/* 167 */     return stack;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EnumRarity getRarity(ItemStack itemStack) {
/* 174 */     return EnumRarity.epic;
/*     */   }
/*     */ }


/* Location:              C:\Users\antro\Documents\forgottenRelicsDeobfuscated.jar!\com\integral\forgottenrelics\items\ItemDimensionalMirror.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */