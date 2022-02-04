package ru.sid.izk.modbus.listener;

import com.intelligt.modbus.jlibmodbus.exception.ModbusIOException;
import com.intelligt.modbus.jlibmodbus.exception.ModbusNumberException;
import com.intelligt.modbus.jlibmodbus.exception.ModbusProtocolException;
import ru.sid.izk.modbus.connection.ModbusReader;
import ru.sid.izk.modbus.entity.Query;
import ru.sid.izk.modbus.frames.IZKModbusGUI;
import ru.sid.izk.modbus.runnables.RefButtonRunnable;

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

        izkModbusGUI.getExecutor().execute(new RefButtonRunnable(izkModbusGUI,modbusReader,query));
    }
}