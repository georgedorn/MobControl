package com.WinSock.MobControl.Spawner;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Random;

import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Creature;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import com.WinSock.MobControl.MobControlPlugin;
import com.WinSock.MobControl.Spawner.CreatureInfo.CreatureNature;
import com.WinSock.MobControl.Spawner.CreatureInfo.SpawnTime;

public class SpawnerCreature implements Runnable{
	private final MobControlPlugin plugin;
	private final Random rand = new Random();
	private Creatures creatures;
	
	public SpawnerCreature(final MobControlPlugin plugin, Creatures creatures)
	{
		this.plugin = plugin;
		this.creatures = creatures;
	}
	
	public Set<Creature> spawnMobPack(Block spawnBlock, CreatureInfo spawnCreature, World world)
	{
		Set<Location> spawnLocations = getMobSpawnLocations(spawnBlock, world);
		Set<Creature> returnData = new HashSet<Creature>();
		
		Iterator<Location> it = spawnLocations.iterator();
		checkloop: while(it.hasNext())
		{
			Location loc = it.next();
			int x = loc.getBlockX();
			int y = loc.getBlockY();
			int z = loc.getBlockZ();

			for (LivingEntity e : world.getLivingEntities())
			{
				if (e instanceof Creature)
				{
					if (e.getLocation().getBlock() == loc.getBlock())
					{
						it.remove();
						continue checkloop;
					}
				}
				else if (e instanceof HumanEntity)
				{
					int deltax = Math.abs(e.getLocation().getBlockX()-loc.getBlockX());
					int deltay = Math.abs(e.getLocation().getBlockY()-loc.getBlockY());
					int deltaz = Math.abs(e.getLocation().getBlockZ()-loc.getBlockZ());
					double distance = Math.sqrt((deltax*deltax)+(deltay*deltay)+(deltaz*deltaz));
					
					if (distance < creatures.getDistanceFromPlayer())
					{
						it.remove();
						continue checkloop;
					}
				}
			}
			for(int i = 0; i < spawnCreature.getSpawnRoom(); i++)
			{
				if(world.getBlockAt(x, y + i, z).getType() != Material.AIR)
				{
					it.remove();
					continue checkloop;
				}
			}
			for (Material m : spawnCreature.getSpawnBlocks())
			{
				if (world.getBlockAt(x, y - 1, z).getType() != m)
				{
					it.remove();
					continue checkloop;
				}
			}
			
			if (plugin.isDay(world))
			{
				switch (spawnCreature.getNatureDay())
				{
					case AGGRESSIVE:
						int maxLight = rand.nextInt(spawnCreature.getMaxLight());
						if (loc.getBlock().getLightLevel() < spawnCreature.getMinLight())
						{
							it.remove();
							continue checkloop;
						}
						else if (loc.getBlock().getLightLevel() > maxLight)
						{
							it.remove();
							continue checkloop;
						}
						break;
					default:
						if (loc.getBlock().getLightLevel() < spawnCreature.getMinLight())
						{
							it.remove();
							continue checkloop;
						}
						else if (loc.getBlock().getLightLevel() > spawnCreature.getMaxLight())
						{
							it.remove();
							continue checkloop;
						}
						break;
				}
			}
			else
			{
				switch (spawnCreature.getNatureNight())
				{
					case AGGRESSIVE:
						int maxLight = rand.nextInt(spawnCreature.getMaxLight());
						if (loc.getBlock().getLightLevel() < spawnCreature.getMinLight())
						{
							it.remove();
							continue checkloop;
						}
						else if (loc.getBlock().getLightLevel() > maxLight)
						{
							it.remove();
							continue checkloop;
						}
						break;
					default:
						if (loc.getBlock().getLightLevel() < spawnCreature.getMinLight())
						{
							it.remove();
							continue checkloop;
						}
						else if (loc.getBlock().getLightLevel() > spawnCreature.getMaxLight())
						{
							it.remove();
							continue checkloop;
						}
						break;
				}
			}
			
			if (loc.getBlockY() > spawnCreature.getMaxSpawnHeight())
			{
				it.remove();
				continue checkloop;
			}
			if (loc.getBlockY() < spawnCreature.getMinSpawnHeight())
			{
				it.remove();
				continue checkloop;
			}
		}
		
		for (Location loc : spawnLocations)
		{
			Creature c = loc.getWorld().spawnCreature(loc, spawnCreature.getCreature());
			returnData.add(c);
			System.out.println("SPAWN");
			
		}
		
		return returnData;
	}
	
	private Set<World> getActiveWorlds()
	{
		Set<World> returnData = new HashSet<World>();
		for (Player p : plugin.getServer().getOnlinePlayers())
		{
			returnData.add(p.getWorld());
		}
		return returnData;
	}
	
	private Set<Chunk> getChunks(World world)
	{
		Set<Chunk> returnData = new HashSet<Chunk>();
		for (Player p : plugin.getServer().getOnlinePlayers())
		{
			if (p.getWorld() == world)
			{
				Chunk pChunk = p.getWorld().getChunkAt(p.getLocation());
				Chunk startChunk = world.getChunkAt(pChunk.getX() - 4, pChunk.getZ() - 4);
				
				for (int x = 0; x <= 8; x++)
				{
					for (int z = 0; z <= 8; z++)
					{
						returnData.add(world.getChunkAt(startChunk.getX() + x, startChunk.getZ() + z));
					}
				}
			}
		}
		return returnData;
	}
	
	private Chunk getRandChunk(Set<Chunk> chunkSet)
	{
		for (Chunk c : chunkSet)
		{
			if (rand.nextInt(10) == 1)
			{
				return c;
			}
		}
		return null;
	}
	
	private Set<Location> getMobSpawnLocations(Block mobPackLocation, World world)
	{
		double x = mobPackLocation.getLocation().getX();
		double y = mobPackLocation.getLocation().getY();
		double z = mobPackLocation.getLocation().getZ();
		
		Set<Location> returnData = new HashSet<Location>();
		for (int i = 0; i < 12; i++)
		{
			int[] numbers = {rand.nextInt(3), rand.nextInt(3)};
			
			for (int i1 = 0; i1 < 7; i1++)
			{
				switch(rand.nextInt(2))
				{
					case 0:
						returnData.add(new Location(world, x + numbers[0], y, z));
						break;
					case 1:
						returnData.add(new Location(world, x, y, z + numbers[1]));
						break;
					case 2:
						returnData.add(new Location(world, x + numbers[0], y, z + numbers[1]));
						break;
				}
			}
		}
		return returnData;
	}
	
	private CreatureInfo getRandPassiveCreature(World world)
	{
		if (plugin.isDay(world))
		{
			List<CreatureInfo> enabledCreatures = creatures.getCreatures(SpawnTime.DAY, CreatureNature.PASSIVE);
			if (enabledCreatures.size() != 0)
			{
				return enabledCreatures.get(rand.nextInt(enabledCreatures.size()));
			}
			else
			{
				return null;
			}
		}
		else
		{
			List<CreatureInfo> enabledCreatures = creatures.getCreatures(SpawnTime.NIGHT, CreatureNature.PASSIVE);
			if (enabledCreatures.size() != 0)
			{
				return enabledCreatures.get(rand.nextInt(enabledCreatures.size()));
			}
			else
			{
				return null;
			}
		}
	}
	
	private CreatureInfo getRandHostileCreature(World world)
	{
		if (plugin.isDay(world))
		{
			List<CreatureInfo> enabledCreatures = creatures.getCreatures(SpawnTime.DAY, CreatureNature.AGGRESSIVE);
			enabledCreatures.addAll(creatures.getCreatures(SpawnTime.DAY, CreatureNature.NEUTRAL));
			if (enabledCreatures.size() != 0)
			{
				return enabledCreatures.get(rand.nextInt(enabledCreatures.size()));
			}
			else
			{
				return null;
			}
		}
		else
		{
			List<CreatureInfo> enabledCreatures = creatures.getCreatures(SpawnTime.NIGHT, CreatureNature.AGGRESSIVE);
			enabledCreatures.addAll(creatures.getCreatures(SpawnTime.NIGHT, CreatureNature.NEUTRAL));
			if (enabledCreatures.size() != 0)
			{
				return enabledCreatures.get(rand.nextInt(enabledCreatures.size()));
			}
			else
			{
				return null;
			}
		}
	}

	public void run() {
		Set<World> worlds = getActiveWorlds();
		for (World w : worlds)
		{
		Set<Chunk> chunkSet = getChunks(w);
		
		Chunk spawnChunk = getRandChunk(chunkSet);
		if (spawnChunk != null)
		{
			CreatureInfo spawnPassive = getRandPassiveCreature(w);
			CreatureInfo spawnAggressive = getRandHostileCreature(w);
			if (spawnPassive != null || spawnAggressive != null)
			{
				// Enough space for one chunk worth of blocks.
				List<Block> blocks= new ArrayList<Block>();

				for (int y = 0; y < 128; y++) {
				    for (int x = 0; x < 16; x++) {
				        for (int z = 0; z < 16; z++) {
				            blocks.add(spawnChunk.getBlock(x, y, z));
				        }
				    }
				}
				
				Block spawnPassiveBlock = blocks.get(rand.nextInt(blocks.size()));
				while (spawnPassiveBlock.getType() != Material.AIR)
				{
					spawnPassiveBlock = blocks.get(rand.nextInt(blocks.size()));
				}
				
				Block spawnAggressiveBlock = blocks.get(rand.nextInt(blocks.size()));
				while (spawnAggressiveBlock.getType() != Material.AIR)
				{
					spawnAggressiveBlock = blocks.get(rand.nextInt(blocks.size()));
				}
				
				if (spawnPassive != null)
				{
					System.out.println("TRY: "+spawnPassive.getCreature().getName());
					spawnMobPack(spawnPassiveBlock, spawnPassive, w);
				}
				if (spawnAggressive != null)
				{
					System.out.println("TRY: "+spawnAggressive.getCreature().getName());
					spawnMobPack(spawnAggressiveBlock, spawnAggressive, w);
				}
			}
		}
	}
	}
}
