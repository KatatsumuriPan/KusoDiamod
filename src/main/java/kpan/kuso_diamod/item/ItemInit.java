package kpan.kuso_diamod.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

import java.util.ArrayList;

public class ItemInit {

	public static final ArrayList<Item> ITEMS = new ArrayList<>();

	static {
		for (int i = 1; i <= 14; i++) {
			new ItemBase("diamond_piece" + i, CreativeTabs.MISC);
		}
	}
}
