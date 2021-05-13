package ru.sid.izk.modbus.listener;

import ru.sid.izk.modbus.connection.DownloaderSerialPort;
import ru.sid.izk.modbus.connection.MasterModbus;
import ru.sid.izk.modbus.connection.Terminal;
import ru.sid.izk.modbus.frames.IZKModbusGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DownloaderActionListener implements ActionListener {

    private final IZKModbusGUI izkModbusGUI;
    private final MasterModbus masterModbus;

    public DownloaderActionListener(IZKModbusGUI izkModbusGUI, MasterModbus masterModbus) {
        this.izkModbusGUI = izkModbusGUI;
        this.masterModbus = masterModbus;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            int result = JOptionPane.showConfirmDialog(izkModbusGUI, "<html>Вы переходите в режим загрузчика!<br>" +
                    "Продолжить?</html>", "Предупреждение", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
            if (result == JOptionPane.YES_OPTION) {
                izkModbusGUI.getModbusReader().writeModeRegister(0,65535);
                masterModbus.disconnect();
                new DownloaderSerialPort(masterModbus.getTerminal().getComName(),izkModbusGUI);
            }

        } catch (Exception ex){
            ex.printStackTrace();
            JOptionPane.showMessageDialog(izkModbusGUI,
                    "Ошибка перехода в загрузчик! " + ex.getMessage(), "Ошибка", JOptionPane.ERROR_MESSAGE);
        }
    }
}
