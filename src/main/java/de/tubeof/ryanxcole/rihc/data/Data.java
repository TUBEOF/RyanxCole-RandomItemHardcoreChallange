package de.tubeof.ryanxcole.rihc.data;

public class Data {

    public Data() {}

    private boolean isTimerRunning = false;
    private boolean playerDied = false;

    public void setTimerRunning(boolean bol) {
        isTimerRunning = bol;
    }

    public boolean isTimerRunning() {
        return isTimerRunning;
    }

    public void setPlayerDied(boolean bol) {
        playerDied = bol;
    }

    public boolean isPlayerDied() {
        return isTimerRunning;
    }
}
