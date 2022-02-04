package ru.sid.izk.modbus.connection;

import com.intelligt.modbus.jlibmodbus.serial.*;

public class Terminal {

    private final SerialParameters sp;
    private final String comName;
    private final String bound;
    private final String timeOut;
    private boolean error;


    public Terminal(String comName, String bound, String timeOut) {
        this.comName = comName;
        this.bound = bound;
        this.timeOut = timeOut;
        this.sp = new SerialParameters();
        sp.setDevice(comName);
        switch (bound){
            case "4800":
                sp.setBaudRate(SerialPort.BaudRate.BAUD_RATE_4800);
                break;
            case "9600":
                sp.setBaudRate(SerialPort.BaudRate.BAUD_RATE_9600);
                break;
            case "14400":
                sp.setBaudRate(SerialPort.BaudRate.BAUD_RATE_14400);
                break;
            case "19200":
                sp.setBaudRate(SerialPort.BaudRate.BAUD_RATE_19200);
                break;
            case "38400":
                sp.setBaudRate(SerialPort.BaudRate.BAUD_RATE_38400);
                break;
            case "57600":
                sp.setBaudRate(SerialPort.BaudRate.BAUD_RATE_57600);
                break;
            case "115200":
                sp.setBaudRate(SerialPort.BaudRate.BAUD_RATE_115200);
                break;
        }
        sp.setDataBits(8);
        sp.setParity(SerialPort.Parity.NONE);
        sp.setStopBits(1);
        SerialUtils.setSerialPortFactory(new SerialPortFactoryJSSC());




    }

    public SerialParameters getSp() {
        return sp;
    }

    public String getComName() {
        return comName;
    }

    public String getBound() {
        return bound;
    }

    public String getTimeOut() {
        return timeOut;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }
}
