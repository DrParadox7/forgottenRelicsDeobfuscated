/*    */ package com.integral.forgottenrelics.packets;
/*    */ 
/*    */ import cpw.mods.fml.client.FMLClientHandler;
/*    */ import cpw.mods.fml.common.network.simpleimpl.IMessage;
/*    */ import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
/*    */ import cpw.mods.fml.common.network.simpleimpl.MessageContext;
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import net.minecraft.client.entity.EntityClientPlayerMP;
/*    */ 
/*    */ public class ICanSwingMySwordMessage
/*    */   implements IMessage {
/*    */   private boolean swingTheSword;
/*    */   
/*    */   public ICanSwingMySwordMessage() {}
/*    */   
/*    */   public ICanSwingMySwordMessage(boolean swingTheSword) {
/* 19 */     this.swingTheSword = swingTheSword;
/*    */   }
/*    */ 
/*    */   
/*    */   public void fromBytes(ByteBuf buf) {
/* 24 */     this.swingTheSword = buf.readBoolean();
/*    */   }
/*    */ 
/*    */   
/*    */   public void toBytes(ByteBuf buf) {
/* 29 */     buf.writeBoolean(this.swingTheSword);
/*    */   }
/*    */   
/*    */   public static class Handler
/*    */     implements IMessageHandler<ICanSwingMySwordMessage, IMessage>
/*    */   {
/*    */     @SideOnly(Side.CLIENT)
/*    */     public IMessage onMessage(ICanSwingMySwordMessage message, MessageContext ctx) {
/* 37 */       EntityClientPlayerMP entityClientPlayerMP = FMLClientHandler.instance().getClientPlayerEntity();
/*    */       
/* 39 */       if (message.swingTheSword) {
/* 40 */         entityClientPlayerMP.swingItem();
/*    */       }
/* 42 */       return null;
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\antro\Documents\forgottenRelicsDeobfuscated.jar!\com\integral\forgottenrelics\packets\ICanSwingMySwordMessage.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */