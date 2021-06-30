/*    */ package com.integral.forgottenrelics.packets;
/*    */ 
/*    */ import com.integral.forgottenrelics.Main;
/*    */ import cpw.mods.fml.common.network.simpleimpl.IMessage;
/*    */ import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
/*    */ import cpw.mods.fml.common.network.simpleimpl.MessageContext;
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.entity.player.EntityPlayerMP;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.ItemStack;
/*    */ 
/*    */ public class TelekinesisAttackMessage
/*    */   implements IMessage {
/*    */   private boolean doIt;
/*    */   
/*    */   public TelekinesisAttackMessage() {}
/*    */   
/*    */   public TelekinesisAttackMessage(boolean doIt) {
/* 20 */     this.doIt = doIt;
/*    */   }
/*    */ 
/*    */   
/*    */   public void fromBytes(ByteBuf buf) {
/* 25 */     this.doIt = buf.readBoolean();
/*    */   }
/*    */ 
/*    */   
/*    */   public void toBytes(ByteBuf buf) {
/* 30 */     buf.writeBoolean(this.doIt);
/*    */   }
/*    */   
/*    */   public static class Handler
/*    */     implements IMessageHandler<TelekinesisAttackMessage, IMessage>
/*    */   {
/*    */     public IMessage onMessage(TelekinesisAttackMessage message, MessageContext ctx) {
/* 37 */       EntityPlayerMP entityPlayerMP = (ctx.getServerHandler()).playerEntity;
/* 38 */       ItemStack stack = entityPlayerMP.getCurrentEquippedItem();
/* 39 */       if (stack == null) {
/* 40 */         return null;
/*    */       }
/* 42 */       Item item = stack.getItem();
/*    */       
/* 44 */       if ((message.doIt & ((item == Main.itemTelekinesisTome) ? 1 : 0)) != 0) {
/* 45 */         Main.itemTelekinesisTome.leftClick((EntityPlayer)entityPlayerMP);
/*    */       }
/*    */       
/* 48 */       return null;
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\antro\Documents\forgottenRelicsDeobfuscated.jar!\com\integral\forgottenrelics\packets\TelekinesisAttackMessage.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */