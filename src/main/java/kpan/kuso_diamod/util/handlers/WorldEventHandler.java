package kpan.kuso_diamod.util.handlers;

import net.minecraft.util.math.BlockPos;
import net.minecraftforge.event.world.WorldEvent.CreateSpawnPosition;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class WorldEventHandler {

	@SubscribeEvent
	public static void onCreateWorldSpawn(CreateSpawnPosition event) {
		event.getWorld().setSpawnPoint(BlockPos.ORIGIN.up(4));
		event.getWorld().getGameRules().setOrCreateGameRule("randomTickSpeed", "0");
		event.setCanceled(true);
	}
}
