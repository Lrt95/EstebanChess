package fr.rphstudio.chess.game;

import fr.rphstudio.chess.interf.IChess;

public class Piece {

    private IChess.ChessColor pieceColor;
    private IChess.ChessType pieceType;

    public Piece(IChess.ChessColor pieceColor, IChess.ChessType pieceType) {
        this.pieceColor = pieceColor;
        this.pieceType = pieceType;
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

}
