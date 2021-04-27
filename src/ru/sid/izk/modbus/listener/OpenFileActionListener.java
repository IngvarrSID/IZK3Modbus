package ru.sid.izk.modbus.listener;

import ru.sid.izk.modbus.frames.IZKModbusGUI;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;

public class OpenFileActionListener implements ActionListener {

    private final IZKModbusGUI izkModbusGUI;

    public OpenFileActionListener(IZKModbusGUI izkModbusGUI) {
        this.izkModbusGUI = izkModbusGUI;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        izkModbusGUI.getReadAllDataAdapter().setDataUpDate(false);
        JFileChooser fileChooser = new JFileChooser(String.format("%s/Documents/Technosensor/", System.getProperty("user.home")));
        fileChooser.setDialogTitle("Выбор файла настроек");
        FileNameExtensionFilter filter = new FileNameExtensionFilter("SU5v save file", "su5v", "txt");
        fileChooser.setFileFilter(filter);
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int result = fileChooser.showOpenDialog(izkModbusGUI);
        if (result == JFileChooser.APPROVE_OPTION){
            try (BufferedReader reader = new BufferedReader(new FileReader(fileChooser.getSelectedFile()))){
                while (reader.ready()) {
                    String data = reader.readLine();
                    data = data.replace(',','.');
                    if (data.startsWith("Settings")) {
                        data = data.split(":")[1];
                        izkModbusGUI.getReadAllDataAdapter().readFileSettings(data.split(";"));
                    } else if (data.startsWith("Sensor1")) {
                        data = data.split(":")[1];
                        izkModbusGUI.getReadAllDataAdapter().readFileSensorOne(data.split(";"));
                    } else if (data.startsWith("Sensor2")) {
                        data = data.split(":")[1];
                        izkModbusGUI.getReadAllDataAdapter().readFileSensorTwo(data.split(";"));
                    } else if (data.startsWith("Sensor3")) {
                        data = data.split(":")[1];
                        izkModbusGUI.getReadAllDataAdapter().readFileSensorThree(data.split(";"));
                    } else if (data.startsWith("Sensor4")) {
                        data = data.split(":")[1];
                        izkModbusGUI.getReadAllDataAdapter().readFileSensorFour(data.split(";"));
                        izkModbusGUI.getReadAllDataAdapter().setDataUpDate(true);
                    }
                }
                if(izkModbusGUI.getReadAllDataAdapter().isDataUpDate()){
                    izkModbusGUI.getDataLabel().setText("Данные загружены из файла");
                    JOptionPane.showMessageDialog(izkModbusGUI, "Данные загружены из файла", "Подтверждение", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(izkModbusGUI, "Ошибка чтения файла!", "Ошибка", JOptionPane.ERROR_MESSAGE);
                    izkModbusGUI.getDataLabel().setText("Ошибка чтения файла");
                }
            } catch (Exception ex){
                ex.printStackTrace();
            }
        }
    }
}
