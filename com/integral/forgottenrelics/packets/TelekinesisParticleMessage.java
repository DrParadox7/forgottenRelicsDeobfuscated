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
/*    */ import vazkii.botania.common.Botania;
/*    */ 
/*    */ public class TelekinesisParticleMessage
/*    */   implements IMessage
/*    */ {
/*    */   private double x;
/*    */   private double y;
/*    */   private double z;
/*    */   private float modifier;
/*    */   
/*    */   public TelekinesisParticleMessage() {}
/*    */   
/*    */   public TelekinesisParticleMessage(double x, double y, double z, float modifier) {
/* 26 */     this.x = x;
/* 27 */     this.y = y;
/* 28 */     this.z = z;
/*    */     
/* 30 */     this.modifier = modifier;
/*    */   }
/*    */ 
/*    */   
/*    */   public void fromBytes(ByteBuf buf) {
/* 35 */     this.x = buf.readDouble();
/* 36 */     this.y = buf.readDouble();
/* 37 */     this.z = buf.readDouble();
/*    */     
/* 39 */     this.modifier = buf.readFloat();
/*    */   }
/*    */ 
/*    */   
/*    */   public void toBytes(ByteBuf buf) {
/* 44 */     buf.writeDouble(this.x);
/* 45 */     buf.writeDouble(this.y);
/* 46 */     buf.writeDouble(this.z);
/*    */     
/* 48 */     buf.writeFloat(this.modifier);
/*    */   }
/*    */   
/*    */   public static class Handler
/*    */     implements IMessageHandler<TelekinesisParticleMessage, IMessage>
/*    */   {
/*    */     @SideOnly(Side.CLIENT)
/*    */     public IMessage onMessage(TelekinesisParticleMessage message, MessageContext ctx) {
/* 56 */       EntityClientPlayerMP entityClientPlayerMP = FMLClientHandler.instance().getClientPlayerEntity();
/*    */       
/* 58 */       int wisps = (int)(1.0D * message.modifier);
/* 59 */       int supers = (int)(3.0D * message.modifier);
/*    */       
/* 61 */       for (int i = 0; i <= wisps; i++) {
/* 62 */         float r = 0.2F + (float)Math.random() * 0.3F;
/* 63 */         float g = 0.0F;
/* 64 */         float b = 0.5F + (float)Math.random() * 0.2F;
/* 65 */         float s = 0.2F + (float)Math.random() * 0.1F;
/* 66 */         float m = 0.15F;
/* 67 */         float xm = ((float)Math.random() - 0.5F) * m;
/* 68 */         float ym = ((float)Math.random() - 0.5F) * m;
/* 69 */         float zm = ((float)Math.random() - 0.5F) * m;
/*    */ 
/*    */         
/* 72 */         Botania.proxy.wispFX(((EntityPlayer)entityClientPlayerMP).worldObj, message.x, message.y, message.z, r, g, b, s, xm, ym, zm);
/*    */       } 
/*    */ 
/*    */       
/* 76 */       for (int counter = 0; counter <= supers; counter++) {
/* 77 */         Main.proxy.spawnSuperParticle(((EntityPlayer)entityClientPlayerMP).worldObj, "portalstuff", message.x, message.y, message.z, (Math.random() - 0.5D) * 3.0D, (Math.random() - 0.5D) * 3.0D, (Math.random() - 0.5D) * 3.0D, 1.0F, 64.0D);
/*    */       }
/* 79 */       return null;
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\antro\Documents\forgottenRelicsDeobfuscated.jar!\com\integral\forgottenrelics\packets\TelekinesisParticleMessage.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */