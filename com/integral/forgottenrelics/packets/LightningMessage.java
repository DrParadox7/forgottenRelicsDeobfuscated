/*    */ package com.integral.forgottenrelics.packets;
/*    */ 
/*    */ import com.integral.forgottenrelics.Main;
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
/*    */ 
/*    */ public class LightningMessage
/*    */   implements IMessage
/*    */ {
/*    */   private double x;
/*    */   private double y;
/*    */   private double z;
/*    */   private double destx;
/*    */   private double desty;
/*    */   private double destz;
/*    */   private int duration;
/*    */   private float curve;
/*    */   private int speed;
/*    */   private int type;
/*    */   private float width;
/*    */   
/*    */   public LightningMessage() {}
/*    */   
/*    */   public LightningMessage(double x, double y, double z, double destx, double desty, double destz, int duration, float curve, int speed, int type, float width) {
/* 33 */     this.x = x;
/* 34 */     this.y = y;
/* 35 */     this.z = z;
/*    */     
/* 37 */     this.destx = destx;
/* 38 */     this.desty = desty;
/* 39 */     this.destz = destz;
/*    */     
/* 41 */     this.duration = duration;
/* 42 */     this.curve = curve;
/* 43 */     this.speed = speed;
/* 44 */     this.type = type;
/* 45 */     this.width = width;
/*    */   }
/*    */ 
/*    */   
/*    */   public void fromBytes(ByteBuf buf) {
/* 50 */     this.x = buf.readDouble();
/* 51 */     this.y = buf.readDouble();
/* 52 */     this.z = buf.readDouble();
/*    */     
/* 54 */     this.destx = buf.readDouble();
/* 55 */     this.desty = buf.readDouble();
/* 56 */     this.destz = buf.readDouble();
/*    */     
/* 58 */     this.duration = buf.readInt();
/* 59 */     this.curve = buf.readFloat();
/* 60 */     this.speed = buf.readInt();
/* 61 */     this.type = buf.readInt();
/* 62 */     this.width = buf.readFloat();
/*    */   }
/*    */ 
/*    */   
/*    */   public void toBytes(ByteBuf buf) {
/* 67 */     buf.writeDouble(this.x);
/* 68 */     buf.writeDouble(this.y);
/* 69 */     buf.writeDouble(this.z);
/*    */     
/* 71 */     buf.writeDouble(this.destx);
/* 72 */     buf.writeDouble(this.desty);
/* 73 */     buf.writeDouble(this.destz);
/*    */     
/* 75 */     buf.writeInt(this.duration);
/* 76 */     buf.writeFloat(this.curve);
/* 77 */     buf.writeInt(this.speed);
/* 78 */     buf.writeInt(this.type);
/* 79 */     buf.writeFloat(this.width);
/*    */   }
/*    */   
/*    */   public static class Handler
/*    */     implements IMessageHandler<LightningMessage, IMessage>
/*    */   {
/*    */     @SideOnly(Side.CLIENT)
/*    */     public IMessage onMessage(LightningMessage message, MessageContext ctx) {
/* 87 */       EntityClientPlayerMP entityClientPlayerMP = FMLClientHandler.instance().getClientPlayerEntity();
/*    */       
/* 89 */       float fix = 1.0F;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */       
/* 98 */       Main.proxy.lightning(((EntityPlayer)entityClientPlayerMP).worldObj, message.x, message.y, message.z, message.destx, message.desty, message.destz, message.duration, message.curve, message.speed, message.type, message.width);
/* 99 */       return null;
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\antro\Documents\forgottenRelicsDeobfuscated.jar!\com\integral\forgottenrelics\packets\LightningMessage.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */