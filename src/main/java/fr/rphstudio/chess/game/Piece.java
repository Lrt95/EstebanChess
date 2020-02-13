package fr.rphstudio.chess.game;

import fr.rphstudio.chess.game.moves.*;
import fr.rphstudio.chess.interf.IChess;

public class Piece {

    private IChess.ChessColor pieceColor;
    private IChess.ChessType pieceType;
    private IMove move;
    private int counterMove;

    public Piece(IChess.ChessColor pieceColor, IChess.ChessType pieceType) {
        this.pieceColor = pieceColor;
        this.pieceType = pieceType;
        this.counterMove = 0;
        switch(pieceType) {
            case TYP_KING: this.move = new King();
            break;
            case TYP_QUEEN: this.move = new Queen();
            break;
            case TYP_ROOK: this.move = new Rook();
            break;
            case TYP_BISHOP: this.move = new Bishop();
            break;
            case TYP_KNIGHT: this.move = new Knight();
            break;
            case TYP_PAWN: this.move = new Pawn();
            break;
        }
    }

    public IChess.ChessColor getPieceColor() {
        return this.pieceColor;
    }

    public IChess.ChessType getPieceType() {
        return this.pieceType;
    }

    public void setPieceColor(IChess.ChessColor pieceColor) {
        this.pieceColor = pieceColor;
    }

    public void setPieceType(IChess.ChessType pieceType) {
        this.pieceType = pieceType;
    }

    public int getCounterMove() {
        return counterMove;
    }

    public void setCounterMove(int nbMove) {
        this.counterMove = this.counterMove + nbMove;
    }

    public IMove getMove() {
        return move;
    }
}
