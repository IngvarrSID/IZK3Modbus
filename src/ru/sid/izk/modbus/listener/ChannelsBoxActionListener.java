package ru.sid.izk.modbus.listener;

import ru.sid.izk.modbus.connection.ModbusReader;
import ru.sid.izk.modbus.frames.IZKModbusGUI;

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

        Runnable tusk = () -> {
            try {
                int index = (((JComboBox<?>) e.getSource()).getSelectedIndex());
                System.out.println(index);
                String text;
                switch (index) {
                    case 0:
                        text = modbusReader.changeChannel(1);
                        JOptionPane.showMessageDialog(izkModbusGUI,
                                text, "Подтверждение", JOptionPane.INFORMATION_MESSAGE);
                        izkModbusGUI.getChannelLabel().setText("Настройка 1 измерительного канала");
                        clearFields(izkModbusGUI);
                        izkModbusGUI.getRefreshSensorButton().doClick();
                        break;
                    case 1:
                        text = modbusReader.changeChannel(2);
                        JOptionPane.showMessageDialog(izkModbusGUI,
                                text, "Подтверждение", JOptionPane.INFORMATION_MESSAGE);
                        izkModbusGUI.getChannelLabel().setText("Настройка 2 измерительного канала");
                        clearFields(izkModbusGUI);
                        izkModbusGUI.getRefreshSensorButton().doClick();
                        break;
                    case 2:
                        text = modbusReader.changeChannel(3);
                        JOptionPane.showMessageDialog(izkModbusGUI,
                                text, "Подтверждение", JOptionPane.INFORMATION_MESSAGE);
                        izkModbusGUI.getChannelLabel().setText("Настройка 3 измерительного канала");
                        clearFields(izkModbusGUI);
                        izkModbusGUI.getRefreshSensorButton().doClick();
                        break;
                    case 3:
                        text = modbusReader.changeChannel(4);
                        JOptionPane.showMessageDialog(izkModbusGUI,
                                text, "Подтверждение", JOptionPane.INFORMATION_MESSAGE);
                        izkModbusGUI.getChannelLabel().setText("Настройка 4 измерительного канала");
                        clearFields(izkModbusGUI);
                        izkModbusGUI.getRefreshSensorButton().doClick();
                        break;
                    case 5:
                        JOptionPane.showMessageDialog(izkModbusGUI,
                                "Выбран режим выдачи данных с уровнемеров ", "Подтверждение", JOptionPane.INFORMATION_MESSAGE);
                        break;
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(izkModbusGUI,
                        "Ошибка инициализации канала: " + ex.getMessage(), "Ошибка", JOptionPane.ERROR_MESSAGE);
            }
        };

        Thread thread = new Thread(tusk);
        thread.start();

    }


}