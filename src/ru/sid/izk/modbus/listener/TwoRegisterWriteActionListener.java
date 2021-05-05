package ru.sid.izk.modbus.listener;

import ru.sid.izk.modbus.connection.ModbusReader;
import ru.sid.izk.modbus.frames.IZKModbusGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static ru.sid.izk.modbus.utils.FromHexUtils.hexStringFromField;

public class TwoRegisterWriteActionListener implements ActionListener {

    private final int offset;
    private final IZKModbusGUI izkModbusGUI;
    private final ModbusReader modbusReader;

    public TwoRegisterWriteActionListener(int offset, IZKModbusGUI izkModbusGUI, ModbusReader modbusReader) {
        this.offset = offset;
        this.izkModbusGUI = izkModbusGUI;
        this.modbusReader = modbusReader;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        final String field = ((JTextField) e.getSource()).getText();
        String sF = hexStringFromField(field);
        int[] registers = {Integer.valueOf(sF.substring(4, 8), 16), Integer.valueOf(sF.substring(0, 4), 16)};
        for (int i : registers) {
            System.out.println(i);
        }
        try {
            modbusReader.writeRegister(offset, registers);
            JOptionPane.showMessageDialog(izkModbusGUI,
                    "Запись завершена успешно.", "Подтверждение", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(izkModbusGUI,
                    "Ошибка записи! " + ex.getMessage(), "Ошибка", JOptionPane.ERROR_MESSAGE);
        }
    }
}