package ru.sid.izk.modbus.listener;

import ru.sid.izk.modbus.archive.ReadAllDataAdapter;
import ru.sid.izk.modbus.connection.ModbusReader;
import ru.sid.izk.modbus.frames.IZKModbusGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WriteAllDataActionListener implements ActionListener {

    private final ReadAllDataAdapter readAllDataAdapter;
    private final IZKModbusGUI izkModbusGUI;
    private final ModbusReader modbusReader;

    public WriteAllDataActionListener(ReadAllDataAdapter readAllDataAdapter, IZKModbusGUI izkModbusGUI, ModbusReader modbusReader) {
        this.readAllDataAdapter = readAllDataAdapter;
        this.izkModbusGUI = izkModbusGUI;
        this.modbusReader = modbusReader;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(readAllDataAdapter.isDataUpDate()) {
            int result = JOptionPane.showConfirmDialog(izkModbusGUI, "<html>Внимание! Запись всех настроек может <br>привести к потери данным блока ИЗК-3!<br>" +
                    "Записать все настройки?</html>", "Подтверждение", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
            if (result == JOptionPane.YES_OPTION) {
                final JCheckBox queryBox = izkModbusGUI.getQueryBox();
                if (queryBox.isSelected()) queryBox.setSelected(false);
                izkModbusGUI.getProgressBar().setValue(0);
                izkModbusGUI.getProgressBar().setVisible(true);
                izkModbusGUI.getProgressBar().setMaximum(5);
                Timer timer = new Timer(1000, new TimerWriteBarActionListener(izkModbusGUI, readAllDataAdapter, modbusReader));
                timer.start();
            }
        } else {
            int result = JOptionPane.showConfirmDialog(izkModbusGUI, "<html>Данные для записи не обнаружены!<br>" +
                    "Открыть файл с данными?</html>", "Ошибка", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
            if (result == JOptionPane.YES_OPTION) {
                new OpenFileActionListener(izkModbusGUI).actionPerformed(new ActionEvent(new JButton(),Event.ACTION_EVENT,""));
            }
        }


    }
}
