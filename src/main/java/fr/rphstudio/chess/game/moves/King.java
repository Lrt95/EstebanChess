package fr.rphstudio.chess.game.moves;

import fr.rphstudio.chess.game.Board;
import fr.rphstudio.chess.interf.IChess;
import fr.rphstudio.chess.interf.OutOfBoardException;

import java.util.ArrayList;
import java.util.List;

public class King implements IMove {
    @Override
    public List<IChess.ChessPosition> getPieceMoves(IChess.ChessPosition position, Board board) {

        IChess.ChessPosition p = new IChess.ChessPosition();
        ArrayList<IChess.ChessPosition> positionPossible = new ArrayList();

        for (int i = -1; i <= 1; i++){
            for (int j = -1; j <= 1 ; j++) {
                try {
                    if (board.getPieces(getPosition(position.x + i, position.y + j)) == null ||
                            board.getPieces(getPosition(position.x + i, position.y + j)).getPieceColor()
                                    != board.getPieces(getPosition(position.x, position.y)).getPieceColor())
                    positionPossible.add(new IChess.ChessPosition(position.x + i, position.y + j));
                } catch (OutOfBoardException e) {
                    e.printStackTrace();
                }

            }
        }
        return positionPossible;
    }
    private IChess.ChessPosition getPosition(int x, int y) {
        return  new IChess.ChessPosition(x, y);
    }
}
