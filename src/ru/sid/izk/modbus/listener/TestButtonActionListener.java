package ru.sid.izk.modbus.listener;

import ru.sid.izk.modbus.connection.ModbusReader;
import ru.sid.izk.modbus.entity.Query;
import ru.sid.izk.modbus.frames.IZKModbusGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;

public class TestButtonActionListener implements ActionListener {

    private final IZKModbusGUI izkModbusGUI;
    private final Query query;
    private final ModbusReader reader;
    private int count;

    public TestButtonActionListener(IZKModbusGUI izkModbusGUI, Query query, ModbusReader reader) {
        this.izkModbusGUI = izkModbusGUI;
        this.query = query;
        this.reader = reader;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            izkModbusGUI.getMode0Field().setText(String.format("%d",query.queryMode0()));
        } catch (Exception ex){
            ex.printStackTrace();
           /* JOptionPane.showMessageDialog(izkModbusGUI,
                    "Нет связи. Попробуй еще " + ex.getMessage(), "Ошибка", JOptionPane.ERROR_MESSAGE);*/
            JOptionPane op = new JOptionPane("Нет связи. Попробуй еще " + ex.getMessage(),JOptionPane.ERROR_MESSAGE);
            JDialog dialog = op.createDialog("Ошибка связи");

            Timer timer = new Timer();

            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    dialog.setVisible(false);
                    dialog.dispose();
                    count++;
                    if (count<5){
                        izkModbusGUI.getTestButton().doClick();
                    }
                    else count = 0;
                }
            },10000);

            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setAlwaysOnTop(false);
            dialog.setModal(false);
            dialog.setVisible(true);
        }
    }
}
