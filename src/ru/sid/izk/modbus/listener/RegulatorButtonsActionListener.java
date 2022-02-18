package ru.sid.izk.modbus.listener;

import ru.sid.izk.modbus.connection.ModbusReader;
import ru.sid.izk.modbus.entity.Query;
import ru.sid.izk.modbus.frames.IZKModbusGUI;
import ru.sid.izk.modbus.runnables.RegulatorButtonsRunnable;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegulatorButtonsActionListener implements ActionListener {

    private final IZKModbusGUI izkModbusGUI;
    private final int offset;
    private final ModbusReader modbusReader;

    public RegulatorButtonsActionListener(IZKModbusGUI izkModbusGUI, int offset, ModbusReader modbusReader) {
        this.izkModbusGUI = izkModbusGUI;
        this.offset = offset;
        this.modbusReader = modbusReader;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        final JButton currentButton =  (JButton)e.getSource();
        izkModbusGUI.getExecutor().execute(new RegulatorButtonsRunnable(izkModbusGUI,offset,modbusReader,currentButton));

    }
}
