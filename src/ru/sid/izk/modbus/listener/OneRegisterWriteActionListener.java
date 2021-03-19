package ru.sid.izk.modbus.listener;

import ru.sid.izk.modbus.connection.ModbusReader;
import ru.sid.izk.modbus.frames.IZKModbusGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OneRegisterWriteActionListener implements ActionListener {

    private final int offset;
    private final IZKModbusGUI izkModbusGUI;
    private final ModbusReader modbusReader;

    public OneRegisterWriteActionListener(int offset, IZKModbusGUI izkModbusGUI, ModbusReader modbusReader) {
        this.offset = offset;
        this.izkModbusGUI = izkModbusGUI;
        this.modbusReader = modbusReader;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        final String field = ((JTextField) e.getSource()).getText();
        try {
            modbusReader.writeModeRegister(offset, Integer.parseInt(field));
            JOptionPane.showMessageDialog(izkModbusGUI,
                    "Запись завершена", "Подтверждение", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e2) {
            e2.printStackTrace();
            JOptionPane.showMessageDialog(izkModbusGUI,
                    "Ошибка записи! " + e2.getMessage(), "Ошибка", JOptionPane.ERROR_MESSAGE);

        }
    }
}