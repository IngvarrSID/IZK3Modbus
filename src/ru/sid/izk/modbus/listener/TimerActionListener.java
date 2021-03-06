package ru.sid.izk.modbus.listener;

import ru.sid.izk.modbus.archive.CSVAdapter;
import ru.sid.izk.modbus.connection.MasterModbus;
import ru.sid.izk.modbus.entity.Query;
import ru.sid.izk.modbus.frames.IZKModbusGUI;
import ru.sid.izk.modbus.utils.FieldVisible;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

            CSVAdapter csvAdapter = new CSVAdapter(izkModbusGUI,masterModbus,query);
            csvAdapter.fileWrite();
            izkModbusGUI.refreshTable(csvAdapter);
        } else
            System.out.println("ждем");
        if (FieldVisible.isStatus()){
            query.queryPidRegulator();
            izkModbusGUI.getPidErrField().setText(String.format("PID_err: %.1f у.е.",query.getPidErr()));
            izkModbusGUI.getPidIntField().setText(String.format("PID_int: %.1f у.е.",query.getPidInt()));
            izkModbusGUI.getPidDifField().setText(String.format("PID_err: %.1f у.е.",query.getPidDif()));
            izkModbusGUI.getProgressRegulatorBar().setValue(query.getPosition());
            izkModbusGUI.getRegulatorStatusField().setText(query.getRegStatus());
            izkModbusGUI.getModeButton().setText(query.getRegulatorMode());
            izkModbusGUI.getManualButton().setText(query.getManualMode());
            izkModbusGUI.getCloseButton().setText(query.getClose());
            izkModbusGUI.getOpenButton().setText(query.getOpen());
            izkModbusGUI.getFullOpenButton().setText(query.getOpenFull());
            izkModbusGUI.getFullCloseButton().setText(query.getCloseFull());
        }
    }
}