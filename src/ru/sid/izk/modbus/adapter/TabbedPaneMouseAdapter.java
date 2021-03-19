package ru.sid.izk.modbus.adapter;

import ru.sid.izk.modbus.connection.ModbusReader;
import ru.sid.izk.modbus.frames.IZKModbusGUI;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TabbedPaneMouseAdapter extends MouseAdapter {

    private static final int INFO = 0;
    public static final int IZK_SETTINGS = 1;
    private static final int SENSOR = 2;
    private final IZKModbusGUI izkModbusGUI;
    private final ModbusReader modbusReader;

    public TabbedPaneMouseAdapter(IZKModbusGUI izkModbusGUI, ModbusReader modbusReader) {
        this.izkModbusGUI = izkModbusGUI;
        this.modbusReader = modbusReader;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int idx = ((JTabbedPane) e.getSource()).indexAtLocation(e.getX(), e.getY());
        System.out.println("Выбрана вкладка " + idx);
        final JCheckBox queryBox = izkModbusGUI.getQueryBox();
        if (queryBox.isSelected() && idx != -1)
            queryBox.setSelected(false);
        switch (idx) {
            case INFO:
                izkModbusGUI.getRefButton().doClick();
                break;
            case IZK_SETTINGS:
                break;
            case SENSOR:
                try {
                    modbusReader.writeModeRegister(0, 5);
                    modbusReader.writeModeRegister(1, 0);
                    izkModbusGUI.getChannelsBox().setSelectedIndex(0);
                } catch (Exception e1) {
                    e1.printStackTrace();
                    JOptionPane.showMessageDialog(izkModbusGUI,
                            "Ошибка инициализации канала: " + e1.getMessage(), "Ошибка", JOptionPane.ERROR_MESSAGE);
                }
                izkModbusGUI.getChannelLabel().setText("Настройка 1 измерительного канала");
                izkModbusGUI.getRefreshSensorButton().doClick();
                break;
            default:
                System.out.println("No action for idx value " + idx);
                break;
        }
    }
}