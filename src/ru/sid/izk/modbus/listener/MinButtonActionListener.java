package ru.sid.izk.modbus.listener;

import ru.sid.izk.modbus.connection.ModbusReader;
import ru.sid.izk.modbus.frames.IZKModbusGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static ru.sid.izk.modbus.utils.FromHexUtils.hexStringFromField;

public class MinButtonActionListener implements ActionListener {

    private final IZKModbusGUI izkModbusGUI;
    private final ModbusReader modbusReader;

    public MinButtonActionListener(IZKModbusGUI izkModbusGUI, ModbusReader modbusReader) {

        this.izkModbusGUI = izkModbusGUI;
        this.modbusReader = modbusReader;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        final String field = izkModbusGUI.getAutoMinFieldWrite().getText();
        String sF = hexStringFromField(field);
        int[] registers = {Integer.valueOf(sF.substring(4, 8), 16), Integer.valueOf(sF.substring(0, 4), 16)};
        for (int i : registers) {
            System.out.println(i);
        }
        try {
            modbusReader.writeRegister(38, registers);
            JOptionPane.showMessageDialog(izkModbusGUI,
                    "Запись завершена! Изменения появятся\nпосле получения новых данных.", "Подтверждение", JOptionPane.INFORMATION_MESSAGE);
            izkModbusGUI.getRefreshSensorButton().doClick();
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(izkModbusGUI,
                    "Ошибка записи! " + ex.getMessage(), "Ошибка", JOptionPane.ERROR_MESSAGE);
        }
    }

}