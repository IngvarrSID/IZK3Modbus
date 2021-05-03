package ru.sid.izk.modbus.listener;

import ru.sid.izk.modbus.archive.ReadAllDataAdapter;
import ru.sid.izk.modbus.connection.ModbusReader;
import ru.sid.izk.modbus.frames.IZKModbusGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TimerWriteBar implements ActionListener {
    private final IZKModbusGUI izkModbusGUI;
    private final ReadAllDataAdapter readAllDataAdapter;
    private final ModbusReader modbusReader;
    private int state;

    public TimerWriteBar(IZKModbusGUI izkModbusGUI, ReadAllDataAdapter readAllDataAdapter, ModbusReader modbusReader, int state) {
        this.izkModbusGUI = izkModbusGUI;
        this.readAllDataAdapter = readAllDataAdapter;
        this.modbusReader = modbusReader;
        this.state = state;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        izkModbusGUI.getProgressBar().setValue(state);
        try {
            switch (state) {
                case 1:
                    modbusReader.writeModeRegister(0,1);
                    modbusReader.writeMultipleRegisters(2,readAllDataAdapter.getSettings());
                    break;
                case 2:
                    modbusReader.writeModeRegister(0,5);
                    modbusReader.writeMultipleRegisters(2,readAllDataAdapter.getSensor1());
                    break;
                case 3:
                    modbusReader.writeModeRegister(0,6);
                    modbusReader.writeMultipleRegisters(2,readAllDataAdapter.getSensor2());
                    break;
                case 4:
                    modbusReader.writeModeRegister(0,7);
                    modbusReader.writeMultipleRegisters(2,readAllDataAdapter.getSensor3());
                    break;
                case 5:
                    modbusReader.writeModeRegister(0,8);
                    modbusReader.writeMultipleRegisters(2,readAllDataAdapter.getSensor4());
                    break;
            }
        } catch (Exception ex){
            ex.printStackTrace();
        }
        if (izkModbusGUI.getProgressBar().getValue() ==5) {
            ((Timer) e.getSource()).stop();
            JOptionPane.showMessageDialog(izkModbusGUI, "Запись всех настроек завершена!", "Подтверждение", JOptionPane.INFORMATION_MESSAGE);
            izkModbusGUI.getProgressBar().setVisible(false);
            izkModbusGUI.getDataLabel().setText("Данные записаны");
            izkModbusGUI.getReadAllDataAdapter().setDataUpDate(false);
        }
        else state++;

    }
}
