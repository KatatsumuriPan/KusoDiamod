package kpan.kuso_diamod.util.handlers;

import kpan.kuso_diamod.item.ItemInit;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class LivingEventHandler {

	@SubscribeEvent
	public void onFall(LivingFallEvent event) {
		EntityLivingBase entityLiving = event.getEntityLiving();
		World world = entityLiving.world;
		if (world.isRemote)
			return;
		int height = 230;
		if (event.getDistance() < height - 2)
			return;
		if (entityLiving instanceof EntityPlayer) {
			BlockPos pos = new BlockPos(entityLiving).down();
			IBlockState state = world.getBlockState(pos);
			if (state.getBlock() == Blocks.DIAMOND_BLOCK) {
				world.setBlockToAir(pos);
				for (int i = 0; i < 14 * 9; i++) {
					float f = 0.5F;
					double d0 = (double) (world.rand.nextFloat() * f) - f / 2;
					double d1 = (double) (world.rand.nextFloat() * f) - f / 2;
					double d2 = (double) (world.rand.nextFloat() * f) - f / 2;
					EntityItem entityitem = new EntityItem(world, (double) pos.getX() + d0, (double) pos.getY() + d1, (double) pos.getZ() + d2, new ItemStack(ItemInit.ITEMS.get(i % 14)));
					float vf = 1.5F;
					double vx = (double) (world.rand.nextFloat() * vf);
					double vy = (double) (world.rand.nextFloat() * vf);
					double vz = (double) (world.rand.nextFloat() * vf);
					entityitem.motionX = vx;
					entityitem.motionY = vy;
					entityitem.motionZ = vz;
					entityitem.setDefaultPickupDelay();
					world.spawnEntity(entityitem);
				}
			}
		}
	}
}
