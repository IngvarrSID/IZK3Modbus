package ru.sid.izk.modbus.archive;

import au.com.bytecode.opencsv.CSVWriter;
import ru.sid.izk.modbus.connection.MasterModbus;
import ru.sid.izk.modbus.entity.Query;
import ru.sid.izk.modbus.frames.IZKModbusGUI;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

public class CSVAdapter {

    private String year;
    private String month;
    private String day;
    private String time;
    private final IZKModbusGUI izkModbusGUI;
    private String currentChannel;
    private String path;
    private String fullPath;
    private final MasterModbus masterModbus;
    private final Query query;


    public CSVAdapter(IZKModbusGUI izkModbusGUI, MasterModbus masterModbus, Query query){
        this.izkModbusGUI = izkModbusGUI;
        this.masterModbus =  masterModbus;
        this.query = query;
       whatsTheTime();
       whatsTheChannel();
       fileWrite();
    }


    private void whatsTheTime(){
    String [] allDate = LocalDateTime.now().toString().split("T");
    String [] date = allDate[0].split("-");
    year = date[0];
    month = date[1];
    day = date[2];
    String[] fullTime = allDate[1].split("\\.");
    time = fullTime[0];
    }

    private void whatsTheChannel(){
        switch (izkModbusGUI.getChannelsBox().getSelectedIndex()){
            case 0:
                currentChannel = "channel1";
                break;
            case 1:
                currentChannel = "channel2";
                break;
            case 2:
                currentChannel = "channel3";
                break;
            case 3:
                currentChannel = "channel4";
                break;
            default:
                currentChannel = "unknown";
        }
    }

    private void fileWrite(){
        String dgsAddress = String.valueOf(query.getSensorAddress());
        String humidity = String.format("%.2f",query.getHumidity());
        String density = String.format("%.1f",query.getDensity());
        String temperature = String.format("%.1f",query.getTemperature());
        String cs1 = String.format("%.1f",query.getCs1());
        String cs2 = String.format("%.1f",query.getCs2());
        String period = String.format("%.1f",query.getPeriod());
        String error = String.format("%.1f",query.getError());
        path = String.format("%s/Documents/Technosensor/archive/%s/%s/%s/IZK%d",System.getProperty("user.home"),year,month,day,masterModbus.getId());
        fullPath = String.format("%s/%s.csv",path,currentChannel);
        try {
            File file = new File(fullPath);
            if (file.exists()){
                CSVWriter writer = new CSVWriter(new FileWriter(fullPath,true),';','"');
                String[] data = String.format("%s.%s.%s.%s.%s.%s.%s.%s.%s.%s",time,masterModbus.getId(),dgsAddress,humidity,temperature,density,period,cs1,cs2,error).split("\\.");
                writer.writeNext(data);
                writer.close();

            }else {
                FileOutputStream fos = new FileOutputStream(fullPath);
                Writer preWriter = new OutputStreamWriter(fos,Charset.forName("Windows-1251"));
                CSVWriter writer = new CSVWriter(preWriter,';','"');
                String head1 = "Время.Адрес ИЗК.Адрес ДЖС.Влажность, %.Температура, °C.Плотность, кг/м².Период.CS1, пФ.CS2, пФ.погрешность";
                String[] head = head1.split("\\.");
                writer.writeNext(head);
                String[] data = String.format("%s.%s.%s.%s.%s.%s.%s.%s.%s.%s",time,masterModbus.getId(),dgsAddress,humidity,temperature,density,period,cs1,cs2,error).split("\\.");
                writer.writeNext(data);
                writer.close();
                fos.close();
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

}
