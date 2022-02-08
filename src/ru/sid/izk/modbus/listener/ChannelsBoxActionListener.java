package ru.sid.izk.modbus.listener;

import ru.sid.izk.modbus.connection.ModbusReader;
import ru.sid.izk.modbus.frames.IZKModbusGUI;
import ru.sid.izk.modbus.runnables.ChannelBoxRunnable;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static ru.sid.izk.modbus.utils.ClearSensorFields.clearFields;

public class ChannelsBoxActionListener implements ActionListener {

    private final IZKModbusGUI izkModbusGUI;
    private final ModbusReader modbusReader;

    public ChannelsBoxActionListener(IZKModbusGUI izkModbusGUI, ModbusReader modbusReader) {
        this.izkModbusGUI = izkModbusGUI;
        this.modbusReader = modbusReader;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        int index = (((JComboBox<?>) e.getSource()).getSelectedIndex());
        System.out.println(index);
        Runnable tusk = new ChannelBoxRunnable(izkModbusGUI,modbusReader,index);
        izkModbusGUI.getExecutor().execute(tusk);
    }


}