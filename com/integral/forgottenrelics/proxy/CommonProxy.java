package com.integral.forgottenrelics.proxy;

import com.integral.forgottenrelics.Main;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;

public class CommonProxy {
  public void registerRenderers(Main ins) {}
  
  public void wispFX4(World worldObj, double posX, double posY, double posZ, Entity target, int type, boolean shrink, float gravity) {}
  
  public void lightning(World world, double sx, double sy, double sz, double ex, double ey, double ez, int dur, float curve, int speed, int type, float width) {}
  
  public void spawnSuperParticle(World world, String particleType, double x, double y, double z, double velX, double velY, double velZ, float particleSize, double renderDistance) {}
  
  public void registerDisplayInformation() {}
  
  public void lunarBurst(World world, double x, double y, double z, float size) {}
  
  public void registerKeybinds() {}
}


/* Location:              C:\Users\antro\Documents\forgottenRelicsDeobfuscated.jar!\com\integral\forgottenrelics\proxy\CommonProxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */