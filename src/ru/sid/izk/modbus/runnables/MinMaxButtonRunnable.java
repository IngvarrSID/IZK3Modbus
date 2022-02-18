package ru.sid.izk.modbus.runnables;

import ru.sid.izk.modbus.connection.ModbusReader;
import ru.sid.izk.modbus.frames.IZKModbusGUI;

import javax.swing.*;

import static ru.sid.izk.modbus.utils.FromHexUtils.hexStringFromField;

public class MinMaxButtonRunnable implements Runnable{

    private final IZKModbusGUI izkModbusGUI;
    private final ModbusReader modbusReader;
    private final int i;
    private final JButton button;

    public MinMaxButtonRunnable(IZKModbusGUI izkModbusGUI, ModbusReader modbusReader, int i, JButton button) {
        this.izkModbusGUI = izkModbusGUI;
        this.modbusReader = modbusReader;
        this.i = i;
        this.button = button;
    }

    @Override
    public void run() {
        processAction();
    }

    private void processAction(){
        button.setEnabled(false);
        String field = "";
        if (i ==0) field = izkModbusGUI.getAutoMinFieldWrite().getText();
        else   field = izkModbusGUI.getAutoMaxFieldWrite().getText();

        String sF = hexStringFromField(field);
        int[] registers = {Integer.valueOf(sF.substring(4, 8), 16), Integer.valueOf(sF.substring(0, 4), 16)};
        try {
            if (i == 0) modbusReader.writeRegister(38, registers);
            else modbusReader.writeRegister(40,registers);
            JOptionPane.showMessageDialog(izkModbusGUI,
                    "Запись завершена", "Подтверждение", JOptionPane.INFORMATION_MESSAGE);
            button.setEnabled(true);
            izkModbusGUI.getRefreshSensorButton().doClick();
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(izkModbusGUI,
                    "Ошибка записи! " + ex.getMessage(), "Ошибка", JOptionPane.ERROR_MESSAGE);
            button.setEnabled(true);
        }
    }
}
