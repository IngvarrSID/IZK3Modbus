package ru.sid.izk.modbus.listener;

import ru.sid.izk.modbus.connection.ModbusReader;
import ru.sid.izk.modbus.entity.Query;
import ru.sid.izk.modbus.frames.IZKModbusGUI;
import ru.sid.izk.modbus.runnables.TestButtonRunnable;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class TestButtonActionListener implements ActionListener {

    private final IZKModbusGUI izkModbusGUI;
    private final Query query;

    public TestButtonActionListener(IZKModbusGUI izkModbusGUI, Query query) {
        this.izkModbusGUI = izkModbusGUI;
        this.query = query;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        izkModbusGUI.getExecutor().execute(new TestButtonRunnable(izkModbusGUI,query));

    }
}
