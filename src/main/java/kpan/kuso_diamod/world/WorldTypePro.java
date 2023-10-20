package kpan.kuso_diamod.world;

import net.minecraft.block.BlockChest;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockPos.MutableBlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.Biome.SpawnListEntry;
import net.minecraft.world.biome.BiomeProvider;
import net.minecraft.world.biome.BiomeProviderSingle;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.ChunkGeneratorFlat;

import java.util.Collections;
import java.util.List;

public class WorldTypePro extends WorldType {
	public static final WorldTypePro INSTANCE = new WorldTypePro();

	private WorldTypePro() {
		super("pro_mode");
	}
	public static void init() {
		//クラスロードされた時点でインスタンスがつくられるのでOK
	}

	@Override
	public BiomeProvider getBiomeProvider(World world) {
		return new BiomeProviderSingle(Biomes.PLAINS);
	}

	@Override
	public net.minecraft.world.gen.IChunkGenerator getChunkGenerator(World world, String generatorOptions) {
		return new ChunkGeneratorFlat(world, world.getSeed(), false, generatorOptions) {
			@Override
			public Chunk generateChunk(int x, int z) {
				Chunk chunk = super.generateChunk(x, z);
				if (x == 0 && z < 0) {
					for (MutableBlockPos pos : MutableBlockPos.getAllInBoxMutable(0, 3, 0, 0, 3, 15)) {
						chunk.setBlockState(pos, Blocks.SAND.getDefaultState());
					}
				}
				if (x == 0 && z == 0)
					chunk.setBlockState(new BlockPos(0, 3, 0), Blocks.DIAMOND_BLOCK.getDefaultState());
				return chunk;
			}
			@Override
			public void populate(int x, int z) {
				if (x == 0 && z == 0) {
					world.setBlockState(new BlockPos(0, 4, 1), Blocks.CHEST.getDefaultState().withProperty(BlockChest.FACING, EnumFacing.NORTH));
					TileEntity tileEntity = world.getTileEntity(new BlockPos(0, 4, 1));
					((TileEntityChest) tileEntity).setInventorySlotContents(0, new ItemStack(Items.DIAMOND_SHOVEL));
					((TileEntityChest) tileEntity).setInventorySlotContents(1, new ItemStack(Items.ENDER_PEARL, 2));
				}
			}
			@Override
			public List<SpawnListEntry> getPossibleCreatures(EnumCreatureType creatureType, BlockPos pos) {
				return Collections.emptyList();
			}
		};
	}

	@Override
	public int getMinimumSpawnHeight(World world) {
		return 4;
	}

	@Override
	public double getHorizon(World world) {
		return 0.0D;
	}

	@Override
	public double voidFadeMagnitude() {
		return 1.0D;
	}

	@Override
	public int getSpawnFuzz(WorldServer world, MinecraftServer server) {
		return 0;
	}
}
