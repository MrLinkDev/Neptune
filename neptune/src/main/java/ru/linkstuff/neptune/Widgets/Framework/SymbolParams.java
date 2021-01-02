package ru.linkstuff.neptune.Widgets.Framework;

public class SymbolParams {
    public static int getSymbolId(char c) {
        switch (c) {
            case ' ': return 1;
            case '0': return 2;
            case '1': return 3;
            case '2': return 4;
            case '3': return 5;
            case '4': return 6;
            case '5': return 7;
            case '6': return 8;
            case '7': return 9;
            case '8': return 10;
            case '9': return 11;
            case '.': return 12;
            case '?': return 13;
            case '!': return 14;
            case ':': return 15;
            case '(': return 16;
            case ')': return 17;
            case '%': return 18;
            case '-': return 19;
            case '\'': return 20;
            case '_': return 21;
            case 'a': return 22;
            case 'b': return 23;
            case 'c': return 24;
            case 'd': return 25;
            case 'e': return 26;
            case 'f': return 27;
            case 'g': return 28;
            case 'h': return 29;
            case 'i': return 30;
            case 'j': return 31;
            case 'k': return 32;
            case 'l': return 33;
            case 'm': return 34;
            case 'n': return 35;
            case 'o': return 36;
            case 'p': return 37;
            case 'q': return 38;
            case 'r': return 39;
            case 's': return 40;
            case 't': return 41;
            case 'u': return 42;
            case 'v': return 43;
            case 'w': return 44;
            case 'x': return 45;
            case 'y': return 46;
            case 'z': return 47;
            case 'а': return 48;
            case 'б': return 49;
            case 'в': return 50;
            case 'г': return 51;
            case 'д': return 52;
            case 'е': return 53;
            case 'ё': return 54;
            case 'ж': return 55;
            case 'з': return 56;
            case 'и': return 57;
            case 'й': return 58;
            case 'к': return 59;
            case 'л': return 60;
            case 'м': return 61;
            case 'н': return 62;
            case 'о': return 63;
            case 'п': return 64;
            case 'р': return 65;
            case 'с': return 66;
            case 'т': return 67;
            case 'у': return 68;
            case 'ф': return 69;
            case 'х': return 70;
            case 'ц': return 71;
            case 'ч': return 72;
            case 'ш': return 73;
            case 'щ': return 74;
            case 'ь': return 75;
            case 'ы': return 76;
            case 'ъ': return 77;
            case 'э': return 78;
            case 'ю': return 79;
            case 'я': return 80;

            default: return 0;
        }
    }

    public static int getSymbolWidth(int charId){
        switch (charId){
            case 3: case 19: case 30: case 33: case 41: case 46: case 51: case 67: return 12;
            case 52: case 74: case 79: return 16;
            case 12: case 15: case 20: return 4;
            case 16: case 17: case 1: return 8;
            case 14: return 6;
            case 0: return 10;
            case 76: return 18;

            default: return 14;
        }
    }

    public static int getSymbolHeight(){
        return 18;
    }
}
