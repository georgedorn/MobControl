package com.WinSock.MobControl.Listeners;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Creature;
import org.bukkit.entity.CreatureType;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.MobType;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityCombustEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.EntityListener;
import org.bukkit.event.entity.EntityTargetEvent;
import org.bukkit.event.entity.EntityTargetEvent.TargetReason;

import com.WinSock.MobControl.MobControlPlugin;

public class MobControlEntityListener extends EntityListener {
	private final MobControlPlugin plugin;

	private List<Creature> attacked = new ArrayList<Creature>();

	public MobControlEntityListener(final MobControlPlugin plugin) {
		this.plugin = plugin;
	}

	@Override
	public void onEntityDeath(EntityDeathEvent event) {
		if (event.getEntity() instanceof Creature) {
			Creature c = (Creature) event.getEntity();
			attacked.remove(c);
		}
	}

	@Override
	public void onEntityCombust(EntityCombustEvent event) {
		CreatureType cType = plugin.getCreatureType(event.getEntity());
		if (cType != null) {

			String burnNode = "MobControl.Mobs."
					+ cType.getName().toUpperCase() + ".Day.Burn";

			if (!plugin.getConfiguration().getBoolean(burnNode, false)) {
				event.setCancelled(true);
			}
		}
	}

	@Override
	public void onEntityDamage(EntityDamageEvent event) {
		if (event instanceof EntityDamageByEntityEvent) {
			EntityDamageByEntityEvent dmgByEntity = (EntityDamageByEntityEvent) event;
			CreatureType cType = plugin.getCreatureType(event.getEntity());
			if (cType != null) {
				if (event.getEntity().getLocation().getWorld().getTime() < 12000
						|| event.getEntity().getLocation().getWorld().getTime() == 24000) {
					String natureLightNode = "MobControl.Mobs."
							+ cType.getName().toUpperCase() + ".Day.Nature";
					String data = plugin.getConfiguration().getString(
							natureLightNode);
					if (plugin.shouldTarget(event.getEntity(), data, true)) {
						Creature c = (Creature) event.getEntity();
						if (dmgByEntity.getDamager() instanceof LivingEntity) {
							c.setTarget((LivingEntity) dmgByEntity.getDamager());
							if (!attacked.contains(c)) {
								attacked.add(c);
							}
						}
					}
				} else {
					String natureNightNode = "MobControl.Mobs."
							+ cType.getName().toUpperCase() + ".Night.Nature";
					String data = plugin.getConfiguration().getString(
							natureNightNode);
					if (plugin.shouldTarget(event.getEntity(), data, true)) {
						Creature c = (Creature) event.getEntity();
						if (dmgByEntity.getDamager() instanceof LivingEntity) {
							c.setTarget((LivingEntity) dmgByEntity.getDamager());
							if (!attacked.contains(c)) {
								attacked.add(c);
							}
						}
					}
				}
			}
		}
	}

	@Override
	public void onEntityTarget(EntityTargetEvent event) {
		if (event.getReason() == TargetReason.FORGOT_TARGET
				|| event.getReason() == TargetReason.TARGET_DIED) {
			if (event.getEntity() instanceof Creature) {
				Creature c = (Creature) event.getEntity();
				attacked.remove(c);
			}
		} else if (event.getReason() == TargetReason.TARGET_ATTACKED_ENTITY) {
			if (event.getEntity() instanceof Creature) {
				Creature c = (Creature) event.getEntity();
				if (!attacked.contains(c)) {
					attacked.add(c);
				}
			}
		} else if (!(event.getReason() == TargetReason.CUSTOM)) {
			CreatureType cType = plugin.getCreatureType(event.getEntity());
			if (cType != null) {
				if (event.getEntity().getLocation().getWorld().getTime() < 12000
						|| event.getEntity().getLocation().getWorld().getTime() == 24000) {
					String natureLightNode = "MobControl.Mobs."
							+ cType.getName().toUpperCase() + ".Day.Nature";
					String data = plugin.getConfiguration().getString(
							natureLightNode);
					Creature c = (Creature) event.getEntity();
					if (!plugin.shouldTarget(event.getEntity(), data,
							attacked.contains(c))) {
						event.setCancelled(true);
					}
				} else {
					String natureNightNode = "MobControl.Mobs."
							+ cType.getName().toUpperCase() + ".Night.Nature";
					String data = plugin.getConfiguration().getString(
							natureNightNode);
					Creature c = (Creature) event.getEntity();
					if (!plugin.shouldTarget(event.getEntity(), data,
							attacked.contains(c))) {
						event.setCancelled(true);
					}
				}
			}
		}
	}

	@Override
	public void onEntityExplode(EntityExplodeEvent event) {
		CreatureType cType = plugin.getCreatureType(event.getEntity());
		if (cType != null) {
			if (cType == CreatureType.CREEPER) {
				if (event.getEntity().getLocation().getWorld().getTime() < 12000
						|| event.getEntity().getLocation().getWorld().getTime() == 24000) {
					String natureLightNode = "MobControl.Mobs."
							+ cType.getName().toUpperCase() + ".Day.Nature";
					String data = plugin.getConfiguration().getString(
							natureLightNode);
					Creature c = (Creature) event.getEntity();
					if (!plugin.shouldTarget(event.getEntity(), data,
							attacked.contains(c))) {
						event.setCancelled(true);
					}
				} else {
					String natureNightNode = "MobControl.Mobs."
							+ cType.getName().toUpperCase() + ".Night.Nature";
					String data = plugin.getConfiguration().getString(
							natureNightNode);
					Creature c = (Creature) event.getEntity();
					if (!plugin.shouldTarget(event.getEntity(), data,
							attacked.contains(c))) {
						event.setCancelled(true);
					}
				}
			}
		}
	}

	@Override
	public void onCreatureSpawn(CreatureSpawnEvent event) {
		event.setCancelled(true);
	}
}
