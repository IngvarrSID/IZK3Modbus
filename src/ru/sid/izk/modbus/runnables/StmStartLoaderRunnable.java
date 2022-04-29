package ru.sid.izk.modbus.runnables;

import ru.sid.izk.modbus.connection.ModbusReader;
import ru.sid.izk.modbus.frames.IZKModbusGUI;
import ru.sid.izk.modbus.listener.EraseTimerActionListener;

import javax.swing.*;

public class StmStartLoaderRunnable implements Runnable{

    private final ModbusReader modbusReader;
    private final IZKModbusGUI izkModbusGUI;

    public StmStartLoaderRunnable(ModbusReader modbusReader, IZKModbusGUI izkModbusGUI) {
        this.modbusReader = modbusReader;
        this.izkModbusGUI = izkModbusGUI;
    }


    @Override
    public void run() {
        processAction();
    }

    private void processAction() {

        IZKModbusGUI newGUI = izkModbusGUI;
                try {
                    int result = JOptionPane.showConfirmDialog(izkModbusGUI, "<html>Переключить скорость связи<br>" + "с блоком ИЗК-3 на 115200</html>", "Предупреждение", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
                    if (result == JOptionPane.YES_OPTION) {
                    modbusReader.writeCoil(44, true);
                    izkModbusGUI.getMaserModbus().setBoundRate115200();
                    izkModbusGUI.dispose();
                    newGUI = new IZKModbusGUI(izkModbusGUI.getMaserModbus().getTerminal(), izkModbusGUI.getMaserModbus());
                        newGUI.getComLabel().setText(String.format("%s на скорости 115200 ИЗК-3 № %s", newGUI.getMaserModbus().getTerminal().getComName(), newGUI.getMaserModbus().getId()));
                }
                    newGUI.getQueryBox().setSelected(false);
                newGUI.getModbusReader().writeCoil(37, true);

                Timer eraseTimer = new Timer(500, new EraseTimerActionListener(newGUI, newGUI.getModbusReader()));
                eraseTimer.start();
                    newGUI.getDataLabel().setText("Идет стирание");
            } catch(Exception ex)
            {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(newGUI,
                        "Ошибка связи " + ex.getMessage(), "Ошибка", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
