package fr.rphstudio.chess.game;

import fr.rphstudio.chess.game.moves.*;
import fr.rphstudio.chess.interf.IChess;

public class Piece {

    private IChess.ChessColor pieceColor;
    private IChess.ChessType pieceType;
    private IMove move;
    private boolean firstMove;

    /**
     * Property of a piece
     * @param pieceColor the color of the piece
     * @param pieceType the type of the piece
     */

    public Piece(IChess.ChessColor pieceColor, IChess.ChessType pieceType) {
        this.pieceColor = pieceColor;
        this.pieceType = pieceType;
        this.firstMove = true;
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

    /**
     * Get the color of the piece
     * @return the piece color
     */

    public IChess.ChessColor getPieceColor() {
        return this.pieceColor;
    }

    /**
     * Get the type of the piece
     * @return the type piece
     */
    public IChess.ChessType getPieceType() {
        return this.pieceType;
    }

    /**
     * Set the color of the piece
     * @param pieceColor the piece color
     */
    public void setPieceColor(IChess.ChessColor pieceColor) {
        this.pieceColor = pieceColor;
    }

    /**
     * Set the type of the piece
     * @param pieceType the type piece
     */
    public void setPieceType(IChess.ChessType pieceType) {
        this.pieceType = pieceType;
    }

    /**
     * Get the piece's first move
     * @return the first move of the piece
     */
    public boolean isFirstMove() {
        return firstMove;
    }

    /**
     * If true, pieces advances of two squares.
     */
    public void setFirstMoveFalse() {
        this.firstMove = false;
    }

    /**
     * Get the move of a piece
     * @return the move of the piece
     */

    public IMove getMove() {
        return move;
    }
}
