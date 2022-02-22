package ru.sid.izk.modbus.runnables;

import ru.sid.izk.modbus.archive.ReadAllDataAdapter;
import ru.sid.izk.modbus.frames.IZKModbusGUI;

import javax.swing.*;
import java.awt.*;

public class ReadBarRunnable implements Runnable{

    private final IZKModbusGUI izkModbusGUI;
    private final ReadAllDataAdapter readAllDataAdapter;
    private final Timer timer;
    private int state;

    public ReadBarRunnable(IZKModbusGUI izkModbusGUI, ReadAllDataAdapter readAllDataAdapter, int state, Timer timer) {
        this.izkModbusGUI = izkModbusGUI;
        this.readAllDataAdapter = readAllDataAdapter;
        this.state = state;
        this.timer = timer;
    }

    @Override
    public void run() {
        izkModbusGUI.getReadAllButton().setEnabled(false);
        processAction();
    }

    private void processAction(){
        izkModbusGUI.getProgressBar().setValue(state);
        try {
            switch (state) {
                case 1:
                    readAllDataAdapter.readSettingsData();
                    izkModbusGUI.getDataLabel().setForeground(Color.BLACK);
                    izkModbusGUI.getDataLabel().setText("Считывание..");
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
                izkModbusGUI.getReadAllButton().setEnabled(true);
                timer.stop();
                JOptionPane.showMessageDialog(izkModbusGUI, "Чтение всех настроек завершено!", "Подтверждение", JOptionPane.INFORMATION_MESSAGE);
                izkModbusGUI.getProgressBar().setVisible(false);
                readAllDataAdapter.setDataUpDate(true);
                izkModbusGUI.getDataLabel().setForeground(new Color(0, 120, 60));
                izkModbusGUI.getDataLabel().setText("Данные считаны");
                izkModbusGUI.getReadAllDataAdapter().compilationData();
            }
        } catch (Exception ex) {
            timer.stop();
            izkModbusGUI.getReadAllButton().setEnabled(true);
            ex.printStackTrace();
            JOptionPane.showMessageDialog(izkModbusGUI, "Ошибка чтения! " + ex.getMessage(), "Ошибка!", JOptionPane.ERROR_MESSAGE);
            izkModbusGUI.getProgressBar().setVisible(false);
            readAllDataAdapter.setDataUpDate(false);
            izkModbusGUI.getDataLabel().setForeground(Color.RED);
            izkModbusGUI.getDataLabel().setText("Ошибка чтения");
        }
    }
}
