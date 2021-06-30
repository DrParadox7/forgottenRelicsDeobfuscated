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
/*    */ import vazkii.botania.common.Botania;
/*    */ import vazkii.botania.common.core.helper.Vector3;
/*    */ 
/*    */ public class BanishmentCastingMessage
/*    */   implements IMessage {
/*    */   private double x;
/*    */   private double y;
/*    */   private double z;
/*    */   private int amount;
/*    */   
/*    */   public BanishmentCastingMessage() {}
/*    */   
/*    */   public BanishmentCastingMessage(double x, double y, double z, int amount) {
/* 25 */     this.x = x;
/* 26 */     this.y = y;
/* 27 */     this.z = z;
/*    */     
/* 29 */     this.amount = amount;
/*    */   }
/*    */ 
/*    */   
/*    */   public void fromBytes(ByteBuf buf) {
/* 34 */     this.x = buf.readDouble();
/* 35 */     this.y = buf.readDouble();
/* 36 */     this.z = buf.readDouble();
/*    */     
/* 38 */     this.amount = buf.readInt();
/*    */   }
/*    */ 
/*    */   
/*    */   public void toBytes(ByteBuf buf) {
/* 43 */     buf.writeDouble(this.x);
/* 44 */     buf.writeDouble(this.y);
/* 45 */     buf.writeDouble(this.z);
/*    */     
/* 47 */     buf.writeInt(this.amount);
/*    */   }
/*    */   
/*    */   public static class Handler
/*    */     implements IMessageHandler<BanishmentCastingMessage, IMessage>
/*    */   {
/*    */     @SideOnly(Side.CLIENT)
/*    */     public IMessage onMessage(BanishmentCastingMessage message, MessageContext ctx) {
/* 55 */       EntityClientPlayerMP entityClientPlayerMP = FMLClientHandler.instance().getClientPlayerEntity();
/*    */       
/* 57 */       Vector3 thisPos = new Vector3(message.x, message.y, message.z);
/*    */       
/* 59 */       for (int counter = 0; counter < message.amount; counter++) {
/*    */         
/* 61 */         double calculatedPositionX = message.x + (Math.random() - 0.5D) * 8.0D;
/* 62 */         double calculatedPositionY = message.y + (Math.random() - 0.5D) * 8.0D;
/* 63 */         double calculatedPositionZ = message.z + (Math.random() - 0.5D) * 8.0D;
/*    */         
/* 65 */         Vector3 targetPos = new Vector3(calculatedPositionX, calculatedPositionY, calculatedPositionZ);
/* 66 */         Vector3 diff = thisPos.copy().sub(targetPos);
/*    */         
/* 68 */         diff.multiply(0.07999999821186066D);
/*    */         
/* 70 */         float calculatedMotionX = (float)diff.x;
/* 71 */         float calculatedMotionY = (float)diff.y;
/* 72 */         float calculatedMotionZ = (float)diff.z;
/*    */         
/* 74 */         float r = 0.9F + (float)Math.random() * 0.1F;
/* 75 */         float g = 0.1F + (float)Math.random() * 0.15F;
/* 76 */         float b = 0.0F;
/*    */         
/* 78 */         float s = 0.2F + (float)Math.random() * 0.2F;
/*    */         
/* 80 */         Botania.proxy.wispFX(((EntityPlayer)entityClientPlayerMP).worldObj, calculatedPositionX, calculatedPositionY, calculatedPositionZ, r, g, b, s, calculatedMotionX, calculatedMotionY, calculatedMotionZ, 0.5F);
/*    */       } 
/*    */       
/* 83 */       return null;
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\antro\Documents\forgottenRelicsDeobfuscated.jar!\com\integral\forgottenrelics\packets\BanishmentCastingMessage.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */