package ru.sid.izk.modbus.archive;

import com.intelligt.modbus.jlibmodbus.exception.ModbusIOException;
import com.intelligt.modbus.jlibmodbus.exception.ModbusNumberException;
import com.intelligt.modbus.jlibmodbus.exception.ModbusProtocolException;
import ru.sid.izk.modbus.connection.ModbusReader;
import ru.sid.izk.modbus.entity.Query;

import static ru.sid.izk.modbus.utils.BitsReversUtils.bitsReader;
import static ru.sid.izk.modbus.utils.FromHexUtils.hexStringFromFloat;


public class ReadAllDataAdapter {
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
    private float d204;
    private float kd4;
    private int min4;
    private int max4;
    private int emerMax4;
    private int noDensity4;

    private final Query query;
    private final ModbusReader modbusReader;

    private boolean dataUpDate;

    private String dataCompiled;

    private int[] settings;
    private int[] sensor1;
    private int[] sensor2;
    private int[] sensor3;
    private int[] sensor4;


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

    public void readRegulatorData() throws ModbusProtocolException, ModbusNumberException, ModbusIOException {


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
        cs01 = query.getCs0();
        tc1 = query.getTc();
        csMin1 = query.getCsMin();
        hMin1 = query.gethMin();
        tsd11 = query.getTsd1();
        tsd21 = query.getTsd1();
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
        cs02 = query.getCs0();
        tc2 = query.getTc();
        csMin2 = query.getCsMin();
        hMin2 = query.gethMin();
        tsd12 = query.getTsd1();
        tsd22 = query.getTsd1();
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
        cs03 = query.getCs0();
        tc3 = query.getTc();
        csMin3 = query.getCsMin();
        hMin3 = query.gethMin();
        tsd13 = query.getTsd1();
        tsd23 = query.getTsd1();
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
        cs04 = query.getCs0();
        tc4 = query.getTc();
        csMin4 = query.getCsMin();
        hMin4 = query.gethMin();
        tsd14 = query.getTsd1();
        tsd24 = query.getTsd1();
        d204 = query.getD20();
        kd4 = query.getKd();
        min4 = query.getMin();
        max4 = query.getMax();
        emerMax4 = query.getEmerMax();
        noDensity4 = query.getNoDensity();

    }

    public void compilationData() {
        dataCompiled = ((String.format("Settings:%d;%b;%b;%b;%b;%d;%d;%d;%d;%d;%d;%d;%d;%d;%d;%d;%d;%d;%d;%d;%d;%d;%d;%d;%d;%d;%d;%d;%d;%d;%d;%d;%d;%d;%d;%.1f;%.1f;%.1f;%.1f;%d;%d\n",
                addressIZK, activatedChannel[0], activatedChannel[1], activatedChannel[2], activatedChannel[3], settingRelay1, numberRelay1, modeRelay1, settingRelay2, numberRelay2, modeRelay2, settingRelay3, numberRelay3, modeRelay3,
                settingRelay4, numberRelay4, modeRelay4, settingRelay5, numberRelay5, modeRelay5, settingRelay6, numberRelay6, modeRelay6, settingRelay7, numberRelay7, modeRelay7, settingRelay8, numberRelay8, modeRelay8,
                settingRelay9, numberRelay9, modeRelay9, settingRelay10, numberRelay10, modeRelay10, kP, kI, kD, requiredHumidity, step, fullStep) +
                String.format("Sensor1:%d;%d;%d;%d;%f;%f;%f;%f;%f;%f;%f;%f;%f;%f;%f;%f;%f;%d;%d;%d;%d;%f;%d\n",
                        sensorAddressWrite1, timeoutWrite1, periodWrite1, t01Write1, ck1Write1, cd1Write1, cs1001, cm1, k1, cs01, csMin1, hMin1, d201, kd1, tsd11, tsd21, tc1, min1, max1, emerMax1, noDensity1, errorWrite1, checkPeriod1) +
                String.format("Sensor2:%d;%d;%d;%d;%f;%f;%f;%f;%f;%f;%f;%f;%f;%f;%f;%f;%f;%d;%d;%d;%d;%f;%d\n",
                        sensorAddressWrite2, timeoutWrite2, periodWrite2, t01Write2, ck1Write2, cd1Write2, cs1002, cm2, k2, cs02, csMin2, hMin2, d202, kd2, tsd12, tsd22, tc2, min2, max2, emerMax2, noDensity2, errorWrite2, checkPeriod2) +
                String.format("Sensor3:%d;%d;%d;%d;%f;%f;%f;%f;%f;%f;%f;%f;%f;%f;%f;%f;%f;%d;%d;%d;%d;%f;%d\n",
                        sensorAddressWrite3, timeoutWrite3, periodWrite3, t01Write3, ck1Write3, cd1Write3, cs1003, cm3, k3, cs03, csMin3, hMin3, d203, kd3, tsd13, tsd23, tc3, min3, max3, emerMax3, noDensity3, errorWrite3, checkPeriod3) +
                String.format("Sensor4:%d;%d;%d;%d;%f;%f;%f;%f;%f;%f;%f;%f;%f;%f;%f;%f;%f;%d;%d;%d;%d;%f;%d\n",
                        sensorAddressWrite4, timeoutWrite4, periodWrite4, t01Write4, ck1Write4, cd1Write4, cs1004, cm4, k4, cs04, csMin4, hMin4, d204, kd4, tsd14, tsd24, tc4, min4, max4, emerMax4, noDensity4, errorWrite4, checkPeriod4)));

        String dataFull = dataCompiled.replace(",",".");
        String[] strings = dataFull.split("\n");
        String data;
        data = strings[0].split(":")[1];
        settings = settingsBuild(data.split(";"));
        data = strings[1].split(":")[1];
        sensor1 = sensorBuild(data.split(";"));
        data = strings[2].split(":")[1];
        sensor2 = sensorBuild(data.split(";"));
        data = strings[3].split(":")[1];
        sensor3 = sensorBuild(data.split(";"));
        data = strings[4].split(":")[1];
        sensor4 = sensorBuild(data.split(";"));

    }


    public void readFileSettings(String[] readDataStrings) {
        addressIZK = Integer.parseInt(readDataStrings[0]);
        activatedChannel = new boolean[4];
        for (int i = 0; i < 4; i++) {
            activatedChannel[i] = readDataStrings[1 + i].equals("true");
        }
        settingRelay1 = Integer.parseInt(readDataStrings[5]);
        numberRelay1 = Integer.parseInt(readDataStrings[6]);
        modeRelay1 = Integer.parseInt(readDataStrings[7]);
        settingRelay2 = Integer.parseInt(readDataStrings[8]);
        numberRelay2 = Integer.parseInt(readDataStrings[9]);
        modeRelay2 = Integer.parseInt(readDataStrings[10]);
        settingRelay3 = Integer.parseInt(readDataStrings[11]);
        numberRelay3 = Integer.parseInt(readDataStrings[12]);
        modeRelay3 = Integer.parseInt(readDataStrings[13]);
        settingRelay4 = Integer.parseInt(readDataStrings[14]);
        numberRelay4 = Integer.parseInt(readDataStrings[15]);
        modeRelay4 = Integer.parseInt(readDataStrings[16]);
        settingRelay5 = Integer.parseInt(readDataStrings[17]);
        numberRelay5 = Integer.parseInt(readDataStrings[18]);
        modeRelay5 = Integer.parseInt(readDataStrings[19]);
        settingRelay6 = Integer.parseInt(readDataStrings[20]);
        numberRelay6 = Integer.parseInt(readDataStrings[21]);
        modeRelay6 = Integer.parseInt(readDataStrings[22]);
        settingRelay7 = Integer.parseInt(readDataStrings[23]);
        numberRelay7 = Integer.parseInt(readDataStrings[24]);
        modeRelay7 = Integer.parseInt(readDataStrings[25]);
        settingRelay8 = Integer.parseInt(readDataStrings[26]);
        numberRelay8 = Integer.parseInt(readDataStrings[27]);
        modeRelay8 = Integer.parseInt(readDataStrings[28]);
        settingRelay9 = Integer.parseInt(readDataStrings[29]);
        numberRelay9 = Integer.parseInt(readDataStrings[30]);
        modeRelay9 = Integer.parseInt(readDataStrings[31]);
        settingRelay10 = Integer.parseInt(readDataStrings[32]);
        numberRelay10 = Integer.parseInt(readDataStrings[33]);
        modeRelay10 = Integer.parseInt(readDataStrings[34]);
        kP = Float.parseFloat(readDataStrings[35]);
        kI = Float.parseFloat(readDataStrings[36]);
        kD = Float.parseFloat(readDataStrings[37]);
        requiredHumidity = Float.parseFloat(readDataStrings[38]);
        step = Integer.parseInt(readDataStrings[39]);
        fullStep = Integer.parseInt(readDataStrings[40]);

        settings = settingsBuild(readDataStrings);
    }

    public void readFileSensorOne(String[] readDataStrings) {
        sensorAddressWrite1 = Integer.parseInt(readDataStrings[0]);
        timeoutWrite1 = Integer.parseInt(readDataStrings[1]);
        periodWrite1 = Integer.parseInt(readDataStrings[2]);
        t01Write1 = Integer.parseInt(readDataStrings[3]);
        ck1Write1 = Float.parseFloat(readDataStrings[4]);
        cd1Write1 = Float.parseFloat(readDataStrings[5]);
        cs1001 = Float.parseFloat(readDataStrings[6]);
        cm1 = Float.parseFloat(readDataStrings[7]);
        k1 = Float.parseFloat(readDataStrings[8]);
        cs01 = Float.parseFloat(readDataStrings[9]);
        tc1 = Float.parseFloat(readDataStrings[10]);
        csMin1 = Float.parseFloat(readDataStrings[11]);
        hMin1 = Float.parseFloat(readDataStrings[12]);
        tsd11 = Float.parseFloat(readDataStrings[13]);
        tsd21 = Float.parseFloat(readDataStrings[14]);
        d201 = Float.parseFloat(readDataStrings[15]);
        kd1 = Float.parseFloat(readDataStrings[16]);
        min1 = Integer.parseInt(readDataStrings[17]);
        max1 = Integer.parseInt(readDataStrings[18]);
        emerMax1 = Integer.parseInt(readDataStrings[19]);
        noDensity1 = Integer.parseInt(readDataStrings[20]);
        errorWrite1 = Float.parseFloat(readDataStrings[21]);
        checkPeriod1 = Integer.parseInt(readDataStrings[22]);


        sensor1 = sensorBuild(readDataStrings);
    }

    public void readFileSensorTwo(String[] readDataStrings) {
        sensorAddressWrite2 = Integer.parseInt(readDataStrings[0]);
        timeoutWrite2 = Integer.parseInt(readDataStrings[1]);
        periodWrite2 = Integer.parseInt(readDataStrings[2]);
        t01Write2 = Integer.parseInt(readDataStrings[3]);
        ck1Write2 = Float.parseFloat(readDataStrings[4]);
        cd1Write2 = Float.parseFloat(readDataStrings[5]);
        cs1002 = Float.parseFloat(readDataStrings[6]);
        cm2 = Float.parseFloat(readDataStrings[7]);
        k2 = Float.parseFloat(readDataStrings[8]);
        cs02 = Float.parseFloat(readDataStrings[9]);
        tc2 = Float.parseFloat(readDataStrings[10]);
        csMin2 = Float.parseFloat(readDataStrings[11]);
        hMin2 = Float.parseFloat(readDataStrings[12]);
        tsd12 = Float.parseFloat(readDataStrings[13]);
        tsd22 = Float.parseFloat(readDataStrings[14]);
        d202 = Float.parseFloat(readDataStrings[15]);
        kd2 = Float.parseFloat(readDataStrings[16]);
        min2 = Integer.parseInt(readDataStrings[17]);
        max2 = Integer.parseInt(readDataStrings[18]);
        emerMax2 = Integer.parseInt(readDataStrings[19]);
        noDensity2 = Integer.parseInt(readDataStrings[20]);
        errorWrite2 = Float.parseFloat(readDataStrings[21]);
        checkPeriod2 = Integer.parseInt(readDataStrings[22]);


        sensor2 = sensorBuild(readDataStrings);
    }

    public void readFileSensorThree(String[] readDataStrings) {
        sensorAddressWrite3 = Integer.parseInt(readDataStrings[0]);
        timeoutWrite3 = Integer.parseInt(readDataStrings[1]);
        periodWrite3 = Integer.parseInt(readDataStrings[2]);
        t01Write3 = Integer.parseInt(readDataStrings[3]);
        ck1Write3 = Float.parseFloat(readDataStrings[4]);
        cd1Write3 = Float.parseFloat(readDataStrings[5]);
        cs1003 = Float.parseFloat(readDataStrings[6]);
        cm3 = Float.parseFloat(readDataStrings[7]);
        k3 = Float.parseFloat(readDataStrings[8]);
        cs03 = Float.parseFloat(readDataStrings[9]);
        tc3 = Float.parseFloat(readDataStrings[10]);
        csMin3 = Float.parseFloat(readDataStrings[11]);
        hMin3 = Float.parseFloat(readDataStrings[12]);
        tsd13 = Float.parseFloat(readDataStrings[13]);
        tsd23 = Float.parseFloat(readDataStrings[14]);
        d203 = Float.parseFloat(readDataStrings[15]);
        kd3 = Float.parseFloat(readDataStrings[16]);
        min3 = Integer.parseInt(readDataStrings[17]);
        max3 = Integer.parseInt(readDataStrings[18]);
        emerMax3 = Integer.parseInt(readDataStrings[19]);
        noDensity3 = Integer.parseInt(readDataStrings[20]);
        errorWrite3 = Float.parseFloat(readDataStrings[21]);
        checkPeriod3 = Integer.parseInt(readDataStrings[22]);


        sensor3 = sensorBuild(readDataStrings);
    }

    public void readFileSensorFour(String[] readDataStrings) {
        sensorAddressWrite4 = Integer.parseInt(readDataStrings[0]);
        timeoutWrite4 = Integer.parseInt(readDataStrings[1]);
        periodWrite4 = Integer.parseInt(readDataStrings[2]);
        t01Write4 = Integer.parseInt(readDataStrings[3]);
        ck1Write4 = Float.parseFloat(readDataStrings[4]);
        cd1Write4 = Float.parseFloat(readDataStrings[5]);
        cs1004 = Float.parseFloat(readDataStrings[6]);
        cm4 = Float.parseFloat(readDataStrings[7]);
        k4 = Float.parseFloat(readDataStrings[8]);
        cs04 = Float.parseFloat(readDataStrings[9]);
        tc4 = Float.parseFloat(readDataStrings[10]);
        csMin4 = Float.parseFloat(readDataStrings[11]);
        hMin4 = Float.parseFloat(readDataStrings[12]);
        tsd14 = Float.parseFloat(readDataStrings[13]);
        tsd24 = Float.parseFloat(readDataStrings[14]);
        d204 = Float.parseFloat(readDataStrings[15]);
        kd4 = Float.parseFloat(readDataStrings[16]);
        min4 = Integer.parseInt(readDataStrings[17]);
        max4 = Integer.parseInt(readDataStrings[18]);
        emerMax4 = Integer.parseInt(readDataStrings[19]);
        noDensity4 = Integer.parseInt(readDataStrings[20]);
        errorWrite4 = Float.parseFloat(readDataStrings[21]);
        checkPeriod4 = Integer.parseInt(readDataStrings[22]);


        sensor4 = sensorBuild(readDataStrings);
    }

    public int[] settingsBuild(String[] readDataStrings) {
        int[] hex = new int[43];
        int in = 0;
        for (int i = 0; i < readDataStrings.length; i++) {
            if (i == 1) {
                StringBuilder bits = new StringBuilder();
                for (int j = 0; j < 16; j++) {
                    if (activatedChannel.length > j) bits.append(activatedChannel[j] ? "1" : "0");
                    else bits.append("0");
                }
                hex[in] = Integer.parseInt(bitsReader(bits.toString()), 2);
                in++;
                i = i + 3;
            } else if (in == 2) {
                hex[in] = 0;
                in++;
                i--;
            } else if (readDataStrings[i].contains(".")) {
                float f = Float.parseFloat(readDataStrings[i]);
                String sF = hexStringFromFloat(f);
                hex[in] = Integer.valueOf(sF.substring(4, 8), 16);
                in++;
                hex[in] = Integer.valueOf(sF.substring(0, 4), 16);
                in++;
            } else {
                hex[in] = Integer.parseInt(readDataStrings[i]);
                in++;
            }
        }
        return hex;
    }

    public int[] sensorBuild(String[] readDataStrings) {
        int[] hex = new int[43];
        int in = 0;
        for (int i = 0; i < readDataStrings.length; i++) {
            if (in == 2 || in == 39 || in == 34 || in == 36 || in == 37 || in == 38) {
                hex[in] = 0;
                i--;
                in++;
            } else if (readDataStrings[i].contains(".")) {
                float f = Float.parseFloat(readDataStrings[i]);
                String sF = hexStringFromFloat(f);
                hex[in] = Integer.valueOf(sF.substring(4, 8), 16);
                in++;
                hex[in] = Integer.valueOf(sF.substring(0, 4), 16);
                in++;
            } else {
                hex[in] = Integer.parseInt(readDataStrings[i]);
                in++;
            }
        }
        return hex;
    }


    public boolean isDataUpDate() {
        return dataUpDate;
    }

    public void setDataUpDate(boolean dataUpDate) {
        this.dataUpDate = dataUpDate;
    }

    public int getAddressIZK() {
        return addressIZK;
    }

    public boolean[] getActivatedChannel() {
        return activatedChannel;
    }

    public int[] getSettings() {
        return settings;
    }

    public int[] getSensor1() {
        return sensor1;
    }

    public int[] getSensor2() {
        return sensor2;
    }

    public int[] getSensor3() {
        return sensor3;
    }

    public int[] getSensor4() {
        return sensor4;
    }

    public String getDataCompiled() {
        return dataCompiled;
    }
}
