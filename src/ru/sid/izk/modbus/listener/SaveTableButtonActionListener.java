package ru.sid.izk.modbus.listener;

import ru.sid.izk.modbus.frames.IZKModbusGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class SaveTableButtonActionListener implements ActionListener {

    private final IZKModbusGUI izkModbusGUI;

    public SaveTableButtonActionListener(IZKModbusGUI izkModbusGUI) {
        this.izkModbusGUI = izkModbusGUI;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser fileChooser = new JFileChooser(String.format("%s/Documents/Technosensor/ConfigSU5DV", System.getProperty("user.home")));
        fileChooser.setDialogTitle("Сохранение градуировочной таблицы");
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        File file = new File(String.format("tarTab%s.csv", izkModbusGUI.getListTableStrings().get(izkModbusGUI.getListTableStrings().size()-1)[1]));
        fileChooser.setSelectedFile(file);
        int result = fileChooser.showSaveDialog(izkModbusGUI);
        if (result == JFileChooser.APPROVE_OPTION) {

        }
    }
}
