package msharp.NotePopulation;

import msharp.MinecraftClasses.Instrument;
import msharp.MinecraftClasses.MinecraftNote;

import java.util.Objects;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class FinalNote implements Comparable<FinalNote> {
    Instrument instrument;
    int octave;
    ToneEnum tone;
    FractionPrecise timing;
    
    public FinalNote (Instrument instrument, ToneEnum tone, int octave, FractionPrecise timing)
    {
        this.instrument = instrument;
        this.tone = tone;
        this.octave = octave;
        this.timing = timing;
    }
    
    public void transpose (int amount)
    {
        // determine if we are stepping postive or negative steps.
        int direction = amount > 0 ? 1 : -1;
        
        // loop the amount
        for (int i = 0; i < Math.abs(amount); i++) {
            int toneId = tone.getToneId();
            toneId += direction;
            if (toneId < 0) {
                this.octave--;
                toneId = 11;
            }
            if (this.tone.getToneId() + direction > 11) {
                this.octave++;
                toneId = 0;
            }
            tone = ToneEnum.fromToneId(toneId);
        }
    }
    
    @Override
    public boolean equals (Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FinalNote finalNote = (FinalNote) o;
        return octave == finalNote.octave &&
                instrument == finalNote.instrument &&
                tone == finalNote.tone &&
                timing.equals(finalNote.timing);
    }
    
    @Override
    public int hashCode ()
    {
        return Objects.hash(instrument, octave, tone, timing);
    }
    
    @Override
    public int compareTo (FinalNote finalNote)
    {
        return this.timing.compareTo(finalNote.timing);
    }
    
    public Instrument getInstrument ()
    {
        return instrument;
    }
    
    public int getOctave ()
    {
        return octave;
    }
    
    public ToneEnum getTone ()
    {
        return tone;
    }
    
    public FractionPrecise getTiming ()
    {
        return timing;
    }
    
    public MinecraftNote convertToMinecraftNote ()
    {
        int pitch  = this.getInstrument().getPitch(this.getTone(), this.getOctave());
        if(pitch < 0 || pitch > 24){
            Logger log = LogManager.getLogManager().getLogger("");
            log.warning("Note with instrument: \""+this.getInstrument().toString()+"\" at octave \""+this.getOctave()+"\" is out of Minecraft bounds *WAS "+pitch+"* " +
                    "(See https://minecraft.gamepedia.com/Note_Block for more info.)");
        }
        return new MinecraftNote(this.getInstrument(), pitch);
    }
}
