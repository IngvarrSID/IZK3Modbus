package ru.sid.izk.modbus.listener;

import ru.sid.izk.modbus.entity.Query;
import ru.sid.izk.modbus.frames.IZKModbusGUI;
import ru.sid.izk.modbus.runnables.DensityTableReaderRunnable;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ReadDensityButtonActionListener implements ActionListener {
    private final IZKModbusGUI izkModbusGUI;
    private final Query query;

    public ReadDensityButtonActionListener(IZKModbusGUI izkModbusGUI, Query query) {

        this.izkModbusGUI = izkModbusGUI;
        this.query = query;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        izkModbusGUI.getExecutor().execute(new DensityTableReaderRunnable(izkModbusGUI,query));
    }
}
