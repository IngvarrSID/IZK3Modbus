package ru.sid.izk.modbus.listener;

import ru.sid.izk.modbus.connection.ModbusReader;
import ru.sid.izk.modbus.frames.IZKModbusGUI;
import ru.sid.izk.modbus.runnables.ComboBoxRelayRunnable;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ComboBoxActionListener implements ActionListener {

    private final IZKModbusGUI izkModbusGUI;
    private final ModbusReader modbusReader;
    int offset;
    boolean isNumber;

    public ComboBoxActionListener(IZKModbusGUI izkModbusGUI, ModbusReader modbusReader, int offset, boolean isNumber) {
        this.izkModbusGUI = izkModbusGUI;
        this.modbusReader = modbusReader;
        this.offset = offset;
        this.isNumber = isNumber;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        Runnable tusk = new ComboBoxRelayRunnable(izkModbusGUI,modbusReader,isNumber,offset,(JComboBox<?>) e.getSource());
        izkModbusGUI.getExecutor().execute(tusk);
    }
}
