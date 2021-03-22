package ru.sid.izk.modbus.listener;

import ru.sid.izk.modbus.entity.Query;
import ru.sid.izk.modbus.frames.IZKModbusGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChannelsButtonActionListener implements ActionListener {
    private final IZKModbusGUI izkModbusGUI;
    private final Query query;
    private final int channelNumber;

    public ChannelsButtonActionListener(IZKModbusGUI izkModbusGUI, int channelNumber, Query query) {
        this.izkModbusGUI = izkModbusGUI;
        this.channelNumber = channelNumber;
        this.query = query;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        

    }
}
