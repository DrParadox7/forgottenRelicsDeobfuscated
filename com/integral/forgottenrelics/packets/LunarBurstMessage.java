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
/*    */ public class LunarBurstMessage
/*    */   implements IMessage
/*    */ {
/*    */   private double x;
/*    */   private double y;
/*    */   private double z;
/*    */   private float size;
/*    */   
/*    */   public LunarBurstMessage() {}
/*    */   
/*    */   public LunarBurstMessage(double x, double y, double z, float size) {
/* 25 */     this.x = x;
/* 26 */     this.y = y;
/* 27 */     this.z = z;
/*    */     
/* 29 */     this.size = size;
/*    */   }
/*    */ 
/*    */   
/*    */   public void fromBytes(ByteBuf buf) {
/* 34 */     this.x = buf.readDouble();
/* 35 */     this.y = buf.readDouble();
/* 36 */     this.z = buf.readDouble();
/*    */     
/* 38 */     this.size = buf.readFloat();
/*    */   }
/*    */ 
/*    */   
/*    */   public void toBytes(ByteBuf buf) {
/* 43 */     buf.writeDouble(this.x);
/* 44 */     buf.writeDouble(this.y);
/* 45 */     buf.writeDouble(this.z);
/*    */     
/* 47 */     buf.writeFloat(this.size);
/*    */   }
/*    */   
/*    */   public static class Handler
/*    */     implements IMessageHandler<LunarBurstMessage, IMessage>
/*    */   {
/*    */     @SideOnly(Side.CLIENT)
/*    */     public IMessage onMessage(LunarBurstMessage message, MessageContext ctx) {
/* 55 */       EntityClientPlayerMP entityClientPlayerMP = FMLClientHandler.instance().getClientPlayerEntity();
/*    */       
/* 57 */       Main.proxy.lunarBurst(((EntityPlayer)entityClientPlayerMP).worldObj, message.x, message.y, message.z, message.size);
/*    */       
/* 59 */       return null;
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\antro\Documents\forgottenRelicsDeobfuscated.jar!\com\integral\forgottenrelics\packets\LunarBurstMessage.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */