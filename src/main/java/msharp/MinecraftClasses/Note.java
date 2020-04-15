package msharp.MinecraftClasses;

public class Note {
    private Instrument instrument;
    private int pitch;

    public Note(Instrument instrument, int pitch) {
        this.instrument = instrument;
        this.pitch = pitch;
    }

    public Instrument getInstrument() {
        return instrument;
    }

    public void setInstrument(Instrument instrument) {
        this.instrument = instrument;
    }

    public int getPitch() {
        return pitch;
    }

    public void setPitch(int pitch) {
        this.pitch = pitch;
    }
}
