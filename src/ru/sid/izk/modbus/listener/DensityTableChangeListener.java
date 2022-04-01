package ru.sid.izk.modbus.listener;

import ru.sid.izk.modbus.connection.ModbusReader;
import ru.sid.izk.modbus.frames.IZKModbusGUI;
import ru.sid.izk.modbus.runnables.DensityTableChangeRunnable;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

public class DensityTableChangeListener implements TableModelListener {

    private final ModbusReader modbusReader;
    private final IZKModbusGUI izkModbusGUI;
    private final DefaultTableModel model;

    public DensityTableChangeListener(ModbusReader modbusReader, IZKModbusGUI izkModbusGUI, DefaultTableModel model) {
        this.modbusReader = modbusReader;
        this.izkModbusGUI = izkModbusGUI;
        this.model = model;
    }

    @Override
    public void tableChanged(TableModelEvent e) {
        izkModbusGUI.getExecutor().execute(new DensityTableChangeRunnable(modbusReader,izkModbusGUI,e,model));
    }
}
