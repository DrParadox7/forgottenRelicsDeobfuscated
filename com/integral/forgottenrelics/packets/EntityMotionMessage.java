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
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ 
/*    */ public class EntityMotionMessage
/*    */   implements IMessage
/*    */ {
/*    */   private int entityID;
/*    */   private double motX;
/*    */   private double motY;
/*    */   private double motZ;
/*    */   private boolean motionless;
/*    */   
/*    */   public EntityMotionMessage() {}
/*    */   
/*    */   public EntityMotionMessage(int ID, double motX, double motY, double motZ, boolean stopit) {
/* 27 */     this.entityID = ID;
/*    */     
/* 29 */     this.motX = motX;
/* 30 */     this.motY = motY;
/* 31 */     this.motZ = motZ;
/*    */     
/* 33 */     this.motionless = stopit;
/*    */   }
/*    */ 
/*    */   
/*    */   public void fromBytes(ByteBuf buf) {
/* 38 */     this.entityID = buf.readInt();
/*    */     
/* 40 */     this.motX = buf.readDouble();
/* 41 */     this.motY = buf.readDouble();
/* 42 */     this.motZ = buf.readDouble();
/*    */     
/* 44 */     this.motionless = buf.readBoolean();
/*    */   }
/*    */ 
/*    */   
/*    */   public void toBytes(ByteBuf buf) {
/* 49 */     buf.writeInt(this.entityID);
/*    */     
/* 51 */     buf.writeDouble(this.motX);
/* 52 */     buf.writeDouble(this.motY);
/* 53 */     buf.writeDouble(this.motZ);
/*    */     
/* 55 */     buf.writeBoolean(this.motionless);
/*    */   }
/*    */   
/*    */   public static class Handler
/*    */     implements IMessageHandler<EntityMotionMessage, IMessage>
/*    */   {
/*    */     @SideOnly(Side.CLIENT)
/*    */     public IMessage onMessage(EntityMotionMessage message, MessageContext ctx) {
/* 63 */       EntityClientPlayerMP entityClientPlayerMP = FMLClientHandler.instance().getClientPlayerEntity();
/*    */       
/* 65 */       Entity statedEntity = ((EntityPlayer)entityClientPlayerMP).worldObj.getEntityByID(message.entityID);
/*    */       
/* 67 */       if (statedEntity instanceof EntityLivingBase) {
/* 68 */         EntityLivingBase entity = (EntityLivingBase)statedEntity;
/*    */         
/* 70 */         if (statedEntity != entityClientPlayerMP) {
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */           
/* 76 */           if (message.motionless) {
/* 77 */             entity.fallDistance = 0.0F;
/*    */           }
/* 79 */           entity.setVelocity(message.motX, message.motY, message.motZ);
/*    */           
/* 81 */           entity.velocityChanged = true;
/*    */         
/*    */         }
/*    */         else {
/*    */           
/* 86 */           if (message.motionless) {
/* 87 */             ((EntityPlayer)entityClientPlayerMP).fallDistance = 0.0F;
/*    */           }
/* 89 */           entityClientPlayerMP.setVelocity(message.motX, message.motY, message.motZ);
/*    */         } 
/*    */       } 
/*    */ 
/*    */ 
/*    */       
/* 95 */       return null;
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\antro\Documents\forgottenRelicsDeobfuscated.jar!\com\integral\forgottenrelics\packets\EntityMotionMessage.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */