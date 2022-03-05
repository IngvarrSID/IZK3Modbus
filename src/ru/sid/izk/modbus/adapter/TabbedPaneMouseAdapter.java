package ru.sid.izk.modbus.adapter;

import ru.sid.izk.modbus.connection.MasterModbus;
import ru.sid.izk.modbus.entity.Query;
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
    private static final int TIME =4;
    private static final int LEVEL =5;
    private static final int DENSITY_TABLE = 6;
    private final IZKModbusGUI izkModbusGUI;
    private final Query query;
    private final MasterModbus masterModbus;

    public TabbedPaneMouseAdapter(IZKModbusGUI izkModbusGUI, Query query, MasterModbus masterModbus) {
        this.izkModbusGUI = izkModbusGUI;
        this.query = query;
        this.masterModbus = masterModbus;
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
                izkModbusGUI.initTable(masterModbus,query);
                break;
            case IZK_SETTINGS:
                toggleFields(izkModbusGUI,false);
                izkModbusGUI.getRefreshSettingsButton().doClick();
                izkModbusGUI.setReadyToWriteRelay(true);
                izkModbusGUI.relayActionListeners(izkModbusGUI.getModbusReader());
                break;
            case SENSOR:
                toggleFields(izkModbusGUI,false);
                izkModbusGUI.getChannelsBox().setSelectedIndex(0);
                izkModbusGUI.initTable(masterModbus,query);
                break;
            case REGULATOR:
                toggleFields(izkModbusGUI,true);
                izkModbusGUI.getChannelsBox().setSelectedIndex(0);
                izkModbusGUI.getRefreshRegulatorButton().doClick();
                izkModbusGUI.initTable(masterModbus,query);
                break;
            case TIME:
                izkModbusGUI.getChannelsBox().setSelectedIndex(0);
                izkModbusGUI.getRefreshTimeButton().doClick();
                break;
            case LEVEL:
                toggleFields(izkModbusGUI,true);
                    izkModbusGUI.getChannelsBox().setSelectedIndex(5);
                    izkModbusGUI.initTable(masterModbus,query);
                break;
            case DENSITY_TABLE:
                JOptionPane.showMessageDialog(izkModbusGUI,
                        "Для загрузки таблицы из блока нажмите \"Прочитать таблицу\"", "Подтверждение", JOptionPane.INFORMATION_MESSAGE);
            default:
                System.out.println("No action for idx value " + idx);
                break;
        }

    }
}