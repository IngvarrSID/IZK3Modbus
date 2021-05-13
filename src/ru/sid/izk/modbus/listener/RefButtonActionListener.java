package ru.sid.izk.modbus.listener;

import com.intelligt.modbus.jlibmodbus.exception.ModbusIOException;
import com.intelligt.modbus.jlibmodbus.exception.ModbusNumberException;
import com.intelligt.modbus.jlibmodbus.exception.ModbusProtocolException;
import ru.sid.izk.modbus.connection.ModbusReader;
import ru.sid.izk.modbus.entity.Query;
import ru.sid.izk.modbus.frames.IZKModbusGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static ru.sid.izk.modbus.constants.CommonConstants.NEED_ACTIVATION_TRIAL_PERIOD_EXPIRED;
import static ru.sid.izk.modbus.constants.CommonConstants.NEED_ACTIVATION_TRIAL_PERIOD_ONGOING;

public class RefButtonActionListener implements ActionListener {

    private final Query query;
    private final IZKModbusGUI izkModbusGUI;
    private final ModbusReader modbusReader;

    public RefButtonActionListener(Query query, IZKModbusGUI izkModbusGUI, ModbusReader modbusReader) {

        this.query = query;
        this.izkModbusGUI = izkModbusGUI;
        this.modbusReader = modbusReader;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        final JCheckBox queryBox = izkModbusGUI.getQueryBox();
        if (queryBox.isSelected()) queryBox.setSelected(false);
        try {
            modbusReader.writeModeRegister(1, 16);
            modbusReader.writeModeRegister(0, 25);
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(izkModbusGUI,
                    "Ошибка инициализации " + ex.getMessage(), "Ошибка", JOptionPane.ERROR_MESSAGE);

        }
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
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(izkModbusGUI,
                    "Ошибка чтения " + ex.getMessage(), "Ошибка", JOptionPane.ERROR_MESSAGE);
        }
    }
}