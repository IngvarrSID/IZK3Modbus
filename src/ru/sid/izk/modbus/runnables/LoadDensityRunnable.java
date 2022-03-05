package ru.sid.izk.modbus.runnables;

import ru.sid.izk.modbus.connection.ModbusReader;
import ru.sid.izk.modbus.frames.IZKModbusGUI;
import ru.sid.izk.modbus.utils.DensityTableUnits;
import ru.sid.izk.modbus.utils.TarTableReader;

import javax.swing.*;
import java.util.List;

import static ru.sid.izk.modbus.utils.FromHexUtils.hexStringFromField;

public class LoadDensityRunnable implements Runnable {

    private final IZKModbusGUI izkModbusGUI;
    private final ModbusReader modbusReader;
    private final List<String[]> list;

    public LoadDensityRunnable(IZKModbusGUI izkModbusGUI, ModbusReader modbusReader, List<String[]> list) {
        this.izkModbusGUI = izkModbusGUI;
        this.modbusReader = modbusReader;
        this.list = list;
    }

    @Override
    public void run() {
        izkModbusGUI.getLoadDensityTableButton().setEnabled(false);
        processAction();
        izkModbusGUI.getLoadDensityTableButton().setEnabled(true);


    }

    public void processAction() {
        try {

            modbusReader.writeModeRegister(0, 13);
            int[] registers = new int[1024];
            int j = 0;
            for (int i = 0; i < list.size(); i++) {
                String numb1 = hexStringFromField(list.get(i)[0]);
                String numb2 = hexStringFromField(list.get(i)[1]);
                registers[j++] = Integer.valueOf(numb1.substring(4,8),16);
                registers[j++] = Integer.valueOf(numb1.substring(0,4),16);
                registers[j++] = Integer.valueOf(numb2.substring(4,8),16);
                registers[j++] = Integer.valueOf(numb2.substring(0,4),16);
            }
            for (int i = 0; i < 1024; i= i+32) {
                int [] writeRegisters = new int[32];
                for (int k = 0; k < writeRegisters.length; k++) {
                    writeRegisters[k] = registers[i+k];
                }
                modbusReader.writeMultipleRegisters(2+i,writeRegisters);
            }
            JOptionPane.showMessageDialog(izkModbusGUI,
                    "Таблица загружена в блок ИЗК-3", "Подтверждение", JOptionPane.INFORMATION_MESSAGE);


        } catch (Exception e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(izkModbusGUI,
                    "Ошибка записи " + e.getMessage(), "Ошибка", JOptionPane.ERROR_MESSAGE);
        }

    }
}

