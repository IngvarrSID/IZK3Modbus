package ru.sid.izk.modbus.archive;

import au.com.bytecode.opencsv.CSVWriter;
import ru.sid.izk.modbus.frames.IZKModbusGUI;

import java.io.File;
import java.io.FileWriter;
import java.time.LocalDateTime;

public class CSVAdapter {

    private String year;
    private String month;
    private String day;
    private String time;
    private IZKModbusGUI izkModbusGUI;
    private String currentChannel;
    private String path;

    public CSVAdapter(IZKModbusGUI izkModbusGUI){
        this.izkModbusGUI = izkModbusGUI;
       whatsTheTime();
       whatsTheChannel();
       fileCheck();

    }


    private void whatsTheTime(){
    String [] allDate = LocalDateTime.now().toString().split("T");
    String [] date = allDate[0].split("-");
    year = date[0];
    month = date[1];
    day = date[2];
    time = allDate[1];
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

    private void fileCheck(){
        path = String.format("archive/%s/%s/%s/%s.csv",year,month,day,currentChannel);
        try {
            File file = new File(path);
            if (file.exists()){
                //do something
            }else {
                CSVWriter writer = new CSVWriter(new FileWriter(path));
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
