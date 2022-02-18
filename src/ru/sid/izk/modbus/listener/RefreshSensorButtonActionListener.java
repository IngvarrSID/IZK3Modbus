package ru.sid.izk.modbus.listener;

import ru.sid.izk.modbus.entity.Query;
import ru.sid.izk.modbus.frames.IZKModbusGUI;
import ru.sid.izk.modbus.runnables.RefreshSensorButtonRunnable;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static ru.sid.izk.modbus.utils.ClearSensorFields.clearFields;

public class RefreshSensorButtonActionListener implements ActionListener {

    private final Query query;
    private final IZKModbusGUI izkModbusGUI;

    public RefreshSensorButtonActionListener(Query query, IZKModbusGUI izkModbusGUI) {

        this.query = query;
        this.izkModbusGUI = izkModbusGUI;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        izkModbusGUI.getExecutor().execute(new RefreshSensorButtonRunnable(query,izkModbusGUI));
    }
}