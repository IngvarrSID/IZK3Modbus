package ru.sid.izk.modbus.runnables;

import ru.sid.izk.modbus.connection.ModbusReader;
import ru.sid.izk.modbus.entity.Query;
import ru.sid.izk.modbus.frames.IZKModbusGUI;

import javax.swing.*;

import static ru.sid.izk.modbus.constants.CommonConstants.NEED_ACTIVATION_TRIAL_PERIOD_EXPIRED;
import static ru.sid.izk.modbus.constants.CommonConstants.NEED_ACTIVATION_TRIAL_PERIOD_ONGOING;

public class RefButtonRunnable implements Runnable{

    private final IZKModbusGUI izkModbusGUI;
    private final ModbusReader modbusReader;
    private final Query query;

    public RefButtonRunnable(IZKModbusGUI izkModbusGUI, ModbusReader modbusReader, Query query) {
        this.izkModbusGUI = izkModbusGUI;
        this.modbusReader = modbusReader;
        this.query = query;
    }

    @Override
    public void run() {
        processAction();
    }

    public void  processAction() {
        boolean error = false;
        final JCheckBox queryBox = izkModbusGUI.getQueryBox();
        if (queryBox.isSelected()) queryBox.setSelected(false);
        try {
            izkModbusGUI.getRefButton().setEnabled(false);
            modbusReader.writeModeRegister(0, 25);
            modbusReader.writeModeRegister(1, 16);
        } catch (Exception ex) {
            ex.printStackTrace();
            izkModbusGUI.getRefButton().setEnabled(true);
            error = true;
            JOptionPane.showMessageDialog(izkModbusGUI,
                    "Ошибка инициализации " + ex.getMessage(), "Ошибка", JOptionPane.ERROR_MESSAGE);

        }
        if (!error) {
            try {
                query.queryInfo();
                izkModbusGUI.getVersionFirmField().setText(query.getVersionFirm());
                izkModbusGUI.getDataFirmField().setText(query.getDataFirm());
                izkModbusGUI.getIdentificatorField().setText(query.getIdentificator());
                izkModbusGUI.getDataActivField().setText(query.getDataActiv());
                final String statusActive = query.getStatusActive();
                izkModbusGUI.getStatusActivField().setText(statusActive);
                if (NEED_ACTIVATION_TRIAL_PERIOD_ONGOING.equals(statusActive) || NEED_ACTIVATION_TRIAL_PERIOD_EXPIRED.equals(statusActive)) {
                    izkModbusGUI.getActivButton().setEnabled(true);
                    izkModbusGUI.getPasswordField().setEditable(true);
                    izkModbusGUI.getPasswordField().setText("Введите код активации");
                } else {
                    izkModbusGUI.getActivButton().setEnabled(false);
                    izkModbusGUI.getPasswordField().setEditable(false);
                    izkModbusGUI.getPasswordField().setText("");
                }
                izkModbusGUI.getRefButton().setEnabled(true);
            } catch (Exception ex) {
                ex.printStackTrace();
                izkModbusGUI.getRefButton().setEnabled(true);
                JOptionPane.showMessageDialog(izkModbusGUI,
                        "Ошибка чтения " + ex.getMessage(), "Ошибка", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
