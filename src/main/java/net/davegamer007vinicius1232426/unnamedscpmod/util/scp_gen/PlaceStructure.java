package net.davegamer007vinicius1232426.unnamedscpmod.util.scp_gen;

import com.google.common.base.Stopwatch;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.datafixers.util.Pair;
import net.davegamer007vinicius1232426.unnamedscpmod.UnnamedSCPMod;
import net.minecraft.ResourceLocationException;
import net.minecraft.Util;
import net.minecraft.commands.arguments.ResourceOrTagArgument;
import net.minecraft.commands.arguments.coordinates.BlockPosArgument;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.block.entity.StructureBlockEntity;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplateManager;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class PlaceStructure {


    public static boolean placeStructureProtocol(Player pPlayer, Integer pSCPNumber) throws CommandSyntaxException {
        ServerLevel pLevel = pPlayer.getServer().getLevel(pPlayer.level().dimension());

        BlockPos pStructurePos = locateBiome(pPlayer, eventBiomeMap(pSCPNumber));

        ResourceLocation pTemplate = eventTemplateMap(pSCPNumber);

        if (pStructurePos != null && pTemplate != null && pLevel != null
                && !eventHappenedMapOut(pSCPNumber) && eventBoolMap.containsKey(pSCPNumber)){
            place(pLevel, pTemplate, pStructurePos);
            setEventBoolMap(pSCPNumber);
            return true;
        }
        return false;
    }


    private static BlockPos locateBiome(Player pPlayer, ResourceOrTagArgument.Result<Biome> pBiome) {
        BlockPos blockPos = BlockPos.containing(pPlayer.getPosition(1));
        ServerLevel pServer = pPlayer.level().getServer().getLevel(pPlayer.level().dimension());
        Stopwatch stopwatch = Stopwatch.createStarted(Util.TICKER);

        Pair<BlockPos, Holder<Biome>> closestBiome3d = pServer.findClosestBiome3d(pBiome, blockPos, 6400, 32, 64);
        stopwatch.stop();

        if (closestBiome3d == null) {
            return null;
        } else {
            return closestBiome3d.getFirst();
        }
    }

    public static boolean place(ServerLevel pLevel,ResourceLocation pTemplate, BlockPos pPos) throws CommandSyntaxException {
        StructureTemplateManager structureManager = pLevel.getStructureManager();
        long pSeed = pLevel.getSeed();
        if (pPos == null){
            return false;
        }
        Optional optional;
            try {
            optional = structureManager.get(pTemplate);
        } catch (
        ResourceLocationException var13) {
                return false;
        }
        if (optional.isEmpty()) {
            return false;
        } else {
            StructureTemplate structureTemplate = (StructureTemplate)optional.get();
            checkLoaded(pLevel, new ChunkPos(pPos), new ChunkPos(pPos.offset(structureTemplate.getSize())));
            StructurePlaceSettings placeSettings = (new StructurePlaceSettings());

            return structureTemplate.placeInWorld(pLevel, pPos, pPos, placeSettings, StructureBlockEntity.createRandom(pSeed), 2);
        }
    }

    private static void checkLoaded(ServerLevel pLevel, ChunkPos pStart, ChunkPos pEnd) throws CommandSyntaxException {
        if (ChunkPos.rangeClosed(pStart, pEnd).anyMatch((chunkPos) -> {
            return !pLevel.isLoaded(chunkPos.getWorldPosition());
            })) {
            throw BlockPosArgument.ERROR_NOT_LOADED.create();
        }
    }

    private static ResourceOrTagArgument.Result<Biome> eventBiomeMap(int pEventNumber){
        Map<Integer, ResourceOrTagArgument.Result<Biome>> resultMap = Map.of();

        resultMap.put(268, (ResourceOrTagArgument.Result<Biome>) Biomes.DARK_FOREST);
        resultMap.put(310, (ResourceOrTagArgument.Result<Biome>) Biomes.TAIGA);
        resultMap.put(458, (ResourceOrTagArgument.Result<Biome>) Biomes.PLAINS);
        resultMap.put(622, (ResourceOrTagArgument.Result<Biome>) Biomes.DESERT);
        resultMap.put(999, (ResourceOrTagArgument.Result<Biome>) Biomes.FLOWER_FOREST);

        return resultMap.getOrDefault(pEventNumber, null);
    }

    private static String pStructurePath(String pInput){
        return "unnamedscpmod:"+pInput;
    }

    private static ResourceLocation eventTemplateMap(int pEventNumber){
        Map<Integer, ResourceLocation> resultMap = Map.of();

        resultMap.put(268, new ResourceLocation(UnnamedSCPMod.MOD_ID, pStructurePath("scp268_structure")));
        resultMap.put(310, new ResourceLocation(UnnamedSCPMod.MOD_ID, pStructurePath("scp310_structure")));
        resultMap.put(458, new ResourceLocation(UnnamedSCPMod.MOD_ID, pStructurePath("scp458_structure")));
        resultMap.put(622, new ResourceLocation(UnnamedSCPMod.MOD_ID, pStructurePath("scp622_structure")));
        resultMap.put(999, new ResourceLocation(UnnamedSCPMod.MOD_ID, pStructurePath("scp999_structure")));

        return resultMap.getOrDefault(pEventNumber, null);
    }

    public static Map<Integer,Boolean> eventBoolMap;

    private static boolean eventHappenedMapOut(int pInput){
        Map<Integer, Boolean> resultMap = Map.of();

        eventBoolMap = resultMap;
        eventBoolMap.put(268, false);
        eventBoolMap.put(310, false);
        eventBoolMap.put(458, false);
        eventBoolMap.put(622, false);
        eventBoolMap.put(999, false);

        return eventBoolMap.getOrDefault(pInput, true);
    }

    public static List<Integer> pSCPsList = List.of(268,310,458,622,999);

    private static void setEventBoolMap(int pInput){
        if (eventBoolMap.containsKey(pInput)){
            eventBoolMap.replace(pInput, true);
        }
    }

    public static void resetBoolMap(){

    }
}
