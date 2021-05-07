package ru.sid.izk.modbus.utils;

import ru.sid.izk.modbus.frames.IZKModbusGUI;

public final class FieldVisible {

    private static boolean status;

    public static void toggleFields(IZKModbusGUI izkModbusGUI, Boolean bool){
        izkModbusGUI.getPidErrField().setVisible(bool);
        izkModbusGUI.getPidDifField().setVisible(bool);
        izkModbusGUI.getPidIntField().setVisible(bool);
        izkModbusGUI.getProgressRegulatorBar().setVisible(bool);
        izkModbusGUI.getRegulatorStatusField().setVisible(bool);
        status = bool;
    }

    public static boolean isStatus() {
        return status;
    }
}
