package ru.sid.izk.modbus.listener;

import ru.sid.izk.modbus.frames.IZKModbusGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AboutActionListener implements ActionListener {
    private final IZKModbusGUI izkModbusGUI;

    public AboutActionListener(IZKModbusGUI izkModbusGUI) {
        this.izkModbusGUI = izkModbusGUI;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JOptionPane.showMessageDialog(izkModbusGUI,
                "<html><center>Конфигуратор СУ-5Д Влагомер<br>версия программы 0.8alfa<br>ООО \"Техносенсор\"<br> 2021 г. </center></html>", "О программе", JOptionPane.INFORMATION_MESSAGE);
    }
}
