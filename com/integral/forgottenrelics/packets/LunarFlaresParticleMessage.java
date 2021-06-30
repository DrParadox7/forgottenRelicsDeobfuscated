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
/*    */ 
/*    */ public class LunarFlaresParticleMessage
/*    */   implements IMessage {
/*    */   private double x;
/*    */   private double y;
/*    */   private double z;
/*    */   private int quantity;
/*    */   
/*    */   public LunarFlaresParticleMessage() {}
/*    */   
/*    */   public LunarFlaresParticleMessage(double x, double y, double z, int quantity) {
/* 24 */     this.x = x;
/* 25 */     this.y = y;
/* 26 */     this.z = z;
/*    */     
/* 28 */     this.quantity = quantity;
/*    */   }
/*    */ 
/*    */   
/*    */   public void fromBytes(ByteBuf buf) {
/* 33 */     this.x = buf.readDouble();
/* 34 */     this.y = buf.readDouble();
/* 35 */     this.z = buf.readDouble();
/*    */     
/* 37 */     this.quantity = buf.readInt();
/*    */   }
/*    */ 
/*    */   
/*    */   public void toBytes(ByteBuf buf) {
/* 42 */     buf.writeDouble(this.x);
/* 43 */     buf.writeDouble(this.y);
/* 44 */     buf.writeDouble(this.z);
/*    */     
/* 46 */     buf.writeInt(this.quantity);
/*    */   }
/*    */   
/*    */   public static class Handler
/*    */     implements IMessageHandler<LunarFlaresParticleMessage, IMessage>
/*    */   {
/*    */     @SideOnly(Side.CLIENT)
/*    */     public IMessage onMessage(LunarFlaresParticleMessage message, MessageContext ctx) {
/* 54 */       EntityClientPlayerMP entityClientPlayerMP = FMLClientHandler.instance().getClientPlayerEntity();
/*    */       
/* 56 */       for (int i = 0; i <= message.quantity; i++) {
/* 57 */         float r = 0.0F;
/* 58 */         float g = 0.8F + (float)Math.random() * 0.2F;
/* 59 */         float b = 0.4F + (float)Math.random() * 0.6F;
/* 60 */         float s = 0.3F + (float)Math.random() * 0.3F;
/* 61 */         float m = 0.4F;
/* 62 */         float xm = ((float)Math.random() - 0.5F) * m;
/* 63 */         float ym = ((float)Math.random() - 0.5F) * m;
/* 64 */         float zm = ((float)Math.random() - 0.5F) * m;
/*    */         
/* 66 */         Botania.proxy.setWispFXDistanceLimit(false);
/* 67 */         Botania.proxy.wispFX(((EntityPlayer)entityClientPlayerMP).worldObj, message.x, message.y, message.z, r, g, b, s, xm, ym, zm, 1.0F);
/* 68 */         Botania.proxy.setWispFXDistanceLimit(true);
/*    */       } 
/*    */       
/* 71 */       return null;
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\antro\Documents\forgottenRelicsDeobfuscated.jar!\com\integral\forgottenrelics\packets\LunarFlaresParticleMessage.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */