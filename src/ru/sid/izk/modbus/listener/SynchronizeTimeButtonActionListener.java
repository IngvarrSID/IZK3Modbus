package ru.sid.izk.modbus.listener;

import ru.sid.izk.modbus.connection.ModbusReader;
import ru.sid.izk.modbus.entity.Query;
import ru.sid.izk.modbus.frames.IZKModbusGUI;
import ru.sid.izk.modbus.runnables.RefreshTimeButtonRunnable;
import ru.sid.izk.modbus.runnables.SynchronizeTimeButtonRunnable;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SynchronizeTimeButtonActionListener implements ActionListener {

    private final IZKModbusGUI izkModbusGUI;
    private final ModbusReader modbusReader;
    private final Query query;

    public SynchronizeTimeButtonActionListener(IZKModbusGUI izkModbusGUI, ModbusReader modbusReader,Query query) {
        this.izkModbusGUI = izkModbusGUI;
        this.modbusReader = modbusReader;
        this.query = query;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        izkModbusGUI.getExecutor().execute(new SynchronizeTimeButtonRunnable(izkModbusGUI,modbusReader,query));
    }
}
