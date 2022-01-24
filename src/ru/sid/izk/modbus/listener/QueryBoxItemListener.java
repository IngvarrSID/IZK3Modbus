package ru.sid.izk.modbus.listener;

import ru.sid.izk.modbus.connection.ModbusReader;
import ru.sid.izk.modbus.frames.IZKModbusGUI;

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class QueryBoxItemListener implements ItemListener {

    private final IZKModbusGUI izkModbusGUI;
    private final ModbusReader modbusReader;

    public QueryBoxItemListener(IZKModbusGUI izkModbusGUI, ModbusReader modbusReader) {

        this.izkModbusGUI = izkModbusGUI;
        this.modbusReader = modbusReader;
    }

    @Override
    public void itemStateChanged(ItemEvent e) {

        final Timer timer1 = izkModbusGUI.getConnectionTimeoutTimer();
        if (izkModbusGUI.getQueryBox().isSelected()) {
            try {
                modbusReader.writeModeRegister(1, izkModbusGUI.getChannelsBox().getSelectedIndex());
                timer1.start();
                izkModbusGUI.getStatLabel().setText("Идет опрос");
            } catch (Exception e1) {
                e1.printStackTrace();
                timer1.stop();
                JOptionPane.showMessageDialog(izkModbusGUI,
                        "Ошибка инициализации " + e1.getMessage(), "Ошибка", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            timer1.stop();
            System.out.println("Опрос завершен");
            izkModbusGUI.getSensorAddressField().setText("");
            izkModbusGUI.getHumidityField().setText("");
            izkModbusGUI.getTemperatureField().setText("");
            izkModbusGUI.getDensityField().setText("");
            izkModbusGUI.getPeriodField().setText("");
            izkModbusGUI.getCs1Field().setText("");
            izkModbusGUI.getCs2Field().setText("");
            izkModbusGUI.getErrorField().setText("");
            izkModbusGUI.getDataField().setText("");
            izkModbusGUI.getTimeField().setText("");
            izkModbusGUI.getStatLabel().setText("Нет информации");
            izkModbusGUI.getPidErrField().setText("");
            izkModbusGUI.getPidIntField().setText("");
            izkModbusGUI.getPidDifField().setText("");
            izkModbusGUI.getProgressRegulatorBar().setValue(0);
            izkModbusGUI.getRegulatorStatusField().setText("");
        }
    }
}