package ru.sid.izk.modbus.listener;

import ru.sid.izk.modbus.connection.ModbusReader;
import ru.sid.izk.modbus.entity.Query;
import ru.sid.izk.modbus.frames.IZKModbusGUI;
import ru.sid.izk.modbus.runnables.ChannelsButtonRunnable;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static ru.sid.izk.modbus.utils.BitsReversUtils.bitsReader;

public class ChannelsButtonActionListener implements ActionListener {
    private final IZKModbusGUI izkModbusGUI;
    private final Query query;
    private final int channelNumber;
    private final ModbusReader modbusReader;

    public ChannelsButtonActionListener(IZKModbusGUI izkModbusGUI, int channelNumber, Query query, ModbusReader modbusReader) {
        this.izkModbusGUI = izkModbusGUI;
        this.channelNumber = channelNumber;
        this.query = query;
        this.modbusReader = modbusReader;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        Runnable tusk = new ChannelsButtonRunnable(izkModbusGUI,query,channelNumber,modbusReader,(JButton) e.getSource());
        izkModbusGUI.getExecutor().execute(tusk);
    }

}
