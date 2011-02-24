package com.WinSock.MobControl.Spawner;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.bukkit.Material;
import org.bukkit.entity.CreatureType;

public class CreatureInfo {
	
	public enum CreatureNature { PASSIVE, NEUTRAL, AGGRESSIVE }
	public enum SpawnTime { DAY, NIGHT, BOTH }
	
	private CreatureType creature;
	private Set<Material> spawnBlocks = new HashSet<Material>();
	private int minLight, maxLight;
	private int spawnRate;
	private int minSpawnHeight;
	private int maxSpawnHeight;
	private int spawnRoom;
	private boolean burn;
	private CreatureNature natureDay;
	private CreatureNature natureNight;
	private int health;
	private int attackDamage;
	private SpawnTime spawnTime;
	private boolean enabled;
	
	public void setCreature(CreatureType creature) {
		this.creature = creature;
	}
	public CreatureType getCreature() {
		return creature;
	}
	public void setSpawnBlocks(Set<Material> spawnBlocks) {
		this.spawnBlocks = spawnBlocks;
	}
	public void addSpawnBlocks(Material[] spawnBlocks) {
		this.spawnBlocks.addAll(Arrays.asList(spawnBlocks));
	}
	public void addSpawnBlock(Material spawnBlock) {
		this.spawnBlocks.add(spawnBlock);
	}
	public Set<Material> getSpawnBlocks() {
		return spawnBlocks;
	}
	public void setMaxLight(int maxLight) {
		this.maxLight = maxLight;
	}
	public int getMaxLight() {
		return maxLight;
	}
	public void setMinLight(int minLight) {
		this.minLight = minLight;
	}
	public int getMinLight() {
		return minLight;
	}
	public void setSpawnRate(int spawnRate) {
		this.spawnRate = spawnRate;
	}
	public int getSpawnRate() {
		return spawnRate;
	}
	public void setMinSpawnHeight(int minSpawnHeight) {
		this.minSpawnHeight = minSpawnHeight;
	}
	public int getMinSpawnHeight() {
		return minSpawnHeight;
	}
	public void setMaxSpawnHeight(int maxSpawnHeight) {
		this.maxSpawnHeight = maxSpawnHeight;
	}
	public int getMaxSpawnHeight() {
		return maxSpawnHeight;
	}
	public void setSpawnRoom(int spawnRoom) {
		this.spawnRoom = spawnRoom;
	}
	public int getSpawnRoom() {
		return spawnRoom;
	}
	public void setBurn(boolean burn) {
		this.burn = burn;
	}
	public boolean isBurn() {
		return burn;
	}
	public void setNatureDay(CreatureNature natureDay) {
		this.natureDay = natureDay;
	}
	public CreatureNature getNatureDay() {
		return natureDay;
	}
	public void setNatureNight(CreatureNature natureNight) {
		this.natureNight = natureNight;
	}
	public CreatureNature getNatureNight() {
		return natureNight;
	}
	public void setHealth(int health) {
		this.health = health;
	}
	public int getHealth() {
		return health;
	}
	public void setAttackDamage(int attackDamage) {
		this.attackDamage = attackDamage;
	}
	public int getAttackDamage() {
		return attackDamage;
	}
	public void setSpawnTime(SpawnTime spawnTime) {
		this.spawnTime = spawnTime;
	}
	public SpawnTime getSpawnTime() {
		return spawnTime;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	public boolean isEnabled() {
		return enabled;
	}
}
