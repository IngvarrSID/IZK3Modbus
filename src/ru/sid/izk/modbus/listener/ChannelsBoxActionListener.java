package ru.sid.izk.modbus.listener;

import ru.sid.izk.modbus.connection.ModbusReader;
import ru.sid.izk.modbus.frames.IZKModbusGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChannelsBoxActionListener implements ActionListener {

    private final IZKModbusGUI izkModbusGUI;
    private final ModbusReader modbusReader;

    public ChannelsBoxActionListener(IZKModbusGUI izkModbusGUI, ModbusReader modbusReader) {
        this.izkModbusGUI = izkModbusGUI;
        this.modbusReader = modbusReader;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            int index = (((JComboBox<?>) e.getSource()).getSelectedIndex());
            System.out.println(index);
            // TODO: WTF code duplication, make 1 function 'writeModeRegister' that will take register values as arguments.
            switch (index) {
                case 0:
                    modbusReader.writeModeRegister(0, 5);
                    modbusReader.writeModeRegister(1, 0);
                    JOptionPane.showMessageDialog(izkModbusGUI,
                            "Канал изменен", "Подтверждение", JOptionPane.INFORMATION_MESSAGE);
                    izkModbusGUI.getChannelLabel().setText("Настройка 1 измерительного канала");
                    izkModbusGUI.getRefreshSensorButton().doClick();
                    break;
                case 1:
                    modbusReader.writeModeRegister(0, 6);
                    modbusReader.writeModeRegister(1, 1);
                    JOptionPane.showMessageDialog(izkModbusGUI,
                            "Канал изменен", "Подтверждение", JOptionPane.INFORMATION_MESSAGE);
                    izkModbusGUI.getChannelLabel().setText("Настройка 2 измерительного канала");
                    izkModbusGUI.getRefreshSensorButton().doClick();
                    break;
                case 2:
                    modbusReader.writeModeRegister(0, 7);
                    modbusReader.writeModeRegister(1, 2);
                    JOptionPane.showMessageDialog(izkModbusGUI,
                            "Канал изменен", "Подтверждение", JOptionPane.INFORMATION_MESSAGE);
                    izkModbusGUI.getChannelLabel().setText("Настройка 3 измерительного канала");
                    izkModbusGUI.getRefreshSensorButton().doClick();
                    break;
                case 3:
                    modbusReader.writeModeRegister(0, 8);
                    modbusReader.writeModeRegister(1, 3);
                    JOptionPane.showMessageDialog(izkModbusGUI,
                            "Канал изменен", "Подтверждение", JOptionPane.INFORMATION_MESSAGE);
                    izkModbusGUI.getChannelLabel().setText("Настройка 4 измерительного канала");
                    izkModbusGUI.getRefreshSensorButton().doClick();
                    break;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(izkModbusGUI,
                    "Ошибка инициализации канала: " + ex.getMessage(), "Ошибка", JOptionPane.ERROR_MESSAGE);
        }
    }
}