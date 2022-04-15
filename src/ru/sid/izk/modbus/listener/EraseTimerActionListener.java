package ru.sid.izk.modbus.listener;

import ru.sid.izk.modbus.connection.ModbusReader;
import ru.sid.izk.modbus.frames.IZKModbusGUI;
import ru.sid.izk.modbus.runnables.StmLoadNewFirmwareRunnable;

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
        this.progressBar = izkModbusGUI.getProgressBar();
        progressBar.setMinimum(0);
        progressBar.setMaximum(100);
        progressBar.setVisible(true);
        progressBar.setValue(0);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        try {

            int[] result = modbusReader.readRegisters(9000, 1, 1);

            progressBar.setValue(result[0]);
            if (result[0] >= 100) {
                Timer timer = (Timer) e.getSource();
                timer.stop();
                progressBar.setVisible(false);
                izkModbusGUI.getDataLabel().setText("Стирание завершено");
                izkModbusGUI.getExecutor().execute(new StmLoadNewFirmwareRunnable(izkModbusGUI,modbusReader));
            }
        } catch (Exception ex){
            ex.printStackTrace();
        }

    }
}
