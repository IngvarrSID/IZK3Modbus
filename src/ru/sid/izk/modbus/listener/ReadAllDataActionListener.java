package ru.sid.izk.modbus.listener;

import ru.sid.izk.modbus.archive.ReadAllDataAdapter;
import ru.sid.izk.modbus.frames.IZKModbusGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ReadAllDataActionListener implements ActionListener {

    private final ReadAllDataAdapter readAllDataAdapter;
    private final IZKModbusGUI izkModbusGUI;

    public ReadAllDataActionListener(ReadAllDataAdapter readAllDataAdapter, IZKModbusGUI izkModbusGUI) {
        this.readAllDataAdapter = readAllDataAdapter;
        this.izkModbusGUI = izkModbusGUI;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        final JCheckBox queryBox = izkModbusGUI.getQueryBox();
        if (queryBox.isSelected()) queryBox.setSelected(false);
        izkModbusGUI.getProgressBar().setValue(0);
        izkModbusGUI.getProgressBar().setVisible(true);
        izkModbusGUI.getProgressBar().setMaximum(6);
        Timer timer = new Timer(1000, new TimerReadBarActionListener(izkModbusGUI, readAllDataAdapter));
        timer.start();

    }
}
