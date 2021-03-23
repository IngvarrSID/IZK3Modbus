package ru.sid.izk.modbus.entity;

import com.intelligt.modbus.jlibmodbus.exception.ModbusIOException;
import com.intelligt.modbus.jlibmodbus.exception.ModbusNumberException;
import com.intelligt.modbus.jlibmodbus.exception.ModbusProtocolException;
import ru.sid.izk.modbus.connection.ModbusReader;

import static ru.sid.izk.modbus.utils.BitsReversUtils.bitsReader;

public class Query{

    private final ModbusReader modbusReader;
    //main
    private int sensorAddress;
    private String time;
    private float humidity;
    private float temperature;
    private float density;
    private String status;
    private float period;
    private float cs1;
    private float cs2;
    private float error;
    private String data;
    //info
    private String versionFirm;
    private String dataFirm;
    private String statusActiv;
    private String identificator;
    private String dataActiv;


    //sensor
    private int sensorAddressWrite;
    private int timeoutWrite;
    private int periodWrite;
    private int t01Write;
    private float ck1Write;
    private float cd1Write;
    private int checkPeriod;
    private float errorWrite;
    private float cs100;
    private float cm;
    private float k;
    private float cs0;
    private float tc;
    private float csMin;
    private float hMin;
    private float tsd1;
    private float tsd2;
    private float autoMin;
    private float autoMax;
    private float d20;
    private float kd;
    private int min;
    private int max;
    private int emerMax;
    private int noDensity;

    //IZKSettings
    private int addressIZK;
    private boolean[] channels;
    private int settingRelay1;
    private int settingRelay2;
    private int settingRelay3;
    private int settingRelay4;
    private int settingRelay5;
    private int settingRelay6;
    private int settingRelay7;
    private int settingRelay8;
    private int settingRelay9;
    private int settingRelay10;
    private int numberRelay1;
    private int numberRelay2;
    private int numberRelay3;
    private int numberRelay4;
    private int numberRelay5;
    private int numberRelay6;
    private int numberRelay7;
    private int numberRelay8;
    private int numberRelay9;
    private int numberRelay10;
    private int modeRelay1;
    private int modeRelay2;
    private int modeRelay3;
    private int modeRelay4;
    private int modeRelay5;
    private int modeRelay6;
    private int modeRelay7;
    private int modeRelay8;
    private int modeRelay9;
    private int modeRelay10;

    public int getAddressIZK() {
        return addressIZK;
    }

    public boolean[] getChannels() {
        return channels;
    }

    public int getSettingRelay1() {
        return settingRelay1;
    }

    public int getSettingRelay2() {
        return settingRelay2;
    }

    public int getSettingRelay3() {
        return settingRelay3;
    }

    public int getSettingRelay4() {
        return settingRelay4;
    }

    public int getSettingRelay5() {
        return settingRelay5;
    }

    public int getSettingRelay6() {
        return settingRelay6;
    }

    public int getSettingRelay7() {
        return settingRelay7;
    }

    public int getSettingRelay8() {
        return settingRelay8;
    }

    public int getSettingRelay9() {
        return settingRelay9;
    }

    public int getSettingRelay10() {
        return settingRelay10;
    }

    public int getNumberRelay1() {
        return numberRelay1;
    }

    public int getNumberRelay2() {
        return numberRelay2;
    }

    public int getNumberRelay3() {
        return numberRelay3;
    }

    public int getNumberRelay4() {
        return numberRelay4;
    }

    public int getNumberRelay5() {
        return numberRelay5;
    }

    public int getNumberRelay6() {
        return numberRelay6;
    }

    public int getNumberRelay7() {
        return numberRelay7;
    }

    public int getNumberRelay8() {
        return numberRelay8;
    }

    public int getNumberRelay9() {
        return numberRelay9;
    }

    public int getNumberRelay10() {
        return numberRelay10;
    }

    public int getModeRelay1() {
        return modeRelay1;
    }

    public int getModeRelay2() {
        return modeRelay2;
    }

    public int getModeRelay3() {
        return modeRelay3;
    }

    public int getModeRelay4() {
        return modeRelay4;
    }

    public int getModeRelay5() {
        return modeRelay5;
    }

    public int getModeRelay6() {
        return modeRelay6;
    }

    public int getModeRelay7() {
        return modeRelay7;
    }

    public int getModeRelay8() {
        return modeRelay8;
    }

    public int getModeRelay9() {
        return modeRelay9;
    }

    public int getModeRelay10() {
        return modeRelay10;
    }

    public int getCheckPeriod() {
        return checkPeriod;
    }

    public float getErrorWrite() {
        return errorWrite;
    }

    public float getCs100() {
        return cs100;
    }

    public float getCm() {
        return cm;
    }

    public float getK() {
        return k;
    }

    public float getCs0() {
        return cs0;
    }

    public float getTc() {
        return tc;
    }

    public float getCsMin() {
        return csMin;
    }

    public float gethMin() {
        return hMin;
    }

    public float getTsd1() {
        return tsd1;
    }

    public float getTsd2() {
        return tsd2;
    }

    public float getAutoMin() {
        return autoMin;
    }

    public float getAutoMax() {
        return autoMax;
    }

    public float getD20() {
        return d20;
    }

    public float getKd() {
        return kd;
    }

    public int getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }

    public int getEmerMax() {
        return emerMax;
    }

    public int getNoDensity() {
        return noDensity;
    }

    public int getSensorAddressWrite() {
        return sensorAddressWrite;
    }

    public int getTimeoutWrite() {
        return timeoutWrite;
    }

    public int getPeriodWrite() {
        return periodWrite;
    }

    public float getCd1Write() {
        return cd1Write;
    }

    public String getVersionFirm() {
        return versionFirm;
    }

    public String getDataFirm() {
        return dataFirm;
    }

    public String getStatusActive() {
        return statusActiv;
    }

    public String getIdentificator() {
        return identificator;
    }

    public String getDataActiv() {
        return dataActiv;
    }

    public float getPeriod() {
        return period;
    }

    public float getCs1() {
        return cs1;
    }

    public float getCs2() {
        return cs2;
    }

    public float getError() {
        return error;
    }

    public String getData() {
        return data;
    }

    public int getSensorAddress() {
        return sensorAddress;
    }

    public float getHumidity() {
        return humidity;
    }

    public String getTime() {
        return time;
    }

    public float getTemperature() {
        return temperature;
    }

    public float getDensity() {
        return density;
    }

    public String getStatus() {
        return status;
    }

    public int getT01Write() {
        return t01Write;
    }

    public float getCk1Write() {
        return ck1Write;
    }

    public Query(ModbusReader modbusReader){
        this.modbusReader = modbusReader;

        sensorAddress = 0;
        time = null;
        humidity = 0;


    }


     public void queryStatus (){
        try {
            int[] registerValues = modbusReader.readRegisters(22, 1, 1);
            String reversStatus = Integer.toBinaryString(registerValues[0]);
            status = statusReader(reversStatus);

        } catch (Exception e){
            e.printStackTrace();
        }
    }
     public void queryMain(){
        System.out.println("Опрос запущен");

            try {
                int[] registerValues = modbusReader.readRegisters(0, 32, 1);
                sensorAddress = registerValues[0];
                time = timeReader(registerValues[4], registerValues[5], registerValues[6]);
                humidity = hexToFloat(registerValues[7], registerValues[8]);
                temperature = hexToFloat(registerValues[19],registerValues[20]);
                density = hexToFloat(registerValues[9],registerValues[10]);
                String reversStatus = Integer.toBinaryString(registerValues[22]);
                status = statusReader(reversStatus);
                period = hexToFloat(registerValues[24],registerValues[25]);
                cs1 = hexToFloat(registerValues[26],registerValues[27]);
                cs2 = hexToFloat(registerValues[28],registerValues[29]);
                error = hexToFloat(registerValues[30],registerValues[31]);
                data = dateReader(registerValues[1],registerValues[2],registerValues[3]);



                System.out.println(registerValues[19]);
                System.out.println(registerValues[20]);
                System.out.println(temperature);



            } catch (Exception e) {
                e.printStackTrace();
            }

    }

    public void querySettings() throws ModbusNumberException, ModbusProtocolException, ModbusIOException {
        channels = new boolean[4];
        int[] registerValues = modbusReader.readHoldingsRegisters(2,32,2);
        addressIZK = registerValues[0];
        String reversBits = Integer.toBinaryString(registerValues[1]);
        char[] chars = bitsReader(reversBits).toCharArray();
        for (int i = 0; i < channels.length; i++) {
            if(chars[i] == '1') channels[i] = true;
        }
        settingRelay1 = registerValues[3];
        numberRelay1 = registerValues[4];
        modeRelay1 = registerValues[5];
        settingRelay2 = registerValues[6];
        numberRelay2 = registerValues[7];
        modeRelay2 = registerValues[8];
        settingRelay3 = registerValues[9];
        numberRelay3 = registerValues[10];
        modeRelay3 = registerValues[11];
        settingRelay4 = registerValues[12];
        numberRelay4 = registerValues[13];
        modeRelay4 = registerValues[14];
        settingRelay5 = registerValues[15];
        numberRelay5 = registerValues[16];
        modeRelay5 = registerValues[17];
        settingRelay6 = registerValues[18];
        numberRelay6 = registerValues[19];
        modeRelay6 = registerValues[20];
        settingRelay7 = registerValues[21];
        numberRelay7 = registerValues[22];
        modeRelay7 = registerValues[23];
        settingRelay8 = registerValues[24];
        numberRelay8 = registerValues[25];
        modeRelay8 = registerValues[26];
        settingRelay9 = registerValues[27];
        numberRelay9 = registerValues[28];
        modeRelay9 = registerValues[29];
        settingRelay10 = registerValues[30];
        numberRelay10 = registerValues[31];
        modeRelay10 = registerValues[32];

    }

    public void querySensor(){
        try {
            int[] registerValues = modbusReader.readHoldingsRegisters(2,32,2);
            sensorAddressWrite = registerValues[0];
            timeoutWrite = registerValues[1];
            periodWrite = registerValues[3];
            t01Write = registerValues[4];
            ck1Write = hexToFloat(registerValues[5],registerValues[6]);
            cd1Write = hexToFloat(registerValues[7],registerValues[8]);
            checkPeriod = registerValues[42];
            errorWrite = hexToFloat(registerValues[40],registerValues[41]);
            cs100 = hexToFloat(registerValues[9],registerValues[10]);
            cm = hexToFloat(registerValues[11],registerValues[12]);
            k = hexToFloat(registerValues[13],registerValues[14]);
            cs0 = hexToFloat(registerValues[15],registerValues[16]);
            tc = hexToFloat(registerValues[29],registerValues[30]);
            csMin = hexToFloat(registerValues[17],registerValues[18]);
            hMin = hexToFloat(registerValues[19],registerValues[20]);
            tsd1 = hexToFloat(registerValues[25], registerValues[26]);
            tsd2 = hexToFloat(registerValues[27],registerValues[28]);
            autoMin = hexToFloat(registerValues[36],registerValues[37]);
            autoMax = hexToFloat(registerValues[38],registerValues[39]);
            d20 = hexToFloat(registerValues[21],registerValues[22]);
            kd = hexToFloat(registerValues[23],registerValues[24]);
            min = registerValues[31];
            max = registerValues[32];
            emerMax = registerValues[33];
            noDensity = registerValues[35];

        } catch (Exception e){
            e.printStackTrace();
        }
    }
    public void queryInfo (){
        try {
            int[] registerValues = modbusReader.readRegisters(0, 32, 1);
            StringBuilder builderVersion = new StringBuilder();
            for (int i = 0; i < 10; i++) {
                String s = Integer.toHexString(registerValues[i]);

                String a = s.substring(2,4);
                String b = s.substring(0,2);
                int c = Integer.valueOf(a, 16);
                int d = Integer.valueOf(b, 16);
                builderVersion.append((char)c);
                builderVersion.append((char)d);

            }
            versionFirm = builderVersion.toString();

            StringBuilder builderData = new StringBuilder();
            for (int i = 10; i < 13; i++) {
                String s = Integer.toHexString(registerValues[i]);

                String a = s.substring(2,4);
                String b = s.substring(0,2);
                int c = Integer.valueOf(a, 16);
                int d = Integer.valueOf(b, 16);
                builderData.append((char)c);
                builderData.append((char)d);
                if (builderData.length()<6) builderData.append(".");

            }
            dataFirm = builderData.toString();

            StringBuilder builderIdentif = new StringBuilder();
            for (int i = 18; i < 21; i++) {
                String s = Integer.toHexString(registerValues[i]);

                String a = s.substring(2,4);
                String b = s.substring(0,2);
                int c = Integer.valueOf(a, 16);
                int d = Integer.valueOf(b, 16);
                builderIdentif.append((char)c);
                builderIdentif.append((char)d);

            }
            identificator = builderIdentif.toString();

            dataActiv = dateReader(registerValues[14],registerValues[15],registerValues[16]);

            switch (registerValues[30]){
                case 0:
                    statusActiv = "Активация не требуется";
                    break;
                case 1:
                    statusActiv = "Блок активирован";
                    break;
                case 2:
                    statusActiv = "Идет пробный период. Требуется активация";
                    break;
                case 3:
                    statusActiv = "Пробный период закончился. Требуется активация";
                    break;
                case 4:
                    statusActiv = "Ошибка календаря";
                    break;

            }



        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public static Float hexToFloat(int value1, int value2){
        String s;
        if (value1 !=0 && value2 !=0) {
            if (value1 <4096) s = Integer.toHexString(value2) + "0" + Integer.toHexString(value1);
            else s = Integer.toHexString(value2) + Integer.toHexString(value1);
        }
        else if (value1 == 0) s = Integer.toHexString(value2) + "0000";
        else s = Integer.toHexString(value1);
        Long value1AsLong = Long.parseLong(s, 16);
        return Float.intBitsToFloat(value1AsLong.intValue());
    }

    private static String statusReader (String reversStatus){
        StringBuilder status = new StringBuilder();
        for (int j = 0; j < 16; j++) {
            if (reversStatus.length() > j) status.insert(0, reversStatus.charAt(j));
            else status.append("0");
        }
        boolean dataExist = status.charAt(0) == '1';
        boolean measuring = status.charAt(1) == '1';
        boolean noData = status.charAt(2) == '1';
        boolean nullPeriod = status.charAt(3) == '1';
        boolean nullAddress = status.charAt(5) == '1';
        boolean disChannel = status.charAt(6) == '1';
        if (dataExist && !nullPeriod)  return  "Данные получены";
        else if (measuring) return  "Идут измерения";
        else if (noData) return "Нет связи с датчиком";
        else if (nullPeriod) return "Нулевой период";
        else if (nullAddress) return "Адрес датчика 0";
        else if (disChannel) return "Канал отключен";
        else return "Не определен";
    }


    private static String timeReader (int value1,int value2,int value3){
        return String.format("%s:%s:%s", String.valueOf(value1).length() < 2 ? "0" + value1 : String.valueOf(value1),
                String.valueOf(value2).length() < 2 ? "0" + value2 : String.valueOf(value2),
                String.valueOf(value3).length() < 2 ? "0" + value3 : String.valueOf(value3));
    }

    private static String dateReader (int value1,int value2,int value3){
        return String.format("%s.%s.%s", String.valueOf(value1).length() < 2 ? "0" + value1 : String.valueOf(value1),
                String.valueOf(value2).length() < 2 ? "0" + value2 : String.valueOf(value2), String.valueOf(value3).length()<3 ? "20" +value3 : value3);
    }

}
