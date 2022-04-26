package ru.sid.izk.modbus.runnables;

import ru.sid.izk.modbus.connection.ModbusReader;
import ru.sid.izk.modbus.frames.IZKModbusGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class EraseTimerRunnable implements Runnable{

    private final IZKModbusGUI izkModbusGUI;
    private final ModbusReader modbusReader;
    private final ActionEvent e;
    private JProgressBar progressBar;

    public EraseTimerRunnable(IZKModbusGUI izkModbusGUI, ModbusReader modbusReader, ActionEvent e) {
        this.izkModbusGUI = izkModbusGUI;
        this.modbusReader = modbusReader;
        this.e = e;
        progressBar = izkModbusGUI.getProgressBar();
    }

    @Override
    public void run() {
        processAction();
    }

    private void processAction(){

        if (izkModbusGUI.getExecutor().getActiveCount() < 2) {

            try {

                int[] result = modbusReader.readRegisters(9000, 1, 1);

                progressBar.setValue(result[0]);
                if (result[0] >= 100) {
                    Timer timer = (Timer) e.getSource();
                    timer.stop();
                    progressBar.setVisible(false);
                    izkModbusGUI.getDataLabel().setText("Стирание завершено");
                    izkModbusGUI.getExecutor().execute(new StmLoadNewFirmwareRunnable(izkModbusGUI, modbusReader));
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(izkModbusGUI,
                        "Ошибка связи " + ex.getMessage(), "Ошибка", JOptionPane.ERROR_MESSAGE);
            }
        }

    }
}
