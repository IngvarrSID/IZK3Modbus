package ru.sid.izk.modbus.runnables;

import ru.sid.izk.modbus.connection.ModbusReader;
import ru.sid.izk.modbus.frames.IZKModbusGUI;


import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.nio.file.Files;
import java.nio.file.Paths;


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

                System.out.println(byteQuantity);
                System.out.println(count);
                System.out.println(remainder);

                JProgressBar progressBar = izkModbusGUI.getProgressBar();
                progressBar.setMaximum(byteQuantity);
                progressBar.setVisible(true);
                progressBar.setValue(0);

                izkModbusGUI.getDataLabel().setText("Загрузка прошивки");

                int k;

                for (int i = 0; i < count; i++) {
                    int[] registers = new int[32];
                    k = i*64;
                    for (int j = 0; j <registers.length ; j++) {
                        registers[j] = ((allBytes[k+1] & 0xff) << 8) | (allBytes[k] & 0xff);
                        k = k+2;
                    }
                    int[] data = new int[2];

                    data[0] =  ((i*32) & 0xFFFF);
                    data[1] =  (((i*32) >> 16) & 0xFFFF);


                    modbusReader.writeMultipleRegisters(2,data);
                    modbusReader.writeModeRegister(4,32);
                    modbusReader.writeMultipleRegisters(5,registers);
                    progressBar.setValue(i*64);
                }
                k = count*64;

                if(remainder%2 !=0) {
                    remainder = remainder + 1;
                    int[] data = new int[2];
                    data[0] =  (k/2 & 0xFFFF);
                    data[1] =  ((k/2 >> 16) & 0xFFFF);
                    modbusReader.writeMultipleRegisters(2,data);
                    modbusReader.writeModeRegister(4, remainder / 2);

                    int[] registers = new int[remainder / 2];

                    for (int j = 0; j < registers.length; j++) {
                        if (j==registers.length-1) {
                            registers[j] = (0xff<<8) | (allBytes[k] & 0xff);
                        } else {
                        registers[j] = ((allBytes[k+1] & 0xff) << 8) | (allBytes[k] & 0xff);
                        }
                        k = k+2;
                    }

                    modbusReader.writeMultipleRegisters(5, registers);

                }
                else {
                    int[] data = new int[2];

                    data[0] =  (k/2 & 0xFFFF);
                    data[1] =  ((k/2 >> 16) & 0xFFFF);
                    modbusReader.writeMultipleRegisters(2,data);
                    modbusReader.writeModeRegister(4, remainder / 2);

                    int[] registers = new int[remainder / 2];

                    for (int j = 0; j < registers.length; j++) {

                            registers[j] = ((allBytes[k+1] & 0xff) << 8) | (allBytes[k] & 0xff);
                        k = k+2;
                    }

                    modbusReader.writeMultipleRegisters(5, registers);
                }

                progressBar.setValue(byteQuantity);

                modbusReader.writeCoil(39,true);
                modbusReader.writeCoil(41,true);

                izkModbusGUI.getDataLabel().setText("Загрузка завершена");

                JOptionPane.showMessageDialog(izkModbusGUI,
                        "<html><center>Прошивка загружена в блок.<br>После автоматической перезагрузки блока в течение 1 минуты<br>прошивка будет установленна.</center></html>", "Подтверждение", JOptionPane.INFORMATION_MESSAGE);
                progressBar.setVisible(false);

            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(izkModbusGUI,
                        "Ошибка загрузки прошивки в блок " + ex.getMessage(), "Ошибка", JOptionPane.ERROR_MESSAGE);

            }
        }
    }
}
