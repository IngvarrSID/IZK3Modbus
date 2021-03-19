package ru.sid.izk.modbus.listener;

import ru.sid.izk.modbus.connection.ModbusReader;
import ru.sid.izk.modbus.frames.IZKModbusGUI;

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class QueryBoxItemListener implements ItemListener {

    private final IZKModbusGUI izkModbusGUI;
    private final ModbusReader modbusReader;

    public QueryBoxItemListener(IZKModbusGUI izkModbusGUI, ModbusReader modbusReader) {

        this.izkModbusGUI = izkModbusGUI;
        this.modbusReader = modbusReader;
    }

    @Override
    public void itemStateChanged(ItemEvent e) {

        final Timer timer1 = izkModbusGUI.getTimer1();
        if (izkModbusGUI.getQueryBox().isSelected()) {
            try {
                modbusReader.writeModeRegister(1, izkModbusGUI.getChannelsBox().getSelectedIndex());
                timer1.start();
            } catch (Exception e1) {
                e1.printStackTrace();
                timer1.stop();
                JOptionPane.showMessageDialog(izkModbusGUI,
                        "Ошибка инициализации " + e1.getMessage(), "Ошибка", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            timer1.stop();
            System.out.println("Опрос завершен");
        }
    }
}