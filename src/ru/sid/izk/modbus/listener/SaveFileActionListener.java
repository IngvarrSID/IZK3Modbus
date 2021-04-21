package ru.sid.izk.modbus.listener;

import ru.sid.izk.modbus.frames.IZKModbusGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

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
                try {
                    FileOutputStream fileStream = new FileOutputStream(fileChooser.getSelectedFile());
                    ObjectOutputStream os = new ObjectOutputStream(fileStream);
                    os.writeObject(izkModbusGUI.getReadAllDataAdapter());

                } catch (Exception ex) {
                    ex.printStackTrace();
                }


            }
        }
    }
}
