import jdk.nashorn.internal.ir.Terminal;

public class Query implements Runnable {

    private ModbusReader modbusReader;
    private int sensorAddress;
    private String time;

    public int getSensorAddress() {
        return sensorAddress;
    }


    public String getTime() {
        return time;
    }


    public Query(ModbusReader modbusReader){
        this.modbusReader = modbusReader;

        sensorAddress = 0;
        time = null;


    }
    @Override
    public void run() {
        System.out.println("Опрос запущен");
        while (true){
            try {
                int[] registerValues = modbusReader.readRegisters(0, 32, 1);
                sensorAddress = registerValues[0];
                time = String.format("%s:%s:%s", String.valueOf(registerValues[4]).length() < 2 ? "0" + registerValues[4] : String.valueOf(registerValues[4]),
                        String.valueOf(registerValues[5]).length() < 2 ? "0" + registerValues[5] : String.valueOf(registerValues[5]),
                        String.valueOf(registerValues[6]).length() < 2 ? "0" + registerValues[6] : String.valueOf(registerValues[6]));
                Thread.sleep(100);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
