package ru.sid.izk.modbus.utils;

import ru.sid.izk.modbus.frames.IZKModbusGUI;

public final class ClearSensorFields {

    public static void clearFields(IZKModbusGUI izkModbusGUI){
        izkModbusGUI.getAddressSensorFieldWrite().setText("");
        izkModbusGUI.getTimeoutFieldWrite().setText("");
        izkModbusGUI.getPeriodFieldWrite().setText("");
        izkModbusGUI.getT01FieldWrite().setText("");
        izkModbusGUI.getCk1FieldWrite().setText("");
        izkModbusGUI.getCd1FieldWrite().setText("");
        izkModbusGUI.getCheckPeriodFieldWrite().setText("");
        izkModbusGUI.getErrorFieldWrite().setText("");
        izkModbusGUI.getCs100FieldWrite().setText("");
        izkModbusGUI.getCmFieldWrite().setText("");
        izkModbusGUI.getkFieldWrite().setText("");
        izkModbusGUI.getCs0FieldWrite().setText("");
        izkModbusGUI.getTcFieldWrite().setText("");
        izkModbusGUI.getCsMinFieldWrite().setText("");
        izkModbusGUI.gethMinFieldWrite().setText("");
        izkModbusGUI.getTsd1FieldWrite().setText("");
        izkModbusGUI.getTsd2FieldWrite().setText("");
        izkModbusGUI.getAutoMinFieldWrite().setText("");
        izkModbusGUI.getAutoMaxFieldWrite().setText("");
        izkModbusGUI.getD20FieldWrite().setText("");
        izkModbusGUI.getKdFieldWrite().setText("");
        izkModbusGUI.getMinFieldWrite().setText("");
        izkModbusGUI.getMaxFieldWrite().setText("");
        izkModbusGUI.getEmerMaxFieldWrite().setText("");
        izkModbusGUI.getNoDensityFieldWrite().setText("");
    }
}
