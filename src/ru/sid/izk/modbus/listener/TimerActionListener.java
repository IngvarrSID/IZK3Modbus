package ru.sid.izk.modbus.listener;

import ru.sid.izk.modbus.archive.CSVAdapter;
import ru.sid.izk.modbus.connection.MasterModbus;
import ru.sid.izk.modbus.entity.Query;
import ru.sid.izk.modbus.frames.IZKModbusGUI;
import ru.sid.izk.modbus.utils.FieldVisible;
import ru.sid.izk.modbus.utils.Settings;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

public class TimerActionListener implements ActionListener {

    private final Query query;
    private final IZKModbusGUI izkModbusGUI;
    private final MasterModbus masterModbus;

    public TimerActionListener(Query query, IZKModbusGUI izkModbusGUI, MasterModbus masterModbus) {

        this.query = query;
        this.izkModbusGUI = izkModbusGUI;
        this.masterModbus = masterModbus;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (izkModbusGUI.getChannelsBox().getSelectedIndex() !=5) {
            try {
                query.queryStatus();
                final String status = query.getStatus();
                izkModbusGUI.getStatLabel().setText(status);
                if (status.equals("Данные получены")) {
                    query.queryMain();
                    izkModbusGUI.getSensorAddressField().setText(String.format("Адрес платы: %d", query.getSensorAddress()));
                    izkModbusGUI.getTimeField().setText(String.format("Текущее время: %s", query.getTime()));
                    izkModbusGUI.getHumidityField().setText(String.format("Влажность: %.2f %%", query.getHumidity()));
                    izkModbusGUI.getTemperatureField().setText(String.format("Температура: %.1f °C", query.getTemperature()));
                    izkModbusGUI.getDensityField().setText(String.format("Плотность: %.1f кг/м²", query.getDensity()));
                    izkModbusGUI.getPeriodField().setText(String.format("Период: %.1f у.е.", query.getPeriod()));
                    izkModbusGUI.getCs1Field().setText(String.format("CS1: %.1f Пф", query.getCs1()));
                    izkModbusGUI.getCs2Field().setText(String.format("CS2: %.1f Пф", query.getCs2()));
                    izkModbusGUI.getErrorField().setText(String.format("Инстр. погрешность: %.1f у.е.", query.getError()));
                    izkModbusGUI.getDataField().setText(String.format("Текущая дата: %s", query.getData()));

                    CSVAdapter csvAdapter = new CSVAdapter(izkModbusGUI, masterModbus, query);
                    csvAdapter.fileWrite();
                    izkModbusGUI.refreshTable(csvAdapter);
                }
                if (FieldVisible.isStatus()) {
                    query.queryPidRegulator();
                    izkModbusGUI.getPidErrField().setText(String.format("PID_err: %.1f у.е.", query.getPidErr()));
                    izkModbusGUI.getPidIntField().setText(String.format("PID_int: %.1f у.е.", query.getPidInt()));
                    izkModbusGUI.getPidDifField().setText(String.format("PID_err: %.1f у.е.", query.getPidDif()));
                    izkModbusGUI.getProgressRegulatorBar().setValue(query.getPosition());
                    izkModbusGUI.getRegulatorStatusField().setText(query.getRegStatus());
                    izkModbusGUI.getModeButton().setText(query.getRegulatorMode());
                    izkModbusGUI.getManualButton().setText(query.getManualMode());
                    izkModbusGUI.getCloseButton().setText(query.getClose());
                    izkModbusGUI.getOpenButton().setText(query.getOpen());
                    izkModbusGUI.getFullOpenButton().setText(query.getOpenFull());
                    izkModbusGUI.getFullCloseButton().setText(query.getCloseFull());
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(izkModbusGUI,
                        "Ошибка чтения " + ex.getMessage(), "Ошибка", JOptionPane.ERROR_MESSAGE);
                izkModbusGUI.getQueryBox().setSelected(false);
            }
        } else {
            try{
                String elMetroSaveLevel = "0";
                String korundSaveLevel = "0";
                if(Settings.propertiesFileExists()){
                    final Settings settings = new Settings();
                    elMetroSaveLevel = settings.getElMetroX();
                    korundSaveLevel = settings.getKorundX();
                }
                query.queryLevel();
                izkModbusGUI.getSensorAddressField().setText(String.format("Влажность: %.2f %%", query.getHumidityLevel()));
                izkModbusGUI.getHumidityField().setText(String.format("Плотность: %.1f кг/м²", query.getDensityLevel()));
                float elMetroLevelCalculate = 10 - Float.parseFloat(elMetroSaveLevel.replace(',','.'))-query.getElMetroDistance();
             //   izkModbusGUI.getTemperatureField().setText(String.format("Уровень ЭлМетро: %.3f мм", query.getElMetroLevel()));
                izkModbusGUI.getTemperatureField().setText(String.format("Уровень ЭлМетро: %.3f мм", elMetroLevelCalculate));
                izkModbusGUI.getDensityField().setText(String.format("Расстояние ЭлМетро: %.3f мм", query.getElMetroDistance()));
                izkModbusGUI.getPeriodField().setText(String.format("Температура ЭлМетро: %.1f °C", query.getElMetroTemperature()));
                izkModbusGUI.getCs1Field().setText(String.format("Уровень воды Корунд: %.3f мм", query.getKorundWaterLevel()));
               // izkModbusGUI.getCs2Field().setText(String.format("Уровень мазута Корунд: %.3f мм", query.getKorundFuelOil()));
                float korundLevelCalculate = query.getKorundFuelOil() + Float.parseFloat(korundSaveLevel.replace(',','.'));
                izkModbusGUI.getCs2Field().setText(String.format("Уровень мазута Корунд: %.3f мм",korundLevelCalculate ));
                StringBuilder s = new StringBuilder();
                for (float f:query.getTemperatures()) {
                    if (!s.toString().equals("")) s.append(", ");

                        s.append(String.format("%.1f", f));

                }
                String s1 = s.toString();
                //s1 = s1.substring(s1.length()-1);
                izkModbusGUI.getErrorField().setText(String.format("Температуры: %s °C", s1));
                String time = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
                LocalDate date = LocalDate.now();
                izkModbusGUI.getDataField().setText(String.format("Текущая дата: %s", date));
                izkModbusGUI.getTimeField().setText(String.format("Текущее время: %s", time));

                    CSVAdapter csvAdapter = new CSVAdapter(izkModbusGUI, masterModbus, query);
                    csvAdapter.fileWrite();
                    izkModbusGUI.refreshTable(csvAdapter);



            } catch (Exception ex){
                ex.printStackTrace();
                JOptionPane.showMessageDialog(izkModbusGUI,
                        "Ошибка чтения " + ex.getMessage(), "Ошибка", JOptionPane.ERROR_MESSAGE);
                izkModbusGUI.getQueryBox().setSelected(false);
            }


        }
    }
}