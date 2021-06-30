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
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.effect.EntityLightningBolt;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ 
/*    */ public class LightningBoltMessage implements IMessage {
/*    */   private double x;
/*    */   private double y;
/*    */   private double z;
/*    */   private int amount;
/*    */   
/*    */   public LightningBoltMessage() {}
/*    */   
/*    */   public LightningBoltMessage(double x, double y, double z, int amount) {
/* 24 */     this.x = x;
/* 25 */     this.y = y;
/* 26 */     this.z = z;
/*    */     
/* 28 */     this.amount = amount;
/*    */   }
/*    */ 
/*    */   
/*    */   public void fromBytes(ByteBuf buf) {
/* 33 */     this.x = buf.readDouble();
/* 34 */     this.y = buf.readDouble();
/* 35 */     this.z = buf.readDouble();
/*    */     
/* 37 */     this.amount = buf.readInt();
/*    */   }
/*    */ 
/*    */   
/*    */   public void toBytes(ByteBuf buf) {
/* 42 */     buf.writeDouble(this.x);
/* 43 */     buf.writeDouble(this.y);
/* 44 */     buf.writeDouble(this.z);
/*    */     
/* 46 */     buf.writeInt(this.amount);
/*    */   }
/*    */   
/*    */   public static class Handler
/*    */     implements IMessageHandler<LightningBoltMessage, IMessage>
/*    */   {
/*    */     @SideOnly(Side.CLIENT)
/*    */     public IMessage onMessage(LightningBoltMessage message, MessageContext ctx) {
/* 54 */       EntityClientPlayerMP entityClientPlayerMP = FMLClientHandler.instance().getClientPlayerEntity();
/*    */       
/* 56 */       for (int counter = message.amount; counter > 0; counter--) {
/* 57 */         ((EntityPlayer)entityClientPlayerMP).worldObj.spawnEntityInWorld((Entity)new EntityLightningBolt(((EntityPlayer)entityClientPlayerMP).worldObj, message.x, message.y, message.z));
/*    */       }
/* 59 */       return null;
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\antro\Documents\forgottenRelicsDeobfuscated.jar!\com\integral\forgottenrelics\packets\LightningBoltMessage.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */