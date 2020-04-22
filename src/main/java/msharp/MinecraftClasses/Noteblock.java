package msharp.MinecraftClasses;

import java.util.HashMap;
import java.util.Map;

public class Noteblock extends Block {
    public Noteblock (MinecraftNote minecraftNote)
    {
        
        super("minecraft:note_block");
        Map<String, String> hashMap = new HashMap<>();
        
        
        hashMap.put("note", String.valueOf(minecraftNote.getPitch()));
        hashMap.put("instrument", minecraftNote.getInstrument().toString().toLowerCase());
        super.set_metadata(hashMap);
    }
}
