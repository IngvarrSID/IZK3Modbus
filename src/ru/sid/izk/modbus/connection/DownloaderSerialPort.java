package ru.sid.izk.modbus.connection;

import com.intelligt.modbus.jlibmodbus.serial.SerialPortAT;
import jssc.SerialPort;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;

public class DownloaderSerialPort {

    public SerialPort serialPort;

    public DownloaderSerialPort(String id) {
        try {
            serialPort = new SerialPort(id);
            serialPort.openPort();
            serialPort.setParams(SerialPort.BAUDRATE_19200, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
            serialPort.addEventListener(new PortReader());
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public class PortReader implements SerialPortEventListener{
//TODO работает!
        @Override
        public void serialEvent(SerialPortEvent event) {
            if (event.isRXCHAR() && event.getEventValue()>0){
                try {
                    String s = serialPort.readString(event.getEventValue());
                    System.out.println(s);
                    if(s.contains("AVRREADY")){
                        serialPort.writeString("@");
                    }
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }
}
