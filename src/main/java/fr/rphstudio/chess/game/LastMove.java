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

    /**
     * Property of the last move
     * @param piece1 the first piece
     * @param position1P1 the second piece
     * @param position2P1 the second piece
     * @param piece2 the second piece
     * @param position1P2 the second piece
     * @param position2P2 the second piece
     * @param timeBlack the second piece
     * @param timeWhite the second piece
     * @param isSpecialMove the second piece
     */
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

    /**
     *
     * @return the first piece
     */
    public Piece getPiece1() {
        return piece1;
    }

    /**
     *
     * @return first position of piece1
     */
    public IChess.ChessPosition getPosition1P1() {
        return position1P1;
    }

    /**
     *
     * @return second position of piece1
     */
    public IChess.ChessPosition getPosition2P1() {
        return position2P1;
    }

    /**
     *
     * @return  piece2
     */
    public Piece getPiece2() {
        return piece2;
    }


    /**
     *
     * @return first position of piece2
     */
    public IChess.ChessPosition getPosition1P2() {
        return position1P2;
    }

    /**
     *
     * @return second position od piece2
     */
    public IChess.ChessPosition getPosition2P2() {
        return position2P2;
    }

    /**
     *
     * @return if piece is eaten (bool)
     */
    public boolean isPieceEaten() {
        return isPieceEaten;
    }

    /**
     * Set the status of piece eaten
     * @param pieceEaten if piece is eaten
     */
    public void setPieceEaten(boolean pieceEaten) {
        isPieceEaten = pieceEaten;
    }

    /**
     *
     * @return the time for black
     */
    public long getTimeBlack() {
        return timeBlack;
    }

    /**
     *
     * @return the time for white
     */
    public long getTimeWhite() {
        return timeWhite;
    }

    /**
     *
     * @return the state special move (bool)
     */
    public boolean isSpecialMove() {
        return isSpecialMove;
    }
}
