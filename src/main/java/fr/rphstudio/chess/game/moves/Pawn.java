package fr.rphstudio.chess.game.moves;

import fr.rphstudio.chess.game.Board;
import fr.rphstudio.chess.interf.IChess;
import fr.rphstudio.chess.interf.OutOfBoardException;

import java.util.ArrayList;
import java.util.List;

public class Pawn implements IMove  {

    /**
     *
     * @param position
     * @param board
     * @return
     */

    @Override
    public List<IChess.ChessPosition> getPieceMoves(IChess.ChessPosition position, Board board) {

        IChess.ChessPosition destinationFinalWhite = new IChess.ChessPosition();
        destinationFinalWhite.x = position.x; destinationFinalWhite.y = position.y-2;
        IChess.ChessPosition destinationFinalBlack = new IChess.ChessPosition();
        destinationFinalBlack.x = position.x; destinationFinalBlack.y = position.y+2;

        ArrayList<IChess.ChessPosition> positionPossible = new ArrayList();
        int dY = 0;

        try {
           if(board.getPieces(position).getPieceColor() == IChess.ChessColor.CLR_WHITE) {
               dY = -1;
           } else {
               dY = 1;
           }

        for (int pX = Math.max(position.x-1, 0) ; pX <= Math.min(position.x+1, 7); pX++) {
            IChess.ChessPosition pCheck = new IChess.ChessPosition(pX, position.y+dY);
            if (Math.abs(pX-position.x) == 0 && board.getPieces(pCheck) == null) {
                positionPossible.add(pCheck);
                IChess.ChessPosition pFirstMove = new IChess.ChessPosition(position.x, position.y + dY * 2);
                if(board.getPieces(position).isFirstMove()==true){
                    if(board.getPieces(pFirstMove) == null) {
                        positionPossible.add(pFirstMove);
                    }
                }
            }
            else if(Math.abs(pX-position.x) == 1) {
                if (board.getPieces(pCheck) != null) {
                    if(board.getPieces(pCheck).getPieceColor() != board.getPieces(position).getPieceColor()) {
                        positionPossible.add(pCheck);
                    }
                }

            }
        }

        } catch (OutOfBoardException | NullPointerException e) {
            e.printStackTrace();
        }

        return positionPossible;
    }

    /**
     *
     * @param x
     * @param y
     * @return
     */

        private IChess.ChessPosition getPosition(int x, int y) {
            return  new IChess.ChessPosition(x, y);
    }
}
