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
/*    */ import vazkii.botania.common.core.helper.Vector3;
/*    */ 
/*    */ 
/*    */ public class PortalTraceMessage
/*    */   implements IMessage
/*    */ {
/*    */   private double x;
/*    */   private double y;
/*    */   private double z;
/*    */   private double xs;
/*    */   private double ys;
/*    */   private double zs;
/*    */   private double distance;
/*    */   
/*    */   public PortalTraceMessage() {}
/*    */   
/*    */   public PortalTraceMessage(double x, double y, double z, double xs, double ys, double zs, double distance) {
/* 30 */     this.x = x;
/* 31 */     this.y = y;
/* 32 */     this.z = z;
/*    */     
/* 34 */     this.xs = xs;
/* 35 */     this.ys = ys;
/* 36 */     this.zs = zs;
/*    */     
/* 38 */     this.distance = distance;
/*    */   }
/*    */ 
/*    */   
/*    */   public void fromBytes(ByteBuf buf) {
/* 43 */     this.x = buf.readDouble();
/* 44 */     this.y = buf.readDouble();
/* 45 */     this.z = buf.readDouble();
/*    */     
/* 47 */     this.xs = buf.readDouble();
/* 48 */     this.ys = buf.readDouble();
/* 49 */     this.zs = buf.readDouble();
/*    */     
/* 51 */     this.distance = buf.readDouble();
/*    */   }
/*    */ 
/*    */   
/*    */   public void toBytes(ByteBuf buf) {
/* 56 */     buf.writeDouble(this.x);
/* 57 */     buf.writeDouble(this.y);
/* 58 */     buf.writeDouble(this.z);
/*    */     
/* 60 */     buf.writeDouble(this.xs);
/* 61 */     buf.writeDouble(this.ys);
/* 62 */     buf.writeDouble(this.zs);
/*    */     
/* 64 */     buf.writeDouble(this.distance);
/*    */   }
/*    */ 
/*    */   
/*    */   public static class Handler
/*    */     implements IMessageHandler<PortalTraceMessage, IMessage>
/*    */   {
/*    */     @SideOnly(Side.CLIENT)
/*    */     public IMessage onMessage(PortalTraceMessage message, MessageContext ctx) {
/* 73 */       EntityClientPlayerMP entityClientPlayerMP = FMLClientHandler.instance().getClientPlayerEntity();
/*    */       
/* 75 */       Vector3 primalVec = new Vector3(message.x, message.y, message.z);
/* 76 */       Vector3 finalVec = new Vector3(message.xs, message.ys, message.zs);
/* 77 */       Vector3 diffVec = finalVec.copy().sub(primalVec);
/* 78 */       Vector3 motionVec = diffVec.copy().multiply(1.0D / message.distance);
/*    */       
/* 80 */       for (int counterS = (int)message.distance; counterS >= 0; counterS--) {
/*    */         
/* 82 */         for (int ISS = 0; ISS <= 4; ISS++) {
/* 83 */           Main.proxy.spawnSuperParticle(((EntityPlayer)entityClientPlayerMP).worldObj, "portalstuff", primalVec.x, primalVec.y, primalVec.z, (Math.random() - 0.5D) * 1.0D, (Math.random() - 0.5D) * 1.0D, (Math.random() - 0.5D) * 1.0D, 1.0F, 64.0D);
/*    */         }
/* 85 */         primalVec.add(motionVec);
/*    */       } 
/*    */       
/* 88 */       return null;
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\antro\Documents\forgottenRelicsDeobfuscated.jar!\com\integral\forgottenrelics\packets\PortalTraceMessage.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */