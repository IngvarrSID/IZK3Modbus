package ru.sid.izk.modbus.listener;

import ru.sid.izk.modbus.connection.ModbusReader;
import ru.sid.izk.modbus.entity.Query;
import ru.sid.izk.modbus.frames.IZKModbusGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TestButtonActionListener implements ActionListener {

    private final IZKModbusGUI izkModbusGUI;
    private final Query query;
    private final ModbusReader reader;

    public TestButtonActionListener(IZKModbusGUI izkModbusGUI, Query query, ModbusReader reader) {
        this.izkModbusGUI = izkModbusGUI;
        this.query = query;
        this.reader = reader;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            izkModbusGUI.getMode0Field().setText(String.format("%d",query.queryMode0()));
        } catch (Exception ex){
            ex.printStackTrace();
            JOptionPane.showMessageDialog(izkModbusGUI,
                    "Нет связи. Попробуй еще" + ex.getMessage(), "Ошибка", JOptionPane.ERROR_MESSAGE);
        }
    }
}
