package ru.sid.izk.modbus.connection;

import com.intelligt.modbus.jlibmodbus.master.ModbusMaster;
import com.intelligt.modbus.jlibmodbus.master.ModbusMasterFactory;
import com.intelligt.modbus.jlibmodbus.serial.SerialPort;

import javax.swing.*;


public class MasterModbus {

    private ModbusMaster modbusMaster;
    private final int id;
    private final Terminal terminal;

    public MasterModbus(Terminal terminal, int id) {
        this.id = id;
        this.terminal = terminal;
    }

    public void createNewConnect(){
        try {
            modbusMaster = ModbusMasterFactory.createModbusMasterASCII(terminal.getSp());
            modbusMaster.setResponseTimeout(Integer.parseInt(terminal.getTimeOut()));
            modbusMaster.connect();
        } catch (Exception e) {
            e.printStackTrace();
            terminal.setError(true);
        }
    }

    public void setBoundRate115200(){

        try {
            disconnect();
            terminal.getSp().setBaudRate(SerialPort.BaudRate.BAUD_RATE_115200);
            createNewConnect();

        } catch (Exception e) {
            e.printStackTrace();
            terminal.setError(true);
        }
    }

    public void setBoundRate19200(){

        try {
            disconnect();
            terminal.getSp().setBaudRate(SerialPort.BaudRate.BAUD_RATE_19200);
            createNewConnect();

        } catch (Exception e) {
            e.printStackTrace();
            terminal.setError(true);
        }
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

    public ModbusMaster getModbusMaster() {
        return modbusMaster;
    }

    public int getId() {
        return id;
    }
}
