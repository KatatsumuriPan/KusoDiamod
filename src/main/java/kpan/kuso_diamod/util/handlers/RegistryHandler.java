package kpan.kuso_diamod.util.handlers;

import kpan.kuso_diamod.ModMain;
import kpan.kuso_diamod.block.BlockBase;
import kpan.kuso_diamod.block.BlockInit;
import kpan.kuso_diamod.item.ItemInit;
import kpan.kuso_diamod.util.interfaces.IHasModel;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@EventBusSubscriber
public class RegistryHandler {

	@SuppressWarnings("InstantiationOfUtilityClass")
	public static void preInitRegistries(@SuppressWarnings("unused") FMLPreInitializationEvent event) {
		MinecraftForge.EVENT_BUS.register(new RegistryHandler());
		ModMain.proxy.registerOnlyClient();
	}

	public static void initRegistries() {
	}

	public static void postInitRegistries() {
	}

	public static void serverRegistries(@SuppressWarnings("unused") FMLServerStartingEvent event) {
	}

//	@SubscribeEvent
//	public void onEnchantmentRegister(RegistryEvent.Register<Enchantment> event) {
//	}

//	@SubscribeEvent
//	public static void registerRecipes(RegistryEvent.Register<IRecipe> event) {
//	}

//	@SubscribeEvent
//	public static void onDataSerializerRegister(RegistryEvent.Register<DataSerializerEntry> event) {
//	}


	@SubscribeEvent
	public void onBlockRegister(RegistryEvent.Register<Block> event) {
		BlockBase.prepareRegistering();
		event.getRegistry().registerAll(BlockInit.BLOCKS.toArray(new Block[0]));
	}

	@SubscribeEvent
	public void onItemRegister(RegistryEvent.Register<Item> event) {
		event.getRegistry().registerAll(ItemInit.ITEMS.toArray(new Item[0]));
	}

	@SubscribeEvent
	public void onModelRegister(ModelRegistryEvent event) {
		for (Item item : ItemInit.ITEMS) {
			if (item instanceof IHasModel)
				((IHasModel) item).registerItemModels();
		}

		for (Block block : BlockInit.BLOCKS) {
			if (block instanceof IHasModel)
				((IHasModel) block).registerItemModels();
		}
	}
}
