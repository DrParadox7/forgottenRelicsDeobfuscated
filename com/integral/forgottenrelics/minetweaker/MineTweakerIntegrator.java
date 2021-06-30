/*    */ package com.integral.forgottenrelics.minetweaker;
/*    */ 
/*    */ import cpw.mods.fml.common.Optional.Method;
/*    */ import minetweaker.MineTweakerAPI;
/*    */ import minetweaker.MineTweakerImplementationAPI;
/*    */ 
/*    */ 
/*    */ public class MineTweakerIntegrator
/*    */ {
/*    */   @Method(modid = "MineTweaker3")
/*    */   public static void init() {
/* 12 */     MineTweakerImplementationAPI.onReloadEvent(new ReloadEventHandler());
/* 13 */     MineTweakerAPI.registerClass(ResearchSuperset.class);
/* 14 */     MineTweakerAPI.registerClass(JusticeHandlerInteraction.class);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   @Method(modid = "MineTweaker3")
/*    */   public static void registerCommands() {
/* 21 */     if (MineTweakerAPI.server != null)
/* 22 */       MineTweakerAPI.server.addMineTweakerCommand("forgottenKnowledge", new String[] { "/minetweaker forgottenKnowledge", "    Outputs a list of all forgotten knowledge reasearches, alongside with arrays of ItemStacks bound to them in string representation" }, new ForgottenKnowledgeCommand()); 
/*    */   }
/*    */ }


/* Location:              C:\Users\antro\Documents\forgottenRelicsDeobfuscated.jar!\com\integral\forgottenrelics\minetweaker\MineTweakerIntegrator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */