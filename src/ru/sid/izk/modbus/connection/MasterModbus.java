package ru.sid.izk.modbus.connection;

import com.intelligt.modbus.jlibmodbus.master.ModbusMaster;
import com.intelligt.modbus.jlibmodbus.master.ModbusMasterFactory;


public class MasterModbus {
    private ModbusMaster modbusMaster;
    private final int id;

    public MasterModbus(Terminal terminal, int id) {
        this.id = id;
        try {
            modbusMaster = ModbusMasterFactory.createModbusMasterASCII(terminal.getSp());
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
}
