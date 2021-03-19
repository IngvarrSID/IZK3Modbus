package ru.sid.izk.modbus.listener;

import ru.sid.izk.modbus.connection.MasterModbus;
import ru.sid.izk.modbus.frames.IZKModbusGUI;
import ru.sid.izk.modbus.frames.IZKTerminal;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TerminalButtonActionListener implements ActionListener {

    private IZKModbusGUI izkModbusGUI;
    private MasterModbus masterModbus;

    public TerminalButtonActionListener(IZKModbusGUI izkModbusGUI, MasterModbus masterModbus) {
        this.izkModbusGUI = izkModbusGUI;
        this.masterModbus = masterModbus;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        new IZKTerminal();
        izkModbusGUI.getConnectionTimeoutTimer().stop();
        masterModbus.disconnect();
        izkModbusGUI.dispose();
    }
}