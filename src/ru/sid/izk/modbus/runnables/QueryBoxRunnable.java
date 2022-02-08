package ru.sid.izk.modbus.runnables;

import ru.sid.izk.modbus.connection.ModbusReader;
import ru.sid.izk.modbus.frames.IZKModbusGUI;

import javax.swing.*;
import java.util.TimerTask;

public class QueryBoxRunnable implements Runnable{

    private final IZKModbusGUI izkModbusGUI;
    private final ModbusReader modbusReader;
    private int count;

    public QueryBoxRunnable(IZKModbusGUI izkModbusGUI, ModbusReader modbusReader) {
        this.izkModbusGUI = izkModbusGUI;
        this.modbusReader = modbusReader;
    }

    @Override
    public void run() {
        processAction();
    }

    public void  processAction() {
        final Timer timer1 = izkModbusGUI.getConnectionTimeoutTimer();
        if (izkModbusGUI.getQueryBox().isSelected()) {
            try {
                modbusReader.writeModeRegister(1, izkModbusGUI.getChannelsBox().getSelectedIndex());
                timer1.start();
                izkModbusGUI.getStatLabel().setText("Идет опрос");
            } catch (Exception e1) {
                e1.printStackTrace();
                if (!izkModbusGUI.isEnableCyclic()) {
                    izkModbusGUI.getQueryBox().setSelected(false);
                    JOptionPane.showMessageDialog(izkModbusGUI,
                            "Ошибка инициализации " + e1.getMessage(), "Ошибка", JOptionPane.ERROR_MESSAGE);
                }
                else {
                    JOptionPane op = new JOptionPane("Ошибка инициализации. Окно закроется автоматически " + e1.getMessage(), JOptionPane.ERROR_MESSAGE);
                    JDialog dialog = op.createDialog("Ошибка связи");

                    izkModbusGUI.getQueryBox().setSelected(false);

                    java.util.Timer timer = new java.util.Timer();

                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            dialog.setVisible(false);
                            dialog.dispose();
                            count++;
                            if (count < 50) {
                                izkModbusGUI.getQueryBox().setSelected(true);
                            } else count = 0;
                        }
                    }, 10000);

                    dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                    dialog.setAlwaysOnTop(false);
                    dialog.setModal(false);
                    dialog.setVisible(true);
                }
            }
        } else {
            timer1.stop();
            System.out.println("Опрос завершен");
            izkModbusGUI.getSensorAddressField().setText("");
            izkModbusGUI.getHumidityField().setText("");
            izkModbusGUI.getTemperatureField().setText("");
            izkModbusGUI.getDensityField().setText("");
            izkModbusGUI.getPeriodField().setText("");
            izkModbusGUI.getCs1Field().setText("");
            izkModbusGUI.getCs2Field().setText("");
            izkModbusGUI.getErrorField().setText("");
            izkModbusGUI.getDataField().setText("");
            izkModbusGUI.getTimeField().setText("");
            izkModbusGUI.getStatLabel().setText("Нет информации");
            izkModbusGUI.getPidErrField().setText("");
            izkModbusGUI.getPidIntField().setText("");
            izkModbusGUI.getPidDifField().setText("");
            izkModbusGUI.getProgressRegulatorBar().setValue(0);
            izkModbusGUI.getRegulatorStatusField().setText("");
        }
    }

}
