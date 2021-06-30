/*    */ package com.integral.forgottenrelics.packets;
/*    */ 
/*    */ import cpw.mods.fml.client.FMLClientHandler;
/*    */ import cpw.mods.fml.common.network.ByteBufUtils;
/*    */ import cpw.mods.fml.common.network.simpleimpl.IMessage;
/*    */ import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
/*    */ import cpw.mods.fml.common.network.simpleimpl.MessageContext;
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import net.minecraft.client.entity.EntityClientPlayerMP;
/*    */ import net.minecraft.util.ChatComponentText;
/*    */ import net.minecraft.util.IChatComponent;
/*    */ import net.minecraft.util.StatCollector;
/*    */ 
/*    */ public class OverthrowChatMessage implements IMessage {
/*    */   private String overthrownPlayer;
/*    */   private String overthrower;
/*    */   private int type;
/*    */   
/*    */   public OverthrowChatMessage() {}
/*    */   
/*    */   public OverthrowChatMessage(String overthrownPlayer, String overthrower, int type) {
/* 24 */     this.overthrownPlayer = overthrownPlayer;
/* 25 */     this.overthrower = overthrower;
/* 26 */     this.type = type;
/*    */   }
/*    */ 
/*    */   
/*    */   public void fromBytes(ByteBuf buf) {
/* 31 */     this.overthrownPlayer = ByteBufUtils.readUTF8String(buf);
/* 32 */     this.overthrower = ByteBufUtils.readUTF8String(buf);
/* 33 */     this.type = buf.readInt();
/*    */   }
/*    */ 
/*    */   
/*    */   public void toBytes(ByteBuf buf) {
/* 38 */     ByteBufUtils.writeUTF8String(buf, this.overthrownPlayer);
/* 39 */     ByteBufUtils.writeUTF8String(buf, this.overthrower);
/* 40 */     buf.writeInt(this.type);
/*    */   }
/*    */   
/*    */   public static class Handler
/*    */     implements IMessageHandler<OverthrowChatMessage, IMessage>
/*    */   {
/*    */     @SideOnly(Side.CLIENT)
/*    */     public IMessage onMessage(OverthrowChatMessage message, MessageContext ctx) {
/* 48 */       EntityClientPlayerMP entityClientPlayerMP = FMLClientHandler.instance().getClientPlayerEntity();
/*    */       
/* 50 */       String overthrown = message.overthrownPlayer;
/* 51 */       String overthrower = message.overthrower;
/*    */       
/* 53 */       if (message.type == 0) {
/* 54 */         entityClientPlayerMP.addChatMessage((IChatComponent)new ChatComponentText(overthrower + " " + StatCollector.translateToLocal("message.overthrown1") + " " + overthrown + " " + StatCollector.translateToLocal("message.overthrown2")));
/* 55 */       } else if (message.type == 1) {
/* 56 */         entityClientPlayerMP.addChatMessage((IChatComponent)new ChatComponentText(overthrower + " " + StatCollector.translateToLocal("message.overthrown1") + " " + overthrown + " " + StatCollector.translateToLocal("message.overthrown3")));
/*    */       } 
/* 58 */       return null;
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\antro\Documents\forgottenRelicsDeobfuscated.jar!\com\integral\forgottenrelics\packets\OverthrowChatMessage.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */