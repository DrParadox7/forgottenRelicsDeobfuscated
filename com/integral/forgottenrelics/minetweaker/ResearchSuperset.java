/*     */ package com.integral.forgottenrelics.minetweaker;
/*     */ 
/*     */ import com.integral.forgottenrelics.handlers.SuperpositionHandler;
/*     */ import minetweaker.IUndoableAction;
/*     */ import minetweaker.MineTweakerAPI;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import stanhebben.zenscript.annotations.ZenClass;
/*     */ import stanhebben.zenscript.annotations.ZenMethod;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.api.research.ResearchCategories;
/*     */ import thaumcraft.api.research.ResearchItem;
/*     */ 
/*     */ 
/*     */ @ZenClass("mods.forgottenrelics.Research")
/*     */ public class ResearchSuperset
/*     */ {
/*     */   @ZenMethod
/*     */   public static void setHidden(String researchKey, boolean def) {
/*  19 */     MineTweakerAPI.apply(new Executor(researchKey, def, 0));
/*     */   }
/*     */   
/*     */   @ZenMethod
/*     */   public static void setLost(String researchKey, boolean def) {
/*  24 */     MineTweakerAPI.apply(new Executor(researchKey, def, 1));
/*     */   }
/*     */   
/*     */   @ZenMethod
/*     */   public static void obliterateDefaultTriggers(String researchKey) {
/*  29 */     MineTweakerAPI.apply(new Executor(researchKey, false, 2));
/*     */   }
/*     */   
/*     */   private static class Executor
/*     */     implements IUndoableAction {
/*     */     String researchKey;
/*     */     boolean def;
/*     */     int commandIndex;
/*     */     boolean wasHidden = false;
/*     */     boolean wasLost = false;
/*     */     
/*     */     public Executor(String researchKey, boolean def, int index) {
/*  41 */       this.researchKey = researchKey;
/*  42 */       this.def = def;
/*  43 */       this.commandIndex = index;
/*     */     }
/*     */     
/*     */     public void apply() {
/*  47 */       ResearchItem research = ResearchCategories.getResearch(this.researchKey);
/*     */       
/*  49 */       if (research == null) {
/*  50 */         MineTweakerAPI.logError("The research " + this.researchKey + " is unlikely to exist.");
/*     */         
/*     */         return;
/*     */       } 
/*     */       
/*  55 */       if (this.commandIndex == 0) {
/*     */         
/*  57 */         if (this.def) {
/*  58 */           research.setHidden();
/*     */         } else {
/*  60 */           if (research.isHidden()) {
/*  61 */             this.wasHidden = true;
/*     */           }
/*  63 */           SuperpositionHandler.setResearchUnhidden(research);
/*     */         } 
/*  65 */       } else if (this.commandIndex == 1) {
/*     */         
/*  67 */         if (this.def) {
/*  68 */           research.setLost();
/*     */         } else {
/*  70 */           if (research.isLost()) {
/*  71 */             this.wasLost = true;
/*     */           }
/*  73 */           SuperpositionHandler.setResearchUnlost(research);
/*     */         } 
/*  75 */       } else if (this.commandIndex == 2) {
/*  76 */         research.setItemTriggers((ItemStack[])null);
/*  77 */         research.setAspectTriggers((Aspect[])null);
/*  78 */         research.setEntityTriggers((String[])null);
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean canUndo() {
/*  84 */       ResearchItem research = ResearchCategories.getResearch(this.researchKey);
/*     */       
/*  86 */       if (research == null) {
/*  87 */         return true;
/*     */       }
/*  89 */       if (this.commandIndex == 0) {
/*  90 */         if (this.def) {
/*  91 */           return research.isHidden();
/*     */         }
/*  93 */         return true;
/*  94 */       }  if (this.commandIndex == 1) {
/*     */         
/*  96 */         if (this.def) {
/*  97 */           return research.isLost();
/*     */         }
/*  99 */         return true;
/*     */       } 
/* 101 */       if (this.commandIndex == 2) {
/* 102 */         return false;
/*     */       }
/*     */       
/* 105 */       return false;
/*     */     }
/*     */     
/*     */     public String describe() {
/* 109 */       ResearchItem research = ResearchCategories.getResearch(this.researchKey);
/* 110 */       String desc = null;
/*     */       
/* 112 */       if (research == null) {
/* 113 */         return desc;
/*     */       }
/* 115 */       if (this.commandIndex == 0) {
/* 116 */         if (this.def)
/* 117 */         { desc = "Research " + research.key + " is set as Hidden."; }
/*     */         else
/* 119 */         { desc = "Research " + research.key + " is set as not Hidden."; } 
/* 120 */       } else if (this.commandIndex == 1) {
/* 121 */         if (this.def)
/* 122 */         { desc = "Research " + research.key + " is set as Lost."; }
/*     */         else
/* 124 */         { desc = "Research " + research.key + " is set as not Lost."; } 
/* 125 */       } else if (this.commandIndex == 2) {
/* 126 */         desc = "Research " + research.key + " had it's default triggers obliterated.";
/*     */       } 
/*     */       
/* 129 */       return desc;
/*     */     }
/*     */     
/*     */     public void undo() {
/* 133 */       ResearchItem research = ResearchCategories.getResearch(this.researchKey);
/*     */       
/* 135 */       if (research == null) {
/*     */         return;
/*     */       }
/* 138 */       if (this.commandIndex == 0) {
/* 139 */         if (this.def) {
/* 140 */           SuperpositionHandler.setResearchUnhidden(research);
/*     */         }
/* 142 */         else if (this.wasHidden) {
/* 143 */           research.setHidden();
/*     */         } 
/* 145 */       } else if (this.commandIndex == 1) {
/* 146 */         if (this.def) {
/* 147 */           SuperpositionHandler.setResearchUnlost(research);
/*     */         }
/* 149 */         else if (this.wasLost) {
/* 150 */           research.setLost();
/*     */         } 
/*     */       } 
/*     */     }
/*     */     
/*     */     public String describeUndo() {
/* 156 */       ResearchItem research = ResearchCategories.getResearch(this.researchKey);
/* 157 */       String desc = null;
/*     */       
/* 159 */       if (research == null) {
/* 160 */         return desc;
/*     */       }
/* 162 */       if (this.commandIndex == 0) {
/* 163 */         if (this.def)
/* 164 */         { desc = "Research " + research.key + " is no longer Hidden."; }
/*     */         
/* 166 */         else if (this.wasHidden)
/* 167 */         { desc = "Research " + research.key + " is Hidden once more."; } 
/* 168 */       } else if (this.commandIndex == 1) {
/* 169 */         if (this.def) {
/* 170 */           desc = "Research " + research.key + " is no longer Lost.";
/*     */         }
/* 172 */         else if (this.wasLost) {
/* 173 */           desc = "Research " + research.key + " is Lost once more.";
/*     */         } 
/*     */       } 
/* 176 */       return desc;
/*     */     }
/*     */     
/*     */     public Object getOverrideKey() {
/* 180 */       return null;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\antro\Documents\forgottenRelicsDeobfuscated.jar!\com\integral\forgottenrelics\minetweaker\ResearchSuperset.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */