
import com.intelligt.modbus.jlibmodbus.master.ModbusMaster;
import com.intelligt.modbus.jlibmodbus.master.ModbusMasterFactory;


public class MasterModbus {
   private Terminal terminal;
   private ModbusMaster modbusMaster;
   private int id;

   public MasterModbus(Terminal terminal, int id){
       this.terminal = terminal;
       this.id = id;
       try {
           modbusMaster = ModbusMasterFactory.createModbusMasterASCII(terminal.getSp());
           modbusMaster.connect();
       } catch (Exception e){
           e.printStackTrace();
       }
   }

    public ModbusMaster getModbusMaster() {
        return modbusMaster;
    }

    public int getId() {
        return id;
    }
}
