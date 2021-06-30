/*    */ package com.integral.forgottenrelics.handlers;
/*    */ 
/*    */ import com.integral.forgottenrelics.Main;
/*    */ import com.integral.forgottenrelics.packets.ForgottenResearchMessage;
/*    */ import cpw.mods.fml.common.network.simpleimpl.IMessage;
/*    */ import java.util.List;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.entity.player.EntityPlayerMP;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import thaumcraft.api.research.ResearchCategories;
/*    */ import thaumcraft.api.research.ResearchItem;
/*    */ import thaumcraft.api.research.ScanResult;
/*    */ import thaumcraft.common.Thaumcraft;
/*    */ import thaumcraft.common.lib.research.ResearchManager;
/*    */ import thaumcraft.common.lib.research.ScanManager;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class JusticeHandler
/*    */   extends Thread
/*    */ {
/*    */   EntityPlayer player;
/*    */   
/*    */   public JusticeHandler(EntityPlayer player) {
/* 36 */     this.player = player;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void run() {
/*    */     try {
/* 46 */       if (this.player.worldObj.isRemote) {
/*    */         return;
/*    */       }
/* 49 */       for (String research : Main.forgottenKnowledge.keySet()) {
/* 50 */         ResearchItem researchItem = ResearchCategories.getResearch(research);
/*    */         
/* 52 */         if (researchItem == null)
/*    */           continue; 
/* 54 */         if (((!researchItem.isHidden() ? 1 : 0) & (!researchItem.isLost() ? 1 : 0)) != 0) {
/*    */           continue;
/*    */         }
/* 57 */         boolean clueUnlocked = ResearchManager.isResearchComplete(this.player.getDisplayName(), "@" + research);
/* 58 */         boolean researchCompleted = ResearchManager.isResearchComplete(this.player.getDisplayName(), research);
/*    */         
/* 60 */         if (((!clueUnlocked ? 1 : 0) & (!researchCompleted ? 1 : 0)) != 0) {
/* 61 */           List<ItemStack> triggerList = (List<ItemStack>)Main.forgottenKnowledge.get(research);
/* 62 */           boolean shouldBeUnlocked = true;
/*    */           
/* 64 */           for (ItemStack stack : triggerList) {
/* 65 */             stack.getItem(); ScanResult testItem = new ScanResult((byte)1, Item.getIdFromItem(stack.getItem()), stack.getItemDamage(), (Entity)this.player, "UndefinedPhenomena");
/* 66 */             if (ScanManager.hasBeenScanned(this.player, testItem)) {
/*    */               continue;
/*    */             }
/* 69 */             shouldBeUnlocked = false;
/*    */           } 
/*    */ 
/*    */ 
/*    */ 
/*    */           
/* 75 */           if (shouldBeUnlocked) {
/*    */             
/* 77 */             Main.packetInstance.sendTo((IMessage)new ForgottenResearchMessage("@" + research), (EntityPlayerMP)this.player);
/* 78 */             Thaumcraft.proxy.getResearchManager().completeResearch(this.player, "@" + research);
/*    */ 
/*    */ 
/*    */             
/*    */             return;
/*    */           } 
/*    */         } 
/*    */       } 
/* 86 */     } catch (Exception ex) {
/* 87 */       Main.log.error("Something has gone wrong while executing Justice Handler!");
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\antro\Documents\forgottenRelicsDeobfuscated.jar!\com\integral\forgottenrelics\handlers\JusticeHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */