package ru.sid.izk.modbus.listener;


import ru.sid.izk.modbus.connection.ModbusReader;
import ru.sid.izk.modbus.entity.Query;
import ru.sid.izk.modbus.frames.IZKModbusGUI;
import ru.sid.izk.modbus.runnables.SearchButtonRunnable;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SearchButtonActionListener implements ActionListener {
   final private IZKModbusGUI izkModbusGUI;
   final private Query query;
   final private ModbusReader modbusReader;


   public SearchButtonActionListener(IZKModbusGUI izkModbusGUI,Query query,ModbusReader modbusReader){
       this.modbusReader = modbusReader;
       this.izkModbusGUI = izkModbusGUI;
       this.query = query;
   }

    @Override
    public void actionPerformed(ActionEvent e) {
       izkModbusGUI.getExecutor().execute(new SearchButtonRunnable(izkModbusGUI,query,modbusReader));
    }


}
