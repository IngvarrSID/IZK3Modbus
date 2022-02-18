package ru.sid.izk.modbus.listener;

import ru.sid.izk.modbus.frames.IZKModbusGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoadLabelActionListener implements ActionListener {

    private final IZKModbusGUI izkModbusGUI;

    public LoadLabelActionListener(IZKModbusGUI izkModbusGUI) {
        this.izkModbusGUI = izkModbusGUI;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        izkModbusGUI.getLoadGifLabel().setVisible(izkModbusGUI.getExecutor().getActiveCount() > 0);

    }

}
