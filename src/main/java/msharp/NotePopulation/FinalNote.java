package msharp.NotePopulation;

import msharp.MinecraftClasses.Instrument;

public class FinalNote implements Comparable<FinalNote>{
    Instrument instrument;
    int octave;
    ToneEnum tone;
    float timing;

    public FinalNote(Instrument instrument, ToneEnum tone, int octave, float timing) {
        this.instrument = instrument;
        this.tone = tone;
        this.octave = octave;
        this.timing = timing;
    }

    public void transpose(int amount){
        // determine if we are stepping postive or negative steps.
        int direction = amount > 0 ? 1 : -1;

        // loop the amount
        for(int i = 0; i < Math.abs(amount); i++){
            int toneId = tone.getToneId();
            toneId += direction;
            if(toneId < 0){
                this.octave --;
                toneId = 11;
            }
            if(this.tone.getToneId() + direction > 11){
                this.octave ++;
                toneId = 0;
            }
            tone = ToneEnum.fromToneId(toneId);
        }

    }

    @Override
    public int compareTo(FinalNote finalNote) {
        return Float.compare(this.timing,finalNote.timing);
    }
}
