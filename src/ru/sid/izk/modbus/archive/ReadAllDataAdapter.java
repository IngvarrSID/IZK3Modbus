package ru.sid.izk.modbus.archive;

import com.intelligt.modbus.jlibmodbus.exception.ModbusIOException;
import com.intelligt.modbus.jlibmodbus.exception.ModbusNumberException;
import com.intelligt.modbus.jlibmodbus.exception.ModbusProtocolException;
import ru.sid.izk.modbus.connection.ModbusReader;
import ru.sid.izk.modbus.entity.Query;

import java.io.Serializable;


public class ReadAllDataAdapter implements Serializable {
    //mode0 = 1
    private int addressIZK;
    private boolean[] activatedChannel;
    private int settingRelay1;
    private int numberRelay1;
    private int modeRelay1;
    private int settingRelay2;
    private int numberRelay2;
    private int modeRelay2;
    private int settingRelay3;
    private int numberRelay3;
    private int modeRelay3;
    private int settingRelay4;
    private int numberRelay4;
    private int modeRelay4;
    private int settingRelay5;
    private int numberRelay5;
    private int modeRelay5;
    private int settingRelay6;
    private int numberRelay6;
    private int modeRelay6;
    private int settingRelay7;
    private int numberRelay7;
    private int modeRelay7;
    private int settingRelay8;
    private int numberRelay8;
    private int modeRelay8;
    private int settingRelay9;
    private int numberRelay9;
    private int modeRelay9;
    private int settingRelay10;
    private int numberRelay10;
    private int modeRelay10;
    private float kP;
    private float kI;
    private float kD;
    private float requiredHumidity;
    private int step;
    private int fullStep;
    //mode0 = 5
    private int sensorAddressWrite1;
    private int timeoutWrite1;
    private int periodWrite1;
    private int t01Write1;
    private float ck1Write1;
    private float cd1Write1;
    private int checkPeriod1;
    private float errorWrite1;
    private float cs1001;
    private float cm1;
    private float k1;
    private float cs01;
    private float tc1;
    private float csMin1;
    private float hMin1;
    private float tsd11;
    private float tsd21;
    private float autoMin1;
    private float autoMax1;
    private float d201;
    private float kd1;
    private int min1;
    private int max1;
    private int emerMax1;
    private int noDensity1;
    //mode0 = 6
    private int sensorAddressWrite2;
    private int timeoutWrite2;
    private int periodWrite2;
    private int t01Write2;
    private float ck1Write2;
    private float cd1Write2;
    private int checkPeriod2;
    private float errorWrite2;
    private float cs1002;
    private float cm2;
    private float k2;
    private float cs02;
    private float tc2;
    private float csMin2;
    private float hMin2;
    private float tsd12;
    private float tsd22;
    private float autoMin2;
    private float autoMax2;
    private float d202;
    private float kd2;
    private int min2;
    private int max2;
    private int emerMax2;
    private int noDensity2;
    //mode0 = 7
    private int sensorAddressWrite3;
    private int timeoutWrite3;
    private int periodWrite3;
    private int t01Write3;
    private float ck1Write3;
    private float cd1Write3;
    private int checkPeriod3;
    private float errorWrite3;
    private float cs1003;
    private float cm3;
    private float k3;
    private float cs03;
    private float tc3;
    private float csMin3;
    private float hMin3;
    private float tsd13;
    private float tsd23;
    private float autoMin3;
    private float autoMax3;
    private float d203;
    private float kd3;
    private int min3;
    private int max3;
    private int emerMax3;
    private int noDensity3;
    //mode0 = 8
    private int sensorAddressWrite4;
    private int timeoutWrite4;
    private int periodWrite4;
    private int t01Write4;
    private float ck1Write4;
    private float cd1Write4;
    private int checkPeriod4;
    private float errorWrite4;
    private float cs1004;
    private float cm4;
    private float k4;
    private float cs04;
    private float tc4;
    private float csMin4;
    private float hMin4;
    private float tsd14;
    private float tsd24;
    private float autoMin4;
    private float autoMax4;
    private float d204;
    private float kd4;
    private int min4;
    private int max4;
    private int emerMax4;
    private int noDensity4;

    private final Query query;
    private final ModbusReader modbusReader;

    private boolean dataUpDate;


    public ReadAllDataAdapter(Query query, ModbusReader modbusReader) {
        this.query = query;
        this.modbusReader = modbusReader;
    }

    public void readSettingsData() throws ModbusNumberException, ModbusProtocolException, ModbusIOException {

            modbusReader.writeModeRegister(0, 1);
            query.querySettings();
            addressIZK = query.getAddressIZK();
            activatedChannel = query.getChannels();
            settingRelay1 = query.getSettingRelay1();
            numberRelay1 = query.getNumberRelay1();
            modeRelay1 = query.getModeRelay1();
            settingRelay2 = query.getSettingRelay2();
            numberRelay2 = query.getNumberRelay2();
            modeRelay2 = query.getModeRelay2();
            settingRelay3 = query.getSettingRelay3();
            numberRelay3 = query.getNumberRelay3();
            modeRelay3 = query.getModeRelay3();
            settingRelay4 = query.getSettingRelay4();
            numberRelay4 = query.getNumberRelay4();
            modeRelay4 = query.getModeRelay4();
            settingRelay5 = query.getSettingRelay5();
            numberRelay5 = query.getNumberRelay5();
            modeRelay5 = query.getModeRelay5();
            settingRelay6 = query.getSettingRelay6();
            numberRelay6 = query.getNumberRelay6();
            modeRelay6 = query.getModeRelay6();
            settingRelay7 = query.getSettingRelay7();
            numberRelay7 = query.getNumberRelay7();
            modeRelay7 = query.getModeRelay7();
            settingRelay8 = query.getSettingRelay8();
            numberRelay8 = query.getNumberRelay8();
            modeRelay8 = query.getModeRelay8();
            settingRelay9 = query.getSettingRelay9();
            numberRelay9 = query.getNumberRelay9();
            modeRelay9 = query.getModeRelay9();
            settingRelay10 = query.getSettingRelay10();
            numberRelay10 = query.getNumberRelay10();
            modeRelay10 = query.getModeRelay10();

    }

    public void readRegulatorData() {


            query.queryPidSettings();
            kP = query.getkP();
            kI = query.getkI();
            kD = query.getkD();
            requiredHumidity = query.getHumidityRequired();
            step = query.getStep();
            fullStep = query.getFullStep();


    }

    public void readSensorOneData() throws ModbusNumberException, ModbusProtocolException, ModbusIOException {

            modbusReader.writeModeRegister(0, 5);
            query.querySensor();
            sensorAddressWrite1 = query.getSensorAddressWrite();
            timeoutWrite1 = query.getTimeoutWrite();
            periodWrite1 = query.getPeriodWrite();
            t01Write1 = query.getT01Write();
            ck1Write1 = query.getCk1Write();
            cd1Write1 = query.getCd1Write();
            checkPeriod1 = query.getCheckPeriod();
            errorWrite1 = query.getErrorWrite();
            cs1001 = query.getCs100();
            cm1 = query.getCm();
            k1 = query.getK();
            cs01 = query.getCs1();
            tc1 = query.getTc();
            csMin1 = query.getCsMin();
            hMin1 = query.gethMin();
            tsd11 = query.getTsd1();
            tsd21 = query.getTsd1();
            autoMin1 = query.getAutoMin();
            autoMax1 = query.getAutoMax();
            d201 = query.getD20();
            kd1 = query.getKd();
            min1 = query.getMin();
            max1 = query.getMax();
            emerMax1 = query.getEmerMax();
            noDensity1 = query.getNoDensity();

    }

    public void readSensorTwoData() throws ModbusNumberException, ModbusProtocolException, ModbusIOException {

            modbusReader.writeModeRegister(0, 6);
            query.querySensor();
            sensorAddressWrite2 = query.getSensorAddressWrite();
            timeoutWrite2 = query.getTimeoutWrite();
            periodWrite2 = query.getPeriodWrite();
            t01Write2 = query.getT01Write();
            ck1Write2 = query.getCk1Write();
            cd1Write2 = query.getCd1Write();
            checkPeriod2 = query.getCheckPeriod();
            errorWrite2 = query.getErrorWrite();
            cs1002 = query.getCs100();
            cm2 = query.getCm();
            k2 = query.getK();
            cs02 = query.getCs1();
            tc2 = query.getTc();
            csMin2 = query.getCsMin();
            hMin2 = query.gethMin();
            tsd12 = query.getTsd1();
            tsd22 = query.getTsd1();
            autoMin2 = query.getAutoMin();
            autoMax2 = query.getAutoMax();
            d202 = query.getD20();
            kd2 = query.getKd();
            min2 = query.getMin();
            max2 = query.getMax();
            emerMax2 = query.getEmerMax();
            noDensity2 = query.getNoDensity();


    }

    public void readSensorThreeData() throws ModbusNumberException, ModbusProtocolException, ModbusIOException {

            modbusReader.writeModeRegister(0, 7);
            query.querySensor();
            sensorAddressWrite3 = query.getSensorAddressWrite();
            timeoutWrite3 = query.getTimeoutWrite();
            periodWrite3 = query.getPeriodWrite();
            t01Write3 = query.getT01Write();
            ck1Write3 = query.getCk1Write();
            cd1Write3 = query.getCd1Write();
            checkPeriod3 = query.getCheckPeriod();
            errorWrite3 = query.getErrorWrite();
            cs1003 = query.getCs100();
            cm3 = query.getCm();
            k3 = query.getK();
            cs03 = query.getCs1();
            tc3 = query.getTc();
            csMin3 = query.getCsMin();
            hMin3 = query.gethMin();
            tsd13 = query.getTsd1();
            tsd23 = query.getTsd1();
            autoMin3 = query.getAutoMin();
            autoMax3 = query.getAutoMax();
            d203 = query.getD20();
            kd3 = query.getKd();
            min3 = query.getMin();
            max3 = query.getMax();
            emerMax3 = query.getEmerMax();
            noDensity3 = query.getNoDensity();

    }

    public void readSensorFourData() throws ModbusNumberException, ModbusProtocolException, ModbusIOException {

            modbusReader.writeModeRegister(0, 8);
            query.querySensor();
            sensorAddressWrite4 = query.getSensorAddressWrite();
            timeoutWrite4 = query.getTimeoutWrite();
            periodWrite4 = query.getPeriodWrite();
            t01Write4 = query.getT01Write();
            ck1Write4 = query.getCk1Write();
            cd1Write4 = query.getCd1Write();
            checkPeriod4 = query.getCheckPeriod();
            errorWrite4 = query.getErrorWrite();
            cs1004 = query.getCs100();
            cm4 = query.getCm();
            k4 = query.getK();
            cs04 = query.getCs1();
            tc4 = query.getTc();
            csMin4 = query.getCsMin();
            hMin4 = query.gethMin();
            tsd14 = query.getTsd1();
            tsd24 = query.getTsd1();
            autoMin4 = query.getAutoMin();
            autoMax4 = query.getAutoMax();
            d204 = query.getD20();
            kd4 = query.getKd();
            min4 = query.getMin();
            max4 = query.getMax();
            emerMax4 = query.getEmerMax();
            noDensity4 = query.getNoDensity();

    }


    public boolean isDataUpDate() {
        return dataUpDate;
    }

    public void setDataUpDate(boolean dataUpDate) {
        this.dataUpDate = dataUpDate;
    }
}
