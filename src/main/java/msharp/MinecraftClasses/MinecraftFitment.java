package msharp.MinecraftClasses;

import msharp.NotePopulation.FinalNote;

import java.util.ArrayList;
import java.util.List;

public class MinecraftFitment {
    public static NoteStructure fitToMinecraft (List<FinalNote> finalNotes)
    {
        double songDuration = finalNotes.get(finalNotes.size() - 1).getTiming().toDouble();
        long ticks = Math.round(songDuration * 10) + 1;
        
        NoteStructure minecraftNotes = new NoteStructure();
        for (int i = 0; i < ticks; i++)
            minecraftNotes.add(new ArrayList<>());
        
        for (FinalNote note : finalNotes) {
            int tick = (int) Math.round(note.getTiming().toDouble() * 10);
            minecraftNotes.get(tick).add(convertFinalNoteToMinecraftNote(note));
        }
        
        return minecraftNotes;
    }
    
    private static MinecraftNote convertFinalNoteToMinecraftNote (FinalNote finalNote)
    {
        return new MinecraftNote(finalNote.getInstrument(), finalNote.getInstrument().getPitch(finalNote.getTone(), finalNote.getOctave()));
    }
}
