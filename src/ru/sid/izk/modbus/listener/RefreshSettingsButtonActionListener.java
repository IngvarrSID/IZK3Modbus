package ru.sid.izk.modbus.listener;


import ru.sid.izk.modbus.connection.ModbusReader;
import ru.sid.izk.modbus.entity.Query;
import ru.sid.izk.modbus.frames.IZKModbusGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RefreshSettingsButtonActionListener implements ActionListener {

    private final Query query;
    private final ModbusReader modbusReader;
    private final IZKModbusGUI izkModbusGUI;

    public RefreshSettingsButtonActionListener(Query query, ModbusReader modbusReader, IZKModbusGUI izkModbusGUI) {
        this.query = query;
        this.modbusReader = modbusReader;
        this.izkModbusGUI = izkModbusGUI;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        final JCheckBox queryBox = izkModbusGUI.getQueryBox();
        if (queryBox.isSelected()) queryBox.setSelected(false);
        if (izkModbusGUI.isReadyToWriteRelay()) izkModbusGUI.setReadyToWriteRelay(false);
        try {
            modbusReader.writeModeRegister(0, 1);

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(izkModbusGUI,
                    "Ошибка инициализации " + ex.getMessage(), "Ошибка", JOptionPane.ERROR_MESSAGE);
        }
        try {
            query.querySettings();
            izkModbusGUI.getAddressIZK().setText(String.valueOf(query.getAddressIZK()));
            izkModbusGUI.getSettingRelayBox1().setSelectedIndex(query.getSettingRelay1());
            izkModbusGUI.getSettingRelayBox2().setSelectedIndex(query.getSettingRelay2());
            izkModbusGUI.getSettingRelayBox3().setSelectedIndex(query.getSettingRelay3());
            izkModbusGUI.getSettingRelayBox4().setSelectedIndex(query.getSettingRelay4());
            izkModbusGUI.getSettingRelayBox5().setSelectedIndex(query.getSettingRelay5());
            izkModbusGUI.getSettingRelayBox6().setSelectedIndex(query.getSettingRelay6());
            izkModbusGUI.getSettingRelayBox7().setSelectedIndex(query.getSettingRelay7());
            izkModbusGUI.getSettingRelayBox8().setSelectedIndex(query.getSettingRelay8());
            if(query.getSettingRelay9()< izkModbusGUI.getSettingsRelays().length) izkModbusGUI.getSettingRelayBox9().setSelectedIndex(query.getSettingRelay9());
            if(query.getSettingRelay10()< izkModbusGUI.getSettingsRelays().length) izkModbusGUI.getSettingRelayBox10().setSelectedIndex(query.getSettingRelay10());
            izkModbusGUI.getNumberRelayBox1().setSelectedIndex(query.getNumberRelay1()-1);
            izkModbusGUI.getNumberRelayBox2().setSelectedIndex(query.getNumberRelay2()-1);
            izkModbusGUI.getNumberRelayBox3().setSelectedIndex(query.getNumberRelay3()-1);
            izkModbusGUI.getNumberRelayBox4().setSelectedIndex(query.getNumberRelay4()-1);
            izkModbusGUI.getNumberRelayBox5().setSelectedIndex(query.getNumberRelay5()-1);
            izkModbusGUI.getNumberRelayBox6().setSelectedIndex(query.getNumberRelay6()-1);
            izkModbusGUI.getNumberRelayBox7().setSelectedIndex(query.getNumberRelay7()-1);
            izkModbusGUI.getNumberRelayBox8().setSelectedIndex(query.getNumberRelay8()-1);
            if(query.getNumberRelay9()< izkModbusGUI.getNumbersRelays().length) izkModbusGUI.getNumberRelayBox9().setSelectedIndex(query.getNumberRelay9()-1);
            if(query.getNumberRelay10()< izkModbusGUI.getNumbersRelays().length) izkModbusGUI.getNumberRelayBox10().setSelectedIndex(query.getNumberRelay10()-1);
            izkModbusGUI.getModeRelayBox1().setSelectedIndex(query.getModeRelay1());
            izkModbusGUI.getModeRelayBox2().setSelectedIndex(query.getModeRelay2());
            izkModbusGUI.getModeRelayBox3().setSelectedIndex(query.getModeRelay3());
            izkModbusGUI.getModeRelayBox4().setSelectedIndex(query.getModeRelay4());
            izkModbusGUI.getModeRelayBox5().setSelectedIndex(query.getModeRelay5());
            izkModbusGUI.getModeRelayBox6().setSelectedIndex(query.getModeRelay6());
            izkModbusGUI.getModeRelayBox7().setSelectedIndex(query.getModeRelay7());
            izkModbusGUI.getModeRelayBox8().setSelectedIndex(query.getModeRelay8());
            if (query.getModeRelay9()<izkModbusGUI.getModesRelays().length)izkModbusGUI.getModeRelayBox9().setSelectedIndex(query.getModeRelay9());
            if (query.getModeRelay10()<izkModbusGUI.getModesRelays().length)izkModbusGUI.getModeRelayBox10().setSelectedIndex(query.getModeRelay10());
            if (query.getChannels()[0]) izkModbusGUI.getOneChannelButton().setText("<html><center>Опрос канала 1<br>разрешен</html></center>");
            else izkModbusGUI.getOneChannelButton().setText("нет опроса канала 1");
            if (query.getChannels()[1]) izkModbusGUI.getTwoChannelButton().setText("<html><center>Опрос канала 2<br>разрешен</html></center>");
            else izkModbusGUI.getTwoChannelButton().setText("нет опроса канала 2");
            if (query.getChannels()[2]) izkModbusGUI.getThreeChannelButton().setText("<html><center>Опрос канала 3<br>разрешен</html></center>");
            else izkModbusGUI.getThreeChannelButton().setText("нет опроса канала 3");
            if (query.getChannels()[3]) izkModbusGUI.getFourChannelButton().setText("<html><center>Опрос канала 4<br>разрешен</html></center>");
            else izkModbusGUI.getFourChannelButton().setText("нет опроса канала 4");

            if (!izkModbusGUI.isReadyToWriteRelay()) izkModbusGUI.setReadyToWriteRelay(true);

        } catch (Exception ex){
            ex.printStackTrace();
            JOptionPane.showMessageDialog(izkModbusGUI,
                    "Ошибка чтения" + ex.getMessage(), "Ошибка", JOptionPane.ERROR_MESSAGE);
        }
    }
}