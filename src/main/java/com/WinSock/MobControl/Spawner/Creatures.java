package com.WinSock.MobControl.Spawner;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.bukkit.Material;
import org.bukkit.entity.CreatureType;

import com.WinSock.MobControl.Spawner.CreatureInfo.CreatureNature;
import com.WinSock.MobControl.Spawner.CreatureInfo.SpawnTime;

public class Creatures {
	
	// Passive
	private CreatureInfo pig = new CreatureInfo();
	private CreatureInfo chicken = new CreatureInfo();
	private CreatureInfo cow = new CreatureInfo();
	private CreatureInfo squid = new CreatureInfo();
	private CreatureInfo sheep = new CreatureInfo();
	
	// Neutral
	private CreatureInfo pigZombie = new CreatureInfo();
	
	// Aggressive
	private CreatureInfo spider = new CreatureInfo();
	private CreatureInfo zombie = new CreatureInfo();
	private CreatureInfo skeleton = new CreatureInfo();
	private CreatureInfo creeper = new CreatureInfo();
	private CreatureInfo slime = new CreatureInfo();
	private CreatureInfo ghast = new CreatureInfo();
	
	// Global Settings
	private int maxHostile = 200;
	private int maxNeutral = 15;
	private int distanceFromPlayer = 24;
	private int spawnDelay = 1;
	
	private Set<CreatureInfo> creatureList = new HashSet<CreatureInfo>();
	
	public Creatures()
	{
		// pig
		pig.setEnabled(true);
		pig.setAttackDamage(0);
		pig.setBurn(false);
		pig.setCreature(CreatureType.PIG);
		pig.setHealth(5);
		pig.setMaxLight(15);
		pig.setMaxSpawnHeight(0);
		pig.setMinLight(9);
		pig.setMinSpawnHeight(0);
		pig.setNatureDay(CreatureNature.PASSIVE);
		pig.setNatureNight(CreatureNature.PASSIVE);
		pig.addSpawnBlocks(Material.values());
		pig.setSpawnRate(70);
		pig.setSpawnRoom(2);
		pig.setSpawnTime(SpawnTime.BOTH);
		creatureList.add(pig);
		
		// chicken
		chicken.setEnabled(true);
		chicken.setAttackDamage(0);
		chicken.setBurn(false);
		chicken.setCreature(CreatureType.CHICKEN);
		chicken.setHealth(5);
		chicken.setMaxLight(15);
		chicken.setMaxSpawnHeight(0);
		chicken.setMinLight(9);
		chicken.setMinSpawnHeight(0);
		chicken.setNatureDay(CreatureNature.PASSIVE);
		chicken.setNatureNight(CreatureNature.PASSIVE);
		pig.addSpawnBlocks(Material.values());
		chicken.setSpawnRate(70);
		chicken.setSpawnRoom(2);
		chicken.setSpawnTime(SpawnTime.BOTH);
		creatureList.add(chicken);
		
		// cow
		cow.setEnabled(true);
		cow.setAttackDamage(0);
		cow.setBurn(false);
		cow.setCreature(CreatureType.COW);
		cow.setHealth(5);
		cow.setMaxLight(15);
		cow.setMaxSpawnHeight(0);
		cow.setMinLight(9);
		cow.setMinSpawnHeight(0);
		cow.setNatureDay(CreatureNature.PASSIVE);
		cow.setNatureNight(CreatureNature.PASSIVE);
		pig.addSpawnBlocks(Material.values());
		cow.setSpawnRate(70);
		cow.setSpawnRoom(2);
		cow.setSpawnTime(SpawnTime.BOTH);
		creatureList.add(cow);
		
		// squid
		squid.setEnabled(true);
		squid.setAttackDamage(0);
		squid.setBurn(false);
		squid.setCreature(CreatureType.SQUID);
		squid.setHealth(5);
		squid.setMaxLight(15);
		squid.setMaxSpawnHeight(0);
		squid.setMinLight(9);
		squid.setMinSpawnHeight(0);
		squid.setNatureDay(CreatureNature.PASSIVE);
		squid.setNatureNight(CreatureNature.PASSIVE);
		pig.addSpawnBlocks(Material.values());
		squid.setSpawnRate(70);
		squid.setSpawnRoom(2);
		squid.setSpawnTime(SpawnTime.BOTH);
		creatureList.add(squid);
		
		// sheep
		sheep.setEnabled(true);
		sheep.setAttackDamage(0);
		sheep.setBurn(false);
		sheep.setCreature(CreatureType.SHEEP);
		sheep.setHealth(5);
		sheep.setMaxLight(15);
		sheep.setMaxSpawnHeight(0);
		sheep.setMinLight(9);
		sheep.setMinSpawnHeight(0);
		sheep.setNatureDay(CreatureNature.PASSIVE);
		sheep.setNatureNight(CreatureNature.PASSIVE);
		pig.addSpawnBlocks(Material.values());
		sheep.setSpawnRate(70);
		sheep.setSpawnRoom(2);
		sheep.setSpawnTime(SpawnTime.BOTH);
		creatureList.add(sheep);
		
		// pigzombie
		pigZombie.setEnabled(true);
		pigZombie.setAttackDamage(0);
		pigZombie.setBurn(false);
		pigZombie.setCreature(CreatureType.PIG_ZOMBIE);
		pigZombie.setHealth(5);
		pigZombie.setMaxLight(15);
		pigZombie.setMaxSpawnHeight(0);
		pigZombie.setMinLight(0);
		pigZombie.setMinSpawnHeight(0);
		pigZombie.setNatureDay(CreatureNature.NEUTRAL);
		pigZombie.setNatureNight(CreatureNature.NEUTRAL);
		pig.addSpawnBlocks(Material.values());
		pigZombie.setSpawnRate(70);
		pigZombie.setSpawnRoom(2);
		pigZombie.setSpawnTime(SpawnTime.BOTH);
		creatureList.add(pigZombie);
		
		// spider
		spider.setEnabled(true);
		spider.setAttackDamage(0);
		spider.setBurn(false);
		spider.setCreature(CreatureType.SPIDER);
		spider.setHealth(5);
		spider.setMaxLight(7);
		spider.setMaxSpawnHeight(0);
		spider.setMinLight(0);
		spider.setMinSpawnHeight(0);
		spider.setNatureDay(CreatureNature.NEUTRAL);
		spider.setNatureNight(CreatureNature.AGGRESSIVE);
		pig.addSpawnBlocks(Material.values());
		spider.setSpawnRate(70);
		spider.setSpawnRoom(2);
		spider.setSpawnTime(SpawnTime.BOTH);
		creatureList.add(spider);
		
		// zombie
		zombie.setEnabled(true);
		zombie.setAttackDamage(0);
		zombie.setBurn(false);
		zombie.setCreature(CreatureType.ZOMBIE);
		zombie.setHealth(5);
		zombie.setMaxLight(7);
		zombie.setMaxSpawnHeight(0);
		zombie.setMinLight(0);
		zombie.setMinSpawnHeight(0);
		zombie.setNatureDay(CreatureNature.AGGRESSIVE);
		zombie.setNatureNight(CreatureNature.AGGRESSIVE);
		pig.addSpawnBlocks(Material.values());
		zombie.setSpawnRate(70);
		zombie.setSpawnRoom(2);
		zombie.setSpawnTime(SpawnTime.BOTH);
		creatureList.add(zombie);
		
		// skeleton
		skeleton.setEnabled(true);
		skeleton.setAttackDamage(0);
		skeleton.setBurn(false);
		skeleton.setCreature(CreatureType.SKELETON);
		skeleton.setHealth(5);
		skeleton.setMaxLight(7);
		skeleton.setMaxSpawnHeight(0);
		skeleton.setMinLight(0);
		skeleton.setMinSpawnHeight(0);
		skeleton.setNatureDay(CreatureNature.AGGRESSIVE);
		skeleton.setNatureNight(CreatureNature.AGGRESSIVE);
		pig.addSpawnBlocks(Material.values());
		skeleton.setSpawnRate(70);
		skeleton.setSpawnRoom(2);
		skeleton.setSpawnTime(SpawnTime.BOTH);
		creatureList.add(skeleton);
		
		// creeper
		creeper.setEnabled(true);
		creeper.setAttackDamage(0);
		creeper.setBurn(false);
		creeper.setCreature(CreatureType.CREEPER);
		creeper.setHealth(5);
		creeper.setMaxLight(7);
		creeper.setMaxSpawnHeight(0);
		creeper.setMinLight(0);
		creeper.setMinSpawnHeight(0);
		creeper.setNatureDay(CreatureNature.AGGRESSIVE);
		creeper.setNatureNight(CreatureNature.AGGRESSIVE);
		pig.addSpawnBlocks(Material.values());
		creeper.setSpawnRate(70);
		creeper.setSpawnRoom(2);
		creeper.setSpawnTime(SpawnTime.BOTH);
		creatureList.add(creeper);
		
		// slime
		slime.setEnabled(true);
		slime.setAttackDamage(0);
		slime.setBurn(false);
		slime.setCreature(CreatureType.SLIME);
		slime.setHealth(5);
		slime.setMaxLight(15);
		slime.setMaxSpawnHeight(16);
		slime.setMinLight(0);
		slime.setMinSpawnHeight(0);
		slime.setNatureDay(CreatureNature.AGGRESSIVE);
		slime.setNatureNight(CreatureNature.AGGRESSIVE);
		pig.addSpawnBlocks(Material.values());
		slime.setSpawnRate(70);
		slime.setSpawnRoom(2);
		slime.setSpawnTime(SpawnTime.BOTH);
		creatureList.add(slime);
		
		// ghast
		ghast.setEnabled(true);
		ghast.setAttackDamage(0);
		ghast.setBurn(false);
		ghast.setCreature(CreatureType.GHAST);
		ghast.setHealth(5);
		ghast.setMaxLight(15);
		ghast.setMaxSpawnHeight(0);
		ghast.setMinLight(0);
		ghast.setMinSpawnHeight(0);
		ghast.setNatureDay(CreatureNature.AGGRESSIVE);
		ghast.setNatureNight(CreatureNature.AGGRESSIVE);
		pig.addSpawnBlocks(Material.values());
		ghast.setSpawnRate(70);
		ghast.setSpawnRoom(2);
		ghast.setSpawnTime(SpawnTime.BOTH);
		creatureList.add(ghast);
	}
	
	public void setMaxHostile(int maxHostile) {
		this.maxHostile = maxHostile;
	}

	public int getMaxHostile() {
		return maxHostile;
	}

	public void setMaxNeutral(int maxNeutral) {
		this.maxNeutral = maxNeutral;
	}

	public int getMaxNeutral() {
		return maxNeutral;
	}

	public void setDistanceFromPlayer(int distanceFromPlayer) {
		this.distanceFromPlayer = distanceFromPlayer;
	}

	public int getDistanceFromPlayer() {
		return distanceFromPlayer;
	}
	
	public List<CreatureInfo> getEnabled()
	{
		List<CreatureInfo> returnData = new ArrayList<CreatureInfo>();
		for(CreatureInfo i : creatureList)
		{
			if (i.isEnabled())
			{
				returnData.add(i);
			}
		}
		
		return returnData;
	}

	public List<CreatureInfo> getCreatures(SpawnTime t, CreatureNature n)
	{
		List<CreatureInfo> returnData = new ArrayList<CreatureInfo>();
		for(CreatureInfo i : creatureList)
		{
			if (i.isEnabled())
			{
				if (t == SpawnTime.DAY)
				{
					if (i.getNatureDay() == n)
					{
						returnData.add(i);
					}
				}
				else if (t == SpawnTime.NIGHT)
				{
					if (i.getNatureNight() == n)
					{
						returnData.add(i);
					}
				}
			}
		}
		
		return returnData;
	}

	public void setSpawnDelay(int spawnDelay) {
		this.spawnDelay = spawnDelay;
	}

	public int getSpawnDelay() {
		return spawnDelay;
	}
}
