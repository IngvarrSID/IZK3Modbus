package ru.sid.izk.modbus.runnables;

import ru.sid.izk.modbus.connection.ModbusReader;
import ru.sid.izk.modbus.frames.IZKModbusGUI;

import javax.swing.*;

public class ComboBoxRelayRunnable implements Runnable{

    private final IZKModbusGUI izkModbusGUI;
    private final ModbusReader modbusReader;
    private final boolean isNumber;
    private final int offset;
    private final JComboBox<?> comboBox;

    public ComboBoxRelayRunnable(IZKModbusGUI izkModbusGUI, ModbusReader modbusReader, boolean isNumber, int offset, JComboBox<?> comboBox) {
        this.izkModbusGUI = izkModbusGUI;
        this.modbusReader = modbusReader;
        this.isNumber = isNumber;
        this.offset = offset;
        this.comboBox = comboBox;
    }

    @Override
    public void run() {
        processAction();
    }

    public void processAction(){

        if (izkModbusGUI.isReadyToWriteRelay()) {
            comboBox.setEnabled(false);
            try {
                if(isNumber){
                    modbusReader.writeModeRegister(offset, comboBox.getSelectedIndex()+1);
                } else {
                    modbusReader.writeModeRegister(offset,comboBox.getSelectedIndex());
                }
                JOptionPane.showMessageDialog(izkModbusGUI,
                        "Запись завершена", "Подтверждение", JOptionPane.INFORMATION_MESSAGE);
                comboBox.setEnabled(true);

            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(izkModbusGUI,
                        "Ошибка записи! " + ex.getMessage(), "Ошибка", JOptionPane.ERROR_MESSAGE);
                comboBox.setEnabled(true);
            }
        }
    }
}
