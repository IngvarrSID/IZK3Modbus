package ru.sid.izk.modbus.listener;

import ru.sid.izk.modbus.connection.ModbusReader;
import ru.sid.izk.modbus.frames.IZKModbusGUI;
import ru.sid.izk.modbus.runnables.QueryBoxRunnable;

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
        izkModbusGUI.getExecutor().execute(new QueryBoxRunnable(izkModbusGUI,modbusReader));
    }
}