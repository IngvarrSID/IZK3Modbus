package ru.sid.izk.modbus.runnables;

import ru.sid.izk.modbus.connection.ModbusReader;
import ru.sid.izk.modbus.frames.IZKModbusGUI;
import ru.sid.izk.modbus.utils.DensityTableUnits;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class StmLoadNewFirmwareRunnable implements Runnable{

    private final IZKModbusGUI izkModbusGUI;
    private final ModbusReader modbusReader;

    public StmLoadNewFirmwareRunnable(IZKModbusGUI izkModbusGUI, ModbusReader modbusReader) {
        this.izkModbusGUI = izkModbusGUI;
        this.modbusReader = modbusReader;
    }

    @Override
    public void run() {
        processAction();
    }

    private void processAction(){
        JFileChooser fileChooser = new JFileChooser(String.format("%s/Documents/Technosensor/", System.getProperty("user.home")));
        fileChooser.setDialogTitle("Выбор файла прошивки STM");
        FileNameExtensionFilter filter = new FileNameExtensionFilter("SU5 firmware files", "hex");
        fileChooser.setFileFilter(filter);
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int result = fileChooser.showOpenDialog(izkModbusGUI);
        if (result == JFileChooser.APPROVE_OPTION) {
            try {
                modbusReader.writeModeRegister(0,34);

                byte[] allBytes = Files.readAllBytes(Paths.get(fileChooser.getSelectedFile().getPath()));
                int byteQuantity = allBytes.length;
                int count = byteQuantity/64;
                int remainder = byteQuantity%64;

                System.out.println(count);
                System.out.println(remainder);

                JProgressBar progressBar = izkModbusGUI.getProgressBar();
                progressBar.setMaximum(byteQuantity);
                progressBar.setVisible(true);
                progressBar.setValue(0);

                int k = 0;

                for (int i = 0; i < count/2; i++) {
                    int[] registers = new int[32];
                    k = i*32;
                    for (int j = i; j <registers.length ; j++) {
                        registers[j] = ((allBytes[k] & 0xff) << 8) | (allBytes[k+1] & 0xff);
                        k++;
                    }
                    modbusReader.writeModeRegister(2,0);
                    System.out.println("1");
                    modbusReader.writeModeRegister(3,k);
                    System.out.println("2");
                    modbusReader.writeModeRegister(4,32);
                    System.out.println("3");
                    modbusReader.writeMultipleRegisters(k,registers);
                    System.out.println("4");
                    progressBar.setValue(i*128);
                    System.out.println(i);
                }
                if(count%2 !=0) remainder =remainder+1;

                modbusReader.writeModeRegister(2,0);
                modbusReader.writeModeRegister(3,k);
                modbusReader.writeModeRegister(4,remainder/2);

                int[] registers = new int[remainder/2];

                for (int j = k; j <registers.length ; j++) {
                    registers[j] = ((allBytes[k] & 0xff) << 8) | (allBytes[k+1] & 0xff);
                    k++;
                }

                modbusReader.writeMultipleRegisters(k,registers);

                modbusReader.writeCoil(38,true);
                modbusReader.writeCoil(41,true);

                JOptionPane.showMessageDialog(izkModbusGUI,
                        "Таблица загружена в программу", "Подтверждение", JOptionPane.INFORMATION_MESSAGE);


            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(izkModbusGUI,
                        "Ошибка загрузки таблицы " + ex.getMessage(), "Ошибка", JOptionPane.ERROR_MESSAGE);

            }
        }
    }
}
