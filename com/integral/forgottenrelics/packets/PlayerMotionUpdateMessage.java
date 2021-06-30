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
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ 
/*    */ public class PlayerMotionUpdateMessage implements IMessage {
/*    */   private double x;
/*    */   private double y;
/*    */   private double z;
/*    */   
/*    */   public PlayerMotionUpdateMessage() {}
/*    */   
/*    */   public PlayerMotionUpdateMessage(double x, double y, double z) {
/* 21 */     this.x = x;
/* 22 */     this.y = y;
/* 23 */     this.z = z;
/*    */   }
/*    */ 
/*    */   
/*    */   public void fromBytes(ByteBuf buf) {
/* 28 */     this.x = buf.readDouble();
/* 29 */     this.y = buf.readDouble();
/* 30 */     this.z = buf.readDouble();
/*    */   }
/*    */ 
/*    */   
/*    */   public void toBytes(ByteBuf buf) {
/* 35 */     buf.writeDouble(this.x);
/* 36 */     buf.writeDouble(this.y);
/* 37 */     buf.writeDouble(this.z);
/*    */   }
/*    */   
/*    */   public static class Handler
/*    */     implements IMessageHandler<PlayerMotionUpdateMessage, IMessage>
/*    */   {
/*    */     @SideOnly(Side.CLIENT)
/*    */     public IMessage onMessage(PlayerMotionUpdateMessage message, MessageContext ctx) {
/* 45 */       EntityClientPlayerMP entityClientPlayerMP = FMLClientHandler.instance().getClientPlayerEntity();
/*    */       
/* 47 */       ((EntityPlayer)entityClientPlayerMP).motionX = message.x;
/* 48 */       ((EntityPlayer)entityClientPlayerMP).motionY = message.y;
/* 49 */       ((EntityPlayer)entityClientPlayerMP).motionZ = message.z;
/*    */       
/* 51 */       entityClientPlayerMP.setVelocity(message.x, message.y, message.z);
/* 52 */       return null;
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\antro\Documents\forgottenRelicsDeobfuscated.jar!\com\integral\forgottenrelics\packets\PlayerMotionUpdateMessage.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */