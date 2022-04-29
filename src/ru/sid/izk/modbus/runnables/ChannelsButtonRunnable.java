package ru.sid.izk.modbus.runnables;

import ru.sid.izk.modbus.connection.ModbusReader;
import ru.sid.izk.modbus.entity.Query;
import ru.sid.izk.modbus.frames.IZKModbusGUI;

import javax.swing.*;


import static ru.sid.izk.modbus.utils.BitsReversUtils.bitsReader;

public class ChannelsButtonRunnable implements Runnable{

    private final IZKModbusGUI izkModbusGUI;
    private final Query query;
    private final int channelNumber;
    private final ModbusReader modbusReader;
    private final JButton currentButton;

    public ChannelsButtonRunnable(IZKModbusGUI izkModbusGUI, Query query, int channelNumber, ModbusReader modbusReader, JButton currentButton) {
        this.izkModbusGUI = izkModbusGUI;
        this.query = query;
        this.channelNumber = channelNumber;
        this.modbusReader = modbusReader;
        this.currentButton = currentButton;
    }

    @Override
    public void run() {
    processAction();
    }

    public void processAction(){
        currentButton.setEnabled(false);
        try {
            query.querySettings();
            boolean[] booleans = query.getChannels();
            booleans[channelNumber-1] = !booleans[channelNumber-1];
            StringBuilder bits = new StringBuilder();
            for (int i = 0; i < 16 ; i++) {
                if(booleans.length>i) bits.append(booleans[i] ? "1" : "0");
                else bits.append("0");
            }
            modbusReader.writeModeRegister(3,Integer.parseInt(bitsReader(bits.toString()),2));
            String text = currentButton.getText();
            if (text.equals(String.format("<html><center>Опрос канала %d<br>разрешен</center></html>",channelNumber))) currentButton.setText(String.format("нет опроса канала %d",channelNumber));
            else currentButton.setText(String.format("<html><center>Опрос канала %d<br>разрешен</center></html>",channelNumber));
            currentButton.setEnabled(true);
        } catch (Exception ex){
            ex.printStackTrace();
            JOptionPane.showMessageDialog(izkModbusGUI,
                    "Ошибка инициализации:" + ex.getMessage(), "Ошибка", JOptionPane.ERROR_MESSAGE);
            currentButton.setEnabled(true);
        }
    }
}
