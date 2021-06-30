/*     */ package com.integral.forgottenrelics.handlers;
/*     */ 
/*     */ import cpw.mods.fml.common.event.FMLPreInitializationEvent;
/*     */ import net.minecraftforge.common.config.Configuration;
/*     */ import net.minecraftforge.common.config.Property;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class RelicsConfigHandler
/*     */ {
/*     */   public static float damageApotheosisDirect;
/*     */   public static float damageApotheosisImpact;
/*     */   public static float damageLunarFlareDirect;
/*     */   public static float damageLunarFlareImpact;
/*     */   public static float paradoxDamageCap;
/*     */   public static float telekinesisTomeDamageMIN;
/*     */   public static float telekinesisTomeDamageMAX;
/*     */   public static float nuclearFuryDamageMIN;
/*     */   public static float nuclearFuryDamageMAX;
/*     */   public static float crimsonSpellDamageMIN;
/*     */   public static float crimsonSpellDamageMAX;
/*     */   public static float weatherStoneVisMult;
/*     */   public static float chaosTomeDamageCap;
/*     */   public static float eldritchSpellDamage;
/*     */   public static float eldritchSpellDamageEx;
/*     */   public static float discordTomeVisMult;
/*     */   public static float telekinesisTomeVisMult;
/*     */   public static float chaosTomeVisMult;
/*     */   public static float eldritchSpellVisMult;
/*     */   public static float crimsonSpellVisMult;
/*     */   public static float soulTomeVisMult;
/*     */   public static float nuclearFuryVisMult;
/*     */   public static float lunarFlaresVisMult;
/*     */   public static float apotheosisVisMult;
/*     */   public static float fateTomeVisMult;
/*     */   public static float obeliskDrainerVisMult;
/*     */   public static float oblivionAmuletVisMult;
/*     */   public static float deificAmuletVisMult;
/*     */   public static float dormantArcanumVisMult;
/*     */   public static float arcanumGenRate;
/*     */   public static float soulTomeDivisor;
/*     */   public static boolean falseJusticeEnabled;
/*     */   public static int shinyStoneCheckrate;
/*     */   public static boolean deificAmuletInvincibility;
/*     */   public static boolean deificAmuletEffectImmunity;
/*     */   public static float darkSunRingDamageCap;
/*     */   public static float darkSunRingDeflectChance;
/*     */   public static boolean darkSunRingHealLimit;
/*     */   public static boolean interdimensionalMirror;
/*     */   public static float ancientAegisDamageReduction;
/*     */   public static float nebulousCoreDodgeChance;
/*     */   public static float miningCharmBoost;
/*     */   public static float miningCharmReach;
/*     */   public static float advancedMiningCharmBoost;
/*     */   public static float advancedMiningCharmReach;
/*     */   public static float damageThunderpealDirect;
/*     */   public static float damageThunderpealBolt;
/*     */   public static float thunderpealVisMult;
/*     */   public static float overthrowerVisMult;
/*     */   public static float voidGrimoireVisMult;
/*     */   public static int runicCost;
/*     */   public static int runicRechargeDelay;
/*     */   public static int runicRechargeSpeed;
/*     */   public static int notificationDelay;
/*     */   public static int fateTomeCooldownMIN;
/*     */   public static int fateTomeCooldownMAX;
/*     */   public static boolean telekinesisOnPlayers;
/*     */   public static float revelationModifier;
/*     */   public static int researchInspectionFrequency;
/*     */   public static double knowledgeChance;
/*     */   public static int outerLandsCheckrate;
/*     */   public static float outerLandsAntiAbuseDamage;
/*     */   public static boolean outerLandsAntiAbuseEnabled;
/*     */   public static boolean voidGrimoireEnabled;
/*     */   public static boolean updateNotificationsEnabled;
/*     */   public static boolean memesEnabled;
/*     */   public static int oblivionStoneSoftCap;
/*     */   public static int oblivionStoneHardCap;
/*     */   public static float guardianNotificationRadius;
/*     */   public static String[] forgottenKnowledgeOverrides;
/*     */   public static boolean forgottenKnowledgeOverridingEnabled;
/*     */   public static float guardianAntiAbuseRadius;
/*     */   public static boolean altTelekinesisAlgorithm;
/*     */   public static boolean deificAmuletOnlyNegatesDebuffs;
/* 108 */   public static String[] exampleOverrides = new String[] { "EldritchSpell[Thaumcraft:ItemEldritchObject:2]", "AdvancedMiningCharm[Botania:manaResource:5, ForgottenRelics:ItemMiningCharm:0, minecraft:diamond_pickaxe:0]", "TerrorCrown[minecraft:ender_eye:0, minecraft:nether_star:0, minecraft:golden_helmet:0, Botania:manaResource:15]" };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void configDisposition(FMLPreInitializationEvent event) {
/* 136 */     String overridesDesc = "Allows you to override item triggers for any research classified as forgotten knowledge." + System.lineSeparator() + System.lineSeparator() + "Your overrides should be formatted like this: ResearchKey[modid:itemname:meta, modid:itemname:meta, ..., modid:itemname:meta]" + System.lineSeparator() + System.lineSeparator() + "Notice: there are no optional parameters, even metadata should be always specified - even though it makes no difference" + System.lineSeparator() + "for 'damageable' items, like Diamond Sword or Iron Helmet. The Thaumcraft alone decides what to do with one or another" + System.lineSeparator() + "type of item, but items should always be passed like ItemStack with defined metadata - so here it is." + System.lineSeparator() + "If you leave research key with no items specified (for instance: 'AncientAegis[]'), it would simply be unlocked without" + System.lineSeparator() + "scanning any items, but that will still take some time and you will receive respective notification upon unlocking." + System.lineSeparator() + System.lineSeparator() + "Comlete list of forgotten knowledge research keys is always printed into log file upon post-initialization." + System.lineSeparator() + "Needless to say, setting triggers for researches that are not classified as forgotten knowledge won't have any effect." + System.lineSeparator() + System.lineSeparator() + "If you are using MineTweaker, you may prefer using integration features that were designed for it." + System.lineSeparator() + "You can read about them here: https://github.com/Extegral/Forgotten-Relics/wiki/MineTweaker-Integration" + System.lineSeparator() + System.lineSeparator() + "If you have no idea what the Justice Handler is, read this article: " + System.lineSeparator() + "https://github.com/Extegral/Forgotten-Relics/wiki/Research-Trigger-System";
/*     */ 
/*     */     
/* 139 */     Configuration config = new Configuration(event.getSuggestedConfigurationFile());
/* 140 */     config.load();
/*     */ 
/*     */     
/* 143 */     Property theOverrides = config.get("Justice Handler Overrides", "justiceHandlerOverrides", exampleOverrides);
/* 144 */     theOverrides.comment = "Here is some working expamles. You may want to replace them with your own ones if you enabled overriding.";
/* 145 */     this; forgottenKnowledgeOverrides = theOverrides.getStringList();
/*     */     
/* 147 */     this; forgottenKnowledgeOverridingEnabled = config.getBoolean("justiceOverridingEnabled", "Justice Handler Overrides", false, "Whether or not overriding triggers by config should be enabled. If it's disabled, no overrides specified here would take effect.");
/*     */ 
/*     */     
/* 150 */     config.addCustomCategoryComment("Justice Handler Overrides", overridesDesc);
/*     */     
/* 152 */     this; deificAmuletOnlyNegatesDebuffs = config.getBoolean("deificAmuletOnlyNegatesDebuffs", "Generic Config", false, "Allows you to prevent Deific Amulet from dispelling positive potion effects, so that it would only cure debuffs.");
/*     */ 
/*     */     
/* 155 */     this; altTelekinesisAlgorithm = config.getBoolean("altTelekinesisAlgorithm", "Generic Config", false, "Whether or not Tome of Predestiny should be handled in alternative way, that uses vanilla .onUsingTick(). Basically, it just adds charging animation and slows player down while casting.");
/*     */ 
/*     */     
/* 158 */     this; guardianAntiAbuseRadius = config.getFloat("guardianAntiAbuseRadius", "Generic Config", 16.0F, 0.0F, 1024.0F, "Defines the radius in which anti-abuse system of Guardian of Gaia checks for liquids. Set to 0 to disable it... and proclaim yourself as wuss.");
/*     */ 
/*     */     
/* 161 */     this; guardianNotificationRadius = config.getFloat("guardianNotificationRadius", "Generic Config", 64.0F, -32768.0F, 32768.0F, "Defines the radius in which players receive chat notification upon force despawn of Guardian of Gaia (when it's anti-abuse system triggers). Set to 0 to disable notification. Set to any negative value for message to be sent to ALL players that are present in the world at the moment.");
/*     */ 
/*     */     
/* 164 */     this; oblivionStoneHardCap = config.getInt("oblivionStoneHardCap", "Generic Config", 64, 0, 2048, "How much items you can add into list of single Keystone of The Oblivion before you would be unable add nothing more. This limit exists to prevent players from occasional or intentional abusing, since multiple keystones with huge lists (like tens of thousands of items) may cause significant performance impact.");
/*     */ 
/*     */     
/* 167 */     this; oblivionStoneSoftCap = config.getInt("oblivionStoneSoftCap", "Generic Config", 28, 0, 2048, "Controls the amount of items that can be added into list of Keystone of The Oblivion, before displayble list in Ctrl tooltip stops expanding and becomes unreadable. You may want to increase or decrease it, depending on your screen resolution.");
/*     */ 
/*     */     
/* 170 */     this; memesEnabled = config.getBoolean("memesEnabled", "Generic Config", false, "Enables super secret memes. You are not prepared!");
/*     */ 
/*     */     
/* 173 */     this; updateNotificationsEnabled = config.getBoolean("updateNotificationsEnabled", "Generic Config", true, "Whether or not update notifications should be enabled. I have no idea why someone may not want to behold greatness of new versions, but alright, it's all up to you.");
/*     */ 
/*     */     
/* 176 */     this; voidGrimoireEnabled = config.getBoolean("voidGrimoireEnabled", "Generic Config", true, "Whethere or not Grimoire of The Abyss should be enabled. Note that it will only remove respective research, so it would be impossible to create this relic legally - it won't remove existing copies from world or prevent it's spawning from Creative Mode.");
/*     */ 
/*     */     
/* 179 */     this; outerLandsCheckrate = config.getInt("outerLandsCheckrate", "Generic Config", 20, 1, 1024000, "Checkrate for Outer Lands anti-abuse system, if it's enabled. Measured in ticks. Setting this value to 20 means that it would check each player once in 20 ticks, or once per second.");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 185 */     this; outerLandsAntiAbuseEnabled = config.getBoolean("outerLandsAntiAbuseEnabled", "Generic Config", true, "Whether or not anti-abuse system for Outer Lands should be enabled. Disable if you like cheating or don't want it for some other reason.");
/*     */ 
/*     */     
/* 188 */     this; revelationModifier = config.getFloat("revelationModifier", "Generic Config", 1.0F, 0.001F, 32.0F, "Multiplier for probability of revealing forgotten knowledge. This multiplies both inspection frequency and individual chance for each check, so increasing it more than few times over is highly unrecommended.");
/*     */ 
/*     */     
/* 191 */     this; telekinesisOnPlayers = config.getBoolean("telekinesisOnPlayers", "Generic Config", true, "In a perfect world, this option would disable Tome of Predestiny's ability to affect players... BUT IT'S A WRONG WORLD BRO, AHAHAHAHAHAHAHAHAAHAHAHAHAHAH");
/*     */ 
/*     */     
/* 194 */     this; fateTomeCooldownMIN = config.getInt("fateTomeCooldownMIN", "Generic Config", 30, 0, 32768, "Minimal possible cooldown (in seconds) for triggering Tome of Broken Fates' death prevention effect.");
/*     */ 
/*     */     
/* 197 */     this; fateTomeCooldownMAX = config.getInt("fateTomeCooldownMAX", "Generic Config", 90, 0, 32768, "Maximal possible cooldown (in seconds) for triggering Tome of Broken Fates' death prevention effect. Setting this to 0 will disable cooldown entirely.");
/*     */ 
/*     */     
/* 200 */     this; notificationDelay = config.getInt("notificationDelay", "Thaumcraft Overrides", 2000, 0, 32768, "Determines how fast notifications scroll downwards. Overrides respective option in default Thaumcraft config.");
/*     */ 
/*     */     
/* 203 */     this; runicRechargeSpeed = config.getInt("runicRechargeSpeed", "Thaumcraft Overrides", 750, 0, 32768, "How many milliseconds pass between Runic Shield recharge ticks. Setting this value lower than 50 is not recommended. Overrides respective option in default Thaumcraft config.");
/*     */ 
/*     */     
/* 206 */     this; runicRechargeDelay = config.getInt("runicRechargeDelay", "Thaumcraft Overrides", 40, 0, 32768, "How many game ticks pass after Runic Shield has been reduced to zero before it can start recharging again. Overrides respective option in default Thaumcraft config.");
/*     */ 
/*     */     
/* 209 */     this; runicCost = config.getInt("runicCost", "Thaumcraft Overrides", 10, 0, 32768, "How much Aer and Terra centi-vis (0.01 vis) it costs to reacharge a single unit of Runic Shield. Overrides respective option in default Thaumcraft config.");
/*     */ 
/*     */     
/* 212 */     this; advancedMiningCharmReach = config.getFloat("advancedMiningCharmReach", "Generic Config", 4.0F, 0.0F, 32.0F, "Block reach increase for Ethereal Mining Charm.");
/*     */ 
/*     */     
/* 215 */     this; miningCharmReach = config.getFloat("miningCharmReach", "Generic Config", 2.0F, 0.0F, 32.0F, "Block reach increase for Mining Charm.");
/*     */ 
/*     */     
/* 218 */     this; advancedMiningCharmBoost = config.getFloat("advancedMiningCharmBoost", "Generic Config", 3.0F, 0.0F, 32000.0F, "Mining speed boost for Ethereal Mining Charm. 3.0 means that it is boosted by 300%.");
/*     */ 
/*     */     
/* 221 */     this; miningCharmBoost = config.getFloat("miningCharmBoost", "Generic Config", 1.0F, 0.0F, 32000.0F, "Mining speed boost for Mining Charm. 1.0 means that it is boosted by 100%.");
/*     */ 
/*     */     
/* 224 */     this; nebulousCoreDodgeChance = config.getFloat("nebulousCoreDodgeChance", "Generic Config", 0.4F, 0.0F, 1.0F, "Chance to dodge attack by teleporting player from it for Nebulous Core. 1.0 equals 100% chance, 0.0 - 0%.");
/*     */ 
/*     */     
/* 227 */     this; ancientAegisDamageReduction = config.getFloat("ancientAegisDamageReduction", "Generic Config", 0.25F, 0.0F, 1.0F, "Damage Reduction for Ancient Aegis. 1.0 equals 100% reduction, 0.0 - 0%.");
/*     */ 
/*     */     
/* 230 */     this; deificAmuletEffectImmunity = config.getBoolean("deificAmuletEffectImmunity", "Generic Config", true, "Whether or not Deific Amulet should provide immunity to status effects. Note, that it includes buffs as well as debuffs.");
/*     */ 
/*     */     
/* 233 */     this; deificAmuletInvincibility = config.getBoolean("deificAmuletInvincibility", "Generic Config", true, "Whether or not Deific Amulet should provide prolonged invincibility frames.");
/*     */ 
/*     */     
/* 236 */     this; darkSunRingDeflectChance = config.getFloat("darkSunRingDeflectChance", "Generic Config", 0.2F, 0.0F, 1.0F, "Chance to deflect enemy's attack back to it, while wearing Ring of The Seven Suns. 1.0 equals 100% chance, 0.0 - 0%.");
/*     */ 
/*     */     
/* 239 */     this; darkSunRingDamageCap = config.getFloat("darkSunRingDamageCap", "Generic Config", 100.0F, 0.0F, 32768.0F, "Damage cap for Ring of The Seven Suns. Any attacks that exceed this amount of damage will be completely negated while wearing it.");
/*     */ 
/*     */     
/* 242 */     this; darkSunRingHealLimit = config.getBoolean("darkSunRingHealLimit", "Generic Config", false, "Enables the cooldown on Ring of The Seven Sun's healing effect, so standing in fire or lava wouldn't replenish your health insanely fast. WARNING: This config option is experimental, only touch it if you really want this.");
/*     */ 
/*     */     
/* 245 */     this; interdimensionalMirror = config.getBoolean("interdimensionalMirror", "Generic Config", true, "Whether or not Dimensional Mirror should be capable of teleporting player across dimensions. If this is set to false, player must reside in the dimension of saved location in order to teleport to it.");
/*     */ 
/*     */     
/* 248 */     this; shinyStoneCheckrate = config.getInt("shinyStoneCheckrate", "Generic Config", 4, 1, 2048, "Checkrate for Shiny Stone effects. The greater it is, the less frequently health regen would happen, and the more time acceleration would take. WARNING: This config option is experimental, only touch it if you really want this.");
/*     */ 
/*     */     
/* 251 */     this; obeliskDrainerVisMult = config.getFloat("obeliskDrainerVisGen", "Generic Config", 1.0F, 0.0F, 32000.0F, "Vis production multiplier for Devourer of The Void.");
/*     */ 
/*     */     
/* 254 */     this; arcanumGenRate = config.getFloat("arcanumGenRate", "Generic Config", 1.0F, 0.0F, 32000.0F, "Multiplier for Vis generation rate of Nebulous Core.");
/*     */ 
/*     */     
/* 257 */     this; soulTomeDivisor = config.getFloat("soulTomeDivisor", "Generic Config", 10.0F, 0.0F, Float.POSITIVE_INFINITY, "Divisor, used during damage calculations by Edict of a Thousand Damned Souls. Setting this value to 10 basically means that most of the time it will drain 1/10 of entity's max health per attack.");
/*     */ 
/*     */     
/* 260 */     this; falseJusticeEnabled = config.getBoolean("falseJusticeEnabled", "Generic Config", true, "Whether or not False Justice should be enabled. Note that it will only remove respective research, so it would be impossible to create this relic legally - it won't remove existing copies from world or prevent it's spawning from Creative Mode.");
/*     */ 
/*     */     
/* 263 */     this; damageThunderpealDirect = config.getFloat("damageThunderpealDirect", "Damage Values", 24.0F, 0.0F, 32000.0F, "How much damage inflicts direct hit of Thunderpeal's electrical orbs.");
/*     */ 
/*     */     
/* 266 */     this; damageThunderpealBolt = config.getFloat("damageThunderpealBolt", "Damage Values", 16.0F, 0.0F, 32000.0F, "How much damage deal lightning bolts of Thunderpeal's electrical orbs.");
/*     */ 
/*     */     
/* 269 */     this; damageApotheosisDirect = config.getFloat("damageApotheosisDirect", "Damage Values", 100.0F, 0.0F, 32000.0F, "How much damage inflicts direct hit of Babylon Weapons, summoned by Apotheosis.");
/*     */ 
/*     */     
/* 272 */     this; damageApotheosisImpact = config.getFloat("damageApotheosisImpact", "Damage Values", 75.0F, 0.0F, 32000.0F, "How much damage receive entities within impact zone of Babylon Weapons, summoned by Apotheosis.");
/*     */ 
/*     */     
/* 275 */     this; damageLunarFlareDirect = config.getFloat("damageLunarFlareDirect", "Damage Values", 72.0F, 0.0F, 32000.0F, "How much damage inflicts direct hit of Lunar Flare.");
/*     */ 
/*     */     
/* 278 */     this; damageLunarFlareImpact = config.getFloat("damageLunarFlareImpact", "Damage Values", 40.0F, 0.0F, 32000.0F, "How much damage receive entities within impact zone of Lunar Flare.");
/*     */ 
/*     */     
/* 281 */     this; paradoxDamageCap = config.getFloat("paradoxDamageCap", "Damage Values", 200.0F, 0.0F, 32000.0F, "Maximum damage The Paradox can deal. Damage dealt varies between 1 and this value for each hit.");
/*     */ 
/*     */     
/* 284 */     this; telekinesisTomeDamageMIN = config.getFloat("telekinesisTomeDamageMIN", "Damage Values", 16.0F, 0.0F, 32000.0F, "Minimal damage that can be dealt by lightning attack of Tome of Predestiny.");
/*     */ 
/*     */     
/* 287 */     this; telekinesisTomeDamageMAX = config.getFloat("telekinesisTomeDamageMAX", "Damage Values", 40.0F, 0.0F, 32000.0F, "Maximal damage that can be dealt by lightning attack of Tome of Predestiny.");
/*     */ 
/*     */     
/* 290 */     this; nuclearFuryDamageMIN = config.getFloat("nuclearFuryDamageMIN", "Damage Values", 24.0F, 0.0F, 32000.0F, "Minimal damage that can be dealt by charges of Nuclear Fury.");
/*     */ 
/*     */     
/* 293 */     this; nuclearFuryDamageMAX = config.getFloat("nuclearFuryDamageMAX", "Damage Values", 32.0F, 0.0F, 32000.0F, "Maximal damage that can be dealt by charges of Nuclear Fury.");
/*     */ 
/*     */     
/* 296 */     this; crimsonSpellDamageMIN = config.getFloat("crimsonSpellDamageMIN", "Damage Values", 42.0F, 0.0F, 32000.0F, "Minimal damage that can be dealt by projectiles of Crimson Spell.");
/*     */ 
/*     */     
/* 299 */     this; crimsonSpellDamageMAX = config.getFloat("crimsonSpellDamageMAX", "Damage Values", 100.0F, 0.0F, 32000.0F, "Maximal damage that can be dealt by projectiles of Crimson Spell.");
/*     */ 
/*     */     
/* 302 */     this; chaosTomeDamageCap = config.getFloat("chaosTomeDamageCap", "Damage Values", 100.0F, 0.0F, 32000.0F, "Maximum damage that projectile of Tome of Primal Chaos can deal. Damage dealt varies between 1 and this value for each hit.");
/*     */ 
/*     */     
/* 305 */     this; eldritchSpellDamage = config.getFloat("eldritchSpellDamage", "Damage Values", 32.5F, 0.0F, 32000.0F, "Default damage dealt by projectiles of Eldritch Spell.");
/*     */ 
/*     */     
/* 308 */     this; eldritchSpellDamageEx = config.getFloat("eldritchSpellDamageEx", "Damage Values", 100.0F, 0.0F, 32000.0F, "Damage dealt by projectiles of Eldritch Spell, while it is used in Outer Lands.");
/*     */ 
/*     */     
/* 311 */     this; apotheosisVisMult = config.getFloat("apotheosisVisMult", "Vis Costs", 1.0F, 0.0F, 1024.0F, "Vis cost multiplier for Apotheosis.");
/*     */ 
/*     */     
/* 314 */     this; chaosTomeVisMult = config.getFloat("chaosTomeVisMult", "Vis Costs", 1.0F, 0.0F, 1024.0F, "Vis cost multiplier for Tome of Primal Chaos.");
/*     */ 
/*     */     
/* 317 */     this; crimsonSpellVisMult = config.getFloat("crimsonSpellVisMult", "Vis Costs", 1.0F, 0.0F, 1024.0F, "Vis cost multiplier for Crimson Spell.");
/*     */ 
/*     */     
/* 320 */     this; deificAmuletVisMult = config.getFloat("deificAmuletVisCost", "Vis Costs", 1.0F, 0.0F, 1024.0F, "Vis cost multiplier for Deific Amulet.");
/*     */ 
/*     */     
/* 323 */     this; discordTomeVisMult = config.getFloat("discordTomeVisCost", "Vis Costs", 1.0F, 0.0F, 1024.0F, "Vis cost multiplier for Tome of Discord.");
/*     */ 
/*     */     
/* 326 */     this; dormantArcanumVisMult = config.getFloat("dormantArcanumVisMult", "Vis Costs", 1.0F, 0.0F, 1024.0F, "Vis cost multiplier for Dormant Nebulous Core (applies in the moment of transormation; final amount of Vis required for re-awakening depends on this.)");
/*     */ 
/*     */     
/* 329 */     this; eldritchSpellVisMult = config.getFloat("eldritchSpellVisCost", "Vis Costs", 1.0F, 0.0F, 1024.0F, "Vis cost multiplier for Eldritch Spell.");
/*     */ 
/*     */     
/* 332 */     this; fateTomeVisMult = config.getFloat("fateTomeVisCost", "Vis Costs", 1.0F, 0.0F, 1024.0F, "Vis cost multiplier for Tome of Broken Fates.");
/*     */ 
/*     */     
/* 335 */     this; lunarFlaresVisMult = config.getFloat("lunarFlaresVisCost", "Vis Costs", 1.0F, 0.0F, 1024.0F, "Vis cost multiplier for Tome of Lunar Flares.");
/*     */ 
/*     */     
/* 338 */     this; nuclearFuryVisMult = config.getFloat("nuclearFuryVisCost", "Vis Costs", 1.0F, 0.0F, 1024.0F, "Vis cost multiplier for Nuclear Fury.");
/*     */ 
/*     */     
/* 341 */     this; oblivionAmuletVisMult = config.getFloat("oblivionAmuletVisCost", "Vis Costs", 1.0F, 0.0F, 1024.0F, "Vis cost multiplier for Amulet of The Oblivion.");
/*     */ 
/*     */     
/* 344 */     this; soulTomeVisMult = config.getFloat("soulTomeVisCost", "Vis Costs", 1.0F, 0.0F, 1024.0F, "Vis cost multiplier for Edict of a Thousand Damned Souls.");
/*     */ 
/*     */     
/* 347 */     this; telekinesisTomeVisMult = config.getFloat("telekinesisTomeVisCost", "Vis Costs", 1.0F, 0.0F, 1024.0F, "Vis cost multiplier for Tome of Predestiny.");
/*     */ 
/*     */     
/* 350 */     this; weatherStoneVisMult = config.getFloat("weatherStoneVisCost", "Vis Costs", 1.0F, 0.0F, 1024.0F, "Vis cost multiplier for Runic Stone.");
/*     */ 
/*     */     
/* 353 */     this; thunderpealVisMult = config.getFloat("thunderpealVisCost", "Vis Costs", 1.0F, 0.0F, 1024.0F, "Vis cost multiplier for Thunderpeal.");
/*     */ 
/*     */     
/* 356 */     this; overthrowerVisMult = config.getFloat("overthrowerVisCost", "Vis Costs", 1.0F, 0.0F, 1024.0F, "Vis cost multiplier for Edict of Eternal Banishment.");
/*     */ 
/*     */     
/* 359 */     this; voidGrimoireVisMult = config.getFloat("voidGrimoireVisMult", "Vis Costs", 1.0F, 0.0F, 1024.0F, "Vis cost multiplier for Grimoire of The Abyss.");
/*     */ 
/*     */     
/* 362 */     config.save();
/*     */ 
/*     */     
/* 365 */     this; this; researchInspectionFrequency = (int)(600.0F / revelationModifier);
/* 366 */     this; this; knowledgeChance = 0.1D * revelationModifier;
/*     */   }
/*     */ }


/* Location:              C:\Users\antro\Documents\forgottenRelicsDeobfuscated.jar!\com\integral\forgottenrelics\handlers\RelicsConfigHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */