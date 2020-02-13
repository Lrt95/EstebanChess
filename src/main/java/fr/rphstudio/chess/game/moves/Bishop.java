package fr.rphstudio.chess.game.moves;

import fr.rphstudio.chess.game.Board;
import fr.rphstudio.chess.interf.IChess;
import fr.rphstudio.chess.interf.OutOfBoardException;

import java.util.ArrayList;
import java.util.List;

public class Bishop implements IMove {

    /**
     * Get the possible bishop moves
     * @param position position of the piece
     * @param board the chessboard
     * @return the list of all possibles positions
     */

    @Override
    public List<IChess.ChessPosition> getPieceMoves(IChess.ChessPosition position, Board board) {

        ArrayList<IChess.ChessPosition> positionPossible = new ArrayList();
        IChess.ChessPosition checkP;

        try {
            checkP = getPosition(position.x - 1, position.y - 1);
            while (board.getPieces(checkP) == null) {
                positionPossible.add(checkP);
                checkP = getPosition(checkP.x - 1, checkP.y - 1);
            }
            if (board.getPieces(checkP).getPieceColor() != board.getPieces(position).getPieceColor()) {
                positionPossible.add(checkP);
            }
        } catch (OutOfBoardException e) {
            e.printStackTrace();
        }

        try {
            checkP = getPosition(position.x + 1, position.y + 1);
            while (board.getPieces(checkP) == null) {
                positionPossible.add(checkP);
                checkP = getPosition(checkP.x + 1, checkP.y + 1);
            }
            if (board.getPieces(checkP).getPieceColor() != board.getPieces(position).getPieceColor()) {
                positionPossible.add(checkP);
            }
        } catch (OutOfBoardException e) {
            e.printStackTrace();
        }

        try {
            checkP = getPosition(position.x + 1, position.y - 1);
            while (board.getPieces(checkP) == null) {
                positionPossible.add(checkP);
                checkP = getPosition(checkP.x + 1, checkP.y - 1);
            }
            if (board.getPieces(checkP).getPieceColor() != board.getPieces(position).getPieceColor()) {
                positionPossible.add(checkP);
            }
        } catch (OutOfBoardException e) {
            e.printStackTrace();
        }

        try {
            checkP = getPosition(position.x - 1, position.y + 1);
            while (board.getPieces(checkP) == null) {
                positionPossible.add(checkP);
                checkP = getPosition(checkP.x - 1, checkP.y + 1);
            }
            if (board.getPieces(checkP).getPieceColor() != board.getPieces(position).getPieceColor()) {
                positionPossible.add(checkP);
            }
        } catch (OutOfBoardException e) {
            e.printStackTrace();
        }

        return positionPossible;
    }


    /**
     * Get the bishop position
     * @param x
     * @param y
     * @return the bishop position on the chess
     */

    private IChess.ChessPosition getPosition(int x, int y) {
        return  new IChess.ChessPosition(x, y);
    }
}

