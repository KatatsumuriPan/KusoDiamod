package kpan.kuso_diamod.block.item;

import kpan.kuso_diamod.util.interfaces.IMetaName;
import kpan.kuso_diamod.util.interfaces.block.IHasMultiModels;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

public class ItemBlockVariants extends ItemBlockBase {
	public ItemBlockVariants(Block block) {
		super(block);
		setHasSubtypes(true);
		setMaxDamage(0);
	}

	@Override
	public int getMetadata(int damage) {
		return damage;
	}

	@Override
	public String getTranslationKey(ItemStack stack) {
		if (block instanceof IHasMultiModels)
			return "tile." + ((IHasMultiModels) block).getItemRegistryName() + "." + ((IMetaName) block).getSpecialName(stack);
		else
			return super.getTranslationKey() + "." + ((IMetaName) block).getSpecialName(stack);
	}
}
