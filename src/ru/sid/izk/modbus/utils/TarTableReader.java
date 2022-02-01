package ru.sid.izk.modbus.utils;

import au.com.bytecode.opencsv.CSVReader;
import ru.sid.izk.modbus.frames.IZKModbusGUI;

import javax.swing.table.DefaultTableModel;
import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public final class TarTableReader {

    public static void tarTableRead(String path, IZKModbusGUI izkModbusGUI) throws IOException {
        FileInputStream fis = new FileInputStream(path);
        Reader preReader = new InputStreamReader(fis, Charset.forName("Windows-1251"));
        CSVReader reader = new CSVReader(preReader, ';', '"');
        List<String[]> list = reader.readAll();
        tableBuilder(list,izkModbusGUI);
        saveTable(list);
    }

    public static void tableBuilder(List<String[]> list,IZKModbusGUI izkModbusGUI) {

        izkModbusGUI.getTarTable().setModel(new DefaultTableModel());
        DefaultTableModel model = (DefaultTableModel) izkModbusGUI.getTarTable().getModel();
        model.addColumn("Уровень, cм");
        model.addColumn("Объем, м³");
        if (list.size() > 0) {
            for (String[] strings : list) {
                model.addRow(strings);
            }
        }
        izkModbusGUI.getTarTable().setFillsViewportHeight(true);


        ArrayList<float[]> arrayList = new ArrayList<>();

        for (String[] array : list) {

            float[] floats = new float[2];
            floats[0] = Float.parseFloat(array[0]) / 100;
            floats[1] = Float.parseFloat(array[1]);
            arrayList.add(floats);

        }
        izkModbusGUI.setListTableFloats(arrayList);
    }

    public static void saveTable(List<String[]> list) throws IOException {
        if (Settings.propertiesFileExists()) {
           final Settings settings = new Settings();
           if(settings.getTarTab().equals("false")) {
               settings.setTarTab("true");
               settings.storeProperties("Table Refresh");
               File file = new File(settings.getAbsolutePath() + "/tarTab.su5");
               FileOutputStream out= new FileOutputStream (file);
               ObjectOutputStream oos = new ObjectOutputStream(out);
               oos.writeObject(list);
               out.close();

           }
        }
    }

}
