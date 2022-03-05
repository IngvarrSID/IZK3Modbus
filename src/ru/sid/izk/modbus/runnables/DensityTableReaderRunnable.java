package ru.sid.izk.modbus.runnables;

import ru.sid.izk.modbus.entity.Query;
import ru.sid.izk.modbus.frames.IZKModbusGUI;

import javax.swing.*;
import java.util.ArrayList;

import static ru.sid.izk.modbus.utils.DensityTableUnits.densityTableBuilder;

public class DensityTableReaderRunnable implements Runnable{

    private final IZKModbusGUI izkModbusGUI;
    private final Query query;

    public DensityTableReaderRunnable(IZKModbusGUI izkModbusGUI, Query query) {
        this.izkModbusGUI = izkModbusGUI;
        this.query = query;
    }

    @Override
    public void run() {
        izkModbusGUI.getWriteDensityTableButton().setEnabled(false);
        ArrayList<String[]> list = null;
        try {
            list = (new DensityTableReaderCallable(izkModbusGUI,query)).call();
        } catch (Exception e) {
            e.printStackTrace();
            e.printStackTrace();
            JOptionPane.showMessageDialog(izkModbusGUI,
                    "Ошибка чтения " + e.getMessage(), "Ошибка", JOptionPane.ERROR_MESSAGE);
        }

        densityTableBuilder(list,izkModbusGUI);
        izkModbusGUI.getWriteDensityTableButton().setEnabled(true);
    }

}
