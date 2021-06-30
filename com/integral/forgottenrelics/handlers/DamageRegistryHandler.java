/*    */ package com.integral.forgottenrelics.handlers;
/*    */ 
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.util.DamageSource;
/*    */ import net.minecraft.util.EntityDamageSource;
/*    */ 
/*    */ public class DamageRegistryHandler {
/*    */   public static class DamageSourceTrueDamage
/*    */     extends EntityDamageSource {
/*    */     public DamageSourceTrueDamage(Entity entity) {
/* 11 */       super("trueDamage", entity);
/* 12 */       setMagicDamage();
/* 13 */       setDamageIsAbsolute();
/* 14 */       setDamageBypassesArmor();
/* 15 */       setDamageAllowedInCreativeMode();
/*    */     }
/*    */   }
/*    */   
/*    */   public static class DamageSourceSoulDrain extends EntityDamageSource {
/*    */     public DamageSourceSoulDrain(Entity entity) {
/* 21 */       super("soulAttack", entity);
/* 22 */       setMagicDamage();
/* 23 */       setDamageIsAbsolute();
/* 24 */       setDamageBypassesArmor();
/*    */     }
/*    */   }
/*    */   
/*    */   public static class DamageSourceTrueDamageUndef extends DamageSource {
/*    */     public DamageSourceTrueDamageUndef() {
/* 30 */       super("trueDamageUndef");
/* 31 */       setMagicDamage();
/* 32 */       setDamageIsAbsolute();
/* 33 */       setDamageBypassesArmor();
/* 34 */       setDamageAllowedInCreativeMode();
/*    */     }
/*    */   }
/*    */   
/*    */   public static class DamageSourceOblivion extends DamageSource {
/*    */     public DamageSourceOblivion() {
/* 40 */       super("attackOblivion");
/* 41 */       setMagicDamage();
/*    */     }
/*    */   }
/*    */   
/*    */   public static class DamageSourceFate extends DamageSource {
/*    */     public DamageSourceFate() {
/* 47 */       super("attackFate");
/* 48 */       setMagicDamage();
/* 49 */       setDamageIsAbsolute();
/* 50 */       setDamageBypassesArmor();
/* 51 */       setDamageAllowedInCreativeMode();
/*    */     }
/*    */   }
/*    */   
/*    */   public static class DamageSourceTLightning extends EntityDamageSource {
/*    */     public DamageSourceTLightning(Entity entity) {
/* 57 */       super("attackLightning", entity);
/*    */     }
/*    */   }
/*    */   
/*    */   public static class DamageSourceDarkMatter extends EntityDamageSource {
/*    */     public DamageSourceDarkMatter(Entity entity) {
/* 63 */       super("attackDarkMatter", entity);
/* 64 */       setMagicDamage();
/* 65 */       setDamageBypassesArmor();
/*    */     }
/*    */   }
/*    */   
/*    */   public static class DamageSourceMagic extends EntityDamageSource {
/*    */     public DamageSourceMagic(Entity entity) {
/* 71 */       super("forgottenMagic", entity);
/* 72 */       setMagicDamage();
/*    */     }
/*    */   }
/*    */   
/*    */   public static class DamageSourceSuperposition extends DamageSource {
/*    */     public DamageSourceSuperposition() {
/* 78 */       super("superpositionedDamage");
/*    */     }
/*    */   }
/*    */   
/*    */   public static class DamageSourceSuperpositionDefined extends EntityDamageSource {
/*    */     public DamageSourceSuperpositionDefined(Entity entity) {
/* 84 */       super("superpositionedDamage", entity);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\antro\Documents\forgottenRelicsDeobfuscated.jar!\com\integral\forgottenrelics\handlers\DamageRegistryHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */