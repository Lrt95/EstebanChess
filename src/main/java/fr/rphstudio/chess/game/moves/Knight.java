package fr.rphstudio.chess.game.moves;

import fr.rphstudio.chess.game.Board;
import fr.rphstudio.chess.interf.IChess;
import fr.rphstudio.chess.interf.OutOfBoardException;

import java.util.ArrayList;
import java.util.List;

public class Knight implements IMove {

    /**
     * Get the possibles knight moves
     * @param position the position of the piece
     * @param board the chessboard
     * @return the list of all possibles positions
     */

    public List<IChess.ChessPosition> getPieceMoves(IChess.ChessPosition position, Board board)  {
        ArrayList<IChess.ChessPosition> positionPossible = new ArrayList();

        for (int i = position.x-2; i <= position.x+2;i++){
            for (int j = position.y-2; j <= position.y+2;j++) {
                int dX = Math.abs(position.x-i);
                int dY = Math.abs(position.y-j);
                if (dY + dX == 3) {
                    IChess.ChessPosition p = getPosition(i,j);
                    try {
                        if (board.getPieces(p) == null || board.getPieces(p).getPieceColor()!= board.getPieces(position).getPieceColor()){
                            positionPossible.add(p);
                        }
                    } catch (OutOfBoardException e) {
                        e.printStackTrace();
                    }
                }

            }
        }
    return positionPossible;
    }

    /**
     * Get the knight position
     * @param x the x position
     * @param y the y position
     * @return the knight position on the chess
     */

    private IChess.ChessPosition getPosition(int x, int y) {
        return  new IChess.ChessPosition(x, y);
    }

}

