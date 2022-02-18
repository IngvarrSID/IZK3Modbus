package ru.sid.izk.modbus.runnables;

import ru.sid.izk.modbus.connection.ModbusReader;
import ru.sid.izk.modbus.frames.IZKModbusGUI;

import javax.swing.*;

public class OneRegisterWriteRunnable implements Runnable{

    private final int offset;
    private final IZKModbusGUI izkModbusGUI;
    private final ModbusReader modbusReader;
    private final JTextField field;

    public OneRegisterWriteRunnable(int offset, IZKModbusGUI izkModbusGUI, ModbusReader modbusReader,JTextField field) {
        this.offset = offset;
        this.izkModbusGUI = izkModbusGUI;
        this.modbusReader = modbusReader;
        this.field = field;
    }

    @Override
    public void run() {
        processAction();
    }

    private void processAction(){
        try {
            field.setEnabled(false);
            modbusReader.writeModeRegister(offset, Integer.parseInt(field.getText()));
            JOptionPane.showMessageDialog(izkModbusGUI,
                    "Запись завершена", "Подтверждение", JOptionPane.INFORMATION_MESSAGE);
            field.setEnabled(true);
        } catch (Exception e2) {
            e2.printStackTrace();
            JOptionPane.showMessageDialog(izkModbusGUI,
                    "Ошибка записи! " + e2.getMessage(), "Ошибка", JOptionPane.ERROR_MESSAGE);
            field.setEnabled(true);

        }
    }
}
