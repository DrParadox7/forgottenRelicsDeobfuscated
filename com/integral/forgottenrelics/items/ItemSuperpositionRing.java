/*    */ package com.integral.forgottenrelics.items;
/*    */ 
/*    */ import baubles.api.BaubleType;
/*    */ import baubles.api.IBauble;
/*    */ import com.integral.forgottenrelics.Main;
/*    */ import com.integral.forgottenrelics.handlers.SuperpositionHandler;
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import java.util.List;
/*    */ import net.minecraft.client.gui.GuiScreen;
/*    */ import net.minecraft.client.renderer.texture.IIconRegister;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.entity.player.EntityPlayerMP;
/*    */ import net.minecraft.item.EnumRarity;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.StatCollector;
/*    */ import net.minecraft.world.WorldServer;
/*    */ import vazkii.botania.common.core.helper.Vector3;
/*    */ 
/*    */ public class ItemSuperpositionRing
/*    */   extends ItemBaubleBase
/*    */   implements IBauble {
/*    */   public ItemSuperpositionRing() {
/* 26 */     super("ItemSuperpositionRing");
/* 27 */     this.maxStackSize = 1;
/* 28 */     setUnlocalizedName("ItemSuperpositionRing");
/* 29 */     setCreativeTab(Main.tabForgottenRelics);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void registerIcons(IIconRegister iconRegister) {
/* 37 */     this.itemIcon = iconRegister.registerIcon("forgottenrelics:Superposition_Ring");
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List<String> par3List, boolean par4) {
/* 45 */     if (GuiScreen.isShiftKeyDown()) {
/* 46 */       par3List.add(StatCollector.translateToLocal("item.ItemSuperpositionRing1.lore"));
/* 47 */       par3List.add(StatCollector.translateToLocal("item.ItemSuperpositionRing2.lore"));
/* 48 */       par3List.add(StatCollector.translateToLocal("item.ItemSuperpositionRing3.lore"));
/* 49 */       par3List.add(StatCollector.translateToLocal("item.FREmpty.lore"));
/* 50 */       par3List.add(StatCollector.translateToLocal("item.ItemSuperpositionRing4.lore"));
/* 51 */       par3List.add(StatCollector.translateToLocal("item.ItemSuperpositionRing5.lore"));
/* 52 */       par3List.add(StatCollector.translateToLocal("item.FREmpty.lore"));
/* 53 */       par3List.add(SuperpositionHandler.getBaubleTooltip(getBaubleType(par1ItemStack)));
/*    */     } else {
/*    */       
/* 56 */       par3List.add(StatCollector.translateToLocal("item.FRShiftTooltip.lore"));
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public EnumRarity getRarity(ItemStack itemStack) {
/* 65 */     return EnumRarity.epic;
/*    */   }
/*    */ 
/*    */   
/*    */   public BaubleType getBaubleType(ItemStack arg0) {
/* 70 */     return BaubleType.RING;
/*    */   }
/*    */ 
/*    */   
/*    */   public void onWornTick(ItemStack itemstack, EntityLivingBase entity) {
/* 75 */     super.onWornTick(itemstack, entity);
/*    */     
/* 77 */     if ((((entity.ticksExisted % 600 == 0) ? 1 : 0) & (!entity.worldObj.isRemote ? 1 : 0) & entity instanceof EntityPlayer) != 0)
/*    */     {
/* 79 */       if (Math.random() <= 0.025D) {
/* 80 */         List<EntityPlayer> players = SuperpositionHandler.getBaubleOwnersList(entity.worldObj, Main.itemSuperpositionRing);
/* 81 */         if (players.contains(entity)) {
/* 82 */           players.remove(entity);
/*    */         }
/* 84 */         if (players.size() > 0) {
/* 85 */           EntityPlayer randomPlayer = players.get((int)(Math.random() * players.size()));
/* 86 */           Vector3 pos1 = Vector3.fromEntityCenter((Entity)entity);
/* 87 */           Vector3 pos2 = Vector3.fromEntityCenter((Entity)randomPlayer);
/*    */           
/* 89 */           if (randomPlayer.dimension != entity.dimension) {
/* 90 */             int dim1 = entity.dimension;
/* 91 */             int dim2 = randomPlayer.dimension;
/* 92 */             ((EntityPlayerMP)randomPlayer).mcServer.getConfigurationManager().transferPlayerToDimension((EntityPlayerMP)randomPlayer, dim1, ((WorldServer)randomPlayer.worldObj).getDefaultTeleporter());
/* 93 */             ((EntityPlayerMP)entity).mcServer.getConfigurationManager().transferPlayerToDimension((EntityPlayerMP)entity, dim2, ((WorldServer)entity.worldObj).getDefaultTeleporter());
/*    */           } 
/*    */           
/* 96 */           entity.setPositionAndUpdate(pos2.x, pos2.y, pos2.z);
/* 97 */           randomPlayer.setPositionAndUpdate(pos1.x, pos1.y, pos1.z);
/* 98 */           entity.worldObj.playSoundEffect(pos2.x, pos2.y, pos2.z, "mob.endermen.portal", 1.0F, (float)(0.800000011920929D + Math.random() * 0.2D));
/* 99 */           randomPlayer.worldObj.playSoundEffect(pos1.x, pos1.y, pos1.z, "mob.endermen.portal", 1.0F, (float)(0.800000011920929D + Math.random() * 0.2D));
/*    */         } 
/*    */       } 
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\antro\Documents\forgottenRelicsDeobfuscated.jar!\com\integral\forgottenrelics\items\ItemSuperpositionRing.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */