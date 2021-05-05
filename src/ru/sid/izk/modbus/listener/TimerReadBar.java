package ru.sid.izk.modbus.listener;

import ru.sid.izk.modbus.archive.ReadAllDataAdapter;
import ru.sid.izk.modbus.frames.IZKModbusGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TimerReadBar implements ActionListener {
    private final IZKModbusGUI izkModbusGUI;
    private final ReadAllDataAdapter readAllDataAdapter;
    private int state;



    public TimerReadBar(IZKModbusGUI izkModbusGUI, ReadAllDataAdapter readAllDataAdapter, int state) {
        this.izkModbusGUI = izkModbusGUI;
        this.readAllDataAdapter = readAllDataAdapter;
        this.state = state;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        izkModbusGUI.getProgressBar().setValue(state);
        try {
            switch (state) {
                case 1:
                    readAllDataAdapter.readSettingsData();
                    break;
                case 2:
                    readAllDataAdapter.readRegulatorData();
                    break;
                case 3:
                    readAllDataAdapter.readSensorOneData();
                    break;
                case 4:
                    readAllDataAdapter.readSensorTwoData();
                    break;
                case 5:
                    readAllDataAdapter.readSensorThreeData();
                    break;
                case 6:
                    readAllDataAdapter.readSensorFourData();
                    break;
            }
                if (izkModbusGUI.getProgressBar().getValue() == 6) {
                    ((Timer) e.getSource()).stop();
                    JOptionPane.showMessageDialog(izkModbusGUI, "Чтение всех настроек завершено!", "Подтверждение", JOptionPane.INFORMATION_MESSAGE);
                    izkModbusGUI.getProgressBar().setVisible(false);
                    readAllDataAdapter.setDataUpDate(true);
                    izkModbusGUI.getDataLabel().setForeground(new Color(0, 120, 60));
                    izkModbusGUI.getDataLabel().setText("Данные считаны");
                    izkModbusGUI.getReadAllDataAdapter().compilationData();
                } else state++;
        } catch (Exception ex) {
            ((Timer) e.getSource()).stop();
            ex.printStackTrace();
            JOptionPane.showMessageDialog(izkModbusGUI, "Ошибка чтения! " + ex.getMessage(), "Ошибка!", JOptionPane.ERROR_MESSAGE);
            izkModbusGUI.getProgressBar().setVisible(false);
            readAllDataAdapter.setDataUpDate(false);
            izkModbusGUI.getDataLabel().setForeground(Color.RED);
            izkModbusGUI.getDataLabel().setText("Ошибка чтения");
        }
    }
}
