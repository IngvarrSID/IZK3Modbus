package ru.sid.izk.modbus.listener;

import ru.sid.izk.modbus.connection.ModbusReader;
import ru.sid.izk.modbus.frames.IZKModbusGUI;
import ru.sid.izk.modbus.runnables.ActiveButtonRunnable;

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

        Runnable tusk = new ActiveButtonRunnable(izkModbusGUI,modbusReader);
        izkModbusGUI.getExecutor().execute(tusk);
    }
}