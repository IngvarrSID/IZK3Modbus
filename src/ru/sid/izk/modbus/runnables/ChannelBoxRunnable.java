package ru.sid.izk.modbus.runnables;

import ru.sid.izk.modbus.connection.ModbusReader;
import ru.sid.izk.modbus.frames.IZKModbusGUI;

import javax.swing.*;

import static ru.sid.izk.modbus.utils.ClearSensorFields.clearFields;

public class ChannelBoxRunnable implements Runnable{

    private final IZKModbusGUI izkModbusGUI;
    private final ModbusReader modbusReader;
    private final int index;

    public ChannelBoxRunnable(IZKModbusGUI izkModbusGUI, ModbusReader modbusReader, int index) {
        this.izkModbusGUI = izkModbusGUI;
        this.modbusReader = modbusReader;
        this.index = index;
    }

    @Override
    public void run() {
        processAction();
    }

    public void processAction(){
        try {
            izkModbusGUI.getChannelsBox().setEnabled(false);
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
            izkModbusGUI.getChannelsBox().setEnabled(true);
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(izkModbusGUI,
                    "Ошибка инициализации канала: " + ex.getMessage(), "Ошибка", JOptionPane.ERROR_MESSAGE);
            izkModbusGUI.getChannelsBox().setEnabled(true);
        }
    }
}
