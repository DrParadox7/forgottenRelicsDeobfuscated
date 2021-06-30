/*     */ package com.integral.forgottenrelics.packets;
/*     */ 
/*     */ import com.integral.forgottenrelics.Main;
/*     */ import cpw.mods.fml.client.FMLClientHandler;
/*     */ import cpw.mods.fml.common.network.simpleimpl.IMessage;
/*     */ import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
/*     */ import cpw.mods.fml.common.network.simpleimpl.MessageContext;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import io.netty.buffer.ByteBuf;
/*     */ import net.minecraft.client.entity.EntityClientPlayerMP;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import vazkii.botania.common.Botania;
/*     */ import vazkii.botania.common.core.helper.Vector3;
/*     */ 
/*     */ public class PacketVoidMessage
/*     */   implements IMessage
/*     */ {
/*     */   private double x;
/*     */   private double y;
/*     */   private double z;
/*     */   private boolean finish;
/*     */   
/*     */   public PacketVoidMessage() {}
/*     */   
/*     */   public PacketVoidMessage(double x, double y, double z, boolean finish) {
/*  27 */     this.x = x;
/*  28 */     this.y = y;
/*  29 */     this.z = z;
/*     */     
/*  31 */     this.finish = finish;
/*     */   }
/*     */ 
/*     */   
/*     */   public void fromBytes(ByteBuf buf) {
/*  36 */     this.x = buf.readDouble();
/*  37 */     this.y = buf.readDouble();
/*  38 */     this.z = buf.readDouble();
/*     */     
/*  40 */     this.finish = buf.readBoolean();
/*     */   }
/*     */ 
/*     */   
/*     */   public void toBytes(ByteBuf buf) {
/*  45 */     buf.writeDouble(this.x);
/*  46 */     buf.writeDouble(this.y);
/*  47 */     buf.writeDouble(this.z);
/*     */     
/*  49 */     buf.writeBoolean(this.finish);
/*     */   }
/*     */   
/*     */   public static class Handler
/*     */     implements IMessageHandler<PacketVoidMessage, IMessage>
/*     */   {
/*     */     @SideOnly(Side.CLIENT)
/*     */     public IMessage onMessage(PacketVoidMessage message, MessageContext ctx) {
/*  57 */       EntityClientPlayerMP entityClientPlayerMP = FMLClientHandler.instance().getClientPlayerEntity();
/*     */       
/*  59 */       Vector3 thisPos = new Vector3(message.x, message.y, message.z);
/*     */       
/*  61 */       if (!message.finish) {
/*     */         int counter;
/*  63 */         for (counter = 0; counter < 8; counter++) {
/*     */           
/*  65 */           double calculatedPositionX = thisPos.x + (Math.random() - 0.5D) * 12.0D;
/*  66 */           double calculatedPositionY = thisPos.y + (Math.random() - 0.5D) * 12.0D;
/*  67 */           double calculatedPositionZ = thisPos.z + (Math.random() - 0.5D) * 12.0D;
/*     */           
/*  69 */           Vector3 targetPos = new Vector3(calculatedPositionX, calculatedPositionY, calculatedPositionZ);
/*  70 */           Vector3 diff = thisPos.copy().sub(targetPos);
/*     */           
/*  72 */           diff.multiply(0.07999999821186066D);
/*     */           
/*  74 */           float calculatedMotionX = (float)diff.x;
/*  75 */           float calculatedMotionY = (float)diff.y;
/*  76 */           float calculatedMotionZ = (float)diff.z;
/*     */           
/*  78 */           float r = 0.2F + (float)Math.random() * 0.3F;
/*  79 */           float g = 0.0F;
/*  80 */           float b = 0.5F + (float)Math.random() * 0.2F;
/*     */           
/*  82 */           float s = 0.2F + (float)Math.random() * 0.2F;
/*     */           
/*  84 */           Botania.proxy.wispFX(((EntityPlayer)entityClientPlayerMP).worldObj, calculatedPositionX, calculatedPositionY, calculatedPositionZ, r, g, b, s, calculatedMotionX, calculatedMotionY, calculatedMotionZ, 0.45F);
/*     */         } 
/*     */         
/*  87 */         for (counter = 0; counter < 5; counter++) {
/*  88 */           Main.proxy.spawnSuperParticle(((EntityPlayer)entityClientPlayerMP).worldObj, "portalstuff", thisPos.x, thisPos.y, thisPos.z, (Math.random() - 0.5D) * 8.0D, (Math.random() - 0.5D) * 8.0D, (Math.random() - 0.5D) * 8.0D, 1.0F, 64.0D);
/*     */         }
/*     */       } else {
/*  91 */         for (int i = 0; i <= 128; i++) {
/*  92 */           float r = 0.2F + (float)Math.random() * 0.3F;
/*  93 */           float g = 0.0F;
/*  94 */           float b = 0.5F + (float)Math.random() * 0.2F;
/*  95 */           float s = 0.4F + (float)Math.random() * 0.4F;
/*  96 */           float m = 0.5F;
/*  97 */           float xm = ((float)Math.random() - 0.5F) * m;
/*  98 */           float ym = ((float)Math.random() - 0.5F) * m;
/*  99 */           float zm = ((float)Math.random() - 0.5F) * m;
/*     */           
/* 101 */           Botania.proxy.setWispFXDistanceLimit(false);
/* 102 */           Botania.proxy.wispFX(((EntityPlayer)entityClientPlayerMP).worldObj, thisPos.x, thisPos.y, thisPos.z, r, g, b, s, xm, ym, zm, 1.0F);
/* 103 */           Botania.proxy.setWispFXDistanceLimit(true);
/*     */         } 
/*     */       } 
/*     */       
/* 107 */       return null;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\antro\Documents\forgottenRelicsDeobfuscated.jar!\com\integral\forgottenrelics\packets\PacketVoidMessage.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */