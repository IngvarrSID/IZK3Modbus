package ru.sid.izk.modbus.listener;

import com.intelligt.modbus.jlibmodbus.exception.ModbusIOException;
import com.intelligt.modbus.jlibmodbus.exception.ModbusNumberException;
import com.intelligt.modbus.jlibmodbus.exception.ModbusProtocolException;
import ru.sid.izk.modbus.archive.CSVAdapter;
import ru.sid.izk.modbus.connection.MasterModbus;
import ru.sid.izk.modbus.entity.Query;
import ru.sid.izk.modbus.frames.IZKModbusGUI;
import ru.sid.izk.modbus.runnables.QueryBoxRunnable;
import ru.sid.izk.modbus.runnables.QueryTimerRunnable;
import ru.sid.izk.modbus.utils.FieldVisible;
import ru.sid.izk.modbus.utils.Settings;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class QueryTimerActionListener implements ActionListener {

    private final Query query;
    private final IZKModbusGUI izkModbusGUI;
    private final MasterModbus masterModbus;

    public QueryTimerActionListener(Query query, IZKModbusGUI izkModbusGUI, MasterModbus masterModbus) {

        this.query = query;
        this.izkModbusGUI = izkModbusGUI;
        this.masterModbus = masterModbus;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        izkModbusGUI.getExecutor().execute(new QueryTimerRunnable(izkModbusGUI,query,masterModbus));
    }
}