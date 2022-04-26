package ru.sid.izk.modbus.listener;

import ru.sid.izk.modbus.connection.ModbusReader;
import ru.sid.izk.modbus.frames.IZKModbusGUI;
import ru.sid.izk.modbus.runnables.EraseTimerRunnable;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EraseTimerActionListener implements ActionListener {

    private final IZKModbusGUI izkModbusGUI;
    private final ModbusReader modbusReader;
    private JProgressBar progressBar;


    public EraseTimerActionListener(IZKModbusGUI izkModbusGUI, ModbusReader modbusReader) {
        this.izkModbusGUI = izkModbusGUI;
        this.modbusReader = modbusReader;
        progressBar = izkModbusGUI.getProgressBar();
        progressBar.setMinimum(0);
        progressBar.setMaximum(100);
        progressBar.setVisible(true);
        progressBar.setValue(0);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        izkModbusGUI.getExecutor().execute(new EraseTimerRunnable(izkModbusGUI,modbusReader,e));

    }
}
