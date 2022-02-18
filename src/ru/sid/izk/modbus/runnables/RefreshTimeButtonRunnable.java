package ru.sid.izk.modbus.runnables;

import ru.sid.izk.modbus.connection.ModbusReader;
import ru.sid.izk.modbus.entity.Query;
import ru.sid.izk.modbus.frames.IZKModbusGUI;

import javax.swing.*;

public class RefreshTimeButtonRunnable implements Runnable{

    private final Query query;
    private final IZKModbusGUI izkModbusGUI;
    private final ModbusReader modbusReader;

    public RefreshTimeButtonRunnable(Query query, IZKModbusGUI izkModbusGUI, ModbusReader modbusReader) {
        this.query = query;
        this.izkModbusGUI = izkModbusGUI;
        this.modbusReader = modbusReader;
    }

    @Override
    public void run() {
        processAction();
    }

    private void processAction(){

        try {
            izkModbusGUI.getRefreshTimeButton().setEnabled(false);
            modbusReader.writeModeRegister(0, 23);
            query.queryTime();
            izkModbusGUI.getDayWriteTextField().setText(correctDateTime(query.getDay()));
            izkModbusGUI.getMonthWriteTextField().setText(correctDateTime(query.getMonth()));
            izkModbusGUI.getYearWriteTextField().setText(correctDateTime(query.getYear()));
            izkModbusGUI.getHourWriteTextField().setText(correctDateTime(query.getHour()));
            izkModbusGUI.getMinuteWriteTextField().setText(correctDateTime(query.getMinute()));
            izkModbusGUI.getSecondWriteTextField().setText(correctDateTime(query.getSecond()));
            izkModbusGUI.getRefreshTimeButton().setEnabled(true);
        } catch (Exception ex){
            ex.printStackTrace();
            JOptionPane.showMessageDialog(izkModbusGUI,
                    "Ошибка чтения " + ex.getMessage(), "Ошибка", JOptionPane.ERROR_MESSAGE);
            izkModbusGUI.getRefreshTimeButton().setEnabled(true);
        }
    }


    private String correctDateTime(int date){
        if(date<10) return "0"+ date;
        else return String.valueOf(date);
    }
}
