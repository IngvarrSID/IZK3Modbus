import jssc.SerialPortList;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class IZKModbusGUI extends JFrame {

    private JPanel mainPanel;
    private JLabel comLabel;
    private JButton terminalButton;
    private JTextField sensorAddressField;
    private JTextField humidityField;
    private JTextField temperatureField;
    private JTextField timeField;
    private JCheckBox queryBox;
    private JTextField densityField;
    private JLabel nameStatLabel;
    private JLabel statLabel;
    private Terminal terminal;
    private MasterModbus masterModbus;
    private Timer timer1;

    private int sensorAddress;
    private String time;
    private float humidity;
    private float temperature;
    private float density;
    private String status;

    public IZKModbusGUI(Terminal terminal, MasterModbus masterModbus){
        this.terminal = terminal;
        this.masterModbus = masterModbus;
        setContentPane(mainPanel);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(1440,900);

        if (!terminal.isError()) comLabel.setText(String.format("Подключено к %s на скорости %s",terminal.getComName(),terminal.getBound()));
        else comLabel.setText(String.format("Ошибка подключения к %s", terminal.getComName()));

        terminalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                IZKTerminal izkTerminal = new IZKTerminal();
                timer1.stop();
                masterModbus.disconnect();
                dispose();
            }
        });
        ModbusReader modbusReader = new ModbusReader(masterModbus.getModbusMaster(), masterModbus.getId());
        Query query = new Query(modbusReader);

        queryBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (queryBox.isSelected()) {
                    timer1.start();

                }
                else {
                    timer1.stop();
                    System.out.println("Опрос завершен");
                }
            }
        });


        timer1 = new Timer(500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                query.queryStatus();
                status = query.getStatus();
                statLabel.setText(status);
               if (status.equals("Данные получены")){
                query.queryMain();
                sensorAddress = query.getSensorAddress();
                time = query.getTime();
                humidity = query.getHumidity();
                temperature = query.getTemperature();
                density = query.getDensity();
                //status = query.getStatus();
                sensorAddressField.setText(String.format("Адрес платы: %d", sensorAddress));
                timeField.setText(String.format("Текущее время: %s", time));
                humidityField.setText(String.format("Влажность: %.1f %%", humidity));
                temperatureField.setText(String.format("Температура: %.1f °C", temperature));
                densityField.setText(String.format("Плотность: %.1f кг/м²", density));
                nameStatLabel.setText("Состояние датчика:");
               // statLabel.setText(status);

                System.out.println("Таймер");
           } else System.out.println("ждем");
            }
        });








    }






    public static void main(String[] args) {
        IZKTerminal izkTerminal = new IZKTerminal();
    }

}
