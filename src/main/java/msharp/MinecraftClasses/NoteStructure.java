package msharp.MinecraftClasses;

import java.util.*;

public class NoteStructure extends ArrayList<ArrayList<MinecraftNote>> {
    
    private int turnAroundLength = 1;
    private static final int heightPrLane = 4;
    private Block fillerBlock = new Block("minecraft:stone");
    private static final Block redstone = new Block("minecraft:redstone_wire");
    
    public void setTurnAroundLength (int turnAroundLength)
    {
        this.turnAroundLength = turnAroundLength;
    }
    
    public void setFillerBlock (String fillerBlock)
    {
        this.fillerBlock = new Block(fillerBlock);
    }
    
    public Schematic GenerateSchematic ()
    {
        Analyze();
        Schematic schem = new Schematic(getWidth(), getMinimumPowerOfTwo(_lanes) * heightPrLane, getLength());
        
        GenerateStarter(schem, true);
        
        Map<Integer, List<BlockLocation>> emptyLocations = new HashMap<>();
        
        GenerateTrack(schem, emptyLocations);
        
        int currentTick = 0;
        for (ArrayList<MinecraftNote> minecraftNotes : this) {
            for (MinecraftNote minecraftNote : minecraftNotes) {
                BlockLocation location = emptyLocations.get(currentTick).remove(emptyLocations.get(currentTick).size() - 1);
                
                schem.setBlock(location.x, location.y, location.z, new Noteblock(minecraftNote));
            }
            currentTick++;
        }
        
        schem.fixNoteblocksBlockBeneath();
        
        return schem;
    }
    
    private BlockLocation GenerateStarter (Schematic schem, boolean building)
    {
        
        // lanes need to be combined to start at the same time. This is one method of doing so
        // Basically we can calculate all the locations where a lane starts, using the lame logic as in GenerateTrack.
        // From this we will make a list of all points that need to be collected, sort of a queue.
        // Then we will connect 2 points at a time, and then add the new point to the queue.
        // This should result in a tree like structure.
        // This function assumes that all the locations are in the same x-z plane, and that the starter is to be built,
        // such that it extends into negative z.
        
        // lets make this queue
        Queue<BlockLocation> toBeConnected = new LinkedList<>();
        
        // and fill it
        // Since we are going to build this tree from the bottom up, we want to have a 2^n number, so all branches develop evenly.
        // this will probably end up in certain situations where the starter is way bigger than it has to be, but it will always work.
        // this could of course be improved upon later.
        int lanes = getMinimumPowerOfTwo(_lanes);
        
        
        for (int lane = 0; lane < lanes; lane++)
            toBeConnected.add(new BlockLocation(1, lane * heightPrLane + 3, 0));
        
        while (toBeConnected.size() > 1) {
            // everytime this is run, at least 2 locations are in the queue.
            BlockLocation p1 = toBeConnected.remove();
            BlockLocation p2 = toBeConnected.remove();
            
            // the logic is, we want to move the first point upwards, and the second point downwards,
            // until they are only 2 blocks apart in the y direction.
            
            // since we optimally want to move both positions at once, we must first check if they are an odd amount
            // of blocks apart.
            if ((p2.y - p1.y) % 2 == 1) {
                p2.x++;
                p2.y--;
                
                p1.x++;
                if (building) {
                    schem.setBlock(p2, redstone);
                    schem.setBlock(p2.x, p2.y - 1, p2.z, fillerBlock);
                    schem.setBlock(p1, redstone);
                    schem.setBlock(p1.x, p1.y - 1, p1.z, fillerBlock);
                }
                
            }
            
            //the 2 points are now an even amount apart.
            int iterations = 0; // we need to keep track of how many steps we perform. If we perform more than 13 steps, we should add a repeater to amplify the signal.
            while (p2.y - p1.y > 2) {
                p2.x++;
                p2.y--;
                
                p1.x++;
                p1.y++;
                if (building) {
                    schem.setBlock(p2, redstone);
                    schem.setBlock(p2.x, p2.y - 1, p2.z, fillerBlock);
                    schem.setBlock(p1, redstone);
                    schem.setBlock(p1.x, p1.y - 1, p1.z, fillerBlock);
                }
                iterations++;
                if (iterations > 13) {
                    Map<String, String> meta_data = new HashMap<>();
                    meta_data.put("facing", "east");
                    
                    p2.x++;
                    p1.x++;
                    if (building) {
                        schem.setBlock(p2, new Block("minecraft:repeater", meta_data));
                        schem.setBlock(p2.x, p2.y - 1, p2.z, fillerBlock);
                        schem.setBlock(p1, new Block("minecraft:repeater", meta_data));
                        schem.setBlock(p1.x, p1.y - 1, p1.z, fillerBlock);
                    }
                    p2.x++;
                    p1.x++;
                    if (building) {
                        schem.setBlock(p2, redstone);
                        schem.setBlock(p2.x, p2.y - 1, p2.z, fillerBlock);
                        schem.setBlock(p1, redstone);
                        schem.setBlock(p1.x, p1.y - 1, p1.z, fillerBlock);
                    }
                    iterations = 0;
                }
            }
            
            // now we have the 2 connecting points 2 blocks apart in the y direction. To split one signal into
            // these 2 points, we use the following structure:
            //   R
            // RAS
            // SSR
            //   S
            // where R = redstone dust, S = filler, A = repeater
            // the new connecting point is the leftmost redstone dust at line 2.
            // Also worth nothing, the right most column does not need to be created, as this is assumed to be prepared
            // from the earlier steps.
            if (building) {
                Map<String, String> meta_data = new HashMap<>();
                meta_data.put("facing", "east");
                schem.setBlock(p2.x + 1, p2.y - 1, p2.z, new Block("minecraft:repeater", meta_data));
                schem.setBlock(p2.x + 2, p2.y - 1, p2.z, redstone);
                schem.setBlock(p2.x + 1, p2.y - 2, p2.z, fillerBlock);
                schem.setBlock(p2.x + 2, p2.y - 2, p2.z, fillerBlock);
            }
            
            // at the redstone as the new connection point:
            toBeConnected.add(new BlockLocation(p2.x + 2, p2.y - 1, p2.z));
        }
        
        //there needs two extra blocks pr layer as a spacer for the starter:
        if (building) {
            for (int lane = 0; lane < lanes; lane++) {
                schem.setBlock(1, lane * heightPrLane + 2, 1, fillerBlock);
                schem.setBlock(1, lane * heightPrLane + 3, 1, redstone);
                
                schem.setBlock(1, lane * heightPrLane + 2, 0, fillerBlock);
                schem.setBlock(1, lane * heightPrLane + 3, 0, redstone);
            }
        }
        return toBeConnected.remove();
    }
    
    private void GenerateTrack (Schematic schem, Map<Integer, List<BlockLocation>> emptyLocations)
    {
        // ok, lets start by placing the stone track.
        // xxxxx
        // x
        // xxxxx
        //     x looping
        for (int lane = 0; lane < _lanes; lane++) {
            int currentTick = 0;
            for (int turn = 0; turn < _turns; turn++) {
                int goingSouth = (turn) % 2;
                // so we build each "strip" of the turn here. Height is depending on lane.
                for (int i = 2; i < getLength(); i++) {
                    schem.setBlock(turn * 2 + 1, lane * heightPrLane + 2, i, fillerBlock);
                    if (i == 2 || i == getLength() - 1) {
                        // place redstone dust on layer above
                        schem.setBlock(turn * 2 + 1, lane * heightPrLane + 3, i, new Block("minecraft:redstone_wire"));
                    }
                    // if we are not at the end of a turn, then place switching between stone and repeater.
                    // Depending on the direction we are gonig.
                    else if ((i + goingSouth) % 2 == 0) {
                        schem.setBlock(turn * 2 + 1, lane * heightPrLane + 3, i, fillerBlock);
                        // this is where we can place 2 notes, either +1 or -1 x. Lets remember these locations:
                        
                        emptyLocations.putIfAbsent(currentTick, new ArrayList<>());
                        List<BlockLocation> thisTickBlockLocations = emptyLocations.get(currentTick);
                        thisTickBlockLocations.add(new BlockLocation(turn * 2, lane * heightPrLane + 3, i));
                        thisTickBlockLocations.add(new BlockLocation(turn * 2 + 2, lane * heightPrLane + 3, i));
                        if (goingSouth == 1) currentTick--;
                        else currentTick++;
                    } else {
                        Map<String, String> meta_data = new HashMap<>();
                        meta_data.put("facing", "north");
                        if (goingSouth == 1)
                            meta_data.put("facing", "south");
                        schem.setBlock(turn * 2 + 1, lane * heightPrLane + 3, i, new Block("minecraft:repeater", meta_data));
                    }
                }
                
                // at the end of a turn, add the little block to bridge to turns.
                int posAtLength = 2;
                if (goingSouth == 0)
                    posAtLength = getLength() - 1;
                schem.setBlock(turn * 2 + 2, lane * heightPrLane + 2, posAtLength, fillerBlock);
                schem.setBlock(turn * 2 + 2, lane * heightPrLane + 3, posAtLength, new Block("minecraft:redstone_wire"));
                
                
                if (goingSouth == 0) currentTick += turnAroundLength - 1;
                if (goingSouth == 1) currentTick += turnAroundLength + 1;
            }
        }
    }
    
    private int _lanes = 0;
    private int _turns = 0;
    
    
    private void Analyze ()
    {
        for (ArrayList<MinecraftNote> minecraftNotes : this) {
            if (minecraftNotes.size() > _lanes * 2) _lanes = (int) Math.ceil(minecraftNotes.size() / 2.0);
        }
        _turns = (int) Math.ceil((this.size() * 1.0) / turnAroundLength);
    }
    
    private int getWidth ()
    {
        //
        return Math.max(_turns * 2 + 2, (GenerateStarter(new Schematic(0, 0, 0), false).x + 1));
    }
    
    private int getLength ()
    {
        return turnAroundLength * 2 + 4; // 2 pr. note, and 2 at each end to connect, and 2 spare for the starter.
    }
    
    
    /**
     * This method is supposed to return the lowest number, x, that follows the rules:  x >= input, and x is a power of 2.
     * @param input - The integer, must be positive
     * @return - The minimum power of 2 that is >= input.
     */
    private int getMinimumPowerOfTwo (int input)
    {
        if ((input & (input - 1)) == 0) {
            // the number is a power of 2
            // why?
            // Binary a integer is a power of 2 if only one bit is a 1.
            // this means when we take the input -1, every bit to the right of the 1 will be set to 1, and the original 1 will be set to 0.
            // this means that input and input-1 will have no common "1s" in any place, and input & (input-1) will always be 0.
            //
            // Example: 256 if a power of 2. (2^8)
            // 256 =     0000 ... 0001 0000 0000
            // 255 =     0000 ... 0000 1111 1111
            // 256&255 = 0000 ... 0000 0000 0000 = 0
            
            return input;
            
        } else {
            // the input isnt a power of 2. We need to find the minimum power of 2 greater than input.
            
            // we do this by bitshifting right untill all the bits are 0, and keeping track of the amount of bit shifts it takes.
            //
            // Example: 200 is not a power of 2, expected minimum power of two is 256.
            // input = 200 = 11001000
            // we bit shift untill it is
            //           1 = 01100100
            //           2 = 00110010
            //           3 = 00011001
            //           4 = 00001100
            //           5 = 00000110
            //           6 = 00000011
            //           7 = 00000001
            //           8 = 00000000
            int power;
            for (power = 0; input != 0; power++)
                input >>>= 1;
            
            // we then take the number 1, and left shift the amount we counted above.
            //
            // Example: We bitshifted 8 times to get 0. 1 shifted left 8 times is 0001 0000 0000 = 256. This is the correct result.
            return 0x01 << power;
            
        }
    }
    
    
}
