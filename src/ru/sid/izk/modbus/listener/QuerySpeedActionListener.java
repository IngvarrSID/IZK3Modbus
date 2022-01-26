package ru.sid.izk.modbus.listener;

import ru.sid.izk.modbus.connection.MasterModbus;
import ru.sid.izk.modbus.entity.Query;
import ru.sid.izk.modbus.frames.IZKModbusGUI;
import ru.sid.izk.modbus.utils.Settings;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class QuerySpeedActionListener implements ActionListener {

    private final IZKModbusGUI izkModbusGUI;
    private final Query query;
    private final MasterModbus masterModbus;

    public QuerySpeedActionListener(IZKModbusGUI izkModbusGUI, Query query, MasterModbus masterModbus) {
        this.izkModbusGUI = izkModbusGUI;
        this.query = query;
        this.masterModbus = masterModbus;
    }


    @Override
    public void actionPerformed(ActionEvent e) {

        String querySpeed = izkModbusGUI.getQuerySpeedField().getText();
        final Settings settings = new Settings();
        settings.setQuerySpeed(querySpeed);
        settings.storeProperties("Change query speed");
        izkModbusGUI.getConnectionTimeoutTimer().stop();
        izkModbusGUI.getQueryBox().setSelected(false);
        izkModbusGUI.queryInit(query,masterModbus);

        JOptionPane.showMessageDialog(izkModbusGUI,
                "Запись завершена", "Подтверждение", JOptionPane.INFORMATION_MESSAGE);
    }
}
