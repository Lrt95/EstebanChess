package fr.rphstudio.chess.game;

import fr.rphstudio.chess.interf.IChess;

public class Timer {
    private long timerBlack = 0;
    private long timerWhite = 0;
    private long timeStart = System.currentTimeMillis();


    /**
     *
     * @return the current time
     */
    public long getCurrentTime() {
        return System.currentTimeMillis() - timeStart;
    }

    /**
     * Start a new timer
     */
    public void StartNewTime() {
        timeStart = System.currentTimeMillis();
    }


    /**
     *
     * @param color color of piece
     * @param isPlaying state of round
     * @return the time for the color
     */
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

    /**
     * Add time for new round
     * @param color color of piece
     */
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

    /**
     *
     * @return the actual time for black
     */
    public long getTimerBlack() {
        return timerBlack;
    }

    /**
     *
     * @param timerBlack set time for black
     */
    public void setTimerBlack(long timerBlack) {
        this.timerBlack = timerBlack;
    }

    /**
     *
     * @return the actual time for white
     */
    public long getTimerWhite() {
        return timerWhite;
    }

    /**
     *
     * @param timerWhite set the time for white
     */
    public void setTimerWhite(long timerWhite) {
        this.timerWhite = timerWhite;
    }

}