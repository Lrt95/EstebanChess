package fr.rphstudio.chess.game.moves;

import fr.rphstudio.chess.game.Board;
import fr.rphstudio.chess.interf.IChess;
import fr.rphstudio.chess.interf.OutOfBoardException;

import java.util.ArrayList;
import java.util.List;

public class Rook implements IMove {

    /**
     * Get the possible rook moves
     * @param position the position of the piece
     * @param board the chessboard
     * @return the list of all possibles positions
     */

    @Override
    public List<IChess.ChessPosition> getPieceMoves(IChess.ChessPosition position, Board board) {

        ArrayList<IChess.ChessPosition> positionPossible = new ArrayList();
        IChess.ChessPosition checkP ;

        try {
            checkP = getPosition(position.x, position.y - 1);
            while (board.getPieces(checkP) == null) {
                positionPossible.add(checkP);
                checkP = getPosition(checkP.x, checkP.y - 1);
            }
            if(board.getPieces(checkP).getPieceColor() != board.getPieces(position).getPieceColor()){
                positionPossible.add(checkP);
            }
        }
        catch (OutOfBoardException e) {
        //e.printStackTrace();
        }

        try{
            checkP=getPosition(position.x, position.y+1);
            while(board.getPieces(checkP)==null) {
                positionPossible.add(checkP);
                checkP=getPosition(checkP.x, checkP.y+1);
            }
            if(board.getPieces(checkP).getPieceColor() != board.getPieces(position).getPieceColor()){
                positionPossible.add(checkP);
            }
        }
        catch (OutOfBoardException e) {
            //e.printStackTrace();
        }

        try{
            checkP=getPosition(position.x-1, position.y);
            while(board.getPieces(checkP)==null) {
                positionPossible.add(checkP);
                checkP=getPosition(checkP.x-1, checkP.y);
            }
            if(board.getPieces(checkP).getPieceColor() != board.getPieces(position).getPieceColor()){
                positionPossible.add(checkP);
            }
        }
        catch (OutOfBoardException e) {
            //e.printStackTrace();
        }

        try{
            checkP=getPosition(position.x+1, position.y);
            while(board.getPieces(checkP)==null) {
                positionPossible.add(checkP);
                checkP=getPosition(checkP.x+1, checkP.y);
            }
            if(board.getPieces(checkP).getPieceColor() != board.getPieces(position).getPieceColor()){
                positionPossible.add(checkP);
            }
        }
            catch (OutOfBoardException e) {
            //e.printStackTrace();
        }

        return positionPossible;
    }

    /**
     * Get the rook position
     * @param x the x position
     * @param y the y position
     * @return the rook position on the chess
     */

    private IChess.ChessPosition getPosition(int x, int y) {
        return  new IChess.ChessPosition(x, y);
    }
}
