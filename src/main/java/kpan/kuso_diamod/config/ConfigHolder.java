package kpan.kuso_diamod.config;

import kpan.kuso_diamod.config.ConfigAnnotations.Name;
import net.minecraftforge.common.config.Configuration;

public class ConfigHolder {

	//	@Comment("Common settings(Blocks, items, etc.)") //
	//	public static Common common = new Common();

	public static class Common {

	}

//	@Comment("Client only settings(Rendering, resources, etc.)") //
//	public static Client client = new Client();

	public static class Client {

	}

	//	@Comment("Server settings(Behaviors, phisics, etc.)") //
	public static Server server = new Server();

	public static class Server {
		@Name("Give Diamond Block First")
		public boolean giveDiamondBlockFirst = false;
		@Name("Many many, many!")
		public boolean giveMany = false;
	}

	public static void updateVersion(Configuration config) {
		String loadedConfigVersion = config.getLoadedConfigVersion();
		switch (loadedConfigVersion) {
			case "1":
				break;
			default:
				throw new RuntimeException("Unknown config version:" + loadedConfigVersion);
		}
	}

	public static String getVersion() { return "1"; }
}
