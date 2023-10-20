package kpan.kuso_diamod.util.handlers;

import kpan.kuso_diamod.ModTagsGenerated;
import kpan.kuso_diamod.config.ConfigHolder;
import kpan.kuso_diamod.world.WorldTypePro;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.items.ItemHandlerHelper;

public class PlayerEventHandler {

	public static final String NBT_TAG_NAME = ModTagsGenerated.MODID + "_item_gained";

	@SubscribeEvent
	public void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent event) {
		EntityPlayer player = event.player;
		if (player.world.getWorldInfo().getTerrainType() == WorldTypePro.INSTANCE)
			return;
		if (ConfigHolder.server.giveDiamondBlockFirst) {
			NBTTagCompound compound = player.getEntityData();
			if (!compound.getBoolean(NBT_TAG_NAME)) {
				ItemHandlerHelper.giveItemToPlayer(player, new ItemStack(Blocks.DIAMOND_BLOCK));
				compound.setBoolean(NBT_TAG_NAME, true);
			}
		}
		if (ConfigHolder.server.giveMany) {
			ItemHandlerHelper.giveItemToPlayer(player, new ItemStack(Blocks.DIAMOND_BLOCK, 4));
		}
	}
}
