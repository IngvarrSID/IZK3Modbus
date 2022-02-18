package ru.sid.izk.modbus.runnables;

import ru.sid.izk.modbus.connection.ModbusReader;
import ru.sid.izk.modbus.frames.IZKModbusGUI;

import javax.swing.*;

public class RegulatorButtonsRunnable implements Runnable{

    private final IZKModbusGUI izkModbusGUI;
    private final int offset;
    private final ModbusReader modbusReader;
    private final JButton button;

    public RegulatorButtonsRunnable(IZKModbusGUI izkModbusGUI, int offset, ModbusReader modbusReader, JButton button) {
        this.izkModbusGUI = izkModbusGUI;
        this.offset = offset;
        this.modbusReader = modbusReader;
        this.button = button;
    }

    @Override
    public void run() {
        processAction();
    }

    private void processAction(){

        try {
            button.setEnabled(false);
            boolean coil = modbusReader.coilsReader(offset, 1)[0];
            button.setText(coil? "OFF" : "ON");
            modbusReader.writeCoil(offset,!coil);
            izkModbusGUI.getQueryBox().setSelected(true);
            button.setEnabled(true);
        } catch (Exception ex){
            ex.printStackTrace();
            JOptionPane.showMessageDialog(izkModbusGUI,
                    "Ошибка инициализации:" + ex.getMessage(), "Ошибка", JOptionPane.ERROR_MESSAGE);
            button.setEnabled(true);
        }

    }
}
