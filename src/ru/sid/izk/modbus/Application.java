package ru.sid.izk.modbus;

import ru.sid.izk.modbus.archive.CSVAdapter;
import ru.sid.izk.modbus.connection.MasterModbus;
import ru.sid.izk.modbus.connection.Terminal;
import ru.sid.izk.modbus.frames.IZKModbusGUI;
import ru.sid.izk.modbus.frames.IZKTerminal;

import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class Application {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            try {
                File settings = new File("settings.properties");
                if (settings.exists()) {
                    FileInputStream in = new FileInputStream(settings);
                    Properties properties = new Properties();
                    properties.load(in);
                    Terminal terminal = new Terminal(properties.getProperty("ComPort"), properties.getProperty("BoundRate"));
                    MasterModbus masterModbus = new MasterModbus(terminal, Integer.parseInt(properties.getProperty("Id")));
                    new IZKModbusGUI(terminal, masterModbus);
                } else {
                    new IZKTerminal();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}