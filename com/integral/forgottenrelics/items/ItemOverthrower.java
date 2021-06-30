/*     */ package com.integral.forgottenrelics.items;
/*     */ 
/*     */ import com.integral.forgottenrelics.Main;
/*     */ import com.integral.forgottenrelics.handlers.RelicsConfigHandler;
/*     */ import com.integral.forgottenrelics.handlers.SuperpositionHandler;
/*     */ import com.integral.forgottenrelics.packets.BanishmentCastingMessage;
/*     */ import com.integral.forgottenrelics.packets.InfernalParticleMessage;
/*     */ import com.integral.forgottenrelics.packets.LightningBoltMessage;
/*     */ import com.integral.forgottenrelics.packets.OverthrowChatMessage;
/*     */ import cpw.mods.fml.common.network.NetworkRegistry;
/*     */ import cpw.mods.fml.common.network.simpleimpl.IMessage;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.client.gui.GuiScreen;
/*     */ import net.minecraft.client.renderer.texture.IIconRegister;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityList;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.effect.EntityLightningBolt;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.EntityPlayerMP;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.item.EnumAction;
/*     */ import net.minecraft.item.EnumRarity;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.potion.Potion;
/*     */ import net.minecraft.potion.PotionEffect;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.StatCollector;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.common.DimensionManager;
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
/*     */ public class ItemOverthrower
/*     */   extends Item
/*     */   implements IWarpingGear
/*     */ {
/*  52 */   public static final int AerCost = (int)(0.0F * RelicsConfigHandler.overthrowerVisMult);
/*  53 */   public static final int TerraCost = (int)(0.0F * RelicsConfigHandler.overthrowerVisMult);
/*  54 */   public static final int IgnisCost = (int)(8.0F * RelicsConfigHandler.overthrowerVisMult);
/*  55 */   public static final int AquaCost = (int)(0.0F * RelicsConfigHandler.overthrowerVisMult);
/*  56 */   public static final int OrdoCost = (int)(5.0F * RelicsConfigHandler.overthrowerVisMult);
/*  57 */   public static final int PerditioCost = (int)(5.0F * RelicsConfigHandler.overthrowerVisMult);
/*     */   
/*  59 */   static HashMap<EntityPlayer, EntityLivingBase> targetList = new HashMap<>();
/*     */   
/*     */   public ItemOverthrower() {
/*  62 */     setMaxStackSize(1);
/*  63 */     setUnlocalizedName("ItemOverthrower");
/*  64 */     setCreativeTab(Main.tabForgottenRelics);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List<String> par3List, boolean par4) {
/*  71 */     if (GuiScreen.isShiftKeyDown()) {
/*  72 */       par3List.add(StatCollector.translateToLocal("item.ItemOverthrower1.lore"));
/*  73 */       par3List.add(StatCollector.translateToLocal("item.ItemOverthrower2.lore"));
/*  74 */       par3List.add(StatCollector.translateToLocal("item.FREmpty.lore"));
/*  75 */       par3List.add(StatCollector.translateToLocal("item.ItemOverthrower3.lore"));
/*  76 */       par3List.add(StatCollector.translateToLocal("item.ItemOverthrower4.lore"));
/*  77 */       par3List.add(StatCollector.translateToLocal("item.FREmpty.lore"));
/*  78 */       par3List.add(StatCollector.translateToLocal("item.ItemOverthrower5.lore"));
/*  79 */       par3List.add(StatCollector.translateToLocal("item.ItemOverthrower6.lore"));
/*  80 */       par3List.add(StatCollector.translateToLocal("item.ItemOverthrower7.lore"));
/*  81 */     } else if (GuiScreen.isCtrlKeyDown()) {
/*  82 */       par3List.add(StatCollector.translateToLocal("item.FRVisPerTick.lore"));
/*  83 */       this; par3List.add(" " + StatCollector.translateToLocal("item.FRIgnisCost.lore") + (IgnisCost / 100.0D));
/*  84 */       this; par3List.add(" " + StatCollector.translateToLocal("item.FROrdoCost.lore") + (OrdoCost / 100.0D));
/*  85 */       this; par3List.add(" " + StatCollector.translateToLocal("item.FRPerditioCost.lore") + (PerditioCost / 100.0D));
/*     */     } else {
/*     */       
/*  88 */       par3List.add(StatCollector.translateToLocal("item.FRShiftTooltip.lore"));
/*  89 */       par3List.add(StatCollector.translateToLocal("item.FRViscostTooltip.lore"));
/*     */     } 
/*     */     
/*  92 */     par3List.add(StatCollector.translateToLocal("item.FREmpty.lore"));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EnumRarity getRarity(ItemStack itemStack) {
/* 100 */     return EnumRarity.epic;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void registerIcons(IIconRegister iconRegister) {
/* 106 */     this.itemIcon = iconRegister.registerIcon("forgottenrelics:Just_Stop_Blaming_Me_For_Stealing_Calamity_Textures_Already");
/*     */   }
/*     */ 
/*     */   
/*     */   public EnumAction getItemUseAction(ItemStack par1ItemStack) {
/* 111 */     return EnumAction.bow;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getMaxItemUseDuration(ItemStack par1ItemStack) {
/* 116 */     return 150;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void onUpdate(ItemStack itemstack, World world, Entity entity, int i, boolean b) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean overthrow(EntityLivingBase entity, EntityPlayer overthrower) {
/* 126 */     int x = (int)((Math.random() - 0.5D) * 20002.0D);
/* 127 */     int z = (int)((Math.random() - 0.5D) * 20002.0D);
/* 128 */     int y = 124;
/*     */     
/* 130 */     DimensionManager.getWorld(-1).getChunkProvider().loadChunk(x >> 4, z >> 4);
/*     */     
/* 132 */     for (int counter = 124; counter > 0; counter--) {
/* 133 */       boolean valid = SuperpositionHandler.validatePosition((World)DimensionManager.getWorld(-1), x, counter, z);
/*     */       
/* 135 */       if (valid) {
/* 136 */         y = counter;
/*     */ 
/*     */         
/*     */         break;
/*     */       } 
/*     */     } 
/*     */     
/* 143 */     if (y != 124) {
/*     */       
/* 145 */       if (!(entity instanceof EntityPlayerMP)) {
/* 146 */         NBTTagCompound nbt = new NBTTagCompound();
/* 147 */         entity.writeToNBTOptional(nbt);
/*     */ 
/*     */         
/*     */         try {
/* 151 */           EntityLivingBase overthrownEntity = (EntityLivingBase)EntityList.createEntityFromNBT(nbt, (World)DimensionManager.getWorld(-1));
/* 152 */           overthrownEntity.dimension = -1;
/*     */           
/* 154 */           overthrownEntity.setPositionAndUpdate(x, y, z);
/* 155 */           DimensionManager.getWorld(-1).spawnEntityInWorld((Entity)overthrownEntity);
/*     */         }
/* 157 */         catch (Exception exception) {}
/*     */         
/* 159 */         entity.setDead();
/*     */         
/* 161 */         for (int a = 0; a < 12; a++) {
/*     */ 
/*     */ 
/*     */           
/* 165 */           this; this; int xx = MathHelper.floor_double(entity.posX) + itemRand.nextInt(4) - itemRand.nextInt(4), yy = MathHelper.floor_double(entity.posY) + 4; this; this; int zz; for (zz = MathHelper.floor_double(entity.posZ) + itemRand.nextInt(4) - itemRand.nextInt(4); entity.worldObj.isAirBlock(xx, yy, zz) && yy > MathHelper.floor_double(entity.posY) - 4; yy--);
/* 166 */           if (entity.worldObj.isAirBlock(xx, yy + 1, zz) && !entity.worldObj.isAirBlock(xx, yy, zz) && entity.worldObj.getBlock(xx, yy + 1, zz) != Blocks.fire && EntityUtils.canEntityBeSeen((Entity)entity, xx + 0.5D, yy + 1.5D, zz + 0.5D)) {
/* 167 */             entity.worldObj.setBlock(xx, yy + 1, zz, (Block)Blocks.fire, 0, 3);
/*     */           }
/*     */         } 
/*     */         
/* 171 */         return true;
/*     */       } 
/* 173 */       ((EntityPlayerMP)entity).mcServer.getConfigurationManager().transferPlayerToDimension((EntityPlayerMP)entity, -1);
/* 174 */       entity.setPositionAndUpdate(x, y, z);
/* 175 */       Main.packetInstance.sendToAll((IMessage)new OverthrowChatMessage(((EntityPlayer)entity).getDisplayName(), overthrower.getDisplayName(), 0));
/* 176 */       Main.log.info(overthrower.getDisplayName() + " has overthrown " + ((EntityPlayer)entity).getDisplayName() + " into the Nether.");
/* 177 */       return true;
/*     */     } 
/*     */ 
/*     */     
/* 181 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onUsingTick(ItemStack stack, EntityPlayer player, int count) {
/* 189 */     if (player.worldObj.isRemote) {
/*     */       return;
/*     */     }
/* 192 */     this; this; this; this; if (!targetList.containsKey(player) || !WandManager.consumeVisFromInventory(player, (new AspectList()).add(Aspect.FIRE, IgnisCost).add(Aspect.ORDER, OrdoCost).add(Aspect.ENTROPY, PerditioCost))) {
/* 193 */       player.stopUsingItem();
/*     */       
/*     */       return;
/*     */     } 
/* 197 */     this; EntityLivingBase target = targetList.get(player);
/*     */     
/* 199 */     if (target != null) {
/* 200 */       if (!target.isDead) {
/*     */         
/* 202 */         if (((!player.worldObj.isRemote ? 1 : 0) & ((count % 10 == 0) ? 1 : 0) & ((count != stack.getMaxItemUseDuration()) ? 1 : 0)) != 0) {
/* 203 */           player.worldObj.playSoundEffect(player.posX, player.posY, player.posZ, "thaumcraft:fireloop", 0.33F, 2.0F);
/* 204 */           target.worldObj.playSoundEffect(target.posX, target.posY, target.posZ, "thaumcraft:fireloop", 0.33F, 2.0F);
/*     */         } 
/*     */         
/* 207 */         EntityLivingBase theTarget = target;
/* 208 */         Vector3 thisPos = Vector3.fromEntityCenter((Entity)target);
/*     */         
/* 210 */         if (!player.worldObj.isRemote) {
/* 211 */           Main.packetInstance.sendToAllAround((IMessage)new BanishmentCastingMessage(thisPos.x, thisPos.y, thisPos.z, 5), new NetworkRegistry.TargetPoint(target.dimension, target.posX, target.posY, target.posZ, 64.0D));
/*     */         }
/*     */         try {
/* 214 */           theTarget.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 30, 2, true));
/* 215 */         } catch (Exception exception) {}
/*     */         
/* 217 */         if (count == 1) {
/* 218 */           if ((((theTarget.dimension != -1) ? 1 : 0) & (!player.worldObj.isRemote ? 1 : 0)) != 0) {
/* 219 */             boolean gotEffect = false;
/* 220 */             for (int counter = 8; counter >= 0; counter--) {
/* 221 */               if (overthrow(theTarget, player)) {
/* 222 */                 gotEffect = true;
/*     */ 
/*     */                 
/*     */                 break;
/*     */               } 
/*     */             } 
/*     */             
/* 229 */             if (((!gotEffect ? 1 : 0) & (!(theTarget instanceof EntityPlayerMP) ? 1 : 0)) != 0) {
/* 230 */               theTarget.setPositionAndUpdate(0.0D, 0.0D, 0.0D);
/* 231 */               theTarget.setDead();
/*     */             }
/*     */           
/*     */           }
/* 235 */           else if ((player.worldObj.isRemote & ((theTarget.dimension != -1) ? 1 : 0)) != 0) {
/* 236 */             theTarget.setPositionAndUpdate(0.0D, 0.0D, 0.0D);
/* 237 */             theTarget.setDead();
/*     */           } 
/* 239 */           if (!player.worldObj.isRemote) {
/* 240 */             for (int counter = 3; counter > 0; counter--)
/* 241 */               player.worldObj.spawnEntityInWorld((Entity)new EntityLightningBolt(player.worldObj, thisPos.x - 0.5D, thisPos.y - (theTarget.height / 2.0F), thisPos.z - 0.5D)); 
/* 242 */             Main.packetInstance.sendToAllAround((IMessage)new LightningBoltMessage(thisPos.x - 0.5D, thisPos.y - (theTarget.height / 2.0F), thisPos.z - 0.5D, 3), new NetworkRegistry.TargetPoint(player.dimension, thisPos.x, thisPos.y - (theTarget.height / 2.0F), thisPos.z, 128.0D));
/* 243 */             Main.packetInstance.sendToAllAround((IMessage)new InfernalParticleMessage(thisPos.x, thisPos.y, thisPos.z, 128), new NetworkRegistry.TargetPoint(player.dimension, thisPos.x, thisPos.y, thisPos.z, 128.0D));
/*     */           } 
/*     */ 
/*     */ 
/*     */           
/*     */           return;
/*     */         } 
/*     */       } else {
/* 251 */         this; targetList.put(player, null);
/* 252 */         player.stopUsingItem();
/*     */         
/*     */         return;
/*     */       } 
/*     */     } else {
/* 257 */       this; targetList.put(player, null);
/* 258 */       player.stopUsingItem();
/*     */       return;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
/* 266 */     if (player.dimension == -1) {
/* 267 */       return stack;
/*     */     }
/* 269 */     Entity pointedEntity = EntityUtils.getPointedEntity(world, (Entity)player, 0.0D, 64.0D, 3.0F);
/*     */     
/* 271 */     if (pointedEntity instanceof EntityLivingBase) {
/* 272 */       this; targetList.put(player, (EntityLivingBase)pointedEntity);
/* 273 */       player.setItemInUse(stack, getMaxItemUseDuration(stack));
/*     */     } else {
/*     */       
/* 276 */       this; targetList.put(player, null);
/*     */     } 
/* 278 */     return stack;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isFull3D() {
/* 283 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getWarp(ItemStack arg0, EntityPlayer arg1) {
/* 288 */     return 2;
/*     */   }
/*     */ }


/* Location:              C:\Users\antro\Documents\forgottenRelicsDeobfuscated.jar!\com\integral\forgottenrelics\items\ItemOverthrower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */