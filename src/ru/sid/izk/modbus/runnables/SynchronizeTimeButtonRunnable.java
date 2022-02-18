package ru.sid.izk.modbus.runnables;


import ru.sid.izk.modbus.connection.ModbusReader;
import ru.sid.izk.modbus.entity.Query;
import ru.sid.izk.modbus.frames.IZKModbusGUI;

import javax.swing.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SynchronizeTimeButtonRunnable implements Runnable {

    private final IZKModbusGUI izkModbusGUI;
    private final ModbusReader modbusReader;
    private final Query query;

    public SynchronizeTimeButtonRunnable(IZKModbusGUI izkModbusGUI, ModbusReader modbusReader, Query query) {
        this.izkModbusGUI = izkModbusGUI;
        this.modbusReader = modbusReader;
        this.query = query;
    }



    @Override
    public void run() {
        izkModbusGUI.getSynchronizeTimeButton().setEnabled(false);
        processAction();
        izkModbusGUI.getSynchronizeTimeButton().setEnabled(true);

    }

    private void processAction(){

        String[] timeDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("d:MM:yy:HH:mm:ss")).split(":");
        int [] data = new int[6];
        for (int i = 0; i < timeDate.length; i++) {
            data[i] = Integer.parseInt(timeDate[i]);
        }
        try {
            query.queryStatus();
            if (query.getActivationStatus()>1){
                JOptionPane.showMessageDialog(izkModbusGUI,
                        "Блока ИЗК-3 не активирован, синхронизация не доступна", "Ошибка", JOptionPane.ERROR_MESSAGE);
            } else {
                modbusReader.writeModeRegister(0, 23);
                modbusReader.writeMultipleRegisters(2, data);

                JOptionPane.showMessageDialog(izkModbusGUI,
                        "Время блока ИЗК-3 синхронизировано с ПК", "Подтверждение", JOptionPane.INFORMATION_MESSAGE);

                izkModbusGUI.getRefreshTimeButton().doClick();
            }
        } catch (Exception ex){
            ex.printStackTrace();
            JOptionPane.showMessageDialog(izkModbusGUI,
                    "Ошибка синхронизации " + ex.getMessage(), "Ошибка", JOptionPane.ERROR_MESSAGE);
        }
    }
}
