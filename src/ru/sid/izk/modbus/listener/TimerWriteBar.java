package ru.sid.izk.modbus.listener;

import com.intelligt.modbus.jlibmodbus.exception.ModbusIOException;
import com.intelligt.modbus.jlibmodbus.exception.ModbusNumberException;
import com.intelligt.modbus.jlibmodbus.exception.ModbusProtocolException;
import ru.sid.izk.modbus.archive.ReadAllDataAdapter;
import ru.sid.izk.modbus.connection.ModbusReader;
import ru.sid.izk.modbus.frames.IZKModbusGUI;

import javax.swing.*;
import java.awt.*;
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
                    modbusReader.writeModeRegister(0, 1);
                    modbusReader.writeMultipleRegisters(2, readAllDataAdapter.getSettings());
                    break;
                case 2:
                    modbusReader.writeModeRegister(0, 5);
                    arraySplitWrite(readAllDataAdapter.getSensor1());
                    break;
                case 3:
                    modbusReader.writeModeRegister(0, 6);
                    arraySplitWrite(readAllDataAdapter.getSensor2());
                    break;
                case 4:
                    modbusReader.writeModeRegister(0, 7);
                    arraySplitWrite(readAllDataAdapter.getSensor3());
                    break;
                case 5:
                    modbusReader.writeModeRegister(0, 8);
                    arraySplitWrite(readAllDataAdapter.getSensor4());
                    break;
            }

            if (izkModbusGUI.getProgressBar().getValue() == 5) {
                ((Timer) e.getSource()).stop();
                JOptionPane.showMessageDialog(izkModbusGUI, "Запись всех настроек завершена!", "Подтверждение", JOptionPane.INFORMATION_MESSAGE);
                izkModbusGUI.getProgressBar().setVisible(false);
                izkModbusGUI.getDataLabel().setForeground(new Color(0, 120, 60));
                izkModbusGUI.getDataLabel().setText("Данные записаны");
                izkModbusGUI.getReadAllDataAdapter().setDataUpDate(false);
                modbusReader.writeModeRegister(0, izkModbusGUI.getChannelsBox().getSelectedIndex() + 5);
            } else state++;
        } catch (Exception ex) {
            ((Timer) e.getSource()).stop();
            ex.printStackTrace();
            JOptionPane.showMessageDialog(izkModbusGUI, "Ошибка чтения! " + ex.getMessage(), "Ошибка!", JOptionPane.ERROR_MESSAGE);
            izkModbusGUI.getProgressBar().setVisible(false);
            readAllDataAdapter.setDataUpDate(false);
            izkModbusGUI.getDataLabel().setForeground(Color.RED);
            izkModbusGUI.getDataLabel().setText("Ошибка записи");
        }

    }

    public void arraySplitWrite(int[] registers) throws ModbusProtocolException, ModbusNumberException, ModbusIOException {
        int[] sensor1RegistersPart1 = new int[36];
        int[] sensor1RegistersPart2 = new int[3];
        System.arraycopy(registers, 0, sensor1RegistersPart1, 0, sensor1RegistersPart1.length);
        System.arraycopy(registers, 40, sensor1RegistersPart2, 0, sensor1RegistersPart2.length);
        modbusReader.writeMultipleRegisters(2, sensor1RegistersPart1);
        modbusReader.writeMultipleRegisters(42, sensor1RegistersPart2);
    }
}
