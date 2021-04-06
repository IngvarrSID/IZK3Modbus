package ru.sid.izk.modbus.listener;


import ru.sid.izk.modbus.connection.ModbusReader;
import ru.sid.izk.modbus.entity.Query;
import ru.sid.izk.modbus.frames.IZKModbusGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SearchButtonActionListener implements ActionListener {
   final private IZKModbusGUI izkModbusGUI;
   final private Query query;
   final private ModbusReader modbusReader;
   private int searchProgress;

   public SearchButtonActionListener(IZKModbusGUI izkModbusGUI,Query query,ModbusReader modbusReader){
       this.modbusReader = modbusReader;
       this.izkModbusGUI = izkModbusGUI;
       this.query = query;
   }

    @Override
    public void actionPerformed(ActionEvent e) {
        final JCheckBox queryBox = izkModbusGUI.getQueryBox();
        if (queryBox.isSelected()) queryBox.setSelected(false);
        try {
            modbusReader.writeModeRegister(0, 26);
            modbusReader.writeModeRegister(1, 26);
            modbusReader.writeModeRegister(2,0);
            Timer searchTimer = createTimer();
            searchTimer.start();

        } catch (Exception ex){
            ex.printStackTrace();
            JOptionPane.showMessageDialog(izkModbusGUI,
                    "Ошибка инициализации " + ex.getMessage(), "Ошибка", JOptionPane.ERROR_MESSAGE);
        }


    }

    public Timer createTimer(){
        return new Timer(500, e -> {
            izkModbusGUI.getProgressBar().setVisible(true);
            izkModbusGUI.getProgressBar().setMaximum(0);
            izkModbusGUI.getProgressBar().setMaximum(100);
         searchProgress = query.searchProgress();
         izkModbusGUI.getProgressBar().setValue(searchProgress);
         if (searchProgress>=100) {
             ((Timer) e.getSource()).stop();
             izkModbusGUI.getProgressBar().setValue(0);
             izkModbusGUI.getProgressBar().setVisible(false);

             JOptionPane.showMessageDialog(izkModbusGUI,"Поиск завершен!");
         }
        });
    }
}
