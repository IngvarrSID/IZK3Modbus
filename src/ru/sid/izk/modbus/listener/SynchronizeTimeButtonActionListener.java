package ru.sid.izk.modbus.listener;

import ru.sid.izk.modbus.connection.ModbusReader;
import ru.sid.izk.modbus.frames.IZKModbusGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SynchronizeTimeButtonActionListener implements ActionListener {

    private final IZKModbusGUI izkModbusGUI;
    private final ModbusReader modbusReader;

    public SynchronizeTimeButtonActionListener(IZKModbusGUI izkModbusGUI, ModbusReader modbusReader) {
        this.izkModbusGUI = izkModbusGUI;
        this.modbusReader = modbusReader;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String[] timeDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("d:MM:yy:HH:mm:ss")).split(":");
        int [] data = new int[6];
        for (int i = 0; i < timeDate.length; i++) {
            data[i] = Integer.parseInt(timeDate[i]);
        }
        try {
            modbusReader.writeModeRegister(0,23);
            modbusReader.writeMultipleRegisters(2, data);

            JOptionPane.showMessageDialog(izkModbusGUI,
                    "Время блока ИЗК-3 синхронизировано с ПК", "Подтверждение", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception ex){
            ex.printStackTrace();
            JOptionPane.showMessageDialog(izkModbusGUI,
                    "Ошибка чтения " + ex.getMessage(), "Ошибка", JOptionPane.ERROR_MESSAGE);
        }

    }
}
