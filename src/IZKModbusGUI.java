import com.sun.corba.se.spi.orbutil.fsm.Action;

import javax.swing.*;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.NumberFormatter;
import java.awt.event.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Properties;

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
    private JTextField dataField;
    private JTextField periodField;
    private JTextField cs1Field;
    private JTextField cs2Field;
    private JTabbedPane tabbedPane1;
    private JTextField errorField;
    private JButton refButton;
    private JTextField versionFirmField;
    private JTextField dataFirmField;
    private JTextField statusActivField;
    private JTextField identificatorField;
    private JTextField dataActivField;
    private JButton reButton2;
    private JFormattedTextField addressSensorFieldWrite;
    private JTextField timeoutFieldWrite;
    private JTextField periodFieldWrite;
    private JTextField t01FieldWrite;
    private JTextField ck1FieldWrite;
    private JTextField cd1FieldWrite;
    private JButton activButton;
    private JTextField passwordField;
    private Terminal terminal;
    private MasterModbus masterModbus;
    private Timer timer1;

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

    public IZKModbusGUI(Terminal terminal, MasterModbus masterModbus){
        this.terminal = terminal;
        this.masterModbus = masterModbus;
        setContentPane(mainPanel);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(1440,900);

        nameStatLabel.setText("Состояние датчика:");
        statLabel.setText("Нет информации");


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

        tabbedPane1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int idx = ((JTabbedPane)e.getSource()).indexAtLocation(e.getX(), e.getY());
                System.out.println("Выбрана вкладка " + idx);
                if (queryBox.isSelected()) queryBox.setSelected(false);
                switch (idx){
                    case 0:

                        break;
                    case 2:
                        modbusReader.writeModeRegister(0,5);
                        modbusReader.writeModeRegister(1,0);
                        query.querySensor();
                        sensorAddressWrite = query.getSensorAddressWrite();
                        addressSensorFieldWrite.setText(String.valueOf(sensorAddressWrite));
                        timeoutWrite = query.getTimeoutWrite();
                        timeoutFieldWrite.setText(String.valueOf(timeoutWrite));
                        periodWrite = query.getPeriodWrite();
                        periodFieldWrite.setText(String.valueOf(periodWrite));
                        t01Write = query.getT01Write();
                        t01FieldWrite.setText(String.valueOf(t01Write));
                        ck1Write = query.getCk1Write();
                        ck1FieldWrite.setText(String.format("%.1f",ck1Write));
                        cd1Write = query.getCd1Write();
                        cd1FieldWrite.setText(String.format("%.1f",cd1Write));
                        break;

                }
            }
        });

        //sensor
        NumberFormat number = new DecimalFormat("#0");
        NumberFormatter nF = new NumberFormatter(number);
        DefaultFormatterFactory dFF = new DefaultFormatterFactory(nF);
        addressSensorFieldWrite.setFormatterFactory(dFF);
        addressSensorFieldWrite.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modbusReader.writeModeRegister(2,Integer.parseInt(addressSensorFieldWrite.getText()));
                JOptionPane.showMessageDialog(IZKModbusGUI.this,
                        "Запись завершена");
            }
        });

//info
        refButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (queryBox.isSelected()) queryBox.setSelected(false);
                modbusReader.writeModeRegister(1,16);
                modbusReader.writeModeRegister(0,25);
                query.queryInfo();
                versionFirm = query.getVersionFirm();
                versionFirmField.setText(versionFirm);
                dataFirm = query.getDataFirm();
                dataFirmField.setText(dataFirm);
                identificator = query.getIdentificator();
                identificatorField.setText(identificator);
                dataActiv = query.getDataActiv();
                dataActivField.setText(dataActiv);
                statusActiv = query.getStatusActiv();
                statusActivField.setText(statusActiv);
                if (statusActiv.equals("Идет пробный период. Требуется активация") || statusActiv.equals("Пробный период закончился. Требуется активация")){
                    activButton.setEnabled(true);
                    passwordField.setEditable(true);
                    passwordField.setText("Введите код активации");
                }
                else {
                    activButton.setEnabled(false);
                    passwordField.setEditable(false);
                    passwordField.setText("");
                }

            }
        });
        activButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (passwordField.getText().length() == 8){
                    char [] chars = passwordField.getText().toCharArray();
                    int [] registers = new int[4];
                    int j = 0;
                    for (int i = 0; i < chars.length; i = i+2) {
                        String hex = String.format("%02x", (int) chars[i+1]) + String.format("%02x", (int) chars[i]);
                        registers[j] = Integer.valueOf(hex, 16);
                        j++;
                    }
                    modbusReader.writeASCII(2,registers);
                    refButton.doClick();
                }
                else {
                    passwordField.setText("Не верный код активации!");
                }

            }
        });


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
                modbusReader.writeModeRegister(1,0);
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
                period = query.getPeriod();
                cs1 = query.getCs1();
                cs2 = query.getCs2();
                error = query.getError();
                data = query.getData();

                sensorAddressField.setText(String.format("Адрес платы: %d", sensorAddress));
                timeField.setText(String.format("Текущее время: %s", time));
                humidityField.setText(String.format("Влажность: %.1f %%", humidity));
                temperatureField.setText(String.format("Температура: %.1f °C", temperature));
                densityField.setText(String.format("Плотность: %.1f кг/м²", density));


                periodField.setText(String.format("Период: %.1f у.е.", period));
                cs1Field.setText(String.format("CS1: %.1f Пф", cs1));
                cs2Field.setText(String.format("CS2: %.1f Пф", cs2));
                errorField.setText(String.format("Инструмент. погрешность: %.1f у.е.", error));
                dataField.setText(String.format("Текущая дата: %s", data));


                System.out.println("Таймер");
           } else System.out.println("ждем");
            }
        });







    }






    public static void main(String[] args) {
        Properties properties = new Properties();
        try {
            FileInputStream in = new FileInputStream("settings.properties");
            properties.load(in);
            Terminal terminal = new Terminal(properties.getProperty("ComPort"),properties.getProperty("BoundRate"));
            MasterModbus masterModbus = new MasterModbus(terminal, Integer.parseInt(properties.getProperty("Id")));
            IZKModbusGUI izkModbusGUI = new IZKModbusGUI(terminal,masterModbus);


        } catch (IOException e) {
            IZKTerminal izkTerminal = new IZKTerminal();
            e.printStackTrace();
        }



    }

}
