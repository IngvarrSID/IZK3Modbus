package ru.sid.izk.modbus.listener;

import ru.sid.izk.modbus.connection.ModbusReader;
import ru.sid.izk.modbus.frames.IZKModbusGUI;
import ru.sid.izk.modbus.runnables.TwoRegisterWriteRunnable;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static ru.sid.izk.modbus.utils.FromHexUtils.hexStringFromField;

public class TwoRegisterWriteActionListener implements ActionListener {

    private final int offset;
    private final IZKModbusGUI izkModbusGUI;
    private final ModbusReader modbusReader;

    public TwoRegisterWriteActionListener(int offset, IZKModbusGUI izkModbusGUI, ModbusReader modbusReader) {
        this.offset = offset;
        this.izkModbusGUI = izkModbusGUI;
        this.modbusReader = modbusReader;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        final JTextField field = ((JTextField) e.getSource());
        izkModbusGUI.getExecutor().execute(new TwoRegisterWriteRunnable(offset,izkModbusGUI,modbusReader,field));

    }
}