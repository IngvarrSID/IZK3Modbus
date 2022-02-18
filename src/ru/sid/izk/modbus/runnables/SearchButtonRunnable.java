package ru.sid.izk.modbus.runnables;

import ru.sid.izk.modbus.connection.ModbusReader;
import ru.sid.izk.modbus.entity.Query;
import ru.sid.izk.modbus.frames.IZKModbusGUI;

import javax.swing.*;

public class SearchButtonRunnable implements Runnable{

    final private IZKModbusGUI izkModbusGUI;
    final private Query query;
    final private ModbusReader modbusReader;
    private int searchProgress;
    private int channelRead;
    private int mode0;
    private int mode1;

    public SearchButtonRunnable(IZKModbusGUI izkModbusGUI, Query query, ModbusReader modbusReader) {
        this.izkModbusGUI = izkModbusGUI;
        this.query = query;
        this.modbusReader = modbusReader;
    }

    @Override
    public void run() {
        processAction();
    }

    private void processAction(){
        final JCheckBox queryBox = izkModbusGUI.getQueryBox();
        if (queryBox.isSelected()) queryBox.setSelected(false);
        try {
            int[] mods = modbusReader.readHoldingsRegisters(0,2,1);
            mode0 = mods[0];
            mode1 = mods[1];
            //сохраняем режим работы
            modbusReader.writeModeRegister(0,1);
            channelRead = modbusReader.readHoldingsRegisters(3,1,1)[0];
            //сохраняем состояние каналов
            modbusReader.writeModeRegister(3,0);
            //отключаем отпрос датчиков
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
        return new Timer(1000, e -> {
            try {
                izkModbusGUI.getProgressBar().setVisible(true);
                izkModbusGUI.getProgressBar().setMaximum(0);
                izkModbusGUI.getProgressBar().setMaximum(100);
                searchProgress = query.searchProgress();
                izkModbusGUI.getProgressBar().setValue(searchProgress);
                if (searchProgress>=100) {
                    ((Timer) e.getSource()).stop();
                    izkModbusGUI.getProgressBar().setValue(0);
                    izkModbusGUI.getProgressBar().setVisible(false);
                    query.querySearch();

                    switch (query.getSensorCount()){
                        case 0:
                            JOptionPane.showMessageDialog(izkModbusGUI,"Поиск завершен! Датчики не обноружены!","Поиск ДЖС-7",JOptionPane.ERROR_MESSAGE);
                            break;
                        case 1:
                            JOptionPane.showMessageDialog(izkModbusGUI,String.format("<html>Поиск завершен! Обнаружено датчиков: %d<br><br><table border=\"1\"><tr><td>Адрес: %d</td><td>Версия: %d</td><td>Дата: %s</td></tr></table></html>",
                                    query.getSensorCount(),query.getSearchAddress1(),query.getSearchFirmware1(),query.getSearchDate1()),"Поиск ДЖС-7",JOptionPane.INFORMATION_MESSAGE);
                            break;
                        case 2:
                            JOptionPane.showMessageDialog(izkModbusGUI,String.format("<html>Поиск завершен! Обнаружено датчиков: %d<br><br><table border=\"1\"><tr><td>Адрес: %d</td><td>Версия: %d</td><td>Дата: %s</td></tr><tr><td>Адрес: %d</td><td>Версия: %d</td><td>Дата: %s</td></tr></table></html>",
                                    query.getSensorCount(),query.getSearchAddress1(),query.getSearchFirmware1(),query.getSearchDate1(),query.getSearchAddress2(),query.getSearchFirmware2(),query.getSearchDate2()),"Поиск ДЖС-7",JOptionPane.INFORMATION_MESSAGE);
                            break;
                        case 3:
                            JOptionPane.showMessageDialog(izkModbusGUI,String.format("<html>Поиск завершен! Обнаружено датчиков: %d<br><br>Датчик 1: Адрес: %d, Версия: %d, Дата: %s<br><br>Датчик 2: Адрес: %d, Версия: %d, Дата: %s<br><br>Датчик 3: Адрес: %d, Версия: %d, Дата: %s</html>",
                                    query.getSensorCount(),query.getSearchAddress1(),query.getSearchFirmware1(),query.getSearchDate1(),query.getSearchAddress2(),query.getSearchFirmware2(),query.getSearchDate2(),
                                    query.getSearchAddress3(),query.getSearchFirmware3(),query.getSearchDate3()),"Поиск ДЖС-7",JOptionPane.INFORMATION_MESSAGE);
                            break;
                        case 4:
                            JOptionPane.showMessageDialog(izkModbusGUI,String.format("<html>Поиск завершен! Обнаружено датчиков: %d<br<br>>Датчик 1: Адрес: %d, Версия: %d, Дата: %s<br><br>Датчик 2: Адрес: %d, Версия: %d, Дата: %s<br><br>Датчик 3: Адрес: %d, Версия: %d, Дата: %s<br><br>Датчик 4: Адрес: %d, Версия: %d, Дата: %s</html>",
                                    query.getSensorCount(),query.getSearchAddress1(),query.getSearchFirmware1(),query.getSearchDate1(),query.getSearchAddress2(),query.getSearchFirmware2(),query.getSearchDate2(),
                                    query.getSearchAddress3(),query.getSearchFirmware3(),query.getSearchDate3(),query.getSearchAddress4(),query.getSearchFirmware4(),query.getSearchDate4()),"Поиск ДЖС-7",JOptionPane.INFORMATION_MESSAGE);
                            break;
                        default:
                            JOptionPane.showMessageDialog(izkModbusGUI,"Ошибка поиска!","Поиск ДЖС-7",JOptionPane.ERROR_MESSAGE);
                            break;
                    }
                    try {
                        modbusReader.writeModeRegister(0, 1);
                        modbusReader.writeModeRegister(3, channelRead);
                    } catch (Exception ex){
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(izkModbusGUI,
                                "Ошибка включения измерительных каналов " + ex.getMessage(), "Ошибка", JOptionPane.ERROR_MESSAGE);
                    }
                    try {
                        modbusReader.writeModeRegister(0, mode0);
                        modbusReader.writeModeRegister(1, mode1);
                    } catch (Exception ex){
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(izkModbusGUI,
                                "Ошибка выбора режима работы " + ex.getMessage(), "Ошибка", JOptionPane.ERROR_MESSAGE);
                    }

                }
            }catch (Exception ex){
                ex.printStackTrace();
                JOptionPane.showMessageDialog(izkModbusGUI,
                        "Ошибка чтения " + ex.getMessage(), "Ошибка", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}
