package ru.sid.izk.modbus.listener;

import ru.sid.izk.modbus.frames.IZKModbusGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SaveFileActionListener implements ActionListener {
    private final IZKModbusGUI izkModbusGUI;

    public SaveFileActionListener(IZKModbusGUI izkModbusGUI) {
        this.izkModbusGUI = izkModbusGUI;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (izkModbusGUI.getReadAllDataAdapter().isDataUpDate()) {
            JFileChooser fileChooser = new JFileChooser(String.format("%s/Documents/Technosensor/", System.getProperty("user.home")));
            fileChooser.setDialogTitle("Сохранение настроек");
            fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            int result = fileChooser.showOpenDialog(fileChooser);
            if (result == JFileChooser.APPROVE_OPTION) {
                try(BufferedWriter writer = new BufferedWriter(new FileWriter(fileChooser.getSelectedFile()))) {
                    String newSaveFile = String.format("SU5D vlagomer SAVE FILE, currentDate: %s\n", LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yy")));
                    writer.write(newSaveFile);
                    String data = izkModbusGUI.getReadAllDataAdapter().compilationData();
                    writer.write(data);



                } catch (Exception ex) {
                    ex.printStackTrace();
                }


            }
        }
    }
}
