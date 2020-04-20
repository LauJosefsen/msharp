package msharp.MinecraftClasses;

import net.darkhax.opennbt.NBTHelper;
import net.darkhax.opennbt.tags.CompoundTag;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Schematic
{
    public Schematic(int width, int height, int length) {
        _length = length;
        _height = height;
        _width = width;
        _blocks = new Block[_width][_height][_length];

        //initialize all blocks to air
        for (int y = 0; y < _height; y++) {
            for (int z = 0; z < _length; z++) {
                for (int x = 0; x < _width; x++) {
                    _blocks[x][y][z] = new Block("minecraft:air");
                }
            }
        }
    };

    private Block[][][] _blocks;

    public int get_length() {
        return _length;
    }

    public void set_length(int _length) {
        this._length = _length;
    }

    public int get_height() {
        return _height;
    }

    public void set_height(int _height) {
        this._height = _height;
    }

    public int get_width() {
        return _width;
    }

    public void set_width(int _width) {
        this._width = _width;
    }

    private int _length;
    private int _height;
    private int _width;

    public void setBlock(int x, int y, int z, Block block){
        _blocks[x][y][z] = block;
    }

    public void setBlock(BlockLocation loc, Block block) {
        _blocks[loc.x][loc.y][loc.z] = block;
    }

    public void fixNoteblocksBlockBeneath() {
        for (int x = 0; x < _width; x++) {
            for (int y = 0; y < _height; y++) {
                for (int z = 0; z < _length; z++) {
                    if (
                            _blocks[x][y][z].get_blockName().equalsIgnoreCase("minecraft:note_block") &&
                                    _blocks[x][y][z].get_metadata().containsKey("instrument")
                    ) {
                        Block blockBeneath = Enum.valueOf(Instrument.class,
                                _blocks[x][y][z].get_metadata().get("instrument").toUpperCase()).mapToBlockBeneath();
                        _blocks[x][y - 1][z] = blockBeneath;

                        // if block is sand, set block below to be stone, in order to avoid gravity.
                        if (blockBeneath.get_blockName().equalsIgnoreCase("minecraft:sand"))
                            _blocks[x][y - 2][z] = new Block("minecraft:stone");

                    }
                }
            }
        }
    }

    public void saveToFile(String file_name) {
        CompoundTag tag = new CompoundTag("Schematic");
        tag.setShort("Width",(short) _width);
        tag.setShort("Height",(short) _height);
        tag.setShort("Length",(short) _length);

        Map<String, Integer> blockTypes = new HashMap<>();

        List<Byte> block_data = new ArrayList<>();

        int blockTypeIndex = 0;
        int block_data_position = 0;

        for (int y = 0; y < _height; y++) {
            for (int z = 0; z < _length; z++) {
                for (int x = 0; x < _width; x++) {
                    blockTypes.putIfAbsent(_blocks[x][y][z].toString(), blockTypeIndex++);

                    int value = blockTypes.get(_blocks[x][y][z].toString());
                    //https://github.com/SpongePowered/SpongeCommon/blob/aa2c8c53b4f9f40297e6a4ee281bee4f4ce7707b/src/main/java/org/spongepowered/common/data/persistence/SchematicTranslator.java#L230-L251
                    while ((value & -128) != 0) {
                        block_data.add((byte) (value & 127 | 128));
                        value >>>= 7;
                    }
                    block_data.add((byte) value);
                }
            }
        }

        byte[] blockDataArr = new byte[block_data.size()];
        for (int i =0; i < block_data.size(); i++)
            blockDataArr[i] = block_data.get(i);
        tag.setByteArray("BlockData",blockDataArr);

        //set the palette
        CompoundTag paletteTag = new CompoundTag("Palette");
        for (Map.Entry<String, Integer> stringIntegerEntry : blockTypes.entrySet()) {
            paletteTag.setInt(stringIntegerEntry.getKey(), stringIntegerEntry.getValue());
        }
        tag.setTag(paletteTag);

        tag.setInt("PaletteMax",blockTypes.size());

        tag.setInt("Version", 2); // we use schem v2 https://github.com/SpongePowered/Schematic-Specification/blob/master/versions/schematic-2.md
        tag.setInt("DataVersion", 2230); // we used Minecraft 1.15.2, which data version is 2230 https://minecraft.gamepedia.com/Data_version

        tag.setIntArray("Offset",new int[]{0,0,0});

        NBTHelper.writeFile(tag, file_name);
    }
}


