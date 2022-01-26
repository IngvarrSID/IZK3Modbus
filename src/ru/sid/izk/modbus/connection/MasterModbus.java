package ru.sid.izk.modbus.connection;

import com.intelligt.modbus.jlibmodbus.master.ModbusMaster;
import com.intelligt.modbus.jlibmodbus.master.ModbusMasterFactory;

import javax.swing.*;


public class MasterModbus {

    private ModbusMaster modbusMaster;
    private final int id;
    private final Terminal terminal;

    public MasterModbus(Terminal terminal, int id) {
        this.id = id;
        this.terminal = terminal;
        try {
            modbusMaster = ModbusMasterFactory.createModbusMasterASCII(terminal.getSp());
            modbusMaster.setResponseTimeout(Integer.parseInt(terminal.getTimeOut()));
            modbusMaster.connect();
        } catch (Exception e) {
            e.printStackTrace();
            terminal.setError(true);
        }
    }


    public ModbusMaster getModbusMaster() {
        return modbusMaster;
    }

    public int getId() {
        return id;
    }

    public void disconnect() {
        try {
            modbusMaster.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Terminal getTerminal() {
        return terminal;
    }
}
