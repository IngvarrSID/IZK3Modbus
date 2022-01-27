package ru.sid.izk.modbus.listener;

import ru.sid.izk.modbus.frames.IZKModbusGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CyclicalCheckBoxMenuActionListener implements ActionListener {

    private final IZKModbusGUI izkModbusGUI;

    public CyclicalCheckBoxMenuActionListener(IZKModbusGUI izkModbusGUI) {
        this.izkModbusGUI = izkModbusGUI;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (izkModbusGUI.getQueryCyclical().isSelected()){
            izkModbusGUI.setEnableCyclic(true);
        }
        else izkModbusGUI.setEnableCyclic(false);

    }
}
