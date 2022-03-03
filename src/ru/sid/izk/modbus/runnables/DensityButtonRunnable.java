package ru.sid.izk.modbus.runnables;

import ru.sid.izk.modbus.connection.ModbusReader;
import ru.sid.izk.modbus.entity.Query;
import ru.sid.izk.modbus.frames.IZKModbusGUI;

import javax.swing.*;

import static ru.sid.izk.modbus.utils.BitsReversUtils.bitsReader;

public class DensityButtonRunnable implements Runnable{

    private final IZKModbusGUI izkModbusGUI;
    private final ModbusReader modbusReader;
    private final Query query;

    public DensityButtonRunnable(IZKModbusGUI izkModbusGUI, ModbusReader modbusReader, Query query) {
        this.izkModbusGUI = izkModbusGUI;
        this.modbusReader = modbusReader;
        this.query = query;
    }

    @Override
    public void run() {
        processAction();
    }

    public void processAction(){
        String buttonName = izkModbusGUI.getDensityButton().getText();

            char[] sensorMode = query.getSensorMode();
            sensorMode[3] = buttonName.equals("ВЫКЛ") ? '1' : '0';
            String reversString = new String(sensorMode);
            try {
                modbusReader.writeModeRegister(4, Integer.parseInt(bitsReader(reversString), 2));
                if(sensorMode[3] =='1'){
                  izkModbusGUI.getDensityButton().setText("ВКЛ");
                } else {
                    izkModbusGUI.getDensityButton().setText("ВЫКЛ");
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(izkModbusGUI,
                        "Ошибка записи " + e.getMessage(), "Ошибка", JOptionPane.ERROR_MESSAGE);
            }

    }
}
