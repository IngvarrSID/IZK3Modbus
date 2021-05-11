package ru.sid.izk.modbus.connection;

import com.intelligt.modbus.jlibmodbus.serial.SerialPortAT;
import jssc.SerialPort;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import ru.sid.izk.modbus.frames.IZKModbusGUI;

import java.awt.*;
import java.io.File;

public class DownloaderSerialPort {

    private SerialPort serialPort;

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
        @Override
        public void serialEvent(SerialPortEvent event) {
            if (event.isRXCHAR() && event.getEventValue()>0){
                try {
                    String s = serialPort.readString(event.getEventValue());
                    if(s.contains("AVRREADY")){
                        serialPort.writeString("@");
                        File file = new File("AVRprog.exe");
                        serialPort.closePort();
                        Desktop.getDesktop().open(file);
                        System.exit(0);
                    }
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }
}
