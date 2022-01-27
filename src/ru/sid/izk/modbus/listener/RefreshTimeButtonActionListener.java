package ru.sid.izk.modbus.listener;

import com.sun.org.apache.xpath.internal.operations.Mod;
import ru.sid.izk.modbus.connection.ModbusReader;
import ru.sid.izk.modbus.entity.Query;
import ru.sid.izk.modbus.frames.IZKModbusGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RefreshTimeButtonActionListener implements ActionListener {

    private final Query query;
    private final IZKModbusGUI izkModbusGUI;
    private final ModbusReader modbusReader;

    public RefreshTimeButtonActionListener(Query query, IZKModbusGUI izkModbusGUI,ModbusReader modbusReader) {

        this.query = query;
        this.izkModbusGUI = izkModbusGUI;
        this.modbusReader = modbusReader;
    }
    @Override
    public void actionPerformed(ActionEvent e) {

        try {
            modbusReader.writeModeRegister(0, 23);

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(izkModbusGUI,
                    "Ошибка инициализации " + ex.getMessage(), "Ошибка", JOptionPane.ERROR_MESSAGE);
        }

        try {
            query.queryTime();
            izkModbusGUI.getDayWriteTextField().setText(correctDateTime(query.getDay()));
            izkModbusGUI.getMonthWriteTextField().setText(correctDateTime(query.getMonth()));
            izkModbusGUI.getYearWriteTextField().setText(correctDateTime(query.getYear()));
            izkModbusGUI.getHourWriteTextField().setText(correctDateTime(query.getHour()));
            izkModbusGUI.getMinuteWriteTextField().setText(correctDateTime(query.getMinute()));
            izkModbusGUI.getSecondWriteTextField().setText(correctDateTime(query.getSecond()));
        } catch (Exception ex){
            ex.printStackTrace();
            JOptionPane.showMessageDialog(izkModbusGUI,
                    "Ошибка чтения " + ex.getMessage(), "Ошибка", JOptionPane.ERROR_MESSAGE);
        }
    }

    private String correctDateTime(int date){
        if(date<10) return "0"+ date;
        else return String.valueOf(date);
    }
}
