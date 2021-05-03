package ru.sid.izk.modbus.listener;

import ru.sid.izk.modbus.frames.IZKModbusGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class PathInputActionListener implements ActionListener {

    private final IZKModbusGUI izkModbusGUI;

    public PathInputActionListener(IZKModbusGUI izkModbusGUI){
        this.izkModbusGUI = izkModbusGUI;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Properties properties = new Properties();
        String changePath = String.format("%s/Documents/Technosensor/Archive",System.getProperty("user.home"));
        try {
            FileInputStream in = new FileInputStream("settings.properties");
            properties.load(in);
            changePath = properties.getProperty("Path");
            in.close();
        } catch (IOException ex){
            ex.printStackTrace();
        }
        JFileChooser fileChooser = new JFileChooser(changePath);
        fileChooser.setDialogTitle("Выбор дериктории архива");
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int result = fileChooser.showOpenDialog(fileChooser);
        try {
            if(result == JFileChooser.APPROVE_OPTION) {
                FileOutputStream out = new FileOutputStream("settings.properties");
                properties.setProperty("Path", fileChooser.getSelectedFile().getAbsolutePath());
                properties.store(out, "Path settings");
                JOptionPane.showMessageDialog(izkModbusGUI,"Выбрана директория для архива: " + fileChooser.getSelectedFile().getAbsolutePath(),"Подтверждение",JOptionPane.INFORMATION_MESSAGE);
                out.close();
            }
        } catch (IOException q) {
            q.printStackTrace();
        }

    }
}
