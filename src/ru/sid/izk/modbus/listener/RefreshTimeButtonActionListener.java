package ru.sid.izk.modbus.listener;

import com.sun.org.apache.xpath.internal.operations.Mod;
import ru.sid.izk.modbus.connection.ModbusReader;
import ru.sid.izk.modbus.entity.Query;
import ru.sid.izk.modbus.frames.IZKModbusGUI;
import ru.sid.izk.modbus.runnables.RefreshTimeButtonRunnable;

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

        izkModbusGUI.getExecutor().execute(new RefreshTimeButtonRunnable(query,izkModbusGUI,modbusReader));

    }

}
