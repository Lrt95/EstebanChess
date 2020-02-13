package fr.rphstudio.chess.game;

import fr.rphstudio.chess.interf.IChess;

public class Timer {
    long timerBlack = 0;
    long timerWhite = 0;
    long timeStart = System.currentTimeMillis();


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

        System.out.println("finalTime = " + time);
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


    // getCurrentTime retourne currentTime millis moins timestart


    // getplayerTime (retourne timerB ou W  + getcurrenttime (si playing)


    // startnewturn (color ) : ajoute à timeW/B le current time et remet le starttime avec currenttimemillis


}