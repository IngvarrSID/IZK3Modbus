package ru.sid.izk.modbus.listener;

import ru.sid.izk.modbus.frames.IZKModbusGUI;
import ru.sid.izk.modbus.utils.Settings;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PathInputActionListener implements ActionListener {

    private final IZKModbusGUI izkModbusGUI;

    public PathInputActionListener(IZKModbusGUI izkModbusGUI) {
        this.izkModbusGUI = izkModbusGUI;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        final Settings settings;
        if (Settings.propertiesFileExists()) {
            settings = new Settings();
        } else {
            settings = new Settings(String.format("%s/Documents/Technosensor/Archive", System.getProperty("user.home")));
            settings.storeProperties("Path settings");
        }
        JFileChooser fileChooser = new JFileChooser(settings.getPath());
        fileChooser.setDialogTitle("Выбор дериктории архива");
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int result = fileChooser.showOpenDialog(fileChooser);
        if (result == JFileChooser.APPROVE_OPTION) {
            final String absolutePath = fileChooser.getSelectedFile().getAbsolutePath();
            settings.setPath(absolutePath);
            settings.storeProperties("Path settings");
            JOptionPane.showMessageDialog(izkModbusGUI, "Выбрана директория для архива: " + absolutePath, "Подтверждение", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}