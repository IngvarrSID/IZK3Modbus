import com.intelligt.modbus.jlibmodbus.exception.ModbusIOException;
import com.intelligt.modbus.jlibmodbus.exception.ModbusNumberException;
import com.intelligt.modbus.jlibmodbus.exception.ModbusProtocolException;
import com.intelligt.modbus.jlibmodbus.master.ModbusMaster;

public class ModbusReader {

    private ModbusMaster modbusMaster;
    private int slaveID;


    public ModbusReader(ModbusMaster m, int slaveID){
        this.modbusMaster = m;
        this.slaveID = slaveID;
    }

    public int[] readRegisters (int offset, int quantity, int count) throws ModbusNumberException, ModbusProtocolException, ModbusIOException {
        int[] allRegisters = new int[quantity*count];
        for (int i = 0; i < count; i++) {
            int[] registers = modbusMaster.readInputRegisters(slaveID,offset,quantity);
            for (int j = offset; j < offset+registers.length; j++) {
                allRegisters[j] = registers[j-offset];
            }
            offset = offset+quantity;
        }
        return allRegisters;
    }

    public int[] readModeRegister (int offset) throws ModbusNumberException, ModbusProtocolException, ModbusIOException {
        return modbusMaster.readHoldingRegisters(slaveID,offset,1);

    }

    public boolean readStatusBit() throws ModbusNumberException, ModbusProtocolException, ModbusIOException{
        String status = Integer.toBinaryString(modbusMaster.readInputRegisters(slaveID,52,1)[0]);
        String statusRevers = "";
        for (int j = 0; j < 16; j++) {
            if (status.length() > j) statusRevers = status.charAt(j) + statusRevers;
            else statusRevers = statusRevers + "0";
        }
        return statusRevers.charAt(0) == '1';
    }
}
