package msharp.MinecraftClasses;

public enum Instrument {
    BASS(0),
    SNARE(1),
    HAT(2),
    BASEDRUM(3),
    BELL(4),
    FLUTE(5),
    CHIME(6),
    GUITAR(7),
    XYLOPHONE(8),
    IRON_XYLOPHONE(9),
    COW_BELL(10),
    DIDGERIDOO(11),
    BIT(12),
    BANJO(13),
    PLING(14),
    HARP(15);

    private int _ic;

    Instrument(int instrumentCode) {
        _ic = instrumentCode;
    }

    public static Instrument fromIc(int ic) {
        for (Instrument type : values()) {
            if (type.getIc() == ic) {
                return type;
            }
        }
        return null;
    }

    public int getIc() {
        return _ic;
    }

    public Block mapToBlockBeneath() {
        switch (_ic) {
            case 0:
                return new Block("minecraft:oak_log");
            case 1:
                return new Block("minecraft:sand");
            case 2:
                return new Block("minecraft:glass");
            case 3:
                return new Block("minecraft:stone");
            case 4:
                return new Block("minecraft:gold_block");
            case 5:
                return new Block("minecraft:clay");
            case 6:
                return new Block("minecraft:packed_ice");
            case 7:
                return new Block("minecraft:white_wool");
            case 8:
                return new Block("minecraft:bone_block");
            case 9:
                return new Block("minecraft:iron_block");
            case 10:
                return new Block("minecraft:soul_sand");
            case 11:
                return new Block("minecraft:pumpkin");
            case 12:
                return new Block("minecraft:emerald_block");
            case 13:
                return new Block("minecraft:hay_block");
            case 14:
                return new Block("minecraft:glowstone");
            default:
                return new Block("minecraft:air"); // harp is any other block, so might as well be air for all other cases.
        }
    }

    public static Instrument fromString(String string) throws IllegalArgumentException {
        string = string.toUpperCase();      // Just to be sure
        switch (string) {
            case "BASS":
                return Instrument.BASS;
            case "SNARE":
                return Instrument.SNARE;
            case "HAT":
                return Instrument.HAT;
            case "BASEDRUM":
                return Instrument.BASEDRUM;
            case "BELL":
                return Instrument.BELL;
            case "FLUTE":
                return Instrument.FLUTE;
            case "CHIME":
                return Instrument.CHIME;
            case "GUITAR":
                return Instrument.GUITAR;
            case "XYLOPHONE":
                return Instrument.XYLOPHONE;
            case "VIBRAPHONE":
                return Instrument.IRON_XYLOPHONE;
            case "COWBELL":
                return Instrument.COW_BELL;
            case "DIDGERIDOO":
                return Instrument.DIDGERIDOO;
            case "BIT":
                return Instrument.BIT;
            case "BANJO":
                return Instrument.BANJO;
            case "PLING":
                return Instrument.PLING;
            case "HARP":
                return Instrument.HARP;
            case "PIANO": // fuck minecraftr
                return Instrument.HARP;
            default:
                throw new IllegalArgumentException("Instrument provided was invalid ("+string+")");
        }
    }
}
