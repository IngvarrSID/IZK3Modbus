package ru.sid.izk.modbus.runnables;

import ru.sid.izk.modbus.connection.ModbusReader;
import ru.sid.izk.modbus.frames.IZKModbusGUI;
import ru.sid.izk.modbus.listener.EraseTimerActionListener;

import javax.swing.*;

public class StmStartLoaderRunnable implements Runnable{

    private final ModbusReader modbusReader;
    private final IZKModbusGUI izkModbusGUI;

    public StmStartLoaderRunnable(ModbusReader modbusReader, IZKModbusGUI izkModbusGUI) {
        this.modbusReader = modbusReader;
        this.izkModbusGUI = izkModbusGUI;
    }


    @Override
    public void run() {
        processAction();
    }

    private void processAction(){

        try {
            izkModbusGUI.getQueryBox().setSelected(false);
            modbusReader.writeCoil(37, true);

            Timer eraseTimer = new Timer(500, new EraseTimerActionListener(izkModbusGUI, modbusReader));
            eraseTimer.start();
            izkModbusGUI.getDataLabel().setText("Идет стирание");
        } catch (Exception ex)
        {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(izkModbusGUI,
                    "Ошибка связи " + ex.getMessage(), "Ошибка", JOptionPane.ERROR_MESSAGE);
        }
    }
}
