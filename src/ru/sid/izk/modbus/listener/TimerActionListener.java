package ru.sid.izk.modbus.listener;

import ru.sid.izk.modbus.entity.Query;
import ru.sid.izk.modbus.frames.IZKModbusGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TimerActionListener implements ActionListener {

    private final Query query;
    private final IZKModbusGUI izkModbusGUI;

    public TimerActionListener(Query query, IZKModbusGUI izkModbusGUI) {

        this.query = query;
        this.izkModbusGUI = izkModbusGUI;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
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
            System.out.println("Таймер");
        } else
            System.out.println("ждем");
    }
}