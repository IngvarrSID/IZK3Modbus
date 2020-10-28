import com.intelligt.modbus.jlibmodbus.serial.SerialParameters;
import com.intelligt.modbus.jlibmodbus.serial.SerialPort;
import com.intelligt.modbus.jlibmodbus.serial.SerialPortFactoryJSSC;
import com.intelligt.modbus.jlibmodbus.serial.SerialUtils;
import jssc.SerialPortList;

public class Terminal {

    private SerialParameters sp;
    private String comName;
    private String bound;


    public Terminal(String comName, String bound) {
        this.comName = comName;
        this.bound = bound;
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
}
