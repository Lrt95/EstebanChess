package fr.rphstudio.chess.game;

import fr.rphstudio.chess.interf.IChess;

public class LastMove {

    private IChess.ChessPosition position1;
    private IChess.ChessPosition position2;
    private Piece piece1;
    private Piece piece2;
    private boolean isPieceEaten;

    public LastMove(IChess.ChessPosition position1, IChess.ChessPosition getPosition2, Piece piece1, Piece piece2) {
        this.position1 = position1;
        this.position2 = getPosition2;
        this.piece1 = piece1;
        this.piece2 = piece2;
        this.isPieceEaten = false;
    }

    public IChess.ChessPosition getPosition1() {
        return position1;
    }

    public void setPosition1(IChess.ChessPosition position1) {
        this.position1 = position1;
    }

    public IChess.ChessPosition getPosition2() {
        return position2;
    }

    public void setPosition2(IChess.ChessPosition getPosition2) {
        this.position2 = getPosition2;
    }

    public Piece getPiece1() {
        return piece1;
    }

    public void setPiece1(Piece piece1) {
        this.piece1 = piece1;
    }

    public Piece getPiece2() {
        return piece2;
    }

    public void setPiece2(Piece piece2) {
        this.piece2 = piece2;
    }

    public boolean isPieceEaten() {
        return isPieceEaten;
    }

    public void setPieceEaten(boolean pieceEaten) {
        isPieceEaten = pieceEaten;
    }
}
