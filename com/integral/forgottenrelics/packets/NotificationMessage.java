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
/*    */ import net.minecraft.util.StatCollector;
/*    */ import thaumcraft.client.lib.PlayerNotifications;
/*    */ 
/*    */ public class NotificationMessage
/*    */   implements IMessage
/*    */ {
/*    */   private int type;
/*    */   
/*    */   public NotificationMessage() {}
/*    */   
/*    */   public NotificationMessage(int type) {
/* 23 */     this.type = type;
/*    */   }
/*    */ 
/*    */   
/*    */   public void fromBytes(ByteBuf buf) {
/* 28 */     this.type = buf.readInt();
/*    */   }
/*    */ 
/*    */   
/*    */   public void toBytes(ByteBuf buf) {
/* 33 */     buf.writeInt(this.type);
/*    */   }
/*    */   
/*    */   public static class Handler
/*    */     implements IMessageHandler<NotificationMessage, IMessage> {
/*    */     @SideOnly(Side.CLIENT)
/*    */     public IMessage onMessage(NotificationMessage message, MessageContext ctx) {
/*    */       String notification;
/* 41 */       EntityClientPlayerMP entityClientPlayerMP = FMLClientHandler.instance().getClientPlayerEntity();
/*    */ 
/*    */ 
/*    */       
/* 45 */       switch (message.type) {
/*    */         case 1:
/* 47 */           notification = StatCollector.translateToLocal("notification.fate_cooldown_over");
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */           
/* 59 */           PlayerNotifications.addNotification(notification);
/*    */           
/* 61 */           return null;case 2: notification = StatCollector.translateToLocal("notification.overdamage_block"); PlayerNotifications.addNotification(notification); return null;
/*    */       } 
/*    */       Main.log.error("Recived invalid notification!");
/*    */       return null;
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\antro\Documents\forgottenRelicsDeobfuscated.jar!\com\integral\forgottenrelics\packets\NotificationMessage.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */