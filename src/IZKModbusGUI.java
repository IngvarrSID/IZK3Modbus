import jssc.SerialPortList;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class IZKModbusGUI extends JFrame {

    private JPanel mainPanel;
    private JLabel comLabel;
    private JButton terminalButton;
    private JTextField sensorAddressField;
    private JTextField humidityField;
    private JTextField temperatureField;
    private JTextField timeField;
    private Terminal terminal;
    private MasterModbus masterModbus;

    private int sensorAddress;
    private String time;

    public IZKModbusGUI(Terminal terminal, MasterModbus masterModbus){
        this.terminal = terminal;
        this.masterModbus = masterModbus;
        setContentPane(mainPanel);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(500,300);

        if (!terminal.isError()) comLabel.setText(String.format("Подключено к %s на скорости %s",terminal.getComName(),terminal.getBound()));
        else comLabel.setText(String.format("Ошибка подключения к %s", terminal.getComName()));

        terminalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                IZKTerminal izkTerminal = new IZKTerminal();
                masterModbus.disconnect();
                dispose();
            }
        });

            ModbusReader modbusReader = new ModbusReader(masterModbus.getModbusMaster(), masterModbus.getId());
            Query query = new Query(modbusReader);
            query.run();

            sensorAddress = query.getSensorAddress();
            time = query.getTime();

            sensorAddressField.setText(Integer.toString(sensorAddress));
            timeField.setText(time);


    }






    public static void main(String[] args) {
        IZKTerminal izkTerminal = new IZKTerminal();
    }

}
