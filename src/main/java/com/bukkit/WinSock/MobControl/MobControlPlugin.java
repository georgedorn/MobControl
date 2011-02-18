package com.bukkit.WinSock.MobControl;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

import net.minecraft.server.EntityHuman;
import net.minecraft.server.EntityLiving;
import net.minecraft.server.EntityPlayer;

import org.bukkit.Server;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.entity.CraftEntity;
import org.bukkit.entity.Animals;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Boat;
import org.bukkit.entity.Chicken;
import org.bukkit.entity.Cow;
import org.bukkit.entity.Creature;
import org.bukkit.entity.CreatureType;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.Egg;
import org.bukkit.entity.Entity;
import org.bukkit.entity.FallingSand;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Fish;
import org.bukkit.entity.Flying;
import org.bukkit.entity.Ghast;
import org.bukkit.entity.Giant;
import org.bukkit.entity.Item;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Minecart;
import org.bukkit.entity.MobType;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Painting;
import org.bukkit.entity.Pig;
import org.bukkit.entity.PigZombie;
import org.bukkit.entity.Player;
import org.bukkit.entity.Sheep;
import org.bukkit.entity.Skeleton;
import org.bukkit.entity.Slime;
import org.bukkit.entity.Snowball;
import org.bukkit.entity.Spider;
import org.bukkit.entity.Squid;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.entity.WaterMob;
import org.bukkit.entity.Zombie;
import org.bukkit.event.Event;
import org.bukkit.event.Event.Priority;
import org.bukkit.event.Event.Type;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginLoader;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.config.Configuration;

import com.bukkit.WinSock.MobControl.Listeners.MobControlEntityListener;

public class MobControlPlugin extends JavaPlugin {

	MobControlEntityListener entityListener = new MobControlEntityListener(this);
	
	Logger log = null;
	private PluginDescriptionFile pdfFile = this.getDescription();

	public MobControlPlugin(PluginLoader pluginLoader, Server instance,
			PluginDescriptionFile desc, File folder, File plugin,
			ClassLoader cLoader) {
		super(pluginLoader, instance, desc, folder, plugin, cLoader);
		// TODO Auto-generated constructor stub
	}

	public void onDisable() {
		log.info("[" + pdfFile.getName() + "] Version "
				+ pdfFile.getVersion()
				+ " is disabled!");
	}

	public boolean shouldTarget(Entity e, String nodeData, boolean attacked) {
		if (nodeData != null) {
			if (nodeData.equalsIgnoreCase("Passive")) {
				return false;
			} else if (nodeData.equalsIgnoreCase("Neutral")) {
				if (attacked) {
					return true;
				} else {
					return false;
				}
			} else if (nodeData.equalsIgnoreCase("Aggressive")) {
				return true;
			}
		}
		if (e instanceof Monster) {
			return true;
		} else {
			return false;
		}
	}

	public CreatureType getCreatureType(Entity entity) {
		if (entity instanceof LivingEntity) {
			if (entity instanceof Creature) {
				// Animals
				if (entity instanceof Animals) {
					if (entity instanceof Chicken) {
						return CreatureType.CHICKEN;
					} else if (entity instanceof Cow) {
						return CreatureType.COW;
					} else if (entity instanceof Pig) {
						return CreatureType.PIG;
					} else if (entity instanceof Sheep) {
						return CreatureType.SHEEP;
					}
				}
				// Monsters
				else if (entity instanceof Monster) {
					if (entity instanceof Zombie) {
						if (entity instanceof PigZombie) {
							return CreatureType.PIG_ZOMBIE;
						}
					} else if (entity instanceof Creeper) {
						return CreatureType.CREEPER;
					} else if (entity instanceof Giant) { /* No Giant in Creature */
					} else if (entity instanceof Skeleton) {
						return CreatureType.SKELETON;
					} else if (entity instanceof Spider) {
						return CreatureType.SPIDER;
					} else if (entity instanceof Slime) { /* Slime in Creature */
					}
				}
				// Water Animals
				else if (entity instanceof WaterMob) {
					if (entity instanceof Squid) {
						return CreatureType.SQUID;
					}
				}
			}
			// Flying
			else if (entity instanceof Flying) {
				if (entity instanceof Ghast) {
					return CreatureType.GHAST;
				}
			}
		}
		return null;
	}

	public void onEnable() {
		log = Logger.getLogger("Minecraft");
		
		// Load config file
		Configuration config = this.getConfiguration();
		loadSettings(config);

		// Register our events
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvent(Type.CREATURE_SPAWN, entityListener, Priority.Highest,
				this);
		pm.registerEvent(Type.ENTITY_COMBUST, entityListener, Priority.Highest,
				this);
		pm.registerEvent(Type.ENTITY_TARGET, entityListener, Priority.Highest,
				this);
		pm.registerEvent(Type.ENTITY_DEATH, entityListener, Priority.Highest,
				this);
		pm.registerEvent(Type.ENTITY_DAMAGEDBY_ENTITY, entityListener,
				Priority.Highest, this);

		// Scheduler
		getServer().getScheduler().scheduleSyncRepeatingTask(this,
				new Runnable() {

					public void run() {
						if (getServer().getOnlinePlayers().length > 0) {
							for (World w : getServer().getWorlds()) {
								for (Entity e : w.getEntities()) {
									if (e instanceof Creature) {
										if (getCreatureType(e) != null) {
											String burnNode = "MobControl.Mobs."
													+ getCreatureType(e)
															.getName()
															.toUpperCase()
													+ ".Day.Burn";
											if (getConfiguration().getBoolean(
													burnNode, false)) {
												if (w.getTime() < 12000
														|| w.getTime() == 24000) {
													if (e.getLocation()
															.getWorld()
															.getBlockAt(
																	e.getLocation()
																			.getBlockX(),
																	e.getLocation()
																			.getBlockY() + 1,
																	e.getLocation()
																			.getBlockZ())
															.getLightLevel() > 7) {
														CraftEntity ce = (CraftEntity) e;
														ce.getHandle().fireTicks = 20;
														ce.getHandle().maxFireTicks = 220;
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}, 0L, 10L);
		log.info("[" + pdfFile.getName() + "] Version "
				+ pdfFile.getVersion()
				+ " is enabled!");
	}

	public void loadSettings(Configuration config) {
		File yml = new File(getDataFolder() + "/config.yml");
		if (!this.getDataFolder().exists()) {
			this.getDataFolder().mkdirs();
		}
		if (!yml.exists()) {
			try {
				yml.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}

			// Default Settings
			config.setProperty("MobControl.Mobs.PIG.Enabled", true);
			config.setProperty("MobControl.Mobs.PIG.Day.Nature", "Passive");
			config.setProperty("MobControl.Mobs.PIG.Day.Burn", false);
			config.setProperty("MobControl.Mobs.PIG.Night.Nature", "Passive");

			config.setProperty("MobControl.Mobs.COW.Enabled", true);
			config.setProperty("MobControl.Mobs.COW.Day.Nature", "Passive");
			config.setProperty("MobControl.Mobs.COW.Day.Burn", false);
			config.setProperty("MobControl.Mobs.COW.Night.Nature", "Passive");

			config.setProperty("MobControl.Mobs.SHEEP.Enabled", true);
			config.setProperty("MobControl.Mobs.SHEEP.Day.Nature", "Passive");
			config.setProperty("MobControl.Mobs.SHEEP.Day.Burn", false);
			config.setProperty("MobControl.Mobs.SHEEP.Night.Nature", "Passive");

			config.setProperty("MobControl.Mobs.SHEEP.Enabled", true);
			config.setProperty("MobControl.Mobs.SHEEP.Day.Nature", "Passive");
			config.setProperty("MobControl.Mobs.SHEEP.Day.Burn", false);
			config.setProperty("MobControl.Mobs.SHEEP.Night.Nature", "Passive");

			config.setProperty("MobControl.Mobs.CHICKEN.Enabled", true);
			config.setProperty("MobControl.Mobs.CHICKEN.Day.Nature", "Passive");
			config.setProperty("MobControl.Mobs.CHICKEN.Day.Burn", false);
			config.setProperty("MobControl.Mobs.CHICKEN.Night.Nature",
					"Passive");

			config.setProperty("MobControl.Mobs.SQUID.Enabled", true);
			config.setProperty("MobControl.Mobs.SQUID.Day.Nature", "Passive");
			config.setProperty("MobControl.Mobs.SQUID.Day.Burn", false);
			config.setProperty("MobControl.Mobs.SQUID.Night.Nature", "Passive");

			config.setProperty("MobControl.Mobs.PIG_ZOMBIE.Enabled", true);
			config.setProperty("MobControl.Mobs.PIG_ZOMBIE.Day.Nature",
					"Neutral");
			config.setProperty("MobControl.Mobs.PIG_ZOMBIE.Day.Burn", false);
			config.setProperty("MobControl.Mobs.PIG_ZOMBIE.Night.Nature",
					"Neutral");

			config.setProperty("MobControl.Mobs.SPIDER.Enabled", true);
			config.setProperty("MobControl.Mobs.SPIDER.Day.Nature", "Neutral");
			config.setProperty("MobControl.Mobs.SPIDER.Day.Burn", false);
			config.setProperty("MobControl.Mobs.SPIDER.Night.Nature",
					"Aggressive");

			config.setProperty("MobControl.Mobs.ZOMBIE.Enabled", true);
			config.setProperty("MobControl.Mobs.ZOMBIE.Day.Nature",
					"Aggressive");
			config.setProperty("MobControl.Mobs.ZOMBIE.Day.Burn", true);
			config.setProperty("MobControl.Mobs.ZOMBIE.Night.Nature",
					"Aggressive");

			config.setProperty("MobControl.Mobs.SKELETON.Enabled", true);
			config.setProperty("MobControl.Mobs.SKELETON.Day.Nature",
					"Aggressive");
			config.setProperty("MobControl.Mobs.SKELETON.Day.Burn", true);
			config.setProperty("MobControl.Mobs.SKELETON.Night.Nature",
					"Aggressive");

			config.setProperty("MobControl.Mobs.CREEPER.Enabled", true);
			config.setProperty("MobControl.Mobs.CREEPER.Day.Nature",
					"Aggressive");
			config.setProperty("MobControl.Mobs.CREEPER.Day.Burn", false);
			config.setProperty("MobControl.Mobs.CREEPER.Night.Nature",
					"Aggressive");

			config.setProperty("MobControl.Mobs.SLIME.Enabled", true);
			config.setProperty("MobControl.Mobs.SLIME.Day.Nature", "Aggressive");
			config.setProperty("MobControl.Mobs.SLIME.Day.Burn", false);
			config.setProperty("MobControl.Mobs.SLIME.Night.Nature",
					"Aggressive");

			config.setProperty("MobControl.Mobs.GHAST.Enabled", true);
			config.setProperty("MobControl.Mobs.GHAST.Day.Nature", "Aggressive");
			config.setProperty("MobControl.Mobs.GHAST.Day.Burn", false);
			config.setProperty("MobControl.Mobs.GHAST.Night.Nature",
					"Aggressive");
			config.save();
		}
	}

	public MobType findType(String mob) {
		for (MobType mobtype : MobType.values()) {
			if (mobtype.name().equalsIgnoreCase(mob))
				return mobtype;
			else if (mobtype.name().replaceAll("_", "").equalsIgnoreCase(mob))
				return mobtype;
		}
		return null;
	}

}