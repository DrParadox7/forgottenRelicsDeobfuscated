/*    */ package com.integral.forgottenrelics.handlers;
/*    */ 
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.world.Teleporter;
/*    */ import net.minecraft.world.WorldServer;
/*    */ 
/*    */ public class ExtradimensionalTeleporter
/*    */   extends Teleporter {
/*    */   private double X;
/*    */   private double Y;
/*    */   private double Z;
/*    */   
/*    */   public ExtradimensionalTeleporter(WorldServer p_i1963_1_, double x, double y, double z) {
/* 15 */     super(p_i1963_1_);
/* 16 */     this.X = x;
/* 17 */     this.Y = y;
/* 18 */     this.Z = z;
/*    */   }
/*    */ 
/*    */   
/*    */   public void placeInPortal(Entity entity, double x, double y, double z, float rotationYaw) {
/* 23 */     if (entity instanceof EntityPlayer) {
/* 24 */       EntityPlayer player = (EntityPlayer)entity;
/* 25 */       player.setPositionAndUpdate(this.X, this.Y, this.Z);
/*    */     } else {
/* 27 */       entity.posX = this.X;
/* 28 */       entity.posY = this.Y;
/* 29 */       entity.posZ = this.Z;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\antro\Documents\forgottenRelicsDeobfuscated.jar!\com\integral\forgottenrelics\handlers\ExtradimensionalTeleporter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */