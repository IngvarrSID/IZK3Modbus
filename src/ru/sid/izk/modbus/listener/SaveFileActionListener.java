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
            File file = new File(String.format("izk%d.su5v",izkModbusGUI.getReadAllDataAdapter().getAddressIZK()));
            fileChooser.setSelectedFile(file);
            int result = fileChooser.showSaveDialog(izkModbusGUI);
            if (result == JFileChooser.APPROVE_OPTION) {
                try(BufferedWriter writer = new BufferedWriter(new FileWriter(fileChooser.getSelectedFile()))) {
                    String newSaveFile = String.format("SU5D vlagomer SAVE FILE, currentDate: %s\n", LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yy")));
                    writer.write(newSaveFile);
                    String data = izkModbusGUI.getReadAllDataAdapter().compilationData();
                    writer.write(data);
                    JOptionPane.showMessageDialog(izkModbusGUI,"Файл '" + fileChooser.getSelectedFile() + " ) сохранен","Подтверждение",JOptionPane.INFORMATION_MESSAGE);

                } catch (Exception ex) {
                    ex.printStackTrace();
                }


            }
        }
        else {
            int result = JOptionPane.showConfirmDialog(izkModbusGUI,"<html>Перед сохранением необходимо считать все настройки ИЗК-3!<br>Считать все настройки?</html>","Подтверждение",JOptionPane.YES_NO_OPTION,JOptionPane.ERROR_MESSAGE);
            if (result == JOptionPane.YES_OPTION) izkModbusGUI.getReadAllButton().doClick();
        }
    }
}
