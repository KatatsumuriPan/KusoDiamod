package kpan.kuso_diamod.util.handlers;

import kpan.kuso_diamod.config.ConfigHolder;
import kpan.kuso_diamod.world.WorldTypePro;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.items.ItemHandlerHelper;

public class TickingHandler {

	@SubscribeEvent
	public static void onTick(TickEvent.PlayerTickEvent event) {
		if (event.phase == Phase.START && event.side == Side.SERVER) {
			if (event.player.world.getWorldInfo().getTerrainType() != WorldTypePro.INSTANCE) {
				if (ConfigHolder.server.giveMany && event.player.world.rand.nextInt(40) == 0)
					ItemHandlerHelper.giveItemToPlayer(event.player, new ItemStack(Blocks.DIAMOND_BLOCK));
			}
		}
	}
}
