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
            String text;
            switch (index) {
                case 0:
                    text = modbusReader.changeChannel(1);
                    JOptionPane.showMessageDialog(izkModbusGUI,
                            text, "Подтверждение", JOptionPane.INFORMATION_MESSAGE);
                    izkModbusGUI.getChannelLabel().setText("Настройка 1 измерительного канала");
                    clearFields();
                    izkModbusGUI.getRefreshSensorButton().doClick();
                    break;
                case 1:
                    text = modbusReader.changeChannel(2);
                    JOptionPane.showMessageDialog(izkModbusGUI,
                            text, "Подтверждение", JOptionPane.INFORMATION_MESSAGE);
                    izkModbusGUI.getChannelLabel().setText("Настройка 2 измерительного канала");
                    clearFields();
                    izkModbusGUI.getRefreshSensorButton().doClick();
                    break;
                case 2:
                    text = modbusReader.changeChannel(3);
                    JOptionPane.showMessageDialog(izkModbusGUI,
                            text, "Подтверждение", JOptionPane.INFORMATION_MESSAGE);
                    izkModbusGUI.getChannelLabel().setText("Настройка 3 измерительного канала");
                    clearFields();
                    izkModbusGUI.getRefreshSensorButton().doClick();
                    break;
                case 3:
                    text = modbusReader.changeChannel(4);
                    JOptionPane.showMessageDialog(izkModbusGUI,
                            text, "Подтверждение", JOptionPane.INFORMATION_MESSAGE);
                    izkModbusGUI.getChannelLabel().setText("Настройка 4 измерительного канала");
                    clearFields();
                    izkModbusGUI.getRefreshSensorButton().doClick();
                    break;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(izkModbusGUI,
                    "Ошибка инициализации канала: " + ex.getMessage(), "Ошибка", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void clearFields(){
        izkModbusGUI.getAddressSensorFieldWrite().setText("");
        izkModbusGUI.getTimeoutFieldWrite().setText("");
        izkModbusGUI.getPeriodFieldWrite().setText("");
        izkModbusGUI.getT01FieldWrite().setText("");
        izkModbusGUI.getCk1FieldWrite().setText("");
        izkModbusGUI.getCd1FieldWrite().setText("");
        izkModbusGUI.getCheckPeriodFieldWrite().setText("");
        izkModbusGUI.getErrorFieldWrite().setText("");
        izkModbusGUI.getCs100FieldWrite().setText("");
        izkModbusGUI.getCmFieldWrite().setText("");
        izkModbusGUI.getkFieldWrite().setText("");
        izkModbusGUI.getCs0FieldWrite().setText("");
        izkModbusGUI.getTcFieldWrite().setText("");
        izkModbusGUI.getCsMinFieldWrite().setText("");
        izkModbusGUI.gethMinFieldWrite().setText("");
        izkModbusGUI.getTsd1FieldWrite().setText("");
        izkModbusGUI.getTsd2FieldWrite().setText("");
        izkModbusGUI.getAutoMinFieldWrite().setText("");
        izkModbusGUI.getAutoMaxFieldWrite().setText("");
        izkModbusGUI.getD20FieldWrite().setText("");
        izkModbusGUI.getKdFieldWrite().setText("");
        izkModbusGUI.getMinFieldWrite().setText("");
        izkModbusGUI.getMaxFieldWrite().setText("");
        izkModbusGUI.getEmerMaxFieldWrite().setText("");
        izkModbusGUI.getNoDensityFieldWrite().setText("");
    }
}