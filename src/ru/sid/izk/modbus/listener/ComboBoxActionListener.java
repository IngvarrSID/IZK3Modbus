package ru.sid.izk.modbus.listener;

import ru.sid.izk.modbus.connection.ModbusReader;
import ru.sid.izk.modbus.frames.IZKModbusGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ComboBoxActionListener implements ActionListener {

    private final IZKModbusGUI izkModbusGUI;
    private final ModbusReader modbusReader;
    int offset;
    boolean isNumber;

    public ComboBoxActionListener(IZKModbusGUI izkModbusGUI, ModbusReader modbusReader, int offset, boolean isNumber) {
        this.izkModbusGUI = izkModbusGUI;
        this.modbusReader = modbusReader;
        this.offset = offset;
        this.isNumber = isNumber;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (izkModbusGUI.isReadyToWriteRelay()) {
            try {
                if(isNumber){
                modbusReader.writeModeRegister(offset, ((JComboBox<?>) e.getSource()).getSelectedIndex()+1);
                } else {
                    modbusReader.writeModeRegister(offset, ((JComboBox<?>) e.getSource()).getSelectedIndex());
                }
                JOptionPane.showMessageDialog(izkModbusGUI,
                        "Запись завершена", "Подтверждение", JOptionPane.INFORMATION_MESSAGE);

            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(izkModbusGUI,
                        "Ошибка записи! " + ex.getMessage(), "Ошибка", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
