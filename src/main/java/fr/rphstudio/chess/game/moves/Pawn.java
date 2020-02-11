package fr.rphstudio.chess.game.moves;

import fr.rphstudio.chess.game.Board;
import fr.rphstudio.chess.interf.IChess;
import fr.rphstudio.chess.interf.OutOfBoardException;

import java.util.ArrayList;
import java.util.List;

public class Pawn implements IMove  {


    @Override
    public List<IChess.ChessPosition> getPieceMoves(IChess.ChessPosition position, Board board) {

        IChess.ChessPosition p = new IChess.ChessPosition();
        p.x = position.x; p.y = position.y;
        IChess.ChessPosition destinationFinalWhite = new IChess.ChessPosition();
        destinationFinalWhite.x = position.x; destinationFinalWhite.y = position.y-2;
        IChess.ChessPosition destinationFinalBlack = new IChess.ChessPosition();
        destinationFinalBlack.x = position.x; destinationFinalBlack.y = position.y+2;
        ArrayList<IChess.ChessPosition> positionPossible = new ArrayList();


        try {
            if (board.getPieces(p).getPieceColor() == IChess.ChessColor.CLR_WHITE) {
                p.y = p.y-1;

                if (board.getPieces(p)== null){
                    positionPossible.add(new IChess.ChessPosition(position.x, position.y - 1));
                    if (position.y == IChess.BOARD_POS_Y_WHITE_PAWNS && board.getPieces(destinationFinalWhite) == null) {
                        positionPossible.add(new IChess.ChessPosition(position.x, position.y - 2));
                    }

                }
                if (board.getPieces(getPosition(position.x+1, position.y-1)).getPieceColor() == IChess.ChessColor.CLR_BLACK) {
                    positionPossible.add(new IChess.ChessPosition(position.x+1, position.y - 1));
                }
                if (board.getPieces(getPosition(position.x-1, position.y-1)).getPieceColor() == IChess.ChessColor.CLR_BLACK) {
                    positionPossible.add(new IChess.ChessPosition(position.x-1, position.y - 1));
                }

            }
            else if  (board.getPieces(p).getPieceColor() == IChess.ChessColor.CLR_BLACK) {
                p.y = p.y+1;


                if (board.getPieces(p)== null) {
                    if (position.y == IChess.BOARD_POS_Y_BLACK_PAWNS && board.getPieces(destinationFinalBlack) == null) {
                        positionPossible.add(new IChess.ChessPosition(position.x, position.y + 2));

                    }
                    positionPossible.add(new IChess.ChessPosition(position.x, position.y + 1));
                }
                if (board.getPieces(getPosition(position.x+1, position.y+1)).getPieceColor() == IChess.ChessColor.CLR_WHITE) {
                    positionPossible.add(new IChess.ChessPosition(position.x+1, position.y + 1));
                }
                 if (board.getPieces(getPosition(position.x-1, position.y+1)).getPieceColor() == IChess.ChessColor.CLR_WHITE) {
                    positionPossible.add(new IChess.ChessPosition(position.x-1, position.y + 1));

                }

                }

        } catch (OutOfBoardException e) {
            e.printStackTrace();
        }

        return positionPossible;
    }
        private IChess.ChessPosition getPosition(int x, int y) {
            return  new IChess.ChessPosition(x, y);
    }
}
