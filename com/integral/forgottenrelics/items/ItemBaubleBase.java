/*     */ package com.integral.forgottenrelics.items;
/*     */ 
/*     */ import baubles.api.BaubleType;
/*     */ import baubles.api.IBauble;
/*     */ import baubles.common.container.InventoryBaubles;
/*     */ import baubles.common.lib.PlayerHandler;
/*     */ import cpw.mods.fml.common.Optional.Interface;
/*     */ import cpw.mods.fml.common.Optional.Method;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.List;
/*     */ import java.util.UUID;
/*     */ import net.minecraft.client.gui.GuiScreen;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.stats.StatBase;
/*     */ import net.minecraft.util.StatCollector;
/*     */ import net.minecraft.world.World;
/*     */ import thaumcraft.api.IRunicArmor;
/*     */ import vazkii.botania.api.item.ICosmeticAttachable;
/*     */ import vazkii.botania.api.item.IPhantomInkable;
/*     */ import vazkii.botania.client.core.helper.RenderHelper;
/*     */ import vazkii.botania.common.achievement.ModAchievements;
/*     */ import vazkii.botania.common.core.helper.ItemNBTHelper;
/*     */ import vazkii.botania.common.entity.EntityDoppleganger;
/*     */ 
/*     */ 
/*     */ @Interface(modid = "Thaumcraft", iface = "thaumcraft.api.IRunicArmor")
/*     */ public abstract class ItemBaubleBase
/*     */   extends Item
/*     */   implements IBauble, ICosmeticAttachable, IPhantomInkable, IRunicArmor
/*     */ {
/*     */   private static final String TAG_HASHCODE = "playerHashcode";
/*     */   private static final String TAG_BAUBLE_UUID_MOST = "baubleUUIDMost";
/*     */   private static final String TAG_BAUBLE_UUID_LEAST = "baubleUUIDLeast";
/*     */   private static final String TAG_COSMETIC_ITEM = "cosmeticItem";
/*     */   private static final String TAG_PHANTOM_INK = "phantomInk";
/*     */   
/*     */   public ItemBaubleBase(String name) {
/*  44 */     setMaxStackSize(1);
/*  45 */     setUnlocalizedName(name);
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
/*  50 */     if (!EntityDoppleganger.isTruePlayer((Entity)par3EntityPlayer)) {
/*  51 */       return par1ItemStack;
/*     */     }
/*  53 */     if (canEquip(par1ItemStack, (EntityLivingBase)par3EntityPlayer)) {
/*  54 */       InventoryBaubles baubles = PlayerHandler.getPlayerBaubles(par3EntityPlayer);
/*  55 */       for (int i = 0; i < baubles.getSizeInventory(); i++) {
/*  56 */         if (baubles.isItemValidForSlot(i, par1ItemStack)) {
/*  57 */           ItemStack stackInSlot = baubles.getStackInSlot(i);
/*  58 */           if (stackInSlot == null || ((IBauble)stackInSlot.getItem()).canUnequip(stackInSlot, (EntityLivingBase)par3EntityPlayer)) {
/*  59 */             if (!par2World.isRemote) {
/*  60 */               baubles.setInventorySlotContents(i, par1ItemStack.copy());
/*  61 */               if (!par3EntityPlayer.capabilities.isCreativeMode) {
/*  62 */                 par3EntityPlayer.inventory.setInventorySlotContents(par3EntityPlayer.inventory.currentItem, null);
/*     */               }
/*     */             } 
/*  65 */             if (stackInSlot != null) {
/*  66 */               ((IBauble)stackInSlot.getItem()).onUnequipped(stackInSlot, (EntityLivingBase)par3EntityPlayer);
/*  67 */               return stackInSlot.copy();
/*     */             } 
/*     */             
/*     */             break;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*  75 */     return par1ItemStack;
/*     */   }
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List<String> par3List, boolean par4) {
/*  81 */     if (GuiScreen.isShiftKeyDown())
/*  82 */     { addHiddenTooltip(par1ItemStack, par2EntityPlayer, par3List, par4); }
/*  83 */     else { addStringToTooltip(StatCollector.translateToLocal("botaniamisc.shiftinfo"), par3List); }
/*     */   
/*     */   }
/*     */   public void addHiddenTooltip(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List<String> par3List, boolean par4) {
/*  87 */     BaubleType type = getBaubleType(par1ItemStack);
/*  88 */     addStringToTooltip(StatCollector.translateToLocal("botania.baubletype." + type.name().toLowerCase()), par3List);
/*     */     
/*  90 */     String key = RenderHelper.getKeyDisplayString("Baubles Inventory");
/*     */     
/*  92 */     if (key != null) {
/*  93 */       addStringToTooltip(StatCollector.translateToLocal("botania.baubletooltip").replaceAll("%key%", key), par3List);
/*     */     }
/*  95 */     ItemStack cosmetic = getCosmeticItem(par1ItemStack);
/*  96 */     if (cosmetic != null) {
/*  97 */       addStringToTooltip(String.format(StatCollector.translateToLocal("botaniamisc.hasCosmetic"), new Object[] { cosmetic.getDisplayName() }), par3List);
/*     */     }
/*  99 */     if (hasPhantomInk(par1ItemStack))
/* 100 */       addStringToTooltip(StatCollector.translateToLocal("botaniamisc.hasPhantomInk"), par3List); 
/*     */   }
/*     */   
/*     */   void addStringToTooltip(String s, List<String> tooltip) {
/* 104 */     tooltip.add(s.replaceAll("&", "§"));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canEquip(ItemStack stack, EntityLivingBase player) {
/* 109 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canUnequip(ItemStack stack, EntityLivingBase player) {
/* 114 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void onWornTick(ItemStack stack, EntityLivingBase player) {
/* 119 */     if (getLastPlayerHashcode(stack) != player.hashCode()) {
/* 120 */       onEquippedOrLoadedIntoWorld(stack, player);
/* 121 */       setLastPlayerHashcode(stack, player.hashCode());
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void onEquipped(ItemStack stack, EntityLivingBase player) {
/* 127 */     if (player != null) {
/* 128 */       if (!player.worldObj.isRemote) {
/* 129 */         player.worldObj.playSoundAtEntity((Entity)player, "botania:equipBauble", 0.1F, 1.3F);
/*     */       }
/* 131 */       if (player instanceof EntityPlayer) {
/* 132 */         ((EntityPlayer)player).addStat((StatBase)ModAchievements.baubleWear, 1);
/*     */       }
/* 134 */       onEquippedOrLoadedIntoWorld(stack, player);
/* 135 */       setLastPlayerHashcode(stack, player.hashCode());
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void onEquippedOrLoadedIntoWorld(ItemStack stack, EntityLivingBase player) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void onUnequipped(ItemStack stack, EntityLivingBase player) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack getCosmeticItem(ItemStack stack) {
/* 150 */     NBTTagCompound cmp = ItemNBTHelper.getCompound(stack, "cosmeticItem", true);
/* 151 */     if (cmp == null)
/* 152 */       return null; 
/* 153 */     return ItemStack.loadItemStackFromNBT(cmp);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCosmeticItem(ItemStack stack, ItemStack cosmetic) {
/* 158 */     NBTTagCompound cmp = new NBTTagCompound();
/* 159 */     if (cosmetic != null)
/* 160 */       cosmetic.writeToNBT(cmp); 
/* 161 */     ItemNBTHelper.setCompound(stack, "cosmeticItem", cmp);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasContainerItem(ItemStack stack) {
/* 166 */     return (getContainerItem(stack) != null);
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack getContainerItem(ItemStack itemStack) {
/* 171 */     return getCosmeticItem(itemStack);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean doesContainerItemLeaveCraftingGrid(ItemStack p_77630_1_) {
/* 176 */     return false;
/*     */   }
/*     */   
/*     */   public static UUID getBaubleUUID(ItemStack stack) {
/* 180 */     long most = ItemNBTHelper.getLong(stack, "baubleUUIDMost", 0L);
/* 181 */     if (most == 0L) {
/* 182 */       UUID uuid = UUID.randomUUID();
/* 183 */       ItemNBTHelper.setLong(stack, "baubleUUIDMost", uuid.getMostSignificantBits());
/* 184 */       ItemNBTHelper.setLong(stack, "baubleUUIDLeast", uuid.getLeastSignificantBits());
/* 185 */       return getBaubleUUID(stack);
/*     */     } 
/*     */     
/* 188 */     long least = ItemNBTHelper.getLong(stack, "baubleUUIDLeast", 0L);
/* 189 */     return new UUID(most, least);
/*     */   }
/*     */   
/*     */   public static int getLastPlayerHashcode(ItemStack stack) {
/* 193 */     return ItemNBTHelper.getInt(stack, "playerHashcode", 0);
/*     */   }
/*     */   
/*     */   public static void setLastPlayerHashcode(ItemStack stack, int hash) {
/* 197 */     ItemNBTHelper.setInt(stack, "playerHashcode", hash);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasPhantomInk(ItemStack stack) {
/* 202 */     return ItemNBTHelper.getBoolean(stack, "phantomInk", false);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setPhantomInk(ItemStack stack, boolean ink) {
/* 207 */     ItemNBTHelper.setBoolean(stack, "phantomInk", ink);
/*     */   }
/*     */ 
/*     */   
/*     */   @Method(modid = "Thaumcraft")
/*     */   public int getRunicCharge(ItemStack itemstack) {
/* 213 */     return 0;
/*     */   }
/*     */ }


/* Location:              C:\Users\antro\Documents\forgottenRelicsDeobfuscated.jar!\com\integral\forgottenrelics\items\ItemBaubleBase.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */