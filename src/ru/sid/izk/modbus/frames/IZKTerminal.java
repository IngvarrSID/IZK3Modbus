package ru.sid.izk.modbus.frames;

import jssc.SerialPortList;
import ru.sid.izk.modbus.connection.MasterModbus;
import ru.sid.izk.modbus.connection.Terminal;
import ru.sid.izk.modbus.utils.DigitFilter;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.text.PlainDocument;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class IZKTerminal extends JFrame {

    private JComboBox<String> comboBoxCOM;
    private JComboBox<String> comboBoxBound;
    private JFormattedTextField IZKCOMAddressField;
    private JButton oKButton;
    private JPanel terminalPanel;
    private final String[] portNames = SerialPortList.getPortNames();
    private final String[] bounds = {"4800", "9600", "14400", "19200", "38400", "57600", "115200"};
    private String comName;
    private String bound;


    public IZKTerminal() {
        initTerminalWindow();
        for (String s : portNames) {
            comboBoxCOM.addItem(s);
        }
        for (String s : bounds) {
            comboBoxBound.addItem(s);
        }
        PlainDocument doc = (PlainDocument) IZKCOMAddressField.getDocument();
        doc.setDocumentFilter(new DigitFilter(3));
        try {
            initTerminalSettings();
        } catch (IOException e){
            e.printStackTrace();

        }
        comboBoxCOM.addActionListener(new ActionListenerCom());
        comboBoxBound.addActionListener(new ActionListenerBound());
        oKButton.addActionListener(new ActionListenerButton());
    }
    private void initTerminalWindow(){
        setContentPane(terminalPanel);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setSize(350, 200);
        setTitle("Терминал");
        File file = new File("icon.png");
        try {
            setIconImage(ImageIO.read(file));
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    private void initTerminalSettings() throws IOException {
        File settings = new File("settings.properties");
        if (settings.exists()){
            FileInputStream in = new FileInputStream(settings);
            Properties properties = new Properties();
            properties.load(in);
            String currentCOM = properties.getProperty("ComPort");
            String currentBound = properties.getProperty("Bound");
            String currentAddress = properties.getProperty("Id");
            for (int i = 0; i <portNames.length ; i++) {
                if (portNames[i].equals(currentCOM)){
                    comName = currentCOM;
                    comboBoxCOM.setSelectedIndex(i);
                    break;
                } else {
                    comName = portNames[0];
                    comboBoxCOM.setSelectedIndex(0);
                }
            }
            for (int i = 0; i <bounds.length ; i++) {
                if (bounds[i].equals(currentBound)){
                    bound = currentBound;
                    comboBoxBound.setSelectedIndex(i);
                }else {
                    bound = bounds[3];
                    comboBoxBound.setSelectedIndex(3);
                }
            }
            IZKCOMAddressField.setText(currentAddress);
        }
        else {
            comName = portNames[0];
            comboBoxCOM.setSelectedIndex(0);
            bound = bounds[3];
            comboBoxBound.setSelectedIndex(3);
            IZKCOMAddressField.setText("80");
        }
    }

    class ActionListenerCom implements java.awt.event.ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int i = ((JComboBox<?>) e.getSource()).getSelectedIndex();
            comName = portNames[i];
        }
    }

    class ActionListenerBound implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int i = ((JComboBox<?>) e.getSource()).getSelectedIndex();
            bound = bounds[i];
        }
    }

    class ActionListenerButton implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Properties properties = new Properties();
            try {
                FileOutputStream out = new FileOutputStream("settings.properties");
                properties.setProperty("ComPort", comName);
                properties.setProperty("BoundRate", bound);
                properties.setProperty("Id", IZKCOMAddressField.getText());
                properties.setProperty("Path",String.format("%s/Documents/Technosensor/Archive",System.getProperty("user.home")));
                properties.store(out, "terminal settings");
            } catch (IOException q) {
                q.printStackTrace();
            }
            Terminal terminal = new Terminal(comName, bound);
            MasterModbus masterModbus = new MasterModbus(terminal, Integer.parseInt(IZKCOMAddressField.getText()));
            dispose();
            new IZKModbusGUI(terminal, masterModbus);
        }
    }
}