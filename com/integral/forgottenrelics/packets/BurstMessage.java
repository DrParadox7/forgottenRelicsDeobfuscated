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
/*    */ import thaumcraft.common.Thaumcraft;
/*    */ 
/*    */ public class BurstMessage
/*    */   implements IMessage {
/*    */   private double x;
/*    */   private double y;
/*    */   private double z;
/*    */   private float size;
/*    */   
/*    */   public BurstMessage() {}
/*    */   
/*    */   public BurstMessage(double x, double y, double z, float size) {
/* 24 */     this.x = x;
/* 25 */     this.y = y;
/* 26 */     this.z = z;
/*    */     
/* 28 */     this.size = size;
/*    */   }
/*    */ 
/*    */   
/*    */   public void fromBytes(ByteBuf buf) {
/* 33 */     this.x = buf.readDouble();
/* 34 */     this.y = buf.readDouble();
/* 35 */     this.z = buf.readDouble();
/*    */     
/* 37 */     this.size = buf.readFloat();
/*    */   }
/*    */ 
/*    */   
/*    */   public void toBytes(ByteBuf buf) {
/* 42 */     buf.writeDouble(this.x);
/* 43 */     buf.writeDouble(this.y);
/* 44 */     buf.writeDouble(this.z);
/*    */     
/* 46 */     buf.writeFloat(this.size);
/*    */   }
/*    */   
/*    */   public static class Handler
/*    */     implements IMessageHandler<BurstMessage, IMessage>
/*    */   {
/*    */     @SideOnly(Side.CLIENT)
/*    */     public IMessage onMessage(BurstMessage message, MessageContext ctx) {
/* 54 */       EntityClientPlayerMP entityClientPlayerMP = FMLClientHandler.instance().getClientPlayerEntity();
/*    */       
/* 56 */       Thaumcraft.proxy.burst(((EntityPlayer)entityClientPlayerMP).worldObj, message.x, message.y, message.z, message.size);
/*    */       
/* 58 */       return null;
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\antro\Documents\forgottenRelicsDeobfuscated.jar!\com\integral\forgottenrelics\packets\BurstMessage.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */