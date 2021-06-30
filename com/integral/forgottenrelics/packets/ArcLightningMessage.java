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
/*    */ public class ArcLightningMessage
/*    */   implements IMessage
/*    */ {
/*    */   private double x;
/*    */   private double y;
/*    */   private double z;
/*    */   private double destx;
/*    */   private double desty;
/*    */   private double destz;
/*    */   private float r;
/*    */   private float g;
/*    */   private float b;
/*    */   private float h;
/*    */   
/*    */   public ArcLightningMessage() {}
/*    */   
/*    */   public ArcLightningMessage(double x, double y, double z, double destx, double desty, double destz, float r, float g, float b, float h) {
/* 31 */     this.x = x;
/* 32 */     this.y = y;
/* 33 */     this.z = z;
/*    */     
/* 35 */     this.destx = destx;
/* 36 */     this.desty = desty;
/* 37 */     this.destz = destz;
/*    */     
/* 39 */     this.r = r;
/* 40 */     this.g = g;
/* 41 */     this.b = b;
/*    */     
/* 43 */     this.h = h;
/*    */   }
/*    */ 
/*    */   
/*    */   public void fromBytes(ByteBuf buf) {
/* 48 */     this.x = buf.readDouble();
/* 49 */     this.y = buf.readDouble();
/* 50 */     this.z = buf.readDouble();
/*    */     
/* 52 */     this.destx = buf.readDouble();
/* 53 */     this.desty = buf.readDouble();
/* 54 */     this.destz = buf.readDouble();
/*    */     
/* 56 */     this.r = buf.readFloat();
/* 57 */     this.g = buf.readFloat();
/* 58 */     this.b = buf.readFloat();
/*    */     
/* 60 */     this.h = buf.readFloat();
/*    */   }
/*    */ 
/*    */   
/*    */   public void toBytes(ByteBuf buf) {
/* 65 */     buf.writeDouble(this.x);
/* 66 */     buf.writeDouble(this.y);
/* 67 */     buf.writeDouble(this.z);
/*    */     
/* 69 */     buf.writeDouble(this.destx);
/* 70 */     buf.writeDouble(this.desty);
/* 71 */     buf.writeDouble(this.destz);
/*    */     
/* 73 */     buf.writeFloat(this.r);
/* 74 */     buf.writeFloat(this.g);
/* 75 */     buf.writeFloat(this.b);
/*    */     
/* 77 */     buf.writeFloat(this.h);
/*    */   }
/*    */   
/*    */   public static class Handler
/*    */     implements IMessageHandler<ArcLightningMessage, IMessage>
/*    */   {
/*    */     @SideOnly(Side.CLIENT)
/*    */     public IMessage onMessage(ArcLightningMessage message, MessageContext ctx) {
/* 85 */       EntityClientPlayerMP entityClientPlayerMP = FMLClientHandler.instance().getClientPlayerEntity();
/*    */       
/* 87 */       Thaumcraft.proxy.arcLightning(((EntityPlayer)entityClientPlayerMP).worldObj, message.x, message.y, message.z, message.destx, message.desty, message.destz, message.r, message.g, message.b, message.h);
/* 88 */       return null;
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\antro\Documents\forgottenRelicsDeobfuscated.jar!\com\integral\forgottenrelics\packets\ArcLightningMessage.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */