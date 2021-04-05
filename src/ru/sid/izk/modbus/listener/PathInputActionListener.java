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
        String result = JOptionPane.showInputDialog(izkModbusGUI,changePath);
        try {
            FileOutputStream out = new FileOutputStream("settings.properties");
            properties.setProperty("Path",result);
            properties.store(out, "Path settings");
        } catch (IOException q) {
            q.printStackTrace();
        }

    }
}
