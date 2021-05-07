package ru.sid.izk.modbus.listener;

import ru.sid.izk.modbus.connection.DownloaderSerialPort;
import ru.sid.izk.modbus.connection.MasterModbus;
import ru.sid.izk.modbus.connection.Terminal;
import ru.sid.izk.modbus.frames.IZKModbusGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DownloaderActionListener implements ActionListener {

    private final IZKModbusGUI izkModbusGUI;
    private final MasterModbus masterModbus;

    public DownloaderActionListener(IZKModbusGUI izkModbusGUI, MasterModbus masterModbus) {
        this.izkModbusGUI = izkModbusGUI;
        this.masterModbus = masterModbus;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
//TODO будет переводить блок в режим загрузчика
        masterModbus.disconnect();
        new DownloaderSerialPort(String.valueOf(masterModbus.getTerminal().getComName()));
    }
}
