package ru.sid.izk.modbus.frames;

import ru.sid.izk.modbus.connection.MasterModbus;
import ru.sid.izk.modbus.connection.ModbusReader;
import ru.sid.izk.modbus.connection.Terminal;
import ru.sid.izk.modbus.entity.Query;
import ru.sid.izk.modbus.utils.DigitFilter;
import ru.sid.izk.modbus.utils.FloatDigitalFilter;

import javax.swing.*;
import javax.swing.text.PlainDocument;
import java.awt.event.*;

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
    private JTextField addressSensorFieldWrite;
    private JTextField timeoutFieldWrite;
    private JTextField periodFieldWrite;
    private JTextField t01FieldWrite;
    private JTextField ck1FieldWrite;
    private JTextField cd1FieldWrite;
    private JButton activButton;
    private JTextField passwordField;
    private JTextField checkPeriodFieldWrite;
    private JTextField errorFieldWrite;
    private JTextField cs100FieldWrite;
    private JTextField cmFieldWrite;
    private JTextField kFieldWrite;
    private JTextField cs0FieldWrite;
    private JTextField tcFieldWrite;
    private JTextField csMinFieldWrite;
    private JTextField hMinFieldWrite;
    private JTextField tsd1FieldWrite;
    private JTextField tsd2FieldWrite;
    private JTextField autoMinFieldWrite;
    private JTextField autoMaxFieldWrite;
    private JTextField d20FieldWrite;
    private JTextField kdFieldWrite;
    private JTextField minFieldWrite;
    private JTextField maxFieldWrite;
    private JTextField emerMaxFieldWrite;
    private JTextField noDensityFieldWrite;
    private JButton minButton;
    private JButton maxButton;
    private Timer timer1;
    private final ModbusReader modbusReader;


    public IZKModbusGUI(Terminal terminal, MasterModbus masterModbus) {

        setContentPane(mainPanel);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(1440, 900);
        setLocationRelativeTo(null);
        nameStatLabel.setText("Состояние датчика:");
        statLabel.setText("Нет информации");
        if (!terminal.isError())
            comLabel.setText(String.format("Подключено к %s на скорости %s", terminal.getComName(), terminal.getBound()));
        else comLabel.setText(String.format("Ошибка подключения к %s", terminal.getComName()));
        terminalButton.addActionListener(e -> {
            new IZKTerminal();
            timer1.stop();
            masterModbus.disconnect();
            dispose();
        });
        modbusReader = new ModbusReader(masterModbus.getModbusMaster(), masterModbus.getId());
        Query query = new Query(modbusReader);

        //menu
        tabbedPane1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int idx = ((JTabbedPane) e.getSource()).indexAtLocation(e.getX(), e.getY());
                System.out.println("Выбрана вкладка " + idx);
                if (queryBox.isSelected() && idx != -1) queryBox.setSelected(false);
                switch (idx) {
                    case 0: //info
                        refButton.doClick();
                        break;
                    case 2: //sensor
                        try {
                            modbusReader.writeModeRegister(0, 5);
                            modbusReader.writeModeRegister(1, 0);
                        } catch (Exception e1) {
                            e1.printStackTrace();
                            JOptionPane.showMessageDialog(IZKModbusGUI.this,
                                    "Ошибка инициализации: " + e1.getMessage(), "Ошибка", JOptionPane.ERROR_MESSAGE);

                        }
                        query.querySensor();
                        addressSensorFieldWrite.setText(String.valueOf(query.getSensorAddressWrite()));
                        timeoutFieldWrite.setText(String.valueOf(query.getTimeoutWrite()));
                        periodFieldWrite.setText(String.valueOf(query.getPeriodWrite()));
                        t01FieldWrite.setText(String.valueOf(query.getT01Write()));
                        ck1FieldWrite.setText(String.format("%.1f",query.getCk1Write()));
                        cd1FieldWrite.setText(String.format("%.1f", query.getCd1Write()));
                        checkPeriodFieldWrite.setText(String.valueOf(query.getCheckPeriod()));
                        errorFieldWrite.setText(String.format("%.2f", query.getErrorWrite()));
                        cs100FieldWrite.setText(String.format("%.1f", query.getCs100()));
                        cmFieldWrite.setText(String.format("%.1f", query.getCm()));
                        kFieldWrite.setText(String.format("%.3f", query.getK()));
                        cs0FieldWrite.setText(String.format("%.1f", query.getCs0()));
                        tcFieldWrite.setText(String.format("%.3f", query.getTc()));
                        csMinFieldWrite.setText(String.format("%.1f", query.getCsMin()));
                        hMinFieldWrite.setText(String.format("%.1f", query.gethMin()));
                        tsd1FieldWrite.setText(String.format("%.3f", query.getTsd1()));
                        tsd2FieldWrite.setText(String.format("%.3f", query.getTsd2()));
                        autoMinFieldWrite.setText(String.format("%.1f", query.getAutoMin()));
                        autoMaxFieldWrite.setText(String.format("%.1f", query.getAutoMax()));
                        d20FieldWrite.setText(String.format("%.1f", query.getD20()));
                        kdFieldWrite.setText(String.format("%.3f", query.getKd()));
                        minFieldWrite.setText(String.valueOf(query.getMin()));
                        maxFieldWrite.setText(String.valueOf(query.getMax()));
                        emerMaxFieldWrite.setText(String.valueOf(query.getEmerMax()));
                        noDensityFieldWrite.setText(String.valueOf(query.getNoDensity()));
                        break;
                }
            }
        });

        //sensor
        digitFilter(addressSensorFieldWrite, 2);
        addressSensorFieldWrite.addActionListener(new OneRegisterWriteActionListener(2));
        digitFilter(timeoutFieldWrite, 5);
        timeoutFieldWrite.addActionListener(new OneRegisterWriteActionListener(3));
        digitFilter(periodFieldWrite, 3);
        periodFieldWrite.addActionListener(new OneRegisterWriteActionListener(5));
        digitFilter(t01FieldWrite, 5);
        t01FieldWrite.addActionListener(new OneRegisterWriteActionListener(6));
        floatFilter(ck1FieldWrite, "^[0-9]{0,3}+[,]?[0-9]?$");
        ck1FieldWrite.addActionListener(new TwoRegisterWriteActionListener(7));
        floatFilter(cd1FieldWrite, "^[0-9]{0,3}+[,]?[0-9]?$");
        cd1FieldWrite.addActionListener(new TwoRegisterWriteActionListener(9));
        digitFilter(checkPeriodFieldWrite, 3);
        checkPeriodFieldWrite.addActionListener(new OneRegisterWriteActionListener(44));
        floatFilter(errorFieldWrite, "^[0-9]{0,2}+[,]?[0-9]{0,2}$");
        errorFieldWrite.addActionListener(new TwoRegisterWriteActionListener(42));
        floatFilter(cs100FieldWrite, "^[0-9]{0,3}+[,]?[0-9]?$");
        cs100FieldWrite.addActionListener(new TwoRegisterWriteActionListener(11));
        floatFilter(cmFieldWrite, "^-?[0-9]{0,3}+[,]?[0-9]?$");
        cmFieldWrite.addActionListener(new TwoRegisterWriteActionListener(13));
        floatFilter(kFieldWrite, "^[0-9]?+[,]?[0-9]{0,3}$");
        kFieldWrite.addActionListener(new TwoRegisterWriteActionListener(15));
        floatFilter(cs0FieldWrite, "^-?[0-9]{0,3}+[,]?[0-9]?$");
        cs0FieldWrite.addActionListener(new TwoRegisterWriteActionListener(17));
        floatFilter(csMinFieldWrite, "^[0-9]{0,3}+[,]?[0-9]?$");
        csMinFieldWrite.addActionListener(new TwoRegisterWriteActionListener(19));
        floatFilter(hMinFieldWrite, "^[0-9]{0,3}+[,]?[0-9]?$");
        hMinFieldWrite.addActionListener(new TwoRegisterWriteActionListener(21));
        floatFilter(tsd1FieldWrite, "^[0-9]?+[,]?[0-9]{0,3}$");
        tsd1FieldWrite.addActionListener(new TwoRegisterWriteActionListener(27));
        floatFilter(tsd2FieldWrite, "^[0-9]?+[,]?[0-9]{0,3}$");
        tsd2FieldWrite.addActionListener(new TwoRegisterWriteActionListener(29));
        floatFilter(tcFieldWrite, "^[0-9]?+[,]?[0-9]{0,3}$");
        tcFieldWrite.addActionListener(new TwoRegisterWriteActionListener(31));
        floatFilter(d20FieldWrite, "^[0-9]{0,3}+[,]?[0-9]?$");
        d20FieldWrite.addActionListener(new TwoRegisterWriteActionListener(23));
        floatFilter(kdFieldWrite, "^[0-9]?+[,]?[0-9]{0,3}$");
        kdFieldWrite.addActionListener(new TwoRegisterWriteActionListener(25));
        digitFilter(minFieldWrite, 5);
        minFieldWrite.addActionListener(new OneRegisterWriteActionListener(33));
        digitFilter(maxFieldWrite, 5);
        maxFieldWrite.addActionListener(new OneRegisterWriteActionListener(34));
        digitFilter(emerMaxFieldWrite, 5);
        emerMaxFieldWrite.addActionListener(new OneRegisterWriteActionListener(35));
        digitFilter(noDensityFieldWrite, 3);
        noDensityFieldWrite.addActionListener(new OneRegisterWriteActionListener(37));
        floatFilter(autoMinFieldWrite, "^[0-9]{0,3}+[,]?[0-9]?$");
        minButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String field = autoMinFieldWrite.getText();
                String s;
                if (field.contains(",")) s = field.replace(",", ".");
                else s = field;
                float f = Float.parseFloat(s);
                String sF = hex(f);

                sF = sF.replace("0x", "");
                int[] registers = {Integer.valueOf(sF.substring(4, 8), 16), Integer.valueOf(sF.substring(0, 4), 16)};
                for (int i : registers) {
                    System.out.println(i);
                }
                try {
                    modbusReader.writeRegister(38, registers);
                    JOptionPane.showMessageDialog(IZKModbusGUI.this,
                            "Запись завершена! Изменения появятся\nпосле получения новых данных.", "Подтверждение", JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception q) {
                    q.printStackTrace();
                    JOptionPane.showMessageDialog(IZKModbusGUI.this,
                            "Ошибка записи!", "Ошибка", JOptionPane.ERROR_MESSAGE);
                }

            }

            public String hex(int n) {
                return String.format("0x%8s", Integer.toHexString(n)).replace(' ', '0');
            }

            public String hex(float f) {
                return hex(Float.floatToIntBits(f));
            }
        });
        floatFilter(autoMaxFieldWrite, "^[0-9]{0,3}+[,]?[0-9]?$");
        maxButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String field = autoMaxFieldWrite.getText();
                String s;
                if (field.contains(",")) s = field.replace(",", ".");
                else s = field;
                float f = Float.parseFloat(s);
                String sF = hex(f);

                sF = sF.replace("0x", "");
                int[] registers = {Integer.valueOf(sF.substring(4, 8), 16), Integer.valueOf(sF.substring(0, 4), 16)};
                for (int i : registers) {
                    System.out.println(i);
                }
                try {
                    modbusReader.writeRegister(40, registers);
                    JOptionPane.showMessageDialog(IZKModbusGUI.this,
                            "Запись завершена", "Подтверждение", JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception q) {
                    q.printStackTrace();
                    JOptionPane.showMessageDialog(IZKModbusGUI.this,
                            "Ошибка записи!", "Ошибка", JOptionPane.ERROR_MESSAGE);
                }

            }

            public String hex(int n) {
                return String.format("0x%8s", Integer.toHexString(n)).replace(' ', '0');
            }

            public String hex(float f) {
                return hex(Float.floatToIntBits(f));
            }
        });


//info
        refButton.addActionListener(e -> {
            if (queryBox.isSelected()) queryBox.setSelected(false);
            try {
                modbusReader.writeModeRegister(1, 16);
                modbusReader.writeModeRegister(0, 25);
            } catch (Exception e3) {
                e3.printStackTrace();
                JOptionPane.showMessageDialog(IZKModbusGUI.this,
                        "Ошибка инициализации", "Ошибка", JOptionPane.ERROR_MESSAGE);

            }
            query.queryInfo();
            versionFirmField.setText(query.getVersionFirm());
            dataFirmField.setText(query.getDataFirm());
            identificatorField.setText(query.getIdentificator());
            dataActivField.setText(query.getDataActiv());
            final String statusActive = query.getStatusActive();
            statusActivField.setText(statusActive);
            if (statusActive.equals("Идет пробный период. Требуется активация") || statusActive.equals("Пробный период закончился. Требуется активация")) {
                activButton.setEnabled(true);
                passwordField.setEditable(true);
                passwordField.setText("Введите код активации");
            } else {
                activButton.setEnabled(false);
                passwordField.setEditable(false);
                passwordField.setText("");
            }
        });
        activButton.addActionListener(e -> {
            if (passwordField.getText().length() == 8) {
                char[] chars = passwordField.getText().toCharArray();
                int[] registers = new int[4];
                int j = 0;
                for (int i = 0; i < chars.length; i = i + 2) {
                    String hex = String.format("%02x", (int) chars[i + 1]) + String.format("%02x", (int) chars[i]);
                    registers[j] = Integer.valueOf(hex, 16);
                    j++;
                }
                try {
                    modbusReader.writeRegister(2, registers);
                    JOptionPane.showMessageDialog(IZKModbusGUI.this,
                            "Запись завершена", "", JOptionPane.INFORMATION_MESSAGE);
                    refButton.doClick();
                } catch (Exception e1) {
                    e1.printStackTrace();
                    JOptionPane.showMessageDialog(IZKModbusGUI.this,
                            "Ошибка записи!", "", JOptionPane.ERROR_MESSAGE);
                    passwordField.setText("Ошибка записи");
                }
            } else {
                passwordField.setText("Не верный код активации!");
            }

        });


        queryBox.addItemListener(e -> {
            if (queryBox.isSelected()) {
                try {
                    modbusReader.writeModeRegister(1, 0);
                    timer1.start();
                } catch (Exception e1) {
                    e1.printStackTrace();
                    timer1.stop();
                    JOptionPane.showMessageDialog(IZKModbusGUI.this,
                            "Ошибка инициализации", "Ошибка", JOptionPane.ERROR_MESSAGE);
                }

            } else {
                timer1.stop();
                System.out.println("Опрос завершен");
            }
        });


        timer1 = new Timer(500, e -> {
            query.queryStatus();
            final String status = query.getStatus();
            statLabel.setText(status);
            if (status.equals("Данные получены")) {
                query.queryMain();
                sensorAddressField.setText(String.format("Адрес платы: %d", query.getSensorAddress()));
                timeField.setText(String.format("Текущее время: %s", query.getTime()));
                humidityField.setText(String.format("Влажность: %.1f %%", query.getHumidity()));
                temperatureField.setText(String.format("Температура: %.1f °C", query.getTemperature()));
                densityField.setText(String.format("Плотность: %.1f кг/м²", query.getDensity()));
                periodField.setText(String.format("Период: %.1f у.е.", query.getPeriod()));
                cs1Field.setText(String.format("CS1: %.1f Пф", query.getCs1()));
                cs2Field.setText(String.format("CS2: %.1f Пф", query.getCs2()));
                errorField.setText(String.format("Инструмент. погрешность: %.1f у.е.", query.getError()));
                dataField.setText(String.format("Текущая дата: %s", query.getData()));
                System.out.println("Таймер");
            } else System.out.println("ждем");
        });
    }

    public static void digitFilter(JTextField textField, int count) {
        PlainDocument doc = (PlainDocument) textField.getDocument();
        doc.setDocumentFilter(new DigitFilter(count));
    }

    public static void floatFilter(JTextField textField, String pattern) {
        PlainDocument doc = (PlainDocument) textField.getDocument();
        doc.setDocumentFilter(new FloatDigitalFilter(pattern));
    }

    public class OneRegisterWriteActionListener implements ActionListener {
        int offset;
        String field;

        public OneRegisterWriteActionListener(int offset) {
            this.offset = offset;
            // this.field = field;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            field = ((JTextField) e.getSource()).getText();
            try {
                modbusReader.writeModeRegister(offset, Integer.parseInt(field));
                JOptionPane.showMessageDialog(IZKModbusGUI.this,
                        "Запись завершена", "Подтверждение", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception e2) {
                e2.printStackTrace();
                JOptionPane.showMessageDialog(IZKModbusGUI.this,
                        "Ошибка записи!", "Ошибка", JOptionPane.ERROR_MESSAGE);

            }
        }
    }

    public class TwoRegisterWriteActionListener implements ActionListener {
        int offset;
        String field;

        public TwoRegisterWriteActionListener(int offset) {
            this.offset = offset;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            field = ((JTextField) e.getSource()).getText();
            String s;
            if (field.contains(",")) s = field.replace(",", ".");
            else s = field;
            float f = Float.parseFloat(s);
            String sF = stringFromHex(f);

            sF = sF.replace("0x", "");
            int[] registers = {Integer.valueOf(sF.substring(4, 8), 16), Integer.valueOf(sF.substring(0, 4), 16)};
            for (int i : registers) {
                System.out.println(i);
            }
            try {
                modbusReader.writeRegister(offset, registers);
                JOptionPane.showMessageDialog(IZKModbusGUI.this,
                        "Запись завершена", "Подтверждение", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception q) {
                q.printStackTrace();
                JOptionPane.showMessageDialog(IZKModbusGUI.this,
                        "Ошибка записи!", "Ошибка", JOptionPane.ERROR_MESSAGE);
            }
        }

        public String stringFromHex(int n) {
            return String.format("0x%8s", Integer.toHexString(n)).replace(' ', '0');
        }

        public String stringFromHex(float f) {
            return stringFromHex(Float.floatToIntBits(f));
        }
    }
}