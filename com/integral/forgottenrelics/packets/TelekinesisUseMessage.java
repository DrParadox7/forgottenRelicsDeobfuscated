/*    */ package com.integral.forgottenrelics.packets;
/*    */ 
/*    */ import com.integral.forgottenrelics.Main;
/*    */ import cpw.mods.fml.common.network.simpleimpl.IMessage;
/*    */ import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
/*    */ import cpw.mods.fml.common.network.simpleimpl.MessageContext;
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.entity.player.EntityPlayerMP;
/*    */ import net.minecraft.item.ItemStack;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TelekinesisUseMessage
/*    */   implements IMessage
/*    */ {
/*    */   public void fromBytes(ByteBuf buf) {}
/*    */   
/*    */   public void toBytes(ByteBuf buf) {}
/*    */   
/*    */   public static class Handler
/*    */     implements IMessageHandler<TelekinesisUseMessage, IMessage>
/*    */   {
/*    */     public IMessage onMessage(TelekinesisUseMessage message, MessageContext ctx) {
/* 28 */       EntityPlayerMP entityPlayerMP = (ctx.getServerHandler()).playerEntity;
/* 29 */       ItemStack stack = entityPlayerMP.getCurrentEquippedItem();
/*    */       
/* 31 */       if (stack != null && 
/* 32 */         stack.getItem() == Main.itemTelekinesisTome) {
/* 33 */         Main.itemTelekinesisTome.onUsingTickAlt(stack, (EntityPlayer)entityPlayerMP, 0);
/*    */       }
/*    */ 
/*    */       
/* 37 */       return null;
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\antro\Documents\forgottenRelicsDeobfuscated.jar!\com\integral\forgottenrelics\packets\TelekinesisUseMessage.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */