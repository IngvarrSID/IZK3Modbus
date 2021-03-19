package ru.sid.izk.modbus.frames;

import ru.sid.izk.modbus.adapter.TabbedPaneMouseAdapter;
import ru.sid.izk.modbus.connection.MasterModbus;
import ru.sid.izk.modbus.connection.ModbusReader;
import ru.sid.izk.modbus.connection.Terminal;
import ru.sid.izk.modbus.entity.Query;
import ru.sid.izk.modbus.listener.*;

import javax.swing.*;

import static ru.sid.izk.modbus.utils.FilterUtils.digitFilter;
import static ru.sid.izk.modbus.utils.FilterUtils.floatFilter;

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
    private JButton refreshSensorButton;
    private JComboBox<String> channelsBox;
    private JLabel channelLabel;
    private JButton refreshSettingsButton;
    private JTextField textField1;
    private JButton oneChannelButton;
    private JButton threeChannelButton;
    private JButton fourChannelButton;
    private JButton twoChannelButton;
    private JComboBox<String> numberRelayBox1;
    private JComboBox<String> settingRelayBox1;
    private JComboBox<String> modeRelayBox1;
    private JComboBox<String> numberRelayBox2;
    private JComboBox<String> numberRelayBox3;
    private JComboBox<String> numberRelayBox4;
    private JComboBox<String> numberRelayBox5;
    private JComboBox<String> numberRelayBox6;
    private JComboBox<String> numberRelayBox7;
    private JComboBox<String> numberRelayBox8;
    private JComboBox<String> numberRelayBox9;
    private JComboBox<String> numberRelayBox10;
    private JComboBox<String> settingRelayBox2;
    private JComboBox<String> settingRelayBox3;
    private JComboBox<String> settingRelayBox4;
    private JComboBox<String> settingRelayBox5;
    private JComboBox<String> settingRelayBox6;
    private JComboBox<String> settingRelayBox7;
    private JComboBox<String> settingRelayBox8;
    private JComboBox<String> settingRelayBox9;
    private JComboBox<String> settingRelayBox10;
    private JComboBox<String> modeRelayBox2;
    private JComboBox<String> modeRelayBox3;
    private JComboBox<String> modeRelayBox4;
    private JComboBox<String> modeRelayBox5;
    private JComboBox<String> modeRelayBox6;
    private JComboBox<String> modeRelayBox7;
    private JComboBox<String> modeRelayBox8;
    private JComboBox<String> modeRelayBox9;
    private JComboBox<String> modeRelayBox10;
    private final Timer connectionTimeoutTimer;
    //TODO get rid of this argument in ActionListeners, use getter instead.
    private final ModbusReader modbusReader;

    public IZKModbusGUI(Terminal terminal, MasterModbus masterModbus) {

        initWindow();
        initIZKSettings();
        nameStatLabel.setText("Состояние датчика:");
        statLabel.setText("Нет информации");
        if (!terminal.isError())
            comLabel.setText(String.format("Подключено к %s на скорости %s", terminal.getComName(), terminal.getBound()));
        else
            comLabel.setText(String.format("Ошибка подключения к %s", terminal.getComName()));
        terminalButton.addActionListener(new TerminalButtonActionListener(this, masterModbus));
        modbusReader = new ModbusReader(masterModbus.getModbusMaster(), masterModbus.getId());
        final Query query = new Query(modbusReader);
        //menu
        tabbedPane1.addMouseListener(new TabbedPaneMouseAdapter(this, modbusReader));
        //channels
        final String[] channels = {"Канал 1", "Канал 2", "Канал 3", "Канал 4"};
        for (String s : channels) {
            channelsBox.addItem(s);
        }
        channelsBox.setSelectedIndex(0);
        channelsBox.addActionListener(new ChannelsBoxActionListener(this, modbusReader));
        //sensor
        refreshSensorButton.addActionListener(new RefreshSensorButtonActionListener(query, this));
        filterAndAddRegisterActionListeners(modbusReader);
        minButton.addActionListener(new MinButtonActionListener(this, modbusReader));
        maxButton.addActionListener(new MaxButtonActionListener(this, modbusReader));
        //info
        refButton.addActionListener(new RefButtonActionListener(query, this, modbusReader));
        activButton.addActionListener(new ActivButtonActionListener(this, modbusReader));
        queryBox.addItemListener(new QueryBoxItemListener(this, modbusReader));
        connectionTimeoutTimer = new Timer(500, new TimerActionListener(query, this));
    }

    private void initWindow() {

        setContentPane(mainPanel);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(1440, 900);
        setLocationRelativeTo(null);
    }

    private void initIZKSettings(){
        final String[] numbersRelays = {"1","2","3","4","5","6","7","8","9","10"};
        final String[] settingsRelays = {"Не используется","Минимимум по любому каналу","Максимум по любому каналу","Аварийный максимум по любому каналу","Предельное давление по любому каналу", "Нет потока по любому каналу", "Минимум по первому каналу",
                "Минимум по второму каналу", "Минимум по третьему каналу","Минимум по четвертому каналу","Максимум по первому каналу", "Максимум по второму каналу", "Максимум по третьему каналу", "Максимум по четвертому каналу","Аварийный максимум по первому каналу",
                "Аварийный максимум по второму каналу", "Аварийный максимум по третьему каналу", "Аварийный максимум по четвертому каналу","Предельное давление по первому каналу", "Предельное давление по второму каналу","Предельное давление по третьему каналу",
                "Предельное давление по четветому каналу","Нет потока по первому каналу", "Нет потока по второму каналу", "Нет потока по третьему канаду", "Нет потока по четвертому каналу"};
        final String[] modesRelays = {"Не используется","Нормально открыт","Нормально закрыт","Мигание"};
        for (String s: numbersRelays) {
            numberRelayBox1.addItem(s);
            numberRelayBox2.addItem(s);
            numberRelayBox3.addItem(s);
            numberRelayBox4.addItem(s);
            numberRelayBox5.addItem(s);
            numberRelayBox6.addItem(s);
            numberRelayBox7.addItem(s);
            numberRelayBox8.addItem(s);
            numberRelayBox9.addItem(s);
            numberRelayBox10.addItem(s);
        }
        for (String s: settingsRelays) {
            settingRelayBox1.addItem(s);
            settingRelayBox2.addItem(s);
            settingRelayBox3.addItem(s);
            settingRelayBox4.addItem(s);
            settingRelayBox5.addItem(s);
            settingRelayBox6.addItem(s);
            settingRelayBox7.addItem(s);
            settingRelayBox8.addItem(s);
            settingRelayBox9.addItem(s);
            settingRelayBox10.addItem(s);
        }
        for (String s:modesRelays) {
            modeRelayBox1.addItem(s);
            modeRelayBox2.addItem(s);
            modeRelayBox3.addItem(s);
            modeRelayBox4.addItem(s);
            modeRelayBox5.addItem(s);
            modeRelayBox6.addItem(s);
            modeRelayBox7.addItem(s);
            modeRelayBox8.addItem(s);
            modeRelayBox9.addItem(s);
            modeRelayBox10.addItem(s);
        }
    }

    private void filterAndAddRegisterActionListeners(ModbusReader modbusReader) {

        digitFilter(addressSensorFieldWrite, 2);
        addressSensorFieldWrite.addActionListener(new OneRegisterWriteActionListener(2, this, modbusReader));
        digitFilter(timeoutFieldWrite, 5);
        timeoutFieldWrite.addActionListener(new OneRegisterWriteActionListener(3, this, modbusReader));
        digitFilter(periodFieldWrite, 3);
        periodFieldWrite.addActionListener(new OneRegisterWriteActionListener(5, this, modbusReader));
        digitFilter(t01FieldWrite, 5);
        t01FieldWrite.addActionListener(new OneRegisterWriteActionListener(6, this, modbusReader));
        floatFilter(ck1FieldWrite, "^[0-9]{1,3}+[,]?[0-9]?$");
        ck1FieldWrite.addActionListener(new TwoRegisterWriteActionListener(7, this, modbusReader));
        floatFilter(cd1FieldWrite, "^[0-9]{0,3}+[,]?[0-9]?$");
        cd1FieldWrite.addActionListener(new TwoRegisterWriteActionListener(9, this, modbusReader));
        digitFilter(checkPeriodFieldWrite, 3);
        checkPeriodFieldWrite.addActionListener(new OneRegisterWriteActionListener(44, this, modbusReader));
        floatFilter(errorFieldWrite, "^[0-9]{0,2}+[,]?[0-9]{0,2}$");
        errorFieldWrite.addActionListener(new TwoRegisterWriteActionListener(42, this, modbusReader));
        floatFilter(cs100FieldWrite, "^[0-9]{0,3}+[,]?[0-9]?$");
        cs100FieldWrite.addActionListener(new TwoRegisterWriteActionListener(11, this, modbusReader));
        floatFilter(cmFieldWrite, "^-?[0-9]{0,3}+[,]?[0-9]?$");
        cmFieldWrite.addActionListener(new TwoRegisterWriteActionListener(13, this, modbusReader));
        floatFilter(kFieldWrite, "^[0-9]?+[,]?[0-9]{0,3}$");
        kFieldWrite.addActionListener(new TwoRegisterWriteActionListener(15, this, modbusReader));
        floatFilter(cs0FieldWrite, "^-?[0-9]{0,3}+[,]?[0-9]?$");
        cs0FieldWrite.addActionListener(new TwoRegisterWriteActionListener(17, this, modbusReader));
        floatFilter(csMinFieldWrite, "^[0-9]{0,3}+[,]?[0-9]?$");
        csMinFieldWrite.addActionListener(new TwoRegisterWriteActionListener(19, this, modbusReader));
        floatFilter(hMinFieldWrite, "^[0-9]{0,3}+[,]?[0-9]?$");
        hMinFieldWrite.addActionListener(new TwoRegisterWriteActionListener(21, this, modbusReader));
        floatFilter(tsd1FieldWrite, "^[0-9]?+[,]?[0-9]{0,3}$");
        tsd1FieldWrite.addActionListener(new TwoRegisterWriteActionListener(27, this, modbusReader));
        floatFilter(tsd2FieldWrite, "^[0-9]?+[,]?[0-9]{0,3}$");
        tsd2FieldWrite.addActionListener(new TwoRegisterWriteActionListener(29, this, modbusReader));
        floatFilter(tcFieldWrite, "^[0-9]?+[,]?[0-9]{0,3}$");
        tcFieldWrite.addActionListener(new TwoRegisterWriteActionListener(31, this, modbusReader));
        floatFilter(d20FieldWrite, "^[0-9]{0,3}+[,]?[0-9]?$");
        d20FieldWrite.addActionListener(new TwoRegisterWriteActionListener(23, this, modbusReader));
        floatFilter(kdFieldWrite, "^[0-9]?+[,]?[0-9]{0,3}$");
        kdFieldWrite.addActionListener(new TwoRegisterWriteActionListener(25, this, modbusReader));
        digitFilter(minFieldWrite, 5);
        minFieldWrite.addActionListener(new OneRegisterWriteActionListener(33, this, modbusReader));
        digitFilter(maxFieldWrite, 5);
        maxFieldWrite.addActionListener(new OneRegisterWriteActionListener(34, this, modbusReader));
        digitFilter(emerMaxFieldWrite, 5);
        emerMaxFieldWrite.addActionListener(new OneRegisterWriteActionListener(35, this, modbusReader));
        digitFilter(noDensityFieldWrite, 3);
        noDensityFieldWrite.addActionListener(new OneRegisterWriteActionListener(37, this, modbusReader));
        floatFilter(autoMinFieldWrite, "^[0-9]{0,3}+[,]?[0-9]?$");
        floatFilter(autoMaxFieldWrite, "^[0-9]{0,3}+[,]?[0-9]?$");
    }

    public Timer getConnectionTimeoutTimer() {
        return connectionTimeoutTimer;
    }

    public JCheckBox getQueryBox() {
        return queryBox;
    }

    public JButton getRefButton() {
        return refButton;
    }

    public JComboBox<String> getChannelsBox() {
        return channelsBox;
    }

    public JLabel getChannelLabel() {
        return channelLabel;
    }

    public JButton getRefreshSensorButton() {
        return refreshSensorButton;
    }

    public JTextField getSensorAddressField() {
        return sensorAddressField;
    }

    public JTextField getHumidityField() {
        return humidityField;
    }

    public JTextField getTemperatureField() {
        return temperatureField;
    }

    public JTextField getTimeField() {
        return timeField;
    }

    public JTextField getDensityField() {
        return densityField;
    }

    public JLabel getStatLabel() {
        return statLabel;
    }

    public JTextField getDataField() {
        return dataField;
    }

    public JTextField getPeriodField() {
        return periodField;
    }

    public JTextField getCs1Field() {
        return cs1Field;
    }

    public JTextField getCs2Field() {
        return cs2Field;
    }

    public JTextField getErrorField() {
        return errorField;
    }

    public JTextField getVersionFirmField() {
        return versionFirmField;
    }

    public JTextField getDataFirmField() {
        return dataFirmField;
    }

    public JTextField getStatusActivField() {
        return statusActivField;
    }

    public JTextField getIdentificatorField() {
        return identificatorField;
    }

    public JTextField getDataActivField() {
        return dataActivField;
    }

    public JTextField getAddressSensorFieldWrite() {
        return addressSensorFieldWrite;
    }

    public JTextField getTimeoutFieldWrite() {
        return timeoutFieldWrite;
    }

    public JTextField getPeriodFieldWrite() {
        return periodFieldWrite;
    }

    public JTextField getT01FieldWrite() {
        return t01FieldWrite;
    }

    public JTextField getCk1FieldWrite() {
        return ck1FieldWrite;
    }

    public JTextField getCd1FieldWrite() {
        return cd1FieldWrite;
    }

    public JButton getActivButton() {
        return activButton;
    }

    public JTextField getPasswordField() {
        return passwordField;
    }

    public JTextField getCheckPeriodFieldWrite() {
        return checkPeriodFieldWrite;
    }

    public JTextField getErrorFieldWrite() {
        return errorFieldWrite;
    }

    public JTextField getCs100FieldWrite() {
        return cs100FieldWrite;
    }

    public JTextField getCmFieldWrite() {
        return cmFieldWrite;
    }

    public JTextField getkFieldWrite() {
        return kFieldWrite;
    }

    public JTextField getCs0FieldWrite() {
        return cs0FieldWrite;
    }

    public JTextField getTcFieldWrite() {
        return tcFieldWrite;
    }

    public JTextField getCsMinFieldWrite() {
        return csMinFieldWrite;
    }

    public JTextField gethMinFieldWrite() {
        return hMinFieldWrite;
    }

    public JTextField getTsd1FieldWrite() {
        return tsd1FieldWrite;
    }

    public JTextField getTsd2FieldWrite() {
        return tsd2FieldWrite;
    }

    public JTextField getAutoMinFieldWrite() {
        return autoMinFieldWrite;
    }

    public JTextField getAutoMaxFieldWrite() {
        return autoMaxFieldWrite;
    }

    public JTextField getD20FieldWrite() {
        return d20FieldWrite;
    }

    public JTextField getKdFieldWrite() {
        return kdFieldWrite;
    }

    public JTextField getMinFieldWrite() {
        return minFieldWrite;
    }

    public JTextField getMaxFieldWrite() {
        return maxFieldWrite;
    }

    public JTextField getEmerMaxFieldWrite() {
        return emerMaxFieldWrite;
    }

    public JTextField getNoDensityFieldWrite() {
        return noDensityFieldWrite;
    }

    public ModbusReader getModbusReader() {
        return modbusReader;
    }
}