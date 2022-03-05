package ru.sid.izk.modbus.utils;

import au.com.bytecode.opencsv.CSVReader;
import ru.sid.izk.modbus.frames.IZKModbusGUI;

import javax.swing.table.DefaultTableModel;
import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public final class DensityTableUnits {

    public static void densityTableBuilder(List<String[]> list, IZKModbusGUI izkModbusGUI) {

        izkModbusGUI.getDensityTable().setModel(new DefaultTableModel());
        DefaultTableModel model = (DefaultTableModel) izkModbusGUI.getDensityTable().getModel();
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
        izkModbusGUI.getTarTable().setFillsViewportHeight(true);

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
