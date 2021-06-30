/*    */ package com.integral.forgottenrelics.handlers;
/*    */ 
/*    */ import com.google.common.collect.Lists;
/*    */ import java.util.List;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraftforge.common.ForgeChunkManager;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RelicsChunkLoadCallback
/*    */   implements ForgeChunkManager.OrderedLoadingCallback
/*    */ {
/*    */   public void ticketsLoaded(List<ForgeChunkManager.Ticket> tickets, World world) {
/* 17 */     for (ForgeChunkManager.Ticket ticket : tickets);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public List<ForgeChunkManager.Ticket> ticketsLoaded(List<ForgeChunkManager.Ticket> tickets, World world, int maxTicketCount) {
/* 25 */     List<ForgeChunkManager.Ticket> validTickets = Lists.newArrayList();
/* 26 */     for (ForgeChunkManager.Ticket ticket : tickets) {
/* 27 */       validTickets.add(ticket);
/*    */     }
/* 29 */     return validTickets;
/*    */   }
/*    */ }


/* Location:              C:\Users\antro\Documents\forgottenRelicsDeobfuscated.jar!\com\integral\forgottenrelics\handlers\RelicsChunkLoadCallback.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */