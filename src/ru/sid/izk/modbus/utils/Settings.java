package ru.sid.izk.modbus.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Optional;
import java.util.Properties;

public class Settings {

    private String comPort;
    private String boundRate;
    private String id;
    private String path;
    private static final String settingsPath = String.format("%s/Documents/Technosensor/ConfigSU5DV", System.getProperty("user.home"));
    private final String absolutePath = settingsPath;
    private String timeOut;
    private String querySpeed;
    private String elMetroX;
    private String korundX;
    private String tarTab;

    public Settings() {
        try (FileInputStream in = new FileInputStream(settingsPath + "/settings1.properties")) {
            final Properties properties = new Properties();
            properties.load(in);
            this.comPort = properties.getProperty("ComPort");
            this.boundRate = properties.getProperty("BoundRate");
            this.id = properties.getProperty("Id");
            this.path = properties.getProperty("Path");
            this.timeOut = properties.getProperty("Timeout");
            this.querySpeed = properties.getProperty("QuerySpeed");
            this.elMetroX = properties.getProperty("ElMetroX");
            this.korundX = properties.getProperty("KorundX");
            this.tarTab = properties.getProperty("TarTab");
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public Settings(final String comPort, final String boundRate, final String id, final String path, final String timeOut, final String querySpeed, final String elMetroX, final String korundX, final String tarTab) {
        this.comPort = comPort;
        this.boundRate = boundRate;
        this.id = id;
        this.path = path;
        this.timeOut = timeOut;
        this.querySpeed = querySpeed;
        this.elMetroX = elMetroX;
        this.korundX = korundX;
        this.tarTab = tarTab;
    }

    public Settings(final String path) {
        this.path=path;
    }

    public static boolean propertiesFileExists() {
        File f = new File(settingsPath + "/settings1.properties");
        return f.exists();
    }

    public String getComPort() {
        return comPort;
    }

    public String getBoundRate() {
        return boundRate;
    }

    public String getId() {
        return id;
    }

    public String getPath() {
        return path;
    }
    public String getTimeOut() {
        return timeOut;
    }

    public String getQuerySpeed() {
        return querySpeed;
    }

    public String getElMetroX() {
        return elMetroX;
    }

    public String getKorundX() {
        return korundX;
    }

    public String getTarTab() {
        return tarTab;
    }

    public String getAbsolutePath() {
        return absolutePath;
    }

    public void setComPort(String comPort) {
        this.comPort = comPort;
    }

    public void setBoundRate(String boundRate) {
        this.boundRate = boundRate;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTarTab(String tarTab) {
        this.tarTab = tarTab;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setTimeOut(String timeOut) {
        this.timeOut = timeOut;
    }

    public void setQuerySpeed(String querySpeed) {
        this.querySpeed = querySpeed;
    }

    public void setElMetroX(String elMetroX) {
        this.elMetroX = elMetroX;
    }

    public void setKorundX(String korundX) {
        this.korundX = korundX;
    }

    public void storeProperties(final String comments) {

        File dir = new File(settingsPath);
        if (dir.mkdirs()) System.out.println("Путь создан");
        else System.out.println("Путь не создан");

        try (FileOutputStream out = new FileOutputStream(settingsPath + "/settings1.properties")) {
            final Properties properties = new Properties();
            Optional.ofNullable(comPort).ifPresent(v -> properties.setProperty("ComPort", comPort));
            Optional.ofNullable(boundRate).ifPresent(v -> properties.setProperty("BoundRate", boundRate));
            Optional.ofNullable(id).ifPresent(v -> properties.setProperty("Id", id));
            Optional.ofNullable(path).ifPresent(v -> properties.setProperty("Path", path));
            Optional.ofNullable(timeOut).ifPresent(v -> properties.setProperty("Timeout",timeOut));
            Optional.ofNullable(querySpeed).ifPresent(v -> properties.setProperty("QuerySpeed",querySpeed));
            Optional.ofNullable(elMetroX).ifPresent(v -> properties.setProperty("ElMetroX",elMetroX));
            Optional.ofNullable(korundX).ifPresent(v -> properties.setProperty("KorundX",korundX));
            Optional.ofNullable(tarTab).ifPresent(v -> properties.setProperty("TarTab",tarTab));
            properties.store(out, comments);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }
}