package ru.sid.izk.modbus.frames;

import ru.sid.izk.modbus.adapter.TabbedPaneMouseAdapter;
import ru.sid.izk.modbus.archive.CSVAdapter;
import ru.sid.izk.modbus.connection.MasterModbus;
import ru.sid.izk.modbus.connection.ModbusReader;
import ru.sid.izk.modbus.connection.Terminal;
import ru.sid.izk.modbus.entity.Query;
import ru.sid.izk.modbus.listener.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static ru.sid.izk.modbus.utils.FilterUtils.digitFilter;
import static ru.sid.izk.modbus.utils.FilterUtils.floatFilter;
import static ru.sid.izk.modbus.utils.FieldVisible.hideFielder;

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
    private JTextField addressIZK;
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
    private JTable archiveTable;
    private JButton searchButton;
    private JProgressBar progressBar;
    private JButton manualButton;
    private JButton modeButton;
    private JButton closeButton;
    private JButton openButton;
    private JButton fullCloseButton;
    private JButton fullOpenButton;
    private JTextField oneStepRegulator;
    private JTextField fullStepRegulator;
    private JTextField humidityWriteField;
    private JTextField kpWriteField;
    private JTextField kiWriteField;
    private JTextField kdWriteField;
    private JTextField pidErrField;
    private JTextField pidIntField;
    private JTextField pidDifField;
    private JProgressBar progressRegulatorBar;
    private JLabel regulatorStatusField;
    private JButton refreshRegulatorButton;
    private final Timer connectionTimeoutTimer;
    private final String[] numbersRelays;
    private final String[] settingsRelays;
    private final String[] modesRelays;

    private boolean readyToWriteRelay;

    //TODO get rid of this argument in ActionListeners, use getter instead.
    private final ModbusReader modbusReader;

    public IZKModbusGUI(Terminal terminal, MasterModbus masterModbus) {

        initWindow();
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
        tabbedPane1.addMouseListener(new TabbedPaneMouseAdapter(this));
        //channels
        final String[] channels = {"Канал 1", "Канал 2", "Канал 3", "Канал 4"};
        for (String s : channels) {
            channelsBox.addItem(s);
        }
        channelsBox.setSelectedIndex(0);
        channelsBox.addActionListener(new ChannelsBoxActionListener(this, modbusReader));
        //sensor
        refreshSensorButton.addActionListener(new RefreshSensorButtonActionListener(query, this));
        minButton.addActionListener(new MinButtonActionListener(this, modbusReader));
        maxButton.addActionListener(new MaxButtonActionListener(this, modbusReader));
        //info
        refButton.addActionListener(new RefButtonActionListener(query, this, modbusReader));
        activButton.addActionListener(new ActivButtonActionListener(this, modbusReader));
        queryBox.addItemListener(new QueryBoxItemListener(this, modbusReader));
        connectionTimeoutTimer = new Timer(500, new TimerActionListener(query, this,masterModbus));
        //settings
        numbersRelays = new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
        settingsRelays = new String[]{"Не используется", "Минимимум по любому каналу", "Максимум по любому каналу", "Аварийный максимум по любому каналу", "Предельное давление по любому каналу", "Нет потока по любому каналу", "Минимум по первому каналу",
                "Минимум по второму каналу", "Минимум по третьему каналу", "Минимум по четвертому каналу", "Максимум по первому каналу", "Максимум по второму каналу", "Максимум по третьему каналу", "Максимум по четвертому каналу", "Аварийный максимум по первому каналу",
                "Аварийный максимум по второму каналу", "Аварийный максимум по третьему каналу", "Аварийный максимум по четвертому каналу", "Предельное давление по первому каналу", "Предельное давление по второму каналу", "Предельное давление по третьему каналу",
                "Предельное давление по четветому каналу", "Нет потока по первому каналу", "Нет потока по второму каналу", "Нет потока по третьему канаду", "Нет потока по четвертому каналу"};
        modesRelays = new String[]{"Не используется", "Нормально открыт", "Нормально закрыт", "Мигание"};
        initIZKSettings();
        refreshSettingsButton.addActionListener(new RefreshSettingsButtonActionListener(query,modbusReader,this));
        oneChannelButton.addActionListener(new ChannelsButtonActionListener(this,1,query,modbusReader));
        twoChannelButton.addActionListener(new ChannelsButtonActionListener(this,2,query,modbusReader));
        threeChannelButton.addActionListener(new ChannelsButtonActionListener(this,3,query,modbusReader));
        fourChannelButton.addActionListener(new ChannelsButtonActionListener(this,4,query,modbusReader));
        filterAndAddRegisterActionListeners(modbusReader);
        initTable(masterModbus,query);
        progressBar.setVisible(false);
        searchButton.addActionListener(new SearchButtonActionListener(this,query,modbusReader));
        refreshRegulatorButton.addActionListener(new RefreshRegulatorButtonActionListener(query,modbusReader,this));
        regulatorButtonsInit();
    }

    private void initWindow() {

        setContentPane(mainPanel);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(1350, 950);
        setLocationRelativeTo(null);
        setTitle("Конфигуратор СУ-5Д. Влагомер");
        File file = new File("icon.png");
        try {
            setIconImage(ImageIO.read(file));
        } catch (IOException e){
            e.printStackTrace();
        }
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(createFileMenu());
        menuBar.add(createSettingsMenu());
        setJMenuBar(menuBar);
        hideFielder(this,false);
    }

    private void initTable(MasterModbus masterModbus, Query query){
        CSVAdapter csvAdapter = new CSVAdapter(this,masterModbus,query);
        List<String[]> allRows = csvAdapter.fileRead();
        DefaultTableModel model = (DefaultTableModel) archiveTable.getModel();
        for (String s:allRows.get(0)) {
            model.addColumn(s);
        }
        if (allRows.size()>1) {
            for (int i = 1; i < allRows.size(); i++) {
                model.addRow(allRows.get(i));
            }
        }
        archiveTable.setFillsViewportHeight(true);
    }

    public void refreshTable(CSVAdapter csvAdapter){
        List<String[]> allRows = csvAdapter.fileRead();
        DefaultTableModel model = (DefaultTableModel) archiveTable.getModel();
        int rowCount = model.getRowCount();
        for (int i = rowCount-1; i >= 0 ; i--) {
            model.removeRow(i);
        }
        if (allRows.size()>1) {
            for (int i = 1; i < allRows.size(); i++) {
                model.addRow(allRows.get(i));
            }
        }
        archiveTable.setFillsViewportHeight(true);
    }

    private void initIZKSettings(){
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
        //sensor
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
        //settings
        digitFilter(addressIZK,3);
        addressIZK.addActionListener(new OneRegisterWriteActionListener(2,this,modbusReader));
        //PID
        floatFilter(kpWriteField,"^[0-9]{1,3}+[,]?[0-9]?$");
        floatFilter(kiWriteField,"^[0-9]{1,3}+[,]?[0-9]?$");
        floatFilter(kdWriteField,"^[0-9]{1,3}+[,]?[0-9]?$");
        floatFilter(humidityWriteField,"^[0-9]{1,3}+[,]?[0-9]?$");
        digitFilter(oneStepRegulator,5);
        digitFilter(fullStepRegulator,5);
        kpWriteField.addActionListener(new TwoRegisterWriteActionListener(35,this,modbusReader));
        kiWriteField.addActionListener(new TwoRegisterWriteActionListener(37,this,modbusReader));
        kdWriteField.addActionListener(new TwoRegisterWriteActionListener(39,this,modbusReader));
        humidityWriteField.addActionListener(new TwoRegisterWriteActionListener(41,this,modbusReader));
        oneStepRegulator.addActionListener(new OneRegisterWriteActionListener(43,this,modbusReader));
        fullStepRegulator.addActionListener(new OneRegisterWriteActionListener(44,this,modbusReader));

    }

    public void relayActionListeners(ModbusReader modbusReader){
        settingRelayBox1.addActionListener(new ComboBoxActionListener(this,modbusReader,5,false));
        numberRelayBox1.addActionListener(new ComboBoxActionListener(this,modbusReader,6,true));
        modeRelayBox1.addActionListener(new ComboBoxActionListener(this,modbusReader,7,false));
        settingRelayBox2.addActionListener(new ComboBoxActionListener(this,modbusReader,8,false));
        numberRelayBox2.addActionListener(new ComboBoxActionListener(this,modbusReader,9,true));
        modeRelayBox2.addActionListener(new ComboBoxActionListener(this,modbusReader,10,false));
        settingRelayBox3.addActionListener(new ComboBoxActionListener(this,modbusReader,11,false));
        numberRelayBox3.addActionListener(new ComboBoxActionListener(this,modbusReader,12,true));
        modeRelayBox3.addActionListener(new ComboBoxActionListener(this,modbusReader,13,false));
        settingRelayBox4.addActionListener(new ComboBoxActionListener(this,modbusReader,14,false));
        numberRelayBox4.addActionListener(new ComboBoxActionListener(this,modbusReader,15,true));
        modeRelayBox4.addActionListener(new ComboBoxActionListener(this,modbusReader,16,false));
        settingRelayBox5.addActionListener(new ComboBoxActionListener(this,modbusReader,17,false));
        numberRelayBox5.addActionListener(new ComboBoxActionListener(this,modbusReader,18,true));
        modeRelayBox5.addActionListener(new ComboBoxActionListener(this,modbusReader,19,false));
        settingRelayBox6.addActionListener(new ComboBoxActionListener(this,modbusReader,20,false));
        numberRelayBox6.addActionListener(new ComboBoxActionListener(this,modbusReader,21,true));
        modeRelayBox6.addActionListener(new ComboBoxActionListener(this,modbusReader,22,false));
        settingRelayBox7.addActionListener(new ComboBoxActionListener(this,modbusReader,23,false));
        numberRelayBox7.addActionListener(new ComboBoxActionListener(this,modbusReader,24,true));
        modeRelayBox7.addActionListener(new ComboBoxActionListener(this,modbusReader,25,false));
        settingRelayBox8.addActionListener(new ComboBoxActionListener(this,modbusReader,26,false));
        numberRelayBox8.addActionListener(new ComboBoxActionListener(this,modbusReader,27,true));
        modeRelayBox8.addActionListener(new ComboBoxActionListener(this,modbusReader,28,false));
        settingRelayBox9.addActionListener(new ComboBoxActionListener(this,modbusReader,29,false));
        numberRelayBox9.addActionListener(new ComboBoxActionListener(this,modbusReader,30,true));
        modeRelayBox9.addActionListener(new ComboBoxActionListener(this,modbusReader,31,false));
        settingRelayBox10.addActionListener(new ComboBoxActionListener(this,modbusReader,32,false));
        numberRelayBox10.addActionListener(new ComboBoxActionListener(this,modbusReader,33,true));
        modeRelayBox10.addActionListener(new ComboBoxActionListener(this,modbusReader,34,false));
    }

    public void regulatorButtonsInit(){
        modeButton.addActionListener(new RegulatorButtonsActionListener(this,10,modbusReader));
        manualButton.addActionListener(new RegulatorButtonsActionListener(this,11,modbusReader));
        closeButton.addActionListener(new RegulatorButtonsActionListener(this,12,modbusReader));
        openButton.addActionListener(new RegulatorButtonsActionListener(this,13,modbusReader));
        fullCloseButton.addActionListener(new RegulatorButtonsActionListener(this,14,modbusReader));
        fullOpenButton.addActionListener(new RegulatorButtonsActionListener(this,15,modbusReader));
    }

    private JMenu createFileMenu(){
        JMenu file = new JMenu("Файл");
        JMenuItem open = new JMenuItem("Открыть");
        JMenuItem exit = new JMenuItem("Закрыть");
        file.add(open);
        file.addSeparator();
        file.add(exit);
        exit.addActionListener(e -> System.exit(0));
        return file;
    }
    private JMenu createSettingsMenu(){
        JMenu settings = new JMenu("Настройки");
        JMenuItem path = new JMenuItem("Путь к архиву");
        settings.add(path);
        path.addActionListener(new PathInputActionListener(this));
        return settings;
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

    public JButton getRefreshSettingsButton() {
        return refreshSettingsButton;
    }

    public JTextField getAddressIZK() {
        return addressIZK;
    }

    public JButton getOneChannelButton() {
        return oneChannelButton;
    }

    public JButton getThreeChannelButton() {
        return threeChannelButton;
    }

    public JButton getFourChannelButton() {
        return fourChannelButton;
    }

    public JButton getTwoChannelButton() {
        return twoChannelButton;
    }

    public JComboBox<String> getNumberRelayBox1() {
        return numberRelayBox1;
    }

    public JComboBox<String> getSettingRelayBox1() {
        return settingRelayBox1;
    }

    public JComboBox<String> getModeRelayBox1() {
        return modeRelayBox1;
    }

    public JComboBox<String> getNumberRelayBox2() {
        return numberRelayBox2;
    }

    public JComboBox<String> getNumberRelayBox3() {
        return numberRelayBox3;
    }

    public JComboBox<String> getNumberRelayBox4() {
        return numberRelayBox4;
    }

    public JComboBox<String> getNumberRelayBox5() {
        return numberRelayBox5;
    }

    public JComboBox<String> getNumberRelayBox6() {
        return numberRelayBox6;
    }

    public JComboBox<String> getNumberRelayBox7() {
        return numberRelayBox7;
    }

    public JComboBox<String> getNumberRelayBox8() {
        return numberRelayBox8;
    }

    public JComboBox<String> getNumberRelayBox9() {
        return numberRelayBox9;
    }

    public JComboBox<String> getNumberRelayBox10() {
        return numberRelayBox10;
    }

    public JComboBox<String> getSettingRelayBox2() {
        return settingRelayBox2;
    }

    public JComboBox<String> getSettingRelayBox3() {
        return settingRelayBox3;
    }

    public JComboBox<String> getSettingRelayBox4() {
        return settingRelayBox4;
    }

    public JComboBox<String> getSettingRelayBox5() {
        return settingRelayBox5;
    }

    public JComboBox<String> getSettingRelayBox6() {
        return settingRelayBox6;
    }

    public JComboBox<String> getSettingRelayBox7() {
        return settingRelayBox7;
    }

    public JComboBox<String> getSettingRelayBox8() {
        return settingRelayBox8;
    }

    public JComboBox<String> getSettingRelayBox9() {
        return settingRelayBox9;
    }

    public JComboBox<String> getSettingRelayBox10() {
        return settingRelayBox10;
    }

    public JComboBox<String> getModeRelayBox2() {
        return modeRelayBox2;
    }

    public JComboBox<String> getModeRelayBox3() {
        return modeRelayBox3;
    }

    public JComboBox<String> getModeRelayBox4() {
        return modeRelayBox4;
    }

    public JComboBox<String> getModeRelayBox5() {
        return modeRelayBox5;
    }

    public JComboBox<String> getModeRelayBox6() {
        return modeRelayBox6;
    }

    public JComboBox<String> getModeRelayBox7() {
        return modeRelayBox7;
    }

    public JComboBox<String> getModeRelayBox8() {
        return modeRelayBox8;
    }

    public JComboBox<String> getModeRelayBox9() {
        return modeRelayBox9;
    }

    public JComboBox<String> getModeRelayBox10() {
        return modeRelayBox10;
    }

    public String[] getNumbersRelays() {
        return numbersRelays;
    }

    public String[] getSettingsRelays() {
        return settingsRelays;
    }

    public String[] getModesRelays() {
        return modesRelays;
    }

    public JProgressBar getProgressBar() {
        return progressBar;
    }

    public boolean isReadyToWriteRelay() {
        return readyToWriteRelay;
    }

    public void setReadyToWriteRelay(boolean readyToWriteRelay) {
        this.readyToWriteRelay = readyToWriteRelay;
    }

    public JButton getManualButton() {
        return manualButton;
    }

    public JButton getModeButton() {
        return modeButton;
    }

    public JButton getCloseButton() {
        return closeButton;
    }

    public JButton getOpenButton() {
        return openButton;
    }

    public JButton getFullCloseButton() {
        return fullCloseButton;
    }

    public JButton getFullOpenButton() {
        return fullOpenButton;
    }

    public JTextField getOneStepRegulator() {
        return oneStepRegulator;
    }

    public JTextField getFullStepRegulator() {
        return fullStepRegulator;
    }

    public JTextField getHumidityWriteField() {
        return humidityWriteField;
    }

    public JTextField getKpWriteField() {
        return kpWriteField;
    }

    public JTextField getKiWriteField() {
        return kiWriteField;
    }

    public JTextField getKdWriteField() {
        return kdWriteField;
    }

    public JTextField getPidErrField() {
        return pidErrField;
    }

    public JTextField getPidIntField() {
        return pidIntField;
    }

    public JTextField getPidDifField() {
        return pidDifField;
    }

    public JProgressBar getProgressRegulatorBar() {
        return progressRegulatorBar;
    }

    public JLabel getRegulatorStatusField() {
        return regulatorStatusField;
    }

    public JButton getRefreshRegulatorButton() {
        return refreshRegulatorButton;
    }
}