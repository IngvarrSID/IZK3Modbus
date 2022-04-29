package ru.sid.izk.modbus.runnables;


import ru.sid.izk.modbus.entity.Query;
import ru.sid.izk.modbus.frames.IZKModbusGUI;

import javax.swing.*;
import java.util.ArrayList;
import java.util.concurrent.Callable;

public class DensityTableReaderCallable implements Callable<ArrayList<String[]>> {

    private final IZKModbusGUI izkModbusGUI;
    private final Query query;
    private ArrayList<String[]> list;

    public DensityTableReaderCallable(IZKModbusGUI izkModbusGUI, Query query) {
        this.izkModbusGUI = izkModbusGUI;
        this.query = query;
        list = new ArrayList<>();

    }

    @Override
    public ArrayList<String[]> call() throws Exception {

        processAction();

        return list;
    }

    private void processAction(){
        try {
            query.queryDensityTable();
            list = query.getList();

        } catch (Exception e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(izkModbusGUI,
                    "Ошибка чтения " + e.getMessage(), "Ошибка", JOptionPane.ERROR_MESSAGE);
        }
    }


}