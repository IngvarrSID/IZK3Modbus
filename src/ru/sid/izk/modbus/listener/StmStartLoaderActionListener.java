package ru.sid.izk.modbus.listener;

import com.intelligt.modbus.jlibmodbus.master.ModbusMaster;
import ru.sid.izk.modbus.connection.MasterModbus;
import ru.sid.izk.modbus.connection.ModbusReader;
import ru.sid.izk.modbus.frames.IZKModbusGUI;

import javax.swing.*;
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

        try {
            izkModbusGUI.getQueryBox().setSelected(false);
            modbusReader.writeCoil(37, true);

            Timer eraseTimer = new Timer(500, new EraseTimerActionListener(izkModbusGUI, modbusReader));
            eraseTimer.start();
            izkModbusGUI.getDataLabel().setText("Идет стирание");
        } catch (Exception ex)
        {
            ex.printStackTrace();
        }


    }
}
