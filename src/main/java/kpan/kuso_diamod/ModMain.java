package kpan.kuso_diamod;

import kpan.kuso_diamod.proxy.CommonProxy;
import kpan.kuso_diamod.util.handlers.RegistryHandler;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerAboutToStartEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

//文字コードをMS932にすると日本語ベタ打ちしたものがゲーム時に文字化けしないが
//色々と問題があるので
//.langをちゃんと使うのを推奨

@Mod(modid = ModTagsGenerated.MODID, version = ModTagsGenerated.VERSION, name = ModTagsGenerated.MODNAME, acceptedMinecraftVersions = "[1.12.2]"
		, guiFactory = ModTagsGenerated.MODGROUP + ".config.ModGuiFactory"
		, dependencies = ""
		, acceptableRemoteVersions = ModTagsGenerated.VERSION_MAJOR + "." + ModTagsGenerated.VERSION_MINOR
//
//, serverSideOnly = true //サーバーのみにする場合に必要(acceptableRemoteVersionsを*に変えないとダメ)、デバッグ時はオフにする
)
public class ModMain {

	@SidedProxy(clientSide = ModReference.CLIENT_PROXY_CLASS, serverSide = ModReference.COMMON_PROXY_CLASS)
	public static CommonProxy proxy;

	public static MinecraftServer server;

	@EventHandler
	public static void preInit(FMLPreInitializationEvent event) {
		RegistryHandler.preInitRegistries(event);
	}

	@EventHandler
	public static void init(FMLInitializationEvent event) { RegistryHandler.initRegistries(); }

	@EventHandler
	public static void postInit(FMLPostInitializationEvent event) { RegistryHandler.postInitRegistries(); }

	@EventHandler
	public static void serverInit(FMLServerStartingEvent event) { RegistryHandler.serverRegistries(event); }

	@EventHandler
	public static void onServerAboutToStart(FMLServerAboutToStartEvent event) {
		server = event.getServer();
	}
}
