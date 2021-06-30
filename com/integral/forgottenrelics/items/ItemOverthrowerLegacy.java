/*     */ package com.integral.forgottenrelics.items;
/*     */ 
/*     */ import com.integral.forgottenrelics.Main;
/*     */ import com.integral.forgottenrelics.handlers.RelicsConfigHandler;
/*     */ import com.integral.forgottenrelics.handlers.SuperpositionHandler;
/*     */ import com.integral.forgottenrelics.packets.BanishmentCastingMessage;
/*     */ import com.integral.forgottenrelics.packets.InfernalParticleMessage;
/*     */ import com.integral.forgottenrelics.packets.ItemUseMessage;
/*     */ import com.integral.forgottenrelics.packets.LightningBoltMessage;
/*     */ import com.integral.forgottenrelics.packets.OverthrowChatMessage;
/*     */ import cpw.mods.fml.common.network.NetworkRegistry;
/*     */ import cpw.mods.fml.common.network.simpleimpl.IMessage;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
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
/*     */ import net.minecraft.inventory.Container;
/*     */ import net.minecraft.item.EnumAction;
/*     */ import net.minecraft.item.EnumRarity;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.potion.Potion;
/*     */ import net.minecraft.potion.PotionEffect;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.StatCollector;
/*     */ import net.minecraft.world.ChunkCoordIntPair;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.chunk.Chunk;
/*     */ import net.minecraftforge.common.DimensionManager;
/*     */ import net.minecraftforge.common.ForgeChunkManager;
/*     */ import thaumcraft.api.IWarpingGear;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.api.aspects.AspectList;
/*     */ import thaumcraft.common.items.wands.WandManager;
/*     */ import thaumcraft.common.lib.utils.EntityUtils;
/*     */ import vazkii.botania.common.core.helper.ItemNBTHelper;
/*     */ import vazkii.botania.common.core.helper.Vector3;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ItemOverthrowerLegacy
/*     */   extends Item
/*     */   implements IWarpingGear
/*     */ {
/*  57 */   public static final int AerCost = (int)(0.0F * RelicsConfigHandler.overthrowerVisMult);
/*  58 */   public static final int TerraCost = (int)(0.0F * RelicsConfigHandler.overthrowerVisMult);
/*  59 */   public static final int IgnisCost = (int)(8.0F * RelicsConfigHandler.overthrowerVisMult);
/*  60 */   public static final int AquaCost = (int)(0.0F * RelicsConfigHandler.overthrowerVisMult);
/*  61 */   public static final int OrdoCost = (int)(5.0F * RelicsConfigHandler.overthrowerVisMult);
/*  62 */   public static final int PerditioCost = (int)(5.0F * RelicsConfigHandler.overthrowerVisMult);
/*     */   
/*     */   public ItemOverthrowerLegacy() {
/*  65 */     setMaxStackSize(1);
/*  66 */     setUnlocalizedName("ItemOverthrower");
/*  67 */     setCreativeTab(Main.tabForgottenRelics);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List<String> par3List, boolean par4) {
/*  74 */     if (GuiScreen.isShiftKeyDown()) {
/*  75 */       par3List.add(StatCollector.translateToLocal("item.ItemOverthrower1.lore"));
/*  76 */       par3List.add(StatCollector.translateToLocal("item.ItemOverthrower2.lore"));
/*  77 */       par3List.add(StatCollector.translateToLocal("item.FREmpty.lore"));
/*  78 */       par3List.add(StatCollector.translateToLocal("item.ItemOverthrower3.lore"));
/*  79 */       par3List.add(StatCollector.translateToLocal("item.ItemOverthrower4.lore"));
/*  80 */       par3List.add(StatCollector.translateToLocal("item.FREmpty.lore"));
/*  81 */       par3List.add(StatCollector.translateToLocal("item.ItemOverthrower5.lore"));
/*  82 */       par3List.add(StatCollector.translateToLocal("item.ItemOverthrower6.lore"));
/*  83 */       par3List.add(StatCollector.translateToLocal("item.ItemOverthrower7.lore"));
/*  84 */     } else if (GuiScreen.isCtrlKeyDown()) {
/*  85 */       par3List.add(StatCollector.translateToLocal("item.FRVisPerTick.lore"));
/*  86 */       this; par3List.add(" " + StatCollector.translateToLocal("item.FRIgnisCost.lore") + (IgnisCost / 100.0D));
/*  87 */       this; par3List.add(" " + StatCollector.translateToLocal("item.FROrdoCost.lore") + (OrdoCost / 100.0D));
/*  88 */       this; par3List.add(" " + StatCollector.translateToLocal("item.FRPerditioCost.lore") + (PerditioCost / 100.0D));
/*     */     } else {
/*     */       
/*  91 */       par3List.add(StatCollector.translateToLocal("item.FRShiftTooltip.lore"));
/*  92 */       par3List.add(StatCollector.translateToLocal("item.FRViscostTooltip.lore"));
/*     */     } 
/*     */     
/*  95 */     par3List.add(StatCollector.translateToLocal("item.FREmpty.lore"));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EnumRarity getRarity(ItemStack itemStack) {
/* 103 */     return EnumRarity.epic;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void registerIcons(IIconRegister iconRegister) {
/* 109 */     this.itemIcon = iconRegister.registerIcon("forgottenrelics:Just_Stop_Blaming_Me_For_Stealing_Calamity_Textures_Already");
/*     */   }
/*     */ 
/*     */   
/*     */   public EnumAction getItemUseAction(ItemStack par1ItemStack) {
/* 114 */     return EnumAction.bow;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getMaxItemUseDuration(ItemStack par1ItemStack) {
/* 119 */     return 100;
/*     */   }
/*     */ 
/*     */   
/*     */   public void onUpdate(ItemStack itemstack, World world, Entity entity, int i, boolean b) {
/* 124 */     if ((entity instanceof EntityPlayer & (!world.isRemote ? 1 : 0)) != 0) {
/* 125 */       EntityPlayer player = (EntityPlayer)entity;
/*     */       
/* 127 */       if (ItemNBTHelper.getInt(itemstack, "StoredID", -1) != -1 && (
/* 128 */         !player.isUsingItem() || player.getHeldItem() != itemstack)) {
/* 129 */         ItemNBTHelper.setInt(itemstack, "StoredID", -1);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean overthrow(EntityLivingBase entity, EntityPlayer overthrower) {
/* 140 */     ForgeChunkManager.Ticket ticket = ForgeChunkManager.requestTicket(Main.instance, (World)DimensionManager.getWorld(-1), ForgeChunkManager.Type.NORMAL);
/*     */     
/* 142 */     int x = (int)((Math.random() - 0.5D) * 20002.0D);
/* 143 */     int z = (int)((Math.random() - 0.5D) * 20002.0D);
/* 144 */     int y = 124;
/*     */     
/* 146 */     Chunk chunk = DimensionManager.getWorld(-1).getChunkFromBlockCoords(x, z);
/*     */     
/* 148 */     ForgeChunkManager.forceChunk(ticket, new ChunkCoordIntPair(chunk.xPosition, chunk.zPosition));
/*     */     
/* 150 */     for (int counter = 124; counter > 0; counter--) {
/* 151 */       boolean valid = SuperpositionHandler.validatePosition((World)DimensionManager.getWorld(-1), x, counter, z);
/*     */       
/* 153 */       if (valid) {
/* 154 */         y = counter;
/*     */ 
/*     */         
/*     */         break;
/*     */       } 
/*     */     } 
/*     */     
/* 161 */     if (y != 124) {
/*     */       
/* 163 */       if (!(entity instanceof EntityPlayerMP)) {
/* 164 */         NBTTagCompound nbt = new NBTTagCompound();
/* 165 */         entity.writeToNBTOptional(nbt);
/*     */ 
/*     */         
/*     */         try {
/* 169 */           EntityLivingBase overthrownEntity = (EntityLivingBase)EntityList.createEntityFromNBT(nbt, (World)DimensionManager.getWorld(-1));
/* 170 */           overthrownEntity.dimension = -1;
/*     */           
/* 172 */           overthrownEntity.setPositionAndUpdate(x, y, z);
/* 173 */           DimensionManager.getWorld(-1).spawnEntityInWorld((Entity)overthrownEntity);
/* 174 */         } catch (Exception exception) {}
/*     */         
/* 176 */         entity.setDead();
/*     */         
/* 178 */         for (int a = 0; a < 12; a++) {
/*     */ 
/*     */ 
/*     */           
/* 182 */           this; this; int xx = MathHelper.floor_double(entity.posX) + itemRand.nextInt(4) - itemRand.nextInt(4), yy = MathHelper.floor_double(entity.posY) + 4; this; this; int zz; for (zz = MathHelper.floor_double(entity.posZ) + itemRand.nextInt(4) - itemRand.nextInt(4); entity.worldObj.isAirBlock(xx, yy, zz) && yy > MathHelper.floor_double(entity.posY) - 4; yy--);
/* 183 */           if (entity.worldObj.isAirBlock(xx, yy + 1, zz) && !entity.worldObj.isAirBlock(xx, yy, zz) && entity.worldObj.getBlock(xx, yy + 1, zz) != Blocks.fire && EntityUtils.canEntityBeSeen((Entity)entity, xx + 0.5D, yy + 1.5D, zz + 0.5D)) {
/* 184 */             entity.worldObj.setBlock(xx, yy + 1, zz, (Block)Blocks.fire, 0, 3);
/*     */           }
/*     */         } 
/*     */         
/* 188 */         ForgeChunkManager.releaseTicket(ticket);
/* 189 */         return true;
/*     */       } 
/* 191 */       ((EntityPlayerMP)entity).mcServer.getConfigurationManager().transferPlayerToDimension((EntityPlayerMP)entity, -1);
/* 192 */       entity.setPositionAndUpdate(x, y, z);
/* 193 */       Main.packetInstance.sendToAll((IMessage)new OverthrowChatMessage(overthrower.getDisplayName(), ((EntityPlayer)entity).getDisplayName(), 0));
/* 194 */       Main.log.info(overthrower.getDisplayName() + " has overthrown " + ((EntityPlayer)entity).getDisplayName() + " into the Nether.");
/* 195 */       ForgeChunkManager.releaseTicket(ticket);
/* 196 */       return true;
/*     */     } 
/*     */ 
/*     */     
/* 200 */     ForgeChunkManager.releaseTicket(ticket);
/* 201 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onUsingTick(ItemStack stack, EntityPlayer player, int count) {
/* 210 */     this; this; this; if (!WandManager.consumeVisFromInventory(player, (new AspectList()).add(Aspect.FIRE, IgnisCost).add(Aspect.ORDER, OrdoCost).add(Aspect.ENTROPY, PerditioCost))) {
/* 211 */       count--;
/*     */       
/*     */       return;
/*     */     } 
/* 215 */     int targetID = ItemNBTHelper.getInt(stack, "StoredID", -1);
/* 216 */     if (targetID == -1) {
/*     */       return;
/*     */     }
/*     */ 
/*     */     
/* 221 */     Entity target = player.worldObj.getEntityByID(targetID);
/*     */     
/* 223 */     if (target != null) {
/* 224 */       if (!target.isDead) {
/*     */         
/* 226 */         if (((!player.worldObj.isRemote ? 1 : 0) & ((count % 10 == 0) ? 1 : 0) & ((count != stack.getMaxItemUseDuration()) ? 1 : 0)) != 0) {
/* 227 */           player.worldObj.playSoundEffect(player.posX, player.posY, player.posZ, "thaumcraft:fireloop", 0.33F, 2.0F);
/* 228 */           target.worldObj.playSoundEffect(target.posX, target.posY, target.posZ, "thaumcraft:fireloop", 0.33F, 2.0F);
/*     */         } 
/*     */         
/* 231 */         EntityLivingBase theTarget = (EntityLivingBase)target;
/* 232 */         Vector3 thisPos = Vector3.fromEntityCenter(target);
/*     */         
/* 234 */         if (!player.worldObj.isRemote) {
/* 235 */           Main.packetInstance.sendToAllAround((IMessage)new BanishmentCastingMessage(thisPos.x, thisPos.y, thisPos.z, 5), new NetworkRegistry.TargetPoint(target.dimension, target.posX, target.posY, target.posZ, 64.0D));
/*     */         }
/* 237 */         theTarget.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 30, 2, true));
/*     */         
/* 239 */         if (count == 1) {
/*     */           
/* 241 */           if ((((theTarget.dimension != -1) ? 1 : 0) & (!player.worldObj.isRemote ? 1 : 0)) != 0) {
/* 242 */             boolean gotEffect = false;
/* 243 */             for (int counter = 8; counter >= 0; counter--) {
/* 244 */               if (overthrow(theTarget, player)) {
/* 245 */                 gotEffect = true;
/*     */ 
/*     */                 
/*     */                 break;
/*     */               } 
/*     */             } 
/*     */             
/* 252 */             if (((!gotEffect ? 1 : 0) & (!(theTarget instanceof EntityPlayerMP) ? 1 : 0)) != 0) {
/* 253 */               theTarget.setPositionAndUpdate(0.0D, 0.0D, 0.0D);
/* 254 */               theTarget.setDead();
/*     */             }
/*     */           
/*     */           }
/* 258 */           else if ((player.worldObj.isRemote & ((theTarget.dimension != -1) ? 1 : 0)) != 0) {
/* 259 */             theTarget.setPositionAndUpdate(0.0D, 0.0D, 0.0D);
/* 260 */             theTarget.setDead();
/*     */           } 
/* 262 */           if (!player.worldObj.isRemote) {
/* 263 */             for (int counter = 3; counter > 0; counter--)
/* 264 */               player.worldObj.spawnEntityInWorld((Entity)new EntityLightningBolt(player.worldObj, thisPos.x - 0.5D, thisPos.y - (theTarget.height / 2.0F), thisPos.z - 0.5D)); 
/* 265 */             Main.packetInstance.sendToAllAround((IMessage)new LightningBoltMessage(thisPos.x - 0.5D, thisPos.y - (theTarget.height / 2.0F), thisPos.z - 0.5D, 3), new NetworkRegistry.TargetPoint(player.dimension, thisPos.x, thisPos.y - (theTarget.height / 2.0F), thisPos.z, 128.0D));
/* 266 */             Main.packetInstance.sendToAllAround((IMessage)new InfernalParticleMessage(thisPos.x, thisPos.y, thisPos.z, 128), new NetworkRegistry.TargetPoint(player.dimension, thisPos.x, thisPos.y, thisPos.z, 128.0D));
/*     */           } 
/*     */ 
/*     */           
/*     */           return;
/*     */         } 
/*     */       } 
/*     */     } else {
/* 274 */       ItemNBTHelper.setInt(stack, "StoredID", -1);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
/* 282 */     if (((!world.isRemote ? 1 : 0) & ((player.dimension != -1) ? 1 : 0)) != 0) {
/* 283 */       Entity pointedEntity = EntityUtils.getPointedEntity(world, (Entity)player, 0.0D, 128.0D, 3.0F);
/*     */       
/* 285 */       if (pointedEntity instanceof EntityLivingBase)
/*     */       {
/* 287 */         if (pointedEntity.getEntityId() != ItemNBTHelper.getInt(stack, "StoredID", -1)) {
/* 288 */           ItemNBTHelper.setInt(stack, "StoredID", pointedEntity.getEntityId());
/* 289 */           player.setItemInUse(stack, getMaxItemUseDuration(stack));
/*     */           
/* 291 */           Container inventory = player.inventoryContainer;
/* 292 */           ((EntityPlayerMP)player).sendContainerToPlayer(inventory);
/*     */         
/*     */         }
/*     */         else {
/*     */           
/* 297 */           player.setItemInUse(stack, getMaxItemUseDuration(stack));
/* 298 */           Main.packetInstance.sendToAllAround((IMessage)new ItemUseMessage(stack.getMaxItemUseDuration()), new NetworkRegistry.TargetPoint(player.dimension, player.posX, player.posY, player.posZ, 128.0D));
/*     */         } 
/*     */       }
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 305 */     return stack;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isFull3D() {
/* 310 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getWarp(ItemStack arg0, EntityPlayer arg1) {
/* 315 */     return 2;
/*     */   }
/*     */ }


/* Location:              C:\Users\antro\Documents\forgottenRelicsDeobfuscated.jar!\com\integral\forgottenrelics\items\ItemOverthrowerLegacy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */