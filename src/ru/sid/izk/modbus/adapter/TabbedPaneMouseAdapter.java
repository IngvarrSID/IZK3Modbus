package ru.sid.izk.modbus.adapter;

import ru.sid.izk.modbus.frames.IZKModbusGUI;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static ru.sid.izk.modbus.utils.FieldVisible.toggleFields;

public class TabbedPaneMouseAdapter extends MouseAdapter {

    private static final int INFO = 0;
    private static final int IZK_SETTINGS = 1;
    private static final int SENSOR = 2;
    private static final int REGULATOR =3;
    private final IZKModbusGUI izkModbusGUI;

    public TabbedPaneMouseAdapter(IZKModbusGUI izkModbusGUI) {
        this.izkModbusGUI = izkModbusGUI;
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
                toggleFields(izkModbusGUI,false);
                izkModbusGUI.getRefButton().doClick();
                break;
            case IZK_SETTINGS:
                toggleFields(izkModbusGUI,false);
                izkModbusGUI.getRefreshSettingsButton().doClick();
                izkModbusGUI.setReadyToWriteRelay(true);
                izkModbusGUI.relayActionListeners(izkModbusGUI.getModbusReader());
                break;
            case SENSOR:
                toggleFields(izkModbusGUI,false);
                try {
                    izkModbusGUI.getChannelsBox().setSelectedIndex(0);
                } catch (Exception e1) {
                    e1.printStackTrace();
                    JOptionPane.showMessageDialog(izkModbusGUI,
                            "Ошибка инициализации канала: " + e1.getMessage(), "Ошибка", JOptionPane.ERROR_MESSAGE);
                }
                break;
            case REGULATOR:
                toggleFields(izkModbusGUI,true);
                izkModbusGUI.getChannelsBox().setSelectedIndex(0);
                izkModbusGUI.getRefreshRegulatorButton().doClick();
                break;
            default:
                System.out.println("No action for idx value " + idx);
                break;
        }
    }
}