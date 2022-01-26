package ru.sid.izk.modbus.listener;

import ru.sid.izk.modbus.frames.IZKModbusGUI;
import ru.sid.izk.modbus.utils.Settings;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LevelActionListener implements ActionListener {

    private final IZKModbusGUI izkModbusGUI;

    public LevelActionListener(IZKModbusGUI izkModbusGUI) {
        this.izkModbusGUI = izkModbusGUI;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (Settings.propertiesFileExists()){
            final Settings settings = new Settings();
            settings.setElMetroX(izkModbusGUI.getElMetroXField().getText());
            settings.setKorundX(izkModbusGUI.getKorundXField().getText());
            settings.storeProperties("Edit level settings");

            JOptionPane.showMessageDialog(izkModbusGUI,
                    "Запись завершена", "Подтверждение", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
