package ru.sid.izk.modbus.runnables;

import ru.sid.izk.modbus.entity.Query;
import ru.sid.izk.modbus.frames.IZKModbusGUI;

import javax.swing.*;
import java.util.Timer;
import java.util.TimerTask;

public class TestButtonRunnable implements Runnable{

    private final IZKModbusGUI izkModbusGUI;
    private final Query query;
    private int count;

    public TestButtonRunnable(IZKModbusGUI izkModbusGUI, Query query) {
        this.izkModbusGUI = izkModbusGUI;
        this.query = query;
    }

    @Override
    public void run() {
     //   izkModbusGUI.getLoadGifLabel().setVisible(!izkModbusGUI.getLoadGifLabel().isVisible());
        processAction();
    }

    private void processAction(){
        try {
            izkModbusGUI.getTestButton().setEnabled(false);
            izkModbusGUI.getMode0Field().setText(String.format("%d",query.queryMode0()));
            izkModbusGUI.getTestButton().setEnabled(true);
       //     izkModbusGUI.getLoadGifLabel().setVisible(!izkModbusGUI.getLoadGifLabel().isVisible());

        } catch (Exception ex){
            ex.printStackTrace();
       /* JOptionPane.showMessageDialog(izkModbusGUI,
                "Нет связи. Попробуй еще " + ex.getMessage(), "Ошибка", JOptionPane.ERROR_MESSAGE);*/
            JOptionPane op = new JOptionPane("Нет связи. Попробуй еще " + ex.getMessage(),JOptionPane.ERROR_MESSAGE);
            JDialog dialog = op.createDialog("Ошибка связи");

            java.util.Timer timer = new Timer();

            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    dialog.setVisible(false);
                    dialog.dispose();
                    count++;
                    izkModbusGUI.getTestButton().setEnabled(true);
               //     izkModbusGUI.getLoadGifLabel().setVisible(!izkModbusGUI.getLoadGifLabel().isVisible());
                    if (count<10){

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

    };

}
