package ru.sid.izk.modbus.utils;

public final class FromHexUtils {

    public static String stringFromHex(float f) {
        return String.format("0x%8s", Integer.toHexString(Float.floatToIntBits(f))).replace(' ', '0');
    }
}