package ru.sid.izk.modbus.listener;

import ru.sid.izk.modbus.connection.ModbusReader;
import ru.sid.izk.modbus.entity.Query;
import ru.sid.izk.modbus.frames.IZKModbusGUI;

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
        try {
            boolean coil = modbusReader.coilsReader(offset, 1)[0];
            ((JButton)e.getSource()).setText(coil? "OFF" : "ON");
            modbusReader.writeCoil(offset,!coil);
            izkModbusGUI.getQueryBox().setSelected(true);
        } catch (Exception ex){
            ex.printStackTrace();
            JOptionPane.showMessageDialog(izkModbusGUI,
                    "Ошибка инициализации:" + ex.getMessage(), "Ошибка", JOptionPane.ERROR_MESSAGE);
        }
    }
}
