package msharp.MinecraftClasses;

import msharp.MinecraftClasses.Note;

import java.util.HashMap;
import java.util.Map;

public class Noteblock extends Block {
    public Noteblock(Note note) {

        super("minecraft:note_block");
        Map<String, String> hashMap = new HashMap<>();


        hashMap.put("note", String.valueOf(note.getPitch()));
        hashMap.put("instrument", note.getInstrument().toString().toLowerCase());
        super.set_metadata(hashMap);
    }
}
