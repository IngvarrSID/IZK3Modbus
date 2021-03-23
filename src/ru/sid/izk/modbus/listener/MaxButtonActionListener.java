package ru.sid.izk.modbus.listener;

import ru.sid.izk.modbus.connection.ModbusReader;
import ru.sid.izk.modbus.frames.IZKModbusGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static ru.sid.izk.modbus.utils.FromHexUtils.stringFromHex;

public class MaxButtonActionListener implements ActionListener {

    private final IZKModbusGUI izkModbusGUI;
    private final ModbusReader modbusReader;

    public MaxButtonActionListener(IZKModbusGUI izkModbusGUI, ModbusReader modbusReader) {

        this.izkModbusGUI = izkModbusGUI;
        this.modbusReader = modbusReader;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        final String field = izkModbusGUI.getAutoMaxFieldWrite().getText();
        String s;
        if (field.contains(",")) s = field.replace(",", ".");
        else s = field;
        float f = Float.parseFloat(s);
        String sF = stringFromHex(f);
        sF = sF.replace("0x", "");
        int[] registers = {Integer.valueOf(sF.substring(4, 8), 16), Integer.valueOf(sF.substring(0, 4), 16)};
        for (int i : registers) {
            System.out.println(i);
        }
        try {
            modbusReader.writeRegister(40, registers);
            JOptionPane.showMessageDialog(izkModbusGUI,
                    "Запись завершена", "Подтверждение", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(izkModbusGUI,
                    "Ошибка записи! " + ex.getMessage(), "Ошибка", JOptionPane.ERROR_MESSAGE);
        }
    }

}