package ru.sid.izk.modbus.listener;

import ru.sid.izk.modbus.frames.IZKModbusGUI;
import ru.sid.izk.modbus.utils.TarTableReader;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OpenTableButtonActionListener implements ActionListener {

    private final IZKModbusGUI izkModbusGUI;

    public OpenTableButtonActionListener(IZKModbusGUI izkModbusGUI) {
        this.izkModbusGUI = izkModbusGUI;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        JFileChooser fileChooser = new JFileChooser(String.format("%s/Documents/Technosensor/", System.getProperty("user.home")));
        fileChooser.setDialogTitle("Выбор градуировочной таблицы");
        FileNameExtensionFilter filter = new FileNameExtensionFilter("SU5v table file", "csv", "txt");
        fileChooser.setFileFilter(filter);
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int result = fileChooser.showOpenDialog(izkModbusGUI);
        if (result == JFileChooser.APPROVE_OPTION){
            try {
                TarTableReader.tarTableRead(fileChooser.getSelectedFile().getPath(),izkModbusGUI);
                JOptionPane.showMessageDialog(izkModbusGUI,
                        "Таблица загружена в программу", "Подтверждение", JOptionPane.INFORMATION_MESSAGE);

            } catch (Exception ex){
                ex.printStackTrace();
                JOptionPane.showMessageDialog(izkModbusGUI,
                        "Ошибка загрузки таблицы " + ex.getMessage(), "Ошибка", JOptionPane.ERROR_MESSAGE);

            }
        }
    }
}
