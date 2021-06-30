/*     */ package com.integral.forgottenrelics.handlers;
/*     */ 
/*     */ import com.integral.forgottenrelics.Main;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import net.minecraft.inventory.InventoryCrafting;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.item.crafting.IRecipe;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class RecipeOblivionStone
/*     */   implements IRecipe
/*     */ {
/*     */   public ItemStack getCraftingResult(InventoryCrafting par1InventoryCrafting) {
/*  17 */     ItemStack repairedStack = null;
/*  18 */     List<ItemStack> stackList = new ArrayList<>();
/*  19 */     ItemStack voidStone = null;
/*     */     
/*  21 */     for (int i = 0; i < par1InventoryCrafting.getSizeInventory(); i++) {
/*  22 */       ItemStack checkedItemStack = par1InventoryCrafting.getStackInSlot(i);
/*     */       
/*  24 */       if (checkedItemStack != null) {
/*  25 */         if (checkedItemStack.getItem() == Main.itemOblivionStone)
/*  26 */         { if (voidStone == null) {
/*  27 */             voidStone = checkedItemStack;
/*     */           } else {
/*  29 */             return null;
/*     */           }  }
/*  31 */         else { stackList.add(checkedItemStack); }
/*     */       
/*     */       }
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/*  38 */     if ((((voidStone != null) ? 1 : 0) & ((stackList.size() == 1) ? 1 : 0)) != 0) {
/*  39 */       NBTTagCompound nbt; ItemStack savedStack = ((ItemStack)stackList.get(0)).copy();
/*     */ 
/*     */ 
/*     */       
/*  43 */       if (voidStone.hasTagCompound()) {
/*  44 */         nbt = (NBTTagCompound)voidStone.getTagCompound().copy();
/*     */       } else {
/*  46 */         nbt = new NBTTagCompound();
/*     */       } 
/*  48 */       int[] arr = nbt.getIntArray("SupersolidID");
/*  49 */       int[] meta = nbt.getIntArray("SupersolidMetaID");
/*  50 */       int counter = 0;
/*     */       
/*  52 */       if (arr.length >= RelicsConfigHandler.oblivionStoneHardCap) {
/*  53 */         return null;
/*     */       }
/*  55 */       for (int s : arr) {
/*  56 */         int metaD = meta[counter];
/*  57 */         counter++;
/*  58 */         if ((((s == Item.itemRegistry.getIDForObject(savedStack.getItem())) ? 1 : 0) & ((metaD != -1) ? 1 : 0) & (
/*  59 */           (metaD == savedStack.getItemDamage()) ? 1 : 0)) != 0)
/*  60 */           return null; 
/*  61 */         if ((((s == Item.itemRegistry.getIDForObject(savedStack.getItem())) ? 1 : 0) & ((metaD == -1) ? 1 : 0)) != 0) {
/*  62 */           return null;
/*     */         }
/*     */       } 
/*  65 */       arr = SuperpositionHandler.addInt(arr, Item.itemRegistry.getIDForObject(savedStack.getItem()));
/*     */       
/*  67 */       if (!savedStack.isItemStackDamageable()) {
/*  68 */         meta = SuperpositionHandler.addInt(meta, savedStack.getItemDamage());
/*     */       } else {
/*  70 */         meta = SuperpositionHandler.addInt(meta, -1);
/*     */       } 
/*  72 */       nbt.setIntArray("SupersolidID", arr);
/*  73 */       nbt.setIntArray("SupersolidMetaID", meta);
/*     */       
/*  75 */       ItemStack returnedStack = voidStone.copy();
/*  76 */       returnedStack.setTagCompound(nbt);
/*     */       
/*  78 */       return returnedStack;
/*  79 */     }  if ((((voidStone != null) ? 1 : 0) & ((stackList.size() == 0) ? 1 : 0)) != 0) {
/*  80 */       ItemStack returnedStack = new ItemStack(Main.itemOblivionStone, 1, voidStone.getItemDamage());
/*  81 */       return returnedStack;
/*     */     } 
/*  83 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack getRecipeOutput() {
/*  88 */     return null;
/*     */   }
/*     */   
/*     */   public int getRecipeSize() {
/*  92 */     return 10;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean matches(InventoryCrafting par1InventoryCrafting, World arg1) {
/*  97 */     ItemStack repairedStack = null;
/*  98 */     List<ItemStack> stackList = new ArrayList<>();
/*  99 */     ItemStack voidStone = null;
/*     */     
/* 101 */     for (int i = 0; i < par1InventoryCrafting.getSizeInventory(); i++) {
/* 102 */       ItemStack checkedItemStack = par1InventoryCrafting.getStackInSlot(i);
/*     */       
/* 104 */       if (checkedItemStack != null) {
/* 105 */         if (checkedItemStack.getItem() == Main.itemOblivionStone)
/* 106 */         { if (voidStone == null) {
/* 107 */             voidStone = checkedItemStack;
/*     */           } else {
/* 109 */             return false;
/*     */           }  }
/* 111 */         else { stackList.add(checkedItemStack); }
/*     */       
/*     */       }
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 118 */     if ((((voidStone != null) ? 1 : 0) & ((stackList.size() == 1) ? 1 : 0)) != 0) {
/* 119 */       NBTTagCompound nbt; ItemStack savedStack = ((ItemStack)stackList.get(0)).copy();
/*     */ 
/*     */ 
/*     */       
/* 123 */       if (voidStone.hasTagCompound()) {
/* 124 */         nbt = (NBTTagCompound)voidStone.getTagCompound().copy();
/*     */       } else {
/* 126 */         nbt = new NBTTagCompound();
/*     */       } 
/* 128 */       int[] arr = nbt.getIntArray("SupersolidID");
/* 129 */       int[] meta = nbt.getIntArray("SupersolidMetaID");
/* 130 */       int counter = 0;
/*     */       
/* 132 */       if (arr.length >= RelicsConfigHandler.oblivionStoneHardCap) {
/* 133 */         return false;
/*     */       }
/* 135 */       for (int s : arr) {
/* 136 */         int metaD = meta[counter];
/* 137 */         counter++;
/* 138 */         if ((((s == Item.itemRegistry.getIDForObject(savedStack.getItem())) ? 1 : 0) & ((metaD != -1) ? 1 : 0) & (
/* 139 */           (metaD == savedStack.getItemDamage()) ? 1 : 0)) != 0)
/* 140 */           return false; 
/* 141 */         if ((((s == Item.itemRegistry.getIDForObject(savedStack.getItem())) ? 1 : 0) & ((metaD == -1) ? 1 : 0)) != 0) {
/* 142 */           return false;
/*     */         }
/*     */       } 
/* 145 */       return true;
/* 146 */     }  if ((((voidStone != null) ? 1 : 0) & ((stackList.size() == 0) ? 1 : 0)) != 0) {
/* 147 */       return true;
/*     */     }
/* 149 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\antro\Documents\forgottenRelicsDeobfuscated.jar!\com\integral\forgottenrelics\handlers\RecipeOblivionStone.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */