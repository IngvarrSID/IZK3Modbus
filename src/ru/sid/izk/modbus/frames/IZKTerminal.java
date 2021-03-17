package ru.sid.izk.modbus.frames;

import jssc.SerialPortList;
import ru.sid.izk.modbus.connection.MasterModbus;
import ru.sid.izk.modbus.connection.Terminal;
import ru.sid.izk.modbus.utils.DigitFilter;

import javax.swing.*;
import javax.swing.text.PlainDocument;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class IZKTerminal extends JFrame {

    private JComboBox<String> comboBoxCOM;
    private JComboBox<String> comboBoxBound;
    private JFormattedTextField textField1;
    private JButton oKButton;
    private JPanel terminalPanel;
    private final String[] portNames;
    private final String[] bounds = {"4800", "9600", "14400", "19200", "38400", "57600", "115200"};
    private String comName;
    private String bound;


    public IZKTerminal() {
        setContentPane(terminalPanel);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setSize(350, 200);
        portNames = SerialPortList.getPortNames();
        for (String s : portNames) {
            comboBoxCOM.addItem(s);
        }
        comName = portNames[0];
        comboBoxCOM.setSelectedIndex(0);
        ActionListener aL1 = new ActionListenerCom();
        comboBoxCOM.addActionListener(aL1);
        for (String s : bounds) {
            comboBoxBound.addItem(s);
        }
        bound = bounds[3];
        comboBoxBound.setSelectedIndex(3);
        ActionListener aL2 = new ActionListenerBound();
        comboBoxBound.addActionListener(aL2);
        textField1.setText("80");
        PlainDocument doc = (PlainDocument) textField1.getDocument();
        doc.setDocumentFilter(new DigitFilter(2));


        ActionListener aL3 = new ActionListenerButton();
        oKButton.addActionListener(aL3);

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
                properties.setProperty("Id", textField1.getText());
                properties.store(out, "terminal settings");
            } catch (IOException q) {
                q.printStackTrace();
            }
            Terminal terminal = new Terminal(comName, bound);
            MasterModbus masterModbus = new MasterModbus(terminal, Integer.parseInt(textField1.getText()));
            dispose();
            new IZKModbusGUI(terminal, masterModbus);
        }
    }
}