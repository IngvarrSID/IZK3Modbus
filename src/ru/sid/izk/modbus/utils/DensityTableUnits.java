package ru.sid.izk.modbus.utils;

import ru.sid.izk.modbus.frames.IZKModbusGUI;

import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public final class DensityTableUnits {

    public static void densityTableBuilder(ArrayList<String[]> list, IZKModbusGUI izkModbusGUI) {

        izkModbusGUI.getDensityTable().setModel(new DefaultTableModel());
        DefaultTableModel model = (DefaultTableModel) izkModbusGUI.getDensityTable().getModel();
        model.addColumn("Влажность, %");
        model.addColumn("Плотность, кг/м³");
        if (list.size() > 0) {
            for (String[] strings : list) {
                model.addRow(strings);
            }
        }
        izkModbusGUI.getTarTable().setFillsViewportHeight(true);

    }
}
