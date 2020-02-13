package fr.rphstudio.chess.game;

import fr.rphstudio.chess.interf.IChess;

public class Timer {
    private long timerBlack = 0;
    private long timerWhite = 0;
    private long timeStart = System.currentTimeMillis();


    public long getCurrentTime() {
        return System.currentTimeMillis() - timeStart;
    }

    public void StartNewTime() {
        timeStart = System.currentTimeMillis();
    }

    public long timerFor(IChess.ChessColor color, boolean isPlaying) {
        long time = 0;

        if (color == IChess.ChessColor.CLR_WHITE) {
            time = timerWhite;
        } else if (color == IChess.ChessColor.CLR_BLACK) {
            time = timerBlack;
        }

        if (isPlaying == true) {
            time += getCurrentTime();
        }
        return time;
    }

    public void newTour(IChess.ChessColor color) {
        switch (color) {
            case CLR_WHITE:
                timerWhite += getCurrentTime();
                break;
            case CLR_BLACK:
                timerBlack += getCurrentTime();
                break;
        }

        StartNewTime();
    }

    public long getTimerBlack() {
        return timerBlack;
    }

    public void setTimerBlack(long timerBlack) {
        this.timerBlack = timerBlack;
    }

    public long getTimerWhite() {
        return timerWhite;
    }

    public void setTimerWhite(long timerWhite) {
        this.timerWhite = timerWhite;
    }
}