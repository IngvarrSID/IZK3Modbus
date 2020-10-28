import jssc.SerialPortList;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.NumberFormat;

public class IZKTerminal extends JFrame {
    private JComboBox<String> comboBoxCOM;
    private JComboBox<String> comboBoxBound;
    private JFormattedTextField textField1;
    private JButton oKButton;
    private JPanel terminalPanel;


    private String[] portNames;
    private String[] bounds = {"4800","9600","14400","19200","38400","57600","115200"};
    private String comName;
    private String bound;


    public IZKTerminal() {
        setContentPane(terminalPanel);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setSize(350,200);
        portNames = SerialPortList.getPortNames();
        for (String s:portNames) {
            comboBoxCOM.addItem(s);
        }
        comName = portNames[0];
        comboBoxCOM.setSelectedIndex(0);
        ActionListener aL1 = new ActionListenerCom();
        comboBoxCOM.addActionListener(aL1);
        for (String s:bounds) {
            comboBoxBound.addItem(s);
        }
        bound = bounds[3];
        comboBoxBound.setSelectedIndex(3);
        ActionListener aL2 = new ActionListenerBound();
        comboBoxBound.addActionListener(aL2);
        NumberFormat number = new DecimalFormat("##0");
        NumberFormatter nF = new NumberFormatter(number);
        DefaultFormatterFactory dFF = new DefaultFormatterFactory(nF);
        textField1.setFormatterFactory(dFF);
        textField1.setText("80");


        ActionListener aL3 = new ActionListenerButton();
        oKButton.addActionListener(aL3);

    }

    class ActionListenerCom implements java.awt.event.ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            int i = ((JComboBox<?>)e.getSource()).getSelectedIndex();
            comName = portNames[i];
        }
    }

    class ActionListenerBound implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            int i = ((JComboBox<?>)e.getSource()).getSelectedIndex();
            bound = bounds[i];
        }
    }

    class ActionListenerButton implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            Terminal terminal = new Terminal(comName,bound);
            new MasterModbus(terminal,Integer.parseInt(textField1.getText()));

        }
    }

}
