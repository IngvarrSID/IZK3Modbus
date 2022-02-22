package ru.sid.izk.modbus.runnables;

import ru.sid.izk.modbus.connection.ModbusReader;
import ru.sid.izk.modbus.frames.IZKModbusGUI;

import javax.swing.*;

import static ru.sid.izk.modbus.utils.FromHexUtils.hexStringFromField;

public class TwoRegisterWriteRunnable implements Runnable{

    private final int offset;
    private final IZKModbusGUI izkModbusGUI;
    private final ModbusReader modbusReader;
    private final JTextField field;

    public TwoRegisterWriteRunnable(int offset, IZKModbusGUI izkModbusGUI, ModbusReader modbusReader, JTextField field) {
        this.offset = offset;
        this.izkModbusGUI = izkModbusGUI;
        this.modbusReader = modbusReader;
        this.field = field;
    }

    @Override
    public void run() {
        field.setEnabled(false);
        processAction();
        field.setEnabled(true);

    }

    private void processAction(){

        String sF = hexStringFromField(field.getText());
        int[] registers = {Integer.valueOf(sF.substring(4, 8), 16), Integer.valueOf(sF.substring(0, 4), 16)};
        try {
            modbusReader.writeRegister(offset, registers);
            JOptionPane.showMessageDialog(izkModbusGUI,
                    "Запись завершена успешно.", "Подтверждение", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(izkModbusGUI,
                    "Ошибка записи! " + ex.getMessage(), "Ошибка", JOptionPane.ERROR_MESSAGE);
            field.setEnabled(true);
        }

    }
}
