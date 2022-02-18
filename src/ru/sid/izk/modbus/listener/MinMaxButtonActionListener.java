package ru.sid.izk.modbus.listener;

import ru.sid.izk.modbus.connection.ModbusReader;
import ru.sid.izk.modbus.frames.IZKModbusGUI;
import ru.sid.izk.modbus.runnables.MinMaxButtonRunnable;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MinMaxButtonActionListener implements ActionListener {

    private final IZKModbusGUI izkModbusGUI;
    private final ModbusReader modbusReader;
    private final int i;

    public MinMaxButtonActionListener(IZKModbusGUI izkModbusGUI, ModbusReader modbusReader, int i) {

        this.izkModbusGUI = izkModbusGUI;
        this.modbusReader = modbusReader;
        this.i = i;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        JButton currentButton = (JButton) e.getSource();
        izkModbusGUI.getExecutor().execute(new MinMaxButtonRunnable(izkModbusGUI,modbusReader,i,currentButton));
    }

}