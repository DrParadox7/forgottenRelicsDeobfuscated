/*     */ package com.integral.forgottenrelics.items;
/*     */ 
/*     */ import com.google.common.collect.ArrayListMultimap;
/*     */ import com.integral.forgottenrelics.Main;
/*     */ import com.integral.forgottenrelics.handlers.RelicsConfigHandler;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import net.minecraft.client.gui.GuiScreen;
/*     */ import net.minecraft.client.renderer.texture.IIconRegister;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.EnumRarity;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.util.EnumChatFormatting;
/*     */ import net.minecraft.util.StatCollector;
/*     */ import net.minecraft.world.World;
/*     */ import thaumcraft.api.IWarpingGear;
/*     */ 
/*     */ public class ItemOblivionStone
/*     */   extends Item
/*     */   implements IWarpingGear
/*     */ {
/*     */   public ItemOblivionStone() {
/*  31 */     setMaxDamage(0);
/*  32 */     setMaxStackSize(1);
/*  33 */     setUnlocalizedName("ItemOblivionStone");
/*  34 */     setCreativeTab(Main.tabForgottenRelics);
/*     */   }
/*     */ 
/*     */   
/*     */   public void registerIcons(IIconRegister iconRegister) {
/*  39 */     this.itemIcon = iconRegister.registerIcon("forgottenrelics:Oblivion_Stone");
/*     */   }
/*     */   
/*     */   public EnumRarity getRarity(ItemStack itemstack) {
/*  43 */     return EnumRarity.epic;
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer player) {
/*  48 */     int damage = itemstack.getItemDamage();
/*     */     
/*  50 */     if (player.isSneaking()) {
/*     */       
/*  52 */       if (damage < 100) {
/*  53 */         damage += 100;
/*  54 */         player.playSound("dftoolkit:sound.hhoff", 1.0F, 1.0F);
/*     */       } else {
/*  56 */         player.playSound("dftoolkit:sound.hhon", 1.0F, 1.0F);
/*  57 */         damage -= 100;
/*     */       } 
/*     */       
/*  60 */       itemstack.setItemDamage(damage);
/*     */     }
/*     */     else {
/*     */       
/*  64 */       if (damage == 0 || damage == 1 || damage == 100 || damage == 101) {
/*  65 */         damage++;
/*  66 */       } else if (damage == 2 || damage == 102) {
/*  67 */         damage -= 2;
/*     */       } 
/*     */       
/*  70 */       player.playSound("random.orb", 1.0F, (float)(0.800000011920929D + Math.random() * 0.20000000298023224D));
/*     */       
/*  72 */       itemstack.setItemDamage(damage);
/*     */     } 
/*     */     
/*  75 */     player.swingItem();
/*     */     
/*  77 */     return itemstack;
/*     */   }
/*     */ 
/*     */   
/*     */   public void onUpdate(ItemStack itemstack, World world, Entity entity, int i, boolean b) {
/*  82 */     if (!(entity instanceof EntityPlayer) || entity.ticksExisted % 10 != 0) {
/*     */       return;
/*     */     }
/*  85 */     EntityPlayer player = (EntityPlayer)entity;
/*     */     
/*  87 */     int damage = itemstack.getItemDamage();
/*  88 */     if (damage >= 100 || !itemstack.hasTagCompound()) {
/*     */       return;
/*     */     }
/*  91 */     NBTTagCompound nbt = itemstack.getTagCompound();
/*  92 */     int[] arr = nbt.getIntArray("SupersolidID");
/*  93 */     int[] meta = nbt.getIntArray("SupersolidMetaID");
/*     */     
/*  95 */     this; consumeStuff(player, arr, meta, damage);
/*     */   }
/*     */ 
/*     */   
/*     */   public static void consumeStuff(EntityPlayer player, int[] ID, int[] meta, int mode) {
/* 100 */     HashMap<Integer, ItemStack> stackMap = new HashMap<>();
/* 101 */     int cycleCounter = 0;
/* 102 */     int filledStacks = 0;
/*     */     
/* 104 */     for (int slot = 0; slot < player.inventory.mainInventory.length; slot++) {
/* 105 */       if (player.inventory.mainInventory[slot] != null) {
/* 106 */         filledStacks++;
/* 107 */         if (player.inventory.mainInventory[slot].getItem() != Main.itemOblivionStone) {
/* 108 */           stackMap.put(Integer.valueOf(slot), player.inventory.mainInventory[slot]);
/*     */         }
/*     */       } 
/*     */     } 
/* 112 */     if (stackMap.size() == 0) {
/*     */       return;
/*     */     }
/* 115 */     if (mode == 0) {
/*     */       
/* 117 */       for (int sID : ID) {
/* 118 */         for (Iterator<Integer> iterator = stackMap.keySet().iterator(); iterator.hasNext(); ) { int i = ((Integer)iterator.next()).intValue();
/* 119 */           if (meta[cycleCounter] != -1) {
/* 120 */             if ((((((ItemStack)stackMap.get(Integer.valueOf(i))).getItem() == Item.getItemById(sID)) ? 1 : 0) & ((((ItemStack)stackMap.get(Integer.valueOf(i))).getItemDamage() == meta[cycleCounter]) ? 1 : 0)) != 0)
/* 121 */               player.inventory.setInventorySlotContents(i, null);  continue;
/*     */           } 
/* 123 */           if (((ItemStack)stackMap.get(Integer.valueOf(i))).getItem() == Item.getItemById(sID)) {
/* 124 */             player.inventory.setInventorySlotContents(i, null);
/*     */           } }
/*     */ 
/*     */         
/* 128 */         cycleCounter++;
/*     */       } 
/* 130 */     } else if (mode == 1) {
/*     */       
/* 132 */       for (int sID : ID) {
/* 133 */         HashMap<Integer, ItemStack> localStackMap = new HashMap<>(stackMap);
/* 134 */         ArrayListMultimap arrayListMultimap = ArrayListMultimap.create();
/*     */         Iterator<Integer> iterator;
/* 136 */         for (iterator = stackMap.keySet().iterator(); iterator.hasNext(); ) { int i = ((Integer)iterator.next()).intValue();
/* 137 */           if (meta[cycleCounter] != -1) {
/* 138 */             if (((ItemStack)stackMap.get(Integer.valueOf(i))).getItem() != Item.getItemById(sID) || ((ItemStack)stackMap.get(Integer.valueOf(i))).getItemDamage() != meta[cycleCounter])
/* 139 */               localStackMap.remove(Integer.valueOf(i));  continue;
/*     */           } 
/* 141 */           if (((ItemStack)stackMap.get(Integer.valueOf(i))).getItem() != Item.getItemById(sID)) {
/* 142 */             localStackMap.remove(Integer.valueOf(i));
/*     */           } }
/*     */ 
/*     */         
/* 146 */         for (iterator = localStackMap.keySet().iterator(); iterator.hasNext(); ) { int i = ((Integer)iterator.next()).intValue();
/* 147 */           arrayListMultimap.put(Integer.valueOf(((ItemStack)localStackMap.get(Integer.valueOf(i))).stackSize), Integer.valueOf(i)); }
/*     */ 
/*     */         
/* 150 */         while (localStackMap.size() > 1) {
/* 151 */           int smallestStackSize = ((Integer)Collections.<Integer>min(arrayListMultimap.keySet())).intValue();
/* 152 */           Collection<Integer> smallestStacks = arrayListMultimap.get(Integer.valueOf(smallestStackSize));
/* 153 */           int slotWithSmallestStack = ((Integer)Collections.<Integer>max(smallestStacks)).intValue();
/*     */           
/* 155 */           player.inventory.setInventorySlotContents(slotWithSmallestStack, null);
/* 156 */           arrayListMultimap.remove(Integer.valueOf(smallestStackSize), Integer.valueOf(slotWithSmallestStack));
/* 157 */           localStackMap.remove(Integer.valueOf(slotWithSmallestStack));
/*     */         } 
/* 159 */         cycleCounter++;
/*     */       }
/*     */     
/* 162 */     } else if (mode == 2 && 
/* 163 */       filledStacks >= player.inventory.mainInventory.length) {
/*     */       
/* 165 */       for (int sID : ID) {
/* 166 */         HashMap<Integer, ItemStack> localStackMap = new HashMap<>(stackMap);
/* 167 */         ArrayListMultimap arrayListMultimap = ArrayListMultimap.create();
/*     */         Iterator<Integer> iterator;
/* 169 */         for (iterator = stackMap.keySet().iterator(); iterator.hasNext(); ) { int i = ((Integer)iterator.next()).intValue();
/* 170 */           if (meta[cycleCounter] != -1) {
/* 171 */             if (((ItemStack)stackMap.get(Integer.valueOf(i))).getItem() != Item.getItemById(sID) || ((ItemStack)stackMap.get(Integer.valueOf(i))).getItemDamage() != meta[cycleCounter])
/* 172 */               localStackMap.remove(Integer.valueOf(i));  continue;
/*     */           } 
/* 174 */           if (((ItemStack)stackMap.get(Integer.valueOf(i))).getItem() != Item.getItemById(sID)) {
/* 175 */             localStackMap.remove(Integer.valueOf(i));
/*     */           } }
/*     */ 
/*     */         
/* 179 */         for (iterator = localStackMap.keySet().iterator(); iterator.hasNext(); ) { int i = ((Integer)iterator.next()).intValue();
/* 180 */           arrayListMultimap.put(Integer.valueOf(((ItemStack)localStackMap.get(Integer.valueOf(i))).stackSize), Integer.valueOf(i)); }
/*     */ 
/*     */         
/* 183 */         if (localStackMap.size() > 0) {
/* 184 */           int smallestStackSize = ((Integer)Collections.<Integer>min(arrayListMultimap.keySet())).intValue();
/* 185 */           Collection<Integer> smallestStacks = arrayListMultimap.get(Integer.valueOf(smallestStackSize));
/* 186 */           int slotWithSmallestStack = ((Integer)Collections.<Integer>max(smallestStacks)).intValue();
/*     */           
/* 188 */           player.inventory.setInventorySlotContents(slotWithSmallestStack, null);
/*     */           
/*     */           return;
/*     */         } 
/* 192 */         cycleCounter++;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void addInformation(ItemStack stack, EntityPlayer player, List<String> list, boolean par4) {
/* 204 */     if (GuiScreen.isShiftKeyDown()) {
/* 205 */       list.add(StatCollector.translateToLocal("item.OblivionStone1.lore"));
/* 206 */       list.add(StatCollector.translateToLocal("item.OblivionStone2.lore"));
/* 207 */       list.add(StatCollector.translateToLocal("item.OblivionStone2_more.lore"));
/* 208 */       list.add(StatCollector.translateToLocal("item.FREmpty.lore"));
/* 209 */       list.add(StatCollector.translateToLocal("item.OblivionStone3.lore"));
/* 210 */       list.add(StatCollector.translateToLocal("item.OblivionStone4.lore"));
/* 211 */       list.add(StatCollector.translateToLocal("item.OblivionStone5.lore"));
/* 212 */       list.add(StatCollector.translateToLocal("item.FREmpty.lore"));
/* 213 */       list.add(StatCollector.translateToLocal("item.OblivionStone6.lore"));
/* 214 */       list.add(StatCollector.translateToLocal("item.OblivionStone7.lore"));
/* 215 */       list.add(StatCollector.translateToLocal("item.OblivionStone8.lore"));
/* 216 */       list.add(StatCollector.translateToLocal("item.FREmpty.lore"));
/* 217 */       list.add(StatCollector.translateToLocal("item.OblivionStone9.lore"));
/* 218 */       list.add(StatCollector.translateToLocal("item.OblivionStone10.lore"));
/* 219 */       list.add(StatCollector.translateToLocal("item.OblivionStone11.lore"));
/* 220 */       list.add(StatCollector.translateToLocal("item.FREmpty.lore"));
/* 221 */       list.add(StatCollector.translateToLocal("item.OblivionStone12.lore"));
/* 222 */       list.add(StatCollector.translateToLocal("item.OblivionStone13.lore"));
/* 223 */       list.add(StatCollector.translateToLocal("item.OblivionStone14.lore"));
/* 224 */       list.add(StatCollector.translateToLocal("item.OblivionStone15.lore"));
/* 225 */     } else if (GuiScreen.isCtrlKeyDown()) {
/* 226 */       list.add(StatCollector.translateToLocal("item.OblivionStoneCtrlList.lore"));
/* 227 */       if (stack.hasTagCompound()) {
/* 228 */         NBTTagCompound nbt = stack.getTagCompound();
/* 229 */         int[] arr = nbt.getIntArray("SupersolidID");
/* 230 */         int[] meta = nbt.getIntArray("SupersolidMetaID");
/* 231 */         int counter = 0;
/*     */         
/* 233 */         if (arr.length <= RelicsConfigHandler.oblivionStoneSoftCap) {
/* 234 */           for (int s : arr) {
/* 235 */             Item something = Item.getItemById(s);
/* 236 */             if (something != null) {
/*     */               ItemStack displayStack;
/* 238 */               if (meta[counter] != -1) {
/* 239 */                 displayStack = new ItemStack(something, 1, meta[counter]);
/*     */               } else {
/* 241 */                 displayStack = new ItemStack(something, 1, 0);
/*     */               } 
/* 243 */               list.add(EnumChatFormatting.GOLD + " - " + displayStack
/* 244 */                   .getDisplayName());
/*     */             } 
/* 246 */             counter++;
/*     */           } 
/*     */         } else {
/* 249 */           for (int s = 0; s < RelicsConfigHandler.oblivionStoneSoftCap; s++) {
/* 250 */             int randomID = (int)(Math.random() * 30.0D);
/* 251 */             Item something = Item.getItemById(arr[randomID]);
/*     */             
/* 253 */             if (something != null) {
/*     */               ItemStack displayStack;
/* 255 */               if (meta[randomID] != -1) {
/* 256 */                 displayStack = new ItemStack(something, 1, meta[randomID]);
/*     */               } else {
/* 258 */                 displayStack = new ItemStack(something, 1, 0);
/*     */               } 
/* 260 */               list.add(EnumChatFormatting.GOLD + " - " + displayStack
/* 261 */                   .getDisplayName());
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } else {
/* 267 */       list.add(StatCollector.translateToLocal("item.FRShiftTooltip.lore"));
/* 268 */       list.add(StatCollector.translateToLocal("item.OblivionStoneCtrlTooltip.lore"));
/* 269 */       list.add(StatCollector.translateToLocal("item.FREmpty.lore"));
/*     */       
/* 271 */       int mode = stack.getItemDamage();
/* 272 */       if (mode < 100) {
/* 273 */         list.add(StatCollector.translateToLocal("item.OblivionStoneMode.lore") + " " + 
/* 274 */             StatCollector.translateToLocal("item.OblivionMode" + mode + ".lore"));
/*     */       } else {
/* 276 */         list.add(StatCollector.translateToLocal("item.OblivionStoneMode.lore") + " " + 
/* 277 */             StatCollector.translateToLocal("item.OblivionStoneDeactivated.lore"));
/*     */       } 
/*     */     } 
/*     */     
/* 281 */     list.add(StatCollector.translateToLocal("item.FREmpty.lore"));
/*     */   }
/*     */ 
/*     */   
/*     */   public int getWarp(ItemStack arg0, EntityPlayer arg1) {
/* 286 */     return 2;
/*     */   }
/*     */ }


/* Location:              C:\Users\antro\Documents\forgottenRelicsDeobfuscated.jar!\com\integral\forgottenrelics\items\ItemOblivionStone.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */