package de.tubeof.ryanxcole.rihc.data;

public class Data {

    public Data() {}

    private boolean isTimerRunning = false;

    public void setTimerRunning(boolean bol) {
        isTimerRunning = bol;
    }

    public boolean isTimerRunning() {
        return isTimerRunning;
    }
}
