/*    */ package com.integral.forgottenrelics.minetweaker;
/*    */ 
/*    */ import com.integral.forgottenrelics.Main;
/*    */ import minetweaker.MineTweakerAPI;
/*    */ import minetweaker.MineTweakerImplementationAPI;
/*    */ import minetweaker.api.player.IPlayer;
/*    */ import minetweaker.api.server.ICommandFunction;
/*    */ 
/*    */ public class ForgottenKnowledgeCommand
/*    */   implements ICommandFunction
/*    */ {
/*    */   public void execute(String[] arguments, IPlayer player) {
/* 13 */     for (String key : Main.forgottenKnowledge.keySet()) {
/* 14 */       MineTweakerAPI.logCommand(key + ": " + Main.forgottenKnowledge.get(key));
/*    */     }
/*    */     
/* 17 */     if (player != null)
/* 18 */       player.sendChat(MineTweakerImplementationAPI.platform.getMessage("List generated; see minetweaker.log in your minecraft dir")); 
/*    */   }
/*    */ }


/* Location:              C:\Users\antro\Documents\forgottenRelicsDeobfuscated.jar!\com\integral\forgottenrelics\minetweaker\ForgottenKnowledgeCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */