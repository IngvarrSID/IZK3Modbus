package ru.sid.izk.modbus;

import ru.sid.izk.modbus.connection.MasterModbus;
import ru.sid.izk.modbus.connection.Terminal;
import ru.sid.izk.modbus.frames.IZKModbusGUI;
import ru.sid.izk.modbus.frames.IZKTerminal;
import ru.sid.izk.modbus.utils.Settings;

import javax.swing.*;

public class Application {

    public static void main(String[] args) {

        initUiManager();
        SwingUtilities.invokeLater(() -> {
            try {
                if (Settings.propertiesFileExists()) {
                    final Settings settings = new Settings();
                    Terminal terminal = new Terminal(settings.getComPort(), settings.getBoundRate(),settings.getTimeOut());
                    MasterModbus masterModbus = new MasterModbus(terminal, Integer.parseInt(settings.getId()));
                    new IZKModbusGUI(terminal, masterModbus);
                } else {
                    new IZKTerminal();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private static void initUiManager() {

        UIManager.put("FileChooser.saveButtonText", "Сохранить");
        UIManager.put("FileChooser.openButtonText", "Открыть");
        UIManager.put("FileChooser.cancelButtonText", "Отмена");
        UIManager.put("FileChooser.fileNameLabelText", "Наименование файла");
        UIManager.put("FileChooser.filesOfTypeLabelText", "Типы файлов");
        UIManager.put("FileChooser.lookInLabelText", "Директория");
        UIManager.put("FileChooser.saveInLabelText", "Сохранить в директории");
        UIManager.put("FileChooser.folderNameLabelText", "Путь директории");
        UIManager.put("OptionPane.yesButtonText", "Да");
        UIManager.put("OptionPane.noButtonText", "Нет");
        UIManager.put("OptionPane.cancelButtonText", "Отмена");
    }
}