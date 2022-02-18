package ru.sid.izk.modbus.listener;

import ru.sid.izk.modbus.connection.ModbusReader;
import ru.sid.izk.modbus.frames.IZKModbusGUI;
import ru.sid.izk.modbus.runnables.OneRegisterWriteRunnable;

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
        JTextField field = ((JTextField) e.getSource());
        izkModbusGUI.getExecutor().execute(new OneRegisterWriteRunnable(offset,izkModbusGUI,modbusReader,field));

    }
}