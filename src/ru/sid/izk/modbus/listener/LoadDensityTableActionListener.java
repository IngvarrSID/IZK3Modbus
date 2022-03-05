package ru.sid.izk.modbus.listener;

import ru.sid.izk.modbus.connection.ModbusReader;
import ru.sid.izk.modbus.frames.IZKModbusGUI;
import ru.sid.izk.modbus.runnables.LoadDensityRunnable;
import ru.sid.izk.modbus.utils.DensityTableUnits;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;


public class LoadDensityTableActionListener implements ActionListener {

    private final IZKModbusGUI izkModbusGUI;
    private final ModbusReader modbusReader;

    public LoadDensityTableActionListener(IZKModbusGUI izkModbusGUI, ModbusReader modbusReader) {
        this.izkModbusGUI = izkModbusGUI;
        this.modbusReader = modbusReader;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        JFileChooser fileChooser = new JFileChooser(String.format("%s/Documents/Technosensor/", System.getProperty("user.home")));
        fileChooser.setDialogTitle("Выбор таблицы плотности");
        FileNameExtensionFilter filter = new FileNameExtensionFilter("SU5v density table file", "csv", "txt");
        fileChooser.setFileFilter(filter);
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int result = fileChooser.showOpenDialog(izkModbusGUI);
        if (result == JFileChooser.APPROVE_OPTION) {
            try {
                List<String[]> list = DensityTableUnits.densityTableReader(fileChooser.getSelectedFile().getPath(),izkModbusGUI);
                JOptionPane.showMessageDialog(izkModbusGUI,
                        "Таблица загружена в программу", "Подтверждение", JOptionPane.INFORMATION_MESSAGE);
                izkModbusGUI.getExecutor().execute(new LoadDensityRunnable(izkModbusGUI,modbusReader,list));

            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(izkModbusGUI,
                        "Ошибка загрузки таблицы " + ex.getMessage(), "Ошибка", JOptionPane.ERROR_MESSAGE);

            }
        }
    }
}
