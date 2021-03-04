import jdk.nashorn.internal.ir.Terminal;

public class Query{

    private ModbusReader modbusReader;
    private int sensorAddress;
    private String time;
    private float humidity;
    private float temperature;
    private float density;
    private String status;

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


                System.out.println(registerValues[19]);
                System.out.println(registerValues[20]);
                System.out.println(temperature);



            } catch (Exception e) {
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
        //else if () s = Integer.toHexString(value2) + "0" + Integer.toHexString(value1);
        else s = Integer.toHexString(value1);
        Long l = Long.parseLong(s, 16);
        return Float.intBitsToFloat(l.intValue());
    }

    private static String statusReader (String reversStatus){
        String status = "";
        for (int j = 0; j < 16; j++) {
            if (reversStatus.length() > j) status = reversStatus.charAt(j) + status;
            else status = status + "0";
        }
        boolean dataExist = status.charAt(0) == '1';
        boolean measuring = status.charAt(1) == '1';
        boolean noData = status.charAt(2) == '1';
        boolean nullPeriod = status.charAt(3) == '1';
        boolean gradError = status.charAt(4) == '1';
        boolean nullAddress = status.charAt(5) == '1';
        boolean disChannel = status.charAt(6) == '1';
        int sensType = Integer.parseInt(status.substring(7, 9), 2);
        int sensWare = Integer.parseInt(status.substring(9, 13), 2);
        int activStatus = Integer.parseInt(status.substring(13), 2);
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




}
