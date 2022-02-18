package ru.sid.izk.modbus.listener;

import ru.sid.izk.modbus.connection.ModbusReader;
import ru.sid.izk.modbus.entity.Query;
import ru.sid.izk.modbus.frames.IZKModbusGUI;
import ru.sid.izk.modbus.runnables.RefreshRegulatorButtonRunnable;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RefreshRegulatorButtonActionListener implements ActionListener {

    private final Query query;
    private final ModbusReader modbusReader;
    private final IZKModbusGUI izkModbusGUI;

    public RefreshRegulatorButtonActionListener(Query query,ModbusReader modbusReader,IZKModbusGUI izkModbusGUI){
        this.query = query;
        this.modbusReader = modbusReader;
        this.izkModbusGUI = izkModbusGUI;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        izkModbusGUI.getExecutor().execute(new RefreshRegulatorButtonRunnable(query,modbusReader,izkModbusGUI));


    }
}
