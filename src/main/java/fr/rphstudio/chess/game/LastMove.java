package fr.rphstudio.chess.game;

import fr.rphstudio.chess.interf.IChess;

public class LastMove {

    private IChess.ChessPosition position1;
    private IChess.ChessPosition position2;
    private Piece piece1;
    private Piece piece2;

    /**
     * Property of the last move
     * @param position1 the initial position
     * @param getPosition2 the destination position
     * @param piece1 the first piece
     * @param piece2 the second piece
     */
    public LastMove(IChess.ChessPosition position1, IChess.ChessPosition getPosition2, Piece piece1, Piece piece2) {
        this.position1 = position1;
        this.position2 = getPosition2;
        this.piece1 = piece1;
        this.piece2 = piece2;
    }

    /**
     * Get the first position of the piece
     * @return position1
     */

    public IChess.ChessPosition getPosition1() {
        return position1;
    }

    /**
     * Set the first position of the piece
     * @param position1
     */

    public void setPosition1(IChess.ChessPosition position1) {
        this.position1 = position1;
    }

    /**
     * Get the second position of the piece
     * @return position2
     */

    public IChess.ChessPosition getPosition2() {
        return position2;
    }

    /**
     * Get the second position of the piece
     * @param getPosition2
     */
    public void setPosition2(IChess.ChessPosition getPosition2) {
        this.position2 = getPosition2;
    }

    /**
     * Get the first piece
     * @return piece1
     */
    public Piece getPiece1() {
        return piece1;
    }

    /**
     * Set the first piece
     * @param piece1
     */

    public void setPiece1(Piece piece1) {
        this.piece1 = piece1;
    }

    /**
     * Get the second piece
     * @return piece2
     */

    public Piece getPiece2() {
        return piece2;
    }

    /**
     * Set the second piece
     * @param piece2
     */

    public void setPiece2(Piece piece2) {
        this.piece2 = piece2;
    }
}
