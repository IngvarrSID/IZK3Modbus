package ru.sid.izk.modbus.runnables;

import ru.sid.izk.modbus.entity.Query;
import ru.sid.izk.modbus.frames.IZKModbusGUI;

import javax.swing.*;
import java.util.Date;
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
        izkModbusGUI.getPing().setEnabled(false);
        processAction();
    }

    private void processAction(){
        try {
            Date date1 = new Date();
            int mode0 = query.queryMode0();
            Date date2 = new Date();
            Long responseTime = date2.getTime() - date1.getTime();
            JOptionPane.showMessageDialog(izkModbusGUI,
                    String.format("<html><center>Попытка пинга %d.<br>Блок ИЗК-3 ответил за %d мс.<br>Режим записи: %d.</html></center>",count+1,responseTime,mode0), "Подтверждение", JOptionPane.INFORMATION_MESSAGE);
            izkModbusGUI.getPing().setEnabled(true);

        } catch (Exception ex){
            ex.printStackTrace();
            JOptionPane op = new JOptionPane("Нет связи. Окно закроется автоматически для следующей попытки пинга " + ex.getMessage(),JOptionPane.ERROR_MESSAGE);
            JDialog dialog = op.createDialog("Ошибка связи");

            java.util.Timer timer = new Timer();

            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    dialog.setVisible(false);
                    dialog.dispose();
                    count++;
                    if (count<10){
                        izkModbusGUI.getPing().setEnabled(true);
                        izkModbusGUI.getPing().doClick();
                    }
                    else {
                        izkModbusGUI.getPing().setEnabled(true);
                        count = 0;
                    }
                }
            },10000);

            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setAlwaysOnTop(false);
            dialog.setModal(false);
            dialog.setVisible(true);
        }

    };

}
