package ru.sid.izk.modbus.listener;

import ru.sid.izk.modbus.connection.ModbusReader;
import ru.sid.izk.modbus.frames.IZKModbusGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActivButtonActionListener implements ActionListener {

    private final IZKModbusGUI izkModbusGUI;
    private final ModbusReader modbusReader;

    public ActivButtonActionListener(IZKModbusGUI izkModbusGUI, ModbusReader modbusReader) {

        this.izkModbusGUI = izkModbusGUI;
        this.modbusReader = modbusReader;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        final JTextField passwordField = izkModbusGUI.getPasswordField();
        if (passwordField.getText().length() == 8) {
            char[] chars = passwordField.getText().toCharArray();
            int[] registers = new int[4];
            int j = 0;
            for (int i = 0; i < chars.length; i = i + 2) {
                String hex = String.format("%02x", (int) chars[i + 1]) + String.format("%02x", (int) chars[i]);
                registers[j] = Integer.valueOf(hex, 16);
                j++;
            }
            try {
                modbusReader.writeRegister(2, registers);
                JOptionPane.showMessageDialog(izkModbusGUI,
                        "Запись завершена", "", JOptionPane.INFORMATION_MESSAGE);
                izkModbusGUI.getRefButton().doClick();
            } catch (Exception e1) {
                e1.printStackTrace();
                JOptionPane.showMessageDialog(izkModbusGUI,
                        "Ошибка записи! " + e1.getMessage(), "", JOptionPane.ERROR_MESSAGE);
                passwordField.setText("Ошибка записи");
            }
        } else {
            passwordField.setText("Не верный код активации!");
        }
    }
}