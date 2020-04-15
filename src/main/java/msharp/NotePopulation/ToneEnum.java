package msharp.NotePopulation;

public enum ToneEnum {
        C_SHARP(0),
        D(1),
        D_SHARP(2),
        E(3),
        F(4),
        F_SHARP(5),
        G(6),
        G_SHARP(7),
        A(8),
        A_SHARP(9),
        B(10),
        C(11);

        private int _toneId;

        ToneEnum(int instrumentCode) {
            _toneId = instrumentCode;
        }

        public static ToneEnum fromToneId(int ic) {
            for (ToneEnum type : values()) {
                if (type.getToneId() == ic) {
                    return type;
                }
            }
            return null;
        }

    public static ToneEnum fromLetter(char letter) {

            switch (letter){
                case 'a':return ToneEnum.A;
                case 'b':return ToneEnum.B;
                case 'c':return ToneEnum.C;
                case 'd':return ToneEnum.D;
                case 'e':return ToneEnum.E;
                case 'f':return ToneEnum.F;
                case 'g':return ToneEnum.G;
                default: throw new IllegalArgumentException("Unknown tone: "+letter);
            }
    }

    public int getToneId() {
            return _toneId;
        }
}
