package ru.sid.izk.modbus.utils;

import au.com.bytecode.opencsv.CSVReader;
import ru.sid.izk.modbus.frames.IZKModbusGUI;
import ru.sid.izk.modbus.listener.DensityTableChangeListener;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public final class DensityTableUnits {

    public static void densityTableBuilder(List<String[]> list, IZKModbusGUI izkModbusGUI) {

        JTable table = izkModbusGUI.getDensityTable();
        table.setModel(new DefaultTableModel());
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        int rowCount = model.getRowCount();
        for (int i = rowCount-1; i >= 0 ; i--) {
            model.removeRow(i);
        }
        model.addColumn("№");
        model.addColumn("Влажность, %");
        model.addColumn("Плотность, кг/м³");
        if (list.size() > 0) {
            for (String[] strings : list) {
                model.addRow(strings);
            }
        }
        TableColumn column0 = table.getColumnModel().getColumn( 0 );
        column0.setMinWidth(20);
        column0.setMaxWidth(40);

        DefaultTableCellRenderer renderer = (DefaultTableCellRenderer)table.getDefaultRenderer(String.class);
        renderer.setHorizontalAlignment(SwingConstants.CENTER);
        table.setFillsViewportHeight(true);
        model.addTableModelListener(new DensityTableChangeListener(izkModbusGUI.getModbusReader(),izkModbusGUI,model));

    }

    public static List<String[]> densityTableReader(String path, IZKModbusGUI izkModbusGUI) throws IOException {
        FileInputStream fis = new FileInputStream(path);
        Reader preReader = new InputStreamReader(fis, Charset.forName("Windows-1251"));
        CSVReader reader = new CSVReader(preReader, ';', '"');
        List<String[]> list = reader.readAll();
        int listSize = list.size();
        if(listSize<256){
            String[] lastRow = list.get(listSize-1);
            for (int i = 0; i <256 - listSize ; i++) {
                list.add(lastRow);
            }
        }
        List<String[]> listWithNumber = new ArrayList<>();
        for (int i = 0; i <list.size() ; i++) {
            String[] array = new String[3];
            array[0] = String.valueOf(i+1);
            array[1] = list.get(i)[0];
            array[2] = list.get(i)[1];
            listWithNumber.add(array);
        }
        densityTableBuilder(listWithNumber,izkModbusGUI);
       return list;


    }
}
