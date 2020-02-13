package fr.rphstudio.chess.game.moves;

import fr.rphstudio.chess.game.Board;
import fr.rphstudio.chess.interf.IChess;
import fr.rphstudio.chess.interf.OutOfBoardException;

import java.util.ArrayList;
import java.util.List;

public class King implements IMove {



    @Override
    public List<IChess.ChessPosition> getPieceMoves(IChess.ChessPosition position, Board board) {
        ArrayList<IChess.ChessPosition> positionPossible = new ArrayList();

        for (int i = -1; i <= 1; i++){
            for (int j = -1; j <= 1 ; j++) {
                try {
                    if (board.getPieces(getPosition(position.x + i, position.y + j)) == null ||
                            board.getPieces(getPosition(position.x + i, position.y + j)).getPieceColor()
                                    != board.getPieces(position).getPieceColor()) {
                        positionPossible.add(new IChess.ChessPosition(position.x + i, position.y + j));
                    }
                } catch (OutOfBoardException e) {
                    //e.printStackTrace();
                }
            }
        }

        IChess.ChessPosition pTour1 = new IChess.ChessPosition();
        IChess.ChessPosition pTour2 = new IChess.ChessPosition();
        try {
            int smallRoquePossible = 0;
            int bigRoquePossible = 0;
            if (board.getPieces(position).getPieceColor() == IChess.ChessColor.CLR_BLACK) {
                pTour1.x = 0;
                pTour1.y = 0;
                pTour2.x = 7;
                pTour2.y = 0;
            } else if (board.getPieces(position).getPieceColor() == IChess.ChessColor.CLR_WHITE){
                pTour1.x = 0;
                pTour1.y = 7;
                pTour2.x = 7;
                pTour2.y = 7;
            }
            if((board.getPieces(pTour1).getPieceType() == IChess.ChessType.TYP_ROOK && board.getPieces(pTour1).getCounterMove() == 0)){
                for(int i = 1; i < position.x; i++) {
                    if (board.getPieces(new IChess.ChessPosition(i,position.y)) == null) {
                        bigRoquePossible++;
                    }
                }
                if (bigRoquePossible == 3) {
                    positionPossible.add(new IChess.ChessPosition(position.x - 3, position.y));
                }
            }
            if((board.getPieces(pTour2).getPieceType() == IChess.ChessType.TYP_ROOK && board.getPieces(pTour2).getCounterMove() == 0)){
                for(int i = position.x + 1; i < pTour2.x; i++) {
                    if (board.getPieces(new IChess.ChessPosition(i,position.y)) == null) {
                        smallRoquePossible++;
                    }
                }
                if (smallRoquePossible == 2) {
                    positionPossible.add(new IChess.ChessPosition(position.x + 2, pTour2.y));
                }
            }
        } catch (OutOfBoardException | NullPointerException e) {
            //e.printStackTrace();
        }

        return positionPossible;
    }


    private IChess.ChessPosition getPosition(int x, int y) {
        return  new IChess.ChessPosition(x, y);
    }


}
