package ru.sid.izk.modbus.runnables;

import ru.sid.izk.modbus.connection.ModbusReader;
import ru.sid.izk.modbus.frames.IZKModbusGUI;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableModel;

import static ru.sid.izk.modbus.utils.FromHexUtils.hexStringFromField;

public class DensityTableChangeRunnable implements Runnable{

    private final ModbusReader modbusReader;
    private final IZKModbusGUI izkModbusGUI;
    private final TableModelEvent event;
    private final DefaultTableModel model;

    public DensityTableChangeRunnable(ModbusReader modbusReader, IZKModbusGUI izkModbusGUI, TableModelEvent event, DefaultTableModel model) {
        this.modbusReader = modbusReader;
        this.izkModbusGUI = izkModbusGUI;
        this.event = event;
        this.model = model;
    }

    @Override
    public void run() {
        processAction();
    }

    private void processAction() {
        int column = event.getColumn();
        int row = event.getFirstRow();
        String newDate = (String) model.getValueAt(row, column);
        String sF = hexStringFromField(newDate);
        int[] registers = {Integer.valueOf(sF.substring(4, 8), 16), Integer.valueOf(sF.substring(0, 4), 16)};
        if(column !=0) {
            int offset = (2*column) + 4*row;
            try {
                modbusReader.writeRegister(offset, registers);
                JOptionPane.showMessageDialog(izkModbusGUI,
                        "Запись завершена успешно.", "Подтверждение", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(izkModbusGUI,
                        "Ошибка записи! " + ex.getMessage(), "Ошибка", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
