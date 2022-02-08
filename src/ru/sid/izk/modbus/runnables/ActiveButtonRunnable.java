package ru.sid.izk.modbus.runnables;

import ru.sid.izk.modbus.connection.ModbusReader;
import ru.sid.izk.modbus.frames.IZKModbusGUI;

import javax.swing.*;

public class ActiveButtonRunnable implements Runnable{

    private final IZKModbusGUI izkModbusGUI;
    private final ModbusReader modbusReader;

    public ActiveButtonRunnable(IZKModbusGUI izkModbusGUI, ModbusReader modbusReader) {
        this.izkModbusGUI = izkModbusGUI;
        this.modbusReader = modbusReader;
    }

    @Override
    public void run() {
        System.out.println("Активация");
        processAction();
    }

    public void processAction(){
        izkModbusGUI.getActivButton().setEnabled(false);
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
                izkModbusGUI.getActivButton().setEnabled(true);
            } catch (Exception e1) {
                e1.printStackTrace();
                JOptionPane.showMessageDialog(izkModbusGUI,
                        "Ошибка записи! " + e1.getMessage(), "Ошибка", JOptionPane.ERROR_MESSAGE);
                passwordField.setText("Ошибка записи");
                izkModbusGUI.getActivButton().setEnabled(true);
            }
        } else {
            passwordField.setText("Не верный код активации!");
            izkModbusGUI.getActivButton().setEnabled(true);
        }
    }
}
