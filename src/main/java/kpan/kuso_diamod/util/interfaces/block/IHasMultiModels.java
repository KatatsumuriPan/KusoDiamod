package kpan.kuso_diamod.util.interfaces.block;

import kpan.kuso_diamod.ModMain;
import kpan.kuso_diamod.block.BlockBase;
import kpan.kuso_diamod.util.interfaces.IMetaName;
import net.minecraft.item.Item;

public interface IHasMultiModels extends IMetaName {

	int metaMax();

	default String getItemRegistryName() { return ((BlockBase) this).getItemRegistryName(); }
	default String getInventoryItemFileName(int i) { return getItemRegistryName(); }

	static void registerMultiItemModels(BlockBase block) {
		IHasMultiModels t = (IHasMultiModels) block;
		for (int i = 0; i <= t.metaMax(); i++) {
			ModMain.proxy.registerMultiItemModel(Item.getItemFromBlock(block), i, t.getInventoryItemFileName(i), block.getInventoryItemStateName(i));
		}
	}
}
