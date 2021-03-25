package ru.sid.izk.modbus.listener;

import ru.sid.izk.modbus.connection.ModbusReader;
import ru.sid.izk.modbus.entity.Query;
import ru.sid.izk.modbus.frames.IZKModbusGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static ru.sid.izk.modbus.utils.BitsReversUtils.bitsReader;

public class ChannelsButtonActionListener implements ActionListener {
    private final IZKModbusGUI izkModbusGUI;
    private final Query query;
    private final int channelNumber;
    private final ModbusReader modbusReader;

    public ChannelsButtonActionListener(IZKModbusGUI izkModbusGUI, int channelNumber, Query query, ModbusReader modbusReader) {
        this.izkModbusGUI = izkModbusGUI;
        this.channelNumber = channelNumber;
        this.query = query;
        this.modbusReader = modbusReader;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
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
        String text = ((JButton) e.getSource()).getText();
        if (text.equals(String.format("<html><center>Опрос канала %d<br>разрешен</html></center>",channelNumber))) ((JButton) e.getSource()).setText(String.format("нет опроса канала %d",channelNumber));
        else ((JButton) e.getSource()).setText(String.format("<html><center>Опрос канала %d<br>разрешен</html></center>",channelNumber));

     } catch (Exception ex){
            ex.printStackTrace();
            JOptionPane.showMessageDialog(izkModbusGUI,
                    "Ошибка инициализации:" + ex.getMessage(), "Ошибка", JOptionPane.ERROR_MESSAGE);
        }

    }

}
