/*     */ package com.integral.forgottenrelics.items;
/*     */ 
/*     */ import com.integral.forgottenrelics.Main;
/*     */ import com.integral.forgottenrelics.handlers.RelicsConfigHandler;
/*     */ import com.integral.forgottenrelics.handlers.SuperpositionHandler;
/*     */ import com.integral.forgottenrelics.packets.EntityMotionMessage;
/*     */ import com.integral.forgottenrelics.packets.OverthrowChatMessage;
/*     */ import com.integral.forgottenrelics.packets.PacketVoidMessage;
/*     */ import cpw.mods.fml.common.network.NetworkRegistry;
/*     */ import cpw.mods.fml.common.network.simpleimpl.IMessage;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.gui.GuiScreen;
/*     */ import net.minecraft.client.renderer.texture.IIconRegister;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.EnumAction;
/*     */ import net.minecraft.item.EnumRarity;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.potion.Potion;
/*     */ import net.minecraft.potion.PotionEffect;
/*     */ import net.minecraft.util.StatCollector;
/*     */ import net.minecraft.world.World;
/*     */ import thaumcraft.api.IWarpingGear;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.api.aspects.AspectList;
/*     */ import thaumcraft.common.items.wands.WandManager;
/*     */ import thaumcraft.common.lib.utils.EntityUtils;
/*     */ import vazkii.botania.common.core.helper.Vector3;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ItemVoidGrimoire
/*     */   extends Item
/*     */   implements IWarpingGear
/*     */ {
/*  45 */   public static final int AerCost = (int)(0.0F * RelicsConfigHandler.voidGrimoireVisMult);
/*  46 */   public static final int TerraCost = (int)(0.0F * RelicsConfigHandler.voidGrimoireVisMult);
/*  47 */   public static final int IgnisCost = (int)(0.0F * RelicsConfigHandler.voidGrimoireVisMult);
/*  48 */   public static final int AquaCost = (int)(0.0F * RelicsConfigHandler.voidGrimoireVisMult);
/*  49 */   public static final int OrdoCost = (int)(9.0F * RelicsConfigHandler.voidGrimoireVisMult);
/*  50 */   public static final int PerditioCost = (int)(16.0F * RelicsConfigHandler.voidGrimoireVisMult);
/*     */   
/*  52 */   public static int localCooldown = 0;
/*     */   
/*  54 */   static HashMap<EntityPlayer, EntityLivingBase> targetList = new HashMap<>();
/*     */   
/*     */   public ItemVoidGrimoire() {
/*  57 */     setMaxStackSize(1);
/*  58 */     setUnlocalizedName("ItemVoidGrimoire");
/*  59 */     setCreativeTab(Main.tabForgottenRelics);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List<String> par3List, boolean par4) {
/*  66 */     if (GuiScreen.isShiftKeyDown()) {
/*  67 */       par3List.add(StatCollector.translateToLocal("item.ItemVoidGrimoire1.lore"));
/*  68 */       par3List.add(StatCollector.translateToLocal("item.ItemVoidGrimoire2.lore"));
/*  69 */       par3List.add(StatCollector.translateToLocal("item.ItemVoidGrimoire3.lore"));
/*  70 */       par3List.add(StatCollector.translateToLocal("item.FREmpty.lore"));
/*  71 */       par3List.add(StatCollector.translateToLocal("item.ItemVoidGrimoire4.lore"));
/*  72 */       par3List.add(StatCollector.translateToLocal("item.ItemVoidGrimoire5.lore"));
/*  73 */       par3List.add(StatCollector.translateToLocal("item.FREmpty.lore"));
/*  74 */       par3List.add(StatCollector.translateToLocal("item.ItemVoidGrimoire6.lore"));
/*  75 */     } else if (GuiScreen.isCtrlKeyDown()) {
/*  76 */       par3List.add(StatCollector.translateToLocal("item.FRVisPerTick.lore"));
/*  77 */       this; par3List.add(" " + StatCollector.translateToLocal("item.FROrdoCost.lore") + (OrdoCost / 100.0D));
/*  78 */       this; par3List.add(" " + StatCollector.translateToLocal("item.FRPerditioCost.lore") + (PerditioCost / 100.0D));
/*     */     } else {
/*     */       
/*  81 */       par3List.add(StatCollector.translateToLocal("item.FRShiftTooltip.lore"));
/*  82 */       par3List.add(StatCollector.translateToLocal("item.FRViscostTooltip.lore"));
/*     */     } 
/*     */     
/*  85 */     par3List.add(StatCollector.translateToLocal("item.FREmpty.lore"));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EnumRarity getRarity(ItemStack itemStack) {
/*  92 */     return EnumRarity.epic;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void registerIcons(IIconRegister iconRegister) {
/*  98 */     this.itemIcon = iconRegister.registerIcon("forgottenrelics:Void_Grimoire");
/*     */   }
/*     */ 
/*     */   
/*     */   public EnumAction getItemUseAction(ItemStack par1ItemStack) {
/* 103 */     return EnumAction.bow;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getMaxItemUseDuration(ItemStack par1ItemStack) {
/* 108 */     return 100;
/*     */   }
/*     */ 
/*     */   
/*     */   public void overthrow(EntityLivingBase entity, EntityPlayer overthrower) {
/* 113 */     if (overthrower.worldObj.isRemote) {
/* 114 */       entity.setDead();
/*     */       
/*     */       return;
/*     */     } 
/* 118 */     double x = (Math.random() - 0.5D) * 20002.0D;
/* 119 */     double z = (Math.random() - 0.5D) * 20002.0D;
/* 120 */     double y = -100000.0D + (Math.random() - 0.5D) * 20002.0D;
/*     */     
/* 122 */     entity.setPositionAndUpdate(x, y, z);
/*     */     
/* 124 */     if (!(entity instanceof EntityPlayer)) {
/* 125 */       entity.setDead();
/*     */     }
/* 127 */     else if (!overthrower.worldObj.isRemote) {
/* 128 */       Main.packetInstance.sendToAll((IMessage)new OverthrowChatMessage(((EntityPlayer)entity).getDisplayName(), overthrower.getDisplayName(), 1));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void onPlayerStoppedUsing(ItemStack stack, World world, EntityPlayer player, int count) {
/* 134 */     if ((world.isRemote & ((count != 1) ? 1 : 0)) != 0) {
/* 135 */       Minecraft.getMinecraft().getSoundHandler().stopSounds();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void onUsingTick(ItemStack stack, EntityPlayer player, int count) {
/* 142 */     this; this; if (!WandManager.consumeVisFromInventory(player, (new AspectList()).add(Aspect.ORDER, OrdoCost).add(Aspect.ENTROPY, PerditioCost))) {
/* 143 */       player.stopUsingItem();
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/* 148 */     this; if (!targetList.containsKey(player)) {
/* 149 */       this; targetList.put(player, null);
/* 150 */       player.stopUsingItem();
/*     */       
/*     */       return;
/*     */     } 
/* 154 */     this; EntityLivingBase target = targetList.get(player);
/*     */     
/* 156 */     if (target != null) {
/* 157 */       target.fallDistance = 0.0F;
/*     */       
/*     */       try {
/* 160 */         target.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 30, 100, true));
/* 161 */       } catch (Exception exception) {}
/*     */       
/* 163 */       target.motionY = 0.03D;
/* 164 */       target.velocityChanged = true;
/*     */       
/* 166 */       if (!player.worldObj.isRemote) {
/* 167 */         Main.packetInstance.sendToAllAround((IMessage)new EntityMotionMessage(target.getEntityId(), target.motionX, target.motionY, target.motionZ, true), new NetworkRegistry.TargetPoint(player.dimension, target.posX, target.posY, target.posZ, 64.0D));
/*     */       }
/*     */       
/* 170 */       Vector3 thisPos = Vector3.fromEntityCenter((Entity)target);
/* 171 */       thisPos.y += 0.03D;
/*     */       
/* 173 */       if (((!player.worldObj.isRemote ? 1 : 0) & ((count == stack.getMaxItemUseDuration()) ? 1 : 0)) != 0) {
/* 174 */         player.worldObj.playSoundEffect(thisPos.x, thisPos.y, thisPos.z, "forgottenrelics:sound.mdcharge", 4.0F, 0.75F);
/*     */       }
/*     */       
/* 177 */       if (!player.worldObj.isRemote) {
/* 178 */         Main.packetInstance.sendToAllAround((IMessage)new PacketVoidMessage(thisPos.x, thisPos.y, thisPos.z, false), new NetworkRegistry.TargetPoint(player.dimension, thisPos.x, thisPos.y, thisPos.z, 64.0D));
/*     */       }
/* 180 */       if (count == 1) {
/*     */         
/* 182 */         if (!player.worldObj.isRemote) {
/* 183 */           SuperpositionHandler.imposeBurst(target.worldObj, target.dimension, thisPos.x, thisPos.y, thisPos.z, 2.0F);
/* 184 */           Main.packetInstance.sendToAllAround((IMessage)new PacketVoidMessage(thisPos.x, thisPos.y, thisPos.z, true), new NetworkRegistry.TargetPoint(player.dimension, thisPos.x, thisPos.y, thisPos.z, 128.0D));
/*     */         } 
/*     */         
/* 187 */         this; target.worldObj.playSoundEffect(target.posX, target.posY, target.posZ, "thaumcraft:craftfail", 4.0F, 0.8F + itemRand.nextFloat() * 0.2F);
/*     */         
/* 189 */         overthrow(target, player);
/*     */         
/* 191 */         SuperpositionHandler.setCasted(player, 30, false);
/*     */         
/* 193 */         if (player.worldObj.isRemote) {
/* 194 */           this; localCooldown = 60;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
/* 202 */     if (!world.isRemote && 
/* 203 */       SuperpositionHandler.isOnCoodown(player)) {
/* 204 */       return stack;
/*     */     }
/* 206 */     if (world.isRemote) {
/* 207 */       this; if (localCooldown != 0)
/* 208 */         return stack; 
/*     */     } 
/* 210 */     Entity pointedEntity = EntityUtils.getPointedEntity(world, (Entity)player, 0.0D, 64.0D, 3.0F);
/*     */     
/* 212 */     if (pointedEntity instanceof EntityLivingBase) {
/* 213 */       this; targetList.put(player, (EntityLivingBase)pointedEntity);
/* 214 */       player.setItemInUse(stack, getMaxItemUseDuration(stack));
/*     */     } else {
/*     */       
/* 217 */       this; targetList.put(player, null);
/*     */     } 
/* 219 */     return stack;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isFull3D() {
/* 224 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getWarp(ItemStack arg0, EntityPlayer arg1) {
/* 229 */     return 3;
/*     */   }
/*     */ }


/* Location:              C:\Users\antro\Documents\forgottenRelicsDeobfuscated.jar!\com\integral\forgottenrelics\items\ItemVoidGrimoire.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */