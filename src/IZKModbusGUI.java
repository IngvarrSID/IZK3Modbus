import jssc.SerialPortList;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class IZKModbusGUI extends JFrame {

    private JPanel mainPanel;
    private JTextField textField1;
    private JList list1;
    private JButton terminalButton;
    private String[] portNames;

    public IZKModbusGUI(){
        setContentPane(mainPanel);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(500,300);
        terminalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                IZKTerminal izkTerminal = new IZKTerminal();
            }
        });
        textField1.setText("Выберите COM порт:");
        portNames = SerialPortList.getPortNames();
        list1.setListData(portNames);
        list1.addListSelectionListener(new ListSelectionListener());

    }

    public static void main(String[] args) {
        IZKTerminal izkTerminal = new IZKTerminal();
    }

    class ListSelectionListener implements javax.swing.event.ListSelectionListener {
        private int currentSelected = 99;
        @Override
        public void valueChanged(ListSelectionEvent e) {
            int selected = ((JList<?>)e.getSource()).getSelectedIndex();
            if (currentSelected!=selected) {


            }
        }
    }
}
