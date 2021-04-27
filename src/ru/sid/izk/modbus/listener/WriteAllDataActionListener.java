package ru.sid.izk.modbus.listener;

import ru.sid.izk.modbus.archive.ReadAllDataAdapter;
import ru.sid.izk.modbus.connection.ModbusReader;
import ru.sid.izk.modbus.frames.IZKModbusGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WriteAllDataActionListener implements ActionListener {

    private final ReadAllDataAdapter readAllDataAdapter;
    private final IZKModbusGUI izkModbusGUI;
    private final ModbusReader modbusReader;

    public WriteAllDataActionListener(ReadAllDataAdapter readAllDataAdapter, IZKModbusGUI izkModbusGUI, ModbusReader modbusReader) {
        this.readAllDataAdapter = readAllDataAdapter;
        this.izkModbusGUI = izkModbusGUI;
        this.modbusReader = modbusReader;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        final JCheckBox queryBox = izkModbusGUI.getQueryBox();
        if (queryBox.isSelected()) queryBox.setSelected(false);
        izkModbusGUI.getProgressBar().setValue(0);
        izkModbusGUI.getProgressBar().setVisible(true);
        izkModbusGUI.getProgressBar().setMaximum(5);
        Timer timer = new Timer(500,new TimerWriteBar(izkModbusGUI,readAllDataAdapter,modbusReader,1));
        timer.start();
    }
}
