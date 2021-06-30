/*     */ package com.integral.forgottenrelics.minetweaker;
/*     */ 
/*     */ import com.integral.forgottenrelics.Main;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.List;
/*     */ import minetweaker.IUndoableAction;
/*     */ import minetweaker.MineTweakerAPI;
/*     */ import minetweaker.api.item.IItemStack;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import stanhebben.zenscript.annotations.ZenClass;
/*     */ import stanhebben.zenscript.annotations.ZenMethod;
/*     */ import thaumcraft.api.research.ResearchCategories;
/*     */ 
/*     */ 
/*     */ @ZenClass("mods.forgottenrelics.JusticeHandler")
/*     */ public class JusticeHandlerInteraction
/*     */ {
/*     */   @ZenMethod
/*     */   public static void addTrigger(String researchKey, IItemStack stack) {
/*  21 */     MineTweakerAPI.apply(new Executor(researchKey, stack));
/*     */   }
/*     */   
/*     */   @ZenMethod
/*     */   public static void obliterateJusticeTriggers(String researchKey) {
/*  26 */     MineTweakerAPI.apply(new Executor(researchKey));
/*     */   }
/*     */   
/*     */   public static ItemStack toStack(IItemStack iStack) {
/*  30 */     if (iStack == null) {
/*  31 */       return null;
/*     */     }
/*     */     
/*  34 */     Object internal = iStack.getInternal();
/*  35 */     if (!(internal instanceof ItemStack)) {
/*  36 */       MineTweakerAPI.logError("Invalid ItemStack: " + iStack);
/*  37 */       return null;
/*     */     } 
/*     */     
/*  40 */     ItemStack theStack = (ItemStack)internal;
/*     */ 
/*     */ 
/*     */     
/*  44 */     theStack.stackSize = 1;
/*     */     
/*  46 */     return theStack;
/*     */   }
/*     */   
/*     */   private static class Executor
/*     */     implements IUndoableAction {
/*     */     String researchKey;
/*     */     IItemStack iStack;
/*     */     boolean obliteration;
/*     */     List<ItemStack> obliteratedStacks;
/*     */     ItemStack operableStack;
/*     */     
/*     */     public Executor(String researchKey, IItemStack stack) {
/*  58 */       this.researchKey = researchKey;
/*  59 */       this.iStack = stack;
/*  60 */       this.obliteration = false;
/*     */     }
/*     */     
/*     */     public Executor(String researchKey) {
/*  64 */       this.researchKey = researchKey;
/*  65 */       this.iStack = null;
/*  66 */       this.obliteration = true;
/*     */     }
/*     */ 
/*     */     
/*     */     public void apply() {
/*  71 */       if (this.researchKey == "Apotheosis") {
/*  72 */         MineTweakerAPI.logError("You can't redefine triggers for Apotheosis research. Don't even try.");
/*     */         
/*     */         return;
/*     */       } 
/*  76 */       if (ResearchCategories.getResearch(this.researchKey) == null) {
/*  77 */         MineTweakerAPI.logError("The research " + this.researchKey + " is unlikely to exist.");
/*     */         
/*     */         return;
/*     */       } 
/*  81 */       if (this.obliteration) {
/*  82 */         if (Main.forgottenKnowledge.containsKey(this.researchKey))
/*  83 */           this.obliteratedStacks = (List<ItemStack>)Main.forgottenKnowledge.get(this.researchKey); 
/*  84 */         Main.forgottenKnowledge.remove(this.researchKey);
/*     */       } else {
/*  86 */         ItemStack stack = JusticeHandlerInteraction.toStack(this.iStack);
/*  87 */         List<ItemStack> triggerList = new ArrayList<>();
/*  88 */         if (Main.forgottenKnowledge.get(this.researchKey) != null) {
/*  89 */           triggerList.addAll((Collection<? extends ItemStack>)Main.forgottenKnowledge.get(this.researchKey));
/*     */         }
/*  91 */         if (stack != null) {
/*  92 */           triggerList.add(stack);
/*  93 */           this.operableStack = stack;
/*  94 */           Main.forgottenKnowledge.put(this.researchKey, triggerList);
/*     */         } 
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean canUndo() {
/* 101 */       return true;
/*     */     }
/*     */     
/*     */     public String describe() {
/* 105 */       if (this.obliteration) {
/* 106 */         return "Obliterating Justice triggers of " + this.researchKey + " research";
/*     */       }
/* 108 */       return "Adding " + this.iStack + " to Justice triggers of " + this.researchKey;
/*     */     }
/*     */ 
/*     */     
/*     */     public void undo() {
/* 113 */       if (this.researchKey == "Apotheosis") {
/*     */         return;
/*     */       }
/*     */       
/* 117 */       if (ResearchCategories.getResearch(this.researchKey) == null) {
/*     */         return;
/*     */       }
/* 120 */       if (this.obliteration) {
/* 121 */         if (this.obliteratedStacks != null) {
/* 122 */           Main.forgottenKnowledge.put(this.researchKey, this.obliteratedStacks);
/*     */         }
/*     */       } else {
/*     */         
/* 126 */         List<ItemStack> triggerList = new ArrayList<>();
/*     */         
/* 128 */         if (Main.forgottenKnowledge.get(this.researchKey) != null) {
/* 129 */           triggerList.addAll((Collection<? extends ItemStack>)Main.forgottenKnowledge.get(this.researchKey));
/*     */         }
/* 131 */         if (triggerList != null) {
/* 132 */           triggerList.remove(this.operableStack);
/* 133 */           if (triggerList.size() > 0) {
/* 134 */             Main.forgottenKnowledge.put(this.researchKey, triggerList);
/*     */           } else {
/* 136 */             Main.forgottenKnowledge.remove(this.researchKey);
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     }
/*     */     
/*     */     public String describeUndo() {
/* 143 */       if (this.obliteration) {
/* 144 */         return "Reversing obliteration of " + this.researchKey + " research";
/*     */       }
/* 146 */       return "Removing " + this.iStack + " from Justice triggers of " + this.researchKey;
/*     */     }
/*     */     
/*     */     public Object getOverrideKey() {
/* 150 */       return null;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\antro\Documents\forgottenRelicsDeobfuscated.jar!\com\integral\forgottenrelics\minetweaker\JusticeHandlerInteraction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */