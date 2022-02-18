package ru.sid.izk.modbus.listener;

import com.intelligt.modbus.jlibmodbus.exception.ModbusIOException;
import com.intelligt.modbus.jlibmodbus.exception.ModbusNumberException;
import com.intelligt.modbus.jlibmodbus.exception.ModbusProtocolException;
import ru.sid.izk.modbus.archive.ReadAllDataAdapter;
import ru.sid.izk.modbus.connection.ModbusReader;
import ru.sid.izk.modbus.frames.IZKModbusGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TimerWriteBarActionListener implements ActionListener {
    private final IZKModbusGUI izkModbusGUI;
    private final ReadAllDataAdapter readAllDataAdapter;
    private final ModbusReader modbusReader;
    private int state;

    public TimerWriteBarActionListener(IZKModbusGUI izkModbusGUI, ReadAllDataAdapter readAllDataAdapter, ModbusReader modbusReader, int state) {
        this.izkModbusGUI = izkModbusGUI;
        this.readAllDataAdapter = readAllDataAdapter;
        this.modbusReader = modbusReader;
        this.state = state;
    }

    @Override
    public void actionPerformed(ActionEvent e) {


    }


}
