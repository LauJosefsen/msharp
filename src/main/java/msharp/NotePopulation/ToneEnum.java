package msharp.NotePopulation;

public enum ToneEnum {

        C(0),
        C_SHARP(1),
        D(2),
        D_SHARP(3),
        E(4),
        F(5),
        F_SHARP(6),
        G(7),
        G_SHARP(8),
        A(9),
        A_SHARP(10),
        B(11);

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
