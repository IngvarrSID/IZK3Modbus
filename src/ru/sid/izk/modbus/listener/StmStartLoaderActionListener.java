package ru.sid.izk.modbus.listener;


import ru.sid.izk.modbus.connection.ModbusReader;
import ru.sid.izk.modbus.frames.IZKModbusGUI;
import ru.sid.izk.modbus.runnables.StmStartLoaderRunnable;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StmStartLoaderActionListener implements ActionListener {

    private final ModbusReader modbusReader;
    private final IZKModbusGUI izkModbusGUI;

    public StmStartLoaderActionListener(ModbusReader modbusReader, IZKModbusGUI izkModbusGUI) {
        this.modbusReader = modbusReader;
        this.izkModbusGUI = izkModbusGUI;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        izkModbusGUI.getExecutor().execute(new StmStartLoaderRunnable(modbusReader,izkModbusGUI));


    }
}
