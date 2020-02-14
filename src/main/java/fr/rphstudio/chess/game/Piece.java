package fr.rphstudio.chess.game;

import fr.rphstudio.chess.game.moves.*;
import fr.rphstudio.chess.interf.IChess;

public class Piece {

    private IChess.ChessColor pieceColor;
    private IChess.ChessType pieceType;
    private IMove move;
    private int counterMove;

    /**
     * Property of a piece
     * @param pieceColor the color of the piece
     * @param pieceType the type of the piece
     */
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
     *
     * @return the number of move for a piece
     */
    public int getCounterMove() {
        return counterMove;
    }

    /**
     * Increase the counter of move
     * @param nbMove the number of move
     */
    public void setCounterMove(int nbMove) {
        this.counterMove = this.counterMove + nbMove;
    }

    /**
     * Get the move of a piece
     * @return the move of the piece
     */

    public IMove getMove() {
        return move;
    }
}
