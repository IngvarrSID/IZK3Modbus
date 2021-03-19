package ru.sid.izk.modbus.listener;

import ru.sid.izk.modbus.entity.Query;
import ru.sid.izk.modbus.frames.IZKModbusGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RefreshSensorButtonActionListener implements ActionListener {

    private final Query query;
    private final IZKModbusGUI izkModbusGUI;

    public RefreshSensorButtonActionListener(Query query, IZKModbusGUI izkModbusGUI) {

        this.query = query;
        this.izkModbusGUI = izkModbusGUI;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        query.querySensor();
        izkModbusGUI.getAddressSensorFieldWrite().setText(String.valueOf(query.getSensorAddressWrite()));
        izkModbusGUI.getTimeoutFieldWrite().setText(String.valueOf(query.getTimeoutWrite()));
        izkModbusGUI.getPeriodFieldWrite().setText(String.valueOf(query.getPeriodWrite()));
        izkModbusGUI.getT01FieldWrite().setText(String.valueOf(query.getT01Write()));
        izkModbusGUI.getCk1FieldWrite().setText(String.format("%.1f", query.getCk1Write()));
        izkModbusGUI.getCd1FieldWrite().setText(String.format("%.1f", query.getCd1Write()));
        izkModbusGUI.getCheckPeriodFieldWrite().setText(String.valueOf(query.getCheckPeriod()));
        izkModbusGUI.getErrorFieldWrite().setText(String.format("%.2f", query.getErrorWrite()));
        izkModbusGUI.getCs100FieldWrite().setText(String.format("%.1f", query.getCs100()));
        izkModbusGUI.getCmFieldWrite().setText(String.format("%.1f", query.getCm()));
        izkModbusGUI.getkFieldWrite().setText(String.format("%.3f", query.getK()));
        izkModbusGUI.getCs0FieldWrite().setText(String.format("%.1f", query.getCs0()));
        izkModbusGUI.getTcFieldWrite().setText(String.format("%.3f", query.getTc()));
        izkModbusGUI.getCsMinFieldWrite().setText(String.format("%.1f", query.getCsMin()));
        izkModbusGUI.gethMinFieldWrite().setText(String.format("%.1f", query.gethMin()));
        izkModbusGUI.getTsd1FieldWrite().setText(String.format("%.3f", query.getTsd1()));
        izkModbusGUI.getTsd2FieldWrite().setText(String.format("%.3f", query.getTsd2()));
        izkModbusGUI.getAutoMinFieldWrite().setText(String.format("%.1f", query.getAutoMin()));
        izkModbusGUI.getAutoMaxFieldWrite().setText(String.format("%.1f", query.getAutoMax()));
        izkModbusGUI.getD20FieldWrite().setText(String.format("%.1f", query.getD20()));
        izkModbusGUI.getKdFieldWrite().setText(String.format("%.3f", query.getKd()));
        izkModbusGUI.getMinFieldWrite().setText(String.valueOf(query.getMin()));
        izkModbusGUI.getMaxFieldWrite().setText(String.valueOf(query.getMax()));
        izkModbusGUI.getEmerMaxFieldWrite().setText(String.valueOf(query.getEmerMax()));
        izkModbusGUI.getNoDensityFieldWrite().setText(String.valueOf(query.getNoDensity()));
    }
}