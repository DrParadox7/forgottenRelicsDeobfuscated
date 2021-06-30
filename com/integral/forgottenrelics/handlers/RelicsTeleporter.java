/*    */ package com.integral.forgottenrelics.handlers;
/*    */ 
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.world.Teleporter;
/*    */ import net.minecraft.world.WorldServer;
/*    */ 
/*    */ public class RelicsTeleporter
/*    */   extends Teleporter
/*    */ {
/*    */   private final WorldServer worldServerInstance;
/*    */   private double XX;
/*    */   private double YY;
/*    */   private double ZZ;
/*    */   
/*    */   public RelicsTeleporter(WorldServer world, double x, double y, double z) {
/* 17 */     super(world);
/*    */     
/* 19 */     this.worldServerInstance = world;
/*    */     
/* 21 */     this.XX = x;
/* 22 */     this.YY = y;
/* 23 */     this.ZZ = z;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void placeInPortal(Entity entity, double x, double y, double z, float yaw) {
/* 30 */     EntityLivingBase base = (EntityLivingBase)entity;
/* 31 */     entity.setLocationAndAngles(this.XX, this.YY, this.ZZ, entity.rotationYaw, 0.0F);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean placeInExistingPortal(Entity entity, double x, double y, double z, float yaw) {
/* 39 */     EntityLivingBase base = (EntityLivingBase)entity;
/* 40 */     entity.setLocationAndAngles(this.XX, this.YY, this.ZZ, entity.rotationYaw, 0.0F);
/*    */     
/* 42 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean makePortal(Entity entity) {
/* 49 */     EntityLivingBase base = (EntityLivingBase)entity;
/* 50 */     entity.setLocationAndAngles(this.XX, this.YY, this.ZZ, entity.rotationYaw, 0.0F);
/*    */     
/* 52 */     return true;
/*    */   }
/*    */   
/*    */   public void removeStalePortalLocations(long p_85189_1_) {}
/*    */ }


/* Location:              C:\Users\antro\Documents\forgottenRelicsDeobfuscated.jar!\com\integral\forgottenrelics\handlers\RelicsTeleporter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */