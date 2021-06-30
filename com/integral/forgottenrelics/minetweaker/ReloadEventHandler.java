/*    */ package com.integral.forgottenrelics.minetweaker;
/*    */ 
/*    */ import minetweaker.MineTweakerImplementationAPI;
/*    */ import minetweaker.util.IEventHandler;
/*    */ 
/*    */ 
/*    */ public class ReloadEventHandler
/*    */   implements IEventHandler<MineTweakerImplementationAPI.ReloadEvent>
/*    */ {
/*    */   public void handle(MineTweakerImplementationAPI.ReloadEvent event) {
/* 11 */     MineTweakerIntegrator.registerCommands();
/*    */   }
/*    */ }


/* Location:              C:\Users\antro\Documents\forgottenRelicsDeobfuscated.jar!\com\integral\forgottenrelics\minetweaker\ReloadEventHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */