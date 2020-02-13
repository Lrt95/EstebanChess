package fr.rphstudio.chess.game;

import fr.rphstudio.chess.interf.IChess;

import javax.swing.*;

public class LastMove {

    private Piece piece1;
    private IChess.ChessPosition position1P1;
    private IChess.ChessPosition position2P1;
    private Piece piece2;
    private IChess.ChessPosition position1P2;
    private IChess.ChessPosition position2P2;
    private boolean isPieceEaten;

    public LastMove(Piece piece1, IChess.ChessPosition position1P1, IChess.ChessPosition position2P1,  Piece piece2,  IChess.ChessPosition position1P2, IChess.ChessPosition position2P2) {
        this.piece1 = piece1;
        this.position1P1 = position1P1;
        this.position2P1 = position2P1;
        this.position1P2 = position1P2;
        this.position2P2 = position2P2;
        this.piece2 = piece2;


        this.isPieceEaten = false;
    }

    public Piece getPiece1() {
        return piece1;
    }

    public void setPiece1(Piece piece1) {
        this.piece1 = piece1;
    }

    public IChess.ChessPosition getPosition1P1() {
        return position1P1;
    }

    public void setPosition1P1(IChess.ChessPosition position1P1) {
        this.position1P1 = position1P1;
    }

    public IChess.ChessPosition getPosition2P1() {
        return position2P1;
    }

    public void setPosition2P1(IChess.ChessPosition position2P1) {
        this.position2P1 = position2P1;
    }

    public Piece getPiece2() {
        return piece2;
    }

    public void setPiece2(Piece piece2) {
        this.piece2 = piece2;
    }

    public IChess.ChessPosition getPosition1P2() {
        return position1P2;
    }

    public void setPosition1P2(IChess.ChessPosition position1P2) {
        this.position1P2 = position1P2;
    }

    public IChess.ChessPosition getPosition2P2() {
        return position2P2;
    }

    public void setPosition2P2(IChess.ChessPosition position2P2) {
        this.position2P2 = position2P2;
    }

    public boolean isPieceEaten() {
        return isPieceEaten;
    }

    public void setPieceEaten(boolean pieceEaten) {
        isPieceEaten = pieceEaten;
    }
}
