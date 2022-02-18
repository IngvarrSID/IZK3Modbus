package ru.sid.izk.modbus.listener;


import ru.sid.izk.modbus.connection.ModbusReader;
import ru.sid.izk.modbus.entity.Query;
import ru.sid.izk.modbus.frames.IZKModbusGUI;
import ru.sid.izk.modbus.runnables.RefreshSettingsButtonRunnable;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RefreshSettingsButtonActionListener implements ActionListener {

    private final Query query;
    private final ModbusReader modbusReader;
    private final IZKModbusGUI izkModbusGUI;

    public RefreshSettingsButtonActionListener(Query query, ModbusReader modbusReader, IZKModbusGUI izkModbusGUI) {
        this.query = query;
        this.modbusReader = modbusReader;
        this.izkModbusGUI = izkModbusGUI;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        izkModbusGUI.getExecutor().execute(new RefreshSettingsButtonRunnable(query,modbusReader,izkModbusGUI));
    }
}