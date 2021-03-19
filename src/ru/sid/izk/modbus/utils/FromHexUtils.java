package ru.sid.izk.modbus.utils;

public final class FromHexUtils {

    //TODO method with int argument used only here, remove it.
    public static String stringFromHex(int n) {
        return String.format("0x%8s", Integer.toHexString(n)).replace(' ', '0');
    }

    public static String stringFromHex(float f) {
        return stringFromHex(Float.floatToIntBits(f));
    }
}