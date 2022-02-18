package ru.sid.izk.modbus.listener;

import ru.sid.izk.modbus.archive.ReadAllDataAdapter;
import ru.sid.izk.modbus.frames.IZKModbusGUI;
import ru.sid.izk.modbus.runnables.ReadBarRunnable;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TimerReadBarActionListener implements ActionListener {
    private final IZKModbusGUI izkModbusGUI;
    private final ReadAllDataAdapter readAllDataAdapter;
    private int state = 1;



    public TimerReadBarActionListener(IZKModbusGUI izkModbusGUI, ReadAllDataAdapter readAllDataAdapter) {
        this.izkModbusGUI = izkModbusGUI;
        this.readAllDataAdapter = readAllDataAdapter;

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Timer timer = (Timer) e.getSource();
        izkModbusGUI.getExecutor().execute(new ReadBarRunnable(izkModbusGUI,readAllDataAdapter,state,timer));
        state++;
    }
}
