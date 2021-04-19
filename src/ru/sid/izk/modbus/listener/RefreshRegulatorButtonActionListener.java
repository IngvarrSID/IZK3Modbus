package ru.sid.izk.modbus.listener;

import ru.sid.izk.modbus.connection.ModbusReader;
import ru.sid.izk.modbus.entity.Query;
import ru.sid.izk.modbus.frames.IZKModbusGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RefreshRegulatorButtonActionListener implements ActionListener {

    private final Query query;
    private final ModbusReader modbusReader;
    private final IZKModbusGUI izkModbusGUI;

    public RefreshRegulatorButtonActionListener(Query query,ModbusReader modbusReader,IZKModbusGUI izkModbusGUI){
        this.query = query;
        this.modbusReader = modbusReader;
        this.izkModbusGUI = izkModbusGUI;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            modbusReader.writeModeRegister(0, 1);
            modbusReader.writeModeRegister(1, 0);
            izkModbusGUI.getProgressRegulatorBar().setMinimum(0);
            izkModbusGUI.getProgressRegulatorBar().setMaximum(modbusReader.readHoldingsRegisters(44,1,1)[0]);
        } catch (Exception ex){
            ex.printStackTrace();
            JOptionPane.showMessageDialog(izkModbusGUI,
                    "Ошибка инициализации " + ex.getMessage(), "Ошибка", JOptionPane.ERROR_MESSAGE);
        }

    }
}
