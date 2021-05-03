package ru.sid.izk.modbus.listener;

import ru.sid.izk.modbus.connection.ModbusReader;
import ru.sid.izk.modbus.entity.Query;
import ru.sid.izk.modbus.frames.IZKModbusGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RefreshRegulatorButtonActionListener implements ActionListener {

    private final Query query;
    private final ModbusReader modbusReader;
    private final IZKModbusGUI izkModbusGUI;

    public RefreshRegulatorButtonActionListener(Query query,ModbusReader modbusReader,IZKModbusGUI izkModbusGUI){
        this.query = query;
        this.modbusReader = modbusReader;
        this.izkModbusGUI = izkModbusGUI;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            modbusReader.writeModeRegister(0,1);
            query.queryPidSettings();
            izkModbusGUI.getProgressRegulatorBar().setMinimum(0);
            izkModbusGUI.getProgressRegulatorBar().setMaximum(query.getFullStep());
            izkModbusGUI.getKpWriteField().setText(String.format("%.1f",query.getkP()));
            izkModbusGUI.getKiWriteField().setText(String.format("%.1f",query.getkI()));
            izkModbusGUI.getKdWriteField().setText(String.format("%.1f",query.getkD()));
            izkModbusGUI.getHumidityWriteField().setText(String.format("%.1f",query.getHumidityRequired()));
            izkModbusGUI.getOneStepRegulator().setText(String.valueOf(query.getStep()));
            izkModbusGUI.getFullStepRegulator().setText(String.valueOf(query.getFullStep()));

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
        } catch (Exception ex){
            ex.printStackTrace();
            JOptionPane.showMessageDialog(izkModbusGUI,
                    "Ошибка инициализации " + ex.getMessage(), "Ошибка", JOptionPane.ERROR_MESSAGE);
        }

    }
}
