package fr.rphstudio.chess.game;

import fr.rphstudio.chess.interf.IChess;

public class LastMove {

    private Piece piece1;
    private IChess.ChessPosition position1P1;
    private IChess.ChessPosition position2P1;
    private Piece piece2;
    private IChess.ChessPosition position1P2;
    private IChess.ChessPosition position2P2;
    private boolean isPieceEaten;
    private long timeBlack;
    private long timeWhite;
    private boolean isSpecialMove;

    public LastMove(Piece piece1,
                    IChess.ChessPosition position1P1,
                    IChess.ChessPosition position2P1,
                    Piece piece2,
                    IChess.ChessPosition position1P2,
                    IChess.ChessPosition position2P2,
                    long timeBlack,
                    long timeWhite,
                    boolean isSpecialMove) {

        this.piece1 = piece1;
        this.position1P1 = position1P1;
        this.position2P1 = position2P1;
        this.position1P2 = position1P2;
        this.position2P2 = position2P2;
        this.piece2 = piece2;
        this.timeBlack = timeBlack;
        this.timeWhite = timeWhite;
        this.isPieceEaten = false;
        this.isSpecialMove = isSpecialMove;
    }

    public Piece getPiece1() {
        return piece1;
    }

    public IChess.ChessPosition getPosition1P1() {
        return position1P1;
    }

    public IChess.ChessPosition getPosition2P1() {
        return position2P1;
    }

    public Piece getPiece2() {
        return piece2;
    }

    public IChess.ChessPosition getPosition1P2() {
        return position1P2;
    }

    public IChess.ChessPosition getPosition2P2() {
        return position2P2;
    }

    public boolean isPieceEaten() {
        return isPieceEaten;
    }

    public void setPieceEaten(boolean pieceEaten) {
        isPieceEaten = pieceEaten;
    }

    public long getTimeBlack() {
        return timeBlack;
    }

    public long getTimeWhite() {
        return timeWhite;
    }

    public boolean isSpecialMove() {
        return isSpecialMove;
    }
}
