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

        try {
            izkModbusGUI.getProgressBar().setVisible(true);
            izkModbusGUI.getProgressBar().setMaximum(10);
            //TODO прогресс бар не работает тут почему то, я уже и методы разделил и чего только не делал:(
            readAllDataAdapter.readSettingsData();
            izkModbusGUI.getProgressBar().setValue(1);
            readAllDataAdapter.readRegulatorData();
            izkModbusGUI.getProgressBar().setValue(2);
            readAllDataAdapter.readSensorOneData();
            izkModbusGUI.getProgressBar().setValue(4);
            readAllDataAdapter.readSensorTwoData();
            izkModbusGUI.getProgressBar().setValue(6);
            readAllDataAdapter.readSensorThreeData();
            izkModbusGUI.getProgressBar().setValue(8);
            readAllDataAdapter.readSensorFourData();
            izkModbusGUI.getProgressBar().setValue(10);
            JOptionPane.showMessageDialog(izkModbusGUI, "Чтение завершено!", "Подтверждение", JOptionPane.INFORMATION_MESSAGE);
            izkModbusGUI.getProgressBar().setVisible(false);
            readAllDataAdapter.setDataUpDate(true);
            izkModbusGUI.getDataLabel().setText("Данные загружены");
        } catch (Exception ex) {
            ex.printStackTrace();

        }


    }

}
